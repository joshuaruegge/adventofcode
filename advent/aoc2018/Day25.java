package advent.aoc2018;

import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.IntStream;

import advent.utilities.general.Coord4;
import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

public class Day25 implements IDay {

	static String input;
	
	@Override
	public String part1() {
		String[] lines = input.split("\n");
		int[] parents = IntStream.range(0, lines.length).toArray();
		
		HashMap<Coord4,Integer> id = new HashMap<Coord4,Integer>();
		for(int i = 0; i < lines.length; i++) {
			String[] parts = lines[i].split(",");
			Coord4 star = new Coord4(Integer.parseInt(parts[0]),Integer.parseInt(parts[1]),Integer.parseInt(parts[2]),Integer.parseInt(parts[3]));
			id.put(star, i);
			
			for(Coord4 c : id.keySet()) {
				if(star.distance(c) < 4) {
					merge(i,id.get(c),parents);
				}
			}
			
		}
		
		//final pass
		for(Coord4 c : id.keySet()) {
			for(Coord4 d : id.keySet()) {
				if(c.distance(d) <= 3) {
					merge(id.get(d),id.get(c),parents);
				}
			}
		}
		
		return Long.toString(Arrays.stream(parents).distinct().count());
	}
	
	//find function for union-set
	public int find(int x, int[] parent) {
		if(parent[x] != x)
			parent[x] = find(parent[x],parent);
		return parent[x];	
	}
	
	//merge function for union-set
	public void merge(int x, int y, int[] parent) {
		parent[find(x,parent)] = find(y,parent);
	}

	@Override
	public String part2() {
		return "Merry Christmas!";
	}

	public static void main(String[] args) {
		input = Input.fetchInput(2018,25);
		DayRunner.run(new Day25());
	}

}
