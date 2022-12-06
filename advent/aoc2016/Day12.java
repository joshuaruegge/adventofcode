package advent.aoc2016;

import java.util.ArrayList;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

public class Day12 implements IDay {

	static String input;
	
	@Override
	public String part1() {
		//parse input into arraylist to store program
		ArrayList<String> program = new ArrayList<String>();
		for(String s : input.split("\n"))
			program.add(s);

		//pointer and registers
		int pointer = 0;
		int a = 0;
		int b = 0;
		int c = 0;
		int d = 0;
		
		//evaluation loop
		while(pointer > -1 && pointer < program.size()) {
			String[] instruction = program.get(pointer).split(" ");
			switch(instruction[0]) {
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
		
		return Integer.toString(a);
		
		
	}

	@Override
	public String part2() {
		//parse input into arraylist to store program
		ArrayList<String> program = new ArrayList<String>();
		for(String s : input.split("\n"))
			program.add(s);
		
		//pointer and registers
		int pointer = 0;
		int a = 0;
		int b = 0;
		//part 2 modification - initialization
		int c = 1;
		int d = 0;
		
		//evaluation loop
		while(pointer > -1 && pointer < program.size()) {
			String[] instruction = program.get(pointer).split(" ");
			switch(instruction[0]) {
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
		
		return Integer.toString(a);
	}

	public static void main(String[] args) {
		input = Input.fetchInput(2016,12);
		DayRunner.run(new Day12());
	}

}
