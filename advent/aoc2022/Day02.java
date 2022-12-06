package advent.aoc2022;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

public class Day02 implements IDay {

	static String input;
	
	@Override
	public String part1() {
		int total = 0;
		for(String s : input.split("\n")) {
			String[] t = s.split(" ");
			int roundScore = 0;
			//if draw
			if(t[0].charAt(0) == t[1].charAt(0) - 23) {
				roundScore += 3;
			} else {
				switch(t[0]) {
				//rock
				case "A":
					//if we picked paper, we win
					if(t[1].equals("Y"))
						roundScore += 6;
					break;
				//paper
				case "B":
					//if we picked scissors, we win
					if(t[1].equals("Z"))
						roundScore += 6;
					break;
				//scissors
				case "C":
					//if we picked rock, we win
					if(t[1].equals("X"))
						roundScore += 6;
					break;
				}
			}
			switch(t[1]) {
			case "X":
				roundScore += 1;
				break;
			case "Y":
				roundScore += 2;
				break;
			case "Z":
				roundScore += 3;
				break;
			}
			total += roundScore;
		}
		return Integer.toString(total);
	}

	@Override
	public String part2() {
		int total = 0;
		for(String s : input.split("\n")) {
			String[] t = s.split(" ");
			int roundScore = 0;
			//must lose
			switch(t[1]) {
			case "X":
				switch(t[0]) {
				//rock - picked scissors
				case "A":
					roundScore += 3;
					break;
				//paper - picked rock
				case "B":
					roundScore += 1;
					break;
				//scissors - picked paper
				case "C":
					roundScore += 2;
					break;
				}
				break;
			//draw
			case "Y":
				roundScore += 3;
				switch(t[0]) {
				case "A":
					roundScore += 1;
					break;
				case "B":
					roundScore += 2;
					break;
				case "C":
					roundScore += 3;
					break;
				}
				break;
			//must win
			case "Z":
				switch(t[0]) {
				//rock - pick paper
				case "A":
					roundScore += 8;
					break;
				//paper - pick scissors
				case "B":
					roundScore += 9;
					break;
				//scissors - pick rock
				case "C":
					roundScore += 7;
					break;
				}
				break;
			}
			total += roundScore;
		}
		return Integer.toString(total);
	}

	public static void main(String[] args) {
		input = Input.fetchInput(2022,2);
		DayRunner.run(new Day02());
	}

}
