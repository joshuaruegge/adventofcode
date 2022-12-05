package advent.aoc2021;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;

public class Day06 implements IDay {

	String input = "4,1,1,1,5,1,3,1,5,3,4,3,3,1,3,3,1,5,3,2,4,4,3,4,1,4,2,2,1,3,5,1,1,3,2,5,1,1,4,2,5,4,3,2,5,3,3,4,5,4,3,5,4,2,5,5,2,2,2,3,5,5,4,2,1,1,5,1,4,3,2,2,1,2,1,5,3,3,3,5,1,5,4,2,2,2,1,4,2,5,2,3,3,2,3,4,4,1,4,4,3,1,1,1,1,1,4,4,5,4,2,5,1,5,4,4,5,2,3,5,4,1,4,5,2,1,1,2,5,4,5,5,1,1,1,1,1,4,5,3,1,3,4,3,3,1,5,4,2,1,4,4,4,1,1,3,1,3,5,3,1,4,5,3,5,1,1,2,2,4,4,1,4,1,3,1,1,3,1,3,3,5,4,2,1,1,2,1,2,3,3,5,4,1,1,2,1,2,5,3,1,5,4,3,1,5,2,3,4,4,3,1,1,1,2,1,1,2,1,5,4,2,2,1,4,3,1,1,1,1,3,1,5,2,4,1,3,2,3,4,3,4,2,1,2,1,2,4,2,1,5,2,2,5,5,1,1,2,3,1,1,1,3,5,1,3,5,1,3,3,2,4,5,5,3,1,4,1,5,2,4,5,5,5,2,4,2,2,5,2,4,1,3,2,1,1,4,4,1,5";
	
	@Override
	public String part1() {
		//rather than keeping whole string, count number of each type of lanternfish - all of a type "iterate" simultaneously (all 4s become 3s, etc)
		int[] fish = new int[9];
		for(String s : input.split(",")) {
			fish[Integer.parseInt(s)]++;
		}
		
		for(int day = 0; day < 80; day++) {
			int numNew = fish[0];
			//move counters downwards
			for(int i = 0; i < 8; i++) {
				fish[i] = fish[i+1];
			}
			//add new fish
			fish[6] += numNew;
			//add fish on cooldown
			fish[8] = numNew;
		}
		int total = 0;
		for(int i : fish)
			total += i;
		
		return Integer.toString(total);
	}

	@Override
	public String part2() {
		//rather than keeping whole string, count number of lanternfish
		long[] fish = new long[9];
		for(String s : input.split(",")) {
			fish[Integer.parseInt(s)]++;
		}
		
		for(int day = 0; day < 256; day++) {
			long numNew = fish[0];
			//move counters downwards
			for(int i = 0; i < 8; i++) {
				fish[i] = fish[i+1];
			}
			fish[6] += numNew;
			fish[8] = numNew;
		}
		long total = 0;
		for(long i : fish)
			total += i;
		
		return Long.toString(total);
	}

	public static void main(String[] args) {
		DayRunner.run(new Day06());
	}

}
