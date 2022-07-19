package advent.aoc2016;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;

public class Day21 implements IDay {

	String input = "rotate based on position of letter a\r\n"
			+ "swap letter g with letter d\r\n"
			+ "move position 1 to position 5\r\n"
			+ "reverse positions 6 through 7\r\n"
			+ "move position 5 to position 4\r\n"
			+ "rotate based on position of letter b\r\n"
			+ "reverse positions 6 through 7\r\n"
			+ "swap letter h with letter f\r\n"
			+ "swap letter e with letter c\r\n"
			+ "reverse positions 0 through 7\r\n"
			+ "swap position 6 with position 4\r\n"
			+ "rotate based on position of letter e\r\n"
			+ "move position 2 to position 7\r\n"
			+ "swap position 6 with position 4\r\n"
			+ "rotate based on position of letter e\r\n"
			+ "reverse positions 2 through 3\r\n"
			+ "rotate right 2 steps\r\n"
			+ "swap position 7 with position 1\r\n"
			+ "move position 1 to position 2\r\n"
			+ "move position 4 to position 7\r\n"
			+ "move position 5 to position 0\r\n"
			+ "swap letter e with letter f\r\n"
			+ "move position 4 to position 7\r\n"
			+ "reverse positions 1 through 7\r\n"
			+ "rotate based on position of letter g\r\n"
			+ "move position 7 to position 4\r\n"
			+ "rotate right 6 steps\r\n"
			+ "rotate based on position of letter g\r\n"
			+ "reverse positions 0 through 5\r\n"
			+ "reverse positions 0 through 7\r\n"
			+ "swap letter c with letter f\r\n"
			+ "swap letter h with letter f\r\n"
			+ "rotate right 7 steps\r\n"
			+ "rotate based on position of letter g\r\n"
			+ "rotate based on position of letter c\r\n"
			+ "swap position 1 with position 4\r\n"
			+ "move position 7 to position 3\r\n"
			+ "reverse positions 2 through 6\r\n"
			+ "move position 7 to position 0\r\n"
			+ "move position 7 to position 1\r\n"
			+ "move position 6 to position 7\r\n"
			+ "rotate right 5 steps\r\n"
			+ "reverse positions 0 through 6\r\n"
			+ "move position 1 to position 4\r\n"
			+ "rotate left 3 steps\r\n"
			+ "swap letter d with letter c\r\n"
			+ "move position 4 to position 5\r\n"
			+ "rotate based on position of letter f\r\n"
			+ "rotate right 1 step\r\n"
			+ "move position 7 to position 6\r\n"
			+ "swap position 6 with position 0\r\n"
			+ "move position 6 to position 2\r\n"
			+ "rotate right 1 step\r\n"
			+ "swap position 1 with position 6\r\n"
			+ "move position 2 to position 6\r\n"
			+ "swap position 2 with position 1\r\n"
			+ "reverse positions 1 through 7\r\n"
			+ "move position 4 to position 1\r\n"
			+ "move position 7 to position 0\r\n"
			+ "swap position 6 with position 7\r\n"
			+ "rotate left 1 step\r\n"
			+ "reverse positions 0 through 4\r\n"
			+ "rotate based on position of letter c\r\n"
			+ "rotate based on position of letter b\r\n"
			+ "move position 2 to position 1\r\n"
			+ "rotate right 0 steps\r\n"
			+ "swap letter b with letter d\r\n"
			+ "swap letter f with letter c\r\n"
			+ "swap letter d with letter a\r\n"
			+ "swap position 7 with position 6\r\n"
			+ "rotate right 0 steps\r\n"
			+ "swap position 0 with position 3\r\n"
			+ "swap position 2 with position 5\r\n"
			+ "swap letter h with letter f\r\n"
			+ "reverse positions 2 through 3\r\n"
			+ "rotate based on position of letter c\r\n"
			+ "rotate left 2 steps\r\n"
			+ "move position 0 to position 5\r\n"
			+ "swap position 2 with position 3\r\n"
			+ "rotate right 1 step\r\n"
			+ "rotate left 2 steps\r\n"
			+ "move position 0 to position 4\r\n"
			+ "rotate based on position of letter c\r\n"
			+ "rotate based on position of letter g\r\n"
			+ "swap position 3 with position 0\r\n"
			+ "rotate right 3 steps\r\n"
			+ "reverse positions 0 through 2\r\n"
			+ "move position 1 to position 2\r\n"
			+ "swap letter e with letter c\r\n"
			+ "rotate right 7 steps\r\n"
			+ "move position 0 to position 7\r\n"
			+ "rotate left 2 steps\r\n"
			+ "reverse positions 0 through 4\r\n"
			+ "swap letter e with letter b\r\n"
			+ "reverse positions 2 through 7\r\n"
			+ "rotate right 5 steps\r\n"
			+ "swap position 2 with position 4\r\n"
			+ "swap letter d with letter g\r\n"
			+ "reverse positions 3 through 4\r\n"
			+ "reverse positions 4 through 5";
	
