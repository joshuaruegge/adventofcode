package advent.aoc2024;

import advent.utilities.general.Coord;
import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Day06 implements IDay {

    static String input;

    public static void main(String[] args) {
        input = Input.fetchInput(2024, 6);
        DayRunner.run(new Day06());
    }

    @Override
    public String part1() {
        String[] lines = input.split("\n");

        boolean[][] grid = new boolean[lines.length][lines[0].length()];

        Coord cur = new Coord();

        for(int row = 0; row < lines.length; row++) {
            for(int col = 0; col < lines[row].length(); col++) {
                char c = lines[row].charAt(col);

                if(c == '^') {
                    cur = new Coord(row, col);
                }
                grid[row][col] = c == '#';
            }
        }

        Coord dir = Coord.LEFT;


        HashSet<Coord> visited = new HashSet<>();
        while(true) {

            visited.add(cur);

            Coord next = cur.sum(dir);
            if(next.x < 0 || next.x >= grid.length || next.y < 0 || next.y >= grid[0].length)
                break;
            if(grid[next.x][next.y]) {
                cur = cur.copy();
                dir = dir.left();
            } else {
                cur = next;
            }
        }

        return Integer.toString(visited.size());
    }

    public boolean formsLoop(Coord newObs, Coord start, Coord startDir, boolean[][] grid) {

        if(newObs.x < 0 || newObs.x >= grid.length || newObs.y < 0 || newObs.y >= grid[0].length || grid[newObs.x][newObs.y])
            return false;



        System.out.println(newObs);

        grid[newObs.x][newObs.y] = true;



        Coord cur = start.copy();
        Coord dir = startDir;


        HashMap<Coord, ArrayList<Coord>> visited = new HashMap<>();

        while(true) {
            System.out.println(cur);
            if(visited.getOrDefault(cur, new ArrayList<>()).contains(dir)) {
                grid[newObs.x][newObs.y] = false;
                return true;
            }

            visited.computeIfAbsent(cur, k -> new ArrayList<Coord>()).add(dir);

            Coord next = cur.sum(dir);

            if(next.x < 0 || next.x >= grid.length || next.y < 0 || next.y >= grid[0].length)
                break;
            if(grid[next.x][next.y]) {
                cur = cur.copy();
                dir = dir.left();
            } else {
                cur = next;
            }


        }

        grid[newObs.x][newObs.y] = false;
        return false;
    }

    public boolean checkFor(Coord target, Coord targetDir, boolean[][] grid) {
        Coord cur = target.copy();
        Coord dir = targetDir;
        while(true) {
            Coord next = cur.sum(dir);
            if(next.equals(target) && dir.equals(targetDir))
                return true;
            if(next.x < 0 || next.x >= grid.length || next.y < 0 || next.y >= grid[0].length)
                break;
            if(grid[next.x][next.y]) {
                cur = cur.copy();
                dir = dir.left();
            } else {
                cur = next;
            }


        }
        return false;
    }



    @Override
    public String part2() {
        String[] lines = input.split("\n");

        boolean[][] grid = new boolean[lines.length][lines[0].length()];

        Coord start = new Coord();

        for(int row = 0; row < lines.length; row++) {
            for(int col = 0; col < lines[row].length(); col++) {
                char c = lines[row].charAt(col);

                if(c == '^') {
                    start = new Coord(row, col);
                }
                grid[row][col] = c == '#';
            }
        }

        Coord dir = Coord.LEFT;

        HashMap<Coord, ArrayList<Coord>> visited = new HashMap<>();

        Coord cur = start;

        while(true) {
            visited.computeIfAbsent(cur, k -> new ArrayList<>()).add(dir);

            Coord next = cur.sum(dir);
            if(next.x < 0 || next.x >= grid.length || next.y < 0 || next.y >= grid[0].length)
                break;
            if(grid[next.x][next.y]) {
                cur = cur.copy();
                dir = dir.left();
            } else {
                cur = next;
            }
        }

        int addLocations = 0;


        /*
        for(Coord c : visited.keySet()) {
            System.out.println(c);
            for(Coord d : visited.get(c))
                if(formsLoop(c.sum(d), start, Coord.LEFT, grid)) {
                    addLocations++;
                }
        }

         */

        //System.out.println(formsLoop(start.sum(Coord.UP), start, Coord.LEFT, grid));



        for(int row = 0; row < grid.length; row++) {
            for(int col = 0; col < grid[0].length; col++) {
                if(new Coord(row,col).equals(start.sum(Coord.UP)))
                    System.out.println("!!");
                if(formsLoop(new Coord(row, col), start, Coord.LEFT, grid))
                    addLocations++;
            }
        }



        return Integer.toString(addLocations);
    }
}
