package advent.aoc2019;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import advent.utilities.general.Coord;
import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;
import advent.utilities.utils2019.IntCodeComputer;

public class Day17 implements IDay {

	static String input;
	
	static HashMap<Coord,Integer> map;
	static HashSet<Coord> scaffold;
	static HashSet<Coord> intersect;
	static Coord start = new Coord();

	@Override
	public String part1() {
		//initialize and run computer to get map output
		ArrayList<Long> program = new ArrayList<Long>(Arrays.stream(input.split(",")).map(x -> Long.parseLong(x)).toList());
		IntCodeComputer i = new IntCodeComputer(program);
		i.run();
		
		scaffold = new HashSet<Coord>();
		int x = 0;
		int y = 0;
		//parse output of initial grid
		while(i.output.size() > 0) {
			int out = Math.toIntExact(i.output());
			//increment y on newline
			if(out == 10) {
				x = 0;
				y++;
			} else {
				if(out == 35) {
					scaffold.add(new Coord(x,y));
				}
				else if(out != 46) {
					start = new Coord(x,y);
				}	
				x++;
			}
		}
		//calculate alignment value
		int align = 0;
		intersect = new HashSet<Coord>();
		check:
		for(Coord c : scaffold) {
			//intersections have scaffolding on all 4 sides, so if a point does not have scaffolding at all 4 neighbors, we skip it
			for(Coord d : c.directNeighbors()) {
				if(!scaffold.contains(d))
					continue check;
			}
			align += c.x * c.y;
			intersect.add(c);			
		}
		return Integer.toString(align);
	}

	@Override
	public String part2() {
		//initialize path, account for first turn (my input has to turn right initially, swap to L if regex matching fails
		String path = "R,";
		Coord position = start;
		Coord facing = new Coord(1,0);
		//non-intersect scaffolds are removed from array after traversing, so if arrays are the same size, all squares have been traversed
		while(scaffold.size() != intersect.size()) {
			//calculate distance that can be moved forwards
			int step = 0;
			//while there is still path ahead
			while(scaffold.contains(position.sum(facing))) {
				step++;
				//remove non-intersect scaffolds so they can't be re-traversed
				if(!intersect.contains(position))
					scaffold.remove(position);
				//move position forwards
				position.sumSelf(facing);
			}
			path += step + ",";
			//figure out which direction the turn is
			if(scaffold.contains(position.sum(facing.left()))) {
				path += "L,";
				facing = facing.left();
			} else if(scaffold.contains(position.sum(facing.right()))) {
				path += "R,";
				facing = facing.right();
			} else {
				break;
			}
		}
		
		//I bargained my firstborn and a year off the life of every developer in the United States for this regex. Use it well.
		//essentially, it iterates through all potential segments of length 1-20, and finds the three that can together make up the whole pattern
		Pattern pattern = Pattern.compile("^(.{1,20})\\1*(.{1,20})(?:\\1|\\2)*(.{1,20})(?:\\1|\\2|\\3)*$");
		Matcher match = pattern.matcher(path);
		
		if(!match.matches())
			System.out.println("Regex matching failed! See code comments to fix.");
		String A = match.group(1);
		String B = match.group(2);
		String C = match.group(3);
		
		//trim trailing comma
		A = A.substring(0,A.length() - 1);
		B = B.substring(0,B.length() - 1);
		C = C.substring(0,C.length() - 1);
		
		//update full path to use function names
		String mainFunction = path.replaceAll(A, "A").replaceAll(B, "B").replaceAll(C, "C");
		//trim trailing comma
		mainFunction = mainFunction.substring(0,mainFunction.length() - 1);
		
		//reinitialize bot
		ArrayList<Long> program = new ArrayList<Long>(Arrays.stream(input.split(",")).map(x -> Long.parseLong(x)).toList());
		//set to alternate mode
		program.set(0, 2l);
		IntCodeComputer i = new IntCodeComputer(program);
		//input functions
		for(int j = 0; j < mainFunction.length(); j++) {
			i.input((int) mainFunction.charAt(j));
		}
		i.input(10);
		for(int j = 0; j < A.length(); j++) {
			i.input((int) A.charAt(j));
		}
		i.input(10);
		for(int j = 0; j < B.length(); j++) {
			i.input((int) B.charAt(j));
		}
		i.input(10);
		for(int j = 0; j < C.length(); j++) {
			i.input((int) C.charAt(j));
		}
		i.input(10);
		//input 'n' for live view
		i.input((int) 'n');
		i.input(10);
		i.run();
		//ignore all output except grabbing our magic number at the end
		return Long.toString(i.output.get(i.output.size() - 1));
	}

	public static void main(String[] args) {
		input = Input.fetchInput(2019,17).trim();
		DayRunner.run(new Day17());
	}
}
