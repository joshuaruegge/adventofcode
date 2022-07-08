package advent.aoc2015;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;

public class Day25 implements IDay {

	String input = "To continue, please consult the code grid in the manual.  Enter the code at row 2981, column 3075.";
	
	@Override
	public String part1() {
		String[] parts = input.split(" ");
		//start at 0 rather than 1, so decrease each by 1
		int row = Integer.parseInt(parts[16].substring(0,parts[16].length() - 1)) - 1;
		int col = Integer.parseInt(parts[18].substring(0,parts[18].length() - 1)) - 1;
		long start = 20151125;
		int x = 0;
		int y = 0;
		//step up the diagonals, updating the code value each time, until we reach the target location
		//this is much more computationally efficient than recursive calculation, creating a giant array, etc.
		while(true) {
			//move to bottom of next diagonal
			if(y == 0) {
				y = x+1;
				x = 0;
			//continue along diagonal
			} else {
				y -= 1;
				x += 1;
			}
			//update current code
			start = (start * 252533) % 33554393;
			//return code if location reached
			if(x == col && y == row)
				return Long.toString(start);
		}
	}
	
	@Override
	public String part2() {
		return "Merry Christmas!";
	}

	public static void main(String[] args) {
		DayRunner.run(new Day25());
	}

}
