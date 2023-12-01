package advent.aoc2015;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;
import advent.utilities.utils2015.Wire;

public class Day07 implements IDay {

    static String input;

    //buffer part 1 result for part 2 usage
    static String part1;

    public static void main(String[] args) {
        input = Input.fetchInput(2015, 7);
        DayRunner.run(new Day07());
    }

    @Override
    public String part1() {
        //parse input into wires
        for (String s : input.split("\n")) {
            String[] parts = s.split(" ");
            //determine wire type based on number of parameters
            //direct input
            if (parts.length == 3) {
                //destination wire, number/source
                Wire.wires.put(parts[2], new Wire(parts[2], parts[0]));
                //NOT operation
            } else if (parts.length == 4) {
                //destination wire, operation, input 1, (nonpresent input 2)
                Wire.wires.put(parts[3], new Wire(parts[3], parts[0], parts[1], null));
                //2-input operation
            } else {
                //destination wire, operation, input 1, input 2
                Wire.wires.put(parts[4], new Wire(parts[4], parts[1], parts[0], parts[2]));
            }
        }
        //then, just calculate result of wire a. result method will recursively calculate other wires as needed
        part1 = Integer.toString(Wire.wires.get("a").result());
        return part1;
    }

    @Override
    public String part2() {
        //reinitialize all wires
        Wire.wires.clear();
        for (String s : input.split("\n")) {
            String[] parts = s.split(" ");
            //direct input
            if (parts.length == 3) {
                //destination wire, result/source
                Wire.wires.put(parts[2], new Wire(parts[2], parts[0]));
                //NOT operation
            } else if (parts.length == 4) {
                //destination wire, operation, input 1, nonpresent input 2
                Wire.wires.put(parts[3], new Wire(parts[3], parts[0], parts[1], null));
                //2-input operation
            } else {
                //destination wire, operation, input 1, input 2
                Wire.wires.put(parts[4], new Wire(parts[4], parts[1], parts[0], parts[2]));
            }
        }
        //update wire b with cached part 1 value
        Wire.wires.put("b", new Wire("b", part1));
        //then, recalculate a and return
        return Integer.toString(Wire.wires.get("a").result());
    }

}

