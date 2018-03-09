// this subclass for the car driving form north to south
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
public class CarN2S extends Car{
	public CarN2S(String[][] road, int startPoint, ReentrantLock[][] lock,Condition[][] gridAvailable,Report report) {
		super(road, startPoint, lock, gridAvailable,report);
		// west-north car
		this.type = "o";
		// get the starting point of a car
		this.startPointY = startPoint;
		// The cars of this direction are begin with first row
		this.startPointX = 0;
		this.road =road;
	}
	
	// it is just for Spec1
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
				 //Determine whether this grid can be passed,if not wait until it free
				while(!road[x][startPointY].equals("")) {
					this.gridAvailable[x][startPointY].await();
				}
				Thread.sleep(this.sleepTime);
				
				if(x > 0) {
					road[x][startPointY] = this.type;
					//clear the last grid
					road[x - 1][startPointY] = "";
					this.gridAvailable[x - 1][startPointY].signalAll();
				}
				else {
					// when car just in start point , it doesn't clear last grid
					road[x][startPointY] = this.type;
					this.gridAvailable[x][startPointY].signalAll();
				}
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}finally {
				if(x > 0)
					//unlock the the grid and make other car can pass it
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
		//record the ending time of a thread
		long end = System.currentTimeMillis();
		//Calculate the time of car pass whole road
		double time =(double) end - this.start;
		//insert it
		this.report.carData.add(time);

			
	}
			
		
		
	}