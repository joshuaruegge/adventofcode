package advent.aoc2022;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

import java.util.ArrayList;
import java.util.LinkedList;

public class Day05 implements IDay {

    static String input;

    @Override
    public String part1() {
        String[] parts = input.split("\n\n");
        String[] initial = parts[0].split("\n");
        //create stacks
        ArrayList<LinkedList<Character>> stacks = new ArrayList<>();
        for(int i = 0; i < 9; i++)
            stacks.add(new LinkedList<>());
        //use last row to convert string index to stack number
        //index of a character in the input is in the stack at the same index in the last row
        String stackNums = initial[initial.length - 1];
        for(int i = 0; i < initial.length - 1; i++) {
            String row = initial[i];
            for(int j = 0; j < row.length(); j++) {
                char c = row.charAt(j);
                if(!(c == '[' || c == ']' || c == ' ')) {
                    int rowID = Integer.parseInt(stackNums.substring(j,j+1)) - 1;
                    stacks.get(rowID).add(c);
                }
            }
        }
        for(String s : parts[1].split("\n")) {
            String[] words = s.split(" ");
            int count = Integer.parseInt(words[1]);
            int src = Integer.parseInt(words[3]) - 1;
            int dest = Integer.parseInt(words[5]) - 1;
            for (int i = 0; i < count; i++) {
                stacks.get(dest).addFirst(stacks.get(src).removeFirst());
            }
        }
        StringBuilder answer = new StringBuilder();
        for(LinkedList<Character> s : stacks)
            answer.append(s.getFirst());
        return answer.toString();
    }

    @Override
    public String part2() {
        String[] parts = input.split("\n\n");
        String[] initial = parts[0].split("\n");
        //create stacks
        ArrayList<LinkedList<Character>> stacks = new ArrayList<>();
        for(int i = 0; i < 9; i++)
            stacks.add(new LinkedList<>());
        //use last row to convert string index to stack number
        //index of a character in the input is in the stack at the same index in the last row
        String stackNums = initial[initial.length - 1];
        for(int i = 0; i < initial.length - 1; i++) {
            String row = initial[i];
            for(int j = 0; j < row.length(); j++) {
                char c = row.charAt(j);
                if(!(c == '[' || c == ']' || c == ' ')) {
                    int rowID = Integer.parseInt(stackNums.substring(j,j+1)) - 1;
                    stacks.get(rowID).add(c);
                }
            }
        }
        for(String s : parts[1].split("\n")) {
            String[] words = s.split(" ");
            int count = Integer.parseInt(words[1]);
            int src = Integer.parseInt(words[3]) - 1;
            int dest = Integer.parseInt(words[5]) - 1;
            //put characters onto buffer so that order stays correct
            LinkedList<Character> temp = new LinkedList<>();
            for (int i = 0; i < count; i++) {
                temp.add(stacks.get(src).removeFirst());
            }
            //move entire buffer
            stacks.get(dest).addAll(0,temp);
        }
        StringBuilder answer = new StringBuilder();
        for(LinkedList<Character> s : stacks)
            answer.append(s.getFirst());
        return answer.toString();
    }

    public static void main(String[] args) {
        input = Input.fetchInput(2022,5);
        DayRunner.run(new Day05());
    }
}
