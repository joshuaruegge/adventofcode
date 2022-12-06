package advent.aoc2018;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import advent.utilities.general.Coord;
import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

public class Day06 implements IDay {

	static String input;
	
	@Override
	public String part1() {
		ArrayList<Coord> points = new ArrayList<Coord>();
	
		for(String s : input.split("\n")) {
			int x = Integer.parseInt(s.split(", ")[0]);
			int y = Integer.parseInt(s.split(", ")[1]);
			points.add(new Coord(x,y));
		}
		
		int maxX = points.stream().mapToInt(x -> x.x).max().getAsInt();
		int maxY = points.stream().mapToInt(x -> x.y).max().getAsInt();
		//store points in hashmap coord -> closest point (index of point in points)
		HashMap<Coord,Integer> closest = new HashMap<Coord,Integer>();
		for(int x = 0; x <= maxX + 1; x++) {
			for(int y = 0; y <= maxY + 1; y++) {
				Coord cur = new Coord(x,y);
				int minDist = Integer.MAX_VALUE;
				int minIndex = -1;
				for(int i = 0; i < points.size(); i++) {
					int dist = cur.dist(points.get(i));
					if(dist < minDist) {
						minDist = dist;
						minIndex = i;
					}
				}
				closest.put(cur, minIndex);
				//check to make sure no other values equal minimum - if they do, mark as invalid and break
				for(int i = 0; i < points.size(); i++) {
					if(i != minIndex && cur.dist(points.get(i)) == minDist) {
						closest.put(cur, -1);
						break;
					}
				}
			}
		}
		HashSet<Integer> invalidIndexes = new HashSet<Integer>();
		//iterate over edge tiles - if point has a tile on edge, point extends infinitely and is invalid
		for(Coord c : closest.keySet()) {
			if(c.x == 0 || c.x == maxX + 1 || c.y == 0 || c.y == maxY + 1) {
				invalidIndexes.add(closest.get(c));
			}
		}
		//calculate frequency of values in closest (size of regions)
		HashMap<Integer,Integer> freq = new HashMap<Integer,Integer>();
		for(int i : closest.values()) {
			freq.put(i, freq.getOrDefault(i,0) + 1);
		}
		int largestArea = 0;
		for(int i = 0; i < points.size(); i++) {
			if(invalidIndexes.contains(i)) {
				continue;
			}
			if(freq.get(i) > largestArea)
				largestArea = freq.get(i);
		}
		return Integer.toString(largestArea);
	}

	@Override
	public String part2() {
		ArrayList<Coord> points = new ArrayList<Coord>();
		
		for(String s : input.split("\n")) {
			int x = Integer.parseInt(s.split(", ")[0]);
			int y = Integer.parseInt(s.split(", ")[1]);
			points.add(new Coord(x,y));
		}
		int maxX = points.stream().mapToInt(x -> x.x).max().getAsInt();
		int maxY = points.stream().mapToInt(x -> x.y).max().getAsInt();
		
		int region = 0;
		for(int x = 0; x < maxX; x++) {
			for(int y = 0; y < maxY; y++) {
				int totalDist = 0;
				for(Coord c : points) {
					totalDist += new Coord(x,y).dist(c);
				}
				if(totalDist < 10000)
					region++;
			}
		}
		return Integer.toString(region);
	}

	public static void main(String[] args) {
		input = Input.fetchInput(2018,6);
		DayRunner.run(new Day06());
	}

}
