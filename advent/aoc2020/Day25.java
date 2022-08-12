package advent.aoc2020;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;

public class Day25 implements IDay {

	String input = "9033205\r\n"
			+ "9281649";
	
	@Override
	public String part1() {
		int cardPublic = Integer.parseInt(input.split("\r\n")[0]);
		int doorPublic = Integer.parseInt(input.split("\r\n")[1]);
		
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
		DayRunner.run(new Day25());
	}

}
