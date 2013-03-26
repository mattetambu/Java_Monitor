package Examples.Lettori_Scrittori.Using_Monitor;

public class Writer extends Thread {
	private int write_times;
	private File_Manager file_manager;
	
	public Writer (File_Manager file_manager) {
		write_times = 1;
		this.file_manager = file_manager;
		
		start();
	}
	public Writer (File_Manager file_manager, int times) {
		write_times = times;
		this.file_manager = file_manager;
		
		start();
	}

	public void run () {
		try {
            for (int i = 0; i < write_times; i++) {
                file_manager.start_writing();
                sleep(500);
                file_manager.end_writing();
            }
        }
		catch (Exception e) {
            System.out.println(e.toString());
        }
	}
}