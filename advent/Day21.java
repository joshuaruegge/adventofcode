package advent.aoc2017;

import java.util.ArrayList;
import java.util.HashMap;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.utils2017.GridState;

public class Day21 implements IDay {

	String input = "../.. => .#./.../###\r\n"
			+ "#./.. => .#./##./#..\r\n"
			+ "##/.. => #.#/#../###\r\n"
			+ ".#/#. => ##./..#/###\r\n"
			+ "##/#. => .#./#../..#\r\n"
			+ "##/## => #../..#/#.#\r\n"
			+ ".../.../... => .###/.#.#/.###/##.#\r\n"
			+ "#../.../... => .##./##../##../#.##\r\n"
			+ ".#./.../... => .#.#/#.#./..#./..#.\r\n"
			+ "##./.../... => ###./#.##/...#/#.##\r\n"
			+ "#.#/.../... => .#.#/.#../.###/.###\r\n"
			+ "###/.../... => ..##/#.#./..../##.#\r\n"
			+ ".#./#../... => #.../..../..../....\r\n"
			+ "##./#../... => ...#/..#./.###/#.#.\r\n"
			+ "..#/#../... => #.../#.##/###./##..\r\n"
			+ "#.#/#../... => .##./#..#/#..#/..##\r\n"
			+ ".##/#../... => .#.#/#.##/..##/.#.#\r\n"
			+ "###/#../... => #.#./.###/..#./#.#.\r\n"
			+ ".../.#./... => #..#/..../.##./.#.#\r\n"
			+ "#../.#./... => .#../.##./.#.#/...#\r\n"
			+ ".#./.#./... => ##.#/...#/.##./...#\r\n"
			+ "##./.#./... => ..#./#.#./#.##/####\r\n"
			+ "#.#/.#./... => ..##/#..#/.###/....\r\n"
			+ "###/.#./... => .#../#..#/#.../..#.\r\n"
			+ ".#./##./... => ..##/#.#./####/###.\r\n"
			+ "##./##./... => ...#/.#../####/#..#\r\n"
			+ "..#/##./... => ..##/##../###./....\r\n"
			+ "#.#/##./... => ..##/#.../.#../.##.\r\n"
			+ ".##/##./... => #.../##../#.##/...#\r\n"
			+ "###/##./... => .#../####/#.##/#.##\r\n"
			+ ".../#.#/... => #..#/####/###./#.#.\r\n"
			+ "#../#.#/... => #.../##.#/#.../.#..\r\n"
			+ ".#./#.#/... => ##.#/##.#/..#./..#.\r\n"
			+ "##./#.#/... => .###/..#./.#../.###\r\n"
			+ "#.#/#.#/... => .###/##../..#./..#.\r\n"
			+ "###/#.#/... => ##../.#../.#../.#..\r\n"
			+ ".../###/... => ..#./#.#./..#./#..#\r\n"
			+ "#../###/... => ..../#.#./##.#/..##\r\n"
			+ ".#./###/... => ..#./#.#./..##/.#..\r\n"
			+ "##./###/... => .##./..##/#..#/#.#.\r\n"
			+ "#.#/###/... => ###./###./#.##/..##\r\n"
			+ "###/###/... => ##.#/..../.##./.#..\r\n"
			+ "..#/.../#.. => .###/####/..../##.#\r\n"
			+ "#.#/.../#.. => ##../###./#..#/...#\r\n"
			+ ".##/.../#.. => ###./#..#/###./...#\r\n"
			+ "###/.../#.. => #.../#..#/##.#/.##.\r\n"
			+ ".##/#../#.. => ..##/####/..##/#...\r\n"
			+ "###/#../#.. => #.../..../...#/..##\r\n"
			+ "..#/.#./#.. => ####/#.#./..../.#.#\r\n"
			+ "#.#/.#./#.. => .##./.#.#/##.#/.##.\r\n"
			+ ".##/.#./#.. => ###./.#.#/###./##.#\r\n"
			+ "###/.#./#.. => #.##/..##/#.#./##.#\r\n"
			+ ".##/##./#.. => ..../..##/#.#./.##.\r\n"
			+ "###/##./#.. => #.#./#..#/#..#/###.\r\n"
			+ "#../..#/#.. => ..../####/#..#/.###\r\n"
			+ ".#./..#/#.. => .###/#.../#.../#.##\r\n"
			+ "##./..#/#.. => ####/##.#/###./####\r\n"
			+ "#.#/..#/#.. => .#../##.#/#..#/#..#\r\n"
			+ ".##/..#/#.. => ..##/##.#/#.##/###.\r\n"
			+ "###/..#/#.. => ##.#/####/##.#/.#..\r\n"
			+ "#../#.#/#.. => .###/#..#/.##./.###\r\n"
			+ ".#./#.#/#.. => #.##/.##./.#../..#.\r\n"
			+ "##./#.#/#.. => ###./..#./.##./##..\r\n"
			+ "..#/#.#/#.. => .###/.#.#/#.#./##..\r\n"
			+ "#.#/#.#/#.. => #..#/.###/.##./....\r\n"
			+ ".##/#.#/#.. => ###./.###/#.##/.###\r\n"
			+ "###/#.#/#.. => ####/.###/..../.##.\r\n"
			+ "#../.##/#.. => ##.#/..../#.../..#.\r\n"
			+ ".#./.##/#.. => #.../..../...#/###.\r\n"
			+ "##./.##/#.. => ###./.#../..##/...#\r\n"
			+ "#.#/.##/#.. => #.../...#/..#./.###\r\n"
			+ ".##/.##/#.. => ###./..../##.#/...#\r\n"
			+ "###/.##/#.. => ##.#/##../###./.##.\r\n"
			+ "#../###/#.. => ..#./#.../..##/#.##\r\n"
			+ ".#./###/#.. => ...#/.##./.#../.#..\r\n"
			+ "##./###/#.. => ##.#/.#.#/###./....\r\n"
			+ "..#/###/#.. => #.##/#.../####/.##.\r\n"
			+ "#.#/###/#.. => .#.#/...#/#..#/..#.\r\n"
			+ ".##/###/#.. => .##./#..#/#..#/.#.#\r\n"
			+ "###/###/#.. => ###./####/#.##/#...\r\n"
			+ ".#./#.#/.#. => ###./#..#/...#/...#\r\n"
			+ "##./#.#/.#. => #.#./#.##/#.../#..#\r\n"
			+ "#.#/#.#/.#. => .#.#/#.##/..../.#..\r\n"
			+ "###/#.#/.#. => #.#./.#../.###/#.#.\r\n"
			+ ".#./###/.#. => #.../.###/##../##.#\r\n"
			+ "##./###/.#. => .###/#.../####/.#.#\r\n"
			+ "#.#/###/.#. => #..#/####/#.#./#...\r\n"
			+ "###/###/.#. => .#../..../.##./.#.#\r\n"
			+ "#.#/..#/##. => ##../###./...#/###.\r\n"
			+ "###/..#/##. => .##./###./.###/#.##\r\n"
			+ ".##/#.#/##. => ..../##.#/#..#/#...\r\n"
			+ "###/#.#/##. => .###/##../..../..#.\r\n"
			+ "#.#/.##/##. => ####/.###/##../...#\r\n"
			+ "###/.##/##. => #.##/..##/..#./#..#\r\n"
			+ ".##/###/##. => ..../#.##/#.../#.##\r\n"
			+ "###/###/##. => ..../#..#/#.##/#.##\r\n"
			+ "#.#/.../#.# => #.../##.#/..../.#.#\r\n"
			+ "###/.../#.# => ##../##../#.#./.##.\r\n"
			+ "###/#../#.# => .##./.#../#.##/.##.\r\n"
			+ "#.#/.#./#.# => #.../.#../####/#.##\r\n"
			+ "###/.#./#.# => .###/##.#/#.../#.#.\r\n"
			+ "###/##./#.# => .##./.##./.###/.#.#\r\n"
			+ "#.#/#.#/#.# => ####/####/###./.##.\r\n"
			+ "###/#.#/#.# => #.#./.###/...#/.#.#\r\n"
			+ "#.#/###/#.# => .###/..#./..../.##.\r\n"
			+ "###/###/#.# => #.#./##.#/..#./..#.\r\n"
			+ "###/#.#/### => ###./#.../##../##..\r\n"
			+ "###/###/### => ##.#/.#.#/#.#./...#";
	
