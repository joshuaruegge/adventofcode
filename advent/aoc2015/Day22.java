package advent.aoc2015;

import java.util.HashMap;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;

public class Day22 implements IDay {

	String input = "Hit Points: 71\r\n"
			+ "Damage: 10";
	
	static int bossDmg;
	@Override
	public String part1() {
		//parse input
		String[] lines = input.split("\r\n");
		int startBossHP = Integer.parseInt(lines[0].split(": ")[1]);
		bossDmg = Integer.parseInt(lines[1].split(": ")[1]);
		int startPlayerHP = 50;
		int startPlayerMana = 500;
		
		//call recursion
		calculateLeastMana(true,startPlayerHP,startPlayerMana,startBossHP, new HashMap<Integer,Integer>(), 0);
		return Integer.toString(leastMana);
	}

	static int leastMana = Integer.MAX_VALUE;
	
	//spell IDs:
	// 0 - missle, 1 - drain, 2 - shield, 3 - poison, 4 - recharge
	public void calculateLeastMana(boolean playerTurn, int playerHp, int playerMana, int bossHp, HashMap<Integer,Integer> activeSpells, int totalManaCost) {
		//first, apply any effects, then decrease duration
		for(int i : activeSpells.keySet()) {
			if(i == 3) {
				bossHp -= 3;
			}
			if(i == 4) {
				playerMana += 101;
			}
			activeSpells.put(i, activeSpells.get(i) - 1);
		}
		//then, prune spells that have ended
		while(activeSpells.containsValue(0)) {
			for(int i : activeSpells.keySet()) {
				if(activeSpells.get(i) == 0) {
					activeSpells.remove(i);
					break;
				}
			}
		}
		//if boss is dead, update leastMana
		if(bossHp <= 0) {
			if(totalManaCost < leastMana)
				leastMana = totalManaCost;
			return;
		}
		//if player is out of mana, prune branch
		if(playerMana < 53)
			return;
		//if cost is already too high, prune branch
		if(totalManaCost > leastMana)
			return;
		//run either player turn or boss turn, based on boolean value
		if(playerTurn) {
			//recursively call for each possible spell cast
			//missile
			if(playerMana >= 53) {
				calculateLeastMana(false,playerHp,playerMana - 53, bossHp - 4, new HashMap<Integer,Integer>(activeSpells),totalManaCost + 53);
			}
			//drain
			if(playerMana >= 73) {
				calculateLeastMana(false,playerHp + 2, playerMana - 73, bossHp - 2, new HashMap<Integer,Integer>(activeSpells), totalManaCost + 73);
			}
			//shield
			if(playerMana >= 113 && !activeSpells.containsKey(2)) {
				HashMap<Integer,Integer> newActive = new HashMap<Integer,Integer>(activeSpells);
				newActive.put(2, 6);
				calculateLeastMana(false,playerHp,playerMana - 113, bossHp, newActive, totalManaCost+113);
			}
			//poison
			if(playerMana >= 173 && !activeSpells.containsKey(3)) {
				HashMap<Integer,Integer> newActive = new HashMap<Integer,Integer>(activeSpells);
				newActive.put(3, 6);
				calculateLeastMana(false,playerHp,playerMana - 173, bossHp, newActive, totalManaCost+173);
			}
			//recharge
			if(playerMana >= 229 && !activeSpells.containsKey(4)) {
				HashMap<Integer,Integer> newActive = new HashMap<Integer,Integer>(activeSpells);
				newActive.put(4, 5);
				calculateLeastMana(false,playerHp,playerMana - 229, bossHp, newActive, totalManaCost+229);
			}
		} else {
			//deal boss dmg
			if(activeSpells.containsKey(2))
				playerHp -= (bossDmg - 7);
			else
				playerHp -= bossDmg;
			//if player died, prune branch
			if(playerHp <= 0)
				return;
			//otherwise proceed to player turn
			calculateLeastMana(true,playerHp,playerMana,bossHp,new HashMap<Integer,Integer>(activeSpells),totalManaCost);
		}
	}
	
