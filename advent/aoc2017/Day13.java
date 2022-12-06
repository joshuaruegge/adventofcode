package advent.aoc2017;

import java.util.HashMap;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

public class Day13 implements IDay {

	static String input;
	
	@Override
	public String part1() {
		//keep track of maximums of firewall
		HashMap<Integer,Integer> firewall = new HashMap<Integer,Integer>();
		for(String s : input.split("\n")) {
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
		for(String s : input.split("\n")) {
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
		input = Input.fetchInput(2017,13);
		DayRunner.run(new Day13());
	}

}
