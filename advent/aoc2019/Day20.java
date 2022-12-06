package advent.aoc2019;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;

import advent.utilities.general.*;

public class Day20 implements IDay {

	static String input;
	
	static HashSet<Coord> pathPositions = new HashSet<Coord>();
	static HashMap<Coord,Coord> portals = new HashMap<Coord,Coord>();
	static ArrayList<String> inputMap = new ArrayList<String>();
	
	static Coord start, end;
	
	@Override
	public String part1() {
		//keep track of portal identifiers as they are encountered, to assign them a numeric value based on index in this list
		ArrayList<String> portalCodes = new ArrayList<String>();
		
		//parse input into String arraylist to get a feel for x and y bounds
		inputMap = new ArrayList<String>(Arrays.asList(input.split("\n")));
		
		//we replace locations of matching portals with an ASCII character within the lowercase range to later clean them up during the conversion pass
		
		//top-down portal code replace
		for(int i = 0; i < inputMap.size() - 2; i++) {
			for(int j = 0; j < inputMap.get(i).length() - 2; j++) {
				char a = inputMap.get(i).charAt(j);
				char b = inputMap.get(i+1).charAt(j);
				char c = inputMap.get(i+2).charAt(j);
				//letter, letter, path
				if(a > 64 && a < 91 && b > 64 && b < 91 && c == '.') {
					String code = a + "" + b;
					if(!portalCodes.contains(code))
						portalCodes.add(code);
					//just replace path, we'll take out uppercase letters on a later pass
					String replace = inputMap.get(i+2).substring(0,j) + (char) (portalCodes.indexOf(code) + 91) + inputMap.get(i+2).substring(j+1);
					inputMap.set(i+2, replace);
					
				}
			}
		}
		
		//bottom-top replace
		for(int i = 2; i < inputMap.size(); i++) {
			for(int j = 0; j < inputMap.get(i).length() - 2; j++) {
				char c = inputMap.get(i).charAt(j);
				char b = inputMap.get(i-1).charAt(j);
				char a = inputMap.get(i-2).charAt(j);
				//path, letter, letter
				if(a == '.' && b > 64 && b < 91 && c > 64 && c < 91) {
					String code = b + "" + c;
					if(!portalCodes.contains(code))
						portalCodes.add(code);
					String replace = inputMap.get(i-2).substring(0,j) + (char) (portalCodes.indexOf(code) + 91) + inputMap.get(i-2).substring(j+1);
					inputMap.set(i-2,replace);
					
				}
			}
		}
		
		//left-right
		for(int i = 0; i < inputMap.size(); i++) {
			for(int j = 0; j < inputMap.get(i).length() - 2; j++) {
				char a = inputMap.get(i).charAt(j);
				char b = inputMap.get(i).charAt(j+1);
				char c = inputMap.get(i).charAt(j+2);
				//letter, letter, path
				if(c == '.' && a > 64 && a < 91 && b > 64 && b < 91) {
					String code = a + "" + b;
					if(!portalCodes.contains(code))
						portalCodes.add(code);
					String replace = inputMap.get(i).substring(0,j) + "  " + (char) (portalCodes.indexOf(code) + 91) + inputMap.get(i).substring(j+3);
					inputMap.set(i, replace);
					
				}
			}
		}
		
		//right-left
		for(int i = 0; i < inputMap.size(); i++) {
			for(int j = 2; j < inputMap.get(i).length(); j++) {
				char a = inputMap.get(i).charAt(j);
				char b = inputMap.get(i).charAt(j-1);
				char c = inputMap.get(i).charAt(j-2);
				//path, letter, letter
				if(c == '.' && a > 64 && a < 91 && b > 64 && b < 91) {
					String code = b + "" + a;
					if(!portalCodes.contains(code)) {
						portalCodes.add(code);
					}
					String replace = inputMap.get(i).substring(0,j-2) + (char) (portalCodes.indexOf(code) + 91) + "  " + inputMap.get(i).substring(j+1);
					inputMap.set(i, replace);
					
				}
					
			}
		}
		
		//remove remaining uppercase letters
		for(int i = 0; i < inputMap.size(); i++) {
			inputMap.set(i, inputMap.get(i).replaceAll("[A-Z]", " "));
		}
		
		//trim whitespace now that portals are gone
		for(int i = 0; i < inputMap.size(); i++) {
			String trim = inputMap.get(i).trim();
			if(trim.equals("")) {
				inputMap.remove(i);
				i--;
				continue;
			}
			inputMap.set(i, trim);
		}
		
		//add all portals to a temporary map
		HashMap<Coord,Character> tempPortals = new HashMap<Coord,Character>();
		start = null;
		end = null;
		for(int y = 0; y < inputMap.size(); y++) {
			String line = inputMap.get(y);
			for(int x = 0; x < line.length(); x++) {
				char c = line.charAt(x);
				//paths go in path positions
				if(c == '.') {
					pathPositions.add(new Coord(x,y));
				//skip wall and whitespace
				} else if (c == ' ' || c == '#') {
					continue;
				//entrance
				} else if (portalCodes.get(c-91).equals("AA")){
					start = new Coord(x,y);
					pathPositions.add(new Coord(x,y));
				//exit
				} else if (portalCodes.get(c-91).equals("ZZ")) {
					end = new Coord(x,y);
					pathPositions.add(new Coord(x,y));
				//other portal
				} else {
					tempPortals.put(new Coord(x,y), c);
					pathPositions.add(new Coord(x,y));
				}
			}
		}
		
		//now, perform portal matching
		portal:
		while(tempPortals.keySet().size() > 0) {
			for(Coord c : tempPortals.keySet()) {
				char code = tempPortals.get(c);
				for(Coord d : tempPortals.keySet()) {
					//different locations, matching portal code
					if(!c.equals(d) && tempPortals.get(d) == code) {
						//make portals bidirectional just in case
						portals.put(c, d);
						portals.put(d, c);
						tempPortals.remove(c);
						tempPortals.remove(d);
						continue portal;
					}
				}
			}
		}
		
		return Integer.toString(path(start,end));
	}

