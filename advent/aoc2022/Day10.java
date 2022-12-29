package advent.aoc2022;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

import java.util.LinkedList;

public class Day10 implements IDay {

    static String input;

    @Override
    public String part1() {
        //keep track of signal at indices
        LinkedList<Integer> signal = new LinkedList<>();
        signal.add(1);
        for(String s : input.split("\n")) {
            String[] words = s.split(" ");
            //if noop, append 1 cycle of signal staying the same
            if(words[0].equals("noop")) {
                signal.add(signal.getLast());
            //if addx, append 1 cycle of staying the same, and 1 cycle of increase
            } else {
                signal.add(signal.getLast());
                signal.add(signal.getLast() + Integer.parseInt(words[1]));
            }
        }
        return Integer.toString(signal.get(19) * 20 + signal.get(59) * 60 + signal.get(99) * 100 + signal.get(139) * 140 + signal.get(179) * 180 + signal.get(219) * 220);
    }

    @Override
    public String part2() {
        //keep track of signal at indices
        LinkedList<Integer> signal = new LinkedList<>();
        signal.add(1);
        for(String s : input.split("\n")) {
            String[] words = s.split(" ");
            //if noop, append 1 cycle of signal staying the same
            if(words[0].equals("noop")) {
               signal.add(signal.getLast());
            //if addx, append 1 cycle of staying the same, and 1 cycle of increase
            } else {
                signal.add(signal.getLast());
                signal.add(signal.getLast() + Integer.parseInt(words[1]));
            }
        }

        //reconstruct display line by line
        for(int y=0; y < 6; y++) {
            for(int x = 0; x < 40; x++) {
                //cycle count is row * 40 + column
                //because x is basically cycle % 40
                int signalAt = signal.get(y*40 + x);
                //if x was within 1 of signal at cycle, print character
                if(x == signalAt || x == signalAt - 1 || x == signalAt + 1)
                    System.out.print("█");
                else
                    System.out.print(" ");
            }
            System.out.println();
        }
        return "Examine output";
    }

    public static void main(String[] args) {
        input = Input.fetchInput(2022,10);
        DayRunner.run(new Day10());
    }
}
