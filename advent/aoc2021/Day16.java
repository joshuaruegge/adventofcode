package advent.aoc2021;

import java.util.ArrayList;
import java.util.Collections;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

public class Day16 implements IDay {

	static String input;
	
	static int pointer;
	
	static String data;
	
	@Override
	public String part1() {
		pointer = 0;
		data = "";
		for(char c : input.toCharArray()) {
			data += hex(c);
		}
		
		return Integer.toString(sumPacketVersions());
	}

	@Override
	public String part2() {
		//global pointer for recursiveness
		pointer = 0;
		data = "";
		for(char c : input.toCharArray()) {
			data += hex(c);
		}
		
		return Long.toString(getPacketValue());
	}

	public int sumPacketVersions() {
		int version = 0;
		version += Integer.parseInt(data.substring(pointer,pointer+3),2);
		pointer += 3;
		int packetType = Integer.parseInt(data.substring(pointer,pointer+3),2);
		pointer += 3;
		ArrayList<Integer> versions = new ArrayList<Integer>();
		if(packetType != 4) {
			boolean type = data.charAt(pointer++) == '1';
			if(type) {
				int numPackets = Integer.parseInt(data.substring(pointer,pointer + 11),2);
				pointer += 11;
				while(versions.size() < numPackets) {
					versions.add(sumPacketVersions());
				}
			} else {
				int dataLength = Integer.parseInt(data.substring(pointer,pointer + 15),2);
				pointer += 15;
				int dataTarget = pointer + dataLength;
				while(pointer < dataTarget) {
					versions.add(sumPacketVersions());
				}
			}
		} else {
			while(data.charAt(pointer) != '0') {
				//skip literal data for now
				pointer += 5;
			}
			//skip last part of data
			pointer += 5;
		}
		for(int i : versions)
			version += i;
		return version;
	}

	public long getPacketValue() {
		pointer += 3;
		int packetType = Integer.parseInt(data.substring(pointer,pointer+3),2);
		pointer += 3;
		ArrayList<Long> values = new ArrayList<Long>();
		if(packetType != 4) {
			boolean type = data.charAt(pointer++) == '1';
			if(type) {
				int numPackets = Integer.parseInt(data.substring(pointer,pointer + 11),2);
				pointer += 11;
				while(values.size() < numPackets) {
					values.add(getPacketValue());
				}
			} else {
				int dataLength = Integer.parseInt(data.substring(pointer,pointer + 15),2);
				pointer += 15;
				int dataTarget = pointer + dataLength;
				while(pointer < dataTarget) {
					values.add(getPacketValue());
				}
			}
			
		} else {
			String binary = "";
			while(data.charAt(pointer) == '1') {
				binary += data.substring(pointer+1,pointer+5);
				pointer += 5;
			}
			binary += data.substring(pointer+1,pointer+5);
			pointer += 5;
			values.add(Long.parseLong(binary,2));
		}
		
		switch(packetType) {
		case 0:
			return values.stream().reduce(Long::sum).get();
		case 1:
			long mult = 1;
			for(long l : values)
				mult *= l;
			return mult;
		case 2:
			return Collections.min(values);
		case 3:
			return Collections.max(values);
		case 4:
			return values.get(0);
		case 5:
			return values.get(0) > values.get(1) ? 1l : 0l;
		case 6:
			return values.get(0) < values.get(1) ? 1l : 0l;
		case 7:
			//boxed Longs are very finicky about == comparison
			//you don't want to know how much trouble this caused me
			return values.get(0).equals(values.get(1)) ? 1l : 0l;
		}
		return -1;
	}
	
	public String hex(char c) {
		switch(c) {
		case '0':
			return "0000";
		case '1':
			return "0001";
		case '2':
			return "0010";
		case '3':
			return "0011";
		case '4':
			return "0100";
		case '5':
			return "0101";
		case '6':
			return "0110";
		case '7':
			return "0111";
		case '8':
			return "1000";
		case '9':
			return "1001";
		case 'A':
			return "1010";
		case 'B':
			return "1011";
		case 'C':
			return "1100";
		case 'D':
			return "1101";
		case 'E':
			return "1110";
		case 'F':
			return "1111";
		}
		return null;
	}
	
	public static void main(String[] args) {
		input = Input.fetchInput(2021,16);
		DayRunner.run(new Day16());
	}
}
