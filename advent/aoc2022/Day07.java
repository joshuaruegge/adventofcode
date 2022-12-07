package advent.aoc2022;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;
import advent.utilities.utils2022.DirectoryTreeNode;

public class Day07 implements IDay {

    static String input;

    @Override
    public String part1() {
        DirectoryTreeNode root = new DirectoryTreeNode("/");
        DirectoryTreeNode cur = root;

        for(String s : input.split("\n")) {
            //dirs are irrelevant - we'll add them once (if) they're cd'd into
            if(s.endsWith("/") || s.startsWith("dir"))
                continue;
            String[] words = s.split(" ");
            if(words[0].equals("$")) {
                if(words[1].equals("cd")) {
                    //move up level
                    if(words[2].equals("..")) {
                        cur = cur.parent;
                    //initialize subdirectory and move into it
                    } else {
                        DirectoryTreeNode next = new DirectoryTreeNode(words[2]);
                        cur.children.add(next);
                        next.parent = cur;
                        cur = next;
                    }
                }
            //only other possibility is file within directory - add its size to total
            } else {
                cur.filesTotal += Long.parseLong(words[0]);
            }
        }
        //recursively calculate sum of all smaller than given size
        return Long.toString(sizeGreaterThanTotal(root));
    }

    @Override
    public String part2() {
        DirectoryTreeNode root = new DirectoryTreeNode("/");
        DirectoryTreeNode cur = root;

        for(String s : input.split("\n")) {
            //dirs are irrelevant - we'll add them once (if) they're cd'd into
            if(s.endsWith("/") || s.startsWith("dir"))
                continue;
            String[] words = s.split(" ");
            if(words[0].equals("$")) {
                if(words[1].equals("cd")) {
                    //move up level
                    if(words[2].equals("..")) {
                        cur = cur.parent;
                        //initialize subdirectory and move into it
                    } else {
                        DirectoryTreeNode next = new DirectoryTreeNode(words[2]);
                        cur.children.add(next);
                        next.parent = cur;
                        cur = next;
                    }
                }
                //only other possibility is file within directory - add its size to total
            } else {
                cur.filesTotal += Long.parseLong(words[0]);
            }
        }

        //calculate minimum size required to delete
        long required = 30000000 - (70000000 - sizeTotal(root));

        return Long.toString(smallestAbove(root,required));
    }

    //totals size of n and below
    public long sizeTotal(DirectoryTreeNode n) {
        long total = 0;
        for(DirectoryTreeNode o : n.children)
            total += sizeTotal(o);
        total += n.filesTotal;
        return total;
    }

    //totals sizes of n and below that are individually less than 100001
    public long sizeGreaterThanTotal(DirectoryTreeNode n) {
        long total = 0;
        for(DirectoryTreeNode o : n.children)
            total += sizeGreaterThanTotal(o);
        if(sizeTotal(n) < 100001)
            total += sizeTotal(n);
        return total;
    }

    //finds smallest directory in n and below that is still above size required
    public long smallestAbove(DirectoryTreeNode n, long required) {
        long smallest = Long.MAX_VALUE;
        for(DirectoryTreeNode o : n.children)
            smallest = Math.min(smallest,smallestAbove(o,required));
        if(sizeTotal(n) > required)
            smallest = Math.min(smallest,sizeTotal(n));
        return smallest;
    }

    public static void main(String[] args) {
        input = Input.fetchInput(2022,7);
        DayRunner.run(new Day07());
    }
}
