package advent.aoc2024;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

public class Day03 implements IDay {

    static String input;

    public static void main(String[] args) {
        input = Input.fetchInput(2024, 3);
        DayRunner.run(new Day03());
    }

    @Override
    public String part1() {
        int result = 0;

        int index = 0;
        while(true) {
            index = input.indexOf("mul(",index);
            if(index > input.length() || index == -1)
                break;
            index += 4;
            String num1 = "";
            while(Character.isDigit(input.charAt(index))) {
                num1 += input.charAt(index++);
            }
            if(input.charAt(index++) != ',')
                continue;
            String num2 = "";
            while(Character.isDigit(input.charAt(index))) {
                num2 += input.charAt(index++);
            }
            if(input.charAt(index++) != ')')
                continue;
            if(num1.equals("") || num2.equals(""))
                continue;

            result += Integer.parseInt(num1) * Integer.parseInt(num2);
        }

        return Integer.toString(result);
    }

    @Override
    public String part2() {
        int result = 0;

        int index = 0;
        while(index != -1) {
            if(input.indexOf("don't()",index) != -1 && input.indexOf("don't()",index) < input.indexOf("mul(",index)) {
                index = input.indexOf("do()", index);
                continue;
            }
            index = input.indexOf("mul(",index);
            if(index > input.length() || index == -1)
                break;

            index += 4;

            String num1 = "";
            while(Character.isDigit(input.charAt(index))) {
                num1 += input.charAt(index++);
            }
            if(input.charAt(index++) != ',')
                continue;
            String num2 = "";
            while(Character.isDigit(input.charAt(index))) {
                num2 += input.charAt(index++);
            }
            if(input.charAt(index++) != ')')
                continue;
            if(num1.equals("") || num2.equals(""))
                continue;

            result += Integer.parseInt(num1) * Integer.parseInt(num2);
        }

        return Integer.toString(result);
    }
}
