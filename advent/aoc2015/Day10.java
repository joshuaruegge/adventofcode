package advent.aoc2015;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;

public class Day10 implements IDay {

	String input = "3113322113";

	@Override
	public String part1() {
		String sequence = input;
		for(int x = 0; x < 40; x++) {
			//using a stringbuilder rather than a string reduces concat times 
			//(concat is O(n^2)) and makes iteration trivial
			StringBuilder next = new StringBuilder();
			char number = sequence.charAt(0);
			int times = 0;
			//iterate over string, appending (times repeated) then (number repeated) to
			//new string whenever number changes
			for(int i = 0; i < sequence.length(); i++) {
				//if current number is not equal to stored number, repetition is done - append
				if(!(sequence.charAt(i) == number)) {
					next.append(times);
					next.append(number);
					number = sequence.charAt(i);
					times = 1;
				} else {
					times++;
				}
			}
			//final append
			next.append(times);
			next.append(number);
			//update sequence
			sequence = next.toString();
		}
		return Integer.toString(sequence.length());
	}

	@Override
	public String part2() {
		String sequence = input;
		for(int x = 0; x < 50; x++) {
			//using a stringbuilder rather than a string reduces concat times and makes iteration trivial
			StringBuilder next = new StringBuilder();
			char number = sequence.charAt(0);
			int times = 0;
			//iterate over string, appending (times repeated) then (number repeated) to
			//new string whenever number changes
			for(int i = 0; i < sequence.length(); i++) {
				//when repeat stops, add times and number to sequence, then set number to new value and times to 1
				if(!(sequence.charAt(i) == number)) {
					next.append(times);
					next.append(number);
					number = sequence.charAt(i);
					times = 1;
				} else {
					times++;
				}
			}
			//final append
			next.append(times);
			next.append(number);
			sequence = next.toString();
		}
		return Integer.toString(sequence.length());
	}

	public static void main(String[] args) {
		DayRunner.run(new Day10());
	}

}
