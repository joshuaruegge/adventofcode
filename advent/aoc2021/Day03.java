package advent.aoc2021;

import java.util.ArrayList;
import java.util.Arrays;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

public class Day03 implements IDay {

	static String input;
	
	@Override
	public String part1() {
		String[] nums = input.split("\n");
		//keep int[] of 1 frequencies for position
		int[] freq = new int[nums[0].length()];
		for(String s : nums) 
			for(int i = 0; i < s.length(); i++) 
				if(s.charAt(i) == '1')
					freq[i]++;
		
		int gamma = 0;
		int epsilon = 0;
		for(int i = 0; i < freq.length; i++) {
			if(freq[i] > nums.length / 2) {
				//flip bit at i by adding 2^i to number
				//however, keep in mind we are in reversed order, so do size - i - 1 for bit
				gamma += 1 << (freq.length - i - 1);
			} else {
				//flip epsilon bit
				epsilon += 1 << (freq.length - i - 1);
			}
		}
		
		return Integer.toString(gamma * epsilon);
	}

	@Override
	public String part2() {
		String[] nums = input.split("\n");
		
		int bits = nums[0].length();
		
		ArrayList<String> oxyPoss = new ArrayList<String>(Arrays.asList(nums));
		ArrayList<String> co2Poss = new ArrayList<String>(oxyPoss);
		
		for(int bit = 0; bit < bits; bit++) {
			
			if(oxyPoss.size() > 1) {
				int sum = 0;
				for(String s : oxyPoss)
					if(s.charAt(bit) == '1')
						sum++;
				
				char common = (sum >= (oxyPoss.size()/2.0) ? '1' : '0');
				
				for(int i = 0; i < oxyPoss.size(); i++) {
					if(oxyPoss.get(i).charAt(bit) != common) {
						oxyPoss.remove(i);
						i--;
					}
				}
			}
			
			if(co2Poss.size() > 1) {
				int sum = 0;
				for(String s : co2Poss)
					if(s.charAt(bit) == '1')
						sum++;
				char lessCommon = (sum >= (co2Poss.size()/2.0) ? '0' : '1');
				for(int i = 0; i < co2Poss.size(); i++) {
					if(co2Poss.get(i).charAt(bit) != lessCommon) {
						co2Poss.remove(i);
						i--;
					}
				}
			}
		}
		
		return Integer.toString(Integer.parseInt(oxyPoss.get(0),2) * Integer.parseInt(co2Poss.get(0),2));
	}

	public static void main(String[] args) {
		input = Input.fetchInput(2021,3);
		DayRunner.run(new Day03());
	}

}
