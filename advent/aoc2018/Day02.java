package advent.aoc2018;

import java.util.HashMap;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

public class Day02 implements IDay {

	static String input;
	
	@Override
	public String part1() {
		int twoCount = 0;
		int threeCount = 0;
		for(String s : input.split("\n")) {
			//character frequency map
			HashMap<Character,Integer> freq = new HashMap<Character,Integer>();
			for(char c : s.toCharArray()) {
				freq.put(c, freq.getOrDefault(c, 0) + 1);
			}
			if(freq.values().contains(2))
				twoCount++;
			if(freq.values().contains(3))
				threeCount++;
		}
		return Integer.toString(twoCount * threeCount);
	}

	@Override
	public String part2() {
		String[] ids = input.split("\n");
		for(String s : ids) {
			for(String t : ids) {
				if(s.equals(t))
					continue;
				//tally different characters - break if more than 1
				int charDifference = 0;
				for(int i = 0; i < s.length(); i++) {
					if(s.charAt(i) != t.charAt(i))
						charDifference++;
					if(charDifference > 1)
						break;
				}
				if(charDifference == 1) {
					//create solution string from common characters
					String sol = "";
					for(int i = 0; i < s.length(); i++) {
						if(s.charAt(i) == t.charAt(i))
							sol += s.charAt(i);
					}
					return sol;
				}
			}
		}
		return null;
	}

	public static void main(String[] args) {
		input = Input.fetchInput(2018,2);
		DayRunner.run(new Day02());
	}

}
