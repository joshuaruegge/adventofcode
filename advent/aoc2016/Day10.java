package advent.aoc2016;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;
import advent.utilities.utils2016.Bot;

public class Day10 implements IDay {

	static String input;
	
	@Override
	public String part1() {
		//parse lines into arraylist and sort by alphabetical so
		//bots are created before receiving values+
		ArrayList<String> lines = new ArrayList<String>();
		for(String s : input.split("\n"))
			lines.add(s);
		//alphabetic order (first character only)
		Collections.sort(lines,new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				return Integer.compare(o1.charAt(0),o2.charAt(0));
			}
		
		});
		
		for(String s : lines) {
			String[] parts = s.split(" ");
			if(parts[0].equals("value")) {
				//push value to bot
				Bot.find(Integer.parseInt(parts[5])).buffer.add(Integer.parseInt(parts[1]));
			} else {
				//create new bot
				Bot.bots.add(new Bot(Integer.parseInt(parts[1]), Integer.parseInt(parts[6]), parts[5].equals("bot"), Integer.parseInt(parts[11]), parts[10].equals("bot")));
			}
		}
		
		//go over bot array, pushing result of bots that have both values necessary to compare
		while(true) {
			for(Bot b : Bot.bots) {
				if(b.buffer.size() == 2) {
					//if target condition found, return ID
					if(b.buffer.contains(61) && b.buffer.contains(17)) {
						return Integer.toString(b.id);
					}
					b.pushValues();
					break;
				}
			}
		}
		
		
	}

	@Override
	public String part2() {
		//clear bot internal static fields
		Bot.bots.clear();
		Bot.outputBins.clear();
		
		//parse lines into arraylist and sort by alphabetical so
		//bots are created before recieving values
		ArrayList<String> lines = new ArrayList<String>();
		for(String s : input.split("\n"))
			lines.add(s);
		//alphabetic order (first character only)
		Collections.sort(lines,new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				return Integer.compare(o1.charAt(0),o2.charAt(0));
			}
		
		});
		
		for(String s : lines) {
			String[] parts = s.split(" ");
			if(parts[0].equals("value")) {
				//push value to bot
				Bot.find(Integer.parseInt(parts[5])).buffer.add(Integer.parseInt(parts[1]));
			} else {
				//create bot
				Bot.bots.add(new Bot(Integer.parseInt(parts[1]), Integer.parseInt(parts[6]), parts[5].equals("bot"), Integer.parseInt(parts[11]), parts[10].equals("bot")));
			}
		}
		
		//go over bot array, pushing result of bots that have both values necessary to compare
		while(!Bot.outputBins.containsKey(0) || !Bot.outputBins.containsKey(1) || !Bot.outputBins.containsKey(2)) {
			for(Bot b : Bot.bots) {
				if(b.buffer.size() == 2) {
					b.pushValues();
					break;
				}
			}
		}
			
		//return values of outputs 0, 1, and 2 multiplied
		return Integer.toString(Bot.outputBins.get(0) * Bot.outputBins.get(1) * Bot.outputBins.get(2));
	}

	public static void main(String[] args) {
		input = Input.fetchInput(2016,10);
		DayRunner.run(new Day10());
	}

}
