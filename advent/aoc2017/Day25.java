package advent.aoc2017;

import java.util.HashSet;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;

public class Day25 implements IDay {
	
	//this one would be... an ordeal to program an input-based solution for, as the input is essentially the program instructions
	//so, if necessary, just create your own version of the program from your input
	//this should serve as a good guide/starting point.
	
	@Override
	public String part1() {
		//store indexes of ones in hashset - if not in hashset, is zero
		HashSet<Integer> ones = new HashSet<Integer>();
		int cursor = 0;
		int state = 0;
		//use diagnostic as simple counter
		int diagnostic = 12386363;
		while(diagnostic > 0) {
			switch(state) {
			case 0:
				if(ones.contains(cursor)) {
					ones.remove(cursor);
					cursor--;
					state = 4;
				} else {
					ones.add(cursor);
					cursor++;
					state = 1;
				}
				break;
			case 1:
				if(ones.contains(cursor)) {
					ones.remove(cursor);
					cursor++;
					state = 0;
				} else {
					ones.add(cursor);
					cursor--;
					state = 2;
				}
				break;
			case 2:
				if(ones.contains(cursor)) {
					ones.remove(cursor);
					cursor++;
					//state remains 2
				} else {
					ones.add(cursor);
					cursor--;
					state = 3;
				}
				break;
			case 3:
				if(ones.contains(cursor)) {
					ones.remove(cursor);
					cursor--;
					state = 5;
				} else {
					ones.add(cursor);
					cursor--;
					state = 4;
				}
				break;
			case 4:
				if(ones.contains(cursor)) {
					//keep one
					cursor--;
					state = 2;
				} else {
					ones.add(cursor);
					cursor--;
					state = 0;
				}
				break;
			case 5:
				if(ones.contains(cursor)) {
					//keep one
					cursor++;
					state = 0;
				} else {
					ones.add(cursor);
					cursor--;
					state = 4;
				}
			}
			diagnostic--;
		}
		return Integer.toString(ones.size());
	}

	@Override
	public String part2() {
		return "Merry Christmas!";
	}

	public static void main(String[] args) {
		DayRunner.run(new Day25());
	}

}
