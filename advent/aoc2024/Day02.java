package advent.aoc2024;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

import java.util.ArrayList;

public class Day02 implements IDay {

    static String input;

    static String testInput = "7 6 4 2 1\n" +
            "1 2 7 8 9\n" +
            "9 7 6 2 1\n" +
            "1 3 2 4 5\n" +
            "8 6 4 4 1\n" +
            "1 3 6 7 9";

    public static void main(String[] args) {
        input = Input.fetchInput(2024, 2);
        DayRunner.run(new Day02());
    }

    @Override
    public String part1() {
        int safeCount = 0;
        lines:
        for(String line : input.split("\n")) {
            ArrayList<Integer> nums = new ArrayList<>();
            for(String num : line.split(" "))
                nums.add(Integer.parseInt(num));

            int dir = (int) Math.signum(nums.get(1) - nums.get(0));
            if(dir == 0) continue;

            for(int i = 1; i < nums.size(); i++) {
                if(((int) (Math.signum(nums.get(i) - nums.get(i-1))) != dir) || Math.abs(nums.get(i) - nums.get(i-1)) > 3)
                    continue lines;
            }
            safeCount++;
        }
        return Integer.toString(safeCount);
    }

    @Override
    public String part2() {
        int safeCount = 0;

        for(String line : input.split("\n")) {
            boolean singleBadSeen = false;
            ArrayList<Integer> nums = new ArrayList<>();
            for(String num : line.split(" "))
                nums.add(Integer.parseInt(num));

            //TODO: refactor for efficiency
            testWithout:
            for(int x = 0; x < nums.size(); x++) {
                ArrayList<Integer> numsX = new ArrayList<>(nums);
                numsX.remove(x);
                int dir = (int) Math.signum(numsX.get(1) - numsX.get(0));
                if(dir == 0) continue;

                for(int i = 1; i < numsX.size(); i++) {
                    if(((int) (Math.signum(numsX.get(i) - numsX.get(i-1))) != dir) || Math.abs(numsX.get(i) - numsX.get(i-1)) > 3)
                        continue testWithout;
                }
                safeCount++;
                break;
            }

        }
        return Integer.toString(safeCount);
    }
}
