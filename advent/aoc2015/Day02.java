package advent.aoc2015;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

public class Day02 implements IDay {

    static String input;

    public static void main(String[] args) {
        input = Input.fetchInput(2015, 2);
        DayRunner.run(new Day02());
    }

    @Override
    public String part1() {
        int total = 0;
        for (String s : input.split("\n")) {
            //split into individual dimensions and parse
            String[] parts = s.split("x");
            int x = Integer.parseInt(parts[0]);
            int y = Integer.parseInt(parts[1]);
            int z = Integer.parseInt(parts[2]);

            //calculate side areas
            int side1 = x * y;
            int side2 = y * z;
            int side3 = x * z;

            //total surface area, plus slack
            total += 2 * side1 + 2 * side2 + 2 * side3 + Math.min(side1, Math.min(side2, side3));
        }
        return Integer.toString(total);
    }

    @Override
    public String part2() {
        int total = 0;
        for (String s : input.split("\n")) {
            //split into individual dimensions and parse
            String[] parts = s.split("x");
            int x = Integer.parseInt(parts[0]);
            int y = Integer.parseInt(parts[1]);
            int z = Integer.parseInt(parts[2]);

            //perimeters
            int p1 = 2 * (x + y);
            int p2 = 2 * (y + z);
            int p3 = 2 * (x + z);

            //smallest perimeter, and cubic for bow
            total += Math.min(p1, Math.min(p2, p3)) + x * y * z;
        }
        return Integer.toString(total);
    }

}
