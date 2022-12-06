package advent.aoc2022;

import java.util.HashSet;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

public class Day03 implements IDay {

	static String input;

	@Override
	public String part1() {
		int total = 0;
		for(String s : input.split("\n")) {
			HashSet<Character> firstHash = stringToHashSet(s.substring(0,s.length() / 2));
			firstHash.retainAll(stringToHashSet(s.substring(s.length()/2)));
			
			char c = firstHash.stream().findFirst().get();
			
			if(Character.isUpperCase(c)) {
				total += c - 38;
			} else {
				total += c - 96;
			}
		}
		return Integer.toString(total);
	}

	@Override
	public String part2() {
		int total = 0;
		String[] split = input.split("\n");
		for(int i = 0; i < split.length; i+=3) {
			HashSet<Character> common = stringToHashSet(split[i]);
			common.retainAll(stringToHashSet(split[i+1]));
			common.retainAll(stringToHashSet(split[i+2]));
			char c = common.stream().findFirst().get();
			
			if(Character.isUpperCase(c)) {
				total += c - 38;
			} else {
				total += c - 96;
			}
		}
		return Integer.toString(total);
	}
	
	public HashSet<Character> stringToHashSet(String s) {
		HashSet<Character> a = new HashSet<Character>();
		for(char c : s.toCharArray())
			a.add(c);
		return a;
	}
	public static void main(String[] args) {
		input = Input.fetchInput(2022,3);
		DayRunner.run(new Day03());
	}

}
