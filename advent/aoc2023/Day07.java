package advent.aoc2023;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;
import advent.utilities.utils2023.Hand;

import java.util.ArrayList;
import java.util.Collections;

public class Day07 implements IDay {

    static String input;

    public static void main(String[] args) {
        input = Input.fetchInput(2023, 7);
        DayRunner.run(new Day07());
    }

    @Override
    public String part1() {
        ArrayList<Hand> hands = new ArrayList<>();
        for(String s : input.split("\n")) {
            String[] parts = s.split(" ");
            hands.add(new Hand(parts[0],Integer.parseInt(parts[1]),false));
        }
        Collections.sort(hands);
        int tot = 0;
        for(int i = 0; i < hands.size(); i++) {
            tot += (i+1)*hands.get(i).bid;
        }
        return Integer.toString(tot);
    }

    @Override
    public String part2() {
        ArrayList<Hand> hands = new ArrayList<>();
        for(String s : input.split("\n")) {
            String[] parts = s.split(" ");
            hands.add(new Hand(parts[0],Integer.parseInt(parts[1]),true));
        }
        Collections.sort(hands);
        int tot = 0;
        for(int i = 0; i < hands.size(); i++) {
            tot += (i+1)*hands.get(i).bid;
        }
        return Integer.toString(tot);
    }
}
