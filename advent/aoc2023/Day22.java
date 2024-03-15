package advent.aoc2023;

import advent.utilities.general.Coord3;
import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

import java.util.ArrayList;
import java.util.Comparator;

public class Day22 implements IDay {

    static String input;

    public static void main(String[] args) {
        input = Input.fetchInput(2023, 22);
        DayRunner.run(new Day22());
    }

    @Override
    public String part1() {
        //stores bricks as a list, where each brick is a list of Coord3s making up the brick
        ArrayList<ArrayList<Coord3>> bricks = new ArrayList<>();
        int xmax = 0;
        int ymax = 0;
        for (String s : input.split("\n")) {
            String[] halves = s.split("~");
            String[] startNums = halves[0].split(",");
            Coord3 start = new Coord3(Integer.parseInt(startNums[0]), Integer.parseInt(startNums[1]), Integer.parseInt(startNums[2]));
            String[] endNums = halves[1].split(",");
            Coord3 end = new Coord3(Integer.parseInt(endNums[0]), Integer.parseInt(endNums[1]), Integer.parseInt(endNums[2]));
            Coord3 inc;
            if (start.x != end.x) {
                inc = new Coord3(1, 0, 0);
            } else if (start.y != end.y) {
                inc = new Coord3(0, 1, 0);
            } else {
                inc = new Coord3(0, 0, 1);
            }
            ArrayList<Coord3> brick = new ArrayList<>();
            for (Coord3 c = start.copy(); !c.equals(end); c.sumSelf(inc)) {
                brick.add(c.copy());
            }
            brick.add(end);
            bricks.add(brick);
            xmax = Math.max(xmax, Math.max(start.x, end.x));
            ymax = Math.max(ymax, Math.max(start.y, end.y));
        }
        bricks.sort(Comparator.comparingInt(o -> o.get(0).z));

        drop(bricks, xmax, ymax);

        int safe = 0;
        for (int i = 0; i < bricks.size(); i++) {
            if (testDrop(bricks, xmax, ymax, i) == 0)
                safe++;
        }
        return Integer.toString(safe);
    }

    //this method uses a 2-dimensional highestZ array to track the current "top of the pile" at each [x][y] location
    //this trivializes the process of dropping new bricks - each brick just needs to fall until it rests 1 above
    //the maximum value in highestZ for all its (x,y) locations
    //importantly, this means dropping bricks is no longer O(n^2) for n bricks (no need to check every other brick every time)
    public void drop(ArrayList<ArrayList<Coord3>> bricks, int xmax, int ymax) {
        int[][] highestZ = new int[xmax + 1][ymax + 1];

        for (ArrayList<Coord3> brick : bricks) {
            int fallDist = Integer.MAX_VALUE;
            for (Coord3 c : brick) {
                fallDist = Math.min(fallDist, c.z - highestZ[c.x][c.y] - 1);
            }
            for (Coord3 c : brick) {
                c.z -= fallDist;
                highestZ[c.x][c.y] = c.z;
            }
        }
    }

    //the testDrop method does everything the drop method does, except update the original list
    //and additionally, allows us to ignore a brick at index [skip]
    //this is because to test the conditions for parts 1 and 2
    //i.e. whether any bricks drop or how many bricks drop
    //does not require the original bricks to be updated, just the highestZ array
    //additionally, ignoring the brick at index [skip] basically acts as if it weren't there, allowing us to simulate
    //aftereffects of its destruction
    int testDrop(ArrayList<ArrayList<Coord3>> bricks, int xmax, int ymax, int skip) {
        int dropped = 0;

        int[][] highestZ = new int[xmax + 1][ymax + 1];

        for (int i = 0; i < bricks.size(); i++) {
            ArrayList<Coord3> brick = bricks.get(i);
            if (i == skip)
                continue;
            int fallDist = Integer.MAX_VALUE;
            for (Coord3 c : brick) {
                fallDist = Math.min(fallDist, c.z - highestZ[c.x][c.y] - 1);
            }
            for (Coord3 c : brick) {
                highestZ[c.x][c.y] = c.z - fallDist;
            }
            if (fallDist > 0)
                dropped++;
        }
        return dropped;
    }

    @Override
    public String part2() {
        ArrayList<ArrayList<Coord3>> bricks = new ArrayList<>();
        int xmax = 0;
        int ymax = 0;
        for (String s : input.split("\n")) {
            String[] halves = s.split("~");
            String[] startNums = halves[0].split(",");
            Coord3 start = new Coord3(Integer.parseInt(startNums[0]), Integer.parseInt(startNums[1]), Integer.parseInt(startNums[2]));
            String[] endNums = halves[1].split(",");
            Coord3 end = new Coord3(Integer.parseInt(endNums[0]), Integer.parseInt(endNums[1]), Integer.parseInt(endNums[2]));
            Coord3 inc;
            if (start.x != end.x) {
                inc = new Coord3(1, 0, 0);
            } else if (start.y != end.y) {
                inc = new Coord3(0, 1, 0);
            } else {
                inc = new Coord3(0, 0, 1);
            }
            ArrayList<Coord3> brick = new ArrayList<>();
            for (Coord3 c = start.copy(); !c.equals(end); c.sumSelf(inc)) {
                brick.add(c.copy());
            }
            brick.add(end);
            bricks.add(brick);
            xmax = Math.max(xmax, Math.max(start.x, end.x));
            ymax = Math.max(ymax, Math.max(start.y, end.y));
        }
        bricks.sort(Comparator.comparingInt(o -> o.get(0).z));
        drop(bricks, xmax, ymax);

        int total = 0;
        for (int i = 0; i < bricks.size(); i++) {
            total += testDrop(bricks, xmax, ymax, i);
        }
        return Integer.toString(total);
    }
}
