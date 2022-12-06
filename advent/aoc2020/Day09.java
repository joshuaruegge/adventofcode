package advent.aoc2020;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

public class Day09 implements IDay {

	static String input;
	
	long part1;
	
	@Override
	public String part1() {
		List<Long> nums = Arrays.stream(input.split("\n")).map(x -> Long.parseLong(x)).toList();
		int curIndex = 25;
		while(curIndex < nums.size()) {
			//build list of all sums of previous 25 numbers
			HashSet<Long> sums = new HashSet<Long>();
			for(int i = curIndex - 1; i > curIndex - 25; i--) {
				for(int j = i-1; j > curIndex - 26; j--) {
					sums.add(nums.get(i) + nums.get(j));
				}
			}
			if(!sums.contains(nums.get(curIndex))) {
				break;
			}
			curIndex++;
		}
		//buffer part 1 value
		part1 = nums.get(curIndex);
		return Long.toString(nums.get(curIndex));
	}

	@Override
	public String part2() {
		List<Long> nums = Arrays.stream(input.split("\n")).map(x -> Long.parseLong(x)).toList();
		HashSet<Long> sumParts = null;
		sizeLoop:
		//iterate over possible contiguous section size
		for(int size = 2; size < nums.size(); size++) {
			//increment from start
			for(int start = 0; start < nums.size() - size; start++) {
				long sum = 0;
				sumParts = new HashSet<Long>();
				//add all numbers in contiguous length from start to start+size
				for(int cur = start; cur < start + size; cur++) {
					sum += nums.get(cur);
					sumParts.add(nums.get(cur));
				}
				if(sum == part1) {
					break sizeLoop;
				}
			}
		}
		//extract and sum min and max of components
		return Long.toString(sumParts.stream().mapToLong(x -> x).min().getAsLong() + sumParts.stream().mapToLong(x -> x).max().getAsLong());
	}

	public static void main(String[] args) {
		input = Input.fetchInput(2020,9);
		DayRunner.run(new Day09());
	}
}
