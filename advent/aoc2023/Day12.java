package advent.aoc2023;

import advent.utilities.general.Coord3;
import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

import java.util.HashMap;

public class Day12 implements IDay {

    static String input;

    public static void main(String[] args) {
        input = Input.fetchInput(2023, 12);
        DayRunner.run(new Day12());
    }

    @Override
    public String part1() {
        long total = 0;
        for (String line : input.split("\n")) {
            String[] parts = line.split(" ");
            String[] nums = parts[1].split(",");
            int[] count = new int[nums.length];
            int hiCount = 0;
            for (int i = 0; i < nums.length; i++) {
                count[i] = Integer.parseInt(nums[i]);
                hiCount = Math.max(hiCount, count[i]);
            }

            //pad to avoid a silly ending issue - doesn't cause problems
            long result = memoizedRecurse(hiCount, new HashMap<>(), parts[0] + ".", count, 0, 0, 0);
            total += result;
        }
        return Long.toString(total);
    }

    @Override
    public String part2() {
        long total = 0;
        for (String line : input.split("\n")) {
            String[] parts = line.split(" ");
            String[] nums = parts[1].split(",");
            int[] count = new int[nums.length];
            int hiCount = 0;
            for (int i = 0; i < nums.length; i++) {
                count[i] = Integer.parseInt(nums[i]);
                hiCount = Math.max(hiCount, count[i]);
            }
            int[] fullCount = new int[count.length * 5];
            for (int i = 0; i < 5; i++)
                System.arraycopy(count, 0, fullCount, count.length * i, count.length);
            String fullMap = parts[0];
            for (int i = 0; i < 4; i++)
                fullMap += "?" + parts[0];
            //pad to make bounds checking at end easier
            fullMap += ".";

            long result = memoizedRecurse(hiCount, new HashMap<>(), fullMap, fullCount, 0, 0, 0);
            total += result;
        }
        return Long.toString(total);
    }

    public long memoizedRecurse(int hiCount, HashMap<Coord3, Long> memo, String map, int[] counts, int index, int curGroup, int countIndex) {
        if (curGroup > hiCount) return 0;
        Coord3 key = new Coord3(index, curGroup, countIndex);
        if (memo.containsKey(key)) return memo.get(key);
        //if at end of string and all counts have been checked (i.e. countIndex is past end), this is a valid combination
        if (index == map.length()) return countIndex == counts.length ? 1 : 0;
        if (map.charAt(index) == '#')
            return memoizedRecurse(hiCount, memo, map, counts, index + 1, curGroup + 1, countIndex);
        if (map.charAt(index) == '.' || countIndex == counts.length) {
            //if properly finished last count
            if (countIndex < counts.length && curGroup == counts[countIndex]) {
                return memoizedRecurse(hiCount, memo, map, counts, index + 1, 0, countIndex + 1);

                //keep going till end - hope we dont run into more #
            } else if (curGroup == 0) {
                return memoizedRecurse(hiCount, memo, map, counts, index + 1, 0, countIndex);

                //incorrect!
            } else {
                return 0;
            }
        }
        //question mark behavior:
        //default assume spring, above behavior will correct
        long springCount = memoizedRecurse(hiCount, memo, map, counts, index + 1, curGroup + 1, countIndex);
        long nonCount = 0;
        //cases where dot is valid:
        if (curGroup == counts[countIndex])
            nonCount = memoizedRecurse(hiCount, memo, map, counts, index + 1, 0, countIndex + 1);
        else if (curGroup == 0) nonCount = memoizedRecurse(hiCount, memo, map, counts, index + 1, 0, countIndex);
        springCount += nonCount;

        memo.put(key, springCount);
        return springCount;
    }
}
