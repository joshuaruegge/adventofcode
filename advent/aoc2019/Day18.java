package advent.aoc2019;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

import advent.utilities.general.Coord;
import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;
import advent.utilities.utils2019.State;

public class Day18 implements IDay {

	static String input;
	
	static HashSet<Coord> walls = new HashSet<Coord>();
	
	static ArrayList<Coord> keys = new ArrayList<Coord>();
	static HashMap<Coord,Coord> doors;
	
	static HashMap<ArrayList<Coord>, ArrayList<Integer>> keyDistances;
	
	static int ALL_KEYS;
	
	static int worldBest = Integer.MAX_VALUE;

	@Override
	public String part1() {
		//parse input. both doors and keys are added into the doorsAndKeysTemp map, which stores the location and its original character
		int inY = 0;
		HashMap<Coord,Character> doorsAndKeysTemp = new HashMap<Coord,Character>();
		for(String line : input.split("\n")) {
			for(int i = 0; i < line.length(); i++) {
				if(line.charAt(i) == '.') {
					continue;
				} else if(line.charAt(i) == '#') {
					walls.add(new Coord(i,inY));
				} else {
					doorsAndKeysTemp.put(new Coord(i,inY), line.charAt(i));
				}
			}
			inY++;
		}
		//parses doorsAndKeysTemp into separate doors and keys arrays
		Coord startLocation = new Coord();
		doors = new HashMap<Coord,Coord>();
		for(Coord c : doorsAndKeysTemp.keySet()) {
			if(doorsAndKeysTemp.get(c) == '@') {
				startLocation = c;
			} else if(doorsAndKeysTemp.get(c) > 90) {
				keys.add(c);
			} else {
				doors.put(c, new Coord());
			}
		}
		
		//for each door, locate its key and put it into the map
		for(Coord c : doors.keySet()) {
			char key = Character.toLowerCase(doorsAndKeysTemp.get(c));
			Coord keyLoc = null;
			for(Coord d : doorsAndKeysTemp.keySet()) {
				if(doorsAndKeysTemp.get(d) == key) {
					keyLoc = d;
					break;
				}
			}
			if(keyLoc == null) {
				System.out.println("Error!");
				System.exit(0);
			} else {
				doors.put(c, keyLoc);
			}
		}
		//this sets ALL_KEYS to the bitmask representing all keys collected
		ALL_KEYS = (1 << keys.size()) - 1;
		
		//initialize path result cache
		keyDistances = new HashMap<ArrayList<Coord>, ArrayList<Integer>>();
		
		//return most efficient path through map
		return Integer.toString(distanceToCollectKeys(new State(startLocation,0,0), new HashMap<State,Integer>()));
	}

