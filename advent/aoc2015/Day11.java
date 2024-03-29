package advent.aoc2015;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

public class Day11 implements IDay {

    static String input;

    //buffer part 1 answer for part 2
    String part1;

    public static void main(String[] args) {
        input = Input.fetchInput(2015, 11);
        DayRunner.run(new Day11());
    }

    @Override
    public String part1() {
        //store as character array to increment easily
        char[] pass = input.replace("\n", "").toCharArray();
        inc:
        while (true) {
            //first, increment password
            //step back to first non-z character
            int index = pass.length - 1;
            while (pass[index] == 'z') index--;
            //increment first non-z character
            pass[index]++;
            //turn all trailing zs to as
            index++;
            while (index < pass.length) {
                pass[index++] = 'a';
            }
            //check password
            //illegal characters
            for (char c : pass)
                if (c == 'i' || c == 'o' || c == 'l') continue inc;
            //2+ pairs
            int pairs = 0;
            for (int i = 0; i < pass.length - 1; i++) {
                if (pass[i] == pass[i + 1]) {
                    pairs++;
                    //avoid overlap
                    i++;
                }
            }
            //ascending
            boolean ascending = false;
            for (int i = 0; i < pass.length - 2; i++) {
                if (pass[i] == pass[i + 1] - 1 && pass[i] == pass[i + 2] - 2) {
                    ascending = true;
                    break;
                }
            }
            if (pairs > 1 && ascending) break;
        }
        part1 = new String(pass);
        return part1;
    }

    @Override
    public String part2() {
        //use cached part 1 value, continue calculating
        //store as character array to increment easily
        char[] pass = part1.toCharArray();
        inc:
        while (true) {
            //increment password
            int index = pass.length - 1;
            while (pass[index] == 'z') index--;
            pass[index]++;
            index++;
            while (index < pass.length) {
                pass[index++] = 'a';
            }
            //check password
            //illegal characters
            for (char c : pass)
                if (c == 'i' || c == 'o' || c == 'l') continue inc;
            //2+ pairs
            int pairs = 0;
            for (int i = 0; i < pass.length - 1; i++) {
                if (pass[i] == pass[i + 1]) {
                    pairs++;
                    //avoid overlap
                    i++;
                }
            }
            //ascending
            boolean ascending = false;
            for (int i = 0; i < pass.length - 2; i++) {
                if (pass[i] == pass[i + 1] - 1 && pass[i] == pass[i + 2] - 2) {
                    ascending = true;
                    break;
                }
            }
            if (pairs > 1 && ascending) break;
        }
        return new String(pass);
    }

}
