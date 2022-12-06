package advent.aoc2016;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

public class Day11 implements IDay {

	static String input;
	
	@Override
	public String part1() {
		//we're using a particularly iffy but much easier algorithm for this solution:
		//the optimal strategy is to simply shift items up a floor one at a time
		//additionally, given elevator rules, it takes 2 * (n-1) - 1 moves to move n items up a floor
		//so, we can theoretically just calculate the total number of moves needed to move all items upwards
		//(praying for no unusual cases that mess up this delicate pattern)
		
		//so first, calculate number of items on each floor
		int[] floorCounts = new int[4];
	
		//now, count items from input
		String[] lines = input.split("\n");
		for(int i = 0; i < 4; i++) {
			String line = lines[i];
			//number of items is, coincidentally, number of commas plus number of "and"s
			int commas = 0;
			for(char c : line.toCharArray())
				if(c == ',')
					commas++;
			if(line.contains("and"))
				commas++;
			if(commas == 1)
				commas++;
			floorCounts[i] = commas;
		}
		int totalMoves = 0;
		for(int i = 0; i < 3; i++) {
			//increment moves by moves necessary
			totalMoves += 2 * (floorCounts[i] - 1) - 1;
			//move all items up a floor
			floorCounts[i+1] += floorCounts[i];
			floorCounts[i] = 0;
		}
		return Integer.toString(totalMoves);
	}

	@Override
	public String part2() {
		//we're using a particularly iffy but much easier algorithm for this solution:
		//the optimal strategy is to simply shift items up a floor one at a time
		//additionally, given elevator rules, it takes 2 * (n-1) - 1 moves to move n items up a floor
		//so, we can theoretically just calculate the total number of moves needed to move all items upwards
		//(praying for no unusual cases that mess up this delicate pattern)
		
		//so first, calculate number of items on each floor
		int[] floorCounts = new int[4];
	
		//now, count items from input
		String[] lines = input.split("\n");
		for(int i = 0; i < 4; i++) {
			String line = lines[i];
			//number of items is, coincidentally, number of commas plus number of "and"s
			int commas = 0;
			for(char c : line.toCharArray())
				if(c == ',')
					commas++;
			if(line.contains("and"))
				commas++;
			if(commas == 1)
				commas++;
			floorCounts[i] = commas;
		}
		//part 2 modification - 4 extra items on first floor
		floorCounts[0] += 4;
		
		int totalMoves = 0;
		for(int i = 0; i < 3; i++) {
			totalMoves += 2 * (floorCounts[i] - 1) - 1;
			floorCounts[i+1] += floorCounts[i];
			floorCounts[i] = 0;
		}
		return Integer.toString(totalMoves);
	}

	public static void main(String[] args) {
		input = Input.fetchInput(2016,11);
		DayRunner.run(new Day11());
	}

}
