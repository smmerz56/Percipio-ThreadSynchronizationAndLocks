package com.skillsoft.concurrency;

import java.util.concurrent.TimeUnit;
/*More Notes in FirstTask.java
 	public void run() {
		
		try {
			synchronized(rTwo) {
				System.out.println("Lock aquired on ResourceTwo by "
						+ Thread.currentThread().getName());
				
				rOne.myVar--;
				Thread.sleep(1000);
			}
			System.out.println("Lock on ResourceTwo released by "
					+ Thread.currentThread().getName());
			
			synchronized(rOne) {
				System.out.println("Lock aquired on ResourceOne by "
						+ Thread.currentThread().getName());
				
				rTwo.myVar++;
				Thread.sleep(1000);
				
			}
			System.out.println("Lock on ResourceOne released by "
					+ Thread.currentThread().getName());
			
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
		
	}
 */

public class SecondTask implements Runnable{
	ResourceOne rOne;
	ResourceTwo rTwo;
	
	public SecondTask(ResourceOne r1, ResourceTwo r2) {
		this.rOne = r1;
		this.rTwo = r2;
	}

	@Override
	public void run() {
		
		try {
			boolean rTwoLockAquired = rTwo.rTwoLock.tryLock(10, TimeUnit.SECONDS);//Doesnt block if lock isnt available
			
			if(rTwoLockAquired) {
				System.out.println("Lock aquired on ResourceTwo by "
						+ Thread.currentThread().getName());
				rTwo.myVar--;
				Thread.sleep(5000);
				
				rTwo.rTwoLock.unlock();
				
				System.out.println("Lock on ResourceTwo released by "
						+ Thread.currentThread().getName());
			}	
			
			boolean rOneLockAquired = rOne.rOneLock.tryLock(10, TimeUnit.SECONDS);
			
			if(rOneLockAquired) {
				System.out.println("Lock aquired on ResourceOne by "
						+ Thread.currentThread().getName());
				rOne.myVar++;
				Thread.sleep(1000);
				
				rOne.rOneLock.unlock();
				
				System.out.println("Lock on ResourceOne released by "
						+ Thread.currentThread().getName());
			}
			
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
		
	}
}
