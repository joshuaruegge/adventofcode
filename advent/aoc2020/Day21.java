package advent.aoc2020;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

public class Day21 implements IDay {

	static String input;
	
	@Override
	public String part1() {
		//map allergens to list of ingredient sets by line
		HashMap<String,ArrayList<HashSet<String>>> allToIng = new HashMap<String,ArrayList<HashSet<String>>>();
		for(String line : input.split("\n")) {
			String[] parts = line.split(" \\(");
			String[] ingredients = parts[0].split(" ");
			String[] allergens = parts[1].split(", | |\\)");
			
			//first allergen is always "contains"
			for(int index = 1; index < allergens.length; index++) {
				ArrayList<HashSet<String>> potentialList = allToIng.getOrDefault(allergens[index], new ArrayList<HashSet<String>>());
				HashSet<String> potentialIngredients = new HashSet<String>();
				potentialIngredients.addAll(Arrays.asList(ingredients));
				potentialList.add(potentialIngredients);
				allToIng.put(allergens[index], potentialList);
			}
		}
		
		HashSet<String> allIngredients = new HashSet<String>();
		for(ArrayList<HashSet<String>> a : allToIng.values())
			for(HashSet<String> b : a)
				allIngredients.addAll(b);
		
		//map ingredients to allergens
		HashMap<String,String> allergenMap = new HashMap<String,String>();
		
		while(allToIng.size() > 0) {
			for(String allergen : allToIng.keySet()) {
				ArrayList<HashSet<String>> ingredientList = allToIng.get(allergen);
				HashSet<String> potentialIngredients = new HashSet<String>(ingredientList.get(0));
				//keep commons for each set in list
				for(int i = 1; i < ingredientList.size(); i++) {
					potentialIngredients.retainAll(ingredientList.get(i));
				}
				
				//if only one possible ingredient
				if(potentialIngredients.size() == 1) {
					String ingredient = potentialIngredients.stream().findFirst().get();
					
					allergenMap.put(ingredient, allergen);
					
					//remove ingredient as possibility from other maps
					for(ArrayList<HashSet<String>> a : allToIng.values())
						for(HashSet<String> b : a)
							b.remove(ingredient);
					
					//remove allergen from map
					allToIng.remove(allergen);
					
					//break to avoid pissing off concurrency
					break;
				}
			}
		}
		
		//remove confirmed allergens from all list
		allIngredients.removeAll(allergenMap.keySet());
		
		//iterate over words in input, count non-allergen ingredients
		int count = 0;
		for(String word : input.split("\n| \\(|, | |\\)"))
			if(allIngredients.contains(word))
				count++;
		
		return Integer.toString(count);
	}

	@Override
	public String part2() {
		//map allergens to list of ingredient sets by line
		HashMap<String,ArrayList<HashSet<String>>> allToIng = new HashMap<String,ArrayList<HashSet<String>>>();
		for(String line : input.split("\n")) {
			String[] parts = line.split(" \\(");
			String[] ingredients = parts[0].split(" ");
			String[] allergens = parts[1].split(", | |\\)");
			
			//first allergen is always "contains"
			for(int index = 1; index < allergens.length; index++) {
				ArrayList<HashSet<String>> potentialList = allToIng.getOrDefault(allergens[index], new ArrayList<HashSet<String>>());
				HashSet<String> potentialIngredients = new HashSet<String>();
				potentialIngredients.addAll(Arrays.asList(ingredients));
				potentialList.add(potentialIngredients);
				allToIng.put(allergens[index], potentialList);
			}
		}
		
		HashSet<String> allIngredients = new HashSet<String>();
		for(ArrayList<HashSet<String>> a : allToIng.values())
			for(HashSet<String> b : a)
				allIngredients.addAll(b);
		
		//map ingredients to allergens
		HashMap<String,String> allergenMap = new HashMap<String,String>();
		
		while(allToIng.size() > 0) {
			for(String allergen : allToIng.keySet()) {
				ArrayList<HashSet<String>> ingredientList = allToIng.get(allergen);
				HashSet<String> potentialIngredients = new HashSet<String>(ingredientList.get(0));
				//keep commons for each set in list
				for(int i = 1; i < ingredientList.size(); i++) {
					potentialIngredients.retainAll(ingredientList.get(i));
				}
				
				//if only one possible ingredient
				if(potentialIngredients.size() == 1) {
					String ingredient = potentialIngredients.stream().findFirst().get();
					
					allergenMap.put(ingredient, allergen);
					
					//remove ingredient as possibility from other maps
					for(ArrayList<HashSet<String>> a : allToIng.values())
						for(HashSet<String> b : a)
							b.remove(ingredient);
					
					//remove allergen from map
					allToIng.remove(allergen);
					
					//break to avoid pissing off concurrency
					break;
				}
			}
		}
		
		ArrayList<String> dangerousIngredients = new ArrayList<String>(allergenMap.keySet());
		
		//sort by alphabet of allergen
		Collections.sort(dangerousIngredients, new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				return allergenMap.get(o1).compareTo(allergenMap.get(o2));
			}
		
		});
		
		String ret = dangerousIngredients.get(0);
		for(int i = 1; i < dangerousIngredients.size(); i++)
			ret += "," + dangerousIngredients.get(i);
		
		return ret;
	}

	public static void main(String[] args) {
		input = Input.fetchInput(2020,21);
		DayRunner.run(new Day21());
	}
}
