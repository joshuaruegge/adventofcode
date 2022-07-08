package advent.aoc2015;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;

public class Day21 implements IDay {

	String input = "Hit Points: 104\r\n"
			+ "Damage: 8\r\n"
			+ "Armor: 1";
	
	@Override
	public String part1() {
		//parse out monster stats
		String[] lines = input.split("\r\n");
		int monsterHp = Integer.parseInt(lines[0].split(": ")[1]);
		int monsterDmg = Integer.parseInt(lines[1].split(": ")[1]);
		int monsterArmor = Integer.parseInt(lines[2].split(": ")[1]);
		
		int lowestGold = Integer.MAX_VALUE;
		//iterate over all possible combinations, tracking lowest gold that results in a win
		for(int weapon = 1; weapon < 6; weapon++) {
			for(int armor = 0; armor < 6; armor++) {
				for(int ring1 = 0; ring1 < 7; ring1++) {
					for(int ring2 = 0; ring2 < 7; ring2++) {
						//filter out duplicate ring cases
						if(ring1 == ring2)
							continue;
						//calculate gold costs and player stats
						int totalGold = 0;
						//initialize damage, calculate weapon cost
						int weaponDmg = weapon + 3;
						switch(weapon) {
						case 1:
							totalGold += 8;
							break;
						case 2:
							totalGold += 10;
							break;
						case 3:
							totalGold += 25;
							break;
						case 4:
							totalGold += 40;
							break;
						case 5:
							totalGold += 74;
							break;
						}
						//initialize armor, calculate armor costs
						int armorValue = armor;
						switch(armor) {
						case 1:
							totalGold += 13;
							break;
						case 2:
							totalGold += 31;
							break;
						case 3:
							totalGold += 53;
							break;
						case 4:
							totalGold += 75;
							break;
						case 5:
							totalGold += 102;
							break;
						}
						//add ring values
						switch(ring1) {
						case 1:
							weaponDmg++;
							totalGold += 25;
							break;
						case 2:
							weaponDmg += 2;
							totalGold += 50;
							break;
						case 3:
							weaponDmg += 3;
							totalGold += 100;
							break;
						case 4:
							armorValue++;
							totalGold += 20;
							break;
						case 5:
							armorValue += 2;
							totalGold += 40;
							break;
						case 6:
							armorValue += 3;
							totalGold += 80;
							break;
						}
						
						switch(ring2) {
						case 1:
							weaponDmg++;
							totalGold += 25;
							break;
						case 2:
							weaponDmg += 2;
							totalGold += 50;
							break;
						case 3:
							weaponDmg += 3;
							totalGold += 100;
							break;
						case 4:
							armorValue++;
							totalGold += 20;
							break;
						case 5:
							armorValue += 2;
							totalGold += 40;
							break;
						case 6:
							armorValue += 3;
							totalGold += 80;
							break;
						}
						//break early if already higher than best gold
						if(totalGold > lowestGold)
							continue;
						//simulate combat
						//initialize round-specific hp values
						int enemyHp = monsterHp;
						int playerHp = 100;
						//combat loop
						while(playerHp > 0) {
							//player attack
							enemyHp -= (weaponDmg - monsterArmor);
							//break if boss killed
							if(enemyHp <= 0)
								break;
							//enemy attack
							playerHp -= (monsterDmg - armorValue);
						}
						//if boss killed and player survived, update gold if necessary
						if(playerHp > 0 && enemyHp <= 0) {
							if(totalGold < lowestGold)
								lowestGold = totalGold;
						}
					}
				}
			}
		}
		return Integer.toString(lowestGold);
	}

	@Override
	public String part2() {
		//same code, just reverse health and cost evaluations
		
		//parse out monster stats
		String[] lines = input.split("\r\n");
		int monsterHp = Integer.parseInt(lines[0].split(": ")[1]);
		int monsterDmg = Integer.parseInt(lines[1].split(": ")[1]);
		int monsterArmor = Integer.parseInt(lines[2].split(": ")[1]);
		
		int highestGold = 0;
		//iterate over all possible combinations, tracking lowest gold that results in a win
		for(int weapon = 1; weapon < 6; weapon++) {
			for(int armor = 0; armor < 6; armor++) {
				for(int ring1 = 0; ring1 < 7; ring1++) {
					for(int ring2 = 0; ring2 < 7; ring2++) {
						//filter out duplicate ring cases
						if(ring1 == ring2)
							continue;
						//calculate gold costs and player stats
						int totalGold = 0;
						//initialize damage, calculate weapon cost
						int weaponDmg = weapon + 3;
						switch(weapon) {
						case 1:
							totalGold += 8;
							break;
						case 2:
							totalGold += 10;
							break;
						case 3:
							totalGold += 25;
							break;
						case 4:
							totalGold += 40;
							break;
						case 5:
							totalGold += 74;
							break;
						}
						//initialize armor, calculate armor costs
						int armorValue = armor;
						switch(armor) {
						case 1:
							totalGold += 13;
							break;
						case 2:
							totalGold += 31;
							break;
						case 3:
							totalGold += 53;
							break;
						case 4:
							totalGold += 75;
							break;
						case 5:
							totalGold += 102;
							break;
						}
						//add ring values
						switch(ring1) {
						case 1:
							weaponDmg++;
							totalGold += 25;
							break;
						case 2:
							weaponDmg += 2;
							totalGold += 50;
							break;
						case 3:
							weaponDmg += 3;
							totalGold += 100;
							break;
						case 4:
							armorValue++;
							totalGold += 20;
							break;
						case 5:
							armorValue += 2;
							totalGold += 40;
							break;
						case 6:
							armorValue += 3;
							totalGold += 80;
							break;
						}
						
						switch(ring2) {
						case 1:
							weaponDmg++;
							totalGold += 25;
							break;
						case 2:
							weaponDmg += 2;
							totalGold += 50;
							break;
						case 3:
							weaponDmg += 3;
							totalGold += 100;
							break;
						case 4:
							armorValue++;
							totalGold += 20;
							break;
						case 5:
							armorValue += 2;
							totalGold += 40;
							break;
						case 6:
							armorValue += 3;
							totalGold += 80;
							break;
						}
						//simulate combat
						//initialize round-specific hp values
						int enemyHp = monsterHp;
						int playerHp = 100;
						//combat loop
						while(playerHp > 0) {
							//player attack
							enemyHp -= (weaponDmg - monsterArmor);
							//break if boss killed
							if(enemyHp <= 0)
								break;
							//enemy attack
							playerHp -= (monsterDmg - armorValue);
						}
						//if boss survived and player killed, update gold if necessary
						if(playerHp <= 0 && enemyHp > 0) {
							if(totalGold > highestGold)
								highestGold = totalGold;
						}
					}
				}
			}
		}
		return Integer.toString(highestGold);
	}

	public static void main(String[] args) {
		DayRunner.run(new Day21());
	}

}