	@Override
	public String part2() {
		//keep track of portal identifiers as they are encountered, to assign them a numeric value based on index in this list
		ArrayList<String> portalCodes = new ArrayList<String>();
		
		//parse input into String arraylist to get a feel for x and y bounds
		inputMap = new ArrayList<String>(Arrays.asList(input.split("\n")));
		
		//we replace locations of matching portals with an ASCII character within the lowercase range to later clean them up during the conversion pass
		
		//top-down portal code replace
		for(int i = 0; i < inputMap.size() - 2; i++) {
			for(int j = 0; j < inputMap.get(i).length() - 2; j++) {
				char a = inputMap.get(i).charAt(j);
				char b = inputMap.get(i+1).charAt(j);
				char c = inputMap.get(i+2).charAt(j);
				//letter, letter, path
				if(a > 64 && a < 91 && b > 64 && b < 91 && c == '.') {
					String code = a + "" + b;
					if(!portalCodes.contains(code))
						portalCodes.add(code);
					//just replace path, we'll take out uppercase letters on a later pass
					String replace = inputMap.get(i+2).substring(0,j) + (char) (portalCodes.indexOf(code) + 91) + inputMap.get(i+2).substring(j+1);
					inputMap.set(i+2, replace);
					
				}
			}
		}
		
		//bottom-top replace
		for(int i = 2; i < inputMap.size(); i++) {
			for(int j = 0; j < inputMap.get(i).length() - 2; j++) {
				char c = inputMap.get(i).charAt(j);
				char b = inputMap.get(i-1).charAt(j);
				char a = inputMap.get(i-2).charAt(j);
				//path, letter, letter
				if(a == '.' && b > 64 && b < 91 && c > 64 && c < 91) {
					String code = b + "" + c;
					if(!portalCodes.contains(code))
						portalCodes.add(code);
					String replace = inputMap.get(i-2).substring(0,j) + (char) (portalCodes.indexOf(code) + 91) + inputMap.get(i-2).substring(j+1);
					inputMap.set(i-2,replace);
					
				}
			}
		}
		
		//left-right
		for(int i = 0; i < inputMap.size(); i++) {
			for(int j = 0; j < inputMap.get(i).length() - 2; j++) {
				char a = inputMap.get(i).charAt(j);
				char b = inputMap.get(i).charAt(j+1);
				char c = inputMap.get(i).charAt(j+2);
				//letter, letter, path
				if(c == '.' && a > 64 && a < 91 && b > 64 && b < 91) {
					String code = a + "" + b;
					if(!portalCodes.contains(code))
						portalCodes.add(code);
					String replace = inputMap.get(i).substring(0,j) + "  " + (char) (portalCodes.indexOf(code) + 91) + inputMap.get(i).substring(j+3);
					inputMap.set(i, replace);
					
				}
			}
		}
		
		//right-left
		for(int i = 0; i < inputMap.size(); i++) {
			for(int j = 2; j < inputMap.get(i).length(); j++) {
				char a = inputMap.get(i).charAt(j);
				char b = inputMap.get(i).charAt(j-1);
				char c = inputMap.get(i).charAt(j-2);
				//path, letter, letter
				if(c == '.' && a > 64 && a < 91 && b > 64 && b < 91) {
					String code = b + "" + a;
					if(!portalCodes.contains(code)) {
						portalCodes.add(code);
					}
					String replace = inputMap.get(i).substring(0,j-2) + (char) (portalCodes.indexOf(code) + 91) + "  " + inputMap.get(i).substring(j+1);
					inputMap.set(i, replace);
					
				}
					
			}
		}
		
		//remove remaining uppercase letters
		for(int i = 0; i < inputMap.size(); i++) {
			inputMap.set(i, inputMap.get(i).replaceAll("[A-Z]", " "));
		}
		
		//trim whitespace now that portals are gone
		for(int i = 0; i < inputMap.size(); i++) {
			String trim = inputMap.get(i).trim();
			if(trim.equals("")) {
				inputMap.remove(i);
				i--;
				continue;
			}
			inputMap.set(i, trim);
		}
		
		//add all portals to a temporary map
		HashMap<Coord,Character> tempPortals = new HashMap<Coord,Character>();
		start = null;
		end = null;
		for(int y = 0; y < inputMap.size(); y++) {
			String line = inputMap.get(y);
			for(int x = 0; x < line.length(); x++) {
				char c = line.charAt(x);
				//paths go in path positions
				if(c == '.') {
					pathPositions.add(new Coord(x,y));
				//skip wall and whitespace
				} else if (c == ' ' || c == '#') {
					continue;
				//entrance
				} else if (portalCodes.get(c-91).equals("AA")){
					start = new Coord(x,y);
					pathPositions.add(new Coord(x,y));
				//exit
				} else if (portalCodes.get(c-91).equals("ZZ")) {
					end = new Coord(x,y);
					pathPositions.add(new Coord(x,y));
				//other portal
				} else {
					tempPortals.put(new Coord(x,y), c);
					pathPositions.add(new Coord(x,y));
				}
			}
		}
		
		//now, perform portal matching
		portal:
		while(tempPortals.keySet().size() > 0) {
			for(Coord c : tempPortals.keySet()) {
				char code = tempPortals.get(c);
				for(Coord d : tempPortals.keySet()) {
					//different locations, matching portal code
					if(!c.equals(d) && tempPortals.get(d) == code) {
						//make portals bidirectional just in case
						portals.put(c, d);
						portals.put(d, c);
						tempPortals.remove(c);
						tempPortals.remove(d);
						continue portal;
					}
				}
			}
		}
		
		return Integer.toString(path2(new Coord3(start.x,start.y,0), new Coord3(end.x,end.y,0)));
	}

