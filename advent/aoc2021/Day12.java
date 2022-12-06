package advent.aoc2021;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

public class Day12 implements IDay {

	static String input;
	
	static HashSet<String> paths;
	
	@Override
	public String part1() {
		HashMap<String,HashSet<String>> connections = new HashMap<String,HashSet<String>>();
		for(String s : input.split("\n")) {
			String[] parts = s.split("-");
			if(connections.containsKey(parts[0])) {
				connections.get(parts[0]).add(parts[1]);
			} else {
				HashSet<String> empty = new HashSet<String>();
				empty.add(parts[1]);
				connections.put(parts[0], empty);
			}
			if(connections.containsKey(parts[1])) {
				connections.get(parts[1]).add(parts[0]);
			} else {
				HashSet<String> empty = new HashSet<String>();
				empty.add(parts[0]);
				connections.put(parts[1], empty);
			}
		}
		
		paths = new HashSet<String>();
		path(new ArrayList<String>(), "start",connections);
		return Integer.toString(paths.size());
	}

	@Override
	public String part2() {
		HashMap<String,HashSet<String>> connections = new HashMap<String,HashSet<String>>();
		for(String s : input.split("\n")) {
			String[] parts = s.split("-");
			if(connections.containsKey(parts[0])) {
				connections.get(parts[0]).add(parts[1]);
			} else {
				HashSet<String> empty = new HashSet<String>();
				empty.add(parts[1]);
				connections.put(parts[0], empty);
			}
			if(connections.containsKey(parts[1])) {
				connections.get(parts[1]).add(parts[0]);
			} else {
				HashSet<String> empty = new HashSet<String>();
				empty.add(parts[0]);
				connections.put(parts[1], empty);
			}
		}
		
		paths = new HashSet<String>();
		pathWithDupe(new ArrayList<String>(), "start",connections,false);
		return Integer.toString(paths.size());
	}

	public void path(ArrayList<String> path, String cur, HashMap<String,HashSet<String>> connections) {
		if(cur.equals("end")) {
			paths.add(path.toString());
			return;
		}
		path.add(cur);
		for(String s : connections.get(cur)) {
			if(s.equals("start"))
				continue;
			if(Character.isUpperCase(s.charAt(0))) {
				path(new ArrayList<String>(path),s,connections);
			} else {
				if(!path.contains(s))
					path(new ArrayList<String>(path),s,connections);
			}
		}
	}

	public void pathWithDupe(ArrayList<String> path, String cur, HashMap<String,HashSet<String>> connections, boolean twice) {
		if(cur.equals("end")) {
			paths.add(path.toString());
			return;
		}
		path.add(cur);
		for(String s : connections.get(cur)) {
			if(s.equals("start"))
				continue;
			if(Character.isUpperCase(s.charAt(0))) {
				pathWithDupe(new ArrayList<String>(path),s,connections,twice);
			} else {
				if(!path.contains(s))
					pathWithDupe(new ArrayList<String>(path),s,connections,twice);
				else if(!twice)
					pathWithDupe(new ArrayList<String>(path),s,connections,true);
			}
		}
	}

	public static void main(String[] args) {
		input = Input.fetchInput(2021,12);
		DayRunner.run(new Day12());
	}
}
