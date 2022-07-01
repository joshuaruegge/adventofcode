package advent.utilities.utils2019;

import java.util.Objects;

import advent.utilities.general.Coord;

public class State {

	
	public Coord position;
	//integer bitmask representing which of 26 total keys have been grabbed
	public int keys;
	//distance traversed so far
	public int dist;
	
	public State(Coord p, int k, int d) {
		position = p;
		keys = k;
		dist = d;
	}

	@Override
	public int hashCode() {
		return Objects.hash(keys, position,dist);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		State other = (State) obj;
		return keys == other.keys && Objects.equals(position, other.position) && dist == other.dist;
	}
	
	public String toString() {
		return position + " " + Integer.toBinaryString(keys) + " " + dist;
	}

}