	@Override
	public String part1() {
		//store string as arraylist of chars (to simplify rotate operations, etc)
		ArrayList<Character> pass = new ArrayList<Character>();
		for(char c : "abcdefgh".toCharArray())
			pass.add(c);
		
		//iterate over instructions
		for(String s : input.split("\r\n")) {
			String[] parts = s.split(" ");
			switch(parts[0]) {
			case "move":
				int startPosition = Integer.parseInt(parts[2]);
				char moved = pass.remove(startPosition);
				pass.add(Integer.parseInt(parts[5]),moved);
				break;
			case "reverse":
				ArrayList<Character> newPass = new ArrayList<Character>();
				int reverseStart = Integer.parseInt(parts[2]);
				int reverseEnd = Integer.parseInt(parts[4]);
				//if reverseStart is greater than zero, append unreversed leading characters to newPass
				if(reverseStart > 0) {
					for(int i = 0; i < reverseStart; i++) {
						newPass.add(pass.get(i));
					}
				}
				//store reverse segment, then perform reverse, then append to newPass
				ArrayList<Character> reverse = new ArrayList<Character>();
				for(int i = reverseStart; i <= reverseEnd; i++) {
					reverse.add(pass.get(i));
				}
				Collections.reverse(reverse);
				newPass.addAll(reverse);
				//if reverseEnd is less than end of list, append unreversed trailing characters
				if(reverseEnd < pass.size() - 1) {
					for(int i = reverseEnd + 1; i < pass.size(); i++) {
						newPass.add(pass.get(i));
					}
				}
				//set pass to newPass
				pass = newPass;
				break;
			case "swap":
				if(parts[1].equals("position")) {
					int index1 = Integer.parseInt(parts[2]);
					int index2 = Integer.parseInt(parts[5]);
					char buffer = pass.get(index2);
					pass.set(index2, pass.get(index1));
					pass.set(index1, buffer);
				} else {
					int index1 = pass.indexOf(parts[2].charAt(0));
					int index2 = pass.indexOf(parts[5].charAt(0));
					char buffer = pass.get(index2);
					pass.set(index2, pass.get(index1));
					pass.set(index1, buffer);
				}
				break;
			case "rotate":
				if(parts[1].equals("right")) {
					Collections.rotate(pass, Integer.parseInt(parts[2]));
				} else if (parts[1].equals("left")) {
					Collections.rotate(pass, Integer.parseInt(parts[2]) * -1);
				} else {
					int rotationCount = pass.indexOf(parts[6].charAt(0));
					if(rotationCount >= 4)
						rotationCount++;
					rotationCount++;
					Collections.rotate(pass, rotationCount);
				}
				break;
			}
		}
		
		String finalPass = "";
		for(char c : pass)
			finalPass += c;
		return finalPass;
	}

