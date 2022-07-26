package advent.utilities.utils2017;

import java.util.Arrays;

public class GridState {
	public boolean[][] grid;
	
	public GridState(boolean[][] grid) {
		this.grid = grid;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.deepHashCode(grid);
		
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
		GridState other = (GridState) obj;
		return Arrays.deepEquals(grid, other.grid);
	}
	
}
