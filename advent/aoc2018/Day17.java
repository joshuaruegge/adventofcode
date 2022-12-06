package advent.aoc2018;

import java.util.HashMap;
import java.util.LinkedList;

import advent.utilities.general.Coord;
import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

public class Day17 implements IDay {

	static String input;
	
	static HashMap<Coord,Integer> state;
	
	static int minX, maxX, minY, maxY;
	
	@Override
	public String part1() {
		state = new HashMap<Coord,Integer>();
		//state indexes: 0 - clay
		//1 - flowing water
		//2 - still water
		//not present (no key) - air
		
		for(String line : input.split("\n")) {
			String[] parts = line.split(", ");
			String[] firstHalf = parts[0].split("=");
			String[] secondHalf = parts[1].split("=|\\.\\.");
			if(firstHalf[0].equals("x")) {
				int x = Integer.parseInt(firstHalf[1]);
				int min = Integer.parseInt(secondHalf[1]);
				int max = Integer.parseInt(secondHalf[2]);
				for(int inY = min; inY <= max; inY++) {
					state.put(new Coord(x,inY),0);
				}
			}
			else {
				int y = Integer.parseInt(firstHalf[1]);
				int min = Integer.parseInt(secondHalf[1]);
				int max = Integer.parseInt(secondHalf[2]);
				for(int inX = min; inX <= max; inX++) {
					state.put(new Coord(inX,y),0);
				}
			}
		}
		
		minX = state.keySet().stream().mapToInt(x -> x.x).min().getAsInt();
		maxX = state.keySet().stream().mapToInt(x -> x.x).max().getAsInt();
		minY = state.keySet().stream().mapToInt(x -> x.y).min().getAsInt();
		maxY = state.keySet().stream().mapToInt(x -> x.y).max().getAsInt();
		//offset minX and maxX by one to account for potential water flowing one past the side of the furthest 'bin'
		minX--;
		maxX++;
		
		state.put(new Coord(500,0), 1);
		
		queue = new LinkedList<Coord>();
		queue.add(new Coord(500,0));
		
		
		while(queue.size() > 0) {
			Coord cur = queue.poll();
			if(cur.y > maxY)
				continue;
			
			if(state.getOrDefault(cur, 0) != 0) {
				//push water downwards
				if(state.get(cur) == 1 && !state.containsKey(cur.sum(new Coord(0,1)))) {
					queueIfNecessary(cur.sum(Coord.DOWN),1);
				}
				//check sideways to see if water is properly bounded to turn still
				if(state.get(cur) == 1) {
					boolean supported = true;
					for(int leftX = cur.x; leftX >= minX; leftX--) {
						Coord check = new Coord(leftX,cur.y);
						if(state.getOrDefault(check, -1) == 0) {
							//bounded on left - break
							break;
						}
						//last comparison is below check, so move check to block below check
						check = check.sum(Coord.DOWN);
						if(state.getOrDefault(check, -1) != 0 && state.getOrDefault(check, -1) != 2) {
							//not safely supported - mark and break
							supported = false;
							break;
						}
					}
					for(int rightX = cur.x; rightX <= maxX; rightX++) {
						Coord check = new Coord(rightX,cur.y);
						if(state.getOrDefault(check, -1) == 0) {
							//bounded on left - break
							break;
						}
						//last comparison is below check, so move check to block below check
						check = check.sum(Coord.DOWN);
						if(state.getOrDefault(check, -1) != 0 && state.getOrDefault(check, -1) != 2) {
							//not safely supported - mark and break
							supported = false;
							break;
						}
					}
					//if supported, change from flowing to still
					if(supported)
						queueIfNecessary(cur,2);
				}
				
				if(state.get(cur) == 1 && (state.getOrDefault(cur.sum(new Coord(0,1)), -1) == 2 || state.getOrDefault(cur.sum(new Coord(0,1)), -1) == 0)) { 
					//spread flowing left and right if possible
					queueIfNecessary(cur.sum(Coord.RIGHT),1);
					queueIfNecessary(cur.sum(Coord.LEFT),1);
				}
				
				if(state.get(cur) == 2) {
					//spread still left and right if possible
					queueIfNecessary(cur.sum(Coord.RIGHT),2);
					queueIfNecessary(cur.sum(Coord.LEFT),2);
				}
			}	
		}
		
		int totalWater = 0;
		for(Coord c : state.keySet()) {
			if(c.x >= minX && c.x <= maxX && c.y >= minY && c.y <= maxY)
				if(state.get(c) == 1 || state.get(c) == 2)
					totalWater++;
		}
		return Integer.toString(totalWater);
	}
	
