package advent.aoc2017;

import java.util.Arrays;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

public class Day04 implements IDay {

	static String input;
	
	@Override
	public String part1() {
		int validPasswords = 0;
		nextLine:
		for(String s : input.split("\n")) {
			String[] parts = s.split(" ");
			//for each phrase, check if if appears in later indices
			for(int i = 0; i < parts.length; i++) {
				String phrase = parts[i];
				for(int j = i+1; j < parts.length; j++) {
					//if phrase appears again, skip to next line
					if(phrase.equals(parts[j]))
						continue nextLine;
				}
			}
			//if we made it all the way here without skipping, then passphrase is valid - increment counter
			validPasswords++;
		}
		return Integer.toString(validPasswords);
	}

	@Override
	public String part2() {
		int validPasswords = 0;
		nextLine:
		for(String s : input.split("\n")) {
			//split into phrases
			String[] parts = s.split(" ");
			//for each phrase, check if if appears in later indices
			for(int i = 0; i < parts.length; i++) {
				String phrase = parts[i];
				for(int j = i+1; j < parts.length; j++) {
					//part 2 modification - check for anagrams
					//convert each string to a char array, sort alphabetically, and check if the arrays are equal
					String phrase2 = parts[j];
					char[] letters = phrase.toCharArray();
					char[] letters2 = phrase2.toCharArray();
					Arrays.sort(letters);
					Arrays.sort(letters2);
					if(Arrays.equals(letters, letters2)) {
						continue nextLine;
					}
				}
			}
			//if we made it all the way here without skipping, then passphrase is valid - increment counter
			validPasswords++;
		}
		return Integer.toString(validPasswords);
	}

	public static void main(String[] args) {
		input = Input.fetchInput(2017,4);
		DayRunner.run(new Day04());
	}

}
