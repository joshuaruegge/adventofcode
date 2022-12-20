package advent.aoc2022;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;
import advent.utilities.utils2022.NumWrapper;

import java.util.ArrayList;

public class Day20 implements IDay {

    static String input;

    @Override
    public String part1() {
        ArrayList<NumWrapper> nums = new ArrayList<NumWrapper>();
        String[] lines = input.split("\n");
        NumWrapper zero = null;
        for(int i = 0; i < lines.length; i++) {
            nums.add(new NumWrapper(Integer.parseInt(lines[i]), i));
            if(Integer.parseInt(lines[i]) == 0)
                zero = new NumWrapper(Integer.parseInt(lines[i]),i);
        }
        mix(nums, new ArrayList<NumWrapper>(nums));
        int startIndex = nums.indexOf(zero);
        return Long.toString(nums.get((startIndex + 1000) % nums.size()).num + nums.get((startIndex + 2000) % nums.size()).num + nums.get((startIndex + 3000) % nums.size()).num);
    }

    public void mix(ArrayList<NumWrapper> nums, ArrayList<NumWrapper> orig) {
        for(NumWrapper i : orig) {
            int oldIndex = nums.indexOf(i);
            nums.remove(oldIndex);
            //floorMod is good for ensuring the sign ends up correct
            int newIndex = Math.floorMod(oldIndex + i.num,nums.size());
            nums.add(newIndex,i);
        }
    }

    @Override
    public String part2() {
        final long key = 811589153;
        String[] lines = input.split("\n");
        ArrayList<NumWrapper> nums = new ArrayList<>();
        NumWrapper zero = null;
        for(int i = 0; i < lines.length; i++) {
            nums.add(new NumWrapper(Integer.parseInt(lines[i]) * key, i));
            if(Integer.parseInt(lines[i]) == 0)
                zero = new NumWrapper(Integer.parseInt(lines[i]),i);
        }
        ArrayList<NumWrapper> orig = new ArrayList<>(nums);
        for(int i = 0; i < 10; i++) {
            mix(nums,orig);
        }
        int startIndex = nums.indexOf(zero);
        return Long.toString(nums.get((startIndex + 1000) % nums.size()).num + nums.get((startIndex + 2000) % nums.size()).num + nums.get((startIndex + 3000) % nums.size()).num);
    }

    public static void main(String[] args) {
        input = Input.fetchInput(2022,20);
        DayRunner.run(new Day20());
    }
}