	public void queueIfNecessary(Coord c, int key) { 
		if(c.y > maxY) {
			return;
		}
		if(state.getOrDefault(c, -1) == 0)
			return;
		if(state.getOrDefault(c, -1) != key) {
			//push neighbors
			for(Coord d : c.directNeighbors())
				queue.add(d);
			//push self
			queue.add(c);
		}
		//update map
		state.put(c, key);
	}
	
	static LinkedList<Coord> queue;

	@Override
	public String part2() {
		state = new HashMap<Coord,Integer>();
		//state indexes: 0 - clay
		//1 - flowing water
		//2 - still water
		//not present (no key) - air
		
		for(String line : input.split("\n")) {
			String[] parts = line.split(", ");
			String[] firstHalf = parts[0].split("=");
			String[] secondHalf = parts[1].split("=|\\.\\.");
			if(firstHalf[0].equals("x")) {
				int x = Integer.parseInt(firstHalf[1]);
				int min = Integer.parseInt(secondHalf[1]);
				int max = Integer.parseInt(secondHalf[2]);
				for(int inY = min; inY <= max; inY++) {
					state.put(new Coord(x,inY),0);
				}
			}
			else {
				int y = Integer.parseInt(firstHalf[1]);
				int min = Integer.parseInt(secondHalf[1]);
				int max = Integer.parseInt(secondHalf[2]);
				for(int inX = min; inX <= max; inX++) {
					state.put(new Coord(inX,y),0);
				}
			}
		}
		
		minX = state.keySet().stream().mapToInt(x -> x.x).min().getAsInt();
		maxX = state.keySet().stream().mapToInt(x -> x.x).max().getAsInt();
		minY = state.keySet().stream().mapToInt(x -> x.y).min().getAsInt();
		maxY = state.keySet().stream().mapToInt(x -> x.y).max().getAsInt();
		//offset minX and maxX by one to account for potential water flowing one past the side of the furthest 'bin'
		minX--;
		maxX++;
		
		state.put(new Coord(500,0), 1);
		
		queue = new LinkedList<Coord>();
		queue.add(new Coord(500,0));
		
		
		while(queue.size() > 0) {
			Coord cur = queue.poll();
			if(cur.y > maxY)
				continue;
			
			if(state.getOrDefault(cur, 0) != 0) {
				//push water downwards
				if(state.get(cur) == 1 && !state.containsKey(cur.sum(new Coord(0,1)))) {
					queueIfNecessary(cur.sum(Coord.DOWN),1);
				}
				//check sideways to see if water is properly bounded to turn still
				if(state.get(cur) == 1) {
					boolean supported = true;
					for(int leftX = cur.x; leftX >= minX; leftX--) {
						Coord check = new Coord(leftX,cur.y);
						if(state.getOrDefault(check, -1) == 0) {
							//bounded on left - break
							break;
						}
						//last comparison is below check, so move check to block below check
						check = check.sum(Coord.DOWN);
						if(state.getOrDefault(check, -1) != 0 && state.getOrDefault(check, -1) != 2) {
							//not safely supported - mark and break
							supported = false;
							break;
						}
					}
					for(int rightX = cur.x; rightX <= maxX; rightX++) {
						Coord check = new Coord(rightX,cur.y);
						if(state.getOrDefault(check, -1) == 0) {
							//bounded on left - break
							break;
						}
						//last comparison is below check, so move check to block below check
						check = check.sum(Coord.DOWN);
						if(state.getOrDefault(check, -1) != 0 && state.getOrDefault(check, -1) != 2) {
							//not safely supported - mark and break
							supported = false;
							break;
						}
					}
					//if supported, change from flowing to still
					if(supported)
						queueIfNecessary(cur,2);
				}
				
				if(state.get(cur) == 1 && (state.getOrDefault(cur.sum(new Coord(0,1)), -1) == 2 || state.getOrDefault(cur.sum(new Coord(0,1)), -1) == 0)) { 
					//spread flowing left and right if possible
					queueIfNecessary(cur.sum(Coord.RIGHT),1);
					queueIfNecessary(cur.sum(Coord.LEFT),1);
				}
				
				if(state.get(cur) == 2) {
					//spread still left and right if possible
					queueIfNecessary(cur.sum(Coord.RIGHT),2);
					queueIfNecessary(cur.sum(Coord.LEFT),2);
				}
			}	
		}
		
		int totalWater = 0;
		for(Coord c : state.keySet()) {
			if(c.x >= minX && c.x <= maxX && c.y >= minY && c.y <= maxY)
				if(state.get(c) == 2)
					totalWater++;
		}
		return Integer.toString(totalWater);
	}

	public static void main(String[] args) {
		input = Input.fetchInput(2018,17);
		DayRunner.run(new Day17());
	}

}
