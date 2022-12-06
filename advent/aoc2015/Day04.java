package advent.aoc2015;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

public class Day04 implements IDay {

	static String input;

	@Override
	public String part1() {
		//ensure trailing newline is trimmed
		input = input.replace("\n","");
		//use java MessageDigest class to perform MD5 hashing
		//everyone say "thank you native java libraries"
		MessageDigest m = null;
		try {
			m = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			//come on. run this on a normal computer. please
			//even your toaster probably has native md5 implementation now
			System.out.println("no.");
			e.printStackTrace();
			System.exit(0);
		}
		int counter = 1;
		while(true) {
			//create message
			String message = input + counter;
			//get bytes of MD5
			byte[] bytes = m.digest(message.getBytes());
			//each byte represents 2 hex digits
			//so, for 5 hex zeroes, the first 2 bytes must be zero, and the third byte must be less than 15
			//(15 is maximum for first byte to be zero, as byte 15 is 00001111)
			//note that comparisons assume the bytes are signed, so the byte must also be greater than 0
			//(negative bytes have a 1 in the highest binary digit ("signed"))
			if(bytes[0] == 0 && bytes[1] == 0 && bytes[2] < 15 && bytes[2] > 0) {
				//if this is the case, then counter is the minimum number to produce the result
				return Integer.toString(counter);
			}
			//increment counter
			counter++;
		}
	}

	@Override
	public String part2() {
		input.replace("\\n","");
		MessageDigest m = null;
		try {
			m = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			//come on. run this on a normal computer. please
			System.out.println("no.");
			e.printStackTrace();
			System.exit(0);
		}
		int counter = 1;
		while(true) {
			//create message
			String message = input + counter;
			//get bytes of MD5
			byte[] bytes = m.digest(message.getBytes());
			//6 zeroes is much easier - first 3 bytes must all be zero
			if(bytes[0] == 0 && bytes[1] == 0 && bytes[2] == 0)
				return Integer.toString(counter);
			//increment counter
			counter++;
		}
	}

	public static void main(String[] args) {
		input = Input.fetchInput(2015,4);
		DayRunner.run(new Day04());
	}

}
