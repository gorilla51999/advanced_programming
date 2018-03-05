import java.util.concurrent.locks.*; 
public class Car implements Runnable {
    protected int sleepTime;
    protected String[][]road;
    protected int startPointX; 
    protected int startPointY;
    protected ReentrantLock[][]lock;
    protected Condition[][] gridAvailable;
    protected String type;
    
	public Car(String[][] road, int startPoint, ReentrantLock[][] lock,Condition[][] gridAvailable) {
		this.sleepTime = (int)(100 + Math.random()*900);
		this.road= road;
		this.lock = lock;
		this.gridAvailable = gridAvailable;
		
		
		
	}

	public int getSleepTime() {
		return sleepTime;
		
	}
	public void carMove() throws InterruptedException {}

	@Override
    public void run() {}
    	
    	
    }

