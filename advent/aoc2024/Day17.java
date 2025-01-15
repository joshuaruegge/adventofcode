package advent.aoc2024;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Day17 implements IDay {

    static String input;

    static String testInput = "Register A: 2024\n" +
            "Register B: 0\n" +
            "Register C: 0\n" +
            "\n" +
            "Program: 0,3,5,4,3,0";

    public static void main(String[] args) {
        input = Input.fetchInput(2024, 17);
        DayRunner.run(new Day17());
    }

    @Override
    public String part1() {
        LinkedList<Long> out = new LinkedList<>();

        String[] chunks = input.split("\n\n");

        String[] varInit = chunks[0].split("\n");
        int a = Integer.parseInt(varInit[0].split(": ")[1]);
        int b = Integer.parseInt(varInit[1].split(": ")[1]);
        int c = Integer.parseInt(varInit[2].split(": ")[1]);

        List<Integer> program = Arrays.stream(chunks[1].split(": ")[1].trim().split(",")).map(Integer::parseInt).toList();
        /*
        int ip = 0;
        while(ip < program.size()) {
            int opcode = program.get(ip);
            int operand = program.get(ip+1);

            switch (opcode) {
                case 0 -> {
                    switch (operand) {
                        case 0, 1, 2, 3 -> a /= (int) Math.pow(2, operand);
                        case 4 -> a /= (int) Math.pow(2, a);
                        case 5 -> a /= (int) Math.pow(2, b);
                        case 6 -> a /= (int) Math.pow(2, c);
                    }
                }
                case 1 -> b ^= operand;
                case 2 -> b = switch (operand) {
                    case 0, 1, 2, 3 -> operand;
                    case 4 -> a % 8;
                    case 5 -> b % 8;
                    case 6 -> c % 8;
                    default -> b;
                };
                case 3 -> {
                    if (a != 0) {
                        ip = operand;
                        continue;
                    }
                }
                case 4 -> b ^= c;
                case 5 -> {
                    switch (operand) {
                        case 0, 1, 2, 3 -> out.add((long) (operand % 8));
                        case 4 -> out.add(a % 8);
                        case 5 -> out.add(b % 8);
                        case 6 -> out.add(c % 8);
                    }
                }
                case 6 -> {
                    switch (operand) {
                        case 0, 1, 2, 3 -> b = a / (int) Math.pow(2, operand);
                        case 4 -> b = a / (int) Math.pow(2, a);
                        case 5 -> b = a / (int) Math.pow(2, b);
                        case 6 -> b = a / (int) Math.pow(2, c);
                    }
                }
                case 7 -> {
                    switch (operand) {
                        case 0, 1, 2, 3 -> c /= (int) Math.pow(2, operand);
                        case 4 -> c = a / (int) Math.pow(2, a);
                        case 5 -> c = a / (int) Math.pow(2, b);
                        case 6 -> c = a / (int) Math.pow(2, c);
                    }
                }
            }

            ip += 2;
        }
        */
        return String.join(",", eval(a,b,c,program).stream().map(Object::toString).toList());
    }

    public LinkedList<Integer> eval(long a, long b, long c, List<Integer> program) {
        LinkedList<Integer> out = new LinkedList<>();

        int ip = 0;
        while(ip < program.size()) {
            int opcode = program.get(ip);
            int operand = program.get(ip+1);

            switch (opcode) {
                case 0 -> {
                    /*
                    switch (operand) {
                        case 0, 1, 2, 3 -> a /= (int) Math.pow(2, operand);
                        case 4 -> a /= (int) Math.pow(2, a);
                        case 5 -> a /= (int) Math.pow(2, b);
                        case 6 -> a /= (int) Math.pow(2, c);
                    }*

                     */
                    a >>= combo(a,b,c,operand);
                }
                case 1 -> b ^= operand;
                case 2 -> b = combo(a,b,c,operand) % 8;
                case 3 -> {
                    if (a != 0) {
                        ip = operand;
                        continue;
                    }
                }
                case 4 -> b ^= c;
                case 5 -> {
                    /*
                    switch (operand) {
                        case 0, 1, 2, 3 -> out.add(operand % 8);
                        case 4 -> out.add((int) a % 8);
                        case 5 -> out.add((int) b % 8);
                        case 6 -> out.add((int) c % 8);
                    }*

                     */
                    out.add((int) (combo(a,b,c,operand) % 8));
                }
                case 6 -> {
                    /*
                    switch (operand) {
                        case 0, 1, 2, 3 -> b = a / (int) Math.pow(2, operand);
                        case 4 -> b = a / (int) Math.pow(2, a);
                        case 5 -> b = a / (int) Math.pow(2, b);
                        case 6 -> b = a / (int) Math.pow(2, c);
                    }

                     */
                    b = a >> combo(a,b,c,operand);
                }
                case 7 -> {
                    /*
                    switch (operand) {
                        case 0, 1, 2, 3 -> c /= (int) Math.pow(2, operand);
                        case 4 -> c = a / (int) Math.pow(2, a);
                        case 5 -> c = a / (int) Math.pow(2, b);
                        case 6 -> c = a / (int) Math.pow(2, c);
                    }

                     */
                    c = a >> combo(a,b,c,operand);
                }
            }

            ip += 2;
        }

        return out;
    }

    public long combo(long a, long b, long c, long operand) {
        if(operand == 4)
            return a;
        else if(operand == 5)
            return b;
        else if(operand == 6)
            return c;
        else
            return operand;
    }

    @Override
    public String part2() {
        String[] chunks = input.split("\n\n");

        List<Integer> program = Arrays.stream(chunks[1].split(": ")[1].trim().split(",")).map(Integer::parseInt).toList();

        return Long.toString(dfs(program, program.size() - 1, 0));
    }

    public long dfs(List<Integer> program, int result_index, long curr_a) {
        System.out.println(result_index + " " + curr_a);
        if(result_index == -1)
            return curr_a;

        curr_a <<= 3;
        for(int newLowest3 = 0; newLowest3 < 8; newLowest3++) {
            long pot_a = curr_a + newLowest3;
            LinkedList<Integer> out = eval(pot_a, 0, 0, program);
            System.out.println(out);
            System.out.println(program);
            if(out.get(0) == (program.get(result_index))) {
                long deeperResult = dfs(program, result_index - 1, pot_a);
                if(deeperResult != -1)
                    return deeperResult;
            }
        }

        return -1;
    }
}
