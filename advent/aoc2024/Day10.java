package advent.aoc2024;

import advent.utilities.general.Coord;
import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

import java.util.HashSet;
import java.util.LinkedList;

public class Day10 implements IDay {

    static String input;

    static String testInput = "";

    public static void main(String[] args) {
        input = Input.fetchInput(2024, 10);
        DayRunner.run(new Day10());
    }

    @Override
    public String part1() {
        int total = 0;

        String[] lines = input.split("\n");

        int[][] grid = new int[lines.length][lines[0].length()];

        for(int row = 0; row < grid.length; row++) {
            for(int col = 0; col < grid[0].length; col++) {
                grid[row][col] = lines[row].charAt(col) - '0';
            }
        }

        for(int row = 0; row < grid.length; row++) {
            for(int col = 0; col < grid[0].length; col++) {
                if(grid[row][col] != 0)
                    continue;

                HashSet<Coord> reachableNines = new HashSet<>();

                Coord start = new Coord(row, col);

                LinkedList<Coord> queue = new LinkedList<>();
                queue.add(start);

                while(!queue.isEmpty()) {
                    Coord cur = queue.poll();

                    int num = grid[cur.x][cur.y];
                    if(num == 9) {

                        reachableNines.add(cur);
                        continue;
                    }

                    for(Coord c : cur.directNeighbors()) {
                        if(c.x < 0 || c.x >= grid.length || c.y < 0 || c.y >= grid[0].length || grid[c.x][c.y] != num + 1)
                            continue;
                        queue.add(c);
                    }
                }

                total += reachableNines.size();
            }
        }

        return Integer.toString(total);
    }



    @Override
    public String part2() {
        int total = 0;

        String[] lines = input.split("\n");

        int[][] grid = new int[lines.length][lines[0].length()];

        for(int row = 0; row < grid.length; row++) {
            for(int col = 0; col < grid[0].length; col++) {
                grid[row][col] = lines[row].charAt(col) - '0';
            }
        }

        for(int row = 0; row < grid.length; row++) {
            for(int col = 0; col < grid[0].length; col++) {
                if(grid[row][col] != 0)
                    continue;

                HashSet<Coord> reachableNines = new HashSet<>();

                Coord start = new Coord(row, col);

                LinkedList<Coord> queue = new LinkedList<>();
                queue.add(start);

                while(!queue.isEmpty()) {
                    Coord cur = queue.poll();

                    int num = grid[cur.x][cur.y];
                    if(num == 9) {

                        total++;
                        continue;
                    }

                    for(Coord c : cur.directNeighbors()) {
                        if(c.x < 0 || c.x >= grid.length || c.y < 0 || c.y >= grid[0].length || grid[c.x][c.y] != num + 1)
                            continue;
                        queue.add(c);
                    }
                }

            }
        }

        return Integer.toString(total);
    }
}
