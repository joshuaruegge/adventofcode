package advent.utilities.general;

import java.util.ArrayList;
import java.util.Objects;

public class Coord implements Comparable<Coord> {

    public static final Coord UP = new Coord(0, -1);
    public static final Coord DOWN = new Coord(0, 1);
    public static final Coord LEFT = new Coord(-1, 0);
    public static final Coord RIGHT = new Coord(1, 0);

    public int x;
    public int y;

    public Coord(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Coord() {
        x = 0;
        y = 0;
    }

    //returns this coord "rotated" to the left
    //ex: (0, -1) (up) becomes (-1,0) (left), (-1, 0) becomes (0, 1) (down), etc.
    public Coord left() {
        return new Coord(y, -x);
    }

    //does the same, but rotates to the right
    public Coord right() {
        return new Coord(-y, x);
    }

    //updates this object to the value of this + o (i.e., (this.x + o.x, this.y + o.y))
    public void sumSelf(Coord o) {
        x += o.x;
        y += o.y;
    }

    //returns a new coordinate that is the result of this + o
    public Coord sum(Coord o) {
        return new Coord(x + o.x, y + o.y);
    }

    //returns Manhattan distance to coord o
    public int dist(Coord o) {
        return Math.abs(x - o.x) + Math.abs(y - o.y);
    }

    //returns copy of self
    public Coord copy() {
        return new Coord(x, y);
    }

    //returns version with x and y swapped
    public Coord invert() {
        return new Coord(y, x);
    }

    //based on the value of this coord, returns a list of the four orthogonally adjacent coords
    public ArrayList<Coord> directNeighbors() {
        ArrayList<Coord> list = new ArrayList<>();
        for (int yOff = -1; yOff < 2; yOff++) {
            for (int xOff = -1; xOff < 2; xOff++) {
                //if the x offset and y offset are both nonzero, the coord is diagonally adjacent
                //however, if they are both zero, then we are just looking at this coord
                //therefore, use XOR to test for offsets where only one is equal to zero
                if (xOff == 0 ^ yOff == 0) {
                    list.add(new Coord(x + xOff, y + yOff));
                }
            }
        }
        return list;
    }

    //based on the value of this coord, returns a list of all 8 adjacent coords
    public ArrayList<Coord> allNeighbors() {
        ArrayList<Coord> list = new ArrayList<>();
        for (int yOff = -1; yOff < 2; yOff++) {
            for (int xOff = -1; xOff < 2; xOff++) {
                //ignore the offset case 0,0 representing the original coord
                if (!(xOff == 0 && yOff == 0)) {
                    list.add(new Coord(x + xOff, y + yOff));
                }
            }
        }
        return list;
    }

    //orders coordinates in left-to-right, top-to-bottom order
    @Override
    public int compareTo(Coord o) {
        if (this.equals(o)) return 0;
        else if (o.y > this.y) return -1;
        else if (o.y < this.y) return 1;
        else return (o.x > this.x ? -1 : 1);
    }

    @Override
    public int hashCode() {
        return Objects.hash(y, x);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Coord other = (Coord) obj;
        return x == other.x && y == other.y;
    }

    public String toString() {
        return "(" + x + "," + y + ")";
    }
}
