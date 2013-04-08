package Examples.File_Manager.Using_Java5;

public class Reader extends Thread {
	private int read_times;
	private File_Manager file_manager;
	
	public Reader (File_Manager file_manager, int times) {
		read_times = times;
		this.file_manager = file_manager;
		System.out.println(" -- Created Reader -- ");
		
		start();
	}

	public void run () {
		try {
            for (int i = 0; i < read_times; i++) {
                file_manager.start_reading();
                sleep(100);
                file_manager.end_reading();
            }
        }
		catch (Exception e) {
            System.out.println("\n\t############## ERROR ##############\n\t" + e.toString() + "\n");
        }
	}
}
