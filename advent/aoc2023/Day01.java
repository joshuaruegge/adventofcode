package advent.aoc2023;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

import java.util.ArrayList;
import java.util.Collections;

public class Day01 implements IDay {

    static String input;

    public static void main(String[] args) {
            input = Input.fetchInput(2023, 1);
            DayRunner.run(new Day01());
    }

    @Override
    public String part1() {
        int total = 0;
        for(String s : input.split("\n")) {
            char first = 0;
            for(int i = 0; i < s.length(); i++) {
                if(!Character.isAlphabetic(s.charAt(i))) {
                    first = s.charAt(i);
                    break;
                }
            }
            char last = 0;
            for(int i = s.length() - 1; i > -1; i--) {
                if(!Character.isAlphabetic(s.charAt(i))) {
                    last = s.charAt(i);
                    break;
                }
            }
            total += Integer.parseInt(first+""+last);
        }
        return Integer.toString(total);
    }

    final String[] con = new String[] {"one", "two", "three", "four", "five", "six","seven","eight","nine"};
    @Override
    public String part2() {
        int total = 0;

        for(String s : input.split("\n")) {
            int first = s.length();
            for(int i = 0; i < s.length(); i++) {
                if(!Character.isAlphabetic(s.charAt(i))) {
                    first = i;
                    break;
                }
            }

            ArrayList<Integer> indices = new ArrayList<>();
            for(String t : con) {
                indices.add(s.indexOf(t) == -1 ? Integer.MAX_VALUE : s.indexOf(t));
            }

            int min = Math.min(first, Collections.min(indices));
            int firstNum;
            if(min == first) {
                firstNum = Integer.parseInt(s.charAt(first) + "");
            } else {
                firstNum = indices.indexOf(Collections.min(indices)) + 1;
            }
            firstNum *= 10;

            int last = 0;
            for(int i = s.length() - 1; i > -1; i--) {
                if(!Character.isAlphabetic(s.charAt(i))) {
                    last = i;
                    break;
                }
            }

            indices.clear();
            for(String t : con) {
                indices.add(s.indexOf(t) == -1 ? Integer.MIN_VALUE : s.lastIndexOf(t));
            }

            int max = Math.max(last, Collections.max(indices));
            int lastNum;
            if(max == last) {
                lastNum = Integer.parseInt(s.charAt(last) + "");
            } else {
                lastNum = indices.indexOf(Collections.max(indices)) + 1;
            }
            
            total += firstNum+lastNum;
        }
        return Integer.toString(total);
    }
}
