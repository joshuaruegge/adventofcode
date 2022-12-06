package advent.aoc2019;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import advent.utilities.general.Coord;
import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;
import advent.utilities.utils2019.IntCodeComputer;

public class Day13 implements IDay {

	static String input;

	@Override
	public String part1() {
		//start computer
		ArrayList<Long> program = new ArrayList<Long>(Arrays.stream(input.split(",")).map(x -> Long.parseLong(x)).toList());
		IntCodeComputer game = new IntCodeComputer(program);
		game.run();
		
		int blockCount = 0;
		for(int i = 2; i < game.output.size(); i += 3) {
			if(game.output.get(i) == 2)
				blockCount++;
		}
		return Integer.toString(blockCount);
	}

	@Override
	public String part2() {
		//start computer
		ArrayList<Long> program = new ArrayList<Long>(Arrays.stream(input.split(",")).map(x -> Long.parseLong(x)).toList());
		//quarters
		program.set(0, 2l);
		IntCodeComputer game = new IntCodeComputer(program);
		game.run();
		//keep all game objects in one big HashMap of coordinate to tile ID
		HashMap<Coord,Integer> gameState = new HashMap<Coord,Integer>();
		while(game.output.size() > 0) {
			gameState.put(new Coord(Math.toIntExact(game.output()), Math.toIntExact(game.output())), Math.toIntExact(game.output()));
		}
		
		while(!game.halted) {
			//locate ball and paddle by iterating through gamestate
			int ballX = -1;
			int paddleX = -1;
			for(Coord c : gameState.keySet()) {
				if(gameState.get(c) == 3) {
					paddleX = c.x;
				}
				if(gameState.get(c) == 4) {
					ballX = c.x;
				}
			}
			//just move paddle left or right towards current position of ball
			int input = 0;
			if(ballX > paddleX) {
				input = 1;
			} else if (ballX < paddleX) {
				input = -1;
			}
			game.input(input);
			game.run();
			//update gamestate with new output
			while(game.output.size() > 0) {
				gameState.put(new Coord(Math.toIntExact(game.output()), Math.toIntExact(game.output())), Math.toIntExact(game.output()));
			}
			//this input pattern will get the answer. eventually
		}
		//extract score
		return Integer.toString(gameState.get(new Coord(-1,0)));
	}
	
	public static void main(String[] args) {
		input = Input.fetchInput(2019,13).trim();
		DayRunner.run(new Day13());
	}

}
