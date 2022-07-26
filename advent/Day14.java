package advent.aoc2017;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;

import advent.utilities.general.Coord;
import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;

public class Day14 implements IDay {

	String input = "hfdlxzhv";
	
	@Override
	public String part1() {
		boolean[][] disk = new boolean[128][128];
		for(int row = 0; row < 128; row++) {
			String key = input + "-" + row;
			String hash = knotHash(key);
			//use BigInteger to convert hex to binary for bit checking
			BigInteger num = new BigInteger(hash,16);
			for(int col = 0; col < 128; col++) {
				disk[row][col] = num.testBit(col);
			}
		}
		int used = 0;
		for(boolean[] b : disk)
			for(boolean a : b)
				if(a)
					used++;
		return Integer.toString(used);
	}

	//reused part 2 code from day 10 for portable knothashing
	public static String knotHash(String key) {
		final Integer[] constantLengths = {17,31,73,47,23};
		ArrayList<Integer> lengths = new ArrayList<Integer>();
		//careful - ascii values now
		for(char c : key.toCharArray())
			lengths.add((int) c);
		lengths.addAll(Arrays.asList(constantLengths));
		
		
		ArrayList<Integer> knotHash = new ArrayList<Integer>();
		for(int i = 0; i < 256; i++)
			knotHash.add(i);
		//current position
		int current = 0;
		//skip size
		int skip = 0;
		//64 rounds
		for(int x = 0; x < 64; x++) {
			//iterate over lengths
			for(int length : lengths) {
				 //determine end of reversed section
				int end = current + length;
				//fix to be circular if necessary
				if(end > knotHash.size()) {
					end %= knotHash.size();
				}
				 
				ArrayList<Integer> reverse = new ArrayList<Integer>();
				//populate values into reverse array 
				if(end < current) {
					//append circularly - current to max, then 0 to end
					for(int i = current; i < knotHash.size(); i++)
						reverse.add(knotHash.get(i));
					for(int i = 0; i < end; i++) {
						reverse.add(knotHash.get(i));
					}
				} else {
					for(int i = current; i < end; i++) {
						reverse.add(knotHash.get(i));
					}
				}
				
				Collections.reverse(reverse);
				
				//put reversed values back in knotHash
				if(end < current) {
					int count = 0;
					for(int i = current; i < knotHash.size(); i++) {
						knotHash.set(i, reverse.get(count));
						count++;
					}
					for(int i = 0; i < end; i++) {
						knotHash.set(i, reverse.get(count));
						count++;
					}
				} else {
					int count = 0;
					for(int i = current; i < end; i++) {
						knotHash.set(i, reverse.get(count));
						count++;
					}
				}
				//move current
				current += length + skip;
				if(current >= knotHash.size())
					current %= knotHash.size();
				
				//increment skip
				skip++;
			}		
		}
		
		
		String dense = "";
		for(int i = 0; i < 16; i++) {
			int startIndex = i * 16;
			int xor = knotHash.get(startIndex);
			for(int j = 1; j < 16; j++) {
				xor = xor ^ knotHash.get(startIndex + j);
			}
			//do each digit separately (to auto-append zeros if necessary
			dense += Integer.toHexString(xor >> 4);
			dense += Integer.toHexString(xor & 15);
		}
		
		return dense;
	}
	
	@Override
	public String part2() {
		//store disk as two-dimensional boolean array - row-column
		boolean[][] disk = new boolean[128][128];
		//iterate over rows
		for(int row = 0; row < 128; row++) {
			String key = input + "-" + row;
			String hash = knotHash(key);
			//use BigInteger to convert hex to binary for bit checking
			BigInteger num = new BigInteger(hash,16);
			for(int col = 0; col < 128; col++) {
				disk[row][col] = num.testBit(col);
			}
		}
		//now, use floodfill to get regions
		HashSet<HashSet<Coord>> regions = new HashSet<HashSet<Coord>>();
		boolean[][] flags = new boolean[128][128];
		for(int row = 0; row < 128; row++) {
			for(int col = 0; col < 128; col++) {
				if(!flags[row][col] && disk[row][col]) {
					HashSet<Coord> region = regionAt(row,col,disk);
					for(Coord c : region)
						flags[c.x][c.y] = true;
					regions.add(region);
				}
			}
		}
		return Integer.toString(regions.size());
	}
	
	//helper method - gets region connected to (row, column)
	public HashSet<Coord> regionAt(int x, int y, boolean[][] disk) {
		HashSet<Coord> region = new HashSet<Coord>();
		boolean[][] flags = new boolean[128][128];
		
		LinkedList<Coord> queue = new LinkedList<Coord>();
		queue.add(new Coord(x,y));
		flags[x][y] = true;
		
		while(queue.size() > 0) {
			Coord cur = queue.remove(0);
			region.add(cur);
			
			for(Coord c : cur.directNeighbors()) {
				if(!queue.contains(c) && !region.contains(c) && c.x > -1 && c.y > -1 && c.x < 128 && c.y < 128 && !flags[c.x][c.y] && disk[c.x][c.y]) {
					flags[c.x][c.y] = true;
					queue.add(c);
				}
			}
		}
		return region;
	}

	public static void main(String[] args) {
		DayRunner.run(new Day14());
	}

}
