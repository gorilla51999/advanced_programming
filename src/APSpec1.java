import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
public class APSpec1 implements Runnable{
	public static int row;
	public static int column ;
	public static int runTimes; 
	// time gap between print two road 
	public static long drawGridTime;
	// time gap between generate two cars
	public static long drawCarTime;
	// set lock to make a car waiting another car.
	public static ReentrantLock[][] lock;
	public static Condition[][] gridAvailable;
	public static String[][]road;
	//stop generate new car thread 
	public volatile boolean isStop;
    //assist in determining stopping conditions
	public static int index;
	// this for level 2
	public static Report report;
	
	
	@SuppressWarnings("static-access")
	// default
	public APSpec1() {
		row = 10;
		column =20;
		runTimes =2000;
		drawGridTime = 20;
		//traffic flow are random so the the frequency of car thread generated randomly
		drawCarTime = (long) (200+Math.random()*200);
		index= 0;
		road = new String[row][column];
		this.lock = new ReentrantLock[row][column];
		this.gridAvailable = new Condition[row][column];
		report = new Report();
		for(int i = 0; i< road.length;i++) {
			for(int j =0 ; j < road[i].length;j++) {
				road[i][j]="";
				this.lock[i][j] = new ReentrantLock();
				this.gridAvailable[i][j] = this.lock[i][j].newCondition();
			}
				
		}
		
	}
	// Determine whether to stop generating new car threads
	public void setStop() {
		if(index<runTimes)
		   isStop = false;
		else 
		   isStop = true;
			
		
	}
	
	public void run() {

		
		while(!this.isStop) {
			//determine whether to stop every time.
			setStop();
			//initialization
			Car car = null;
			Thread carThread = null;
			try {
				Thread.sleep(drawCarTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
				break;
			}
			//Randomly generate two different directions of cars
			int random  = (int)(0+Math.random()*2);
			if(random == 0) {
			int startPoint = (int)(0+Math.random()*row);
			// generate the car whose driving direction from west to east
			car = new CarW2E(road,startPoint,lock,gridAvailable,report);
    			
			}
			else if(random == 1) {
			int startPoint = (int)(0+Math.random()*column);
			// generate the car whose driving direction from north to south
			car = new CarN2S(road,startPoint,lock,gridAvailable,report);
			}
			carThread = new Thread(car);
			carThread.start();
			
			}
	}
	
	public static void main(String[] args) {
		//it is the main thread for print road 	
		Thread generator = new Thread(new APSpec1());
		generator.start();
		
	    Road roads = new Road(road);
	    while(index < runTimes) {
	    	try {
				Thread.sleep(drawGridTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	    	roads.printRoad();
	    	//record the running times.
	    	index++;
	    	// show the running times.
	    	System.out.println("\n->"+index);
	    }
	    System.out.println("Waitting for "+(Thread.activeCount()-1)+" car threads finished.");
	    // waiting for all car threads finished
	    while(Thread.activeCount()>1) {
			
				}
	    System.out.println("END");
			
}

}