	@Override
	public String part2() {
		//parse input
		String[] lines = input.split("\r\n");
		int startBossHP = Integer.parseInt(lines[0].split(": ")[1]);
		bossDmg = Integer.parseInt(lines[1].split(": ")[1]);
		int startPlayerHP = 50;
		int startPlayerMana = 500;
		
		//call recursion
		calculateLeastMana2(true,startPlayerHP,startPlayerMana,startBossHP, new HashMap<Integer,Integer>(), 0);
		return Integer.toString(leastMana2);
	}
	
	static int leastMana2 = Integer.MAX_VALUE;
	
	//spell IDs:
	// 0 - missle, 1 - drain, 2 - shield, 3 - poison, 4 - recharge
	public void calculateLeastMana2(boolean playerTurn, int playerHp, int playerMana, int bossHp, HashMap<Integer,Integer> activeSpells, int totalManaCost) {
		//only change from part 1 - health drain
		if(playerTurn) {
			playerHp--;
			//if player died from health drain, prune branch
			if(playerHp <= 0)
				return;
		}
		//first, apply any effects, then decrease duration
		for(int i : activeSpells.keySet()) {
			if(i == 3) {
				bossHp -= 3;
			}
			if(i == 4) {
				playerMana += 101;
			}
			activeSpells.put(i, activeSpells.get(i) - 1);
		}
		//then, prune spells that have ended
		while(activeSpells.containsValue(0)) {
			for(int i : activeSpells.keySet()) {
				if(activeSpells.get(i) == 0) {
					activeSpells.remove(i);
					break;
				}
			}
		}
		//if boss is dead, update leastMana
		if(bossHp <= 0) {
			if(totalManaCost < leastMana2)
				leastMana2 = totalManaCost;
			return;
		}
		//if player is out of mana, prune branch
		if(playerMana < 53)
			return;
		//if cost is already too high, prune branch
		if(totalManaCost > leastMana2)
			return;
		//run either player turn or boss turn, based on boolean value
		if(playerTurn) {
			//recursively call for each possible spell cast
			//missile
			if(playerMana >= 53) {
				calculateLeastMana2(false,playerHp,playerMana - 53, bossHp - 4, new HashMap<Integer,Integer>(activeSpells),totalManaCost + 53);
			}
			//drain
			if(playerMana >= 73) {
				calculateLeastMana2(false,playerHp + 2, playerMana - 73, bossHp - 2, new HashMap<Integer,Integer>(activeSpells), totalManaCost + 73);
			}
			//shield
			if(playerMana >= 113 && !activeSpells.containsKey(2)) {
				HashMap<Integer,Integer> newActive = new HashMap<Integer,Integer>(activeSpells);
				newActive.put(2, 6);
				calculateLeastMana2(false,playerHp,playerMana - 113, bossHp, newActive, totalManaCost+113);
			}
			//poison
			if(playerMana >= 173 && !activeSpells.containsKey(3)) {
				HashMap<Integer,Integer> newActive = new HashMap<Integer,Integer>(activeSpells);
				newActive.put(3, 6);
				calculateLeastMana2(false,playerHp,playerMana - 173, bossHp, newActive, totalManaCost+173);
			}
			//recharge
			if(playerMana >= 229 && !activeSpells.containsKey(4)) {
				HashMap<Integer,Integer> newActive = new HashMap<Integer,Integer>(activeSpells);
				newActive.put(4, 5);
				calculateLeastMana2(false,playerHp,playerMana - 229, bossHp, newActive, totalManaCost+229);
			}
		} else {
			//deal boss dmg
			if(activeSpells.containsKey(2))
				playerHp -= (bossDmg - 7);
			else
				playerHp -= bossDmg;
			//if player died, prune branch
			if(playerHp <= 0)
				return;
			//otherwise proceed to player turn
			calculateLeastMana2(true,playerHp,playerMana,bossHp,new HashMap<Integer,Integer>(activeSpells),totalManaCost);
		}
	}

	public static void main(String[] args) {
		DayRunner.run(new Day22());
	}

}
