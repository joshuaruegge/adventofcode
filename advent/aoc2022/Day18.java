package advent.aoc2022;

import advent.utilities.general.Coord3;
import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;

public class Day18 implements IDay {

    static String input;

    @Override
    public String part1() {
        HashSet<Coord3> droplets = new HashSet<>();
        for(String s : input.split("\n")) {
            String[] nums = s.split(",");
            droplets.add(new Coord3(Integer.parseInt(nums[0]), Integer.parseInt(nums[1]), Integer.parseInt(nums[2])));
        }
        int surfaceArea = 0;
        for(Coord3 c : droplets) {
            //each coord starts with 6 visible faces, each face present in the array reduces by one
            int startingSurface = 6;
            for(Coord3 d : c.directNeighbors())
                if(droplets.contains(d))
                    startingSurface--;
            surfaceArea += startingSurface;
        }
        return Integer.toString(surfaceArea);
    }

    @Override
    public String part2() {
        HashSet<Coord3> droplets = new HashSet<>();
        for(String s : input.split("\n")) {
            String[] nums = s.split(",");
            droplets.add(new Coord3(Integer.parseInt(nums[0]), Integer.parseInt(nums[1]), Integer.parseInt(nums[2])));
        }

        //find the coord with the smallest x,y, and z
        //this is likely to have external air adjacent to it
        Coord3 minimum = droplets.stream().min(new Comparator<Coord3>() {
            @Override
            public int compare(Coord3 o1, Coord3 o2) {
                return Integer.compare(o1.x + o1.y + o1.z, o2.x + o2.y + o2.z);
            }
        }).get();

        //find first coordinate around the min x, y, and z that is air
        //hopefully, this will be external
        Coord3 firstAir = null;
        for(Coord3 d : minimum.directNeighbors()) {
            if(!droplets.contains(d)) {
                firstAir = d;
                break;
            }
        }

        //flood-fill (kinda?) from first air block?
        LinkedList<Coord3> queue = new LinkedList<Coord3>();
        queue.add(firstAir);
        HashSet<Coord3> airBlocks = new HashSet<Coord3>();
        while(queue.size() > 0) {
            Coord3 cur = queue.poll();
            airBlocks.add(cur);

            for(Coord3 neighbor : cur.directNeighbors()) {
                if(airBlocks.contains(neighbor) || droplets.contains(neighbor) || queue.contains(neighbor))
                    continue;
                //if shortest dist from any droplet is more than two, we're expanding out into infinity
                if(shortestDistToDroplet(droplets,neighbor) > 2)
                    continue;
                queue.add(neighbor);
            }
        }

        //tally surface area - one for each adjacent in droplets for all of our external air
        int surfaceArea = 0;
        for(Coord3 c : airBlocks) {
            for(Coord3 d : c.directNeighbors()) {
                if(droplets.contains(d)) {
                    surfaceArea++;
                }
            }
        }
        return Integer.toString(surfaceArea);
    }

    //calculate and return shortest distance to any droplet
    public int shortestDistToDroplet(HashSet<Coord3> droplets, Coord3 pos) {
        return droplets.stream().map(x -> x.dist(pos)).min(Integer::compare).get();
    }

    public static void main(String[] args) {
        input = Input.fetchInput(2022,18);
        DayRunner.run(new Day18());
    }
}
