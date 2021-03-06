package advent.aoc2015;

import java.util.ArrayList;
import java.util.Collections;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;

public class Day14 implements IDay {

	String input = "Vixen can fly 19 km/s for 7 seconds, but then must rest for 124 seconds.\r\n"
			+ "Rudolph can fly 3 km/s for 15 seconds, but then must rest for 28 seconds.\r\n"
			+ "Donner can fly 19 km/s for 9 seconds, but then must rest for 164 seconds.\r\n"
			+ "Blitzen can fly 19 km/s for 9 seconds, but then must rest for 158 seconds.\r\n"
			+ "Comet can fly 13 km/s for 7 seconds, but then must rest for 82 seconds.\r\n"
			+ "Cupid can fly 25 km/s for 6 seconds, but then must rest for 145 seconds.\r\n"
			+ "Dasher can fly 14 km/s for 3 seconds, but then must rest for 38 seconds.\r\n"
			+ "Dancer can fly 3 km/s for 16 seconds, but then must rest for 37 seconds.\r\n"
			+ "Prancer can fly 25 km/s for 6 seconds, but then must rest for 143 seconds.";
	
	int flightTime = 2503;
	
	@Override
	public String part1() {
		//store reindeer as 3-item int[] (speed, flight time, rest time)
		ArrayList<int[]> deers = new ArrayList<int[]>();
		for(String s : input.split("\r\n")) {
			String[] parts = s.split(" ");
			deers.add(new int[] {Integer.parseInt(parts[3]), Integer.parseInt(parts[6]), Integer.parseInt(parts[13])});
		}
		//index-linked array of results for each deer
		ArrayList<Integer> results = new ArrayList<Integer>();
		for(int[] deer : deers) {
			int distance = 0;
			//time it takes for a deer to complete one run-rest cycle
			int cycleLength = deer[1] + deer[2];
			//number of full cycles completed over the time interval
			int numCycles = flightTime / cycleLength;
			//add total distance covered by full cycles
			//speed * time running * number of cycles
			distance += deer[0] * deer[1] * numCycles;
			//determine remainder of time
			int leftover = flightTime % cycleLength;
			//if remainder of time is less than run time, add remainder
			if(leftover < deer[1]) {
				distance += deer[0] * leftover;
			}
			//otherwise, deer is in resting period, and ran for full run length
			else {
				distance += deer[1] * deer[0];
			}
			results.add(distance);
		}
		//return furthest distance
		return Integer.toString(Collections.max(results));
	}

	@Override
	public String part2() {
		//now, it gets just a smidge more complicated
		//store reindeer as 3-item int[]
		ArrayList<int[]> deers = new ArrayList<int[]>();
		for(String s : input.split("\r\n")) {
			String[] parts = s.split(" ");
			deers.add(new int[] {Integer.parseInt(parts[3]), Integer.parseInt(parts[6]), Integer.parseInt(parts[13])});
		}
		//now, keep track of scores as an array rather than a list
		//still index-linked
		int[] scores = new int[deers.size()];
		//additionally, track distances each reindeer has covered
		int[] distances = new int[deers.size()];
		for(int i = 0; i < flightTime; i++) {
			//travel deer forwards if necessary
			for(int j = 0; j < deers.size(); j++) {
				int[] deer = deers.get(j);
				//calculate cycle length
				int cycleLength = deer[1] + deer[2];
				//if remainder value is less than or equal to run time
				//deer is running so increment distance by speed
				if((i % cycleLength) < deer[1]) {
					distances[j] += deer[0];
				}
			}
			//find highest distance of any deer
			int best = 0;
			for(int j = 0; j < distances.length; j++) {
				if(distances[j] > best) {
					best = distances[j];
				}
			}
			//to account for ties, increment all scores that are equal to best dist
			for(int j = 0; j < scores.length; j++)
				if(distances[j] == best)
					scores[j]++;
			
		}
		//calculate final maximum score
		int max = 0;
		for(int i : scores) {
			if(i > max)
				max = i;
		}
		return Integer.toString(max);
	}

	public static void main(String[] args) {
		DayRunner.run(new Day14());
	}

}
