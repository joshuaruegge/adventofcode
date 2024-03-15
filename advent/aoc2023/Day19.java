package advent.aoc2023;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

import java.util.HashMap;

public class Day19 implements IDay {

    static String input;

    public static void main(String[] args) {
        input = Input.fetchInput(2023, 19);
        DayRunner.run(new Day19());
    }

    @Override
    public String part1() {
        String[] chunks = input.split("\n\n");
        //workflows are stored as id -> rules[] pairs in hashmap
        HashMap<String, String[]> workflows = new HashMap<>();
        for (String workflow : chunks[0].split("\n")) {
            String[] parts = workflow.split("\\{");
            parts[1] = parts[1].substring(0, parts[1].length() - 1);
            workflows.put(parts[0], parts[1].split(","));
        }

        int total = 0;
        for (String s : chunks[1].split("\n")) {
            //trim
            s = s.substring(1, s.length() - 1);
            String[] nums = s.split(",");
            int[] item = new int[4];
            for (int i = 0; i < 4; i++)
                item[i] = Integer.parseInt(nums[i].substring(2));
            total += process(workflows, "in", item);
        }

        return Integer.toString(total);
    }

    @Override
    public String part2() {
        String[] chunks = input.split("\n\n");
        //workflows are stored as id -> rules[] pairs in hashmap
        HashMap<String, String[]> workflows = new HashMap<>();
        for (String workflow : chunks[0].split("\n")) {
            String[] parts = workflow.split("\\{");
            parts[1] = parts[1].substring(0, parts[1].length() - 1);
            workflows.put(parts[0], parts[1].split(","));
        }

        int[][] startingRanges = new int[4][2];
        for (int i = 0; i < 4; i++)
            startingRanges[i] = new int[]{1, 4000};
        return Long.toString(processRanges(workflows, "in", startingRanges));
    }

    public int process(HashMap<String, String[]> workflows, String id, int[] item) {
        String[] rules = workflows.get(id);
        //iterate through rules for this workflow
        for (String rule : rules) {
            //edge case - rules without condition happen no matter what
            if (!rule.contains(":")) {
                if (rule.equals("A")) {
                    return item[0] + item[1] + item[2] + item[3];
                } else if (rule.equals("R")) {
                    return 0;
                } else {
                    return process(workflows, rule, item);
                }
            }

            String[] chunks = rule.split(":");
            //determine which of the four values is being examined for this rule
            int value = switch (chunks[0].charAt(0)) {
                case 'x' -> item[0];
                case 'm' -> item[1];
                case 'a' -> item[2];
                case 's' -> item[3];
                default -> -1;
            };

            int threshold = Integer.parseInt(chunks[0].substring(2));

            //if value is either below or above threshold, based on rule string
            if ((chunks[0].charAt(1) == '<' ? value < threshold : value > threshold)) {
                if (chunks[1].equals("A"))
                    return item[0] + item[1] + item[2] + item[3];
                else if (chunks[1].equals("R"))
                    return 0;
                else
                    //return workflows.get(chunks[1]).process(item);
                    return process(workflows, chunks[1], item);
            }
        }
        return -1;
    }

    //ranges are held as int[4][2] where int[i] has [start, end] of range for [x, m, a, s]
    public long processRanges(HashMap<String, String[]> workflows, String id, int[][] ranges) {
        long total = 0;
        String[] rules = workflows.get(id);

        for (String rule : rules) {
            //same edge case - rules without condition that occur no matter what
            if (!rule.contains(":")) {
                if (rule.equals("A")) {
                    long result = 1;
                    for (int[] i : ranges) {
                        result *= (i[1] - i[0] + 1);
                    }
                    total += result;
                } else if (!rule.equals("R")) {
                    total += processRanges(workflows, rule, arrayCopy(ranges));
                }
                continue;
            }

            String[] chunks = rule.split(":");

            int index = switch (chunks[0].charAt(0)) {
                case 'x' -> 0;
                case 'm' -> 1;
                case 'a' -> 2;
                case 's' -> 3;
                default -> -1;
            };
            int condition = Integer.parseInt(chunks[0].substring(2));

            if (chunks[0].charAt(1) == '<') {
                //range of interest: range min to condition-1
                if (ranges[index][0] < condition) {
                    int[] newRange = new int[]{ranges[index][0], condition - 1};
                    int[][] passRanges = arrayCopy(ranges);
                    passRanges[index] = newRange;
                    if (chunks[1].equals("A")) {
                        long result = 1;
                        for (int[] i : passRanges) {
                            result *= (i[1] - i[0] + 1);
                        }
                        total += result;
                    } else if (!chunks[1].equals("R")) {
                        total += processRanges(workflows, chunks[1], passRanges);
                    }
                }

                //range to continue investigating: condition to range max
                if (ranges[index][1] >= condition) {
                    ranges[index] = new int[]{condition, ranges[index][1]};
                } else {
                    //if the above range doesn't exist, one of our values no longer has a valid range, so we can stop evaluating
                    break;
                }
            } else {
                //range of interest: condition+1 to range max
                if (ranges[index][1] > condition) {
                    int[] newRange = new int[]{condition + 1, ranges[index][1]};
                    int[][] passRanges = arrayCopy(ranges);
                    passRanges[index] = newRange;

                    //pass on/handle resulting range
                    if (chunks[1].equals("A")) {
                        long result = 1;
                        for (int[] i : passRanges) {
                            result *= (i[1] - i[0] + 1);
                        }
                        total += result;
                    } else if (!chunks[1].equals("R")) {
                        total += processRanges(workflows, chunks[1], passRanges);
                    }
                }

                //range to keep investigating: min to condition
                if (ranges[index][0] <= condition) {
                    ranges[index] = new int[]{ranges[index][0], condition};
                } else {
                    break;
                }
            }
        }
        return total;
    }

    public int[][] arrayCopy(int[][] o) {
        return new int[][]{new int[]{o[0][0], o[0][1]}, new int[]{o[1][0], o[1][1]}, new int[]{o[2][0], o[2][1]}, new int[]{o[3][0], o[3][1]}};
    }
}
