package advent.aoc2021;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

public class Day06 implements IDay {

	static String input;
	
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
		input = Input.fetchInput(2021,6).trim();
		DayRunner.run(new Day06());
	}
}
