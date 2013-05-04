package Examples.Mailbox.Using_Monitor;

public class Main {
	public static void main (String[] args) {
		Mailbox mailbox = new Mailbox(3);
		Receiver r1 = new Receiver(mailbox, 4);
		Sender s1 = new Sender(mailbox, 3);
		Sender s2 = new Sender(mailbox, 5);
		Receiver r2 = new Receiver(mailbox, 4);
	}
};
