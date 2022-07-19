package advent.aoc2016;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

import advent.utilities.general.Coord;
import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.PathNode;

public class Day24 implements IDay {

	String input = "#####################################################################################################################################################################################\r\n"
			+ "#.....#.#.....#.#.#...#.....#.#.#.#.....#3......#...........#.#.....#.....#.............#.#...#...#.....#...#.........#.#...............#.....#.....#.........................#.....#\r\n"
			+ "#.#.#.#.#.#.#.#.#.#.#.#.###.#.#.#.#.#.#####.#.#.#.#####.#.###.#.#.###.#.#####.###.#.#.#.#.#####.#.#.#.#####.#.#.#####.#.#.#####.###.#.#.###.###.###.#.###.#.###.###.#.#######.#######\r\n"
			+ "#.....#...#.#.....#...#.........#...........#.......#...#.#.....#.....#.#.#.......#...#.#...#...#...#.#...#...#.#...#.#.#.#.......#...#...#.....#.....#.....#...#...#.......#.#...#.#\r\n"
			+ "###.#####.#.#######.###.#.#.#.#.#.#####.#.#.#.#####.#.###.#.#####.#.#.#.#.#####.#.#.#.#.#.###.#.###.#.#####.#.#.#.#.###.#.#.#.###.#.#.#.#.###.#.#.###.#.#.#.#.#.#.#.#.#.#####.#.#.#.#\r\n"
			+ "#...#1......#.....#.#.#.#.....#.#.#.....#...#.#...#.......#...........#.............#...#.....#.......#.....#.....#.#.......#.#...#.#.#.#.......#...#.#...........#.#.....#...#.#...#\r\n"
			+ "#.#.###.#.###.#.#.#.#.#.#.#######.#.#.###.#.#.#.###.#####.###.#.#.#.#.#.###.#.#####.#.#.#####.#####.#.#.#.#.#.#.#.###.###.#.#####.#.#.#####.#.#.#.#.#.#.#.###.#####.###.#.#.#.#.###.#\r\n"
			+ "#.....#.#.....#.#...#.#.....#.#.#.#.........#.#.#.......#.#.#.......#...#...#...#.....#.#...#.......#.....#.......#...#.....#...#...#.......#...#.#.....#...#.#.....#.#...#.#...#...#\r\n"
			+ "#.#.#.#.#####.#.###.#.#.#.###.#.###.#.#.#####.#.#####.#.#.#.#.#####.#.#.#.###.#######.#.###.#.###.#.#.#.#.#.#.#####.###.#####.#.#.#.#.#####.#####.#.###.#.#.#.#.#.#.#.#####.###.#.###\r\n"
			+ "#...#.#...#.........#.#...#.#...#.....#.#.....#.......#.........#.....#.....#.........#.....#...#.#.#.#.....#.#.................#.#.#.......#.......#.......#...#...#.......#.#...#.#\r\n"
			+ "#.#.#.#.#.#.#.###.#.###.###.#.#.#.###.###.#.#.#.#.#.#.#########.#.###.#.#.#####.#.#.#.###.#######.#.###.#.#.#.#.#.#.#.###.#.#.###.#######.#.###.#.#.#.#.###.#.#.#.#####.###.#.#.###.#\r\n"
			+ "#...#.#.#.#.#...#...#...#.............#.....#.....#...#...#.#.....#...#...#.....#.#.....#...#...........#.#.#.#.......#...#.............#...#.#...#...........#...#2#...#.....#.#.#.#\r\n"
			+ "###.###.#.#####.#.#.#.#.#.#.#.#.#.#.###.###.#.#.#.###.###.#.#.#.#.#.#.###.###.#.#.#.#.###.#.#.#.###.#####.###.###.#.#.#.###.#######.###.###.#.#.#.#####.#####.###.#.#.#####.#.#.#.###\r\n"
			+ "#.......#...#...........#.......#...#.#.......#.....#.....#...#...#.#.........#.......#...#.#...#...........#.#...#.#...............#.#.#.....#.......#.#.#.#.....#.........#.#.#.#.#\r\n"
			+ "###.#.#.#.#.#.#.#####.###.#.#.#.#.#.#.#.#.#.#.#.#.#.#.#####.#.###.#.###.#.#.#.#########.###.#.###.#.###.#.#.#.#.#######.#####.###.#.#.#.#.#####.#####.#.#.#.#.#.#######.#.###.#.#.#.#\r\n"
			+ "#...#.....#.#.............#...#.#.....#.#...#.......#.........#...#...#.#...#...........#...#...........#...#...#.................#...#.#.#.....#.......#.#.#.......#...#...#...#...#\r\n"
			+ "###.#.#######.#.#.#.###.#.#.#.###.#.#.#.#.#.###.#####.#.###.#.#.#.###.#.#.#.#.#.#.#.###.#.#.#.#.#.#.#####.#.#####.#.#.#.#.#.#######.#.#.#.###.#.#.#.#.#.###.#.###.#########.###.#.#.#\r\n"
			+ "#.....#...#.....#.....#...#.........#.#...#.....#...#.................#...#.#.#.......#.....#...#.#.................#.#.#.........#.....#...#.#...#.....#.................#.#.....#.#\r\n"
			+ "#.#####.#.#.#####.###.#.#.#.###.###.#.#########.#.#######.###.###.#.#.#.#.#.#.#.###.#.#.#####.#.###.#.#########.#.###.###.#.###.###.#.#.#.#.#.###.#####.#.#.#######.#.###.#.#.###.###\r\n"
			+ "#0#...#.#...#...........#.#.............#.#.......#.....#.#.#.....#.#.#...#.....#.#.#.......#.#...#.................#.#.#.....#...#.#.........#.......#.#...#.......#.......#.....#.#\r\n"
			+ "#####.#.###.#.#.###.#####.###.#######.###.#.#.###.#.#.#.#.#.#.#.#.###.#.#.#.#.#.#.#.#.#####.###.#.#####.#.#.#.###.#.#.#.#########.#.#.#.###.#.#.###.#.#####.#.#.#.#.#.###.###.#.#.#.#\r\n"
			+ "#...#.#.....#.#.#...#.......#.#.#...#.........#.......#...#.#.....#.............#...........#...#.#.......#.#.....#.#...........#...#.........#.#...#.#.....#.......#...#.......#...#\r\n"
			+ "###.###.###.#.###.###.#.###.###.#.#.#.#########.#####.###.#######.#.###.#.#.#.###.#.#.#.#.#.#.#.#####.###.#.#.#.#.#.#.#####.###.#.#.###.#####.#.#.#####.#.#.###.#.#####.#.#.#.#.#.#.#\r\n"
			+ "#.......#...............#.....#...#...#.#...#.........#...........#.....#.....#.......#...#.....#.......#.#.#.#...#...............#.....#.....#...#.#...#...#.#.#.#.#.....#...#.#.#4#\r\n"
			+ "#.#.#.#.#.#####.###.#.#####.#.###.#.###.#.#.#.###.###.#.#####.#.#####.#.#######.#.#####.#.#######.#.###.#.#.#.###.###.###.#####.#.###.#.#.#.#####.#.#.#.###.#.#.###.###.#.#.#.#.#.###\r\n"
			+ "#...#...........#.......#...#.....#.#.......#.....#.........#.#.......#.#...#...#...........#.......#.....#...#...#...#.#...#.....#...#.........#...#...#.....#.....#...#...#...#...#\r\n"
			+ "###.#.###.#############.#.###.###.#.###.#.#.#.#.#.#.#.#.###.#####.#.#.#.#.#.#####.###.#.#.#.#.###.#.#.#.#.#.###.#.#.###.#.#.#.###.###.#.#.###.#.#.#.#.#.#.#####.#.#.#.#.#.#.###.#.###\r\n"
			+ "#...#.....#.#...#.#.#.#...........#.......#...#.....#...#...................#...#...#.#.#.#...#.......#...............#...#...#...#...#.#.#5#...#...#.#...#...#.#...#.#...#...#.#...#\r\n"
			+ "#.###.###.#.#.#.#.#.#.###.#.#######.#.#.#.###.#.#.#.#.#.#.###.#.###.###.#.#.#.#.#.###.#.#.#.#.#.###.#.#.###.#.#.###.#.#.#.#.#.#.#.#.#####.#.#.#######.#.#.#.#.#.#####.#.#######.#.#.#\r\n"
			+ "#.........#.#...#.#...#...#.............#.....#...#.#.#.#.#.#.....#.#...#.....#.#.#.........#.....#.........#.....#...........#...#.........#.....#...#.#.#.#...#...........#...#...#\r\n"
			+ "#.#.#.#######.#.#####.#.#.#.###.#.#######.#####.#.#.###.#.#.#.###.###.#.###.#.#.#.###.#.#.#.#####.#.###.#.#.#.#.#.#.#####.#.#.###.#.#.#.#.#####.###.#####.#.#.#.#.###.#.#.#####.#####\r\n"
			+ "#...#...............#.#.......#.......#.......#.........#.#.#.#...#...#...#.......#.#.....#.#...........#...#.#.#...........#.#...#.......#.........#...#...#.....#...#.#.....#.#...#\r\n"
			+ "###.#.#.#.#.#########.#.#.#.#.#.#####.#.#######.#.###.#.#.#.###.###.#.#.#.#.#.###.#.#.###.#.#####.#.###########.#.#.#####.#####.#####.#####.#.#.#.#.#.#.#####.#.#.#########.#.###.#.#\r\n"
			+ "#.#...#.....#.......#.....#...#.......#.#.#...#...#...#.........#...#...#.#...#.#.........#.#.......#.#.#...#...#.........#.#.........#.....#.#.#...#.......#.#.....#.......#.#.....#\r\n"
			+ "#.#.###.#.###.#.#.#.#.###.#########.#.#.#.#.#.#.###.#.#.#####.#.#.#####.#####.#.#.#########.#.#.#.###.#.#####.#.#.###.###.#.#.#.#.#.#####.###.#.###########.#.#.#.#.#.#######.#####.#\r\n"
			+ "#........7#.....#.#...#.#.#.#.........#...#.#...........#.....#.......#.........#.........#.....#.......#.#.......#.#.#...#...#.#.#....6#.#.........................#...#.#.......#.#\r\n"
			+ "#.###.#.###.###.###.#.#.###.###.#####.###.#.#.###.#####.###.#.###.#.#.#.#.#.#.#.#.#####.#.#.#####.#######.#.###.#.#.#####.#####.#.#.#.###.#.#.#.#.#####.#.#.###.#####.#.#.#.#.#.#.#.#\r\n"
			+ "#.#...#.#...#.#.....#.#.......#...#.....#...#...#...#...#.#.....#...#.#...#...#...#.#.............#.............#.#...#.............#.#.....#.....#.......#.#.#.#.........#...#...#.#\r\n"
			+ "#####################################################################################################################################################################################";
	
