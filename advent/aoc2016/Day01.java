package advent.aoc2016;

import java.util.HashSet;

import advent.utilities.general.Coord;
import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;

public class Day01 implements IDay {

	String input = "R2, L3, R2, R4, L2, L1, R2, R4, R1, L4, L5, R5, R5, R2, R2, R1, L2, L3, L2, L1, R3, L5, R187, R1, R4, L1, R5, L3, L4, R50, L4, R2, R70, L3, L2, R4, R3, R194, L3, L4, L4, L3, L4, R4, R5, L1, L5, L4, R1, L2, R4, L5, L3, R4, L5, L5, R5, R3, R5, L2, L4, R4, L1, R3, R1, L1, L2, R2, R2, L3, R3, R2, R5, R2, R5, L3, R2, L5, R1, R2, R2, L4, L5, L1, L4, R4, R3, R1, R2, L1, L2, R4, R5, L2, R3, L4, L5, L5, L4, R4, L2, R1, R1, L2, L3, L2, R2, L4, R3, R2, L1, L3, L2, L4, L4, R2, L3, L3, R2, L4, L3, R4, R3, L2, L1, L4, R4, R2, L4, L4, L5, L1, R2, L5, L2, L3, R2, L2";
	
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
		DayRunner.run(new Day01());
	}

}
