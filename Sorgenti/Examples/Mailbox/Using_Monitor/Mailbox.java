package Examples.Mailbox.Using_Monitor;

import Monitor.*;

public class Mailbox {
	private final int N;
	private int [] buffer;
	private int count, head, tail;
	
	private FairLock mutex = new FairLock();
	private FairLock.Condition not_full = mutex.newCondition();
	private FairLock.Condition not_empty = mutex.newCondition();

	
	public Mailbox(int dim) {
		N = dim;
		buffer = new int[dim];
		count = head = tail = 0;
		System.out.println(" -----------------------------------------------\n -- Created Mailbox --");
	}
	
	public void send (int message) throws InterruptedException  {
		try {
			mutex.lock();
			
			System.out.println("  Send request of message " + message);
			
			if (count == N) {
				System.out.println("    Wait not-full buffer");
				not_full.await();
			}
			buffer[tail] = message;
			tail = (tail + 1) % N;
			count++;
			System.out.println("      Message " + message + " send");

			not_empty.signal();
		}
		finally {
		mutex.unlock();
		}
	}
	
	public int receive () throws InterruptedException  {
		int message;
		try {
			mutex.lock();
			
			System.out.println("  Receive request");
			
			if (count == 0) {
				System.out.println("    Wait not-empty buffer");
				not_empty.await();
			}
			message = buffer[head];
			head = (head + 1) % N;
			count--;
			System.out.println("      Received message " + message);
						
			not_full.signal();
		}
		finally {
			mutex.unlock();
		}
		return message;
	}
}


