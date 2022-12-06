package advent.aoc2020;

import java.util.HashSet;

import advent.utilities.general.*;

public class Day17 implements IDay {

	static String input;
	
	@Override
	public String part1() {
		HashSet<Coord3> activeCubes = new HashSet<Coord3>();
		String[] lines = input.split("\n");
		for(int y = 0; y < lines.length; y++) {
			String line = lines[y];
			for(int x = 0; x < line.length(); x++) {
				if(line.charAt(x) == '#')
					activeCubes.add(new Coord3(x,y,0));
			}
		}
		
		for(int iter = 0; iter < 6; iter++) {
			HashSet<Coord3> newActiveCubes = new HashSet<Coord3>();
			//extract min and max for each dimension
			//pad each one extra in that direction to account for growth
			int minZ = activeCubes.stream().mapToInt(x -> x.z).min().getAsInt() - 1;
			int maxZ = activeCubes.stream().mapToInt(x -> x.z).max().getAsInt() + 1;
			int minY = activeCubes.stream().mapToInt(x -> x.y).min().getAsInt() - 1;
			int maxY = activeCubes.stream().mapToInt(x -> x.y).max().getAsInt() + 1;
			int minX = activeCubes.stream().mapToInt(x -> x.x).min().getAsInt() - 1;
			int maxX = activeCubes.stream().mapToInt(x -> x.x).max().getAsInt() + 1;
			
			for(int z = minZ; z <= maxZ; z++) {
				for(int y = minY; y <= maxY; y++) {
					for(int x = minX; x <= maxX; x++) {
						Coord3 cur = new Coord3(x,y,z);
						if(activeCubes.contains(cur)) {
							int surroundCount = 0;
							for(Coord3 c : cur.allNeighbors())
								if(activeCubes.contains(c))
									surroundCount++;
							if(surroundCount == 2 || surroundCount == 3)
								newActiveCubes.add(cur);
						} else {
							int surroundCount = 0;
							for(Coord3 c : cur.allNeighbors())
								if(activeCubes.contains(c))
									surroundCount++;
							if(surroundCount == 3)
								newActiveCubes.add(cur);
						}
					}
				}
			}
			
			activeCubes = newActiveCubes;
		}
		
		return Integer.toString(activeCubes.size());
	}
	
	@Override
	public String part2() {
		HashSet<Coord4> activeCubes = new HashSet<Coord4>();
		String[] lines = input.split("\n");
		for(int y = 0; y < lines.length; y++) {
			String line = lines[y];
			for(int x = 0; x < line.length(); x++) {
				if(line.charAt(x) == '#')
					activeCubes.add(new Coord4(x,y,0,0));
			}
		}
		
		for(int iter = 0; iter < 6; iter++) {
			HashSet<Coord4> newActiveCubes = new HashSet<Coord4>();
			//extract min and max for each dimension
			//pad each one extra in that direction to account for growth
			int minA = activeCubes.stream().mapToInt(x -> x.a).min().getAsInt() - 1;
			int maxA = activeCubes.stream().mapToInt(x -> x.a).max().getAsInt() + 1;
			int minZ = activeCubes.stream().mapToInt(x -> x.z).min().getAsInt() - 1;
			int maxZ = activeCubes.stream().mapToInt(x -> x.z).max().getAsInt() + 1;
			int minY = activeCubes.stream().mapToInt(x -> x.y).min().getAsInt() - 1;
			int maxY = activeCubes.stream().mapToInt(x -> x.y).max().getAsInt() + 1;
			int minX = activeCubes.stream().mapToInt(x -> x.x).min().getAsInt() - 1;
			int maxX = activeCubes.stream().mapToInt(x -> x.x).max().getAsInt() + 1;
			
			for(int a = minA; a <= maxA; a++) {
				for(int z = minZ; z <= maxZ; z++) {
					for(int y = minY; y <= maxY; y++) {
						for(int x = minX; x <= maxX; x++) {
							Coord4 cur = new Coord4(x,y,z,a);
							if(activeCubes.contains(cur)) {
								int surroundCount = 0;
								for(Coord4 c : cur.allNeighbors())
									if(activeCubes.contains(c))
										surroundCount++;
								if(surroundCount == 2 || surroundCount == 3)
									newActiveCubes.add(cur);
							} else {
								int surroundCount = 0;
								for(Coord4 c : cur.allNeighbors())
									if(activeCubes.contains(c))
										surroundCount++;
								if(surroundCount == 3)
									newActiveCubes.add(cur);
							}
						}
					}
				}
			}
			
			activeCubes = newActiveCubes;
		}
		
		return Integer.toString(activeCubes.size());
	}

	public static void main(String[] args) {
		input = Input.fetchInput(2020,17);
		DayRunner.run(new Day17());
	}
}
