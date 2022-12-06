package advent.aoc2017;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

public class Day11 implements IDay {

	static String input;
	
	@Override
	public String part1() {
		//hexagonal coordinates use an x-y-z system, but each direction modifies two at once
		//curious? https://www.redblobgames.com/grids/hexagons/
		int x = 0;
		int y = 0;
		int z = 0;
		for(String s : input.split(",")) {
			switch(s) {
			case "n":
				y++;
				z--;
				break;
			case "s":
				y--;
				z++;
				break;
			case "ne":
				x++;
				z--;
				break;
			case "sw":
				x--;
				z++;
				break;
			case "nw":
				x--;
				y++;
				break;
			case "se":
				x++;
				y--;
				break;
			}
		}
		//hexagonal version of Manhattan distance
		return Integer.toString((Math.abs(x) + Math.abs(y) + Math.abs(z))/2);
	}

	@Override
	public String part2() {
		//hexagonal coordinates use an x-y-z system, but each direction modifies two at once
		int x = 0;
		int y = 0;
		int z = 0;
		
		int max = 0;
		for(String s : input.split(",")) {
			switch(s) {
			case "n":
				y++;
				z--;
				break;
			case "s":
				y--;
				z++;
				break;
			case "ne":
				x++;
				z--;
				break;
			case "sw":
				x--;
				z++;
				break;
			case "nw":
				x--;
				y++;
				break;
			case "se":
				x++;
				y--;
				break;
			}
			int dist = (Math.abs(x) + Math.abs(y) + Math.abs(z))/2;
			if(dist > max)
				max = dist;
		}
		
		return Integer.toString(max);
	}

	public static void main(String[] args) {
		input = Input.fetchInput(2017,11).replace("\n","");
		DayRunner.run(new Day11());
	}

}
