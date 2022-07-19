package advent.aoc2016;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import advent.utilities.general.Coord;
import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;

public class Day17 implements IDay {

	static String input = "yjjvjgan";
	
	final static Coord END = new Coord(3,3);
	
	static int lowest = Integer.MAX_VALUE;
	
	static MessageDigest m = null;
	@Override
	public String part1() {
		try {
			m = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return shortestPath(new Coord(0,0),"");
	}

	public static String shortestPath (Coord cur, String path) {
		if(path.length() > lowest)
			return null;
		if(cur.equals(END)) {
			if(path.length() < lowest)
				lowest = path.length();
			return path;
		}
		String hashKey = input + path;
		byte[] hash = m.digest(hashKey.getBytes());
		//extract first four hex values of hash, store in ints
		int bit1 = Byte.toUnsignedInt(hash[0]);
		int bit2 = Byte.toUnsignedInt(hash[1]);
		
		int up = bit1 >> 4;
		int down = bit1 & 15;
		int left = bit2 >> 4;
		int right = bit2 & 15;
	
		//make recursive calls for each 
		int minLength = Integer.MAX_VALUE;
		String min = null;
		//up
		if(up > 10) {
			Coord newPosition = cur.sum(new Coord(0,-1));
			if(inBounds(newPosition)) {
				String result = shortestPath(cur.sum(new Coord(0,-1)),path + "U");
				if(result != null && result.length() < minLength) {
					minLength = result.length();
					min = result;
				}
			}
		}
		//down
		if(down > 10) {
			Coord newPosition = cur.sum(new Coord(0,1));
			if(inBounds(newPosition)) {
				String result = shortestPath(cur.sum(new Coord(0,1)),path + "D");
				if(result != null && result.length() < minLength) {
					minLength = result.length();
					min = result;
				}
			}
		}
		//left
		if(left > 10) {
			Coord newPosition = cur.sum(new Coord(-1,0));
			if(inBounds(newPosition)) {
				String result = shortestPath(cur.sum(new Coord(-1,0)),path + "L");
				if(result != null && result.length() < minLength) {
					minLength = result.length();
					min = result;
				}
			}
		}
		//right
		if(right > 10) {
			Coord newPosition = cur.sum(new Coord(1,0));
			if(inBounds(newPosition)) {
				String result = shortestPath(cur.sum(new Coord(1,0)),path + "R");
				if(result != null && result.length() < minLength) {
					minLength = result.length();
					min = result;
				}
			}
		}
		//return smallest of up, down, left, right
		return min;
	}
	
	public static boolean inBounds(Coord c) { 
		return c.x > -1 && c.x < 4 && c.y > -1 && c.y < 4;
	}
	@Override
	public String part2() {
		return Integer.toString(longestPath(new Coord(0,0), ""));
	}
	
	public static int longestPath(Coord cur, String path) {
		if(cur.equals(END)) {
			return path.length();
		}
		String hashKey = input + path;
		byte[] hash = m.digest(hashKey.getBytes());
		//extract first four hex values of hash, store in ints
		int bit1 = Byte.toUnsignedInt(hash[0]);
		int bit2 = Byte.toUnsignedInt(hash[1]);
		
		int up = bit1 >> 4;
		int down = bit1 & 15;
		int left = bit2 >> 4;
		int right = bit2 & 15;
		
		//make recursive calls for each 
		int max = 0;
		//up
		if(up > 10) {
			Coord newPosition = cur.sum(new Coord(0,-1));
			if(inBounds(newPosition)) {
				int upMax = longestPath(newPosition, path + "U");
				max = Math.max(max, upMax);
			}
		}
		//down
		if(down > 10) {
			Coord newPosition = cur.sum(new Coord(0,1));
			if(inBounds(newPosition)) {
				int downMax = longestPath(newPosition, path + "D");
				max = Math.max(max, downMax);
			}
		}
		//left
		if(left > 10) {
			Coord newPosition = cur.sum(new Coord(-1,0));
			if(inBounds(newPosition)) {
				int leftMax = longestPath(newPosition,path + "L");
				max = Math.max(max, leftMax);
			}
		}
		//right
		if(right > 10) {
			Coord newPosition = cur.sum(new Coord(1,0));
			if(inBounds(newPosition)) {
				int rightMax = longestPath(newPosition,path + "R");
				max = Math.max(max, rightMax);
			}
		}
		//return smallest of up, down, left, right
		return max;
	}

	public static void main(String[] args) {
		DayRunner.run(new Day17());
	}

}
