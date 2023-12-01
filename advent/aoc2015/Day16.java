package advent.aoc2015;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

import java.util.ArrayList;
import java.util.Arrays;

public class Day16 implements IDay {

    static String input;

    //hardcoded comparison value array based on puzzle description - may vary
    int[] comparison = new int[]{3, 7, 2, 3, 0, 0, 5, 3, 2, 1};

    public static void main(String[] args) {
        input = Input.fetchInput(2015, 16);
        DayRunner.run(new Day16());
    }

    @Override
    public String part1() {
        //store sues as list of integer list representing counts
        ArrayList<ArrayList<Integer>> sues = new ArrayList<>();
        for (String s : input.split("\n")) {
            ArrayList<String> parts = new ArrayList<>(Arrays.asList(s.split(" ")));
            //categories for reference:
            //children, cats, samoyeds, pomeranians, akitas, vizslas, goldfish, trees, cars, perfumes
            //arraylist will store characteristics (or -1 if unknown) in above order
            ArrayList<Integer> sue = new ArrayList<>();
            //copy-pasted checks for each characteristic
            if (parts.contains("children:")) {
                String value = parts.get(parts.indexOf("children:") + 1);
                if (value.contains(","))
                    value = value.substring(0, value.length() - 1);
                sue.add(Integer.parseInt(value));
            } else {
                sue.add(-1);
            }
            if (parts.contains("cats:")) {
                String value = parts.get(parts.indexOf("cats:") + 1);
                if (value.contains(","))
                    value = value.substring(0, value.length() - 1);
                sue.add(Integer.parseInt(value));
            } else {
                sue.add(-1);
            }
            if (parts.contains("samoyeds:")) {
                String value = parts.get(parts.indexOf("samoyeds:") + 1);
                if (value.contains(","))
                    value = value.substring(0, value.length() - 1);
                sue.add(Integer.parseInt(value));
            } else {
                sue.add(-1);
            }
            if (parts.contains("pomeranians:")) {
                String value = parts.get(parts.indexOf("pomeranians:") + 1);
                if (value.contains(","))
                    value = value.substring(0, value.length() - 1);
                sue.add(Integer.parseInt(value));
            } else {
                sue.add(-1);
            }
            if (parts.contains("akitas:")) {
                String value = parts.get(parts.indexOf("akitas:") + 1);
                if (value.contains(","))
                    value = value.substring(0, value.length() - 1);
                sue.add(Integer.parseInt(value));
            } else {
                sue.add(-1);
            }
            if (parts.contains("vizslas:")) {
                String value = parts.get(parts.indexOf("vizslas:") + 1);
                if (value.contains(","))
                    value = value.substring(0, value.length() - 1);
                sue.add(Integer.parseInt(value));
            } else {
                sue.add(-1);
            }
            if (parts.contains("goldfish:")) {
                String value = parts.get(parts.indexOf("goldfish:") + 1);
                if (value.contains(","))
                    value = value.substring(0, value.length() - 1);
                sue.add(Integer.parseInt(value));
            } else {
                sue.add(-1);
            }
            if (parts.contains("trees:")) {
                String value = parts.get(parts.indexOf("trees:") + 1);
                if (value.contains(","))
                    value = value.substring(0, value.length() - 1);
                sue.add(Integer.parseInt(value));
            } else {
                sue.add(-1);
            }
            if (parts.contains("cars:")) {
                String value = parts.get(parts.indexOf("cars:") + 1);
                if (value.contains(","))
                    value = value.substring(0, value.length() - 1);
                sue.add(Integer.parseInt(value));
            } else {
                sue.add(-1);
            }
            if (parts.contains("perfumes:")) {
                String value = parts.get(parts.indexOf("perfumes:") + 1);
                if (value.contains(","))
                    value = value.substring(0, value.length() - 1);
                sue.add(Integer.parseInt(value));
            } else {
                sue.add(-1);
            }
            sues.add(sue);
        }
        sueLoop:
        for (int i = 0; i < 500; i++) {
            ArrayList<Integer> sue = sues.get(i);
            //check each value - if it's not equal to the comparison case AND it's not unknown, skip to next eval
            for (int j = 0; j < sue.size(); j++) {
                if (sue.get(j) != comparison[j] && sue.get(j) != -1)
                    continue sueLoop;
            }
            //if all checks were passed without continuing loop, this is correct aunt sue.
            //adjust for 0-499 range instead of 1-500
            return Integer.toString(i + 1);
        }
        return "Solution not found, input error";
    }

