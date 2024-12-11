package advent.aoc2024;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

import java.util.Arrays;

public class Day07 implements IDay {

    static String input;

    static String testInput = "190: 10 19\n" +
            "3267: 81 40 27\n" +
            "83: 17 5\n" +
            "156: 15 6\n" +
            "7290: 6 8 6 15\n" +
            "161011: 16 10 13\n" +
            "192: 17 8 14\n" +
            "21037: 9 7 18 13\n" +
            "292: 11 6 16 20";

    public static void main(String[]  args) {
        input = Input.fetchInput(2024, 7);
        DayRunner.run(new Day07());
    }

    @Override
    public String part1() {
        long total = 0;

        for(String line : input.split("\n")) {

            String[] chunks = line.split(": ");
            String[] numsString = chunks[1].split(" ");
            long[] nums = new long[numsString.length];
            for(int i = 0; i < numsString.length; i++)
                nums[i] = Long.parseLong(numsString[i]);

            if(valid(nums, Long.parseLong(chunks[0]),nums[0],1)) {

                total += Long.parseLong(chunks[0]);
            }

        }

        return Long.toString(total);
    }

    public boolean valid(long[] nums, long target, long cur, int index) {
        if(index == nums.length)
            return cur == target;
        return valid(nums, target, cur+nums[index], index + 1) || valid(nums, target, cur*nums[index], index + 1);
    }

    public boolean valid2(long[] nums, long target, long cur, long curNum, int index) {
        if(index == nums.length)
            return cur == target;
        long opNum = (curNum == -1 ? nums[index] : curNum);
        boolean retval = valid2(nums, target, cur+opNum, -1, index + 1) || valid2(nums, target, cur*opNum, -1, index + 1);

            String newNum = Long.toString(cur) + Long.toString(opNum);

                try {
                    retval |= valid2(nums, target, Long.parseLong(newNum), -1, index + 1);
                } catch (NumberFormatException e) {

                }


        return retval;
    }

    @Override
    public String part2() {
        long total = 0;

        for(String line : input.split("\n")) {

            String[] chunks = line.split(": ");
            String[] numsString = chunks[1].split(" ");
            long[] nums = new long[numsString.length];
            for(int i = 0; i < numsString.length; i++)
                nums[i] = Long.parseLong(numsString[i]);

            if(valid2(nums, Long.parseLong(chunks[0]),nums[0],-1,1)) {

                total += Long.parseLong(chunks[0]);
            }

        }

        return Long.toString(total);
    }
}
