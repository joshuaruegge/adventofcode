package advent.aoc2017;

import java.util.ArrayList;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

public class Day02 implements IDay {

	static String input;
	
	@Override
	public String part1() {
		int checksum = 0;
		for(String  s: input.split("\n")) {
			String[] nums = s.split(" |\t");
			int max = 0;
			int min = Integer.MAX_VALUE;
			for(String n : nums) {
				int number = Integer.parseInt(n);
				if(number > max)
					max = number;
				if(number < min)
					min = number;
			}
			checksum += (max - min);
		}
		return Integer.toString(checksum);
	}

	@Override
	public String part2() {
		int checksum = 0;
		rowLoop:
		for(String s : input.split("\n")) {
			String[] nums = s.split(" |\t");
			ArrayList<Integer> numbers = new ArrayList<Integer>();
			for(String t : nums)
				numbers.add(Integer.parseInt(t));
			//now, iterate over numbers to find our evenly divisible pair
			for(int i : numbers) {
				for(int j : numbers) {
					if(i == j)
						continue;
					//if numbers are in correct order and divide to zero
					if(i > j && i % j == 0) {
						//add division to checksum, then break all the way to next row
						checksum += (i / j);
						continue rowLoop;
					}
				}
			}
		}
		return Integer.toString(checksum);
	}

	public static void main(String[] args) {
		input = Input.fetchInput(2017,2);
		DayRunner.run(new Day02());
	}

}
