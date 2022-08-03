package advent.utilities.utils2018;

import java.util.Objects;

import advent.utilities.general.Coord;

public class Unit {

	public Coord position;
	public int hp;
	public int atk;
	
	public Unit(Coord p, int h, int a) {
		position = p;
		hp = h;
		atk = a;
	}

	@Override
	public int hashCode() {
		return Objects.hash(atk, hp, position);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Unit other = (Unit) obj;
		return atk == other.atk && hp == other.hp && Objects.equals(position, other.position);
	}

	@Override
	public String toString() {
		return "Unit [position=" + position + ", hp=" + hp + ", atk=" + atk + "]";
	}
	
	
	
}
