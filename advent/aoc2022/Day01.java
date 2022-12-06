package advent.aoc2022;

import java.util.ArrayList;
import java.util.Collections;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

public class Day01 implements IDay {

	static String input;
	
	@Override
	public String part1() {
		int best = 0;
		for(String s : input.split("\n\n")) {
			//total each section, keeping track of maximum
			int total = 0;
			for(String t : s.split("\n"))
				total += Integer.parseInt(t);
			best = Math.max(best,total);
		}
		return Integer.toString(best);
	}

	@Override
	public String part2() {
		//keep track of all totals
		ArrayList<Integer> cals = new ArrayList<Integer>();
		for(String s : input.split("\n\n")) {
			int total = 0;
			for(String t : s.split("\n"))
				total += Integer.parseInt(t);
			cals.add(total);
		}
		//sort least to greatest, then reverse
		Collections.sort(cals);
		Collections.reverse(cals);
		//get total of best 3
		return Integer.toString((cals.get(0) + cals.get(1) + cals.get(2)));
	}

	public static void main(String[] args) {
		input = Input.fetchInput(2022,1);
		DayRunner.run(new Day01());
	}

}