	public static int path(Coord start, Coord end) {
		HashMap<Coord,Integer> gScore = new HashMap<Coord,Integer>();
		gScore.put(start, 0);
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
				return gScore.get(cur);
			}
			//append portal adjacency
			if(portals.containsKey(cur)) {
				Coord dest = portals.get(cur);
				int tentativeG = gScore.get(cur) + 1;
				if(tentativeG < gScore.getOrDefault(dest, Integer.MAX_VALUE)) {
					gScore.put(dest, tentativeG);
					queue.add(dest);
				}
			}
			//normal adjacency
			for(Coord c : cur.directNeighbors()) {
				if(pathPositions.contains(c)) {
					int tentativeG = gScore.get(cur) + 1;
					if(tentativeG < gScore.getOrDefault(c, Integer.MAX_VALUE)) {
						gScore.put(c, tentativeG);
						queue.add(c);
					}
				}
			}
		}
		return -1;
	}
	
	public static int path2(Coord3 start, Coord3 end) {
		HashMap<Coord3,Integer> gScore = new HashMap<Coord3,Integer>();
		gScore.put(start, 0);
		LinkedList<Coord3> queue = new LinkedList<Coord3>();
		queue.add(start);
		while(queue.size() > 0) {
			Coord3 cur = queue.poll();
			
			//no need to ever descend more layers than the total number of portals
			//since portals has each portal mapped in both directions, this means size / 2
			if(cur.z > portals.size() / 2) {
				continue;
			}
			if(cur.equals(end)) {
				return gScore.get(cur);
			}
			
			//portal
			Coord curFlat = new Coord(cur.x,cur.y);
			if(portals.containsKey(curFlat)) {
				Coord destFlat = portals.get(curFlat);
				Coord3 dest = null;
				//if outer portal, travel up unless at top level
				if(outerPortal(curFlat)) {
					if(cur.z > 0) {
						dest = new Coord3(destFlat.x,destFlat.y,cur.z - 1);
					}
				//if inner portal, travel down unless too far
				} else {
					if(cur.z < portals.size() / 2) {
						dest = new Coord3(destFlat.x,destFlat.y,cur.z + 1);
					}
				}
				if(dest != null) {
					int tentativeG = gScore.get(cur) + 1;
					if(tentativeG < gScore.getOrDefault(dest, Integer.MAX_VALUE)) {
						gScore.put(dest, tentativeG);
						queue.add(dest);
					}
				}
			}
			//linear
			for(Coord c : curFlat.directNeighbors()) {
				if(pathPositions.contains(c)) {
					Coord3 dest = new Coord3(c.x,c.y,cur.z);
					int tentativeG = gScore.get(cur) + 1;
					if(tentativeG < gScore.getOrDefault(dest, Integer.MAX_VALUE)) {
						gScore.put(dest, tentativeG);
						queue.add(dest);
					}
				}
			}
		}
		return -1;
	}
	
	public static boolean outerPortal(Coord c) {
		return c.x == 0 || c.x == inputMap.get(0).length() - 1 || c.y == 0 || c.y == inputMap.size() - 1;
	}

	public static void main(String[] args) {
		input = Input.fetchInput(2019,20);
		DayRunner.run(new Day20());
	}
}
