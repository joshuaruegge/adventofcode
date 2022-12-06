package advent.aoc2020;

import java.util.HashMap;
import java.util.HashSet;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

public class Day14 implements IDay {

	static String input;
	
	@Override
	public String part1() {
		HashMap<Integer,String> mem = new HashMap<Integer,String>();
		String mask = "";
		for(String s : input.split("\n")) {
			String[] parts = s.split(" = ");
			if(parts[0].equals("mask")) {
				mask = parts[1];
				continue;
			}
			String bits = Long.toBinaryString(Long.parseLong(parts[1]));
			//append leading zeroes
			while(bits.length() < 36)
				bits = "0" + bits;
			
			//create new string from mask
			String result = "";
			for(int i = 0; i < 36; i++) {
				if(mask.charAt(i) == 'X') {
					result += bits.charAt(i);
				} else {
					result += mask.charAt(i);
				}
			}
			int memAddress = Integer.parseInt(parts[0].substring(parts[0].indexOf('[') + 1, parts[0].indexOf(']')));
			mem.put(memAddress, result);
		}
		
		long sum = 0;
		for(String s : mem.values())
			sum += Long.parseLong(s,2);
		return Long.toString(sum);
	}
	
	@Override
	public String part2() {
		HashMap<String, Long> mem = new HashMap<String,Long>();
		String mask = "";
		for(String s : input.split("\n")) {
			String[] parts = s.split(" = ");
			if(parts[0].equals("mask")) {
				mask = parts[1];
				continue;
			}
			
			long memValue = Long.parseLong(parts[1]);
			
			String initialAddress = Integer.toBinaryString(Integer.parseInt(parts[0].substring(4, parts[0].indexOf(']'))));
			//leading zeroes
			while(initialAddress.length() < 36)
				initialAddress = '0' + initialAddress;
			
			//apply mask
			String maskedAddress = "";
			for(int i = 0; i < 36; i++) {
				switch(mask.charAt(i)) {
				case 'X':
					maskedAddress += 'X';
					break;
				case '1':
					maskedAddress += '1';
					break;
				case '0':
					maskedAddress += initialAddress.charAt(i);
					break;
				}
			}
			
			for(String a : allAddresses(maskedAddress))
				mem.put(a, memValue);
			
		}
		return Long.toString(mem.values().stream().mapToLong(x -> x).sum());
	}
	
	public HashSet<String> allAddresses(String s) {
		HashSet<String> addresses = new HashSet<String>();
		if(!s.contains("X")) {
			addresses.add(s);
			return addresses;
		}
		int firstX = s.indexOf("X");
		//recursive of each potential value for first X
		addresses.addAll(allAddresses(s.substring(0,firstX) + "0" + s.substring(firstX + 1)));
		addresses.addAll(allAddresses(s.substring(0,firstX) + "1" + s.substring(firstX + 1)));
		return addresses;
	}

	public static void main(String[] args) {
		input = Input.fetchInput(2020,14);
		DayRunner.run(new Day14());
	}
}
