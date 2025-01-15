package advent.aoc2024;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class Day23 implements IDay{

    static String input;

    static String testInput = "kh-tc\n" +
            "qp-kh\n" +
            "de-cg\n" +
            "ka-co\n" +
            "yn-aq\n" +
            "qp-ub\n" +
            "cg-tb\n" +
            "vc-aq\n" +
            "tb-ka\n" +
            "wh-tc\n" +
            "yn-cg\n" +
            "kh-ub\n" +
            "ta-co\n" +
            "de-co\n" +
            "tc-td\n" +
            "tb-wq\n" +
            "wh-td\n" +
            "ta-ka\n" +
            "td-qp\n" +
            "aq-cg\n" +
            "wq-ub\n" +
            "ub-vc\n" +
            "de-ta\n" +
            "wq-aq\n" +
            "wq-vc\n" +
            "wh-yn\n" +
            "ka-de\n" +
            "kh-ta\n" +
            "co-tc\n" +
            "wh-qp\n" +
            "tb-vc\n" +
            "td-yn";

    public static void main(String[] args) {
        input = Input.fetchInput(2024, 23);
        DayRunner.run(new Day23());
    }
    
    @Override
    public String part1() {

        HashMap<String, HashSet<String>> connections = new HashMap<>();
        LinkedList<String> distinct = new LinkedList<>();

        for(String con : input.split("\n")) {
            String[] comps = con.split("-");
            if(!distinct.contains(comps[0]))
                distinct.add(comps[0]);
            if(!distinct.contains(comps[1]))
                distinct.add(comps[1]);
            connections.computeIfAbsent(comps[0], h -> new HashSet<>()).add(comps[1]);
            connections.computeIfAbsent(comps[1], h -> new HashSet<>()).add(comps[0]);
        }

        HashSet<String> tTriangles = new HashSet<>();

        for(String s : distinct) {
            if(s.charAt(0) != 't')
                continue;
            LinkedList<String> iterableNeighbors = new LinkedList<>(connections.get(s));
            for(int i = 0; i < iterableNeighbors.size(); i++) {
                String t = iterableNeighbors.get(i);
                for(int j = i+1; j < iterableNeighbors.size(); j++) {
                    String u = iterableNeighbors.get(j);
                    //add sorted triangle merged into one string to distinct triangles set
                    //set is necessary because otherwise, a triangle containing 2 elements starting with t would be double-counted
                    //and sort/merge is the best way to hash triangles to ensure only distinct are counted
                    if(connections.get(t).contains(u))
                        tTriangles.add(Arrays.stream(new String[] {s, t, u}).sorted().collect(Collectors.joining()));
                }
            }
        }

        return Integer.toString(tTriangles.size());
    }

    @Override
    public String part2() {

        HashMap<String, HashSet<String>> connections = new HashMap<>();
        LinkedList<String> distinct = new LinkedList<>();

        for(String con : input.split("\n")) {
            String[] comps = con.split("-");
            if(!distinct.contains(comps[0]))
                distinct.add(comps[0]);
            if(!distinct.contains(comps[1]))
                distinct.add(comps[1]);
            connections.computeIfAbsent(comps[0], h -> new HashSet<>()).add(comps[1]);
            connections.computeIfAbsent(comps[1], h -> new HashSet<>()).add(comps[0]);
        }

        HashSet<String> largest = new HashSet<>();

        HashSet<String> examined = new HashSet<>();

        for(String s : distinct) {
            if(examined.contains(s))
                continue;

            HashSet<String> connectedToS = connections.get(s);
            connectedToS.add(s);

            while (true) {
                HashMap<String, Integer> numConnected = new HashMap<>();
                for(String t : connectedToS)
                    numConnected.put(t, 0);
                for (String t : connectedToS) {
                    for (String t1 : connectedToS) {
                        if (t.equals(t1))
                            continue;
                        if(connections.get(t).contains(t1)) {
                            numConnected.put(t, numConnected.getOrDefault(t, 0) + 1 );
                        }
                    }
                }
                numConnected.put("", numConnected.values().stream().max(Integer::compare).get());
                String fewest = "";

                for(String t : connectedToS) {
                    if(numConnected.get(t) < numConnected.get(fewest))
                        fewest = t;
                }
                if(fewest.equals(""))
                    break;
                connectedToS.remove(fewest);
            }

            examined.addAll(connectedToS);

            if(connectedToS.size() > largest.size())
                largest = connectedToS;
        }




        /*
        HashSet<HashSet<String>> cliques = new HashSet<>();
        HashSet<String> r = new HashSet<>();
        HashSet<String> p = new HashSet<>(distinct);
        HashSet<String> x = new HashSet<>();

        bronKerbosch(connections, cliques, r, p, x);

        HashSet<String> largest = new HashSet<>();
        for(HashSet<String> h : cliques) {
            if(h.size() > largest.size())
                largest = h;
        }


         */

        return largest.stream().sorted().collect(Collectors.joining(","));
    }
}
