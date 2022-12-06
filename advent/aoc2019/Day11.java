package advent.aoc2019;

import java.util.ArrayList;
import java.util.HashSet;

import advent.utilities.general.Coord;
import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;
import advent.utilities.utils2019.IntCodeComputer;

public class Day11 implements IDay {

	static String input;
	
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
		input = Input.fetchInput(2019,11).trim();
		DayRunner.run(new Day11());
	}

}
