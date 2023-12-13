package advent.aoc2023;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

import java.util.ArrayList;

public class Day06 implements IDay {

    static String input;

    public static void main(String[] args) {
        input = Input.fetchInput(2023, 6);
        DayRunner.run(new Day06());
    }

    @Override
    public String part1() {
        long mult = 1;
        String[] time = input.split("\n")[0].split("\\s+");
        String[] distance = input.split("\n")[1].split("\\s+");
        for(int i = 1; i < time.length; i++) {
            int count = 0;
            int t = Integer.parseInt(time[i]);
            int d = Integer.parseInt(distance[i]);
            for(int hold = 0; hold < t; hold++) {
                if(t*hold-hold*hold>d) {
                    count++;
                }
            }
            mult *= count;
        }
        return Long.toString(mult);
    }

    @Override
    public String part2() {
        String[] time = input.split("\n")[0].split("\\s+");
        String[] distance = input.split("\n")[1].split("\\s+");

        String fullTime = "";
        String fullDist = "";
        for(int i = 1; i < time.length; i++) {
            fullTime += time[i];
            fullDist += distance[i];
        }

        long t = Long.parseLong(fullTime);
        long d = Long.parseLong(fullDist);

        long firstZero = (long) Math.ceil((t - Math.sqrt(t*t-4*d))/2);
        long secondZero = (long) Math.floor((t + Math.sqrt(t*t-4*d))/2);

        System.out.println(firstZero + " " + secondZero + " " + (secondZero - firstZero));
        return Long.toString(secondZero-firstZero+1);
    }
}
