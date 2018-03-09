import java.util.ArrayList;
import java.util.Collections;

public class Report {
	private double maxTime,minTime, avgTime;
	private double variance;
	private int size;
	public ArrayList<Double> carData;
	
	
	
	//default
	public Report() {
		carData = new ArrayList<Double>();
		
	}
    //get the quantity of car threads
	public int getSize() {
		size = carData.size();
		return size;
		}
	//get the max time of a car finished the road
	public double getMaxTime() {
		maxTime = Collections.max(carData);
		
		return maxTime;
	}
	//get the min time of a car finished the road
	public double getMinTime() {
		minTime = Collections.min(carData);
		
		return minTime;
	}
	// get the mean time of a car finished the road
	public double getAvgTime() {
		double total = 0;
		for(int i = 0;i<getSize();i++) {
			 total += carData.get(i);
			
		}
		avgTime = total/getSize();
		return avgTime;
	}
	// get the variance
	public double getVariance() {
		double temp = 0;
		
		for(int i = 0;i<getSize();i++) {
			temp += (carData.get(i) - avgTime)*(carData.get(i) - avgTime);
			
			
		}
		
		variance = temp/getSize();
		
		return variance;
	}
	//draw the report
    public String toString() {
    		String statisticReport = "\n->MAX TIME : "+ String.format("%.2f", getMaxTime()).toString() +"ms"+
    				"\n->MIN TIME: "+ String.format("%.2f", getMinTime()).toString()+"ms" +"\n->AVERAGE TIME:"+
    				String.format("%.2f", getAvgTime()).toString()+"ms"+
    				"\n->VARIANCE : "+ String.format("%.2f", getVariance()).toString();
    		return statisticReport;
    }
	//print the report
    public void printReport() {
    		System.out.println(toString());
    	
    }

}