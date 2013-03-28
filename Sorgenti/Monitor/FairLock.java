package Monitor;

public class FairLock {
	private int urgent_count;
	private Semaphore mutex;
	private Semaphore urgent;
		
	public FairLock () {
		urgent_count = 0;
		mutex = new Semaphore(1);
		urgent = new Semaphore(0);
	}
	
	public void lock () throws InterruptedException  {
		mutex.P();
	}
	
	public void unlock () {
		if (urgent_count > 0) urgent.V();
		else mutex.V();
	}
	
	public Condition newCondition () {
		return new Condition();
	}
	
	public class Condition {
		private int cond_count;
		private Semaphore cond_sem ;
		
		public Condition () {
			cond_count = 0;
			cond_sem = new Semaphore (0);
		}
		
		public void await () throws InterruptedException  {
			cond_count++;
			if (urgent_count > 0) urgent.V();
			else mutex.V();
			cond_sem.P();
			cond_count--;			
		}
		
		public void signal () throws InterruptedException  {
			if (cond_count > 0) {
				urgent_count++;
				cond_sem.V();
				urgent.P();
				urgent_count--;
			}
		}
		
		public boolean empty () {
			return (cond_count == 0);
		}
	};
}
