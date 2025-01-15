package advent.aoc2024;

import advent.utilities.general.*;

import java.util.*;
import java.util.stream.Collectors;

public class Day14 implements IDay {

    static String input;

    static String testInput = "";

    public static void main(String[] args) {
        input = Input.fetchInput(2024, 14);
        DayRunner.run(new Day14());
    }

    @Override
    public String part1() {
        long total = 0;

        int dimX = 101;
        int dimY = 103;

        LinkedList<Pair<Coord, Coord>> bots = new LinkedList<>();

        for(String line : input.split("\n")) {
            String[] chunks = line.split("=|,| ");

            Coord pos = new Coord(Integer.parseInt(chunks[1]), Integer.parseInt(chunks[2]));

            Coord vel = new Coord(Integer.parseInt(chunks[4]), Integer.parseInt(chunks[5]));

            bots.add(new Pair<>(pos,vel));
        }

        int[] quads = new int[4];

        for(Pair<Coord,Coord> bot : bots) {
            Coord pos = bot.key;
            Coord vel = bot.value;

            int finalX = Math.floorMod(pos.x + vel.x * 100, dimX);
            int finalY = Math.floorMod(pos.y + vel.y * 100, dimY);

            if(finalX < dimX/2) {
                if(finalY < dimY/2) {
                    quads[0]++;
                } else if (finalY > dimY/2)
                    quads[1]++;
            } else if (finalX > dimX/2) {
                if(finalY < dimY/2) {
                    quads[2]++;
                } else if (finalY > dimY/2)
                    quads[3]++;
            }
        }

        return Integer.toString(quads[0]*quads[1]*quads[2]*quads[3]);
    }

    @Override
    public String part2() {

        int dimX = 101;
        int dimY = 103;

        LinkedList<Pair<Coord, Coord>> bots = new LinkedList<>();

        for(String line : input.split("\n")) {
            String[] chunks = line.split("=|,| ");

            Coord pos = new Coord(Integer.parseInt(chunks[1]), Integer.parseInt(chunks[2]));

            Coord vel = new Coord(Integer.parseInt(chunks[4]), Integer.parseInt(chunks[5]));

            bots.add(new Pair<>(pos,vel));
        }

        int seconds = 0;

        while(true) {
            HashSet<Coord> posList = new HashSet<>();
            LinkedList<Pair<Coord, Coord>> newBots = new LinkedList<>();

            for(Pair<Coord,Coord> bot : bots) {
                Coord pos = bot.key;
                Coord vel = bot.value;

                int finalX = Math.floorMod(pos.x + vel.x, dimX);
                int finalY = Math.floorMod(pos.y + vel.y, dimY);

                Coord newPos = new Coord(finalX, finalY);

                newBots.add(new Pair<>(newPos, vel));
                posList.add(newPos);
            }

            seconds++;

            bots = newBots;


            if(posList.size() == bots.size())
                break;

        }

        List<Coord> posList = bots.stream().map(k -> k.key).collect(Collectors.toList());

        for(int y = 0; y < dimY; y++) {
            for(int x = 0; x < dimX; x++) {
                System.out.print(posList.contains(new Coord(x,y)) ? '#' : '.');
            }
            System.out.println();
        }


        return Integer.toString(seconds);
    }
}
