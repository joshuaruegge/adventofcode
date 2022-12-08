package advent.aoc2022;

import advent.utilities.general.Coord;
import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

public class Day08 implements IDay {

    static String input;

    @Override
    public String part1() {
        String[] lines = input.split("\n");
        int[][] map = new int[lines.length][lines[0].length()];
        for(int i = 0; i < lines.length; i++) {
            String s = lines[i];
            for(int j = 0; j < s.length(); j++) {
                map[i][j] = Integer.parseInt(s.substring(j,j+1));
            }
        }

        int visible = 0;
        final Coord[] DIRS = new Coord[] {Coord.UP, Coord.DOWN, Coord.LEFT, Coord.RIGHT};
        for(int i = 0; i < map.length; i++) {
            for(int j  = 0; j < map[i].length; j++) {
                int curHeight = map[i][j];
                //edge tiles automatically visible
                if(i == 0 || j == 0 || i == map.length - 1 || j == map[i].length - 1) {
                    visible++;
                    continue;
                }
                //iterate over each direction
                inner:
                for(Coord dir : DIRS) {
                    Coord cur = new Coord(i,j);
                    //do first move in direction
                    cur.sumSelf(dir);
                    //while loop is skipped if first move pushed us over edge
                    while(cur.x > -1 && cur.y > -1 && cur.x < map.length && cur.y < map[i].length) {
                        //if current location is too tall, skip to next direction
                        if(map[cur.x][cur.y] >= curHeight) {
                            continue inner;
                        }
                        //move in direction
                        cur.sumSelf(dir);
                    }
                    //if we didn't continue before here, location is visible. increment and avoid also checking other dirs
                    visible++;
                    break;
                }
            }
        }
        return Integer.toString(visible);
    }

    @Override
    public String part2() {
        String[] lines = input.split("\n");
        int[][] map = new int[lines.length][lines[0].length()];
        for(int j = 0; j < lines.length; j++) {
            String s = lines[j];
            for(int i = 0; i < s.length(); i++) {
                map[j][i] = Integer.parseInt(s.substring(i,i+1));
            }
        }
        long bestScenic = 0;
        final Coord[] DIRS = new Coord[] {Coord.UP, Coord.DOWN, Coord.LEFT, Coord.RIGHT};
        for(int i = 0; i < map.length; i++) {
            for(int j  = 0; j < map[i].length; j++) {
                int curHeight = map[i][j];
                //start with 1 for multipliers
                long scenic = 1;
                inner:
                for(Coord dir : DIRS) {
                    long dist = 0;
                    Coord cur = new Coord(i,j);
                    //do first move in direction
                    cur.sumSelf(dir);
                    //while loop is skipped if first move pushed us over edge
                    while(cur.x > -1 && cur.y > -1 && cur.x < map.length && cur.y < map[i].length) {
                        //increment distance
                        dist++;
                        //break if too tall
                        if(map[cur.x][cur.y] >= curHeight) {
                            break;
                        }
                        //move in direction
                        cur.sumSelf(dir);
                    }
                    //multiply inner total by distance value
                    scenic *= dist;
                }
                //keep highest scenic
                bestScenic = Math.max(scenic,bestScenic);
            }
        }
        return Long.toString(bestScenic);
    }

    public static void main(String[] args) {
        input = Input.fetchInput(2022,8);
        DayRunner.run(new Day08());
    }
}
