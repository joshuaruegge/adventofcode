package advent.aoc2022;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;
import advent.utilities.utils2022.Valve;

import java.util.*;

public class Day16 implements IDay {

    static String input;

    @Override
    public String part1() {
        //parse input into Valve objects. valve object has its name, flow rate, and connections
        ArrayList<Valve> valves = new ArrayList<Valve>();
        for(String s : input.split("\n")) {
            String[] words = s.split(", |; | ");
            Valve v = new Valve();
            v.name = words[1];
            v.flow = Integer.parseInt(words[4].split("=")[1]);
            v.connections.addAll(Arrays.asList(words).subList(9, words.length));
            valves.add(v);
        }

        //build map of paths: each string key links to a map that contains the distance to each other path
        HashMap<String,HashMap<String,Integer>> paths = new HashMap<String,HashMap<String,Integer>>();
        for(Valve v : valves) {
            LinkedList<Valve> queue = new LinkedList<Valve>();
            queue.add(v);
            HashMap<String,Integer> dists = new HashMap<String,Integer>();
            dists.put(v.name,0);
            HashSet<String> seen = new HashSet<String>();
            seen.add(v.name);

            while(queue.size() > 0) {
                Valve cur = queue.poll();
                int distFrom = dists.get(cur.name);

                for(String connection : cur.connections) {
                    if(!seen.contains(connection)) {
                        seen.add(connection);
                        dists.put(connection, distFrom + 1);
                        queue.add(valves.stream().filter(x -> x.name.equals(connection)).findFirst().get());
                    }
                }
            }

            paths.put(v.name,dists);
        }

        ArrayList<Valve> nonzeroFlow = new ArrayList<Valve>(valves.stream().filter(x -> x.flow > 0).toList());

        final int BITSET_MAX = 1 << nonzeroFlow.size();

        //there's a reason i dont like magical dp array solutions to problems. its cause i'm not good at them
        //dp is a three-dimensional int array representing:
        //dp[a][b][c] = max pressure released at minute a, currently at valve b, with valves c opened
        //we convert valves opened to an integer representing a bitset in order to allow it to be used as an array index
        int[][][] dp = new int[31][nonzeroFlow.size()][BITSET_MAX];

        //unfortunately, java doesn't support specifying a default value, so we have to fill manually
        for(int[][] square : dp)
            for(int[] row : square)
                Arrays.fill(row,Integer.MIN_VALUE);

        //now, populate our starting values:
        //for each node in nonzero, we set the earliest minute it can be traveled to and opened (based on distance) to zero
        for(int i = 0; i < nonzeroFlow.size(); i++) {
            int distFromStart = paths.get("AA").get(nonzeroFlow.get(i).name);
            //1 << i produces a binary number with only digit i "on" or 1
            //this represents the bitset for just that valve being open
            dp[distFromStart + 1][i][1 << i] = 0;
        }

        int bestPressure = 0;
        for(int minute = 1; minute < 31; minute++) {
            //iterate over potential current locations
            for(int curPos = 0; curPos < nonzeroFlow.size(); curPos++) {
                //iterate over possible open bitsets
                for(int bitset = 0; bitset < BITSET_MAX; bitset++) {

                    //calculate flow that occurred for this condition since last minute
                    int potentialFlow = getFlowOfBitmask(nonzeroFlow,bitset);

                    int newPressure = dp[minute-1][curPos][bitset] + potentialFlow;
                    //if newPressure is better than current max for these conditions, update
                    if(newPressure > dp[minute][curPos][bitset]) {
                        dp[minute][curPos][bitset] = newPressure;
                    }

                    bestPressure = Math.max(bestPressure,newPressure);

                    //if current valve is unopened, we don't want to move
                    if(((1 << curPos) & bitset) == 0) {
                        continue;
                    }

                    //iterate over potential destinations to move to and/or open
                    //if we are at an unopened valve, this will just open it
                    for(int other = 0; other < nonzeroFlow.size(); other++) {
                        //ignore if already open in bitset
                        if(((1 << other)& bitset) != 0)
                            continue;

                        //find travel distance to next valve
                        int distTo = paths.get(nonzeroFlow.get(curPos).name).get(nonzeroFlow.get(other).name);

                        //ignore if it would take too long to travel to and open
                        if(minute + distTo + 1 > 30)
                            continue;

                        //calculate flow that would take place while we are traveling to and opening next valve
                        int travelPressure =  dp[minute][curPos][bitset] + potentialFlow * (distTo + 1);

                        //use | operation to insert bit of other valve being opened into bitmask
                        int newBitset = bitset | (1 << other);

                        //if travel pressure is better than best pressure for the condition at end of travel, update array
                        if(travelPressure > dp[minute + distTo + 1][other][newBitset]) {
                            dp[minute + distTo + 1][other][newBitset] = travelPressure;
                        }
                    }
                }
            }
        }
        return Integer.toString(bestPressure);
    }

    //calculates total flow per minute of the valves listed as open by the bitmask
    public int getFlowOfBitmask(ArrayList<Valve> nonzero, int bitmask) {
        int flow = 0;
        for(int i = 0; i < nonzero.size(); i++) {
            //1 << i & bitmask will return zero if the bit at i is 0
            //and nonzero if it is 1
            //so, if the bit is on (valve is open) add its flow to the total flow
            if(((1 << i) & bitmask) != 0) {
                flow += nonzero.get(i).flow;
            }
        }
        return flow;
    }

