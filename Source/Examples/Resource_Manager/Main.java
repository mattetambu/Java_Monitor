package Examples.Resource_Manager;

import Examples.Resource_Manager.Using_Java5.*;
//import Examples.Resource_Manager.Using_Monitor.*;

public class Main {
    public static void main (String[] args) {
        Resource_Manager resource_manager = new Resource_Manager(2);
        Requester r1 = new Requester(resource_manager, 3);
		Requester r3 = new Requester(resource_manager, 5);
        Requester r2 = new Requester(resource_manager, 4);
    }
};
