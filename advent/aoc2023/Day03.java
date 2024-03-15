package advent.aoc2023;

import advent.utilities.general.Coord;
import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

import java.util.HashSet;
import java.util.LinkedList;

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
        char[][] grid = new char[lines.length][lines[0].length()];
        for (int i = 0; i < lines.length; i++) {
            grid[i] = lines[i].toCharArray();
        }

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                //we only want to examine the symbols
                if (grid[i][j] == '.' || Character.isDigit(grid[i][j])) {
                    continue;
                }

                //first step: collect all digits around symbol
                Coord symbol = new Coord(j, i);
                LinkedList<Coord> digits = new LinkedList<>();
                for (Coord c : symbol.allNeighbors()) {
                    if (Character.isDigit(grid[c.y][c.x])) {
                        digits.add(c);
                    }
                }

                //now, the issue is that some digits around a symbol are two separate digits of one contiguous number
                //so, move from left to right to capture entire number, and add any digits we collect to skip
                //to avoid re-checking them a second time
                HashSet<Coord> skip = new HashSet<>();
                for (Coord c : digits) {
                    if (skip.contains(c)) continue;
                    int left = c.x;
                    int right = c.x;
                    while (left - 1 >= 0 && Character.isDigit(grid[c.y][left - 1])) {
                        left--;
                    }
                    while (right + 1 < grid[0].length && Character.isDigit(grid[c.y][right + 1])) {
                        right++;
                    }
                    String num = "";
                    for (int x = left; x <= right; x++) {
                        if (!c.equals(new Coord(x, c.y)) && digits.contains(new Coord(x, c.y)))
                            skip.add(new Coord(x, c.y));
                        num += grid[c.y][x];
                    }
                    total += Integer.parseInt(num);
                }
            }
        }
        return Integer.toString(total);
    }

    @Override
    public String part2() {
        String[] lines = input.split("\n");
        char[][] grid = new char[lines.length][lines[0].length()];
        for (int i = 0; i < lines.length; i++) {
            grid[i] = lines[i].toCharArray();
        }

        int sum = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] != '*') {
                    continue;
                }

                //first step: collect all digits around gear
                Coord gear = new Coord(j, i);
                LinkedList<Coord> digits = new LinkedList<>();
                for (Coord c : gear.allNeighbors()) {
                    if (Character.isDigit(grid[c.y][c.x])) {
                        digits.add(c);
                    }
                }


                //now, the issue is that some digits around a gear are two separate digits of one contiguous number
                //so, move from left to right to capture entire number, and add any digits we collect to skip
                long product = 1;
                HashSet<Coord> skip = new HashSet<>();
                for (Coord c : digits) {
                    if (skip.contains(c)) continue;
                    int left = c.x;
                    int right = c.x;
                    while (left - 1 >= 0 && Character.isDigit(grid[c.y][left - 1])) {
                        left--;
                    }
                    while (right + 1 < grid[0].length && Character.isDigit(grid[c.y][right + 1])) {
                        right++;
                    }
                    String num = "";
                    for (int x = left; x <= right; x++) {
                        //avoid adding the current value to skip, but add values other than current
                        if (!c.equals(new Coord(x, c.y)) && digits.contains(new Coord(x, c.y)))
                            skip.add(new Coord(x, c.y));
                        num += grid[c.y][x];
                    }
                    product *= Long.parseLong(num);
                }

                //however, we run into the issue that we need to find the count of actual numbers, not just of digits
                //thankfully, digits total - digits skipped will give us the count of actual numbers
                if (digits.size() - skip.size() == 2) {
                    sum += product;
                }
            }
        }
        return Integer.toString(sum);
    }
}
