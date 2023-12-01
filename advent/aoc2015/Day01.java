package advent.aoc2015;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

public class Day01 implements IDay {

    static String input;

    public static void main(String[] args) {
        input = Input.fetchInput(2015, 1);
        DayRunner.run(new Day01());
    }

    @Override
    public String part1() {
        //keep track of floor position
        int floorCount = 0;
        for (char c : input.toCharArray()) {
            //increment upwards or downwards
            if (c == ')') {
                floorCount--;
            } else {
                floorCount++;
            }
        }
        //return final
        return Integer.toString(floorCount);
    }

    @Override
    public String part2() {
        //keep track of floor and index
        int floorCount = 0;
        int i = 0;
        while (floorCount >= 0) {
            //increment or decrement
            char c = input.charAt(i);
            if (c == ')') {
                floorCount--;
            } else {
                floorCount++;
            }
            i++;
        }
        return Integer.toString(i);
    }

}
