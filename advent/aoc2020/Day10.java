package advent.aoc2020;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;

public class Day10 implements IDay {

	String input = "49\r\n"
			+ "89\r\n"
			+ "70\r\n"
			+ "56\r\n"
			+ "34\r\n"
			+ "14\r\n"
			+ "102\r\n"
			+ "148\r\n"
			+ "143\r\n"
			+ "71\r\n"
			+ "15\r\n"
			+ "107\r\n"
			+ "127\r\n"
			+ "165\r\n"
			+ "135\r\n"
			+ "26\r\n"
			+ "119\r\n"
			+ "46\r\n"
			+ "53\r\n"
			+ "69\r\n"
			+ "134\r\n"
			+ "1\r\n"
			+ "40\r\n"
			+ "81\r\n"
			+ "140\r\n"
			+ "160\r\n"
			+ "33\r\n"
			+ "117\r\n"
			+ "82\r\n"
			+ "55\r\n"
			+ "25\r\n"
			+ "11\r\n"
			+ "128\r\n"
			+ "159\r\n"
			+ "61\r\n"
			+ "105\r\n"
			+ "112\r\n"
			+ "99\r\n"
			+ "93\r\n"
			+ "151\r\n"
			+ "20\r\n"
			+ "108\r\n"
			+ "168\r\n"
			+ "2\r\n"
			+ "109\r\n"
			+ "75\r\n"
			+ "139\r\n"
			+ "170\r\n"
			+ "65\r\n"
			+ "114\r\n"
			+ "21\r\n"
			+ "92\r\n"
			+ "106\r\n"
			+ "162\r\n"
			+ "124\r\n"
			+ "158\r\n"
			+ "38\r\n"
			+ "136\r\n"
			+ "95\r\n"
			+ "161\r\n"
			+ "146\r\n"
			+ "129\r\n"
			+ "154\r\n"
			+ "121\r\n"
			+ "86\r\n"
			+ "118\r\n"
			+ "88\r\n"
			+ "50\r\n"
			+ "48\r\n"
			+ "62\r\n"
			+ "155\r\n"
			+ "28\r\n"
			+ "120\r\n"
			+ "78\r\n"
			+ "60\r\n"
			+ "147\r\n"
			+ "87\r\n"
			+ "27\r\n"
			+ "7\r\n"
			+ "54\r\n"
			+ "39\r\n"
			+ "113\r\n"
			+ "5\r\n"
			+ "74\r\n"
			+ "169\r\n"
			+ "6\r\n"
			+ "43\r\n"
			+ "8\r\n"
			+ "29\r\n"
			+ "18\r\n"
			+ "68\r\n"
			+ "32\r\n"
			+ "19\r\n"
			+ "133\r\n"
			+ "22\r\n"
			+ "94\r\n"
			+ "47\r\n"
			+ "132\r\n"
			+ "59\r\n"
			+ "83\r\n"
			+ "12\r\n"
			+ "13\r\n"
			+ "96\r\n"
			+ "35";
	
	@Override
	public String part1() {
		ArrayList<Integer> nums = new ArrayList<Integer>(Arrays.stream(input.split("\r\n")).map(x -> Integer.parseInt(x)).toList());
		nums.add(0);
		nums.add(nums.stream().mapToInt(x -> x).max().getAsInt() + 3);
		Collections.sort(nums);
		int ones = 0;
		int threes = 0;
		for(int i = 0; i < nums.size() - 1; i++) {
			if(nums.get(i+1) - nums.get(i) == 1)
				ones++;
			if(nums.get(i+1) - nums.get(i) == 3)
				threes++;
		}
		return Integer.toString(ones * threes);
	}

	@Override
	public String part2() {
		ArrayList<Integer> nums = new ArrayList<Integer>(Arrays.stream(input.split("\r\n")).map(x -> Integer.parseInt(x)).toList());
		nums.add(0);
		nums.add(nums.stream().mapToInt(x -> x).max().getAsInt() + 3);
		Collections.sort(nums);
		HashMap<Integer,Long> count = new HashMap<Integer,Long>();
		//first element has "one" path to it
		count.put(0,1l);
		for(int i : nums) {
			//skip - zero already in
			if(i == 0)
				continue;
			//number of potential paths to i is sum of all potential paths from i, i-2, and i-3
			count.put(i, count.getOrDefault(i-1,0l) + count.getOrDefault(i-2, 0l) + count.getOrDefault(i-3, 0l));
		}
		return count.get(nums.stream().max(Integer::compare).get()).toString();
	}

	public static void main(String[] args) {
		DayRunner.run(new Day10());
	}

}
