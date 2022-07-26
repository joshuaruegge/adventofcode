package advent.utilities.utils2017;

import java.util.ArrayList;
import java.util.HashMap;

import advent.aoc2017.Day18;

public class Duet {
	
	public ArrayList<Long> queue = new ArrayList<Long>();
	public boolean stopped = false;
	int position = 0;
	ArrayList<String> lines = new ArrayList<String>();
	HashMap<String,Long> registers = new HashMap<String,Long>();
	public int id;
	public long timesSent = 0;
	
	public Duet(ArrayList<String> a, int i) {
		lines = a;
		id = i;
		registers.put("p", (long) id);
	}
	
	public void run() {
		if(position >= lines.size() || position < 0) {
			stopped = true;
			return;
		}
		if(stopped && queue.size() == 0) {
			return;
		} else if (stopped && queue.size() > 0) {
			stopped = false;
		}
		
		String[] line = lines.get(position).split(" ");
		switch(line[0]) {
		case "rcv":
			if(queue.size() > 0) {
				registers.put(line[1], queue.remove(0));
			} else {
				stopped = true;
				return;
			}
			break;
		case "snd":
			timesSent++;
			try {
				Day18.send(Math.abs(id - 1), Long.parseLong(line[1]));
			} catch (Exception e) {
				Day18.send(Math.abs(id - 1),registers.getOrDefault(line[1], (long) 0));
			}
			break;
		case "set":
			try {
				registers.put(line[1], Long.parseLong(line[2]));
			} catch (Exception e) {
				registers.put(line[1], registers.getOrDefault(line[2], (long) 0));
			}
			break;
		case "add":
			try {
				registers.put(line[1], registers.getOrDefault(line[1], (long) 0) + Long.parseLong(line[2]));
			} catch (Exception e) {
				registers.put(line[1], registers.getOrDefault(line[1], (long) 0) + registers.getOrDefault(line[2], (long) 0));
			}
			break;
		case "mul":
			try {
				registers.put(line[1], registers.getOrDefault(line[1], (long) 0) * Long.parseLong(line[2]));
			} catch (Exception e) {
				registers.put(line[1], registers.getOrDefault(line[1], (long) 0) * registers.getOrDefault(line[2], (long) 0));
			}
			break;
		case "mod":
			try {
				registers.put(line[1], registers.getOrDefault(line[1], (long) 0) % Long.parseLong(line[2]));
			} catch (Exception e) {
				registers.put(line[1], registers.getOrDefault(line[1], (long) 0) % registers.getOrDefault(line[2], (long) 0));
			}
			break;
		case "jgz":
			boolean jump = false;
			try {
				jump = Long.parseLong(line[1]) > 0;
			} catch (Exception e) {
				jump = registers.getOrDefault(line[1], (long) 0) > 0;
			}
			if(jump) {
				try {
					position += Long.parseLong(line[2]) - 1;
				} catch (Exception e) {
					position += registers.getOrDefault(line[2],(long) 0) - 1;
				}
			}
		}
		position++;
	}
	
	
}
