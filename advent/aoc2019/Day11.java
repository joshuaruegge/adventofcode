package advent.aoc2019;

import java.util.ArrayList;
import java.util.HashSet;

import advent.utilities.general.Coord;
import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.utils2019.IntCodeComputer;

public class Day11 implements IDay {

	static String input = "3,8,1005,8,344,1106,0,11,0,0,0,104,1,104,0,3,8,1002,8,-1,10,1001,10,1,10,4,10,108,1,8,10,4,10,101,0,8,28,1006,0,40,2,1009,2,10,1,1108,13,10,1,1007,6,10,3,8,1002,8,-1,10,101,1,10,10,4,10,1008,8,1,10,4,10,1002,8,1,66,1006,0,91,2,103,5,10,1006,0,12,3,8,102,-1,8,10,1001,10,1,10,4,10,1008,8,0,10,4,10,102,1,8,98,3,8,102,-1,8,10,1001,10,1,10,4,10,1008,8,0,10,4,10,102,1,8,120,1,1001,15,10,2,102,4,10,3,8,1002,8,-1,10,101,1,10,10,4,10,108,0,8,10,4,10,1002,8,1,149,1,106,9,10,1,5,5,10,1,1106,6,10,2,5,15,10,3,8,102,-1,8,10,101,1,10,10,4,10,108,1,8,10,4,10,1001,8,0,187,2,1106,9,10,2,9,13,10,3,8,1002,8,-1,10,101,1,10,10,4,10,1008,8,1,10,4,10,1002,8,1,218,1,1106,3,10,1006,0,13,2,1005,15,10,2,1006,19,10,3,8,102,-1,8,10,101,1,10,10,4,10,108,0,8,10,4,10,1002,8,1,254,2,1108,14,10,1006,0,33,1,7,20,10,2,105,6,10,3,8,1002,8,-1,10,1001,10,1,10,4,10,1008,8,1,10,4,10,101,0,8,292,1006,0,82,2,6,7,10,3,8,102,-1,8,10,101,1,10,10,4,10,108,0,8,10,4,10,1001,8,0,320,1006,0,11,101,1,9,9,1007,9,979,10,1005,10,15,99,109,666,104,0,104,1,21102,932700857100,1,1,21101,0,361,0,1106,0,465,21102,825599210392,1,1,21102,1,372,0,1106,0,465,3,10,104,0,104,1,3,10,104,0,104,0,3,10,104,0,104,1,3,10,104,0,104,1,3,10,104,0,104,0,3,10,104,0,104,1,21101,29195529219,0,1,21101,419,0,0,1106,0,465,21101,0,235324673063,1,21102,1,430,0,1105,1,465,3,10,104,0,104,0,3,10,104,0,104,0,21102,988225098508,1,1,21102,453,1,0,1106,0,465,21102,988753318756,1,1,21101,464,0,0,1106,0,465,99,109,2,22101,0,-1,1,21102,40,1,2,21101,0,496,3,21101,0,486,0,1105,1,529,109,-2,2106,0,0,0,1,0,0,1,109,2,3,10,204,-1,1001,491,492,507,4,0,1001,491,1,491,108,4,491,10,1006,10,523,1101,0,0,491,109,-2,2106,0,0,0,109,4,2102,1,-1,528,1207,-3,0,10,1006,10,546,21102,0,1,-3,21201,-3,0,1,22102,1,-2,2,21101,0,1,3,21102,565,1,0,1105,1,570,109,-4,2105,1,0,109,5,1207,-3,1,10,1006,10,593,2207,-4,-2,10,1006,10,593,22102,1,-4,-4,1105,1,661,22101,0,-4,1,21201,-3,-1,2,21202,-2,2,3,21101,612,0,0,1106,0,570,22101,0,1,-4,21102,1,1,-1,2207,-4,-2,10,1006,10,631,21101,0,0,-1,22202,-2,-1,-2,2107,0,-3,10,1006,10,653,21202,-1,1,1,21101,0,653,0,106,0,528,21202,-2,-1,-2,22201,-4,-2,-4,109,-5,2105,1,0";
	
	@Override
	public String part1() {
		//initialize intcode
		ArrayList<Long> program = new ArrayList<Long>();
		String[] parts = input.split(",");
		for(String s : parts)
			program.add(Long.parseLong(s));
		IntCodeComputer bot = new IntCodeComputer(program);
		//keep track of currently white panels (and by exclusion, black panels)
		//as well as position, facing, and a list of panels that were ever painted white
		HashSet<Coord> whitePanels = new HashSet<Coord>();
		Coord facing = new Coord(0,-1);
		Coord pos = new Coord(0,0);
		HashSet<Coord> everPaintedPanels = new HashSet<Coord>();
		while(!bot.halted) {
			//input current value
			if(whitePanels.contains(pos)) {
				bot.input(1);
			} else {
				bot.input(0);
			}
			bot.run();
			//update panel based on bot output
			long firstOut = bot.output();
			if(firstOut == 0 && whitePanels.contains(pos)) {
				whitePanels.remove(pos);
			} 
			if(firstOut == 1 && !whitePanels.contains(pos)) {
				if(!everPaintedPanels.contains(pos)) {
					everPaintedPanels.add(pos.copy());
				}
				whitePanels.add(pos.copy());
			}
			//rotate direction based on bot output
			long secondOut = bot.output();
			if(secondOut == 1) {
				facing = facing.right();
			} else {
				facing = facing.left();
			}
			//move in newly rotated direction
			pos.sumSelf(facing);
		}
		//return number of panels ever painted
		return Integer.toString(everPaintedPanels.size());
	}

	@Override
	public String part2() {
		//same initialization
		ArrayList<Long> program = new ArrayList<Long>();
		String[] parts = input.split(",");
		for(String s : parts)
			program.add(Long.parseLong(s));
		IntCodeComputer bot = new IntCodeComputer(program);
		ArrayList<Coord> whitePanels = new ArrayList<Coord>();
		Coord facing = new Coord(0,-1);
		Coord pos = new Coord(0,0);
		//start on white panel
		whitePanels.add(new Coord(0,0));
		while(!bot.halted) {
			if(whitePanels.contains(pos)) {
				bot.input(1);
			} else {
				bot.input(0);
			}
			bot.run();
			long firstOut = bot.output();
			if(firstOut == 0 && whitePanels.contains(pos)) {
				whitePanels.remove(pos);
			} 
			if(firstOut == 1 && !whitePanels.contains(pos)) {
				whitePanels.add(pos.copy());
			}
			long secondOut = bot.output();
			if(secondOut == 1) {
				facing = facing.right();
			} else {
				facing = facing.left();
			}
			pos.sumSelf(facing);
		}
		//size painted area to properly print letters
		int minX = Integer.MAX_VALUE;
		int minY = Integer.MAX_VALUE;
		int maxX = Integer.MIN_VALUE;
		int maxY = Integer.MIN_VALUE;
		for(Coord c : whitePanels) {
			if(c.x < minX)
				minX = c.x;
			if(c.x > maxX)
				maxX = c.x;
			if(c.y < minY)
				minY = c.y;
			if(c.y > maxY)
				maxY = c.y;
		}
		//iterate through potential letter space, printing painted and unpainted tiles
		for(int y = minY; y <= maxY; y++) {
			for(int x = minX; x <= maxX; x++) {
				if(whitePanels.contains(new Coord(x,y))) {
					System.out.print("#");
				} else {
					System.out.print(".");
				}
			}
			System.out.println();
		}
		return "N/A, see output";
	}
	
	public static void main(String[] args) {
		DayRunner.run(new Day11());
	}

}
