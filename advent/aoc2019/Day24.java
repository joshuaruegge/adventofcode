package advent.aoc2019;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

import advent.utilities.general.Coord;
import advent.utilities.general.Coord3;
import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;

public class Day24 implements IDay {

	static String input = ".###.\r\n"
			+ "##...\r\n"
			+ "...##\r\n"
			+ ".#.#.\r\n"
			+ "#.#.#";
	static String input2 = "....#\r\n"
			+ "#..#.\r\n"
			+ "#..##\r\n"
			+ "..#..\r\n"
			+ "#....";
	
	static ArrayList<ArrayList<Integer>> map;
	
	static HashSet<Coord> bugPositions;
	static HashSet<Coord3> bugPositions2;
	
	static int minDepth = 0;
	static int maxDepth = 0;
	
	
	public static void main(String[] args) {
		DayRunner.run(new Day24());
	}
	
	//"score" an array for part 1
	public static long total(HashSet<Coord> a) {		
		long total = 0;
		int index = 0;
		for(int y = 0; y < 5; y++) {
			for(int x = 0; x < 5; x++) {
				if(a.contains(new Coord(x,y))) {
					total += Math.pow(2,index);
				}
				index++;
			}
		}
		return total;
	}
	
	//calculate the number of bugs adjacent to a tile for part 1
	public static int neighbors(int x, int y) {
		int count = 0;
		for(int xOff = -1; xOff < 2; xOff++) {
			for(int yOff = -1; yOff < 2; yOff++) {
				if(xOff == 0 ^ yOff == 0) {
					Coord neighbor = new Coord(x+xOff,y+yOff);
					if(bugPositions.contains(neighbor)) {
						count++;
					}
				}
			}
		}
		return count;
	}
	
	//create a copy of bugPositions
	public static HashSet<Coord> copyMap() {
		HashSet<Coord> temp = new HashSet<Coord>();
		for(Coord c : bugPositions) {
			temp.add(c);
		}
		return temp;
	}
	
	//calculate neighbors to a tile for part 2
	public static int neighbors2(Coord3 c) {
		int count = 0;
		for(int xOff = -1; xOff < 2; xOff++) {
			for(int yOff = -1; yOff < 2; yOff++) {
				if(xOff == 0 ^ yOff == 0) {
					Coord3 neighbor = new Coord3(c.x + xOff,c.y + yOff, c.z);
					if(bugPositions2.contains(neighbor))
						count++;
				}
			}
		}
		//special cases - tiles that are affected by recursive depth
		//if tile has neighbors a recursive depth up
		if(c.x == 0) {
			if(bugPositions2.contains(new Coord3(1,2,c.z - 1)))
				count++;
		}
		if(c.y == 0) {
			if(bugPositions2.contains(new Coord3(2,1,c.z - 1)))
				count++;
		}
		if(c.x == 4) {
			if(bugPositions2.contains(new Coord3(3,2,c.z - 1))) {
				count++;
			}
		}
		if(c.y == 4) {
			if(bugPositions2.contains(new Coord3(2,3,c.z - 1))) {
				count++;
			}
		}
		//if tile has neighbors a recursive depth down
		if(c.x == 1 && c.y == 2) {
			for(int y = 0; y < 5; y++) {
				if(bugPositions2.contains(new Coord3(0,y,c.z + 1)))
					count++;
			}
		}
		if(c.x == 3 && c.y == 2) {
			for(int y = 0; y < 5; y++) {
				if(bugPositions2.contains(new Coord3(4,y,c.z + 1)))
					count++;
			}
		}
		if(c.x == 2 & c.y == 1) {
			for(int x = 0; x < 5; x++) {
				if(bugPositions2.contains(new Coord3(x,0,c.z + 1)))
					count++;
			}
		}
		if(c.x == 2 && c.y == 3) {
			for(int x = 0; x < 5; x++) {
				if(bugPositions2.contains(new Coord3(x,4,c.z + 1)))
					count++;
			}
		}
		return count;
	}

	@Override
	public String part1() {
		//parse input into two-dimensional int array list
		Scanner scan = new Scanner(input);
		map = new ArrayList<ArrayList<Integer>>();
		while(scan.hasNextLine()) {
			ArrayList<Integer> row = new ArrayList<Integer>();
			String line = scan.nextLine();
			for(char c : line.toCharArray()) {
				row.add((c == '.' ? 0 : 1));
			}
			map.add(row);
		}
		
		//we can track states by just keeping track of bug positions (locations not in the array are automatically empty)
		bugPositions = new HashSet<Coord>();
		for(int y = 0; y < 5; y++) {
			for(int x = 0; x < 5; x++) {
				if(map.get(y).get(x) == 1) {
					bugPositions.add(new Coord(x,y));
				}
			}
 		}
		
		//keep track of all seen states to check for first that appears twice
		HashSet<HashSet<Coord>> seenStates = new HashSet<HashSet<Coord>>();
		seenStates.add(copyMap());
		while(true) {
			//update bug positions to new array
			HashSet<Coord> newBugPos = new HashSet<Coord>();
			for(int y = 0; y < 5; y++) {
				for(int x = 0; x < 5; x++) {
					Coord cur = new Coord(x,y);
					if(bugPositions.contains(cur)) {
						if(neighbors(x,y) == 1) {
							newBugPos.add(cur);
						}
					} else {
						if(neighbors(x,y) == 1 || neighbors(x,y) == 2) {
							newBugPos.add(cur);
						}
					}
				}
			}
			//if array is seen twice, "score" it and return value
			if(seenStates.contains(newBugPos)) {
				return Long.toString(total(newBugPos));
			}
			//update array to new values, add to seenstates list
			bugPositions = newBugPos;
			seenStates.add(copyMap());
		}
	}

