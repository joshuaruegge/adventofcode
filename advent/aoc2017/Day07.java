package advent.aoc2017;

import java.util.ArrayList;
import java.util.Collections;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;
import advent.utilities.utils2017.TreeNode;

public class Day07 implements IDay {

	static String input;
	
	@Override
	public String part1() {
		ArrayList<TreeNode> nodes = new ArrayList<TreeNode>();
		
		for(String s : input.split("\n")) {
			String[] parts = s.split(" ");
			TreeNode node = new TreeNode(parts[0]);
			int weight = Integer.parseInt(parts[1].substring(1,parts[1].length() - 1));
			node.setNodeWeight(weight);
			//add placeholders for children
			if(parts.length > 2) {
				for(int i = 3; i < parts.length; i++) {
					node.addChild(new TreeNode(parts[i].replace(",", "")));
				}
			}
			nodes.add(node);
		}
		//for each node, connect it to its children in list
		for(TreeNode node : nodes) {
			for(int i = 0; i < node.children.size(); i++) {
				node.children.set(i, find(nodes,node.children.get(i).name));
				node.children.get(i).parent = node;
			}
		}
		
		//from any node (first will do), go up till root
		TreeNode root = nodes.get(0);
		while(root.parent != null)
			root = root.parent;
		
		return root.name;
	}

	@Override
	public String part2() {
		//store temporary nodes in list
		ArrayList<TreeNode> nodes = new ArrayList<TreeNode>();
		
		for(String s : input.split("\n")) {
			String[] parts = s.split(" ");
			TreeNode node = new TreeNode(parts[0]);
			int weight = Integer.parseInt(parts[1].substring(1,parts[1].length() - 1));
			node.setNodeWeight(weight);
			//add placeholders for children
			if(parts.length > 2) {
				for(int i = 3; i < parts.length; i++) {
					node.addChild(new TreeNode(parts[i].replace(",", "")));
				}
			}
			nodes.add(node);
		}
		//for each node, connect it to its children in list
		for(TreeNode node : nodes) {
			for(int i = 0; i < node.children.size(); i++) {
				node.children.set(i, find(nodes,node.children.get(i).name));
				node.children.get(i).parent = node;
			}
		}
		
		//from any node (first will do), go up till root
		TreeNode root = nodes.get(0);
		while(root.parent != null)
			root = root.parent;		
		
		TreeNode unbalanced = root;
		
		//iterate downwards until we find the first balanced node above an unbalanced one
		while(!isBalanced(unbalanced)) {
			int weight = (unbalanced.children.get(0).getTotalWeight() - unbalanced.children.get(0).nodeWeight) / unbalanced.children.size();
			for(TreeNode c : unbalanced.children) {
				if(c.getTotalWeight() != weight) {
					unbalanced = c;
					break;
				}
			}
		}
		
		
		//move up a level (to deepest unbalanced node), compare children
		unbalanced = unbalanced.parent;
		
		ArrayList<Integer> weights = new ArrayList<Integer>();
		for(TreeNode c : unbalanced.children)
			weights.add(c.totalNodeWeight);
		
		Collections.sort(weights);
		
		//if index of 1 is equal to index of 0, differing weight is at end. otherwise, is at start
		//imagine sorting the array [1,3,1,1,1] - you end up with [1,1,1,1,3], and because indexes 0 and 1 are equal, the differing value must be at the end
		//alternatively, imagine [3,3,1,3,3] - result is [1,3,3,3,3], indexes 0 and 1 are unequal, so 0 is differing value
		int necessaryOffset = (weights.get(1) == Collections.min(weights) ? (Collections.max(weights) - Collections.min(weights)) : (Collections.min(weights) - Collections.max(weights)));
		
		int weight = (unbalanced.children.get(0).getTotalWeight() - unbalanced.children.get(0).nodeWeight) / unbalanced.children.size();
		
		//find weight that needs to be adjusted, return value it needs to be
		for(TreeNode c : unbalanced.children) {
			if(c.getTotalWeight() != weight) {
				return Integer.toString(c.nodeWeight + necessaryOffset);
			}
		}
		return null;
	}
	
	public boolean isBalanced(TreeNode n) {
		if(n.children.size() == 0)
			return true;
		int weight = n.children.get(0).getTotalWeight();
		for(TreeNode c : n.children)
			if(c.getTotalWeight() != weight)
				return false;
		return true;
	}

	public TreeNode find(ArrayList<TreeNode> nodes, String name) {
		for(TreeNode n : nodes)
			if(n.name.equals(name))
				return n;
		return null;
	}
	
	public static void main(String[] args) {
		input = Input.fetchInput(2017,7);
		DayRunner.run(new Day07());
	}
}
