package donadel;

import java.util.concurrent.Semaphore;

public class Client extends Thread {
	public int id;
	public int maxWait;
	public Semaphore mutex;
	
	public Client(int id, Semaphore mutex, int maxWait) {
		this.id = id;
		this.maxWait = maxWait;
		this.mutex = mutex;
	}
	
	@Override
	public void run() {
		try {
			mutex.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Il cliente " + id + " si siede ed inizia a farsi dipingere il ritratto");
		try {
			Thread.sleep(3000, 10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Il cliente " + id + " ha ricevuto il suo ritratto completato");
		mutex.release();
	}
}
