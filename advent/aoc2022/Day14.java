package advent.aoc2022;

import advent.utilities.general.Coord;
import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

import java.util.HashSet;

public class Day14 implements IDay {

    static String input;

    @Override
    public String part1() {
        //keep track of solid objects - starts with walls, sand is added as it comes to rest
        HashSet<Coord> solids = new HashSet<Coord>();
        for(String s : input.split("\n")) {
            String[] words = s.split(" -> ");
            for(int i = 1; i < words.length; i++) {
                String[] coord1 = words[i-1].split(",");
                String[] coord2 = words[i].split(",");

                int x1 = Integer.parseInt(coord1[0]);
                int x2 = Integer.parseInt(coord2[0]);
                int y1 = Integer.parseInt(coord1[1]);
                int y2 = Integer.parseInt(coord2[1]);

                //swap to ensure correct lower -> higher order for below for loops
                if(x1 > x2) {
                    int temp = x1;
                    x1 = x2;
                    x2 = temp;
                }

                if(y1 > y2) {
                    int temp = y1;
                    y1 = y2;
                    y2 = temp;
                }

                if(x1 == x2) {
                    for(int y = y1; y <= y2; y++)
                        solids.add(new Coord(x1,y));
                } else if(y1 == y2) {
                    for(int x = x1; x <= x2; x++)
                        solids.add(new Coord(x,y1));
                }
            }
        }

        int lowestY = solids.stream().map(x -> x.y).max(Integer::compare).get();

        final Coord downLeft = new Coord(-1,1);
        final Coord downRight = new Coord(1,1);

        Coord source = new Coord(500,0);
        Coord sandPosition = source.copy();

        int particleCount = 0;
        do {
            //if can move down, move down
            if(!solids.contains(sandPosition.sum(Coord.DOWN)))
                 sandPosition.sumSelf(Coord.DOWN);
            //if can move down and left, move down and left
            else if(!solids.contains(sandPosition.sum(downLeft)))
                sandPosition.sumSelf(downLeft);
            //if can move down and right, move down and right
            else if(!solids.contains(sandPosition.sum(downRight)))
                sandPosition.sumSelf(downRight);
            else {
                //at rest - add to solids for collision
                solids.add(sandPosition.copy());
                sandPosition = source.copy();
                particleCount++;
            }
        } while (sandPosition.y < lowestY);
        //if sand position went below (above) lowest Y, then will never rest - so break and return number of grains so far
        return Integer.toString(particleCount);
    }

    @Override
    public String part2() {
        //keep track of solid objects - starts with walls, sand is added as it comes to rest
        HashSet<Coord> solids = new HashSet<Coord>();
        for(String s : input.split("\n")) {
            String[] words = s.split(" -> ");
            for(int i = 1; i < words.length; i++) {
                String[] coord1 = words[i-1].split(",");
                String[] coord2 = words[i].split(",");

                int x1 = Integer.parseInt(coord1[0]);
                int x2 = Integer.parseInt(coord2[0]);
                int y1 = Integer.parseInt(coord1[1]);
                int y2 = Integer.parseInt(coord2[1]);

                //swap to ensure correct lower -> higher order for below for loops
                if(x1 > x2) {
                    int temp = x1;
                    x1 = x2;
                    x2 = temp;
                }

                if(y1 > y2) {
                    int temp = y1;
                    y1 = y2;
                    y2 = temp;
                }

                if(x1 == x2) {
                    for(int y = y1; y <= y2; y++)
                        solids.add(new Coord(x1,y));
                } else if(y1 == y2) {
                    for(int x = x1; x <= x2; x++)
                        solids.add(new Coord(x,y1));
                }
            }
        }

        int lowestY = solids.stream().map(x -> x.y).max(Integer::compare).get();

        //add "infinite" (as far as we need) floor
        //because sand triangles up, we can assume that the x side of the triangle will be at most lowestY
        //but because it isnt an exact triangle, we double that for wiggle room
        for(int x = 500 - (2 * lowestY); x <= 500 + (2 * lowestY); x++) {
            solids.add(new Coord(x,lowestY + 2));
        }

        final Coord downLeft = new Coord(-1,1);
        final Coord downRight = new Coord(1,1);

        Coord source = new Coord(500,0);
        Coord sandPosition = source.copy();

        int particleCount = 0;
        do {
            //if can move down, move down
            if(!solids.contains(sandPosition.sum(Coord.DOWN)))
                sandPosition.sumSelf(Coord.DOWN);
            //if can move down and left, move down and left
            else if(!solids.contains(sandPosition.sum(downLeft)))
                sandPosition.sumSelf(downLeft);
            //if can move down and right, move down and right
            else if(!solids.contains(sandPosition.sum(downRight)))
                sandPosition.sumSelf(downRight);
            else {
                //at rest - add to solids for collision
                solids.add(sandPosition.copy());
                sandPosition = source.copy();
                particleCount++;
            }
        } while (!solids.contains(source));
        return Integer.toString(particleCount);
    }

    public static void main(String[] args) {
        input = Input.fetchInput(2022,14);
        DayRunner.run(new Day14());
    }
}
