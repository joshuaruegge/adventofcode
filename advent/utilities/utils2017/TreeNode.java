package advent.utilities.utils2017;

import java.util.ArrayList;

public class TreeNode {
	public String name;
	public ArrayList<TreeNode> children;
	public TreeNode parent = null;
	public int nodeWeight = 0;
	public int totalNodeWeight = -1;
	
	public TreeNode(String n) {
		name = n;
		children = new ArrayList<TreeNode>();
	}
	
	public void addChild(TreeNode n) {
		try {
			nodeWeight += Integer.parseInt(n.name);
		} catch (Exception e) {
		children.add(n);
		nodeWeight += n.nodeWeight;
		}
	}
	
	public String toString() {
		String ret = name + " ";
		for(TreeNode c : children)
			ret += c.name + " " + c.getTotalWeight() + " ";
		ret += getTotalWeight();
		return ret;
	}
	
	public void setNodeWeight(int n) {
		nodeWeight = n;
	}
	
	public int getTotalWeight() {
		if(totalNodeWeight != -1)
			return totalNodeWeight;
		int weight = nodeWeight;
		for(TreeNode n : children)
			weight += n.getTotalWeight();
		totalNodeWeight = weight;
		return weight;
	}
	/**
	public TreeNode find(String n) {
		if(this.name.equals(n))
			return this;
		else
			for(TreeNode t : children) {
				if(t.find(n) != null)
					return t;
			}
		return null;
	}
	**/
}
