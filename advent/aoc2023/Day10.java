package advent.aoc2023;

import advent.utilities.general.Coord;
import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;
import advent.utilities.utils2022.PacketList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

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

        for(int y = 0; y < lines.length; y++) {
            for(int x = 0; x < lines[0].length(); x++) {
                grid[x][y] = lines[y].charAt(x);
                if(grid[x][y] == 'S')
                    start = new Coord(x,y);
            }
        }
        ArrayList<Coord> startAdj = new ArrayList<>();

        for(Coord adj : start.directNeighbors()) {
            if(adj.x < 0 || adj.y < 0 || adj.x >= grid.length || adj.y >= grid[0].length)
                continue;
            ArrayList<Coord> adjCon = connections(grid[adj.x][adj.y]);
            for(Coord c : adjCon) {
                if(adj.sum(c).equals(start)) {
                    startAdj.add(new Coord(-start.x, -start.y).sum(adj));
                }
            }
        }

        char sReplace;
        //replace S with corresponding character (helps later)
        if(startAdj.contains(Coord.UP)) {
            if(startAdj.contains(Coord.RIGHT)) {
                sReplace = 'L';
            } else if (startAdj.contains(Coord.LEFT)) {
                sReplace = 'J';
            } else
                sReplace = '|';
        } else if(startAdj.contains(Coord.DOWN)) {
            if(startAdj.contains(Coord.LEFT)) {
                sReplace = '7';
            } else {
                sReplace = 'F';
            }
        } else {
            sReplace = '-';
        }
        grid[start.x][start.y] = sReplace;

        HashSet<Coord> mainLoop = new HashSet<>();
        mainLoop.add(start);
        Coord prev = start;
        //arbitrarily pick direction 0? shouldn't matter - its all one loop
        Coord cur = start.sum(startAdj.get(0));
        do {
            mainLoop.add(cur);
            ArrayList<Coord> adjDirs = connections(grid[cur.x][cur.y]);
            Coord next;
            if(cur.sum(adjDirs.get(0)).equals(prev))
                next = cur.sum(adjDirs.get(1));
            else
                next = cur.sum(adjDirs.get(0));
            prev = cur;
            cur = next;
        } while(!cur.equals(start));

        //because loop does not cross over itself and is continuous
        //and we are counting distance along the loop,
        //furthest point will be equally distant in both directions from start
        //and consequently will be (total coords in loop)/2 steps away
        return Integer.toString(mainLoop.size()/2);
    }

    public ArrayList<Coord> connections(char s) {
        ArrayList<Coord> con = new ArrayList<>();
        switch (s) {
            case '|' -> {
                con.add(Coord.UP);
                con.add(Coord.DOWN);
            }
            case '-' -> {
                con.add(Coord.LEFT);
                con.add(Coord.RIGHT);
            }
            case 'L' -> {
                con.add(Coord.UP);
                con.add(Coord.RIGHT);
            }
            case 'J' -> {
                con.add(Coord.UP);
                con.add(Coord.LEFT);
            }
            case '7' -> {
                con.add(Coord.LEFT);
                con.add(Coord.DOWN);
            }
            case 'F' -> {
                con.add(Coord.RIGHT);
                con.add(Coord.DOWN);
            }
        }
        return con;
    }

    @Override
    public String part2() {
        String[] lines = input.split("\n");
        char[][] grid = new char[lines[0].length()][lines.length];
        Coord start = new Coord();

        for(int y = 0; y < lines.length; y++) {
            for(int x = 0; x < lines[0].length(); x++) {
                grid[x][y] = lines[y].charAt(x);
                if(grid[x][y] == 'S')
                    start = new Coord(x,y);
            }
        }
        ArrayList<Coord> startAdj = new ArrayList<>();

        for(Coord adj : start.directNeighbors()) {
            if(adj.x < 0 || adj.y < 0 || adj.x >= grid.length || adj.y >= grid[0].length)
                continue;
            ArrayList<Coord> adjCon = connections(grid[adj.x][adj.y]);
            for(Coord c : adjCon) {
                if(adj.sum(c).equals(start)) {
                    startAdj.add(new Coord(-start.x, -start.y).sum(adj));
                }
            }
        }

        char sReplace;
        //replace S with corresponding character (helps later)
        if(startAdj.contains(Coord.UP)) {
            if(startAdj.contains(Coord.RIGHT)) {
                sReplace = 'L';
            } else if (startAdj.contains(Coord.LEFT)) {
                sReplace = 'J';
            } else
                sReplace = '|';
        } else if(startAdj.contains(Coord.DOWN)) {
            if(startAdj.contains(Coord.LEFT)) {
                sReplace = '7';
            } else {
                sReplace = 'F';
            }
        } else {
            sReplace = '-';
        }
        grid[start.x][start.y] = sReplace;

        HashSet<Coord> mainLoop = new HashSet<>();
        mainLoop.add(start);
        Coord prev = start;
        //arbitrarily pick direction 0? shouldn't matter - its all one loop
        Coord cur = start.sum(startAdj.get(0));
        do {
            mainLoop.add(cur);
            ArrayList<Coord> adjDirs = connections(grid[cur.x][cur.y]);
            Coord next;
            if(cur.sum(adjDirs.get(0)).equals(prev))
                next = cur.sum(adjDirs.get(1));
            else
                next = cur.sum(adjDirs.get(0));
            prev = cur;
            cur = next;
        } while(!cur.equals(start));

        //examining the examples, a location is not in the loop if it is left-bound by an even number of
        //'|','J',or'L' characters, and in the loop if it is bound by an odd number of characters
        //something about parity? idk, it seems to work though
        int inside = 0;
        for(int x = 0; x < grid.length; x++) {
            for(int y = 0; y < grid[0].length; y++) {
                if(mainLoop.contains(new Coord(x,y)))
                    continue;
                int tot = 0;
                for(int x1 = 0; x1 < x; x1++) {
                    char c = grid[x1][y];
                    if((c == '|' || c == 'J' || c == 'L') && mainLoop.contains(new Coord(x1, y)))
                        tot++;
                }
                inside += tot%2;
            }
        }

        return Integer.toString(inside);
    }
}
