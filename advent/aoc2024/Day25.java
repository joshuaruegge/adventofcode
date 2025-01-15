package advent.aoc2024;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

import java.util.LinkedList;

public class Day25 implements IDay {

    static String input;

    static String testInput = "";

    public static void main(String[] args) {
        input = Input.fetchInput(2024, 25);
        DayRunner.run(new Day25());
    }

    @Override
    public String part1() {
        LinkedList<int[]> locks = new LinkedList<>();
        LinkedList<int[]> keys = new LinkedList<>();

        String[] items = input.split("\n\n");
        for(String item : items) {
            String[] lines = item.split("\n");
            //lock
            int[] heights = new int[5];
            if(lines[0].equals("#####")) {
                for(int i = 1; i < lines.length; i++) {
                    for(int j = 0; j < 5; j++) {
                        if(lines[i].charAt(j) == '#')
                            heights[j]++;
                    }
                }
                locks.add(heights);
            //key
            } else {
                for(int i = 0; i < lines.length-1; i++) {
                    for(int j = 0; j < 5; j++) {
                        if(lines[i].charAt(j) == '#')
                            heights[j]++;
                    }
                }
                keys.add(heights);
            }
        }

        long total = 0;

        for (int[] lock : locks) {
            keys:
            for (int[] key : keys) {
                for (int k = 0; k < 5; k++) {
                    if (lock[k] + key[k] > 5)
                        continue keys;
                }
                total++;
            }
        }
        return Long.toString(total);
    }

    @Override
    public String part2() {
        return "Merry Christmas!";
    }
}
