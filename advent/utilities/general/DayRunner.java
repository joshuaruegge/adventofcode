package advent.utilities.general;

public class DayRunner {

    //runner method - times and executes each part of inputted day
    public static void run(IDay d) {
        //log time before starting part 1 solution
        long part1Start = System.currentTimeMillis();
        //run part 1 code, print answer
        String part1 = d.part1();
        if (part1 == null) part1 = "Part 1 has not yet been implemented.";
        //log time after starting and use difference to calculate runtime
        long part1End = System.currentTimeMillis();
        System.out.println("Part 1 Solution: " + part1);
        System.out.println("Part 1 Runtime: " + ((part1End - part1Start) / 1000.0) + " seconds");

        //same for part 2
        long part2Start = System.currentTimeMillis();
        String part2 = d.part2();
        if (part2 == null) part2 = "Part 2 has not yet been implemented.";
        long part2End = System.currentTimeMillis();
        System.out.println("Part 2 Solution: " + part2);
        System.out.println("Part 2 Runtime: " + ((part2End - part2Start) / 1000.0) + " seconds");
    }

}
