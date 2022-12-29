package advent.aoc2022;

import advent.utilities.general.*;

import java.util.*;
import java.util.stream.Collectors;

public class Day17 implements IDay {

    static String input;

    @Override
    public String part1() {
        HashSet<Coord> rocksInCave = new HashSet<>();
        //put in floor
        for(int i = 0; i < 7; i++) {
            rocksInCave.add(new Coord(i,-1));
        }
        LinkedList<Boolean> jetPattern = new LinkedList<>();
        for(char c : input.trim().toCharArray())
            jetPattern.add(c == '>');

        ArrayList<HashSet<Coord>> rocks = new ArrayList<>();

        //for each rock, x=0 is the leftmost point and y=0 is the bottommost point

        //horizontal line
        HashSet<Coord> horizLine = new HashSet<>();
        for(int i = 0; i < 4; i++)
            horizLine.add(new Coord(i,0));

        rocks.add(horizLine);

        //plus sign
        HashSet<Coord> plus = new HashSet<>();
        plus.add(new Coord(0,1));
        plus.add(new Coord(1,1));
        plus.add(new Coord(2,1));
        plus.add(new Coord(1,2));
        plus.add(new Coord(1,0));
        rocks.add(plus);

        //backwards L
        HashSet<Coord> backL = new HashSet<>();
        backL.add(new Coord(0,0));
        backL.add(new Coord(1,0));
        backL.add(new Coord(2,0));
        backL.add(new Coord(2,1));
        backL.add(new Coord(2,2));
        rocks.add(backL);

        //vertical line
        HashSet<Coord> vertLine = new HashSet<>();
        for(int i = 0; i < 4; i++)
            vertLine.add(new Coord(0,i));
        rocks.add(vertLine);

        //square
        HashSet<Coord> square = new HashSet<>();
        square.add(new Coord(0,0));
        square.add(new Coord(0,1));
        square.add(new Coord(1,1));
        square.add(new Coord(1,0));
        rocks.add(square);

        int jetCounter = 0;
        for(int rockCount = 0; rockCount < 2022; rockCount++) {
            HashSet<Coord> curRock = new HashSet<>(rocks.get(rockCount % 5));

            int maxY = rocksInCave.stream().map(x -> x.y).max(Integer::compare).get();

            //offset rock to proper starting position
            curRock = (HashSet<Coord>) curRock.stream().map(x -> x.sum(new Coord(2, maxY + 4))).collect(Collectors.toSet());
            movement:
            while(true) {
                //try to push with jet
                if(jetPattern.get(jetCounter % jetPattern.size())) {
                    int highestX = curRock.stream().map(x -> x.x).max(Integer::compare).get();
                    HashSet<Coord> tentativeRight = (HashSet<Coord>) curRock.stream().map(x -> x.sum(Coord.RIGHT)).collect(Collectors.toSet());
                    if(highestX < 6 && !containsAny(rocksInCave,tentativeRight)) {
                        //push entire to right
                        curRock = tentativeRight;
                    }
                } else {
                    int lowestX = curRock.stream().map(x -> x.x).min(Integer::compare).get();
                    HashSet<Coord> tentativeLeft = (HashSet<Coord>) curRock.stream().map(x -> x.sum(Coord.LEFT)).collect(Collectors.toSet());
                    if(lowestX > 0 && !containsAny(rocksInCave,tentativeLeft))
                        curRock = tentativeLeft;
                }
                jetCounter++;

                //check if resting on something
                for(Coord c : curRock) {
                    //coord system is reversed for this problem - this checks one below
                    if(rocksInCave.contains(c.sum(Coord.UP))) {
                        //solidify rock and continue to next loop
                        rocksInCave.addAll(curRock);
                        break movement;
                    }
                }

                //if not resting on something, shift entire rock down
                curRock = (HashSet<Coord>) curRock.stream().map(x -> x.sum(Coord.UP)).collect(Collectors.toSet());

            }
        }

        return Integer.toString(rocksInCave.stream().map(x -> x.y).max(Integer::compare).get() + 1);
    }

    public boolean containsAny(HashSet<Coord> big, HashSet<Coord> small) {
        for(Coord c : small)
            if(big.contains(c))
                return true;
        return false;
    }

