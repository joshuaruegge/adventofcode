package advent.aoc2019;

import java.util.ArrayList;
import java.util.Arrays;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;
import advent.utilities.utils2019.IntCodeComputerThreadable;

//run a minimum of 3 times to ensure your answer is correct, this is a very poor multithreading implementation
public class Day23 implements IDay{

	static String input;
	
	//may god have mercy on anyone that has to use this
	public static void main(String[] args) {
		input = Input.fetchInput(2019,23).trim();
		DayRunner.run(new Day23());
	}

	@SuppressWarnings("removal")
	@Override
	public String part1() {
		//parse program
		ArrayList<Long> program = new ArrayList<Long>(Arrays.stream(input.split(",")).map(x -> Long.parseLong(x)).toList());
		//create "network"
		ArrayList<IntCodeComputerThreadable> network = new ArrayList<IntCodeComputerThreadable>();
		for(int i = 0; i < 50; i++) {
			IntCodeComputerThreadable c = new IntCodeComputerThreadable(program);
			c.input(i);
			network.add(c);
			c.start();
		}
		
		//as threads run, continuously perform check
		while(true) {
			//pause threads for duration of check
			for(IntCodeComputerThreadable c : network)
				c.suspend();
			
			//check if any have packets to send, push packets to destination
			for(IntCodeComputerThreadable c : network) {
				if(c.output.size() > 2) {
					while(c.output.size() > 2) {
						int address = Math.toIntExact(c.output());
						long x = c.output();
						long y = c.output();
						//exit condition
						if(address == 255) {
							return Long.toString(y);
						} else if(address < 50) {
							IntCodeComputerThreadable dest = network.get(address);
							dest.input(x);
							dest.input(y);
						}
					}
				}		
			}
			//resume threads
			for(IntCodeComputerThreadable c : network)
				c.resume();
		}
	}

	@SuppressWarnings("removal")
	@Override
	public String part2() {
		ArrayList<Long> program = new ArrayList<Long>(Arrays.stream(input.split(",")).map(x -> Long.parseLong(x)).toList());
		ArrayList<IntCodeComputerThreadable> network = new ArrayList<IntCodeComputerThreadable>();
		for(int i = 0; i < 50; i++) {
			IntCodeComputerThreadable c = new IntCodeComputerThreadable(program);
			c.input(i);
			network.add(c);
			c.start();
		}
		//keep track of all seen y values to check for first duplicate
		ArrayList<Long> yValues = new ArrayList<Long>();
		//NAT storage buffer
		long[] natStorage = new long[2];
		//just pause threads, check, and resume threads until end condition. simple, right?
		//i shouldnt be let within 100 feet of any program running threads
		while(true) {
			//pause computers
			for(IntCodeComputerThreadable c : network)
				c.suspend();
			
			//determine if all computers are halted
			boolean halted = true;
			for(IntCodeComputerThreadable c : network) {
				if(c.inputWaiting == false || c.output.size() > 0)
					halted = false;
			}
			//if all computers are halted, input NAT values
			if(halted && natStorage[1] != 0l) {
				if(yValues.contains(natStorage[1])) {
					//caches unusual threading bug that only manifests sometimes
					if(yValues.size() < 20) {
						System.out.println("Do NOT use this answer!! Threading issues have occured, run the program again.");
					}
					return Long.toString(natStorage[1]);
				}
				yValues.add(natStorage[1]);
				network.get(0).input(natStorage[0]);
				network.get(0).input(natStorage[1]);
			}
			
			//push packets
			for(IntCodeComputerThreadable c : network) {
				if(c.output.size() > 2) {
					while(c.output.size() > 2) {
						int address = Math.toIntExact(c.output());
						long x = c.output();
						long y = c.output();
						if(address == 255) {
							natStorage[0] = x;
							natStorage[1] = y;
						} else if(address < 50) {
							IntCodeComputerThreadable dest = network.get(address);
							dest.input(x);
							dest.input(y);
						}
					}
				}		
			}
			
			//unpause computers
			for(IntCodeComputerThreadable c : network)
				c.resume();
			
		}
	}

}
