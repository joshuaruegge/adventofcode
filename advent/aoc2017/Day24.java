package advent.aoc2017;

import java.util.HashSet;

import advent.utilities.general.Coord;
import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;

public class Day24 implements IDay {

	String input = "32/31\r\n"
			+ "2/2\r\n"
			+ "0/43\r\n"
			+ "45/15\r\n"
			+ "33/24\r\n"
			+ "20/20\r\n"
			+ "14/42\r\n"
			+ "2/35\r\n"
			+ "50/27\r\n"
			+ "2/17\r\n"
			+ "5/45\r\n"
			+ "3/14\r\n"
			+ "26/1\r\n"
			+ "33/38\r\n"
			+ "29/6\r\n"
			+ "50/32\r\n"
			+ "9/48\r\n"
			+ "36/34\r\n"
			+ "33/50\r\n"
			+ "37/35\r\n"
			+ "12/12\r\n"
			+ "26/13\r\n"
			+ "19/4\r\n"
			+ "5/5\r\n"
			+ "14/46\r\n"
			+ "17/29\r\n"
			+ "45/43\r\n"
			+ "5/0\r\n"
			+ "18/18\r\n"
			+ "41/22\r\n"
			+ "50/3\r\n"
			+ "4/4\r\n"
			+ "17/1\r\n"
			+ "40/7\r\n"
			+ "19/0\r\n"
			+ "33/7\r\n"
			+ "22/48\r\n"
			+ "9/14\r\n"
			+ "50/43\r\n"
			+ "26/29\r\n"
			+ "19/33\r\n"
			+ "46/31\r\n"
			+ "3/16\r\n"
			+ "29/46\r\n"
			+ "16/0\r\n"
			+ "34/17\r\n"
			+ "31/7\r\n"
			+ "5/27\r\n"
			+ "7/4\r\n"
			+ "49/49\r\n"
			+ "14/21\r\n"
			+ "50/9\r\n"
			+ "14/44\r\n"
			+ "29/29\r\n"
			+ "13/38\r\n"
			+ "31/11";
	
	static HashSet<Coord> pipes;
	
	@Override
	public String part1() {
		String[] lines = input.split("\r\n");
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
		String[] lines = input.split("\r\n");
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
		DayRunner.run(new Day24());
	}

}
