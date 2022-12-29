package advent.aoc2022;

import advent.utilities.general.Coord;
import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

import java.util.*;

public class Day15 implements IDay {

    static String input;

    @java.lang.Override
    public String part1() {
        HashMap<Coord, Coord> sensorAndBeacon = new HashMap<>();
        for(String s : input.split("\n")) {
            String[] words = s.split(", |: | ");
            Coord sensor = new Coord(Integer.parseInt(words[2].split("=")[1]), Integer.parseInt(words[3].split("=")[1]));
            Coord beacon = new Coord(Integer.parseInt(words[8].split("=")[1]), Integer.parseInt(words[9].split("=")[1]));
            sensorAndBeacon.put(sensor,beacon);
        }
        ArrayList<Coord> ranges = mergeRanges(noBeaconRanges(sensorAndBeacon,2000000));
        return Integer.toString(ranges.get(0).y - ranges.get(0).x);
    }

    @java.lang.Override
    public String part2() {
        HashMap<Coord, Coord> sensorAndBeacon = new HashMap<>();
        for(String s : input.split("\n")) {
            String[] words = s.split(", |: | ");
            Coord sensor = new Coord(Integer.parseInt(words[2].split("=")[1]), Integer.parseInt(words[3].split("=")[1]));
            Coord beacon = new Coord(Integer.parseInt(words[8].split("=")[1]), Integer.parseInt(words[9].split("=")[1]));
            sensorAndBeacon.put(sensor,beacon);
        }

        //iterate over rows, calculating range
        //a good trick for large-iteration in AOC problems is to start from the end and go back
        //the answer is often near the end in order to make brute-force attempts from the start take as long as possible
        for(int y = 4000000; y > 0; y--) {
            ArrayList<Coord> ranges = mergeRanges(noBeaconRanges(sensorAndBeacon,y));
            //if range has multiple ranges, we have a gap! now to find it
            if(ranges.size() > 1) {
                //locate first x coordinate that is outside all ranges
                xFind:
                for(int x = 0; x < 4000000; x++) {
                    for(Coord c : ranges) {
                        if(x >= c.x && x <= c.y) {
                            continue xFind;
                        }
                    }
                    return Long.toString(x * 4000000L + y);
                }
            }
        }
        return null;
    }

    //collects list of ranges that cannot contain a beacon on row Y
    public ArrayList<Coord> noBeaconRanges(HashMap<Coord,Coord> map, int y) {
        ArrayList<Coord> ranges = new ArrayList<>();
        for(Coord c : map.keySet()) {
            int dist = c.dist(map.get(c));
            //possible range of X values on row is max manhattan distance minus distance portion used for y
            int xRange = dist - Math.abs(c.y - y);
            //if xRange is greater than zero, this beacon has a range on this row we need to add
            if(xRange > 0) {
                ranges.add(new Coord(c.x - xRange, c.x + xRange));
            }
        }
        return ranges;
    }

    //tries to remove overlaps in ranges and condense down to one coord
    public ArrayList<Coord> mergeRanges(ArrayList<Coord> ranges) {
        //make sure in proper order
        ranges.sort(Comparator.comparingInt(o -> o.x));

        //create return list, stick first coord in
        ArrayList<Coord> newRanges = new ArrayList<>();
        newRanges.add(ranges.get(0));
        for(int i = 1; i < ranges.size(); i++) {
            Coord range = ranges.get(i);
            Coord end = newRanges.get(newRanges.size() - 1);
            //try to adjust first range's bounds to include overlap
            if(range.y >= end.x && range.y <= end.y) {
                newRanges.get(newRanges.size()-1).x = Math.min(range.x,end.x);
            }
            if(range.x >= end.x && range.x <= end.y) {
                newRanges.get(newRanges.size() - 1).y = Math.max(range.y,end.y);
            }
            //if no overlap whatsoever, append to list
            if(!(range.x >= end.x && range.x <= end.y) && !(range.y >= end.x && range.y <= end.y)) {
                newRanges.add(0,range);
            }
        }
        return newRanges;
    }

    public static void main(String[] args) {
        input = Input.fetchInput(2022,15);
        DayRunner.run(new Day15());
    }
}
