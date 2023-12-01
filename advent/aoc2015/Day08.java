package advent.aoc2015;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

public class Day08 implements IDay {

    static String input;

    public static void main(String[] args) {
        input = Input.fetchInput(2015, 8);
        DayRunner.run(new Day08());
    }

    @Override
    public String part1() {
        int total = 0;
        //iterate over lines
        for (String s : input.split("\n")) {
            //calculate code length
            int codeLength = s.length();
            //initialize mem length to same value as code length
            int memLength = codeLength;
            //decrease mem length as characters that are parsed out appear
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '\\') {
                    //decrease mem length by 3 for ascii characters
                    if (s.charAt(i + 1) == 'x') {
                        memLength -= 3;
                        i += 3;
                        //decrease by 1 for single-escape characters
                    } else {
                        memLength--;
                        i++;
                    }
                    //count unescaped quotes
                } else if (s.charAt(i) == '"') {
                    memLength--;
                }

            }
            total += (codeLength - memLength);
        }
        return Integer.toString(total);
    }

    @Override
    public String part2() {
        int total = 0;
        //iterate over lines
        for (String s : input.split("\n")) {
            //calculate code length
            int codeLength = s.length();
            //initialize mem length to same value as code length
            int memLength = codeLength;
            //increase mem length as characters that requre escaping appear
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '\\' || s.charAt(i) == '"') {
                    memLength++;
                }
            }
            //account for opening and closing quotes
            memLength += 2;
            total += (memLength - codeLength);
        }
        return Integer.toString(total);
    }

}
