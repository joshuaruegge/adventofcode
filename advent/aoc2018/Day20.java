package advent.aoc2018;

import java.util.HashMap;
import java.util.LinkedList;

import advent.utilities.general.Coord;
import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

public class Day20 implements IDay {

	static String input;
	
	@Override
	public String part1() {
		//rather than building the entire map structure, all we need to do is keep track of the distance a point is from the center
		//when doing this with the regex, we run into the fact that when the path splits and segments, the distance has to count up from the original value before the split each time
		//so, every time the path splits, we push the location onto a stack, and every time we split or end a split section, we retrieve it
		HashMap<Coord,Integer> distances = new HashMap<Coord,Integer>();
		LinkedList<Coord> stack = new LinkedList<Coord>();
		
		//trim regex and convert to char array
		char[] paths = input.substring(1,input.length() - 1).toCharArray();
		
		Coord position = new Coord(0,0);
		distances.put(position.copy(), 0);
		for(char c : paths) {
			switch(c) {
			case 'W':
				Coord next = position.sum(Coord.LEFT);
				distances.put(next, Math.min(distances.getOrDefault(next, Integer.MAX_VALUE), distances.get(position) + 1));
				position = next;
				break;
			case 'E':
				next = position.sum(Coord.RIGHT);
				distances.put(next, Math.min(distances.getOrDefault(next, Integer.MAX_VALUE), distances.get(position) + 1));
				position = next;
				break;
			case 'N':
				next = position.sum(Coord.UP);
				distances.put(next, Math.min(distances.getOrDefault(next, Integer.MAX_VALUE), distances.get(position) + 1));
				position = next;
				break;
			case 'S':
				next = position.sum(Coord.DOWN);
				distances.put(next, Math.min(distances.getOrDefault(next, Integer.MAX_VALUE), distances.get(position) + 1));
				position = next;
				break;
			case '(':
				//push current location to top of stack
				stack.addFirst(position.copy());
				break;
			case '|':
				//backtrack to top of stack
				position = stack.peek();
				break;
			case ')':
				//group is over - backtrack to top of stack, and also remove from stack
				position = stack.poll();
				break;
			}
		}
		return Integer.toString(distances.values().stream().mapToInt(x -> x).max().getAsInt());
	}

	@Override
	public String part2() {
		//rather than building the entire map structure, all we need to do is keep track of the distance a point is from the center
		//when doing this with the regex, we run into the fact that when the path splits and segments, the distance has to count up from the original value before the split each time
		//so, every time the path splits, we push the location onto a stack, and every time we split or end a split section, we retrieve it
		HashMap<Coord,Integer> distances = new HashMap<Coord,Integer>();
		LinkedList<Coord> stack = new LinkedList<Coord>();
		
		//trim regex and convert to char array
		char[] paths = input.substring(1,input.length() - 1).toCharArray();
		
		Coord position = new Coord(0,0);
		distances.put(position.copy(), 0);
		for(char c : paths) {
			switch(c) {
			case 'W':
				Coord next = position.sum(Coord.LEFT);
				distances.put(next, Math.min(distances.getOrDefault(next, Integer.MAX_VALUE), distances.get(position) + 1));
				position = next;
				break;
			case 'E':
				next = position.sum(Coord.RIGHT);
				distances.put(next, Math.min(distances.getOrDefault(next, Integer.MAX_VALUE), distances.get(position) + 1));
				position = next;
				break;
			case 'N':
				next = position.sum(Coord.UP);
				distances.put(next, Math.min(distances.getOrDefault(next, Integer.MAX_VALUE), distances.get(position) + 1));
				position = next;
				break;
			case 'S':
				next = position.sum(Coord.DOWN);
				distances.put(next, Math.min(distances.getOrDefault(next, Integer.MAX_VALUE), distances.get(position) + 1));
				position = next;
				break;
			case '(':
				//push current location to top of stack
				stack.addFirst(position.copy());
				break;
			case '|':
				//backtrack to top of stack
				position = stack.peek();
				break;
			case ')':
				//group is over - backtrack to top of stack, and also remove from stack
				position = stack.poll();
				break;
			}
		}
		return Long.toString(distances.values().stream().filter(x -> x >= 1000).count());
	}

	public static void main(String[] args) {
		input = Input.fetchInput(2018,20).trim();
		DayRunner.run(new Day20());
	}

}
