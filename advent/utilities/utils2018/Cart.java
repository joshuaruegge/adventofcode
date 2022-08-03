package advent.utilities.utils2018;

import java.util.Objects;

import advent.utilities.general.Coord;

public class Cart implements Comparable<Cart>{
	public Coord pos;
	public Coord facing;
	public int turnCount;
	
	public Cart(Coord p, Coord f) {
		pos = p;
		facing = f;
		turnCount = 0;
	}

	@Override
	public int compareTo(Cart o) {
		// TODO Auto-generated method stub
		return pos.compareTo(o.pos);
	}
	
	public String toString() {
		return pos + " " + facing + " " + turnCount;
	}

	@Override
	public int hashCode() {
		return Objects.hash(facing, pos, turnCount);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cart other = (Cart) obj;
		return Objects.equals(facing, other.facing) && Objects.equals(pos, other.pos) && turnCount == other.turnCount;
	}
	
	
}
