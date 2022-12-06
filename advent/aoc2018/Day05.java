package advent.aoc2018;

import java.util.ArrayList;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

public class Day05 implements IDay {
	
	//copy-pasting this one in likes to overload text buffers, be careful
	static String input;
	
	@Override
	public String part1() {
		//avoid iterating over string over and over by using a stack
		//the stack stores characters in order, so that after a pair collapses, the character before it can be accessed easily
		ArrayList<Character> stack = new ArrayList<Character>();
		for(char c : input.toCharArray()) {
			if(!stack.isEmpty() && stack.get(stack.size() - 1) == (Character.isUpperCase(c) ? Character.toLowerCase(c) : Character.toUpperCase(c))) {
				//if characters are a collapsing pair, remove the other one from array
				stack.remove(stack.size() - 1);
			} else {
				//append character to stack
				stack.add(c);
			}
		}
		return Integer.toString(stack.size());
	}

	@Override
	public String part2() {
		int lowestLength = Integer.MAX_VALUE;
		for(char exclude = 'a'; exclude < '{'; exclude++) {
			ArrayList<Character> stack = new ArrayList<Character>();
			for(char c : input.toCharArray()) {
				if(!stack.isEmpty() && stack.get(stack.size() - 1) == (Character.isUpperCase(c) ? Character.toLowerCase(c) : Character.toUpperCase(c))) {
					//if characters are a collapsing pair, remove the other one from array
					stack.remove(stack.size() - 1);
				} else if (c == exclude || c == Character.toUpperCase(exclude)) {
					//ignore excluded character completely - don't stack or remove
					continue;
				} else {
					//append character to stack
					stack.add(c);
				}
			}
			if(stack.size() < lowestLength)
				lowestLength = stack.size();
		}
		return Integer.toString(lowestLength);
	}

	public static void main(String[] args) {
		input = Input.fetchInput(2018,5).trim();
		DayRunner.run(new Day05());
	}

}
