package advent.aoc2019;

import java.util.ArrayList;
import java.util.Arrays;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;
import advent.utilities.utils2019.IntCodeComputer;

public class Day09 implements IDay {

	static String input;
	
	@Override
	public String part1() {
		//parse input to list, initialize computer, input, run, and grab last value (after selftest results)
		ArrayList<Long> program = new ArrayList<Long>(Arrays.stream(input.split(",")).map(x -> Long.parseLong(x)).toList());
		IntCodeComputer i = new IntCodeComputer(program);
		i.input(1);
		i.run();
		return Long.toString(i.output.get(i.output.size() - 1));
	}

	@Override
	public String part2() {
		//same as above, but input alternate value, and get only output
		ArrayList<Long> program = new ArrayList<Long>(Arrays.stream(input.split(",")).map(x -> Long.parseLong(x)).toList());
		IntCodeComputer i = new IntCodeComputer(program);
		i.input(2);
		i.run();
		return Long.toString(i.output());
	}

	public static void main(String[] args) {
		input = Input.fetchInput(2019,9).trim();
		DayRunner.run(new Day09());
	}
}
