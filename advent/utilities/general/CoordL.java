package advent.utilities.general;

import java.util.ArrayList;
import java.util.Objects;

public class CoordL {

    public long x;
    public long y;

    public CoordL(long x, long y) {
        this.x = x;
        this.y = y;
    }

    public CoordL() {
        x = 0;
        y = 0;
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
        CoordL other = (CoordL) obj;
        return x == other.x && y == other.y;
    }

    public String toString() {
        return "(" + x + "," + y + ")";
    }
}
