package advent.aoc2022;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

import advent.utilities.utils2022.Monkey;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day11 implements IDay {

    static String input;

    @Override
    public String part1() {
        ArrayList<Monkey> monkeys = new ArrayList<Monkey>();
        for(String s : input.split("\n\n")) {
            Monkey m = new Monkey();
            String[] lines = s.split("\n");
            for(int i = 0; i < lines.length; i++)
                lines[i] = lines[i].trim();
            //items
            String[] itemWords = lines[1].split(", | +");
            System.out.println(Arrays.toString(itemWords));
            ArrayList<Long> items = new ArrayList<Long>();
            for(int i = 2; i < itemWords.length; i++)
                items.add(Long.parseLong(itemWords[i]));
            m.items = items;
            //operation
            String[] opWords = lines[2].split(" ");
            m.add = opWords[opWords.length - 2].equals("+");
            //handle the one that squares
            if(opWords[opWords.length - 1].equals("old"))
                m.square = true;
            else
                m.opValue = Integer.parseInt(opWords[opWords.length - 1]);
            //test
            String[] testWords = lines[3].split(" ");
            m.mod = Integer.parseInt(testWords[testWords.length - 1]);
            //true
            String[] trueWords = lines[4].split(" ");
            m.trueDest = Integer.parseInt(trueWords[trueWords.length-1]);
            String[] falseWords = lines[5].split(" ");
            m.falseDest = Integer.parseInt(falseWords[falseWords.length-1]);
            monkeys.add(m);
        }
        //keep track of number of inspections per monkey (matched indexing)
        int[] inspectionCounts = new int[monkeys.size()];
        for(int iter = 0; iter < 20; iter++) {
            for(int monkeyNum = 0; monkeyNum < monkeys.size(); monkeyNum++) {
                Monkey cur = monkeys.get(monkeyNum);
                //skip if nothing to examine
                if(cur.items.size() == 0)
                    continue;
                while(cur.items.size() > 0) {
                    //pop item examining off
                    long curItem = cur.items.remove(0);
                    inspectionCounts[monkeyNum]++;
                    //perform operation
                    if(cur.square) {
                        curItem *= curItem;
                    } else if (cur.add) {
                        curItem += cur.opValue;
                    } else {
                        curItem *= cur.opValue;
                    }
                    curItem /= 3;
                    //pass on to next monkey
                    if (curItem % cur.mod == 0) {
                        monkeys.get(cur.trueDest).items.add(curItem);
                    } else {
                        monkeys.get(cur.falseDest).items.add(curItem);
                    }
                }
            }
        }
        //sort number of inspections and return multiplication of two highest
        Arrays.sort(inspectionCounts);
        return Integer.toString(inspectionCounts[inspectionCounts.length - 2] * inspectionCounts[inspectionCounts.length - 1]);
    }

    @Override
    public String part2() {
        ArrayList<Monkey> monkeys = new ArrayList<Monkey>();
        for(String s : input.split("\n\n")) {
            Monkey m = new Monkey();
            String[] lines = s.split("\n");
            for(int i = 0; i < lines.length; i++)
                lines[i] = lines[i].trim();
            //items
            String[] itemWords = lines[1].split(", | +");
            System.out.println(Arrays.toString(itemWords));
            ArrayList<Long> items = new ArrayList<Long>();
            for(int i = 2; i < itemWords.length; i++)
                items.add(Long.parseLong(itemWords[i]));
            m.items = items;
            //operation
            String[] opWords = lines[2].split(" ");
            m.add = opWords[opWords.length - 2].equals("+");
            if(opWords[opWords.length - 1].equals("old"))
                m.square = true;
            else
                m.opValue = Integer.parseInt(opWords[opWords.length - 1]);
            //test
            String[] testWords = lines[3].split(" ");
            m.mod = Integer.parseInt(testWords[testWords.length - 1]);
            //true
            String[] trueWords = lines[4].split(" ");
            m.trueDest = Integer.parseInt(trueWords[trueWords.length-1]);
            String[] falseWords = lines[5].split(" ");
            m.falseDest = Integer.parseInt(falseWords[falseWords.length-1]);
            monkeys.add(m);
        }
        //the only time we ever care about an item's number is when deciding whether it's modulo a monkey's check
        //therefore, to keep worry numbers small, we can keep all worry numbers modulo the LCM of all monkey check values
        //this keeps the numbers relatively small without affecting the results of the true/false check
        List<Integer> checks = monkeys.stream().map(x -> x.mod).toList();
        int lcm = lcm(checks,0);
        System.out.println(lcm);
        long[] inspectionCounts = new long[monkeys.size()];
        for(int iter = 0; iter < 10000; iter++) {
            for(int monkeyNum = 0; monkeyNum < monkeys.size(); monkeyNum++) {
                Monkey cur = monkeys.get(monkeyNum);
                if(cur.items.size() == 0)
                    continue;
                while(cur.items.size() > 0) {
                    long curItem = cur.items.remove(0);
                    inspectionCounts[monkeyNum]++;
                    if(cur.square) {
                        curItem *= curItem;
                    } else if (cur.add) {
                        curItem += cur.opValue;
                    } else {
                        curItem *= cur.opValue;
                    }
                    curItem %= lcm;
                    if (curItem % cur.mod == 0) {
                        monkeys.get(cur.trueDest).items.add(curItem);
                    } else {
                        monkeys.get(cur.falseDest).items.add(curItem);
                    }
                }
            }
        }
        Arrays.sort(inspectionCounts);
        return Long.toString(inspectionCounts[inspectionCounts.length - 2] * inspectionCounts[inspectionCounts.length - 1]);
    }

    //recursive calculation of lcm of arraylist nums
    //lcm of two numbers: (a * b) / greatest common factor of a and b
    public static int lcm(List<Integer> nums, int index) {
        if(index == nums.size() - 1)
            return nums.get(index);
        int a = nums.get(index);
        int b = lcm(nums,index+1);
        return (a * b)/gcd(a,b);
    }

    //recursive GCD calculation
    public static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public static void main(String[] args) {
        input = Input.fetchInput(2022,11);
        DayRunner.run(new Day11());
    }

}
