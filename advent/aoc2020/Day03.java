package advent.aoc2020;

import java.util.HashSet;

import advent.utilities.general.Coord;
import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

public class Day03 implements IDay {

	static String input;
	
	@Override
	public String part1() {
		//keep trees as coord hashset - not in trees, is open space
		HashSet<Coord> trees = new HashSet<Coord>();
		String[] lines = input.split("\n");
		for(int y = 0; y < lines.length; y++) {
			String line = lines[y];
			for(int x = 0; x < line.length(); x++) {
				if(line.charAt(x) == '#')
					trees.add(new Coord(x,y));
			}
		}
		int maxX = lines[0].length();
		
		Coord cur = new Coord(0,0);
		Coord slopeStep = new Coord(3,1);
		
		int treeCount = 0;
		while(cur.y < lines.length) {
			cur.sumSelf(slopeStep);
			if(cur.x >= maxX)
				cur.x %= maxX;
			if(trees.contains(cur))
				treeCount++;
		}
		return Integer.toString(treeCount);
	}

	@Override
	public String part2() {
		HashSet<Coord> trees = new HashSet<Coord>();
		String[] lines = input.split("\n");
		for(int y = 0; y < lines.length; y++) {
			String line = lines[y];
			for(int x = 0; x < line.length(); x++) {
				if(line.charAt(x) == '#')
					trees.add(new Coord(x,y));
			}
		}
		int maxX = lines[0].length();
		
		final Coord[] SLOPES = new Coord[] {new Coord(1,1), new Coord(3,1), new Coord(5,1), new Coord(7,1), new Coord(1,2)};
		
		long multTotal = 1;
		for(Coord slope : SLOPES) {
			Coord cur = new Coord(0,0);
			
			int treeCount = 0;
			while(cur.y < lines.length) {
				cur.sumSelf(slope);
				if(cur.x >= maxX)
					cur.x %= maxX;
				
				if(trees.contains(cur))
					treeCount++;
			}
			multTotal *= treeCount;
		}
		
		return Long.toString(multTotal);
	}

	public static void main(String[] args) {
		input = Input.fetchInput(2020,3);
		DayRunner.run(new Day03());
	}
}
