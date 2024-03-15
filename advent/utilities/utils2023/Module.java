package advent.utilities.utils2023;

import advent.utilities.general.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class Module {
    //{string, string, "0"|"1"}?
    public static LinkedList<Pair<String, Pair<String, Boolean>>> pulseQueue = new LinkedList<>();
    public static HashMap<String, Module> modules = new HashMap<>();

    public static int lowCount = 0;
    public static int highCount = 0;

    public String id;
    public LinkedList<String> destinations;

    public Module(String id, LinkedList<String> destinations) {
        this.id = id;
        this.destinations = destinations;
    }

    public static void processPulses() {
        while(!pulseQueue.isEmpty()) {
            Pair<String, Pair<String, Boolean>> pulse = pulseQueue.poll();
            String src = pulse.key;
            modules.getOrDefault(pulse.value.key, new Module(null, null)).processPulse(src, pulse.value.value);
        }
    }

    public static ArrayList<Integer> cyclesOf(LinkedList<String> cycleModules) {
        ArrayList<Integer> cycles = new ArrayList<>();
        for(String s : cycleModules)
            cycles.add(-1);
        int count = 0;
        boolean notComplete = true;
        while(notComplete) {
            count++;
            Module.pulseQueue.add(new Pair<>("", new Pair<>("broadcaster", false)));
            while(!pulseQueue.isEmpty()) {
                Pair<String, Pair<String, Boolean>> pulse = pulseQueue.poll();
                //if true/high pulse being emitted from one of our modules we are interested in finding the cycle of
                //additionally, check if value is -1 to avoid overwriting existing cycle length if present
                if(pulse.value.value && cycleModules.contains(pulse.key) && cycles.get(cycleModules.indexOf(pulse.key)) == -1) {
                    cycles.set(cycleModules.indexOf(pulse.key), count);
                    if(!cycles.contains(-1))
                        notComplete = false;
                }
                String src = pulse.key;
                modules.getOrDefault(pulse.value.key, new Module(null, null)).processPulse(src, pulse.value.value);
            }
        }
        return cycles;
    }

    public void processPulse(String src, boolean pulse) {

    }
}
