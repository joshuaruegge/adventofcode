package advent.aoc2017;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import advent.utilities.general.Coord;
import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

public class Day19 implements IDay {

	static String input;
	
	@Override
	public String part1() {
		ArrayList<String> lines = new ArrayList<String>(Arrays.asList(input.split("\n")));
		//count letters to determine required solution length
		Pattern caps = Pattern.compile(("[A-Z]"));
		Matcher letters = caps.matcher(input);
		int letterCount = (int) letters.results().count();
		
		Coord position = new Coord(lines.get(0).indexOf("|"), 0);
		//initialize facing (down)
		Coord facing = new Coord(0,1);
		String path = "";
		while(path.length() < letterCount) {
			position.sumSelf(facing);
			char c = lines.get(position.y).charAt(position.x);
			if(c == '+' ) {
				facing = (lines.get(position.sum(facing.left()).y).charAt(position.sum(facing.left()).x) != ' ' ? facing.left() : facing.right());
			} else if (c != '-' && c != '|') {
				path += c;
			}
		}
		return path;
	}

	
	
	@Override
	public String part2() {
		ArrayList<String> lines = new ArrayList<String>(Arrays.asList(input.split("\n")));
		//count letters
		Pattern caps = Pattern.compile(("[A-Z]"));
		Matcher letters = caps.matcher(input);
		int letterCount = (int) letters.results().count();
		
		Coord position = new Coord(lines.get(0).indexOf("|"), 0);
		//initialize facing (down)
		Coord facing = new Coord(0,1);
		String path = "";
		//count starting location
		int steps = 1;
		while(path.length() < letterCount) {
			position.sumSelf(facing);
			char c = lines.get(position.y).charAt(position.x);
			if(c == '+' ) {
				facing = (lines.get(position.sum(facing.left()).y).charAt(position.sum(facing.left()).x) != ' ' ? facing.left() : facing.right());
			} else if (c != '-' && c != '|') {
				path += c;
			}
			steps++;
		}
		//off-by-one
		return Integer.toString(steps);
	}

	public static void main(String[] args) {
		input = Input.fetchInput(2017,19);
		DayRunner.run(new Day19());
	}

}
