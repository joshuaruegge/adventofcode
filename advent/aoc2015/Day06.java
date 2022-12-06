package advent.aoc2015;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

public class Day06 implements IDay {

	static String input;
	
	@Override
	public String part1() {
		//x,y grid of lights
		int[][] lights = new int[1000][1000];
		//iterate over input
		for(String s : input.split("\n")) {
			//split into words
			String[] parts = s.split(" ");
			if(parts[0].equals("toggle")) {
				//parse out numbers
				String[] coord1 = parts[1].split(",");
				String[] coord2 = parts[3].split(",");
				int x1 = Integer.parseInt(coord1[0]);
				int y1 = Integer.parseInt(coord1[1]);
				int x2 = Integer.parseInt(coord2[0]);
				int y2 = Integer.parseInt(coord2[1]);
				for(int x = x1; x <= x2; x++) {
					for(int y = y1; y <= y2; y++) {
						//invert
						lights[x][y] = Math.abs(lights[x][y] - 1);
					}
				}
			} else {
				//value to set locations to
				int set = (parts[1].equals("on") ? 1 : 0);
				//parse out numbers
				String[] coord1 = parts[2].split(",");
				String[] coord2 = parts[4].split(",");
				int x1 = Integer.parseInt(coord1[0]);
				int y1 = Integer.parseInt(coord1[1]);
				int x2 = Integer.parseInt(coord2[0]);
				int y2 = Integer.parseInt(coord2[1]);
				//set range to value
				for(int x = x1; x <= x2; x++) {
					for(int y = y1; y <= y2; y++) {
						lights[x][y] = set;
					}
				}
			}
		}
		int lightsOn = 0;
		for(int x = 0; x < 1000; x++) {
			for(int y = 0; y < 1000; y++) {
				lightsOn += lights[x][y];
			}
		}
		return Integer.toString(lightsOn);
	}

	@Override
	public String part2() {
		//exact same, just adjusted grid changes
		//x,y grid of lights
		int[][] lights = new int[1000][1000];
		//iterate over input
		for(String s : input.split("\n")) {
			//split into words
			String[] parts = s.split(" ");
			if(parts[0].equals("toggle")) {
				//parse out numbers
				String[] coord1 = parts[1].split(",");
				String[] coord2 = parts[3].split(",");
				int x1 = Integer.parseInt(coord1[0]);
				int y1 = Integer.parseInt(coord1[1]);
				int x2 = Integer.parseInt(coord2[0]);
				int y2 = Integer.parseInt(coord2[1]);
				for(int x = x1; x <= x2; x++) {
					for(int y = y1; y <= y2; y++) {
						//increase by 2
						lights[x][y] += 2;
					}
				}
			} else {
				//value to set locations to
				int set = (parts[1].equals("on") ? 1 : -1);
				//parse out numbers
				String[] coord1 = parts[2].split(",");
				String[] coord2 = parts[4].split(",");
				int x1 = Integer.parseInt(coord1[0]);
				int y1 = Integer.parseInt(coord1[1]);
				int x2 = Integer.parseInt(coord2[0]);
				int y2 = Integer.parseInt(coord2[1]);
				//set range to value
				for(int x = x1; x <= x2; x++) {
					for(int y = y1; y <= y2; y++) {
						//if going in positive direction or above zero
						//(not at risk of going negative)
						if(set == 1 || lights[x][y] > 0)
							lights[x][y] += set;
					}
				}
			}
		}
		int lightsOn = 0;
		for(int x = 0; x < 1000; x++) {
			for(int y = 0; y < 1000; y++) {
				lightsOn += lights[x][y];
			}
		}
		return Integer.toString(lightsOn);
	}

	public static void main(String[] args) {
		input = Input.fetchInput(2015,6);
		DayRunner.run(new Day06());
	}

}
