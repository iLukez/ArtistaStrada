package donadel;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Client extends Thread {
	public int id;
	public int maxWait;
	
	public Client(int id) {
		this.id = id;
	}
	
	@Override
	public void run() {
			try {
				if (Main.semaphore.tryAcquire(3, TimeUnit.SECONDS)) {
					System.out.println("Cliente [" + id + "] si è seduto e sta aspettando");
					Main.mutex.acquire();
					System.out.println("Ritratto [" + id + "] iniziato");
					try {
						Thread.sleep(5000, 15000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("Ritratto [" + id + "] finito");
					Main.mutex.release();
					Main.semaphore.release();
				}
				else {
					System.out.println("Il cliente [" + id + "] ha aspettato troppo e se ne è andato");
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}
