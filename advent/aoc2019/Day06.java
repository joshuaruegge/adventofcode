package advent.aoc2019;

import java.util.ArrayList;
import java.util.HashMap;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

public class Day06 implements IDay {
	static String input;
	
	static HashMap<String,String> pairs = new HashMap<String,String>();

	@Override
	public String part1() {
		for(String s : input.split("\n")) {
			String[] parts = s.split("\\)");
			pairs.put(parts[1], parts[0]);	
		}
		//essentially just totaling tree depths, but using pathfinding method because tree structure was annoying
		int dist = 0;
		for(String key : pairs.keySet()) {
			dist += pathTo(key,"COM",new ArrayList<String>()).size();
		}
		return Integer.toString(dist);
	}

	@Override
	public String part2() {
		pairs.clear();
		for(String s : input.split("\n")) {
			String[] parts = s.split("\\)");
			pairs.put(parts[1], parts[0]);	
		}
		
		int minTransfers = Integer.MAX_VALUE;
		//pathfind from our two locations to every location in the tree, finding the shortest connector
		for(String key : pairs.keySet()) {
			ArrayList<String> youPath = pathTo("YOU",key,new ArrayList<String>());
			ArrayList<String> sanPath = pathTo("SAN",key,new ArrayList<String>());
			if(youPath != null && sanPath != null) {
				//take off one to account for the starting nodes and the connection present in both
				if(youPath.size() - 1 + sanPath.size() - 1 < minTransfers) {
					minTransfers = youPath.size() - 1 + sanPath.size() - 1;
				}
			}
		}
		return Integer.toString(minTransfers);
	}
	
	//pathfinding method - recursively work our way through pairs/down the tree until dead end or destination reached
	public static ArrayList<String> pathTo(String start, String dest, ArrayList<String> path) {
		if(start.equals(dest)) {
			return path;
		} else {
			if(!pairs.containsKey(start)) {
				return null;
			}
			//add current location to path
			path.add(pairs.get(start));
			//continue from the location connected to current
			return pathTo(pairs.get(start),dest,path);
		}
	}
	
	public static void main(String[] args) {
		input = Input.fetchInput(2019,6);
		DayRunner.run(new Day06());
	}
}
