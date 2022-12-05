package advent.aoc2021;

import java.util.Collections;
import java.util.HashMap;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;

public class Day14 implements IDay {

	String input = "HBCHSNFFVOBNOFHFOBNO\r\n"
			+ "\r\n"
			+ "HF -> O\r\n"
			+ "KF -> F\r\n"
			+ "NK -> F\r\n"
			+ "BN -> O\r\n"
			+ "OH -> H\r\n"
			+ "VC -> F\r\n"
			+ "PK -> B\r\n"
			+ "SO -> B\r\n"
			+ "PP -> H\r\n"
			+ "KO -> F\r\n"
			+ "VN -> S\r\n"
			+ "OS -> B\r\n"
			+ "NP -> C\r\n"
			+ "OV -> C\r\n"
			+ "CS -> P\r\n"
			+ "BH -> P\r\n"
			+ "SS -> P\r\n"
			+ "BB -> H\r\n"
			+ "PH -> V\r\n"
			+ "HN -> F\r\n"
			+ "KV -> H\r\n"
			+ "HC -> B\r\n"
			+ "BC -> P\r\n"
			+ "CK -> P\r\n"
			+ "PS -> O\r\n"
			+ "SH -> N\r\n"
			+ "FH -> N\r\n"
			+ "NN -> P\r\n"
			+ "HS -> O\r\n"
			+ "CB -> F\r\n"
			+ "HH -> F\r\n"
			+ "SB -> P\r\n"
			+ "NB -> F\r\n"
			+ "BO -> V\r\n"
			+ "PN -> H\r\n"
			+ "VP -> B\r\n"
			+ "SC -> C\r\n"
			+ "HB -> H\r\n"
			+ "FP -> O\r\n"
			+ "FC -> H\r\n"
			+ "KP -> B\r\n"
			+ "FB -> B\r\n"
			+ "VK -> F\r\n"
			+ "CV -> P\r\n"
			+ "VF -> V\r\n"
			+ "SP -> K\r\n"
			+ "CC -> K\r\n"
			+ "HV -> P\r\n"
			+ "NC -> N\r\n"
			+ "VH -> K\r\n"
			+ "PF -> P\r\n"
			+ "PB -> S\r\n"
			+ "BF -> K\r\n"
			+ "FF -> C\r\n"
			+ "FV -> V\r\n"
			+ "KS -> H\r\n"
			+ "VB -> F\r\n"
			+ "SV -> F\r\n"
			+ "HO -> B\r\n"
			+ "FN -> C\r\n"
			+ "SN -> F\r\n"
			+ "OB -> N\r\n"
			+ "KN -> P\r\n"
			+ "BV -> H\r\n"
			+ "ON -> N\r\n"
			+ "NF -> S\r\n"
			+ "OF -> P\r\n"
			+ "NV -> S\r\n"
			+ "VS -> C\r\n"
			+ "OO -> C\r\n"
			+ "BP -> H\r\n"
			+ "BK -> N\r\n"
			+ "CP -> N\r\n"
			+ "PC -> K\r\n"
			+ "CN -> H\r\n"
			+ "KB -> B\r\n"
			+ "BS -> P\r\n"
			+ "KK -> P\r\n"
			+ "SF -> V\r\n"
			+ "CO -> V\r\n"
			+ "CH -> P\r\n"
			+ "FO -> B\r\n"
			+ "FS -> F\r\n"
			+ "VO -> H\r\n"
			+ "NS -> F\r\n"
			+ "KC -> H\r\n"
			+ "VV -> K\r\n"
			+ "NO -> P\r\n"
			+ "OK -> F\r\n"
			+ "PO -> V\r\n"
			+ "FK -> H\r\n"
			+ "OP -> H\r\n"
			+ "PV -> N\r\n"
			+ "CF -> P\r\n"
			+ "NH -> K\r\n"
			+ "SK -> O\r\n"
			+ "KH -> P\r\n"
			+ "HP -> V\r\n"
			+ "OC -> V\r\n"
			+ "HK -> F";
	
	@Override
	public String part1() {
		HashMap<String,String> insert = new HashMap<String,String>();
		String polymer = input.split("\r\n")[0];
		for(String s : input.split("\r\n\r\n")[1].split("\r\n")) {
			String[] parts = s.split(" -> ");
			insert.put(parts[0], parts[1]);
		}
		for(int iter = 0; iter < 10; iter++) {
			StringBuilder newPol = new StringBuilder();
			for(int i = 0; i < polymer.length() - 1; i++) {
				char one = polymer.charAt(i);
				char two = polymer.charAt(i+1);
				newPol.append(one + insert.getOrDefault(one + "" + two, ""));
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
		
		for(String s : input.split("\r\n\r\n")[1].split("\r\n")) {
			String[] parts = s.split(" -> ");
			insert.put(parts[0], parts[1]);
		}
		//track pairs present rather than whole polymer
		HashMap<String,Long> pairs = new HashMap<String,Long>();
		String polymer = input.split("\r\n")[0];
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
		DayRunner.run(new Day14());
	}

}
