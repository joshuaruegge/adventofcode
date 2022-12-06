package advent.aoc2015;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.IntStream;

import advent.utilities.general.Coord;
import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

public class Day13 implements IDay {

	static String input;
	
	@Override
	public String part1() {
		//add new names to names array, then use indexes of names to replace strings with numbers
		ArrayList<String> names = new ArrayList<String>();
		//store happiness changes as (id 1, id 2) -> change map
		//use coord as improvised tuple
		HashMap<Coord,Integer> happiness = new HashMap<Coord,Integer>();
		for(String s : input.split("\n")) {
			String[] parts = s.split(" ");
			//trim period off last word to avoid conflicts
			parts[10] = parts[10].substring(0,parts[10].length() - 1);
			if(!names.contains(parts[0]))
				names.add(parts[0]);
			if(!names.contains(parts[10]))
				names.add(parts[10]);
			//invert happiness value if necessary
			int happinessChange = Integer.parseInt(parts[3]);
			if(parts[2].equals("lose"))
				happinessChange *= -1;
			happiness.put(new Coord(names.indexOf(parts[0]), names.indexOf(parts[10])),happinessChange);
		}
		//similarly to day 9, use permutation of all possible arrangements, and calculate 
		//values for happiness that result from neighbors in each arrangement
		int[] permBase = IntStream.range(0, names.size()).toArray();
		ArrayList<int[]> perms = perms(permBase,permBase.length);
		//keep track of score of best discovered arrangement
		int best = 0;
		for(int[] a : perms) {
			int happinessTotal = 0;
			//iterate over array, adding values for neighbor happiness
			for(int i = 0; i < a.length - 1; i++) {
				Coord neighbors = new Coord(a[i], a[i+1]);
				//happiness relations take place in both directions
				happinessTotal += happiness.get(neighbors);
				happinessTotal += happiness.get(neighbors.invert());
			}
			//add neighbor status of front and back of array (wrapping)
			happinessTotal += happiness.get(new Coord(a[0],a[a.length-1]));
			happinessTotal += happiness.get(new Coord(a[a.length -1], a[0]));
			//grade position
			if(happinessTotal > best)
				best = happinessTotal;
		}
		return Integer.toString(best);
	}
	
	//Heap's permutation generation algorithm. look it up on wikipedia if you need to
	public static ArrayList<int[]> perms(int[] a, int n) {
		ArrayList<int[]> perms = new ArrayList<int[]>();
		if(n == 1) {
			perms.add(Arrays.copyOf(a, a.length));
			return perms;
		}
		
		perms.addAll(perms(a,n-1));
		for(int i = 0; i < n-1; i++) {
			if(n % 2 == 0) {
				int temp = a[i];
				a[i] = a[n-1];
				a[n-1] = temp;
			} else {
				int temp = a[0];
				a[0] = a[n-1];
				a[n-1] = temp;
			}
			perms.addAll(perms(a,n-1));
		}
		return perms;
	}

	@Override
	public String part2() {
		//add new names to names array, then use indexes of names to replace strings with numbers
		ArrayList<String> names = new ArrayList<String>();
		//store happiness changes as (id 1, id 2) -> change map
		//use coord as improvised tuple
		HashMap<Coord,Integer> happiness = new HashMap<Coord,Integer>();
		for(String s : input.split("\n")) {
			String[] parts = s.split(" ");
			//trim period off last word to avoid conflicts
			parts[10] = parts[10].substring(0,parts[10].length() - 1);
			if(!names.contains(parts[0]))
				names.add(parts[0]);
			if(!names.contains(parts[10]))
				names.add(parts[10]);
			//invert happiness change value if necessary
			int happinessChange = Integer.parseInt(parts[3]);
			if(parts[2].equals("lose"))
				happinessChange *= -1;
			happiness.put(new Coord(names.indexOf(parts[0]), names.indexOf(parts[10])),happinessChange);
		}
		//similarly to day 9, use permutation of all possible arrangements, and calculate 
		//values for happiness that result from neighbors in each arrangement
		
		//for part 2: we add one extra number to the permutations (for ourself)
		int[] permBase = IntStream.range(0, names.size() + 1).toArray();
		//now, create all neighbor relations for extra person (all equal to zero per instructions)
		for(int i = 0; i < permBase.length - 1; i++) {
			Coord c = new Coord(permBase.length - 1, i);
			happiness.put(c, 0);
			happiness.put(c.invert(), 0);
		}
		ArrayList<int[]> perms = perms(permBase,permBase.length);
		int best = 0;
		for(int[] a : perms) {
			int happinessTotal = 0;
			//iterate over array, adding values for neighbor happiness
			for(int i = 0; i < a.length - 1; i++) {
				Coord neighbors = new Coord(a[i], a[i+1]);
				happinessTotal += happiness.get(neighbors);
				happinessTotal += happiness.get(neighbors.invert());
			}
			//add neighbor status of front and back of array (wrapping)
			happinessTotal += happiness.get(new Coord(a[0],a[a.length-1]));
			happinessTotal += happiness.get(new Coord(a[a.length -1], a[0]));
			//grade position
			if(happinessTotal > best)
				best = happinessTotal;
		}
		return Integer.toString(best);
	}

	public static void main(String[] args) {
		input = Input.fetchInput(2015,13);
		DayRunner.run(new Day13());
	}

}
