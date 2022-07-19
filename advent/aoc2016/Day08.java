package advent.aoc2016;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;

public class Day08 implements IDay {

	String input = "rect 1x1\r\n"
			+ "rotate row y=0 by 5\r\n"
			+ "rect 1x1\r\n"
			+ "rotate row y=0 by 6\r\n"
			+ "rect 1x1\r\n"
			+ "rotate row y=0 by 5\r\n"
			+ "rect 1x1\r\n"
			+ "rotate row y=0 by 2\r\n"
			+ "rect 1x1\r\n"
			+ "rotate row y=0 by 5\r\n"
			+ "rect 2x1\r\n"
			+ "rotate row y=0 by 2\r\n"
			+ "rect 1x1\r\n"
			+ "rotate row y=0 by 4\r\n"
			+ "rect 1x1\r\n"
			+ "rotate row y=0 by 3\r\n"
			+ "rect 2x1\r\n"
			+ "rotate row y=0 by 7\r\n"
			+ "rect 3x1\r\n"
			+ "rotate row y=0 by 3\r\n"
			+ "rect 1x1\r\n"
			+ "rotate row y=0 by 3\r\n"
			+ "rect 1x2\r\n"
			+ "rotate row y=1 by 13\r\n"
			+ "rotate column x=0 by 1\r\n"
			+ "rect 2x1\r\n"
			+ "rotate row y=0 by 5\r\n"
			+ "rotate column x=0 by 1\r\n"
			+ "rect 3x1\r\n"
			+ "rotate row y=0 by 18\r\n"
			+ "rotate column x=13 by 1\r\n"
			+ "rotate column x=7 by 2\r\n"
			+ "rotate column x=2 by 3\r\n"
			+ "rotate column x=0 by 1\r\n"
			+ "rect 17x1\r\n"
			+ "rotate row y=3 by 13\r\n"
			+ "rotate row y=1 by 37\r\n"
			+ "rotate row y=0 by 11\r\n"
			+ "rotate column x=7 by 1\r\n"
			+ "rotate column x=6 by 1\r\n"
			+ "rotate column x=4 by 1\r\n"
			+ "rotate column x=0 by 1\r\n"
			+ "rect 10x1\r\n"
			+ "rotate row y=2 by 37\r\n"
			+ "rotate column x=19 by 2\r\n"
			+ "rotate column x=9 by 2\r\n"
			+ "rotate row y=3 by 5\r\n"
			+ "rotate row y=2 by 1\r\n"
			+ "rotate row y=1 by 4\r\n"
			+ "rotate row y=0 by 4\r\n"
			+ "rect 1x4\r\n"
			+ "rotate column x=25 by 3\r\n"
			+ "rotate row y=3 by 5\r\n"
			+ "rotate row y=2 by 2\r\n"
			+ "rotate row y=1 by 1\r\n"
			+ "rotate row y=0 by 1\r\n"
			+ "rect 1x5\r\n"
			+ "rotate row y=2 by 10\r\n"
			+ "rotate column x=39 by 1\r\n"
			+ "rotate column x=35 by 1\r\n"
			+ "rotate column x=29 by 1\r\n"
			+ "rotate column x=19 by 1\r\n"
			+ "rotate column x=7 by 2\r\n"
			+ "rotate row y=4 by 22\r\n"
			+ "rotate row y=3 by 5\r\n"
			+ "rotate row y=1 by 21\r\n"
			+ "rotate row y=0 by 10\r\n"
			+ "rotate column x=2 by 2\r\n"
			+ "rotate column x=0 by 2\r\n"
			+ "rect 4x2\r\n"
			+ "rotate column x=46 by 2\r\n"
			+ "rotate column x=44 by 2\r\n"
			+ "rotate column x=42 by 1\r\n"
			+ "rotate column x=41 by 1\r\n"
			+ "rotate column x=40 by 2\r\n"
			+ "rotate column x=38 by 2\r\n"
			+ "rotate column x=37 by 3\r\n"
			+ "rotate column x=35 by 1\r\n"
			+ "rotate column x=33 by 2\r\n"
			+ "rotate column x=32 by 1\r\n"
			+ "rotate column x=31 by 2\r\n"
			+ "rotate column x=30 by 1\r\n"
			+ "rotate column x=28 by 1\r\n"
			+ "rotate column x=27 by 3\r\n"
			+ "rotate column x=26 by 1\r\n"
			+ "rotate column x=23 by 2\r\n"
			+ "rotate column x=22 by 1\r\n"
			+ "rotate column x=21 by 1\r\n"
			+ "rotate column x=20 by 1\r\n"
			+ "rotate column x=19 by 1\r\n"
			+ "rotate column x=18 by 2\r\n"
			+ "rotate column x=16 by 2\r\n"
			+ "rotate column x=15 by 1\r\n"
			+ "rotate column x=13 by 1\r\n"
			+ "rotate column x=12 by 1\r\n"
			+ "rotate column x=11 by 1\r\n"
			+ "rotate column x=10 by 1\r\n"
			+ "rotate column x=7 by 1\r\n"
			+ "rotate column x=6 by 1\r\n"
			+ "rotate column x=5 by 1\r\n"
			+ "rotate column x=3 by 2\r\n"
			+ "rotate column x=2 by 1\r\n"
			+ "rotate column x=1 by 1\r\n"
			+ "rotate column x=0 by 1\r\n"
			+ "rect 49x1\r\n"
			+ "rotate row y=2 by 34\r\n"
			+ "rotate column x=44 by 1\r\n"
			+ "rotate column x=40 by 2\r\n"
			+ "rotate column x=39 by 1\r\n"
			+ "rotate column x=35 by 4\r\n"
			+ "rotate column x=34 by 1\r\n"
			+ "rotate column x=30 by 4\r\n"
			+ "rotate column x=29 by 1\r\n"
			+ "rotate column x=24 by 1\r\n"
			+ "rotate column x=15 by 4\r\n"
			+ "rotate column x=14 by 1\r\n"
			+ "rotate column x=13 by 3\r\n"
			+ "rotate column x=10 by 4\r\n"
			+ "rotate column x=9 by 1\r\n"
			+ "rotate column x=5 by 4\r\n"
			+ "rotate column x=4 by 3\r\n"
			+ "rotate row y=5 by 20\r\n"
			+ "rotate row y=4 by 20\r\n"
			+ "rotate row y=3 by 48\r\n"
			+ "rotate row y=2 by 20\r\n"
			+ "rotate row y=1 by 41\r\n"
			+ "rotate column x=47 by 5\r\n"
			+ "rotate column x=46 by 5\r\n"
			+ "rotate column x=45 by 4\r\n"
			+ "rotate column x=43 by 5\r\n"
			+ "rotate column x=41 by 5\r\n"
			+ "rotate column x=33 by 1\r\n"
			+ "rotate column x=32 by 3\r\n"
			+ "rotate column x=23 by 5\r\n"
			+ "rotate column x=22 by 1\r\n"
			+ "rotate column x=21 by 2\r\n"
			+ "rotate column x=18 by 2\r\n"
			+ "rotate column x=17 by 3\r\n"
			+ "rotate column x=16 by 2\r\n"
			+ "rotate column x=13 by 5\r\n"
			+ "rotate column x=12 by 5\r\n"
			+ "rotate column x=11 by 5\r\n"
			+ "rotate column x=3 by 5\r\n"
			+ "rotate column x=2 by 5\r\n"
			+ "rotate column x=1 by 5";
	
