package advent.utilities.utils2019;

import java.util.Objects;

import advent.utilities.general.Coord3;

public class Node3 implements Comparable<Node3>{
	public Coord3 pos;
	public int fcost;
	public int gcost;
	public int hcost;
	public Node3 parent = null;
	
	public Node3() {
		
	}
	
	public Node3(Coord3 c) {
		pos = c;
	}
	
	public Node3(Coord3 c, int g, int h) {
		pos = c;
		
		gcost = g;
		hcost = h;
		fcost = g + h;
	}

	@Override
	public int compareTo(Node3 o) {
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
		Node3 other = (Node3) obj;
		return pos.equals(other.pos);
	}
	
	public String toString() {
		return pos + " " + fcost + " = (" + gcost + " + " + hcost + ")";
	}
	
}
