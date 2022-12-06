package advent.aoc2016;

import java.util.ArrayList;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

public class Day07 implements IDay {

	static String input;
	
	@Override
	public String part1() {
		int valid = 0;
		lineLoop:
		for(String s : input.split("\n")) {
			String[] parts = s.split("\\[|\\]");
			//by splitting along brackets, odd-numbered indexes will be within brackets, even-numbered outside
			//first, check odd numbered
			for(int i = 1; i < parts.length; i+=2) {
				String t = parts[i];
				for(int j = 0; j < t.length() - 3; j++) {
					if(t.charAt(j) == t.charAt(j+3) && t.charAt(j+1) == t.charAt(j+2) && !(t.charAt(j) == t.charAt(j+1))) {
						//invalid IP, skip
						continue lineLoop;
					}
				}
			}
			//now, check even numbered
			boolean abba = false;
			for(int i = 0; i < parts.length; i+= 2) {
				String t = parts[i];
				for(int j = 0; j < t.length() - 3; j++) {
					if(t.charAt(j) == t.charAt(j+3) && t.charAt(j+1) == t.charAt(j+2)  && !(t.charAt(j) == t.charAt(j+1))) {
						abba = true;
						break;
					}
				}
				if(abba)
					break;
			}
			
			//if we made it all the way here, and abba is true, ip is valid
			if(abba)
				valid++;
					
		}
		return Integer.toString(valid);
	}

	@Override
	public String part2() {
		int valid = 0;
		for(String s : input.split("\n")) {
			String[] parts = s.split("\\[|\\]");
			//first, iterate over outside-bracket (even numbers) to build potential bab list
			ArrayList<String> babs = new ArrayList<String>();
			for(int i = 0; i < parts.length; i+=2) {
				String t = parts[i];
				for(int j = 0; j < t.length() - 2; j++) {
					if(t.charAt(j) == t.charAt(j+2) && t.charAt(j) != t.charAt(j+1)) {
						//put in opposite
						babs.add(t.charAt(j+1) + "" + t.charAt(j) + "" + t.charAt(j+1)); 
					}
				}
			}
			//now, go over odds and search for valid babs
			boolean bab = false;
			for(int i = 1; i < parts.length; i+=2) {
				for(String curbab : babs) {
					if(parts[i].contains(curbab)) {
						bab = true;
						break;
					}
				}
				if(bab)
					break;
			}
			 
			if(bab)
				valid++;	
		}
		return Integer.toString(valid);
	}

	public static void main(String[] args) {
		input = Input.fetchInput(2016,7);
		DayRunner.run(new Day07());
	}

}
