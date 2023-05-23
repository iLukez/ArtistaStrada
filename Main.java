package donadel;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Main {
	static Random rand = new Random();
	public static int idCounter = 1;
	public static int numClients = rand.nextInt(100); 
	public static int maxWaitTime = 3500;
	public static int maxIntervalBetweenClients = 5000;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	    Semaphore mutex = new Semaphore(4);
	    
	    for (int i = 0; i < numClients; i++) {
	    	Client newClient = new Client(idCounter++, mutex, maxWaitTime);
	    	newClient.start();
	    	try {
				Thread.sleep(0, maxIntervalBetweenClients);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	}

}
