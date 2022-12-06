package advent.aoc2016;

import java.util.ArrayList;
import java.util.HashMap;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

public class Day06 implements IDay {

	static String input;
	
	@Override
	public String part1() {
		int wordLength = input.split("\n")[0].length();
		//keep array of hashmaps - one for each character position
		ArrayList<HashMap<Character,Integer>> frequencies = new ArrayList<HashMap<Character,Integer>>();
		for(int i = 0; i < wordLength; i++) {
			frequencies.add(new HashMap<Character,Integer>());
		}
		//iterate over lines
		for(String s : input.split("\n")) {
			//tally each character
			for(int i = 0; i < s.length(); i++) {
				frequencies.get(i).put(s.charAt(i), frequencies.get(i).getOrDefault(s.charAt(i), 0) + 1);
			}
		}
		//find most common of each position, append to string
		String plaintext = "";
		for(HashMap<Character,Integer> m : frequencies) {
			int bestCount = 0;
			char best = 0;
			for(char c : m.keySet()) {
				if(m.get(c) > bestCount) {
					bestCount = m.get(c);
					best = c;
				}
			}
			//append most common character
			plaintext += best;
		}
		return plaintext;
	}

	@Override
	public String part2() {
		int wordLength = input.split("\n")[0].length();
		//keep array of hashmaps - one for each character position
		ArrayList<HashMap<Character,Integer>> frequencies = new ArrayList<HashMap<Character,Integer>>();
		for(int i = 0; i < wordLength; i++) {
			frequencies.add(new HashMap<Character,Integer>());
		}
		//iterate over lines
		for(String s : input.split("\n")) {
			//tally each character
			for(int i = 0; i < s.length(); i++) {
				frequencies.get(i).put(s.charAt(i), frequencies.get(i).getOrDefault(s.charAt(i), 0) + 1);
			}
		}
		//part 2 - find least common of each position, append to string
		String plaintext = "";
		for(HashMap<Character,Integer> m : frequencies) {
			int bestCount = Integer.MAX_VALUE;
			char best = 0;
			for(char c : m.keySet()) {
				if(m.get(c) < bestCount) {
					bestCount = m.get(c);
					best = c;
				}
			}
			//append least common character
			plaintext += best;
		}
		return plaintext;
	}

	public static void main(String[] args) {
		input = Input.fetchInput(2016,6);
		DayRunner.run(new Day06());
	}

}
