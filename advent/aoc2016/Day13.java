package advent.aoc2016;

import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Comparator;

import advent.utilities.general.Coord;
import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;

public class Day13 implements IDay {

	String input = "1362";
	
	static HashSet<Coord> path;
	
	final Coord DEST = new Coord(31,39);
	
	@Override
	public String part1() {
		int magicNumber = Integer.parseInt(input);
		//store path positions in static Coord hash set
		//coordinates not in path positions are assumed to be walls
		path = new HashSet<Coord>();
		
		//calculate paths out to (50,50)
		//this is arbitrary based on the destination coordinate
		for(int x = 0; x < 50; x++) {
			for(int y = 0; y < 50; y++) {
				int coordState = x * x + 3 * x + 2 * x * y + y + y * y + magicNumber;
				if(Integer.bitCount(coordState) % 2 == 0)
					path.add(new Coord(x,y));
			}
		}
		
		//return shortest pathfind distance
		return Integer.toString(pathfind(new Coord(1,1),DEST));
	}

	public int pathfind (Coord start, Coord end) {
		
		HashMap<Coord,Integer> gScore = new HashMap<Coord,Integer>();
		PriorityQueue<Coord> queue = new PriorityQueue<Coord>( new Comparator<Coord>() {

			@Override
			public int compare(Coord o1, Coord o2) {
				return Integer.compare(gScore.getOrDefault(o1,0) + o1.dist(end), gScore.getOrDefault(o2,0) + o2.dist(end));
			}
			
		});
		gScore.put(start, 0);
		queue.add(start);
		while(queue.size() != 0) {
			Coord cur = queue.remove();
			if(cur.equals(end)) {
				return gScore.get(cur);
			}
			
			for(Coord c : cur.directNeighbors()) {
				if(!path.contains(c))
					continue;
				int tentativeG = gScore.get(cur) + 1;
				if(tentativeG < gScore.getOrDefault(c, Integer.MAX_VALUE)) {
					gScore.put(c, tentativeG);
					queue.add(c);
				}
			}
		}
		return -1;
	}
	
	@Override
	public String part2() {
		//clear paths
		path.clear();
		
		int magicNumber = Integer.parseInt(input);
		//store path positions in static Coord hash set
		//coordinates not in path positions are assumed to be walls
		path = new HashSet<Coord>();
		
		//calculate paths out to (50,50)
		//this is arbitrary based on the destination coordinate
		for(int x = 0; x < 50; x++) {
			for(int y = 0; y < 50; y++) {
				int coordState = x * x + 3 * x + 2 * x * y + y + y * y + magicNumber;
				if(Integer.bitCount(coordState) % 2 == 0)
					path.add(new Coord(x,y));
			}
		}
		int lessThan50 = 0;
		for(int x = 0; x < 50; x++) {
			for(int y = 0; y < 50; y++) {
				if(path.contains(new Coord(x,y))) {
					int dist = pathfind(new Coord(1,1), new Coord(x,y));
					if(dist != -1 && dist <= 50)
						lessThan50++;
				}
			}
		}
		return Integer.toString(lessThan50);
	}

	public static void main(String[] args) {
		DayRunner.run(new Day13());
	}

}
