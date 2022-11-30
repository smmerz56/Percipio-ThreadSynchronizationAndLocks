package com.skillsoft.concurrency;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ResourceTwo {
	public int myVar = 100;
	
	Lock rTwoLock = new ReentrantLock();//remove for deadlock example
}
