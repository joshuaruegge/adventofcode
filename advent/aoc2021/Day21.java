package advent.aoc2021;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

public class Day21 implements IDay {

	static String input;
	
	@Override
	public String part1() {
		int player1 = Integer.parseInt(input.split("\n")[0].split(" ")[4]);
		int player2 = Integer.parseInt(input.split("\n")[1].split(" ")[4]);
		
		int dice = 1;
		int diceCount = 0;
		
		int p1Score = 0;
		int p2Score = 0;
		
		while(p1Score < 1000 && p2Score < 1000) {
			for(int i = 0; i < 3; i++) {
				player1 += dice;
				dice = (dice + 1);
				if(dice > 100)
					dice = 1;
				diceCount++;
			}
			
			while(player1 > 10)
				player1 -= 10;
			
			p1Score += player1;
			
			if(p1Score >= 1000)
				break;
			
			for(int i = 0; i < 3; i++) {
				player2 += dice;
				dice = (dice + 1);
				if(dice > 100)
					dice = 1;
				diceCount++;
			}
			
			while(player2 > 10)
				player2 -= 10;
			p2Score += player2;
		}
		
		return Integer.toString(diceCount * (Math.min(p1Score, p2Score)));
	}

	static long p1,p2;
	
	//precalculated table representing the potential results and frequencies of the sum of three three-sided dice rolls
	//diceResults[i] == the number of times three three-sided dice rolls sum to i
	//ex. only one possible state out of the 27 results in 3, and the same for 9
	//so diceResults[3] and diceResults[9] are 1
	static final int[] diceResults = new int[] {0,0,0,1,3,6,7,6,3,1};
	
	@Override
	public String part2() {
		p1 = 0;
		p2 = 0;
		
		int player1 = Integer.parseInt(input.split("\n")[0].split(" ")[4]);
		int player2 = Integer.parseInt(input.split("\n")[1].split(" ")[4]);
		
		recurse(1,player1,player2,0,0,true);

		return Long.toString(Math.max(p1, p2));
	}
	
	//mult represents the number of universes the given condition is currently taking place in
	public void recurse(long mult, int p1Pos, int p2Pos, int p1Score, int p2Score, boolean turn) {
		if(p1Score >= 21) {
			p1 += mult;
			return;
		}
		if(p2Score >= 21) {
			p2 += mult;
			return;
		}
		if(turn) {
			for(int i = 3; i < 10; i++) {
				int newP1 = p1Pos + i;
				if(newP1 > 10)
					newP1 -= 10;
				//multiply mult by the number of times this result of 3 dice rolls happens
				recurse(mult * diceResults[i], newP1, p2Pos, p1Score + newP1, p2Score, false);
			}
		} else {
			for(int i = 3; i < 10; i++) {
				int newP2 = p2Pos + i;
				if(newP2 > 10)
					newP2 -= 10;
				recurse(mult * diceResults[i], p1Pos,newP2, p1Score, p2Score + newP2, true);
			}
		}
	}

	public static void main(String[] args) {
		input = Input.fetchInput(2021,21);
		DayRunner.run(new Day21());
	}
}
