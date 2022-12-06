package advent.aoc2015;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

public class Day20 implements IDay {

	static String input;
	
	@Override
	public String part1() {
		int goal = Integer.parseInt(input);
		
		int[] houses = new int[goal / 10];
		//iterate over elves from 1 to goal/10
		for(int i = 1; i < goal / 10; i++) {
			//iterate over houses elf will hit from (elf) to goal / 10, incrementing by (elf)
			for(int j = i; j < goal / 10; j += i) {
				//increase house
				houses[j] += i * 10;
				//if house just got increased above goal, break and return house number
				//(first elf to increase any house past goal will also be house number because
				//each new elf is largest increase to any present value so far)
				if(houses[i] > goal)
					return Integer.toString(i);
			}
		}
		return null;
	}

	@Override
	public String part2() {
		int goal = Integer.parseInt(input);
		int[] houses = new int[goal / 10];
		//iterate over elves from 1 to goal/10
		for(int i = 1; i < goal; i++) {
			//iterate over houses elf will hit from 0 to 50, or bound of array, whichever comes first
			for(int j = i; j < i * 50 && j < houses.length; j += i) {
				//increase house
				houses[j] += i * 11;
				//if house just got increased above goal, break and return house number
				//(first elf to increase any house past goal will also be house number because
				//each new elf is largest increase to any present value so far)
				if(houses[i] > goal)
					return Integer.toString(i);
			}
		}
		return null;
	}

	public static void main(String[] args) {
		input = Input.fetchInput(2015,20).replace("\n","");
		DayRunner.run(new Day20());
	}

}
