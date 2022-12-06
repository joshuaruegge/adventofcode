package advent.aoc2021;

import java.util.Collections;
import java.util.HashMap;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

public class Day14 implements IDay {

	static String input;
	
	@Override
	public String part1() {
		HashMap<String,String> insert = new HashMap<String,String>();
		String polymer = input.split("\n")[0];
		for(String s : input.split("\n\n")[1].split("\n")) {
			String[] parts = s.split(" -> ");
			insert.put(parts[0], parts[1]);
		}
		for(int iter = 0; iter < 10; iter++) {
			StringBuilder newPol = new StringBuilder();
			for(int i = 0; i < polymer.length() - 1; i++) {
				char one = polymer.charAt(i);
				char two = polymer.charAt(i+1);
				newPol.append(one).append(insert.getOrDefault(one + "" + two, ""));
			}
			newPol.append(polymer.charAt(polymer.length() - 1));
			polymer = newPol.toString();
		}
		//character count
		HashMap<Character,Integer> a = new HashMap<Character,Integer>();
		for(char c : polymer.toCharArray()) {
			a.put(c, a.getOrDefault(c,0)+1);
		}
		
		return Integer.toString(Collections.max(a.values()) - Collections.min(a.values()));
	}

	@Override
	public String part2() {
		HashMap<String,String> insert = new HashMap<String,String>();
		
		for(String s : input.split("\n\n")[1].split("\n")) {
			String[] parts = s.split(" -> ");
			insert.put(parts[0], parts[1]);
		}
		//track pairs present rather than whole polymer
		HashMap<String,Long> pairs = new HashMap<String,Long>();
		String polymer = input.split("\n")[0];
		//add initial characters to frequency
		//we have to track character frequency separately because pair count is inaccurate for characters due to overlaps
		HashMap<Character,Long> characterFrequency = new HashMap<Character,Long>();
		for(int i = 0; i < polymer.length() - 1; i++) {
			characterFrequency.put(polymer.charAt(i), characterFrequency.getOrDefault(polymer.charAt(i),0l) + 1);
			String pair = polymer.charAt(i) + "" + polymer.charAt(i+1);
			pairs.put(pair, pairs.getOrDefault(pair,0l) + 1);
		}
		characterFrequency.put(polymer.charAt(polymer.length() - 1), characterFrequency.getOrDefault(polymer.charAt(polymer.length() - 1), 0l) + 1);
		
		for(int iter = 0; iter < 40; iter++) {
			HashMap<String,Long> newPairs = new HashMap<String,Long>();
			for(String s : pairs.keySet()) {
				if(insert.containsKey(s)) {
					long mult = pairs.get(s);
					//only time character count changes is when new character is added
					char insertChar = insert.get(s).charAt(0);
					characterFrequency.put(insertChar, characterFrequency.getOrDefault(insertChar, 0l) + mult);
					String first = s.charAt(0) + insert.get(s);
					newPairs.put(first, newPairs.getOrDefault(first,0l) + mult);
					String second = insert.get(s) + s.charAt(1);
					newPairs.put(second, newPairs.getOrDefault(second, 0l) + mult);
				} else {
					newPairs.put(s, pairs.get(s));
				}
			}
			pairs = newPairs;
		}
		
		return Long.toString(Collections.max(characterFrequency.values()) - Collections.min(characterFrequency.values()));
	}

	public static void main(String[] args) {
		input = Input.fetchInput(2021,14);
		DayRunner.run(new Day14());
	}
}
