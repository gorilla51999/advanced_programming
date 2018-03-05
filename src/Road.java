 public class Road{
	private String[][]road;
	public Road(String[][]road) {
		this.road = road;
		
		
	    
	}
	
	
  
	public void printRoad() {
		
		String s= "\n";
		s += "-----------------------------------------\n";
		for (int i=0; i< road.length; i++) {
			for(int j = 0; j<road[i].length; j++) {
				s += "|";
				if(road[i][j]!="") {
					s +=road[i][j];
				}
				else {
					s += " ";
				}
			}
		   s +="|\n";	    
	}
		 s +="-----------------------------------------";
		 
		 System.out.print(s);
		 
		 
}
}
