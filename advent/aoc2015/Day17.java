package advent.aoc2015;

import java.util.ArrayList;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

public class Day17 implements IDay {

	static String input;
	
	@Override
	public String part1() {
		ArrayList<Integer> containers = new ArrayList<Integer>();
		for(String s : input.split("\n")) {
			containers.add(Integer.parseInt(s));
		}
		//use integer as bitmask for possible combinations
		int validCount = 0;
		//this will iterate over all (binary) numbers from 00000... (length of container list)
		//to 11111...(length of container list), using whether there is a 1 at a position
		//to determine whether to include the container at that same index
		for(int combo = 0; combo < (1 << containers.size()); combo++) {
			int capacity = 0;
			for(int container = 0; container < containers.size(); container++) {
				//this returns nonzero if the bit at position container is 1, 
				//and returns 0 if it's zero. so if it's not zero, we are "using" this
				//container for this possibility, so add its capacity to total
				if((combo & (1 << container)) != 0) {
					capacity += containers.get(container);
				}
				if(capacity > 150)
					break;
			}
			if(capacity == 150)
				validCount++;
		}
		return Integer.toString(validCount);
	}
	

	@Override
	public String part2() {
		ArrayList<Integer> containers = new ArrayList<Integer>();
		for(String s : input.split("\n")) {
			containers.add(Integer.parseInt(s));
		}
		int lowestContainers = Integer.MAX_VALUE;
		int numLowest = 0;
		for(int combo = 0; combo < (1 << containers.size()); combo ++) {
			if(Integer.bitCount(combo) > lowestContainers)
				continue;
			int capacity = 0;
			for(int container = 0; container < containers.size(); container++) {
				//this returns nonzero if the digit at position container is 1, 
				//and returns 0 if it's zero. so if it's not zero, we are "using" this
				//container for this possibility, so add its capacity to total
				if((combo & (1 << container)) != 0) {
					capacity += containers.get(container);
				}
			}
			if(capacity == 150) {
				//bitCount returns number of ones in binary of integer
				//coincidentally, for this representation, this is number of containers used
				int numContainers = Integer.bitCount(combo);
				if(numContainers < lowestContainers) {
					lowestContainers = numContainers;
					//reset numLowest, because we have a new lowest. set it to 1 to count current
					numLowest = 1;
				} else if(numContainers == lowestContainers) {
					numLowest++;
				}
			}
		}
		return Integer.toString(numLowest);
		
	}

	public static void main(String[] args) {
		input = Input.fetchInput(2015,17);
		DayRunner.run(new Day17());
	}

}
