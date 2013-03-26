package Examples.Mailbox.Using_Monitor;

public class Receiver extends Thread {
	private int receive_times;
	private Mailbox mailbox;
	
	public Receiver (Mailbox mailbox) {
		receive_times = 1;
		this.mailbox = mailbox;
		
		start();
	}
	public Receiver (Mailbox mailbox, int times) {
		receive_times = times;
		this.mailbox = mailbox;
		
		start();
	}

	public void run () {
		try {
            for (int i = 0; i < receive_times; i++) {
                mailbox.receive();
                sleep(250);
            }
        }
		catch (Exception e) {
            System.out.println(e.toString());
        }
	}
}