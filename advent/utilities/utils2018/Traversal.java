package advent.utilities.utils2018;

import java.util.Objects;

import advent.utilities.general.Coord;

public class Traversal {
	public Coord pos;
	public int tool;
	
	public Traversal(Coord pos, int tool) {
		this.pos = pos;
		this.tool = tool;
	}

	@Override
	public int hashCode() {
		return Objects.hash(pos, tool);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Traversal other = (Traversal) obj;
		return Objects.equals(pos, other.pos) && tool == other.tool;
	}
	
	
}
