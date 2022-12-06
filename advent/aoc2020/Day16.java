package advent.aoc2020;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

public class Day16 implements IDay {

	static String input;
	
	@Override
	public String part1() {
		//in order: rules, own ticket, nearby tickets
		String[] chunks = input.split("\n\n");
		
		//collect all numbers any value could be
		HashSet<Integer> valids = new HashSet<Integer>();
		for(String rules : chunks[0].split("\n")) {
			String[] ranges = rules.split(": ")[1].split(" or ");
			int range1 = Integer.parseInt(ranges[0].split("-")[0]);
			int range2 = Integer.parseInt(ranges[0].split("-")[1]);
			for(int i = range1; i <= range2; i++)
				valids.add(i);
			int range3 = Integer.parseInt(ranges[1].split("-")[0]);
			int range4 = Integer.parseInt(ranges[1].split("-")[1]);
			for(int i = range3; i <= range4; i++)
				valids.add(i);
		}
		
		int errorSum = 0;
		
		String[] tickets = chunks[2].split("\n");
		for(int i = 1; i < tickets.length; i++) {
			String ticket = tickets[i];
			for(String num : ticket.split(",")) {
				int number = Integer.parseInt(num);
				if(!valids.contains(number))
					errorSum += number;
			}
		}
		
		return Integer.toString(errorSum);
	}

	@Override
	public String part2() {
		String[] chunks = input.split("\n\n");
		//this time, map ranges to an arraylist of hashsets, with each index representing a different field's range
		ArrayList<HashSet<Integer>> ranges = new ArrayList<HashSet<Integer>>();
		for(String rules : chunks[0].split("\n")) {
			HashSet<Integer> valids = new HashSet<Integer>();
			String[] inRanges = rules.split(": ")[1].split(" or ");
			int range1 = Integer.parseInt(inRanges[0].split("-")[0]);
			int range2 = Integer.parseInt(inRanges[0].split("-")[1]);
			for(int i = range1; i <= range2; i++)
				valids.add(i);
			int range3 = Integer.parseInt(inRanges[1].split("-")[0]);
			int range4 = Integer.parseInt(inRanges[1].split("-")[1]);
			for(int i = range3; i <= range4; i++)
				valids.add(i);
			ranges.add(valids);
		}
		
		//build total valids set for invalid ticket check
		HashSet<Integer> allValids = new HashSet<Integer>();
		for(HashSet<Integer> r : ranges)
			allValids.addAll(r);
		
		//now, create ticket list, skipping invalids
		ArrayList<ArrayList<Integer>> tickets = new ArrayList<ArrayList<Integer>>();
		String[] inputTickets = chunks[2].split("\n");
		ticketLoop:
		for(int i = 1; i < inputTickets.length; i++) {
			ArrayList<Integer> ticket = new ArrayList<Integer>();
			for(String s : inputTickets[i].split(",")) {
				int num = Integer.parseInt(s);
				//if invalid, skip entire ticket
				if(!allValids.contains(num))
					continue ticketLoop;
				ticket.add(num);
			}
			tickets.add(ticket);
		}
		
		//now, as we iterate through tickets, we keep a list of hashsets - at each index, the hashset represents the potential category (index in ranges) that the index in the ticket could represent
		//so, for example, if index 0 has a hashset of [1,3,7,19], it means that index 0 on a ticket could be category 1, 3, 7, or 19 in ranges
		//we start by assuming all indices could be any range, and reduce from there
		ArrayList<HashSet<Integer>> ticketIndices = new ArrayList<HashSet<Integer>>();
		for(int i = 0; i < ranges.size(); i++) {
			//add hashset of all values 0-ranges.size
			ticketIndices.add(IntStream.range(0, ranges.size()).boxed().collect(Collectors.toCollection(HashSet::new)));
		}
		
		for(ArrayList<Integer> ticket : tickets) {
			//iterate over ticket indices
			for(int ticketIndex = 0; ticketIndex < ticket.size(); ticketIndex++) {
				int valueAt = ticket.get(ticketIndex);
				HashSet<Integer> potentialRanges = new HashSet<Integer>();
				for(int range = 0; range < ranges.size(); range++)
					if(ranges.get(range).contains(valueAt))
						potentialRanges.add(range);
				//now, merge potential ranges with existing potential range list
				ticketIndices.get(ticketIndex).retainAll(potentialRanges);
			}
		}
		
		//at this point, this is like the opcode matching from 2018 - given our potential lists, we determine the real identity of each
		//realIndex maps (index in rules) -> (index in ticket)
		HashMap<Integer,Integer> realIndex = new HashMap<Integer,Integer>();
		while(ticketIndices.stream().filter(x -> x.size() != 0).count() > 0) {
			for(int index = 0; index < ticketIndices.size(); index++) {
				HashSet<Integer> possibilities = ticketIndices.get(index);
				if(possibilities.size() == 1) {
					//extract value in hashset with stream
					int mappedIndex = possibilities.stream().mapToInt(x -> x).findFirst().getAsInt();
					realIndex.put(mappedIndex, index);
					//remove value from all other hashsets
					for(HashSet<Integer> s : ticketIndices)
						s.remove(mappedIndex);
					break;
				}
			}
		}
		
		//now, parse our own ticket, and for the first 6 indices (ones with departure), get the value at the real index, and multiply it into the total
		ArrayList<Integer> ticket = new ArrayList<Integer>();
		for(String s : chunks[1].split("\n")[1].split(","))
			ticket.add(Integer.parseInt(s));
				
		long total = 1;
		for(int i = 0; i < 6; i++) {
			total *= ticket.get(realIndex.get(i));
		}
		
		return Long.toString(total);
	}
	
	public static void main(String[] args) {
		input = Input.fetchInput(2020,16);
		DayRunner.run(new Day16());
	}
}
