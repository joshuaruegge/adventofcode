package advent.aoc2018;

import java.util.HashSet;

import advent.utilities.general.Coord;
import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

public class Day03 implements IDay {

	static String input;
	
	@Override
	public String part1() {
		HashSet<Coord> claims = new HashSet<Coord>();
		HashSet<Coord> overlaps = new HashSet<Coord>();
		for(String s : input.split("\n")) {
			String[] parts = s.split(" ");
			//trim colon
			parts[2] = parts[2].substring(0,parts[2].length() - 1);
			int xStart = Integer.parseInt(parts[2].split(",")[0]);
			int yStart = Integer.parseInt(parts[2].split(",")[1]);
			int xLength = Integer.parseInt(parts[3].split("x")[0]);
			int yLength = Integer.parseInt(parts[3].split("x")[1]);
			for(int x = xStart; x < xStart + xLength; x++) {
				for(int y = yStart; y < yStart + yLength; y++) {
					//if add fails, square is overlap
					if(!claims.add(new Coord(x,y))) {
						overlaps.add(new Coord(x,y));
					}
				}
			}
		}
		return Integer.toString(overlaps.size());
	}
	
	@Override
	public String part2() {
		HashSet<Coord> claims = new HashSet<Coord>();
		HashSet<Coord> overlaps = new HashSet<Coord>();
		for(String s : input.split("\n")) {
			String[] parts = s.split(" ");
			//trim colon
			parts[2] = parts[2].substring(0,parts[2].length() - 1);
			int xStart = Integer.parseInt(parts[2].split(",")[0]);
			int yStart = Integer.parseInt(parts[2].split(",")[1]);
			int xLength = Integer.parseInt(parts[3].split("x")[0]);
			int yLength = Integer.parseInt(parts[3].split("x")[1]);
			for(int x = xStart; x < xStart + xLength; x++) {
				for(int y = yStart; y < yStart + yLength; y++) {
					//if add fails, square is overlap
					if(!claims.add(new Coord(x,y))) {
						overlaps.add(new Coord(x,y));
					}
				}
			}
		}
		//now that claims and overlaps are determined, iterate again and find claim with no coords in overlap
		lines:
		for(String s : input.split("\n")) {
			String[] parts = s.split(" ");
			//trim colon
			parts[2] = parts[2].substring(0,parts[2].length() - 1);
			int xStart = Integer.parseInt(parts[2].split(",")[0]);
			int yStart = Integer.parseInt(parts[2].split(",")[1]);
			int xLength = Integer.parseInt(parts[3].split("x")[0]);
			int yLength = Integer.parseInt(parts[3].split("x")[1]);
			for(int x = xStart; x < xStart + xLength; x++) {
				for(int y = yStart; y < yStart + yLength; y++) {
					if(overlaps.contains(new Coord(x,y))) {
						//skip to next line
						continue lines;
					}
				}
			}
			//if we made it all the way here without skipping, this is the valid claim
			return parts[0].substring(1);
		}
		return null;
	}

	public static void main(String[] args) {
		input = Input.fetchInput(2018,3);
		DayRunner.run(new Day03());
	}

}
