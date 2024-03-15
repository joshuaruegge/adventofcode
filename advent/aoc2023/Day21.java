package advent.aoc2023;

import advent.utilities.general.Coord;
import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

import java.util.ArrayList;
import java.util.HashSet;

public class Day21 implements IDay {

    static String input;

    public static void main(String[] args) {
        input = Input.fetchInput(2023, 21);
        DayRunner.run(new Day21());
    }

    @Override
    public String part1() {
        String[] lines = input.split("\n");
        Coord start = new Coord();
        HashSet<Coord> rocks = new HashSet<>();
        for (int y = 0; y < lines.length; y++) {
            for (int x = 0; x < lines[0].length(); x++) {
                if (lines[y].charAt(x) == 'S')
                    start = new Coord(x, y);
                else if (lines[y].charAt(x) == '#')
                    rocks.add(new Coord(x, y));
            }
        }
        HashSet<Coord> steps = new HashSet<>();
        steps.add(start);
        HashSet<Coord> oldSteps = new HashSet<>();
        //optimization: if we can guarantee that newSteps for step i only contains the steps not in step i-2
        //then the total number of steps is the size of newSteps + size of i-2
        //this means we only need to keep track of the "leading edge" of the reachable positions as the "wave" moves out
        //because we can just remember and add what the count for the inner steps was due to odd/even parity
        int[] sizeCache = new int[65];
        for (int i = 0; i < 65; i++) {
            HashSet<Coord> newSteps = new HashSet<>();
            for (Coord s : steps) {
                for (Coord t : s.directNeighbors()) {
                    //if not in oldSteps and not in rocks, is valid location for new step
                    if (!oldSteps.contains(t) && !rocks.contains(t)) {
                        newSteps.add(t);
                    }
                }
            }

            int size = steps.size() + (i > 1 ? sizeCache[i - 2] : 0);
            oldSteps = steps;
            steps = newSteps;
            sizeCache[i] = size;
        }
        return Integer.toString(sizeCache[64]);
    }

    @Override
    public String part2() {
        String[] lines = input.split("\n");
        Coord start = new Coord();
        HashSet<Coord> rocks = new HashSet<>();
        for (int y = 0; y < lines.length; y++) {
            for (int x = 0; x < lines[0].length(); x++) {
                if (lines[y].charAt(x) == 'S')
                    start = new Coord(x, y);
                else if (lines[y].charAt(x) == '#')
                    rocks.add(new Coord(x, y));
            }
        }
        HashSet<Coord> steps = new HashSet<>();
        steps.add(start);
        HashSet<Coord> oldSteps = new HashSet<>();
        ArrayList<Integer> points = new ArrayList<>();
        //optimization: if we can guarantee that newSteps for step i only contains the steps not in step i-2
        //then the total number of steps is the size of newSteps + size of i-2
        //this means we only need to keep track of the "leading edge" of the reachable positions as the "wave" moves out
        //because we can just remember and add what the count for the inner steps was due to odd/even parity
        int[] sizeCache = new int[328];
        for (int i = 0; i < 328; i++) {
            HashSet<Coord> newSteps = new HashSet<>();
            for (Coord s : steps) {
                for (Coord t : s.directNeighbors()) {
                    //if not in oldSteps and not in rocks (reduce to fit original rock bounds)
                    //the %131 + 131 % 131 operation ensures that both positive and negative numbers outside of
                    //the [0, 131] range are mapped back to their expected locations
                    //(to determine where rocks are in the repeated tiles outside the initial tile)
                    if (!oldSteps.contains(t) && !rocks.contains(new Coord(((t.x % 131) + 131) % 131, ((t.y % 131) + 131) % 131))) {
                        newSteps.add(t);
                    }
                }
            }
            int size = steps.size() + (i > 1 ? sizeCache[i - 2] : 0);
            oldSteps = steps;
            steps = newSteps;
            if (i % 131 == 65)
                points.add(size);

            sizeCache[i] = size;
        }
        long n = 26501365 / 131;
        long a = points.get(0);
        long b = points.get(1);
        long c = points.get(2);
        //quadratic equation approximation black magic
        return Long.toString(a + n * (b - a) + n * (n - 1) / 2 * ((c - b) - (b - a)));
    }

}
