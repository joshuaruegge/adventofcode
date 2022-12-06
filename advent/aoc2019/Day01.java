package advent.aoc2019;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

public class Day01 implements IDay {

	static String input;

	@Override
	public String part1() {
		//simple - for each line of input, calculate fuel necessary and add it to the total
		int total = 0;
		for(String s : input.split("\n")) {
			total += (Integer.parseInt(s) / 3) - 2;
		}
		return Integer.toString(total);
	}

	@Override
	public String part2() {
		int total = 0;
		for(String s : input.split("\n")) {
			//calculate starting fuel value
			int fuel = (Integer.parseInt(s) / 3) - 2;
		
			//until fuel drops below zero, add current value to total, then update fuel value
			while(fuel > 0) {
				total += fuel;
				fuel = (fuel / 3) - 2;
			}
		}
		return Integer.toString(total);
	}

	public static void main(String[] args) {
		input = Input.fetchInput(2019,1);
		DayRunner.run(new Day01());
	}

}
