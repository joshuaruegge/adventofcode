package advent.aoc2020;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;
import advent.utilities.utils2020.GridEdge;
import advent.utilities.utils2020.GridTile;

public class Day20 implements IDay {

	static String input;
	
	@Override
	public String part1() {
		ArrayList<GridTile> tiles = new ArrayList<GridTile>();
		
		for(String tile : input.split("\n\n")) {
			String[] rows = tile.split("\n");
			int id = Integer.parseInt(rows[0].substring(5,9));
			
			boolean[][] data = new boolean[10][10];
			for(int row = 1; row < rows.length; row++) {
				for(int col = 0; col < 10; col++) {
					data[row-1][col] = rows[row].charAt(col) == '#';
				}
			}
			
			//for now, assume data is rotated correctly - we'll handle rotations and flips later if necessary
			GridTile newTile = new GridTile(id,data);
			newTile.calculateEdges();
			tiles.add(newTile);
		}
		
		//build match lists
		for(GridTile tile : tiles) {
			for(GridTile tile2 : tiles) {
				if(tile.equals(tile2))
					continue;
				HashSet<GridEdge> matchingEdges = new HashSet<GridEdge>(tile.edges);
				matchingEdges.retainAll(tile2.edges);
				//if any edges remaining/in common, mark tiles as matched
				if(matchingEdges.size() > 0) {
					tile.matches.add(tile2);
					tile2.matches.add(tile);
				}
			}
		}
		
		long product = 1;
		for(GridTile g : tiles) {
			//corner tiles will only be matched with 2 other tiles
			if(g.matches.size() == 2) {
				product *= g.id;
			}
		}
		
		return Long.toString(product);
	}