	@Override
	public String part2() {
		//for this one, we use a bit of a cheaty solution
		//basically, we chop the maze into 4 parts, and pathfind through each maze individually
		//HOWEVER, we remove any doors whose keys aren't in that part of the maze, because
		//we assume the other paths will collect those keys on their best path
		//so, we only need to worry about our own shortest path to collect all our keys
		int totalDistance = 0;
		
		String[] lines = input.split("\n");
		int yMid = lines.length / 2;
		int xMid = lines[0].length() / 2;
		//initialize top left maze
		ArrayList<String> topLeft = new ArrayList<String>();
		for(int i = 0; i <= yMid; i++) {
			topLeft.add(lines[i].substring(0,xMid+1));
		}
		//replace with new bot
		topLeft.set(topLeft.size() - 2, topLeft.get(topLeft.size() - 2).substring(0,xMid-1) + "@#");
		topLeft.set(topLeft.size() - 1, topLeft.get(topLeft.size() - 1).substring(0,xMid-1) + "##");
		//remove doors that don't have keys in maze
		for(int i = 0; i < topLeft.size(); i++) {
			String line = topLeft.get(i);
			for(int j = 0; j < line.length(); j++) {
				char c = line.charAt(j);
				if(c > 64 && c < 91) {
					//if map does not contain door key, replace with path
					if(!contains(topLeft,Character.toLowerCase(c))) {
						line = line.substring(0,j) + "." + line.substring(j+1);
						topLeft.set(i, line);
					}
				}
			}
		}
		//iterate through topLeft, set walls, doors, etc.
		walls.clear();
		doors.clear();
		keys.clear();
		keyDistances.clear();
		
		HashMap<Coord,Character> doorsAndKeysTemp = new HashMap<Coord,Character>();
		for(int y = 0; y < topLeft.size(); y++) {
			String line = topLeft.get(y);
			for(int x = 0; x < line.length(); x++) {
				if(line.charAt(x) == '.') {
					continue;
				} else if(line.charAt(x) == '#') {
					walls.add(new Coord(x,y));
				} else {
					doorsAndKeysTemp.put(new Coord(x,y), line.charAt(x));
				}
			}
		}

		Coord startLocation = new Coord();
		for(Coord c : doorsAndKeysTemp.keySet()) {
			if(doorsAndKeysTemp.get(c) == '@') {
				startLocation = c;
			} else if(doorsAndKeysTemp.get(c) > 90) {
				keys.add(c);
			} else {
				doors.put(c, new Coord());
			}
		}
		
		for(Coord c : doors.keySet()) {
			char key = Character.toLowerCase(doorsAndKeysTemp.get(c));
			Coord keyLoc = null;
			for(Coord d : doorsAndKeysTemp.keySet()) {
				if(doorsAndKeysTemp.get(d) == key) {
					keyLoc = d;
					break;
				}
			}
			if(keyLoc == null) {
				System.out.println("Error!");
				System.exit(0);
			} else {
				doors.put(c, keyLoc);
			}
		}
		ALL_KEYS = (1 << keys.size()) - 1;
		
		keyDistances = new HashMap<ArrayList<Coord>, ArrayList<Integer>>();
		
		worldBest = Integer.MAX_VALUE;
		//pathfind, add to total distance
		totalDistance += distanceToCollectKeys(new State(startLocation,0,0),new HashMap<State,Integer>());
		
		//repeat for topRight
		ArrayList<String> topRight = new ArrayList<String>();
		for(int i = 0; i <= yMid; i++) {
			topRight.add(lines[i].substring(xMid));
		}
		topRight.set(topRight.size() - 2, "#@" + topRight.get(topRight.size() - 2).substring(2));
		topRight.set(topRight.size() - 1, "##" + topRight.get(topRight.size() - 1).substring(2));
		
		for(int i = 0; i < topRight.size(); i++) {
			String line = topRight.get(i);
			for(int j = 0; j < line.length(); j++) {
				char c = line.charAt(j);
				if(c > 64 && c < 91) {
					//if map does not contain door key
					if(!contains(topRight,Character.toLowerCase(c))) {
						line = line.substring(0,j) + "." + line.substring(j+1);
						topRight.set(i, line);
					}
				}
			}
		}
		
		walls.clear();
		doors.clear();
		keys.clear();
		keyDistances.clear();
		
		doorsAndKeysTemp = new HashMap<Coord,Character>();
		for(int y = 0; y < topRight.size(); y++) {
			String line = topRight.get(y);
			for(int x = 0; x < line.length(); x++) {
				if(line.charAt(x) == '.') {
					continue;
				} else if(line.charAt(x) == '#') {
					walls.add(new Coord(x,y));
				} else {
					doorsAndKeysTemp.put(new Coord(x,y), line.charAt(x));
				}
			}
		}
		
		startLocation = new Coord();
		for(Coord c : doorsAndKeysTemp.keySet()) {
			if(doorsAndKeysTemp.get(c) == '@') {
				startLocation = c;
			} else if(doorsAndKeysTemp.get(c) > 90) {
				keys.add(c);
			} else {
				doors.put(c, new Coord());
			}
		}
		
		for(Coord c : doors.keySet()) {
			char key = Character.toLowerCase(doorsAndKeysTemp.get(c));
			Coord keyLoc = null;
			for(Coord d : doorsAndKeysTemp.keySet()) {
				if(doorsAndKeysTemp.get(d) == key) {
					keyLoc = d;
					break;
				}
			}
			if(keyLoc == null) {
				System.out.println("Error!");
				System.exit(0);
			} else {
				doors.put(c, keyLoc);
			}
		}
		ALL_KEYS = (1 << keys.size()) - 1;
		
		keyDistances = new HashMap<ArrayList<Coord>, ArrayList<Integer>>();
		
		worldBest = Integer.MAX_VALUE;
		
		totalDistance += distanceToCollectKeys(new State(startLocation,0,0),new HashMap<State,Integer>());
		
		//repeat for bottomLeft
		ArrayList<String> bottomLeft = new ArrayList<String>();
		for(int i = yMid; i < lines.length; i++) {
			bottomLeft.add(lines[i].substring(0,xMid+1));
		}
		bottomLeft.set(0, bottomLeft.get(0).substring(0,bottomLeft.get(0).length() - 2) + "##");
		bottomLeft.set(1, bottomLeft.get(1).substring(0,bottomLeft.get(1).length() - 2) + "@#");
		
		for(int i = 0; i < bottomLeft.size(); i++) {
			String line = bottomLeft.get(i);
			for(int j = 0; j < line.length(); j++) {
				char c = line.charAt(j);
				if(c > 64 && c < 91) {
					//if map does not contain door key
					if(!contains(bottomLeft,Character.toLowerCase(c))) {
						line = line.substring(0,j) + "." + line.substring(j+1);
						bottomLeft.set(i, line);
					}
				}
			}
		}
	
		walls.clear();
		doors.clear();
		keys.clear();
		keyDistances.clear();
		
		doorsAndKeysTemp = new HashMap<Coord,Character>();
		for(int y = 0; y < bottomLeft.size(); y++) {
			String line = bottomLeft.get(y);
			for(int x = 0; x < line.length(); x++) {
				if(line.charAt(x) == '.') {
					continue;
				} else if(line.charAt(x) == '#') {
					walls.add(new Coord(x,y));
				} else {
					doorsAndKeysTemp.put(new Coord(x,y), line.charAt(x));
				}
			}
		}
		
		startLocation = new Coord();
		for(Coord c : doorsAndKeysTemp.keySet()) {
			if(doorsAndKeysTemp.get(c) == '@') {
				startLocation = c;
			} else if(doorsAndKeysTemp.get(c) > 90) {
				keys.add(c);
			} else {
				doors.put(c, new Coord());
			}
		}
		
		for(Coord c : doors.keySet()) {
			char key = Character.toLowerCase(doorsAndKeysTemp.get(c));
			Coord keyLoc = null;
			for(Coord d : doorsAndKeysTemp.keySet()) {
				if(doorsAndKeysTemp.get(d) == key) {
					keyLoc = d;
					break;
				}
			}
			if(keyLoc == null) {
				System.out.println("Error!");
				System.exit(0);
			} else {
				doors.put(c, keyLoc);
			}
		}
		ALL_KEYS = (1 << keys.size()) - 1;
		
		keyDistances = new HashMap<ArrayList<Coord>, ArrayList<Integer>>();
		
		worldBest = Integer.MAX_VALUE;
		
		totalDistance += distanceToCollectKeys(new State(startLocation,0,0),new HashMap<State,Integer>());
		
		
		//repeat for bottomRight
		ArrayList<String> bottomRight = new ArrayList<String>();
		
		for(int i = yMid; i < lines.length; i++) {
			bottomRight.add(lines[i].substring(xMid));
		}
		
		for(int i = 0; i < bottomRight.size(); i++) {
			String line = bottomRight.get(i);
			for(int j = 0; j < line.length(); j++) {
				char c = line.charAt(j);
				if(c > 64 && c < 91) {
					//if map does not contain door key
					if(!contains(bottomRight,Character.toLowerCase(c))) {
						line = line.substring(0,j) + "." + line.substring(j+1);
						bottomRight.set(i, line);
					}
				}
			}
		}
	
		bottomRight.set(0, "##" + bottomRight.get(0).substring(2));
		bottomRight.set(1, "#@" + bottomRight.get(1).substring(2));
		
		walls.clear();
		doors.clear();
		keys.clear();
		keyDistances.clear();
		
		doorsAndKeysTemp = new HashMap<Coord,Character>();
		for(int y = 0; y < bottomRight.size(); y++) {
			String line = bottomRight.get(y);
			for(int x = 0; x < line.length(); x++) {
				if(line.charAt(x) == '.') {
					continue;
				} else if(line.charAt(x) == '#') {
					walls.add(new Coord(x,y));
				} else {
					doorsAndKeysTemp.put(new Coord(x,y), line.charAt(x));
				}
			}
		}
		
		startLocation = new Coord();
		for(Coord c : doorsAndKeysTemp.keySet()) {
			if(doorsAndKeysTemp.get(c) == '@') {
				startLocation = c;
			} else if(doorsAndKeysTemp.get(c) > 90) {
				keys.add(c);
			} else {
				doors.put(c, new Coord());
			}
		}
		
		for(Coord c : doors.keySet()) {
			char key = Character.toLowerCase(doorsAndKeysTemp.get(c));
			Coord keyLoc = null;
			for(Coord d : doorsAndKeysTemp.keySet()) {
				if(doorsAndKeysTemp.get(d) == key) {
					keyLoc = d;
					break;
				}
			}
			if(keyLoc == null) {
				System.out.println("Error!");
				System.exit(0);
			} else {
				doors.put(c, keyLoc);
			}
		}
		ALL_KEYS = (1 << keys.size()) - 1;
		
		keyDistances = new HashMap<ArrayList<Coord>, ArrayList<Integer>>();
		
		worldBest = Integer.MAX_VALUE;
		
		totalDistance += distanceToCollectKeys(new State(startLocation,0,0),new HashMap<State,Integer>());
		
		//return total distance. easy!
		return Integer.toString(totalDistance);
	}

