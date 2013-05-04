package Examples.Resource_Manager.Using_Java5;

import java.util.concurrent.locks.*;

public class Resource_Manager {
	private final int N;
	private boolean [] available;
	private int available_count;
	
	private Lock mutex = new ReentrantLock();
	private Condition resourse_available = mutex.newCondition();

	
	public Resource_Manager(int dim) {
		N = available_count = dim;
		available = new boolean[N];
		for (int i = 0; i < N; i++) available[i] = true;
		System.out.println(" -----------------------------------------------\n -- Created Resource_Manager --");
	}
	
	public int request () throws InterruptedException  {
		int i = 0;
		try {
			mutex.lock();
			
			System.out.println("  Request for using a resource");
			
			while (available_count == 0) {
				System.out.println("    Waiting for an available resource");
				resourse_available.await();
			}
			
			while (!available[i]) i++;
			available[i] = false;
			available_count--;
			System.out.println("      Resource number " + i + " now busy");
		}
		finally {
			mutex.unlock();
		}
		return i;
	}
	
	public void release (int i) throws InterruptedException  {
		try {
			mutex.lock();
			
			available[i] = true;
			available_count++;
			System.out.println("      Resource number " + i + " now available");
						
			resourse_available.signal();
		}
		finally {
			mutex.unlock();
		}
	}
}
