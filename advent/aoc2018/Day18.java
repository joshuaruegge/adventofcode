package advent.aoc2018;

import java.util.HashMap;

import advent.utilities.general.Coord;
import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;

public class Day18 implements IDay {

	String input = "#|#..||.||....|.#..|#.|#......|.#....#..|...|#.#.#\r\n"
			+ "#|...#..#.|..#..#...|..##.|....#|....#|.|..#......\r\n"
			+ "......|.#.|.##.|.|.#..#.|...#...##..||...|..##|.#.\r\n"
			+ ".###..#.#|..#.#.|...#|||.#|..#..|.|..#....#...#..|\r\n"
			+ ".#|.|...|.#.##|.##....#..|...|...|.....#..#|...|.#\r\n"
			+ "....||.#|.#|#..#.#|.....|.#..#.||#||.#....||#.....\r\n"
			+ "#.#.#.|.|.|.|#.#.##|.........#.##.#|..||.##.......\r\n"
			+ ".|.#.#...##.#.||..#|..|##...##...#.|..||.#.||....#\r\n"
			+ ".||.|.#.#....|#|#..#|#.#..#||#..||.|....#|..|.....\r\n"
			+ "##|.||.#...|.||..|..|.|...##.|||...|....#..|.|..|.\r\n"
			+ ".##.|...|..|.....|#.|#.|#..|#....##|......|#.##...\r\n"
			+ "|.###||...||..##...#.#||#.#.....|#..|#|..|.|.#.#..\r\n"
			+ "..|#..#..|..#|||..||#.|#.......|..||......|##.....\r\n"
			+ "##||...#.#...........#|###|...|..#|.....|.|..###..\r\n"
			+ ".#.#...#|.|.|...|....#|.|#.#..#....##|..||..|.....\r\n"
			+ "...#||...|##.#....|||....##|.|#|...|...#.||...|.##\r\n"
			+ "...#.||...|.|.#...#.....#.|##|..|..#.|.#...|..|.#.\r\n"
			+ "...|.#.###.|.||#|..#.|.#.#.........#...|...#.#|#|.\r\n"
			+ "|..|....||.#...|#.#|.#.|.....#.###.|##.|....|.#|..\r\n"
			+ "..#..#|.|.|.||##....#..||||...|.......#.#.......#.\r\n"
			+ "..|....|.....#....|....#.|.#|#..||##.##......|.|#.\r\n"
			+ "#..|.|..#.|||###|..|.#.#|.#...|...||........#..|#.\r\n"
			+ "..#|...#..#....#....#.##...#...#.|...|......#..||.\r\n"
			+ "#|#|...#...|...|..#|...|..#..|.#||..#.||.....|..#|\r\n"
			+ ".||...#.|..|#||||...|..|##..|.#..|#|.##.....|#.||.\r\n"
			+ "#|#.#...|...|......##|..#.|#.|.|....|.....|.#....#\r\n"
			+ "...|....#..#.#|##..#..#||.....|.#|.#|#.||.||.#..#.\r\n"
			+ "........#..##.......#...#..##......#.......#.#..|.\r\n"
			+ ".|.|.#.||......#..||..|...##...#..#|..|.#....#|#..\r\n"
			+ "##...|##....|||.##||..|....##.....|##||..|....#..#\r\n"
			+ ".###.||..|..|..#||#.|#..|..#||#.#.#.##...|....#|#.\r\n"
			+ "..##|.|..|.||...##|#||.........##|.....|#.|#..||..\r\n"
			+ "....#...#|##.|.|.....#|...|..|....|||..|......#...\r\n"
			+ "........|......|.|.....#.|......#......||....#.#.|\r\n"
			+ ".....#|#..|#.||.|...#|#.....||#..|.#..|..|.#.||.#.\r\n"
			+ ".||..|.##..#|...#....|..|..|.....||#......#|..|...\r\n"
			+ "###.|.##..|.|##.|.||..##..#..|..#.#|.|#...##|##...\r\n"
			+ "..##|..#..###..#.#......|.......|#.#.......|..||#|\r\n"
			+ "#..|..|..#....|#|.......#||..#|........#..#.||.|.#\r\n"
			+ "...#.|.#.......#|.....|................|..|#..#.|#\r\n"
			+ "..|..#....|....|.|....#|#..#..#.###.#....#|..|.##.\r\n"
			+ "#.|....#...|.|..|..||....||.#..#|#..#..###||.|#.#|\r\n"
			+ ".|......|#.#|.|..#....|....#.........###..||.#.#|.\r\n"
			+ "....###.#|.....#|...|.#.|..#...........#.......#||\r\n"
			+ ".#|...|....#.#||..|..##|....|#.||..#.#...#.##||.#.\r\n"
			+ "...|.||#.#...||.#|...||#.|||||..|..#..#|...#....#.\r\n"
			+ ".##|||##.#|.|.#..#.....#.......|..|.|...|.|....#|.\r\n"
			+ "#.#|....#.|..|.|.#..|.......#.....###.#..|......#|\r\n"
			+ ".#.......|..#.#.#.|...#|.|...||#.||#.|........#.|.\r\n"
			+ "..|.|##||.###.....|.|#.#|.||......#.##...|.#.#.#..";
	
	int max;
	
