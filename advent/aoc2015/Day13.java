package advent.aoc2015;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import advent.utilities.general.Coord;
import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;

public class Day13 implements IDay {

	String input = "Alice would lose 57 happiness units by sitting next to Bob.\r\n"
			+ "Alice would lose 62 happiness units by sitting next to Carol.\r\n"
			+ "Alice would lose 75 happiness units by sitting next to David.\r\n"
			+ "Alice would gain 71 happiness units by sitting next to Eric.\r\n"
			+ "Alice would lose 22 happiness units by sitting next to Frank.\r\n"
			+ "Alice would lose 23 happiness units by sitting next to George.\r\n"
			+ "Alice would lose 76 happiness units by sitting next to Mallory.\r\n"
			+ "Bob would lose 14 happiness units by sitting next to Alice.\r\n"
			+ "Bob would gain 48 happiness units by sitting next to Carol.\r\n"
			+ "Bob would gain 89 happiness units by sitting next to David.\r\n"
			+ "Bob would gain 86 happiness units by sitting next to Eric.\r\n"
			+ "Bob would lose 2 happiness units by sitting next to Frank.\r\n"
			+ "Bob would gain 27 happiness units by sitting next to George.\r\n"
			+ "Bob would gain 19 happiness units by sitting next to Mallory.\r\n"
			+ "Carol would gain 37 happiness units by sitting next to Alice.\r\n"
			+ "Carol would gain 45 happiness units by sitting next to Bob.\r\n"
			+ "Carol would gain 24 happiness units by sitting next to David.\r\n"
			+ "Carol would gain 5 happiness units by sitting next to Eric.\r\n"
			+ "Carol would lose 68 happiness units by sitting next to Frank.\r\n"
			+ "Carol would lose 25 happiness units by sitting next to George.\r\n"
			+ "Carol would gain 30 happiness units by sitting next to Mallory.\r\n"
			+ "David would lose 51 happiness units by sitting next to Alice.\r\n"
			+ "David would gain 34 happiness units by sitting next to Bob.\r\n"
			+ "David would gain 99 happiness units by sitting next to Carol.\r\n"
			+ "David would gain 91 happiness units by sitting next to Eric.\r\n"
			+ "David would lose 38 happiness units by sitting next to Frank.\r\n"
			+ "David would gain 60 happiness units by sitting next to George.\r\n"
			+ "David would lose 63 happiness units by sitting next to Mallory.\r\n"
			+ "Eric would gain 23 happiness units by sitting next to Alice.\r\n"
			+ "Eric would lose 69 happiness units by sitting next to Bob.\r\n"
			+ "Eric would lose 33 happiness units by sitting next to Carol.\r\n"
			+ "Eric would lose 47 happiness units by sitting next to David.\r\n"
			+ "Eric would gain 75 happiness units by sitting next to Frank.\r\n"
			+ "Eric would gain 82 happiness units by sitting next to George.\r\n"
			+ "Eric would gain 13 happiness units by sitting next to Mallory.\r\n"
			+ "Frank would gain 77 happiness units by sitting next to Alice.\r\n"
			+ "Frank would gain 27 happiness units by sitting next to Bob.\r\n"
			+ "Frank would lose 87 happiness units by sitting next to Carol.\r\n"
			+ "Frank would gain 74 happiness units by sitting next to David.\r\n"
			+ "Frank would lose 41 happiness units by sitting next to Eric.\r\n"
			+ "Frank would lose 99 happiness units by sitting next to George.\r\n"
			+ "Frank would gain 26 happiness units by sitting next to Mallory.\r\n"
			+ "George would lose 63 happiness units by sitting next to Alice.\r\n"
			+ "George would lose 51 happiness units by sitting next to Bob.\r\n"
			+ "George would lose 60 happiness units by sitting next to Carol.\r\n"
			+ "George would gain 30 happiness units by sitting next to David.\r\n"
			+ "George would lose 100 happiness units by sitting next to Eric.\r\n"
			+ "George would lose 63 happiness units by sitting next to Frank.\r\n"
			+ "George would gain 57 happiness units by sitting next to Mallory.\r\n"
			+ "Mallory would lose 71 happiness units by sitting next to Alice.\r\n"
			+ "Mallory would lose 28 happiness units by sitting next to Bob.\r\n"
			+ "Mallory would lose 10 happiness units by sitting next to Carol.\r\n"
			+ "Mallory would gain 44 happiness units by sitting next to David.\r\n"
			+ "Mallory would gain 22 happiness units by sitting next to Eric.\r\n"
			+ "Mallory would gain 79 happiness units by sitting next to Frank.\r\n"
			+ "Mallory would lose 16 happiness units by sitting next to George.";
	
	@Override
	public String part1() {
		//add new names to names array, then use indexes of names to replace strings with numbers
		ArrayList<String> names = new ArrayList<String>();
		//store happiness changes as (id 1, id 2) -> change map
		//use coord as improvised tuple
		HashMap<Coord,Integer> happiness = new HashMap<Coord,Integer>();
		for(String s : input.split("\r\n")) {
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
		int[] permBase = new int[names.size()];
		for(int i = 0; i < permBase.length; i++)
			permBase[i] = i;
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
		for(String s : input.split("\r\n")) {
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
		int[] permBase = new int[names.size()+1];
		for(int i = 0; i < permBase.length; i++)
			permBase[i] = i;
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
		DayRunner.run(new Day13());
	}

}
