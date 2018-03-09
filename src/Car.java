import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock; 
public class Car implements Runnable {
    protected int sleepTime;
    protected String[][]road;
    //Set car departure point
    protected int startPointX; 
    protected int startPointY;
    protected ReentrantLock[][]lock;
    protected Condition[][] gridAvailable;
    protected String type;
    protected Report report;
    //Thread start time
    protected long start;
    
	public Car(String[][] road, int startPoint, ReentrantLock[][] lock,Condition[][] gridAvailable,Report report) {
		//Randomly generated each car thread move sleeptime which related to its driving speed
		this.sleepTime = (int)(100 + Math.random()*900);
		this.road= road;
		this.lock = lock;
		this.gridAvailable = gridAvailable;
		start = System.currentTimeMillis();
		this.report =report;
		
		
		
		
	}
	
    // Pass sleeptime to a subclass
	public int getSleepTime() {
		return sleepTime;
		}
	
    
	@Override
    public void run() {}
    	
    	
    }

