package advent.aoc2016;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;

public class Day16 implements IDay {

	String input = "00101000101111010";
	
	@Override
	public String part1() {
		int length = 272;
		StringBuilder dragon = new StringBuilder();
		dragon.append(input);
		while(dragon.length() < length) {
			String reverse = new StringBuilder(dragon).reverse().toString();
			dragon.append(0);
			
			//swap characters in reverse by using a buffer character
			reverse = reverse.replace("0", "a");
			reverse = reverse.replace("1", "0");
			reverse = reverse.replace("a", "1");
			
			dragon.append(reverse);
		}
		String data = dragon.toString().substring(0,length);
		//checksum shrinking
		while(data.length() % 2 == 0) {
			StringBuilder checksum = new StringBuilder();
			for(int i = 0; i < data.length(); i+=2) {
				if(data.charAt(i) == data.charAt(i+1))
					checksum.append("1");
				else
					checksum.append("0");
			}
			data = checksum.toString();
		}
		
		return data;
	}

	@Override
	public String part2() {
		int length = 35651584;
		StringBuilder dragon = new StringBuilder();
		dragon.append(input);
		while(dragon.length() < length) {
			String reverse = new StringBuilder(dragon).reverse().toString();
			dragon.append(0);
			
			//swap characters in reverse by using a buffer character
			reverse = reverse.replace("0", "a");
			reverse = reverse.replace("1", "0");
			reverse = reverse.replace("a", "1");
			
			dragon.append(reverse);
		}
		String data = dragon.toString().substring(0,length);
		//checksum shrinking
		while(data.length() % 2 == 0) {
			StringBuilder checksum = new StringBuilder();
			for(int i = 0; i < data.length(); i+=2) {
				if(data.charAt(i) == data.charAt(i+1))
					checksum.append("1");
				else
					checksum.append("0");
			}
			data = checksum.toString();
		}
		
		return data;
	}

	public static void main(String[] args) {
		DayRunner.run(new Day16());
	}

}
