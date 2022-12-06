package advent.aoc2020;

import java.util.ArrayList;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

public class Day15 implements IDay {

	static String input;
	
	@Override
	public String part1() {
		ArrayList<Integer> nums = new ArrayList<Integer>();
		String[] start = input.split(",");
		for(String s : start)
			nums.add(Integer.parseInt(s));
		while(nums.size() < 2020) {
			//pop last number off temporarily
			int current = nums.remove(nums.size() - 1);
			//determine last index
			int lastIndexOf = nums.lastIndexOf(current);
			//pop back on
			nums.add(current);
			if(lastIndexOf != -1) {
				//distance = (size - 1) - lastIndex
				nums.add(nums.size() - lastIndexOf - 1);
			} else {
				nums.add(0);
			}
		}
		return Integer.toString(nums.get(nums.size() - 1));
	}

	@Override
	public String part2() {
		//because a new number can only theoretically be as large as the size of the array, our number range is capped by our maximum length
		//so, keep track of the last turn any number was seen in a giant 30 million index array
		int[] lastSeen = new int[30000000];
		int turn = 1;
		String[] nums = input.split(",");
		//mark last turn each starting number was seen on
		for(int i = 0; i < nums.length - 1; i++) {
			lastSeen[Integer.parseInt(nums[i])] = turn;
			turn++;
		}
		//start from last value of input
		int previous = Integer.parseInt(nums[nums.length - 1]);
		while(turn < 30000000) {
			//check last time previous was seen
			int lastOfPrev = lastSeen[previous];
			//mark that we just saw previous
			lastSeen[previous] = turn;
			//if last turn previous was seen is not zero (if zero, previous has not yet been seen)
			if(lastOfPrev != 0) {
				//new number is current - last seen
				previous = turn - lastOfPrev;
			} else {
				//new number is 0
				previous = 0;
			}
			turn++;
		}
		
		return Integer.toString(previous);
	}

	public static void main(String[] args) {
		input = Input.fetchInput(2020,15).trim();
		DayRunner.run(new Day15());
	}
}