    @Override
    public String part2() {
        //store sues as list of integer list representing counts
        ArrayList<ArrayList<Integer>> sues = new ArrayList<>();
        for (String s : input.split("\n")) {
            ArrayList<String> parts = new ArrayList<>(Arrays.asList(s.split(" ")));
            //categories for reference:
            //children, cats, samoyeds, pomeranians, akitas, vizslas, goldfish, trees, cars, perfumes
            //arraylist will store characteristics (or -1 if unknown) in above order
            ArrayList<Integer> sue = new ArrayList<>();
            if (parts.contains("children:")) {
                String value = parts.get(parts.indexOf("children:") + 1);
                if (value.contains(","))
                    value = value.substring(0, value.length() - 1);
                sue.add(Integer.parseInt(value));
            } else {
                sue.add(-1);
            }
            if (parts.contains("cats:")) {
                String value = parts.get(parts.indexOf("cats:") + 1);
                if (value.contains(","))
                    value = value.substring(0, value.length() - 1);
                sue.add(Integer.parseInt(value));
            } else {
                sue.add(-1);
            }
            if (parts.contains("samoyeds:")) {
                String value = parts.get(parts.indexOf("samoyeds:") + 1);
                if (value.contains(","))
                    value = value.substring(0, value.length() - 1);
                sue.add(Integer.parseInt(value));
            } else {
                sue.add(-1);
            }
            if (parts.contains("pomeranians:")) {
                String value = parts.get(parts.indexOf("pomeranians:") + 1);
                if (value.contains(","))
                    value = value.substring(0, value.length() - 1);
                sue.add(Integer.parseInt(value));
            } else {
                sue.add(-1);
            }
            if (parts.contains("akitas:")) {
                String value = parts.get(parts.indexOf("akitas:") + 1);
                if (value.contains(","))
                    value = value.substring(0, value.length() - 1);
                sue.add(Integer.parseInt(value));
            } else {
                sue.add(-1);
            }
            if (parts.contains("vizslas:")) {
                String value = parts.get(parts.indexOf("vizslas:") + 1);
                if (value.contains(","))
                    value = value.substring(0, value.length() - 1);
                sue.add(Integer.parseInt(value));
            } else {
                sue.add(-1);
            }
            if (parts.contains("goldfish:")) {
                String value = parts.get(parts.indexOf("goldfish:") + 1);
                if (value.contains(","))
                    value = value.substring(0, value.length() - 1);
                sue.add(Integer.parseInt(value));
            } else {
                sue.add(-1);
            }
            if (parts.contains("trees:")) {
                String value = parts.get(parts.indexOf("trees:") + 1);
                if (value.contains(","))
                    value = value.substring(0, value.length() - 1);
                sue.add(Integer.parseInt(value));
            } else {
                sue.add(-1);
            }
            if (parts.contains("cars:")) {
                String value = parts.get(parts.indexOf("cars:") + 1);
                if (value.contains(","))
                    value = value.substring(0, value.length() - 1);
                sue.add(Integer.parseInt(value));
            } else {
                sue.add(-1);
            }
            if (parts.contains("perfumes:")) {
                String value = parts.get(parts.indexOf("perfumes:") + 1);
                if (value.contains(","))
                    value = value.substring(0, value.length() - 1);
                sue.add(Integer.parseInt(value));
            } else {
                sue.add(-1);
            }
            sues.add(sue);
        }
        sueLoop:
        for (int i = 0; i < 500; i++) {
            ArrayList<Integer> sue = sues.get(i);
            //check each value - if it's not equal to the comparison case AND it's not unknown, skip to next eval
            for (int j = 0; j < sue.size(); j++) {
                //cats or trees - greater than
                if (j == 1 || j == 7) {
                    if (sue.get(j) <= comparison[j] && sue.get(j) != -1)
                        continue sueLoop;
                    //poms or goldfish - less than
                } else if (j == 3 || j == 6) {
                    if (sue.get(j) >= comparison[j] && sue.get(j) != -1)
                        continue sueLoop;
                } else {
                    if (sue.get(j) != comparison[j] && sue.get(j) != -1)
                        continue sueLoop;
                }
            }
            //if all checks were passed without continuing loop, this is correct aunt sue.
            //adjust for 0-499 range instead of 1-500
            return Integer.toString(i + 1);
        }
        return "Solution not found, input error";
    }

}
