package advent.aoc2016;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

public class Day09 implements IDay {

	static String input;
	
	@Override
	public String part1() {
		int totalLength = 0;
		for(int index = 0; index < input.length(); index++) {
			//if beginning of parenthesis block, find and total parenthesis
			if(input.charAt(index) == '(') {
				//parse out and add total length caused by parenthesis
				int endIndex = input.indexOf(')',index);
				String[] numParts = input.substring(index+1,endIndex).split("x");
				totalLength += Integer.parseInt(numParts[0]) * Integer.parseInt(numParts[1]);
				//skip to end of repetition section
				index = endIndex + Integer.parseInt(numParts[0]);
			} 
		}
		return Integer.toString(totalLength);
	}

	@Override
	public String part2() {
		return Long.toString(totalLength(input));
	}
	
	//recursion helper method for part 2 
	public long totalLength(String section) {
		long totalLength = 0;
		for(int index = 0; index < section.length(); index++) {
			if(section.charAt(index) == '(') {
				int endIndex = section.indexOf(')',index);
				String[] numParts = section.substring(index+1,endIndex).split("x");
				int subsectionLength = Integer.parseInt(numParts[0]);
				//if no subparenthesis, recursion unnecessary
				//total length is just length * repeats
				if(section.substring(endIndex+1,endIndex+subsectionLength).indexOf("(") == -1) {
					totalLength += subsectionLength * Integer.parseInt(numParts[1]);
					index = endIndex + subsectionLength;
				} else {
					//perform recursion
					//get score of entire region referenced by parenthesis, and multiply it by repititions
					totalLength += Integer.parseInt(numParts[1]) * totalLength(section.substring(endIndex+1,endIndex+subsectionLength));
					index = endIndex + subsectionLength;
				}
				
			}
		}
		return totalLength;
	}

	public static void main(String[] args) {
		input = Input.fetchInput(2016,9);
		DayRunner.run(new Day09());
	}

}
