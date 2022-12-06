package advent.aoc2017;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import advent.utilities.general.Coord3;
import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

public class Day20 implements IDay {

	static String input;
	
	@Override
	public String part1() {
		//store particle as list of list of coord3s
		//each coord3 list stores 3 coord3s - position, velocity, acceleration
		ArrayList<ArrayList<Coord3>> particles = new ArrayList<ArrayList<Coord3>>();
		for(String s : input.split("\n")) {
			String[] parts = s.split(", ");
			ArrayList<Coord3> particle = new ArrayList<Coord3>();
			String[] posParts = parts[0].split(",");
			int x = Integer.parseInt(posParts[0].substring(3));
			int y = Integer.parseInt(posParts[1]);
			int z = Integer.parseInt(posParts[2].substring(0,posParts[2].length() - 1));
			Coord3 position = new Coord3(x,y,z);
			particle.add(position);
			String[] velParts = parts[1].split(",");
			x = Integer.parseInt(velParts[0].substring(3));
			y = Integer.parseInt(velParts[1]);
			z = Integer.parseInt(velParts[2].substring(0,velParts[2].length() - 1));
			Coord3 velocity = new Coord3(x,y,z);
			particle.add(velocity);
			String[] accParts = parts[2].split(",");
			x = Integer.parseInt(accParts[0].substring(3));
			y = Integer.parseInt(accParts[1]);
			z = Integer.parseInt(accParts[2].substring(0,accParts[2].length() - 1));
			Coord3 acceleration = new Coord3(x,y,z);
			particle.add(acceleration);
			particles.add(particle);
		}
		ArrayList<ArrayList<Coord3>> sortedParticles = new ArrayList<ArrayList<Coord3>>(particles);
		
		//if we sort the particles list by increasing (absolute) acceleration magnitude, then increasing (absolute) velocity magnitude, then start distance from (0,0,0), index 0 will be our answer
		Collections.sort(sortedParticles, new Comparator<ArrayList<Coord3>>() {

			@Override
			public int compare(ArrayList<Coord3> o1, ArrayList<Coord3> o2) {
				//dist from 0,0,0 for acceleration and velocity is effective magnitude
				Coord3 zero = new Coord3(0,0,0);
				int acc1 = o1.get(2).dist(zero);
				int acc2 = o2.get(2).dist(zero);
				if(acc1 != acc2) {
					return Integer.compare(acc1, acc2);
				}
				int vel1 = o1.get(1).dist(zero);
				int vel2 = o2.get(1).dist(zero);
				if(vel1 != vel2) {
					return Integer.compare(vel1, vel2);
				}
				return Integer.compare(o1.get(0).dist(zero), o2.get(0).dist(zero));
			}
			});
		
		return Integer.toString(particles.indexOf(sortedParticles.get(0)));
	}

	@Override
	public String part2() {
		//store particle as list of list of coord3s
		//each coord3 list stores 3 coord3s - position, velocity, acceleration
		ArrayList<ArrayList<Coord3>> particles = new ArrayList<ArrayList<Coord3>>();
		for(String s : input.split("\n")) {
			String[] parts = s.split(", ");
			ArrayList<Coord3> particle = new ArrayList<Coord3>();
			String[] posParts = parts[0].split(",");
			int x = Integer.parseInt(posParts[0].substring(3));
			int y = Integer.parseInt(posParts[1]);
			int z = Integer.parseInt(posParts[2].substring(0,posParts[2].length() - 1));
			Coord3 position = new Coord3(x,y,z);
			particle.add(position);
			String[] velParts = parts[1].split(",");
			x = Integer.parseInt(velParts[0].substring(3));
			y = Integer.parseInt(velParts[1]);
			z = Integer.parseInt(velParts[2].substring(0,velParts[2].length() - 1));
			Coord3 velocity = new Coord3(x,y,z);
			particle.add(velocity);
			String[] accParts = parts[2].split(",");
			x = Integer.parseInt(accParts[0].substring(3));
			y = Integer.parseInt(accParts[1]);
			z = Integer.parseInt(accParts[2].substring(0,accParts[2].length() - 1));
			Coord3 acceleration = new Coord3(x,y,z);
			particle.add(acceleration);
			particles.add(particle);
		}
		
		//iterate over a bunch of iterations, hope collisions resolve
		//if it spits out the wrong answer, increase the maximum value of x, but be aware of increased runtime
		for(int x = 0; x < 1000; x++) {
			for(ArrayList<Coord3> p : particles) {
				p.get(1).sumSelf(p.get(2));
				p.get(0).sumSelf(p.get(1));
			}
			//build list of currently colliding particles to remove
			//this avoids removing two particles colliding, only for a third to be present at that same location later in the list
			ArrayList<ArrayList<Coord3>> removals = new ArrayList<ArrayList<Coord3>>();
			for(int i = 0; i < particles.size(); i++) {
				for(int j = i+1; j < particles.size(); j++) {
					if(particles.get(i).get(0).equals(particles.get(j).get(0))) {
						removals.add(particles.get(i));
						removals.add(particles.get(j));
					}
				}
			}
			particles.removeAll(removals);
		}
		
		return Integer.toString(particles.size());
				
	}

	public static void main(String[] args) {
		input = Input.fetchInput(2017,20);
		DayRunner.run(new Day20());
	}

}
