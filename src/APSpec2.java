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
	public static int index;
	public static int borderX1, borderX2, borderX3, borderX4;
	public static int borderY1, borderY2, borderY3, borderY4;
	public static Report report;
	public volatile boolean isStop;
	
	
	
	
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
		report = new Report();
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
	
	public void setStop() {
		if(index<runTimes)
		   isStop = false;
		else 
		   isStop = true;
			
		
	}
	
	
	
	public void run() {
		
		while(!isStop) {
			setStop();
			Car car = null;
			
			Thread carThread = null;
			try {
				Thread.sleep(drawCarTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
				break;
			}
			
			
			int random  = (int)(0+Math.random()*4);
			switch(random) {
				case 0 :{ int  startPoint = (int)(borderX1+Math.random()*borderX2);
				               car = new CarW2E(road,startPoint,lock,gridAvailable,report);
						
						      break;
					}
				case 1 :{ int  startPoint = (int)(borderX3+Math.random()*borderX4);
							   car = new CarE2W(road,startPoint,lock,gridAvailable,report);
							   break; 
				    }
				case 2 :{ int startPoint = (int)(borderY1+Math.random()*borderY2);
						      car = new CarN2S(road,startPoint,lock,gridAvailable,report);
						      break;
					}
				case 3 :{ int startPoint = (int)(borderY3+Math.random()*borderY4);
						      car = new CarS2N(road,startPoint,lock,gridAvailable,report);
						      break;
					}


				
				
				
			}
			carThread = new Thread(car);
			carThread.start();
			
		}
		  
	}
	
	public static void main(String[] args) {
			
		Thread generator = new Thread(new APSpec2());
		generator.start();
	    Road roads = new Road(road);
	  
	    while(index < runTimes) {
	    	try {
				Thread.sleep(drawGridTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	    	roads.printRoad();
	    	index++;
	    	System.out.println("\n->"+index);
	    }
	    System.out.println("please waitting for report");
		while(Thread.activeCount()>1) {
		//System.out.println("current thread :" + Thread.activeCount()+" ,please waitting for report");
			}
	    report.printReport();
	    System.out.println("END");
		
		
}
	    
}
