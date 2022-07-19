package advent.aoc2016;

import java.util.ArrayList;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;

public class Day23 implements IDay {

	String input = "cpy a b\r\n"
			+ "dec b\r\n"
			+ "cpy a d\r\n"
			+ "cpy 0 a\r\n"
			+ "cpy b c\r\n"
			+ "inc a\r\n"
			+ "dec c\r\n"
			+ "jnz c -2\r\n"
			+ "dec d\r\n"
			+ "jnz d -5\r\n"
			+ "dec b\r\n"
			+ "cpy b c\r\n"
			+ "cpy c d\r\n"
			+ "dec d\r\n"
			+ "inc c\r\n"
			+ "jnz d -2\r\n"
			+ "tgl c\r\n"
			+ "cpy -16 c\r\n"
			+ "jnz 1 c\r\n"
			+ "cpy 97 c\r\n"
			+ "jnz 79 d\r\n"
			+ "inc a\r\n"
			+ "inc d\r\n"
			+ "jnz d -2\r\n"
			+ "inc c\r\n"
			+ "jnz c -5";
	
	@Override
	public String part1() {
		//parse input into arraylist to store program
		ArrayList<String> program = new ArrayList<String>();
		for(String s : input.split("\r\n"))
			program.add(s);
		
		//pointer and registers
		int pointer = 0;
		//initialize a to 7
		int a = 7;
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
					try {
						pointer += Integer.parseInt(instruction[2]) - 1;
					} catch (Exception e) {
						switch(instruction[2].charAt(0)) {
						case 'a':
							pointer += a - 1;
							break;
						case 'b':
							pointer += b - 1;
							break;
						case 'c':
							pointer += c - 1;
							break;
						case 'd':
							pointer += d - 1;
							break;
						}
					}
				}
				break;
			case "tgl":
				int toggle = pointer;
				if(instruction[1].charAt(0) > 90) {
					switch(instruction[1].charAt(0)) {
					case 'a':
						toggle += a;
						break;
					case 'b':
						toggle += b;
						break;
					case 'c':
						toggle += c;
						break;
					case 'd':
						toggle += d;
						break;
					}
				} else {
					toggle += Integer.parseInt(instruction[1]);
				}
				if(toggle >= program.size())
					break;
				String old = program.get(toggle);
				String[] oldParts = old.split(" ");
				if(oldParts.length == 2) {
					program.set(toggle, (oldParts[0].equals("inc") ? "dec" : "inc") + " " + oldParts[1]);
				} else {
					program.set(toggle, (oldParts[0].equals("jnz") ? "cpy" : "jnz") + " " + oldParts[1] + " " + oldParts[2]);
				}
				break;
			}
			
			pointer++;
		}
		
		return Integer.toString(a);
	}

	@Override
	public String part2() {
		//so, uhh... funny thing. you're welcome for completely picking apart the assembunny program for this info
		//if you analyze the program, you find out it's essentially just a factorializer of your a input
		//hence why 12 takes so much longer than 7
		//the final result is always equal to a! + (constant 1 * constant 2)
		//where constant 1 and constant 2 are the numerical arguments in lines 19 and 20
		//so, let's just pull all these values out and avoid running the program all together
		String[] lines = input.split("\r\n");
		long c1 = Integer.parseInt(lines[19].split(" ")[1]);
		long c2 = Integer.parseInt(lines[20].split(" ")[1]);
		long fac = 1;
		//fun fact - java doesnt have native factorialization
		for(int i = 12; i > 0; i--) {
			fac *= i;
		}
		long result = fac + c1 * c2;
		return Long.toString(result);
	}

	public static void main(String[] args) {
		DayRunner.run(new Day23());
	}

}
