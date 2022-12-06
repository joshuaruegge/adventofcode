package advent.aoc2017;

import java.util.HashMap;
import java.util.HashSet;

import advent.utilities.general.Coord;
import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

public class Day22 implements IDay {

	static String input;
	
	@Override
	public String part1() {
		//determine middle so that we can have (0,0) at middle and assign coords accordingly
		int middle = input.split("\n").length / 2;
		
		//store currently infected coords in set - if coord is not in set, is healthy
		HashSet<Coord> infected = new HashSet<Coord>();
		
		String[] lines = input.split("\n");
		for(int i = 0; i < lines.length; i++) {
			String line = lines[i];
			for(int j = 0; j < line.length(); j++) {
				if(line.charAt(j) == '#') {
					//use middle offset
					infected.add(new Coord(j-middle,i-middle));
				}
			}
		}
		
		Coord position = new Coord(0,0);
		Coord facing = new Coord(0,-1);
		int infectionCounter = 0;
		for(int iter = 0; iter < 10000; iter++) {
			//turn
			if(infected.contains(position)) {
				facing = facing.right();
			} else {
				facing = facing.left();
			}
			//clean or infect
			if(infected.contains(position)) {
				infected.remove(position);
			} else {
				infected.add(position.copy());
				infectionCounter++;
			}
			//move
			position.sumSelf(facing);
		}
		return Integer.toString(infectionCounter);
	}

	@Override
	public String part2() {
		//calculate middle
		int middle = input.split("\n").length / 2;
		
		//now, store states in hashmap
		//not being present in hashmap still means clean
		//2 - weakened
		//1- infected
		//0 - flagged
		HashMap<Coord,Integer> state = new HashMap<Coord,Integer>();
		String[] lines = input.split("\n");
		for(int i = 0; i < lines.length; i++) {
			String line = lines[i];
			for(int j = 0; j < line.length(); j++) {
				if(line.charAt(j) == '#') {
					//use middle offset
					state.put(new Coord(j-middle,i-middle),1);
				}
			}
		}
		//position and facing of carrier
		Coord position = new Coord(0,0);
		Coord facing = new Coord(0,-1);
		int infectionCounter = 0;
		for(int iter = 0; iter < 10000000; iter++) {
			int current = state.getOrDefault(position, -1);
			//turn
			switch(current) {
			case -1:
				facing = facing.left();
				break;
			case 0:
				facing = facing.left().left();
				break;
			case 1:
				facing = facing.right();
				break;
			//2 (weakened) - facing unaltered
			}
			switch(current) {
			case 0:
				state.remove(position);
				break;
			case -1:
				state.put(position.copy(),2);
				break;
			case 2:
				infectionCounter++;
				state.put(position.copy(), 1);
				break;
			case 1:
				state.put(position.copy(), 0);
				break;
			}
			//move forwards
			position.sumSelf(facing);
		}
		return Integer.toString(infectionCounter);
	}

	public static void main(String[] args) {
		input = Input.fetchInput(2017,22);
		DayRunner.run(new Day22());
	}

}
