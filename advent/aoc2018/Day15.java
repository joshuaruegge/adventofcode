package advent.aoc2018;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

import advent.utilities.general.Coord;
import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;
import advent.utilities.utils2018.Unit;

public class Day15 implements IDay {

	static String input;

	static HashSet<Coord> walls;
	static ArrayList<Unit> units;
	
	static Comparator<Unit> positionSort = new Comparator<Unit>() {
		@Override
		public int compare(Unit o1, Unit o2) {
			return o1.position.compareTo(o2.position);
		}		
	};
	
	@Override
	public String part1() {
		ArrayList<Unit> goblins = new ArrayList<Unit>(); 
		ArrayList<Unit> elves = new ArrayList<Unit>();
		walls = new HashSet<Coord>();
		int inY = 0;
		for(String s : input.split("\n")) {
			for(int x = 0; x < s.length(); x++) {
				char c = s.charAt(x);
				if(c != '.') {
					if(c == '#')
						walls.add(new Coord(x,inY));
					else {
						Unit unit = new Unit(new Coord(x,inY),200,3);
						if(c == 'G')
							goblins.add(unit);
						else
							elves.add(unit);
					}
				}
			}
			inY++;
		}
		
		int turnCounter = 0;
		units = new ArrayList<Unit>();
		units.addAll(goblins);
		units.addAll(elves);
		
		//overall turn structure
		do {
			//build turn order	
			Collections.sort(units, positionSort);
			for(int unitIndex = 0; unitIndex < units.size(); unitIndex++) {
				Unit currentUnit = units.get(unitIndex);
				Coord unitPos = currentUnit.position;
				ArrayList<Unit> enemyList = (goblins.contains(currentUnit) ? elves : goblins);
				//determine if adjacent to a target 
				boolean alreadyAdjacent = false;
				for(Coord c : unitPos.directNeighbors()) {
					if(containsPos(enemyList,c)) {
						alreadyAdjacent = true;
						break;
					}
				}
				
				//if not adjacent, find and move towards target
				if(!alreadyAdjacent) {
					//build list of spaces adjacent to targets
					ArrayList<Coord> adjacents = new ArrayList<Coord>();
					for(Unit t : enemyList) {
						for(Coord c : t.position.directNeighbors()) {
							if(!walls.contains(unitPos) && !containsPos(units,c))
								adjacents.add(c);
						}
					}
					Collections.sort(adjacents);
			
					Coord dest = null;
					int shortestDistance = Integer.MAX_VALUE;
					for(Coord c : adjacents) {
						ArrayList<Coord> dist = aStar(unitPos,c);
						if(dist != null) {
							if(dist.size() < shortestDistance) {
								shortestDistance = dist.size();
								dest = c;
							}
						}
					}
					
					if(dest == null)
						continue;
					
					
					Coord move = null;
					int shortestToMove = Integer.MAX_VALUE;
					//determine which space adjacent to current is closest to dest
					for(Coord c : unitPos.directNeighbors()) {
						if(walls.contains(c) || containsPos(units,c))
							continue;
						ArrayList<Coord> path = aStar(c,dest);
						if(path != null && path.size() < shortestToMove) {
							shortestToMove = path.size();
							move = c;
						}
					}
										
					//update unit position
					currentUnit.position = move;
					unitPos = move;
					
					//recheck enemy adjancency (in case move put us adjacent to enemy)
					for(Coord c : unitPos.directNeighbors()) {
						if(containsPos(enemyList,c)) {
							alreadyAdjacent = true;
							break;
						}
					}	
				}
				
				if(alreadyAdjacent) {
					Unit target = null;
					int lowestHealth = Integer.MAX_VALUE;
					for(Coord c : unitPos.directNeighbors()) {
						Unit t = findPos(enemyList,c);
						if(t != null) {
							if(t.hp < lowestHealth) {
								target = t;
								lowestHealth = t.hp;
							}
						}
					}
					
					//attack target
					target.hp -= currentUnit.atk;
					
					//prune if dead
					if(target.hp <= 0) {
						if(units.indexOf(target) <= unitIndex)
							unitIndex--;
						units.remove(target);	
						enemyList.remove(target);
					}
					
					//end turn if that was a final kill
					if(goblins.size() == 0 || elves.size() == 0) {
						return Integer.toString(turnCounter * sumHp(units));
					}		
				}
			}
			turnCounter++;
		} while(goblins.size() > 0 && elves.size() > 0);
		
		return Integer.toString(turnCounter * sumHp(units));
	}

	//a* pathfinding from start to end
	public ArrayList<Coord> aStar(Coord start, Coord end) {
		HashMap<Coord,Coord> parent = new HashMap<Coord,Coord>();
		HashMap<Coord,Integer> gCost = new HashMap<Coord,Integer>();
		gCost.put(start, 0);
		LinkedList<Coord> open = new LinkedList<Coord>();
		open.add(start);
		while(open.size() > 0) {
			Coord cur = open.poll();
			if(cur.equals(end)) {
				ArrayList<Coord> path = new ArrayList<Coord>();
				Coord pathNode = cur;
				while(parent.containsKey(pathNode)) {
					path.add(0,pathNode);
					pathNode = parent.get(pathNode);
				}
				return path;
			}
			for(Coord c : cur.directNeighbors()) {
				if(walls.contains(c) || containsPos(units,c))
					continue;
				int possG = gCost.get(cur) + 1;
				if(possG < gCost.getOrDefault(c, Integer.MAX_VALUE)) {
					parent.put(c, cur);
					gCost.put(c,possG);
					open.add(c);
				}
			}
		}
		//null to signify no path
		return null;
 	}

