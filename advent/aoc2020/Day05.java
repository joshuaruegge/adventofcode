package advent.aoc2020;

import java.util.HashSet;
import java.util.stream.IntStream;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

public class Day05 implements IDay {

	static String input;
	
	@Override
	public String part1() {
		int highestSeatID = 0;
		for(String s : input.split("\n")) {
			int row = position(s.substring(0,7),127);
			int col = position(s.substring(7),7);
			int seatID = row * 8 + col;
			if(seatID > highestSeatID)
				highestSeatID = seatID;
		}
		return Integer.toString(highestSeatID);
	}

	@Override
	public String part2() {
		HashSet<Integer> seatIds = new HashSet<Integer>();
		for(String s : input.split("\n")) {
			int row = position(s.substring(0,7),127);
			int col = position(s.substring(7),7);
			int seatID = row * 8 + col;
			seatIds.add(seatID);
		}
		
		//get all ints in range, filter for a value not in seatIds but with its adjacents in seatIds
		return Integer.toString(IntStream.range(0, 1024).filter(x -> !seatIds.contains(x) && seatIds.contains(x+1) && seatIds.contains(x-1)).findFirst().getAsInt());
	}

	public int position(String s, int max) {
		int min = 0;
		for(char c : s.toCharArray()) {
			if(c == 'F' || c == 'L') {
				max -= (max - min + 1)/2;
			} else {
				min += (max - min + 1)/2;
			}
		}
		return min;
	}

	public static void main(String[] args) {
		input = Input.fetchInput(2020,5);
		DayRunner.run(new Day05());
	}
}
