import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class CarController {
	public   int runTimes;
	public  ReentrantLock[][] lock;
	public  Condition[][] gridAvailable;
	public  String[][]road;
	public 	int row, column;
	public  long drawCarTime;
	public CarController(String[][]road,ReentrantLock[][] lock,Condition[][] gridAvailable,int row,int column,int runTimes,long drawCarTime) {
		this.road = road;
		this.lock = lock;
		this.gridAvailable= gridAvailable;
		this.row =row;
		this.column =column;
		this.runTimes = runTimes;
		this.drawCarTime = drawCarTime;
	}
	public void setCar(boolean spec1) {
    boolean b = spec1; 
	int i = 0;
	while( i < runTimes) {
		Car car = null;
		Thread carThread = null;
		try {
			Thread.sleep(drawCarTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		if(b==true) {
			int random  = (int)(0+Math.random()*2);
			if(random == 0) {
			int startPoint = (int)(0+Math.random()*row);
			car = new CarW2E(road,startPoint,lock,gridAvailable);
    			
			}
			else if(random == 1) {
			int startPoint = (int)(0+Math.random()*column);
			car = new CarN2S(road,startPoint,lock,gridAvailable);
			}
		}
		else if(b==false){
			int random  = (int)(0+Math.random()*4);
			switch(random) {
				case 0 :{ int  startPoint = (int)(0+Math.random()*row/2);
			               car = new CarW2E(road,startPoint,lock,gridAvailable);
					
					      break;
				}
				case 1 :{ int  startPoint = (int)(row/2+Math.random()*row/2);
						   car = new CarE2W(road,startPoint,lock,gridAvailable);
						   break; 
			    }
				case 2 :{ int startPoint = (int)(0+Math.random()*column/2);
					      car = new CarN2S(road,startPoint,lock,gridAvailable);
					      break;
				}
				case 3 :{ int startPoint = (int)(column/2+Math.random()*column/2);
					      car = new CarS2N(road,startPoint,lock,gridAvailable);
					      break;
				}


			
			}
			
		}
		carThread = new Thread(car);
		carThread.start();
		i++;
	}	

	
	}
	
}