	//helper method - determines if the collection contains a unit with position p
	public boolean containsPos(Collection<Unit> c, Coord p) {
		for(Unit u : c) {
			if(u.position.equals(p))
				return true;
		}
		return false;
	}
	
	public Unit findPos(Collection<Unit> c, Coord p) {
		for(Unit u : c)
			if(u.position.equals(p))
				return u;
		return null;
	}
	
	public int sumHp(ArrayList<Unit> a) {
		int hp = 0;
		for(Unit u : a)
			hp += u.hp;
		return hp;
	}
	
	@Override
	public String part2() {
		//because numerous elves died last time, start power pretty high
		int weaponPower = 19;
		powerIncrement:
		while(true) {
			weaponPower++;
			ArrayList<Unit> goblins = new ArrayList<Unit>(); 
			ArrayList<Unit> elves = new ArrayList<Unit>();
			walls = new HashSet<Coord>();
			int inY = 0;
			for(String s : input.split("\n")) {
				for(int x = 0; x < s.length(); x++) {
					char c = s.charAt(x);
					if(c != '.') {
						if(c == '#')
							walls.add(new Coord(x,inY));
						else {
							Unit unit = new Unit(new Coord(x,inY),200,3);
							if(c == 'G')
								goblins.add(unit);
							else {
								unit.atk = weaponPower;
								elves.add(unit);
							}
						}
					}
				}
				inY++;
			}
			
			int turnCounter = 0;
			units = new ArrayList<Unit>();
			units.addAll(goblins);
			units.addAll(elves);
			
			//overall turn structure
			do {
				//build turn order	
				Collections.sort(units, positionSort);
				for(int unitIndex = 0; unitIndex < units.size(); unitIndex++) {
					Unit currentUnit = units.get(unitIndex);
					Coord unitPos = currentUnit.position;
					ArrayList<Unit> enemyList = (goblins.contains(currentUnit) ? elves : goblins);
					//determine if adjacent to a target 
					boolean alreadyAdjacent = false;
					for(Coord c : unitPos.directNeighbors()) {
						if(containsPos(enemyList,c)) {
							alreadyAdjacent = true;
							break;
						}
					}
					
					//if not adjacent, find and move towards target
					if(!alreadyAdjacent) {
						//build list of spaces adjacent to targets
						ArrayList<Coord> adjacents = new ArrayList<Coord>();
						for(Unit t : enemyList) {
							for(Coord c : t.position.directNeighbors()) {
								if(!walls.contains(unitPos) && !containsPos(units,c))
									adjacents.add(c);
							}
						}
						Collections.sort(adjacents);
				
						Coord dest = null;
						int shortestDistance = Integer.MAX_VALUE;
						for(Coord c : adjacents) {
							ArrayList<Coord> dist = aStar(unitPos,c);
							if(dist != null) {
								if(dist.size() < shortestDistance) {
									shortestDistance = dist.size();
									dest = c;
								}
							}
						}
						
						if(dest == null)
							continue;
						
						
						Coord move = null;
						int shortestToMove = Integer.MAX_VALUE;
						//determine which space adjacent to current is closest to dest
						for(Coord c : unitPos.directNeighbors()) {
							if(walls.contains(c) || containsPos(units,c))
								continue;
							ArrayList<Coord> path = aStar(c,dest);
							if(path != null && path.size() < shortestToMove) {
								shortestToMove = path.size();
								move = c;
							}
						}
											
						//update unit position
						currentUnit.position = move;
						unitPos = move;
						
						//recheck enemy adjancency (in case move put us adjacent to enemy)
						for(Coord c : unitPos.directNeighbors()) {
							if(containsPos(enemyList,c)) {
								alreadyAdjacent = true;
								break;
							}
						}	
					}
					
					if(alreadyAdjacent) {
						Unit target = null;
						int lowestHealth = Integer.MAX_VALUE;
						for(Coord c : unitPos.directNeighbors()) {
							Unit t = findPos(enemyList,c);
							if(t != null) {
								if(t.hp < lowestHealth) {
									target = t;
									lowestHealth = t.hp;
								}
							}
						}
						
						//attack target
						target.hp -= currentUnit.atk;
						
						//if elf died, move to next weapon damage
						if(target.hp <= 0 && elves.contains(target))
							continue powerIncrement;
						
						//prune if dead
						if(target.hp <= 0) {
							if(units.indexOf(target) <= unitIndex)
								unitIndex--;
							units.remove(target);	
							enemyList.remove(target);
						}
						
						//end turn if that was a final kill
						if(goblins.size() == 0 || elves.size() == 0) {
							return Integer.toString(turnCounter * sumHp(units));
						}		
					}
				}
				turnCounter++;
			} while(goblins.size() > 0 && elves.size() > 0);
			
			return Integer.toString(turnCounter * sumHp(units));
		}
	}

	public static void main(String[] args) {
		input = Input.fetchInput(2018,15);
		DayRunner.run(new Day15());
	}

}
