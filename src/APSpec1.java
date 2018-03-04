import java.util.Random; 
public class APSpec1{
	
	public static void main(String[] args) {
		String[][]road = new String[10][20];
		
		for(int i = 0; i < road.length; i ++) {
			   for(int j = 0; j < road[i].length; j++) {
			    road[i][j] = "";
			   }
			   }
//		road[0][0]= "o";
//		road[0][1]= "-";
		
//		Road roads = new Road(road);
//		Car cars = new Car(road);
//	    cars.carMove(roads);
     
		
//		try {
//	  		Thread.sleep(1000);
//	    		}
//			catch(InterruptedException e) {
	    		
//	    		};
		
        int minX = 0 ;
        int maxX = 10;
        int minY = 0;
        int maxY = 20;
        
        
      	int nThreads = 100;
		Thread[] threads = new Thread[nThreads];
		Car[]car = new Car[nThreads];
		
		for(int i=0;i<nThreads;i++) {
			int x1 = (int)Math.round(Math.random()*(maxX-minX)+minX);
	        int y1= (int)Math.round(Math.random()*(maxY-minY)+minY);
	        int x2  = (int)Math.round(Math.random()*(maxX-minX)+minX);
	        int y2= (int)Math.round(Math.random()*(maxY-minY)+minY);
	        
	        road[x1][y1]= "o";
	        road[x2][y2]= "-";
			car[i] = new Car(road);
			threads[i] = new Thread(car[i]);
			
			threads[i].start();
			
			Road roads = new Road(road);
			car[i].carMove(roads);
			
		}
		 
		
			
		
	}
	
	
	
}


