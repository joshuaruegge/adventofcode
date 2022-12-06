package advent.aoc2017;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

public class Day15 implements IDay {

	static String input;
	
	@Override
	public String part1() {
		long a, b;
		a = Long.parseLong(input.split("\n")[0].split(" ")[4]);
		b = Long.parseLong(input.split("\n")[1].split(" ")[4]);
		
		int pairs = 0;
		for(int i = 0; i < 40000000; i++) {
			//update values
			a *= 16807;
			b *= 48271;
			a %= Integer.MAX_VALUE;
			b %= Integer.MAX_VALUE;
			
			//& 65535 (16 binary ones) trims number down to lower 16 bits
			if((a & 65535) == (b & 65535))
				pairs++;
		}
		return Integer.toString(pairs);
	}

	@Override
	public String part2() {
		long a, b;
		a = Long.parseLong(input.split("\n")[0].split(" ")[4]);
		b = Long.parseLong(input.split("\n")[1].split(" ")[4]);
		
		int pairs = 0;
		for(int i = 0; i < 5000000; i++) {
			//update values
			a *= 16807;
			b *= 48271;
			a %= Integer.MAX_VALUE;
			b %= Integer.MAX_VALUE;
			
			//ensure multiple
			while(a % 4 != 0) {
				a *= 16807;
				a %= Integer.MAX_VALUE;
			}
			while(b % 8 != 0) {
				b *= 48271;
				b %= Integer.MAX_VALUE;
			}
			//check pair
			if((a & 65535) == (b & 65535))
				pairs++;
		}
		return Integer.toString(pairs);
	}

	public static void main(String[] args) {
		input = Input.fetchInput(2017,15);
		DayRunner.run(new Day15());
	}

}
