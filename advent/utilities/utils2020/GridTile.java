package advent.utilities.utils2020;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;

public class GridTile {
	public int id;
	
	public GridTile top,bottom,left,right;
	
	public boolean[] topEdge, bottomEdge, leftEdge, rightEdge;
	
	//uses one-dimensional 2d gridstate as hash buffer
	public HashSet<GridEdge> edges;
	
	public boolean[][] data;
	
	public HashSet<GridTile> matches;
	
	public GridTile(int id, boolean[][] data) {
		this.id = id;
		this.data = data;
		
		matches = new HashSet<GridTile>();
	}
	
	//calculates edges based on current data
	public void calculateEdges() {
		topEdge = data[0];
		bottomEdge = data[9];
		leftEdge = colToArray(0);
		rightEdge = colToArray(9);
		
		//populate edges array with all potential values (including flips)
		edges = new HashSet<GridEdge>();
		edges.add(new GridEdge(topEdge));
		edges.add(new GridEdge(reverse(topEdge)));
		edges.add(new GridEdge(bottomEdge));
		edges.add(new GridEdge(reverse(bottomEdge)));
		edges.add(new GridEdge(leftEdge));
		edges.add(new GridEdge(reverse(leftEdge)));
		edges.add(new GridEdge(rightEdge));
		edges.add(new GridEdge(reverse(rightEdge)));
	}
	
	//parses a vertical column into a linear boolean array
	public boolean[] colToArray(int colNum) {
		boolean[] col = new boolean[10];
		for(int row = 0; row < 10; row++) {
			col[row] = data[row][colNum];
		}
		return col;
	}
	
	//returns reversed copy of array without modifying original
	public static boolean[] reverse(boolean[] a) {
		boolean[] b = new boolean[10];
		for(int i = 0; i < 10; i++) {
			b[i] = a[10 - i - 1];
		}
		return b;
	}
	
	public GridTile clone() {
		GridTile next = new GridTile(id,data);
		next.calculateEdges();
		next.matches = new HashSet<GridTile>(matches);
		next.top = top;
		next.bottom = bottom;
		next.left = left;
		next.right = right;
		return next;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(bottomEdge);
		result = prime * result + Arrays.deepHashCode(data);
		result = prime * result + Arrays.hashCode(leftEdge);
		result = prime * result + Arrays.hashCode(rightEdge);
		result = prime * result + Arrays.hashCode(topEdge);
		result = prime * result + Objects.hash(edges, id);
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
		GridTile other = (GridTile) obj;
		return Arrays.equals(bottomEdge, other.bottomEdge) && Arrays.deepEquals(data, other.data)
				&& Objects.equals(edges, other.edges) && id == other.id && Arrays.equals(leftEdge, other.leftEdge)
				&& Arrays.equals(rightEdge, other.rightEdge)
				&& Arrays.equals(topEdge, other.topEdge);
	}

	public String toString() {
		return id + ", matches: " + matches.size() + ", top: " + (top != null ? top.id : null) + ", bottom: " + (bottom != null ? bottom.id : null) + ", left: " + (left != null ? left.id : null) + ", right: " + (right != null ? right.id : null);
	}
	
}
