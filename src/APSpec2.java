import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
public class APSpec2 implements Runnable{
	public static int row;
	public static int column;
	public static int runTimes;
	public static long drawGridTime;
	public static long drawCarTime;
	public static ReentrantLock[][] lock;
	public static Condition[][] gridAvailable;
	public static String[][]road;
	public static int borderX1, borderX2, borderX3, borderX4;
	public static int borderY1, borderY2, borderY3, borderY4;
	
	
	@SuppressWarnings("static-access")
	public APSpec2() {
		row = 10;
		column =20;
		runTimes =2000;
		drawGridTime = 20;
		borderX1 = 0;
		borderX2 = 5;
		borderX3 = 5;
		borderX4 = 5;
		borderY1 = 0;
		borderY2 = 10;
		borderY3 = 10;
		borderY4 = 10;
		//drawCarTime = (long) (200+Math.random()*200);
		drawCarTime = 500;
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
			
			
			int random  = (int)(0+Math.random()*4);
			switch(random) {
				case 0 :{ int  startPoint = (int)(borderX1+Math.random()*borderX2);
				               car = new CarW2E(road,startPoint,lock,gridAvailable);
						
						      break;
					}
				case 1 :{ int  startPoint = (int)(borderX3+Math.random()*borderX4);
							   car = new CarE2W(road,startPoint,lock,gridAvailable);
							   break; 
				    }
				case 2 :{ int startPoint = (int)(borderY1+Math.random()*borderY2);
						      car = new CarN2S(road,startPoint,lock,gridAvailable);
						      break;
					}
				case 3 :{ int startPoint = (int)(borderY3+Math.random()*borderY4);
						      car = new CarS2N(road,startPoint,lock,gridAvailable);
						      break;
					}


				
				
				
			}
			carThread = new Thread(car);
			carThread.start();
			i++;
		}
	}
	
	public static void main(String[] args) {
			
		Thread generator = new Thread(new APSpec2());
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
		System.out.println("part2 done!");
		System.out.println("activeCount= " + Thread.activeCount());
}
	    
}
