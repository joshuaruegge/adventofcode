package advent.aoc2015;

import java.util.ArrayList;

import advent.utilities.general.Coord;
import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;

public class Day23 implements IDay {

	String input = "jio a, +16\r\n"
			+ "inc a\r\n"
			+ "inc a\r\n"
			+ "tpl a\r\n"
			+ "tpl a\r\n"
			+ "tpl a\r\n"
			+ "inc a\r\n"
			+ "inc a\r\n"
			+ "tpl a\r\n"
			+ "inc a\r\n"
			+ "inc a\r\n"
			+ "tpl a\r\n"
			+ "tpl a\r\n"
			+ "tpl a\r\n"
			+ "inc a\r\n"
			+ "jmp +23\r\n"
			+ "tpl a\r\n"
			+ "inc a\r\n"
			+ "inc a\r\n"
			+ "tpl a\r\n"
			+ "inc a\r\n"
			+ "inc a\r\n"
			+ "tpl a\r\n"
			+ "tpl a\r\n"
			+ "inc a\r\n"
			+ "inc a\r\n"
			+ "tpl a\r\n"
			+ "inc a\r\n"
			+ "tpl a\r\n"
			+ "inc a\r\n"
			+ "tpl a\r\n"
			+ "inc a\r\n"
			+ "inc a\r\n"
			+ "tpl a\r\n"
			+ "inc a\r\n"
			+ "tpl a\r\n"
			+ "tpl a\r\n"
			+ "inc a\r\n"
			+ "jio a, +8\r\n"
			+ "inc b\r\n"
			+ "jie a, +4\r\n"
			+ "tpl a\r\n"
			+ "inc a\r\n"
			+ "jmp +2\r\n"
			+ "hlf a\r\n"
			+ "jmp -7";
	
	String input2 = "inc a\r\n"
			+ "jio a, +2\r\n"
			+ "tpl a\r\n"
			+ "inc a";
	
	@Override
	public String part1() {
		//store program as list of coords, with coords acting as tuple to hold (instruction ID, parameter)
		//the only 2-parameter instructions, jio and jie, only ever reference register a, so they can be ignored
		ArrayList<Coord> program = new ArrayList<Coord>();
		for(String s : input.split("\r\n")) {
			String[] parts = s.split(" ");
			switch(parts[0]) {
			case "hlf":
				program.add(new Coord(0,parts[1].equals("b") ? 1 : 0));
				break;
			case "tpl":
				program.add(new Coord(1,parts[1].equals("b") ? 1 : 0));
				break;
			case "inc":
				program.add(new Coord(2,parts[1].equals("b") ? 1 : 0));
				break;
			case "jmp":
				program.add(new Coord(3,parts[1].charAt(0) == '-' ? -Integer.parseInt(parts[1].substring(1)) : Integer.parseInt(parts[1].substring(1))));
				break;
			case "jie":
				program.add(new Coord(4,parts[2].charAt(0) == '-' ? -Integer.parseInt(parts[2].substring(1)) : Integer.parseInt(parts[2].substring(1))));
				break;
			case "jio":
				program.add(new Coord(5,parts[2].charAt(0) == '-' ? -Integer.parseInt(parts[2].substring(1)) : Integer.parseInt(parts[2].substring(1))));
				break;
			}
		}
		
		int a = 0;
		int b = 0;
		int pointer = 0;
		//iterate over program until pointer leaves bounds
		while(pointer > -1 && pointer < program.size()) {
			//execute based on opcode 
			switch(program.get(pointer).x) {
			case 0:
				if(program.get(pointer).y == 0)
					a /= 2;
				else
					b /= 2;
				pointer++;
				break;
			case 1:
				if(program.get(pointer).y == 0)
					a *= 3;
				else
					b *= 3;
				pointer++;
				break;
			case 2:
				if(program.get(pointer).y == 0)
					a += 1;
				else
					b += 1;
				pointer++;
				break;
			case 3:
				pointer += program.get(pointer).y;
				break;
			case 4:
				if(a % 2 == 0)
					pointer += program.get(pointer).y;
				else
					pointer++;
				break;
			case 5:
				if(a == 1)
					pointer += program.get(pointer).y;
				else
					pointer++;
				break;
			}
			
		}
		//return b value
		return Integer.toString(b);
	}

	@Override
	public String part2() {
		//store program as list of coords, with coords acting as tuple to hold (instruction ID, parameter)
		//the only 2-parameter instructions, jio and jie, only ever reference register a, so they can be ignored
		ArrayList<Coord> program = new ArrayList<Coord>();
		for(String s : input.split("\r\n")) {
			String[] parts = s.split(" ");
			switch(parts[0]) {
			case "hlf":
				program.add(new Coord(0,parts[1].equals("b") ? 1 : 0));
				break;
			case "tpl":
				program.add(new Coord(1,parts[1].equals("b") ? 1 : 0));
				break;
			case "inc":
				program.add(new Coord(2,parts[1].equals("b") ? 1 : 0));
				break;
			case "jmp":
				program.add(new Coord(3,parts[1].charAt(0) == '-' ? -Integer.parseInt(parts[1].substring(1)) : Integer.parseInt(parts[1].substring(1))));
				break;
			case "jie":
				program.add(new Coord(4,parts[2].charAt(0) == '-' ? -Integer.parseInt(parts[2].substring(1)) : Integer.parseInt(parts[2].substring(1))));
				break;
			case "jio":
				program.add(new Coord(5,parts[2].charAt(0) == '-' ? -Integer.parseInt(parts[2].substring(1)) : Integer.parseInt(parts[2].substring(1))));
				break;
			}
		}
		//only difference - initialize a to 1
		//also, now a goes above the maximum integer range. fun
		long a = 1;
		int b = 0;
		int pointer = 0;
		//iterate over program until pointer leaves bounds
		while(pointer > -1 && pointer < program.size()) {
			
			switch(program.get(pointer).x) {
			case 0:
				if(program.get(pointer).y == 0)
					a /= 2;
				else
					b /= 2;
				pointer++;
				break;
			case 1:
				if(program.get(pointer).y == 0)
					a *= 3;
				else
					b *= 3;
				pointer++;
				break;
			case 2:
				if(program.get(pointer).y == 0)
					a += 1;
				else
					b += 1;
				pointer++;
				break;
			case 3:
				pointer += program.get(pointer).y;
				break;
			case 4:
				if(a % 2 == 0)
					pointer += program.get(pointer).y;
				else
					pointer++;
				break;
			case 5:
				if(a == 1)
					pointer += program.get(pointer).y;
				else
					pointer++;
				break;
			}
		}
		//return b value
		return Integer.toString(b);
	}

	public static void main(String[] args) {
		DayRunner.run(new Day23());
	}

}
