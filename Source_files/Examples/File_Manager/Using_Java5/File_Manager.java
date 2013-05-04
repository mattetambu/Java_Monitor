package Examples.File_Manager.Using_Java5;

import java.util.concurrent.locks.*;

public class File_Manager {
	private int active_readers = 0;
	private boolean active_writer = false;
	
	private ReentrantLock mutex = new ReentrantLock();
	private Condition ok_readers = mutex.newCondition();
	private Condition ok_writer = mutex.newCondition();
	
	public File_Manager () {
		System.out.println(" -----------------------------------------------\n -- Created File_Manager --");
	}
	
	public void start_reading () throws InterruptedException  {
		try {
			mutex.lock();
			System.out.println("  Read request");
			
			if (active_writer || mutex.hasWaiters(ok_writer)) {
				do {
					System.out.println("    Waiting for readers turn");
					ok_readers.await();
				} while (active_writer);
			}
			System.out.println("      Start reading");
			active_readers++;
			ok_readers.signal();
		}
		finally {
			mutex.unlock();
		}
	}
	
	public void end_reading () throws InterruptedException  {
		try {
			mutex.lock();
			active_readers--;
			System.out.println("      End reading - " + active_readers + " readers are still reading");
			
			if (active_readers == 0) ok_writer.signal();
		}
		finally {
			mutex.unlock();
		}
	}
	
	public void start_writing () throws InterruptedException  {
		try {
			mutex.lock();
			System.out.println("  Write request");
			
			while (active_writer || active_readers > 0) {
				System.out.println("    Waiting for my turn");
				ok_writer.await();
			}
			System.out.println("      Start writing");
			active_writer = true;
		}
		finally {
			mutex.unlock();
		}
	}
	
	public void end_writing () throws InterruptedException  {
		try {
			mutex.lock();
			System.out.println("      End writing");
			
			active_writer = false;
			if (mutex.hasWaiters(ok_readers)) ok_readers.signal();
			else if (mutex.hasWaiters(ok_writer)) ok_writer.signal();
		}
		finally {
			mutex.unlock();
		}
	}
}
