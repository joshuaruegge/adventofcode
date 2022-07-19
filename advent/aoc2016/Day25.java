package advent.aoc2016;

import java.util.ArrayList;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;

public class Day25 implements IDay {

	String input = "cpy a d\r\n"
			+ "cpy 4 c\r\n"
			+ "cpy 643 b\r\n"
			+ "inc d\r\n"
			+ "dec b\r\n"
			+ "jnz b -2\r\n"
			+ "dec c\r\n"
			+ "jnz c -5\r\n"
			+ "cpy d a\r\n"
			+ "jnz 0 0\r\n"
			+ "cpy a b\r\n"
			+ "cpy 0 a\r\n"
			+ "cpy 2 c\r\n"
			+ "jnz b 2\r\n"
			+ "jnz 1 6\r\n"
			+ "dec b\r\n"
			+ "dec c\r\n"
			+ "jnz c -4\r\n"
			+ "inc a\r\n"
			+ "jnz 1 -7\r\n"
			+ "cpy 2 b\r\n"
			+ "jnz c 2\r\n"
			+ "jnz 1 4\r\n"
			+ "dec b\r\n"
			+ "dec c\r\n"
			+ "jnz 1 -4\r\n"
			+ "jnz 0 0\r\n"
			+ "out b\r\n"
			+ "jnz a -19\r\n"
			+ "jnz 1 -21";
	
	@Override
	public String part1() {
		//only parse program once 
		//parse input into arraylist to store program
		ArrayList<String> program = new ArrayList<String>();
		for(String s : input.split("\r\n"))
			program.add(s);
		
		//iterate upwards until first value is found
		numberLoop:
		for(int i = 0; i < 1000; i++) {
			//count number of outputs
			int outCounter = 0;
			//store last output - if a new output is the same, we know pattern is invalid
			int lastOut = -1;

			//pointer and registers
			int pointer = 0;
			int a = i;
			int b = 0;
			int c = 0;
			int d = 0;
			
			//evaluation loop
			//100 outs should be enough for pattern to stabilize
			while(pointer > -1 && pointer < program.size() && outCounter < 100) {
				String[] instruction = program.get(pointer).split(" ");
				switch(instruction[0]) {
				case "out":
					//out register is only ever b
					//if out is same as lastOut, pattern is invalid - skip to next number
					if(b == lastOut) {
						continue numberLoop;
					}
					lastOut = b;
					outCounter++;
					break;
				case "cpy":
					//get value to insert - either raw integer or register value
					int value = -1;
					char numOrRegister = instruction[1].charAt(0);
					if(numOrRegister >  96) {
						switch(numOrRegister) {
						case 'a':
							value = a;
							break;
						case 'b':
							value = b;
							break;
						case 'c':
							value = c;
							break;
						case 'd':
							value = d;
							break;
						}
					} else {
						value = Integer.parseInt(instruction[1]);
					}
					switch(instruction[2].charAt(0)) {
					case 'a':
						a = value;
						break;
					case 'b':
						b = value;
						break;
					case 'c':
						c = value;
						break;
					case 'd':
						d = value;
						break;
					}
					break;
				case "inc":
					switch(instruction[1].charAt(0)) {
					case 'a':
						a++;
						break;
					case 'b':
						b++;
						break;
					case 'c':
						c++;
						break;
					case 'd':
						d++;
						break;
					}
					break;
				case "dec":
					switch(instruction[1].charAt(0)) {
					case 'a':
						a--;
						break;
					case 'b':
						b--;
						break;
					case 'c':
						c--;
						break;
					case 'd':
						d--;
						break;
					}
					break;
				case "jnz":
					int zero = -1;
					if(instruction[1].charAt(0) > 96) {
						switch(instruction[1].charAt(0)) {
						case 'a':
							zero = a;
							break;
						case 'b':
							zero = b;
							break;
						case 'c':
							zero = c;
							break;
						case 'd':
							zero = d;
							break;
						}
					} else {
						zero = Integer.parseInt(instruction[1]);
					}
					if(zero != 0) {
						pointer += Integer.parseInt(instruction[2]) - 1;
					}
					break;
				}
				pointer++;
			}
			//if we made it through 100 with no pattern breaks, return number used to initialize
			return Integer.toString(i);
		}
		return null;
	}

	@Override
	public String part2() {
		return "Merry Christmas!";
	}

	public static void main(String[] args) {
		DayRunner.run(new Day25());
	}

}
