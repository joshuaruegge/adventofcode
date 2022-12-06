package advent.aoc2020;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

public class Day08 implements IDay {

	static String input;
	
	@Override
	public String part1() {
		ArrayList<String> program = new ArrayList<String>(Arrays.asList(input.split("\n")));
		int pointer = 0;
		int acc = 0;
		HashSet<Integer> visited = new HashSet<Integer>();
		visited.add(0);
		do {
			String[] parts = program.get(pointer).split(" ");
			switch(parts[0]) {
			case "nop":
				pointer++;
				break;
			case "acc":
				acc += Integer.parseInt(parts[1]);
				pointer++;
				break;
			case "jmp":
				pointer += Integer.parseInt(parts[1]);
				break;
			}
		} while(visited.add(pointer));
		return Integer.toString(acc);
	}

	@Override
	public String part2() {
		ArrayList<String> program = new ArrayList<String>(Arrays.asList(input.split("\n")));
		swap:
		//iterate through every potential instruction index to see which one makes the program function properly
		for(int swap = 0; swap < program.size(); swap++) {
			int pointer = 0;
			int acc = 0;
			HashSet<Integer> visited = new HashSet<Integer>();
			visited.add(0);
			while(pointer < program.size()) {
				String[] parts = program.get(pointer).split(" ");
				if(pointer == swap) {
					if(parts[0].equals("nop")) {
						parts[0] = "jmp";
					} else if (parts[0].equals("jmp")) {
						parts[0] = "nop";
					}
				}
				switch(parts[0]) {
				case "nop":
					pointer++;
					break;
				case "acc":
					acc += Integer.parseInt(parts[1]);
					pointer++;
					break;
				case "jmp":
					pointer += Integer.parseInt(parts[1]);
					break;
				}
				if(!visited.add(pointer)) {
					continue swap;
				}
			}
			return Integer.toString(acc);
		}
		return null;
	}

	public static void main(String[] args) {
		input = Input.fetchInput(2020,8);
		DayRunner.run(new Day08());
	}
}
