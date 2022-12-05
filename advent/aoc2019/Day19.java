package advent.aoc2019;

import java.util.ArrayList;
import java.util.Arrays;

import advent.utilities.general.Coord;
import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.utils2019.IntCodeComputer;

public class Day19 implements IDay {

	static String input = "109,424,203,1,21101,0,11,0,1105,1,282,21101,18,0,0,1105,1,259,1202,1,1,221,203,1,21102,1,31,0,1106,0,282,21102,38,1,0,1106,0,259,21002,23,1,2,22102,1,1,3,21102,1,1,1,21102,1,57,0,1105,1,303,2102,1,1,222,20101,0,221,3,21001,221,0,2,21102,259,1,1,21102,1,80,0,1106,0,225,21102,62,1,2,21101,91,0,0,1105,1,303,2101,0,1,223,21001,222,0,4,21101,0,259,3,21101,0,225,2,21101,0,225,1,21101,0,118,0,1105,1,225,20102,1,222,3,21101,94,0,2,21102,133,1,0,1105,1,303,21202,1,-1,1,22001,223,1,1,21101,0,148,0,1105,1,259,1202,1,1,223,20101,0,221,4,21001,222,0,3,21102,17,1,2,1001,132,-2,224,1002,224,2,224,1001,224,3,224,1002,132,-1,132,1,224,132,224,21001,224,1,1,21101,195,0,0,105,1,109,20207,1,223,2,20101,0,23,1,21102,-1,1,3,21101,214,0,0,1106,0,303,22101,1,1,1,204,1,99,0,0,0,0,109,5,2101,0,-4,249,22102,1,-3,1,22101,0,-2,2,21201,-1,0,3,21102,1,250,0,1106,0,225,22101,0,1,-4,109,-5,2105,1,0,109,3,22107,0,-2,-1,21202,-1,2,-1,21201,-1,-1,-1,22202,-1,-2,-2,109,-3,2106,0,0,109,3,21207,-2,0,-1,1206,-1,294,104,0,99,22101,0,-2,-2,109,-3,2106,0,0,109,5,22207,-3,-4,-1,1206,-1,346,22201,-4,-3,-4,21202,-3,-1,-1,22201,-4,-1,2,21202,2,-1,-1,22201,-4,-1,1,21201,-2,0,3,21101,343,0,0,1106,0,303,1105,1,415,22207,-2,-3,-1,1206,-1,387,22201,-3,-2,-3,21202,-2,-1,-1,22201,-3,-1,3,21202,3,-1,-1,22201,-3,-1,2,22102,1,-4,1,21102,384,1,0,1105,1,303,1105,1,415,21202,-4,-1,-4,22201,-4,-3,-4,22202,-3,-2,-2,22202,-2,-4,-4,22202,-3,-2,-3,21202,-4,-1,-2,22201,-3,-2,1,21201,1,0,-4,109,-5,2105,1,0";
	
	static ArrayList<Long> program;
	
	public static void main(String[] args) {
		DayRunner.run(new Day19());
	}
	
	//determine whether the given coordinate is within the tractor beam
	public static boolean tractor(Coord c) {
		IntCodeComputer i = new IntCodeComputer(program);
		i.input(c.x);
		i.input(c.y);
		i.run();
		return i.output() == 1;
	}

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

}
