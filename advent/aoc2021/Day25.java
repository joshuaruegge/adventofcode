package advent.aoc2021;

import java.util.HashMap;

import advent.utilities.general.Coord;
import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

public class Day25 implements IDay {

	static String input;
	
	@Override
	public String part1() {
		//true for east, false for south
		HashMap<Coord,Boolean> cucumbers = new HashMap<Coord,Boolean>();
		String[] lines = input.split("\n");
		int maxX = lines.length;
		int maxY = lines[0].length();
		
		for(int x = 0; x < maxX; x++) {
			for(int y = 0; y < maxY; y++) {
				switch(lines[x].charAt(y)) {
				case '>':
					cucumbers.put(new Coord(x,y), true);
					break;
				case 'v':
					cucumbers.put(new Coord(x,y), false);
					break;
				}
			}
		}
		
		HashMap<Coord,Boolean> prev = new HashMap<Coord,Boolean>();
		int step = 0;
		do {
			step++;
			prev = cucumbers;
			HashMap<Coord,Boolean> newCucumbers = new HashMap<Coord,Boolean>();
			//easts first
			for(Coord c : cucumbers.keySet()) {
				if(cucumbers.get(c)) {
					if(c.y < maxY - 1) {
						if(!cucumbers.containsKey(new Coord(c.x,c.y + 1))) {
							newCucumbers.put(new Coord(c.x,c.y + 1), true);
						} else {
							newCucumbers.put(c, true);
						}
					} else {
						if(!cucumbers.containsKey(new Coord(c.x,0))) {
							newCucumbers.put(new Coord(c.x,0), true);
						} else {
							newCucumbers.put(c, true);
						}
					}
				} else {
					newCucumbers.put(c, false);
				}
			}
			
			HashMap<Coord,Boolean> newCucumbers2 = new HashMap<Coord,Boolean>();
			for(Coord c : newCucumbers.keySet()) {
				if(!newCucumbers.get(c)) {
					if(c.x < maxX - 1) {
						if(!newCucumbers.containsKey(new Coord(c.x + 1, c.y))) {
							newCucumbers2.put(new Coord(c.x + 1, c.y), false);
						} else {
							newCucumbers2.put(c, false);
						}
					} else {
						if(!newCucumbers.containsKey(new Coord(0,c.y))) {
							newCucumbers2.put(new Coord(0,c.y), false);
						} else {
							newCucumbers2.put(c, false);
						}
					}
				} else {
					newCucumbers2.put(c, true);
				}
			}
			
			cucumbers = newCucumbers2;
		} while(!cucumbers.equals(prev));
		return Integer.toString(step);
	}
	
	@Override
	public String part2() {
		return "Merry Christmas!";
	}

	public static void main(String[] args) {
		input = Input.fetchInput(2021,25);
		DayRunner.run(new Day25());
	}
}
