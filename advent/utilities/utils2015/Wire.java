package advent.utilities.utils2015;

import java.util.HashMap;

public class Wire {
    //static collection of all wires, mapped to ID
    public static HashMap<String, Wire> wires = new HashMap<>();

    //booleans for whether wire values have been calculated
    boolean hasResult, hasInput1, hasInput2;

    //values to be calculated
    int result, input1, input2;

    //input sources
    String input1Source, input2Source;

    //operation type
    String op;

    //wire ID
    String id;

    //already-calculated result constructor
    //for direct assignment, i.e. 127 -> b
    public Wire(String id, String result) {
        this.id = id;
        //determine whether result is parseable int, or another wire
        try {
            //convert int, set as result, note that result has been calculated
            this.result = Integer.parseInt(result);
            hasResult = true;
        } catch (NumberFormatException e) {
            op = "DIRECT";
            input1Source = result;
        }
    }

    //operation-based result constructor
    public Wire(String id, String operator, String input1, String input2) {
        this.id = id;
        op = operator;
        //try parsing each input as number, otherwise set to source (i.e., other wire to calculate result of and use)
        try {
            this.input1 = Integer.parseInt(input1);
            hasInput1 = true;
        } catch (NumberFormatException e) {
            input1Source = input1;
        }
        try {
            this.input2 = Integer.parseInt(input2);
            hasInput2 = true;
        } catch (NumberFormatException e) {
            input2Source = input2;
        }
    }

    //method to calculate result
    //if values for inputs are needed for other wires, calculates them recursively
    public int result() {
        //returns result if already known
        if (hasResult) return result;
        //calculate input 1 if necessary
        if (!hasInput1) input1 = wires.get(input1Source).result();
        //calculate input 2 if necessary
        if (!hasInput2 && input2Source != null) input2 = wires.get(input2Source).result();
        switch (op) {
            case "DIRECT" -> result = input1;
            case "AND" -> result = input1 & input2;
            case "OR" -> result = input1 | input2;
            case "LSHIFT" -> result = input1 << input2;
            case "RSHIFT" -> result = input1 >> input2;
            case "NOT" -> result = ~input1;
        }
        hasResult = true;
        return result;
    }
}
