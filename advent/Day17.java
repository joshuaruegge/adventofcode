package advent.aoc2017;

import java.util.ArrayList;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;

public class Day17 implements IDay {

	String input = "363";
	
	@Override
	public String part1() {
		int offset = Integer.parseInt(input);
		ArrayList<Integer> buffer = new ArrayList<Integer>();
		buffer.add(0);
		
		int current = 0;
		for(int i = 1; i < 2018; i++) {
			int insert = current + offset + 1;
			insert %= buffer.size();
			buffer.add(insert,i);
			current = insert;
		}
		
		return Integer.toString(buffer.get(buffer.indexOf(2017) + 1));
	}

	@Override
	public String part2() {
		int offset = Integer.parseInt(input);
		//since the problem has grown very large, we'll forego calculating buffer
		//instead, we just need to keep track of the index of zero, and the value currently after zero
		//if we insert a number before zero, zero needs to be pushed forwards one
		//and if we insert a number right after zero, we update the value after zero to the new number
		//then, we just return valueAfterZero after 50,000,000 inserts
		int current = 0;
		int indexOfZero = 0;
		int valueAfterZero = 1;
		for(int i = 2; i < 50000001; i++) {
			int insert = current + offset + 1;
			insert %= i;
			//if inserting before zero, move zero forwards
			if(insert < indexOfZero)
				indexOfZero++;
			//if inserting right in front of zero, update value of valueAfterZero
			else if(insert == indexOfZero)
				valueAfterZero = i;
			//update current
			current = insert;
		}
		return Integer.toString(valueAfterZero);
	}

	public static void main(String[] args) {
		DayRunner.run(new Day17());
	}

}
