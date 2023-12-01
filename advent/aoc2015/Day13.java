package advent.aoc2015;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;

public class Day13 implements IDay {

    static String input;

    //Heap's permutation generation algorithm. look it up on wikipedia if you need to
    public static ArrayList<int[]> perms(int[] a, int n) {
        ArrayList<int[]> perms = new ArrayList<>();
        if (n == 1) {
            perms.add(Arrays.copyOf(a, a.length));
            return perms;
        }

        perms.addAll(perms(a, n - 1));
        for (int i = 0; i < n - 1; i++) {
            int temp;
            if (n % 2 == 0) {
                temp = a[i];
                a[i] = a[n - 1];
            } else {
                temp = a[0];
                a[0] = a[n - 1];
            }
            a[n - 1] = temp;
            perms.addAll(perms(a, n - 1));
        }
        return perms;
    }

    public static void main(String[] args) {
        input = Input.fetchInput(2015, 13);
        DayRunner.run(new Day13());
    }

    @Override
    public String part1() {
        //add new names to names array, then use indexes of names to replace strings with numbers
        ArrayList<String> names = new ArrayList<>();
        //store happiness as map[id1][id2] = change
        int[][] happiness = new int[8][8];
        for (String s : input.split("\n")) {
            String[] parts = s.split(" ");
            //trim period off last word to avoid conflicts
            parts[10] = parts[10].substring(0, parts[10].length() - 1);
            if (!names.contains(parts[0])) names.add(parts[0]);
            if (!names.contains(parts[10])) names.add(parts[10]);
            //invert happiness value if necessary
            int happinessChange = Integer.parseInt(parts[3]);
            if (parts[2].equals("lose")) happinessChange *= -1;
            happiness[names.indexOf(parts[0])][names.indexOf(parts[10])] = happinessChange;
        }
        //similarly to day 9, use permutation of all possible arrangements, and calculate
        //values for happiness that result from neighbors in each arrangement
        int[] permBase = IntStream.range(0, names.size()).toArray();
        ArrayList<int[]> perms = perms(permBase, permBase.length);
        //keep track of score of best discovered arrangement
        int best = 0;
        for (int[] a : perms) {
            int happinessTotal = 0;
            //iterate over array, adding values for neighbor happiness
            for (int i = 0; i < a.length - 1; i++) {
                //happiness relations take place in both directions
                happinessTotal += happiness[a[i]][a[i + 1]];
                happinessTotal += happiness[a[i + 1]][a[i]];
            }
            //add neighbor status of front and back of array (wrapping)
            happinessTotal += happiness[a[0]][a[a.length - 1]];
            happinessTotal += happiness[a[a.length - 1]][a[0]];
            //grade position
            if (happinessTotal > best) best = happinessTotal;
        }
        return Integer.toString(best);
    }

    @Override
    public String part2() {
        //add new names to names array, then use indexes of names to replace strings with numbers
        ArrayList<String> names = new ArrayList<>();
        //store happiness as map[id1][id2] = change
        int[][] happiness = new int[9][9];
        for (String s : input.split("\n")) {
            String[] parts = s.split(" ");
            //trim period off last word to avoid conflicts
            parts[10] = parts[10].substring(0, parts[10].length() - 1);
            if (!names.contains(parts[0])) names.add(parts[0]);
            if (!names.contains(parts[10])) names.add(parts[10]);
            //invert happiness change value if necessary
            int happinessChange = Integer.parseInt(parts[3]);
            if (parts[2].equals("lose")) happinessChange *= -1;
            happiness[names.indexOf(parts[0])][names.indexOf(parts[10])] = happinessChange;
        }
        //similarly to day 9, use permutation of all possible arrangements, and calculate
        //values for happiness that result from neighbors in each arrangement

        //for part 2: we add one extra number to the permutations (for self)
        int[] permBase = IntStream.range(0, names.size() + 1).toArray();
        //now, create all neighbor relations for extra person (all equal to zero per instructions)
        for (int i = 0; i < permBase.length - 1; i++) {
            happiness[8][i] = 0;
            happiness[i][8] = 0;
        }
        ArrayList<int[]> perms = perms(permBase, permBase.length);
        int best = 0;
        for (int[] a : perms) {
            int happinessTotal = 0;
            //iterate over array, adding values for neighbor happiness
            for (int i = 0; i < a.length - 1; i++) {
                happinessTotal += happiness[a[i]][a[i + 1]];
                happinessTotal += happiness[a[i + 1]][a[i]];
            }
            //add neighbor status of front and back of array (wrapping)
            happinessTotal += happiness[a[0]][a[a.length - 1]];
            happinessTotal += happiness[a[a.length - 1]][a[0]];

            //grade position
            if (happinessTotal > best) best = happinessTotal;
        }
        return Integer.toString(best);
    }

}
