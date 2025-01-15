package advent.aoc2024;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.stream.Collectors;

public class Day22 implements IDay {

    static String input;

    static String testInput = "123";

    public static void main(String[] args) {
        input = Input.fetchInput(2024, 22);
        DayRunner.run(new Day22());
    }

    @Override
    public String part1() {
        long total = 0;

        System.out.println(input.split("\n").length);

        for(String num : input.split("\n")) {
            long secretnum = Long.parseLong(num);

            for(int i = 0; i < 2000; i++) {
                secretnum = nextSecretNum(secretnum);
                //System.out.println(secretnum);
            }

            //System.out.println(secretnum);

            total += secretnum;
        }

        return Long.toString(total);
    }

    public long nextSecretNum(long secretNum) {


        final long MODVALUE = 16777216;


        long prod1 = secretNum * 64;
        secretNum ^= prod1;
        secretNum %= MODVALUE;
        long div = secretNum / 32;
        secretNum ^= div;
        secretNum %= MODVALUE;
        long prod2 = secretNum * 2048;


        secretNum ^= prod2;
        secretNum %= MODVALUE;

        return secretNum;
    }

    @Override
    public String part2() {
        long total = 0;

        String[] monkeys = input.split("\n");
        //first index: monkey num (i.e. index in input)
        //second: num in sequence (0 - 1999)
        //third: 0 for price, 1 for diff from last
        //byte[][][] buffer = new byte[monkeys.length][2001][2];

        HashMap<Integer, Integer> sequenceTotals = new HashMap<>();

        for(int m = 0; m < monkeys.length; m++) {
            String num = monkeys[m];

            long secretnum = Long.parseLong(num);

            HashSet<Integer> seen = new HashSet<>();

            //buffer[m][0] = new byte[] {(byte) (secretnum % 10), 125};
            int diff3 = Integer.MAX_VALUE;
            int diff2 = Integer.MAX_VALUE;
            int diff1 = Integer.MAX_VALUE;
            int diff0 = Integer.MAX_VALUE;

            //calculate first three iterations (to ensure at least four differences in diff buffers)
            for(int i = 0; i < 3; i++) {
                diff3 = diff2;
                diff2 = diff1;
                diff1 = diff0;

                long newSecretNum = nextSecretNum(secretnum);
                byte newOnes = (byte) (newSecretNum % 10);
                byte oldOnes = (byte) (secretnum % 10);

                diff0 = newOnes - oldOnes;
                secretnum = newSecretNum;
            }


            for(int i = 3; i < 2000; i++) {

                long newSecretNum = nextSecretNum(secretnum);
                int newOnes = (int) (newSecretNum % 10);
                int oldOnes = (int) (secretnum % 10);

                diff3 = diff2;
                diff2 = diff1;
                diff1 = diff0;


                diff0 = newOnes - oldOnes;

                int hash = Objects.hash(diff3, diff2, diff1, diff0);
                if(seen.add(hash))
                    sequenceTotals.put(hash, sequenceTotals.getOrDefault(hash,0) + newOnes);
                //buffer[m][i] = new byte[]{newOnes, (byte) (newOnes - oldOnes)};
                secretnum = newSecretNum;
            }
        }

        /*
        for(int a = -9; a < 10; a++) {
            for(int b = -9; b < 10; b++) {
                for(int c = -9; c < 10; c++) {
                    for(int d = -9; d < 10; d++) {
                        int bananas = 0;
                        monkey:
                        for(int m = 0; m < buffer.length; m++) {
                            int b3 = Integer.MAX_VALUE;
                            int b2 = Integer.MAX_VALUE;
                            int b1 = Integer.MAX_VALUE;
                            int b0 = Integer.MAX_VALUE;
                            for(int i = 0; i < 2001; i++) {
                                b3 = b2;
                                b2 = b1;
                                b1 = b0;
                                b0 = buffer[m][i][1];

                                if(a == b3 && b == b2 && c == b1 && d == b0) {
                                    bananas += buffer[m][i][0];
                                    continue monkey;
                                }
                            }
                        }
                        System.out.println(a + " " + b + " " + c + " " + d + " " + bananas);
                        if(bananas > total)
                            total = bananas;
                    }
                }
            }
        }

         */
        return Integer.toString(sequenceTotals.values().stream().mapToInt(x -> x).max().getAsInt());
    }
}
