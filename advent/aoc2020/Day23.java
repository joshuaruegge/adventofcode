package advent.aoc2020;

import java.util.ArrayList;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

public class Day23 implements IDay {

	static String input;
	
	@Override
	public String part1() {
		ArrayList<Integer> cups = new ArrayList<Integer>();
		for(String s : input.split(""))
			cups.add(Integer.parseInt(s));
		
		int cur = 0;
		int oldCurCup = 0;
		for(int iter = 0; iter < 100; iter++) {
			int cupAtCur = cups.get(cur);
			oldCurCup = cupAtCur;
			cupAtCur--;
			
			int indexOfThree = cur + 1;
			ArrayList<Integer> three = new ArrayList<Integer>();
			for(int i = 0; i < 3; i++) {
				if(indexOfThree == cups.size())
					indexOfThree = 0;
				three.add(cups.remove(indexOfThree));
				//after remove, index will still point to proper location because entries shift down
			}
			
			//find index of destination - minimum cup is always 1, maximum always 9
			while(!cups.contains(cupAtCur)) {
				cupAtCur--;
				if(cupAtCur <= 0)
					cupAtCur = 9;
			}
			
			int destIndex = cups.indexOf(cupAtCur);
			
			//move so inserted in front
			destIndex++;
			if(destIndex == cups.size())
				destIndex = 0;
			
			cups.addAll(destIndex,three);
			
			//find new cur based off index of old cur value, in case indexes shifted around
			cur = cups.indexOf(oldCurCup) + 1;
			if(cur == cups.size())
				cur = 0;
		}
		
		String num = "";
		
		int index = cups.indexOf(1);
		
		do {
			index++;
			if(index == cups.size())
				index = 0;
			num += cups.get(index);
		} while(index != cups.indexOf(1));
		
		//trim off 1
		num = num.substring(0,num.length() - 1);
		
		return num;
	}

	@Override
	public String part2() {
		//rather than a prohibitively big and time-consuming list, simply map cup -> cup after to replicate the circle
		//since values are in range 0-1000000, array works better than map
		//in next, next[cup] is equal to the cup after cup
		int[] next = new int[1000001];
		String[] nums = input.split("");
		for(int i = 1; i < nums.length; i++) {
			next[Integer.parseInt(nums[i-1])] = Integer.parseInt(nums[i]);
		}
		next[Integer.parseInt(nums[nums.length - 1])] = 10;
		for(int i = 11; i < 1000001; i++) {
			next[i-1] = i;
		}
			
		next[1000000] = Integer.parseInt(nums[0]);
		
		int cur = Integer.parseInt(nums[0]);
		for(int iter = 0; iter < 10000000; iter++) {
			int first = next[cur];
			int second = next[first];
			int third = next[second];
			
			int dest = cur - 1;
			if(dest == 0)
				dest = 1000000;
			while(dest == first || dest == second || dest == third) {
				dest--;
				if(dest == 0)
					dest = 1000000;
			}
			
			//update mapping to reflect removal
			next[cur] = next[third];
			
			//update mapping to reflect insert
			next[third] = next[dest];
			next[dest] = first;
			//first still points to second and second to third
			
			//update cur
			cur = next[cur];
		}
		long label1 = next[1];
		long label2 = next[next[1]];
		long labelResult = label1 * label2;
		
		return Long.toString(labelResult);
	}

	public static void main(String[] args) {
		input = Input.fetchInput(2020,23).trim();
		DayRunner.run(new Day23());
	}
}
