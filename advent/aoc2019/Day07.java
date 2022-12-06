package advent.aoc2019;

import java.util.ArrayList;
import java.util.Arrays;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;
import advent.utilities.utils2019.IntCodeComputer;

public class Day07 implements IDay {

	static String input;

	@Override
	public String part1() {
		ArrayList<Long> program = new ArrayList<Long>(Arrays.stream(input.split(",")).map(x -> Long.parseLong(x)).toList());
		//generate possible orders of initial conditions
		ArrayList<int[]> permutations = perms(new int[]{0,1,2,3,4},5);
		long bestResult = 0;
		for(int[] a : permutations) {
			ArrayList<Integer> phases = toList(a);
			ArrayList<IntCodeComputer> amps = new ArrayList<IntCodeComputer>();
			//initialize each computer with the initial condition
			for(int i = 0; i < 5; i++) {
				amps.add(new IntCodeComputer(program));
				amps.get(i).input(phases.remove(0));
			}
			amps.get(0).input(0);
			//cycle output forwards
			for(int i = 0; i < 5; i++) {
				amps.get(i).run();
				if(i < 4) {
					amps.get(i+1).input(amps.get(i).output());
				} else {
					long out = amps.get(i).output();
					if(out > bestResult)
						bestResult = out;
				}
			}
		}
		return Long.toString(bestResult);
	}

	@Override
	public String part2() {
		ArrayList<Long> program = new ArrayList<Long>(Arrays.stream(input.split(",")).map(x -> Long.parseLong(x)).toList());
		//same as above
		ArrayList<int[]> permutations2 = perms(new int[] {5,6,7,8,9},5);
		long best = 0;
		for(int[] a : permutations2) {
			ArrayList<Integer> phases = toList(a);
			ArrayList<IntCodeComputer> amps = new ArrayList<IntCodeComputer>();
			for(int i = 0; i < 5; i++) {
				amps.add(new IntCodeComputer(program));
				amps.get(i).input(phases.remove(0));
			}
			//while last computer has not halted, cycle output forwards to next computer
			amps.get(0).input(0);
			while(!amps.get(4).halted) {
				for(int i = 0; i < 5; i++) {
					amps.get(i).run();
					if(i < 4) {
						amps.get(i+1).input(amps.get(i).output());
					} else if(!amps.get(4).halted){
						amps.get(0).input(amps.get(i).output());
					}
					
				}
			}
			long result = amps.get(4).output();
			if(result > best) {
				best = result;
			}
		}
		return Long.toString(best);
	}

	//Heap's permutation generation algorithm. look it up on wikipedia if you need to
	public static ArrayList<int[]> perms(int[] a, int n) {
		ArrayList<int[]> perms = new ArrayList<int[]>();
		if(n == 1) {
			perms.add(Arrays.copyOf(a, a.length));
			return perms;
		}
		
		perms.addAll(perms(a,n-1));
		for(int i = 0; i < n-1; i++) {
			if(n % 2 == 0) {
				int temp = a[i];
				a[i] = a[n-1];
				a[n-1] = temp;
			} else {
				int temp = a[0];
				a[0] = a[n-1];
				a[n-1] = temp;
			}
			perms.addAll(perms(a,n-1));
		}
		return perms;
	}
	
	public static ArrayList<Integer> toList(int[] a) {
		ArrayList<Integer> i = new ArrayList<Integer>();
		for(int b : a)
			i.add(b);
		return i;
	}

	public static void main(String[] args) {
		input = Input.fetchInput(2019,7).trim();
		DayRunner.run(new Day07());
	}

}
