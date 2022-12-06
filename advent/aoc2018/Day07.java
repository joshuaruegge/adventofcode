package advent.aoc2018;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

public class Day07 implements IDay {

	static String input;
	
	@Override
	public String part1() {
		HashMap<Character,HashSet<Character>> stepsBefore = new HashMap<Character,HashSet<Character>>();
		for(String s : input.split("\n")) {
			char requirement = s.split(" ")[1].charAt(0);
			char step = s.split(" ")[7].charAt(0);
			HashSet<Character> stepsRequired = stepsBefore.getOrDefault(step, new HashSet<Character>());
			stepsRequired.add(requirement);
			stepsBefore.put(step,stepsRequired);
			//also add requirement (potentially empty)
			stepsBefore.put(requirement, stepsBefore.getOrDefault(requirement, new HashSet<Character>()));
		}
		String solution = "";
		while(solution.length() < 26) {
			//find first available step
			for(char step : sorted(stepsBefore.keySet())) {
				if(stepsBefore.get(step).isEmpty()) {
					stepsBefore.remove(step);
					//mark step as complete in all requirement sets
					for(HashSet<Character> set : stepsBefore.values())
						set.remove(step);
					solution += step;
					break;
				}
			}
		}
		return solution;
	}
	
	//helper method - returns sorted set after sorting (for iteration)
	public ArrayList<Character> sorted(Set<Character> c) {
		ArrayList<Character> sort = new ArrayList<Character>(c);
		Collections.sort(sort);
		return sort;
	}

	@Override
	public String part2() {
		HashMap<Character,HashSet<Character>> stepsBefore = new HashMap<Character,HashSet<Character>>();
		for(String s : input.split("\n")) {
			char requirement = s.split(" ")[1].charAt(0);
			char step = s.split(" ")[7].charAt(0);
			HashSet<Character> stepsRequired = stepsBefore.getOrDefault(step, new HashSet<Character>());
			stepsRequired.add(requirement);
			stepsBefore.put(step,stepsRequired);
			//also add requirement (potentially empty)
			stepsBefore.put(requirement, stepsBefore.getOrDefault(requirement, new HashSet<Character>()));
		}
		String solution = "";
		//workers as character -> time remaining hashmap
		HashMap<Character,Integer> workers = new HashMap<Character,Integer>();
		int stepCounter = -1;
		while(solution.length() < 26) {
			stepCounter++;
			HashSet<Character> removalList = new HashSet<Character>();
			//decrement any present steps, remove and append finished steps
			for(char c : workers.keySet()) {
				if(workers.get(c) - 1 == 0)
					removalList.add(c);
				workers.put(c, workers.get(c) - 1);
			}
			
			for(char c : removalList) {
				workers.remove(c);
				//mark step as complete in prerequisites
				for(HashSet<Character> set : stepsBefore.values())
					set.remove(c);
				solution += c;
			}

			//assign workers any available tasks
			ArrayList<Character> available = available(stepsBefore);
			while(workers.size() < 5 && available.size() > 0) {
				char step = available.remove(0);
				stepsBefore.remove(step);
				int stepLength = 60 + (step - 'A') + 1;
				workers.put(step, stepLength);
			}
		}
		return Integer.toString(stepCounter);
	}
	
	//helper method - builds available step list in sorted order
	public ArrayList<Character> available(HashMap<Character,HashSet<Character>> a) {
		ArrayList<Character> available = new ArrayList<Character>();
		for(char c : sorted(a.keySet())) {
			if(a.get(c).isEmpty())
				available.add(c);
		}
		return available;
	}

	public static void main(String[] args) {
		input = Input.fetchInput(2018,7);
		DayRunner.run(new Day07());
	}

}
