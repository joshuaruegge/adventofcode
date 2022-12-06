package advent.aoc2020;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

public class Day25 implements IDay {

	static String input;
	
	@Override
	public String part1() {
		int cardPublic = Integer.parseInt(input.split("\n")[0]);
		int doorPublic = Integer.parseInt(input.split("\n")[1]);
		
		//determine loop size of cardPublic
		int loopSize = 1;
		long value = 1;
		while(true) {
			value = value * 7 % 20201227;
			if(value == cardPublic)
				break;
			loopSize++;
		}
		
		//apply loop size to doorPublic
		return Long.toString(transform(doorPublic,loopSize));
	}
	
	public long transform(int mult, int loop) {
		long value = 1;
		for(int i = 0; i < loop; i++) {
			value *= mult;
			value %= 20201227;
		}
		return value;
	}

	@Override
	public String part2() {
		return "Merry Christmas!";
	}

	public static void main(String[] args) {
		input = Input.fetchInput(2020,25);
		DayRunner.run(new Day25());
	}

}
