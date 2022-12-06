package advent.aoc2018;

import java.util.HashSet;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

public class Day01 implements IDay {

	static String input;
	
	@Override
	public String part1() {
		int frequency = 0;
		for(String s : input.split("\n")) {
			frequency += Integer.parseInt(s);
		}
		return Integer.toString(frequency);
	}

	@Override
	public String part2() {
		int frequency = 0;
		HashSet<Integer> frequencies = new HashSet<Integer>();
		frequencies.add(0);
		//loop over input as necessary till input is found
		while(true) {
			for(String s : input.split("\n")) {
				frequency += Integer.parseInt(s);
				//if add fails, frequency is already present in set - break out of loop
				if(!frequencies.add(frequency))
					return Integer.toString(frequency);
				
			}
		}
	}

	public static void main(String[] args) {
		input = Input.fetchInput(2018,1);
		DayRunner.run(new Day01());
	}

}
