package advent.aoc2016;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

public class Day21 implements IDay {

	static String input;
	
	@Override
	public String part1() {
		//store string as arraylist of chars (to simplify rotate operations, etc)
		ArrayList<Character> pass = new ArrayList<Character>();
		for(char c : "abcdefgh".toCharArray())
			pass.add(c);
		
		//iterate over instructions
		for(String s : input.split("\n")) {
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
			for(String s : input.split("\n")) {
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
		input = Input.fetchInput(2016,21);
		DayRunner.run(new Day21());
	}

}
