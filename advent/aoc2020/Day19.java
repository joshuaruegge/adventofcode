package advent.aoc2020;

import java.util.ArrayList;
import java.util.HashSet;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;
import advent.utilities.utils2020.Rule;

public class Day19 implements IDay {

	static String input;
	
	@Override
	public String part1() {
		String[] chunks = input.split("\n\n");
		String[] rules = chunks[0].split("\n");
		Rule.rules = new Rule[rules.length];
		for(String rule : rules) {
			String[] parts = rule.split(": ");
			Rule r = new Rule(parts[1]);
			Rule.rules[Integer.parseInt(parts[0])] = r;
		}
		
		//construct rule zero
		HashSet<String> zero = Rule.rules[0].possibilities();
		
		int counter = 0;
		for(String s : chunks[1].split("\n")) {
			if(zero.contains(s))
				counter++;
		}
		
		return Integer.toString(counter);
	}

	@Override
	public String part2() {
		//based on our new rules, we can get an idea of what a match should look like
		//for rule 8: 42 | 42 8, a match will be in the form (42) (42)*, where (42)* represents any number of matches to 42
		//for rule 11: 42 31 | 42 11 31, a match will be in the form (42 (42 ()* 31)* 31) , where the () represents further nested 42 (optional nesting) 31 pairs
		//notably, both 42 and 31 point to messages with length of 8
		//also notably, 0 points directly to 8 and 11, meaning our only important rules are 42 and 31 (and whatever subrules that determine them, but these are non-circular)
		//therefore, a rule that satisfies the rule zero must follow the format (42) (42)* (42 (42 ()* 31)* 31)
		//therefore, we can make a few assertions:
		//1. for a message to be valid, it must be made up of sets of 8 characters that match either 42 or 31
		//1a. a message must therefore be a size multiple of 8
		//2. the minimum possible message must match the state 42 42 31, so there must be at minimum 1 31, and 2 42s
		//2a. furthermore, any message must have at least 1 more 42 than 31, because there has to be a leading 42 before the 42 () 31 pattern is repeated some number of times
		//3. after a block of 8 that fits rule 31, no rules can fit rule 42 (because all 42s happen before 31s)
		//therefore, using these 5 assertions, we can evaluate each message!
		
		//create rule set as normal
		String[] chunks = input.split("\n\n");
		String[] rules = chunks[0].split("\n");
		Rule.rules = new Rule[rules.length];
		for(String rule : rules) {
			String[] parts = rule.split(": ");
			Rule r = new Rule(parts[1]);
			Rule.rules[Integer.parseInt(parts[0])] = r;
		}
		
		//construct rules 31 and 42
		HashSet<String> thirtyOne = Rule.rules[31].possibilities();
		HashSet<String> fortyTwo = Rule.rules[42].possibilities();
		
		int counter = 0;
		messages:
		for(String s : chunks[1].split("\n")) {
			if(s.length() % 8 != 0)
				continue;
			
			ArrayList<String> eights = new ArrayList<String>();
			for(int i = 0; i < s.length(); i += 8) {
				eights.add(s.substring(i,i+8));
			}
			
			boolean seen31 = false;
			int fortyTwoCount = 0;
			int thirtyOneCount = 0;
			for(int i = 0; i < eights.size(); i++) {
				String cur = eights.get(i);
				//if already seen a 31, only match 31
				if(seen31) {
					if(thirtyOne.contains(cur)) {
						thirtyOneCount++;
					} else {
						//message invalid, skip
						continue messages;
					}
				} else {
					if(fortyTwo.contains(cur)) {
						fortyTwoCount++;
					} else if(thirtyOne.contains(cur)) {
						seen31 = true;
						thirtyOneCount++;
					} else {
						//message invalid, skip
						continue messages;
					}
				}
			}
			//lastly, verify more 42s than 31s, and increment counter
			//also, verify that there are at least the minimum number of each pair
			//remember, our minimum condition is 42 42 31, so at least 1 31 and 2 42s
			if(fortyTwoCount > thirtyOneCount && thirtyOneCount > 0 && fortyTwoCount > 1) {
				counter++;
			}
		}
		
		return Integer.toString(counter);
	}

	public static void main(String[] args) {
		input = Input.fetchInput(2020,19);
		DayRunner.run(new Day19());
	}
}
