package Examples.Lettori_Scrittori.Using_Monitor;

import Monitor.*;

public class File_Manager {
	private int active_readers;
	private boolean active_writer;
	
	private FairLock mutex;
	private FairLock.Condition ok_readers;
	private FairLock.Condition ok_writer;
	
	public File_Manager () {
		active_readers = 0;
		active_writer = false;
		mutex = new FairLock();
		ok_readers = mutex.newCondition();
		ok_writer = mutex.newCondition();
		System.out.println(" -----------------------------------------------\n -- Created File_Manager --");
	}
	
	public void start_reading () throws InterruptedException  {
		try {
			mutex.lock();
			System.out.println("  Reading request");
			
			if (active_writer || !ok_writer.empty()) {
				System.out.println("    Wait readers turn");
				ok_readers.await();
			}
			System.out.println("      Start Reading");
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
			System.out.println("      End Reading - " + active_readers + " readers are still reading");
			
			if (active_readers == 0) ok_writer.signal();
		}
		finally {
			mutex.unlock();
		}
	}
	
	public void start_writing () throws InterruptedException  {
		try {
			mutex.lock();
			System.out.println("  Writing request");
			
			if (active_writer || active_readers > 0) {
				System.out.println("    Wait my turn");
				ok_writer.await();
			}
			System.out.println("      Start Writing");
			active_writer = true;
		}
		finally {
			mutex.unlock();
		}
	}
	
	public void end_writing () throws InterruptedException  {
		try {
			mutex.lock();
			System.out.println("      End Writing");
			
			active_writer = false;
			if (!ok_readers.empty()) ok_readers.signal();
			else ok_writer.signal();
		}
		finally {
			mutex.unlock();
		}
	}
}


