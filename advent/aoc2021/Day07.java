package advent.aoc2021;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

public class Day07 implements IDay {

	static String input;
	
	@Override
	public String part1() {
		ArrayList<Integer> nums = new ArrayList<Integer>(Arrays.stream(input.split(",")).map(Integer::parseInt).toList());
		Collections.sort(nums);
		//optimal position is median of sorted list
		int best = nums.get(nums.size() / 2);
		
		int totalFuel = 0;
		for(int i : nums)
			totalFuel += Math.abs(i - best);
		
		return Integer.toString(totalFuel);
	}

	@Override
	public String part2() {
		ArrayList<Integer> nums = new ArrayList<Integer>(Arrays.stream(input.split(",")).map(Integer::parseInt).toList());
		//for exponential, use mean instead of median
		//keep as double to round up or down to whole position - either could be correct
		double mean = (nums.stream().reduce(0, Integer::sum).doubleValue())/nums.size();
		
		
		//calculate scores at floor and ceiling, return best
		double floor = Math.floor(mean);
		int floorTot = 0;
		for(int i : nums) {
			double dist = Math.abs(floor - i);
			//sum of consecutive integers 0 to N is n(n+1)/2
			floorTot += dist * (dist+1) * 0.5;
		}
		
		double ceil = Math.ceil(mean);
		int ceilTot = 0;
		for(int i : nums) {
			double dist = Math.abs(ceil - i);
			//sum of consecutive integers 0 to N is n(n+1)/2
			ceilTot += dist * (dist+1) * 0.5;
		}
		
		return Integer.toString(Math.min(floorTot, ceilTot));
	}

	public static void main(String[] args) {
		input = Input.fetchInput(2021,7).trim();
		DayRunner.run(new Day07());
	}
}
