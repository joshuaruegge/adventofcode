package advent.aoc2018;

import java.util.Comparator;
import java.util.HashMap;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

public class Day23 implements IDay {

	static String input;
	
	@Override
	public String part1() {
		HashMap<Coord3L,Integer> bots = new HashMap<Coord3L,Integer>();
		for(String s : input.split("\n")) {
			String[] parts = s.split(", ");
			String[] coords = parts[0].substring(parts[0].indexOf("<") + 1, parts[0].indexOf(">")).split(",");
			Coord3L pos = new Coord3L(Integer.parseInt(coords[0]), Integer.parseInt(coords[1]), Integer.parseInt(coords[2]));
			bots.put(pos, Integer.parseInt(parts[1].split("=")[1]));
		}
		
		Coord3L best = bots.keySet().stream().sorted(new Comparator<Coord3L>() {

			@Override
			public int compare(Coord3L o1, Coord3L o2) {
				return Integer.compare(bots.get(o2), bots.get(o1));
			}
				
		}).findFirst().get();
		
		int bestRange = bots.get(best);
		
		int inRange = 0;
		
		for(Coord3L c : bots.keySet()) {
			if(c.dist(best) <= bestRange)
				inRange++;
		}
		
		return Integer.toString(inRange);
	}

	@Override
	public String part2() {
		System.out.println("Beware of longer runtimes - this is the best it gets");
		HashMap<Coord3L,Integer> bots = new HashMap<Coord3L,Integer>();
		for(String s : input.split("\n")) {
			String[] parts = s.split(", ");
			String[] coords = parts[0].substring(parts[0].indexOf("<") + 1, parts[0].indexOf(">")).split(",");
			Coord3L pos = new Coord3L(Integer.parseInt(coords[0]), Integer.parseInt(coords[1]), Integer.parseInt(coords[2]));
			bots.put(pos, Integer.parseInt(parts[1].split("=")[1]));
		}
		
		//creating a universal solution for this input is difficult - this is the best you can get without z3
		//if this solution doesn't function, change the searchSize value and try again
		int searchSize = 28;
		
		long xMin = bots.keySet().stream().mapToLong(x -> x.x).min().getAsLong();
		long xMax = bots.keySet().stream().mapToLong(x -> x.x).max().getAsLong();
		long yMin = bots.keySet().stream().mapToLong(x -> x.y).min().getAsLong();
		long yMax = bots.keySet().stream().mapToLong(x -> x.y).max().getAsLong();
		long zMin = bots.keySet().stream().mapToLong(x -> x.z).min().getAsLong();
		long zMax = bots.keySet().stream().mapToLong(x -> x.z).max().getAsLong();
		
		long largestRange = Math.max(Math.abs(xMax-xMin), Math.max(Math.abs(yMax-yMin), Math.abs(zMax-zMin)));
		Coord3L mid = new Coord3L(0,0,0);
		Coord3L best = mid.copy();
		long bestScore = 0;
		
		//divide the input up into cubes, and try to estimate which cube our best point is in, shrinking the cube as we go
		for(int stepPower = 32; stepPower > -1; stepPower--) {
			long step = 1 << stepPower;
			long offset = (step * searchSize) / 2;
			if(offset >= largestRange)
				continue;
			
			long negOff = (step / 2) - offset;
			long posOff = (step / 2) + offset;
			
			for(long x = best.x + negOff; x <= best.x + posOff; x += step) {
				for(long y = best.y + negOff; y <= best.y + posOff; y += step) {
					for(long z = best.z + negOff; z <= best.z + posOff; z += step) {
						Coord3L cur = new Coord3L(x,y,z);
						long score = bots.keySet().stream().filter(i -> (i.dist(cur) <= bots.get(i))).count();
						if(score > bestScore) {
							best = cur;
							bestScore = score;
						} else if (score == bestScore && best.dist(mid) > cur.dist(mid)) {
							best = cur;
						}
					}
				}
			}
			System.out.println("Step size: " + stepPower + ", potential solution: " + best.dist(mid));
		}
		
		return Long.toString(best.dist(mid));
	}

	public static void main(String[] args) {
		input = Input.fetchInput(2018,23);
		DayRunner.run(new Day23());
	}

}

//long version of coord3. these numbers are big
class Coord3L {
	long x;
	long y;
	long z;
	
	public Coord3L(long x, long y, long z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public long dist(Coord3L o) {
		return Math.abs(x - o.x) + Math.abs(y - o.y) + Math.abs(z - o.z);
	}
	
	public Coord3L copy() {
		return new Coord3L(x,y,z);
	}
}
