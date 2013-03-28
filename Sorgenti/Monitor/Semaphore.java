<<<<<<< HEAD
package Monitor;public class Semaphore {	private Queue p_suspended;	private int p_count;		public Semaphore () {		p_suspended = new Queue();		p_count = 0;	}	public Semaphore (int num) {		p_suspended = new Queue();		p_count = num;	}		public synchronized void V () {		if (!p_suspended.empty()) notifyAll();		else p_count++;	}		public synchronized void P () {		try {			if (p_count == 0) {				p_suspended.insert(Thread.currentThread().getId());				do					wait();				while(Thread.currentThread().getId() != p_suspended.first());				p_suspended.extract();			}			else p_count--;			}		catch (Exception e) {			System.out.println(e.toString());		}	}}
=======
package Monitor;public class Semaphore {	private Queue p_suspended;	private int p_count;		public Semaphore (int num) {		p_suspended = new Queue();		p_count = num;	}		public synchronized void P () throws InterruptedException  {		p_count--;		if (p_count < 0) {			p_suspended.insert(Thread.currentThread().getId());			while(p_suspended.contains(Thread.currentThread().getId())) wait();		}	}		public synchronized void V () {		p_count++;		if (p_count <= 0) {			p_suspended.extract();			notifyAll();		}	}}
>>>>>>> Completed all the classes and all exsamples
