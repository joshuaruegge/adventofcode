package advent.aoc2023;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;

public class Day17 implements IDay {

    static String input;

    public static void main(String[] args) {
        input = Input.fetchInput(2023, 17);
        DayRunner.run(new Day17());
    }

    @Override
    public String part1() {
        String[] lines = input.split("\n");
        int[][] heat = new int[lines[0].length()][lines.length];
        for (int x = 0; x < heat.length; x++)
            for (int y = 0; y < heat[0].length; y++)
                heat[x][y] = lines[y].charAt(x) - '0';
        return Integer.toString(getMinHeatLoss(heat, 1, 3));
    }

    public int getMinHeatLoss(int[][] map, int minSteps, int maxSteps) {
        //states are handled as 5-item int[]s of [cur x, cur y, cur dir code, steps traveled in this dir, heat loss];
        //dir is handled as:
        //0 - UP
        //1 - RIGHT
        //2 - DOWN
        //3 - LEFT
        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o[4]));
        //two starting cases: corner facing right, corner facing down
        queue.add(new int[]{0, 0, 1, 0, 0});
        queue.add(new int[]{0, 0, 2, 0, 0});

        HashSet<Integer> seen = new HashSet<>();

        int solution = Integer.MAX_VALUE;

        while (!queue.isEmpty()) {
            //curX, curY, curDir, steps in dir, heat
            int[] curState = queue.poll();

            int x = curState[0];
            int y = curState[1];
            int dir = curState[2];
            int steps = curState[3];
            int heat = curState[4];

            if (x == map.length - 1 && y == map[0].length - 1) {
                //edge case - not enough steps to finish
                if (steps < minSteps) continue;
                return heat;
            }

            //build list of [new x, new y, new dir] for each potential direction
            int[][] nextSteps = new int[3][3];
            //each case: straight, turn left, turn right
            switch (dir) {
                //north
                case 0 -> {
                    nextSteps[0] = new int[]{x, y - 1, 0};
                    nextSteps[1] = new int[]{x - 1, y, 3};
                    nextSteps[2] = new int[]{x + 1, y, 1};
                }
                case 1 -> {
                    nextSteps[0] = new int[]{x + 1, y, 1};
                    nextSteps[1] = new int[]{x, y - 1, 0};
                    nextSteps[2] = new int[]{x, y + 1, 2};
                }
                case 2 -> {
                    nextSteps[0] = new int[]{x, y + 1, 2};
                    nextSteps[1] = new int[]{x + 1, y, 1};
                    nextSteps[2] = new int[]{x - 1, y, 3};
                }
                case 3 -> {
                    nextSteps[0] = new int[]{x - 1, y, 3};
                    nextSteps[1] = new int[]{x, y + 1, 2};
                    nextSteps[2] = new int[]{x, y - 1, 0};
                }
            }

            for (int[] nextStep : nextSteps) {
                int nX = nextStep[0];
                int nY = nextStep[1];
                int nDir = nextStep[2];
                if (nX < 0 || nY < 0 || nX >= map.length || nY >= map[0].length) continue;

                if (steps > maxSteps - 1 && dir == nDir) continue;
                if (steps < minSteps && dir != nDir) continue;

                int newSteps = (dir == nDir ? steps + 1 : 1);


                int[] newState = new int[]{nX, nY, nDir, newSteps, heat + map[nX][nY]};
                int key = arrayHash(newState);

                if (!seen.contains(key)) {
                    seen.add(key);
                    queue.add(newState);
                }
            }
        }

        return solution;
    }

    public int arrayHash(int[] state) {
        return (((state[0] + 1) * 141 + (state[1] + 1) * 97) * 37 + (state[2] + 1) * 19) * 11 + (state[3] + 1) * 5;
    }

    @Override
    public String part2() {
        String[] lines = input.split("\n");
        int[][] heat = new int[lines.length][lines[0].length()];
        for (int y = 0; y < lines.length; y++)
            for (int x = 0; x < lines[0].length(); x++)
                heat[x][y] = lines[y].charAt(x) - '0';
        return Integer.toString(getMinHeatLoss(heat, 4, 10));
    }
}
