package advent.aoc2019;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

public class Day04 implements IDay {
	
	static String input;

	@Override
	public String part1() {
		int min = Integer.parseInt(input.split("-")[0]);
		int max = Integer.parseInt(input.split("-")[1]);
		int valids = 0;
		counter:
		for(int x = min; x < max; x++) {
			//convert to string for operation easiness
			String num = x + "";
			boolean repeated = false;
			for(int i = 0; i < 5; i++) {
				char c = num.charAt(i);
				char d = num.charAt(i+1);
				//if number contains current coordinate repeated twice record that this number repeats
				if(num.contains(c + "" + c)) {
					repeated = true;
				}
				//if number not in proper ascending order, skip to next number
				if(Integer.parseInt(d + "") < Integer.parseInt(c + "") ) {
					continue counter;
				}
			}
			//if number was not skipped and repeats, increment counter
			if(repeated) {
				valids++;
			}
			
		}
		return Integer.toString(valids);
	}

	@Override
	public String part2() {
		int min = Integer.parseInt(input.split("-")[0]);
		int max = Integer.parseInt(input.split("-")[1]);
		int valids = 0;
		counter:
		for(int x = min; x < max; x++) {
			String num = x + "";
			boolean repeated = false;
			for(int i = 0; i < 5; i++) {
				char c = num.charAt(i);
				char d = num.charAt(i+1);
				//only difference - make sure repeated section is not part of a 3-character segment
				//larger segments would also contain at least one 3-character segment and also fail this check
				if(num.contains(c + "" + c) && !num.contains(c + "" + c + "" + c)) {
					repeated = true;
				}
				if(Integer.parseInt(d + "") < Integer.parseInt(c + "") ) {
					continue counter;
				}
			}
			if(repeated) {
				valids++;
			}
			
		}
		return Integer.toString(valids);
	}

	public static void main(String[] args) {
		input = Input.fetchInput(2019,4).trim();
		DayRunner.run(new Day04());
	}

}
