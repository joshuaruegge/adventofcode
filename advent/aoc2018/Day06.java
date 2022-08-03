package advent.aoc2018;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import advent.utilities.general.Coord;
import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;

public class Day06 implements IDay {

	String input = "84, 212\r\n"
			+ "168, 116\r\n"
			+ "195, 339\r\n"
			+ "110, 86\r\n"
			+ "303, 244\r\n"
			+ "228, 338\r\n"
			+ "151, 295\r\n"
			+ "115, 49\r\n"
			+ "161, 98\r\n"
			+ "60, 197\r\n"
			+ "40, 55\r\n"
			+ "55, 322\r\n"
			+ "148, 82\r\n"
			+ "86, 349\r\n"
			+ "145, 295\r\n"
			+ "243, 281\r\n"
			+ "91, 343\r\n"
			+ "280, 50\r\n"
			+ "149, 129\r\n"
			+ "174, 119\r\n"
			+ "170, 44\r\n"
			+ "296, 148\r\n"
			+ "152, 160\r\n"
			+ "115, 251\r\n"
			+ "266, 281\r\n"
			+ "269, 285\r\n"
			+ "109, 242\r\n"
			+ "136, 241\r\n"
			+ "236, 249\r\n"
			+ "338, 245\r\n"
			+ "71, 101\r\n"
			+ "254, 327\r\n"
			+ "208, 231\r\n"
			+ "289, 184\r\n"
			+ "282, 158\r\n"
			+ "352, 51\r\n"
			+ "326, 230\r\n"
			+ "88, 240\r\n"
			+ "292, 342\r\n"
			+ "352, 189\r\n"
			+ "231, 141\r\n"
			+ "280, 350\r\n"
			+ "296, 185\r\n"
			+ "226, 252\r\n"
			+ "172, 235\r\n"
			+ "137, 161\r\n"
			+ "207, 90\r\n"
			+ "101, 133\r\n"
			+ "156, 234\r\n"
			+ "241, 185";
	
	@Override
	public String part1() {
		ArrayList<Coord> points = new ArrayList<Coord>();
	
		for(String s : input.split("\r\n")) {
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
		
		for(String s : input.split("\r\n")) {
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
		DayRunner.run(new Day06());
	}

}
