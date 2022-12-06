package advent.aoc2021;

import java.util.HashSet;

import advent.utilities.general.Coord;
import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

public class Day20 implements IDay {

	static String input;
	
	@Override
	public String part1() {
		String[] chunks = input.split("\n\n");
		HashSet<Integer> ons = new HashSet<Integer>();
		for(int c = 0; c < chunks[0].length(); c++)
			if(chunks[0].charAt(c) == '#')
				ons.add(c);
		
		HashSet<Coord> pixels = new HashSet<Coord>();
		String[] lines = chunks[1].split("\n");
		for(int i = 0; i < lines.length; i++) {
			String line = lines[i];
			for(int j = 0; j < line.length(); j++) {
				if(line.charAt(j) == '#')
					pixels.add(new Coord(i,j));
			}
		}
		
		//because of the enhancement algorithm, empty tiles outside the image space swap from all off to all on and vice versa each iteration
		for(int iter = 0; iter < 2; iter++) {
			HashSet<Coord> newPixels = new HashSet<Coord>();
			int minX = pixels.stream().map(x -> x.x).min(Integer::compare).get();
			int maxX = pixels.stream().map(x -> x.x).max(Integer::compare).get();
			int minY = pixels.stream().map(x -> x.y).min(Integer::compare).get();
			int maxY = pixels.stream().map(x -> x.y).max(Integer::compare).get();
			
			for(int x = minX - 1; x <= maxX + 1; x++) {
				for(int y = minY - 1; y <= maxY + 1; y++) {
					Coord cur = new Coord(x,y);
					
					String num = "";
					
					for(int xOff = x - 1; xOff < x + 2; xOff++) {
						for(int yOff = y - 1; yOff < y+2; yOff++) {
							Coord c = new Coord(xOff,yOff);
							if(pixels.contains(c)) {
								num += "1";
							} else if (c.x < minX || c.x > maxX || c.y < minY || c.y > maxY){
								num += iter % 2;
							} else {
								num += "0";
							}
						}
					}
		
					if(ons.contains(Integer.parseInt(num,2))) {
						newPixels.add(cur);
					}
				}
			}
			pixels = newPixels;
		}
			
		return Integer.toString(pixels.size());
	}

	@Override
	public String part2() {
		String[] chunks = input.split("\n\n");
		HashSet<Integer> ons = new HashSet<Integer>();
		for(int c = 0; c < chunks[0].length(); c++)
			if(chunks[0].charAt(c) == '#')
				ons.add(c);
		
		HashSet<Coord> pixels = new HashSet<Coord>();
		String[] lines = chunks[1].split("\n");
		for(int i = 0; i < lines.length; i++) {
			String line = lines[i];
			for(int j = 0; j < line.length(); j++) {
				if(line.charAt(j) == '#')
					pixels.add(new Coord(i,j));
			}
		}
		
		//because of the enhancement algorithm, empty tiles outside the image space swap from all off to all on and vice versa each iteration
		for(int iter = 0; iter < 50; iter++) {
			HashSet<Coord> newPixels = new HashSet<Coord>();
			int minX = pixels.stream().map(x -> x.x).min(Integer::compare).get();
			int maxX = pixels.stream().map(x -> x.x).max(Integer::compare).get();
			int minY = pixels.stream().map(x -> x.y).min(Integer::compare).get();
			int maxY = pixels.stream().map(x -> x.y).max(Integer::compare).get();
			
			for(int x = minX - 1; x <= maxX + 1; x++) {
				for(int y = minY - 1; y <= maxY + 1; y++) {
					Coord cur = new Coord(x,y);
					
					String num = "";
					
					for(int xOff = x - 1; xOff < x + 2; xOff++) {
						for(int yOff = y - 1; yOff < y+2; yOff++) {
							Coord c = new Coord(xOff,yOff);
							if(pixels.contains(c)) {
								num += "1";
							} else if (c.x < minX || c.x > maxX || c.y < minY || c.y > maxY){
								num += iter % 2;
							} else {
								num += "0";
							}
						}
					}
		
					if(ons.contains(Integer.parseInt(num,2))) {
						newPixels.add(cur);
					}
				}
			}
			
			pixels = newPixels;
		}
		
		return Integer.toString(pixels.size());
	}

	public static void main(String[] args) {
		input = Input.fetchInput(2021,20);
		DayRunner.run(new Day20());
	}
}
