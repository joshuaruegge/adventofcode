package advent.aoc2018;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

public class Day16 implements IDay {

	static String input;
	
	@Override
	public String part1() {
		String[] inputChunks = input.split("\n\n\n\n");
 		
		int threeOrMore = 0;
		
		for(String test : inputChunks[0].split("\n\n")) {
			String[] lines = test.split("\n");
			//parse line 1 into before register state
			int[] initialRegisters = new int[4];
			String[] before = lines[0].split(": \\[|, ");
			before[4] = before[4].substring(0,before[4].length() - 1);
			for(int i = 0; i < 4; i++) {
				initialRegisters[i] = Integer.parseInt(before[i+1]);
			}
			int[] instruction = new int[4];
			String[] operation = lines[1].split(" ");
			for(int i = 0; i < 4; i++) {
				instruction[i] = Integer.parseInt(operation[i]);
			}
			int[] resultRegisters = new int[4];
			String[] after = lines[2].split(":  \\[|, ");
			after[4] = after[4].substring(0,after[4].length() - 1);
			for(int i = 0; i < 4; i++)
				resultRegisters[i] = Integer.parseInt(after[i+1]);
			
			int potentialValids = 0;
			
			for(int i = 0; i < 16; i++) {
				instruction[0] = i;
				try {
					if(Arrays.equals(resultRegisters, instruction(instruction,initialRegisters))) {
						potentialValids++;
					}
				} catch (ArrayIndexOutOfBoundsException e) {
					continue;
				}
			}
			
			if(potentialValids > 2)
				threeOrMore++;
		}
		
		return Integer.toString(threeOrMore);
	}
	
	//calculates the given instruction on the given registers, then returns the resulting register
	public int[] instruction(int[] instruction, int[] registers) throws ArrayIndexOutOfBoundsException {
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
	
	
	@Override
	public String part2() {
		String[] inputChunks = input.split("\n\n\n\n");
		//keeps track of potential real opcodes in the form input opcode -> possible "default" opcodes
		//for the default, opcodes are numbered 0-15 in the order they appear in the problem spec
		HashMap<Integer,HashSet<Integer>> possibleCodes = new HashMap<Integer,HashSet<Integer>>();
 		
		for(String test : inputChunks[0].split("\n\n")) {
			String[] lines = test.split("\n");
			//parse line 1 into before register state
			int[] initialRegisters = new int[4];
			String[] before = lines[0].split(": \\[|, ");
			before[4] = before[4].substring(0,before[4].length() - 1);
			for(int i = 0; i < 4; i++) {
				initialRegisters[i] = Integer.parseInt(before[i+1]);
			}
			int[] instruction = new int[4];
			String[] operation = lines[1].split(" ");
			for(int i = 0; i < 4; i++) {
				instruction[i] = Integer.parseInt(operation[i]);
			}
			int[] resultRegisters = new int[4];
			String[] after = lines[2].split(":  \\[|, ");
			after[4] = after[4].substring(0,after[4].length() - 1);
			for(int i = 0; i < 4; i++)
				resultRegisters[i] = Integer.parseInt(after[i+1]);
			
			int realOpcode = instruction[0];
			HashSet<Integer> potentialValids = new HashSet<Integer>();
			
			for(int i = 0; i < 16; i++) {
				instruction[0] = i;
				try {
					if(Arrays.equals(resultRegisters, instruction(instruction,initialRegisters))) {
						potentialValids.add(i);
					}
				} catch (ArrayIndexOutOfBoundsException e) {
					continue;
				}
			}
				
			//put hashset in if no hashset exists
			if(!possibleCodes.containsKey(realOpcode))
				possibleCodes.put(realOpcode, potentialValids);
			else {
				possibleCodes.get(realOpcode).retainAll(potentialValids);
			}
		}
		
		HashMap<Integer,Integer> realOpcodes = new HashMap<Integer,Integer>();
		//in our list, if a real opcode only matches one default opcode, that is the true opcode
		//further, that opcode can be removed from other lists, potentially reducing them to only one, and on and on till all real opcodes are known
		while(possibleCodes.values().stream().filter(x -> x.size() > 0).count() > 0) {
			for(int i : possibleCodes.keySet()) {
				if(possibleCodes.get(i).size() == 1) {
					//use stream to extract from hashset
					int correctCode = possibleCodes.get(i).stream().findFirst().get();
					realOpcodes.put(i, correctCode);
					for(HashSet<Integer> a : possibleCodes.values())
						a.remove(correctCode);
				}
			}
		}
		
		//now, build list of program, replacing opcodes with real opcodes
		ArrayList<int[]> program = new ArrayList<int[]>();
		for(String line : inputChunks[1].split("\n")) {
			String[] nums = line.split(" ");
			int[] instruction = new int[4];
			for(int i = 1; i < 4; i++)
				instruction[i] = Integer.parseInt(nums[i]);
			instruction[0] = realOpcodes.get(Integer.parseInt(nums[0]));
			program.add(instruction);
		}
		
		//lastly, run program
		int[] registers = new int[4];
		for(int[] instruction : program) {
			registers = instruction(instruction,registers);
		}
		
		return Integer.toString(registers[0]);
	}
	
	public static void main(String[] args) {
		input = Input.fetchInput(2018,16);
		DayRunner.run(new Day16());
	}

}
