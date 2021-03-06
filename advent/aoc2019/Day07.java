package advent.aoc2019;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.utils2019.IntCodeComputer;

public class Day07 implements IDay {

	static String input = "3,8,1001,8,10,8,105,1,0,0,21,34,59,68,89,102,183,264,345,426,99999,3,9,102,5,9,9,1001,9,5,9,4,9,99,3,9,101,3,9,9,1002,9,5,9,101,5,9,9,1002,9,3,9,1001,9,5,9,4,9,99,3,9,101,5,9,9,4,9,99,3,9,102,4,9,9,101,3,9,9,102,5,9,9,101,4,9,9,4,9,99,3,9,1002,9,5,9,1001,9,2,9,4,9,99,3,9,1002,9,2,9,4,9,3,9,101,2,9,9,4,9,3,9,1001,9,2,9,4,9,3,9,101,1,9,9,4,9,3,9,102,2,9,9,4,9,3,9,1001,9,2,9,4,9,3,9,1001,9,2,9,4,9,3,9,1001,9,2,9,4,9,3,9,1001,9,2,9,4,9,3,9,102,2,9,9,4,9,99,3,9,1001,9,1,9,4,9,3,9,102,2,9,9,4,9,3,9,102,2,9,9,4,9,3,9,101,1,9,9,4,9,3,9,101,1,9,9,4,9,3,9,102,2,9,9,4,9,3,9,102,2,9,9,4,9,3,9,101,1,9,9,4,9,3,9,1001,9,2,9,4,9,3,9,1001,9,2,9,4,9,99,3,9,1002,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,102,2,9,9,4,9,3,9,1001,9,2,9,4,9,3,9,1001,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,101,1,9,9,4,9,3,9,1001,9,1,9,4,9,3,9,1002,9,2,9,4,9,3,9,102,2,9,9,4,9,99,3,9,101,1,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,101,1,9,9,4,9,3,9,101,2,9,9,4,9,99,3,9,1001,9,1,9,4,9,3,9,1001,9,2,9,4,9,3,9,101,1,9,9,4,9,3,9,102,2,9,9,4,9,3,9,1001,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,101,1,9,9,4,9,3,9,1001,9,1,9,4,9,3,9,1001,9,2,9,4,9,3,9,1002,9,2,9,4,9,99";
	static String input2 = "3,26,1001,26,-4,26,3,27,1002,27,2,27,1,27,26,\r\n"
			+ "27,4,27,1001,28,-1,28,1005,28,6,99,0,0,5";
	
	public static void main(String[] args) {
		DayRunner.run(new Day07());
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

	@Override
	public String part1() {
		Scanner scan = new Scanner(input);
		ArrayList<Long> program = new ArrayList<Long>();
		while(scan.hasNextLine()) {
			String[] line = scan.nextLine().split(",");
			for(String s : line) {
				program.add(Long.parseLong(s));
			}
		}
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
		Scanner scan = new Scanner(input);
		ArrayList<Long> program = new ArrayList<Long>();
		while(scan.hasNextLine()) {
			String[] line = scan.nextLine().split(",");
			for(String s : line) {
				program.add(Long.parseLong(s));
			}
		}
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
}