	@Override
	public String part1() {
		//store all rules in boolean[][] -> boolean[][] map
		//GridState is a boolean[][] wrapper class that provides proper hashing
		HashMap<GridState,GridState> rules = new HashMap<GridState,GridState>();
		for(String s : input.split("\r\n")) {
			String[] keyValue = s.split(" => ");
			String[] keyParts = keyValue[0].split("/");
			boolean[][] key = new boolean[keyParts.length][keyParts.length];
			for(int row = 0; row < keyParts.length; row++) {
				for(int col = 0; col < keyParts.length; col++) {
					key[row][col] = (keyParts[row].charAt(col) == '#');
				}
			}
			String[] valueParts = keyValue[1].split("/");
			boolean[][] value = new boolean[valueParts.length][valueParts.length];
			for(int row = 0; row < value.length; row++) {
				for(int col = 0; col < value.length; col++) {
					value[row][col] = (valueParts[row].charAt(col) == '#');
				}
			}
			//put in original and flips
			rules.put(new GridState(key), new GridState(value));
			rules.put(new GridState(flip(key)), new GridState(value));
			rules.put(new GridState(hflip(key)), new GridState(value));
			//put in each of 3 rotations and flips
			for(int i = 0; i < 3; i++) {
				key = flip(transpose(key));
				rules.put(new GridState(key), new GridState(value));
				rules.put(new GridState(flip(key)), new GridState(value));
				rules.put(new GridState(hflip(key)), new GridState(value));
			}
		}
		
		boolean[][] grid = new boolean[][] {{false,true,false},{false,false,true},{true,true,true}};
		
		for(int iter = 0; iter < 5; iter++) {
			int originalSize = grid.length;
			boolean even = (grid.length % 2 == 0);
			if(even) {
				ArrayList<GridState> original = new ArrayList<GridState>();
				//split grid into 2x2 sections
				for(int row = 0; row < grid.length; row+=2) {
					for(int col = 0; col < grid.length; col += 2) {
						boolean[][] two = new boolean[][] {{grid[row][col], grid[row][col+1]}, {grid[row+1][col],grid[row+1][col+1]}};
						original.add(new GridState(two));
					}
				}
				//update grid size
				int newGridSize = (originalSize / 2) * 3;
				grid = new boolean[newGridSize][newGridSize];
				int rowOffset = 0;
				int colOffset = 0;
				//push each grid square to new grid
				for(GridState g : original) {
					//fetch new grid at location
					boolean[][] newGrid = rules.get(g).grid;
					//push values to proper location at new grid
					for(int row = 0; row < 3; row++) {
						for(int col = 0; col < 3; col++) {
							grid[row+rowOffset][col+colOffset] = newGrid[row][col];
						}
					}
					//increment to next region
					colOffset += 3;
					if(colOffset == newGridSize) {
						rowOffset += 3;
						colOffset = 0;
					}
				}
			} else {
				ArrayList<GridState> original = new ArrayList<GridState>();
				//split into 3x3
				for(int row = 0; row < grid.length; row += 3) {
					for(int col = 0; col < grid.length; col += 3) {
						boolean[][] three = new boolean[][] {{grid[row][col],grid[row][col+1],grid[row][col+2]},{grid[row+1][col],grid[row+1][col+1],grid[row+1][col+2]},{grid[row+2][col],grid[row+2][col+1],grid[row+2][col+2]}};
						original.add(new GridState(three));
					}
				}
				//update grid size
				int newGridSize = (originalSize / 3) * 4;
				grid = new boolean[newGridSize][newGridSize];
				int rowOffset = 0;
				int colOffset = 0;
				//push to new grid
				for(GridState g : original) {
					boolean[][] newGrid = rules.get(g).grid;
					for(int row = 0; row < 4; row++) {
						for(int col = 0; col < 4; col++) {
							grid[row+rowOffset][col+colOffset] = newGrid[row][col];
						}
					}
					colOffset += 4;
					if(colOffset == newGridSize) {
						rowOffset += 4;
						colOffset = 0;
					}
				}
			}
		}
		int trues = 0;
		for(boolean[] a : grid) {
			for(boolean b : a) {
				if(b)
					trues++;
			}
		}
		return Integer.toString(trues);
	}
	