	//recursively calculate best key path (dijkstra-like, but closer to brute force)
	public static int distanceToCollectKeys(State cur, HashMap<State,Integer> cache) {
		//if all keys have been collected, we're done!!
		if(cur.keys == ALL_KEYS) {
			if(cur.dist < worldBest)
				worldBest = cur.dist;
			return 0;
		}
		//if we're already worse than the best rolling solution, we can trim here
		if(cur.dist > worldBest || cur.dist >= 1000000)
			return 1000000;
		//if we have this call cached, we can return it
		if(cache.containsKey(cur)) {
			return cache.get(cur);
		}
		//this value was originally Integer.MAX_VALUE, but turns out that doesn't play nice when you add numbers to it
		int result = 1000000;
		//iterate over possible keys
		for(int i = 0; i < keys.size(); i++) {
			//if key is not held
			//bitmask trick: (1 << i) will produce the equivalent to having key i collected
			//so, performing bitwise and of curKeys and (1 << i) will return (1 << i) if that key is in curKeys, and zero if not
			//ex: (100110) & (000100) == (000100)
			//but (100110) & (010000) == 0
			if((cur.keys & (1 << i)) != (1 << i)) {
				//initialize return value of path (2-item array list, distance and keys required for path)
				ArrayList<Integer> distAndKeys;
				//initialize and create keyDistance key (2-item array list, start and end)
				ArrayList<Coord> key = new ArrayList<Coord>();
				key.add(cur.position);
				key.add(keys.get(i));
				//if we have this value cached, return it
				if(keyDistances.containsKey(key)) {
					distAndKeys = keyDistances.get(key);
				} else {
					//calculate and cache result
					distAndKeys = path(cur.position,keys.get(i),cur.keys);
					keyDistances.put(key, distAndKeys);
				}
				//unreachable, continue
				if(distAndKeys == null)
					continue;
				//already too large, continue
				if(distAndKeys.get(0) > result)
					continue;
				if(!((cur.keys & distAndKeys.get(1)) == distAndKeys.get(1))) {
					//not enough keys, continue
					continue;
				}
				
				//create new state starting from newly-grabbed key, with newly-grabbed key added to key bitmask, and with new distance
				//(cur.keys | (1 << i) inserts the bit for key i to the current key bitmask)
				State newState = new State(keys.get(i),(cur.keys | (1 << i)),cur.dist + distAndKeys.get(0));
				//distance is this new state plus recursive call for best distance of all remaining keys
				int dist = distAndKeys.get(0) + distanceToCollectKeys(newState,cache);
				//if distance is better than current result, replace
				if(dist < result)
					result = dist;
			}
		}
		//cache and return result
		cache.put(cur,result);
		return result;
	}
	
