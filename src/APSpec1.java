import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
public class APSpec1 implements Runnable{
	public static int row;
	public static int column ;
	public static int runTimes; 
	public static long drawGridTime;
	public static long drawCarTime;
	public static ReentrantLock[][] lock;
	public static Condition[][] gridAvailable;
	public static String[][]road;
	
	
	
	@SuppressWarnings("static-access")
	public APSpec1() {
		row = 10;
		column =20;
		runTimes =2000;
		drawGridTime = 20;
		drawCarTime = (long) (200+Math.random()*200);
		road = new String[row][column];
		this.lock = new ReentrantLock[row][column];
		this.gridAvailable = new Condition[row][column];
		
		for(int i = 0; i< road.length;i++) {
			for(int j =0 ; j < road[i].length;j++) {
				road[i][j]="";
				this.lock[i][j] = new ReentrantLock();
				this.gridAvailable[i][j] = this.lock[i][j].newCondition();
			}
				
		}
		
	}
	
	public void run() {

		int i = 0;
		while( i < runTimes) {
			Car car = null;
			Thread carThread = null;
			try {
				Thread.sleep(drawCarTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			int random  = (int)(0+Math.random()*2);
			if(random == 0) {
			int startPoint = (int)(0+Math.random()*row);
			car = new CarW2E(road,startPoint,lock,gridAvailable);
    			
			}
			else if(random == 1) {
			int startPoint = (int)(0+Math.random()*column);
			car = new CarN2S(road,startPoint,lock,gridAvailable);
			}
			carThread = new Thread(car);
			carThread.start();
			i++;
			}
	}
	
	public static void main(String[] args) {
			
		Thread generator = new Thread(new APSpec1());
		generator.start();
		
	    Road roads = new Road(road);
	    int i = 0;
	    while(i < runTimes) {
	    	try {
				Thread.sleep(drawGridTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	    	roads.printRoad();
	    	i++;
	    	System.out.println("\n->"+i);
	    }
	    System.out.println("part1 done");
			
}

}




