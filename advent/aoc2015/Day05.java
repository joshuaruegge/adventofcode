package advent.aoc2015;

import java.util.ArrayList;
import java.util.Arrays;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

public class Day05 implements IDay {

	static String input;
	
	@Override
	public String part1() {
		//parse strings into list
		ArrayList<String> strings = new ArrayList<String>(Arrays.stream(input.split("\n")).toList());
		
		//iterate over strings, keep track of how many are nice
		int nice = 0;
		for(String s : strings) {
			int vowels = 0;
			boolean repeat = false;
			//skip if contains illegal strings
			if(s.contains("ab") || s.contains("cd") || s.contains("pq") || s.contains("xy")) {
				continue;
			}
			for(int i = 0; i < s.length(); i++) {
				//if not checking last character, check this and one ahead for repeat
				//if repeat already found, no need to recheck
				if(!repeat && i < s.length() - 1) {
					if(s.charAt(i) == s.charAt(i+1))
						repeat = true;
				}
				//vowel check
				char c = s.charAt(i);
				if(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u')
					vowels++;
			}
			if(vowels > 2 && repeat)
				nice++;
		}
		return Integer.toString(nice);
	}

	@Override
	public String part2() {
		//parse strings into list
		ArrayList<String> strings = new ArrayList<String>(Arrays.stream(input.split("\n")).toList());

		//iterate over strings, keep track of how many are nice
		int nice = 0;
		for(String s : strings) {
			boolean repeatTwo = false;
			boolean repeatGap = false;
			//iterate over string for gap repeat
			for(int i = 0; i < s.length() - 2; i++) {
				if(s.charAt(i) == s.charAt(i+2)) {
					repeatGap = true;
					break;
				}
			}
			//iterate for two repeat
			for(int i = 0; i < s.length() - 1; i++) {
				String two = s.charAt(i) + "" + s.charAt(i+1);
				//if string is not present after its current location
				//(i.e. not part of a larger repeat)
				if(s.indexOf(two,i+2) != -1) {
					repeatTwo = true;
					break;
				}
			}
			if(repeatGap && repeatTwo)
				nice++;
		}
		return Integer.toString(nice);
	}

	public static void main(String[] args) {
		input = Input.fetchInput(2015,5);
		DayRunner.run(new Day05());
	}

}
