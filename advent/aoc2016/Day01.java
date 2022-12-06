package advent.aoc2016;

import java.util.HashSet;

import advent.utilities.general.Coord;
import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

public class Day01 implements IDay {

	static String input;
	
	@Override
	public String part1() {
		//keep track of position as coord object
		Coord position = new Coord(0,0);
		//keep track of current facing as a "unit coordinate" pointing that direction
		Coord facing = new Coord(0,-1);
		//break input into pieces
		for(String s : input.split(", ")) {
			//turn right or left based on first character
			if(s.charAt(0) == 'R') {
				facing = facing.right();
			} else {
				facing = facing.left();
			}
			//iterate number of steps specified in input
			for(int i = 0; i < Integer.parseInt(s.substring(1)); i++) {
				position.sumSelf(facing);
			}
		}
		//return Manhattan distance from (0,0) of final position
		return Integer.toString(position.dist(new Coord(0,0)));
	}

	@Override
	public String part2() {
		//similar, but use a set to keep track of seen positions, and break on first repeat
		HashSet<Coord> seenPositions = new HashSet<Coord>();
		//keep track of position as coord object
		Coord position = new Coord(0,0);
		//keep track of current facing as a "unit coordinate" pointing that direction
		Coord facing = new Coord(0,-1);
		//break input into pieces
		for(String s : input.split(", ")) {
			//turn right or left based on first character
			if(s.charAt(0) == 'R') {
				facing = facing.right();
			} else {
				facing = facing.left();
			}
			//iterate number of steps specified in input
			for(int i = 0; i < Integer.parseInt(s.substring(1)); i++) {
				position.sumSelf(facing);
				//if position has already been seen, return distance of position
				if(!seenPositions.add(position.copy()))
					return Integer.toString(position.dist(new Coord(0,0)));
			}
		}
		return null;
	}

	public static void main(String[] args) {
		input = Input.fetchInput(2016,1).replace("\n","");
		DayRunner.run(new Day01());
	}

}