	@Override
	public String part2() {
		//reversing every operation is boring, so we'll just forward-execute our scramble operations
		//for every possible permutation till we find the target
		ArrayList<int[]> perms = perms(new int[] {0,1,2,3,4,5,6,7},8);
		//chances are, password would be near end of permutations 
		//(to try and disincentivize exactly this brute-forcing strategy)
		Collections.reverse(perms);
		final char[] letters = new char[] {'a','b','c','d','e','f','g','h'};
		for(int[] perm : perms) {
			ArrayList<Character> pass = new ArrayList<Character>();
			for(int i : perm)
				pass.add(letters[i]);
			
			//iterate over instructions
			for(String s : input.split("\r\n")) {
				String[] parts = s.split(" ");
				switch(parts[0]) {
				case "move":
					int startPosition = Integer.parseInt(parts[2]);
					char moved = pass.remove(startPosition);
					pass.add(Integer.parseInt(parts[5]),moved);
					break;
				case "reverse":
					ArrayList<Character> newPass = new ArrayList<Character>();
					int reverseStart = Integer.parseInt(parts[2]);
					int reverseEnd = Integer.parseInt(parts[4]);
					//if reverseStart is greater than zero, append unreversed leading characters to newPass
					if(reverseStart > 0) {
						for(int i = 0; i < reverseStart; i++) {
							newPass.add(pass.get(i));
						}
					}
					//store reverse segment, then perform reverse, then append to newPass
					ArrayList<Character> reverse = new ArrayList<Character>();
					for(int i = reverseStart; i <= reverseEnd; i++) {
						reverse.add(pass.get(i));
					}
					Collections.reverse(reverse);
					newPass.addAll(reverse);
					//if reverseEnd is less than end of list, append unreversed trailing characters
					if(reverseEnd < pass.size() - 1) {
						for(int i = reverseEnd + 1; i < pass.size(); i++) {
							newPass.add(pass.get(i));
						}
					}
					//set pass to newPass
					pass = newPass;
					break;
				case "swap":
					if(parts[1].equals("position")) {
						int index1 = Integer.parseInt(parts[2]);
						int index2 = Integer.parseInt(parts[5]);
						char buffer = pass.get(index2);
						pass.set(index2, pass.get(index1));
						pass.set(index1, buffer);
					} else {
						int index1 = pass.indexOf(parts[2].charAt(0));
						int index2 = pass.indexOf(parts[5].charAt(0));
						char buffer = pass.get(index2);
						pass.set(index2, pass.get(index1));
						pass.set(index1, buffer);
					}
					break;
				case "rotate":
					if(parts[1].equals("right")) {
						Collections.rotate(pass, Integer.parseInt(parts[2]));
					} else if (parts[1].equals("left")) {
						Collections.rotate(pass, Integer.parseInt(parts[2]) * -1);
					} else {
						int rotationCount = pass.indexOf(parts[6].charAt(0));
						if(rotationCount >= 4)
							rotationCount++;
						rotationCount++;
						Collections.rotate(pass, rotationCount);
					}
					break;
				}
			}
			
			String finalPass = "";
			for(char c : pass)
				finalPass += c;
			
			//if we've discovered the password we were looking to unscramble
			if(finalPass.equals("fbgdceah")) {
				//reconstruct initial password from permutation
				String initial = "";
				for(int i : perm)
					initial += letters[i];
				//return initial password
				return initial;
			}
			
		}
		return null;
	}

	//Heap's permutation generation algorithm. look it up on wikipedia if you need to
	public static ArrayList<int[]> perms(int[] a, int n) {
		ArrayList<int[]> perms = new ArrayList<int[]>();
		if(n == 1) {
			perms.add(Arrays.copyOf(a, a.length));
			return perms;
		}
		
		perms.addAll(perms(a,n-1));
		for(int i = 0; i < n-1; i++) {
			if(n % 2 == 0) {
				int temp = a[i];
				a[i] = a[n-1];
				a[n-1] = temp;
			} else {
				int temp = a[0];
				a[0] = a[n-1];
				a[n-1] = temp;
			}
			perms.addAll(perms(a,n-1));
		}
		return perms;
	}
	
	public static void main(String[] args) {
		DayRunner.run(new Day21());
	}

}
