package advent.aoc2016;

import java.security.MessageDigest;
import java.util.HashMap;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;

public class Day14 implements IDay {

	String input = "qzyelonm";
	
	@Override
	public String part1() {
		//initialize md5
		MessageDigest m = null;
		try {
			m = MessageDigest.getInstance("MD5");
		} catch (Exception e) {
			System.exit(0);
		}
		
		//store keys calculated deeper in the loop in an index -> key hashmap, to avoid excess key generation
		HashMap<Integer,char[]> keys = new HashMap<Integer,char[]>();
		
		int keyCount = 0;
		//start index at -1 and increment at beginning so end index is not off by one (would increment once after 64th key before keycount < 64 check)
		int index = -1;
		while(keyCount < 64) {
			index++;
			char[] hex;
			//check precalc map
			if(keys.containsKey(index))
				hex = keys.get(index);
			else {
				//create key, calculate bytes, convert to hex char array
				String key = input + index;
				byte[] bytes = m.digest(key.getBytes());
				
				hex = hex(bytes).toCharArray();
			}
			
			//iterate over array, looking for 3-repeated pattern
			boolean triple = false;
			char tripleChar = 0;
			for(int i = 0; i < hex.length - 2; i++) {
				if(hex[i] == hex[i+1] && hex[i] == hex[i+2]) {
					triple = true;
					tripleChar = hex[i];
					break;
				}
			}
			//go through next 1000 keys, looking for quintuple of tripleChar
			if(triple) {
				for(int i = index+1; i <= index+1000; i++) {
					char[] inHex;
					if(keys.containsKey(i)) {
						inHex = keys.get(i);
					} else {
						String key = input + i;
						byte[] bytes = m.digest(key.getBytes());
						inHex = hex(bytes).toCharArray();
						//store keys so they don't need to be recalculated later
						keys.put(i, inHex);
					}
					boolean quintuple = false;
					for(int j = 0; j < inHex.length - 4; j++) {
						if(inHex[j] == tripleChar && inHex[j+1] == tripleChar && inHex[j+2] == tripleChar && inHex[j+3] == tripleChar && inHex[j+4] == tripleChar) {
							quintuple = true;
							break;
						}
					}
					if(quintuple) {
						keyCount++;
						break;
					}
				}
			}
		}
		return Integer.toString(index);
	}

	@Override
	public String part2() {
		//initialize md5
		MessageDigest m = null;
		try {
			m = MessageDigest.getInstance("MD5");
		} catch (Exception e) {
			System.exit(0);
		}
		
		//store keys calculated deeper in the loop in an index -> key hashmap, to avoid excess key generation
		HashMap<Integer,char[]> keys = new HashMap<Integer,char[]>();
		
		int keyCount = 0;
		int index = -1;
		while(keyCount < 64) {
			index++;
			char[] hex;
			if(keys.containsKey(index))
				hex = keys.get(index);
			else {
				String key = input + index;
				//key stretching
				for(int i = 0; i < 2017; i++) {
					byte[] bytes = m.digest(key.getBytes());
					key = hex(bytes);
				}
				hex = key.toCharArray();
			}
			
			boolean triple = false;
			char tripleChar = 0;
			for(int i = 0; i < hex.length - 2; i++) {
				if(hex[i] == hex[i+1] && hex[i] == hex[i+2]) {
					triple = true;
					tripleChar = hex[i];
					break;
				}
			}
			if(triple) {
				for(int i = index+1; i <= index+1000; i++) {
					char[] inHex;
					if(keys.containsKey(i)) {
						inHex = keys.get(i);
					} else {
						String key = input + i;
						for(int j = 0; j < 2017; j++) {
							byte[] bytes = m.digest(key.getBytes());
							key = hex(bytes);
						}
						inHex = key.toCharArray();
						keys.put(i, inHex);
					}
					boolean quintuple = false;
					for(int j = 0; j < inHex.length - 4; j++) {
						if(inHex[j] == tripleChar && inHex[j+1] == tripleChar && inHex[j+2] == tripleChar && inHex[j+3] == tripleChar && inHex[j+4] == tripleChar) {
							quintuple = true;
							break;
						}
					}
					if(quintuple) {
						keyCount++;
						break;
					}
				}
			}
		}
		return Integer.toString(index);
	}
	
	//you wouldn't BELIEVE how difficult fast and accurate byte[] to hex conversion can be
	//BigInteger doesnt work, String.format doesnt work, but bitshift tomfoolery does
	public String hex(byte[] b) {
		final char[] hexArray = "0123456789abcdef".toCharArray();
		char[] hexChars = new char[b.length * 2];
		for(int i = 0; i < b.length; i++) {
			//int representation of byte (sign trim)
			int h = b[i] & 0xFF;
			//put each half of byte into hex array
			//upper half
			hexChars[i*2] = hexArray[h >> 4];
			//lower half
			hexChars[i*2+1] = hexArray[h & 0x0F];
		}
		return new String(hexChars);
	}

	public static void main(String[] args) {
		DayRunner.run(new Day14());
	}

}
