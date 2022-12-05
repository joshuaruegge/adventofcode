package advent.aoc2021;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;

public class Day18 implements IDay {

	String input = "[2,[0,[9,[5,9]]]]\r\n"
			+ "[[2,[1,8]],3]\r\n"
			+ "[[[[7,2],6],[[7,8],3]],[9,[[6,9],2]]]\r\n"
			+ "[[[[7,2],[9,8]],7],[4,[[2,2],[5,0]]]]\r\n"
			+ "[[8,[2,2]],[5,[9,[4,9]]]]\r\n"
			+ "[[[[6,2],[4,8]],5],0]\r\n"
			+ "[[3,[3,[6,6]]],[6,9]]\r\n"
			+ "[[[9,5],[[8,2],[4,0]]],[[5,5],[[5,0],[1,9]]]]\r\n"
			+ "[[[[7,4],[8,1]],[2,[7,1]]],2]\r\n"
			+ "[[[[9,6],3],8],[[[9,8],7],[5,[0,8]]]]\r\n"
			+ "[[[4,[4,0]],[[7,3],3]],[8,[3,[8,2]]]]\r\n"
			+ "[[[[8,4],1],6],[[1,[8,7]],1]]\r\n"
			+ "[[[8,2],[[1,4],3]],[[4,5],[[9,1],[7,2]]]]\r\n"
			+ "[[[[5,0],[8,8]],[[4,2],4]],[2,[[4,3],[3,7]]]]\r\n"
			+ "[[[8,7],[2,1]],[9,3]]\r\n"
			+ "[[3,[7,4]],[0,3]]\r\n"
			+ "[4,[[[5,0],[5,2]],3]]\r\n"
			+ "[[[[0,1],0],8],[6,3]]\r\n"
			+ "[[7,[[9,8],[2,7]]],[[[8,8],[9,4]],[[0,5],[4,1]]]]\r\n"
			+ "[[[[3,7],[5,4]],[8,[1,8]]],[[1,8],[[6,9],9]]]\r\n"
			+ "[[[[7,4],[7,7]],7],[1,[[8,2],[1,8]]]]\r\n"
			+ "[[[[6,2],8],[[1,2],3]],[[[3,6],[4,9]],[[3,1],[9,8]]]]\r\n"
			+ "[[[3,[1,1]],[[6,5],[2,2]]],9]\r\n"
			+ "[[[[9,1],4],1],[[[1,3],3],[0,[1,4]]]]\r\n"
			+ "[[[5,0],[4,[6,8]]],[[2,4],[[0,3],[2,6]]]]\r\n"
			+ "[9,[[9,[1,5]],1]]\r\n"
			+ "[[1,[[6,0],[9,2]]],[[[4,2],7],[[2,9],6]]]\r\n"
			+ "[[[[8,2],8],9],[[[4,9],[3,8]],2]]\r\n"
			+ "[[[9,1],[6,5]],[[[9,5],5],1]]\r\n"
			+ "[[[[1,3],5],2],[1,1]]\r\n"
			+ "[[[[0,0],[8,1]],8],8]\r\n"
			+ "[[[[3,3],5],[[9,6],9]],[[3,[0,9]],7]]\r\n"
			+ "[[[6,5],1],1]\r\n"
			+ "[[[4,[1,3]],[[2,2],2]],[[8,0],[[8,1],[2,6]]]]\r\n"
			+ "[9,[[4,6],2]]\r\n"
			+ "[[[5,[8,8]],[[1,8],[4,9]]],[9,[3,6]]]\r\n"
			+ "[[[[9,3],3],0],8]\r\n"
			+ "[[[5,0],[[2,8],[1,1]]],[[[5,6],9],8]]\r\n"
			+ "[[[[5,0],[5,2]],[[7,0],[9,8]]],[3,[[5,7],[5,9]]]]\r\n"
			+ "[[3,[5,7]],1]\r\n"
			+ "[[[[2,5],[0,7]],9],[[[3,2],1],[7,1]]]\r\n"
			+ "[6,[7,[6,0]]]\r\n"
			+ "[[[8,5],[[1,7],[7,6]]],[[1,3],[5,[1,9]]]]\r\n"
			+ "[[[[9,4],[8,3]],1],[[1,6],[[2,5],1]]]\r\n"
			+ "[[[[6,5],[6,6]],[5,5]],[1,8]]\r\n"
			+ "[[[[7,7],[2,2]],3],[1,[[8,6],[5,1]]]]\r\n"
			+ "[[6,[2,4]],[[8,8],[[3,5],6]]]\r\n"
			+ "[[1,[[6,1],[9,3]]],[[2,0],5]]\r\n"
			+ "[[[5,9],[6,[1,9]]],[3,[4,[7,7]]]]\r\n"
			+ "[[[[3,6],[8,5]],[[9,4],[4,1]]],[3,3]]\r\n"
			+ "[[[3,9],[1,6]],2]\r\n"
			+ "[[[[0,9],7],6],[7,[9,[9,9]]]]\r\n"
			+ "[[[5,[6,0]],[8,[7,5]]],[[[8,8],0],[8,1]]]\r\n"
			+ "[[[[6,9],[9,0]],2],[[[0,3],[1,6]],[2,4]]]\r\n"
			+ "[[[[8,2],[3,0]],[[3,8],8]],[6,[[9,3],4]]]\r\n"
			+ "[[[6,6],2],[5,[1,4]]]\r\n"
			+ "[[1,[1,4]],[[[4,3],0],1]]\r\n"
			+ "[[[[9,9],3],0],[[[3,3],[2,8]],[1,0]]]\r\n"
			+ "[[[[1,1],[3,5]],[9,7]],4]\r\n"
			+ "[[[9,[3,6]],5],[[4,9],[9,3]]]\r\n"
			+ "[[8,7],[5,[7,[7,7]]]]\r\n"
			+ "[[[[0,5],[7,3]],[[8,6],8]],[[[4,4],[5,0]],[[2,2],2]]]\r\n"
			+ "[[[5,0],[[1,9],[5,8]]],[[1,5],[[9,3],[0,7]]]]\r\n"
			+ "[[[1,[1,5]],[8,[2,2]]],0]\r\n"
			+ "[[[6,[7,8]],[[0,2],5]],[3,[5,[8,0]]]]\r\n"
			+ "[[[[1,7],2],3],[[[8,7],[7,8]],[7,[5,5]]]]\r\n"
			+ "[[1,[7,[3,3]]],[8,[9,[3,0]]]]\r\n"
			+ "[[5,6],[[5,[2,8]],[[5,5],[8,8]]]]\r\n"
			+ "[[8,[[7,7],[4,0]]],[[5,[0,4]],[6,[6,2]]]]\r\n"
			+ "[[4,[[0,0],[0,1]]],[[3,1],[[6,7],4]]]\r\n"
			+ "[[[[3,2],[4,2]],[[4,4],[6,3]]],[9,[0,[1,9]]]]\r\n"
			+ "[[[[4,6],2],[[9,6],4]],[[9,[9,1]],[0,[1,8]]]]\r\n"
			+ "[[[5,8],[[6,5],[0,4]]],[[0,[6,3]],[2,0]]]\r\n"
			+ "[[6,8],[[5,5],[5,8]]]\r\n"
			+ "[[[7,3],[8,[6,7]]],[[[1,5],2],7]]\r\n"
			+ "[[6,[8,[8,9]]],[[[1,1],[3,0]],[[7,2],[3,7]]]]\r\n"
			+ "[[[[8,1],6],[9,[5,1]]],[[[5,9],[1,9]],5]]\r\n"
			+ "[[[[3,6],[5,7]],[[0,3],8]],[3,[[2,1],0]]]\r\n"
			+ "[[7,[5,1]],[[[3,6],9],[[4,0],6]]]\r\n"
			+ "[[[[3,8],8],0],[[1,[1,4]],[[4,5],[8,5]]]]\r\n"
			+ "[[[8,[0,6]],[4,3]],[8,[[1,5],8]]]\r\n"
			+ "[2,[[1,[9,7]],[[2,0],6]]]\r\n"
			+ "[[[[7,4],4],[[4,9],3]],[[[6,5],[0,5]],[[9,8],[2,6]]]]\r\n"
			+ "[[[3,[7,2]],[[7,7],4]],[[[3,4],[6,0]],[6,3]]]\r\n"
			+ "[[[1,9],[[9,8],9]],5]\r\n"
			+ "[[[4,2],2],[[[4,4],7],5]]\r\n"
			+ "[[[9,1],[2,[1,5]]],[[4,3],[4,[9,5]]]]\r\n"
			+ "[2,[[[8,4],1],[[2,4],2]]]\r\n"
			+ "[[[0,6],5],[1,[[2,0],6]]]\r\n"
			+ "[[[[2,4],[1,7]],[1,0]],[9,5]]\r\n"
			+ "[[7,[3,[2,0]]],[[7,8],8]]\r\n"
			+ "[[9,[1,0]],[[0,4],[[0,1],0]]]\r\n"
			+ "[0,9]\r\n"
			+ "[[[[2,9],[2,4]],[[5,6],8]],[[5,[1,4]],[3,[0,6]]]]\r\n"
			+ "[[5,[[5,8],0]],[[[0,6],[4,5]],[[8,9],[8,3]]]]\r\n"
			+ "[[[[5,2],[7,7]],[0,[4,1]]],[[8,7],[[5,3],7]]]\r\n"
			+ "[[[5,3],5],[0,0]]\r\n"
			+ "[3,5]\r\n"
			+ "[[2,6],5]\r\n"
			+ "[[5,[[6,0],3]],[[3,[8,7]],[2,0]]]";
	
	@Override
	public String part1() {
		String[] lines = input.split("\r\n");
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
	
	public boolean isDigit(char c) {
		return c > 47 && c < 58;
	}

	@Override
	public String part2() {
		String[] lines = input.split("\r\n");
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

	public static void main(String[] args) {
		DayRunner.run(new Day18());
	}

}
