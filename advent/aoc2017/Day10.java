package advent.aoc2017;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

public class Day10 implements IDay {

	static String input;
	
	@Override
	public String part1() {
		ArrayList<Integer> knotHash = new ArrayList<Integer>();
		for(int i = 0; i < 256; i++)
			knotHash.add(i);
		
		int current = 0;
		int skip = 0;
		
		for(String s : input.split(",")) {
			int length = Integer.parseInt(s);
			//determine end of reversed section
			int end = current + length;
			//fix to be circular if necessary
			if(end > knotHash.size()) {
				end %= knotHash.size();
			}
			 
			ArrayList<Integer> reverse = new ArrayList<Integer>();
			//populate values into reverse array 
			if(end < current) {
				//append circularly - current to max, then 0 to end
				for(int i = current; i < knotHash.size(); i++)
					reverse.add(knotHash.get(i));
				for(int i = 0; i < end; i++) {
					reverse.add(knotHash.get(i));
				}
			} else {
				for(int i = current; i < end; i++) {
					reverse.add(knotHash.get(i));
				}
			}
			
			Collections.reverse(reverse);
			
			//put reversed values back in knotHash
			if(end < current) {
				int count = 0;
				for(int i = current; i < knotHash.size(); i++) {
					knotHash.set(i, reverse.get(count));
					count++;
				}
				for(int i = 0; i < end; i++) {
					knotHash.set(i, reverse.get(count));
					count++;
				}
			} else {
				int count = 0;
				for(int i = current; i < end; i++) {
					knotHash.set(i, reverse.get(count));
					count++;
				}
			}
			//move current
			current += length + skip;
			if(current >= knotHash.size())
				current %= knotHash.size();
			
			//increment skip
			skip++;
		}
		
		return Integer.toString(knotHash.get(0) * knotHash.get(1));
	}

	@Override
	public String part2() {
		final Integer[] constantLengths = {17,31,73,47,23};
		ArrayList<Integer> lengths = new ArrayList<Integer>();
		for(char c : input.toCharArray())
			lengths.add((int) c);
		lengths.addAll(Arrays.asList(constantLengths));
		
		ArrayList<Integer> knotHash = new ArrayList<Integer>();
		for(int i = 0; i < 256; i++)
			knotHash.add(i);
		
		int current = 0;
		int skip = 0;
		
		for(int x = 0; x < 64; x++) {
			for(int length : lengths) {
				 //determine end of reversed section
				int end = current + length;
				//fix to be circular if necessary
				if(end > knotHash.size()) {
					end %= knotHash.size();
				}
				 
				ArrayList<Integer> reverse = new ArrayList<Integer>();
				//populate values into reverse array 
				if(end < current) {
					//append circularly - current to max, then 0 to end
					for(int i = current; i < knotHash.size(); i++)
						reverse.add(knotHash.get(i));
					for(int i = 0; i < end; i++) {
						reverse.add(knotHash.get(i));
					}
				} else {
					for(int i = current; i < end; i++) {
						reverse.add(knotHash.get(i));
					}
				}
				
				Collections.reverse(reverse);
				
				//put reversed values back in knotHash
				if(end < current) {
					int count = 0;
					for(int i = current; i < knotHash.size(); i++) {
						knotHash.set(i, reverse.get(count));
						count++;
					}
					for(int i = 0; i < end; i++) {
						knotHash.set(i, reverse.get(count));
						count++;
					}
				} else {
					int count = 0;
					for(int i = current; i < end; i++) {
						knotHash.set(i, reverse.get(count));
						count++;
					}
				}
				//move current
				current += length + skip;
				if(current >= knotHash.size())
					current %= knotHash.size();
				
				//increment skip
				skip++;
			}		
		}
		
		String dense = "";
		for(int i = 0; i < 16; i++) {
			int startIndex = i * 16;
			int xor = knotHash.get(startIndex);
			for(int j = 1; j < 16; j++) {
				xor = xor ^ knotHash.get(startIndex + j);
			}
			//do each digit separately (to auto-append leading zeros if necessary
			dense += Integer.toHexString(xor >> 4);
			dense += Integer.toHexString(xor & 15);
		}
		
		return dense;
	}

	public static void main(String[] args) {
		input = Input.fetchInput(2017,10).replace("\n","");
		DayRunner.run(new Day10());
	}

}