	@Override
	public String part2() {
		ArrayList<GridTile> tiles = new ArrayList<GridTile>();
		for(String tile : input.split("\n\n")) {
			String[] rows = tile.split("\n");
			int id = Integer.parseInt(rows[0].substring(5,9));
			
			boolean[][] data = new boolean[10][10];
			for(int row = 1; row < rows.length; row++) {
				for(int col = 0; col < 10; col++) {
					data[row-1][col] = rows[row].charAt(col) == '#';
				}
			}
			
			//for now, assume data is rotated correctly - we'll handle rotations and flips later if necessary
			GridTile newTile = new GridTile(id,data);
			newTile.calculateEdges();
			tiles.add(newTile);
		}
		
		//build match lists
		for(GridTile tile : tiles) {
			for(GridTile tile2 : tiles) {
				if(tile.equals(tile2) || tile.matches.contains(tile2))
					continue;
				//this time, map edge adjacencies completely, instead of just counting
				//check top
				if(tile2.edges.contains(new GridEdge(tile.topEdge))) {
					tile.matches.add(tile2);
					tile2.matches.add(tile);
					tile.top = tile2;
					tile2.bottom = tile;
				}
				//check bottom
				if(tile2.edges.contains(new GridEdge(tile.bottomEdge))) {
					tile.matches.add(tile2);
					tile2.matches.add(tile);
					tile.bottom = tile2;
					tile2.top = tile;
				}
				//check left
				if(tile2.edges.contains(new GridEdge(tile.leftEdge))) {
					tile.matches.add(tile2);
					tile2.matches.add(tile);
					tile.left = tile2;
					tile2.right = tile;
				}
				//check right
				if(tile2.edges.contains(new GridEdge(tile.rightEdge))) {
					tile.matches.add(tile2);
					tile2.matches.add(tile);
					tile.right = tile2;
					tile2.left = tile;
				}
			}
		}
	
		//arbitrarily start from current top-left - this may not be the "real" top-left,
		//but once all tiles are rotated congruently to each other, we can flip and rotate the entire map as necessary
		GridTile topLeftCorner = tiles.stream().filter(x -> x.matches.size() == 2 && x.top == null && x.left == null).findFirst().get();
		
		GridTile curTile = topLeftCorner.clone();
		GridTile firstInRow = curTile.clone();
		
		//the rotating loop below has some unusual very difficult to track down bugs that i suspect are from the top, bottom, left, and right pointer references
		//so, log IDs in this array to reconstruct from afterwards
		//because the tiles are always *traversed* in the right order, this means we can get the correct solution without relying on the in-object pointers
		int mapSize = (int) Math.sqrt(tiles.size());
		int[][] ids = new int[mapSize][mapSize];
		int idRow = 0;
		int idCol = 0;
		
		//vertical loop
		while(true) {
			//horizontal loop
			while(true) {
				ids[idRow][idCol] = curTile.id;
				idCol++;
				
				//find tile right of current
				//we can't necessarily use curTile.right because we're flipping and rotating like a madman
				GridTile nextTile = null;
				
				for(GridTile t : tiles) {
					if(t.equals(curTile))
						continue;
					if(t.edges.contains(new GridEdge(curTile.rightEdge))) {
						nextTile = t;
						break;
					}
				}
				
				if(nextTile != null) {
					curTile.right = nextTile;
					nextTile.left = curTile;
					
					//if simple flip away, flip and continue
					if(Arrays.equals(curTile.rightEdge, GridTile.reverse(nextTile.leftEdge))) {
						//flip vertically
						nextTile.data = flip(nextTile.data);
						nextTile.calculateEdges();
					}
					
					//otherwise, try rotates
					for(int i = 0; i < 4; i++) {
						nextTile.data = flip(transpose(nextTile.data));
						nextTile.calculateEdges();
						if(Arrays.equals(curTile.rightEdge, nextTile.leftEdge) || Arrays.equals(curTile.rightEdge, GridTile.reverse(nextTile.leftEdge))) {
							if(Arrays.equals(curTile.rightEdge, GridTile.reverse(nextTile.leftEdge))) {
								nextTile.data = flip(nextTile.data);
								nextTile.calculateEdges();
							}
							break;
						}
					}
					//now that value is set, move right
					curTile = nextTile;
				} else {
					//end of row, move down
					curTile.right = null;
					break;
				}
			}
			
			//now that we are done with row, jump back to beginning, and find tile below beginning
			GridTile bottomTile = null;
			for(GridTile t : tiles) {
				if(t.equals(firstInRow))
					continue;
				if(t.edges.contains(new GridEdge(firstInRow.bottomEdge))) {
					bottomTile = t;
					break;
				}
			}
			
			//if we make sure bottom tile matches the one above it, hopefully we don't need to do any vertical rotation the rest of the row
			if(bottomTile != null) {
				idCol = 0;
				idRow++;
				
				firstInRow.bottom = bottomTile;
				bottomTile.top = firstInRow;
				
				if(Arrays.equals(bottomTile.topEdge, GridTile.reverse(firstInRow.bottomEdge))) {
					//horizontal flip
					bottomTile.data = hflip(bottomTile.data);
					bottomTile.calculateEdges();
				}
				//rotations
				for(int i = 0; i < 4; i++) {
					bottomTile.data = flip(transpose(bottomTile.data));
					bottomTile.calculateEdges();
					if(Arrays.equals(bottomTile.topEdge, firstInRow.bottomEdge) || Arrays.equals(bottomTile.topEdge, GridTile.reverse(firstInRow.bottomEdge))) {
						//hflip if necessary
						if(Arrays.equals(bottomTile.topEdge, GridTile.reverse(firstInRow.bottomEdge))) {
							bottomTile.data = hflip(bottomTile.data);
							bottomTile.calculateEdges();
						}
						break;
					}
				}
				//start new row
				firstInRow = bottomTile.clone();
				curTile = bottomTile.clone();
			} else {
				break;
			}
		}
		
		//construct full data from ids list
		boolean[][] fullData = new boolean[mapSize * 8][mapSize * 8];
		int rowOffset = 0;
		int colOffset = 0;
		for(int[] a : ids) {
			for(int id : a) {
				boolean[][] data = find(tiles,id).data;
				for(int row = 1; row < 9; row++) {
					for(int col = 1; col < 9; col++) {
						fullData[(row-1) + rowOffset][(col-1) + colOffset] = data[row][col];
					}
				}
				colOffset += 8;
			}
			colOffset = 0;
			rowOffset += 8;
		}
		
		//lastly, locate our sea monsters in our full grid
		int initialCount = count(fullData);
		//potential rotates
		for(int i = 0; i < 4; i++) {
			//try all potential flips - if sea monsters present, countMinus will be less than initial, and return
			int minusNormal = countMinusSeaMonsters(fullData);
			if(minusNormal < initialCount)
				return Integer.toString(minusNormal);
			int minusFlip = countMinusSeaMonsters(flip(fullData));
			if(minusFlip < initialCount)
				return Integer.toString(minusFlip);
			int minusHFlip = countMinusSeaMonsters(hflip(fullData));
			if(minusHFlip < initialCount)
				return Integer.toString(minusHFlip);
			//if none worked, rotate original data and move onto next loop
			fullData = flip(transpose(fullData));
		}
		
		return null;
	}


