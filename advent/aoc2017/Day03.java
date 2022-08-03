package advent.aoc2017;

import java.util.HashMap;

import advent.utilities.general.Coord;
import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;

public class Day03 implements IDay {

	String input = "289326";
	
	@Override
	public String part1() {
		int target = Integer.parseInt(input);
		
		//store numbers as coord -> integer hashmap
		//center is 0,0, with positive x right and positive y down
		HashMap<Coord,Integer> grid = new HashMap<Coord,Integer>();
		//put in 1 and 2 manually
		grid.put(new Coord(0,0), 1);
		grid.put(new Coord(1,0), 2);
		
		//start at 3, with corresponding position and facing
		int current = 3;
		Coord position = new Coord(1,0);
		Coord facing = new Coord(0,-1);
		
		while(current <= target) {
			//move in facing direction by adding facing to position
			position.sumSelf(facing);
			//place current value
			grid.put(position.copy(),current);
			if(current == target) {
				//return distance of current's location from center (0,0)
				return Integer.toString(position.dist(new Coord(0,0)));
			}
			//increment current
			current++;
			//if there is no value to the left of position, we rotate left (spiral)
			Coord left = position.sum(facing.left());
			if(!grid.containsKey(left)) {
				facing = facing.left();
			}
		}
		return null;
	}
	
	@Override
	public String part2() {
		int target = Integer.parseInt(input);
		
		//store numbers as coord -> integer hashmap
		//center is 0,0, with positive x right and positive y down
		HashMap<Coord,Integer> grid = new HashMap<Coord,Integer>();
		//put in first two ones manually
		grid.put(new Coord(0,0), 1);
		grid.put(new Coord(1,0), 1);
		
		//now, current is calculated per instance rather than incrementing
		int current = 0;
		Coord position = new Coord(1,0);
		Coord facing = new Coord(0,-1);
		
		//insert until number larger than target is inserted
		while(current <= target) {
			//move in facing direction by adding to position
			position.sumSelf(facing);
			//calculate current
			current = 0;
			//for each neighbor of current position
			for(Coord c : position.allNeighbors()) {
				//if position has a value, add it to current
				current += grid.getOrDefault(c,0);
			}
			grid.put(position.copy(),current);
			//if there is no value to the left of position, we rotate left (spiral)
			Coord left = position.sum(facing.left());
			if(!grid.containsKey(left)) {
				facing = facing.left();
			}
		}
		//return last number (first larger than target) to be inserted
		return Integer.toString(current);
	}

	public static void main(String[] args) {
		DayRunner.run(new Day03());
	}

}
