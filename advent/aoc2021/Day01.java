package advent.aoc2021;

import java.util.Arrays;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

public class Day01 implements IDay {

	static String input;
	
	@Override
	public String part1() {
		int[] depths = Arrays.stream(input.split("\n")).mapToInt(Integer::parseInt).toArray();
		int counter = 0;
		for(int i = 1; i < depths.length; i++) {
			if(depths[i] > depths[i-1])
				counter++;
		}
		return Integer.toString(counter);
	}

	@Override
	public String part2() {
		int[] depths = Arrays.stream(input.split("\n")).mapToInt(Integer::parseInt).toArray();
		int counter = 0;
		for(int i = 0; i < depths.length - 3; i++) {
			int window = depths[i] + depths[i+1] + depths[i+2];
			int nextWindow = depths[i+1] + depths[i+2] + depths[i+3];
			if(window < nextWindow)
				counter++;
		}
		return Integer.toString(counter);
	}

	public static void main(String[] args) {
		input = Input.fetchInput(2021,1);
		DayRunner.run(new Day01());
	}
}
