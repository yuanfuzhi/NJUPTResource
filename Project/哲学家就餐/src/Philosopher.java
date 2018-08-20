import java.util.concurrent.Semaphore;

public class Philosopher implements Runnable{
	private int ID;
	static Semaphore room = new Semaphore(4);
	private ChopStick leftStick;
	private ChopStick rightStick;
	public Philosopher(int ID, ChopStick chopStickLeft, ChopStick chopStickRight){
		this.ID = ID;
		this.leftStick = chopStickLeft;
		this.rightStick = chopStickRight;
	}
	
	public void getLeftChopStick(){
		this.leftStick.setAvailable(false);
	}
	
	public void getRightChopStick(){
		this.rightStick.setAvailable(false);
	}
	
	public void dropLeftChopStick(){
		this.leftStick.setAvailable(true);
	}
	
	public void dropRightChopStick(){
		this.rightStick.setAvailable(true);
	}

	
	public int getId(){
		return ID;
	}
	
	public void eat(){
		getLeftChopStick();
		getRightChopStick();
		System.out.println("哲学家"+ this.getId() + "正在用餐。。。");
	}
	
	public void think(){
		System.out.println("哲学家" + this.getId() + "正在思考。。。");
	}
	
	public void finishEat(){
		System.out.println("哲学家" + this.getId() + "用餐结束,正在思考。。。");
		dropLeftChopStick();
		dropRightChopStick();
	}
	
	public void readyToEat(){
		System.out.println("哲学家" + this.getId() + "饿了准备用餐。。。");
	}
	
	public void cannotEat(){
		System.out.println("哲学家" + this.getId() + "缺少筷子，不能用餐，等待。。。");
	}
	
	public void run(){                  
		try{
			
			room.acquire();
			this.readyToEat();
			if(this.leftStick.getSemaphore().availablePermits() == 0 || 
					this.leftStick.getSemaphore().availablePermits() == 0){
				this.cannotEat();
			}
			this.leftStick.getSemaphore().acquire(); 
			System.out.println("哲学家"+this.getId()+"获取左筷子："+this.leftStick.getID());
			Thread.sleep(1000 * 1);               
			this.rightStick.getSemaphore().acquire(); 
			System.out.println("哲学家"+this.getId()+"获取右筷子："+this.rightStick.getID());
			this.eat();
			Thread.sleep(1000 * 2);
			this.finishEat();
			this.leftStick.getSemaphore().release();   
			System.out.println("哲学家"+this.getId()+"放下左筷子："+this.leftStick.getID());
			this.rightStick.getSemaphore().release();  
			System.out.println("哲学家"+this.getId()+"放下右筷子："+this.leftStick.getID());
			
			room.release();
			
		}catch(InterruptedException ex){
			ex.toString();
		}
	}  
}
