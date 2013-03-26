package Examples.Lettori_Scrittori.Using_Monitor;
  
class File_Manager {
	private int active_readers;
	private boolean active_writer;
	
	private FairLock mutex;
	private FairLock.Condition ok_readers;		//FairLock.Condition
	private FairLock.Condition ok_writer;		//FairLock.Condition
	
	public File_Manager () {
		active_readers = 0;
		active_writer = false;
		mutex = new FairLock();
		ok_readers = mutex.newCondition();
		ok_writer = mutex.newCondition();
	}
	
	public void start_reading () {
		mutex.lock();
		
		if (active_writer || ok_writer.empty()) ok_readers.await();
		active_readers++;
		ok_readers.signal();
	
		mutex.unlock();
	}
	
	public void end_reading () {
		mutex.lock();
		
		active_readers--;
		if (active_readers == 0) ok_writer.signal();

		mutex.unlock();
	}
	
	public void start_writing () {
		mutex.lock();
		
		if (active_writer || active_readers>0) ok_writer.await();
		active_writer = true;

		mutex.unlock();
	}
	
	public void end_writing () {
		mutex.lock();
		
		active_writer = false;
		if (!ok_readers.empty()) ok_readers.signal();
		else ok_writer.signal();
		
		mutex.unlock();
	}
}