	@Override
	public String part2() {
		//now, keep track of bug positions as 3 dimensional coordinate, with Z representing recursive depth
		bugPositions2 = new HashSet<Coord3>();
		for(int y = 0; y < 5; y++) {
			for(int x = 0; x < 5; x++) {
				if(map.get(y).get(x) == 1) {
					bugPositions2.add(new Coord3(x,y,0));
				}
			}
		}
		
		
		for(int i = 0; i < 200; i++) {
			//same logic as before - update new positions to new array
			HashSet<Coord3> newBugPos = new HashSet<Coord3>();
			for(int z = minDepth; z <= maxDepth; z++) {
				for(int y = 0; y < 5; y++) {
					for(int x = 0; x < 5; x++) {
						//just in case, skip (2,2), as this position is occupied by the recursion
						if(x == 2 && y == 2)
							continue;
						Coord3 cur = new Coord3(x,y,z);
						if(bugPositions2.contains(cur)) {
							if(neighbors2(cur) == 1)  {
								newBugPos.add(cur);
							}
						} else {
							if(neighbors2(cur) == 1 || neighbors2(cur) == 2) {
								newBugPos.add(cur);
							}
						}
					}
				}
			}
			int newMinDepth = minDepth;
			int newMaxDepth = maxDepth;
			//check cases that would expand recursive depth
			//left side, min depth
			int count = 0;
			for(int y = 0; y < 5; y++) {
				if(bugPositions2.contains(new Coord3(0,y,minDepth)))
					count++;
			}
			if(count == 1 || count == 2) {
				if(newMinDepth == minDepth)
					newMinDepth--;
				newBugPos.add(new Coord3(1,2,newMinDepth));
			}
			//top side, min depth
			count = 0;
			for(int x = 0; x < 5; x++) {
				if(bugPositions2.contains(new Coord3(x,0,minDepth))) {
					count++;
				}
			}
			if(count == 1 || count == 2) {
				if(newMinDepth == minDepth)
					newMinDepth--;
				newBugPos.add(new Coord3(2,1,newMinDepth));
			}
			//right side, min depth
			count = 0;
			for(int y = 0; y < 5; y++) {
				if(bugPositions2.contains(new Coord3(4,y,minDepth)))
					count++;
			}
			if(count == 1 || count == 2) {
				if(newMinDepth == minDepth)
					newMinDepth--;
				newBugPos.add(new Coord3(3,2,newMinDepth));
			}
			//bottom side, min depth
			count = 0;
			for(int x = 0; x < 5; x++) {
				if(bugPositions2.contains(new Coord3(x,4,minDepth))) {
					count++;
				}
			}
			if(count == 1 || count == 2) {
				if(newMinDepth == minDepth)
					newMinDepth--;
				newBugPos.add(new Coord3(2,3,newMinDepth));
			}
			//left middle, max depth
			if(bugPositions2.contains(new Coord3(1,2,maxDepth))) {
				if(newMaxDepth == maxDepth)
					newMaxDepth++;
				for(int y = 0; y < 5; y++) {
					Coord3 next = new Coord3(0,y,newMaxDepth);
					if(!newBugPos.contains(next))
						newBugPos.add(next);
				}
			}
			//top middle, max depth
			if(bugPositions2.contains(new Coord3(2,1,maxDepth))) {
				if(newMaxDepth == maxDepth)
					newMaxDepth++;
				for(int x = 0; x < 5; x++) {
					Coord3 next = new Coord3(x,0,newMaxDepth);
					if(!newBugPos.contains(next)) {
						newBugPos.add(next);
					}
					
				}
			}
			//right middle, max depth
			if(bugPositions2.contains(new Coord3(3,2,maxDepth))) {
				if(newMaxDepth == maxDepth)
					newMaxDepth++;
				for(int y = 0; y < 5; y++) {
					Coord3 next = new Coord3(4,y,newMaxDepth);
					if(!newBugPos.contains(next))
						newBugPos.add(next);
				}
			}
			//bottom middle, max depth
			if(bugPositions2.contains(new Coord3(2,3,maxDepth))) {
				if(newMaxDepth == maxDepth)
					newMaxDepth++;
				for(int x = 0; x < 5; x++) {
					Coord3 next = new Coord3(x,4,newMaxDepth);
					if(!newBugPos.contains(next)) {
						newBugPos.add(next);
					}
					
				}
			}
			
			//update values
			maxDepth = newMaxDepth;
			minDepth = newMinDepth;
			bugPositions2 = newBugPos;
		}
		return Integer.toString(bugPositions2.size());
	}
}
