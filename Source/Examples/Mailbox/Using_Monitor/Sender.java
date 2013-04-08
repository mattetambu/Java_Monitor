package Examples.Mailbox.Using_Monitor;

public class Sender extends Thread {
	private long ID;
	private int send_times;
	private Mailbox mailbox;
	
	public Sender (Mailbox mailbox, int times) {
		send_times = times;
		this.mailbox = mailbox;
		
		System.out.println(" -- Created Sender -- ");
		start();
	}

	public void run () {
		ID = Thread.currentThread().getId();
		try {
            for (int i = 0; i < send_times; i++) {
				mailbox.send((int) ID*(i+1));
                sleep(100);
            }
        }
		catch (Exception e) {
			System.out.println("\n\t############## ERROR ##############\n\t" + e.toString() + "\n");
        }
	}
}
