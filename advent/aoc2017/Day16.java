package advent.aoc2017;

import java.util.ArrayList;
import java.util.Collections;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

public class Day16 implements IDay {

	static String input;
	
	@Override
	public String part1() {
		ArrayList<Character> perm = new ArrayList<Character>();
		for(int i = 97; i < 113; i++)
			perm.add((char) i);
		
		for(String s : input.split(",")) {
			switch(s.charAt(0)) {
			case 's':
				int spinLength = Integer.parseInt(s.substring(1)); 
				Collections.rotate(perm, spinLength);
				break;
			case 'x':
				String[] nums = s.substring(1).split("/");
				int i1 = Integer.parseInt(nums[0]);
				int i2 = Integer.parseInt(nums[1]);
				char temp = perm.get(i1);
				perm.set(i1, perm.get(i2));
				perm.set(i2, temp);
				break;
			case 'p':
				char c1 = s.charAt(1);
				char c2 = s.charAt(3);
				int ic1 = perm.indexOf(c1);
				int ic2 = perm.indexOf(c2);
				char temp2 = perm.get(ic1);
				perm.set(ic1, perm.get(ic2));
				perm.set(ic2, temp2);
				break;
			}
		}
		
		String result = "";
		for(char c : perm)
			result += c;
		return result;
	}

	@Override
	public String part2() {
		ArrayList<Character> perm = new ArrayList<Character>();
		for(int i = 97; i < 113; i++)
			perm.add((char) i);
		ArrayList<Character> original = new ArrayList<Character>(perm);
		//first, we find the "length" of a cycle till original pops up again
		int cycleLength = 0;
		do {
			for(String s : input.split(",")) {
				switch(s.charAt(0)) {
				case 's':
					int spinLength = Integer.parseInt(s.substring(1)); 
					Collections.rotate(perm, spinLength);
					break;
				case 'x':
					String[] nums = s.substring(1).split("/");
					int i1 = Integer.parseInt(nums[0]);
					int i2 = Integer.parseInt(nums[1]);
					char temp = perm.get(i1);
					perm.set(i1, perm.get(i2));
					perm.set(i2, temp);
					break;
				case 'p':
					char c1 = s.charAt(1);
					char c2 = s.charAt(3);
					int ic1 = perm.indexOf(c1);
					int ic2 = perm.indexOf(c2);
					char temp2 = perm.get(ic1);
					perm.set(ic1, perm.get(ic2));
					perm.set(ic2, temp2);
					break;
				}
			}
			cycleLength++;
		} while(!perm.equals(original));
		
		//then, determine remainder of cycles needed, keeping in mind that one cycle length returns to base case
		int remainder = 1000000000 % cycleLength;
		
		//now, just do remainder number of cycles
		for(int i = 0; i < remainder; i++) {
			for(String s : input.split(",")) {
				switch(s.charAt(0)) {
				case 's':
					int spinLength = Integer.parseInt(s.substring(1)); 
					Collections.rotate(perm, spinLength);
					break;
				case 'x':
					String[] nums = s.substring(1).split("/");
					int i1 = Integer.parseInt(nums[0]);
					int i2 = Integer.parseInt(nums[1]);
					char temp = perm.get(i1);
					perm.set(i1, perm.get(i2));
					perm.set(i2, temp);
					break;
				case 'p':
					char c1 = s.charAt(1);
					char c2 = s.charAt(3);
					int ic1 = perm.indexOf(c1);
					int ic2 = perm.indexOf(c2);
					char temp2 = perm.get(ic1);
					perm.set(ic1, perm.get(ic2));
					perm.set(ic2, temp2);
					break;
				}
			}
		}
		
		String result = "";
		for(char c : perm) {
			result += c;
		}
		return result;
	}

	public static void main(String[] args) {
		input = Input.fetchInput(2017,16).trim();
		DayRunner.run(new Day16());
	}

}
