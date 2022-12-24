package advent.aoc2022;

import advent.utilities.general.Coord;
import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Day23 implements IDay {

    static String input;

    final Coord[] DIAGONALS = new Coord[] {new Coord(-1,-1), new Coord(1,-1), new Coord(-1,1), new Coord(1,1)};

    @Override
    public String part1() {
        LinkedList<Coord> dirs = new LinkedList<>(Arrays.asList(Coord.UP, Coord.DOWN, Coord.LEFT, Coord.RIGHT));
        HashSet<Coord> elves = new HashSet<Coord>();
        String[] lines = input.split("\n");
        for(int y = 0; y < lines.length; y++) {
            String line = lines[y];
            for(int x = 0; x < line.length(); x++) {
                if(line.charAt(x) == '#')
                    elves.add(new Coord(x,y));
            }
        }
        for(int iter = 0; iter < 10; iter++) {
            //first half
            HashMap<Coord,Coord> proposals = new HashMap<>();
            for(Coord elf : elves) {
                //get list of adjacent positions, keep ones that contain elves
                ArrayList<Coord> adj = elf.allNeighbors();
                adj.retainAll(elves);
                //if no elves nearby, no movement
                if(adj.size() == 0)
                    continue;
                //try proposing each direction in order
                for(Coord c : dirs) {
                    ArrayList<Coord> checks = new ArrayList<Coord>();
                    checks.add(elf.sum(c));
                    checks.addAll(diags(c).stream().map(elf::sum).toList());
                    checks.retainAll(adj);
                    if(checks.size() == 0) {
                        proposals.put(elf,elf.sum(c));
                        break;
                    }
                }
            }
            //second half
            HashSet<Coord> newElves = new HashSet<>();
            //get frequency map of proposed locations
            HashMap<Coord,Long> propFreq = new HashMap<Coord,Long>(proposals.values().stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting())));
            for(Coord elf : elves) {
                //if elf has no proposed location or if its proposed location has a value of more than one in the frequency map, do not move
                if(propFreq.getOrDefault(proposals.getOrDefault(elf,null),2L) > 1L)
                    newElves.add(elf);
                else
                    newElves.add(proposals.get(elf));
            }
            elves = newElves;
            dirs.add(dirs.removeFirst());
        }

        int minX = elves.stream().map(x -> x.x).min(Integer::compare).get();
        int maxX = elves.stream().map(x -> x.x).max(Integer::compare).get();
        int minY = elves.stream().map(x -> x.y).min(Integer::compare).get();
        int maxY = elves.stream().map(x -> x.y).max(Integer::compare).get();

        return Integer.toString(((maxX - minX + 1) * (maxY - minY + 1)) - elves.size());
    }

    public ArrayList<Coord> diags(Coord c) {
        ArrayList<Coord> ret = new ArrayList<>();
        if(c.equals(Coord.UP)) {
            ret.add(DIAGONALS[0]);
            ret.add(DIAGONALS[1]);
            return ret;
        }
        if(c.equals(Coord.DOWN)) {
            ret.add(DIAGONALS[2]);
            ret.add(DIAGONALS[3]);
            return ret;
        }
        if(c.equals(Coord.LEFT)) {
            ret.add(DIAGONALS[0]);
            ret.add(DIAGONALS[2]);
            return ret;
        }
        if(c.equals(Coord.RIGHT)) {
            ret.add(DIAGONALS[1]);
            ret.add(DIAGONALS[3]);
            return ret;
        }
        return null;
    }

    @Override
    public String part2() {
        LinkedList<Coord> dirs = new LinkedList<>(Arrays.asList(Coord.UP, Coord.DOWN, Coord.LEFT, Coord.RIGHT));
        HashSet<Coord> elves = new HashSet<Coord>();
        String[] lines = input.split("\n");
        for(int y = 0; y < lines.length; y++) {
            String line = lines[y];
            for(int x = 0; x < line.length(); x++) {
                if(line.charAt(x) == '#')
                    elves.add(new Coord(x,y));
            }
        }

        int round = 0;
        while(true) {
            //first half
            HashMap<Coord,Coord> proposals = new HashMap<>();
            for(Coord elf : elves) {
                ArrayList<Coord> adj = elf.allNeighbors();
                adj.retainAll(elves);
                if(adj.size() == 0)
                    continue;
                for(Coord c : dirs) {
                    ArrayList<Coord> checks = new ArrayList<Coord>();
                    checks.add(elf.sum(c));
                    checks.addAll(diags(c).stream().map(elf::sum).toList());
                    checks.retainAll(adj);
                    if(checks.size() == 0) {
                        proposals.put(elf,elf.sum(c));
                        break;
                    }
                }
            }
            HashSet<Coord> newElves = new HashSet<>();
            HashMap<Coord,Long> propFreq = new HashMap<Coord,Long>(proposals.values().stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting())));
            for(Coord elf : elves) {
                if(propFreq.getOrDefault(proposals.getOrDefault(elf,null),2L) > 1L)
                    newElves.add(elf);
                else
                    newElves.add(proposals.get(elf));
            }
            if(elves.equals(newElves))
                break;
            elves = newElves;
            dirs.add(dirs.removeFirst());
            round++;
        }
        return Integer.toString(round + 1);
    }

    public static void main(String[] args) {
        input = Input.fetchInput(2022,23);
        DayRunner.run(new Day23());
    }
}
