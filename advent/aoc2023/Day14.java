package advent.aoc2023;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

import java.util.Arrays;
import java.util.HashMap;

public class Day14 implements IDay {

    static String input;

    public static void main(String[] args) {
        input = Input.fetchInput(2023, 14);
        DayRunner.run(new Day14());
    }

    @Override
    public String part1() {
        String[] lines = input.split("\n");

        char[][] grid = new char[lines.length][lines[0].length()];
        for (int i = 0; i < lines.length; i++)
            grid[i] = lines[i].toCharArray();

        for (int y = 1; y < grid.length; y++) {
            for (int x = 0; x < grid[0].length; x++) {
                if (grid[y][x] == 'O') {
                    if (grid[y - 1][x] != '.') continue;
                    int newY = y - 1;
                    while (newY > -1 && grid[newY][x] == '.') newY--;
                    //we ended loop because of collision, so increase by one to be right above collision
                    newY++;

                    grid[newY][x] = 'O';
                    grid[y][x] = '.';
                }
            }
        }

        int load = 0;
        for (int y = 0; y < grid.length; y++)
            for (int x = 0; x < grid[0].length; x++)
                if (grid[y][x] == 'O') load += grid.length - y;

        return Integer.toString(load);
    }

    @Override
    public String part2() {
        String[] lines = input.split("\n");
        //store states as (hash of state array) -> number of spins
        HashMap<Integer, Integer> seen = new HashMap<>();

        char[][] grid = new char[lines.length][lines[0].length()];
        for (int i = 0; i < lines.length; i++)
            grid[i] = lines[i].toCharArray();

        //iterate until we encounter a state we've already seen - this represents the end of a single "cycle"
        int count = 0;
        while (!seen.containsKey(Arrays.deepHashCode(grid))) {
            seen.put(Arrays.deepHashCode(grid), count++);
            spin(grid);
        }

        //determine the length of the cycle
        int cycleLength = count - seen.get(Arrays.deepHashCode(grid));

        //for the remaining something-hundred-million cycles, the state of the grid every (cycleLength) cycles
        //will be the same as it currently is
        //so, we can skip until there are less than cycleLength cycles remaining
        int remaining = 1000000000 - count;
        remaining %= cycleLength;

        //perform last few spins
        for (int i = 0; i < remaining; i++)
            spin(grid);

        int load = 0;
        for (int y = 0; y < lines.length; y++)
            for (int x = 0; x < lines[y].length(); x++)
                if (grid[y][x] == 'O') load += grid.length - y;

        return Integer.toString(load);
    }

    //performs one spin (tilting north, then west, then south, then east)
    public void spin(char[][] grid) {
        //north
        for (int y = 1; y < grid.length; y++) {
            for (int x = 0; x < grid[0].length; x++) {
                if (grid[y][x] == 'O') {
                    if (grid[y - 1][x] != '.') continue;
                    int newY = y - 1;
                    while (newY > -1 && grid[newY][x] == '.') newY--;
                    newY++;

                    grid[newY][x] = 'O';
                    grid[y][x] = '.';
                }
            }
        }

        //west
        for (int x = 1; x < grid[0].length; x++) {
            for (int y = 0; y < grid.length; y++) {
                if (grid[y][x] == 'O') {
                    if (grid[y][x - 1] != '.') continue;
                    int newX = x - 1;
                    while (newX > -1 && grid[y][newX] == '.') newX--;
                    newX++;

                    grid[y][newX] = 'O';
                    grid[y][x] = '.';
                }
            }
        }

        //south
        for (int y = grid.length - 2; y > -1; y--) {
            for (int x = 0; x < grid[0].length; x++) {
                if (grid[y][x] == 'O') {
                    if (grid[y + 1][x] != '.') continue;
                    int newY = y + 1;
                    while (newY < grid.length && grid[newY][x] == '.') newY++;
                    newY--;

                    grid[newY][x] = 'O';
                    grid[y][x] = '.';
                }
            }
        }

        //east
        for (int x = grid[0].length - 2; x > -1; x--) {
            for (int y = 0; y < grid.length; y++) {
                if (grid[y][x] == 'O') {
                    if (grid[y][x + 1] != '.') continue;
                    int newX = x + 1;
                    while (newX < grid[0].length && grid[y][newX] == '.') newX++;
                    newX--;

                    grid[y][newX] = 'O';
                    grid[y][x] = '.';
                }
            }
        }
    }
}
