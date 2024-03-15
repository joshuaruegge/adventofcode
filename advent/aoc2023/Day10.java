package advent.aoc2023;

import advent.utilities.general.Coord;
import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

import java.util.ArrayList;
import java.util.Arrays;

public class Day10 implements IDay {

    static String input;

    public static void main(String[] args) {
        input = Input.fetchInput(2023, 10);
        DayRunner.run(new Day10());
    }

    @Override
    public String part1() {
        String[] lines = input.split("\n");
        char[][] grid = new char[lines[0].length()][lines.length];
        Coord start = new Coord();

        for (int y = 0; y < lines.length; y++) {
            for (int x = 0; x < lines[0].length(); x++) {
                grid[x][y] = lines[y].charAt(x);
                if (grid[x][y] == 'S') start = new Coord(x, y);
            }
        }

        //arbitrarily pick one of the two locations next to S to begin with
        //either doesnt matter, as we're just traversing around the loop and counting
        Coord cur = new Coord();
        startfind:
        for (Coord c : new Coord[]{Coord.UP, Coord.DOWN, Coord.LEFT, Coord.RIGHT}) {
            Coord newStart = new Coord(start.x + c.x, start.y + c.y);

            for (Coord dir : connections(grid[newStart.x][newStart.y])) {
                if (newStart.sum(dir).equals(start)) {
                    cur = newStart;
                    break startfind;
                }
            }
        }

        //keep a cur coord and a prev coord - at every step, pick the direction to travel that
        //doesn't point us back towards prev, then set prev to cur and cur to next
        Coord prev = start;
        //count starts at 1 cause we're starting at point adjacent to S
        int count = 1;
        while (!cur.equals(start)) {
            Coord[] dirs = connections(grid[cur.x][cur.y]);
            //if summing with dirs[0] takes us backwards, sum with dirs[1]. otherwise, sum with dirs[0]
            Coord next = (cur.sum(dirs[0]).equals(prev) ? cur.sum(dirs[1]) : cur.sum(dirs[0]));
            prev = cur;
            cur = next;
            count++;
        }

        //because loop is closed and has no intersects
        //the distance between start and furthest point is just (loop length/2)
        return Integer.toString(count / 2);
    }

    //finds the directions a given character can connect to, as coords
    public Coord[] connections(char s) {
        switch (s) {
            case '|' -> {
                return new Coord[]{Coord.UP, Coord.DOWN};
            }
            case '-' -> {
                return new Coord[]{Coord.LEFT, Coord.RIGHT};
            }
            case 'L' -> {
                return new Coord[]{Coord.UP, Coord.RIGHT};
            }
            case 'J' -> {
                return new Coord[]{Coord.UP, Coord.LEFT};
            }
            case '7' -> {
                return new Coord[]{Coord.LEFT, Coord.DOWN};
            }
            case 'F' -> {
                return new Coord[]{Coord.RIGHT, Coord.DOWN};
            }
        }
        return null;
    }

    @Override
    public String part2() {
        String[] lines = input.split("\n");
        char[][] grid = new char[lines[0].length()][lines.length];
        Coord start = new Coord();

        for (int y = 0; y < lines.length; y++) {
            for (int x = 0; x < lines[0].length(); x++) {
                grid[x][y] = lines[y].charAt(x);
                if (grid[x][y] == 'S') start = new Coord(x, y);
            }
        }

        //arbitrarily pick one of the two locations next to S to begin with
        //either doesnt matter, as we're just traversing around the loop and counting
        Coord cur = new Coord();
        startfind:
        for (Coord c : new Coord[]{Coord.UP, Coord.DOWN, Coord.LEFT, Coord.RIGHT}) {
            Coord newStart = new Coord(start.x + c.x, start.y + c.y);

            for (Coord dir : connections(grid[newStart.x][newStart.y])) {
                if (newStart.sum(dir).equals(start)) {
                    cur = newStart;
                    break startfind;
                }
            }
        }

        //keep a cur coord and a prev coord - at every step, pick the direction to travel that
        //doesn't point us back towards prev, then set prev to cur and cur to next
        Coord prev = start;
        //count starts at 1 cause we're starting at point adjacent to S
        int count = 1;

        //track vertices for Shoelace
        ArrayList<Coord> vertices = new ArrayList<>();
        vertices.add(start);

        while (!cur.equals(start)) {
            Coord[] dirs = connections(grid[cur.x][cur.y]);
            //if not a vertical or horizontal pipe (i.e. corner/vertex)
            if (!(Arrays.equals(dirs, new Coord[]{Coord.UP, Coord.DOWN}) || Arrays.equals(dirs, new Coord[]{Coord.LEFT, Coord.RIGHT}))) {
                vertices.add(cur);
            }
            //if summing with dirs[0] takes us backwards, sum with dirs[1]. otherwise, sum with dirs[0]
            Coord next = (cur.sum(dirs[0]).equals(prev) ? cur.sum(dirs[1]) : cur.sum(dirs[0]));
            prev = cur;
            cur = next;
            count++;
        }

        //last vertex needs to wrap back to start
        vertices.add(start);

        //shoelace theorem
        int innerarea = 0;
        for (int i = 1; i < vertices.size(); i++) {
            Coord b = vertices.get(i);
            Coord a = vertices.get(i - 1);
            innerarea += (a.x * b.y) - (a.y * b.x);
        }

        //subtract perimeter
        innerarea -= count;

        //pick's theorem
        return Integer.toString(innerarea / 2 + 1);
    }
}
