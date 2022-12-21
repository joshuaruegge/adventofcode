package advent.aoc2022;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;
import advent.utilities.utils2022.TreeMonkey;

public class Day21 implements IDay {

    static String input;

    @Override
    public String part1() {
        for(String s : input.split("\n")) {
            String[] words = s.split(": | ");
            if(words.length == 2) {
                TreeMonkey m = new TreeMonkey(words[0], Integer.parseInt(words[1]));
            } else {
                int operation = switch (words[2]) {
                    case "+" -> 0;
                    case "-" -> 1;
                    case "*" -> 2;
                    case "/" -> 3;
                    default -> -1;
                };
                TreeMonkey m = new TreeMonkey(words[0], words[1], words[3], operation);
            }
        }

        return Long.toString(TreeMonkey.find("root").get());
    }

    @Override
    public String part2() {
        TreeMonkey.monkeys.clear();
        for(String s : input.split("\n")) {
            String[] words = s.split(": | ");
            if(words.length == 2) {
                TreeMonkey m = new TreeMonkey(words[0], Integer.parseInt(words[1]));
            } else {
                int operation = switch (words[2]) {
                    case "+" -> 0;
                    case "-" -> 1;
                    case "*" -> 2;
                    case "/" -> 3;
                    default -> -1;
                };
                TreeMonkey m = new TreeMonkey(words[0], words[1], words[3], operation);
            }
        }
        String rootSource1 = TreeMonkey.find("root").source1;
        String rootSource2 = TreeMonkey.find("root").source2;

        //determines which of root's two children can and cannot be found without using the value of humn
        String findable = (contains(TreeMonkey.find(rootSource2),"humn") ? rootSource1 : rootSource2);
        String notFindable = (findable.equals(rootSource1) ? rootSource2 : rootSource1);

        //find values for all non-humn-based interactions
        for(TreeMonkey m : TreeMonkey.monkeys)
            if(!contains(m,"humn"))
                m.get();

        long required = TreeMonkey.find(findable).get();
        TreeMonkey cur = TreeMonkey.find(notFindable);
        //perform operations in reverse, downwards until we get to humn
        while(!cur.id.equals("humn")) {
            TreeMonkey s1 = TreeMonkey.find(cur.source1);
            TreeMonkey s2 = TreeMonkey.find(cur.source2);
            //perform operation in reverse, using known value
            //ex: if required = a + b, and we know required and a, b = required - a
            //if s1 is our unknown, perform in reverse with s2
            if(contains(s1,"humn")) {
                switch (cur.operation) {
                    case 0 -> required -= s2.get();
                    case 1 -> required += s2.get();
                    case 2 -> required /= s2.get();
                    case 3 -> required *= s2.get();
                }
                cur = s1;
            } else {
                //if s2 is our unknown, perform in reverse with s1
                switch (cur.operation) {
                    case 0 -> required -= s1.get();
                    //REMEMBER!!! subtraction isn't commutative
                    //if the equation is required = s1 - s2
                    //s1 = required + s2
                    //but s2 = s1 - required
                    case 1 -> required = s1.get() - required;
                    case 2 -> required /= s1.get();
                    case 3 -> required *= s1.get();
                }
                cur = s2;
            }
        }

        return Long.toString(required);
    }

    public static void main(String[] args) {
        input = Input.fetchInput(2022,21);
        DayRunner.run(new Day21());
    }

    public boolean contains(TreeMonkey m, String s) {
        if(m.id.equals(s))
            return true;
        if(m.source1 == null && m.source2 == null)
            return false;
        else
            return contains(TreeMonkey.find(m.source1),s) || contains(TreeMonkey.find(m.source2),s);
    }
}
