package advent.aoc2016;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

public class Day04 implements IDay {

	static String input;
	
	@Override
	public String part1() {
		int sectorIDSum = 0;
		//iterate over lines
		lines:
		for(String s : input.split("\n")) {
			//split at '[' to extract checksum
			String[] parts = s.split("\\[");
			//trim checksum
			String checksum = parts[1].substring(0, parts[1].length() - 1);
			//pull sector ID
			int sectorID = Integer.parseInt(parts[0].split("-")[parts[0].split("-").length - 1]);
			//now, keep map of character frequencies
			HashMap<Character,Integer> freq = new HashMap<Character,Integer>();
			//iterate over non-checksum part of line
			for(char c : parts[0].toCharArray()) {
				//make sure it's a valid letter
				if(c > 96 && c < 123) {
					freq.put(c, freq.getOrDefault(c, 0) + 1);
				}
 			}
			//now, get copy of key set to sort
			ArrayList<Character> freqSorted = new ArrayList<Character>(freq.keySet());
			//sort by parameters specified in problem description
			Collections.sort(freqSorted, new Comparator<Character>() {

				@Override
				public int compare(Character o1, Character o2) {
					int a = freq.get(o1);
					int b = freq.get(o2);
					//compare frequency count - if equal, instead compare alphabetical order
					//(by interpreting chars as int values)
					if(Integer.compare(a, b) != 0) {
						//frequency is backwards - highest first
						return Integer.compare(b, a);
					} else {
						//alphabetical is normal order
						return Integer.compare(o1, o2);
					}
				}
				
			});
			//check against checksum
			for(int i = 0; i < 5; i++) {
				if(!(freqSorted.get(i) == checksum.charAt(i)))
					continue lines;
			}
			//if all checks passed, increase id total by id
			sectorIDSum += sectorID;
		}
		return Integer.toString(sectorIDSum);
	}

	@Override
	public String part2() {
		//iterate over input
		for(String s : input.split("\n")) {
			//split at '[' to facilitate sector id extraction
			String[] parts = s.split("\\[");
			//pull sector ID
			int sectorID = Integer.parseInt(parts[0].split("-")[parts[0].split("-").length - 1]);
			//now, check over important half of sector name
			char[] cipher = parts[0].toCharArray();
			for(int i = 0; i < cipher.length; i++) {
				//ignore dashes
				if(cipher[i] == '-')
					continue;
				//char - 97 converts from ASCII to traditional alphabet numeric (a = 0, b = 1, etc.)
				//adding sector id gives total shift/resulting alphabetic position
				//modulo 26 reduces this to within the traditional alphabet range
				//finally, + 97 places it back into ASCII lowercase letter range
				cipher[i] = (char) (((cipher[i] - 97 + sectorID) % 26) + 97);
			}
			//convert to string for quick contains();
			String plaintext = new String(cipher);
			if(plaintext.contains("object")) {
				return Integer.toString(sectorID);
			}
		}
		
		return null;
	}

	public static void main(String[] args) {
		input = Input.fetchInput(2016,4);
		DayRunner.run(new Day04());
	}

}
