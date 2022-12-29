package advent.aoc2022;

import java.util.HashSet;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

public class Day04 implements IDay {

	static String input;

	@Override
	public String part1() {
		int total = 0;
		for(String s : input.split("\n")) {
			String[] pairs = s.split("[,\\-]");
			int a = Integer.parseInt(pairs[0]);
			int b = Integer.parseInt(pairs[1]);
			int c = Integer.parseInt(pairs[2]);
			int d = Integer.parseInt(pairs[3]);
			//determine if either is entirely inside the other
			if((a <= c && b >= d) || (c <= a && d >= b))
				total++;
		}
		return Integer.toString(total);
	}

	@Override
	public String part2() {
		int total = 0;
		for(String s : input.split("\n")) {
			String[] pairs = s.split("[,\\-]");
			int a = Integer.parseInt(pairs[0]);
			int b = Integer.parseInt(pairs[1]);
			int c = Integer.parseInt(pairs[2]);
			int d = Integer.parseInt(pairs[3]);
			//add all numbers in section to set
			HashSet<Integer> overall = new HashSet<>();
			for(int i = a; i <= b; i++)
				overall.add(i);
			for(int i = c; i <= d; i++)
				overall.add(i);
			//if set is smaller than manually calculated range, there were duplicates
			if(overall.size() != ((d - c + 1) + (b - a + 1)))
				total++;
		}
		return Integer.toString(total);
	}

	public static void main(String[] args) {
		input = Input.fetchInput(2022,4);
		DayRunner.run(new Day04());
	}
}
