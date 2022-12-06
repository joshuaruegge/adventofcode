package advent.aoc2016;

import java.util.ArrayList;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

public class Day20 implements IDay {

	static String input;
	
	@Override
	public String part1() {
		//store ranges as long-long array list
		ArrayList<ArrayList<Long>> ranges = new ArrayList<ArrayList<Long>>();
		for(String s : input.split("\n")) {
			String[] parts = s.split("-");
			ArrayList<Long> range = new ArrayList<Long>();
			range.add(Long.parseLong(parts[0]));
			range.add(Long.parseLong(parts[1]));
			ranges.add(range);
		}
		
		//start at 0;
		long lowest = 0;
		
		
		//iterate through ranges - as we discover number is in range, set it to the end of the range
		//this is like starting with lowest at 0, then iterating up by 1, but much faster
		//because if we know lowest is within a range, it wont be outside of the range until (max +1)
		//so, we can skip checking all the numbers in between and go straight to (max+1)
		rangeCheck:
		while(true) {
			//go over all ranges
			for(ArrayList<Long> a : ranges) {
				//if lowest is in range
				if(lowest >= a.get(0) && lowest <= a.get(1)) {
					//set lowest to end of range + 1 (lowest value that is outside the range
					lowest = a.get(1) + 1;
					//restart iteration through ranges
					continue rangeCheck;
				}
			}
			//if we made it here, lowest is not within any of the ranges, so return it
			return Long.toString(lowest);
		}
	}

	@Override
	public String part2() {
		//store ranges as long-long array list
		ArrayList<ArrayList<Long>> ranges = new ArrayList<ArrayList<Long>>();
		for(String s : input.split("\n")) {
			String[] parts = s.split("-");
			ArrayList<Long> range = new ArrayList<Long>();
			range.add(Long.parseLong(parts[0]));
			range.add(Long.parseLong(parts[1]));
			ranges.add(range);
		}
		
		//start at 0;
		long lowest = 0;
		
		
		//iterate through ranges - as we discover number is in range, set it to the end of the range plus 1
		//this is like starting with lowest at 0, then iterating up by 1, but much faster
		//because if we know lowest is within a range, it wont be outside of the range until (max +1)
		//so, we can skip checking all the numbers in between and go straight to (max+1)
		
		//part 2 modification - instead of breaking on first discovery, iterate through entire range, and increase valid counter on discovery
		int validIPs = 0;
		rangeCheck:
		while(lowest < 4294967296l) {
			//go over all ranges
			for(ArrayList<Long> a : ranges) {
				//if lowest is in range
				if(lowest >= a.get(0) && lowest <= a.get(1)) {
					//set lowest to end of range + 1 (lowest value that is outside the range
					lowest = a.get(1) + 1;
					//restart iteration through ranges
					continue rangeCheck;
				}
			}
			//if we made it here, lowest is not within any of the ranges, so increment valid counter, then increment lowest by 1
			validIPs++;
			lowest++;
		}
		return Integer.toString(validIPs);
	}

	public static void main(String[] args) {
		input = Input.fetchInput(2016,20);
		DayRunner.run(new Day20());
	}

}
