package advent.aoc2021;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

import advent.utilities.general.Coord;
import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;

public class Day11 implements IDay {

	String input = "4112256372\r\n"
			+ "3143253712\r\n"
			+ "4516848631\r\n"
			+ "3783477137\r\n"
			+ "3746723582\r\n"
			+ "5861358884\r\n"
			+ "4843351774\r\n"
			+ "2316447621\r\n"
			+ "6643817745\r\n"
			+ "6366815868";
	
	@Override
	public String part1() {
		HashMap<Coord,Integer> level = new HashMap<Coord,Integer>();
		int row = 0;
		for(String s : input.split("\r\n")) {
			for(int i = 0; i < s.length(); i++) {
				level.put(new Coord(row,i), Integer.parseInt(s.substring(i,i+1)));
			}
			row++;
		}
		
		int flashes = 0;
		for(int step = 0; step < 100; step++) {
			LinkedList<Coord> nines = new LinkedList<Coord>();
			for(Coord c : level.keySet()) {
				int cur = level.get(c);
				if(cur > 8)
					nines.add(c);
				level.put(c, cur+1);
			}
			HashSet<Coord> alreadyFlashed = new HashSet<Coord>();
			while(nines.size() > 0) {
				Coord c = nines.poll();
				if(alreadyFlashed.contains(c))
					continue;
				for(Coord d : c.allNeighbors()) {
					int cur = level.getOrDefault(d,-1);
					if(cur > 8)
						nines.add(d);
					if(cur != -1)
						level.put(d, cur+1);
				}
				alreadyFlashed.add(c);
			}
			//mark all flashed octopuses back to zero
			for(Coord c : alreadyFlashed)
				level.put(c, 0);
			flashes += alreadyFlashed.size();
		}
		return Integer.toString(flashes);
	}

	@Override
	public String part2() {
		HashMap<Coord,Integer> level = new HashMap<Coord,Integer>();
		int row = 0;
		for(String s : input.split("\r\n")) {
			for(int i = 0; i < s.length(); i++) {
				level.put(new Coord(row,i), Integer.parseInt(s.substring(i,i+1)));
			}
			row++;
		}
		
		int step = 1;
		while(true) {
			LinkedList<Coord> nines = new LinkedList<Coord>();
			for(Coord c : level.keySet()) {
				int cur = level.get(c);
				if(cur > 8)
					nines.add(c);
				level.put(c, cur+1);
			}
			HashSet<Coord> alreadyFlashed = new HashSet<Coord>();
			while(nines.size() > 0) {
				Coord c = nines.poll();
				if(alreadyFlashed.contains(c))
					continue;
				for(Coord d : c.allNeighbors()) {
					int cur = level.getOrDefault(d,-1);
					if(cur > 8)
						nines.add(d);
					if(cur != -1)
						level.put(d, cur+1);
				}
				alreadyFlashed.add(c);
			}
			if(alreadyFlashed.size() == 100)
				break;
			//mark all flashed octopuses back to zero
			for(Coord c : alreadyFlashed)
				level.put(c, 0);
			step++;
		}
		return Integer.toString(step);
	}

	public static void main(String[] args) {
		DayRunner.run(new Day11());
	}

}