    @Override
    public String part2() {
        final long LENGTH = 1000000000000L;

        HashSet<Coord> rocksInCave = new HashSet<>();
        //put in floor
        for(int i = 0; i < 7; i++) {
            rocksInCave.add(new Coord(i,-1));
        }
        LinkedList<Boolean> jetPattern = new LinkedList<>();
        for(char c : input.trim().toCharArray())
            jetPattern.add(c == '>');

        ArrayList<HashSet<Coord>> rocks = new ArrayList<>();

        //for each rock, x=0 is the leftmost point and y=0 is the bottommost point

        //horizontal line
        HashSet<Coord> horizLine = new HashSet<>();
        for(int i = 0; i < 4; i++)
            horizLine.add(new Coord(i,0));

        rocks.add(horizLine);

        //plus sign
        HashSet<Coord> plus = new HashSet<>();
        plus.add(new Coord(0,1));
        plus.add(new Coord(1,1));
        plus.add(new Coord(2,1));
        plus.add(new Coord(1,2));
        plus.add(new Coord(1,0));
        rocks.add(plus);

        //backwards L
        HashSet<Coord> backL = new HashSet<>();
        backL.add(new Coord(0,0));
        backL.add(new Coord(1,0));
        backL.add(new Coord(2,0));
        backL.add(new Coord(2,1));
        backL.add(new Coord(2,2));
        rocks.add(backL);

        //vertical line
        HashSet<Coord> vertLine = new HashSet<>();
        for(int i = 0; i < 4; i++)
            vertLine.add(new Coord(0,i));
        rocks.add(vertLine);

        //square
        HashSet<Coord> square = new HashSet<>();
        square.add(new Coord(0,0));
        square.add(new Coord(0,1));
        square.add(new Coord(1,1));
        square.add(new Coord(1,0));
        rocks.add(square);

        int jetCounter = 0;

        //cache keeps track of seen states in the form of the top 30 rows of the state, mapped to the rock it occurred on and the max Y at the time
        HashMap<HashSet<Coord>,Coord> cache = new HashMap<>();

        boolean cycleFound = false;
        long heightFromCycleRepeat = 0;

        long rockCount = 0;
        while(rockCount < LENGTH) {
            HashSet<Coord> curRock = new HashSet<>(rocks.get((int) (rockCount % 5)));

            int maxY = rocksInCave.stream().map(x -> x.y).max(Integer::compare).get();

            //offset rock to proper starting position
            curRock = (HashSet<Coord>) curRock.stream().map(x -> x.sum(new Coord(2, maxY + 4))).collect(Collectors.toSet());

            movement:
            while(true) {
                //try to push with jet
                if(jetPattern.get(jetCounter % jetPattern.size())) {
                    int highestX = curRock.stream().map(x -> x.x).max(Integer::compare).get();
                    HashSet<Coord> tentativeRight = (HashSet<Coord>) curRock.stream().map(x -> x.sum(Coord.RIGHT)).collect(Collectors.toSet());
                    if(highestX < 6 && !containsAny(rocksInCave,tentativeRight)) {
                        //push entire to right
                        curRock = tentativeRight;
                    }
                } else {
                    int lowestX = curRock.stream().map(x -> x.x).min(Integer::compare).get();
                    HashSet<Coord> tentativeLeft = (HashSet<Coord>) curRock.stream().map(x -> x.sum(Coord.LEFT)).collect(Collectors.toSet());
                    if(lowestX > 0 && !containsAny(rocksInCave,tentativeLeft))
                        curRock = tentativeLeft;
                }
                jetCounter++;

                //check if resting on something
                for(Coord c : curRock) {
                    //coord system is reversed for this problem - this checks one below each coord in rock
                    if(rocksInCave.contains(c.sum(Coord.UP))) {
                        //solidify rock
                        rocksInCave.addAll(curRock);

                        int curHeight = rocksInCave.stream().map(x->x.y).max(Integer::compare).get();
                        //create cache key for current rock state
                        HashSet<Coord> cacheKey = convertToCacheKey(rocksInCave);
                        if(!cycleFound && cache.containsKey(cacheKey)) {
                            //get info about cycle
                            Coord info = cache.get(cacheKey);
                            int oldTime = info.x;
                            int oldHeight = info.y;
                            int cycleLength = (int) (rockCount - oldTime);
                            int cycleHeightChange = curHeight - oldHeight;
                            //calculate number of times we could cycle without going over LENGTH
                            long numCycles = (LENGTH - rockCount) / cycleLength;
                            //add total height that we would gain from repeating cycle, and add time that cycle repeat would take
                            heightFromCycleRepeat = cycleHeightChange * numCycles;
                            rockCount += numCycles * cycleLength;
                            //mark cycle found to avoid further repeating
                            cycleFound = true;
                        } else {
                            Coord info = new Coord((int) rockCount, curHeight);
                            cache.put(cacheKey,info);
                        }
                        break movement;
                    }
                }

                //if not resting on something, shift entire rock down
                curRock = (HashSet<Coord>) curRock.stream().map(x -> x.sum(Coord.UP)).collect(Collectors.toSet());
            }
            rockCount++;
        }

        //get the height we found manually in the loop, plus the length we calculated for the cycle repeat, plus 1 for starting at 0
        return Long.toString(rocksInCave.stream().map(x -> x.y).max(Integer::compare).get() + heightFromCycleRepeat + 1);
    }

    //converts the current rock state to a hashset of its top 30 rows, in terms of distance from the top row
    //this standardizes the rock state to a set that we can compare to other states to find matches
    public HashSet<Coord> convertToCacheKey(HashSet<Coord> rocks) {
        int maxY = rocks.stream().map(x -> x.y).max(Integer::compare).get();
        return (HashSet<Coord>) rocks.stream().filter(x -> maxY - x.y <= 30).map(x -> new Coord(x.x, maxY - x.y)).collect(Collectors.toSet());
    }

    public static void main(String[] args) {
        input = Input.fetchInput(2022,17);
        DayRunner.run(new Day17());
    }
}
