package advent.utilities.utils2024;

import java.util.HashMap;

public class LogicWire {

    public static HashMap<String, LogicWire> wires = new HashMap<>();

    public String name;
    int optype;

    public boolean valueSet;
    boolean value;
    String s1;
    String s2;
    public LogicWire(String name, boolean value) {
        this.name = name;
        this.value = value;
        valueSet = true;
        wires.put(name, this);
        optype = 0;
        s1 = "";
        s2 = "";
    }

    public LogicWire(String name, int optype, String s1, String s2) {
        this.name = name;
        this.optype = optype;
        valueSet = false;
        wires.put(name,this);
        this.s1 = s1;
        this.s2 = s2;
    }

    public boolean getValue() {
        if(valueSet)
            return value;

        if(optype == 0) {
            value = wires.get(s1).getValue() && wires.get(s2).getValue();
        } else if(optype == 1) {
            value =  wires.get(s1).getValue() || wires.get(s2).getValue();
        } else {
            value =  wires.get(s1).getValue() ^ wires.get(s2).getValue();
        }

        valueSet = true;
        return value;
    }
}
