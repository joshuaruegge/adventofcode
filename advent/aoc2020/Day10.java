package advent.aoc2020;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

public class Day10 implements IDay {

	static String input;
	
	@Override
	public String part1() {
		ArrayList<Integer> nums = new ArrayList<Integer>(Arrays.stream(input.split("\n")).map(x -> Integer.parseInt(x)).toList());
		nums.add(0);
		nums.add(nums.stream().mapToInt(x -> x).max().getAsInt() + 3);
		Collections.sort(nums);
		int ones = 0;
		int threes = 0;
		for(int i = 0; i < nums.size() - 1; i++) {
			if(nums.get(i+1) - nums.get(i) == 1)
				ones++;
			if(nums.get(i+1) - nums.get(i) == 3)
				threes++;
		}
		return Integer.toString(ones * threes);
	}

	@Override
	public String part2() {
		ArrayList<Integer> nums = new ArrayList<Integer>(Arrays.stream(input.split("\n")).map(x -> Integer.parseInt(x)).toList());
		nums.add(0);
		nums.add(nums.stream().mapToInt(x -> x).max().getAsInt() + 3);
		Collections.sort(nums);
		HashMap<Integer,Long> count = new HashMap<Integer,Long>();
		//first element has "one" path to it
		count.put(0,1l);
		for(int i : nums) {
			//skip - zero already in
			if(i == 0)
				continue;
			//number of potential paths to i is sum of all potential paths from i, i-2, and i-3
			count.put(i, count.getOrDefault(i-1,0l) + count.getOrDefault(i-2, 0l) + count.getOrDefault(i-3, 0l));
		}
		return count.get(nums.stream().max(Integer::compare).get()).toString();
	}

	public static void main(String[] args) {
		input = Input.fetchInput(2020,10);
		DayRunner.run(new Day10());
	}
}
