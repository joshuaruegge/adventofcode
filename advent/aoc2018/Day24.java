package advent.aoc2018;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.utils2018.Group;

public class Day24 implements IDay {

	String input = "Immune System:\r\n"
			+ "698 units each with 10286 hit points with an attack that does 133 fire damage at initiative 9\r\n"
			+ "6846 units each with 2773 hit points (weak to slashing, cold) with an attack that does 4 slashing damage at initiative 14\r\n"
			+ "105 units each with 6988 hit points (weak to bludgeoning; immune to radiation) with an attack that does 616 radiation damage at initiative 17\r\n"
			+ "5615 units each with 7914 hit points (weak to bludgeoning) with an attack that does 13 radiation damage at initiative 20\r\n"
			+ "1021 units each with 10433 hit points (weak to cold; immune to slashing, bludgeoning) with an attack that does 86 bludgeoning damage at initiative 12\r\n"
			+ "6099 units each with 11578 hit points with an attack that does 15 bludgeoning damage at initiative 13\r\n"
			+ "82 units each with 1930 hit points (weak to bludgeoning; immune to cold) with an attack that does 179 bludgeoning damage at initiative 5\r\n"
			+ "2223 units each with 9442 hit points (immune to bludgeoning) with an attack that does 38 cold damage at initiative 19\r\n"
			+ "140 units each with 7594 hit points (weak to radiation) with an attack that does 452 fire damage at initiative 8\r\n"
			+ "3057 units each with 3871 hit points (weak to bludgeoning) with an attack that does 11 radiation damage at initiative 16\r\n"
			+ "\r\n"
			+ "Infection:\r\n"
			+ "263 units each with 48098 hit points (immune to radiation; weak to slashing) with an attack that does 293 bludgeoning damage at initiative 2\r\n"
			+ "111 units each with 9893 hit points (immune to slashing) with an attack that does 171 fire damage at initiative 18\r\n"
			+ "2790 units each with 36205 hit points with an attack that does 25 cold damage at initiative 4\r\n"
			+ "3325 units each with 46479 hit points (weak to slashing) with an attack that does 27 radiation damage at initiative 1\r\n"
			+ "3593 units each with 6461 hit points (weak to fire, slashing) with an attack that does 3 radiation damage at initiative 15\r\n"
			+ "2925 units each with 13553 hit points (weak to cold, bludgeoning; immune to fire) with an attack that does 8 cold damage at initiative 10\r\n"
			+ "262 units each with 43260 hit points (weak to cold) with an attack that does 327 radiation damage at initiative 6\r\n"
			+ "4228 units each with 24924 hit points (weak to radiation, fire; immune to cold, bludgeoning) with an attack that does 11 cold damage at initiative 11\r\n"
			+ "689 units each with 42315 hit points (weak to cold, slashing) with an attack that does 116 fire damage at initiative 7\r\n"
			+ "2649 units each with 37977 hit points (weak to radiation) with an attack that does 24 cold damage at initiative 3";
	
