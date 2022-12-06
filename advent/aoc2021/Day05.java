package advent.aoc2021;

import java.util.HashSet;

import advent.utilities.general.Coord;
import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

public class Day05 implements IDay {

	static String input;
	
	@Override
	public String part1() {
		HashSet<Coord> vents = new HashSet<Coord>();
		HashSet<Coord> overlaps = new HashSet<Coord>();

		for(String s : input.split("\n")) {
			String[] parts = s.split(" -> ");
			int startX = Integer.parseInt(parts[0].split(",")[0]);
			int startY = Integer.parseInt(parts[0].split(",")[1]);
			
			int endX = Integer.parseInt(parts[1].split(",")[0]);
			int endY = Integer.parseInt(parts[1].split(",")[1]);
			
			//skip if diagonal
			if(!(startX == endX || startY == endY))
				continue;
			
			//make sure start is less than end for both
			if(endX < startX) {
				int temp = endX;
				endX = startX;
				startX = temp;
			}
			
			if(endY < startY) {
				int temp = endY;
				endY = startY;
				startY = temp;
			}
			
			for(int x = startX; x <= endX; x++) {
				for(int y = startY; y <= endY; y++) {
					//if add to vents fails, add to list of overlaps
					Coord cur = new Coord(x,y);
					if(!vents.add(cur)) {
						overlaps.add(cur);
					}
				}
			}
			
		}
		
		return Integer.toString(overlaps.size());
	}

	@Override
	public String part2() {
		HashSet<Coord> vents = new HashSet<Coord>();
		HashSet<Coord> overlaps = new HashSet<Coord>();
		
		for(String s : input.split("\n")) {
			String[] parts = s.split(" -> ");
			int startX = Integer.parseInt(parts[0].split(",")[0]);
			int startY = Integer.parseInt(parts[0].split(",")[1]);
			
			int endX = Integer.parseInt(parts[1].split(",")[0]);
			int endY = Integer.parseInt(parts[1].split(",")[1]);
			
			
			//this time, be more picky about line type
			if(startX == endX) {
				int y = startY;
				//pad end by one so last value is included
				endY += Math.signum(endY - startY);
				do {
					//if add to vents fails, add to list of overlaps
					Coord cur = new Coord(startX,y);
					if(!vents.add(cur)) {
						overlaps.add(cur);
					}
					//move in proper direction
					y += Math.signum(endY - startY);
				} while(y != endY);
			} else if(startY == endY) {
				int x = startX;
				endX += Math.signum(endX - startX);
				do {
					//if add to vents fails, add to list of overlaps
					Coord cur = new Coord(x,startY);
					if(!vents.add(cur)) {
						overlaps.add(cur);
					}
					x += Math.signum(endX - startX);
				} while(x != endX);
			} else {
				int x = startX;
				int y = startY;
				//pad both ends
				endX += Math.signum(endX - startX);
				endY += Math.signum(endY - startY);
				do {
					//if add to vents fails, add to list of overlaps
					Coord cur = new Coord(x,y);
					if(!vents.add(cur)) {
						overlaps.add(cur);
					}
					//move in correct direction for each independently
					x += Math.signum(endX-startX);
					y += Math.signum(endY-startY);
				} while(x != endX && y != endY);
			}
		}
		
		return Integer.toString(overlaps.size());
	}

	public static void main(String[] args) {
		input = Input.fetchInput(2021,5);
		DayRunner.run(new Day05());
	}
}
