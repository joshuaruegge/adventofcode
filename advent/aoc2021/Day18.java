package advent.aoc2021;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

public class Day18 implements IDay {

	static String input;
	
	@Override
	public String part1() {
		String[] lines = input.split("\n");
		String number = lines[0];
		for(int line = 1; line < lines.length; line++) {
			number = "[" + number + "," + lines[line] + "]";
			
			snail:
			while(true) {
				//look for explodes
				int depth = 0;
				for(int i = 0; i < number.length(); i++) {
					char c = number.charAt(i);
					if(c == '[')
						depth++;
					if(c == ']')
						depth--;
					
					if(depth > 4) {
						int left = Integer.parseInt(number.substring(i+1, number.indexOf(',',i)));
						int right = Integer.parseInt(number.substring(number.indexOf(',',i) + 1, number.indexOf(']',i)));
						number = number.substring(0,i) + "0" + number.substring(number.indexOf(']',i) + 1);
						//insert right first, to avoid nudging i off
						int rightStart = i+1;
						while(rightStart < number.length() && (number.charAt(rightStart) == ']' || number.charAt(rightStart) == '[' || number.charAt(rightStart) == ','))
							rightStart++;
						if(rightStart < number.length()) {
							int rightEnd = rightStart + 1;
							while(!(number.charAt(rightEnd) == ']' || number.charAt(rightEnd) == '[' || number.charAt(rightEnd) == ','))
								rightEnd++;
							int numReplace = Integer.parseInt(number.substring(rightStart,rightEnd)) + right;
							number = number.substring(0,rightStart) + numReplace + number.substring(rightEnd);
						}
						
						int leftEnd = i - 1;
						while(leftEnd >= 0 && (number.charAt(leftEnd) == ']' || number.charAt(leftEnd) == '[' || number.charAt(leftEnd) == ','))
							leftEnd--;
						if(leftEnd > 0) {
							int leftStart = leftEnd - 1;
							while(!(number.charAt(leftStart) == ']' || number.charAt(leftStart) == '[' || number.charAt(leftStart) == ','))
								leftStart--;
							int numReplace = Integer.parseInt(number.substring(leftStart+1,leftEnd+1)) + left;
							number = number.substring(0,leftStart + 1) + numReplace + number.substring(leftEnd + 1);
						}
						continue snail;
					}
				}
				
				//look for splits
				for(int i = 0; i < number.length(); i++) {
					if(isDigit(number.charAt(i)) && isDigit(number.charAt(i+1))) {
						int end = i+1;
						while(!(number.charAt(end) == ']' || number.charAt(end) == '[' || number.charAt(end) == ','))
							end++;
						int split = Integer.parseInt(number.substring(i,end));
						number = number.substring(0,i) + "[" + (int) Math.floor(split / 2.0) + "," + (int) Math.ceil(split/2.0) + "]" + number.substring(end);
						
						continue snail;
					}
				}
				break;
			}
		}
		while(number.contains("[")) {
			Matcher m = Pattern.compile("\\[(\\d+),(\\d+)\\]").matcher(number);
			if(m.find()) {
				number = number.substring(0,m.start(0)) + ((3 *  Integer.parseInt(m.group(1))) + (2 * Integer.parseInt(m.group(2)))) + number.substring(m.end(0));
			}
		}
		return number;
	}

	@Override
	public String part2() {
		String[] lines = input.split("\n");
		int maxMag = 0;
		for(String a : lines) {
			for(String b : lines) {
				if(a.equals(b))
					continue;
				String number = "[" + a + "," + b + "]";

				snail:
				while(true) {
					//look for explodes
					int depth = 0;
					for(int i = 0; i < number.length(); i++) {
						char c = number.charAt(i);
						if(c == '[')
							depth++;
						if(c == ']')
							depth--;
						
						if(depth > 4) {
							int left = Integer.parseInt(number.substring(i+1, number.indexOf(',',i)));
							int right = Integer.parseInt(number.substring(number.indexOf(',',i) + 1, number.indexOf(']',i)));
							number = number.substring(0,i) + "0" + number.substring(number.indexOf(']',i) + 1);
							//insert right first, to avoid nudging i off
							int rightStart = i+1;
							while(rightStart < number.length() && (number.charAt(rightStart) == ']' || number.charAt(rightStart) == '[' || number.charAt(rightStart) == ','))
								rightStart++;
							if(rightStart < number.length()) {
								int rightEnd = rightStart + 1;
								while(!(number.charAt(rightEnd) == ']' || number.charAt(rightEnd) == '[' || number.charAt(rightEnd) == ','))
									rightEnd++;
								int numReplace = Integer.parseInt(number.substring(rightStart,rightEnd)) + right;
								number = number.substring(0,rightStart) + numReplace + number.substring(rightEnd);
							}
							
							int leftEnd = i - 1;
							while(leftEnd >= 0 && (number.charAt(leftEnd) == ']' || number.charAt(leftEnd) == '[' || number.charAt(leftEnd) == ','))
								leftEnd--;
							if(leftEnd > 0) {
								int leftStart = leftEnd - 1;
								while(!(number.charAt(leftStart) == ']' || number.charAt(leftStart) == '[' || number.charAt(leftStart) == ','))
									leftStart--;
								int numReplace = Integer.parseInt(number.substring(leftStart+1,leftEnd+1)) + left;
								number = number.substring(0,leftStart + 1) + numReplace + number.substring(leftEnd + 1);
							}
							continue snail;
						}
					}
					
					//look for splits
					for(int i = 0; i < number.length(); i++) {
						if(isDigit(number.charAt(i)) && isDigit(number.charAt(i+1))) {
							int end = i+1;
							while(!(number.charAt(end) == ']' || number.charAt(end) == '[' || number.charAt(end) == ','))
								end++;
							int split = Integer.parseInt(number.substring(i,end));
							number = number.substring(0,i) + "[" + (int) Math.floor(split / 2.0) + "," + (int) Math.ceil(split/2.0) + "]" + number.substring(end);
							
							continue snail;
						}
					}
					break;
				}
				
				while(number.contains("[")) {
					Matcher m = Pattern.compile("\\[(\\d+),(\\d+)\\]").matcher(number);
					if(m.find()) {
						number = number.substring(0,m.start(0)) + ((3 *  Integer.parseInt(m.group(1))) + (2 * Integer.parseInt(m.group(2)))) + number.substring(m.end(0));
					}
				}
				int mag = Integer.parseInt(number);
				if(mag > maxMag)
					maxMag = mag;
			}
		}
		return Integer.toString(maxMag);
	}

	public boolean isDigit(char c) {
		return c > 47 && c < 58;
	}

	public static void main(String[] args) {
		input = Input.fetchInput(2021,18);
		DayRunner.run(new Day18());
	}
}
