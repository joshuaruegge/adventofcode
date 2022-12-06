package advent.aoc2016;

import java.util.ArrayList;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

public class Day18 implements IDay {

	static String input;
	
	@Override
	public String part1() {
		int rowLength = input.length();
		ArrayList<boolean[]> tiles = new ArrayList<boolean[]>();
		//populate row from input
		boolean[] inputRow = new boolean[rowLength];
		for(int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);
			if(c == '.')
				inputRow[i] = false;
			else
				inputRow[i] = true;
		}
		tiles.add(inputRow);
		while(tiles.size() < 40) {
			boolean[] row = new boolean[rowLength];
			
			for(int i = 0; i < rowLength; i++) {
				boolean center = tiles.get(tiles.size() - 1)[i];
				boolean left = i > 0 ?  tiles.get(tiles.size() - 1)[i-1]: false;
				boolean right = i < rowLength - 1 ? tiles.get(tiles.size() - 1)[i+1] : false;
				if(center) {
					if(left != right)
						row[i] = true;
					else
						row[i] = false;
				} else {
					if(left ^ right)
						row[i] = true;
					else
						row[i] = false;
				}
			}
			tiles.add(row);
		}
		
		//total safe count
		int count = 0;
		for(boolean[] a : tiles)
			for(boolean b : a)
				if(!b)
					count++;
		
		return Integer.toString(count);
	}

	@Override
	public String part2() {
		int rowLength = input.length();
		ArrayList<boolean[]> tiles = new ArrayList<boolean[]>();
		//populate row from input
		boolean[] inputRow = new boolean[rowLength];
		for(int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);
			if(c == '.')
				inputRow[i] = false;
			else
				inputRow[i] = true;
		}
		tiles.add(inputRow);
		while(tiles.size() < 400000) {
			boolean[] row = new boolean[rowLength];
			
			for(int i = 0; i < rowLength; i++) {
				boolean center = tiles.get(tiles.size() - 1)[i];
				boolean left = i > 0 ?  tiles.get(tiles.size() - 1)[i-1]: false;
				boolean right = i < rowLength - 1 ? tiles.get(tiles.size() - 1)[i+1] : false;
				if(center) {
					if(left != right)
						row[i] = true;
					else
						row[i] = false;
				} else {
					if(left ^ right)
						row[i] = true;
					else
						row[i] = false;
				}
			}
			tiles.add(row);
		}
		
		//total safe count
		int count = 0;
		for(boolean[] a : tiles)
			for(boolean b : a)
				if(!b)
					count++;
		
		return Integer.toString(count);
	}

	public static void main(String[] args) {
		input = Input.fetchInput(2016,18).replace("\n","");
		DayRunner.run(new Day18());
	}

}
