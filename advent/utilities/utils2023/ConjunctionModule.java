package advent.utilities.utils2023;

import advent.utilities.general.Pair;

import java.util.HashMap;
import java.util.LinkedList;

public class ConjunctionModule extends Module{
    public HashMap<String, Boolean> sources;

    public ConjunctionModule(String id, LinkedList<String> destinations) {
        super(id, destinations);
        modules.put(id, this);
        sources = new HashMap<>();
    }

    public void processPulse(String src, boolean pulse) {
        sources.put(src, pulse);
        boolean nextPulse = false;
        //if any sources have last recorded as false/low, nextPulse is high
        for(boolean b : sources.values()) {
            nextPulse |= !b;
        }
        for(String dest : destinations) {
            pulseQueue.add(new Pair<>(id, new Pair<>(dest, nextPulse)));
            if(nextPulse)
                highCount++;
            else
                lowCount++;
        }
    }
}
