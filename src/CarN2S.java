import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
public class CarN2S extends Car{
	public CarN2S(String[][] road, int startPoint, ReentrantLock[][] lock,Condition[][] gridAvailable,Report report) {
		super(road, startPoint, lock, gridAvailable,report);
		this.type = "o";
		this.startPointY = startPoint;
		this.startPointX = 0;
		this.road =road;
	}
	
/*	public CarN2S(String[][] road, int startPoint, ReentrantLock[][] lock,Condition[][] gridAvailable) {
		super(road, startPoint, lock, gridAvailable);
		this.type = "o";
		this.startPointY = startPoint;
		this.startPointX = 0;
	}
*/
	
	public void run() {
		int x = startPointX;
		for( ; x < road.length; x++) {
			try {
				this.lock[x][startPointY].lock();
				 
				while(!road[x][startPointY].equals("")) {
					this.gridAvailable[x][startPointY].await();
				}
				Thread.sleep(this.sleepTime);
				if(x > 0) {
					road[x][startPointY] = this.type;
					road[x - 1][startPointY] = "";
					this.gridAvailable[x - 1][startPointY].signalAll();
				}
				else {
					road[x][startPointY] = this.type;
					this.gridAvailable[x][startPointY].signalAll();
				}
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}finally {
				if(x > 0)
					this.lock[x - 1][startPointY].unlock();
			}
		}
		try {
			Thread.sleep(this.sleepTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		road[x - 1][startPointY] = "";
		this.gridAvailable[x - 1][startPointY].signalAll();
		this.lock[x - 1][startPointY].unlock();
		long end = System.currentTimeMillis();
		double time =(double) end - this.start;
//	    this.report.getCarData(time);
		this.report.carData.add(time);

			
	}
			
		
		
	}