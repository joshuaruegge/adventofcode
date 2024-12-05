package advent.aoc2024;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

import java.util.ArrayList;
import java.util.HashMap;

public class Day05 implements IDay {

    static String input;

    public static void main(String[] args) {
        input = Input.fetchInput(2024, 5);
        DayRunner.run(new Day05());
    }

    @Override
    public String part1() {
        int middleSum = 0;

        String[] parts = input.split("\n\n");

        HashMap<Integer, ArrayList<Integer>> deps = new HashMap<>();

        for(String s : parts[0].split("\n")) {
            String[] nums = s.split("\\|");

            int key = Integer.parseInt(nums[1]);

            ArrayList<Integer> thisDeps = deps.getOrDefault(key, new ArrayList<Integer>());

            thisDeps.add(Integer.parseInt(nums[0]));

            deps.put(key, thisDeps);
        }

        lines:
        for(String line : parts[1].split("\n")) {
            String[] nums = line.split(",");

            for(int i = 0; i < nums.length; i++) {
                int cur = Integer.parseInt(nums[i]);
                ArrayList<Integer> curDeps = deps.getOrDefault(cur, new ArrayList<>());

                for(int j = i+1; j < nums.length; j++) {
                    if(curDeps.contains(Integer.parseInt(nums[j])))
                        continue lines;
                }
            }

            middleSum += Integer.parseInt(nums[nums.length/2]);
        }

        return Integer.toString(middleSum);
    }

    @Override
    public String part2() {
        int middleSum = 0;

        String[] parts = input.split("\n\n");

        HashMap<Integer, ArrayList<Integer>> deps = new HashMap<>();

        for(String s : parts[0].split("\n")) {
            String[] nums = s.split("\\|");

            int key = Integer.parseInt(nums[1]);

            ArrayList<Integer> thisDeps = deps.getOrDefault(key, new ArrayList<Integer>());

            thisDeps.add(Integer.parseInt(nums[0]));

            deps.put(key, thisDeps);
        }

        lines:
        for(String line : parts[1].split("\n")) {
            String[] nums = line.split(",");

            boolean neededCorrection = false;

            for(int i = 0; i < nums.length; i++) {
                int cur = Integer.parseInt(nums[i]);
                ArrayList<Integer> curDeps = deps.getOrDefault(cur, new ArrayList<>());

                for(int j = i+1; j < nums.length; j++) {
                    if(curDeps.contains(Integer.parseInt(nums[j]))) {
                        neededCorrection = true;

                        String buf = nums[i];
                        nums[i] = nums[j];
                        nums[j] = buf;
                        i--;
                        break;
                    }
                }
            }

            if(neededCorrection)
                middleSum += Integer.parseInt(nums[nums.length/2]);
        }

        return Integer.toString(middleSum);
    }
}
