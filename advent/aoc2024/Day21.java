package advent.aoc2024;

import advent.utilities.general.*;

import java.util.HashMap;

public class Day21 implements IDay {

    static String input;

    static String testInput = "029A\n" +
            "980A\n" +
            "179A\n" +
            "456A\n" +
            "379A";

    public static void main(String[] args) {
        input = Input.fetchInput(2024, 21);
        DayRunner.run(new Day21());
    }

    @Override
    public String part1() {
        long total = 0;


        HashMap<Pair<Coord, Coord>, String> memo = new HashMap<>();

        int numKeypads = 3;

        for(String originalSequence : input.split("\n")) {
            String sequence = originalSequence;
            for (int i = 0; i < numKeypads; i++) {
                HashMap<Character, Coord> pad = (i == 0 ? initNumpad() : initDirpad());
                StringBuilder newSequence = new StringBuilder();
                Coord cur = pad.get('A');

                for (char c : sequence.toCharArray()) {
                    Coord dest = pad.get(c);

                    /*
                    Coord moves = dest.sum(new Coord(-cur.x, -cur.y));

                    //direction orders hardcoded to avoid leaving keypad
                    if (moves.x < 0) {
                        int x = moves.x;
                        int y = moves.y;
                        //left + down/up - down/up first unless on '^' or in bottom row of num
                        if(!cur.equals(pad.getOrDefault('^', new Coord(-1,-1))) && !(cur.y==3)) {
                            while (x < 0) {
                                newSequence.append('<');
                                x++;
                            }
                            if (y > 0) {
                                while (y > 0) {
                                    newSequence.append('v');
                                    y--;
                                }
                            } else {
                                while (y < 0) {
                                    newSequence.append('^');
                                    y++;
                                }
                            }
                        } else {

                            if (y > 0) {
                                while (y > 0) {
                                    newSequence.append('v');
                                    y--;
                                }
                            } else {
                                while (y < 0) {
                                    newSequence.append('^');
                                    y++;
                                }
                            }
                            while (x < 0) {
                                newSequence.append('<');
                                x++;
                            }
                        }

                    } else {
                        int x = moves.x;
                        int y = moves.y;
                        if(i != 0 && cur.equals(new Coord(0,1))) {
                            while (x > 0) {
                                newSequence.append('>');
                                x--;
                            }

                            if (y > 0) {
                                while (y > 0) {
                                    newSequence.append('v');
                                    y--;
                                }
                            } else {
                                while (y < 0) {
                                    newSequence.append('^');
                                    y++;
                                }
                            }
                        } else {

                            if (y > 0) {
                                if(i != 0 || dest.y != 3) {
                                    while (y > 0) {
                                        newSequence.append('v');
                                        y--;
                                    }
                                    while (x > 0) {
                                        newSequence.append('>');
                                        x--;
                                    }
                                } else {
                                    while (x > 0) {
                                        newSequence.append('>');
                                        x--;
                                    }
                                    while (y > 0) {
                                        newSequence.append('v');
                                        y--;
                                    }
                                }
                            } else {
                                while (x > 0) {
                                    newSequence.append('>');
                                    x--;
                                }
                                while (y < 0) {
                                    newSequence.append('^');
                                    y++;
                                }
                            }



                        }



                    }
                    newSequence.append('A');

                     */
                    newSequence.append(memoizedBestPath(pad, cur, dest, memo));
                    cur = dest;
                }


                sequence = newSequence.toString();
                //System.out.println(sequence);
            }

            //System.out.println(sequence);

            int len = sequence.length();
            int numericalPart = Integer.parseInt(originalSequence.substring(0, originalSequence.length()-1));
            //System.out.println(len + " " + numericalPart);

            total += len*numericalPart;
        }

        return Long.toString(total);
    }

    public HashMap<Character, Coord> initNumpad() {
        HashMap<Character, Coord> pad = new HashMap<>();
        pad.put('7', new Coord(0,0));
        pad.put('8', new Coord(1,0));
        pad.put('9', new Coord(2,0));
        pad.put('4', new Coord(0,1));
        pad.put('5', new Coord(1, 1));
        pad.put('6', new Coord(2, 1));
        pad.put('1', new Coord(0,2));
        pad.put('2', new Coord(1,2));
        pad.put('3', new Coord(2,2));
        pad.put('!', new Coord(0,3));//representing nontraversible space
        pad.put('0', new Coord(1,3));
        pad.put('A', new Coord(2,3));
        return pad;
    }

