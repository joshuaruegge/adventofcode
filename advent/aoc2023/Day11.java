package advent.aoc2023;

import advent.utilities.general.Coord;
import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

import java.util.ArrayList;

public class Day11 implements IDay {

    static String input;

    public static void main(String[] args) {
        input = Input.fetchInput(2023, 11);
        DayRunner.run(new Day11());
    }

    @Override
    public String part1() {
        String[] lines = input.split("\n");
        ArrayList<Coord> galaxies = new ArrayList<>();

        boolean[] occupiedX = new boolean[lines[0].length()];
        boolean[] occupiedY = new boolean[lines.length];

        for (int y = 0; y < lines.length; y++) {
            for (int x = 0; x < lines[0].length(); x++) {
                if (lines[y].charAt(x) == '#') {
                    galaxies.add(new Coord(x, y));
                    occupiedX[x] = true;
                    occupiedY[y] = true;
                }
            }
        }

        long pathTotal = 0;
        for (int i = 0; i < galaxies.size(); i++) {
            for (int j = i + 1; j < galaxies.size(); j++) {
                Coord a = galaxies.get(i);
                Coord b = galaxies.get(j);
                //because we can travel through other galaxies and there are no other obstacles to pathfinding,
                //all shortest paths are equivalent
                //so, traverse the easiest one - first go from xa to xb, then ya to yb
                int path = 0;
                int xInc = Integer.signum(b.x - a.x);
                for (int x = a.x; x != b.x; x += xInc) {
                    if (occupiedX[x]) path++;
                    else path += 2;
                }
                int yInc = Integer.signum(b.y - a.y);
                for (int y = a.y; y != b.y; y += yInc) {
                    if (occupiedY[y]) path++;
                    else path += 2;
                }
                pathTotal += path;
            }
        }
        return Long.toString(pathTotal);
    }

    @Override
    public String part2() {
        String[] lines = input.split("\n");
        ArrayList<Coord> galaxies = new ArrayList<>();

        boolean[] occupiedX = new boolean[lines[0].length()];
        boolean[] occupiedY = new boolean[lines.length];

        for (int y = 0; y < lines.length; y++) {
            for (int x = 0; x < lines[0].length(); x++) {
                if (lines[y].charAt(x) == '#') {
                    galaxies.add(new Coord(x, y));
                    occupiedX[x] = true;
                    occupiedY[y] = true;
                }
            }
        }

        long pathTotal = 0;
        for (int i = 0; i < galaxies.size(); i++) {
            for (int j = i + 1; j < galaxies.size(); j++) {
                Coord a = galaxies.get(i);
                Coord b = galaxies.get(j);
                //because we can travel through other galaxies and there are no other obstacles to pathfinding,
                //all shortest paths are equivalent
                //so, traverse the easiest one - first go from xa to xb, then ya to yb
                int path = 0;
                int xInc = Integer.signum(b.x - a.x);
                for (int x = a.x; x != b.x; x += xInc) {
                    if (occupiedX[x]) path++;
                    else path += 1000000;
                }
                int yInc = Integer.signum(b.y - a.y);
                for (int y = a.y; y != b.y; y += yInc) {
                    if (occupiedY[y]) path++;
                    else path += 1000000;
                }
                pathTotal += path;
            }
        }
        return Long.toString(pathTotal);
    }
}
