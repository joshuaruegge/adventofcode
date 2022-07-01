package advent.aoc2019;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.utils2019.Order;
import advent.utilities.utils2019.Recipe;

public class Day14 implements IDay {

	static String input = "11 RVCS => 8 CBMDT\r\n"
			+ "29 QXPB, 8 QRGRH => 8 LGMKD\r\n"
			+ "3 VPRVD => 6 PMFZG\r\n"
			+ "1 CNWNQ, 11 MJVXS => 6 SPLM\r\n"
			+ "13 SPDRZ, 13 PMFZG => 2 BLFM\r\n"
			+ "8 QWPFN => 7 LWVB\r\n"
			+ "1 SPLM => 8 TKWQ\r\n"
			+ "2 QRGRH, 6 CNWNQ => 7 DTZW\r\n"
			+ "2 DMLT, 1 SPLM, 1 TMDK => 9 NKNS\r\n"
			+ "1 MJVXS, 1 HLBV => 7 PQCQH\r\n"
			+ "1 JZHZP, 9 LWVB => 7 MJSCQ\r\n"
			+ "29 DGFR => 7 QRGRH\r\n"
			+ "14 XFLKQ, 2 NKNS, 4 KMNJF, 3 MLZGQ, 7 TKWQ, 24 WTDW, 11 CBMDT => 4 GJKX\r\n"
			+ "4 TKWQ, 1 WLCFR => 4 PDKGT\r\n"
			+ "2 NKNS => 4 GDKL\r\n"
			+ "4 WRZST => 9 XFLKQ\r\n"
			+ "19 DGFR => 4 VPRVD\r\n"
			+ "10 MJSCQ, 4 QWPFN, 4 QXPB => 2 MLZGQ\r\n"
			+ "1 JZHZP => 7 QWPFN\r\n"
			+ "1 XFLKQ => 9 FQGVL\r\n"
			+ "3 GQGXC => 9 VHGP\r\n"
			+ "3 NQZTV, 1 JZHZP => 2 NVZWL\r\n"
			+ "38 WLCFR, 15 GJKX, 44 LGMKD, 2 CBVXG, 2 GDKL, 77 FQGVL, 10 MKRCZ, 29 WJQD, 33 BWXGC, 19 PQCQH, 24 BKXD => 1 FUEL\r\n"
			+ "102 ORE => 5 DGFR\r\n"
			+ "17 NWKLB, 1 SBPLK => 5 HRQM\r\n"
			+ "3 BWXGC => 8 TQDP\r\n"
			+ "1 TQDP => 2 PSZDZ\r\n"
			+ "2 MJVXS => 9 WNXG\r\n"
			+ "2 NBTW, 1 HRQM => 2 SVHBH\r\n"
			+ "8 CNWNQ, 1 DTZW => 4 RVCS\r\n"
			+ "4 VHGP, 20 WNXG, 2 SVHBH => 3 SPDRZ\r\n"
			+ "110 ORE => 5 TXMC\r\n"
			+ "10 QRGRH => 5 NWKLB\r\n"
			+ "1 SBPLK => 3 MJVXS\r\n"
			+ "9 DGFR => 5 RFSRL\r\n"
			+ "5 LBTV => 3 DMLT\r\n"
			+ "1 NWKLB, 1 KMNJF, 1 HDQXB, 6 LBTV, 2 PSZDZ, 34 PMFZG, 2 SVHBH => 2 WJQD\r\n"
			+ "1 RVCS => 5 MKRCZ\r\n"
			+ "14 NQZTV, 3 FPLT, 1 SJMS => 2 GQGXC\r\n"
			+ "18 RFSRL, 13 VHGP, 23 NBTW => 5 WTDW\r\n"
			+ "1 VHGP, 6 TKWQ => 7 QXPB\r\n"
			+ "1 JZHZP, 1 CNWNQ => 5 KMNJF\r\n"
			+ "109 ORE => 9 BWXGC\r\n"
			+ "2 CNWNQ, 1 PDKGT, 2 KMNJF => 5 HDQXB\r\n"
			+ "1 PDKGT, 18 WRZST, 9 MJSCQ, 3 VHGP, 1 BLFM, 1 LGMKD, 7 WLCFR => 2 BKXD\r\n"
			+ "11 MLJK => 6 FPLT\r\n"
			+ "8 DGFR, 2 TXMC, 3 WJRC => 9 SJMS\r\n"
			+ "2 SBPLK => 1 LBTV\r\n"
			+ "22 QWPFN => 4 WRZST\r\n"
			+ "5 WRZST, 22 WNXG, 1 VHGP => 7 NBTW\r\n"
			+ "7 RVCS => 9 TMDK\r\n"
			+ "1 DGFR, 14 TXMC => 5 JZHZP\r\n"
			+ "2 JZHZP => 3 SBPLK\r\n"
			+ "19 PDKGT => 8 HLBV\r\n"
			+ "195 ORE => 6 WJRC\r\n"
			+ "6 GQGXC => 8 CNWNQ\r\n"
			+ "1 NVZWL, 4 GQGXC => 2 CBVXG\r\n"
			+ "1 NVZWL, 1 KMNJF => 8 WLCFR\r\n"
			+ "153 ORE => 4 MLJK\r\n"
			+ "1 BWXGC => 6 NQZTV";
	static String input2 = "171 ORE => 8 CNZTR\r\n"
			+ "7 ZLQW, 3 BMBT, 9 XCVML, 26 XMNCP, 1 WPTQ, 2 MZWV, 1 RJRHP => 4 PLWSL\r\n"
			+ "114 ORE => 4 BHXH\r\n"
			+ "14 VRPVC => 6 BMBT\r\n"
			+ "6 BHXH, 18 KTJDG, 12 WPTQ, 7 PLWSL, 31 FHTLT, 37 ZDVW => 1 FUEL\r\n"
			+ "6 WPTQ, 2 BMBT, 8 ZLQW, 18 KTJDG, 1 XMNCP, 6 MZWV, 1 RJRHP => 6 FHTLT\r\n"
			+ "15 XDBXC, 2 LTCX, 1 VRPVC => 6 ZLQW\r\n"
			+ "13 WPTQ, 10 LTCX, 3 RJRHP, 14 XMNCP, 2 MZWV, 1 ZLQW => 1 ZDVW\r\n"
			+ "5 BMBT => 4 WPTQ\r\n"
			+ "189 ORE => 9 KTJDG\r\n"
			+ "1 MZWV, 17 XDBXC, 3 XCVML => 2 XMNCP\r\n"
			+ "12 VRPVC, 27 CNZTR => 2 XDBXC\r\n"
			+ "15 KTJDG, 12 BHXH => 5 XCVML\r\n"
			+ "3 BHXH, 2 VRPVC => 7 MZWV\r\n"
			+ "121 ORE => 7 VRPVC\r\n"
			+ "7 XCVML => 6 RJRHP\r\n"
			+ "5 BHXH, 4 VRPVC => 5 LTCX";
	
	public static void main(String[] args) {
		DayRunner.run(new Day14());
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



	@Override
	public String part1() {
		Scanner scan = new Scanner(input);
		//a Recipe consists of an Order for the result, and an ArrayList<Order> of ingredients
		//an Order object is essentially a pair of a name string and an int quantity
		ArrayList<Recipe> recipes = new ArrayList<Recipe>();
		while(scan.hasNextLine()) {
			String[] lineParts = scan.nextLine().split(" => ");
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
		Scanner scan = new Scanner(input);
		//a Recipe consists of an Order for the result, and an ArrayList<Order> of ingredients
		//an Order object is essentially a pair of a name string and an int quantity
		ArrayList<Recipe> recipes = new ArrayList<Recipe>();
		while(scan.hasNextLine()) {
			String[] lineParts = scan.nextLine().split(" => ");
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

}
