package advent.aoc2022;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;
import advent.utilities.utils2022.PacketList;

import java.util.ArrayList;
import java.util.Collections;

public class Day13 implements IDay {

    static String input;

    @Override
    public String part1() {
        String[] packets = input.split("\n\n");
        int correctSum = 0;
        for(int i = 0; i < packets.length; i++) {
            String s = packets[i];
            String left = s.split("\n")[0];
            String right = s.split("\n")[1];
            PacketList leftPacket = parsePacket(left);
            PacketList rightPacket = parsePacket(right);
            //1 signifies correct ordering - increment counter
            if(compare(leftPacket,rightPacket) == 1)
                correctSum += i+1;
        }
        return Integer.toString(correctSum);
    }

    public PacketList parsePacket(String s) {
        PacketList packet = new PacketList();
        int index = 1;
        while(index < s.length()) {
            //sublist
            if (s.charAt(index) == '[') {
                //find end of sublist by comparing bracket depth
                int packetDepth = 1;
                int endIndex = index + 1;
                while (packetDepth > 0) {
                    if (s.charAt(endIndex) == ']')
                        packetDepth--;
                    else if (s.charAt(endIndex) == '[')
                        packetDepth++;
                    endIndex++;
                }
                //break out sublist and recursively parse, then skip over to end of sublist
                packet.sublists.add(parsePacket(s.substring(index, endIndex)));
                index = endIndex;
            } else {
                //break out number and add as sub-packet
                int endIndex = s.indexOf(",",index + 1);
                if(endIndex == -1)
                    endIndex = s.indexOf("]",index);
                String num = s.substring(index, endIndex);
                PacketList number = new PacketList();
                try {
                    number.value = Integer.parseInt(num);
                    packet.sublists.add(number);
                } catch(NumberFormatException e) {
                    //empty packet - just don't add as a sublist
                }
                index = endIndex;
            }
            index++;
        }
        return packet;
    }

    //returns 1 if the packets are in the correct order, -1 if they are not, and
    public static int compare(PacketList left, PacketList right) {
        int compareIndex = 0;
        while(compareIndex < left.sublists.size() || compareIndex < right.sublists.size()) {
            //handle if either list runs out of values
            if(left.sublists.size() <= compareIndex)
                return 1;
            if(right.sublists.size() <= compareIndex)
                return -1;

            PacketList leftCur = left.sublists.get(compareIndex);
            PacketList rightCur = right.sublists.get(compareIndex);

            //handle conversion for list on the left, integer on the right
            if(leftCur.sublists.size() > 0 && rightCur.value != -1) {
                PacketList mask = new PacketList();
                PacketList sub = new PacketList();
                sub.value = rightCur.value;
                mask.sublists.add(sub);
                int compare = compare(leftCur,mask);
                //we only care if this result was "definitive" - if not, we need to go over the rest of the parent list
                if(compare != 0)
                    return compare;
                else {
                    compareIndex++;
                    continue;
                }
            }

            //handle conversion for integer on the left, list on the right
            if(leftCur.value != -1 && rightCur.sublists.size() > 0) {
                PacketList mask = new PacketList();
                PacketList sub = new PacketList();
                sub.value = leftCur.value;
                mask.sublists.add(sub);
                int compare = compare(mask,rightCur);
                //we only care if this result was "definitive" - if not, we need to go over the rest of the parent list
                if(compare != 0)
                    return compare;
                else {
                    compareIndex++;
                    continue;
                }
            }

            //get "values"
            int leftVal = left.sublists.get(compareIndex).value;
            int rightVal = right.sublists.get(compareIndex).value;

            //if both have a value of -1, both are lists rather than single integers - do comparison of the sublists
            if(leftVal == -1 && rightVal == -1) {
                //only care about definitive result
                int compare = compare(left.sublists.get(compareIndex),right.sublists.get(compareIndex));
                if(compare != 0)
                    return compare;
                else {
                    compareIndex++;
                    continue;
                }
            }

            //finally, simple integer comparison
            if(leftVal < rightVal)
                return 1;
            if(leftVal > rightVal)
                return -1;

            //if equal, move onto next index of loop
            compareIndex++;
        }
        //if nothing returned yet, test was inconclusive
        return 0;
    }

    @Override
    public String part2() {
        String[] packets = input.split("\n");
        ArrayList<PacketList> sortedPackets = new ArrayList<>();
        for (String s : packets) {
            //skip empty lines
            if (s.equals(""))
                continue;
            PacketList p = parsePacket(s);
            sortedPackets.add(p);
        }
        //add in divider packets
        PacketList two = parsePacket("[[2]]");
        PacketList six = parsePacket("[[6]]");
        sortedPackets.add(two);
        sortedPackets.add(six);
        //fun fact: my compare method needed zero changes from part 1 to do this. the spirit of java itself guided me to write it in the form of a proper compare method cause it knew what was coming
        Collections.sort(sortedPackets,Day13::compare);
        Collections.reverse(sortedPackets);
        //return indexes of now-sorted divider packets, multiplied
        return Integer.toString((sortedPackets.indexOf(two) + 1) * (sortedPackets.indexOf(six) + 1));
    }

    public static void main(String[] args) {
        input = Input.fetchInput(2022,13);
        DayRunner.run(new Day13());
    }
}
