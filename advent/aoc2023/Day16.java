package advent.aoc2023;

import advent.utilities.general.*;

import java.util.HashSet;
import java.util.LinkedList;

public class Day16 implements IDay {

    static String input;

    public static void main(String[] args) {
        input = Input.fetchInput(2023, 16);
        DayRunner.run(new Day16());
    }

    @Override
    public String part1() {
        String[] lines = input.split("\n");
        char[][] grid = new char[lines.length][lines[0].length()];

        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[0].length; x++) {
                grid[x][y] = lines[y].charAt(x);
            }
        }

        return Integer.toString(beamVisited(new Coord(-1, 0), Coord.RIGHT, grid));
    }

    public int beamVisited(Coord start, Coord startDir, char[][] grid) {
        HashSet<Coord> seenLocations = new HashSet<>();
        HashSet<Pair<Coord, Coord>> seenMirrors = new HashSet<>();
        LinkedList<Pair<Coord, Coord>> mirrorQueue = new LinkedList<>();
        mirrorQueue.add(new Pair<>(start, startDir));

        while (!mirrorQueue.isEmpty()) {
            Pair<Coord, Coord> mirror = mirrorQueue.poll();
            seenMirrors.add(mirror);
            Coord pos = mirror.key;
            Coord dir = mirror.value;
            pos = pos.sum(dir);
            while (pos.x >= 0 && pos.x < grid.length && pos.y >= 0 && pos.y < grid[0].length && grid[pos.x][pos.y] == '.') {
                seenLocations.add(pos);
                pos = pos.sum(dir);
            }
            if (pos.x >= 0 && pos.x < grid.length && pos.y >= 0 && pos.y < grid[0].length) {
                seenLocations.add(pos);
                Coord newMirrorPos = new Coord(pos.x, pos.y);
                Coord[] newDirs = findDirs(dir, grid[pos.x][pos.y]);
                for (Coord newDir : newDirs) {
                    Pair<Coord, Coord> newMirror = new Pair<>(newMirrorPos, newDir);
                    if (!seenMirrors.contains(newMirror)) mirrorQueue.add(newMirror);
                }
            }
        }

        return seenLocations.size();
    }

    //returns a list of the resulting direction(s) from a beam colliding with mirror m coming from direction oldDir
    public Coord[] findDirs(Coord oldDir, char m) {
        switch (m) {
            case '\\' -> {
                if (oldDir.y == 0) {
                    return new Coord[]{oldDir.right()};
                } else {
                    return new Coord[]{oldDir.left()};
                }
            }
            case '/' -> {
                if (oldDir.y == 0) {
                    return new Coord[]{oldDir.left()};
                } else {
                    return new Coord[]{oldDir.right()};
                }
            }
            case '-' -> {
                if (oldDir.x == 0) {
                    return new Coord[]{oldDir.left(), oldDir.right()};
                } else {
                    return new Coord[]{oldDir};
                }
            }
            case '|' -> {
                if (oldDir.y == 0) {
                    return new Coord[]{oldDir.left(), oldDir.right()};
                } else {
                    return new Coord[]{oldDir};
                }
            }
        }
        return new Coord[]{};
    }

    @Override
    public String part2() {
        String[] lines = input.split("\n");
        char[][] grid = new char[lines.length][lines[0].length()];

        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[0].length; x++) {
                grid[x][y] = lines[y].charAt(x);
            }
        }

        int best = 0;
        for (int x = 0; x < grid.length; x++) {
            best = Math.max(best, Math.max(beamVisited(new Coord(x, -1), Coord.DOWN, grid), beamVisited(new Coord(x, grid[0].length), Coord.UP, grid)));
        }
        for (int y = 0; y < grid[0].length; y++) {
            best = Math.max(best, Math.max(beamVisited(new Coord(-1, y), Coord.RIGHT, grid), beamVisited(new Coord(grid.length, y), Coord.LEFT, grid)));
        }

        return Integer.toString(best);
    }
}
