package advent.aoc2019;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;

public class Day01 implements IDay {

	static String input = "83568\r\n"
			+ "132382\r\n"
			+ "65095\r\n"
			+ "105082\r\n"
			+ "138042\r\n"
			+ "59055\r\n"
			+ "79113\r\n"
			+ "123950\r\n"
			+ "59773\r\n"
			+ "55031\r\n"
			+ "56499\r\n"
			+ "122835\r\n"
			+ "123608\r\n"
			+ "82848\r\n"
			+ "109981\r\n"
			+ "115633\r\n"
			+ "126241\r\n"
			+ "137240\r\n"
			+ "54983\r\n"
			+ "129523\r\n"
			+ "101517\r\n"
			+ "90879\r\n"
			+ "82446\r\n"
			+ "105897\r\n"
			+ "108653\r\n"
			+ "130530\r\n"
			+ "113607\r\n"
			+ "140338\r\n"
			+ "125646\r\n"
			+ "112605\r\n"
			+ "68080\r\n"
			+ "105466\r\n"
			+ "93462\r\n"
			+ "147116\r\n"
			+ "127370\r\n"
			+ "128362\r\n"
			+ "83129\r\n"
			+ "146946\r\n"
			+ "102658\r\n"
			+ "62824\r\n"
			+ "52950\r\n"
			+ "119301\r\n"
			+ "61671\r\n"
			+ "92820\r\n"
			+ "139579\r\n"
			+ "93816\r\n"
			+ "148535\r\n"
			+ "77893\r\n"
			+ "80523\r\n"
			+ "69543\r\n"
			+ "51773\r\n"
			+ "144074\r\n"
			+ "100340\r\n"
			+ "64565\r\n"
			+ "68404\r\n"
			+ "88923\r\n"
			+ "144824\r\n"
			+ "87836\r\n"
			+ "51209\r\n"
			+ "99770\r\n"
			+ "111044\r\n"
			+ "144978\r\n"
			+ "56585\r\n"
			+ "137236\r\n"
			+ "73290\r\n"
			+ "86608\r\n"
			+ "72415\r\n"
			+ "57783\r\n"
			+ "130619\r\n"
			+ "109599\r\n"
			+ "59655\r\n"
			+ "99708\r\n"
			+ "118488\r\n"
			+ "104989\r\n"
			+ "93812\r\n"
			+ "135899\r\n"
			+ "110396\r\n"
			+ "89346\r\n"
			+ "119482\r\n"
			+ "67292\r\n"
			+ "143810\r\n"
			+ "64085\r\n"
			+ "104169\r\n"
			+ "145618\r\n"
			+ "104035\r\n"
			+ "75765\r\n"
			+ "88638\r\n"
			+ "139325\r\n"
			+ "89099\r\n"
			+ "132807\r\n"
			+ "117255\r\n"
			+ "98029\r\n"
			+ "114780\r\n"
			+ "104708\r\n"
			+ "100671\r\n"
			+ "98052\r\n"
			+ "141263\r\n"
			+ "149844\r\n"
			+ "117643\r\n"
			+ "123410";
	static String input2 = "";
	
	public static void main(String[] args) {
		DayRunner.run(new Day01());
	}

	@Override
	public String part1() {
		//simple - for each line of input, calculate fuel necessary and add it to the total
		int total = 0;
		for(String s : input.split("\r\n")) {
			total += (Integer.parseInt(s) / 3) - 2;
		}
		return Integer.toString(total);
	}

	@Override
	public String part2() {
		int total = 0;
		for(String s : input.split("\r\n")) {
			//calculate starting fuel value
			int fuel = (Integer.parseInt(s) / 3) - 2;
		
			//until fuel drops below zero, add current value to total, then update fuel value
			while(fuel > 0) {
				total += fuel;
				fuel = (fuel / 3) - 2;
			}
		}
		return Integer.toString(total);
	}

}
