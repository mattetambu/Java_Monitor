package Examples.Lettori_Scrittori.Using_Monitor;

public class Writer extends Thread {
	private int write_times;
	private File_Manager file_manager;

	public Writer (File_Manager file_manager, int times) {
		write_times = times;
		this.file_manager = file_manager;
		System.out.println(" -- Created Writer -- ");
		
		start();
	}

	public void run () {
		try {
            for (int i = 0; i < write_times; i++) {
                file_manager.start_writing();
                //sleep(250);
                file_manager.end_writing();
            }
        }
		catch (Exception e) {
            System.out.println("############## ERROR ##############");
			System.out.println(e.toString());
        }
	}
}