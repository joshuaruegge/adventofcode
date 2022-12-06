package advent.aoc2021;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

public class Day10 implements IDay {

	static String input;
	
	@Override
	public String part1() {
		int score = 0;
		lines:
		for(String s : input.split("\n")) {
			LinkedList<Character> stack = new LinkedList<Character>();
			//put characters onto a stack - if matching closing character is found, remove the opener from the stack
			//this preserves the order that the chunks appear in, and also helps us track the beginning of the corrupted chunk
			for(char c : s.toCharArray()) { 
				//automatically put openers on stack
				if(c == '(' || c == '[' || c == '{' || c == '<')
					stack.add(c);
				else {
					boolean correct = false;
					switch(c) {
					case ')':
						if(stack.getLast() == '(')
							correct = true;
						break;
					case ']':
						if(stack.getLast() == '[')
							correct = true;
						break;
					case '}':
						if(stack.getLast() == '{')
							correct = true;
						break;
					case '>':
						if(stack.getLast() == '<')
							correct = true;
						break;
					}
					
					if(correct) {
						//group is over, so pop last off stack
						stack.removeLast();
					} else {
						//improper character - add to score based on identity
						switch(c) {
						case ')':
							score += 3;
							break;
						case ']':
							score += 57;
							break;
						case '}':
							score += 1197;
							break;
						case '>':
							score += 25137;
							break;
						}
						continue lines;
					}
				}
			}
			
		}
		return Integer.toString(score);
	}

	@Override
	public String part2() {
		ArrayList<Long> scores = new ArrayList<Long>();
		lines:
		for(String s : input.split("\n")) {
			LinkedList<Character> stack = new LinkedList<Character>();
			//put characters onto a stack - if matching closing character is found, remove the opener from the stack
			//this preserves the order that the chunks appear in, and also helps us track the beginning of the corrupted chunk
			for(char c : s.toCharArray()) { 
				//automatically put openers on stack
				if(c == '(' || c == '[' || c == '{' || c == '<')
					stack.add(c);
				else {
					boolean correct = false;
					switch(c) {
					case ')':
						if(stack.getLast() == '(')
							correct = true;
						break;
					case ']':
						if(stack.getLast() == '[')
							correct = true;
						break;
					case '}':
						if(stack.getLast() == '{')
							correct = true;
						break;
					case '>':
						if(stack.getLast() == '<')
							correct = true;
						break;
					}
					
					if(correct) {
						//group is over, so pop last off stack
						stack.removeLast();
					} else {
						//improper character - ignore line
						continue lines;
					}
				}
			}
			//if we made it here, line is simply incomplete. calculate score based on items left in stack
			long score = 0;
			while(stack.size() > 0) {
				score *= 5;
				char c = stack.removeLast();
				switch(c) {
				case '(':
					score += 1;
					break;
				case '[':
					score += 2;
					break;
				case '{':
					score += 3;
					break;
				case '<':
					score += 4;
					break;
				}
			}
			scores.add(score);
		}
		//extract and return middle score
		Collections.sort(scores);
		return Long.toString(scores.get(scores.size()/2));
	}

	public static void main(String[] args) {
		input = Input.fetchInput(2021,10);
		DayRunner.run(new Day10());
	}
}
