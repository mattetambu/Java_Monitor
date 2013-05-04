package Monitor;

public class FairLock {
	private int urgent_count = 0;
	private Semaphore mutex = new Semaphore(1);
	private Semaphore urgent = new Semaphore(0);
		
	public FairLock () {
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
		private int cond_count = 0;
		private Semaphore cond_sem = new Semaphore (0);
		
		public Condition () {
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
