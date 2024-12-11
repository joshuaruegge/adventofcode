package advent.aoc2024;

import advent.utilities.general.Coord;
import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Day08 implements IDay {

    static String input;

    public static void main(String[] args) {
        input = Input.fetchInput(2024, 8);
        DayRunner.run(new Day08());
    }

    @Override
    public String part1() {
        String[] lines = input.split("\n");

        char[][] grid = new char[lines.length][lines[0].length()];
        for(int i = 0; i < lines.length; i++)
            grid[i] = lines[i].toCharArray();

        HashMap<Character, ArrayList<Coord>> antennas = new HashMap<>();

        for(int row = 0; row < grid.length; row++) {
            for(int col = 0; col < grid[0].length; col++) {
                char c = grid[row][col];
                if(c == '.')
                    continue;

                antennas.computeIfAbsent(c, a -> new ArrayList<>()).add(new Coord(row, col));
            }
        }

        HashSet<Coord> antinodes = new HashSet<>();

        for(int row = 0; row < grid.length; row++) {
            col:
            for(int col = 0; col < grid[0].length; col++) {
                Coord cur = new Coord(row, col);
                for(char c : antennas.keySet()) {
                    for(Coord a : antennas.get(c)) {
                        for(Coord b : antennas.get(c)) {
                            if(a.equals(b))
                                continue;
                            int dista = a.dist(cur);
                            int distb = b.dist(cur);

                            int inline = ((cur.y - a.y) * (cur.x - b.x)) - ((cur.y - b.y) * (cur.x - a.x));


                            if( inline == 0 && (dista == 2*distb || distb == 2*dista) ) {

                                antinodes.add(cur);
                                continue col;
                            }
                        }
                    }
                }
            }
        }

        return Integer.toString(antinodes.size());
    }

    @Override
    public String part2() {
        String[] lines = input.split("\n");

        char[][] grid = new char[lines.length][lines[0].length()];
        for(int i = 0; i < lines.length; i++)
            grid[i] = lines[i].toCharArray();

        HashMap<Character, ArrayList<Coord>> antennas = new HashMap<>();

        for(int row = 0; row < grid.length; row++) {
            for(int col = 0; col < grid[0].length; col++) {
                char c = grid[row][col];
                if(c == '.')
                    continue;

                antennas.computeIfAbsent(c, a -> new ArrayList<>()).add(new Coord(row, col));
            }
        }

        HashSet<Coord> antinodes = new HashSet<>();

        for(int row = 0; row < grid.length; row++) {
            col:
            for(int col = 0; col < grid[0].length; col++) {
                Coord cur = new Coord(row, col);
                for(char c : antennas.keySet()) {
                    for(Coord a : antennas.get(c)) {
                        for(Coord b : antennas.get(c)) {
                            if(a.equals(b))
                                continue;

                            int inline = ((cur.y - a.y) * (cur.x - b.x)) - ((cur.y - b.y) * (cur.x - a.x));


                            if( inline == 0) {

                                antinodes.add(cur);
                                continue col;
                            }
                        }
                    }
                }
            }
        }

        return Integer.toString(antinodes.size());
    }
}
