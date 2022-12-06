package advent.aoc2018;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

public class Day04 implements IDay {

	static String input;
	
	@Override
	public String part1() {
		//parse the times to DateTime objects for sorting, and hashmap them to the other part of the input
		HashMap<LocalDateTime,String> logs = new HashMap<LocalDateTime,String>();
		for(String s : input.split("\n")) {
			String[] parts = s.split("] ");
			//fix time to fit format
			parts[0] = parts[0].substring(1).replace(" ", "T");
			LocalDateTime l = LocalDateTime.parse(parts[0]);
			logs.put(l, parts[1]);
		}
		ArrayList<LocalDateTime> sorted = new ArrayList<LocalDateTime>(logs.keySet());
		Collections.sort(sorted);
		//parse information into hashmap of guard id -> ArrayList of times spent asleep
		HashMap<Integer,ArrayList<Integer>> guards = new HashMap<Integer,ArrayList<Integer>>();
		int curGuard = -1;
		int startMin = -1;
		for(LocalDateTime l : sorted) {
			String[] parts = logs.get(l).split(" ");
			//if new shift start, update current guard
			if(parts.length > 2) {
				curGuard = Integer.parseInt(parts[1].substring(1));
			} else {
				//if waking up, put numbers from falling asleep till now into array
				if(parts[0].equals("wakes")) {
					int endMin = l.getMinute();
					ArrayList<Integer> sleepTimes = guards.getOrDefault(curGuard, new ArrayList<Integer>());
					for(int i = startMin; i < endMin; i++) {
						sleepTimes.add(i);
					}
					guards.put(curGuard, sleepTimes);
				} else {
					//otherwise, log falling asleep time
					startMin = l.getMinute();
				}
					
			}
			
		}
		//find guard asleep most often
		ArrayList<Integer> longest = new ArrayList<Integer>();
		int longestID = -1;
		for(int id : guards.keySet()) {
			ArrayList<Integer> asleep = guards.get(id);
			if(asleep.size() > longest.size()) {
				longest = asleep;
				longestID = id;
			}
		}
		
		//lastly, find most common value in longest with frequency map
		HashMap<Integer,Integer> freq = new HashMap<Integer,Integer>();
		for(int i : longest) {
			freq.put(i, freq.getOrDefault(i,0) + 1);
		}
		int highestFreq = 0;
		int bestMin = 0;
		for(int i : freq.keySet()) {
			if(freq.get(i) > highestFreq) {
				highestFreq = freq.get(i);
				bestMin = i;
			}
		}
		
		return Integer.toString(longestID * bestMin);
	}

	@Override
	public String part2() {
		//parse the times to DateTime objects for sorting, and hashmap them to the other part of the input
		HashMap<LocalDateTime,String> logs = new HashMap<LocalDateTime,String>();
		for(String s : input.split("\n")) {
			String[] parts = s.split("] ");
			//fix time to fit format
			parts[0] = parts[0].substring(1).replace(" ", "T");
			LocalDateTime l = LocalDateTime.parse(parts[0]);
			logs.put(l, parts[1]);
		}
		
		ArrayList<LocalDateTime> sorted = new ArrayList<LocalDateTime>(logs.keySet());
		Collections.sort(sorted);
		//parse information into hashmap of guard id -> ArrayList of times spent asleep
		HashMap<Integer,ArrayList<Integer>> guards = new HashMap<Integer,ArrayList<Integer>>();
		int curGuard = -1;
		int startMin = -1;
		for(LocalDateTime l : sorted) {
			String[] parts = logs.get(l).split(" ");
			//if new shift start, update current guard
			if(parts.length > 2) {
				curGuard = Integer.parseInt(parts[1].substring(1));
			} else {
				//if waking up, put numbers from falling asleep till now into array
				if(parts[0].equals("wakes")) {
					int endMin = l.getMinute();
					ArrayList<Integer> sleepTimes = guards.getOrDefault(curGuard, new ArrayList<Integer>());
					for(int i = startMin; i < endMin; i++) {
						sleepTimes.add(i);
					}
					guards.put(curGuard, sleepTimes);
				} else {
					//otherwise, log falling asleep time
					startMin = l.getMinute();
				}
					
			}
			
		}
		
		int bestGuard = 0;
		int bestMin = 0;
		int bestMinFreq = 0;
		for(int i : guards.keySet()) {
			ArrayList<Integer> asleep = guards.get(i);
			HashMap<Integer,Integer> freq = new HashMap<Integer,Integer>();
			for(int j : asleep) {
				freq.put(j, freq.getOrDefault(j,0) + 1);
			}
			int internalBestMin = 0;
			int internalBestFreq = 0;
			for(int j : freq.keySet()) {
				if(freq.get(j) > internalBestFreq) {
					internalBestFreq = freq.get(j);
					internalBestMin = j;
				}
			}
			if(internalBestFreq > bestMinFreq) {
				bestMinFreq = internalBestFreq;
				bestMin = internalBestMin;
				bestGuard = i;
			}
		}
		
		return Integer.toString(bestGuard * bestMin);
	}

	public static void main(String[] args) {
		input = Input.fetchInput(2018,4);
		DayRunner.run(new Day04());
	}

}