	@Override
	public String part1() {
		String[] lines = input.split("\r\n");
		max = lines.length;
		int[][] map = new int[max][max];
		for(int y = 0; y < lines.length; y++) {
			String line = lines[y];
			for(int x = 0; x < line.length(); x++) {
				map[x][y] = (line.charAt(x) == '#' ? 2 : (line.charAt(x) == '|') ? 1 : 0);
			}
		}
		
		for(int iter = 0; iter < 10; iter++) {
			int[][] newMap = new int[max][max];
			for(int x = 0; x < max; x++) {
				for(int y = 0; y < max; y++) {
					switch(map[x][y]) {
					case 0:
						if(surroundCount(x,y,map,1) > 2)
							newMap[x][y] = 1;
						else
							newMap[x][y] = 0;
						break;
					case 1:
						if(surroundCount(x,y,map,2) > 2)
							newMap[x][y] = 2;
						else
							newMap[x][y] = 1;
						break;
					case 2:
						if(surroundCount(x,y,map,1) > 0 && surroundCount(x,y,map,2) > 0)
							newMap[x][y] = 2;
						else
							newMap[x][y] = 0;
					}
				}
			}
			map = newMap;
		}
		
		int oneCount = 0;
		int twoCount = 0;
		
		for(int[] a : map) {
			for(int b : a) {
				if(b == 1)
					oneCount++;
				if(b == 2)
					twoCount++;
			}
		}
		
		return Integer.toString(oneCount * twoCount);
	}
	
	//counts number of searchType values in map around x,y
	public int surroundCount(int x, int y, int[][] map, int searchType) { 
		int count = 0;
		for(int xOff = -1; xOff < 2; xOff++) {
			for(int yOff = -1; yOff < 2; yOff++) {
				if((xOff | yOff) != 0 && inBounds(x+xOff,y+yOff) && map[x+xOff][y+yOff] == searchType)
					count++;
			}
		}
		return count;
	}
	
	public boolean inBounds(int x, int y) {
		return x > -1 && y > -1 && x < max && y < max;
	}

	@Override
	public String part2() {
		String[] lines = input.split("\r\n");
		max = lines.length;
		//maps are now coord -> integer hashmaps because int[][]s dont hash, compare, or set reliably
		HashMap<Coord,Integer> map = new HashMap<Coord,Integer>();
		for(int y = 0; y < lines.length; y++) {
			String line = lines[y];
			for(int x = 0; x < line.length(); x++) {
				map.put(new Coord(x,y), (line.charAt(x) == '#' ? 2 : (line.charAt(x) == '|') ? 1 : 0));
			}
		}
		
		HashMap<HashMap<Coord,Integer>,Integer> states = new HashMap<HashMap<Coord,Integer>,Integer>();
		
		int counter = 0;
		do {
			states.put(map,counter);
			HashMap<Coord,Integer> newMap = new HashMap<Coord,Integer>();
			for(int x = 0; x < max; x++) {
				for(int y = 0; y < max; y++) {
					Coord cur = new Coord(x,y);
					switch(map.get(cur)) {
					case 0:
						if(mapSurroundCount(cur,map,1) > 2)
							newMap.put(cur, 1);
						else
							newMap.put(cur, 0);
						break;
					case 1:
						if(mapSurroundCount(cur,map,2) > 2)
							newMap.put(cur, 2);
						else
							newMap.put(cur, 1);
						break;
					case 2:
						if(mapSurroundCount(cur,map,1) > 0 && mapSurroundCount(cur,map,2) > 0)
							newMap.put(cur, 2);
						else
							newMap.put(cur, 0);
					}
				}
			}
			map = newMap;
			counter++;
		} while(!states.containsKey(map));
		
		int cycleLength = counter - states.get(map);
	
		//at this point, we've discovered a loop, so we know that the state we curently are at
		//will be the same state we're at at 1 billion - (1 billion % loopSize) states
		//so, all we need to do is do 1 billion % loopSize iterations, and we get the equivalent of our billionth state
		for(int i = 0; i <= (1000000000 % cycleLength) ; i++) {
			HashMap<Coord,Integer> newMap = new HashMap<Coord,Integer>();
			for(int x = 0; x < max; x++) {
				for(int y = 0; y < max; y++) {
					Coord cur = new Coord(x,y);
					switch(map.get(cur)) {
					case 0:
						if(mapSurroundCount(cur,map,1) > 2)
							newMap.put(cur, 1);
						else
							newMap.put(cur, 0);
						break;
					case 1:
						if(mapSurroundCount(cur,map,2) > 2)
							newMap.put(cur, 2);
						else
							newMap.put(cur, 1);
						break;
					case 2:
						if(mapSurroundCount(cur,map,1) > 0 && mapSurroundCount(cur,map,2) > 0)
							newMap.put(cur, 2);
						else
							newMap.put(cur, 0);
					}
				}
			}
			map = newMap;
		}
		
		int oneCount = 0;
		int twoCount = 0;
		for(Coord c : map.keySet()) {
			if(map.get(c) == 1)
				oneCount++;
			if(map.get(c) == 2)
				twoCount++;
		}
		
		return Integer.toString(oneCount * twoCount);
	}
	
	//counts number of int type adjacent to coord c
	public int mapSurroundCount(Coord c, HashMap<Coord,Integer> a, int type) {
		int count = 0;
		for(Coord d : c.allNeighbors())
			if(a.getOrDefault(d, -1) == type)
				count++;
		return count;
	}

	public static void main(String[] args) {
		DayRunner.run(new Day18());
	}

}
