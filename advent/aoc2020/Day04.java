package advent.aoc2020;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

public class Day04 implements IDay {

	static String input;
	
	@Override
	public String part1() {
		final String[] CATEGORIES = new String[] {"byr:", "iyr:", "eyr:", "hgt:", "hcl:", "ecl:", "pid:"};
		
		int valid = 0;
		lineLoop:
		for(String s : input.split("\n\n")) {
			for(String t : CATEGORIES)
				if(!s.contains(t))
					continue lineLoop;
			valid++;
		}
		return Integer.toString(valid);
	}

	@Override
	public String part2() {
		int valid = 0;
		
		List<String> ECL = Arrays.stream(new String[] {"amb", "blu", "brn", "gry", "grn", "hzl", "oth"}).toList();
		
		for(String s : input.split("\n\n")) {
			HashMap<String,String> cats = new HashMap<String,String>();
			for(String t : s.replace("\n", " ").split(" ")) {
				cats.put(t.split(":")[0], t.split(":")[1]);
			}
			
			int byr = Integer.parseInt(cats.getOrDefault("byr", "-1"));
			if(byr < 1920 || byr > 2002)
				continue;
			
			int iyr = Integer.parseInt(cats.getOrDefault("iyr", "-1"));
			if(iyr < 2010 || iyr > 2020)
				continue;
			
			int eyr = Integer.parseInt(cats.getOrDefault("eyr", "-1"));
			if(eyr < 2020 || eyr > 2030)
				continue;
			
			if(cats.get("hgt") != null) {
				String hgt = cats.get("hgt");
				if(hgt.contains("cm")) {
					int height = Integer.parseInt(hgt.substring(0,hgt.length() - 2));
					if(height < 150 || height > 193)
						continue;
				} else if (hgt.contains("in")){
					int height = Integer.parseInt(hgt.substring(0,hgt.length() - 2));
					if(height < 59 || height > 76)
						continue;
				} else {
					continue;
				}
			} else {
				continue;
			}
			
			if(!cats.getOrDefault("hcl", "").matches("^#[a-f0-9]{6}$"))
				continue;
			
			if(!ECL.contains(cats.getOrDefault("ecl", "")))
				continue;
			
			if(!cats.getOrDefault("pid", "").matches("^[0-9]{9}$"))
				continue;
			
			//if we didn't continue any time before now, passport is valid!
			valid++;
		}
		return Integer.toString(valid);
	}

	public static void main(String[] args) {
		input = Input.fetchInput(2020,4);
		DayRunner.run(new Day04());
	}
}
