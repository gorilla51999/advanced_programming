import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
	public class CarE2W extends Car{
		public CarE2W(String[][] road, int startPoint, ReentrantLock[][] lock,Condition[][] gridAvailable, Report report) {
			super(road, startPoint, lock, gridAvailable,report);
			this.type = "-";
			this.startPointX = startPoint;
			this.startPointY = this.road[startPointX].length-1;
			this.road = road;
		}
		

		
		public void run() {			
			int y = startPointY;
			for( ;y >= 0; y--) {
				try {
					this.lock[startPointX][y].lock();
					
					while(!road[startPointX][y].equals("")) {
						this.gridAvailable[startPointX][y].await();
					}
					
					Thread.sleep(this.sleepTime);
					if(y < this.road[startPointX].length-1) {
						road[startPointX][y] = this.type;
						road[startPointX][y + 1] = "";
						this.gridAvailable[startPointX][y + 1].signalAll();
					}
					else {
						road[startPointX][y] = this.type;
						this.gridAvailable[startPointX][y].signalAll();
				    }
					
				} catch (InterruptedException e) {
					e.printStackTrace();
				}finally {
					if(y < this.road[startPointX].length-1)
						this.lock[startPointX][y + 1].unlock();
				}
			}
			try {
				Thread.sleep(this.sleepTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			road[startPointX][y + 1] = "";
			this.gridAvailable[startPointX][y + 1].signalAll();
			this.lock[startPointX][y + 1].unlock();
			long end = System.currentTimeMillis();
			double time =(double) end - this.start;
//			this.report.getCarData(time);
			this.report.carData.add(time);
		}
		
	}