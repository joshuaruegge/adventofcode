package advent.aoc2021;

import java.util.ArrayList;
import java.util.HashSet;

import advent.utilities.general.Coord;
import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

public class Day13 implements IDay {

	static String input;
	
	@Override
	public String part1() {
		ArrayList<Coord> dots = new ArrayList<Coord>();
		for(String s : input.split("\n\n")[0].split("\n")) {
			dots.add(new Coord(Integer.parseInt(s.split(",")[0]), Integer.parseInt(s.split(",")[1])));
		}
		
		for(String s : input.split("\n\n")[1].split("\n")) {
			String fold = s.split(" ")[2];
			int mid = Integer.parseInt(fold.substring(2));
			if(fold.charAt(0) == 'y') {
				dots = new ArrayList<Coord>(dots.stream().map(x -> (x.y > mid ? new Coord(x.x, mid - (x.y - mid)) : x)).toList());
			} else {
				dots = new ArrayList<Coord>(dots.stream().map(x -> (x.x > mid ? new Coord(mid - (x.x - mid),x.y) : x)).toList());
			}
			break;
		}
		HashSet<Coord> finDots = new HashSet<Coord>(dots);
		
		return Integer.toString(finDots.size());
	}

	@Override
	public String part2() {
		ArrayList<Coord> dots = new ArrayList<Coord>();
		for(String s : input.split("\n\n")[0].split("\n")) {
			dots.add(new Coord(Integer.parseInt(s.split(",")[0]), Integer.parseInt(s.split(",")[1])));
		}
		
		for(String s : input.split("\n\n")[1].split("\n")) {
			String fold = s.split(" ")[2];
			int mid = Integer.parseInt(fold.substring(2));
			if(fold.charAt(0) == 'y') {
				dots = new ArrayList<Coord>(dots.stream().map(x -> (x.y > mid ? new Coord(x.x, mid - (x.y - mid)) : x)).toList());
			} else {
				dots = new ArrayList<Coord>(dots.stream().map(x -> (x.x > mid ? new Coord(mid - (x.x - mid),x.y) : x)).toList());
			}
			
		}
		HashSet<Coord> finDots = new HashSet<Coord>(dots);
		
		
		int maxX = finDots.stream().mapToInt(x -> x.x).max().getAsInt();
		int maxY = finDots.stream().mapToInt(x -> x.y).max().getAsInt();
		
		for(int y = 0; y <= maxY; y++) {
			for(int x = 0; x <= maxX; x++) {
				if(finDots.contains(new Coord(x,y)))
					System.out.print("#");
				else
					System.out.print(".");
			}
			System.out.println();
		}
		
		return "Examine output";
	}

	public static void main(String[] args) {
		input = Input.fetchInput(2021,13);
		DayRunner.run(new Day13());
	}
}
