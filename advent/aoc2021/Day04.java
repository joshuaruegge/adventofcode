package advent.aoc2021;

import java.util.Arrays;
import java.util.List;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

public class Day04 implements IDay {

	static String input;
	
	@Override
	public String part1() {
		String[] boards = input.split("\n\n");
		//first string in boards is numbers - parse to list
		List<Integer> numberOrder = Arrays.stream(boards[0].split(",")).map(Integer::parseInt).toList();
		
		//rather than go over numbers and slowly mark boards, go over each board and determine how long it will take to produce a bingo
		int earliestBingo = Integer.MAX_VALUE;
		List<Integer> bestBoard = null;
		for(int i = 1; i < boards.length; i++) {
			//store board as a flat number list - we can still access rows and columns pretty easily
			List<Integer> board = Arrays.stream(boards[i].trim().split("  | |\n |\n")).map(Integer::parseInt).toList();
			
			int boardBestBingo = Integer.MAX_VALUE;
			//row loop
			//start at index j, then get it and next four values for "row". j then increases by five
			for(int j = 0; j < board.size(); j += 5) {
				int highestOfRow = 0;
				for(int k = 0; k < 5; k++) {
					int bingoNumber = board.get(j+k);
					//time number is called is index in numberOrder
					highestOfRow = Math.max(highestOfRow, numberOrder.indexOf(bingoNumber));
				}
				boardBestBingo = Math.min(boardBestBingo, highestOfRow);
			}
			
			//col loop
			//start at index j, then get it and every fifth number after
			for(int j = 0; j < 5; j++) {
				int highestOfCol = 0;
				for(int k = 0; k < board.size(); k += 5) {
					int bingoNumber = board.get(j+k);
					highestOfCol = Math.max(highestOfCol, numberOrder.indexOf(bingoNumber));
				}
				boardBestBingo = Math.min(boardBestBingo, highestOfCol);
			}
				
			if(boardBestBingo < earliestBingo) {
				earliestBingo = boardBestBingo;
				bestBoard = board;
			}
		}
		
		//lastly, total unmarked numbers on board and multiply by last number called
		int total = 0;
		for(int i : bestBoard) {
			//if number is unmarked (not called till after bingo)
			if(numberOrder.indexOf(i) > earliestBingo)
				total += i;
		}
		
		//multiply by last number called
		total *= numberOrder.get(earliestBingo);
		
		return Integer.toString(total);
	}
	
	@Override
	public String part2() {
		String[] boards = input.split("\n\n");
		//first string in boards is numbers - parse to list
		List<Integer> numberOrder = Arrays.stream(boards[0].split(",")).map(Integer::parseInt).toList();
		
		//rather than go over numbers and slowly mark boards, go over each board and determine how long it will take to produce a bingo
		//simple change - now look for latest bingo
		//a board's first bingo still counts, but we want the "last" "first" bingo
		int latestBingo = 0;
		List<Integer> bestBoard = null;
		for(int i = 1; i < boards.length; i++) {
			//store board as a flat number list - we can still access rows and columns pretty easily
			List<Integer> board = Arrays.stream(boards[i].trim().split("  | |\n |\n")).map(Integer::parseInt).toList();
			
			int boardBestBingo = Integer.MAX_VALUE;
			//row loop
			//start at index j, then get it and next four values for "row". j then increases by five
			for(int j = 0; j < board.size(); j += 5) {
				int highestOfRow = 0;
				for(int k = 0; k < 5; k++) {
					int bingoNumber = board.get(j+k);
					//time number is called is index in numberOrder
					highestOfRow = Math.max(highestOfRow, numberOrder.indexOf(bingoNumber));
				}
				boardBestBingo = Math.min(boardBestBingo, highestOfRow);
			}
			
			//col loop
			//start at index j, then get it and every fifth number after
			for(int j = 0; j < 5; j++) {
				int highestOfCol = 0;
				for(int k = 0; k < board.size(); k += 5) {
					int bingoNumber = board.get(j+k);
					highestOfCol = Math.max(highestOfCol, numberOrder.indexOf(bingoNumber));
				}
				boardBestBingo = Math.min(boardBestBingo, highestOfCol);
			}
				
			if(boardBestBingo > latestBingo) {
				latestBingo = boardBestBingo;
				bestBoard = board;
			}
		}
		
		//lastly, total unmarked numbers on board and multiply by last number called
		int total = 0;
		for(int i : bestBoard) {
			//if number is unmarked (not called till after bingo)
			if(numberOrder.indexOf(i) > latestBingo)
				total += i;
		}
		
		//multiply by last number called
		total *= numberOrder.get(latestBingo);
		
		return Integer.toString(total);
	}

	public static void main(String[] args) {
		input = Input.fetchInput(2021,4);
		DayRunner.run(new Day04());
	}
}
