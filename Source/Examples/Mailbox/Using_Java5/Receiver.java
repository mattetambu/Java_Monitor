package Examples.Mailbox.Using_Java5;

public class Receiver extends Thread {
	private int receive_times;
	private Mailbox mailbox;
	
	public Receiver (Mailbox mailbox, int times) {
		receive_times = times;
		this.mailbox = mailbox;
		
		System.out.println(" -- Created Receiver -- ");
		start();
	}

	public void run () {
		int messagge;
		try {
            for (int i = 0; i < receive_times; i++) {
				messagge = mailbox.receive();
                sleep(100);
            }
        }
		catch (Exception e) {
            System.out.println("############## ERROR ##############");
			System.out.println(e.toString());
        }
	}
}
