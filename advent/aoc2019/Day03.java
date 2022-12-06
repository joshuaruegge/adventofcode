package advent.aoc2019;

import java.util.HashMap;
import java.util.HashSet;

import advent.utilities.general.Coord;
import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

public class Day03 implements IDay {

	static String input;

	@Override
	public String part1() {
		String[] line1 = input.split("\n")[0].split(",");
		Coord pos1 = new Coord(0,0);
		//collect set of all coordinates first wire touches
		HashSet<Coord> line1Pos = new HashSet<Coord>();
		for(String s : line1) {
			//use first letter of input to determine direction
			Coord dir = new Coord();
			switch(s.charAt(0)) {
			case 'U':
				dir = Coord.UP;
				break;
			case 'D':
				dir = Coord.DOWN;
				break;
			case 'L':
				dir = Coord.LEFT;
				break;
			case 'R':
				dir = Coord.RIGHT;
				break;
			}
			//iterate number of times in input
			int max = Integer.parseInt(s.substring(1));
			for(int d = 0; d < max; d++) {
				//increment position by direction coordinate, add new to list
				pos1.sumSelf(dir);
				line1Pos.add(pos1.copy());
			}
		}
		//repeat for second wire
		String[] line2 = input.split("\n")[1].split(",");
		Coord pos2 = new Coord(0,0);
		HashSet<Coord> line2Pos = new HashSet<Coord>();
		for(String s : line2) {
			Coord dir = new Coord();
			switch(s.charAt(0)) {
			case 'U':
				dir = Coord.UP;
				break;
			case 'D':
				dir = Coord.DOWN;
				break;
			case 'L':
				dir = Coord.LEFT;
				break;
			case 'R':
				dir = Coord.RIGHT;
				break;
			}
			int max = Integer.parseInt(s.substring(1));
			for(int d = 0; d < max; d++) {
				pos2.sumSelf(dir);
				line2Pos.add(pos2.copy());	
			}	
		}
		
		//intersections
		HashSet<Coord> intersections = new HashSet<Coord>();
		//iterate over whichever array is shorter
		for(Coord c : (line1Pos.size() < line2Pos.size() ? line1Pos : line2Pos)) {
			//if coordinate is in other array as well, add to intersections list
			if((line1Pos.size() < line2Pos.size() ? line2Pos : line1Pos).contains(c)) {
				intersections.add(c);
			}
		}
		
		//check each intersection, return shortest distance
		return intersections.stream().map(x -> x.dist(new Coord())).min(Integer::compare).get().toString();
	}

	@Override
	public String part2() {
		String[] line1 = input.split("\n")[0].split(",");
		Coord pos1 = new Coord(0,0);
		//change to a hashmap of coordinates and distance to reach coordinates
		HashMap<Coord,Integer> line1Pos = new HashMap<Coord,Integer>();
		line1Pos.put(pos1,0);
		int step = 0;
		for(String s : line1) {
			Coord dir = new Coord();
			switch(s.charAt(0)) {
			case 'U':
				dir = Coord.UP;
				break;
			case 'D':
				dir = Coord.DOWN;
				break;
			case 'L':
				dir = Coord.LEFT;
				break;
			case 'R':
				dir = Coord.RIGHT;
				break;
			}
			for(int d = 0; d < Integer.parseInt(s.substring(1)); d++) {
				pos1.sumSelf(dir);
				step++;
				//if this isnt an intersection already in the map
				//(keep first step count for intersections)
				if(!line1Pos.containsKey(pos1)) {
					line1Pos.put(pos1.copy(), step);
				}
			}
		}
		String[] line2 = input.split("\n")[1].split(",");
		Coord pos2 = new Coord(0,0);
		HashMap<Coord,Integer> line2Pos = new HashMap<Coord,Integer>();
		line2Pos.put(pos2,0);
		step = 0;
		for(String s : line2) {
			Coord dir = new Coord();
			switch(s.charAt(0)) {
			case 'U':
				dir = Coord.UP;
				break;
			case 'D':
				dir = Coord.DOWN;
				break;
			case 'L':
				dir = Coord.LEFT;
				break;
			case 'R':
				dir = Coord.RIGHT;
				break;
			}
			for(int d = 0; d < Integer.parseInt(s.substring(1)); d++) {
				pos2.sumSelf(dir);
				step++;
				if(!line2Pos.containsKey(pos2))
					line2Pos.put(pos2.copy(), step);
			}	
		}
		
		//for each intersection, combine step count from each wire and find lowest total
		int leastDelay = Integer.MAX_VALUE;
		for(Coord c : line1Pos.keySet()) {
			if(line2Pos.containsKey(c)) {
				int dist = line1Pos.get(c) + line2Pos.get(c);
				if(dist < leastDelay)
					leastDelay = dist;
			}
		}
		return Integer.toString(leastDelay);
	}

	public static void main(String[] args) {
		input = Input.fetchInput(2019,3);
		DayRunner.run(new Day03());
	}

}
