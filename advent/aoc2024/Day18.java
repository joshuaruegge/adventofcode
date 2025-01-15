package advent.aoc2024;

import advent.utilities.general.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class Day18 implements IDay {

    static String input;

    static String testInput = "";

    public static void main(String[] args) {
        input = Input.fetchInput(2024, 18);
        DayRunner.run(new Day18());
    }

    @Override
    public String part1() {
        String[] lines = input.split("\n");

        HashSet<Coord> bytes = new HashSet<Coord>();
        for(int i = 0; i < 1024; i++) {
            String[] nums = lines[i].split(",");
            bytes.add(new Coord(Integer.parseInt(nums[0]), Integer.parseInt(nums[1])));
        }

        return Integer.toString(dist(bytes));
    }

    public int dist(HashSet<Coord> bytes) {

        Coord dest = new Coord(70,70);

        LinkedList<Coord> queue = new LinkedList<>();
        queue.add(new Coord(0,0));

        HashSet<Coord> seen = new HashSet<>();

        HashMap<Coord, Integer> distFromStart = new HashMap<>();
        distFromStart.put(new Coord(0,0), 0);

        while(!queue.isEmpty()) {
            Coord cur = queue.pop();
            if(!seen.add(cur))
                continue;

            if(cur.equals(dest)) {
                break;
            }

            for(Coord c : cur.directNeighbors()) {
                if(!seen.contains(c) && c.x > -1 && c.x < 71 && c.y > -1 && c.y < 71 && !bytes.contains(c)) {
                    distFromStart.put(c, distFromStart.get(cur) + 1);
                    queue.add(c);
                }
            }
        }

        return distFromStart.getOrDefault(dest,-1);
    }

    @Override
    public String part2() {
        String[] lines = input.split("\n");

        HashSet<Coord> bytes = new HashSet<>();
        for(int i = 0; i < 1024; i++) {
            String[] nums = lines[i].split(",");
            bytes.add(new Coord(Integer.parseInt(nums[0]), Integer.parseInt(nums[1])));
        }

        for(int counter = 1024; counter < lines.length; counter++) {
            String[] nums = lines[counter].split(",");
            bytes.add(new Coord(Integer.parseInt(nums[0]), Integer.parseInt(nums[1])));
            if(dist(bytes) == -1)
                return lines[counter];
        }
        return null;
    }
}
