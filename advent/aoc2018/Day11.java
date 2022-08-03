package advent.aoc2018;

import advent.utilities.general.Coord;
import advent.utilities.general.Coord3;
import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;

public class Day11 implements IDay {

	String input = "7672";
	
	@Override
	public String part1() {
		int serial = Integer.parseInt(input);
		int[][] power = new int[301][301];
		for(int y = 0; y < 301; y++) {
			for(int x = 0; x < 301; x++) {
				int rack = x + 10;
				int cellPower = ((rack * y) + serial) * rack;
				cellPower = (cellPower / 100) % 10;
				cellPower -= 5;
				power[y][x] = cellPower;
			}
		}
		Coord highest = new Coord(0,0);
		int highestPower = 0;
		for(int y = 0; y < 299; y++) {
			for(int x = 0; x < 299; x++) {
				int totalPower = 0;
				for(int inY = y; inY < y+3; inY++) {
					for(int inX = x; inX < x+3; inX++) {
						totalPower += power[inY][inX];
					}
				}
				if(totalPower > highestPower) {
					highestPower = totalPower;
					highest = new Coord(x,y);
				}
			}
		}
		return highest.toString();
	}

	@Override
	public String part2() {
		int serial = Integer.parseInt(input);
		//we're gonna replace our power table with something called a "summed-area table" or "partial sums table"
		//https://en.wikipedia.org/wiki/Summed-area_table
		//in such a table, the value at (y,x) is the sum of itself and all values left and above it
		//therefore, we can use this to calculate sum of a range very easily
		int[][] sums = new int[301][301];
		for(int y = 1; y < 301; y++) {
			for(int x = 1; x < 301; x++) {
				int rack = x + 10;
				int cellPower = ((rack * y) + serial) * rack;
				cellPower = (cellPower / 100) % 10;
				cellPower -= 5;
				//value at (x,y) is power + (x,y-1) + (x-1,y) - (x-1,y-1)
				sums[x][y] = cellPower + sums[x][y-1] + sums[x-1][y] - sums[x-1][y-1];
			}
		}
		Coord3 highest = new Coord3(0,0,0);
		int highestPower = 0;
		for(int size = 0; size < 300; size++) {
			//use x,y as ending indices
			//this avoids iterating through positions that would be too small anyways
			for(int y = size; y < 301; y++) {
				for(int x = size; x < 301; x++) {
					//wikipedia: sum of area (x,y) to (x',y') is (x',y') - (x,y') - (x',y) + (x,y)
					//in this case, x and y are actually x' and y', and x-s and y-s are x and y
					int totalPower = sums[x][y] - sums[x-size][y] - sums[x][y-size] + sums[x-size][y-size];
					if(totalPower > highestPower) {
						highestPower = totalPower;
						//our left corner bound is always off-by-one
						highest = new Coord3(x-size+1,y-size+1,size);
					}
				}
			}
		}
		return highest.toString();
	}

	public static void main(String[] args) {
		DayRunner.run(new Day11());
	}

}
