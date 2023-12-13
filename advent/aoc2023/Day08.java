package advent.aoc2023;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;
import advent.utilities.utils2023.BTreeNode;

import java.util.ArrayList;
import java.util.HashMap;

public class Day08 implements IDay {

    static String input;

    public static void main(String[] args) {
        input = Input.fetchInput(2023,8);
        DayRunner.run(new Day08());
    }

    @Override
    public String part1() {
        HashMap<String, BTreeNode> nodes = new HashMap<>();
        String[] halves = input.split("\n\n");
        for(String line : halves[1].split("\n")) {
            String[] parts = line.split(" = ");
            BTreeNode root = nodes.getOrDefault(parts[0], new BTreeNode(parts[0]));
            String[] leaves = parts[1].split(", ");
            leaves[0] = leaves[0].substring(1);
            leaves[1] = leaves[1].substring(0, leaves[1].length() - 1);
            BTreeNode left = nodes.getOrDefault(leaves[0], new BTreeNode(leaves[0]));
            root.left = left;
            nodes.put(leaves[0],left);
            BTreeNode right = nodes.getOrDefault(leaves[1], new BTreeNode(leaves[1]));
            root.right = right;
            nodes.put(leaves[1],right);
            nodes.put(parts[0],root);
        }
        String instr = halves[0];
        int index = 0;
        int step = 0;
        BTreeNode cur = nodes.get("AAA");
        while(!cur.name.equals("ZZZ")) {
            if(instr.charAt(index) == 'L') {
                cur = cur.left;
            } else {
                cur = cur.right;
            }
            index++;
            step++;
            if(index == instr.length())
                index = 0;
        }
        return Integer.toString(step);
    }

    @Override
    public String part2() {
        HashMap<String, BTreeNode> nodes = new HashMap<>();
        ArrayList<BTreeNode> starts = new ArrayList<>();
        String[] halves = input.split("\n\n");
        for(String line : halves[1].split("\n")) {
            String[] parts = line.split(" = ");
            BTreeNode root = nodes.getOrDefault(parts[0], new BTreeNode(parts[0]));
            String[] leaves = parts[1].split(", ");
            leaves[0] = leaves[0].substring(1);
            leaves[1] = leaves[1].substring(0, leaves[1].length() - 1);
            BTreeNode left = nodes.getOrDefault(leaves[0], new BTreeNode(leaves[0]));
            root.left = left;
            nodes.put(leaves[0],left);
            BTreeNode right = nodes.getOrDefault(leaves[1], new BTreeNode(leaves[1]));
            root.right = right;
            nodes.put(leaves[1],right);
            nodes.put(parts[0],root);
            if(parts[0].charAt(2) == 'A') {
                starts.add(root);
            }
        }
        ArrayList<Integer> mults = new ArrayList<>();
        for(BTreeNode start : starts) {
            String instr = halves[0];
            int index = 0;
            int steps = 0;
            BTreeNode cur = start;
            while(!(cur.name.charAt(2)=='Z')) {
                if(instr.charAt(index) == 'L') {
                    cur = cur.left;
                } else {
                    cur = cur.right;
                }
                index++;
                steps++;
                if(index == instr.length())
                    index = 0;
            }
            mults.add(steps);
        }

        long lcm = mults.get(0);
        for(int i = 1; i < mults.size(); i++) {
            lcm = (lcm * mults.get(i))/gcd(lcm, mults.get(i));
        }
        return Long.toString(lcm);
    }

    long gcd(long a, long b) {
        if(b == 0)
            return a;
        return gcd(b, a % b);
    }
}
