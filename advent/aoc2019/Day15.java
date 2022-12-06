package advent.aoc2019;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;

import advent.utilities.general.Coord;
import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;
import advent.utilities.utils2019.IntCodeComputer;

public class Day15 implements IDay{

	static String input;
	
	static HashSet<Coord> wallPositions = new HashSet<Coord>();
	static HashSet<Coord> airPositions = new HashSet<Coord>();
	static Coord target = null;
	
	static Coord position = new Coord(0,0);
	
	@Override
	public String part1() {
		//parse program
		ArrayList<Long> program = new ArrayList<Long>(Arrays.stream(input.split(",")).map(x -> Long.parseLong(x)).toList());
		IntCodeComputer bot = new IntCodeComputer(program);
		airPositions.add(position.copy());
		//get list of unexplored locations
		ArrayList<Coord> unexplored = unexploreds();
		//while map is incomplete
		while(unexplored.size() > 0) {
			//sort unexplored so we check closest unexplored location
			Collections.sort(unexplored, new Comparator<Coord>() {
				@Override
				public int compare(Coord o1, Coord o2) {
					return Integer.compare(o1.dist(position), o2.dist(position));
				}
			});
			//get next unexplored location
			Coord nextUnexplored = unexplored.remove(0);
			//find an adjacent coordinate to move to
			Coord adjacent = adjacentAir(nextUnexplored);
			//if we aren't already adjacent, move the bot to adjacent
			if(!adjacent.equals(position))
				sendBotTo(bot,position,adjacent);
			//test the unexplored location, update position with new position
			position = test(bot,adjacent,nextUnexplored);
			//recalculate unexplored list
			unexplored = unexploreds();
		}
		//find path from start to oxygen
		ArrayList<Coord> targetPath = pathBetween(new Coord(0,0), target);
		return Integer.toString(targetPath.size());
	}

	@Override
	public String part2() {
		//i mean, they dont call it flood-fill algorithm for nothing
		HashSet<Coord> oxygen = new HashSet<Coord>();
		oxygen.add(target);
		int count = 0;
		while(oxygen.size() < airPositions.size()) {
			//keep track of all new oxygen positions this "cycle"
			HashSet<Coord> newOxygen = new HashSet<Coord>();
			for(Coord c : oxygen) {
				for(Coord next : c.directNeighbors()) {
					if(!oxygen.contains(next) && airPositions.contains(next))
						newOxygen.add(next);
				}
			}
			count++;
			oxygen.addAll(newOxygen);
		}
		return Integer.toString(count);
	}

	//iterate through airPositions, and add any adjacent positions that are neither air or wall (i.e. unknown)
	public static ArrayList<Coord> unexploreds () {
		ArrayList<Coord> u = new ArrayList<Coord>();
		for(Coord c : airPositions) {
			for(Coord neighbor : c.directNeighbors()) {
				if(!wallPositions.contains(neighbor) && !airPositions.contains(neighbor)) {
					u.add(neighbor);
				}
			}
		}
		return u;
	}
	
	//find an adjacent "known" coordinate next to an unknown coordinate to move to
	public Coord adjacentAir(Coord c) {
		for(Coord d : c.directNeighbors())
			if(airPositions.contains(d))
				return d;
		return null;
	}
	
	//input moves to send the bot from pos to dest, along the path supplied by pathBetween() and using commands from commandToMove()
	public static void sendBotTo(IntCodeComputer i, Coord pos, Coord dest) {
		ArrayList<Coord> path = pathBetween(pos,dest);
		for(Coord c : path) {
			i.input(commandToMove(pos,c));
			i.run();
			pos = c.copy();
		}
	}
	
	public static ArrayList<Coord> pathBetween(Coord start, Coord end) {
		HashMap<Coord,Integer> gScore = new HashMap<Coord,Integer>();
		gScore.put(start, 0);
		HashMap<Coord,Coord> parent = new HashMap<Coord,Coord>();
		PriorityQueue<Coord> queue = new PriorityQueue<Coord>(new Comparator<Coord>() {

			@Override
			public int compare(Coord o1, Coord o2) {
				return Integer.compare(gScore.getOrDefault(o1, 0) + o1.dist(end), gScore.getOrDefault(o2, 0) + o2.dist(end));
			}
			
		});
		
		queue.add(start);
		while(queue.size() > 0) {
			Coord cur = queue.poll();
			if(cur.equals(end)) {
				ArrayList<Coord> path = new ArrayList<Coord>();
				while(parent.containsKey(cur)) {
					//copy?
					path.add(cur.copy());
					cur = parent.get(cur);
				}
				Collections.reverse(path);
				return path;
			}
			for(Coord c : cur.directNeighbors()) {
				//check in gscore keyset first?
				if(airPositions.contains(c)) {
					int tentativeG = gScore.get(cur) + 1;
					if(tentativeG < gScore.getOrDefault(c, Integer.MAX_VALUE)) {
						gScore.put(c, tentativeG);
						parent.put(c, cur);
						queue.add(c);
					}
				}
			}
		}
		return null;
	}
	
	//returns the movement code to pass to the IntCodeComputer to move from pos to dest
	public static int commandToMove(Coord pos, Coord dest) {
		Coord move = dest.sum(new Coord(-pos.x,-pos.y));
		if(move.equals(new Coord(0,-1)))
			return 1;
		if(move.equals(new Coord(0,1)))
			return 2;
		if(move.equals(new Coord(1,0)))
			return 3;
		if(move.equals(new Coord(-1,0)))
			return 4;
		return -1;
	}
	
	//"tests" an unknown location, and returns the resulting position
	public static Coord test(IntCodeComputer i, Coord pos, Coord test) {
		i.output.clear();
		i.input(commandToMove(pos,test));
		i.run();
		long result = i.output();
		
		if(result == 0) {
			wallPositions.add(test.copy());
			//hit wall - position is unchanged
			return pos;
		} else if(result == 1) {
			airPositions.add(test.copy());
			//moved into open space - position is now test
			return test;
		} else if(result == 2) {
			airPositions.add(test.copy());
			target = test.copy();
			//moved into open space - position is now test
			return test;
		}
		return null;
	}

	public static void main(String[] args) {
		input = Input.fetchInput(2019,15).trim();
		DayRunner.run(new Day15());
	}
}
