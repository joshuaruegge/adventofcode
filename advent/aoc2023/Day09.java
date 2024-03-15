package advent.aoc2023;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

public class Day09 implements IDay {

    static String input;

    public static void main(String[] args) {
        input = Input.fetchInput(2023, 9);
        DayRunner.run(new Day09());
    }

    @Override
    public String part1() {
        long total = 0;
        for (String s : input.split("\n")) {
            String[] nums = s.split(" ");
            int[] startRow = new int[nums.length];
            for (int i = 0; i < nums.length; i++)
                startRow[i] = Integer.parseInt(nums[i]);
            total += startRow[startRow.length - 1] + rowSum(startRow);
        }
        return Long.toString(total);
    }

    @Override
    public String part2() {
        long total = 0;
        for (String s : input.split("\n")) {
            String[] nums = s.split(" ");
            int[] startRow = new int[nums.length];
            for (int i = 0; i < nums.length; i++)
                startRow[i] = Integer.parseInt(nums[i]);
            total += startRow[0] - rowDiff(startRow);
        }
        return Long.toString(total);
    }

    public int rowSum(int[] lastRow) {
        int[] row = new int[lastRow.length - 1];
        for (int i = 1; i < lastRow.length; i++) {
            row[i - 1] = lastRow[i] - lastRow[i - 1];
        }

        for (int i : row) {
            if (i != 0) {
                return row[row.length - 1] + rowSum(row);
            }
        }
        return 0;
    }

    public int rowDiff(int[] lastRow) {
        int[] row = new int[lastRow.length - 1];
        for (int i = 1; i < lastRow.length; i++) {
            row[i - 1] = lastRow[i] - lastRow[i - 1];
        }

        for (int i : row) {
            if (i != 0) {
                return row[0] - rowDiff(row);
            }
        }
        return 0;
    }
}
