package advent.aoc2023;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;
import advent.utilities.utils2023.BinaryTreeNode;

import java.util.HashMap;
import java.util.LinkedList;

public class Day08 implements IDay {

    static String input;

    public static void main(String[] args) {
        input = Input.fetchInput(2023, 8);
        DayRunner.run(new Day08());
    }

    @Override
    public String part1() {
        HashMap<String, BinaryTreeNode> nodes = new HashMap<>();
        String[] halves = input.split("\n\n");

        for (String line : halves[1].split("\n")) {
            String[] parts = line.split(" = ");
            BinaryTreeNode root = nodes.getOrDefault(parts[0], new BinaryTreeNode(parts[0]));
            String[] leaves = parts[1].split(", ");
            leaves[0] = leaves[0].substring(1);
            leaves[1] = leaves[1].substring(0, leaves[1].length() - 1);
            BinaryTreeNode left = nodes.getOrDefault(leaves[0], new BinaryTreeNode(leaves[0]));
            root.left = left;
            nodes.put(leaves[0], left);
            BinaryTreeNode right = nodes.getOrDefault(leaves[1], new BinaryTreeNode(leaves[1]));
            root.right = right;
            nodes.put(leaves[1], right);
            nodes.put(parts[0], root);
        }

        String instr = halves[0];
        int index = 0;
        int stepCount = 0;
        BinaryTreeNode cur = nodes.get("AAA");
        while (!cur.name.equals("ZZZ")) {
            if (instr.charAt(index) == 'L') {
                cur = cur.left;
            } else {
                cur = cur.right;
            }
            index++;
            stepCount++;
            if (index == instr.length()) index = 0;
        }

        return Integer.toString(stepCount);
    }

    @Override
    public String part2() {
        HashMap<String, BinaryTreeNode> nodes = new HashMap<>();
        LinkedList<BinaryTreeNode> starts = new LinkedList<>();
        String[] halves = input.split("\n\n");

        for (String line : halves[1].split("\n")) {
            String[] parts = line.split(" = ");
            BinaryTreeNode root = nodes.getOrDefault(parts[0], new BinaryTreeNode(parts[0]));
            String[] leaves = parts[1].split(", ");
            leaves[0] = leaves[0].substring(1);
            leaves[1] = leaves[1].substring(0, leaves[1].length() - 1);
            BinaryTreeNode left = nodes.getOrDefault(leaves[0], new BinaryTreeNode(leaves[0]));
            root.left = left;
            nodes.put(leaves[0], left);
            BinaryTreeNode right = nodes.getOrDefault(leaves[1], new BinaryTreeNode(leaves[1]));
            root.right = right;
            nodes.put(leaves[1], right);
            nodes.put(parts[0], root);
            if (parts[0].charAt(2) == 'A') {
                starts.add(root);
            }
        }

        LinkedList<Integer> cycleCounts = new LinkedList<>();
        for (BinaryTreeNode start : starts) {
            String instr = halves[0];
            int index = 0;
            int steps = 0;
            BinaryTreeNode cur = start;
            while (!(cur.name.charAt(2) == 'Z')) {
                if (instr.charAt(index) == 'L') {
                    cur = cur.left;
                } else {
                    cur = cur.right;
                }
                index++;
                steps++;
                if (index == instr.length()) index = 0;
            }
            cycleCounts.add(steps);
        }

        long lcm = 1;
        for (int cycle : cycleCounts) {
            lcm = (lcm * cycle) / gcd(lcm, cycle);
        }

        return Long.toString(lcm);
    }

    long gcd(long a, long b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }
}