	//transposes (matrix operation) 2d array a. assumes a must be square
	public boolean[][] transpose(boolean[][] a) {
		boolean[][] b = new boolean[a.length][a.length];
		for(int x = 0; x < a.length; x++) {
			for(int y = 0; y < a.length; y++) {
				b[x][y] = a[y][x];
			}
		}
		return b;
	}

	//flips a over x axis
	public boolean[][] flip(boolean[][] a) {
		boolean[][] b = new boolean[a.length][a.length];
		for(int row = 0; row < a.length; row++) {
			b[row] = a[a.length - row - 1];
		}
		return b;
	}

	//flips a over y axis
	public boolean[][] hflip(boolean[][] a) {
		boolean[][] b = new boolean[a.length][a.length];
		for(int row = 0; row < a.length; row++) {
			for(int col = 0; col < a.length; col++) {
				b[row][col] = a[row][a.length - col - 1];
			}
		}
		return b;
	}

	public boolean compareCol(boolean[][] a, boolean[][] b, int colA, int colB) {
		for(int row = 0; row < 10; row++) {
			if(a[row][colA] != b[row][colB])
				return false;
		}
		return true;
	}

	public int count(boolean[][] a) {
		int counter = 0;
		for(boolean[] b : a)
			for(boolean c : b)
				if(c)
					counter++;
		return counter;
	}
	
	public int countMinusSeaMonsters(boolean[][] a) {
		//stream to deepcopy
		boolean[][] without = Arrays.stream(a).map(x -> x.clone()).toArray($ -> a.clone());
		
		//iterate over all possible indices - if sea monster found, set sea monster indices to false in without
		for(int row = 0; row < a.length; row++) {
			for(int col = 0; col < a.length; col++) {
				try {
					//hardcoded for sea monster indices, relative to current assuming current is head
					if(a[row][col] && a[row+1][col] && a[row+1][col+1] && a[row+1][col-1] && a[row+1][col-6] && a[row+1][col-7] && a[row+1][col-12] && a[row+1][col-13] && a[row+1][col-18] && a[row+2][col-2] && a[row+2][col-5] && a[row+2][col-8] && a[row+2][col-11] && a[row+2][col-14] && a[row+2][col-17]) {
						//set to false in without
						without[row][col] = false;
						without[row+1][col] = false;
						without[row+1][col+1] = false;
						without[row+1][col-1] = false;
						without[row+1][col-6] = false;
						without[row+1][col-7] = false;
						without[row+1][col-12] = false;
						without[row+1][col-13] = false;
						without[row+1][col-18] = false;
						without[row+2][col-2] = false;
						without[row+2][col-5] = false;
						without[row+2][col-8] = false;
						without[row+2][col-11] = false;
						without[row+2][col-14] = false;
						without[row+2][col-17] = false;
					}
				} catch (ArrayIndexOutOfBoundsException e) {
					continue;
				}
			}
		}
		return count(without);
	}
	
	public GridTile find(ArrayList<GridTile> a, int id) {
		for(GridTile g : a)
			if(g.id == id)
				return g;
		return null;
	}

	public static void main(String[] args) {
		input = Input.fetchInput(2020,20);
		DayRunner.run(new Day20());
	}
}
