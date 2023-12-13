package advent.utilities.utils2023;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class Hand implements Comparable<Hand>{

    public String hand;
    public int type;
    public int bid;

    boolean part2;

    public Hand(String hand, int bid, boolean part2) {
        this.hand = hand;
        this.bid = bid;
        this.part2 = part2;

        if(part2) {
            type = Integer.MAX_VALUE;
            HashSet<Character> overallDistinct = new HashSet<>();
            for(char c : hand.toCharArray())
                overallDistinct.add(c);
            for(char rep : overallDistinct) {
                String newHand = hand.replaceAll('J'+"",rep+"");
                int potType = Integer.MAX_VALUE;
                HashSet<Character> distinct = new HashSet<>();
                char[] sorted = newHand.toCharArray();
                Arrays.sort(sorted);
                for(char c : newHand.toCharArray())
                    distinct.add(c);
                switch (distinct.size()) {
                    case 1 -> potType = 1;
                    case 2 -> {
                        if (sorted[0] != sorted[1] || sorted[3] != sorted[4])
                            potType = 2;
                        else
                            potType = 3;
                    }
                    case 3 -> {
                        if ((sorted[0] == sorted[1] && sorted[1] == sorted[2]) || (sorted[1] == sorted[2] && sorted[2] == sorted[3]) || (sorted[2] == sorted[3] && sorted[3] == sorted[4]))
                            potType = 4;
                        else
                            potType = 5;
                    }
                    case 4 -> potType = 6;
                    case 5 -> potType = 7;
                }
                type = Math.min(type,potType);
            }
            return;
        }
        //calculate type
        HashSet<Character> distinct = new HashSet<>();
        char[] sorted = hand.toCharArray();
        Arrays.sort(sorted);
        for(char c : hand.toCharArray())
            distinct.add(c);
        switch (distinct.size()) {
            case 1 -> type = 1;
            case 2 -> {
                if (sorted[0] != sorted[1] || sorted[3] != sorted[4])
                    type = 2;
                else
                    type = 3;
            }
            case 3 -> {
                if ((sorted[0] == sorted[1] && sorted[1] == sorted[2]) || (sorted[1] == sorted[2] && sorted[2] == sorted[3]) || (sorted[2] == sorted[3] && sorted[3] == sorted[4]))
                    type = 4;
                else
                    type = 5;
            }
            case 4 -> type = 6;
            case 5 -> type = 7;
        }
    }

    String order = new String(new char[] {'A','K','Q','J','T','9','8','7','6','5','4','3','2'});
    String order2 = new String(new char[] {'A','K','Q','T','9','8','7','6','5','4','3','2','J'});

    @Override
    public int compareTo(Hand o) {
        if(this.type != o.type) {
            return o.type - this.type;
        }
        for(int i = 0; i < 5; i++) {
            if(this.hand.charAt(i)==o.hand.charAt(i))
                continue;
            return (part2 ? order2: order).indexOf(o.hand.charAt(i)) - (part2 ? order2 : order).indexOf(this.hand.charAt(i));
        }
        return 0;
    }
}
