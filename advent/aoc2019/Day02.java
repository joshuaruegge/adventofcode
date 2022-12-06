package advent.aoc2019;

import java.util.ArrayList;
import java.util.Arrays;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;
import advent.utilities.utils2019.IntCodeComputer;

public class Day02 implements IDay{
	static String input;

	@Override
	public String part1() {
		//split string into number array, initialize computer object, change registers, and execute
		ArrayList<Long> program = new ArrayList<Long>(Arrays.stream(input.split(",")).map(x -> Long.parseLong(x)).toList());
		program.set(1, 12l);
		program.set(2, 2l);
		IntCodeComputer i = new IntCodeComputer(program);
		i.run();
		return Long.toString(i.program.get(0));
	}

	@Override
	public String part2() {
		ArrayList<Long> program = new ArrayList<Long>(Arrays.stream(input.split(",")).map(x -> Long.parseLong(x)).toList());
		//same as above, but try out input values until condition is met
		for(int i = 0; i < 99; i++) {
			for(int j = 0; j < 99; j++) {
				program.set(1, (long) i);
				program.set(2, (long) j);
				IntCodeComputer c = new IntCodeComputer(program);
				c.run();
				if(c.program.get(0) == 19690720) {
					return Integer.toString(100 * i + j);
				}
			}
		}
		return null;
	}


	public static void main(String[] args) {
		input = Input.fetchInput(2019,2).trim();
		DayRunner.run(new Day02());
	}

}
