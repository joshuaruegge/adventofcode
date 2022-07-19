package advent.aoc2016;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;

public class Day05 implements IDay {

	String input = "uqwqemis";
	
	@Override
	public String part1() {
		MessageDigest m = null;
		try {
			m = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			//please. im begging you. run this on a normal computer
			//your fridge can probably hash MD5 at this point
			System.out.println("no");
		}
		int counter = 0;
		//stringbuilder for speed
		StringBuilder pass = new StringBuilder();
		while(pass.length() < 8) {
			String md5Key = input + counter;
			byte[] bytes = m.digest(md5Key.getBytes());
			
			//each byte represents 2 hex digits
			//so, for 5 hex zeroes, the first 2 bytes must be zero, and the third byte must be less than 15
			//(15 is maximum for first byte to be zero, as byte 15 is 00001111)
			//note that comparisons assume the bytes are signed, so the byte must also be greater than 0
			//(negative bytes have a 1 in the highest binary digit ("signed"))
			if(bytes[0] == 0 && bytes[1] == 0 && bytes[2] < 16 && bytes[2] > -1) {
				//by doing bitwise & with 15 (binary 1111), we trim off any values beyond the first 4 digits
				int hex = bytes[2] & 15;
				//lazy conversion
				pass.append(Integer.toHexString(hex));
			}
			//increment counter
			counter++;
		}
		return pass.toString();
	}

	@Override
	public String part2() {
		MessageDigest m = null;
		try {
			m = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			//please. im begging you. run this on a normal computer
			//your fridge can probably hash MD5 at this point
			System.out.println("no");
		}
		int counter = 0;
		//now, store password as char[]
		char[] pass = new char[8];
		while(contains(pass,(char) 0)) {
			String key = input + counter;
			byte[] bytes = m.digest(key.getBytes());
			
			//each byte represents 2 hex digits
			//so, for 5 hex zeroes, the first 2 bytes must be zero, and the third byte must be less than 15
			//(15 is maximum for first byte to be zero, as byte 15 is 00001111)
			//note that comparisons assume the bytes are signed, so the byte must also be greater than 0
			//(negative bytes have a 1 in the highest binary digit ("signed"))
			if(bytes[0] == 0 && bytes[1] == 0 && bytes[2] < 16 && bytes[2] > -1) {
				int hex6 = bytes[2] & 15;
				//if valid pass index and that index has not been set yet
				if(hex6 < 8 && pass[hex6] == (char) 0) {
					//righshift 4 to get first 4 digits
					int hex7 = Byte.toUnsignedInt(bytes[3]) >>> 4;
					if(hex7 < 10)
						//number
						pass[hex6] = (char) (hex7 + 48);
					else
						//letter
						pass[hex6] = (char) (hex7 + 87);
				}
			}
			counter++;
		}
		return new String(pass);
	}

	//helper method - array contains
	public boolean contains(char[] a, char c) {
		for(char b : a)
			if(b == c)
				return true;
		return false;
	}
	public static void main(String[] args) {
		DayRunner.run(new Day05());
	}

}
