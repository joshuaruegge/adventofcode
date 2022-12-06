package advent.aoc2018;

import java.util.ArrayList;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

public class Day19 implements IDay {

	final String[] INSTRUCTIONS = new String[] {"addr", "addi", "mulr","muli","banr","bani","borr","bori","setr","seti","gtir","gtri","gtrr","eqir","eqri","eqrr"};
	
	static String input;
	
	@Override
	public String part1() {
		String[] lines = input.split("\n");
		int pointerRegister = Integer.parseInt(lines[0].split(" ")[1]);
		ArrayList<int[]> program = new ArrayList<int[]>();
		for(int i = 1; i < lines.length; i++) {
			String[] parts = lines[i].split(" ");
			program.add(new int[] {opcode(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), Integer.parseInt(parts[3])});
		}
		int pointer = 0;
		int[] registers = new int[6];
		while(pointer > -1 && pointer < program.size()) {
			registers[pointerRegister] = pointer;
			registers = instruction(program.get(pointer),registers);
			pointer = registers[pointerRegister];
			pointer++;
		}
		
		return Integer.toString(registers[0]);
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
		String[] lines = input.split("\n");
		//similar to the assembly factorial machine for the easter bunny last year, changing the register reveals that simply running this code isn't feasible
		//breaking down the assembly program reveals that the program is designed to sum all the factors of a given number
		//the number relies on two static constants - 10551236 and 22, as well as two user-specific values, at lines 22 and 24 of the input
		int a = Integer.parseInt(lines[22].split(" ")[2]);
		int b = Integer.parseInt(lines[24].split(" ")[2]);

		int factor = 10551236 + (a * 22) + b;
		
		long sum = 0;
		for(int i = 1; i <= factor; i++) {
				if(factor % i == 0)
					sum += i;
		}
		
		return Long.toString(sum);
	}

	public static void main(String[] args) {
		input = Input.fetchInput(2018,19);
		DayRunner.run(new Day19());
	}

}
