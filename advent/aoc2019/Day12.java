package advent.aoc2019;

import java.util.ArrayList;
import java.util.Scanner;

import advent.utilities.general.Coord3;
import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;

public class Day12 implements IDay{

	static String input = "<x=10, y=15, z=7>\r\n"
			+ "<x=15, y=10, z=0>\r\n"
			+ "<x=20, y=12, z=3>\r\n"
			+ "<x=0, y=-3, z=13>";
	static String input2 = "<x=-8, y=-10, z=0>\r\n"
			+ "<x=5, y=5, z=10>\r\n"
			+ "<x=2, y=-7, z=3>\r\n"
			+ "<x=9, y=-8, z=-3>";
	
	public static void main(String[] args) {
		DayRunner.run(new Day12());
	}
	
	
	//compares whether two 2-dimensional coordinate arrays contain the same values
	//for all instances of a given position(0 for x, 1 for y, 2 for z)
	public static boolean positionMatch(ArrayList<ArrayList<Coord3>> a, ArrayList<ArrayList<Coord3>> b,int position) {
		if(a.size() != b.size())
			return false;
		for(int i = 0; i < a.size(); i++) {
			for(int j = 0; j < a.get(i).size(); j++) {
				if(position == 0) {
					if(a.get(i).get(j).x != b.get(i).get(j).x)
						return false;
				} else if(position == 1) {
					if(a.get(i).get(j).y != b.get(i).get(j).y)
						return false;
				} else {
					if(a.get(i).get(j).z != b.get(i).get(j).z)
						return false;
				}
				
			}
				
		}
		return true;
	}
	
	//fully copies a 2-dimensional coordinate array
	public static ArrayList<ArrayList<Coord3>> deepCopy(ArrayList<ArrayList<Coord3>> a) {
		ArrayList<ArrayList<Coord3>> ret = new ArrayList<ArrayList<Coord3>>();
		for(ArrayList<Coord3> b : a) {
			ArrayList<Coord3> temp = new ArrayList<Coord3>();
			for(Coord3 c : b) {
				temp.add(c.copy());
			}
			ret.add(temp);
		}
		return ret;
	}


	@Override
	public String part1() {
		Scanner scan = new Scanner(input);
		//we're storing system states as a list of list of coordinates - each internal list contains a position coordinate and a velocity coordinate
		ArrayList<ArrayList<Coord3>> bodies = new ArrayList<ArrayList<Coord3>>();
		while(scan.hasNextLine()) {
			String[] parts = scan.nextLine().split(", ");
			ArrayList<Coord3> temp = new ArrayList<Coord3>();
			temp.add(new Coord3(Integer.parseInt(parts[0].substring(parts[0].indexOf("=") + 1)),Integer.parseInt(parts[1].substring(parts[1].indexOf("=") + 1)),Integer.parseInt(parts[2].substring(parts[2].indexOf("=") + 1,parts[2].length() - 1))));
			temp.add(new Coord3());
			bodies.add(temp);
		}
		
		for(int cycle = 0; cycle < 1000; cycle++) {
			for(ArrayList<Coord3> c : bodies) {
				for(ArrayList<Coord3> d : bodies) {
					//apply velocity operations
					if(!c.equals(d)) {
						c.get(1).x += (Math.signum(d.get(0).x - c.get(0).x));
						c.get(1).y += (Math.signum(d.get(0).y - c.get(0).y));
						c.get(1).z += (Math.signum(d.get(0).z - c.get(0).z));
					}
				}
			}
			//apply velocities
			for(ArrayList<Coord3> c : bodies) {
				c.get(0).sumSelf(c.get(1));
			}
			
		}
		int energy = 0;
		for(ArrayList<Coord3> c : bodies) {
			int pot = Math.abs(c.get(0).x) + Math.abs(c.get(0).y) + Math.abs(c.get(0).z);
			int kin = Math.abs(c.get(1).x) + Math.abs(c.get(1).y) + Math.abs(c.get(1).z);
			energy += pot * kin;
		}
		return Integer.toString(energy);
		
	}


	@Override
	public String part2() {
		Scanner scan = new Scanner(input);
		ArrayList<ArrayList<Coord3>> bodies = new ArrayList<ArrayList<Coord3>>();
		while(scan.hasNextLine()) {
			String[] parts = scan.nextLine().split(", ");
			ArrayList<Coord3> temp = new ArrayList<Coord3>();
			temp.add(new Coord3(Integer.parseInt(parts[0].substring(parts[0].indexOf("=") + 1)),Integer.parseInt(parts[1].substring(parts[1].indexOf("=") + 1)),Integer.parseInt(parts[2].substring(parts[2].indexOf("=") + 1,parts[2].length() - 1))));
			temp.add(new Coord3());
			bodies.add(temp);
		}
		ArrayList<ArrayList<Coord3>> initialState = deepCopy(bodies);
		
		//because for all operations the x, y, and z coordinates are independent, each coordinate
		//will have its own independent cycle period
		//therefore, the length of a cycle of all 3 variables (full already-seen state) will be
		//the least common multiple of the length of each variable's cycle period
		long xPeriod = 0;
		long yPeriod = 0;
		long zPeriod = 0;
		long counter = 0;
		while(xPeriod == 0 || yPeriod == 0 || zPeriod == 0) {
			for(ArrayList<Coord3> c : bodies) {
				for(ArrayList<Coord3> d : bodies) {
					if(!c.equals(d)) {
						c.get(1).x += (Math.signum(d.get(0).x - c.get(0).x));
						c.get(1).y += (Math.signum(d.get(0).y - c.get(0).y));
						c.get(1).z += (Math.signum(d.get(0).z - c.get(0).z));
					}
				}
			}
			for(ArrayList<Coord3> c : bodies) {
				c.get(0).sumSelf(c.get(1));
			}
			counter++;
			//check if we haven't yet found the length of a variable's period
			//and if we're currently matching the initial position
			if(xPeriod == 0 && positionMatch(initialState,bodies,0)) {
				xPeriod = counter;
			}
			if(yPeriod == 0 && positionMatch(initialState,bodies,1)) {
				yPeriod = counter;
			}
			if(zPeriod == 0 && positionMatch(initialState,bodies,2)) {
				zPeriod = counter;
			}
			
		}
		long lcm = fastLCM(xPeriod,fastLCM(yPeriod,zPeriod));
		return Long.toString(lcm);
	}
	
	//LCM(a,b) = (a*b) / GCD(a,b)
	public long fastLCM(long a, long b) {
		return (a * b)/fastGCD(a,b);
	}
	
	//Euclidean greatest common denominator:
	//gcd(a,b) == gcd(b,a % b)
	//when a % b == 0, gcd = b
	//so simply iterate through until a % b equals zero, then return b
	//this should have a check to make sure a and b are both at least 1, but
	//that condition is never encountered in this code
	public long fastGCD(long a, long b) {
		long remainder = 0;
		do {
			remainder = a % b;
			a = b;
			b = remainder;
		} while (b != 0);
		return a;
	}
}
