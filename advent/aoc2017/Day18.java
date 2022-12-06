package advent.aoc2017;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;
import advent.utilities.utils2017.Duet;

public class Day18 implements IDay {

	static String input;
	
	@Override
	public String part1() {
		ArrayList<String> program = new ArrayList<String>(Arrays.asList(input.split("\n")));
		HashMap<String,Long> registers = new HashMap<String,Long>();
		int pointer = 0;
		long lastSound = -1;
	
		while(pointer > -1 && pointer < program.size()) {
			String[] line = program.get(pointer).split(" ");
			switch(line[0]) {
			case "snd":
				lastSound = registers.get(line[1]);
				break;
			case "set":
				try {
					registers.put(line[1], Long.parseLong(line[2]));
				} catch (Exception e) {
					registers.put(line[1], registers.getOrDefault(line[2], (long) 0));
				}
				break;
			case "add":
				try {
					registers.put(line[1], registers.getOrDefault(line[1], (long) 0) + Long.parseLong(line[2]));
				} catch (Exception e) {
					registers.put(line[1], registers.getOrDefault(line[1], (long) 0) + registers.getOrDefault(line[2], (long) 0));
				}
				break;
			case "mul":
				try {
					registers.put(line[1], registers.getOrDefault(line[1], (long) 0) * Long.parseLong(line[2]));
				} catch (Exception e) {
					registers.put(line[1], registers.getOrDefault(line[1], (long) 0) * registers.getOrDefault(line[2], (long) 0));
				}
				break;
			case "mod":
				try {
					registers.put(line[1], registers.getOrDefault(line[1], (long) 0) % Long.parseLong(line[2]));
				} catch (Exception e) {
					registers.put(line[1], registers.getOrDefault(line[1], (long) 0) % registers.getOrDefault(line[2], (long) 0));
				}
				break;
			case "jgz":
				boolean jump = false;
				try {
					jump = Long.parseLong(line[1]) > 0;
				} catch (Exception e) {
					jump = registers.getOrDefault(line[1], (long) 0) > 0;
				}
				if(jump) {
					try {
						pointer += Long.parseLong(line[2]) - 1;
					} catch (Exception e) {
						pointer += registers.getOrDefault(line[2],(long) 0) - 1;
					}
				}
				break;
			case "rcv":
				long rcv = 0;
				try {
					rcv = Long.parseLong(line[1]);
				} catch (Exception e) {
					rcv = registers.getOrDefault(line[1], 0l);
				}
				if(rcv != 0) {
					return Long.toString(lastSound);
				}
				break;
			}
			pointer++;
		}
		return null;
	}

	static ArrayList<Duet> stack;
	@Override
	public String part2() {
		ArrayList<String> program = new ArrayList<String>(Arrays.asList(input.split("\n")));
		stack = new ArrayList<Duet>();
		stack.add(new Duet(program,0));
		stack.add(new Duet(program,1));
		while(!stack.get(0).stopped || !stack.get(1).stopped) {
			stack.get(0).run();
			stack.get(1).run();
		}
		return Long.toString(stack.get(1).timesSent);
	}

	//static send method - pushes values between duets
	public static void send(int id, long num) {
		stack.get(id).queue.add(num);
	}
	
	public static void main(String[] args) {
		input = Input.fetchInput(2017,18);
		DayRunner.run(new Day18());
	}

}
