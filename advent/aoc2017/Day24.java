package advent.aoc2017;

import java.util.HashSet;

import advent.utilities.general.Coord;
import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

public class Day24 implements IDay {

	static String input;
	
	static HashSet<Coord> pipes;
	
	@Override
	public String part1() {
		String[] lines = input.split("\n");
		pipes = new HashSet<Coord>();
		for(String s : lines) {
			String[] parts = s.split("/");
			pipes.add(new Coord(Integer.parseInt(parts[0]), Integer.parseInt(parts[1])));
		}
		return Integer.toString(strongestBridge(0,new HashSet<Coord>()));
	}
	
	//recursive bridge building method
	public static int strongestBridge(int curConnector, HashSet<Coord> bridge) {
		int strongest = 0;
		HashSet<Coord> potential = new HashSet<Coord>();
		for(Coord c : pipes) {
			if((c.x == curConnector || c.y == curConnector) && !bridge.contains(c)) {
				potential.add(c);
			}
		}
		if(potential.size() == 0) {
			//calculate strength
			for(Coord c : bridge)
				strongest += c.dist(new Coord());
			return strongest;
		}
		for(Coord c : potential) {
			HashSet<Coord> newBridge = new HashSet<Coord>(bridge);
			newBridge.add(c);
			int strength = strongestBridge((curConnector == c.x ? c.y : c.x), newBridge);
			strongest = Math.max(strongest, strength);
		}
		return strongest;
	}

	@Override
	public String part2() {
		String[] lines = input.split("\n");
		pipes = new HashSet<Coord>();
		for(String s : lines) {
			String[] parts = s.split("/");
			pipes.add(new Coord(Integer.parseInt(parts[0]), Integer.parseInt(parts[1])));
		}
		return Integer.toString(sum(longestBridge(0,new HashSet<Coord>())));
	}

	public HashSet<Coord> longestBridge(int curConnector, HashSet<Coord> bridge) {
		int longest = 0;
		HashSet<Coord> potential = new HashSet<Coord>();
		for(Coord c : pipes) {
			if((c.x == curConnector || c.y == curConnector) && !bridge.contains(c)) {
				potential.add(c);
			}
		}
		if(potential.size() == 0) {
			return bridge;
		}
		HashSet<Coord> longestBridge = null;
		for(Coord c : potential) {
			HashSet<Coord> newBridge = new HashSet<Coord>(bridge);
			newBridge.add(c);
			HashSet<Coord> longBridge = longestBridge((curConnector == c.x ? c.y : c.x), newBridge);
			if(longBridge.size() == longest) {
				if(sum(longBridge) > sum(longestBridge)) {
					longestBridge = longBridge;
				}
			}
			if(longBridge.size() > longest) {
				longest = longBridge.size();
				longestBridge = longBridge;
			}
		}
		return longestBridge;
	}
	
	public int sum(HashSet<Coord> s) {
		int sum = 0;
		for(Coord c : s) {
			sum += c.dist(new Coord());
		}
		return sum;
	}
	
	public static void main(String[] args) {
		input = Input.fetchInput(2017,24);
		DayRunner.run(new Day24());
	}

}
