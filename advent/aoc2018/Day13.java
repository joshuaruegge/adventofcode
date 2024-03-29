package advent.aoc2018;

import java.util.ArrayList;
import java.util.Collections;

import advent.utilities.general.Coord;
import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;
import advent.utilities.utils2018.Cart;

public class Day13 implements IDay {

	static String input;
	
	@Override
	public String part1() {
		//turn input into string arraylist so x,y can be accessed by get(y).charAt(x)
		ArrayList<String> map = new ArrayList<String>();
		for(String s : input.split("\n"))
			map.add(s);
		
		ArrayList<Cart> carts = new ArrayList<Cart>();
		
		for(int y = 0; y < map.size(); y++) {
			String line = map.get(y);
			for(int x = 0; x < line.length(); x++) {
				char c = line.charAt(x);
				if(c != '\\' && c != '/' && c != ' ' && c != '|' && c != '-' && c != '+') {
					Coord cartPos = new Coord(x,y);
					Coord facing = null;
					switch(c) {
					case '^':
						facing = Coord.UP;
						break;
					case 'v':
						facing = Coord.DOWN;
						break;
					case '>':
						facing = Coord.RIGHT;
						break;
					case '<':
						facing = Coord.LEFT;
						break;
					}
					carts.add(new Cart(cartPos,facing));
				}
			}
		}
		while(true) {
			Collections.sort(carts);
			for(Cart cart : carts) {
				cart.pos.sumSelf(cart.facing);
				//check if facing needs to be updated
				char cur = map.get(cart.pos.y).charAt(cart.pos.x);
				switch(cur) {
				case '\\':
					//turn left if up or down, otherwise turn right
					if(cart.facing.y != 0)
						cart.facing = cart.facing.left();
					else
						cart.facing = cart.facing.right();
					break;
				case '/':
					//opposite - right if up or down, left otherwise
					if(cart.facing.y != 0)
						cart.facing = cart.facing.right();
					else
						cart.facing = cart.facing.left();
					break;
				case '+':
					switch(cart.turnCount % 3) {
					case 0:
						cart.facing = cart.facing.left();
						break;
					case 1:
						//facing unchanged
						break;
					case 2:
						cart.facing = cart.facing.right();
						break;
					}
					cart.turnCount++;
				}
				//check for collision
				for(int i = 0; i < carts.size(); i++) {
					Cart c = carts.get(i);
					for(int j = i+1; j < carts.size(); j++) {
						if(c.pos.equals(carts.get(j).pos)) {
							//collision took place - return location
							String output = c.pos.toString();
							return output.substring(1,output.length() - 1);
						}
					}
				}
			}
		}
	}

	@Override
	public String part2() {
		//turn input into string arraylist so x,y can be accessed by get(y).charAt(x)
		ArrayList<String> map = new ArrayList<String>();
		for(String s : input.split("\n"))
			map.add(s);
		
		ArrayList<Cart> carts = new ArrayList<Cart>();
		
		for(int y = 0; y < map.size(); y++) {
			String line = map.get(y);
			for(int x = 0; x < line.length(); x++) {
				char c = line.charAt(x);
				if(c != '\\' && c != '/' && c != ' ' && c != '|' && c != '-' && c != '+') {
					Coord cartPos = new Coord(x,y);
					Coord facing = null;
					switch(c) {
					case '^':
						facing = new Coord(0,-1);
						break;
					case 'v':
						facing = new Coord(0,1);
						break;
					case '>':
						facing = new Coord(1,0);
						break;
					case '<':
						facing = new Coord(-1,0);
						break;
					}
					carts.add(new Cart(cartPos,facing));
				}
			}
		}
		while(carts.size() > 1) {
			ArrayList<Cart> removals = new ArrayList<Cart>();
			Collections.sort(carts);
			for(Cart cart : carts) {
				cart.pos.sumSelf(cart.facing);
				//check if facing needs to be updated
				char cur = map.get(cart.pos.y).charAt(cart.pos.x);
				switch(cur) {
				case '\\':
					//turn left if up or down, otherwise turn right
					if(cart.facing.y != 0)
						cart.facing = cart.facing.left();
					else
						cart.facing = cart.facing.right();
					break;
				case '/':
					//opposite - right if up or down, left otherwise
					if(cart.facing.y != 0)
						cart.facing = cart.facing.right();
					else
						cart.facing = cart.facing.left();
					break;
				case '+':
					switch(cart.turnCount % 3) {
					case 0:
						cart.facing = cart.facing.left();
						break;
					case 1:
						//facing unchanged
						break;
					case 2:
						cart.facing = cart.facing.right();
						break;
					}
					cart.turnCount++;
				}
				//check for collision
				for(int i = 0; i < carts.size(); i++) {
					Cart c = carts.get(i);
					for(int j = i+1; j < carts.size(); j++) {
						if(c.pos.equals(carts.get(j).pos)) {
							//mark both for removal
							removals.add(c);
							removals.add(carts.get(j));
						}
					}
				}
			}
			carts.removeAll(removals);
		}
		String output = carts.get(0).pos.toString();
		return output.substring(1,output.length() - 1);
	}

	public static void main(String[] args) {
		input = Input.fetchInput(2018,13);
		DayRunner.run(new Day13());
	}

}
