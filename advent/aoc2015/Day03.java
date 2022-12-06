package advent.aoc2015;

import java.util.HashSet;

import advent.utilities.general.Coord;
import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

public class Day03 implements IDay {

	static String input;
	
	@Override
	public String part1() {
		Coord position = new Coord(0,0);
		//keep track of seen positions
		HashSet<Coord> seen = new HashSet<Coord>();
		for(char c : input.toCharArray()) {
			//add current position to seen array if it hasnt already been seen
			if(!seen.contains(position))
				seen.add(position.copy());
			//update position
			switch(c) {
			case '^':
				position.sumSelf(Coord.UP);
				break;
			case 'v':
				position.sumSelf(Coord.DOWN);
				break;
			case '<':
				position.sumSelf(Coord.LEFT);
				break;
			case '>':
				position.sumSelf(Coord.RIGHT);
				break;
			}
		}
		return Integer.toString(seen.size());
	}

	@Override
	public String part2() {
		//two positions, one seen set
		//tracks total locations seen by both
		Coord p1 = new Coord();
		Coord p2 = new Coord();
		HashSet<Coord> seen = new HashSet<Coord>();
		//take two characters at a time
		for(int i = 0; i < input.length(); i+=2) {
			//santa
			if(!seen.contains(p1))
				seen.add(p1.copy());
			char c1 = input.charAt(i);
			switch(c1) {
			case '^':
				p1.sumSelf(Coord.UP);
				break;
			case 'v':
				p1.sumSelf(Coord.DOWN);
				break;
			case '<':
				p1.sumSelf(Coord.LEFT);
				break;
			case '>':
				p1.sumSelf(Coord.RIGHT);
				break;
			}
			
			//robo-santa
			if(!seen.contains(p2)) {
				seen.add(p2.copy());
			}
			char c2 = input.charAt(i+1);
			switch(c2) {
			case '^':
				p2.sumSelf(Coord.UP);
				break;
			case 'v':
				p2.sumSelf(Coord.DOWN);
				break;
			case '<':
				p2.sumSelf(Coord.LEFT);
				break;
			case '>':
				p2.sumSelf(Coord.RIGHT);
				break;
			}
		}
		return Integer.toString(seen.size());
	}

	public static void main(String[] args) {
		input = Input.fetchInput(2015,3);
		DayRunner.run(new Day03());
	}

}
