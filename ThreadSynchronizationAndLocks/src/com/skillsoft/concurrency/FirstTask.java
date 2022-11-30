package com.skillsoft.concurrency;

import java.util.concurrent.TimeUnit;
/* Another way to do it without using the Lock interface. This is called implicit locking
 	public void run() {
		
		try {
			synchronized(rOne) {//...blocking
				System.out.println("Lock aquired on ResourceOne by "
						+ Thread.currentThread().getName());
				
				rOne.myVar++;
				Thread.sleep(1000);
			}
			
			System.out.println("Lock on ResourceOne released by "
					+ Thread.currentThread().getName());
			
			synchronized(rTwo) {
				System.out.println("Lock aquired on ResourceTwo by "
						+ Thread.currentThread().getName());
				
				rTwo.myVar--;
				Thread.sleep(1000);
			}
			
			System.out.println("Lock on ResourceTwo released by "
					+ Thread.currentThread().getName());
			
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
		
	}
 */

/*Using Lock interface within the Resource classes
 public void run() {
		
		try {
			rOne.rOneLock.lock();//explicit locking...blocking
			
			System.out.println("Lock aquired on ResourceOne by "
						+ Thread.currentThread().getName());
			rOne.myVar++;
			Thread.sleep(1000);
			
			rOne.rOneLock.unlock();
			
			System.out.println("Lock on ResourceOne released by "
					+ Thread.currentThread().getName());
			
			rTwo.rTwoLock.lock();
			
			System.out.println("Lock aquired on ResourceOne by "
						+ Thread.currentThread().getName());
			rTwo.myVar--;
			Thread.sleep(1000);
			
			rTwo.rTwoLock.unlock();
			
			System.out.println("Lock on ResourceOne released by "
					+ Thread.currentThread().getName());
			
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
		
	}
 */

/*
 public void run() {
		
		try {
			boolean rOneLockAquired = rOne.rOneLock.tryLock(10, TimeUnit.SECONDS);//Doesnt block if lock isnt available
			
			if(rOneLockAquired) {
				System.out.println("Lock aquired on ResourceOne by "
						+ Thread.currentThread().getName());
				rOne.myVar++;
				Thread.sleep(1000);
				
				rOne.rOneLock.unlock();
				
				System.out.println("Lock on ResourceOne released by "
						+ Thread.currentThread().getName());
			}
		
//			boolean rTwoLockAquired = rTwo.rTwoLock.tryLock();//Doesnt block if lock isnt available
			boolean rTwoLockAquired = rTwo.rTwoLock.tryLock(10, TimeUnit.SECONDS);//Waits for 10 seconds
			
			if(rTwoLockAquired) {
				System.out.println("Lock aquired on ResourceTwo by "
						+ Thread.currentThread().getName());
				rTwo.myVar--;
				Thread.sleep(1000);
				
				rTwo.rTwoLock.unlock();
				
				System.out.println("Lock on ResourceTwo released by "
						+ Thread.currentThread().getName());
			}		
			
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
		
	}
 */

public class FirstTask implements Runnable{
	ResourceOne rOne;
	ResourceTwo rTwo;
	
	public FirstTask(ResourceOne r1, ResourceTwo r2) {
		this.rOne = r1;
		this.rTwo = r2;
	}

	@Override
	public void run() {
		
		try {
			boolean rOneLockAquired = rOne.rOneLock.tryLock(10, TimeUnit.SECONDS);//Doesnt block if lock isnt available
			
			if(rOneLockAquired) {
				System.out.println("Lock aquired on ResourceOne by "
						+ Thread.currentThread().getName());
				rOne.myVar++;
				Thread.sleep(1000);
				
				rOneLockAquired = rOne.rOneLock.tryLock(10, TimeUnit.SECONDS);
				
				if(rOneLockAquired) {
					System.out.println("Second lock aquired on ResourceOne by "
							+ Thread.currentThread().getName());
					rOne.myVar++;
					Thread.sleep(1000);
					
					rOne.rOneLock.unlock();
					
					System.out.println("Second lock on ResourceOne released by "
							+ Thread.currentThread().getName());
				}
				
				rOne.rOneLock.unlock();
				
				System.out.println("Lock on ResourceOne released by "
						+ Thread.currentThread().getName());
			}
		
//			boolean rTwoLockAquired = rTwo.rTwoLock.tryLock();//Doesnt block if lock isnt available
			boolean rTwoLockAquired = rTwo.rTwoLock.tryLock(10, TimeUnit.SECONDS);//Waits for 10 seconds
			
			if(rTwoLockAquired) {
				System.out.println("Lock aquired on ResourceTwo by "
						+ Thread.currentThread().getName());
				rTwo.myVar--;
				Thread.sleep(1000);
				
				rTwo.rTwoLock.unlock();
				
				System.out.println("Lock on ResourceTwo released by "
						+ Thread.currentThread().getName());
			}		
			
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
		
	}
}
