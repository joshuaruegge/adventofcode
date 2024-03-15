package advent.aoc2023;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;
import advent.utilities.general.Pair;

import java.util.ArrayList;

public class Day15 implements IDay {

    static String input;

    public static void main(String[] args) {
        input = Input.fetchInput(2023, 15).trim();
        DayRunner.run(new Day15());
    }

    @Override
    public String part1() {
        int total = 0;
        for (String s : input.split(",")) {
            total += hash(s);
        }
        return Integer.toString(total);
    }

    public int hash(String s) {
        int cur = 0;
        for (char c : s.toCharArray()) {
            cur += c;
            cur *= 17;
            cur %= 256;
        }
        return cur;
    }

    @Override
    public String part2() {
        ArrayList<ArrayList<Pair<String, Integer>>> map = new ArrayList<>();
        for (int i = 0; i < 256; i++)
            map.add(new ArrayList<>());
        for (String s : input.split(",")) {
            int labelEnd = Math.max(s.indexOf('-'), s.indexOf('='));
            String label = s.substring(0, labelEnd);
            int hash = hash(label);
            if (s.charAt(labelEnd) == '-') {
                ArrayList<Pair<String, Integer>> box = map.get(hash);
                for (Pair<String, Integer> l : box) {
                    if (l.key.equals(label)) {
                        box.remove(l);
                        break;
                    }
                }
            } else {
                int focal = Integer.parseInt(s.substring(labelEnd + 1));
                Pair<String, Integer> newLens = new Pair<>(label, focal);
                ArrayList<Pair<String, Integer>> box = map.get(hash);
                for (int i = 0; i < box.size(); i++) {
                    if (box.get(i).key.equals(label)) {
                        box.set(i, newLens);
                        break;
                    }
                }
                if (!box.contains(newLens)) box.add(newLens);
            }
        }
        int total = 0;
        for (int i = 0; i < 256; i++) {
            ArrayList<Pair<String, Integer>> box = map.get(i);
            for (int j = 0; j < box.size(); j++) {
                total += (i + 1) * (j + 1) * box.get(j).value;
            }
        }
        return Integer.toString(total);
    }
}
