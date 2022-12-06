package advent.aoc2020;

import advent.utilities.general.Coord;
import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

public class Day12 implements IDay {

	static String input;
	
	@Override
	public String part1() {
		Coord pos = new Coord(0,0);
		Coord facing = new Coord(1,0);
		for(String s : input.split("\n")) {
			switch(s.charAt(0)) {
			case 'N':
				for(int i = 0; i < Integer.parseInt(s.substring(1)); i++)
					pos.sumSelf(Coord.UP);
				break;
			case 'S':
				for(int i = 0; i < Integer.parseInt(s.substring(1)); i++)
					pos.sumSelf(Coord.DOWN);
				break;
			case 'E':
				for(int i = 0; i < Integer.parseInt(s.substring(1)); i++)
					pos.sumSelf(Coord.RIGHT);
				break;
			case 'W':
				for(int i = 0; i < Integer.parseInt(s.substring(1)); i++)
					pos.sumSelf(Coord.LEFT);
				break;
			case 'F':
				for(int i = 0; i < Integer.parseInt(s.substring(1)); i++)
					pos.sumSelf(facing);
				break;
			case 'R':
				for(int i = 0; i < Integer.parseInt(s.substring(1))/90; i++) {
					facing = facing.right();
				}
				break;
			case 'L':
				for(int i = 0; i < Integer.parseInt(s.substring(1))/90; i++) {
					facing = facing.left();
				}
				break;
			}
		}
		return Integer.toString(pos.dist(new Coord(0,0)));
	}

	@Override
	public String part2() {
		Coord pos = new Coord(0,0);
		Coord waypoint = new Coord(10,-1);
		for(String s : input.split("\n")) {
			switch(s.charAt(0)) {
			case 'N':
				for(int i = 0; i < Integer.parseInt(s.substring(1)); i++)
					waypoint.sumSelf(Coord.UP);
				break;
			case 'S':
				for(int i = 0; i < Integer.parseInt(s.substring(1)); i++)
					waypoint.sumSelf(Coord.DOWN);
				break;
			case 'E':
				for(int i = 0; i < Integer.parseInt(s.substring(1)); i++)
					waypoint.sumSelf(Coord.RIGHT);
				break;
			case 'W':
				for(int i = 0; i < Integer.parseInt(s.substring(1)); i++)
					waypoint.sumSelf(Coord.LEFT);
				break;
			case 'F':
				for(int i = 0; i < Integer.parseInt(s.substring(1)); i++)
					pos.sumSelf(waypoint);
				break;
			case 'R':
				for(int i = 0; i < Integer.parseInt(s.substring(1))/90; i++) {
					waypoint = waypoint.right();
				}
				break;
			case 'L':
				for(int i = 0; i < Integer.parseInt(s.substring(1))/90; i++) {
					waypoint = waypoint.left();
				}
				break;
			}
		}
		return Integer.toString(pos.dist(new Coord(0,0)));
	}

	public static void main(String[] args) {
		input = Input.fetchInput(2020,12);
		DayRunner.run(new Day12());
	}
}
