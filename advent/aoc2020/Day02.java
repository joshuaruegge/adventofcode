package advent.aoc2020;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

public class Day02 implements IDay {

	static String input;
	
	@Override
	public String part1() {
		int validCount = 0;
		for(String s : input.split("\n")) {
			String[] halves = s.split(": ");
			String[] requirements = halves[0].split(" ");
			int min = Integer.parseInt(requirements[0].split("-")[0]);
			int max = Integer.parseInt(requirements[0].split("-")[1]);
			char target = requirements[1].charAt(0);
			int targetCount = 0;
			for(char c : halves[1].toCharArray())
				if(c == target)
					targetCount++;
			if(targetCount <= max && targetCount >= min)
				validCount++;
		}
		return Integer.toString(validCount);
	}

	@Override
	public String part2() {
		int validCount = 0;
		for(String s : input.split("\n")) {
			String[] halves = s.split(": ");
			String[] requirements = halves[0].split(" ");
			int index1 = Integer.parseInt(requirements[0].split("-")[0]);
			int index2 = Integer.parseInt(requirements[0].split("-")[1]);
			char target = requirements[1].charAt(0);
			if(halves[1].charAt(index1-1) == target ^ halves[1].charAt(index2-1) == target)
				validCount++;
		}
		return Integer.toString(validCount);
	}

	public static void main(String[] args) {
		input = Input.fetchInput(2020,2);
		DayRunner.run(new Day02());
	}
}