	@Override
	public String part1() {
		ArrayList<String> attackTypes = new ArrayList<String>();
		ArrayList<Group> groups = new ArrayList<Group>();
		String[] sections = input.split("\r\n\r\n");
		String[] immuneLines = sections[0].split("\r\n");
		for(int immuneCount = 1; immuneCount < immuneLines.length; immuneCount++) {
			String line = immuneLines[immuneCount];
			String[] words = line.split(" ");
			int unitCount = Integer.parseInt(words[0]);
			int unitHp = Integer.parseInt(words[4]);
			HashSet<Integer> weak = new HashSet<Integer>();
			HashSet<Integer> immune = new HashSet<Integer>();
			//use an index pointer to parse immunities and weaknesses
			int index = 7;
			while(!words[index].equals("with")) {
				if(words[index].replaceAll("\\(|,|;|\\)","").equals("weak")) {
					index += 2;
					while(!words[index].equals("immune") && !words[index].equals("with")) {
						
						String type = words[index].replaceAll("\\(|,|;|\\)","");
						
						if(!attackTypes.contains(type))
							attackTypes.add(type);
						weak.add(attackTypes.indexOf(type));
						index++;
					}
				} else if(words[index].replaceAll("\\(|,|;|\\)", "").equals("immune")) {
					index += 2;
					while(!words[index].equals("weak") && !words[index].equals("with")) {
						String type = words[index].replaceAll("\\(|,|;|\\)","");
						if(!attackTypes.contains(type))
							attackTypes.add(type);
						immune.add(attackTypes.indexOf(type));
						index++;
					}
				}
			}
			int atkDamage = Integer.parseInt(words[index + 5]);
			String type = words[index+6];
			if(!attackTypes.contains(type))
				attackTypes.add(type);
			int atkType = attackTypes.indexOf(type);
			int initiative = Integer.parseInt(words[words.length - 1]);
			
			groups.add(new Group(false,unitHp, unitCount, weak, immune, atkDamage, atkType, initiative));
		}
		String[] infectionLines = sections[1].split("\r\n");
		for(int infectionCount = 1; infectionCount < infectionLines.length; infectionCount++) {
			String line = infectionLines[infectionCount];
			if(line.equals("Infection:"))
				continue;
			line = line.replace("\r\n ", " ");
			String[] words = line.split(" ");
			int unitCount = Integer.parseInt(words[0]);
			int unitHp = Integer.parseInt(words[4]);
			HashSet<Integer> weak = new HashSet<Integer>();
			HashSet<Integer> immune = new HashSet<Integer>();
			//use an index pointer to parse immunities and weaknesses
			int index = 7;
			while(!words[index].equals("with")) {
				if(words[index].replaceAll("\\(|,|;|\\)","").equals("weak")) {
					index += 2;
					while(!words[index].equals("immune") && !words[index].equals("with")) {
						
						String type = words[index].replaceAll("\\(|,|;|\\)","");
						
						if(!attackTypes.contains(type))
							attackTypes.add(type);
						weak.add(attackTypes.indexOf(type));
						index++;
					}
				} else if(words[index].replaceAll("\\(|,|;|\\)", "").equals("immune")) {
					index += 2;
					while(!words[index].equals("weak") && !words[index].equals("with")) {
						String type = words[index].replaceAll("\\(|,|;|\\)","");
						if(!attackTypes.contains(type))
							attackTypes.add(type);
						immune.add(attackTypes.indexOf(type));
						index++;
					}
				}
			}
			int atkDamage = Integer.parseInt(words[index + 5]);
			String type = words[index+6];
			if(!attackTypes.contains(type))
				attackTypes.add(type);
			int atkType = attackTypes.indexOf(type);
			int initiative = Integer.parseInt(words[words.length - 1]);
			
			groups.add(new Group(true, unitHp, unitCount, weak, immune, atkDamage, atkType, initiative));
		}
		
		while(containsBoth(groups)) {
			ArrayList<Group> potentialTargets = new ArrayList<Group>(groups);
			Collections.sort(groups, new Comparator<Group> () {

				@Override
				public int compare(Group o1, Group o2) {
					int effo2 = o2.unitCount * o2.atk;
					int effo1 = o1.unitCount * o1.atk;
					if(effo1 > effo2)
						return -1;
					else if (effo1 < effo2)
						return 1;
					else {
						return Integer.compare(o2.initiative, o1.initiative);
					}
				}
			
			});
			
			for(Group g : groups) {
				int bestDmg = 0;
				for(Group potentialTarget : potentialTargets) {
					int dmg = g.atk * g.unitCount;
					if(g.infection == potentialTarget.infection || g.equals(potentialTarget))
						continue;
					if(potentialTarget.weak.contains(g.atkType))
						dmg *= 2;
					if(potentialTarget.immune.contains(g.atkType))
						dmg = 0;
					if(dmg == bestDmg) {
						if(g.target != null) {
							if(potentialTarget.atk * potentialTarget.unitCount > g.target.atk * g.target.unitCount) {
								g.target = potentialTarget;
							} else if (potentialTarget.atk * potentialTarget.unitCount == g.target.atk * g.target.unitCount) {
								if(potentialTarget.initiative > g.target.initiative)
									g.target = potentialTarget;
							}
						}
					}
					if(dmg > bestDmg) {
						bestDmg = dmg;
						g.target = potentialTarget;
					}
				}
				potentialTargets.remove(g.target);
			}
			
			//sort groups in combat order
			Collections.sort(groups, new Comparator<Group>() {

				@Override
				public int compare(Group o1, Group o2) {
					return Integer.compare(o2.initiative, o1.initiative);
				}
			
			});
			
			for(Group g : groups) {
				if(g.unitCount > 0) {
					g.attackTarget();
					g.target = null;
				}
				
			}
			
			for(int i = 0; i < groups.size(); i++) {
				if(groups.get(i).unitCount <= 0) {
					groups.remove(i);
					i--;
				}
			}
		}
		
		return Integer.toString(groups.stream().mapToInt(x -> x.unitCount).sum());
	}
	
