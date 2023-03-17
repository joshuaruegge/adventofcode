package advent.utilities.general;

import java.util.ArrayList;
import java.util.Objects;

public class Coord4 {
    public int x, y, z, a;

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

    //returns manhattan distance from o
    public int distance(Coord4 o) {
        return Math.abs(x - o.x) + Math.abs(y - o.y) + Math.abs(z - o.z) + Math.abs(a - o.a);
    }

    //returns a list of all 80 coords adjacent to this coord
    public ArrayList<Coord4> allNeighbors() {
        ArrayList<Coord4> neighbors = new ArrayList<>();
        for (int aOff = -1; aOff < 2; aOff++) {
            for (int zOff = -1; zOff < 2; zOff++) {
                for (int xOff = -1; xOff < 2; xOff++) {
                    for (int yOff = -1; yOff < 2; yOff++) {
                        if (!(zOff == 0 && xOff == 0 && yOff == 0 && aOff == 0)) {
                            neighbors.add(new Coord4(x + xOff, y + yOff, z + zOff, a + aOff));
                        }
                    }
                }
            }
        }
        return neighbors;
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, x, y, z);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Coord4 other = (Coord4) obj;
        return a == other.a && x == other.x && y == other.y && z == other.z;
    }

    public String toString() {
        return "(" + x + "," + y + "," + z + "," + a + ")";
    }


}
