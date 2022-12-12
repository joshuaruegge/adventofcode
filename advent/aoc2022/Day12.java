package advent.aoc2022;

import advent.utilities.general.Coord;
import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class Day12 implements IDay {

    static String input;

    static HashMap<Coord,Integer> heights;

    @Override
    public String part1() {
        heights = new HashMap<Coord,Integer>();
        String[] lines = input.split("\n");
        Coord start = null;
        Coord end = null;
        //go over input, populating heights and noting start and end
        for(int y = 0; y < lines.length; y++) {
            String line = lines[y];
            for(int x = 0; x < line.length(); x++) {
                if(line.charAt(x) == 'S')
                    start = new Coord(x,y);
                else if(line.charAt(x) == 'E')
                    end = new Coord(x,y);
                else
                    heights.put(new Coord(x,y), (int) line.charAt(x));
            }
        }
        //start is  one lower than lowest, end is one higher than highest
        heights.put(start,'a' - 1);
        heights.put(end,'z' + 1);
        return Integer.toString(pathfind(start,end));
    }

    //simple BFS pathfinding method
    public int pathfind(Coord start, Coord end) {
        HashMap<Coord,Integer> gCost = new HashMap<Coord,Integer>();
        HashMap<Coord,Coord> parent = new HashMap<Coord,Coord>();
        LinkedList<Coord> queue = new LinkedList<>();
        gCost.put(start,0);
        queue.add(start);
        while(queue.size() > 0) {
            Coord cur = queue.poll();
            if(cur.equals(end)) {
                ArrayList<Coord> path = new ArrayList<>();
                while(parent.containsKey(cur)) {
                    path.add(cur);
                    cur = parent.get(cur);
                }
                return path.size();
            }
            for(Coord c : cur.directNeighbors()) {
                //skip if outside bounds or if height is more than one above current
                if(!heights.containsKey(c) || heights.get(c) > heights.get(cur) + 1)
                    continue;
                int tentativeG = gCost.get(cur) + 1;
                if(tentativeG < gCost.getOrDefault(c,Integer.MAX_VALUE)) {
                    gCost.put(c,tentativeG);
                    parent.put(c,cur);
                    queue.add(c);
                }
            }
        }
        return Integer.MAX_VALUE;
    }

    @Override
    public String part2() {
        heights = new HashMap<Coord,Integer>();
        String[] lines = input.split("\n");
        Coord start = null;
        Coord end = null;
        for(int y = 0; y < lines.length; y++) {
            String line = lines[y];
            for(int x = 0; x < line.length(); x++) {
                if(line.charAt(x) == 'S')
                    start = new Coord(x,y);
                else if(line.charAt(x) == 'E')
                    end = new Coord(x,y);
                else
                    heights.put(new Coord(x,y), (int) line.charAt(x));
            }
        }
        heights.put(start,'a' - 1);
        heights.put(end,'z' + 1);
        //now, go over all possible locations - if location is an 'a', shortest path is minimum of existing shortest and fastest path from cur to end
        int shortest = Integer.MAX_VALUE;
        for(Coord c : heights.keySet()) {
            if(heights.get(c) == 'a')
                shortest = Math.min(shortest,pathfind(c,end));
        }
        return Integer.toString(shortest);
    }

    public static void main(String[] args) {
        input = Input.fetchInput(2022,12);
        DayRunner.run(new Day12());
    }
}
