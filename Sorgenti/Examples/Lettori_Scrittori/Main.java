//import Examples.Lettori_Scrittori.Using_Java5.*;
import Examples.Lettori_Scrittori.Using_Monitor.*;
import Monitor.*;

public class Main {
    public static void main (String[] args) {
        File_Manager file_manager = new File_Manager();
        Writer w1 = new Writer(file_manager, 4);
        Reader r1 = new Reader(file_manager, 5);
        Reader r2 = new Reader(file_manager, 3);
        Writer w2 = new Writer(file_manager, 6);
    }
}