package advent.aoc2022;

import advent.utilities.general.Coord;
import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;

public class Day09 implements IDay {

    static String input;

    @Override
    public String part1() {
        HashSet<Coord> tailLocations = new HashSet<>();
        Coord headPos = new Coord(0,0);
        Coord tailPos = new Coord(0,0);
        for(String s : input.split("\n")) {
            String[] parts = s.split(" ");
            int count = Integer.parseInt(parts[1]);
            Coord dir = switch (parts[0]) {
                case "U" -> Coord.UP;
                case "D" -> Coord.DOWN;
                case "L" -> Coord.LEFT;
                case "R" -> Coord.RIGHT;
                default -> null;
            };
            for(int i = 0; i < count; i++) {
                //push coord, then update tail location if necessary
                headPos.sumSelf(dir);
                tailPos = moveTail(headPos, tailPos);
                tailLocations.add(tailPos.copy());
            }
        }
        return Integer.toString(tailLocations.size());
    }

    @Override
    public String part2() {
        //now, store multiple tails in arraylist
        ArrayList<Coord> tails = new ArrayList<>();
        Coord headPos = new Coord(0,0);
        for(int i = 0; i < 9; i++)
            tails.add(new Coord(0,0));
        HashSet<Coord> tailLocations = new HashSet<>();
        for(String s : input.split("\n")) {
            String[] parts = s.split(" ");
            int count = Integer.parseInt(parts[1]);
            Coord dir = switch (parts[0]) {
                case "U" -> Coord.UP;
                case "D" -> Coord.DOWN;
                case "L" -> Coord.LEFT;
                case "R" -> Coord.RIGHT;
                default -> null;
            };
            for(int i = 0; i < count; i++) {
                //push head, then move each tail incrementally
                headPos.sumSelf(dir);
                for(int j = 0; j < 9; j++)
                    //if not considering first tail, head is tail before
                    //if we are considering first tail, head is actual head
                    tails.set(j, moveTail((j > 0 ? tails.get(j-1) : headPos),tails.get(j)));
                tailLocations.add(tails.get(8).copy());
            }
        }
        return Integer.toString(tailLocations.size());
    }

    //returns new position of tail (or same, if unchanged)
    public Coord moveTail(Coord head, Coord tail) {
        //if already close enough, don't move tail
        if(head.dist(tail) == 0 || tail.allNeighbors().contains(head)) {
            return tail;
        }

        //get squares adjacent to tail
        ArrayList<Coord> moves = tail.allNeighbors();
        //filter for spaces that are also adjacent to head
        moves.retainAll(head.allNeighbors());
        //to avoid making incorrect move, sort to minimize distance from head
        //prioritizes being directly adjacent to being diagonal (prioritize smallest distance)
        moves.sort(Comparator.comparingInt(o -> o.dist(head)));
        //make the best possible move
        return moves.get(0);
    }

    public static void main(String[] args) {
        input = Input.fetchInput(2022,9);
        DayRunner.run(new Day09());
    }
}
