package advent.aoc2019;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

import advent.utilities.general.Coord;
import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

public class Day10 implements IDay{
	
	static String input;
	
	static int maxX = 0;
	static int maxY = 0;
	
	static ArrayList<Coord> asteroidPositions = new ArrayList<Coord>();
	
	static Coord bestCoord = new Coord();

	@Override
	public String part1() {
		int y = 0;
		
		for(String line : input.split("\n")) {
			for(int x = 0; x < line.length(); x++) {
				if(line.charAt(x) == '#') {
					asteroidPositions.add(new Coord(x,y));
					if(x > maxX)
						maxX = x;
				}
			}
			y++;
		}
		maxY = y+1;
		maxX++;
		int bestScore = 0;
		for(Coord current : asteroidPositions) {
			//create new array of asteroid positions for specific location
			ArrayList<Coord> stationVisibles = new ArrayList<Coord>(asteroidPositions);
			//create queue that sorts by distance from current position, shortest first
			PriorityQueue<Coord> queue = new PriorityQueue<Coord>(new Comparator<Coord>() {
			
				@Override
				public int compare(Coord o1, Coord o2) {
					if(o1.dist(current) == o2.dist(current)) {
						return 0;
					} else if(o1.dist(current) < o2.dist(current)) {
						return -1;
					} else {
						return 1;
					}
				}
				
			});
			
			//take starting position out of location array
			stationVisibles.remove(current);
			//enqueue all asteroids
			for(Coord c : stationVisibles) {
				queue.add(c);
			}

			//evaluation loop
			while(queue.size() > 0) {
				//remove top of queue
				Coord eval = queue.poll();
				//if currently evaluating location has not already been removed from visibles by previous iteration
				if(stationVisibles.contains(eval)) {
					//get slope between "origin" and current evaluation location
					Coord slope = slope(current,eval);
					//position coord
					Coord obscure = eval.copy();
					while(true) {
						//iterate position by slope 
						obscure.sumSelf(slope);
						if(inBounds(obscure)) {
							if(stationVisibles.contains(obscure)) {
								//if we haven't left the map yet and there's a coord at the point being examined,
								//it's obscured by the current evaluation point, so remove it
								stationVisibles.remove(obscure);
							}
						} else {
							//iterating slope has taken us out of bounds, this eval location is done
							break;
						}
					}
				}
			}
			
			//once all potential obscurings have been calculated, score this starting location
			if(stationVisibles.size() > bestScore) {
				bestScore = stationVisibles.size();
				bestCoord = current;
			}
		}
		//return score of best location
		return Integer.toString(bestScore);
	}

	@Override
	public String part2() {
		//now that we have a final location, we just use the overall positions array
		asteroidPositions.remove(bestCoord);
		//buffer of destroyed asteroids
		ArrayList<Coord> destroyeds = new ArrayList<Coord>();
		while(destroyeds.size() < 200) {
			//each time, sort so that list ends up in ascending order of angle from vertical,
			//then ascending order of distance from location
			Collections.sort(asteroidPositions, new Comparator<Coord>() {
				public int compare(Coord o1, Coord o2) {
					double angle1 = clockwiseAngleAdjusted(bestCoord,o1);
					double angle2 = clockwiseAngleAdjusted(bestCoord,o2);
					if(angle1 == angle2) {
						if(o1.dist(bestCoord) == o2.dist(bestCoord)) {
							return 0;
						} else if(o1.dist(bestCoord) < o2.dist(bestCoord)) {
							return -1;
						} else {
							return 1;
						}
					} else if(angle1 < angle2) {
						return -1;
					} else {
						return 1;
					}
					
				}
			});
			//get all locations currently unobstructed
			ArrayList<Coord> currentUnobstructed = generateUnobstructed(asteroidPositions,bestCoord);
			//add each to destroyed list, remove each from remaining list
			for(Coord c : currentUnobstructed) {
				destroyeds.add(c);
				asteroidPositions.remove(c);
			}
		}
		//after we've destroyed 200+ asteroids, retrieve location of 200th
		Coord twoHundred = destroyeds.get(199);
		return Integer.toString(twoHundred.x * 100 + twoHundred.y);
	}

	//iterates through asteroid list to determine which ones are currently visible from origin
	public static ArrayList<Coord> generateUnobstructed(ArrayList<Coord> asteroids,Coord origin) {
		//if the list is sorted in angle order and then distance to origin order, then
		//we can generate proper unobstructed list by adding tiles that do not match existing slopes within the list
		//if a tile with said slope is already in the list, all others will be behind it and obscured from sight
		ArrayList<Coord> unobstructeds = new ArrayList<Coord>();
		outer:
		for(Coord c : asteroids) {
			for(Coord d : unobstructeds) {
				if(slope(origin,c).equals(slope(origin,d))) {
					continue outer;
				}
			}
			unobstructeds.add(c);
		}
		return unobstructeds;
	}
	
	//uses vector dot-product/cross-product calculation formula to determine a (slope) vector's angle
	//relative to a vertical upwards vector. 
	public static double clockwiseAngleAdjusted(Coord origin, Coord point) {
		Coord upVector = new Coord(0,-1);
		Coord slope = slope(origin,point);
		
		int dot = (upVector.x * slope.x) + (upVector.y * slope.y);
		int cross = (upVector.x * slope.y) - (slope.x * upVector.y);
		
		double angle = Math.atan2(cross, dot)/Math.PI;
		//adjust so we're always positive distance (0-2pi instead of -pi-pi)
		if(Math.signum(angle) == -1)
			angle += 2;
		return angle;
	}
	
	//returns the "slope" from the origin to the point
	//including reducing the slope to its least divisible form using the GCF
	public static Coord slope(Coord origin, Coord point) {
		Coord slope = point.sum(new Coord(-origin.x, -origin.y));
		int gcf = gcf(Math.abs(slope.x),Math.abs(slope.y));
		if(gcf != 1) {
			slope = new Coord(slope.x / gcf, slope.y / gcf);
		}
		return slope.copy();
	}
	
	//determines if coordinate is within maximum map bounds
	public static boolean inBounds(Coord c) {
		return c.x > -1 && c.y > -1 && c.x <= maxX && c.y <= maxY;
	}
	
	//basic greatest common factor of two inputs
	public static int gcf(int a, int b) {
		if(a == 0 || b == 0) {
			return (a == 0 ? b : a);
		}
		ArrayList<Integer> aFactors = new ArrayList<Integer>();
		for(int i = 1; i <= a; i++) {
			if(a % i == 0)
				aFactors.add(i);
		}
		ArrayList<Integer> bFactors = new ArrayList<Integer>();
		for(int i = 1; i <= b; i++) {
			if(b % i == 0)
				bFactors.add(i);
		}
		ArrayList<Integer> cfs = new ArrayList<Integer>();
		for(int i : (aFactors.size() > bFactors.size() ? bFactors : aFactors)) {
			if((aFactors.size() > bFactors.size() ? aFactors : bFactors).contains(i))
				cfs.add(i);
		}
		Collections.sort(cfs);
		return cfs.get(cfs.size() - 1);
	}

	public static void main(String[] args) {
		input = Input.fetchInput(2019,10);
		DayRunner.run(new Day10());
	}
}
