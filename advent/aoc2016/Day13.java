package advent.aoc2016;

import java.util.ArrayList;
import java.util.HashSet;

import advent.utilities.general.Coord;
import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.PathNode;

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

	public int pathfind(Coord start, Coord end) {
		ArrayList<PathNode> open = new ArrayList<PathNode>();
		HashSet<PathNode> closed = new HashSet<PathNode>();
		open.add(new PathNode(start,0,0));
		while(open.size() > 0) {
			PathNode cur = open.remove(0);
			Coord curPos = cur.pos;
			if(curPos.equals(end)) {
				return cur.gcost;
			}
			
			for(Coord c : curPos.directNeighbors()) {
				if(path.contains(c)) {
					PathNode newNode = new PathNode(c,cur.gcost + 1, 0);
					if(!closed.contains(newNode) && !open.contains(newNode))
						open.add(newNode);
				}
			}
			closed.add(cur);
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
