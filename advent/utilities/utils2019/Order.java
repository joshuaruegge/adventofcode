package advent.utilities.utils2019;

public class Order {

	public String name;
	public int amount;
	
	public Order() {
		name = "";
		amount = 0;
	}
	
	public Order(String s, int a) {
		name = s;
		amount = a;
	}
	
	public String toString() {
		return amount + " " + name;
	}
}