	static HashSet<Coord> walls;
	
	static int shortest;
	
	@Override
	public String part1() {
		//pre-split input to get x and y bounds
		String[] lines = input.split("\r\n");
		//store walls as coord hash set (x,y)
		//store points of interest as coord arraylist (x,y)
		walls = new HashSet<Coord>();
		ArrayList<Coord> locations = new ArrayList<Coord>();
		
		//store starting point ('0') coord
		Coord start = null;
		
		for(int y = 0; y < lines.length; y++) {
			for(int x = 0; x < lines[0].length(); x++) {
				if(lines[y].charAt(x) == '#')
					walls.add(new Coord(x,y));
				else if(lines[y].charAt(x) != '.') {
					if(lines[y].charAt(x) == '0')
						start = new Coord(x,y);
					else
						locations.add(new Coord(x,y));
				}
					
			}
		}
		
		int[] permBase = new int[locations.size()];
		for(int i = 0; i < locations.size(); i++)
			permBase[i] = i;
		ArrayList<int[]> perms = perms(permBase,permBase.length);
		//go through all permutations of location visit order, find shortest
		shortest = Integer.MAX_VALUE;
		
		//memoize distances between points to avoid recalculation
		//coord - integer hashmap : (location index, location index) -> distance
		HashMap<Coord,Integer> distances = new HashMap<Coord,Integer>();
		permLoop:
		for(int[] perm : perms) {
			//pathfind from start to first location
			int distance = 0;
			//use -1 to represent start
			Coord initial = new Coord(-1,perm[0]);
			if(distances.containsKey(initial))
				distance += distances.get(initial);
			else {
				int firstDistance = distance(start, locations.get(perm[0]));
				distance += firstDistance;
				distances.put(new Coord(-1,perm[0]), firstDistance);
			}
			//pathfind between remaining permutation locations
			for(int i = 0; i < perm.length - 1; i++) {
				Coord next = new Coord(perm[i], perm[i+1]);
				if(distances.containsKey(next)) {
					distance += distances.get(next);
				} else {
					int newDistance =  distance(locations.get(perm[i]), locations.get(perm[i+1]));
					distance += newDistance;
					distances.put(next, newDistance);
				}
				//break if distance is already too high
				if(distance > shortest)
					continue permLoop;
			}
			if(distance < shortest)
				shortest = distance;
		}
		return Integer.toString(shortest);
	}
	
	
	//BFS pathfinding
	public int distance(Coord start, Coord end) {
		ArrayList<PathNode> open = new ArrayList<PathNode>();
		HashSet<PathNode> closed = new HashSet<PathNode>();
		open.add(new PathNode(start,0,0));
		while(open.size() > 0) {
			PathNode cur = open.remove(0);
			Coord curPos = cur.pos;
			if(curPos.equals(end))
				return cur.gcost;
			if(cur.gcost > shortest)
				return 100000;
			for(Coord c : curPos.directNeighbors()) {
				if(!walls.contains(c)) {
					PathNode newNode = new PathNode(c,cur.gcost + 1, 0);
					if(!closed.contains(newNode) && !open.contains(newNode)) {
						open.add(newNode);
					}
				}
			}
			closed.add(cur);
		}
		return -1;
	}
	
