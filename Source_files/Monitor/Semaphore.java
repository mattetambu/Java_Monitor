package Monitor;public class Semaphore {	private Queue p_suspended = new Queue();	private int p_count;		public Semaphore (int num) {		p_count = num;	}		public synchronized void P () throws InterruptedException  {		p_count--;		if (p_count < 0) {			p_suspended.insert(Thread.currentThread().getId());			while(p_suspended.contains(Thread.currentThread().getId())) wait();		}	}			public synchronized void V () {		p_count++;
		if (p_count <= 0) {
			p_suspended.extract();
			notifyAll();
		}
	}
}