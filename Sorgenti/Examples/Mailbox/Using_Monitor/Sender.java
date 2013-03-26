package Examples.Mailbox.Using_Monitor;

public class Sender extends Thread {
	private int send_times;
	private Mailbox mailbox;
	
	public Sender (Mailbox mailbox) {
		send_times = 1;
		this.mailbox = mailbox;
		
		start();
	}
	public Sender (Mailbox mailbox, int times) {
		send_times = times;
		this.mailbox = mailbox;
		
		start();
	}

	public void run () {
		try {
            for (int i = 0; i < send_times; i++) {
                mailbox.send(i);
                sleep(250);
            }
        }
		catch (Exception e) {
            System.out.println(e.toString());
        }
	}
}