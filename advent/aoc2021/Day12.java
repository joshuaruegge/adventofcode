package advent.aoc2021;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;

public class Day12 implements IDay {

	String input = "xq-XZ\r\n"
			+ "zo-yr\r\n"
			+ "CT-zo\r\n"
			+ "yr-xq\r\n"
			+ "yr-LD\r\n"
			+ "xq-ra\r\n"
			+ "np-zo\r\n"
			+ "end-LD\r\n"
			+ "np-LD\r\n"
			+ "xq-kq\r\n"
			+ "start-ra\r\n"
			+ "np-kq\r\n"
			+ "LO-end\r\n"
			+ "start-xq\r\n"
			+ "zo-ra\r\n"
			+ "LO-np\r\n"
			+ "XZ-start\r\n"
			+ "zo-kq\r\n"
			+ "LO-yr\r\n"
			+ "kq-XZ\r\n"
			+ "zo-LD\r\n"
			+ "kq-ra\r\n"
			+ "XZ-yr\r\n"
			+ "LD-ws\r\n"
			+ "np-end\r\n"
			+ "kq-yr";
	
	static HashSet<String> paths;
	
	@Override
	public String part1() {
		HashMap<String,HashSet<String>> connections = new HashMap<String,HashSet<String>>();
		for(String s : input.split("\r\n")) {
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
	
	@Override
	public String part2() {
		HashMap<String,HashSet<String>> connections = new HashMap<String,HashSet<String>>();
		for(String s : input.split("\r\n")) {
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

	public static void main(String[] args) {
		DayRunner.run(new Day12());
	}

}
