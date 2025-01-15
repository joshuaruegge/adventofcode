package advent.aoc2024;

import advent.utilities.general.Coord;
import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

import java.util.Arrays;
import java.util.HashSet;

public class Day15 implements IDay {

    static String input;

    static String testInput = "##########\n" +
            "#..O..O.O#\n" +
            "#......O.#\n" +
            "#.OO..O.O#\n" +
            "#..O@..O.#\n" +
            "#O#..O...#\n" +
            "#O..O..O.#\n" +
            "#.OO.O.OO#\n" +
            "#....O...#\n" +
            "##########\n" +
            "\n" +
            "<vv>^<v^>v>^vv^v>v<>v^v<v<^vv<<<^><<><>>v<vvv<>^v^>^<<<><<v<<<v^vv^v>^\n" +
            "vvv<<^>^v^^><<>>><>^<<><^vv^^<>vvv<>><^^v>^>vv<>v<<<<v<^v>^<^^>>>^<v<v\n" +
            "><>vv>v^v^<>><>>>><^^>vv>v<^^^>>v^v^<^^>v^^>v^<^v>v<>>v^v^<v>v^^<^^vv<\n" +
            "<<v<^>>^^^^>>>v^<>vvv^><v<<<>^^^vv^<vvv>^>v<^^^^v<>^>vvvv><>>v^<<^^^^^\n" +
            "^><^><>>><>^^<<^^v>>><^<v>^<vv>>v>>>^v><>^v><<<<v>>v<v<v>vvv>^<><<>^><\n" +
            "^>><>^v<><^vvv<^^<><v<<<<<><^v<<<><<<^^<v<^^^><^>>^<v^><<<^>>^v<v^v<v^\n" +
            ">^>>^v>vv>^<<^v<>><<><<v<<v><>v<^vv<<<>^^v^>^^>>><<^v>>v^v><^^>>^<>vv^\n" +
            "<><^^>^^^<><vvvvv^v<v<<>^v<v>v<<^><<><<><<<^^<<<^<<>><<><^^^>^^<>^>v<>\n" +
            "^^>vv<^v^v<vv>^<><v<^v>^^^>>>^^vvv^>vvv<>>>^<^>>>>>^<<^v>^vvv<>^<><<v>\n" +
            "v^^>>><<^^<>>^v^<v^vv<>v^<<>^<^v^v><^<<<><<^<v><v<>vv>>v><v^<vv<>v^<<^";

    public static void main(String[] args) {
        input = Input.fetchInput(2024, 15);
        DayRunner.run(new Day15());
    }

    @Override
    public String part1() {
        long total = 0;

        String[] chunks = input.split("\n\n");

        String[] lines = chunks[0].split("\n");

        Coord robot = new Coord();

        HashSet<Coord> boxes = new HashSet<>();
        HashSet<Coord> walls = new HashSet<>();

        for(int row = 0; row < lines.length; row++) {
            for(int col = 0; col < lines[0].length(); col++) {

                Coord cur = new Coord(row, col);
                char curChar = lines[row].charAt(col);
                if(curChar == '@')
                    robot = cur;
                else if(curChar == 'O')
                    boxes.add(cur);
                else if(curChar == '#')
                    walls.add(cur);
            }
        }

        for(char move : chunks[1].toCharArray()) {
            System.out.println(robot);
            Coord dir;
            switch (move) {
                case 'v' -> dir = Coord.RIGHT;
                case '^' -> dir = Coord.LEFT;
                case '>' -> dir = Coord.DOWN;
                case '<' -> dir = Coord.UP;
                default -> {
                    continue;
                }
            }

            Coord dest = robot.sum(dir);

            if(!boxes.contains(dest) && !walls.contains(dest)) {
                robot.sumSelf(dir);
                continue;
            }

            if(walls.contains(dest))
                continue;

            Coord last = robot.sum(dir);
            while(boxes.contains(last)) {
                last.sumSelf(dir);
            }

            if(!walls.contains(last)){

                if(!dest.equals(last)) {
                    boxes.remove(dest);
                    boxes.add(last);
                }
                robot.sumSelf(dir);
            }
        }

        for(int row = 0; row < lines.length; row++) {
            for(int col = 0; col < lines[0].length(); col++) {
                if(boxes.contains(new Coord(row, col))) {

                    total += row * 100 + col;
                }
            }
        }

        return Long.toString(total);
    }