    public HashMap<Character, Coord> initDirpad() {
        HashMap<Character, Coord> pad = new HashMap<>();
        pad.put('!', new Coord(0, 0)); // reprsenting nontraversible space
        pad.put('^', new Coord(1,0));
        pad.put('A', new Coord(2, 0));
        pad.put('<', new Coord(0, 1));
        pad.put('v', new Coord(1,1));
        pad.put('>', new Coord(2,1));
        return pad;
    }

    public String memoizedBestPath(HashMap<Character, Coord> pad, Coord cur, Coord dest, HashMap<Pair<Coord, Coord>, String> memo) {
        Pair<Coord, Coord> key = new Pair<>(cur, dest);
        if(pad.size() == 6 && memo.containsKey(key))
            return memo.get(key);

        StringBuilder newSequence = new StringBuilder();

        Coord moves = dest.sum(new Coord(-cur.x, -cur.y));

        int x = moves.x;
        int y = moves.y;

        if(x < 0) {
            if(new Coord(cur.x+x, cur.y).equals(pad.get('!'))) {
                if (y > 0) {
                    while (y > 0) {
                        newSequence.append('v');
                        y--;
                    }
                } else {
                    while (y < 0) {
                        newSequence.append('^');
                        y++;
                    }
                }
                while (x < 0) {
                    newSequence.append('<');
                    x++;
                }
            } else {
                while (x < 0) {
                    newSequence.append('<');
                    x++;
                }
                if (y > 0) {
                    while (y > 0) {
                        newSequence.append('v');
                        y--;
                    }
                } else {
                    while (y < 0) {
                        newSequence.append('^');
                        y++;
                    }
                }
            }
        } else {
            if(new Coord(cur.x, cur.y+y).equals(pad.get('!'))) {
                while (x > 0) {
                    newSequence.append('>');
                    x--;
                }
                if (y > 0) {
                    while (y > 0) {
                        newSequence.append('v');
                        y--;
                    }
                } else {
                    while (y < 0) {
                        newSequence.append('^');
                        y++;
                    }
                }
            } else {
                if (y > 0) {
                    while (y > 0) {
                        newSequence.append('v');
                        y--;
                    }
                } else {
                    while (y < 0) {
                        newSequence.append('^');
                        y++;
                    }
                }
                while (x > 0) {
                    newSequence.append('>');
                    x--;
                }
            }
        }

        newSequence.append('A');

        String ret = newSequence.toString();

        if(pad.size()==6)
            memo.put(key, ret);

        return ret;
    }

    public long memoizedRecursion(HashMap<String, Long> memo, HashMap<Pair<Coord, Coord>, String> padMemo, String sequence, int depth) {
        if(depth==0) {
            return sequence.length();
        }
        if(memo.containsKey(sequence+depth))
            return memo.get(sequence+depth);

        long result = 0;

        HashMap<Character, Coord> pad = (depth == 26 ? initNumpad() : initDirpad());

        Coord cur = pad.get('A');

        for (int j = 0; j < sequence.length(); j++) {
            char c = sequence.charAt(j);
            Coord dest = pad.get(c);
            result += memoizedRecursion(memo, padMemo, memoizedBestPath(pad, cur, dest, padMemo), depth-1);
            cur = dest;
        }

        memo.put(sequence+depth, result);
        return result;
    }


    @Override
    public String part2() {
        long total = 0;


        HashMap<Pair<Coord, Coord>, String> padMemo = new HashMap<>();
        HashMap<String, Long> memo = new HashMap<>();

        /*
        for(String originalSequence : input.split("\n")) {
            StringBuilder sequence = new StringBuilder(originalSequence);
            for (int i = 0; i < numKeypads; i++) {
                System.out.println(i);
                HashMap<Character, Coord> pad = (i == 0 ? initNumpad() : initDirpad());
                StringBuilder newSequence = new StringBuilder();
                Coord cur = pad.get('A');

                for (int j = 0; j < sequence.length(); j++) {
                    char c = sequence.charAt(j);
                    Coord dest = pad.get(c);
                    newSequence.append(memoizedBestPath(pad, cur, dest, memo));
                    cur = dest;
                }


                sequence = newSequence;
                //System.out.println(sequence);
            }

            //System.out.println(sequence);

            int len = sequence.length();
            int numericalPart = Integer.parseInt(originalSequence.substring(0, originalSequence.length()-1));
            //System.out.println(len + " " + numericalPart);

            total += len*numericalPart;
        }
           */

        for(String seq : input.split("\n")) {
            total += memoizedRecursion(memo, padMemo, seq, 26) * Long.parseLong(seq.substring(0, 3));
        }

        return Long.toString(total);
    }
}
