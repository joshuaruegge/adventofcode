package advent.aoc2023;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

import java.util.ArrayList;
import java.util.Collections;

public class Day01 implements IDay {

    static String input;
    final String[] numsAsText = new String[]{"one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};

    public static void main(String[] args) {
        input = Input.fetchInput(2023, 1);
        DayRunner.run(new Day01());
    }

    @Override
    public String part1() {
        int total = 0;
        for (String s : input.split("\n")) {
            int firstNumIndex = 0;
            while (Character.isAlphabetic(s.charAt(firstNumIndex))) firstNumIndex++;
            int lastNumIndex = s.length() - 1;
            while (Character.isAlphabetic(s.charAt(lastNumIndex))) lastNumIndex--;
            total += Integer.parseInt(s.charAt(firstNumIndex) + "" + s.charAt(lastNumIndex));
        }
        return Integer.toString(total);
    }

    @Override
    public String part2() {
        int total = 0;

        for (String s : input.split("\n")) {
            //first holds index of first actual number
            int first = s.length();
            for (int i = 0; i < s.length(); i++) {
                if (!Character.isAlphabetic(s.charAt(i))) {
                    first = i;
                    break;
                }
            }

            //indices iterates through numsAsText, getting index of each text number in string
            //notably, if not in string, put in max value (NOT -1)
            ArrayList<Integer> indices = new ArrayList<>();
            for (String t : numsAsText) {
                indices.add(!s.contains(t) ? Integer.MAX_VALUE : s.indexOf(t));
            }

            //now, actual first number is the minimum of the index of the first Real Number
            //and the minimum of the indices of the text numbers
            int min = Math.min(first, Collections.min(indices));
            //handle conversion based on whether it's a Real Number or a text number
            int firstNum;
            if (min == first) {
                firstNum = Integer.parseInt(s.charAt(first) + "");
            } else {
                //because indices are inserted in order starting at 1, indices(n) represents index of number n+1
                //so, actual number is (location of minimum) + 1
                firstNum = indices.indexOf(Collections.min(indices)) + 1;
            }
            firstNum *= 10;

            int last = 0;
            for (int i = s.length() - 1; i > -1; i--) {
                if (!Character.isAlphabetic(s.charAt(i))) {
                    last = i;
                    break;
                }
            }

            indices.clear();
            //notice here we're comparing the maximum index, so -1 is a valid representation of the string not being present
            for (String t : numsAsText) {
                indices.add(s.lastIndexOf(t));
            }

            //same as before - closest index to end of either real number or highest text number
            int max = Math.max(last, Collections.max(indices));
            int lastNum;
            if (max == last) {
                lastNum = Integer.parseInt(s.charAt(last) + "");
            } else {
                lastNum = indices.indexOf(Collections.max(indices)) + 1;
            }

            total += firstNum + lastNum;
        }
        return Integer.toString(total);
    }
}
