package advent.aoc2020;

import java.util.HashMap;
import java.util.HashSet;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

public class Day07 implements IDay {

	static String input;
	
	@Override
	public String part1() {
		HashMap<String,HashSet<String>> bags = new HashMap<String,HashSet<String>>();
		for(String line : input.split("\n")) {
			String[] chunks = line.split(" contain ");
			String topBagColor = chunks[0].split(" ")[0] + chunks[0].split(" ")[1];
			HashSet<String> containedBags = new HashSet<String>();
			String[] containWords = chunks[1].split(" ");
			if(containWords[0].equals("no")) {
				bags.put(topBagColor, containedBags);
				continue;
			}
			for(int i = 1; i < containWords.length; i += 4) {
				containedBags.add(containWords[i] + containWords[i+1]);
			}
			bags.put(topBagColor,containedBags);
		}
		
		String desiredBag = "shinygold";
		
		int count = 0;
		for(String s : bags.keySet()) {
			if(containsTree(desiredBag,bags.get(s),bags)) {
				count++;
			}
		}
		return Integer.toString(count);
	}

	@Override
	public String part2() {
		//now, bags must be string -> hashmap<string,integer> map
		HashMap<String, HashMap<String,Integer>> bags = new HashMap<String, HashMap<String,Integer>>();
		for(String line : input.split("\n")) {
			String[] chunks = line.split(" contain ");
			String topBagColor = chunks[0].split(" ")[0] + chunks[0].split(" ")[1];
			HashMap<String,Integer> containedBags = new HashMap<String,Integer>();
			String[] containWords = chunks[1].split(" ");
			if(containWords[0].equals("no")) {
				bags.put(topBagColor, containedBags);
				continue;
			}
			for(int i = 1; i < containWords.length; i += 4) {
				containedBags.put(containWords[i] + containWords[i+1], Integer.parseInt(containWords[i-1]));
			}
			bags.put(topBagColor,containedBags);
		}
		//off-by-one because root gold bag is counted
		return Integer.toString(bagsWithin("shinygold",bags) - 1);
	}

	public boolean containsTree(String type, HashSet<String> container, HashMap<String,HashSet<String>> a) {
		if(container.contains(type))
			return true;
		for(String key : container) {
			if(containsTree(type,a.get(key),a))
				return true;
		}
		return false;
	}

	public int bagsWithin(String bag, HashMap<String,HashMap<String,Integer>> a) {
		int bags = 1;
		for(String s : a.get(bag).keySet()) {
			//for each child, bag total is count * number of total recursive bags
			bags += a.get(bag).get(s) * bagsWithin(s,a);
		}
		return bags;
	}

	public static void main(String[] args) {
		input = Input.fetchInput(2020,7);
		DayRunner.run(new Day07());
	}
}
