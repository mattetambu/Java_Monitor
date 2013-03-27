package Examples.Mailbox;

//import Examples.Mailbox.Using_Java5.*;
import Examples.Mailbox.Using_Monitor.*;
import Monitor.*;

public class Main {
    public static void main (String[] args) {
        Mailbox mailbox = new Mailbox(3);
        Sender s1 = new Sender(mailbox, 4);
        Sender s2 = new Sender(mailbox, 6);
        Receiver r1 = new Receiver(mailbox, 5);
        Receiver r2 = new Receiver(mailbox, 3);
    }
}