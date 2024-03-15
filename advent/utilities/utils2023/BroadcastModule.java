package advent.utilities.utils2023;

import advent.utilities.general.Pair;

import java.util.LinkedList;

public class BroadcastModule extends Module{

    public BroadcastModule(String id, LinkedList<String> destinations) {
        super(id, destinations);
        modules.put(id, this);
    }

    public void processPulse(String src, boolean pulse) {
        for(String dest : destinations) {
            Module.pulseQueue.add(new Pair<>(id, new Pair<>(dest, pulse)));
            if(pulse) {
                highCount++;
            } else {
                lowCount++;
            }
        }
    }
}
