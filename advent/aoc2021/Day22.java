package advent.aoc2021;

import java.math.BigInteger;
import java.util.ArrayList;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;
import advent.utilities.utils2021.Cuboid;
import advent.utilities.general.Coord3;

public class Day22 implements IDay {

	static String input;
	
	@Override
	public String part1() {
		ArrayList<Cuboid> cubeTallies = new ArrayList<Cuboid>();
		for(String s : input.split("\n")) {
			String[] parts = s.split(" |,");
			
			String[] xParts = parts[1].substring(2).split("\\.\\.");
			String[] yParts = parts[2].substring(2).split("\\.\\.");
			String[] zParts = parts[3].substring(2).split("\\.\\.");
			Cuboid cur = new Cuboid(new Coord3(Integer.parseInt(xParts[0]),Integer.parseInt(yParts[0]),Integer.parseInt(zParts[0])),new Coord3(Integer.parseInt(xParts[1]) + 1,Integer.parseInt(yParts[1]) + 1,Integer.parseInt(zParts[1]) + 1));	
			
			ArrayList<Cuboid> update = new ArrayList<Cuboid>();
			
			if(parts[0].equals("on"))
				update.add(cur.sign(1));
			
			for(Cuboid old : cubeTallies) {
				Cuboid overlap = old.overlap(cur);
				if(overlap != null) {
					update.add(overlap.sign(old.sign * -1));
				}
			}
			
			cubeTallies.addAll(update);
		}
		
		ArrayList<Cuboid> inRange = new ArrayList<Cuboid>();
		Cuboid activationRange = new Cuboid(new Coord3(-50,-50,-50), new Coord3(51,51,51));
		for(Cuboid c : cubeTallies) {
			Cuboid overlap = c.overlap(activationRange);
			if(overlap != null)
				inRange.add(overlap.sign(c.sign));
		}
		
		BigInteger volume = BigInteger.ZERO;
		
		for(Cuboid c : inRange) {
			if(c.sign == 1) {
				volume = volume.add(c.volume());
			} else if(c.sign == -1) {
				volume = volume.subtract(c.volume());
			}
		}
		
		return volume.toString();
	}

	@Override
	public String part2() {
		ArrayList<Cuboid> cubeTallies = new ArrayList<Cuboid>();
		for(String s : input.split("\n")) {
			String[] parts = s.split(" |,");
			
			String[] xParts = parts[1].substring(2).split("\\.\\.");
			String[] yParts = parts[2].substring(2).split("\\.\\.");
			String[] zParts = parts[3].substring(2).split("\\.\\.");
			Cuboid cur = new Cuboid(new Coord3(Integer.parseInt(xParts[0]),Integer.parseInt(yParts[0]),Integer.parseInt(zParts[0])),new Coord3(Integer.parseInt(xParts[1]) + 1,Integer.parseInt(yParts[1]) + 1,Integer.parseInt(zParts[1]) + 1));	
			
			ArrayList<Cuboid> update = new ArrayList<Cuboid>();
			
			if(parts[0].equals("on"))
				update.add(cur.sign(1));
			
			for(Cuboid old : cubeTallies) {
				Cuboid overlap = old.overlap(cur);
				if(overlap != null) {
					update.add(overlap.sign(old.sign * -1));
				}
			}
			
			cubeTallies.addAll(update);
		}
		
		BigInteger volume = BigInteger.ZERO;
		
		for(Cuboid c : cubeTallies) {
			if(c.sign == 1) {
				volume = volume.add(c.volume());
			} else if(c.sign == -1) {
				volume = volume.subtract(c.volume());
			}
		}
		
		return volume.toString();
	}

	public static void main(String[] args) {
		input = Input.fetchInput(2021,22);
		DayRunner.run(new Day22());
	}
}
