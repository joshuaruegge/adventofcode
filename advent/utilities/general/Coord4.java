package advent.utilities.general;

public class Coord4 {
	int x,y,z,a;
	
	public Coord4() {
		x = 0;
		y = 0;
		z = 0;
		a = 0;
	}

	public Coord4(int x, int y, int z, int a) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.a = a;
	}
	
	public int distance(Coord4 o) {
		return Math.abs(x - o.x) + Math.abs(y - o.y) + Math.abs(z - o.z) + Math.abs(a - o.a);
	}
	
	public String toString() {
		return "(" + x + "," + y + "," + z + "," + a + ")";
	}
}
