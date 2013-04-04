package Examples.File_Manager.Using_Monitor;

import Monitor.*;

public class File_Manager {
	private int active_readers = 0;
	private boolean active_writer = false;
	
	private FairLock mutex = new FairLock();
	private FairLock.Condition ok_readers = mutex.newCondition();
	private FairLock.Condition ok_writer = mutex.newCondition();
	
	public File_Manager () {
		System.out.println(" -----------------------------------------------\n -- Created File_Manager --");
	}
	
	public void start_reading () throws InterruptedException  {
		try {
			mutex.lock();
			System.out.println("  Read request");
			
			if (active_writer || !ok_writer.empty()) {
				System.out.println("    Waiting for readers turn");
				ok_readers.await();
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
			
			if (active_writer || active_readers > 0) {
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
			if (!ok_readers.empty()) ok_readers.signal();
			else ok_writer.signal();
		}
		finally {
			mutex.unlock();
		}
	}
}