	public boolean containsBoth(ArrayList<Group> g) {
		boolean infection = false;
		boolean immune = false;
		for(Group h : g) {
			infection |= h.infection;
			immune |= !h.infection;
		}
		return infection && immune;
	}

	@Override
	public String part2() {
		ArrayList<String> attackTypes = new ArrayList<String>();
		ArrayList<Group> groupsBuffer = new ArrayList<Group>();
		String[] sections = input.split("\r\n\r\n");
		String[] immuneLines = sections[0].split("\r\n");
		for(int immuneCount = 1; immuneCount < immuneLines.length; immuneCount++) {
			String line = immuneLines[immuneCount];
			String[] words = line.split(" ");
			int unitCount = Integer.parseInt(words[0]);
			int unitHp = Integer.parseInt(words[4]);
			HashSet<Integer> weak = new HashSet<Integer>();
			HashSet<Integer> immune = new HashSet<Integer>();
			//use an index pointer to parse immunities and weaknesses
			int index = 7;
			while(!words[index].equals("with")) {
				if(words[index].replaceAll("\\(|,|;|\\)","").equals("weak")) {
					index += 2;
					while(!words[index].equals("immune") && !words[index].equals("with")) {
						
						String type = words[index].replaceAll("\\(|,|;|\\)","");
						
						if(!attackTypes.contains(type))
							attackTypes.add(type);
						weak.add(attackTypes.indexOf(type));
						index++;
					}
				} else if(words[index].replaceAll("\\(|,|;|\\)", "").equals("immune")) {
					index += 2;
					while(!words[index].equals("weak") && !words[index].equals("with")) {
						String type = words[index].replaceAll("\\(|,|;|\\)","");
						if(!attackTypes.contains(type))
							attackTypes.add(type);
						immune.add(attackTypes.indexOf(type));
						index++;
					}
				}
			}
			int atkDamage = Integer.parseInt(words[index + 5]);
			String type = words[index+6];
			if(!attackTypes.contains(type))
				attackTypes.add(type);
			int atkType = attackTypes.indexOf(type);
			int initiative = Integer.parseInt(words[words.length - 1]);
			
			groupsBuffer.add(new Group(false,unitHp, unitCount, weak, immune, atkDamage, atkType, initiative));
		}
		String[] infectionLines = sections[1].split("\r\n");
		for(int infectionCount = 1; infectionCount < infectionLines.length; infectionCount++) {
			String line = infectionLines[infectionCount];
			if(line.equals("Infection:"))
				continue;
			line = line.replace("\r\n ", " ");
			String[] words = line.split(" ");
			int unitCount = Integer.parseInt(words[0]);
			int unitHp = Integer.parseInt(words[4]);
			HashSet<Integer> weak = new HashSet<Integer>();
			HashSet<Integer> immune = new HashSet<Integer>();
			//use an index pointer to parse immunities and weaknesses
			int index = 7;
			while(!words[index].equals("with")) {
				if(words[index].replaceAll("\\(|,|;|\\)","").equals("weak")) {
					index += 2;
					while(!words[index].equals("immune") && !words[index].equals("with")) {
						
						String type = words[index].replaceAll("\\(|,|;|\\)","");
						
						if(!attackTypes.contains(type))
							attackTypes.add(type);
						weak.add(attackTypes.indexOf(type));
						index++;
					}
				} else if(words[index].replaceAll("\\(|,|;|\\)", "").equals("immune")) {
					index += 2;
					while(!words[index].equals("weak") && !words[index].equals("with")) {
						String type = words[index].replaceAll("\\(|,|;|\\)","");
						if(!attackTypes.contains(type))
							attackTypes.add(type);
						immune.add(attackTypes.indexOf(type));
						index++;
					}
				}
			}
			int atkDamage = Integer.parseInt(words[index + 5]);
			String type = words[index+6];
			if(!attackTypes.contains(type))
				attackTypes.add(type);
			int atkType = attackTypes.indexOf(type);
			int initiative = Integer.parseInt(words[words.length - 1]);
			
			groupsBuffer.add(new Group(true, unitHp, unitCount, weak, immune, atkDamage, atkType, initiative));
		}
		
		int boost = 1;
		boost:
		while(true) {
			ArrayList<Group> groups = new ArrayList<Group>();
			for(Group g : groupsBuffer)
				groups.add(g.clone());
			for(Group g : groups)
				if(!g.infection)
					g.atk += boost;
			int lastState = 0;
			while(containsBoth(groups)) {
				lastState = groups.hashCode();
				ArrayList<Group> potentialTargets = new ArrayList<Group>(groups);
				Collections.sort(groups, new Comparator<Group> () {
		
					@Override
					public int compare(Group o1, Group o2) {
						int effo2 = o2.unitCount * o2.atk;
						int effo1 = o1.unitCount * o1.atk;
						if(effo1 > effo2)
							return -1;
						else if (effo1 < effo2)
							return 1;
						else {
							return Integer.compare(o2.initiative, o1.initiative);
						}
					}
				
				});
				
				for(Group g : groups) {
					int bestDmg = 0;
					for(Group potentialTarget : potentialTargets) {
						int dmg = g.atk * g.unitCount;
						if(g.infection == potentialTarget.infection || g.equals(potentialTarget))
							continue;
						if(potentialTarget.weak.contains(g.atkType))
							dmg *= 2;
						if(potentialTarget.immune.contains(g.atkType))
							dmg = 0;
						if(dmg == bestDmg) {
							if(g.target != null) {
								if(potentialTarget.atk * potentialTarget.unitCount > g.target.atk * g.target.unitCount) {
									g.target = potentialTarget;
								} else if (potentialTarget.atk * potentialTarget.unitCount == g.target.atk * g.target.unitCount) {
									if(potentialTarget.initiative > g.target.initiative)
										g.target = potentialTarget;
								}
							}
						}
						if(dmg > bestDmg) {
							bestDmg = dmg;
							g.target = potentialTarget;
						}
					}
					potentialTargets.remove(g.target);
				}
				
				//sort groups in combat order
				Collections.sort(groups, new Comparator<Group>() {
		
					@Override
					public int compare(Group o1, Group o2) {
						return Integer.compare(o2.initiative, o1.initiative);
					}
				
				});
				
				for(Group g : groups) {
					if(g.unitCount > 0) {
						g.attackTarget();
						g.target = null;
					}
					
				}
				
				for(int i = 0; i < groups.size(); i++) {
					if(groups.get(i).unitCount <= 0) {
						groups.remove(i);
						i--;
					}
				}
				
				//some boosts get stuck an in infinite loop of all-immune enemies
				//if same hash code occurs twice in a row, increment and break
				if(groups.hashCode() == lastState) {
					boost++;
					continue boost;
				}
			}
			
			if(groups.get(0).infection) {
				//infection won, increment boost and try again
				boost++;
				continue;
			}
			
			

			return Integer.toString(groups.stream().mapToInt(x -> x.unitCount).sum());
		}
	}

	public static void main(String[] args) {
		DayRunner.run(new Day24());
	}

}
