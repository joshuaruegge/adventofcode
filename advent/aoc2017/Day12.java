package advent.aoc2017;

import java.util.ArrayList;
import java.util.HashSet;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

public class Day12 implements IDay {

	static String input;
	
	@Override
	public String part1() {
		//store groups as arraylist of hash sets
		ArrayList<HashSet<Integer>> groups = new ArrayList<HashSet<Integer>>();
		for(String s : input.split("\n")) {
			String[] nums = s.split(" <-> |, ");
			HashSet<Integer> group = new HashSet<Integer>();
			//ignore first number - because it counts up linearly, we can just use group index
			for(int i = 1; i < nums.length; i++)
				group.add(Integer.parseInt(nums[i]));
			groups.add(group);
		}
		
		//now, create our own union-find / disjoint set structure
		//parent stores the parent pipe of each of the 2000 pipes
		int[] parent = new int[groups.size()];
		for(int i = 0; i < groups.size(); i++)
			parent[i] = i;
		
		//update parent values by merging
		for(int i = 0; i < groups.size(); i++) {
			for(int j : groups.get(i))
				merge(i,j,parent);
		}
		
		//count number of indices with same parent as zero
		int zeroGroupCounter = 0;
		for(int i = 0; i < groups.size(); i++) {
			if(find(i,parent) == find(0,parent))
				zeroGroupCounter++;
		}
 		return Integer.toString(zeroGroupCounter);
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
		//store groups as arraylist of hash sets
		ArrayList<HashSet<Integer>> groups = new ArrayList<HashSet<Integer>>();
		for(String s : input.split("\n")) {
			String[] nums = s.split(" <-> |, ");
			HashSet<Integer> group = new HashSet<Integer>();
			//ignore first number - because it counts up linearly, we can just use group index
			for(int i = 1; i < nums.length; i++)
				group.add(Integer.parseInt(nums[i]));
			groups.add(group);
		}
		
		//now, create our own union-find / disjoint set structure
		int[] parent = new int[groups.size()];
		for(int i = 0; i < groups.size(); i++)
			parent[i] = i;
		
		//merge all groups
		for(int i = 0; i < groups.size(); i++) {
			for(int j : groups.get(i))
				merge(i,j,parent);
		}
 		
		//determine number of groups by counting number of unique parents
 		//use hashset to only accept values not already in set
 		HashSet<Integer> groupCounter = new HashSet<Integer>();
 		for(int i = 0; i < groups.size(); i++) {
 			groupCounter.add(find(i,parent));
 		}
 		
 		//return size of independent group list
 		return Integer.toString(groupCounter.size());
	}

	public static void main(String[] args) {
		input = Input.fetchInput(2017,12);
		DayRunner.run(new Day12());
	}

}
