package advent.utilities.utils2023;

import advent.utilities.general.Pair;

import java.util.LinkedList;

public class FlipFlopModule extends Module{
    boolean state;

    public FlipFlopModule(String id, LinkedList<String > destinations) {
        super(id, destinations);
        modules.put(id, this);
        state = false;
    }

    public void processPulse(String src, boolean pulse) {
        if(pulse)
            return;
        if(state) {
            for(String dest : destinations) {
                pulseQueue.add(new Pair<>(id, new Pair<>(dest, false)));
                lowCount++;
            }
        } else {
            for(String dest : destinations) {
                pulseQueue.add(new Pair<>(id, new Pair<>(dest, true)));
                highCount++;
            }
        }
        state = !state;
    }
}
