package Examples.File_Manager;

import Examples.File_Manager.Using_Java5.*;
//import Examples.File_Manager.Using_Monitor.*;

public class Main {
    public static void main (String[] args) {
        File_Manager file_manager = new File_Manager();
        Reader r1 = new Reader(file_manager, 5);
        Reader r2 = new Reader(file_manager, 4);
		Writer w1 = new Writer(file_manager, 4);
		Reader r3 = new Reader(file_manager, 3);
        Writer w2 = new Writer(file_manager, 6);
    }
};
