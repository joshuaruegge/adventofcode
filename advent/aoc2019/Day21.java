package advent.aoc2019;

import java.util.ArrayList;
import java.util.Arrays;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;
import advent.utilities.utils2019.IntCodeComputer;

public class Day21 implements IDay {

	//the algorithm required to generate a program for this one would be absurdly tedious and require a lot of output parsing
	//so just manually make one with guess and check. if the ones in here don't work, examine the output it gives and adjust them.
	static String input;

	@Override
	public String part1() {
		//initialize program
		ArrayList<Long> program = new ArrayList<Long>(Arrays.stream(input.split(",")).map(x -> Long.parseLong(x)).toList());
		IntCodeComputer i = new IntCodeComputer(program);
		//jump script - essentially, jump if:
		//2 and 3 are empty but 4 is full
		//OR if 1 is empty
		String springscript = "NOT C T\n"
				+ "NOT B J\n"
				+ "OR T J\n"
				+ "AND D J\n"
				+ "NOT A T\n"
				+ "OR T J";
		//input script
		for(String s : springscript.split("\n")) {
			for(char c : s.toCharArray()) {
				i.input((long) c);
			}
			i.input(10);
		}
		//input initialization
		String end = "WALK";
		for(char c : end.toCharArray()) {
			i.input((long) c);
		}
		i.input(10);
		i.run();
		//get rid of unnecessary output values by skipping 4 lines (by detecting ASCII newline):
		int lineCounter = 0;
		while(lineCounter < 4) {
			if(i.output() == 10)
				lineCounter++;
		}
		//print output:
		for(long l : i.output) {
			//if in ascii-space, print as ascii, otherwise return hull damage value
			if(l < 1000) {
				System.out.print((char) l);
			} else {
				return Long.toString(l);
			}
		}
		return "Solution failed - see output and adjust springscript!";
	}

	@Override
	public String part2() {
		ArrayList<Long> program = new ArrayList<Long>(Arrays.stream(input.split(",")).map(x -> Long.parseLong(x)).toList());
		for(String s : input.split(",")) {
			program.add(Long.parseLong(s));
		}
		//only added condition - only jump if there's space at location 8
		String springscript =  "NOT C T\n"
				+ "NOT B J\n"
				+ "OR T J\n"
				+ "AND D J\n"
				+ "AND H J\n"
				+ "NOT A T\n"
				+ "OR T J";
		IntCodeComputer i = new IntCodeComputer(program);
		for(String s : springscript.split("\n")) {
			for(char c : s.toCharArray()) {
				i.input((long) c);
			}
			i.input(10);
		}
		
		String end = "RUN";
		for(char c : end.toCharArray()) {
			i.input((long) c);
		}
		i.input(10);
		i.run();
		//get rid of unnecessary output values by skipping 4 lines (by detecting ASCII newline):
		int lineCounter = 0;
		while(lineCounter < 4) {
			if(i.output() == 10)
				lineCounter++;
		}
		
		//print output - either damage value or ASCII characters
		for(long l : i.output) {
			if(l < 1000) {
				System.out.print((char) l);
			} else {
				return Long.toString(l);
			}
		}
		return "Solution failed - see output and adjust springscript!";
	}

	public static void main(String[] args) {
		input = Input.fetchInput(2019,21).trim();
		DayRunner.run(new Day21());
	}
}
