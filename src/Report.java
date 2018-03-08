import java.util.ArrayList;
import java.util.Collections;

public class Report {
	private double maxTime,minTime, avgTime;
	private double variance;
	private int size;
	public ArrayList<Double> carData;
	
	
	
	
	public Report() {
		carData = new ArrayList<Double>();
		
	}

	public int getSize() {
		size = carData.size();
		return size;
		}
	
	public double getMaxTime() {
		maxTime = Collections.max(carData);
		
		return maxTime;
	}
	
	public double getMinTime() {
		minTime = Collections.min(carData);
		
		return minTime;
	}
	
	public double getAvgTime() {
		double total = 0;
		for(int i = 0;i<getSize();i++) {
			 total += carData.get(i);
			
		}
		avgTime = total/getSize();
		return avgTime;
	}
	
	public double getVariance() {
		double temp = 0;
		
		for(int i = 0;i<getSize();i++) {
			temp += (carData.get(i) - avgTime)*(carData.get(i) - avgTime);
			
			
		}
		
		variance = temp/getSize();
		
		return variance;
	}
	
    public String toString() {
    		String statisticReport = "\n->MAX TIME : "+ String.format("%.2f", getMaxTime()).toString() +"ms"+
    				"\n->MIN TIME: "+ String.format("%.2f", getMinTime()).toString()+"ms" +"\n->AVERAGE TIME:"+
    				String.format("%.2f", getAvgTime()).toString()+"ms"+
    				"\n->VARIANCE : "+ String.format("%.2f", getVariance()).toString();
    		return statisticReport;
    }
	
    public void printReport() {
    		System.out.println(toString());
    	
    }

}