package advent.aoc2023;

import advent.utilities.general.Coord;
import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

import java.util.HashSet;

public class Day11 implements IDay{

    static String input;

    public static void main(String[] args) {
        input = Input.fetchInput(2023, 11);
        DayRunner.run(new Day11());
    }

    @Override
    public String part1() {
        String[] lines = input.split("\n");
        HashSet<Coord> galaxies = new HashSet<>();
        HashSet<Integer> occupiedX = new HashSet<>();
        HashSet<Integer> occupiedY = new HashSet<>();
        for(int y = 0; y < lines.length; y++) {
            for(int x = 0; x < lines[0].length(); x++) {
                if(lines[y].charAt(x)=='#') {
                    galaxies.add(new Coord(x,y));
                    occupiedX.add(x);
                    occupiedY.add(y);
                }
            }
        }
        long pathTotal = 0;
        for(Coord a : galaxies) {
            for(Coord b : galaxies) {
                if(a.equals(b))
                    continue;
                int path = 0;
                for(int x = a.x; x != b.x; x += Math.signum(b.x-a.x)) {
                    if(occupiedX.contains(x))
                        path++;
                    else
                        path+=2;
                }
                for(int y = a.y; y != b.y; y += Math.signum(b.y-a.y)) {
                    if(occupiedY.contains(y))
                        path++;
                    else
                        path += 2;
                }
                pathTotal += path;
            }
        }
        return Long.toString(pathTotal/2);
    }

    @Override
    public String part2() {
        String[] lines = input.split("\n");
        HashSet<Coord> galaxies = new HashSet<>();
        HashSet<Integer> occupiedX = new HashSet<>();
        HashSet<Integer> occupiedY = new HashSet<>();
        for(int y = 0; y < lines.length; y++) {
            for(int x = 0; x < lines[0].length(); x++) {
                if(lines[y].charAt(x)=='#') {
                    galaxies.add(new Coord(x,y));
                    occupiedX.add(x);
                    occupiedY.add(y);
                }
            }
        }
        long pathTotal = 0;
        for(Coord a : galaxies) {
            for(Coord b : galaxies) {
                if(a.equals(b))
                    continue;
                long path = 0;
                for(int x = a.x; x != b.x; x += Math.signum(b.x-a.x)) {
                    if(occupiedX.contains(x))
                        path++;
                    else
                        path+=1000000;
                }
                for(int y = a.y; y != b.y; y += Math.signum(b.y-a.y)) {
                    if(occupiedY.contains(y))
                        path++;
                    else
                        path += 1000000;
                }
                pathTotal += path;
            }
        }
        return Long.toString(pathTotal/2);
    }
}
