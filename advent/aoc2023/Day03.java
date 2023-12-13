package advent.aoc2023;

import advent.utilities.general.Coord;
import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

import java.util.ArrayList;
import java.util.HashSet;

public class Day03 implements IDay {

    static String input;

    public static void main(String[] args) {
        input = Input.fetchInput(2023, 3);
        DayRunner.run(new Day03());
    }


    @Override
    public String part1() {
        int total = 0;
        String[] lines = input.split("\n");
        char[][] grid = new char[input.split("\n").length][input.split("\n")[0].length()];
        for(int i = 0; i < lines.length; i++) {
            for(int j = 0; j < lines[0].length(); j++) {
                grid[i][j] = lines[i].charAt(j);
            }
        }
        ArrayList<Coord> validIndices = new ArrayList<>();
        for(int i = 0; i < lines.length; i++) {
            for(int j = 0; j < lines.length; j++) {
                if(Character.isDigit(grid[i][j])) {
                    ArrayList<Coord> nums = new ArrayList<>();
                    while(j < grid[0].length && Character.isDigit(grid[i][j])) {
                        nums.add(new Coord(i,j));
                        j++;
                    }

                    outer:
                    for(Coord c : nums) {
                        for(Coord d : c.allNeighbors()) {
                            if(d.x < 0 || d.y < 0 || d.x >= grid.length || d.y >= grid[0].length)
                                continue;
                            if(!Character.isDigit(grid[d.x][d.y]) && grid[d.x][d.y] != '.') {
                                validIndices.add(nums.get(0));
                                break outer;
                            }
                        }
                    }
                }
            }
        }
        for(Coord c : validIndices) {
            int j = c.y;
            while(j < grid[0].length && Character.isDigit(grid[c.x][j])) {
                j++;
            }
            total += Integer.parseInt(lines[c.x].substring(c.y, j));
        }
        return Integer.toString(total);
    }

    @Override
    public String part2() {
        String[] lines = input.split("\n");
        char[][] grid = new char[lines.length][lines[0].length()];
        for(int i = 0; i < lines.length; i++) {
            for(int j = 0; j < lines[0].length(); j++) {
                grid[i][j] = lines[i].charAt(j);
            }
        }

        long tot = 0;
        for(int i = 0; i < grid.length; i++) {
            for(int  j = 0; j < grid[0].length; j++) {
                if(grid[i][j] != '*') {
                    continue;
                }

                Coord gear = new Coord(i,j);
                ArrayList<Coord> nums = new ArrayList<>();
                for(Coord c : gear.allNeighbors()) {
                    if(Character.isDigit(grid[c.x][c.y])) {
                        nums.add(c);
                    }
                }
                long mult = 1;
                HashSet<Coord> skip = new HashSet<>();
                for(Coord c : nums) {
                    if(skip.contains(c))
                        continue;
                    int left = c.y;
                    int right = c.y;
                    while(left-1 >= 0 && Character.isDigit(grid[c.x][left-1])) {
                        left--;
                    }
                    while(right+1 < grid[0].length && Character.isDigit(grid[c.x][right+1])) {
                        right++;
                    }
                    String num = "";
                    for(int y = left; y <= right; y++) {
                        if(!c.equals(new Coord(c.x,y)) && nums.contains(new Coord(c.x,y)))
                            skip.add(new Coord(c.x,y));
                        num += grid[c.x][y];
                    }
                    mult *= Long.parseLong(num);
                }
                System.out.println(nums + " " + skip);
                if(nums.size() - skip.size() == 2) {
                    tot += mult;
                }
            }
        }
        return Long.toString(tot);
    }
}
