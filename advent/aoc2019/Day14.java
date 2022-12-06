package advent.aoc2019;

import java.util.ArrayList;
import java.util.HashMap;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;
import advent.utilities.utils2019.Order;
import advent.utilities.utils2019.Recipe;

public class Day14 implements IDay {

	static String input;

	@Override
	public String part1() {
		//a Recipe consists of an Order for the result, and an ArrayList<Order> of ingredients
		//an Order object is essentially a pair of a name string and an int quantity
		ArrayList<Recipe> recipes = new ArrayList<Recipe>();
		for(String s : input.split("\n")) {
			String[] lineParts = s.split(" => ");
			//make result of recipe
			String[] resultParts = lineParts[1].split(" ");
			Order result = new Order(resultParts[1],Integer.parseInt(resultParts[0]));
			String[] ingredients = lineParts[0].split(", ");
			ArrayList<Order> ings = new ArrayList<Order>();
			for(String i : ingredients) {
				String[] iParts = i.split(" ");
				ings.add(new Order(iParts[1],Integer.parseInt(iParts[0])));
			}
			recipes.add(new Recipe(result,ings));
		}
		//basically, we're going to start with an order for fuel, then
		//replace orders over time with their recipes until we've gone
		//all the way down to total ore costs	
		
		//keep track of excess products produced to reuse later
		HashMap<String,Integer> excess = new HashMap<String,Integer>();
		//order queue
		ArrayList<Order> orders = new ArrayList<Order>();
		orders.add(new Order("FUEL",1));
		int oreCost = 0;
		while(orders.size() > 0) {
			Order o = orders.remove(0);
			
			if(o.name.equals("ORE")) {
				//increase ore total
				oreCost += o.amount;
			} else if(excess.containsKey(o.name) && o.amount <= excess.get(o.name)) {
				//use up excess, put resulting value back
				excess.put(o.name, excess.get(o.name) - o.amount);
			} else {
				int amt = o.amount;
				//reduce by using up any excess
				if(excess.containsKey(o.name)) {
					amt -= excess.get(o.name);
					excess.remove(o.name);
				}
				Recipe r = find(recipes,o.name);
				//round upwards for number of times we'll have to do recipe
				int numCrafts = (int) Math.ceil((double) amt / r.product.amount);
				
				//add orders for ingredients of recipe
				for(Order i : r.ingredients) {
					//append to existing order, or creat new 
					if(findOrder(orders,i.name) != null) {
						findOrder(orders,i.name).amount += i.amount * numCrafts;
					} else {
						orders.add(new Order(i.name, i.amount * numCrafts));
					}
				}
				//determine number left over after using up amount necessary
				int leftover = numCrafts * r.product.amount - amt;
				//add excess to list
				if(leftover != 0) {
					excess.put(r.product.name, excess.getOrDefault(r.product.name,0) + leftover);
				}
				
			}
			
		}
		return Integer.toString(oreCost);
	}

	@Override
	public String part2() {
		//a Recipe consists of an Order for the result, and an ArrayList<Order> of ingredients
		//an Order object is essentially a pair of a name string and an int quantity
		ArrayList<Recipe> recipes = new ArrayList<Recipe>();
		for(String s : input.split("\n")) {
			String[] lineParts = s.split(" => ");
			//make result of recipe
			String[] resultParts = lineParts[1].split(" ");
			Order result = new Order(resultParts[1],Integer.parseInt(resultParts[0]));
			String[] ingredients = lineParts[0].split(", ");
			ArrayList<Order> ings = new ArrayList<Order>();
			for(String i : ingredients) {
				String[] iParts = i.split(" ");
				ings.add(new Order(iParts[1],Integer.parseInt(iParts[0])));
			}
			recipes.add(new Recipe(result,ings));
		}
		
		//this time, we just keep our excess products list open across each iteration
		//to continue using up excesses as we produce the large amount of fuel
		
		//keep track of excess products produced to reuse later
		HashMap<String,Integer> excess = new HashMap<String,Integer>();
		//order queue
		ArrayList<Order> orders = new ArrayList<Order>();
		//start with total number of ore
		long totalOre = 1000000000000l;
		int fuelProduced = 0;
		//this is an arbitrary constant based on my part 1 answer.
		//replace the first term with something near your part 1 answer
		while(totalOre > (200000 * 10000)) {
			//increment fuel in batches of 10000 to save time
			orders.add(new Order("FUEL",10000));
			//same order parsing loop as above
			while(orders.size() > 0) {
				Order o = orders.remove(0);
				
				if(o.name.equals("ORE")) {
					totalOre -= o.amount;
				} else if(excess.containsKey(o.name) && o.amount <= excess.get(o.name)) {
					excess.put(o.name, excess.get(o.name) - o.amount);
				} else {
					int amt = o.amount;
					if(excess.containsKey(o.name)) {
						amt -= excess.get(o.name);
						excess.remove(o.name);
					}
					Recipe r = find(recipes,o.name);
					int numCrafts = (int) Math.ceil((double) amt / r.product.amount);
					
					for(Order i : r.ingredients) {
						if(findOrder(orders,i.name) != null) {
							findOrder(orders,i.name).amount += i.amount * numCrafts;
						} else {
							orders.add(new Order(i.name, i.amount * numCrafts));
						}
					}
					int leftover = numCrafts * r.product.amount - amt;
					if(leftover != 0) {
						excess.put(r.product.name, excess.getOrDefault(r.product.name,0) + leftover);
					}
					
				}
			}
			fuelProduced += 10000;
		}

		//now, produce fuel one-by-one until we've used up the last scraps of ore
		while(totalOre > 0) {
			orders.add(new Order("FUEL",1));
			while(orders.size() > 0) {
				Order o = orders.remove(0);
				
				if(o.name.equals("ORE")) {
					totalOre -= o.amount;
				} else if(excess.containsKey(o.name) && o.amount <= excess.get(o.name)) {
					excess.put(o.name, excess.get(o.name) - o.amount);
				} else {
					int amt = o.amount;
					if(excess.containsKey(o.name)) {
						amt -= excess.get(o.name);
						excess.remove(o.name);
					}
					Recipe r = find(recipes,o.name);
					int numCrafts = (int) Math.ceil((double) amt / r.product.amount);
					
					for(Order i : r.ingredients) {
						if(findOrder(orders,i.name) != null) {
							findOrder(orders,i.name).amount += i.amount * numCrafts;
						} else {
							orders.add(new Order(i.name, i.amount * numCrafts));
						}
					}
					int leftover = numCrafts * r.product.amount - amt;
					if(leftover != 0) {
						excess.put(r.product.name, excess.getOrDefault(r.product.name,0) + leftover);
					}
					
				}
			}
			if(totalOre > 0) {
				fuelProduced++;
			}
		}
		return Integer.toString(fuelProduced);
	}

	//locates order with name s in list a
	public static Order findOrder(ArrayList<Order> a, String s) {
		for(Order o : a) {
			if(o.name.equals(s))
				return o;
		}
		return null;
	}
	
	//locates recipe with product s in list a
	public static Recipe find(ArrayList<Recipe> a, String s) {
		for(Recipe r : a) {
			if(r.product.name.equals(s))
				return r;
		}
		return null;
	}

	public static void main(String[] args) {
		input = Input.fetchInput(2019,14);
		DayRunner.run(new Day14());
	}
}
