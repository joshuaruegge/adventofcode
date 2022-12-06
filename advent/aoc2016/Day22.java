package advent.aoc2016;

import java.util.*;

import advent.utilities.general.*;

public class Day22 implements IDay {

	static String input;
	
	@Override
	public String part1() {
		String[] lines = input.split("\n");
		
		//store nodes as coord-coord hashmap
		//key coord: x,y
		//value coord: total space/used space
		HashMap<Coord,Coord> nodes = new HashMap<Coord,Coord>();
		
		//skip first two lines
		for(int i = 2; i < lines.length; i++) {
			String[] parts = lines[i].split(" +");
			String[] location = parts[0].split("-");
			int x = Integer.parseInt(location[1].substring(1));
			int y = Integer.parseInt(location[2].substring(1));
			int total = Integer.parseInt(parts[1].substring(0,parts[1].length() - 1));
			int used = Integer.parseInt(parts[2].substring(0,parts[2].length() - 1));
			nodes.put(new Coord(x,y), new Coord(total,used));
		}
		
		//total valid pairs
		int pairs = 0;
		for(Coord c : nodes.keySet()) {
			for(Coord d : nodes.keySet()) {
				if(c.equals(d))
					continue;
				if(nodes.get(c).y == 0)
					continue;
				//if c's data is less than free space on d
				if(nodes.get(c).y <= nodes.get(d).x - nodes.get(d).y)
					pairs++;
			}
		}
		return Integer.toString(pairs);
	}

	@Override
	public String part2() {
		String[] lines = input.split("\n");
		
		//store nodes as coord-coord hashmap
		//key coord: x,y
		//value coord: total space/used space
		HashMap<Coord,Coord> nodes = new HashMap<Coord,Coord>();
		
		//keep track of max x and y
		int maxX = 0;
		int maxY = 0;
		
		//skip first two lines
		for(int i = 2; i < lines.length; i++) {
			String[] parts = lines[i].split(" +");
			String[] location = parts[0].split("-");
			int x = Integer.parseInt(location[1].substring(1));
			if(x > maxX)
				maxX = x;
			int y = Integer.parseInt(location[2].substring(1));
			if(y > maxY)
				maxY = y;
			int total = Integer.parseInt(parts[1].substring(0,parts[1].length() - 1));
			int used = Integer.parseInt(parts[2].substring(0,parts[2].length() - 1));
			nodes.put(new Coord(x,y), new Coord(total,used));
		}
		
		//all inputs have 4 important features that we'll use:
		//the top right - "source"
		//the top left - destination
		//a near-complete row of high-capacity, high-usage nodes - these function as "walls"
		//an empty space
		//all other spaces are insignificant - they have enough data to block being moved through, but not too much data to the point where it won't fit on the empty space
		//to solve the problem - we first need to move the empty space to the space right in front of the data's starting point
		//then, until the data is in the destination, we move the data over to the empty space, then move the empty space down, left, and back up
		//so it's in front of the data again (which takes 5 moves
		//so, the total number of moves required is:
		//distance from empty to data, minus one (move empty in front of data)
		//plus 5 * (distance from data to destination - 1) (move data into empty, then move empty in front of data, till data is next to destination)
		//plus 1 (final move into destination)
		Coord target = new Coord(maxX,0);
		Coord empty = null;
		//find empty
		for(Coord c : nodes.keySet()) {
			if(nodes.get(c).y == 0) {
				empty = c;
				break;
			}
		}
		
		int emptyToTarget = distance(empty,target,nodes) - 1;
		int dataToDest = distance(target, new Coord(0,0), nodes) - 1;
		
		int total = emptyToTarget + 5 * dataToDest + 1;
		return Integer.toString(total);
	}

	public static int distance(Coord start, Coord end, HashMap<Coord,Coord> nodes) {
		HashMap<Coord,Integer> gScore = new HashMap<Coord,Integer>();
		gScore.put(start,0);
		PriorityQueue<Coord> queue = new PriorityQueue<Coord>(new Comparator<Coord>() {
			@Override
			public int compare(Coord o1, Coord o2) {
				return Integer.compare(gScore.getOrDefault(o1,0) + o1.dist(end), gScore.getOrDefault(o2,0) + o2.dist(end));
			}
		});
		queue.add(start);
		while(queue.size() > 0) {
			Coord cur = queue.poll();
			if(cur.equals(end)) {
				return gScore.get(cur);
			}
			for(Coord c : cur.directNeighbors()) {
				if(!nodes.containsKey(c) || nodes.get(c).x >= 100)
					continue;
				int tentativeG = gScore.get(cur) + 1;
				if(tentativeG < gScore.getOrDefault(c,Integer.MAX_VALUE)) {
					gScore.put(c,tentativeG);
					queue.add(c);
				}
			}
		}
		return -1;
	}

	public static void main(String[] args) {
		input = Input.fetchInput(2016,22);
		DayRunner.run(new Day22());
	}

}