	@Override
	public String part1() {
		//pre-set size as defined in puzzle
		boolean[][] pixels = new boolean[50][6];
	
		for(String s : input.split("\r\n")) {
			String[] parts = s.split(" ");
			if(parts.length == 2) {
				//parse out numbers
				String[] nums = parts[1].split("x");
				int y = Integer.parseInt(nums[1]);
				int x = Integer.parseInt(nums[0]);
				for(int i = 0; i < y; i++) {
					for(int j = 0; j < x; j++) {
						pixels[j][i] = true;
					}
				}
			} else {
				if(parts[1].equals("row")) {
					int y = Integer.parseInt(parts[2].split("=")[1]);
					for(int i = 0; i < Integer.parseInt(parts[4]); i++) { 
						boolean buffer = pixels[pixels.length - 1][y];
						for(int x = pixels.length - 1; x > 0; x--) {
							pixels[x][y] = pixels[x-1][y];
						}
						pixels[0][y] = buffer;
					}
				} else {
					int x = Integer.parseInt(parts[2].split("=")[1]);
					for(int i = 0; i < Integer.parseInt(parts[4]); i++) {
						boolean buffer = pixels[x][pixels[x].length - 1];
						for(int y = pixels[x].length - 1; y > 0; y--) {
							pixels[x][y] = pixels[x][y-1];
						}
						pixels[x][0] = buffer;
					}
				}
			}
			
		}
		//count active pixels
		int pixel = 0;
		for(boolean[] a : pixels) {
			for(boolean b : a)
				if(b)
					pixel++;
		}
		return Integer.toString(pixel);
	}

	@Override
	public String part2() {
		//pre-set size as defined in puzzle
		boolean[][] pixels = new boolean[50][6];
	
		for(String s : input.split("\r\n")) {
			String[] parts = s.split(" ");
			if(parts.length == 2) {
				//parse out numbers
				String[] nums = parts[1].split("x");
				int y = Integer.parseInt(nums[1]);
				int x = Integer.parseInt(nums[0]);
				for(int i = 0; i < y; i++) {
					for(int j = 0; j < x; j++) {
						pixels[j][i] = true;
					}
				}
			} else {
				if(parts[1].equals("row")) {
					int y = Integer.parseInt(parts[2].split("=")[1]);
					for(int i = 0; i < Integer.parseInt(parts[4]); i++) { 
						boolean buffer = pixels[pixels.length - 1][y];
						for(int x = pixels.length - 1; x > 0; x--) {
							pixels[x][y] = pixels[x-1][y];
						}
						pixels[0][y] = buffer;
					}
				} else {
					int x = Integer.parseInt(parts[2].split("=")[1]);
					for(int i = 0; i < Integer.parseInt(parts[4]); i++) {
						boolean buffer = pixels[x][pixels[x].length - 1];
						for(int y = pixels[x].length - 1; y > 0; y--) {
							pixels[x][y] = pixels[x][y-1];
						}
						pixels[x][0] = buffer;
					}
				}
			}
			
		}
		//instead of counting pixels, print pattern
		for(int y = 0; y < pixels[0].length; y++) {
			for(int x = 0; x < pixels.length; x++) {
				if(pixels[x][y]) {
					System.out.print("X");
				} else {
					System.out.print(".");
				}
			}
			System.out.println();
		}
		return "N/A, see output";
	}

	public static void main(String[] args) {
		DayRunner.run(new Day08());
	}

}
