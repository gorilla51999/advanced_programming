import java.util.concurrent.locks.*;
public class APSpec1 implements Runnable{
	final static int row =10;
	final static int column = 20;
	final static int runTimes = 2000; 
	public static ReentrantLock[][] lock;
	public static Condition[][] gridAvailable;
	public static String[][]road;
	
	
	@SuppressWarnings("static-access")
	public APSpec1() {
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
	
	public static void main(String[] args) {
		
		road = new String[row][column];
		for(int i = 0; i< road.length;i++) {
			for(int j =0 ; j < road[i].length;j++) {
				road[i][j]="";
			}
				
		}
		
		Thread generator = new Thread(new APSpec1());
		generator.start();
		
	    Road roads = new Road(road);
	    int i = 0;
	    while(i < runTimes) {
	    	try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	    	roads.printRoad();
	    	i++;
	    	System.out.println("\n->"+i);
	    }
			
}

}




