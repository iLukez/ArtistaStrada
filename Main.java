package donadel;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Main {
	static Random rand = new Random();
	public static int idCounter = 1;
	public static int numClients = rand.nextInt(100);
	public static int minIntervalBetweenClients = 1500;
	public static int maxIntervalBetweenClients = 5000;

    public static Semaphore semaphore = new Semaphore(4);
    public static Semaphore mutex = new Semaphore(1);

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	    
		System.out.println("Inizio programma (" + numClients + " clienti)\n");
	    for (int i = 0; i < numClients; i++) {
	    	Client newClient = new Client(idCounter++);
	    	newClient.start();
	    	System.out.println("Cliente " + (idCounter - 1) + " in attesa");
	    	try {
				Thread.sleep(minIntervalBetweenClients, maxIntervalBetweenClients);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	}

}
