package advent.aoc2019;

import java.util.ArrayList;
import java.util.Arrays;

import advent.utilities.general.Coord;
import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;
import advent.utilities.utils2019.IntCodeComputer;

public class Day19 implements IDay {

	static String input;
	
	static ArrayList<Long> program;

	@Override
	public String part1() {
		program = new ArrayList<Long>(Arrays.stream(input.split(",")).map(x -> Long.parseLong(x)).toList());
		
		//for each coordinate in range, input to program. if in tractor beam, increment counter
		int affected = 0;
		for(int x = 0; x < 50; x++) {
			for(int y = 0; y < 50; y++) {
				IntCodeComputer i = new IntCodeComputer(program);
				i.input(x);
				i.input(y);
				i.run();
				long out = i.output();
				if(out == 1) {
					affected++;
				}
			}
		}
		
		return Integer.toString(affected);
	}

	@Override
	public String part2() {
		program = new ArrayList<Long>(Arrays.stream(input.split(",")).map(x -> Long.parseLong(x)).toList());
		
		//arbitrary starting coordinate of (0,10) - inputs have weird variation near top but level out quickly
		Coord current = new Coord(0,10);
		Coord target = null;
		//trace the bottom edge of the input by moving down and right
		//for new positions, assume they are the bottom-left corner of a potential 100x100 square
		//check if the top-right corner of this potential square is also in the beam
		//if it is, all other points are automatically within the beam due to beam shape and square is located
		//calculate and return top-left corner of square
		while(true) {
			if(tractor(current)) {
				Coord corner = new Coord(current.x + 99, current.y - 99);
				if(tractor(corner)) {
					target = new Coord(current.x, current.y - 99);
					break;
				}
				//move down
				current.sumSelf(Coord.DOWN);
			} else {
				//move right
				current.sumSelf(Coord.RIGHT);
			}
		}
		return Integer.toString(target.x * 10000 + target.y);		
	}

	//determine whether the given coordinate is within the tractor beam
	public static boolean tractor(Coord c) {
		IntCodeComputer i = new IntCodeComputer(program);
		i.input(c.x);
		i.input(c.y);
		i.run();
		return i.output() == 1;
	}

	public static void main(String[] args) {
		input = Input.fetchInput(2019,19).trim();
		DayRunner.run(new Day19());
	}
}
