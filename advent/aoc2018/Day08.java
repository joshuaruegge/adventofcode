package advent.aoc2018;

import java.util.ArrayList;
import java.util.Arrays;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

public class Day08 implements IDay {

	static String input;
	
	static int pointer;
	static ArrayList<Integer> numbers;
	
	@Override
	public String part1() {
		pointer = 0;
		numbers = new ArrayList<Integer>(Arrays.stream(input.split(" ")).map(x -> Integer.parseInt(x)).toList());
		return Integer.toString(metadataTotal());
	}
	
	//recursion is handled by moving of pointer
	public int metadataTotal() {
		int metadata = 0;
		//numbers at pointer and pointer+1 = children and metadata
		//fetch value, push pointer
		int children = numbers.get(pointer++);
		int metas = numbers.get(pointer++);
		for(int i = 0; i < children; i++) {
			//get metadata of child, and push pointer
			metadata += metadataTotal();
		}
		for(int i = 0; i < metas; i++) {
			//get metadata, push pointer
			metadata += numbers.get(pointer++);
		}
		return metadata;
	}

	@Override
	public String part2() {
		pointer = 0;
		numbers = new ArrayList<Integer>(Arrays.stream(input.split(" ")).map(x -> Integer.parseInt(x)).toList());
		return Integer.toString(valuesTotal());
	}
	
	//part 2 version - children must be calculated now
	public int valuesTotal() {
		int value = 0;
		ArrayList<Integer> children = new ArrayList<Integer>();
		ArrayList<Integer> metadata = new ArrayList<Integer>();
		int childrenCount = numbers.get(pointer++);
		int metas = numbers.get(pointer++);
		for(int i = 0; i < childrenCount; i++) {
			children.add(valuesTotal());
		}
		for(int i = 0; i < metas; i++) {
			metadata.add(numbers.get(pointer++));
		}
		if(children.size() > 0) {
			for(int i : metadata) 
				//index offset - 1 refers to first, etc.
				if((i-1) < children.size())
					value += children.get(i-1);
		} else {
			for(int i : metadata)
				value += i;
		}
		return value;
	}
	

	public static void main(String[] args) {
		input = Input.fetchInput(2018,8).trim();
		DayRunner.run(new Day08());
	}

}