	//Heap's permutation generation algorithm. look it up on wikipedia if you need to
	public static ArrayList<int[]> perms(int[] a, int n) {
		ArrayList<int[]> perms = new ArrayList<int[]>();
		if(n == 1) {
			perms.add(Arrays.copyOf(a, a.length));
			return perms;
		}
		
		perms.addAll(perms(a,n-1));
		for(int i = 0; i < n-1; i++) {
			if(n % 2 == 0) {
				int temp = a[i];
				a[i] = a[n-1];
				a[n-1] = temp;
			} else {
				int temp = a[0];
				a[0] = a[n-1];
				a[n-1] = temp;
			}
			perms.addAll(perms(a,n-1));
		}
		return perms;
	}

	@Override
	public String part2() {
		//pre-split input to get x and y bounds
		String[] lines = input.split("\r\n");
		//store walls as coord hash set (x,y)
		//store points of interest as coord arraylist (x,y)
		walls = new HashSet<Coord>();
		ArrayList<Coord> locations = new ArrayList<Coord>();
		
		//store starting point ('0') coord
		Coord start = null;
		
		for(int y = 0; y < lines.length; y++) {
			for(int x = 0; x < lines[0].length(); x++) {
				if(lines[y].charAt(x) == '#')
					walls.add(new Coord(x,y));
				else if(lines[y].charAt(x) != '.') {
					if(lines[y].charAt(x) == '0')
						start = new Coord(x,y);
					else
						locations.add(new Coord(x,y));
				}
					
			}
		}
		
		int[] permBase = new int[locations.size()];
		for(int i = 0; i < locations.size(); i++)
			permBase[i] = i;
		ArrayList<int[]> perms = perms(permBase,permBase.length);
		//go through all permutations of location visit order, find shortest
		shortest = Integer.MAX_VALUE;
		
		//memoize distances between points to avoid recalculation
		//coord - integer hashmap : (location index, location index) -> distance
		HashMap<Coord,Integer> distances = new HashMap<Coord,Integer>();
		permLoop:
		for(int[] perm : perms) {
			//pathfind from start to first location
			int distance = 0;
			//use -1 to represent start
			Coord initial = new Coord(-1,perm[0]);
			if(distances.containsKey(initial))
				distance += distances.get(initial);
			else {
				int firstDistance = distance(start, locations.get(perm[0]));
				distance += firstDistance;
				distances.put(new Coord(-1,perm[0]), firstDistance);
			}
			//pathfind between remaining permutation locations
			for(int i = 0; i < perm.length - 1; i++) {
				Coord next = new Coord(perm[i], perm[i+1]);
				if(distances.containsKey(next)) {
					distance += distances.get(next);
				} else {
					int newDistance =  distance(locations.get(perm[i]), locations.get(perm[i+1]));
					distance += newDistance;
					distances.put(next, newDistance);
				}
				//break if distance is already too high
				if(distance > shortest)
					continue permLoop;
			}
			//part 2 modification - return to start
			Coord back = new Coord(perm[perm.length - 1], -1);
			if(distances.containsKey(back)) {
				distance += distances.get(back);
			} else {
				int backDistance = distance(locations.get(perm[perm.length - 1]),start);
				distance += backDistance;
				distances.put(back, backDistance);
			}
			
			if(distance < shortest)
				shortest = distance;
		}
		return Integer.toString(shortest);
	}

	public static void main(String[] args) {
		DayRunner.run(new Day24());
	}

}
