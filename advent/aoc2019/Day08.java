package advent.aoc2019;

import java.util.ArrayList;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

public class Day08 implements IDay {
	
	static String input;
	
	@Override
	public String part1() {
		int index = 0;
		ArrayList<int[][]> layers = new ArrayList<int[][]>();
		//use index to track position in input string, build layer arrays based on dimensions provided
		while(index < input.length()) {
			int[][] layer = new int[6][25];
			for(int i = 0; i < 6; i++) {
				for(int j = 0; j < 25; j++) {
					layer[i][j] = Integer.parseInt(input.charAt(index) + "");
					index++;
				}
			}
			layers.add(layer);
		}
		//iterate over layers, find layer with fewest zeroes
		int bestLayerScore = Integer.MAX_VALUE;
		int[][] bestLayer = null;
		for(int[][] layer : layers) {
			int zeroCount = 0;
			for(int[] y : layer) {
				for(int x : y) {
					if(x==0)
						zeroCount++;
				}
			}
			if(zeroCount < bestLayerScore) {
				bestLayerScore = zeroCount;
				bestLayer = layer;
			}
		}
		//total ones and twos in best layer
		int oneCount = 0;
		int twoCount = 0;
		for(int[] y : bestLayer) {
			for(int x : y) {
				if(x == 1) {
					oneCount++;
				}
				if(x == 2) {
					twoCount++;
				}
			}
		}
		return Integer.toString(oneCount * twoCount);
		
	}

	@Override
	public String part2() {
		int index = 0;
		ArrayList<int[][]> layers = new ArrayList<int[][]>();
		while(index < input.length()) {
			int[][] layer = new int[6][25];
			for(int i = 0; i < 6; i++) {
				for(int j = 0; j < 25; j++) {
					layer[i][j] = Integer.parseInt(input.charAt(index) + "");
					index++;
				}
			}
			layers.add(layer);
		}
		
		int[][] finalMap = new int[6][25];
		int[][] mapFlags = new int[6][25];
		
		//start at the top and go down, setting the final map to the first nontransparent layer found
		for(int[][] layer : layers) {
			for(int x = 0; x < 6; x++) {
				for(int y = 0; y < 25; y++) {
					//if we havent' checked this space and the layer value isn't transparent
					if(mapFlags[x][y] == 0 && layer[x][y] != 2) { 
						finalMap[x][y] = layer[x][y];
						mapFlags[x][y] = 1;
					}
				}
			}
			//once there are no remaining spaces to set, break out of the loop
			if(!contains(mapFlags,0))
				break;
		}
		for(int[] a : finalMap) {
			for(int b : a) {
				if(b == 1)
					System.out.print("#");
				else
					System.out.print(".");
			}
			System.out.println();
		}
		
		return "N/A, examine output";
	}

	//2 dimensional array search helper method
	//determines whether any int in 2 dimensional array equals i
	public static boolean contains(int[][] a, int i) {
		for(int[] a1 : a) {
			for(int a2 : a1) {
				if(a2 == i)
					return true;
			}
		}
		return false;
	}

	public static void main(String[] args) {
		input = Input.fetchInput(2019,8).trim();
		DayRunner.run(new Day08());
	}
}
