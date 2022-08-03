package advent.aoc2018;

import java.util.ArrayDeque;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;

public class Day09 implements IDay {

	String input = "426 players; last marble is worth 72058 points";
	
	@Override
	public String part1() {
		int players = Integer.parseInt(input.split(" ")[0]);
		int lastMarble = Integer.parseInt(input.split(" ")[6]);
		
		long[] scores = new long[players];
		
		CircleDeque<Integer> circle = new CircleDeque<Integer>();
		circle.addFirst(0);
		for(int i = 1; i < lastMarble; i++) {
			if(i % 23 == 0) {
				circle.rotate(-7);
				scores[i % players] += i + circle.pop();
			} else {
				circle.rotate(2);
				circle.addLast(i);
			}
		}
		long max = 0;
		for(long i : scores)
			if(i > max)
				max = i;
		return Long.toString(max);
	}

	@Override
	public String part2() {
		int players = Integer.parseInt(input.split(" ")[0]);
		int lastMarble = Integer.parseInt(input.split(" ")[6]);
		lastMarble *= 100;
		
		long[] scores = new long[players];
		CircleDeque<Integer> circle = new CircleDeque<Integer>();
		circle.addFirst(0);
		for(int i = 1; i < lastMarble; i++) {
			if(i % 23 == 0) {
				circle.rotate(-7);
				scores[i % players] += i + circle.pop();
			} else {
				circle.rotate(2);
				circle.addLast(i);
			}
		}
		long max = 0;
		for(long i : scores)
			if(i > max)
				max = i;
		return Long.toString(max);	
	}

	public static void main(String[] args) {
		DayRunner.run(new Day09());
	}

}

//helper circle structure
@SuppressWarnings("serial")
class CircleDeque<T> extends ArrayDeque<T> {
    void rotate(int num) {
        if (num == 0) return;
        if (num > 0) {
            for (int i = 0; i < num; i++) {
                T t = this.removeLast();
                this.addFirst(t);
            }
        } else {
            for (int i = 0; i < Math.abs(num) - 1; i++) {
                T t = this.remove();
                this.addLast(t);
            }
        }

    }
}