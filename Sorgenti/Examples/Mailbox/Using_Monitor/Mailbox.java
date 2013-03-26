package Examples.Mailbox.Using_Monitor;

public class Mailbox {
	private final int N;
	private int [] buffer;
	private int count, head, tail;
	
	private FairLock mutex = new FairLock();
	private Condition not_full = mutex.newCondition();			//FairLock.Condition
	private Condition not_empty = mutex.newCondition();			//FairLock.Condition
	
	public Mailbox(int dim) {
		N = dim;
		buffer = new int[dim];
		count = head = tail = 0;
	}
	
	public void send (int message) {
		mutex.lock();
		
		if (count == N) not_full.await();
		buffer[tail] = message;
		tail = (tail + 1) % N;
		count++;
		System.out.println("Inviato " + message);
		not_empty.signal();

		mutex.unlock();
	}
	
	public int receive () {
		int message;
		mutex.lock();

		if (count == 0) not_empty.await();
		message = buffer[head];
		head = (head + 1) % N;
		count--;
		System.out.println("Ricevuto " + message);
		not_full.signal();

		mutex.unlock();
		return message;
	}
}


