package advent.utilities.utils2020;

import java.util.HashSet;
import java.util.Objects;

public class Rule{
	
	public static Rule[] rules;
	
	public String rule;
	public HashSet<String> possibilities;
	
	public Rule(String rule) {
		this.rule = rule;
		possibilities = new HashSet<String>();
	}
	
	public HashSet<String> possibilities() {
		if(possibilities.size() > 0) {
			return possibilities;
		} else {
			//construct possibilities from rule string
			if(rule.length() == 3) {
				//direct-assignment rules are always single-character 
				possibilities.add(rule.substring(1,2));
				return possibilities;
			} else {
				String[] options = rule.split(" \\| ");
				//add all possibilities for each option
				for(String option : options) {
					//single option is always made up of one or two rule numbers
					String[] nums = option.split(" ");
					//if one, parse and return directly
					if(nums.length == 1) {
						possibilities.addAll(rules[Integer.parseInt(option)].possibilities());
						continue;
					}
					
					//otherwise, do both subrules
					int num1 = Integer.parseInt(nums[0]);
					int num2 = Integer.parseInt(nums[1]);
					
					
					//create all possible combinations of possibilities of each subrule
					for(String a : rules[num1].possibilities()) {
						for(String b : rules[num2].possibilities()) {
							possibilities.add(a.concat(b));
						}
					}
				}
				return possibilities;
			}
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(possibilities, rule);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Rule other = (Rule) obj;
		return Objects.equals(possibilities, other.possibilities) && Objects.equals(rule, other.rule);
	}

	@Override
	public String toString() {
		return "Rule [rule=" + rule + ", possibilities=" + possibilities + "]";
	}
}
