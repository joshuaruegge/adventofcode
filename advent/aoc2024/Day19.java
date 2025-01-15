package advent.aoc2024;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Day19 implements IDay {

    static String input;

    static String testInput = "r, wr, b, g, bwu, rb, gb, br\n" +
            "\n" +
            "brwrr\n" +
            "bggr\n" +
            "gbbr\n" +
            "rrbgbr\n" +
            "ubwu\n" +
            "bwurrg\n" +
            "brgr\n" +
            "bbrgwb";

    boolean globalExit = false;

    public static void main(String[] args) {
        input = Input.fetchInput(2024, 19);
        DayRunner.run(new Day19());
    }

    @Override
    public String part1() {
        long total = 0;

        String[] chunks = input.split("\n\n");

        List<String> bases = List.of(chunks[0].split(", "));

        for(String pat : chunks[1].split("\n")) {
            boolean[] dp = new boolean[pat.length()+1];
            dp[0] = true;

            for(int end=1; end <= pat.length(); end++){
                for(int start=0; start < end; start++){
                    if(dp[start] && bases.contains(pat.substring(start, end))){
                        dp[end] = true;
                        break;
                    }
                }
            }

            if(dp[pat.length()])
                total++;
        }

        return Long.toString(total);
    }

    @Override
    public String part2() {
        long total = 0;

        String[] chunks = input.split("\n\n");

        String[] bases = chunks[0].split(", ");
        
        for(String pat : chunks[1].split("\n")) {
            long[] dp = new long[pat.length()+1];
            dp[0] = 1;
            for(int end = 1; end < dp.length; end++) {
                for(String base : bases) {
                    int start = end - base.length();
                    
                    if(start < 0)
                        continue;

                    if(pat.substring(start, end).equals(base))
                        dp[end] += dp[start];
                }
            }

            total += dp[pat.length()];
        }

        return Long.toString(total);
    }
}
