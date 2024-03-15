package advent.aoc2023;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

public class Day06 implements IDay {

    static String input;

    public static void main(String[] args) {
        input = Input.fetchInput(2023, 6);
        DayRunner.run(new Day06());
    }

    @Override
    public String part1() {
        int mult = 1;
        String[] time = input.split("\n")[0].split("\\s+");
        String[] distance = input.split("\n")[1].split("\\s+");
        for (int i = 1; i < time.length; i++) {
            int count = 0;
            int t = Integer.parseInt(time[i]);
            int d = Integer.parseInt(distance[i]);
            for (int hold = 0; hold < t; hold++) {
                //distance is determined by the quadratic equation y=time * time held - time held^2
                if (t * hold - hold * hold > d) {
                    count++;
                }
            }
            mult *= count;
        }
        return Integer.toString(mult);
    }

    @Override
    public String part2() {
        String[] time = input.split("\n")[0].split("\\s+");
        String[] distance = input.split("\n")[1].split("\\s+");

        String fullTime = "";
        String fullDist = "";
        for (int i = 1; i < time.length; i++) {
            fullTime += time[i];
            fullDist += distance[i];
        }

        long t = Long.parseLong(fullTime);
        long d = Long.parseLong(fullDist);
        //we had the quadratic equation distance = time * time held - time held^2
        //if we want to find all the values for time held (or x) where distance is greater than fullDist
        //we can rewrite this equation as x^2-time*x+distance = 0
        //and using our quadratic formula, the two zeroes lie at (time +- sqrt(time^2-4*distance)))/2
        //and the distance between the zeroes will be the number of values for time held that are valid
        double sqrt = Math.sqrt(t * t - 4 * d);
        //round up and down, respectively, to account for the fact that only whole integer values are needed
        double firstZero = Math.ceil((t - sqrt) / 2);
        double secondZero = Math.floor((t + sqrt) / 2);

        return Integer.toString((int) (secondZero - firstZero + 1));
    }
}
