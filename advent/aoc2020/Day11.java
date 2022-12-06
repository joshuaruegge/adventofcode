package advent.aoc2020;

import java.util.HashSet;

import advent.utilities.general.Coord;
import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

public class Day11 implements IDay {

	static String input;

	@Override
	public String part1() {
		HashSet<Coord> floor = new HashSet<Coord>();
		
		String[] lines = input.split("\n");
		int maxX = lines[0].length();
		int maxY = lines.length;
		for(int y = 0; y < maxY; y++) 
			for(int x = 0; x < maxX; x++) 
				if(lines[y].charAt(x) == '.')
					floor.add(new Coord(x,y));
		
		//store grid state as list of filled seats - if coord is not in gridState, and not in floor, is an unfilled seat
		HashSet<Coord> gridState = new HashSet<Coord>();
		HashSet<HashSet<Coord>> gridStateBuffer = new HashSet<HashSet<Coord>>();
		//loop until we see a condition twice
		do {
			HashSet<Coord> newGridState = new HashSet<Coord>();
			for(int y = 0; y < maxY; y++) {
				for(int x = 0; x < maxX; x++) {
					Coord cur = new Coord(x,y);
					if(floor.contains(cur))
						continue;
					if(gridState.contains(cur)) {
						//only push to new if 3 or less seats around are occupied
						int occCount = 0;
						for(Coord c : cur.allNeighbors()) {
							if(gridState.contains(c))
								occCount++;
						}
						if(occCount < 4)
							newGridState.add(cur);
					} else {
						//if not present, only push to new if no neighbors present
						int occCount = 0;
						for(Coord c : cur.allNeighbors())
							if(gridState.contains(c))
								occCount++;
						if(occCount == 0)
							newGridState.add(cur);
					}
				}
			}
			gridState = newGridState;
		} while(gridStateBuffer.add(gridState));
		
		//total our repeating condition
		return Integer.toString(gridState.size());
	}

	@Override
	public String part2() {
		//constant list of each 8 directions, starting with straight up and going clockwise
		final Coord[] SLOPES = new Coord[] {new Coord(0,-1), new Coord(1,-1), new Coord(1,0), new Coord(1,1), new Coord(0,1), new Coord(-1,1), new Coord(-1,0), new Coord(-1,-1)};
		HashSet<Coord> floor = new HashSet<Coord>();
		
		String[] lines = input.split("\n");
		int maxX = lines[0].length();
		int maxY = lines.length;
		for(int y = 0; y < maxY; y++) 
			for(int x = 0; x < maxX; x++) 
				if(lines[y].charAt(x) == '.')
					floor.add(new Coord(x,y));
		
		//store grid state as list of filled seats - if coord is not in gridState, and not in floor, is an unfilled seat
		HashSet<Coord> gridState = new HashSet<Coord>();
		HashSet<HashSet<Coord>> gridStateBuffer = new HashSet<HashSet<Coord>>();
		//loop until we see a condition twice
		do {
			HashSet<Coord> newGridState = new HashSet<Coord>();
			for(int y = 0; y < maxY; y++) {
				for(int x = 0; x < maxX; x++) {
					Coord cur = new Coord(x,y);
					if(floor.contains(cur))
						continue;
					if(gridState.contains(cur)) {
						int occCount = 0;
						//go over each of 8 directions in slopes
						for(Coord slope : SLOPES) {
							Coord examiningPos = cur.sum(slope);
							//iterate until first non-floor position in that direction found
							while(floor.contains(examiningPos)) {
								examiningPos.sumSelf(slope);
							}
							//now, if seat is occupied, increase count
							if(gridState.contains(examiningPos))
								occCount++;
						}
						//now, if seat is 4 or less, push to new 
						if(occCount < 5)
							newGridState.add(cur);
					} else {
						int occCount = 0;
						//go over each of 8 directions in slopes
						for(Coord slope : SLOPES) {
							Coord examiningPos = cur.sum(slope);
							//iterate until first non-floor position in that direction found
							while(floor.contains(examiningPos)) {
								examiningPos.sumSelf(slope);
							}
							//now, if seat is occupied, increase count
							if(gridState.contains(examiningPos))
								occCount++;
						}
						//now, push if no surrounding
						if(occCount == 0)
							newGridState.add(cur);
					}
				}
			}
			gridState = newGridState;
		} while(gridStateBuffer.add(gridState));
		
		//total our repeating condition
		return Integer.toString(gridState.size());
	}

	public static void main(String[] args) {
		input = Input.fetchInput(2020,11);
		DayRunner.run(new Day11());
	}
}
