package advent.utilities.general;

public class DayRunner {
	
	//runner method - times and executes each part of inputted day
	public static void run(IDay d) {
		long part1Start = System.currentTimeMillis();
		String part1 = d.part1();
		if(part1 == null) part1 = "Part 1 has not yet been implemented.";
		System.out.println("Part 1 Solution: "+ part1);
		long part1End = System.currentTimeMillis();
		System.out.println("Part 1 Runtime: " + ((part1End - part1Start)/1000.0) + " seconds");
		long part2Start = System.currentTimeMillis();
		String part2 = d.part2();
		if(part2 == null) part2 = "Part 2 has not yet been implemented.";
		System.out.println("Part 2 Solution: " + part2);
		long part2End = System.currentTimeMillis();
		System.out.println("Part 2 Runtime: " + ((part2End - part2Start)/1000.0) + " seconds");
	}
	
	//part = 1 or 2, or 0 for both
	public static void average(IDay d, int part, int iters) {
		long total = 0;
		for(int i = 0; i < iters; i++) {
			long start = System.currentTimeMillis();
			if(part == 0) {
				d.part1();
				d.part2();
			} else if(part  == 1) {
				d.part1();
			} else {
				d.part2();
			}
			long end = System.currentTimeMillis();
			total += (end - start);
		}
		System.out.println("Average runtime over " + iters + " iterations: " + ((total / (long) iters) / 1000.0) + " seconds.");
	}
	
}
