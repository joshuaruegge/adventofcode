package advent.aoc2015;

import java.util.HashSet;

import advent.utilities.general.Coord;
import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

public class Day18 implements IDay {

	static String input;
	
	@Override
	public String part1() {
		//hold ON light positions in coord set (if position is not in on set, is off)
		HashSet<Coord> lights = new HashSet<Coord>();
		int inputY = 0;
		for(String s : input.split("\n")) {
			for(int x = 0; x < s.length(); x++) {
				if(s.charAt(x) == '#')
					lights.add(new Coord(x,inputY));
			}
			inputY++;
		}
		//iterate over steps
		for(int step = 0; step < 100; step++) {
			//put values in a new array rather than updating current one as we go
			HashSet<Coord> newLights = new HashSet<Coord>();
			for(int x = 0; x < 100; x++) {
				for(int y = 0; y < 100; y++) {
					Coord c = new Coord(x,y);
					int neighborCount = 0;
					for(Coord d : c.allNeighbors())
						if(lights.contains(d))
							neighborCount++;
					//if on, stays on with 2 or 3 neighbors
					if(lights.contains(c)) {
						if(neighborCount == 2 || neighborCount == 3) {
							newLights.add(c);
						}
					//if off, turns on with 3 neighbors
					} else {
						if(neighborCount == 3)
							newLights.add(c);
					}
					
				}
			}
			lights = newLights;
		}
		return Integer.toString(lights.size());
	}
	
	@Override
	public String part2() {
		//same code, but lock corners each loop
		//hold ON light positions in coord set (if position is not in on set, is off)
		HashSet<Coord> lights = new HashSet<Coord>();
		int inputY = 0;
		for(String s : input.split("\n")) {
			for(int x = 0; x < s.length(); x++) {
				if(s.charAt(x) == '#')
					lights.add(new Coord(x,inputY));
			}
			inputY++;
		}
		lights.add(new Coord(0,0));
		lights.add(new Coord(0,99));
		lights.add(new Coord(99,0));
		lights.add(new Coord(99,99));
		for(int step = 0; step < 100; step++) {
			HashSet<Coord> newLights = new HashSet<Coord>();
			for(int x = 0; x < 100; x++) {
				for(int y = 0; y < 100; y++) {
					Coord c = new Coord(x,y);
					int neighborCount = 0;
					for(Coord d : c.allNeighbors())
						if(lights.contains(d))
							neighborCount++;
					if(lights.contains(c)) {
						if(neighborCount == 2 || neighborCount == 3) {
							newLights.add(c);
						}
					} else {
						if(neighborCount == 3)
							newLights.add(c);
					}
					
				}
			}
			lights = newLights;
			lights.add(new Coord(0,0));
			lights.add(new Coord(0,99));
			lights.add(new Coord(99,0));
			lights.add(new Coord(99,99));
		}
		return Integer.toString(lights.size());
	}

	public static void main(String[] args) {
		input = Input.fetchInput(2015,18);
		DayRunner.run(new Day18());
	}

}
