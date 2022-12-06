package advent.aoc2018;

import java.util.ArrayList;

import advent.utilities.general.Coord;
import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

public class Day10 implements IDay {

	static String input;
	
	public String part1() {
		ArrayList<Coord> pos = new ArrayList<Coord>();
		ArrayList<Coord> vel = new ArrayList<Coord>();
		for(String s : input.split("\n")) {
			String[] parts = s.split("> ");
			String[] posParts = parts[0].split(", ");
			int posX = Integer.parseInt(posParts[0].substring(posParts[0].indexOf("<") + 1).trim());
			int posY = Integer.parseInt(posParts[1].trim());
			String[] velParts = parts[1].split(", ");
			int velX = Integer.parseInt(velParts[0].substring(velParts[0].indexOf("<") + 1).trim());
			int velY = Integer.parseInt(velParts[1].substring(0,velParts[1].length() - 1).trim());
			pos.add(new Coord(posX,posY));
			vel.add(new Coord(velX,velY));
		}
		do {
			for(int i = 0; i < pos.size(); i++) {
				pos.get(i).sumSelf(vel.get(i));
			}
		} while (!allAdjacent(pos));
		

		int minX = pos.stream().map(x -> x.x).min(Integer::compare).get();
		int maxX = pos.stream().map(x -> x.x).max(Integer::compare).get();
		int minY = pos.stream().map(x -> x.y).min(Integer::compare).get();
		int maxY = pos.stream().map(x -> x.y).max(Integer::compare).get();
		
		StringBuilder out = new StringBuilder("\n");
		for(int y = minY; y <= maxY; y++) {
			for(int x = minX; x <= maxX; x++) {
				if(pos.contains(new Coord(x,y))) {
					out.append('#');
				} else {
					out.append('.');
				}
			}
			out.append('\n');
		}
		return out.toString();
		
	}
	
	//presumably, our valid "state" where the letters show up is one where all points are adjacent to at least one other point
	public boolean allAdjacent(ArrayList<Coord> a) {
		//iterate over positions - if at least one neighboring point is in positions, point is valid
		pointLoop:
		for(Coord c : a) {
			for(Coord d : c.allNeighbors()) {
				if(a.contains(d))
					continue pointLoop;
			}
			//if we made it here, no neighbors are in positions - state is invalid
			return false;
		}
		return true;
	}

	@Override
	public String part2() {
		ArrayList<Coord> pos = new ArrayList<Coord>();
		ArrayList<Coord> vel = new ArrayList<Coord>();
		for(String s : input.split("\n")) {
			String[] parts = s.split("> ");
			String[] posParts = parts[0].split(", ");
			int posX = Integer.parseInt(posParts[0].substring(posParts[0].indexOf("<") + 1).trim());
			int posY = Integer.parseInt(posParts[1].trim());
			String[] velParts = parts[1].split(", ");
			int velX = Integer.parseInt(velParts[0].substring(velParts[0].indexOf("<") + 1).trim());
			int velY = Integer.parseInt(velParts[1].substring(0,velParts[1].length() - 1).trim());
			pos.add(new Coord(posX,posY));
			vel.add(new Coord(velX,velY));
		}
		int counter = 0;
		do {
			for(int i = 0; i < pos.size(); i++) {
				pos.get(i).sumSelf(vel.get(i));
			}
			counter++;
		} while (!allAdjacent(pos));
		
		return Integer.toString(counter);
	}

	public static void main(String[] args) {
		input = Input.fetchInput(2018,10);
		DayRunner.run(new Day10());
	}

}
