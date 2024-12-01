package advent.aoc2024;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Day01 implements IDay {

    static String input;

    public static void main(String[] args) {
        input = Input.fetchInput(2024, 1);
        DayRunner.run(new Day01());
    }

    @Override
    public String part1() {
        int result = 0;

        ArrayList<Integer> firstList = new ArrayList<>();
        ArrayList<Integer> secondList = new ArrayList<>();

        for(String s : input.split("\n")) {
            String[] chunks = s.split("   ");
            firstList.add(Integer.parseInt(chunks[0]));
            secondList.add(Integer.parseInt(chunks[1]));
        }

        Collections.sort(firstList);
        Collections.sort(secondList);

        for(int i = 0; i < firstList.size(); i++) {
            result += Math.abs(secondList.get(i) - firstList.get(i));
        }

        return Integer.toString(result);
    }

    @Override
    public String part2() {
        int result = 0;

        ArrayList<Integer> firstList = new ArrayList<>();
        HashMap<Integer, Integer> secondListFreqs = new HashMap<>();

        for(String s : input.split("\n")) {
            String[] chunks = s.split("   ");
            firstList.add(Integer.parseInt(chunks[0]));
            int secondListItem = Integer.parseInt(chunks[1]);
            secondListFreqs.put(secondListItem, secondListFreqs.getOrDefault(secondListItem,0) + 1);
        }

        for(int i : firstList) {
            result += i * secondListFreqs.getOrDefault(i, 0);
        }

        return Integer.toString(result);
    }
}
