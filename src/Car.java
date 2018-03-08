import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock; 
public class Car implements Runnable {
    protected int sleepTime;
    protected String[][]road;
    protected int startPointX; 
    protected int startPointY;
    protected ReentrantLock[][]lock;
    protected Condition[][] gridAvailable;
    protected String type;
    protected Report report;
    protected long start;
    
	public Car(String[][] road, int startPoint, ReentrantLock[][] lock,Condition[][] gridAvailable,Report report) {
		this.sleepTime = (int)(100 + Math.random()*900);
		this.road= road;
		this.lock = lock;
		this.gridAvailable = gridAvailable;
		start = System.currentTimeMillis();
		this.report =report;
		
		
		
		
	}
	public Car(String[][] road, int startPoint, ReentrantLock[][] lock,Condition[][] gridAvailable) {
		this.sleepTime = (int)(100 + Math.random()*900);
		this.road= road;
		this.lock = lock;
		this.gridAvailable = gridAvailable;
		
	}

	public int getSleepTime() {
		return sleepTime;
		}
	
    
	@Override
    public void run() {}
    	
    	
    }

