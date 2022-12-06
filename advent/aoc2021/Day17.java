package advent.aoc2021;

import advent.utilities.general.Coord;
import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

public class Day17 implements IDay {

	static String input;
	
	static Coord rangeStart,rangeEnd;

	@Override
	public String part1() {
		String[] parts = input.split(", ");
		int startX = Integer.parseInt(parts[0].substring(parts[0].indexOf('=') + 1, parts[0].indexOf('.')));
		int endX = Integer.parseInt(parts[0].substring(parts[0].lastIndexOf('.') + 1));
		int startY = Integer.parseInt(parts[1].substring(parts[1].indexOf('=') + 1, parts[1].indexOf('.')));
		int endY = Integer.parseInt(parts[1].substring(parts[1].lastIndexOf('.') + 1));
		rangeStart = new Coord(startX,startY);
		rangeEnd = new Coord(endX,endY);
		
		int highestY = 0;
		//minimum y velocity is bottom bounding coord - 1
		
		for(int yVel = rangeEnd.y - 1; yVel < Math.abs(rangeStart.y); yVel++) {
			for(int xVel = 0; xVel < rangeEnd.x + 1; xVel++) {
				int inHighestY = 0;
				Coord vel = new Coord(xVel,yVel);
				Coord pos = new Coord(0,0);
				while(!past(pos)) {
					pos.sumSelf(vel);
					if(pos.y > inHighestY)
						inHighestY = pos.y;
					if(inBox(pos)) {
						if(inHighestY > highestY)
							highestY = inHighestY;
						break;
					}
					vel.x += Math.signum(vel.x) * -1;
					vel.y--;
				}
				
			}
		}
		
		return Integer.toString(highestY);
	}

	@Override
	public String part2() {
		String[] parts = input.split(", ");
		int startX = Integer.parseInt(parts[0].substring(parts[0].indexOf('=') + 1, parts[0].indexOf('.')));
		int endX = Integer.parseInt(parts[0].substring(parts[0].lastIndexOf('.') + 1));
		int startY = Integer.parseInt(parts[1].substring(parts[1].indexOf('=') + 1, parts[1].indexOf('.')));
		int endY = Integer.parseInt(parts[1].substring(parts[1].lastIndexOf('.') + 1));
		rangeStart = new Coord(startX,startY);
		rangeEnd = new Coord(endX,endY);
		
		int yMax = Math.max(Math.abs(startY), Math.abs(endY));
		
		int successCount = 0;
		//minimum y velocity is bottom bounding coord - 1
		
		for(int yVel = -yMax; yVel < yMax + 1; yVel++) {
			for(int xVel = 0; xVel <= rangeEnd.x + 1; xVel++) {
				Coord vel = new Coord(xVel,yVel);
				Coord pos = new Coord(0,0);
				while(!past(pos)) {
					pos.sumSelf(vel);
					if(inBox(pos)) {
						successCount++;
						break;
					}
					vel.x += Math.signum(vel.x) * -1;
					vel.y--;
				}
				
			}
		}
		
		return Integer.toString(successCount);
	}

	public boolean inBox(Coord c) {
		return c.x >= rangeStart.x && c.x <= rangeEnd.x && c.y >= rangeStart.y && c.y <= rangeEnd.y;
	}

	public boolean past(Coord c) {
		return c.x > rangeEnd.x || c.y < rangeStart.y;
	}

	public static void main(String[] args) {
		input = Input.fetchInput(2021,17).trim();
		DayRunner.run(new Day17());
	}
}
