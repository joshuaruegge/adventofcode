package advent.utilities.general;

import java.util.ArrayList;
import java.util.Objects;

public class PathNode implements Comparable<PathNode> {
	public Coord pos;
	public int fcost;
	public int gcost;
	public int hcost;
	public ArrayList<PathNode> path;
	
	public PathNode() {
		path = new ArrayList<PathNode>();
	}
	
	public PathNode(Coord c) {
		path = new ArrayList<PathNode>();
		pos = c;
	}
	
	public PathNode(Coord c, int g, int h) {
		path = new ArrayList<PathNode>();
		pos = c;
		
		gcost = g;
		hcost = h;
		fcost = g + h;
	}
	
	public PathNode(PathNode prev, Coord c, int g, int h) {
		pos = c;
		
		gcost = g;
		hcost = h;
		fcost = g + h;
		path = new ArrayList<PathNode>();
		for(PathNode n : prev.path)
			path.add(n);
		
	}
	
	

	@Override
	public int compareTo(PathNode o) {
		return Integer.compare(fcost, o.fcost);
	}

	@Override
	public int hashCode() {
		return Objects.hash(pos);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PathNode other = (PathNode) obj;
		return pos.equals(other.pos);
	}
	
	public String toString() {
		return pos + " " + fcost + " = (" + gcost + " + " + hcost + ")";
	}
	
}
