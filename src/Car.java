import java.util.Random; 
public class Car implements Runnable {
	private String carName;
	private int x, y;
    private long sleepTime;
	private Road roads;
    private String[][]road;
	public Car(String[][]road) {
		this.road = road;
		
		
	}

	
	public void carMove(Road roads) {
		this.roads = roads;
		for(int i=0;i<road.length;i++) {
			for(int j=0;j<road[i].length;j++) {
				try {
			  		Thread.sleep(200);
			    		}
					catch(InterruptedException e) {
			    		
			    		};
				
				if(road[i][j].equals("o")) {
				    if(i+1<road.length) {
					road[i+1][j]=road[i][j];
					road[i][j]="";
				    }
				    else
				    		break;
				}
				else if(road[i][j].equals("-")) {
					if(j+1<road[i].length) {
					road[i][j+1]=road[i][j];
					road[i][j]="";
					}
					else 
						break;
				}
				roads.printRoad();
			}
			
		}
		
	
	}
//    private void setSleepTime(){
//        sleepTime = (long)(Math.random()*1000);
        
//    }
    public long getSleepTime() {
//    	    setSleepTime();
    		return (long)(Math.random()*1000);
//    }
 
    
    
//    public void run() {
//    	try {
//    		Thread.sleep(getSleepTime());
//    	}
//    	catch(InterruptedException e) {
    		
 //   	}
    	
    	
    }

}
