package advent.aoc2019;

import java.util.ArrayList;
import java.util.Scanner;

import advent.utilities.general.Coord;
import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;
import advent.utilities.utils2019.IntCodeComputer;

public class Day25 implements IDay {

	//this one takes a lot of adjusting the input to suit yours. see comments throughout program
	static String input;
	
	static String itemPickup = "south\r\n"
			+ "take spool of cat6\r\n"
			+ "west\r\n"
			+ "take space heater\r\n"
			+ "south\r\n"
			+ "take shell\r\n"
			+ "north\r\n"
			+ "north\r\n"
			+ "take weather machine\r\n"
			+ "west\r\n"
			+ "south\r\n"
			+ "south\r\n"
			+ "take space law space brochure\r\n"
			+ "north\r\n"
			+ "east\r\n"
			+ "take candy cane\r\n"
			+ "west\r\n"
			+ "north\r\n"
			+ "east\r\n"
			+ "north\r\n"
			+ "west\r\n"
			+ "west\r\n"
			+ "take whirled peas\r\n"
			+ "inv\r\n"
			+ "east\r\n"
			+ "east\r\n"
			+ "south\r\n"
			+ "south\r\n"
			+ "east\r\n"
			+ "east\r\n"
			+ "south\r\n"
			+ "take hypercube\r\n"
			+ "south\r\n"
			+ "south\r\n"
			+ "test";

	@Override
	public String part1() {
		//parse program
		ArrayList<Long> program = new ArrayList<Long>();
		for(String s : input.split(",")) {
			program.add(Long.parseLong(s));
		}
		IntCodeComputer bot = new IntCodeComputer(program);
		bot.run();
		
		//in case you want to have fun exploring day 25 manually, or have to build your item command list
		boolean manual = false;
		String totalInput = "";
		if(manual) {
			Scanner in = new Scanner(System.in);
			Coord position = new Coord(0,0);
			while(!bot.halted) {
				System.out.println(position);
				while(bot.output.size() > 0) {
					int out = Math.toIntExact(bot.output());
					System.out.print((char) out);
				}
				String cmd = in.nextLine();
				totalInput += cmd + "\n";
				if(cmd.equals("test")) {
					break;
				}
				if(cmd.equals("print")) {
					System.out.println(totalInput);
					break;
				}
				switch(cmd) {
				case "north":
					position.sumSelf(new Coord(0,-1));
					break;
				case "south":
					position.sumSelf(new Coord(0,1));
					break;
				case "east":
					position.sumSelf(new Coord(1,0));
					break;
				case "west":
					position.sumSelf(new Coord(-1,0));
					break;
				}
				for(char s : cmd.toCharArray()) {
					bot.input((int) s);
				}
				bot.input(10);
				bot.run();
			}
			in.close();
		} else {
			//replace itemPickup with the set of code that will pick up all your valid items and move to security door
			//this can be found by picking up all items and moving to security door manually, then entering "print".
			//this will output the full command sequence, which you can copy-paste into itemPickup
			//or, alternatively, copy-paste the section after "weight testing segment" below the while loop above, 
			//then manually pick up all items, move to the security room,
			//and enter "test"
			for(String line : itemPickup.split("\r\n")) {
				for(char c : line.toCharArray()) {
					bot.input(c);
				}
				bot.input(10);
				bot.run();
			}
			//weight testing segment
			//manually populated take command array
			String[] takes = new String[] {"take weather machine", "take shell", "take hypercube", "take candy cane", "take whirled peas", "take space law space brochure", "take space heater", "take spool of cat6"};
			String[] drops = new String[8];
			//lazy population of drop command array by modifying takes
			for(int i = 0; i < 8; i++) {
				String drop = "drop " + takes[i].substring(5);
				drops[i] = drop;
			}
			
			//use a 8-digit binary number to represent all possible combinations of the 8 numbers
			for(int i = 0; i < 256; i++) {
				//drop all
				for(int x = 0; x < 8; x++) {
					for(char c : drops[x].toCharArray()) {
						bot.input(c);
					}
					bot.input(10);
					bot.run();
				}
				//convert integer to binary string
				String bin = Integer.toBinaryString(i);
				//add leading zeroes if necessary
				while(bin.length() < 8)
					bin = "0" + bin;
				//use binary value at each index to determine whether to pick up each of the 8 items
				for(int x = 0; x < 8; x++) {
					if(bin.charAt(x) == '1') {
						for(char c : takes[x].toCharArray()) {
							bot.input(c);
						}
						bot.input(10);
						bot.run();
					}
				}
				//input movement code
				bot.input((int) 'e');
				bot.input((int) 'a');
				bot.input((int) 's');
				bot.input((int) 't');
				bot.input(10);
				bot.run();
				//if bot halts, we successfully made it through the door
				if(bot.halted) {
					break;
				}
				//get rid of unnecessary output
				bot.output.clear();
			}
			//print final output
			while(bot.output.size() > 0) {
				int out = Math.toIntExact(bot.output());
				System.out.print((char) out);
			}
		}
		return "N/A, examine output";
	}

	@Override
	public String part2() {
		return "Merry Christmas!";
	}
	
	public static void main(String[] args) {
		input = Input.fetchInput(2019,25).trim();
		DayRunner.run(new Day25());
	}
}
