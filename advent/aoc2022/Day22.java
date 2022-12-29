package advent.aoc2022;

import advent.utilities.general.Coord;
import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class Day22 implements IDay {

    static String input;

    final Comparator<Coord> compX = Comparator.comparingInt(o -> o.x);

    final Comparator<Coord> compY = Comparator.comparingInt(o -> o.y);

    @Override
    public String part1() {
        String[] inputParts = input.split("\n\n");
        //0 for path, 1 for wall
        HashMap<Coord,Integer> map = new HashMap<>();
        String[] mapLines = inputParts[0].split("\n");
        for(int y = 0; y < mapLines.length; y++) {
            String line = mapLines[y];
            for(int x = 0; x < line.length(); x++) {
                char c = line.charAt(x);
                if(c == ' ')
                    continue;
                if(c == '.')
                    map.put(new Coord(x,y), 0);
                else
                    map.put(new Coord(x,y), 1);
            }
        }

        ArrayList<String> instructions = new ArrayList<>();
        char[] digits = inputParts[1].trim().toCharArray();
        for(int i = 0; i < digits.length; i++) {
            char c = digits[i];
            if(c <= '9') {
                String num = c + "";
                if(i < digits.length - 1 && digits[i+1] <= '9') {
                    num += digits[i+1];
                    i++;
                }
                instructions.add(num);
            } else {
                instructions.add(c + "");
            }
        }

        //leftmost open tile of top row
        Coord cur = map.keySet().stream().filter(x -> map.get(x) == 0 && x.y == 0).min(compX).get();

        Coord facing = Coord.RIGHT;

        for(String s : instructions) {
            if(s.equals("L"))
                facing = facing.left();
            else if(s.equals("R"))
                facing = facing.right();
            else {
                int steps = Integer.parseInt(s);
                for(int i = 0; i < steps; i++) {
                    Coord next = cur.sum(facing);
                    if(!map.containsKey(next)) {
                        next = wrap(map,next,facing);
                    }
                    if(map.get(next) == 1)
                        break;
                    cur = next;
                }
            }
        }

        int password = 1000 * (cur.y + 1) + 4 * (cur.x + 1);
        if (facing.equals(Coord.DOWN)) {
            password += 1;
        } else if (facing.equals(Coord.LEFT)) {
            password += 2;
        } else if (facing.equals(Coord.UP)) {
            password += 3;
        }
        return Integer.toString(password);
    }

    public Coord wrap(HashMap<Coord,Integer> map, Coord next, Coord facing) {
        if (facing.equals(Coord.DOWN)) {
            //if wrapping down, examine all in the same column, and find closest to top
            return map.keySet().stream().filter(x -> x.x == next.x).min(compY).get();
        } else if (facing.equals(Coord.UP)) {
            //if wrapping up, examine all in the same column, and find closest to bottom
            return map.keySet().stream().filter(x -> x.x == next.x).max(compY).get();
        } else if (facing.equals(Coord.RIGHT)) {
            //if wrapping right, examine all in same row, and find closest to left
            return map.keySet().stream().filter(x -> x.y == next.y).min(compX).get();
        } else if (facing.equals(Coord.LEFT)) {
            //if wrapping left, examine all in same row, and find closest to right
            return map.keySet().stream().filter(x -> x.y == next.y).max(compX).get();
        }
        return null;
    }

    //returns a two-item list, representing new position and new facing, respectively
    //finds the next position for a step off the edge and onto a different face based on hard-coded edge matches for a folded cube map of the net provided
    public ArrayList<Coord> warp(HashMap<Coord,Integer> region, Coord cur, Coord facing) {
        ArrayList<Coord> ret = new ArrayList<>();
        int curRegion = region.get(cur);
        switch (curRegion) {
            case 0 -> {
                //top 0 points to left 5
                if (facing.equals(Coord.UP)) {
                    Coord newPos = new Coord(0, cur.x + 100);
                    Coord newFacing = Coord.RIGHT;
                    ret.add(newPos);
                    ret.add(newFacing);
                    return ret;
                //left 0 points to left 3 (flips y upside down)
                } else if (facing.equals(Coord.LEFT)) {
                    Coord newPos = new Coord(0, 149 - cur.y);
                    Coord newFacing = Coord.RIGHT;
                    ret.add(newPos);
                    ret.add(newFacing);
                    return ret;
                }
            }
            case 1 -> {
                //top 1 points to bottom 5
                if (facing.equals(Coord.UP)) {
                    Coord newPos = new Coord(cur.x - 100, 199);
                    Coord newFacing = Coord.UP;
                    ret.add(newPos);
                    ret.add(newFacing);
                    return ret;
                //right 1 points to right 4 (flips y upside down)
                } else if (facing.equals(Coord.RIGHT)) {
                    Coord newPos = new Coord(99, 149 - cur.y);
                    Coord newFacing = Coord.LEFT;
                    ret.add(newPos);
                    ret.add(newFacing);
                    return ret;
                //bottom 1 points to right 2
                } else if (facing.equals(Coord.DOWN)) {
                    Coord newPos = new Coord(99, cur.x - 50);
                    Coord newFacing = Coord.LEFT;
                    ret.add(newPos);
                    ret.add(newFacing);
                    return ret;
                }
            }
            case 2 -> {
                //right 2 faces bottom 1
                if (facing.equals(Coord.RIGHT)) {
                    Coord newPos = new Coord(cur.y + 50, 49);
                    Coord newFacing = Coord.UP;
                    ret.add(newPos);
                    ret.add(newFacing);
                    return ret;
                //left 2 faces top 3
                } else if (facing.equals(Coord.LEFT)) {
                    Coord newPos = new Coord(cur.y - 50, 100);
                    Coord newFacing = Coord.DOWN;
                    ret.add(newPos);
                    ret.add(newFacing);
                    return ret;
                }
            }
            case 3 -> {
                //top 3 faces left 2
                if (facing.equals(Coord.UP)) {
                    Coord newPos = new Coord(50, cur.x + 50);
                    Coord newFacing = Coord.RIGHT;
                    ret.add(newPos);
                    ret.add(newFacing);
                    return ret;
                //left 3 faces left 0 (flips Y)
                } else if (facing.equals(Coord.LEFT)) {
                    Coord newPos = new Coord(50, 149 - cur.y);
                    Coord newFacing = Coord.RIGHT;
                    ret.add(newPos);
                    ret.add(newFacing);
                    return ret;
                }
            }
            case 4 -> {
                //right 4 faces right 1 (flips y)
                if (facing.equals(Coord.RIGHT)) {
                    Coord newPos = new Coord(149, 149 - cur.y);
                    Coord newFacing = Coord.LEFT;
                    ret.add(newPos);
                    ret.add(newFacing);
                    return ret;
                //down 4 faces right 5
                } else if (facing.equals(Coord.DOWN)) {
                    Coord newPos = new Coord(49, cur.x + 100);
                    Coord newFacing = Coord.LEFT;
                    ret.add(newPos);
                    ret.add(newFacing);
                    return ret;
                }
            }
            case 5 -> {
                //right 5 faces bottom 4
                if (facing.equals(Coord.RIGHT)) {
                    Coord newPos = new Coord(cur.y - 100, 149);
                    Coord newFacing = Coord.UP;
                    ret.add(newPos);
                    ret.add(newFacing);
                    return ret;
                //bottom 5 faces top 1
                } else if (facing.equals(Coord.DOWN)) {
                    Coord newPos = new Coord(cur.x + 100, 0);
                    Coord newFacing = Coord.DOWN;
                    ret.add(newPos);
                    ret.add(newFacing);
                    return ret;
                //left 5 faces top 0
                } else if (facing.equals(Coord.LEFT)) {
                    Coord newPos = new Coord(cur.y - 100, 0);
                    Coord newFacing = Coord.DOWN;
                    ret.add(newPos);
                    ret.add(newFacing);
                    return ret;
                }
            }
        }
        return null;
    }

    @Override
    public String part2() {
        String[] inputParts = input.split("\n\n");
        //0 for path, 1 for wall
        HashMap<Coord,Integer> map = new HashMap<>();
        String[] mapLines = inputParts[0].split("\n");
        for(int y = 0; y < mapLines.length; y++) {
            String line = mapLines[y];
            for(int x = 0; x < line.length(); x++) {
                char c = line.charAt(x);
                if(c == ' ')
                    continue;
                if(c == '.')
                    map.put(new Coord(x,y), 0);
                else
                    map.put(new Coord(x,y), 1);
            }
        }

        //classify coords by face
        //ends up with map looking like:
        // 01
        // 2
        //34
        //5
        HashMap<Coord,Integer> region = new HashMap<>();
        for(Coord c : map.keySet()) {
            //first face
            if(c.x > 49 && c.x < 100 && c.y < 50) {
                region.put(c,0);
            }
            //second face
            if(c.x > 99 && c.y < 50)
                region.put(c,1);
            //third face
            if(c.y > 49 && c.y < 100)
                region.put(c,2);
            //fourth face
            if(c.x < 50 && c.y > 99 && c.y < 150)
                region.put(c,3);
            //fifth face
            if(c.x > 49 && c.x < 100 && c.y > 99 && c.y < 150)
                region.put(c,4);
            //sixth face
            if(c.y > 149)
                region.put(c,5);
        }

        ArrayList<String> instructions = new ArrayList<>();
        char[] digits = inputParts[1].trim().toCharArray();
        for(int i = 0; i < digits.length; i++) {
            char c = digits[i];
            if(c <= '9') {
                String num = c + "";
                if(i < digits.length - 1 && digits[i+1] <= '9') {
                    num += digits[i+1];
                    i++;
                }
                instructions.add(num);
            } else {
                instructions.add(c + "");
            }
        }

        //leftmost open tile of top row
        Coord cur = map.keySet().stream().filter(x -> map.get(x) == 0 && x.y == 0).min(compX).get();

        Coord facing = Coord.RIGHT;

        for(String s : instructions) {
            if(s.equals("L"))
                facing = facing.left();
            else if(s.equals("R"))
                facing = facing.right();
            else {
                int steps = Integer.parseInt(s);
                for(int i = 0; i < steps; i++) {
                    Coord next = cur.sum(facing);
                    if(!map.containsKey(next)) {
                        ArrayList<Coord> result = warp(region,cur,facing);
                        next = result.get(0);
                        if(map.get(next) == 1)
                            break;
                        facing = result.get(1);
                    }
                    if(map.get(next) == 1)
                        break;
                    cur = next;
                }
            }
        }

        int password = 1000 * (cur.y + 1) + 4 * (cur.x + 1);
        if (facing.equals(Coord.DOWN)) {
            password += 1;
        } else if (facing.equals(Coord.LEFT)) {
            password += 2;
        } else if (facing.equals(Coord.UP)) {
            password += 3;
        }
        return Integer.toString(password);
    }

    public static void main(String[] args) {
        input = Input.fetchInput(2022,22);
        DayRunner.run(new Day22());
    }
}
