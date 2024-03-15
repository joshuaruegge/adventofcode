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
        for (String line : input.split("\n")) {
            String[] halves = line.split(" \\| ");
            HashSet<Integer> win = numsIntoHash(halves[1].trim().split("\\s+"), 0);
            win.retainAll(numsIntoHash(halves[0].trim().split("\\s+"), 2));
            //total += 2^(win.size-1)
            total += (1 << (win.size() - 1));
        }
        return Integer.toString(total);
    }

    @Override
    public String part2() {
        int total = 0;
        String[] lines = input.split("\n");
        int[] cards = new int[lines.length];
        Arrays.fill(cards, 1);

        for (int line = 0; line < lines.length; line++) {
            String[] halves = lines[line].split(" \\| ");
            HashSet<Integer> win = numsIntoHash(halves[1].trim().split("\\s+"), 0);
            win.retainAll(numsIntoHash(halves[0].trim().split("\\s+"), 2));
            //for number of copies of current card, add same number of copies to next cards
            for (int i = 1; i <= win.size(); i++) {
                cards[line + i] += cards[line];
            }
        }

        for (int l : cards)
            total += l;
        return Integer.toString(total);
    }

    //parses strings from the string[] into a hash set of integers, starting at index start
    public HashSet<Integer> numsIntoHash(String[] chunks, int start) {
        HashSet<Integer> ret = new HashSet<>();
        for (int i = start; i < chunks.length; i++) {
            ret.add(Integer.parseInt(chunks[i]));
        }
        return ret;
    }
}
