import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class CarController {
	final   int runTimes = 2000;
	public  ReentrantLock[][] lock;
	public  Condition[][] gridAvailable;
	public  String[][]road;
	public CarController(String[][]road,ReentrantLock[][] lock,Condition[][] gridAvailable) {
		this.road = road;
		this.lock = lock;
		this.gridAvailable= gridAvailable;
	}
	public void setCar() {
	int i = 0;
	while( i < runTimes) {
		Car car = null;
		Thread carThread = null;
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		int random  = (int)(0+Math.random()*2);
		if(random == 0) {
			int startPoint = (int)(0+Math.random()*10);
			car = new CarW2E(road,startPoint,lock,gridAvailable);
    			
		}
		else if(random == 1) {
			int startPoint = (int)(0+Math.random()*20);
			car = new CarN2S(road,startPoint,lock,gridAvailable);
		}
		carThread = new Thread(car);
		carThread.start();
		i++;
	}		
	
	}
}
