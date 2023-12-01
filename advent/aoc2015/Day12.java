package advent.aoc2015;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

import java.util.ArrayList;

public class Day12 implements IDay {

    static String input;

    public static void main(String[] args) {
        input = Input.fetchInput(2015, 12);
        DayRunner.run(new Day12());
    }

    @Override
    public String part1() {
        int total = 0;
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            //if number (ascii range)
            if (c > 47 && c < 58) {
                //find end of number
                String num = "";
                int index = i;
                while (c > 47 && c < 58) {
                    num += c;
                    index++;
                    c = input.charAt(index);
                }
                int number = Integer.parseInt(num);
                //check for negative
                if (input.charAt(i - 1) == '-')
                    number *= -1;
                total += number;
                //increase i by length of num to skip number
                //but minus 1 so i++ at end of for loop puts us at right position
                i += num.length() - 1;
            }
        }
        return Integer.toString(total);
    }

    @Override
    public String part2() {
        //same as before, but when objects are encountered, collect entire object, check for red, and skip if necessary
        int total = 0;
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == '{') {
                //iterate through from i, till we find the matching closed curly
                int index = i;
                int openClosedCount = 1;
                while (openClosedCount > 0) {
                    index++;
                    if (input.charAt(index) == '{')
                        openClosedCount++;
                    if (input.charAt(index) == '}')
                        openClosedCount--;
                }
                //collect entire object
                String object = input.substring(i + 1, index);
                ArrayList<String> parts = new ArrayList<>();
                //get top-level children of object
                int openClosedCounter = 0;
                int beginningOfPhrase = 0;
                for (int j = 0; j < object.length(); j++) {
                    char d = object.charAt(j);
                    if (d == '{' || d == '[')
                        openClosedCounter++;
                    if (d == '}' || d == ']')
                        openClosedCounter--;
                    //if we've reached a comma between top-level children, add to parts list
                    if (d == ',' && openClosedCounter == 0) {
                        parts.add(object.substring(beginningOfPhrase + 1, j));
                        beginningOfPhrase = j;
                    }
                    //add last part when reaching end
                    if (j == object.length() - 1)
                        parts.add(object.substring(beginningOfPhrase + 1));
                }

                //if any parts contain red as object value, skip forwards past entire object
                for (String s : parts) {
                    if (s.matches(".*:\"red\"*")) {
                        i += object.length();
                        break;
                    }
                }
            }
            if (c > 47 && c < 58) {
                //find end of number
                String num = "";
                int index = i;
                while (c > 47 && c < 58) {
                    num += c;
                    index++;
                    c = input.charAt(index);
                }
                int number = Integer.parseInt(num);
                //check for negative
                if (input.charAt(i - 1) == '-')
                    number *= -1;
                total += number;
                //increase i by length of num to skip number
                //but minus 1 so i++ at end of for loop puts us at right position
                i += num.length() - 1;
            }
        }
        return Integer.toString(total);
    }

}
