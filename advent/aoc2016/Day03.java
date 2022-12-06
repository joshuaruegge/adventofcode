package advent.aoc2016;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

public class Day03 implements IDay {

	static String input;
	
	@Override
	public String part1() {
		//valid counter
		int valid = 0;
		//iterate over lines
		for(String s : input.split("\n")) {
			//trim whitespace. i dont like whitespace
			s = s.trim();
			//split into numbers
			//" +" matches any number of 1 or more whitespaces, and helps avoid number length differences
			String[] parts = s.split(" +");
			
			int a = Integer.parseInt(parts[0]);
			int b = Integer.parseInt(parts[1]);
			int c = Integer.parseInt(parts[2]);
			
			//by summing all 3 then subtracting the largest, we get the sum of the smaller sides
			//if this is smaller than the max, then the triangle is invalid
			//this avoids the need to determine which are larger and smaller before calculating
			if((a + b + c - Math.max(a, Math.max(b, c))) > Math.max(a,Math.max(b, c))) {
				valid++;
			}
			
		}
		return Integer.toString(valid);
	}

	@Override
	public String part2() {
		int valid = 0;
		//chunk out input in groups of 3
		String[] lines = input.split("\n");
		for(int i = 0; i < lines.length; i+=3) {
			String[] partsA = lines[i].trim().split(" +");
			String[] partsB = lines[i+1].trim().split(" +");
			String[] partsC = lines[i+2].trim().split(" +");
			int[][] triangles = new int[3][];
			for(int j = 0; j < 3; j++) {
				triangles[j] = new int[] {Integer.parseInt(partsA[j]), Integer.parseInt(partsB[j]), Integer.parseInt(partsC[j])};
			}
			
			//check each triangle like above
			for(int[] j : triangles) {
				int a = j[0];
				int b = j[1];
				int c = j[2];
				
				if((a + b + c - Math.max(a, Math.max(b, c))) > Math.max(a,Math.max(b, c))) {
					valid++;
				}
			}	
		}
		return Integer.toString(valid);
	}

	public static void main(String[] args) {
		input = Input.fetchInput(2016,3);
		DayRunner.run(new Day03());
	}

}
