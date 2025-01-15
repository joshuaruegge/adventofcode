package advent.aoc2024;

import advent.utilities.general.*;

import java.util.HashSet;
import java.util.LinkedList;

public class Day12 implements IDay {

    static String input;

    public static void main(String[] args) {
        input = Input.fetchInput(2024, 12);
        DayRunner.run(new Day12());
    }

    @Override
    public String part1() {
        String[] lines = input.split("\n");

        char[][] grid = new char[lines.length][lines[0].length()];

        for(int row = 0; row < grid.length; row++) {
            for(int col = 0; col < grid[0].length; col++) {
                grid[row][col] = lines[row].charAt(col);
            }
        }



        int total = 0;

        boolean[][] processed = new boolean[grid.length][grid[0].length];

        LinkedList<HashSet<Coord>> plots = new LinkedList<>();

        for(int row = 0; row < grid.length; row++) {
            for(int col = 0; col < grid[0].length; col++) {
                System.out.println(row + " " + col);
                if(processed[row][col])
                    continue;

                char letter = grid[row][col];

                HashSet<Coord> plot = new HashSet<>();

                Coord start = new Coord(row, col);
                plot.add(start);
                LinkedList<Coord> queue = new LinkedList<>();
                queue.add(start);
                while(!queue.isEmpty()) {
                    //System.out.println(queue.size());
                    Coord cur = queue.pop();
                    if(processed[cur.x][cur.y])
                        continue;

                    plot.add(cur);
                    processed[cur.x][cur.y] = true;

                    for(Coord c : cur.directNeighbors()) {
                        if(c.x < 0 || c.x >= grid.length || c.y < 0 || c.y >= grid[0].length || processed[c.x][c.y] || grid[c.x][c.y] != letter)
                            continue;
                        queue.add(c);
                    }
                }

                plots.add(plot);
            }
        }



        for(HashSet<Coord> plot : plots) {
            int perTotal = 0;
            for(Coord p : plot) {
                int per = 4;
                for(Coord n : p.directNeighbors())
                    if(plot.contains(n))
                        per--;
                perTotal += per;
            }

            total += perTotal * plot.size();
        }

        return Integer.toString(total);
    }

    @Override
    public String part2() {
        String[] lines = input.split("\n");

        char[][] grid = new char[lines.length][lines[0].length()];

        for(int row = 0; row < grid.length; row++) {
            for(int col = 0; col < grid[0].length; col++) {
                grid[row][col] = lines[row].charAt(col);
            }
        }



        int total = 0;

        boolean[][] processed = new boolean[grid.length][grid[0].length];

        LinkedList<HashSet<Coord>> plots = new LinkedList<>();

        for(int row = 0; row < grid.length; row++) {
            for(int col = 0; col < grid[0].length; col++) {
                System.out.println(row + " " + col);
                if(processed[row][col])
                    continue;

                char letter = grid[row][col];

                HashSet<Coord> plot = new HashSet<>();

                Coord start = new Coord(row, col);
                plot.add(start);
                LinkedList<Coord> queue = new LinkedList<>();
                queue.add(start);
                while(!queue.isEmpty()) {
                    //System.out.println(queue.size());
                    Coord cur = queue.pop();
                    if(processed[cur.x][cur.y])
                        continue;

                    plot.add(cur);
                    processed[cur.x][cur.y] = true;

                    for(Coord c : cur.directNeighbors()) {
                        if(c.x < 0 || c.x >= grid.length || c.y < 0 || c.y >= grid[0].length || processed[c.x][c.y] || grid[c.x][c.y] != letter)
                            continue;
                        queue.add(c);
                    }
                }

                plots.add(plot);
            }
        }



        for(HashSet<Coord> plot : plots) {
            LinkedList<Pair<Coord, Coord>> borders = new LinkedList<>();
            for(Coord p : plot) {
                for(Coord dir : new Coord[] {Coord.UP, Coord.DOWN, Coord.LEFT, Coord.RIGHT})
                    if (!plot.contains(p.sum(dir)))
                        borders.add(new Pair<>(p, dir));
            }

            System.out.println("Plot: " + grid[borders.get(0).key.x][borders.get(0).key.y]);

            int distinctSides = 0;
            while(!borders.isEmpty()) {
                Pair<Coord, Coord> p = borders.pop();

                distinctSides++;

                Coord base = p.key;
                Coord dir = p.value;

                System.out.println(base + " " + dir);

                Coord ldir = dir.left();
                Coord left = base.sum(ldir);
                while(borders.contains(new Pair<>(left, dir))) {
                    System.out.println("Removing: " + left.toString() + " " + dir.toString());
                    borders.remove(new Pair<>(left, dir));
                    left.sumSelf(ldir);
                }

                Coord rdir = dir.right();
                Coord right = base.sum(rdir);
                while(borders.contains(new Pair<>(right, dir))) {
                    System.out.println("Removing: " + left.toString() + " " + dir.toString());
                    borders.remove(new Pair<>(right, dir));
                    right.sumSelf(rdir);
                }
            }

            System.out.println(distinctSides);

            total += distinctSides * plot.size();
        }

        return Integer.toString(total);
    }
}
