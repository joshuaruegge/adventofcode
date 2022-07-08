package advent.aoc2015;

import java.util.ArrayList;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;

public class Day17 implements IDay {

	String input = "50\r\n"
			+ "44\r\n"
			+ "11\r\n"
			+ "49\r\n"
			+ "42\r\n"
			+ "46\r\n"
			+ "18\r\n"
			+ "32\r\n"
			+ "26\r\n"
			+ "40\r\n"
			+ "21\r\n"
			+ "7\r\n"
			+ "18\r\n"
			+ "43\r\n"
			+ "10\r\n"
			+ "47\r\n"
			+ "36\r\n"
			+ "24\r\n"
			+ "22\r\n"
			+ "40";
	
	@Override
	public String part1() {
		ArrayList<Integer> containers = new ArrayList<Integer>();
		for(String s : input.split("\r\n")) {
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
			}
			if(capacity == 150)
				validCount++;
		}
		return Integer.toString(validCount);
	}
	

	@Override
	public String part2() {
		ArrayList<Integer> containers = new ArrayList<Integer>();
		for(String s : input.split("\r\n")) {
			containers.add(Integer.parseInt(s));
		}
		//first, iterate to find lowest required number of containers
		int lowestContainers = Integer.MAX_VALUE;
		for(int combo = 0; combo < (1 << containers.size()); combo ++) {
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
				if(numContainers < lowestContainers)
					lowestContainers = numContainers;
			}
		}
		
		//then, iterate once more and count number of combos that equal 150 with our lowest number
		int numLowest = 0;
		for(int combo = 0; combo < (1 << containers.size()); combo ++) {
			int capacity = 0;
			for(int container = 0; container < containers.size(); container++) {
				//this returns nonzero if the digit at position container is 1, 
				//and returns 0 if it's zero. so if it's not zero, we are "using" this
				//container for this possibility, so add its capacity to total
				if((combo & (1 << container)) != 0) {
					capacity += containers.get(container);
				}
			}
			if(capacity == 150 && Integer.bitCount(combo) == lowestContainers) {
				numLowest++;
			}
		}
		return Integer.toString(numLowest);
	}

	public static void main(String[] args) {
		DayRunner.run(new Day17());
	}

}
