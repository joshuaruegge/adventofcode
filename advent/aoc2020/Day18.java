package advent.aoc2020;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

public class Day18 implements IDay {

	static String input;
	
	@Override
	public String part1() {
		long sum = 0;
		for(String s : input.split("\n")) {
			while(s.contains("+") || s.contains("*")) {
				//find either first index of deepest parenthesis, or start at first index of string
				int index = -1;
				int parenthesisDepth = 0;
				int highestDepth = 0;
				for(int i = 0; i < s.length(); i++) {
					char c = s.charAt(i);
					if(c == '(') {
						parenthesisDepth++;
						if(parenthesisDepth > highestDepth) {
							highestDepth = parenthesisDepth;
							index = i;
						}
					} else if(c == ')') {
						parenthesisDepth--;
					}
				}
				//pad index by one to skip parenthesis
				index++;
				
				
				
				//operator is always character after first space
				int operatorIndex = s.indexOf(' ',index) + 1;
				
				//endIndex is either index of close parenthesis or index of space, starting from operator + 2
				int closeIndex = s.indexOf(')',operatorIndex + 2);
				int spaceIndex = s.indexOf(' ',operatorIndex + 2);
				
				//if neither close or space is present, end index will be end of string
				int endIndex = Math.min((closeIndex != -1 ? closeIndex : s.length()),(spaceIndex != -1 ? spaceIndex : s.length()));
				
				//extract expression
				String expression = s.substring(index,endIndex);
				
				String[] expParts = expression.split(" ");
				
				long result = -1;
				if(expParts[1].equals("+")) {
					result = Long.parseLong(expParts[0]) + Long.parseLong(expParts[2]);
				} else {
					result = Long.parseLong(expParts[0]) * Long.parseLong(expParts[2]);
				}
				
				//replace expression in s with result
				s = s.substring(0,index) + result + s.substring(endIndex);
				
				//handle case where lone number is inside parenthesis
				//replace (digits) with digits
				s = s.replaceAll("\\(([0-9]*)\\)", "$1");
			}
			//add line value to total
			sum += Long.parseLong(s);
		}
		return Long.toString(sum);
	}

	@Override
	public String part2() {	
		long total = 0;
		for(String line : input.split("\n")) {
			while(line.contains("+") || line.contains("*")) {
				//if parenthesis present, operate on deepest portion first
				int index = -1;
				int parenthesisDepth = 0;
				int highestDepth = 0;
				for(int i = 0; i < line.length(); i++) {
					char c = line.charAt(i);
					if(c == '(') {
						parenthesisDepth++;
						if(parenthesisDepth > highestDepth) {
							highestDepth = parenthesisDepth;
							index = i;
						}
					} else if(c == ')') {
						parenthesisDepth--;
					}
				}
				
				index++;
				
				//if no parentheses, index will be zero, and end index will be end of line
				int endParIndex = line.indexOf(')',index);
				if(endParIndex == -1)
					endParIndex = line.length();
				
				//replace selected portion with evaluation
				line = line.substring(0,index) + evaluate(line.substring(index,endParIndex)) + line.substring(endParIndex); 
				
				//handle parenthesis case mentioned above
				line = line.replaceAll("\\(([0-9]*)\\)", "$1");
			}
			
			total += Long.parseLong(line);
		}
		return Long.toString(total);
	}

	static Pattern addPattern = Pattern.compile("[0-9]+ \\+ [0-9]+");
	
	static Pattern mulPattern = Pattern.compile("[0-9]+ \\* [0-9]+");
	
	//evaluate string s for the given rules, performing first available addition, or first available multiplication if no addition until string is resolved
	//this method always recieves deepest parenthesised section, so does not have to worry about parenthesis being present
	public String evaluate(String s) {
		loop:
		while(s.contains("+") || s.contains("*")) {
			//if parenthesis present, operate on deepest portion first
			
			Matcher add = addPattern.matcher(s);
			if(add.find()) {
				String[] exp = s.substring(add.start(),add.end()).split(" ");
				long result = Long.parseLong(exp[0]) + Long.parseLong(exp[2]);
				s = s.substring(0,add.start()) + result + s.substring(add.end());
				
				//handle parenthesis case
				s = s.replaceAll("\\(([0-9]*)\\)", "$1");
				continue loop;
			}
			Matcher mul = mulPattern.matcher(s);
			if(mul.find()) {
				String[] exp = s.substring(mul.start(),mul.end()).split(" ");
				long result = Long.parseLong(exp[0]) * Long.parseLong(exp[2]);
				s = s.substring(0,mul.start()) + result + s.substring(mul.end());
				
				//handle parenthesis case
				s = s.replaceAll("\\(([0-9]*)\\)", "$1");
			}
		}
		return s;	
	}
	
	public static void main(String[] args) {
		input = Input.fetchInput(2020,18);
		DayRunner.run(new Day18());
	}
}
