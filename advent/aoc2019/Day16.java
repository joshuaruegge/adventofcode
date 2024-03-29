package advent.aoc2019;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

public class Day16 implements IDay {

	static String input;
	
	@Override
	public String part1() {
		int[] state = new int[input.length()];
		for(int i = 0; i < input.length(); i++) {
			state[i] = Integer.parseInt(input.charAt(i) + "");
		}
		//run 100 phase iterations
		for(int phase = 0; phase < 100; phase++) {
			//create new array
			int[] newState = new int[state.length];
			//for each element of old array, populate new array element with sum of all old values of array multiplied by pattern
			for(int elem = 0; elem < state.length; elem++) {
				int sum = 0;
				for(int i = 0; i < state.length; i++) {
					sum += state[i] * pattern(elem,i);
				}
				newState[elem] = Math.abs(sum) % 10;
			}
			state = newState;
		}
		//parse and return first 8 numbers
		String ret = "";
		for(int i = 0; i < 8; i++) {
			ret += state[i];
		}
		return ret;
	}

	@Override
	public String part2() {
		//calculate offset
		int offset = Integer.parseInt(input.substring(0,7));
		int[] state = new int[input.length()];
		for(int i = 0; i < input.length(); i++) {
			state[i] = Integer.parseInt(input.charAt(i) + "");
		}
		//create new array to hold entire pattern
		int[] newState = new int[state.length * 10000];
		//fun system arraycopy bullshit
		for(int n = 0; n < 10000; n++) {
			System.arraycopy(state, 0, newState, n*state.length, state.length);
		}
		//set state to new array
		state = newState;
		
		//calculate distance from end
		int fromEnd = state.length - offset;
		//total array size requred to calculate value from back end is approx. 2x distance
		int toDo = fromEnd * 2;
		
		//create new array, only copy in required length for calculation
		int[] calcPortion = new int[toDo];
		System.arraycopy(state, state.length - toDo, calcPortion, 0, toDo);
		//run 100 iterations of from-back phase calculation
		for(int phase = 0; phase < 100; phase++) {
			calcPortion = phase(calcPortion);
		}
		
		//parse and return 8 digits after offset
		String ret = "";
		for(int i = 0; i < 8; i++) {
			ret += calcPortion[(calcPortion.length - fromEnd) + i];
		}
		return ret;
	}

	//calculates what number of the "pattern" we are on based on:
	public static int pattern(int newPositionCalculating, int oldPositionExamining) {
		//increment by one to avoid zero-division and account for dropping first zero of pattern
		newPositionCalculating++;
		oldPositionExamining++;
		//iteration represents the number of times we've gone (newPositionCalculating) steps through the array
		int iteration = (oldPositionExamining/newPositionCalculating);
		//doing modulo by 4 gives us whether we are on position 1,2,3, or 4 of the 4-number 0,1,0,-1 pattern
		iteration %= 4;
		switch(iteration) {
		case 0:
			return 0;
		case 1:
			return 1;
		case 2:
			return 0;
		case 3:
			return -1;
		}
		//unreachable, (x) % 4 always equals 0, 1, 2, or 3
		return -1;
	}

	//from about halfway through the list to the end, all preceding numbers are 0 in the pattern and self and all trailing numbers are 1.
	//therefore, we can calculate the back half of an incredibly long signal by simply setting each element to the 
	//modulo of the sum of itself and all elements after it.
	//this doesn't work for the front half, so we can't use it for part 1, but all potential part 2 intervals
	//seem to be in the back half, so we can utilize this trick
	public static int[] phase(int[] i) {
		int[] newPhase = new int[i.length];
		
		newPhase[i.length - 1] = i[i.length - 1];
		for(int pos = i.length - 2; pos >= 0; pos--) {
			newPhase[pos] = (i[pos] + newPhase[pos+1]) % 10;
		}
		return newPhase;
	}

	public static void main(String[] args) {
		input = Input.fetchInput(2019,16).trim();
		DayRunner.run(new Day16());
	}
}
