package advent.aoc2023;

import advent.utilities.general.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class Day23 implements IDay {

    static String input;

    public static void main(String[] args) {
        input = Input.fetchInput(2023, 23);
        DayRunner.run(new Day23());
    }

    static String input2 = "#.#####################\n" +
            "#.......#########...###\n" +
            "#######.#########.#.###\n" +
            "###.....#.>.>.###.#.###\n" +
            "###v#####.#v#.###.#.###\n" +
            "###.>...#.#.#.....#...#\n" +
            "###v###.#.#.#########.#\n" +
            "###...#.#.#.......#...#\n" +
            "#####.#.#.#######.#.###\n" +
            "#.....#.#.#.......#...#\n" +
            "#.#####.#.#.#########v#\n" +
            "#.#...#...#...###...>.#\n" +
            "#.#.#v#######v###.###v#\n" +
            "#...#.>.#...>.>.#.###.#\n" +
            "#####v#.#.###v#.#.###.#\n" +
            "#.....#...#...#.#.#...#\n" +
            "#.#########.###.#.#.###\n" +
            "#...###...#...#...#.###\n" +
            "###.###.#.###v#####v###\n" +
            "#...#...#.#.>.>.#.>.###\n" +
            "#.###.###.#.###.#.#v###\n" +
            "#.....###...###...#...#\n" +
            "#####################.#";

    @Override
    public String part1() {
        HashSet<Coord> edge = new HashSet<>();
        HashMap<Coord, Coord> oneWay = new HashMap<>();
        String[] lines = input2.split("\n");
        char[][] map = new char[lines.length][lines[0].length()];
        for(int y = 0; y < lines.length; y++) {
            map[y] = lines[y].toCharArray();
            for(int x = 0; x < map[y].length; x++) {
                if(map[y][x] == '#') {
                    edge.add(new Coord(x,y));
                } else if (map[y][x] == '>' || map[y][x] == '<' || map[y][x] == 'v' || map[y][x] == '^')
                    oneWay.put(new Coord(x,y), correctFacing(map[y][x]));
            }
        }
        System.out.println(oneWay);
        Coord start = new Coord(1,0);
        Coord end = new Coord(map[0].length-2, map.length-1);
        edge.add(start.sum(Coord.UP));
        edge.add(end.sum(Coord.DOWN));
        return Integer.toString(maxPath(start, Coord.DOWN, end, edge, oneWay, 1));
    }

    public int maxPath(Coord cur, Coord facing, Coord end, HashSet<Coord> walls, HashMap<Coord,Coord> oneWay, int length) {
        ArrayList<Coord> options;
        while(true) {
            options = cur.directNeighbors();
            options.removeAll(walls);
            //avoid going backwards
            Coord reverse = new Coord(cur.x - facing.x, cur.y - facing.y);
            options.remove(reverse);
            if(options.size() != 1)
                break;
            Coord next = options.get(0);
            if(next.equals(end))
                return length;
            facing = new Coord(next.x-cur.x, next.y-cur.y);
            cur = next;
            length++;
        }
        System.out.println(cur);

            int ret = 0;
            for(Coord next : options) {
                Coord newFacing = new Coord(next.x-cur.x, next.y-cur.y);
                System.out.println(next + " " + newFacing);
                if(!newFacing.equals(oneWay.getOrDefault(next, newFacing)))
                    continue;
                System.out.println("!!");
                ret = Math.max(ret,maxPath(next, newFacing, end, walls,oneWay, length+1));
            }
            return ret;
    }

    public Coord correctFacing(char c) {
        return switch (c) {
            case '>' -> Coord.RIGHT;
            case '<' -> Coord.LEFT;
            case 'v' -> Coord.DOWN;
            case '^' -> Coord.UP;
            default -> null;
        };
    }


    public int maxPath2(Coord start, Coord facing, Coord end, HashSet<Coord> walls, int length, HashSet<Coord> seen) {
        ArrayList<Coord> options;
        HashSet<Coord> newSeen = new HashSet<>();

        Coord cur = start.copy();
        while(true) {
            options = cur.directNeighbors();
            options.removeAll(walls);
            //avoid going backwards
            Coord reverse = new Coord(cur.x - facing.x, cur.y - facing.y);
            options.remove(reverse);
            if(options.size() != 1)
                break;
            Coord next = options.get(0);
            if(seen.contains(next)) {
                seen.removeAll(newSeen);
                return 0;
            }
            if(next.equals(end)) {
                seen.removeAll(newSeen);
                return length;
            }
            facing = new Coord(next.x-cur.x, next.y-cur.y);
            cur = next;
            seen.add(next);
            newSeen.add(next);
            length++;
        }

        System.out.println(cur);
        int ret = 0;
        for(Coord next : options) {
            System.out.print(" ");
            Coord newFacing = new Coord(next.x-cur.x, next.y-cur.y);
            ret = Math.max(ret,maxPath2(next, newFacing, end, walls, length+1,seen));
            System.out.println(next + " " + ret);
        }
        return ret;

    }

    public HashMap<Coord, LinkedList<Pair<Coord, Integer>>> mergeJunctions(HashSet<Coord> junctions, HashSet<Coord> walls) {
        HashMap<Coord, LinkedList<Pair<Coord, Integer>>> newJunct = new HashMap<>();
        for(Coord junct : junctions) {
            LinkedList<Pair<Coord,Integer>> edges = new LinkedList<>();
            ArrayList<Coord> starts = junct.directNeighbors();
            starts.removeAll(walls);
            for(Coord start : starts)
                edges.add(distToInterest(start, junctions, walls));
            newJunct.put(junct, edges);
        }
        return newJunct;
    }

    public Pair<Coord, Integer> distToInterest(Coord start, HashSet<Coord> junctions, HashSet<Coord> walls) {
        ArrayList<Coord> options = start.directNeighbors();
        options.removeAll(junctions);
        options.removeAll(walls);
        int dist = 1;
        Coord cur = options.get(0);
        Coord last = start.copy();
        while(!junctions.contains(cur)) {
            options = cur.directNeighbors();
            /*
            options.removeAll(walls);
            //avoid going backwards
            options.remove(last);
            last=cur;
            cur=options.get(0);
            dist++;*

             */
            for(Coord c : options) {
                if(!walls.contains(c) && !c.equals(last)) {
                    last = cur;
                    cur = c;
                    dist++;
                    break;
                }
            }
        }
        return new Pair<>(cur,dist+1);
    }

    public int briefDFS(Coord cur, Coord end, HashSet<Coord> seen, HashMap<Coord, LinkedList<Pair<Coord, Integer>>> junctions, int length) {
        if(cur.equals(end))
            return length;
        int max = 0;
        for(Pair<Coord, Integer> next: junctions.get(cur)) {
            if(!seen.add(next.key))
                continue;
            max = Math.max(max, briefDFS(next.key, end, seen, junctions,length+next.value));
            seen.remove(next.key);
        }
        return max;
    }

    @Override
    public String part2() {
        HashSet<Coord> edge = new HashSet<>();
        HashSet<Coord> junction = new HashSet<>();
        String[] lines = input.split("\n");
        char[][] map = new char[lines.length][lines[0].length()];
        for(int y = 0; y < lines.length; y++) {
            map[y] = lines[y].toCharArray();
            for(int x = 0; x < map[y].length; x++) {
                if(map[y][x] == '#') {
                    edge.add(new Coord(x,y));
                }
            }
        }

        Coord start = new Coord(1,0);
        Coord end = new Coord(map[0].length-2, map.length-1);
        edge.add(start.sum(Coord.UP));
        edge.add(end.sum(Coord.DOWN));
        for(int y = 0; y < map.length; y++) {
            for(int x = 0; x < map[y].length; x++) {
                if(map[y][x]=='#')
                    continue;
                ArrayList<Coord> neighbors = new Coord(x,y).directNeighbors();
                neighbors.removeAll(edge);
                if(neighbors.size() > 2)
                    junction.add(new Coord(x,y));
            }
        }

        junction.add(start);
        junction.add(end);
        HashMap<Coord, LinkedList<Pair<Coord,Integer>>> junctions = mergeJunctions(junction, edge);
        System.out.println(junctions);

        return Integer.toString(briefDFS(start, end, new HashSet<>(), junctions,0));
    }
}
