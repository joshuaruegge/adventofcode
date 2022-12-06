package advent.aoc2018;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

public class Day12 implements IDay {

	static String input;
	
	@Override
	public String part1() {
		String[] chunks = input.split("\n\n");
		
		HashSet<Integer> plants = new HashSet<Integer>();
		String plantStart = chunks[0].split(": ")[1];
		for(int i = 0; i < plantStart.length(); i++) {
			if(plantStart.charAt(i) == '#')
				plants.add(i);
		}
		
		//build rules list
		HashMap<ArrayList<Boolean>,Boolean> rules = new HashMap<ArrayList<Boolean>,Boolean>();
		for(String s : chunks[1].split("\n")) {
			String[] parts = s.split(" => ");
			ArrayList<Boolean> rule = new ArrayList<Boolean>();
			for(char c : parts[0].toCharArray())
				rule.add(c == '#');
			rules.put(rule,parts[1].charAt(0) == '#');
		}
		
		for(int iter = 0; iter < 20; iter++) {
			HashSet<Integer> newPlants = new HashSet<Integer>();
			for(int i = Collections.min(plants) - 2; i < Collections.max(plants) + 3; i++) {
				ArrayList<Boolean> rule = new ArrayList<Boolean>();
				for(int j = i-2; j < i+3; j++) {
					rule.add(plants.contains(j));
				}
				if(rules.get(rule))
					newPlants.add(i);
			}
			plants = newPlants;
		}

		return Integer.toString(plants.stream().mapToInt(x -> x).sum());
	}

	@Override
	public String part2() {
		String[] chunks = input.split("\n\n");
		
		HashSet<Integer> plants = new HashSet<Integer>();
		String plantStart = chunks[0].split(": ")[1];
		for(int i = 0; i < plantStart.length(); i++) {
			if(plantStart.charAt(i) == '#')
				plants.add(i);
		}
		
		//build rules list
		HashMap<ArrayList<Boolean>,Boolean> rules = new HashMap<ArrayList<Boolean>,Boolean>();
		for(String s : chunks[1].split("\n")) {
			String[] parts = s.split(" => ");
			ArrayList<Boolean> rule = new ArrayList<Boolean>();
			for(char c : parts[0].toCharArray())
				rule.add(c == '#');
			rules.put(rule,parts[1].charAt(0) == '#');
		}
		
		//for these inputs, after a specific number of cycles, the pattern stabilizes, and adds a static value each iteration afterwards
		//so, iterate until we see the same number added to the sum three times in a row, then add that number the remaining times till 50 billion
		
		int sum = 0;
		int changeFromLast = 0;
		int changeFrom2ndLast = 0;
		int counter = 0;
		
		while(true) {
			HashSet<Integer> newPlants = new HashSet<Integer>();
			for(int i = Collections.min(plants) - 2; i < Collections.max(plants) + 3; i++) {
				ArrayList<Boolean> rule = new ArrayList<Boolean>();
				for(int j = i-2; j < i+3; j++) {
					rule.add(plants.contains(j));
				}
				if(rules.get(rule))
					newPlants.add(i);
			}
			plants = newPlants;
			
			int result = 0;
			for(int i : plants)
				result += i;
			//if result equals sum + change, and change has remained constant twice, we've found our "loop"
			if(result == sum + changeFromLast && changeFromLast == changeFrom2ndLast) {
				sum = result;
				counter++;
				break;
			}
			//otherwise, set sum, changeFromLast, and changeFrom2ndLast
			changeFrom2ndLast = changeFromLast;
			changeFromLast = result - sum;
			sum = result;
			counter++;
		} 
		long endResult = sum + ((50000000000l - counter) * changeFromLast);
		return Long.toString(endResult);
	}

	public static void main(String[] args) {
		input = Input.fetchInput(2018,12);
		DayRunner.run(new Day12());
	}

}
