package advent.aoc2023;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

public class Day02 implements IDay {

    static String input;

    public static void main(String[] args) {
        input = Input.fetchInput(2023, 2);
        DayRunner.run(new Day02());
    }

    @Override
    public String part1() {

        int idTot = 0;
        for (String game : input.split("\n")) {
            String[] chunks = game.split(": |; ");
            int id = Integer.parseInt(chunks[0].split(" ")[1]);

            int red = 0;
            int blue = 0;
            int green = 0;
            for (int round = 1; round < chunks.length; round++) {
                for (String s : chunks[round].split(", ")) {
                    int num = Integer.parseInt(s.split(" ")[0]);
                    if (s.contains("red")) {
                        red = Math.max(red, num);
                    } else if (s.contains("green")) {
                        green = Math.max(green, num);
                    } else {
                        blue = Math.max(blue, num);
                    }
                }

            }
            if (red < 13 && green < 14 && blue < 15) {
                idTot += id;
            }
        }
        return Integer.toString(idTot);
    }

    @Override
    public String part2() {

        int pow = 0;
        for (String game : input.split("\n")) {
            String[] chunks = game.split(": |; ");

            int red = 0;
            int blue = 0;
            int green = 0;
            for (int round = 1; round < chunks.length; round++) {
                for (String s : chunks[round].split(", ")) {
                    int num = Integer.parseInt(s.split(" ")[0]);
                    if (s.contains("red")) {
                        red = Math.max(red, num);
                    } else if (s.contains("green")) {
                        green = Math.max(green, num);
                    } else {
                        blue = Math.max(blue, num);
                    }
                }

            }
            pow += (red * blue * green);
        }
        return Integer.toString(pow);
    }
}
