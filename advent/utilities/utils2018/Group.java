package advent.utilities.utils2018;

import java.util.HashSet;
import java.util.Objects;

public class Group {

	public boolean infection;
	
	public int unitHP;
	public int unitCount;
	public int atk;
	public int atkType;
	public int initiative;
	
	public HashSet<Integer> weak, immune;
	
	public Group target;
	
	public Group (boolean infection, int unitHP, int unitCount, HashSet<Integer> weak, HashSet<Integer> immune, int atk, int atkType, int initiative) {
		this.infection = infection;
		this.unitHP = unitHP;
		this.unitCount = unitCount;
		this.weak = weak;
		this.immune = immune;
		this.atk = atk;
		this.atkType = atkType;
		this.initiative= initiative;
	}
	
	public void attackTarget() {
		if(target == null) {
			return;
		}
		int dmg = atk * unitCount;
		if(target.weak.contains(atkType))
			dmg *= 2;
		if(target.immune.contains(atkType))
			dmg = 0;
		target.unitCount -= (dmg / target.unitHP);	
	}

	@Override
	public String toString() {
		return "Group [infection=" + infection + ", unitHP=" + unitHP + ", unitCount=" + unitCount + ", atk=" + atk
				+ ", atkType=" + atkType + ", initiative=" + initiative + ", weak=" + weak + ", immune=" + immune
				+ "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(atk, atkType, immune, infection, initiative, target, unitCount, unitHP, weak);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Group other = (Group) obj;
		return atk == other.atk && atkType == other.atkType && Objects.equals(immune, other.immune)
				&& infection == other.infection && initiative == other.initiative
				&& Objects.equals(target, other.target) && unitCount == other.unitCount && unitHP == other.unitHP
				&& Objects.equals(weak, other.weak);
	}
	
	public Group clone() {
		return new Group(infection,unitHP,unitCount, weak,immune,atk, atkType, initiative);
	}
	
}
