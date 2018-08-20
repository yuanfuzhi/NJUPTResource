import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test {
	public static void main(String[] args){
		Scanner input = new Scanner(System.in);
		menu();
		int choice = input.nextInt();
		while(choice != 1){
			if(choice == 0){
				ChopStick[] chopStick = new ChopStick[5];
				for(int i = 0; i < 5; i ++){
					chopStick[i] = new ChopStick(i); 
				}
				ExecutorService excutor = Executors.newFixedThreadPool(5);
				Philosopher ph0 = new Philosopher(0, chopStick[0], chopStick[1]);
				excutor.execute(new Philosopher(0, chopStick[0], chopStick[1]));
				excutor.execute(new Philosopher(1, chopStick[1], chopStick[2]));
				excutor.execute(new Philosopher(2, chopStick[2], chopStick[3]));
				excutor.execute(new Philosopher(3, chopStick[3], chopStick[4]));
				excutor.execute(new Philosopher(4, chopStick[4], chopStick[0]));
				excutor.shutdown();
				
			}
			choice = input.nextInt();
			menu();
			
		}
	}
	
	public static void menu(){
		System.out.println("0: 演示");
		System.out.println("1: 结束");
	}
	
}
