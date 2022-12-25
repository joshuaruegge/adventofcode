package advent.aoc2022;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

public class Day25 implements IDay {

    static String input;

    @Override
    public String part1() {
        long sum = 0;
        for(String s : input.split("\n")) {
            String number = new StringBuilder(s).reverse().toString();
            long num = 0;
            for(int i = 0; i < number.length(); i++) {
                double place = Math.pow(5,i);
                switch (number.charAt(i)) {
                    case '=' -> num += -2L * place;
                    case '-' -> num += -1L * place;
                    case '1' -> num += place;
                    case '2' -> num += 2L * place;
                }
            }
            sum += num;
        }
        return numBuilder(sum);
    }

    public String numBuilder(long num) {
        if(num == 0L)
            return "";
        return switch ((int) (num % 5)) {
            case 0 -> numBuilder(num / 5L) + "0";
            case 1 -> numBuilder(num / 5L) + "1";
            case 2 -> numBuilder(num / 5L) + "2";
            case 3 -> numBuilder((num + 2) / 5L) + "=";
            case 4 -> numBuilder((num + 1) / 5L) + "-";
            default -> null;
        };
    }

    @Override
    public String part2() {
        return "Merry Christmas!";
    }

    public static void main(String[] args) {
        input = Input.fetchInput(2022,25);
        DayRunner.run(new Day25());
    }
}
