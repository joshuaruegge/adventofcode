package advent.utilities.utils2020;

import java.util.Arrays;

public class GridEdge {

	public boolean[] edge;
	
	public GridEdge(boolean[] edge) {
		this.edge = edge;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(edge);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GridEdge other = (GridEdge) obj;
		return Arrays.equals(edge, other.edge);
	}
	
	public String toString() {
		return Arrays.toString(edge);
	}
}
