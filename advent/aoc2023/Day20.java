package advent.aoc2023;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;
import advent.utilities.general.Pair;
import advent.utilities.utils2023.BroadcastModule;
import advent.utilities.utils2023.ConjunctionModule;
import advent.utilities.utils2023.FlipFlopModule;
import advent.utilities.utils2023.Module;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Day20 implements IDay {

    static String input;

    public static void main(String[] args) {
        input = Input.fetchInput(2023, 20);
        DayRunner.run(new Day20());
    }

    @Override
    public String part1() {
        LinkedList<String> conj = new LinkedList<>();
        for(String line : input.split("\n")) {
            String[] parts = line.split(" -> ");
            LinkedList<String> dests = new LinkedList<>(Arrays.asList(parts[1].split(", ")));
            if(parts[0].equals("broadcaster")) {
                BroadcastModule b = new BroadcastModule(parts[0], dests);
            } else if(parts[0].charAt(0) == '%') {
                FlipFlopModule f = new FlipFlopModule(parts[0].substring(1), dests);
            } else {
                String id = parts[0].substring(1);
                ConjunctionModule c = new ConjunctionModule(id, dests);
                conj.add(id);
            }
        }
        //post-process - inputs of conj modules
        for(String c : conj) {
            for(Module m : Module.modules.values()) {
                if(m.destinations.contains(c))
                    ((ConjunctionModule) Module.modules.get(c)).sources.put(m.id, false);
            }
        }
        //Module.modules.put("output",new Module("output", null));
        for(int i = 0; i < 1000; i++) {
            Module.pulseQueue.add(new Pair<>("", new Pair<>("broadcaster", false)));
            Module.lowCount++;
            Module.processPulses();
        }
        return Integer.toString(Module.lowCount * Module.highCount);
    }

    @Override
    public String part2() {
        LinkedList<String> conj = new LinkedList<>();
        for(String line : input.split("\n")) {
            String[] parts = line.split(" -> ");
            LinkedList<String> dests = new LinkedList<>(Arrays.asList(parts[1].split(", ")));
            if(parts[0].equals("broadcaster")) {
                BroadcastModule b = new BroadcastModule(parts[0], dests);
            } else if(parts[0].charAt(0) == '%') {
                FlipFlopModule f = new FlipFlopModule(parts[0].substring(1), dests);
            } else {
                String id = parts[0].substring(1);
                ConjunctionModule c = new ConjunctionModule(id, dests);
                conj.add(id);
            }
        }
        ConjunctionModule rx = new ConjunctionModule("rx",  new LinkedList<>());
        conj.add("rx");
        //post-process - inputs of conj modules
        for(String c : conj) {
            for(Module m : Module.modules.values()) {
                if(m.destinations.contains(c))
                    ((ConjunctionModule) Module.modules.get(c)).sources.put(m.id, false);
            }
        }
        //rx is always a module with a single conjunction module as the source - this finds the ID of that source
        String rxSource = ((ConjunctionModule) Module.modules.get("rx")).sources.keySet().stream().findFirst().get();
        //according to the rules, this conjunction module will output a low to rx when all its inputs are high
        //so, we assume each input of this conjunction module becomes high cyclically
        //and find the LCM of each input's high period
        LinkedList<String> highModules = new LinkedList<>(((ConjunctionModule) Module.modules.get(rxSource)).sources.keySet());
        long lcm = 1;
        ArrayList<Integer> cyclesOf = Module.cyclesOf(highModules);
        for(int i : cyclesOf) {
            lcm = (lcm * i)/gcd(lcm, i);
        }
        return Long.toString(lcm);
    }

    long gcd(long a, long b) {
        if(b == 0)
            return a;
        return gcd(b, a % b);
    }
}
