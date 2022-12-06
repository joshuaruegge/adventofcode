package advent.aoc2017;

import java.util.ArrayList;
import java.util.HashSet;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

public class Day06 implements IDay {

	static String input;
	
	@Override
	public String part1() {
		String[] nums = input.split("\t");
		ArrayList<Integer> blocks = new ArrayList<Integer>();
		for(int i = 0; i < nums.length; i++) {
			blocks.add(Integer.parseInt(nums[i]));		
		}
		
		//keep track of seen states
		HashSet<ArrayList<Integer>> seen = new HashSet<ArrayList<Integer>>();
		int cycleCount = 0;
		do {
			seen.add(blocks);
			ArrayList<Integer> newBlocks = new ArrayList<Integer>(blocks);
			//find highest index
			int highest = 0;
			int highestIndex = -1;
			for(int i = 0; i < blocks.size(); i++) {
				if(newBlocks.get(i) > highest) {
					highest = newBlocks.get(i);
					highestIndex = i;
				}
			}
			
			int distribute = newBlocks.get(highestIndex);
			newBlocks.set(highestIndex, 0);
			int index = highestIndex + 1;
			if(index >= blocks.size())
				index %= blocks.size();
			while(distribute > 0) {
				newBlocks.set(index, newBlocks.get(index) + 1);
				distribute--;
				index++;
				if(index >= blocks.size())
					index %= blocks.size();
			}
			cycleCount++;
			blocks = newBlocks;
		} while(!seen.contains(blocks));
		return Integer.toString(cycleCount);
	}

	@Override
	public String part2() {
		String[] nums = input.split("\t");
		ArrayList<Integer> blocks = new ArrayList<Integer>();
		for(int i = 0; i < nums.length; i++) {
			blocks.add(Integer.parseInt(nums[i]));		
		}
		
		//keep track of seen states
		HashSet<ArrayList<Integer>> seen = new HashSet<ArrayList<Integer>>();
		seen.add(new ArrayList<Integer>(blocks));
		int cycleCount = 0;
		
		//part 2 modification - remember first time we see a duplicate state
		ArrayList<Integer> firstRepeat = null;
		int seenLength = -1;
		while(true) {
			ArrayList<Integer> newBlocks = new ArrayList<Integer>(blocks);
			//find highest index
			int highest = 0;
			int highestIndex = -1;
			for(int i = 0; i < blocks.size(); i++) {
				if(newBlocks.get(i) > highest) {
					highest = newBlocks.get(i);
					highestIndex = i;
				}
			}
			
			int distribute = newBlocks.get(highestIndex);
			newBlocks.set(highestIndex, 0);
			int index = highestIndex + 1;
			if(index >= blocks.size())
				index %= blocks.size();
			while(distribute > 0) {
				newBlocks.set(index, newBlocks.get(index) + 1);
				distribute--;
				index++;
				if(index >= blocks.size())
					index %= blocks.size();
			}
			cycleCount++;
			//if third time seeing first repeating value, break
			if(newBlocks.equals(firstRepeat)) {
				break;
			}
			//if first repeat has not been seen and first repeat is found
			if(firstRepeat == null && seen.contains(newBlocks)) {
				//set first repeat
				firstRepeat = newBlocks;
				//remember where it occurred
				seenLength = cycleCount;
			}
			seen.add(newBlocks);
			blocks = newBlocks;
		}
		//after breaking, return current distance - distance of first repeat to get distance between repeats
		return Integer.toString(cycleCount - seenLength);
	}

	public static void main(String[] args) {
		input = Input.fetchInput(2017,6).replace("\n","");
		DayRunner.run(new Day06());
	}

}
