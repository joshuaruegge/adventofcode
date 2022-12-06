package advent.aoc2021;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

public class Day02 implements IDay {

	static String input;
	
	@Override
	public String part1() {
		int horiz = 0;
		int depth = 0;
		for(String s : input.split("\n")) {
			String[] parts = s.split(" ");
			if(parts[0].equals("forward"))
				horiz += Integer.parseInt(parts[1]);
			else if(parts[0].equals("down"))
				depth += Integer.parseInt(parts[1]);
			else
				depth -= Integer.parseInt(parts[1]);
		}
		return Integer.toString(horiz * depth);
	}

	@Override
	public String part2() {
		int horiz = 0;
		int depth = 0;
		int aim = 0;
		for(String s : input.split("\n")) {
			String[] parts = s.split(" ");
			if(parts[0].equals("forward")) {
				horiz += Integer.parseInt(parts[1]);
				depth += Integer.parseInt(parts[1]) * aim;
			}
			else if(parts[0].equals("down"))
				aim += Integer.parseInt(parts[1]);
			else
				aim -= Integer.parseInt(parts[1]);
		}
		return Integer.toString(horiz * depth);
	}

	public static void main(String[] args) {
		input = Input.fetchInput(2021,2);
		DayRunner.run(new Day02());
	}
}
