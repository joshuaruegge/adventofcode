package advent.aoc2023;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

import java.util.HashMap;
import java.util.HashSet;

public class Day25 implements IDay {

    static String input;

    public static void main(String[] args) {
        input = Input.fetchInput(2023, 25);
        DayRunner.run(new Day25());
    }

    static String input2 = "jqt: rhn xhk nvd\n" +
            "rsh: frs pzl lsr\n" +
            "xhk: hfx\n" +
            "cmg: qnr nvd lhk bvb\n" +
            "rhn: xhk bvb hfx\n" +
            "bvb: xhk hfx\n" +
            "pzl: lsr hfx nvd\n" +
            "qnr: nvd\n" +
            "ntq: jqt hfx bvb xhk\n" +
            "nvd: lhk\n" +
            "lsr: lhk\n" +
            "rzs: qnr cmg lsr rsh\n" +
            "frs: qnr lhk lsr";

    @Override
    public String part1() {
        HashMap<String, HashSet<String>> wires = new HashMap<>();
        for(String s : input2.split("\n")) {
            String[] chunks = s.split(": ");
            HashSet<String> connections = new HashSet<>();
            for(String t : chunks[1].split(" ")) {
                connections.add(t);
                HashSet<String> otherCon = wires.getOrDefault(t, new HashSet<>());
                otherCon.add(chunks[0]);
                wires.put(t, otherCon);
            }
            wires.put(chunks[0], connections);
        }
        System.out.println(wires + " " + wires.size());
        HashSet<String> allWires = connectedWires("pzl", new HashSet<>(), wires);
        System.out.println(allWires + " " + allWires.size());
        HashSet<String> keys = new HashSet<>(wires.keySet());
        keys.removeAll(allWires);
        System.out.println(keys);
        return null;
    }

    public HashSet<String> connectedWires(String start, HashSet<String> allWires, HashMap<String, HashSet<String>> wires) {
        if(!allWires.add(start))
            return allWires;
        for(String s : wires.get(start)) {
            connectedWires(s, allWires, wires);
        }
        return allWires;
    }

    @Override
    public String part2() {
        return null;
    }
}
