import java.util.concurrent.Semaphore;

public class ChopStick {
	private int ID;
	private boolean available;
	private Semaphore semaphore = new Semaphore(1);
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public boolean isAvailable() {
		return available;
	}
	public void setAvailable(boolean available) {
		this.available = available;
	}
	public Semaphore getSemaphore() {
		return semaphore;
	}
	public void setSemaphore(Semaphore semaphore) {
		this.semaphore = semaphore;
	}
	
	public ChopStick(int ID){
		this.ID = ID;
		this.available = true;
		this.semaphore = new Semaphore(1);
	}
}
