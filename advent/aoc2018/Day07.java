package advent.aoc2018;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;

public class Day07 implements IDay {

	String input = "Step W must be finished before step B can begin.\r\n"
			+ "Step G must be finished before step T can begin.\r\n"
			+ "Step B must be finished before step P can begin.\r\n"
			+ "Step R must be finished before step M can begin.\r\n"
			+ "Step K must be finished before step Q can begin.\r\n"
			+ "Step Z must be finished before step X can begin.\r\n"
			+ "Step V must be finished before step S can begin.\r\n"
			+ "Step D must be finished before step U can begin.\r\n"
			+ "Step Y must be finished before step J can begin.\r\n"
			+ "Step A must be finished before step C can begin.\r\n"
			+ "Step M must be finished before step U can begin.\r\n"
			+ "Step E must be finished before step X can begin.\r\n"
			+ "Step T must be finished before step F can begin.\r\n"
			+ "Step U must be finished before step C can begin.\r\n"
			+ "Step C must be finished before step Q can begin.\r\n"
			+ "Step S must be finished before step N can begin.\r\n"
			+ "Step X must be finished before step H can begin.\r\n"
			+ "Step F must be finished before step L can begin.\r\n"
			+ "Step Q must be finished before step J can begin.\r\n"
			+ "Step P must be finished before step J can begin.\r\n"
			+ "Step I must be finished before step L can begin.\r\n"
			+ "Step J must be finished before step L can begin.\r\n"
			+ "Step L must be finished before step N can begin.\r\n"
			+ "Step H must be finished before step O can begin.\r\n"
			+ "Step N must be finished before step O can begin.\r\n"
			+ "Step B must be finished before step S can begin.\r\n"
			+ "Step A must be finished before step T can begin.\r\n"
			+ "Step G must be finished before step K can begin.\r\n"
			+ "Step Z must be finished before step N can begin.\r\n"
			+ "Step V must be finished before step I can begin.\r\n"
			+ "Step Z must be finished before step Q can begin.\r\n"
			+ "Step I must be finished before step J can begin.\r\n"
			+ "Step S must be finished before step I can begin.\r\n"
			+ "Step P must be finished before step I can begin.\r\n"
			+ "Step B must be finished before step C can begin.\r\n"
			+ "Step M must be finished before step L can begin.\r\n"
			+ "Step G must be finished before step Z can begin.\r\n"
			+ "Step M must be finished before step C can begin.\r\n"
			+ "Step U must be finished before step F can begin.\r\n"
			+ "Step B must be finished before step Y can begin.\r\n"
			+ "Step W must be finished before step U can begin.\r\n"
			+ "Step G must be finished before step M can begin.\r\n"
			+ "Step M must be finished before step J can begin.\r\n"
			+ "Step C must be finished before step L can begin.\r\n"
			+ "Step K must be finished before step D can begin.\r\n"
			+ "Step S must be finished before step X can begin.\r\n"
			+ "Step Q must be finished before step N can begin.\r\n"
			+ "Step V must be finished before step N can begin.\r\n"
			+ "Step R must be finished before step C can begin.\r\n"
			+ "Step E must be finished before step H can begin.\r\n"
			+ "Step D must be finished before step P can begin.\r\n"
			+ "Step H must be finished before step N can begin.\r\n"
			+ "Step X must be finished before step O can begin.\r\n"
			+ "Step K must be finished before step Y can begin.\r\n"
			+ "Step R must be finished before step F can begin.\r\n"
			+ "Step L must be finished before step O can begin.\r\n"
			+ "Step Y must be finished before step M can begin.\r\n"
			+ "Step T must be finished before step I can begin.\r\n"
			+ "Step T must be finished before step Q can begin.\r\n"
			+ "Step B must be finished before step F can begin.\r\n"
			+ "Step C must be finished before step N can begin.\r\n"
			+ "Step V must be finished before step M can begin.\r\n"
			+ "Step T must be finished before step N can begin.\r\n"
			+ "Step S must be finished before step L can begin.\r\n"
			+ "Step P must be finished before step H can begin.\r\n"
			+ "Step X must be finished before step Q can begin.\r\n"
			+ "Step Z must be finished before step I can begin.\r\n"
			+ "Step Q must be finished before step O can begin.\r\n"
			+ "Step I must be finished before step N can begin.\r\n"
			+ "Step E must be finished before step P can begin.\r\n"
			+ "Step R must be finished before step L can begin.\r\n"
			+ "Step P must be finished before step L can begin.\r\n"
			+ "Step T must be finished before step H can begin.\r\n"
			+ "Step G must be finished before step X can begin.\r\n"
			+ "Step J must be finished before step H can begin.\r\n"
			+ "Step G must be finished before step V can begin.\r\n"
			+ "Step K must be finished before step N can begin.\r\n"
			+ "Step R must be finished before step Q can begin.\r\n"
			+ "Step Z must be finished before step T can begin.\r\n"
			+ "Step E must be finished before step F can begin.\r\n"
			+ "Step Y must be finished before step H can begin.\r\n"
			+ "Step P must be finished before step N can begin.\r\n"
			+ "Step S must be finished before step O can begin.\r\n"
			+ "Step L must be finished before step H can begin.\r\n"
			+ "Step W must be finished before step E can begin.\r\n"
			+ "Step X must be finished before step N can begin.\r\n"
			+ "Step Z must be finished before step D can begin.\r\n"
			+ "Step A must be finished before step H can begin.\r\n"
			+ "Step T must be finished before step X can begin.\r\n"
			+ "Step E must be finished before step Q can begin.\r\n"
			+ "Step K must be finished before step U can begin.\r\n"
			+ "Step M must be finished before step T can begin.\r\n"
			+ "Step J must be finished before step O can begin.\r\n"
			+ "Step D must be finished before step N can begin.\r\n"
			+ "Step K must be finished before step A can begin.\r\n"
			+ "Step G must be finished before step E can begin.\r\n"
			+ "Step R must be finished before step H can begin.\r\n"
			+ "Step W must be finished before step M can begin.\r\n"
			+ "Step U must be finished before step N can begin.\r\n"
			+ "Step Q must be finished before step H can begin.\r\n"
			+ "Step Y must be finished before step A can begin.";
	
	@Override
	public String part1() {
		HashMap<Character,HashSet<Character>> stepsBefore = new HashMap<Character,HashSet<Character>>();
		for(String s : input.split("\r\n")) {
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
		for(String s : input.split("\r\n")) {
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
		DayRunner.run(new Day07());
	}

}
