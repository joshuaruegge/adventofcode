package advent.aoc2018;

import java.util.ArrayList;
import java.util.HashSet;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;

public class Day21 implements IDay {

	final String[] INSTRUCTIONS = new String[] {"addr", "addi", "mulr","muli","banr","bani","borr","bori","setr","seti","gtir","gtri","gtrr","eqir","eqri","eqrr"};

	String input = "#ip 4\r\n"
			+ "seti 123 0 1\r\n"
			+ "bani 1 456 1\r\n"
			+ "eqri 1 72 1\r\n"
			+ "addr 1 4 4\r\n"
			+ "seti 0 0 4\r\n"
			+ "seti 0 3 1\r\n"
			+ "bori 1 65536 5\r\n"
			+ "seti 8586263 3 1\r\n"
			+ "bani 5 255 2\r\n"
			+ "addr 1 2 1\r\n"
			+ "bani 1 16777215 1\r\n"
			+ "muli 1 65899 1\r\n"
			+ "bani 1 16777215 1\r\n"
			+ "gtir 256 5 2\r\n"
			+ "addr 2 4 4\r\n"
			+ "addi 4 1 4\r\n"
			+ "seti 27 8 4\r\n"
			+ "seti 0 1 2\r\n"
			+ "addi 2 1 3\r\n"
			+ "muli 3 256 3\r\n"
			+ "gtrr 3 5 3\r\n"
			+ "addr 3 4 4\r\n"
			+ "addi 4 1 4\r\n"
			+ "seti 25 8 4\r\n"
			+ "addi 2 1 2\r\n"
			+ "seti 17 7 4\r\n"
			+ "setr 2 0 5\r\n"
			+ "seti 7 8 4\r\n"
			+ "eqrr 1 0 2\r\n"
			+ "addr 2 4 4\r\n"
			+ "seti 5 4 4";
	
	@Override
	public String part1() {
		
		String[] lines = input.split("\r\n");
		int pointerRegister = Integer.parseInt(lines[0].split(" ")[1]);
		ArrayList<int[]> program = new ArrayList<int[]>();
		for(int i = 1; i < lines.length; i++) {
			String[] parts = lines[i].split(" ");
			program.add(new int[] {opcode(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), Integer.parseInt(parts[3])});
		}
		
		//the only instruction in the entire program to reference register zero is the eqrr instruction near the end of the program
		//examining the lines around it, we can determine that this means the program ends when register 0 is equal to register 1
		//thinking logically, this means that register 1 is set to a constant value no matter the zero input, and so the correct zero initialization must equal that number
		//so, we just run the program once, and when we hit the eqrr reference, we return the value of the register it checks register 0 against
		int pointer = 0;
		int[] registers = new int[6];
		while(pointer > -1 && pointer < program.size()) {
			registers[pointerRegister] = pointer;
			if(program.get(pointer)[0] == 15)
				return Integer.toString(registers[program.get(pointer)[1]]);
			registers = instruction(program.get(pointer),registers);
			pointer = registers[pointerRegister];
			pointer++;
		
		}
		return null;
	}
	
	public int[] instruction(int[] instruction, int[] registers) {
		int[] newRegisters = registers.clone();
		switch(instruction[0]) {
		case 0:
			newRegisters[instruction[3]] = newRegisters[instruction[1]] + newRegisters[instruction[2]];
			break;
		case 1:
			newRegisters[instruction[3]] = newRegisters[instruction[1]] + instruction[2];
			break;
		case 2:
			newRegisters[instruction[3]] = newRegisters[instruction[1]] * newRegisters[instruction[2]];
			break;
		case 3:
			newRegisters[instruction[3]] = newRegisters[instruction[1]] * instruction[2];
			break;
		case 4:
			newRegisters[instruction[3]] = newRegisters[instruction[1]] & newRegisters[instruction[2]];
			break;
		case 5:
			newRegisters[instruction[3]] = newRegisters[instruction[1]] & instruction[2];
			break;
		case 6:
			newRegisters[instruction[3]] = newRegisters[instruction[1]] | newRegisters[instruction[2]];
			break;
		case 7:
			newRegisters[instruction[3]] = newRegisters[instruction[1]] | instruction[2];
			break;
		case 8:
			newRegisters[instruction[3]] = newRegisters[instruction[1]];
			break;
		case 9:
			newRegisters[instruction[3]] = instruction[1];
			break;
		case 10:
			newRegisters[instruction[3]] = (instruction[1] > newRegisters[instruction[2]] ? 1 : 0);
			break;
		case 11:
			newRegisters[instruction[3]] = (newRegisters[instruction[1]] > instruction[2] ? 1 : 0);
			break;
		case 12:
			newRegisters[instruction[3]] = (newRegisters[instruction[1]] > newRegisters[instruction[2]] ? 1 : 0);
			break;
		case 13:
			newRegisters[instruction[3]] = (instruction[1] == newRegisters[instruction[2]] ? 1 : 0);
			break;
		case 14:
			newRegisters[instruction[3]] = (newRegisters[instruction[1]] == instruction[2] ? 1 : 0);
			break;
		case 15:
			newRegisters[instruction[3]] = (newRegisters[instruction[1]] == newRegisters[instruction[2]] ? 1 : 0);
			break;
		}
		return newRegisters;
	}
	
		
	public int opcode(String s) {
		for(int i = 0; i < INSTRUCTIONS.length; i++) {
			if(s.equals(INSTRUCTIONS[i]))
				return i;
		}
		return -1;
	}

	@Override
	public String part2() {
		//similarly, for part 2, we can backtrack a bit and assume that if the number fails the eqrr too many times it'll loop forever
		//interestingly, when the number is greater than the starting comparison for the eqrr, it loops through and attempts through various different values in register 1
		//however, these values eventually hit a loop and run infinitely
		//so, theoretically, the value we are looking for is the last value passed to the eqrr before the program loops
		//because that would mean halting at the last possible moment
		//we can track this by storing the compared values in a hashset and remembering the last value we saw - when a value repeats, return the last value before the repeat started
		HashSet<Integer> compareValues = new HashSet<Integer>();
		int last = -1;
		
		//furthermore, rather than iterating through the code to get the values, which is very inefficient (nearly a minute)
		//we can extract the section that produces the number to be compared and recreate it in Java rather than pseudo-assembly
		//allowing it to take advantage of complier efficiencies, etc.
		//the process only relies on one unique value, the large number register 3 is initialized to
		int initialize = Integer.parseInt(input.split("\r\n")[8].split(" ")[1]);
		
		int compare = 0;
		//optimized version of comparison generation
		while(true) {
			int register = compare | 65536;
			compare = initialize;
				//calculation until check loop
				while(true) {
					//find number to compare
					compare += (register & 255);
					compare &= 16777215;
					compare *= 65899;
					compare &= 16777215;
					
					//jump to comparison
					if(register <= 256) {
						//put in set - if in set, return last
						if(!compareValues.add(compare)) {
							return Integer.toString(last);
						}
						last = compare;
						break;
					} else {
						register /= 256;
					}
				}
		}
	}

	public static void main(String[] args) {
		DayRunner.run(new Day21());
	}

}
