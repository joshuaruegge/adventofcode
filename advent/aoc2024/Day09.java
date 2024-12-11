package advent.aoc2024;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

import java.util.HashSet;

public class Day09 implements IDay {

    static String input;

    static String testInput = "2333133121414131402";

    public static void main(String[] args) {
        input = Input.fetchInput(2024, 9);
        DayRunner.run(new Day09());
    }

    @Override
    public String part1() {
        long total = 0;

        int tail;

        if(input.length() % 2 == 0)
            tail = input.length() - 2;
        else
            tail = input.length() - 1;


        int tailID = tail/2;

        int residual = 0;

        int effectivePos = 0;

        for(int head = 0; head <= tail; head++) {
            int headNum = input.charAt(head) - '0';
            if(head % 2 == 0) {
                int id = head / 2;
                for(int i = 0; i < headNum; i++) {
                    total += id * effectivePos++;
                }
            } else {
                //headNum = len of empty block

                while(headNum > 0) {
                    while (residual == 0) {
                        tailID = tail/2;

                        residual = input.charAt(tail) - '0';
                        System.out.println(residual);
                        tail -= 2;
                    }
                    //System.out.println(tail);
                    //System.out.println(tailID + " " + effectivePos);
                    total += tailID*effectivePos++;
                    headNum--;
                    residual--;
                }
            }

            if(head >= tail) {
                while(residual > 0) {
                    //System.out.println(tailID + " " + effectivePos);
                    total += tailID*effectivePos++;

                    residual--;
                }
            }
        }

        return Long.toString(total);
    }

    @Override
    public String part2() {
        long total = 0;


        int tail;

        if(input.length() % 2 == 0)
            tail = input.length() - 2;
        else
            tail = input.length() - 1;

        int head = 0;

        //int tailID = tail/2;

        int effectivePos = 0;

        HashSet<Integer> alreadyMoved = new HashSet<>();

        while(head < input.length()) {
            System.out.println(head + " " + tail);
            int headNum = input.charAt(head) - '0';
            if(head % 2 == 0) {
                int id = head / 2;
                if(alreadyMoved.contains(id)) {
                    id = 0;
                }
                for(int i = 0; i < headNum; i++) {
                    total += id * effectivePos++;
                }
            } else {
                //headnum = total block width
                tailloop:
                while(headNum > 0) {

                    int tailSearch = tail;
                    while(tailSearch > head) {
                        int tailSize = input.charAt(tailSearch) - '0';
                        if(tailSize <= headNum) {
                            int tailID = tailSearch / 2;


                            if(!alreadyMoved.add(tailID)) {
                                //System.out.println("Skipping " + tailID);
                                tailSearch-= 2;
                                continue;
                            }


                            //System.out.println(tailID);
                            //System.out.println(tailSize);
                            while(tailSize > 0) {
                                total += tailID * effectivePos++;
                                headNum--;
                                tailSize--;
                            }

                            //tail -= 2;
                            continue tailloop;
                        }
                        tailSearch -= 2;
                    }
                    if(tailSearch <= head) {
                        while(headNum > 0) {
                            effectivePos++;
                            headNum--;
                        }
                    }
                }
            }
            System.out.println(total);
            head++;
        }

        return Long.toString(total);
    }
}
