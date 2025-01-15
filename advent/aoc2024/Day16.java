package advent.aoc2024;

import advent.utilities.general.*;

import java.util.*;

public class Day16 implements IDay {

    static String input;

    static String testInput = "###############\n" +
            "#.......#....E#\n" +
            "#.#.###.#.###.#\n" +
            "#.....#.#...#.#\n" +
            "#.###.#####.#.#\n" +
            "#.#.#.......#.#\n" +
            "#.#.#####.###.#\n" +
            "#...........#.#\n" +
            "###.#.#####.#.#\n" +
            "#...#.....#.#.#\n" +
            "#.#.#.###.#.#.#\n" +
            "#.....#...#.#.#\n" +
            "#.###.#.#.#.#.#\n" +
            "#S..#.....#...#\n" +
            "###############";

    public static void main(String[] args) {
        input = Input.fetchInput(2024, 16);
        DayRunner.run(new Day16());
    }

    @Override
    public String part1() {
        long total = 0;

        HashSet<Coord> walls = new HashSet<>();

        String[] lines = input.split("\n");

        Coord start = new Coord();
        Coord dest = new Coord();

        for(int row = 0; row < lines.length; row++) {
            for(int col = 0; col < lines[0].length(); col++) {
                char c = lines[row].charAt(col);
                if(c == '#') {
                    walls.add(new Coord(row, col));
                } else if (c == 'S') {
                    start = new Coord(row, col);
                } else if(c == 'E') {
                    dest = new Coord(row, col);
                }
            }
        }

        HashMap<Pair<Coord, Coord>, Integer> gScore = new HashMap<>();

        Coord finalDest = dest;
        //PriorityQueue<Pair<Coord, Coord>> queue = new PriorityQueue<>(Comparator.comparingInt(o -> gScore.getOrDefault(o, 0) + o.key.dist(finalDest) * 1000));
        LinkedList<Pair<Coord, Coord>> queue = new LinkedList<>();

        queue.add(new Pair<>(start, Coord.DOWN));
        gScore.put(new Pair<>(start, Coord.DOWN), 0);

        while(!queue.isEmpty()) {
            Pair<Coord,Coord> cur = queue.poll();

            if(gScore.getOrDefault(cur, 0) > bestDest(gScore, dest))
                continue;


            if(cur.key.equals(dest)) {
                for(Coord d : Coord.DIRS) {
                    gScore.put(new Pair<>(dest, d), Math.min(gScore.getOrDefault(new Pair<>(dest, d), Integer.MAX_VALUE), gScore.get(cur)));
                }
                continue;
            }


            //straight
            if(!walls.contains(cur.key.sum(cur.value))) {
                int tentativeG = gScore.get(cur) + 1;
                Pair<Coord, Coord> next = new Pair<> (cur.key.sum(cur.value), cur.value);
                if(tentativeG < gScore.getOrDefault(next, Integer.MAX_VALUE)) {
                    gScore.put(next, tentativeG);
                    queue.add(next);
                }
            }

            int tentativeG = gScore.get(cur) + 1000;
            //turn left
            Pair<Coord, Coord> nextLeft = new Pair<> (cur.key, cur.value.right());
            if(tentativeG < gScore.getOrDefault(nextLeft, Integer.MAX_VALUE)) {
                gScore.put(nextLeft, tentativeG);
                queue.add(nextLeft);
            }
            Pair<Coord, Coord> nextRight = new Pair<> (cur.key, cur.value.left());
            if(tentativeG < gScore.getOrDefault(nextRight, Integer.MAX_VALUE)) {
                gScore.put(nextRight, tentativeG);
                queue.add(nextRight);
            }
        }

        return Integer.toString(bestDest(gScore, dest));
    }

    public int bestDest(HashMap<Pair<Coord, Coord>, Integer> g, Coord dest) {
        int best = Integer.MAX_VALUE;
        for(Coord d : Coord.DIRS)
            best = Math.min(best, g.getOrDefault(new Pair<>(dest, d), Integer.MAX_VALUE));
        return best;
    }

