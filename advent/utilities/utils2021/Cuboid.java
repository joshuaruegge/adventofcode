package advent.utilities.utils2021;

import java.math.BigInteger;
import java.util.Objects;

import advent.utilities.general.Coord3;

public class Cuboid {
	public Coord3 c1,c2;
	
	public int sign;
	
	public Cuboid(Coord3 c1, Coord3 c2) {
		this.c1 = c1;
		this.c2 = c2;
		sign = 0;
	}
	
	public Cuboid(Coord3 c1, Coord3 c2, int sign) {
		this.c1 = c1;
		this.c2 = c2;
		this.sign = sign;
	}
	
	public Cuboid sign(int sign) {
		return new Cuboid(c1,c2,sign);
	}
	
	public boolean valid() {
		return c1.x < c2.x && c1.y < c2.y && c1.z < c2.z;
	}
	
	public BigInteger volume() {
		BigInteger l = new BigInteger(Integer.toString(c2.x-c1.x));
		BigInteger w = new BigInteger(Integer.toString(c2.y-c1.y));
		BigInteger h = new BigInteger(Integer.toString(c2.z-c1.z));
		
		return l.multiply(w).multiply(h);
	}
	
	public Cuboid overlap(Cuboid o) {
		Cuboid overlap = new Cuboid(new Coord3(Math.max(c1.x, o.c1.x), Math.max(c1.y,o.c1.y), Math.max(c1.z,o.c1.z)), new Coord3(Math.min(c2.x,o.c2.x), Math.min(c2.y, o.c2.y), Math.min(c2.z, o.c2.z)));
		if(overlap.valid())
			return overlap;
		else
			return null;
	}

	@Override
	public int hashCode() {
		return Objects.hash(c1, c2);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cuboid other = (Cuboid) obj;
		return Objects.equals(c1, other.c1) && Objects.equals(c2, other.c2);
	}
	
	
}
