package advent.aoc2020;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

public class Day01 implements IDay {

	static String input;
	
	@Override
	public String part1() {
		HashSet<Integer> numRequired = new HashSet<Integer>();
		for(String s : input.split("\n")) {
			int num = Integer.parseInt(s);
			if(numRequired.contains(2020-num)) {
				return Integer.toString(num * (2020 - num));
			}
			numRequired.add(num);
		}
		return null;
	}

	@Override
	public String part2() {
		//parse into sorted list
		List<Integer> nums = Arrays.stream(input.split("\n")).map(x -> Integer.parseInt(x)).sorted().toList();
		//keep two indexes, and for each first index, keep a set of potential third indexes based on each second index
		//if potential third index is discovered, return
		for(int index1 = 0; index1 < nums.size() - 2; index1++) {
			HashSet<Integer> numRequired = new HashSet<Integer>();
			int currentSum = 2020 - nums.get(index1);
			for(int index2 = index1 + 1; index2 < nums.size(); index2++) {
				if(numRequired.contains(currentSum - nums.get(index2))) {
					return Integer.toString(nums.get(index1) * nums.get(index2) *  (currentSum - nums.get(index2)));
				}
				numRequired.add(nums.get(index2));
			}
		}
		return null;
	}

	public static void main(String[] args) {
		input = Input.fetchInput(2020,1);
		DayRunner.run(new Day01());
	}
}
