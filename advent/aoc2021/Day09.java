package advent.aoc2021;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;

import advent.utilities.general.Coord;
import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

public class Day09 implements IDay {

	static String input;
	
	@Override
	public String part1() {
		String[] lines = input.split("\n");
		int[][] heightmap = new int[lines.length][lines[0].length()];
		int row = 0;
		for(String s : lines) {
			for(int i = 0; i < s.length(); i++) {
				heightmap[row][i] = Integer.parseInt(s.substring(i,i+1));
			}
			row++;
		}
		int riskTotal = 0;
		for(int x = 0; x < heightmap.length; x++) {
			for(int y = 0; y < heightmap[x].length; y++) {
				int cur = heightmap[x][y];
				if(x > 0)
					if(heightmap[x-1][y] <= cur)
						continue;
				if(x < heightmap.length - 1)
					if(heightmap[x+1][y] <= cur)
						continue;
				if(y > 0)
					if(heightmap[x][y-1] <= cur)
						continue;
				if(y < heightmap[x].length - 1)
					if(heightmap[x][y+1] <= cur)
						continue;
				//if not continued by this point, all surrounding tiles are higher, so this is a low point
				riskTotal += cur + 1;
			}
		}
		
		return Integer.toString(riskTotal);
	}

	@Override
	public String part2() {
		String[] lines = input.split("\n");
		int[][] heightmap = new int[lines.length][lines[0].length()];
		int row = 0;
		for(String s : lines) {
			for(int i = 0; i < s.length(); i++) {
				heightmap[row][i] = Integer.parseInt(s.substring(i,i+1));
			}
			row++;
		}
		//rather than totaling risk, build set of low points
		HashSet<Coord> lows = new HashSet<Coord>();
		
		for(int x = 0; x < heightmap.length; x++) {
			for(int y = 0; y < heightmap[x].length; y++) {
				int cur = heightmap[x][y];
				if(x > 0)
					if(heightmap[x-1][y] <= cur)
						continue;
				if(x < heightmap.length - 1)
					if(heightmap[x+1][y] <= cur)
						continue;
				if(y > 0)
					if(heightmap[x][y-1] <= cur)
						continue;
				if(y < heightmap[x].length - 1)
					if(heightmap[x][y+1] <= cur)
						continue;
				//if not continued by this point, all surrounding tiles are higher, so this is a low point
				lows.add(new Coord(x,y));
			}
		}
		ArrayList<Integer> sizes = new ArrayList<Integer>();
		//for each low point, perform flood-fill bounded by 9s and add size to list
		for(Coord c : lows) {
			int size = 0;
			LinkedList<Coord> queue = new LinkedList<Coord>();
			HashSet<Coord> closed = new HashSet<Coord>();
			queue.add(c);
			while(queue.size() > 0) {
				Coord cur = queue.poll();
				if(closed.contains(cur) || value(cur,heightmap) == 9)
					continue;
				//otherwise, increment size and push neighbors to queue
				size++;
				for(Coord n : cur.directNeighbors()) {
					if(!closed.contains(n))
						queue.add(n);
				}
				closed.add(cur);
			}
			sizes.add(size);
		}
		Collections.sort(sizes);
		Collections.reverse(sizes);
		return Integer.toString(sizes.get(0) * sizes.get(1) * sizes.get(2));
		
	}
	
	//gets value at point, returning 9 if out of bounds
	public int value(Coord c, int[][] a) {
		if(c.x < 0 || c.x >= a.length || c.y < 0 || c.y >= a[0].length) {
			return 9;
		}
		return a[c.x][c.y];
	}

	public static void main(String[] args) {
		input = Input.fetchInput(2021,9);
		DayRunner.run(new Day09());
	}
}
