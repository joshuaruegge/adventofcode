package advent.aoc2017;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.utils2017.Duet;

public class Day18 implements IDay {

	String input = "set i 31\r\n"
			+ "set a 1\r\n"
			+ "mul p 17\r\n"
			+ "jgz p p\r\n"
			+ "mul a 2\r\n"
			+ "add i -1\r\n"
			+ "jgz i -2\r\n"
			+ "add a -1\r\n"
			+ "set i 127\r\n"
			+ "set p 735\r\n"
			+ "mul p 8505\r\n"
			+ "mod p a\r\n"
			+ "mul p 129749\r\n"
			+ "add p 12345\r\n"
			+ "mod p a\r\n"
			+ "set b p\r\n"
			+ "mod b 10000\r\n"
			+ "snd b\r\n"
			+ "add i -1\r\n"
			+ "jgz i -9\r\n"
			+ "jgz a 3\r\n"
			+ "rcv b\r\n"
			+ "jgz b -1\r\n"
			+ "set f 0\r\n"
			+ "set i 126\r\n"
			+ "rcv a\r\n"
			+ "rcv b\r\n"
			+ "set p a\r\n"
			+ "mul p -1\r\n"
			+ "add p b\r\n"
			+ "jgz p 4\r\n"
			+ "snd a\r\n"
			+ "set a b\r\n"
			+ "jgz 1 3\r\n"
			+ "snd b\r\n"
			+ "set f 1\r\n"
			+ "add i -1\r\n"
			+ "jgz i -11\r\n"
			+ "snd a\r\n"
			+ "jgz f -16\r\n"
			+ "jgz a -19";
	
	@Override
	public String part1() {
		ArrayList<String> program = new ArrayList<String>(Arrays.asList(input.split("\r\n")));
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
		ArrayList<String> program = new ArrayList<String>(Arrays.asList(input.split("\r\n")));
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
		DayRunner.run(new Day18());
	}

}
