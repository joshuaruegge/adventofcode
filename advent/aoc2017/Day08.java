package advent.aoc2017;

import java.util.Collections;
import java.util.HashMap;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

public class Day08 implements IDay {

	static String input;
	
	@Override
	public String part1() {
		//since we don't know all register names, store them in a HashMap rather than as individual variables
		HashMap<String, Integer> registers = new HashMap<String,Integer>();
		
		for(String s : input.split("\n")) {
			String[] parts = s.split(" ");
			int jump = Integer.parseInt(parts[2]);
			if(parts[1].equals("dec")) {
				jump *= -1;
			}
			
			int comparisonInt = Integer.parseInt(parts[6]);
			
			boolean comparison = false;
			switch(parts[5]) {
			//test register (word 4) against comparisonInt based on sign
			case ">":
				comparison = registers.getOrDefault(parts[4], 0) > comparisonInt;
				break;
			case "<":
				comparison = registers.getOrDefault(parts[4], 0) < comparisonInt;
				break;
			case ">=":
				comparison = registers.getOrDefault(parts[4], 0) >= comparisonInt;
				break;
			case "<=":
				comparison = registers.getOrDefault(parts[4], 0) <= comparisonInt;
				break;
			case "==":
				comparison = registers.getOrDefault(parts[4], 0) == comparisonInt;
				break;
			case "!=":
				comparison = registers.getOrDefault(parts[4], 0) != comparisonInt;
				break;
			}
			
			//if comparison passed, put new value into register (word 0)
			if(comparison) {
				registers.put(parts[0], registers.getOrDefault(parts[0], 0) + jump);
			}
		}
		
		return Integer.toString(Collections.max(registers.values()));
	}

	@Override
	public String part2() {
		//since we don't know all register names, store them in a HashMap rather than as individual variables
		HashMap<String, Integer> registers = new HashMap<String,Integer>();
		
		int highestEver = 0;
		for(String s : input.split("\n")) {
			String[] parts = s.split(" ");
			int jump = Integer.parseInt(parts[2]);
			if(parts[1].equals("dec")) {
				jump *= -1;
			}
			
			int comparisonInt = Integer.parseInt(parts[6]);
			
			boolean comparison = false;
			switch(parts[5]) {
			//test register (word 4) against comparisonInt based on sign
			case ">":
				comparison = registers.getOrDefault(parts[4], 0) > comparisonInt;
				break;
			case "<":
				comparison = registers.getOrDefault(parts[4], 0) < comparisonInt;
				break;
			case ">=":
				comparison = registers.getOrDefault(parts[4], 0) >= comparisonInt;
				break;
			case "<=":
				comparison = registers.getOrDefault(parts[4], 0) <= comparisonInt;
				break;
			case "==":
				comparison = registers.getOrDefault(parts[4], 0) == comparisonInt;
				break;
			case "!=":
				comparison = registers.getOrDefault(parts[4], 0) != comparisonInt;
				break;
			}
			
			//if comparison passed, put new value into register (word 0)
			if(comparison) {
				registers.put(parts[0], registers.getOrDefault(parts[0], 0) + jump);
			}
			
			if(!registers.isEmpty() && Collections.max(registers.values()) > highestEver)
				highestEver = Collections.max(registers.values());
		}
			
		return Integer.toString(highestEver);
	}

	public static void main(String[] args) {
		input = Input.fetchInput(2017,8);
		DayRunner.run(new Day08());
	}

}