    @Override
    public String part2() {
        HashSet<Coord> walls = new HashSet<>();

        String[] lines = input.split("\n");

        Coord start = new Coord();
        Coord dest = new Coord();

        for(int row = 0; row < lines.length; row++) {
            for(int col = 0; col < lines[0].length(); col++) {
                char c = lines[row].charAt(col);
                if(c == '#') {
                    walls.add(new Coord(row, col));
                } else if (c == 'S') {
                    start = new Coord(row, col);
                } else if(c == 'E') {
                    dest = new Coord(row, col);
                }
            }
        }

        HashMap<Pair<Coord, Coord>, Integer> gScore = new HashMap<>();

        HashMap<Pair<Coord, Coord>, HashSet<Pair<Coord, Coord>>> parents = new HashMap<>();

        Coord finalDest = dest;
        //PriorityQueue<Pair<Coord, Coord>> queue = new PriorityQueue<>(Comparator.comparingInt(o -> gScore.getOrDefault(o, 0) + o.key.dist(finalDest) * 1000));
        LinkedList<Pair<Coord, Coord>> queue = new LinkedList<>();

        queue.add(new Pair<>(start, Coord.DOWN));
        gScore.put(new Pair<>(start, Coord.DOWN), 0);

        while(!queue.isEmpty()) {
            Pair<Coord,Coord> cur = queue.poll();

            if(gScore.getOrDefault(cur, 0) > bestDest(gScore, dest))
                continue;

            //straight
            if(!walls.contains(cur.key.sum(cur.value))) {
                int tentativeG = gScore.get(cur) + 1;
                Pair<Coord, Coord> next = new Pair<> (cur.key.sum(cur.value), cur.value);
                if(tentativeG == gScore.getOrDefault(next, Integer.MAX_VALUE)) {
                    parents.computeIfAbsent(next, k -> new HashSet<>()).add(cur);
                }
                if(tentativeG < gScore.getOrDefault(next, Integer.MAX_VALUE)) {
                    parents.computeIfAbsent(next, k -> new HashSet<>()).clear();
                    parents.get(next).add(cur);
                    gScore.put(next, tentativeG);
                    queue.add(next);
                }
            }

            int tentativeG = gScore.get(cur) + 1000;
            //turn left
            Pair<Coord, Coord> nextLeft = new Pair<> (cur.key, cur.value.right());
            if(tentativeG == gScore.getOrDefault(nextLeft, Integer.MAX_VALUE)) {
                parents.computeIfAbsent(nextLeft, k -> new HashSet<>()).add(cur);
            }
            if(tentativeG < gScore.getOrDefault(nextLeft, Integer.MAX_VALUE)) {
                parents.computeIfAbsent(nextLeft, k -> new HashSet<>()).clear();
                parents.get(nextLeft).add(cur);
                gScore.put(nextLeft, tentativeG);
                queue.add(nextLeft);
            }
            Pair<Coord, Coord> nextRight = new Pair<> (cur.key, cur.value.left());
            if(tentativeG == gScore.getOrDefault(nextRight, Integer.MAX_VALUE)) {
                parents.computeIfAbsent(nextRight, k -> new HashSet<>()).add(cur);
            }
            if(tentativeG < gScore.getOrDefault(nextRight, Integer.MAX_VALUE)) {
                parents.computeIfAbsent(nextRight, k -> new HashSet<>()).clear();
                parents.get(nextRight).add(cur);
                gScore.put(nextRight, tentativeG);
                queue.add(nextRight);
            }
        }

        HashSet<Coord> distinct = new HashSet<>();
        for(Coord d : Coord.DIRS) {
            recurse(parents, new Pair<>(dest, d), distinct);
        }

        return Integer.toString(distinct.size());
    }

    public void recurse(HashMap<Pair<Coord, Coord>, HashSet<Pair<Coord, Coord>>> parents, Pair<Coord, Coord> cur, HashSet<Coord> distinct) {
        for(Pair<Coord, Coord> p : parents.getOrDefault(cur, new HashSet<>())) {
            distinct.add(p.key);
            recurse(parents, p, distinct);
        }
    }
}
