package advent.aoc2015;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

public class Day24 implements IDay {

	static String input;
	
	@Override
	public String part1() {
		String[] lines = input.split("\n");
		int[] weights = new int[lines.length];
		for(int i = 0; i < lines.length; i++)
			weights[i] = Integer.parseInt(lines[i]);
		//calculate sum of all weights
		int sum = 0;
		for(int i : weights)
			sum += i;
		//each compartment will need to weigh (total weight / 3) for all to be equal
		int targetWeight = sum / 3;
		
		//we only care about the first compartment, because we'll assume if the first compartment equals (weight / 3),
		//that the remaining weights can be divided into two other compartments evenly
		
		int bestCombo = 0;
		//use integer bitmask to represent package combinations (similar to day 17)
		//by starting from the highest possible combination, we'll try combos involving the largest packages first
		//meaning that we'll arrive at the shortest possible sum to reach targetWeight first
		for(int combo = (1 << weights.length)  - 1; combo > 0; combo--) {
			int weight = 0;
			for(int j = 0; j < weights.length; j++) {
				//if bit at j in combo is 1, add weight at index j to total
				if((combo & (1 << j))  != 0) {
					weight += weights[j];
				}
				if(weight > targetWeight)
					break;
			}
			if(weight == targetWeight) {
				bestCombo = combo;
				break;
			}
		}
		//total quantum entanglement of best configuration
		long quantum = 1;
		for(int i = 0; i < weights.length; i++) {
			if((bestCombo & (1 << i)) != 0)
				quantum *= weights[i];
		}
		return Long.toString(quantum);
	}
	
	@Override
	public String part2() {
		String[] lines = input.split("\n");
		int[] weights = new int[lines.length];
		for(int i = 0; i < lines.length; i++)
			weights[i] = Integer.parseInt(lines[i]);
		//calculate sum of all weights
		int sum = 0;
		for(int i : weights)
			sum += i;
		//only part 2 change - 4 compartments instead of 3
		//each compartment will need to weigh (total weight / 4) for all to be equal
		int targetWeight = sum / 4;
		
		//we only care about the first compartment, because we'll assume if the first compartment equals (weight / 3),
		//that the remaining weights can be divided into two other compartments evenly
		
		int bestComboCount = weights.length;
		long bestQuantum = Long.MAX_VALUE;
		//use integer bitmask to represent package combinations (similar to day 17)
		//by starting from the highest possible combination, we'll try combos involving the largest packages first
		//meaning that we'll arrive at the shortest possible sum to reach targetWeight first
		
		//ix-nay on the "arriving at the best first" logic - for part 2, we check all of them
		//and calculate quantums for valid combos on the fly to determine lowest
		for(int combo = (1 << weights.length)  - 1; combo > 0; combo--) {
			//skip if more packages than best solution so far
			if(Integer.bitCount(combo) > bestComboCount)
				continue;
			int weight = 0;
			for(int j = 0; j < weights.length; j++) {
				if((combo & (1 << j))  != 0) {
					weight += weights[j];
				}
				if(weight > targetWeight)
					break;
			}
			if(weight == targetWeight) {
				//calculate quantum, determine and log if best
				long quantum = 1;
				for(int i = 0; i < weights.length; i++) {
					if((combo & (1 << i)) != 0)
						quantum *= weights[i];
					if(quantum > bestQuantum)
						break;
				}
				if(quantum < bestQuantum) {
					bestQuantum = quantum;
					//track lowest number of packages to easily skip later instances without calculating quantum
					bestComboCount = Integer.bitCount(combo);
				}
			}
		}
		return Long.toString(bestQuantum);
	}

	public static void main(String[] args) {
		input = Input.fetchInput(2015,24);
		DayRunner.run(new Day24());
	}

}
