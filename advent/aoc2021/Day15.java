package advent.aoc2021;

import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

import advent.utilities.general.Coord;
import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

public class Day15 implements IDay {

	static String input;
	
	@Override
	public String part1() {
		String[] lines = input.split("\n");
		int[][] map = new int[lines.length][lines[0].length()];
		for(int row = 0; row < lines.length; row++) {
			for(int col = 0; col < lines[row].length(); col++) {
				map[row][col] = Integer.parseInt(lines[row].substring(col,col+1));
			}
		}
		
		return Integer.toString(pathfind(map, new Coord(0,0), new Coord(map.length - 1, map[0].length - 1)));
	}
	
	@Override
	public String part2() {
		String[] lines = input.split("\n");
		int[][] map = new int[lines.length][lines[0].length()];
		for(int row = 0; row < lines.length; row++) {
			for(int col = 0; col < lines[row].length(); col++) {
				map[row][col] = Integer.parseInt(lines[row].substring(col,col+1));
			}
		}
		
		int[][] fullMap = new int[map.length * 5][map[0].length * 5];
		for(int mapRow = 0; mapRow < 5; mapRow++) {
			for(int mapCol = 0; mapCol < 5; mapCol++) {
				for(int row = 0; row < map.length; row++) {
					for(int col = 0; col < map.length; col++) {
						int newTile = (map[row][col] + mapRow + mapCol);
						if(newTile > 9)
							newTile = newTile % 10 + 1;
						fullMap[mapRow * map.length + row][mapCol * map[0].length + col] = newTile;
					}
				}
			}
		}

		return Integer.toString(pathfind(fullMap, new Coord(0,0), new Coord(fullMap.length - 1, fullMap[0].length - 1)));
	}

	public int pathfind(int[][] map, Coord start, Coord end) {
		HashMap<Coord,Integer> totalRisk = new HashMap<Coord,Integer>();

		PriorityQueue<Coord> queue = new PriorityQueue<Coord>(new Comparator<Coord>() {

			//heuristic is total risk so far, plus distance to end
			//default case prioritizes exploring locations where total risk is unknown
			@Override
			public int compare(Coord o1, Coord o2) {
				return Integer.compare(totalRisk.getOrDefault(o1, 0) + o1.dist(end), totalRisk.getOrDefault(o2, 0) + o2.dist(end));
			}

		});

		queue.add(start);
		totalRisk.put(start, 0);
		while(queue.size() > 0) {
			Coord cur = queue.poll();
			int risk = totalRisk.get(cur);
			if(cur.equals(end)) {
				return risk;
			}

			for(Coord c : cur.directNeighbors()) {
				if(c.x < 0 || c.y < 0 || c.x >= map.length || c.y >= map[c.x].length)
					continue;
				int newRisk = risk + map[c.x][c.y];
				if(newRisk < totalRisk.getOrDefault(c, Integer.MAX_VALUE)) {
					totalRisk.put(c, risk + map[c.x][c.y]);
					queue.add(c);
				}
			}
		}
		return -1;
	}

	public static void main(String[] args) {
		input = Input.fetchInput(2021,15);
		DayRunner.run(new Day15());
	}
}
