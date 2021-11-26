package ge.graphspp.main;

import java.util.ArrayList;

public class Maths {
	
	public static float dist(int x, int y, int x2, int y2) {
		return (float) Math.sqrt((x-x2)*(x-x2) + (y-y2)*(y-y2));
	}
	
	public static int[] arithmeticMean(ArrayList<Node> arr) {
		
		int[] result = new int[2];
		
		int sumX = 0;
		int sumY = 0;
		for(Node i: arr) {
			sumX += i.getX();
			sumY += i.getY();
		}
		
		result[0] = sumX/arr.size();
		result[1] = sumY/arr.size();
		return result;
	}

}
