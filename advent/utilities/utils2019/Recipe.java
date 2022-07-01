package advent.utilities.utils2019;

import java.util.ArrayList;

public class Recipe {

	public Order product;
	public ArrayList<Order> ingredients;
	
	public Recipe() {
		product = new Order();
		ingredients = new ArrayList<Order>();
	}
	
	public Recipe(Order p) {
		product = p;
		ingredients = new ArrayList<Order>();
	}
	
	public Recipe(Order p, ArrayList<Order> a) {
		product = p;
		ingredients = new ArrayList<Order>(a);
	}
	
	public String toString() {
		return ingredients + " => " + product;
	}
}
