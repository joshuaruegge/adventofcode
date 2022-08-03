package advent.aoc2018;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;

import advent.utilities.general.Coord;
import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.utils2018.Traversal;

public class Day22 implements IDay {

	String input = "depth: 8112\r\n"
			+ "target: 13,743";
	
	@Override
	public String part1() {
		int depth = Integer.parseInt(input.split("\r\n")[0].split(": ")[1]);
		String[] t = input.split("\r\n")[1].split(": ")[1].split(",");
		Coord target = new Coord(Integer.parseInt(t[0]), Integer.parseInt(t[1]));
		int xMax = target.x + 1;
		int yMax = target.y + 1;
		int[][] map = new int[xMax][yMax];
		for(int x = 0; x < xMax; x++) {
			for(int y = 0; y < yMax; y++) {
				if((x == 0 && y == 0) || (x == xMax - 1 && y == yMax - 1)) {
					map[x][y] = depth % 20183;
					continue;
				}
				if(x == 0) {
					map[x][y] = ((y * 48271) + depth) % 20183;
					continue;
				}
				if(y == 0) {
					map[x][y] = ((x * 16807) + depth) % 20183;
					continue;
				}
				map[x][y] = ((map[x-1][y] * map[x][y-1]) + depth) % 20183;
			}
		}
		//now, iterate and update from erosion values to tiletypes
		for(int x = 0; x < xMax; x++)
			for(int y = 0; y < yMax; y++)
				map[x][y] = map[x][y] % 3;
		
		int sum = 0;
		for(int[] a : map)
			for(int b : a)
				sum += b;
		return Integer.toString(sum);
	}

	final int[][] tools = new int[][] {{1,2},{0,1},{0,2}};
	
	@Override
	public String part2() {
		int depth = Integer.parseInt(input.split("\r\n")[0].split(": ")[1]);
		String[] t = input.split("\r\n")[1].split(": ")[1].split(",");
		Coord target = new Coord(Integer.parseInt(t[0]), Integer.parseInt(t[1]));
		//adjust target coords for zero-indexing
		target.x--;
		target.y--;
		//now that we are pathfinding, pad a bit on edges to account for unusual shortest-path that leaves bounding box 
		int xMax = target.x + 50;
		int yMax = target.y + 50;
		int[][] map = new int[xMax][yMax];
		for(int x = 0; x < xMax; x++) {
			for(int y = 0; y < yMax; y++) {
				if((x == 0 && y == 0) || (x == xMax - 1 && y == yMax - 1)) {
					map[x][y] = depth % 20183;
					continue;
				}
				if(x == 0) {
					map[x][y] = ((y * 48271) + depth) % 20183;
					continue;
				}
				if(y == 0) {
					map[x][y] = ((x * 16807) + depth) % 20183;
					continue;
				}
				map[x][y] = ((map[x-1][y] * map[x][y-1]) + depth) % 20183;
			}
		}
		
		//now, iterate and update from erosion values to tiletypes
		for(int x = 0; x < xMax; x++)
			for(int y = 0; y < yMax; y++)
				map[x][y] = map[x][y] % 3;
		
		HashMap<Traversal,Traversal> parent = new HashMap<Traversal,Traversal>();
		HashMap<Traversal,Integer> gScore = new HashMap<Traversal,Integer>();
		HashMap<Traversal,Integer> fScore = new HashMap<Traversal,Integer>();
		PriorityQueue<Traversal> open = new PriorityQueue<Traversal>(new Comparator<Traversal>() {

			@Override
			public int compare(Traversal o1, Traversal o2) {
				return Integer.compare(fScore.getOrDefault(o1,Integer.MAX_VALUE), fScore.getOrDefault(o2,Integer.MAX_VALUE));
			}
		
		});
		open.add(new Traversal(new Coord(0,0),2));
		gScore.put(new Traversal(new Coord(0,0),2), 0);
		fScore.put(new Traversal(new Coord(0,0),2), target.dist(new Coord(0,0)));
		
		int best = Integer.MAX_VALUE;
		
		while(open.size() > 0) {
			
			Traversal cur = open.poll();
			Coord curPos = cur.pos;
			int[] curPosTools = tools[map[curPos.x][curPos.y]];
			
			if(curPos.equals(target)) {
				//account for fact we have to end with torch, and that we could arrive from multiple traversals - different tools
				best = Math.min(best, gScore.get(cur) + (cur.tool == 2 ? 0 : 7));
			}
				
			for(Coord c : curPos.directNeighbors()) {
				if(c.x < 0 || c.x >= xMax || c.y < 0 || c.y >= yMax)
					continue;
				int[] neighborTools = tools[map[c.x][c.y]];
				for(int tool : commons(curPosTools,neighborTools)) {
					Traversal next = new Traversal(c,tool);
					int possGScore = gScore.get(cur) + (tool == cur.tool ? 1 : 8);
					
					if(possGScore < gScore.getOrDefault(next, Integer.MAX_VALUE)) {
						parent.put(next,cur);
						gScore.put(next, possGScore);
						fScore.put(next, possGScore + c.dist(target));
						if(!open.contains(next))
							open.add(next);
					}
				}
			}
		}
		return Integer.toString(best);
	}
	
	//returns set of values present in both (assumes same size)
	public HashSet<Integer> commons(int[] a, int[] b) {
		HashSet<Integer> ret = new HashSet<Integer>();
		for(int i : a) {
			for(int j : b) {
				if(i == j)
					ret.add(i);
			}
		}
		return ret;
	}

	public static void main(String[] args) {
		DayRunner.run(new Day22());
	}

}
