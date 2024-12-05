package advent.aoc2024;

import advent.utilities.general.Coord;
import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

public class Day04 implements IDay {

    static String input;

    static String testInput = "MMMSXXMASM\n" +
            "MSAMXMSMSA\n" +
            "AMXSXMAAMM\n" +
            "MSAMASMSMX\n" +
            "XMASAMXAMM\n" +
            "XXAMMXXAMA\n" +
            "SMSMSASXSS\n" +
            "SAXAMASAAA\n" +
            "MAMMMXMMMM\n" +
            "MXMXAXMASX";

    public static void main(String[] args) {
        input = Input.fetchInput(2024, 4);
        DayRunner.run(new Day04());
    }

    @Override
    public String part1() {
        String[] lines = input.split("\n");
        char[][] grid = new char[lines.length][lines[0].length()];

        for(int i = 0; i < lines.length; i++) {
            grid[i] = lines[i].toCharArray();
        }

        final Coord[] dirs = {new Coord(1, 0), new Coord(1, 1), new Coord(0, 1), new Coord(-1, 1), new Coord(-1, 0), new Coord(-1, -1), new Coord(0, -1), new Coord(1,-1)};

        final char[] xmas = {'X', 'M', 'A', 'S'};

        int count = 0;

        for(int row = 0; row < grid.length; row++) {
            for(int col = 0; col < grid[0].length; col++) {
                if(grid[row][col] != 'X')
                    continue;
                for(Coord dir : dirs) {
                    int rowp = row;
                    int colp = col;
                    int index = 0;
                    while(rowp >= 0 && rowp < grid.length && colp >= 0 && colp < grid[0].length && index < 4) {
                        if(grid[rowp][colp] != xmas[index])
                            break;
                        index++;
                        rowp += dir.x;
                        colp += dir.y;
                    }
                    if(index == 4)
                        count++;
                }
            }
        }

        return Integer.toString(count);
    }

    @Override
    public String part2() {
        String[] lines = input.split("\n");
        char[][] grid = new char[lines.length][lines[0].length()];

        for(int i = 0; i < lines.length; i++) {
            grid[i] = lines[i].toCharArray();
        }

        final Coord[] dirs = {new Coord(1, 0), new Coord(1, 1), new Coord(0, 1), new Coord(-1, 1), new Coord(-1, 0), new Coord(-1, -1), new Coord(0, -1), new Coord(1,-1)};

        int count = 0;

        for(int row = 0; row < grid.length; row++) {
            for(int col = 0; col < grid[0].length; col++) {
                if(grid[row][col] != 'A')
                    continue;

                Coord a = new Coord(row, col);

                //m left s right
                if(x_mas(grid, a, dirs[3], dirs[5], dirs[1], dirs[7])) count++;
                //m right s left
                if(x_mas(grid, a, dirs[1], dirs[7], dirs[3], dirs[5])) count++;
                //m top s bottom
                if(x_mas(grid, a, dirs[1], dirs[3], dirs[5], dirs[7])) count++;
                //m bottom s top
                if(x_mas(grid, a, dirs[5], dirs[7], dirs[1], dirs[3])) count++;
            }
        }

        return Integer.toString(count);
    }

    public boolean x_mas(char[][] grid, Coord a, Coord md1, Coord md2, Coord sd1, Coord sd2) {
        Coord m1 = a.sum(md1);
        Coord m2 = a.sum(md2);
        Coord s1 = a.sum(sd1);
        Coord s2 = a.sum(sd2);

        if(inBounds(grid, m1) && inBounds(grid, m2) && inBounds(grid, s1) && inBounds(grid, s2)) {
            return grid[m1.x][m1.y] == 'M' && grid[m2.x][m2.y] == 'M' && grid[s1.x][s1.y] == 'S' && grid[s2.x][s2.y] == 'S';
        }
        return false;
    }

    public boolean inBounds(char[][] grid, Coord c) {
        return c.x >= 0 && c.x < grid.length && c.y >= 0 && c.y < grid[0].length;
    }
}
