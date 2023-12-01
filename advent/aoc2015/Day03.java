package advent.aoc2015;

import advent.utilities.general.Coord;
import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

import java.util.HashSet;

public class Day03 implements IDay {

    static String input;

    public static void main(String[] args) {
        input = Input.fetchInput(2015, 3);
        DayRunner.run(new Day03());
    }

    @Override
    public String part1() {
        Coord position = new Coord();
        //keep track of seen positions
        HashSet<Coord> seen = new HashSet<>();
        for (char c : input.toCharArray()) {
            //add current position to seen set
            seen.add(position.copy());
            //update position
            switch (c) {
                case '^' -> position.sumSelf(Coord.UP);
                case 'v' -> position.sumSelf(Coord.DOWN);
                case '<' -> position.sumSelf(Coord.LEFT);
                case '>' -> position.sumSelf(Coord.RIGHT);
            }
        }
        return Integer.toString(seen.size());
    }

    @Override
    public String part2() {
        //two positions, one seen set
        //tracks total locations seen by both
        Coord p1 = new Coord();
        Coord p2 = new Coord();
        HashSet<Coord> seen = new HashSet<>();
        //take two characters at a time
        for (int i = 0; i < input.length(); i += 2) {
            //santa
            seen.add(p1.copy());
            char c1 = input.charAt(i);
            switch (c1) {
                case '^' -> p1.sumSelf(Coord.UP);
                case 'v' -> p1.sumSelf(Coord.DOWN);
                case '<' -> p1.sumSelf(Coord.LEFT);
                case '>' -> p1.sumSelf(Coord.RIGHT);
            }

            //robo-santa
            seen.add(p2.copy());
            char c2 = input.charAt(i + 1);
            switch (c2) {
                case '^' -> p2.sumSelf(Coord.UP);
                case 'v' -> p2.sumSelf(Coord.DOWN);
                case '<' -> p2.sumSelf(Coord.LEFT);
                case '>' -> p2.sumSelf(Coord.RIGHT);
            }
        }
        return Integer.toString(seen.size());
    }

}
