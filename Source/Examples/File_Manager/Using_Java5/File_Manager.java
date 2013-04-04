package Examples.File_Manager.Using_Java5;

import java.util.concurrent.locks.*;

public class File_Manager {
	private int active_readers = 0, waiting_readers = 0, waiting_writers = 0;
	private boolean active_writer = false;
	
	private Lock mutex = new ReentrantLock();
	private Condition ok_readers = mutex.newCondition();
	private Condition ok_writer = mutex.newCondition();
	
	public File_Manager () {
		System.out.println(" -----------------------------------------------\n -- Created File_Manager --");
	}
	
	public void start_reading () throws InterruptedException  {
		try {
			mutex.lock();
			System.out.println("  Read request");
			
			if (active_writer || waiting_writers > 0) {
				do {
					System.out.println("    Waiting for readers turn");
					waiting_readers ++;
					ok_readers.await();
					waiting_readers --;
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
				waiting_writers ++;
				ok_writer.await();
				waiting_writers --;
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
			if (waiting_readers > 0) ok_readers.signal();
			else if (waiting_writers > 0) ok_writer.signal();
		}
		finally {
			mutex.unlock();
		}
	}
}
