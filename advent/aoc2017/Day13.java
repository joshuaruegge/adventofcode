package advent.aoc2017;

import java.util.HashMap;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;

public class Day13 implements IDay {

	String input = "0: 3\r\n"
			+ "1: 2\r\n"
			+ "2: 4\r\n"
			+ "4: 4\r\n"
			+ "6: 5\r\n"
			+ "8: 6\r\n"
			+ "10: 6\r\n"
			+ "12: 8\r\n"
			+ "14: 6\r\n"
			+ "16: 6\r\n"
			+ "18: 9\r\n"
			+ "20: 8\r\n"
			+ "22: 8\r\n"
			+ "24: 8\r\n"
			+ "26: 12\r\n"
			+ "28: 8\r\n"
			+ "30: 12\r\n"
			+ "32: 12\r\n"
			+ "34: 12\r\n"
			+ "36: 10\r\n"
			+ "38: 14\r\n"
			+ "40: 12\r\n"
			+ "42: 10\r\n"
			+ "44: 8\r\n"
			+ "46: 12\r\n"
			+ "48: 14\r\n"
			+ "50: 12\r\n"
			+ "52: 14\r\n"
			+ "54: 14\r\n"
			+ "56: 14\r\n"
			+ "58: 12\r\n"
			+ "62: 14\r\n"
			+ "64: 12\r\n"
			+ "66: 12\r\n"
			+ "68: 14\r\n"
			+ "70: 14\r\n"
			+ "72: 14\r\n"
			+ "74: 17\r\n"
			+ "76: 14\r\n"
			+ "78: 18\r\n"
			+ "84: 14\r\n"
			+ "90: 20\r\n"
			+ "92: 14";
	
	@Override
	public String part1() {
		//keep track of maximums of firewall
		HashMap<Integer,Integer> firewall = new HashMap<Integer,Integer>();
		for(String s : input.split("\r\n")) {
			String[] nums = s.split(": +");
			firewall.put(Integer.parseInt(nums[0]), Integer.parseInt(nums[1]));
		}
		int severity = 0;
		for(int i : firewall.keySet()) {
			//the position of a firewall at time i is normally equal to i % (max - 1)
			//because the firewall has (max - 1) valid positions, and it iterates from zero i times due to travel time
			//however, because the firewalls "bounce" back and forth rather than resetting to zero after max-1
			//we have to account for the travel time back from max-1 to zero
			//this makes the total distance covered between zeroes 2 * (max-1)
			//so, the position is then given by i % (2 * (max-1)) or i % (2 * max - 2)
			//notably, this is off for positions past the "bounce", but before zero, but we only care that it's correct for zero
			int position = i % (2 * firewall.get(i) - 2);
			if(position == 0)
				severity += i * firewall.get(i);
		}
		return Integer.toString(severity);
	}

	@Override
	public String part2() {
		HashMap<Integer,Integer> firewall = new HashMap<Integer,Integer>();
		for(String s : input.split("\r\n")) {
			String[] nums = s.split(": +");
			firewall.put(Integer.parseInt(nums[0]), Integer.parseInt(nums[1]));
		}
		//just include all of part 1 in a loop, and add an offset for the time waited since start
		//when we find the first value that severity = 0, return it
		int delay = 0;
		delay:
		while(true) {
			//iterate over keys
			for(int i : firewall.keySet()) {
				//the position of a firewall at time i is normally equal to i % (max - 1)
				//because the firewall has (max - 1) valid positions, and it iterates from zero i times due to travel time
				//however, because the firewalls "bounce" back and forth rather than resetting to zero after max-1
				//we have to account for the travel time back from max-1 to zero
				//this makes the total distance covered between zeroes 2 * (max-1)
				//so, the position is then given by i % (2 * (max-1)) or i % (2 * max - 2)
				
				//for part 2, we also have to add the delay we wait until traveling to the travel time i
				int position = (delay + i) % (2 * firewall.get(i) - 2);
				if(position == 0) {
					//collision happened - try next delay value
					delay++;
					continue delay;
				}
			}
			//if we make it all the way here, no collisions occurred over entire path - return delay
			return Integer.toString(delay);
		}
	}

	public static void main(String[] args) {
		DayRunner.run(new Day13());
	}

}
