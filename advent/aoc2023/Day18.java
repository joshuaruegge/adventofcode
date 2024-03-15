package advent.aoc2023;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

public class Day18 implements IDay {

    static String input;

    public static void main(String[] args) {
        input = Input.fetchInput(2023, 18);
        DayRunner.run(new Day18());
    }

    @Override
    public String part1() {
        String[] lines = input.split("\n");
        //initialize corners array, add first vertex
        long[][] corners = new long[lines.length + 1][2];
        long x = 0;
        long y = 0;
        corners[0] = new long[]{x, y};

        int counter = 1;
        //initialize perimeter to 1 to account for (0,0)
        long perimeter = 1;
        for (String s : lines) {
            String[] parts = s.split(" ");
            int dist = Integer.parseInt(parts[1]);
            perimeter += dist;
            switch (parts[0]) {
                case "U" -> y -= dist;
                case "R" -> x += dist;
                case "D" -> y += dist;
                case "L" -> x -= dist;
            }
            corners[counter++] = new long[]{x, y};
        }

        return Long.toString(shoelace(corners, perimeter));
    }

    //shoelace method is a godsend and avoids costly flood-fill recursion/iteration
    //because it scales with the number of vertexes/corners, not the area within
    public long shoelace(long[][] corners, long perimeter) {
        long area = 0;

        //for each pair of corners we add the cross product of the corners (this represents the area of a parallelogram??)
        //imagine taking the "determinant" of
        //  corners[i-1][0]  corners[i][0]
        //  corners[i-1][1]  corners[i][1]
        for (int i = 1; i < corners.length; i++) {
            area += (corners[i - 1][0] * corners[i][1]) - (corners[i - 1][1] * corners[i][0]);
        }
        //now, add perimeter to account for the fact that edges are included in area
        area += perimeter;
        //lastly, shoelace method requires dividing by 2 (since calculated area is double actual area (something about triangles)
        area /= 2;

        //correct pesky off-by-one
        return area + 1;
    }

    @Override
    public String part2() {
        String[] lines = input.split("\n");
        long[][] corners = new long[lines.length + 1][2];
        long x = 0;
        long y = 0;
        corners[0] = new long[]{x, y};

        int counter = 1;
        long perimeter = 1;

        for (String s : lines) {
            String hex = s.split(" ")[2];
            long dist = Integer.parseInt(hex.substring(2, 7), 16);
            perimeter += dist;
            switch (hex.charAt(7)) {
                case '0' -> x += dist;
                case '1' -> y += dist;
                case '2' -> x -= dist;
                case '3' -> y -= dist;
            }
            corners[counter++] = new long[]{x, y};
        }
        return Long.toString(shoelace(corners, perimeter));
    }
}
