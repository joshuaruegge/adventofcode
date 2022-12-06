package advent.aoc2017;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

public class Day09 implements IDay {

	static String input;
	
	@Override
	public String part1() {
		int totalScore = 0;
		//store a measure of how many groups deep we are based on bracket count
		int depth = 0;
		boolean inGarbage = false;
		for(int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);
			switch(c) {
			case '{':
				//brackets only count outside garbage
				if(!inGarbage)
					depth++;
				break;
			case '}':
				//end of group - append group score to total, then decrease
				if(!inGarbage) {
					totalScore += depth;
					depth--;
				}
				break;
			case '<':
				//note that we are in garbage
				inGarbage = true;
				break;
			case '>':
				//note leaving garbage
				inGarbage = false;
				break;
			case '!':
				//skip next character
				i++;
				break;
			}
		}
		return Integer.toString(totalScore);
	}

	@Override
	public String part2() {
		//totalScore now represents number of garbage characters
		int totalScore = 0;
		boolean inGarbage = false;
		for(int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);
			//if currently in garbage and not checking a character that doesn't count
			//the opening '<' doesnt count either, but inGarbage is only ever true after the opening is examined
			if(inGarbage && !(c == '>') && !(c == '!'))
				totalScore++;
			switch(c) {
			//note: bracket/group depth is unnecessary for part 2
			case '<':
				//note that we are in garbage
				inGarbage = true;
				break;
			case '>':
				//note leaving garbage
				inGarbage = false;
				break;
			case '!':
				//skip next character
				i++;
				break;
			}
		}
		return Integer.toString(totalScore);
	}

	public static void main(String[] args) {
		input = Input.fetchInput(2017,9).replace("\n","");
		DayRunner.run(new Day09());
	}

}