    @Override
    public String part2() {
        //parse input into Valve objects. valve object has its name, flow rate, and connections
        ArrayList<Valve> valves = new ArrayList<Valve>();
        for(String s : input.split("\n")) {
            String[] words = s.split(", |; | ");
            Valve v = new Valve();
            v.name = words[1];
            v.flow = Integer.parseInt(words[4].split("=")[1]);
            v.connections.addAll(Arrays.asList(words).subList(9, words.length));
            valves.add(v);
        }

        //build map of paths: each string key links to a map that contains the distance to each other path
        HashMap<String,HashMap<String,Integer>> paths = new HashMap<String,HashMap<String,Integer>>();
        for(Valve v : valves) {
            LinkedList<Valve> queue = new LinkedList<Valve>();
            queue.add(v);
            HashMap<String,Integer> dists = new HashMap<String,Integer>();
            dists.put(v.name,0);
            HashSet<String> seen = new HashSet<String>();
            seen.add(v.name);

            while(queue.size() > 0) {
                Valve cur = queue.poll();
                int distFrom = dists.get(cur.name);

                for(String connection : cur.connections) {
                    if(!seen.contains(connection)) {
                        seen.add(connection);
                        dists.put(connection, distFrom + 1);
                        queue.add(valves.stream().filter(x -> x.name.equals(connection)).findFirst().get());
                    }
                }
            }

            paths.put(v.name,dists);
        }

        ArrayList<Valve> nonzeroFlow = new ArrayList<Valve>(valves.stream().filter(x -> x.flow > 0).toList());

        final int BITSET_MAX = 1 << nonzeroFlow.size();

        //there's a reason i dont like magical dp array solutions to problems. its cause i'm not good at them
        //dp is a three-dimensional int array representing:
        //dp[a][b][c] = max pressure released at minute a, currently at valve b, with valves c opened
        //we convert valves opened to an integer representing a bitset in order to allow it to be used as an array index
        int[][][] dp = new int[31][nonzeroFlow.size()][BITSET_MAX];

        //unfortunately, java doesn't support specifying a default value, so we have to fill manually
        for(int[][] square : dp)
            for(int[] row : square)
                Arrays.fill(row,Integer.MIN_VALUE);

        //now, populate our starting values:
        //for each node in nonzero, we set the earliest minute it can be traveled to and opened (based on distance) to zero
        for(int i = 0; i < nonzeroFlow.size(); i++) {
            int distFromStart = paths.get("AA").get(nonzeroFlow.get(i).name);
            //1 << i produces a binary number with only digit i "on" or 1
            //this represents the bitset for just that valve being open
            dp[distFromStart + 1][i][1 << i] = 0;
        }

        //for part 2, we still need to populate the dp array
        for(int minute = 1; minute < 27; minute++) {
            //iterate over potential current locations
            for(int curPos = 0; curPos < nonzeroFlow.size(); curPos++) {
                //iterate over possible open bitsets
                for(int bitset = 0; bitset < BITSET_MAX; bitset++) {

                    //calculate flow that occurred for this condition since last minute
                    int potentialFlow = getFlowOfBitmask(nonzeroFlow,bitset);

                    int newPressure = dp[minute-1][curPos][bitset] + potentialFlow;
                    //if newPressure is better than current max for these conditions, update
                    if(newPressure > dp[minute][curPos][bitset]) {
                        dp[minute][curPos][bitset] = newPressure;
                    }

                    //if current valve is unopened, we don't want to move
                    if(((1 << curPos) & bitset) == 0) {
                        continue;
                    }

                    //iterate over potential destinations to move to and/or open
                    //if we are at an unopened valve, this will just open it
                    for(int other = 0; other < nonzeroFlow.size(); other++) {
                        //ignore if already open in bitset
                        if(((1 << other)& bitset) != 0)
                            continue;

                        //find travel distance to next valve
                        int distTo = paths.get(nonzeroFlow.get(curPos).name).get(nonzeroFlow.get(other).name);

                        //ignore if it would take too long to travel to and open
                        if(minute + distTo + 1 > 26)
                            continue;

                        //calculate flow that would take place while we are traveling to and opening next valve
                        int travelPressure =  dp[minute][curPos][bitset] + potentialFlow * (distTo + 1);

                        //use | operation to insert bit of other valve being opened into bitmask
                        int newBitset = bitset | (1 << other);

                        //if travel pressure is better than best pressure for the condition at end of travel, update array
                        if(travelPressure > dp[minute + distTo + 1][other][newBitset]) {
                            dp[minute + distTo + 1][other][newBitset] = travelPressure;
                        }
                    }
                }
            }
        }

        //for part 2, we can assume that the best solution has us and the elephant traversing two distinct (disjoint) "sets" of valves
        //because repeating valves would be inefficient and not produce the best pressure
        //so, examine all possible sets of our valves (in bitmask form) and find the maximum combined pressure released
        int bestPressure = 0;
        for(int mask1 = 1; mask1 < BITSET_MAX; mask1++) {
            for(int mask2 = 1; mask2 < BITSET_MAX; mask2++) {
                //make sure that mask1 contains all of mask2
                //later, we'll turn on all of mask1 that isnt it mask2
                if((mask1 & mask2) != mask2)
                    continue;

                int best1 = 0;
                int best2 = 0;

                //iterate over all possible ending locations and find best pressure
                for(int i = 0; i < nonzeroFlow.size(); i++) {
                    //get best score of all valves in mask1 that aren't in mask2
                    best1 = Math.max(best1,dp[26][i][(mask1 & (~mask2))]);
                    best2 = Math.max(best2,dp[26][i][mask2]);
                }

                bestPressure = Math.max(bestPressure,best1 + best2);
            }
        }

        return Integer.toString(bestPressure);
    }

    public static void main(String[] args) {
        input = Input.fetchInput(2022,16);
        DayRunner.run(new Day16());
    }
}
