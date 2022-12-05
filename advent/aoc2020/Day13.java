package advent.aoc2020;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;

public class Day13 implements IDay {

	String input = "1002392\r\n"
			+ "23,x,x,x,x,x,x,x,x,x,x,x,x,41,x,x,x,37,x,x,x,x,x,421,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,17,x,19,x,x,x,x,x,x,x,x,x,29,x,487,x,x,x,x,x,x,x,x,x,x,x,x,13";
	
	@Override
	public String part1() {
		String[] chunks = input.split("\r\n");
		int targetMin = Integer.parseInt(chunks[0]);
		int lowestGap = Integer.MAX_VALUE;
		int bestMin = 0;
		for(String s : chunks[1].split(",")) {
			if(s.equals("x"))
				continue;
			int min = Integer.parseInt(s);
			//gap = minute - (distance from targetMin)
			int gap = min - (targetMin % min);
			if(gap < lowestGap) {
				lowestGap = gap;
				bestMin = min;
			}
		}
		
		return Integer.toString(lowestGap * bestMin);
	}

	@Override
	public String part2() {
		String[] nums = input.split("\r\n")[1].split(",");
		int[] intervals = new int[nums.length];
		for(int i = 0; i < nums.length; i++) {
			if(nums[i].equals("x"))
				intervals[i] = -1;
			else
				intervals[i] = Integer.parseInt(nums[i]);
		}
		
		//chinese remainder theorem 
		
		//total moduli - product of all moduli in intervals
		long mod = 1;
		for(int i : intervals)
			if(i != -1)
				mod *= i;
		
		long sum = 0;
		
		//for chinese remainder theorem, we have to sum (remainder * bigN * x) for all intervals
		//where bigN is the total moduli divided by the current interval, and remainder is the remainder necessary for that interval 
		//(in our case, the bus must arrive at a multiple of interval + i, so our remainder is ((interval - i) mod interval)
		//mod interval is necessary for the zero case
		//lastly, x is the smallest linear value such that (bigN * x) % interval = 1
		//so, for each remainder set in remainders, we add remainder * bigN * x to the sum
		
		for(int i = 0; i < intervals.length; i++) {
			if(intervals[i] == -1)
				continue;
			long bigN = mod / intervals[i];
			//departure time % interval must have a remainder of (interval - i) mod interval
			int remainder = (intervals[i] - i) % intervals[i];
			int x = 1;
			while((bigN * x) % intervals[i] != 1)
				x++;
			sum += (bigN * remainder * x);
		}
		
		//lastly, we take our entire sum modulo our total mod from before
		sum %= mod;
		
		return Long.toString(sum);
	}

	public static void main(String[] args) {
		DayRunner.run(new Day13());
	}

}
