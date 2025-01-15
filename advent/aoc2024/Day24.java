package advent.aoc2024;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;
import advent.utilities.utils2024.LogicWire;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Day24 implements IDay {

    static String input;

    static String testInput = "x00: 1\n" +
            "x01: 0\n" +
            "x02: 1\n" +
            "x03: 1\n" +
            "x04: 0\n" +
            "y00: 1\n" +
            "y01: 1\n" +
            "y02: 1\n" +
            "y03: 1\n" +
            "y04: 1\n" +
            "\n" +
            "ntg XOR fgs -> mjb\n" +
            "y02 OR x01 -> tnw\n" +
            "kwq OR kpj -> z05\n" +
            "x00 OR x03 -> fst\n" +
            "tgd XOR rvg -> z01\n" +
            "vdt OR tnw -> bfw\n" +
            "bfw AND frj -> z10\n" +
            "ffh OR nrd -> bqk\n" +
            "y00 AND y03 -> djm\n" +
            "y03 OR y00 -> psh\n" +
            "bqk OR frj -> z08\n" +
            "tnw OR fst -> frj\n" +
            "gnj AND tgd -> z11\n" +
            "bfw XOR mjb -> z00\n" +
            "x03 OR x00 -> vdt\n" +
            "gnj AND wpb -> z02\n" +
            "x04 AND y00 -> kjc\n" +
            "djm OR pbm -> qhw\n" +
            "nrd AND vdt -> hwm\n" +
            "kjc AND fst -> rvg\n" +
            "y04 OR y02 -> fgs\n" +
            "y01 AND x02 -> pbm\n" +
            "ntg OR kjc -> kwq\n" +
            "psh XOR fgs -> tgd\n" +
            "qhw XOR tgd -> z09\n" +
            "pbm OR djm -> kpj\n" +
            "x03 XOR y03 -> ffh\n" +
            "x00 XOR y04 -> ntg\n" +
            "bfw OR bqk -> z06\n" +
            "nrd XOR fgs -> wpb\n" +
            "frj XOR qhw -> z04\n" +
            "bqk OR frj -> z07\n" +
            "y03 OR x01 -> nrd\n" +
            "hwm AND bqk -> z03\n" +
            "tgd XOR rvg -> z12\n" +
            "tnw OR pbm -> gnj";

    public static void main(String[] args) {
        input = Input.fetchInput(2024, 24);
        DayRunner.run(new Day24());
    }

    @Override
    public String part1() {
        String[] chunks = input.split("\n\n");
        for(String line : chunks[0].split("\n")) {
            String[] halves = line.split(": ");
            LogicWire wire = new LogicWire(halves[0], halves[1].equals("1"));
        }

        for(String line : chunks[1].split("\n")) {
            String[] parts = line.split(" ");
            System.out.println(Arrays.toString(parts));
            int opcode;
            switch (parts[1]) {
                    case "AND" -> opcode = 0;
                    case "OR" -> opcode = 1;
                    case "XOR" -> opcode = 2;
                    default -> opcode = -1;
            }
            LogicWire wire = new LogicWire(parts[4], opcode, parts[0], parts[2]);
        }

        for(LogicWire wire : LogicWire.wires.values()) {
            System.out.println(wire.name + " " + wire.getValue());
        }

        //take wires key set, filter for starting with z, sort, map to string 1 or 0 representing value of that wire,
        //concatenate, use stringbuilder to reverse, convert to long
        long num =  Long.parseLong(new StringBuilder(LogicWire.wires.keySet().stream().filter(x -> x.charAt(0) == 'z').sorted().map(x -> (LogicWire.wires.get(x).getValue() ? "1" : "0")).collect(Collectors.joining())).reverse().toString(), 2);
        return Long.toString(num);
    }


    //TODO:: non-manual part 2 solution
    @Override
    public String part2() {
        return null;
    }
}
