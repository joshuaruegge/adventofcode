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
import advent.utilities.utils2019.IntCodeComputer;

public class Day15 implements IDay{

	static String input = "3,1033,1008,1033,1,1032,1005,1032,31,1008,1033,2,1032,1005,1032,58,1008,1033,3,1032,1005,1032,81,1008,1033,4,1032,1005,1032,104,99,101,0,1034,1039,1001,1036,0,1041,1001,1035,-1,1040,1008,1038,0,1043,102,-1,1043,1032,1,1037,1032,1042,1105,1,124,102,1,1034,1039,1002,1036,1,1041,1001,1035,1,1040,1008,1038,0,1043,1,1037,1038,1042,1106,0,124,1001,1034,-1,1039,1008,1036,0,1041,1002,1035,1,1040,102,1,1038,1043,102,1,1037,1042,1106,0,124,1001,1034,1,1039,1008,1036,0,1041,1001,1035,0,1040,1002,1038,1,1043,101,0,1037,1042,1006,1039,217,1006,1040,217,1008,1039,40,1032,1005,1032,217,1008,1040,40,1032,1005,1032,217,1008,1039,37,1032,1006,1032,165,1008,1040,33,1032,1006,1032,165,1101,0,2,1044,1106,0,224,2,1041,1043,1032,1006,1032,179,1101,0,1,1044,1105,1,224,1,1041,1043,1032,1006,1032,217,1,1042,1043,1032,1001,1032,-1,1032,1002,1032,39,1032,1,1032,1039,1032,101,-1,1032,1032,101,252,1032,211,1007,0,62,1044,1106,0,224,1101,0,0,1044,1106,0,224,1006,1044,247,101,0,1039,1034,1002,1040,1,1035,102,1,1041,1036,101,0,1043,1038,1001,1042,0,1037,4,1044,1106,0,0,60,10,88,42,71,78,10,10,70,23,65,29,47,58,86,53,77,61,77,63,18,9,20,68,45,15,67,3,95,10,14,30,81,53,3,83,46,31,95,43,94,40,21,54,93,91,35,80,9,17,81,94,59,83,49,96,61,63,24,85,69,82,45,71,48,39,32,69,93,11,90,19,78,54,79,66,6,13,76,2,67,69,10,9,66,43,73,2,92,39,12,99,33,89,18,9,78,11,96,23,55,96,49,12,85,93,49,22,70,93,59,76,68,55,66,54,32,34,36,53,64,84,87,61,43,79,7,9,66,40,69,9,76,92,18,78,49,39,80,32,70,52,74,37,86,11,77,51,15,28,84,19,13,75,28,86,3,82,93,15,79,61,93,93,31,87,43,67,44,83,78,43,46,46,12,89,19,85,44,95,65,24,70,93,50,98,72,66,80,23,87,19,97,40,25,9,49,6,81,35,9,52,71,27,63,3,96,94,21,24,48,79,67,72,72,15,85,93,22,95,34,3,63,21,79,9,51,92,45,87,25,41,80,13,88,68,66,18,85,75,39,80,17,54,93,89,65,21,91,73,53,60,69,29,82,99,5,22,65,9,69,61,80,63,38,71,61,61,11,68,30,74,11,26,53,59,97,2,12,74,79,44,73,72,27,17,34,92,26,27,88,66,5,97,34,81,86,30,35,6,64,36,34,65,80,12,90,65,95,21,90,55,43,71,89,56,97,91,27,27,73,80,34,22,48,89,84,35,88,90,47,4,32,77,31,2,82,66,76,43,74,68,56,78,36,59,66,58,75,89,96,51,51,97,34,49,86,70,26,46,89,43,99,97,66,32,51,32,77,33,86,92,56,68,64,39,83,55,25,98,24,56,73,21,98,39,24,67,21,4,76,10,32,91,53,82,37,59,72,63,78,43,67,2,72,69,50,71,19,72,92,51,12,93,61,88,24,84,35,93,30,63,70,7,78,83,42,63,6,25,24,73,76,22,99,68,14,85,14,75,32,88,42,47,97,2,91,97,51,79,12,71,91,7,1,87,82,21,98,63,37,19,85,1,48,77,54,76,12,92,28,91,25,85,88,8,92,32,67,18,56,51,67,58,80,59,77,76,25,7,73,58,72,96,75,15,27,37,23,83,58,68,83,50,67,41,39,89,24,1,83,63,8,64,54,76,50,3,89,97,74,48,15,91,22,37,71,77,9,1,85,38,23,58,10,75,86,72,80,59,24,64,7,63,85,53,61,89,68,7,80,4,68,56,39,66,31,69,6,7,76,88,17,89,42,64,56,11,97,65,64,71,88,61,31,32,53,88,99,55,73,20,90,10,86,32,50,89,53,83,42,80,28,63,98,38,85,72,57,88,23,52,96,77,39,65,88,40,26,91,56,1,94,51,94,24,20,81,74,23,45,72,56,22,84,70,44,50,68,32,98,51,75,3,61,75,59,3,7,98,76,45,78,47,74,60,69,78,54,67,29,63,47,79,72,57,73,44,63,98,6,93,36,20,27,90,77,39,44,64,68,47,48,69,78,29,76,48,1,81,10,67,32,72,47,89,83,18,39,85,65,97,15,59,13,74,29,84,50,80,94,8,27,83,67,43,75,52,96,17,82,29,83,45,85,82,71,76,44,30,10,91,16,7,31,63,2,68,75,46,70,28,93,91,17,13,81,57,93,32,27,65,61,93,11,84,10,66,14,83,14,77,26,77,13,86,21,84,87,87,34,99,69,88,1,74,61,72,54,93,16,76,54,86,63,94,13,79,24,97,0,0,21,21,1,10,1,0,0,0,0,0,0";
	
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
	
	public static void main(String[] args) {
		DayRunner.run(new Day15());
		
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
}