	//returns a 2-item array list: the first item is the distance between the points
	//and the second item is the bitmask of keys required to traverse the path (i.e., doors along the path)
	public static ArrayList<Integer> path(Coord start, Coord end, int curKeys) {
		//starting keys required (none)
		int keysRequired = 0; 
		HashMap<Coord,Integer> gScore = new HashMap<Coord,Integer>();
		gScore.put(start, 0);
		HashMap<Coord,Coord> parent = new HashMap<Coord,Coord>();
		LinkedList<Coord> queue = new LinkedList<Coord>();
		
		queue.add(start);
		
		ArrayList<Coord> path = null;
		while(queue.size() > 0) {
			Coord cur = queue.poll();
			if(cur.equals(end)) {
				path = new ArrayList<Coord>();
				while(parent.containsKey(cur)) {
					path.add(cur.copy());
					cur = parent.get(cur);
				}
				Collections.reverse(path);
				break;
			}
			//in order:
			//stop if neighbor is a wall
			//stop if neighbor is a key that is NOT our destination and is NOT a key we already have
			//this prevents pathing to a key that is past another ungrabbed key - otherwise, we might pick up keys out of order
			//the curKeys bitshift and comparison is the same as the bitmask trick in the recursion method
			for(Coord c : cur.directNeighbors()) {
				if(!walls.contains(c) && (!keys.contains(c) || c.equals(end) || (curKeys & (1 << keys.indexOf(c))) == (1 << keys.indexOf(c)))) {
					int tentativeG = gScore.get(cur) + 1;
					if(tentativeG < gScore.getOrDefault(c, Integer.MAX_VALUE)) {
						gScore.put(c, tentativeG);
						parent.put(c, cur);
						queue.add(c);
					}
				}
			}
		}
		if(path == null)
			return null;
		
		//iterate over path, check if any are doors
		for(Coord c : path) {
			if(doors.containsKey(c)) {
				//if there is a door along the path, add the bit for the required key to keysRequired
				Coord key = doors.get(c);
				keysRequired = keysRequired | (1 << keys.indexOf(key));
			}
		}
		//create and return 2-item array
		ArrayList<Integer> ret = new ArrayList<Integer>();
		ret.add(path.size());
		ret.add(keysRequired);
		return ret;
	}
	
	//determines whether any character in the entire arraylist is equal to c
	public boolean contains(ArrayList<String> a, char c) {
		for(String s : a) {
			for(char d : s.toCharArray())
				if(c == d)
					return true;
		}
		return false;
	}

	public static void main(String[] args) {
		input = Input.fetchInput(2019,18);
		DayRunner.run(new Day18());
	}
}