    @Override
    public String part2() {
        long total = 0;

        String[] chunks = input.split("\n\n");

        String[] lines = chunks[0].split("\n");

        Coord robot = new Coord();

        HashSet<Coord[]> boxes = new HashSet<>();
        HashSet<Coord> walls = new HashSet<>();

        for(int row = 0; row < lines.length; row++) {
            for(int col = 0; col < lines[0].length() * 2; col+= 2) {

                Coord cur = new Coord(row, col);
                char curChar = lines[row].charAt(col/2);
                if(curChar == '@')
                    robot = cur;
                else if(curChar == 'O')
                    boxes.add(new Coord[] {cur, cur.sum(Coord.DOWN)});
                else if(curChar == '#') {
                    walls.add(cur);
                    walls.add(cur.sum(Coord.DOWN));
                }
            }
        }

        dirLoop:
        for(char move : chunks[1].toCharArray()) {
            Coord dir;
            switch (move) {
                case 'v' -> dir = Coord.RIGHT;
                case '^' -> dir = Coord.LEFT;
                case '>' -> dir = Coord.DOWN;
                case '<' -> dir = Coord.UP;
                default -> {
                    continue;
                }
            }


            Coord dest = robot.sum(dir);
            //simpler case

            if(dir.equals(Coord.DOWN) || dir.equals(Coord.UP)) {
                if(find(boxes, dest) == null) {
                    if(!walls.contains(dest)) {
                        robot.sumSelf(dir);
                    }
                    continue;
                }

                Coord last = robot.sum(dir);
                while(find(boxes, last) != null) {
                    last.sumSelf(dir);
                }

                if(!walls.contains(last)) {
                    if(!dest.equals(last)) {
                        /*
                        boxes.remove(find(boxes,dest));
                        boxes.add(new Coord[] {last, last.sum(dir)});

                         */
                        HashSet<Coord[]> boxesToMove = new HashSet<>();
                        for(Coord cur = dest; !cur.equals(last); cur.sumSelf(dir.sum(dir))) {
                            boxesToMove.add(find(boxes, cur));
                        }
                        for(Coord[] box : boxesToMove) {
                            box[0].sumSelf(dir);
                            box[1].sumSelf(dir);
                        }
                    }
                    robot.sumSelf(dir);
                }
            } else {
                if (find(boxes, dest) == null) {
                    if (!walls.contains(dest)) {
                        robot.sumSelf(dir);
                    }
                    continue;
                }

                HashSet<Coord[]> contiguousBoxes = new HashSet<>();
                HashSet<Coord[]> oldBoxes = new HashSet<>();
                oldBoxes.add(find(boxes, dest));
                HashSet<Coord[]> newBoxes;
                do {
                    newBoxes = new HashSet<>();
                    for(Coord[] box : oldBoxes) {
                        if(walls.contains(box[0].sum(dir)) || walls.contains(box[1].sum(dir)))
                            continue dirLoop;
                        if(find(boxes, box[0].sum(dir)) != null)
                            newBoxes.add(find(boxes, box[0].sum(dir)));
                        if(find(boxes, box[1].sum(dir)) != null)
                            newBoxes.add(find(boxes, box[1].sum(dir)));
                    }

                    contiguousBoxes.addAll(oldBoxes);
                    oldBoxes = newBoxes;
                } while (!newBoxes.isEmpty());

                /*
                boxes.removeAll(contiguousBoxes);
                for(Coord[] box : contiguousBoxes)
                    boxes.add(new Coord[] {box[0].sum(dir), box[1].sum(dir)});

                 */
                for(Coord[] box : contiguousBoxes) {
                    box[0].sumSelf(dir);
                    box[1].sumSelf(dir);
                }

                robot.sumSelf(dir);
            }
        }

        for(Coord[] box : boxes)
            System.out.println(Arrays.toString(box));


        for(int row = 0; row < lines.length; row++) {
            for(int col = 0; col < lines[0].length() * 2; col++) {
                if(find(boxes, new Coord(row, col)) != null && find(boxes, new Coord(row, col))[0].equals(new Coord(row,col))) {
                    System.out.println(row + ", " + col);
                    total += row * 100 + col;
                }
            }
        }

        return Long.toString(total);
    }

    public Coord[] find(HashSet<Coord[]> boxes, Coord potBox) {
        for(Coord[] box : boxes) {
            if(box[0].equals(potBox) || box[1].equals(potBox))
                return box;
        }

        return null;
    }

    public void print(String[] lines, HashSet<Coord[]> boxes, HashSet<Coord> walls, Coord robot) {
        for(int row = 0; row < lines.length; row++) {
            for(int col = 0; col < lines[0].length() * 2; col++) {
                Coord cur = new Coord(row, col);
                if(walls.contains(cur)) {
                    System.out.print("#");
                } else if(find(boxes, cur) != null) {
                    if(find(boxes, cur)[0].equals(cur))
                        System.out.print("[");
                    else
                        System.out.print("]");
                } else if(cur.equals(robot))
                    System.out.print("@");
                else
                    System.out.print(".");
            }
            System.out.println();
        }
    }
}
