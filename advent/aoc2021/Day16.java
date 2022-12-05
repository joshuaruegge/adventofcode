package advent.aoc2021;

import java.util.ArrayList;
import java.util.Collections;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;

public class Day16 implements IDay {

	String input = "020D708041258C0B4C683E61F674A1401595CC3DE669AC4FB7BEFEE840182CDF033401296F44367F938371802D2CC9801A980021304609C431007239C2C860400F7C36B005E446A44662A2805925FF96CBCE0033C5736D13D9CFCDC001C89BF57505799C0D1802D2639801A900021105A3A43C1007A1EC368A72D86130057401782F25B9054B94B003013EDF34133218A00D4A6F1985624B331FE359C354F7EB64A8524027D4DEB785CA00D540010D8E9132270803F1CA1D416200FDAC01697DCEB43D9DC5F6B7239CCA7557200986C013912598FF0BE4DFCC012C0091E7EFFA6E44123CE74624FBA01001328C01C8FF06E0A9803D1FA3343E3007A1641684C600B47DE009024ED7DD9564ED7DD940C017A00AF26654F76B5C62C65295B1B4ED8C1804DD979E2B13A97029CFCB3F1F96F28CE43318560F8400E2CAA5D80270FA1C90099D3D41BE00DD00010B893132108002131662342D91AFCA6330001073EA2E0054BC098804B5C00CC667B79727FF646267FA9E3971C96E71E8C00D911A9C738EC401A6CBEA33BC09B8015697BB7CD746E4A9FD4BB5613004BC01598EEE96EF755149B9A049D80480230C0041E514A51467D226E692801F049F73287F7AC29CB453E4B1FDE1F624100203368B3670200C46E93D13CAD11A6673B63A42600C00021119E304271006A30C3B844200E45F8A306C8037C9CA6FF850B004A459672B5C4E66A80090CC4F31E1D80193E60068801EC056498012804C58011BEC0414A00EF46005880162006800A3460073007B620070801E801073002B2C0055CEE9BC801DC9F5B913587D2C90600E4D93CE1A4DB51007E7399B066802339EEC65F519CF7632FAB900A45398C4A45B401AB8803506A2E4300004262AC13866401434D984CA4490ACA81CC0FB008B93764F9A8AE4F7ABED6B293330D46B7969998021C9EEF67C97BAC122822017C1C9FA0745B930D9C480";
	
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
		DayRunner.run(new Day16());
	}

}
