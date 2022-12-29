package advent.aoc2022;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

import java.util.HashSet;

public class Day06 implements IDay {

    static String input;

    @Override
    public String part1() {
        int solution = 0;
        int counter = 4;
        while(counter < input.length()) {
            String last4 = input.substring(counter-4,counter);
            HashSet<Character> reduce = stringToHashSet(last4);
            //if hashset is same size as original, no duplicate characters were skipped, so return index
            if(reduce.size() == 4) {
                solution = counter;
                break;
            }
            counter++;
        }
        return Integer.toString(solution);
    }

    @Override
    public String part2() {
        int solution = 0;
        int counter = 14;
        while(counter < input.length()) {
            String last14 = input.substring(counter-14,counter);
            HashSet<Character> reduce = stringToHashSet(last14);
            //if hashset is same size as original, no duplicate characters were skipped, so return index
            if(reduce.size() == 14) {
                solution = counter;
                break;
            }
            counter++;
        }
        return Integer.toString(solution);
    }

    //creates character hash set out of provided string
    public HashSet<Character> stringToHashSet(String s) {
        HashSet<Character> a = new HashSet<Character>();
        for(char c : s.toCharArray())
            a.add(c);
        return a;
    }
    public static void main(String[] args) {
        input = Input.fetchInput(2022,6).trim();
        DayRunner.run(new Day06());
    }
}
