package advent.aoc2023;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

public class Day13 implements IDay {

    static String input;

    public static void main(String[] args) {
        input = Input.fetchInput(2023, 13);
        DayRunner.run(new Day13());
    }

    @Override
    public String part1() {
        int sum = 0;
        pat:
        for (String pattern : input.split("\n\n")) {
            String[] lines = pattern.split("\n");
            //vertical check
            for (int v = 0; v < lines.length - 1; v++) {
                int left = v;
                int right = v + 1;
                //increment each direction, checking that they stay equal - if we reach one of the edges, this row is the reflection
                while (lines[left].equals(lines[right])) {
                    left--;
                    right++;
                    if (left == -1 || right == lines.length) {
                        //midpoint discovered! line lies between midpoint and midpoint-1
                        sum += (v + 1) * 100;
                        continue pat;
                    }
                }
            }
            //horizontal check
            for (int h = 0; h < lines[0].length() - 1; h++) {
                int left = h;
                int right = h + 1;
                while (colsEqual(left, right, lines)) {
                    left--;
                    right++;
                    if (left == -1 || right == lines[0].length()) {
                        sum += h + 1;
                        continue pat;
                    }
                }
            }
        }
        return Integer.toString(sum);
    }

    //helper method to determine if two columns in string array are equal
    public boolean colsEqual(int a, int b, String[] lines) {
        for (String line : lines)
            if (line.charAt(a) != line.charAt(b)) return false;
        return true;
    }

    @Override
    public String part2() {
        int sum = 0;
        pat:
        for (String pattern : input.split("\n\n")) {
            String[] lines = pattern.split("\n");
            for (int v = 0; v < lines.length - 1; v++) {
                int left = v;
                int right = v + 1;
                boolean smudgeFixed = false;
                //continue checking for equality until we fix the smudge
                while (!smudgeFixed || lines[left].equals(lines[right])) {
                    if (lines[left].equals(lines[right])) {
                        left--;
                        right++;
                        //if reached one edge (reflection done)
                        if (left == -1 || right == lines.length) {
                            //if we've finished the reflection and already handled the smudge, this is the correct row
                            if (smudgeFixed) {
                                sum += (v + 1) * 100;
                                continue pat;
                            } else {
                                break;
                            }
                        }
                    } else {
                        //if rows are not equal but have exactly one difference, mark smudge as having been seen and continue
                        if (oneDiff(lines[left], lines[right])) {
                            smudgeFixed = true;
                            left--;
                            right++;
                            if (left == -1 || right == lines.length) {
                                sum += (v + 1) * 100;
                                continue pat;
                            }
                        } else {
                            break;
                        }
                    }
                }
            }
            for (int h = 0; h < lines[0].length() - 1; h++) {
                int left = h;
                int right = h + 1;
                boolean smudgeFixed = false;
                while (!smudgeFixed || colsEqual(left, right, lines)) {
                    if (colsEqual(left, right, lines)) {
                        left--;
                        right++;
                        if (left == -1 || right == lines[0].length()) {
                            if (smudgeFixed) {
                                sum += h + 1;
                                continue pat;
                            } else {
                                break;
                            }
                        }
                    } else {
                        if (oneDiff(left, right, lines)) {
                            smudgeFixed = true;
                            left--;
                            right++;
                            if (left == -1 || right == lines[0].length()) {
                                sum += h + 1;
                                continue pat;
                            }
                        } else {
                            break;
                        }
                    }
                }
            }
        }
        return Integer.toString(sum);
    }

    //determines if the two strings have exactly one character not in common
    public boolean oneDiff(String a, String b) {
        boolean diff = false;
        for (int i = 0; i < a.length(); i++)
            if (a.charAt(i) != b.charAt(i)) if (!diff) diff = true;
            else return false;
        return diff;
    }

    //same as above, but operates on columsn A and B of array
    public boolean oneDiff(int a, int b, String[] lines) {
        boolean diff = false;
        for (String s : lines) {
            if (s.charAt(a) != s.charAt(b)) if (!diff) diff = true;
            else return false;
        }
        return diff;
    }
}
