package advent.aoc2024;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

import java.util.HashMap;
import java.util.LinkedList;

public class Day11 implements IDay {

    static String input;

    static String testInput = "125 17";

    public static void main(String[] args) {
        input = Input.fetchInput(2024, 11);
        DayRunner.run(new Day11());
    }

    @Override
    public String part1() {
        int total = 0;

        HashMap<Long, Integer> stones = new HashMap<>();

        for(String s : input.trim().split(" "))
            stones.put(Long.parseLong(s),1);

        for(int blink = 0; blink < 25; blink++) {
            HashMap<Long, Integer> newStones = new HashMap<>();

            //handle zeroes
            newStones.put(1l,stones.getOrDefault(0l,0));

            for(long key : stones.keySet()) {
                if(key == 0) {
                    newStones.put(1l, stones.get(0l));
                }
                else if((int) (Math.log10(key)+1) % 2 == 0) {
                    String num = Long.toString(key);
                    long upper = Long.parseLong(num.substring(0, num.length()/2));
                    long lower = Long.parseLong(num.substring(num.length()/2));

                    int oldStone = stones.get(key);
                    newStones.put(lower,newStones.getOrDefault(lower, 0) + oldStone);
                    newStones.put(upper, newStones.getOrDefault(upper, 0) + oldStone);
                } else {
                    newStones.put(key*2024, newStones.getOrDefault(key*2024, 0) + stones.get(key));
                }
            }

            stones = newStones;

            System.out.println(stones);
        }

        return Integer.toString(stones.values().stream().mapToInt(Integer::intValue).sum());
    }

    @Override
    public String part2() {
        int total = 0;

        HashMap<Long, Long> stones = new HashMap<>();

        for(String s : input.trim().split(" "))
            stones.put(Long.parseLong(s),1l);

        for(int blink = 0; blink < 75; blink++) {
            HashMap<Long, Long> newStones = new HashMap<>();

            //handle zeroes
            newStones.put(1l,stones.getOrDefault(0l,0l));

            for(long key : stones.keySet()) {
                if(key == 0) {
                    newStones.put(1l, stones.get(0l));
                }
                else if((int) (Math.log10(key)+1) % 2 == 0) {
                    String num = Long.toString(key);
                    long upper = Long.parseLong(num.substring(0, num.length()/2));
                    long lower = Long.parseLong(num.substring(num.length()/2));

                    long oldStone = stones.get(key);
                    newStones.put(lower,newStones.getOrDefault(lower, 0l) + oldStone);
                    newStones.put(upper, newStones.getOrDefault(upper, 0l) + oldStone);
                } else {
                    newStones.put(key*2024, newStones.getOrDefault(key*2024, 0l) + stones.get(key));
                }
            }

            stones = newStones;

            System.out.println(stones);
        }

        return Long.toString(stones.values().stream().mapToLong(Long::longValue).sum());
    }
}
