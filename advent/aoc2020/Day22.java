package advent.aoc2020;

import java.util.HashSet;
import java.util.LinkedList;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;

public class Day22 implements IDay {

	//CRAB COMBAT!!!!!
	String input = "Player 1:\r\n"
			+ "4\r\n"
			+ "25\r\n"
			+ "3\r\n"
			+ "11\r\n"
			+ "2\r\n"
			+ "29\r\n"
			+ "41\r\n"
			+ "23\r\n"
			+ "30\r\n"
			+ "21\r\n"
			+ "50\r\n"
			+ "8\r\n"
			+ "1\r\n"
			+ "24\r\n"
			+ "27\r\n"
			+ "10\r\n"
			+ "42\r\n"
			+ "43\r\n"
			+ "38\r\n"
			+ "15\r\n"
			+ "18\r\n"
			+ "13\r\n"
			+ "32\r\n"
			+ "37\r\n"
			+ "34\r\n"
			+ "\r\n"
			+ "Player 2:\r\n"
			+ "12\r\n"
			+ "6\r\n"
			+ "36\r\n"
			+ "35\r\n"
			+ "40\r\n"
			+ "47\r\n"
			+ "31\r\n"
			+ "9\r\n"
			+ "46\r\n"
			+ "49\r\n"
			+ "19\r\n"
			+ "16\r\n"
			+ "5\r\n"
			+ "26\r\n"
			+ "39\r\n"
			+ "48\r\n"
			+ "7\r\n"
			+ "44\r\n"
			+ "45\r\n"
			+ "20\r\n"
			+ "17\r\n"
			+ "14\r\n"
			+ "33\r\n"
			+ "28\r\n"
			+ "22";
	
	@Override
	public String part1() {
		LinkedList<Integer> deck1 = new LinkedList<Integer>();
		LinkedList<Integer> deck2 = new LinkedList<Integer>();
		
		String[] playerParts = input.split("\r\n\r\n");
		String[] player1Cards = playerParts[0].split("\r\n");
		for(int i = 1; i < player1Cards.length; i++)
			deck1.add(Integer.parseInt(player1Cards[i]));
		
		String[] player2Cards = playerParts[1].split("\r\n");
		for(int i = 1; i < player2Cards.length; i++)
			deck2.add(Integer.parseInt(player2Cards[i]));
		
		while(deck1.size() > 0 && deck2.size() > 0) {
			int card1 = deck1.pop();
			int card2 = deck2.pop();
			if(card1 > card2) {
				deck1.add(card1);
				deck1.add(card2);
			} else {
				deck2.add(card2);
				deck2.add(card1);
			}
		}
		
LinkedList<Integer> winner = (deck1.size() > 0 ? deck1 : deck2);
		
		int score = 0;
		for(int i = 0; i < winner.size(); i++) {
			score += winner.get(i) * (winner.size() - i);
		}
		
		return Integer.toString(score);
	}

	@Override
	public String part2() {
		LinkedList<Integer> deck1 = new LinkedList<Integer>();
		LinkedList<Integer> deck2 = new LinkedList<Integer>();
		
		String[] playerParts = input.split("\r\n\r\n");
		String[] player1Cards = playerParts[0].split("\r\n");
		for(int i = 1; i < player1Cards.length; i++)
			deck1.add(Integer.parseInt(player1Cards[i]));
		
		String[] player2Cards = playerParts[1].split("\r\n");
		for(int i = 1; i < player2Cards.length; i++)
			deck2.add(Integer.parseInt(player2Cards[i]));
		
		game(deck1,deck2);
		
		LinkedList<Integer> winner = (deck1.size() > 0 ? deck1 : deck2);
	
		int score = 0;
		for(int i = 0; i < winner.size(); i++) {
			score += winner.get(i) * (winner.size() - i);
		}
		
		return Integer.toString(score);
	}
	
	//true for player 1 win, false for player 2 win
	public boolean game(LinkedList<Integer> deck1, LinkedList<Integer> deck2) {
		HashSet<LinkedList<Integer>> deckStates = new HashSet<LinkedList<Integer>>();
		LinkedList<Integer> key = new LinkedList<Integer>(deck1);
		//buffer value (to mark in between decks and avoid situations where deck1 and deck2 are different, but produce the same key when appended)
		key.add(100);
		key.addAll(deck2);
		
		deckStates.add(key);
		do {
			int card1 = deck1.pop();
			int card2 = deck2.pop();
			if(deck1.size() >= card1 && deck2.size() >= card2) {
				//recurse, determine winner
				LinkedList<Integer> newDeck1 = new LinkedList<Integer>();
				for(int i = 0; i < card1; i++)
					newDeck1.add(deck1.get(i));
				LinkedList<Integer> newDeck2 = new LinkedList<Integer>();
				for(int i = 0; i < card2; i++)
					newDeck2.add(deck2.get(i));
				
				boolean winner = game(newDeck1,newDeck2);
				if(winner) {
					deck1.add(card1);
					deck1.add(card2);
				} else {
					deck2.add(card2);
					deck2.add(card1);
				}
			} else {
				if(card1 > card2) {
					deck1.add(card1);
					deck1.add(card2);
				} else {
					deck2.add(card2);
					deck2.add(card1);
				}
			}
			//rebuild key
			key = new LinkedList<Integer>(deck1);
			key.add(100);
			key.addAll(deck2);
		} while((deck1.size() > 0 && deck2.size() > 0) && deckStates.add(key));
		
		boolean winner = (deckStates.contains(key) || deck1.size() > 0 ? true : false);
		
		return winner;
	}

	public static void main(String[] args) {
		DayRunner.run(new Day22());
	}

}
