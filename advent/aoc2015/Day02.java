package advent.aoc2015;

import java.util.ArrayList;
import java.util.Arrays;

import advent.utilities.general.Coord3;
import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

public class Day02 implements IDay {

	static String input;
	
	@Override
	public String part1() {
		//parse presents into improvised tuples using Coord3
		ArrayList<Coord3> presents = new ArrayList<Coord3>();

		for(String s : input.split("\n")) {
			//split into individual dimensions adn parse
			String[] parts = s.split("x");
			System.out.println(Arrays.toString(parts));
			presents.add(new Coord3(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]),Integer.parseInt(parts[2])));
		}
		//wrapping total
		int total = 0;
		for(Coord3 present: presents) {
			//calculate areas
			int side1 = present.x * present.y;
			int side2 = present.y * present.z;
			int side3 = present.x * present.z;
			//total surface area, plus slack
			total += 2*side1 + 2*side2 + 2*side3 + Math.min(side1, Math.min(side2,side3));
		}
		return Integer.toString(total);
	}

	@Override
	public String part2() {
		//parse presents into improvised tuples using Coord3
		ArrayList<Coord3> presents = new ArrayList<Coord3>();
		for(String s : input.split("\n")) {
			//split into individual dimensions adn parse
			String[] parts = s.split("x");
			presents.add(new Coord3(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]),Integer.parseInt(parts[2])));
		}
		//ribbon total
		int total = 0;
		for(Coord3 present : presents) {
			//perimeters
			int p1 = 2 * (present.x + present.y);
			int p2 = 2 * (present.y + present.z);
			int p3 = 2 * (present.x + present.z);
			//smallest perimeter, and cubic for bow
			total += Math.min(p1, Math.min(p2, p3)) + present.x * present.y * present.z;
		}
		return Integer.toString(total);
	}

	public static void main(String[] args) {
		input = Input.fetchInput(2015,2);
		DayRunner.run(new Day02());
	}

}
