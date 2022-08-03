package advent.aoc2018;

import java.util.ArrayList;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;

public class Day14 implements IDay {

	String input = "540391";
	
	@Override
	public String part1() {
		int recipeGoal = Integer.parseInt(input);
		ArrayList<Integer> recipes = new ArrayList<Integer>();
		recipes.add(3);
		recipes.add(7);
		int cur1 = 0;
		int cur2 = 1;
		while(recipes.size() < recipeGoal + 10) {
			int old1 = recipes.get(cur1);
			int old2 = recipes.get(cur2);
			int recipeSum = old1 + old2;
			int recipe1 = recipeSum / 10;
			int recipe2 = recipeSum % 10;
			if(recipe1 > 0)
				recipes.add(recipe1);
			recipes.add(recipe2);
			cur1 += 1 + old1;
			cur1 %= recipes.size();
			cur2 += 1 + old2;
			cur2 %= recipes.size();
		}
		String last10 = "";
		for(int i = 10; i > 0; i--)
			last10 += recipes.get(recipes.size() - i);
		return last10;
	}

	@Override
	public String part2() {
		ArrayList<Integer> testSequence = new ArrayList<Integer>();
		for(char c : input.toCharArray())
			testSequence.add(c - '0');
		ArrayList<Integer> recipes = new ArrayList<Integer>();
		recipes.add(3);
		recipes.add(7);
		int cur1 = 0;
		int cur2 = 1;
		do {
			int old1 = recipes.get(cur1);
			int old2 = recipes.get(cur2);
			int recipeSum = old1 + old2;
			int recipe1 = recipeSum / 10;
			int recipe2 = recipeSum % 10;
			if(recipe1 > 0)
				recipes.add(recipe1);
			recipes.add(recipe2);
			cur1 += 1 + old1;
			cur1 %= recipes.size();
			cur2 += 1 + old2;
			cur2 %= recipes.size();
		} while(contains(recipes,testSequence) == -1);
		
		return Integer.toString(recipes.size() - testSequence.size() - contains(recipes,testSequence));
	}
	
	//determines if the integer sequence in search appears in sequence, ending at either size-1 or size-2
	//because our problem adds one or two indexes to the array at a time, our sequence will always either be at the end or at the end minus one
	//returns 0 if it appears at the end, 1 if it appears at end - 1, and -1 if it appears in neither
	public int contains(ArrayList<Integer> sequence, ArrayList<Integer> search) {
		if(sequence.size() <= search.size())
			return -1;
		boolean last = true;
		boolean lastMinusOne = true;
		//check from end
		int lastStartingPoint = sequence.size() - search.size();
		for(int i = 0; i < search.size(); i++) {
			if(sequence.get(lastStartingPoint++) != search.get(i)) {
				last = false;
				break;
			}
		}
		int lastMinusOneStartingPoint = sequence.size() - search.size() - 1;
		//check from end-1
		for(int i = 0; i < search.size(); i++) {
			if(sequence.get(lastMinusOneStartingPoint++) != search.get(i)) {
				lastMinusOne = false;
				break;
			}
		}
		if(last)
			return 0;
		else if(lastMinusOne)
			return 1;
		else
			return -1;
	}

	public static void main(String[] args) {
		DayRunner.run(new Day14());
	}

}
