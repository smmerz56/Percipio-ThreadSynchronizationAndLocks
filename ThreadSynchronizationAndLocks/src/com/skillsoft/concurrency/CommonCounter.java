package com.skillsoft.concurrency;

/*
 	//without using the synchronized keyword, there will be a race condition
	// and the end value of myNum will be unpredictable.
	
	//If a function is large, then using the synchronized keyword on an entire function
	// can make things inefficient.
	public synchronized void incrementCounter() {
		myNum++;
	}
 */

public class CommonCounter {
		
	private int firstNum = 0;
	private int secondNum = 0;
	
	public void incrementCounter() {
		
		synchronized(this){//the argument is the object on which the synchronization should occur
			firstNum++;
		}
		secondNum++;
	}

	public int getFirstNum() {
		return firstNum;
	}

	public int getSecondNum() {
		return secondNum;
	}
	
}
