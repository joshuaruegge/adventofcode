package advent.aoc2016;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;

public class Day15 implements IDay {

	String input = "Disc #1 has 17 positions; at time=0, it is at position 1.\r\n"
			+ "Disc #2 has 7 positions; at time=0, it is at position 0.\r\n"
			+ "Disc #3 has 19 positions; at time=0, it is at position 2.\r\n"
			+ "Disc #4 has 5 positions; at time=0, it is at position 0.\r\n"
			+ "Disc #5 has 3 positions; at time=0, it is at position 0.\r\n"
			+ "Disc #6 has 13 positions; at time=0, it is at position 5.";
	
	@Override
	public String part1() {
		int discCount = input.split("\r\n").length;
		//array of current disc positions
		int[] disks = new int[discCount + 1];
		//no disc at location 0
		disks[0] = 0;
		//array of maximum disc positions 
		int[] disksMax = new int[discCount + 1];
		//no disc at location 0
		disksMax[0] = 0;
		//disk counter for input parsing
		int inputDisk = 1;
		for(String s : input.split("\r\n")) {
			String[] parts = s.split(" ");
			//current disk position
			disks[inputDisk] = Integer.parseInt(parts[11].substring(0,parts[11].length() - 1));
			//disk maximum
			disksMax[inputDisk] = Integer.parseInt(parts[3]);
			inputDisk++;
		}
		
		//time counter
		int time = 0;
		timeLoop:
		while(true) {
			//go over each disk
			//current disk position is represented by disks[i] + time (time waited) + i (time it has taken to fall to index i) all modulo diskMax[i]
			//remember, no disk at 0 so skip index 0
			for(int i = 1; i < disks.length; i++) {
				if((disks[i] + time + i) % disksMax[i] != 0) {
					//increment time
					time++;
					continue timeLoop;
				}
			}
			//if we made it all the way here, then this index is the first correct one
			return Integer.toString(time);
		}
	}

	@Override
	public String part2() {
		int discCount = input.split("\r\n").length;
		//array of current disc positions
		int[] disks = new int[discCount + 2];
		//no disc at location 0
		disks[0] = 0;
		//array of maximum disc positions 
		int[] disksMax = new int[discCount + 2];
		//no disc at location 0
		disksMax[0] = 0;
		//disk counter for input parsing
		int inputDisk = 1;
		for(String s : input.split("\r\n")) {
			String[] parts = s.split(" ");
			//current disk position
			disks[inputDisk] = Integer.parseInt(parts[11].substring(0,parts[11].length() - 1));
			//disk maximum
			disksMax[inputDisk] = Integer.parseInt(parts[3]);
			inputDisk++;
		}
		//part 2 - append extra disk
		disks[disks.length - 1] = 0;
		disksMax[disks.length - 1] = 11;
		
		//time counter
		int time = 0;
		timeLoop:
		while(true) {
			//go over each disk
			//current disk position is represented by disks[i] + time (time waited) + i (time it has taken to fall to index i) all modulo diskMax[i]
			//remember, no disk at 0 so skip index 0
			for(int i = 1; i < disks.length; i++) {
				if((disks[i] + time + i) % disksMax[i] != 0) {
					//increment time
					time++;
					continue timeLoop;
				}
			}
			//if we made it all the way here, then this index is the first correct one
			return Integer.toString(time);
		}
	}

	public static void main(String[] args) {
		DayRunner.run(new Day15());
	}

}
