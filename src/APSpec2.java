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
	
	
	@SuppressWarnings("static-access")
	public APSpec2() {
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

		CarController controller = new CarController(road,lock,gridAvailable,row,column,runTimes,drawCarTime);
		controller.setCar(false);
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
}

}
