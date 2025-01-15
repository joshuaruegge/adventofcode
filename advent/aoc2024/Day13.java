package advent.aoc2024;

import advent.utilities.general.Coord;
import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

public class Day13 implements IDay {

    static String input;

    public static void main(String[] args) {
        input = Input.fetchInput(2024, 13);
        DayRunner.run(new Day13());
    }

    @Override
    public String part1() {
        long total = 0;

        String[] chunks = input.split("\n\n");
        
        for(String machine : chunks) {
            String[] lines = machine.split("\n");

            Coord[] coords = new Coord[2];
            for(int i = 0; i < 2; i++) {

                String[] parts = lines[i].split(": |, ");

                coords[i] = new Coord(Integer.parseInt(parts[1].substring(2)), Integer.parseInt(parts[2].substring(2)));
            }

            long targX = Long.parseLong(lines[2].split(": |, ")[1].substring(2));
            long targY = Long.parseLong(lines[2].split(": |, ")[2].substring(2));

            long denom = (coords[0].x * coords[1].y - coords[0].y * coords[1].x);
            if(denom == 0)
                continue;

            long potA = (coords[1].y * targX - coords[1].x * targY) / denom;
            long potB = (targX - potA * coords[0].x) / coords[1].x;

            if (potA * coords[0].x + potB * coords[1].x == targX && potA * coords[0].y + potB * coords[1].y == targY)
                total += potA * 3 + potB;
        }

        return Long.toString(total);
    }

    @Override
    public String part2() {
        long total = 0;

        String[] chunks = input.split("\n\n");

        long tenTril = 10000000000000L;

        for(String machine : chunks) {
            String[] lines = machine.split("\n");

            Coord[] coords = new Coord[2];
            for(int i = 0; i < 2; i++) {

                String[] parts = lines[i].split(": |, ");

                coords[i] = new Coord(Integer.parseInt(parts[1].substring(2)), Integer.parseInt(parts[2].substring(2)));
            }

            long targX = Long.parseLong(lines[2].split(": |, ")[1].substring(2)) + tenTril;
            long targY = Long.parseLong(lines[2].split(": |, ")[2].substring(2)) + tenTril;


            long denom = (coords[0].x * coords[1].y - coords[0].y * coords[1].x);
            if(denom == 0)
                continue;
            long potA = (coords[1].y * targX - coords[1].x * targY) / denom;
            long potB = (targX - potA * coords[0].x) / coords[1].x;

            if (potA * coords[0].x + potB * coords[1].x == targX && potA * coords[0].y + potB * coords[1].y == targY)
                total += potA * 3 + potB;
        }

        return Long.toString(total);
    }
}
