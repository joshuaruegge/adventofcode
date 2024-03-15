package advent.utilities.general;

public class Coord3L {
    public long x, y, z;

    public Coord3L(long x, long y, long z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public String toString() {
        return "(" + x + "," + y + "," + z + ")";
    }
}
