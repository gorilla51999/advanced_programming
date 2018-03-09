// this subclass for the car driving form west to east
import java.util.concurrent.locks.*;
	public class CarW2E extends Car{
		public CarW2E(String[][] road, int startPoint, ReentrantLock[][] lock,Condition[][] gridAvailable,Report report) {
			super(road, startPoint, lock, gridAvailable,report);
			// stand for west-east car
			this.type = "-";
			// get the starting point of a car
			this.startPointX = startPoint;
			// The cars of this direction are begin with first column
			this.startPointY = 0;
			this.road =road;
		}
		// it is just for Spec1
/*		public CarW2E(String[][] road, int startPoint, ReentrantLock[][] lock,Condition[][] gridAvailable) {
			super(road, startPoint, lock, gridAvailable);
			this.type = "-";
			this.startPointX = startPoint;
			this.startPointY = 0;
			
			
		}
*/
		
		public void run() {	
			int y = startPointY;
			for( ;y < road[startPointX].length; y++) {
				try {
					this.lock[startPointX][y].lock();
					
					while(!road[startPointX][y].equals("")) {
						this.gridAvailable[startPointX][y].await();
					}
					
					Thread.sleep(this.sleepTime);
					if(y > 0) {
						road[startPointX][y] = this.type;
						road[startPointX][y - 1] = "";
						this.gridAvailable[startPointX][y - 1].signalAll();
					}
					else {
						road[startPointX][y] = this.type;
						this.gridAvailable[startPointX][y].signalAll();
				    }
					
				} catch (InterruptedException e) {
					e.printStackTrace();
				}finally {
					if(y > 0)
						this.lock[startPointX][y - 1].unlock();
				}
			}
			try {
				Thread.sleep(this.sleepTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			road[startPointX][y - 1] = "";
			this.gridAvailable[startPointX][y - 1].signalAll();
			this.lock[startPointX][y - 1].unlock();
			long end = System.currentTimeMillis();
			double time =(double) end - this.start;
		  
			this.report.carData.add(time);
			
		}
		
	}


