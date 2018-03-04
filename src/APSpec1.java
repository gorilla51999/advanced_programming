import java.util.Random; 
public class APSpec1{
	
	public static void main(String[] args) {
		String[][]road = new String[10][20];
		for(int i = 0; i < road.length; i ++) {
			   for(int j = 0; j < road[i].length; j++) {
			    road[i][j] = "";
			   }
			   }
		road[0][0]= "o";
//		road[0][1]= "-";
		
		Road roads = new Road(road);
		Car cars = new Car(road);
	    cars.carMove(roads);
     
		
//		try {
//	  		Thread.sleep(1000);
//	    		}
//			catch(InterruptedException e) {
	    		
//	    		};
		
	
//    	int nThreads = 2000;
//		Thread[] threads = new Thread[nThreads];
//		for(int i=0;i<nThreads;i++) {
//			Car car = new Car();
//			threads[i] = new Thread(car);
//			threads[i].start();
//		}
//		try {
//				for(int i=0;i<nThreads;i++) {
//						myThreads[i].join();
//				}
			
//		}catch(InterruptedException e) {
			
//		}
	}
	
	
	
}


