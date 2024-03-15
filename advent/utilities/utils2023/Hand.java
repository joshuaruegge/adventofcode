package advent.utilities.utils2023;

import java.util.Arrays;
import java.util.HashSet;

public class Hand implements Comparable<Hand> {

    public String hand;
    public int bid;

    int type;
    boolean part2;

    String order;

    public Hand(String hand, int bid, boolean part2) {
        this.hand = hand;
        this.bid = bid;
        this.part2 = part2;


        if (part2) {
            order = "AKQT98765432J";
            type = Integer.MAX_VALUE;
            //try replacing Js with each other character in the hand, take best type
            HashSet<Character> overallDistinct = new HashSet<>();
            for (char c : hand.toCharArray())
                overallDistinct.add(c);
            for (char replaceCandidate : overallDistinct) {
                String newHand = hand.replaceAll("J", replaceCandidate + "");
                int potentialType = type(newHand);
                type = Math.min(type, potentialType);
            }
        } else {
            order = "AKQJT98765432";
            //calculate type
            type = type(hand);
        }
    }

    //determines "type" of hand (ordered in same way as problem description) by sorting, counting distinct chars, etc.
    int type(String hand) {
        HashSet<Character> distinct = new HashSet<>();
        char[] sorted = hand.toCharArray();
        //Sorting guarantees instances of same character will end up next to each other, simplifying type classification
        Arrays.sort(sorted);
        for (char c : hand.toCharArray())
            distinct.add(c);
        switch (distinct.size()) {
            case 1 -> {
                return 1;
            }
            case 2 -> {
                //if first is not equal to second or second-to-last is not equal to last, we have a chain of 4
                if (sorted[0] != sorted[1] || sorted[3] != sorted[4]) return 2;
                else return 3;
            }
            case 3 -> {
                //if chain of three present anywhere
                if ((sorted[0] == sorted[1] && sorted[1] == sorted[2]) || (sorted[1] == sorted[2] && sorted[2] == sorted[3]) || (sorted[2] == sorted[3] && sorted[3] == sorted[4]))
                    return 4;
                else return 5;
            }
            case 4 -> {
                return 6;
            }
            case 5 -> {
                return 7;
            }
        }
        return 0;
    }

    @Override
    public int compareTo(Hand o) {
        if (this.type != o.type) {
            return o.type - this.type;
        }
        for (int i = 0; i < 5; i++) {
            if (this.hand.charAt(i) == o.hand.charAt(i)) continue;
            //order contains possible hand characters, indexed according to value
            return order.indexOf(o.hand.charAt(i)) - order.indexOf(this.hand.charAt(i));
        }
        return 0;
    }
}
