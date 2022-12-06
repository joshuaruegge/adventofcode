package advent.aoc2020;

import java.util.HashSet;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

public class Day06 implements IDay {

	static String input;
	
	@Override
	public String part1() {
		int count = 0;
		for(String s : input.split("\n\n")) {
			HashSet<Character> chars = new HashSet<Character>();
			for(char c : s.replace("\n", "").toCharArray())
				chars.add(c);
			count += chars.size();
		}
		return Integer.toString(count);
	}

	@Override
	public String part2() {
		int count = 0;
		HashSet<Character> allLowercase = new HashSet<Character>();
		for(char c = 'a'; c < 'a' + 26; c++) {
			allLowercase.add(c);
		}
		for(String s : input.split("\n\n")) {
			//each time, start from all possible lowercase letters, reduce by line
			HashSet<Character> commons = new HashSet<Character>(allLowercase);
			for(String t : s.split("\n")) {
				HashSet<Character> line = new HashSet<Character>();
				for(char c : t.toCharArray())
					line.add(c);
				commons.retainAll(line);
			}
			count += commons.size();
		}
		return Integer.toString(count);
	}

	public static void main(String[] args) {
		input = Input.fetchInput(2020,6);
		DayRunner.run(new Day06());
	}
}
