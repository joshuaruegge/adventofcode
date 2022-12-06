package advent.aoc2021;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

public class Day08 implements IDay {

	static String input;
	
	@Override
	public String part1() {
		int unique = 0;
		for(String s : input.split("\n")) {
			for(String t : s.split(" \\| ")[1].split(" ")) {
				if(t.length() == 2 || t.length() == 3 || t.length() == 4 || t.length() == 7) {
					unique++;
				}
			}
		}
	
		return Integer.toString(unique);
	}

	@Override
	public String part2() {
		int total = 0;
		
		for(String line : input.split("\n")) {
			String[] parts = line.split(" \\| ");
			//keep hashmap of arrangement for each number
			HashMap<Integer,HashSet<Character>> poss = new HashMap<Integer,HashSet<Character>>();
			
			ArrayList<String> testCases = new ArrayList<String>(Arrays.asList(parts[0].split(" ")));
			
			//first, find one, four, seven, and eight - easy
			poss.put(1,setFromString(findOfLength(testCases,2).get(0)));
			poss.put(4, setFromString(findOfLength(testCases,4).get(0)));
			poss.put(7, setFromString(findOfLength(testCases,3).get(0)));
			poss.put(8, setFromString(findOfLength(testCases,7).get(0)));
			
			//find three - three is only size 5 that contains all of 1
			for(String s : findOfLength(testCases,5)) {
				HashSet<Character> test = setFromString(s);
				if(test.containsAll(poss.get(1))) {
					poss.put(3, test);
					break;
				}
			}
			
			//find nine - only 6 that contains all of 4
			for(String s : findOfLength(testCases,6)) {
				HashSet<Character> test = setFromString(s);
				if(test.containsAll(poss.get(4))) {
					poss.put(9, test);
					break;
				}
			}
			
			//find zero - only 6 that contains all of 1 and is not 9
			//can also find six - only 6 that does not contain 1
			for(String s : findOfLength(testCases,6)) {
				HashSet<Character> test = setFromString(s);
				if(test.equals(poss.get(9)))
					continue;
				if(test.containsAll(poss.get(1))) {
					poss.put(0, test);
				} else {
					poss.put(6, test);
				}
			}
			
			//similarly, five is only 5 that *six contains all of*
			//and two is only 5 that is not three and that six does not contain all of
			for(String s : findOfLength(testCases,5)) {
				HashSet<Character> test = setFromString(s);
				if(test.equals(poss.get(3)))
					continue;
				if(poss.get(6).containsAll(test)) {
					poss.put(5, test);
				} else {
					poss.put(2, test);
				}
			}
			
			//string is the simplest way for left-to-right digit appending
			String num = "";
			for(String s : parts[1].split(" ")) {
				//unfortunately, must iterate over entire map
				HashSet<Character> a = setFromString(s);
				for(int i : poss.keySet()) {
					if(poss.get(i).equals(a)) {
						num += i;
						break;
					}
				}
			}
			
			total += Integer.parseInt(num);
		}
		
		return Integer.toString(total);
	}
		
	public List<String> findOfLength(ArrayList<String> a, int length) {
		return a.stream().filter(x -> x.length() == length).collect(Collectors.toList());
	}
	
	
	//primitive type conversion is a bitch
	public HashSet<Character> setFromString(String s) {
		HashSet<Character> a = new HashSet<Character>();
		for(char c : s.toCharArray())
			a.add(c);
		return a;
	}

	public static void main(String[] args) {
		input = Input.fetchInput(2021,8);
		DayRunner.run(new Day08());
	}
}
