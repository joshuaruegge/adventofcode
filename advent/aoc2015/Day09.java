package advent.aoc2015;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.IntStream;

import advent.utilities.general.Coord;
import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

public class Day09 implements IDay {

	static String input;
	
	@Override
	public String part1() {
		//use an array and its indexes to convert city names to numbers
		//then, use a coord as an improvised tuple to store (source,destination)
		ArrayList<String> cities = new ArrayList<String>();
		//keep track of distances in a hashmap of (source,dest) -> dist
		HashMap<Coord,Integer> map = new HashMap<Coord,Integer>();
		for(String s : input.split("\n")) {
			String[] parts = s.split(" ");
			//get number representation of source
			if(!cities.contains(parts[0]))
				cities.add(parts[0]);
			int source = cities.indexOf(parts[0]);
			//get number representation of dest
			if(!cities.contains(parts[2]))
				cities.add(parts[2]);
			int dest = cities.indexOf(parts[2]);
			Coord pair = new Coord(source,dest);
			if(!map.containsKey(pair))
				map.put(pair, Integer.parseInt(parts[4]));
			//additionally, put in backwards path
			Coord invPair = new Coord(dest,source);
			if(!map.containsKey(invPair))
				map.put(invPair, Integer.parseInt(parts[4]));
		}
		//trying every permutation is quite possibly the worst traveling salesperson solution
		//but, with only 8 cities, it's realtime-manageable despite being computationally awful
		int[] permBase = IntStream.range(0, cities.size()).toArray();
		//calculate all order permutations of (0 - cities.size() - 1)
		ArrayList<int[]> perms = perms(permBase,permBase.length);
		int best = Integer.MAX_VALUE;
		for(int[] a : perms) {
			int distance = 0;
			//go through each path in permutation, calculate path distance
			for(int i = 0; i < a.length - 1; i++) {
				distance += map.get(new Coord(a[i],a[i+1]));
			}
			if(distance < best)
				best = distance;
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
		//same as last time, but check for largest when iterating over perms
		
		//use an array and its indexes to convert city names to numbers
		//then, use a coord as an improvised tuple to store (source,destination)
		ArrayList<String> cities = new ArrayList<String>();
		//keep track of distances in a hashmap of (source,dest) -> dist
		HashMap<Coord,Integer> map = new HashMap<Coord,Integer>();
		for(String s : input.split("\n")) {
			String[] parts = s.split(" ");
			//get number representation of source
			if(!cities.contains(parts[0]))
				cities.add(parts[0]);
			int source = cities.indexOf(parts[0]);
			//get number representation of dest
			if(!cities.contains(parts[2]))
				cities.add(parts[2]);
			int dest = cities.indexOf(parts[2]);
			Coord pair = new Coord(source,dest);
			if(!map.containsKey(pair))
				map.put(pair, Integer.parseInt(parts[4]));
			//additionally, put in backwards path just in case
			Coord invPair = new Coord(dest,source);
			if(!map.containsKey(invPair))
				map.put(invPair, Integer.parseInt(parts[4]));
		}
		//with a single digit city count, trying all permutations is computationally superexponential
		//but still realtime manageable
		int[] permBase = IntStream.range(0, cities.size()).toArray();
		ArrayList<int[]> perms = perms(permBase,permBase.length);
		int best = 0;
		for(int[] a : perms) {
			int distance = 0;
			//go through permutation, calculate path distances
			for(int i = 0; i < a.length - 1; i++) {
				distance += map.get(new Coord(a[i],a[i+1]));
			}
			//check for highest instead of lowest
			if(distance > best)
				best = distance;
		}
		return Integer.toString(best);
	}

	public static void main(String[] args) {
		input = Input.fetchInput(2015,9);
		DayRunner.run(new Day09());
	}

}
