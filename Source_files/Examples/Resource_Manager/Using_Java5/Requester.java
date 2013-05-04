package Examples.Resource_Manager.Using_Java5;

public class Requester extends Thread {
	private int request_times;
	private Resource_Manager resource_manager;
	
	public Requester (Resource_Manager resource_manager, int times) {
		request_times = times;
		this.resource_manager = resource_manager;
		
		System.out.println(" -- Created Requester -- ");
		start();
	}

	public void run () {
		int num;
		try {
			for (int i = 0; i < request_times; i++) {
				num = resource_manager.request();
				sleep(100);
				resource_manager.release(num);
			}
		}
		catch (Exception e) {
			System.out.println("\n\t############## ERROR ##############\n\t" + e.toString() + "\n");
		}
	}
}
