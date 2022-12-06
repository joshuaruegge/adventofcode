package advent.aoc2016;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

public class Day08 implements IDay {

	static String input;
	
	@Override
	public String part1() {
		//pre-set size as defined in puzzle
		boolean[][] pixels = new boolean[50][6];
	
		for(String s : input.split("\n")) {
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
	
		for(String s : input.split("\n")) {
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
		input = Input.fetchInput(2016,8);
		DayRunner.run(new Day08());
	}

}
