package advent.aoc2023;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

public class Day09 implements IDay {

    static String input;

    public static void main(String[] args) {
        input = Input.fetchInput(2023, 9);
        DayRunner.run(new Day09());
    }

    @Override
    public String part1() {
        long total = 0;
        for(String s : input.split("\n")) {
            ArrayList<ArrayList<Integer>> tower = new ArrayList<>();
            ArrayList<Integer> start = new ArrayList<>();
            for(String t : s.split(" "))
                start.add(Integer.parseInt(t));
            tower.add(start);
            boolean cont = true;
            int level = 0;
            while(cont) {
                cont = false;
                ArrayList<Integer> prev = tower.get(level);
                ArrayList<Integer> cur = new ArrayList<>();
                for(int i = 0; i < prev.size() - 1; i++) {
                    int res = prev.get(i+1)-prev.get(i);
                    if(res != 0)
                        cont = true;
                    cur.add(res);
                }
                tower.add(cur);
                level++;
            }
            long sum = 0;
            for(ArrayList<Integer> l : tower)
                sum += l.get(l.size() - 1);
            total += sum;
        }
        return Long.toString(total);
    }

    @Override
    public String part2() {
        long total = 0;
        for(String s : input.split("\n")) {
            ArrayList<ArrayList<Integer>> tower = new ArrayList<>();
            ArrayList<Integer> start = new ArrayList<>();
            for(String t : s.split(" "))
                start.add(Integer.parseInt(t));
            tower.add(start);
            boolean cont = true;
            int level = 0;
            while(cont) {
                cont = false;
                ArrayList<Integer> prev = tower.get(level);
                ArrayList<Integer> cur = new ArrayList<>();
                for(int i = 0; i < prev.size() - 1; i++) {
                    int res = prev.get(i+1)-prev.get(i);
                    if(res != 0)
                        cont = true;
                    cur.add(res);
                }
                tower.add(cur);
                level++;
            }
            Collections.reverse(tower);
            long sum = 0;
            for(ArrayList<Integer> l : tower)
                sum = l.get(0) - sum;
            total += sum;
        }
        return Long.toString(total);
    }
}
