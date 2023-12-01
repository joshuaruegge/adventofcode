package advent.aoc2015;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

import java.util.ArrayList;

public class Day14 implements IDay {

    static String input;

    final int FLIGHT_TIME = 2503;

    public static void main(String[] args) {
        input = Input.fetchInput(2015, 14);
        DayRunner.run(new Day14());
    }

    @Override
    public String part1() {
        //store reindeer as 3-item int[] (speed, flight time, rest time)
        ArrayList<int[]> deers = new ArrayList<>();
        for (String s : input.split("\n")) {
            String[] parts = s.split(" ");
            deers.add(new int[]{Integer.parseInt(parts[3]), Integer.parseInt(parts[6]), Integer.parseInt(parts[13])});
        }

        int best = 0;
        for (int[] deer : deers) {
            int distance = 0;
            //time it takes for a deer to complete one run-rest cycle
            int cycleLength = deer[1] + deer[2];
            //number of full cycles completed over the time interval
            int numCycles = FLIGHT_TIME / cycleLength;
            //add total distance covered by full cycles
            //speed * time running * number of cycles
            distance += deer[0] * deer[1] * numCycles;
            //determine remainder of time
            int leftover = FLIGHT_TIME % cycleLength;
            //if remainder of time is less than run time, add remainder
            if (leftover < deer[1]) {
                distance += deer[0] * leftover;
            }
            //otherwise, deer is in resting period, and ran for full run length
            else {
                distance += deer[1] * deer[0];
            }
            if (distance > best)
                best = distance;
        }
        //return furthest distance
        return Integer.toString(best);
    }

    @Override
    public String part2() {
        //now, it gets just a smidge more complicated
        //store reindeer as 3-item int[]
        ArrayList<int[]> deers = new ArrayList<>();
        for (String s : input.split("\n")) {
            String[] parts = s.split(" ");
            deers.add(new int[]{Integer.parseInt(parts[3]), Integer.parseInt(parts[6]), Integer.parseInt(parts[13])});
        }
        //now, keep track of scores as an array
        //still index-linked
        int[] scores = new int[deers.size()];
        //additionally, track distances each reindeer has covered
        int[] distances = new int[deers.size()];
        for (int i = 0; i < FLIGHT_TIME; i++) {
            //travel deer forwards if necessary
            for (int j = 0; j < deers.size(); j++) {
                int[] deer = deers.get(j);
                //calculate cycle length
                int cycleLength = deer[1] + deer[2];
                //if remainder value is less than or equal to run time
                //deer is running so increment distance by speed
                if ((i % cycleLength) < deer[1]) {
                    distances[j] += deer[0];
                }
            }
            //find highest distance of any deer
            int best = 0;
            for (int distance : distances) {
                if (distance > best) {
                    best = distance;
                }
            }
            //to account for ties, increment all scores that are equal to best dist
            for (int j = 0; j < scores.length; j++)
                if (distances[j] == best)
                    scores[j]++;

        }
        //calculate final maximum score
        int max = 0;
        for (int i : scores) {
            if (i > max)
                max = i;
        }
        return Integer.toString(max);
    }

}
