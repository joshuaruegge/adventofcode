package advent.utilities.utils2021;

import java.util.Arrays;

public class AmphiState {

    public char[][] rooms;
    public char[] hallway;

    public AmphiState(char[][] rooms, char[] hallway) {
        this.rooms = rooms;
        this.hallway = hallway;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AmphiState that = (AmphiState) o;
        return Arrays.deepEquals(rooms, that.rooms) && Arrays.equals(hallway, that.hallway);
    }

    @Override
    public int hashCode() {
        int result = Arrays.deepHashCode(rooms);
        result = 31 * result + Arrays.hashCode(hallway);
        return result;
    }


    @Override
    public String toString() {
        return "AmphiState{" +
                "rooms=" + Arrays.deepToString(rooms) +
                ", hallway=" + Arrays.toString(hallway) +
                '}';
    }
}
