package advent.aoc2023;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

import java.util.Arrays;
import java.util.HashSet;

public class Day04 implements IDay {

    static String input;

    public static void main(String[] args) {
        input = Input.fetchInput(2023, 4);
        DayRunner.run(new Day04());
    }

    @Override
    public String part1() {
        int total = 0;
        for(String line : input.split("\n")) {
            String[] halves = line.split(" \\| ");
            HashSet<Integer> win = parseNums(halves[1].trim().split("\\s+"),0);
            win.retainAll(parseNums(halves[0].trim().split("\\s+"),2));
            total += (1 << (win.size() - 1));
        }
        return Integer.toString(total);
    }

    public HashSet<Integer> parseNums(String[] chunks, int start) {
        HashSet<Integer> ret = new HashSet<>();
        for(int i = start; i < chunks.length; i++) {
            ret.add(Integer.parseInt(chunks[i]));
        }
        return ret;
    }

    @Override
    public String part2() {
        long total = 0;
        String[] lines = input.split("\n");
        long[] cards = new long[lines.length];
        Arrays.fill(cards, 1);

        for(int line = 0; line < lines.length; line++) {
            String[] halves = lines[line].split(" \\| ");
            HashSet<Integer> win = parseNums(halves[1].trim().split("\\s+"),0);
            win.retainAll(parseNums(halves[0].trim().split("\\s+"),2));
            for(int i = 1; i <= win.size(); i++) {
                cards[line+i] += cards[line];
            }
        }

        for(long l : cards)
            total += l;
        return Long.toString(total);
    }
}
