package advent.utilities.utils2022;

import advent.utilities.general.Coord;

import java.util.Objects;

public class Blizzard {

    public Coord pos,facing;

    public Blizzard(Coord pos, Coord facing) {
        this.pos = pos;
        this.facing = facing;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Blizzard blizzard = (Blizzard) o;
        return pos.equals(blizzard.pos) && facing.equals(blizzard.facing);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pos, facing);
    }

    public String toString() {
        return pos.toString();
    }
}
