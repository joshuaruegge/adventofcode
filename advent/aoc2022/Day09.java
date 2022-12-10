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
        HashSet<Coord> tailLocations = new HashSet<Coord>();
        Coord headPos = new Coord(0,0);
        Coord tailPos = new Coord(0,0);
        for(String s : input.split("\n")) {
            String[] parts = s.split(" ");
            int count = Integer.parseInt(parts[1]);
            Coord dir = null;
            switch(parts[0]) {
                case "U":
                    dir = Coord.UP;
                    break;
                case "D":
                    dir = Coord.DOWN;
                    break;
                case "L":
                    dir = Coord.LEFT;
                    break;
                case "R":
                    dir = Coord.RIGHT;
                    break;
            }
            for(int i = 0; i < count; i++) {
                //push coord, then update tail location if necessary
                headPos.sumSelf(dir);
                tailPos = moveTail(headPos, tailPos);
                tailLocations.add(tailPos.copy());
            }
        }
        return Integer.toString(tailLocations.size());
    }

    //returns new position of tail
    public Coord moveTail(Coord head, Coord tail) {
        //do nothing if tail is already close enough
        if(head.dist(tail) == 0 || tail.allNeighbors().contains(head)) {
            return tail;
        }
        //if tail is in same row/column but 2 away
        if((head.x == tail.x || head.y == tail.y) && head.dist(tail) > 1) {
            //push tail one in direction of head
            if(head.x != tail.x) {
                tail.x += Math.signum(head.x - tail.x);
            } else {
                tail.y += Math.signum(head.y - tail.y);
            }
            return tail;
        }
        //if tail is in both different row and column
        if(head.x != tail.x && head.y != tail.y) {
            //get list of all tail's neighbors, including diagonal
            ArrayList<Coord> moves = tail.allNeighbors();
            //filter to only keep coords that are also directly (not diagonally) adjacent to head
            moves.retainAll(head.directNeighbors());
            //update tail to (only?) move
            return moves.get(0);
        }

        return null;
    }

    @Override
    public String part2() {
        //now, store multiple tails in arraylist
        ArrayList<Coord> tails = new ArrayList<Coord>();
        Coord headPos = new Coord(0,0);
        for(int i = 0; i < 9; i++)
            tails.add(new Coord(0,0));
        HashSet<Coord> tailLocations = new HashSet<Coord>();
        for(String s : input.split("\n")) {
            String[] parts = s.split(" ");
            int count = Integer.parseInt(parts[1]);
            Coord dir = null;
            switch(parts[0]) {
                case "U":
                    dir = Coord.UP;
                    break;
                case "D":
                    dir = Coord.DOWN;
                    break;
                case "L":
                    dir = Coord.LEFT;
                    break;
                case "R":
                    dir = Coord.RIGHT;
                    break;
            }
            for(int i = 0; i < count; i++) {
                //push head, then move each tail incrementally
                headPos.sumSelf(dir);
                for(int j = 0; j < 9; j++)
                    //if not considering first tail, head is tail before
                    //if we are considering first tail, head is actual head
                    tails.set(j,moveTails((j > 0 ? tails.get(j-1) : headPos),tails.get(j)));
                tailLocations.add(tails.get(8).copy());
            }
        }
        return Integer.toString(tailLocations.size());
    }

    //same as above, but moves accounting for multiple tails
    public Coord moveTails(Coord head, Coord tail) {
        if(head.dist(tail) == 0 || tail.allNeighbors().contains(head)) {
            return tail;
        }
        //same row/col - same as above
        if((head.x == tail.x || head.y == tail.y) && head.dist(tail) > 1) {
            if(head.x != tail.x) {
                tail.x += Math.signum(head.x - tail.x);
            } else {
                tail.y += Math.signum(head.y - tail.y);
            }
            return tail;
        }
        //different row/col
        if(head.x != tail.x && head.y != tail.y) {
            ArrayList<Coord> moves = tail.allNeighbors();
            //this time, keep all possible moves - sometimes tails are only diagonally adjacent
            moves.retainAll(head.allNeighbors());
            //to avoid making incorrect move, sort to minimize distance from head
            //prioritizes being directly adjacent to being diagonal
            moves.sort(new Comparator<Coord>() {
                @Override
                public int compare(Coord o1, Coord o2) {
                    return Integer.compare(o1.dist(head),o2.dist(head));
                }
            });
            //make best possible move
            return moves.get(0);
        }
        return null;

    }

    public static void main(String[] args) {
        input = Input.fetchInput(2022,9);
        DayRunner.run(new Day09());
    }
}
