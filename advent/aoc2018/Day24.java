package advent.aoc2018;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;
import advent.utilities.utils2018.Group;

public class Day24 implements IDay {

	static String input;
	
	@Override
	public String part1() {
		ArrayList<String> attackTypes = new ArrayList<String>();
		ArrayList<Group> groups = new ArrayList<Group>();
		String[] sections = input.split("\n\n");
		String[] immuneLines = sections[0].split("\n");
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
		String[] infectionLines = sections[1].split("\n");
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
		String[] sections = input.split("\n\n");
		String[] immuneLines = sections[0].split("\n");
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
		String[] infectionLines = sections[1].split("\n");
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
		input = Input.fetchInput(2018,24);
		DayRunner.run(new Day24());
	}

}