	//helper method - flips x and y (matrix transpose)
	//assumes a is square (2x2 or 3x3)
	public boolean[][] transpose(boolean[][] a) {
		boolean[][] b = new boolean[a.length][a.length];
		for(int x = 0; x < a.length; x++) {
			for(int y = 0; y < a.length; y++) {
				b[x][y] = a[y][x];
			}
		}
		return b;
	}
	
	//helper method - vertical flip
	public boolean[][] flip(boolean[][] a) {
		boolean[][] b = new boolean[a.length][a.length];
		b[0] = a[a.length - 1];
		b[b.length - 1] = a[0];
		if(a.length > 2)
			b[1] = a[1];
		return b;
	}
	
	//helper method - horizontal flip
	public boolean[][] hflip(boolean[][] a) {
		boolean[][] b = new boolean[a.length][a.length];
		for(int i = 0; i < a.length; i++) {
			b[i][0] = a[i][a.length - 1];
			b[i][a.length - 1] = a[i][0];
			if(a.length > 2)
				b[i][1] = a[i][1];
		}
		return b;
	}
	

	@Override
	public String part2() {
		//store all rules in boolean[][] -> boolean[][] map
		//GridState is a boolean[][] wrapper class that provides proper hashing
		HashMap<GridState,GridState> rules = new HashMap<GridState,GridState>();
		for(String s : input.split("\r\n")) {
			String[] keyValue = s.split(" => ");
			String[] keyParts = keyValue[0].split("/");
			boolean[][] key = new boolean[keyParts.length][keyParts.length];
			for(int row = 0; row < keyParts.length; row++) {
				for(int col = 0; col < keyParts.length; col++) {
					key[row][col] = (keyParts[row].charAt(col) == '#');
				}
			}
			String[] valueParts = keyValue[1].split("/");
			boolean[][] value = new boolean[valueParts.length][valueParts.length];
			for(int row = 0; row < value.length; row++) {
				for(int col = 0; col < value.length; col++) {
					value[row][col] = (valueParts[row].charAt(col) == '#');
				}
			}
			//put in original
			rules.put(new GridState(key), new GridState(value));
			rules.put(new GridState(flip(key)), new GridState(value));
			rules.put(new GridState(hflip(key)), new GridState(value));
			//put in each of 3 rotations
			for(int i = 0; i < 3; i++) {
				key = flip(transpose(key));
				rules.put(new GridState(key), new GridState(value));
				rules.put(new GridState(flip(key)), new GridState(value));
				rules.put(new GridState(hflip(key)), new GridState(value));
			}
		}
		
		boolean[][] grid = new boolean[][] {{false,true,false},{false,false,true},{true,true,true}};
		
		for(int iter = 0; iter < 18; iter++) {
			int originalSize = grid.length;
			boolean even = (grid.length % 2 == 0);
			if(even) {
				ArrayList<GridState> original = new ArrayList<GridState>();
				//split grid into 2x2 sections
				for(int row = 0; row < grid.length; row+=2) {
					for(int col = 0; col < grid.length; col += 2) {
						boolean[][] two = new boolean[][] {{grid[row][col], grid[row][col+1]}, {grid[row+1][col],grid[row+1][col+1]}};
						original.add(new GridState(two));
					}
				}
				//update grid size
				int newGridSize = (originalSize / 2) * 3;
				grid = new boolean[newGridSize][newGridSize];
				int rowOffset = 0;
				int colOffset = 0;
				//push each grid square to new grid
				for(GridState g : original) {
					//fetch new grid at location
					boolean[][] newGrid = rules.get(g).grid;
					//push values to proper location at new grid
					for(int row = 0; row < 3; row++) {
						for(int col = 0; col < 3; col++) {
							grid[row+rowOffset][col+colOffset] = newGrid[row][col];
						}
					}
					//increment to next region
					colOffset += 3;
					if(colOffset == newGridSize) {
						rowOffset += 3;
						colOffset = 0;
					}
				}
			} else {
				ArrayList<GridState> original = new ArrayList<GridState>();
				//split into 3x3
				for(int row = 0; row < grid.length; row += 3) {
					for(int col = 0; col < grid.length; col += 3) {
						boolean[][] three = new boolean[][] {{grid[row][col],grid[row][col+1],grid[row][col+2]},{grid[row+1][col],grid[row+1][col+1],grid[row+1][col+2]},{grid[row+2][col],grid[row+2][col+1],grid[row+2][col+2]}};
						original.add(new GridState(three));
					}
				}
				//update grid size
				int newGridSize = (originalSize / 3) * 4;
				grid = new boolean[newGridSize][newGridSize];
				int rowOffset = 0;
				int colOffset = 0;
				//push to new grid
				for(GridState g : original) {
					boolean[][] newGrid = rules.get(g).grid;
					for(int row = 0; row < 4; row++) {
						for(int col = 0; col < 4; col++) {
							grid[row+rowOffset][col+colOffset] = newGrid[row][col];
						}
					}
					colOffset += 4;
					if(colOffset == newGridSize) {
						rowOffset += 4;
						colOffset = 0;
					}
				}
			}
		}
		
		int trues = 0;
		for(boolean[] a : grid) {
			for(boolean b : a) {
				if(b)
					trues++;
			}
		}
		return Integer.toString(trues);
	}

	public static void main(String[] args) {
		DayRunner.run(new Day21());
	}

}
