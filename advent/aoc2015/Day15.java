package advent.aoc2015;

import java.util.ArrayList;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

public class Day15 implements IDay {

	static String input;
	
	@Override
	public String part1() {
		//store ingredients as 5-item int array arraylist
		ArrayList<int[]> ingredients = new ArrayList<int[]>();
		for(String s : input.split("\n")) {
			String[] parts = s.split(" ");
			//trim commas and parse integers for different aspect values
			int cap = Integer.parseInt(parts[2].substring(0,parts[2].length() - 1));
			int dur = Integer.parseInt(parts[4].substring(0,parts[4].length() - 1));
			int fla = Integer.parseInt(parts[6].substring(0,parts[6].length() - 1));
			int tex = Integer.parseInt(parts[8].substring(0,parts[8].length() - 1));
			int cal = Integer.parseInt(parts[10].substring(0,parts[10].length()));
			ingredients.add(new int[] {cap,dur,fla,tex,cal});
		}
		
		//now, generate all possible permutations of ingredients
		//slight twist - permutations are 4 numbers such that the sum equals 100
		//this is hard-coded for 4 ingredients
		ArrayList<int[]> perms = new ArrayList<int[]>();
		for(int w = 0; w < 101; w++) {
			for(int x = 0; x < 101; x++) {
				for(int y = 0; y < 101; y++) {
					for(int z = 0; z < 101; z++) {
						if(w+x+y+z == 100) {
							perms.add(new int[] {w,x,y,z});
						}
					}
				}
			}
		}
		//iterate over permutations and find best
		int best = 0;
		for(int[] a : perms) {
			int cap = 0;
			int dur = 0;
			int fla = 0;
			int tex = 0;
			
			//for each ingredient, increase total characteristics by (ingredient change) * (ingredient count)
			for(int i = 0; i < 4; i++) {
				cap += a[i] * ingredients.get(i)[0];
				dur += a[i] * ingredients.get(i)[1];
				fla += a[i] * ingredients.get(i)[2];
				tex += a[i] * ingredients.get(i)[3];
			}
			//values must be positive
			if(cap <= 0 || dur <= 0 || fla <= 0 || tex <= 0)
				continue;
			int score = cap * dur * fla * tex;
			if(score > best)
				best = score;
		}
		return Integer.toString(best);
	}

	@Override
	public String part2() {
		//same exact code, one extra condition for calorie check
		
		//store ingredients as 5-item int array arraylist
		ArrayList<int[]> ingredients = new ArrayList<int[]>();
		for(String s : input.split("\n")) {
			String[] parts = s.split(" ");
			//trim commas and parse integers for different aspect values
			int cap = Integer.parseInt(parts[2].substring(0,parts[2].length() - 1));
			int dur = Integer.parseInt(parts[4].substring(0,parts[4].length() - 1));
			int fla = Integer.parseInt(parts[6].substring(0,parts[6].length() - 1));
			int tex = Integer.parseInt(parts[8].substring(0,parts[8].length() - 1));
			int cal = Integer.parseInt(parts[10].substring(0,parts[10].length()));
			ingredients.add(new int[] {cap,dur,fla,tex,cal});
		}
		
		//now, generate all possible permutations of ingredients
		//slight twist - permutations are sums of 4 numbers such that the sum equals 100
		//this is hard-coded for 4 ingredients
		ArrayList<int[]> perms = new ArrayList<int[]>();
		for(int w = 0; w < 101; w++) {
			for(int x = 0; x < 101; x++) {
				for(int y = 0; y < 101; y++) {
					for(int z = 0; z < 101; z++) {
						if(w+x+y+z == 100) {
							perms.add(new int[] {w,x,y,z});
						}
					}
				}
			}
		}
		//iterate over permutations and find best
		int best = 0;
		for(int[] a : perms) {
			int cap = 0;
			int dur = 0;
			int fla = 0;
			int tex = 0;
			int cal = 0;
			for(int i = 0; i < 4; i++) {
				cap += a[i] * ingredients.get(i)[0];
				dur += a[i] * ingredients.get(i)[1];
				fla += a[i] * ingredients.get(i)[2];
				tex += a[i] * ingredients.get(i)[3];
				cal += a[i] * ingredients.get(i)[4];
			}
			if(cap <= 0 || dur <= 0 || fla <= 0 || tex <= 0)
				continue;
			int score = cap * dur * fla * tex;
			//extra check for calories
			if(score > best && cal == 500)
				best = score;
		}
		return Integer.toString(best);
	}

	public static void main(String[] args) {
		input = Input.fetchInput(2015,15);
		DayRunner.run(new Day15());
	}

}
