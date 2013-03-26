package Examples.Lettori_Scrittori.Using_Monitor;

public class Reader extends Thread {
	private int read_times;
	private File_Manager file_manager;
	
	public Reader (File_Manager file_manager) {
		read_times = 1;
		this.file_manager = file_manager;
		
		start();
	}
	public Reader (File_Manager file_manager, int times) {
		read_times = times;
		this.file_manager = file_manager;
		
		start();
	}

	public void run () {
		try {
            for (int i = 0; i < read_times; i++) {
                file_manager.start_reading();
                sleep(500);
                file_manager.end_reading();
            }
        }
		catch (Exception e) {
            System.out.println(e.toString());
        }
	}
}