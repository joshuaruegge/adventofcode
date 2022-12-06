package advent.aoc2020;

import java.util.ArrayList;
import java.util.HashSet;

import advent.utilities.general.Coord3;
import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

public class Day24 implements IDay {

	static String input;
	
	@Override
	public String part1() {
		//just like 2017 day 11, huge shoutout to redblobgames for their simple(ish) breakdown of hexagonal coordinates
		//https://www.redblobgames.com/grids/hexagons/
		
		//as mentioned in link, hexagonal coordinates can be stored in a three-variable format
		//so, keep a list of coord3s representing flipped tiles - if not in the list, is unflipped
		HashSet<Coord3> flipped = new HashSet<Coord3>();
		
		for(String line : input.split("\n")) {
			Coord3 pos = new Coord3(0,0,0);
			for(int i = 0; i < line.length(); i++) {
				char c = line.charAt(i);
				String move = c + "";
				//grab second half of move if necessary, and update index
				if(c == 'n' || c == 's') {
					i++;
					move += line.charAt(i);
				}
				
				switch(move) {
				case "e":
					pos.sumSelf(new Coord3(1,0,-1));
					break;
				case "w":
					pos.sumSelf(new Coord3(-1,0,1));
					break;
				case "ne":
					pos.sumSelf(new Coord3(1,-1,0));
					break;
				case "nw":
					pos.sumSelf(new Coord3(0,-1,1));
					break;
				case "se":
					pos.sumSelf(new Coord3(0,1,-1));
					break;
				case "sw":
					pos.sumSelf(new Coord3(-1,1,0));
					break;
				}
			}
			
			//try to add to flipped - if already present, "unflip" by removing
			if(!flipped.add(pos)) {
				flipped.remove(pos);
			}
		}
		
		return Integer.toString(flipped.size());
	}

	@Override
	public String part2() {
		HashSet<Coord3> flipped = new HashSet<Coord3>();
		
		for(String line : input.split("\n")) {
			Coord3 pos = new Coord3(0,0,0);
			for(int i = 0; i < line.length(); i++) {
				char c = line.charAt(i);
				String move = c + "";
				//grab second half of move if necessary, and update index
				if(c == 'n' || c == 's') {
					i++;
					move += line.charAt(i);
				}
				
				switch(move) {
				case "e":
					pos.sumSelf(new Coord3(1,0,-1));
					break;
				case "w":
					pos.sumSelf(new Coord3(-1,0,1));
					break;
				case "ne":
					pos.sumSelf(new Coord3(1,-1,0));
					break;
				case "nw":
					pos.sumSelf(new Coord3(0,-1,1));
					break;
				case "se":
					pos.sumSelf(new Coord3(0,1,-1));
					break;
				case "sw":
					pos.sumSelf(new Coord3(-1,1,0));
					break;
				}
			}
			
			//try to add to flipped - if already present, "unflip" by removing
			if(!flipped.add(pos)) {
				flipped.remove(pos);
			}
		}
		
		for(int day = 0; day < 100; day++) {
			HashSet<Coord3> newFlipped = new HashSet<Coord3>();
			
			//only white tiles that can flip are neighbors of black, so build list of unique neighbors
			HashSet<Coord3> uniqueUnflipped = new HashSet<Coord3>();
			
			//on pass through black tiles, both calculate new state and put neighbors in unique
			for(Coord3 cur : flipped) {
				//retain cur in new if 1 or 2 flipped neighbors
				int neighbor = 0;
				for(Coord3 c : hexAdjacent(cur)) {
					if(flipped.contains(c))
						neighbor++;
					else
						//if not in flipped, is a white tile that needs to be added to neighbors list
						uniqueUnflipped.add(c);
				}
				if(neighbor == 1 || neighbor == 2)
					newFlipped.add(cur);
			}
			
			for(Coord3 cur : uniqueUnflipped) {
				//flip if exactly 2 adjacent
				int neighbor = 0;
				for(Coord3 c : hexAdjacent(cur))
					if(flipped.contains(c))
						neighbor++;
				if(neighbor == 2)
					newFlipped.add(cur);
			}
			
			flipped = newFlipped;
		}
		
		return Integer.toString(flipped.size());
	}
	
	public ArrayList<Coord3> hexAdjacent(Coord3 middle) {
		ArrayList<Coord3> _return = new ArrayList<Coord3>();
		_return.add(middle.sum(new Coord3(0,-1,1)));
		_return.add(middle.sum(new Coord3(1,-1,0)));
		_return.add(middle.sum(new Coord3(1,0,-1)));
		_return.add(middle.sum(new Coord3(0,1,-1)));
		_return.add(middle.sum(new Coord3(-1,1,0)));
		_return.add(middle.sum(new Coord3(-1,0,1)));
		return _return;
	}

	public static void main(String[] args) {
		input = Input.fetchInput(2020,24);
		DayRunner.run(new Day24());
	}
}
