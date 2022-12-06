package advent.aoc2017;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

public class Day23 implements IDay {

	static String input;
	
	@Override
	public String part1() {
		ArrayList<String> program = new ArrayList<String>(Arrays.asList(input.split("\n")));
		HashMap<String,Long> registers = new HashMap<String,Long>();
		for(int i = 97; i < 105; i++) {
			registers.put((char) i + "", 0l);
		}
		int pointer = 0;
		int mulCounter = 0;
		while(pointer > -1 && pointer < program.size()) {
			String[] parts = program.get(pointer).split(" ");
			switch(parts[0]) {
			case "set":
				try {
					registers.put(parts[1], Long.parseLong(parts[2]));
				} catch (Exception e) {
					registers.put(parts[1], registers.get(parts[2]));
				}
				break;
			case "sub":
				try {
					registers.put(parts[1], registers.get(parts[1]) - Long.parseLong(parts[2]));
				} catch (Exception e) {
					registers.put(parts[1], registers.get(parts[1]) - registers.get(parts[2]));
				}
				break;
			case "mul":
				mulCounter++;
				try {
					registers.put(parts[1], registers.get(parts[1]) * Long.parseLong(parts[2]));
				} catch (Exception e) {
					registers.put(parts[1], registers.get(parts[1]) * registers.get(parts[2]));
				}
				break;
			case "jnz":
				long zero;
				try {
					zero = Long.parseLong(parts[1]);
				} catch (Exception e) {
					zero = registers.get(parts[1]);
				}
				if(zero != 0) {
					pointer += Long.parseLong(parts[2]) - 1;
				}
				break;
			}
			pointer++;
		}
		return Integer.toString(mulCounter);
	}

	@Override
	public String part2() {
		//if we decompile and examine our assembly, we find out it's essentially a reverse prime checker:
		//for all numbers between our starting b value (first set b instruction * 100 + 100000) and (starting b + (17 * 1001)), incrementing by 17 each time,
		//we increment our counter if b is not prime
		//then, just return counter
		int counter = 0;
		int startb = Integer.parseInt(input.split("\n")[0].split(" ")[2]);
		startb = (startb * 100) + 100000;
		for(int i = startb; i < (startb + (17 * 1001)); i += 17) {
			//check composite-ness
			for(int j = 2; j < i; j++) {
				if(i % j == 0) {
					counter++;
					break;
				}
			}
		}
		return Integer.toString(counter);
	}

	public static void main(String[] args) {
		input = Input.fetchInput(2017,23);
		DayRunner.run(new Day23());
	}

}
