package advent.aoc2017;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

public class Day01 implements IDay {

	static String input;
	
	@Override
	public String part1() {
		int total = 0;
		for(int i = 0; i < input.length() - 1; i++) {
			//compare current digit and digit after
			if(input.charAt(i) == input.charAt(i+1)) {
				//add value of digit to total
				total += Integer.parseInt(input.substring(i,i+1));
			}
		}
		//check last to first
		if(input.charAt(0) == input.charAt(input.length() - 1)) {
			total += Integer.parseInt(input.substring(0,1));
		}
		return Integer.toString(total);
		
	}

	@Override
	public String part2() {
		//precalculate offset
		int offset = input.length() / 2;
		
		int total = 0;
		for(int i = 0; i < input.length(); i++) {
			//(index + offset) % length ensures number is within bounds (wraps around input)
			if(input.charAt(i) == input.charAt((i+offset) % input.length())) {
				total += Integer.parseInt(input.substring(i,i+1));
			}
		}
		return Integer.toString(total);
	}

	public static void main(String[] args) {
		input = Input.fetchInput(2017,1).replace("\n","");
		DayRunner.run(new Day01());
	}

}
