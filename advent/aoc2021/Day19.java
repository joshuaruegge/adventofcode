package advent.aoc2021;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

import advent.utilities.general.Coord3;
import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

public class Day19 implements IDay {

	static String input;
	
	@Override
	public String part1() {
		LinkedList<HashSet<Coord3>> scannerData = new LinkedList<HashSet<Coord3>>();
		for(String s : input.split("\n\n")) {
			HashSet<Coord3> beacons = new HashSet<Coord3>();
			String[] points = s.split("\n");
			for(int i = 1; i < points.length; i++) {
				String[] dims = points[i].split(",");
				beacons.add(new Coord3(Integer.parseInt(dims[0]), Integer.parseInt(dims[1]), Integer.parseInt(dims[2])));
			}
			scannerData.add(beacons);
		}
		
		HashSet<Coord3> beacons = scannerData.remove(0);
		HashSet<Coord3> scanners = new HashSet<Coord3>();
		scanners.add(new Coord3(0,0,0));
		
		while(scannerData.size() > 0) {
			HashSet<Coord3> potentialSet = scannerData.pop();
			ArrayList<Coord3> transformed = transformToMatch(beacons,potentialSet);
			if(transformed == null) {
				//append back into queue, hit later
				scannerData.add(potentialSet);
			} else {
				scanners.add(transformed.remove(0));
				beacons.addAll(transformed);
			}
		}
		
		return Integer.toString(beacons.size());
	}
	
	@Override
	public String part2() {
		LinkedList<HashSet<Coord3>> scannerData = new LinkedList<HashSet<Coord3>>();
		for(String s : input.split("\n\n")) {
			HashSet<Coord3> beacons = new HashSet<Coord3>();
			String[] points = s.split("\n");
			for(int i = 1; i < points.length; i++) {
				String[] dims = points[i].split(",");
				beacons.add(new Coord3(Integer.parseInt(dims[0]), Integer.parseInt(dims[1]), Integer.parseInt(dims[2])));
			}
			scannerData.add(beacons);
		}
		
		HashSet<Coord3> beacons = scannerData.remove(0);
		HashSet<Coord3> scanners = new HashSet<Coord3>();
		scanners.add(new Coord3(0,0,0));
		
		while(scannerData.size() > 0) {
			HashSet<Coord3> potentialSet = scannerData.pop();
			ArrayList<Coord3> transformed = transformToMatch(beacons,potentialSet);
			if(transformed == null) {
				//append back into queue, hit later
				scannerData.add(potentialSet);
			} else {
				scanners.add(transformed.remove(0));
				beacons.addAll(transformed);
			}
		}
		
		int max = 0;
		for(Coord3 a : scanners) {
			for(Coord3 b : scanners) {
				max = Math.max(max, a.dist(b));
			}
		}
		
		return Integer.toString(max);
	}

	public Coord3 rotate(Coord3 c, int i) {
		switch(i) {
			case 0:
				return c;
			case 1:
				return new Coord3(-c.y,c.x,c.z);
			case 2:
				return new Coord3(-c.x,-c.y,c.z);
			case 3:
				return new Coord3(c.y,-c.x,c.z);
		}
		return null;
	}

	public Coord3 face(Coord3 c, int i) {
		switch(i) {
			case 0:
				return c;
			case 1:
				return new Coord3(c.x,-c.y,-c.z);
			case 2:
				return new Coord3(c.x,-c.z,c.y);
			case 3:
				return new Coord3(-c.y,-c.z,c.x);
			case 4:
				return new Coord3(c.y,-c.z,-c.x);
			case 5:
				return new Coord3(-c.x,-c.z,-c.y);
		}
		return null;
	}

	public ArrayList<Coord3> transformToMatch(HashSet<Coord3> points, HashSet<Coord3> newPoints) {
		for(int face = 0; face < 6; face++) {
			for(int rotate = 0; rotate < 4; rotate++) {
				HashSet<Coord3> reoriented = new HashSet<Coord3>();
				for(Coord3 c : newPoints)
					reoriented.add(rotate(face(c,face),rotate));
				for(Coord3 c : points) {
					for(Coord3 d : reoriented) {
						Coord3 diff = c.diff(d);
						HashSet<Coord3> repositioned = new HashSet<Coord3>(reoriented.stream().map(x -> x.sum(diff)).toList());
						HashSet<Coord3> common = new HashSet<Coord3>(repositioned);
						common.retainAll(points);
						if(common.size() >= 12) {
							ArrayList<Coord3> ret = new ArrayList<Coord3>();
							ret.add(diff);
							ret.addAll(repositioned);
							return ret;
						}
					}
				}
			}
		}
		return null;
	}

	public static void main(String[] args) {
		input = Input.fetchInput(2021,19);
		DayRunner.run(new Day19());
	}
}
