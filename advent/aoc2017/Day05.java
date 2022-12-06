package advent.aoc2017;

import java.util.ArrayList;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

public class Day05 implements IDay {

	static String input;
	
	@Override
	public String part1() {
		ArrayList<Integer> jumps = new ArrayList<Integer>();
		for(String s : input.split("\n")) {
			jumps.add(Integer.parseInt(s));
		}
		int pointer = 0;
		int counter = 0;
		while(pointer > -1 && pointer < jumps.size()) {
			//get jump at pointer
			int jump = jumps.get(pointer);
			//increment value
			jumps.set(pointer, jump + 1);
			//perform jump
			pointer += jump;
			//increment counter
			counter++;
		}
		return Integer.toString(counter);
	}

	@Override
	public String part2() {
		ArrayList<Integer> jumps = new ArrayList<Integer>();
		for(String s : input.split("\n")) {
			jumps.add(Integer.parseInt(s));
		}
		int pointer = 0;
		int counter = 0;
		//go until pointer is in bounds
		while(pointer > -1 && pointer < jumps.size()) {
			//get jump at pointer
			int jump = jumps.get(pointer);
			//increment value
			//part 2 - ternary operator
			jumps.set(pointer, (jump > 2 ? jump-1 : jump + 1));
			//perform jump
			pointer += jump;
			//increment counter
			counter++;
		}
		return Integer.toString(counter);
	}

	public static void main(String[] args) {
		input = Input.fetchInput(2017,5);
		DayRunner.run(new Day05());
	}

}
