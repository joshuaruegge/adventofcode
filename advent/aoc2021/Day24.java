package advent.aoc2021;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import advent.utilities.general.Coord3;
import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

public class Day24 implements IDay {

	static String input;
	
	@Override
	public String part1() {
		List<String> lines = Arrays.stream(input.split("\n")).toList();
		
		//use Coord to store the pair of integers
		ArrayList<Coord3> importantValues = new ArrayList<Coord3>();
		
		//the programs repeat a set of 18 instructions over and over for each input
		//examining and reducing these instruction patterns, only two values become important
		//these being the number arguments of instructions 6 and 16
		//so, extract these numbers, tuple them with Coord, and store them
		
		for(int index = 0, i = 5, j = 15;i < lines.size() && j < lines.size(); index++, i += 18, j += 18) {
			//sixth instruction
			int a = Integer.parseInt(lines.get(i).split(" ")[2]);
			//sixteenth instruction
			int b = Integer.parseInt(lines.get(j).split(" ")[2]);
			
			importantValues.add(new Coord3(index,a,b));
		}
		
		//each 18-instruction set does one of two actions:
		//sets z to 26 * z + input + y if x >= 10
		//sets z to z/26 if x < 10
		//in an ideal solution, the multiplication and division operations are balanced, so we only care about the (input + y) portion
		//if x is greater than 10, we need to reduce it so that it is below 10 again, otherwise we fall out of balance
		//because all inputs are 0-9, no single input can upset this balance
		//by keeping conditions on the stack (because the multiplication and division operations cancel, and the addition is linear), we can improve calculation time
		int[] digits = new int[14];
		LinkedList<Coord3> stack = new LinkedList<Coord3>();
		for(Coord3 value : importantValues) {
			if(value.y >= 10) {
				stack.push(value);
			} else {
				int index = value.x;
				Coord3 prev = stack.pop();
				
				if((prev.z + value.y) >= 0) {
					digits[index] = 9;
					digits[prev.x] = 9 - (prev.z + value.y);
				} else {
					digits[prev.x] = 9;
					digits[index] = 9 + (prev.z + value.y);
				}
			}
		}
		String answer = "";
		for(int i : digits)
			answer += i;
		return answer;
	}

	@Override
	public String part2() {
		List<String> lines = Arrays.stream(input.split("\n")).toList();
		
		//use Coord to store the pair of integers
		ArrayList<Coord3> importantValues = new ArrayList<Coord3>();
		
		//the programs repeat a set of 18 instructions over and over for each input
		//examining and reducing these instruction patterns, only two values become important
		//these being the number arguments of instructions 6 and 16
		//so, extract these numbers, tuple them with Coord, and store them
		
		for(int index = 0, i = 5, j = 15;i < lines.size() && j < lines.size(); index++, i += 18, j += 18) {
			//sixth instruction
			int a = Integer.parseInt(lines.get(i).split(" ")[2]);
			//sixteenth instruction
			int b = Integer.parseInt(lines.get(j).split(" ")[2]);
			
			importantValues.add(new Coord3(index,a,b));
		}
		
		//each 18-instruction set does one of two actions:
		//sets z to 26 * z + input + y if x >= 10
		//sets z to z/26 if x < 10
		//in an ideal solution, the multiplication and division operations are balanced, so we only care about the (input + y) portion
		//if x is greater than 10, we need to reduce it so that it is below 10 again, otherwise we fall out of balance
		//because all inputs are 0-9, no single input can upset this balance
		//by keeping conditions on the stack (because the multiplication and division operations cancel, and the addition is linear), we can improve calculation time
		int[] digits = new int[14];
		LinkedList<Coord3> stack = new LinkedList<Coord3>();
		for(Coord3 value : importantValues) {
			if(value.y >= 10) {
				stack.push(value);
			} else {
				int index = value.x;
				Coord3 prev = stack.pop();
				
				if((prev.z + value.y) >= 0) {
					digits[prev.x] = 1;
					digits[index] = 1 + (prev.z + value.y);
				} else {
					digits[index] = 1;
					digits[prev.x] = 1 - (prev.z + value.y);
				}
			}
		}
		String answer = "";
		for(int i : digits)
			answer += i;
		return answer;
	}

	public static void main(String[] args) {
		input = Input.fetchInput(2021,24);
		DayRunner.run(new Day24());
	}
}
