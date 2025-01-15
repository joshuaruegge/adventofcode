package advent.aoc2024;

import advent.utilities.general.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class Day20 implements IDay {

    static String input;

    static String testInput = "###############\n" +
            "#...#...#.....#\n" +
            "#.#.#.#.#.###.#\n" +
            "#S#...#.#.#...#\n" +
            "#######.#.#.###\n" +
            "#######.#.#...#\n" +
            "#######.#.###.#\n" +
            "###..E#...#...#\n" +
            "###.#######.###\n" +
            "#...###...#...#\n" +
            "#.#####.#.###.#\n" +
            "#.#...#.#.#...#\n" +
            "#.#.#.#.#.#.###\n" +
            "#...#...#...###\n" +
            "###############";

    public static void main(String[] args) {
        input = Input.fetchInput(2024, 20);
        DayRunner.run(new Day20());
    }

    @Override
    public String part1() {
        long total = 0;

        String[] lines = input.split("\n");

        HashSet<Coord> walls = new HashSet<Coord>();

        Coord start = new Coord();
        Coord end = new Coord();

        for(int row = 0; row < lines.length; row++) {
            for(int col = 0; col < lines[0].length(); col++) {
                char c = lines[row].charAt(col);
                Coord cur = new Coord(row, col);
                switch(c) {
                    case '#' -> walls.add(cur);
                    case 'S' -> start = cur;
                    case 'E' -> end = cur;
                }
            }
        }

        HashMap<Coord, Integer> distsFromEnd = new HashMap<>();
        LinkedList<Coord> queue = new LinkedList<>();
        queue.add(end);
        distsFromEnd.put(end, 0);

        while(!queue.isEmpty()) {
            Coord cur = queue.pop();

            for(Coord c : cur.directNeighbors()) {
                if(!walls.contains(c) && !distsFromEnd.containsKey(c)) {
                    distsFromEnd.put(c, distsFromEnd.get(cur) + 1);
                    queue.add(c);
                }
            }
        }

        HashMap<Pair<Coord, Coord>, Integer> cheats = new HashMap<>();

        for(int row = 0; row < lines.length; row++) {
            for(int col = 0; col < lines[0].length(); col++) {
                Coord cur = new Coord(row, col);
                if(walls.contains(cur))
                    continue;


                LinkedList<Pair<Coord, Integer>> skips = new LinkedList<>();
                skips.add(new Pair<>(cur, 2));

                while(!skips.isEmpty()) {
                    //System.out.println(skips);
                    Pair<Coord, Integer> skip = skips.pop();

                    if(!walls.contains(skip.key)) {
                        //int skipCost = distsFromEnd.get(cur) + (2 - skip.value);

                        //int timeSave = distsFromEnd.getOrDefault(skip.key,0) - skipCost;

                        int timeSave = distsFromEnd.get(cur) - distsFromEnd.get(skip.key) - (2-skip.value);
                        //System.out.println(distsFromEnd.getOrDefault(skip.key, 0));
                        //System.out.println(skipCost);

                        //System.out.println(distsFromEnd.getOrDefault(skip.key,0) - skipCost);

                        if(timeSave >= 100) {
                            //System.out.println(distsFromEnd.getOrDefault(skip.key,0) - skipCost);
                            cheats.put(new Pair<>(cur, skip.key), timeSave);
                            total++;
                        }


                        if(skip.value < 2)
                            continue;


                    }

                    if(skip.value > 0) {
                        for(Coord c : skip.key.directNeighbors()) {
                            if(c.x >= 0 && c.x < lines.length && c.y >= 0 && c.y < lines[0].length())
                                skips.add(new Pair<>(c, skip.value-1));
                        }
                    }
                }


            }
        }

        HashMap<Integer, Integer> counts = new HashMap<>();
        for(Pair<Coord, Coord> p : cheats.keySet())
            counts.put(cheats.get(p), counts.getOrDefault(cheats.get(p), 0) + 1);

        return Long.toString(total);
    }

    @Override
    public String part2() {
        long total = 0;

        String[] lines = input.split("\n");

        HashSet<Coord> walls = new HashSet<Coord>();

        Coord start = new Coord();
        Coord end = new Coord();

        for(int row = 0; row < lines.length; row++) {
            for(int col = 0; col < lines[0].length(); col++) {
                char c = lines[row].charAt(col);
                Coord cur = new Coord(row, col);
                switch(c) {
                    case '#' -> walls.add(cur);
                    case 'S' -> start = cur;
                    case 'E' -> end = cur;
                }
            }
        }

        HashMap<Coord, Integer> distsFromEnd = new HashMap<>();
        LinkedList<Coord> queue = new LinkedList<>();
        queue.add(end);
        distsFromEnd.put(end, 0);

        while(!queue.isEmpty()) {
            Coord cur = queue.pop();

            for(Coord c : cur.directNeighbors()) {
                if(!walls.contains(c) && !distsFromEnd.containsKey(c)) {
                    distsFromEnd.put(c, distsFromEnd.get(cur) + 1);
                    queue.add(c);
                }
            }
        }

        HashMap<Pair<Coord, Coord>, Integer> cheats = new HashMap<>();

        //HashMap<Integer, HashSet<Pair<Coord, Coord>>> cheats = new HashMap<>();
        for(int row = 0; row < lines.length; row++) {
            for(int col = 0; col < lines[0].length(); col++) {
                Coord cur = new Coord(row, col);
                if(walls.contains(cur))
                    continue;

                LinkedList<Pair<Coord, Integer>> skips = new LinkedList<>();
                skips.add(new Pair<>(cur, 20));

                while(!skips.isEmpty()) {
                    Pair<Coord, Integer> skip = skips.pop();

                    if(!walls.contains(skip.key)) {
                        //int skipCost = distsFromEnd.get(cur) + (2 - skip.value);

                        //int timeSave = distsFromEnd.getOrDefault(skip.key,0) - skipCost;

                        int timeSave = distsFromEnd.get(cur) - distsFromEnd.get(skip.key) - (20-skip.value);
                        //System.out.println(distsFromEnd.getOrDefault(skip.key, 0));
                        //System.out.println(skipCost);

                        //System.out.println(distsFromEnd.getOrDefault(skip.key,0) - skipCost);

                        if(timeSave >= 100) {
                            //System.out.println(distsFromEnd.getOrDefault(skip.key,0) - skipCost);

                            if(timeSave > cheats.getOrDefault(new Pair<>(cur, skip.key), 0))
                                cheats.put(new Pair<>(cur, skip.key), timeSave);
                            total++;
                        }


                        /*
                        if(skip.value < 20)
                            continue;
                        */

                    }

                    if(skip.value > 0) {
                        for(Coord c : skip.key.directNeighbors()) {
                            if(c.x >= 0 && c.x < lines.length && c.y >= 0 && c.y < lines[0].length() && !skips.contains(new Pair<>(c, skip.value-1)))
                                skips.add(new Pair<>(c, skip.value-1));
                        }
                    }
                }


            }
        }


        System.out.println(cheats.size());
        //System.out.println(cheats);
        HashMap<Integer, Integer> counts = new HashMap<>();
        for(Pair<Coord, Coord> p : cheats.keySet())
            counts.put(cheats.get(p), counts.getOrDefault(cheats.get(p), 0) + 1);
        //System.out.println(counts);



        return Long.toString(total);
    }
}
