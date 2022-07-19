package advent.aoc2016;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;

public class Day19 implements IDay {

	String input = "3012210";
	
	@Override
	public String part1() {
		//stealing presents / present ownership is irrelevant
		//all that matters is having presents or not - basically an alive/dead state
		
		boolean[] elves = new boolean[Integer.parseInt(input)];
		//set all to true
		for(int i = 0; i < elves.length; i++)
			elves[i] = true;
		int index = 0;
		while(true) {
			
			//find next elf that still has a present
			int presentIndex = (index + 1);
			if(presentIndex >= elves.length)
				presentIndex %= elves.length;
			while(!elves[presentIndex]) {
				presentIndex++;
				if(presentIndex >= elves.length)
					presentIndex %= elves.length;
			}
			//take present
			elves[presentIndex] = false;
			
			//if that was the last present, return presentIndex
			if(!hasTrue(elves))
				//indexes are all off by one (starting from zero instead of one)
				return Integer.toString(presentIndex+1);
			
			//find next index to start from
			int nextIndex = (presentIndex + 1);
			if(nextIndex >= elves.length)
				nextIndex %= elves.length;
			while(!elves[nextIndex]) {
				nextIndex++;
				if(nextIndex >= elves.length)
					nextIndex %= elves.length;
			}
			index = nextIndex;
		}
	}
	
	public boolean hasTrue(boolean[] b) {
		for(boolean a : b) {
			if(a)
				return true;
		}
		return false;
	}

	@Override
	public String part2() {
		//all of a sudden, using a big boolean array doesn't work, because "across the circle" explicitly depends on the number of remaining elves
		//other options include using an arraylist for size modification, but with the size of the input, this becomes unfeasible
		//so, we instead solve using a modified version of the traditional numeric algorithm for the Josephus problem (thanks wikipedia!!)
		
		//assume winner is 1 to start with - we move this value as the old winner is knocked out
		int winner = 1;
		//iterate across circle
		for(int i = 1; i < Integer.parseInt(input); i++) {
			//move winner to value next to value just knocked out by i looking across circle
			winner = (winner % i) + 1;
			//make sure it's going left or right of knocked out value as necessary
			if(winner > (i+1) / 2) {
				winner++;
			}
		}
		
		return Integer.toString(winner);
	}

	public static void main(String[] args) {
		DayRunner.run(new Day19());
	}

}
