package advent.utilities.general;

import java.util.ArrayList;
import java.util.Objects;

public class Coord3 {
	public int x,y,z;
	
	public Coord3() {
		x = 0;
		y = 0;
		z = 0;
	}
	
	public Coord3(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	//adds values of o to self
	public void sumSelf(Coord3 o) {
		x += o.x;
		y += o.y;
		z += o.z;
	}
	
	public Coord3 sum(Coord3 o) {
		return new Coord3(x + o.x, y+o.y, z+o.z);
	}
	
	public Coord3 diff(Coord3 o) {
		return new Coord3(x-o.x,y-o.y,z-o.z);
	}
	
	public ArrayList<Coord3> allNeighbors() {
		ArrayList<Coord3> neighbors = new ArrayList<Coord3>();
		for(int zOff = -1; zOff < 2; zOff++) {
			for(int xOff = -1; xOff < 2; xOff++) {
				for(int yOff = -1; yOff < 2; yOff++) {
					if(!(zOff == 0 && xOff == 0 && yOff == 0)) {
						neighbors.add(new Coord3(x+xOff,y+yOff,z+zOff));
					}
				}
			}
		}
		return neighbors;
	}
	
	public String toString() {
		return "(" + x + "," + y + "," + z + ")";
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y, z);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coord3 other = (Coord3) obj;
		return x == other.x && y == other.y && z == other.z;
	}
	
	//returns copy of self
	public Coord3 copy() {
		return new Coord3(x,y,z);
	}
	
	//returns Manhattan distance from o
	public int dist(Coord3 o) {
		return Math.abs(o.x - x) + Math.abs(o.y - y) + Math.abs(o.z - z);
	}
}
