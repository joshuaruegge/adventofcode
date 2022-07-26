package advent.aoc2017;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;

public class Day23 implements IDay {

	String input = "set b 93\r\n"
			+ "set c b\r\n"
			+ "jnz a 2\r\n"
			+ "jnz 1 5\r\n"
			+ "mul b 100\r\n"
			+ "sub b -100000\r\n"
			+ "set c b\r\n"
			+ "sub c -17000\r\n"
			+ "set f 1\r\n"
			+ "set d 2\r\n"
			+ "set e 2\r\n"
			+ "set g d\r\n"
			+ "mul g e\r\n"
			+ "sub g b\r\n"
			+ "jnz g 2\r\n"
			+ "set f 0\r\n"
			+ "sub e -1\r\n"
			+ "set g e\r\n"
			+ "sub g b\r\n"
			+ "jnz g -8\r\n"
			+ "sub d -1\r\n"
			+ "set g d\r\n"
			+ "sub g b\r\n"
			+ "jnz g -13\r\n"
			+ "jnz f 2\r\n"
			+ "sub h -1\r\n"
			+ "set g b\r\n"
			+ "sub g c\r\n"
			+ "jnz g 2\r\n"
			+ "jnz 1 3\r\n"
			+ "sub b -17\r\n"
			+ "jnz 1 -23";
	
	@Override
	public String part1() {
		ArrayList<String> program = new ArrayList<String>(Arrays.asList(input.split("\r\n")));
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
		int startb = Integer.parseInt(input.split("\r\n")[0].split(" ")[2]);
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
		DayRunner.run(new Day23());
	}

}
