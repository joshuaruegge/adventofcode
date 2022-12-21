package advent.aoc2021;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;
import advent.utilities.utils2021.AmphiState;

import java.util.*;

public class Day23 implements IDay {

	static String input;

	final int[] COST = new int[] {1,10,100,1000};

	@Override
	public String part1() {
		char[][] startRooms = new char[4][2];
		ArrayList<Character> startChars = new ArrayList<>();
		for(char c : input.toCharArray()) {
			if(c == 'A' || c == 'B' || c == 'C' || c == 'D')
				startChars.add(c);
		}

		for(int i = 0; i < 4; i++) {
			startRooms[i][0] = startChars.get(i);
			startRooms[i][1] = startChars.get(i+4);
		}
		AmphiState start = new AmphiState(startRooms,new char[11]);

		AmphiState end = new AmphiState(new char[][] {{'A','A'}, {'B','B'}, {'C','C'}, {'D','D'}}, new char[11]);



		HashMap<AmphiState,Integer> gCost = new HashMap<>();
		gCost.put(start,0);

		PriorityQueue<AmphiState> queue = new PriorityQueue<>(new Comparator<AmphiState>() {
			@Override
			public int compare(AmphiState o1, AmphiState o2) {
				return Integer.compare(gCost.getOrDefault(o1,0), gCost.getOrDefault(o2,0));
			}
		});
		queue.add(start);

		while(!queue.isEmpty()) {
			AmphiState cur = queue.poll();

			char[] hall = cur.hallway;
			char[][] rooms = cur.rooms;

			//try moving from hallway to burrow
			for(int hallIndex = 0; hallIndex < hall.length; hallIndex++) {
				if(hall[hallIndex] == 0)
					continue;
				int amphib = hall[hallIndex] - 'A';
				//the hallway index for room entrance of amphibian is always 2 * amphib + 2
				//0 - 2
				//1 - 4
				//2 - 6
				//3 - 8
				int destIndex = amphib * 2 + 2;
				//offset hall index to not count current position
				int hallIndexOffset = hallIndex + (int) Math.signum(destIndex - hallIndex);
				//make sure path to front of burrow is clear
				if(!clearBetween(hall,hallIndexOffset,destIndex))
					continue;
				//ensure burrow is clear
				if(rooms[amphib][0] == 0 && (rooms[amphib][1] == 0 || rooms[amphib][1] == amphib + 'A')) {
					//move to back if back is clear
					if(rooms[amphib][1] == 0) {
						char[] newHall = Arrays.copyOf(hall,hall.length);
						newHall[hallIndex] = 0;
						char[][] newRooms = new char[4][];
						for(int i = 0; i < 4; i++)
							newRooms[i] = Arrays.copyOf(rooms[i],rooms[i].length);
						newRooms[amphib][1] = (char) (amphib + 'A');
						AmphiState next = new AmphiState(newRooms,newHall);
						int newCost = (Math.abs(destIndex - hallIndex) + 2) * COST[amphib];
						int cost = newCost + gCost.get(cur);
						if(cost < gCost.getOrDefault(next, Integer.MAX_VALUE)) {
							gCost.put(next,cost);
							queue.add(next);
						}
					} else {
					//otherwise, move to front
						char[] newHall = Arrays.copyOf(hall,hall.length);
						newHall[hallIndex] = 0;
						char[][] newRooms = new char[4][];
						for(int i = 0; i < 4; i++)
							newRooms[i] = Arrays.copyOf(rooms[i],rooms[i].length);
						newRooms[amphib][0] = (char) (amphib + 'A');
						AmphiState next = new AmphiState(newRooms,newHall);
						int newCost = (Math.abs(destIndex - hallIndex) + 1) * COST[amphib];
						int cost = newCost + gCost.get(cur);
						if(cost < gCost.getOrDefault(next, Integer.MAX_VALUE)) {
							gCost.put(next,cost);
							queue.add(next);
						}
					}
				}
			}

			//try moving from burrow to hallway
			for(int burrow = 0; burrow < 4; burrow++) {
				//if front empty, move back
				if(rooms[burrow][0] == 0) {
					//if back is occupied and not correct
					if(rooms[burrow][1] != 0 && rooms[burrow][1] != burrow + 'A') {
						int amphib = rooms[burrow][1] - 'A';
						int amphibStart = burrow * 2 + 2;
						for(int dest = 0; dest < hall.length; dest++) {
							if(hall[dest] != 0)
								continue;
							if(dest == 2 || dest == 4 || dest == 6 || dest == 8)
								continue;
							if(!clearBetween(hall,amphibStart,dest))
								continue;
							char[] newHall = Arrays.copyOf(hall,hall.length);
							//move to hall
							newHall[dest] = (char) (amphib + 'A');
							char[][] newRooms = new char[4][];
							for(int i = 0; i < 4; i++)
								newRooms[i] = Arrays.copyOf(rooms[i],rooms[i].length);
							//delete in burrow
							newRooms[burrow][1] = 0;
							AmphiState next = new AmphiState(newRooms,newHall);
							int newCost = (Math.abs(dest - amphibStart) + 2) * COST[amphib];
							int cost = newCost + gCost.get(cur);
							if(cost < gCost.getOrDefault(next, Integer.MAX_VALUE)) {
								gCost.put(next,cost);
								queue.add(next);
							}
						}
					}
				} else {
					//if front is not already correct
					//or, if front is correct but hiding an incorrect
					if(rooms[burrow][0] != burrow + 'A' || (rooms[burrow][0] == burrow + 'A' && rooms[burrow][1] != burrow + 'A')) {
						int amphib = rooms[burrow][0] - 'A';
						int amphibStart = burrow * 2 + 2;
						//try putting it in different places in hall (this can be optimized later
						for(int dest = 0; dest < hall.length; dest++) {
							if(hall[dest] != 0)
								continue;
							if(dest == 2 || dest == 4 || dest == 6 || dest == 8)
								continue;
							if(!clearBetween(hall,amphibStart,dest))
								continue;
							char[] newHall = Arrays.copyOf(hall,hall.length);
							//move to hall
							newHall[dest] = (char) (amphib + 'A');
							char[][] newRooms = new char[4][];
							for(int i = 0; i < 4; i++)
								newRooms[i] = Arrays.copyOf(rooms[i],rooms[i].length);
							//delete in burrow
							newRooms[burrow][0] = 0;
							AmphiState next = new AmphiState(newRooms,newHall);
							int newCost = (Math.abs(dest - amphibStart) + 1) * COST[amphib];
							int cost = newCost + gCost.get(cur);
							if(cost < gCost.getOrDefault(next, Integer.MAX_VALUE)) {
								gCost.put(next,cost);
								queue.add(next);
							}
						}
					}
				}

			}
		}

		return Integer.toString(gCost.get(end));
	}

	//checks to make sure all values between start and end (both inclusive) in hallway are zero
	public boolean clearBetween(char[] hall, int start, int end) {
		if(start > end) {
			int temp = start;
			start = end;
			end = temp;
		}
		for(int i = start; i <= end; i++)
			if(hall[i] != 0)
				return false;
		return true;
	}

	@Override
	public String part2() {
		char[][] startRooms = new char[4][4];
		ArrayList<Character> startChars = new ArrayList<>();
		for(char c : input.toCharArray()) {
			if (c == 'A' || c == 'B' || c == 'C' || c == 'D')
				startChars.add(c);
		}
		//manually append extra starting input. sigh
		ArrayList<Character> extraChars = new ArrayList<>();
		extraChars.add('D');
		extraChars.add('C');
		extraChars.add('B');
		extraChars.add('A');
		extraChars.add('D');
		extraChars.add('B');
		extraChars.add('A');
		extraChars.add('C');

		for(int i = 0; i < 4; i++) {
			startRooms[i][0] = startChars.get(i);
			startRooms[i][1] = extraChars.get(i);
			startRooms[i][2] = extraChars.get(i+4);
			startRooms[i][3] = startChars.get(i+4);
		}

		AmphiState start = new AmphiState(startRooms,new char[11]);

		AmphiState end = new AmphiState(new char[][] {{'A','A','A','A'}, {'B','B','B','B'}, {'C','C','C','C'}, {'D','D','D','D'}}, new char[11]);

		HashMap<AmphiState,Integer> gCost = new HashMap<>();
		gCost.put(start,0);

		PriorityQueue<AmphiState> queue = new PriorityQueue<>(new Comparator<AmphiState>() {
			@Override
			public int compare(AmphiState o1, AmphiState o2) {
				return Integer.compare(gCost.getOrDefault(o1,0), gCost.getOrDefault(o2,0));
			}
		});
		queue.add(start);

		while(!queue.isEmpty()) {
			AmphiState cur = queue.poll();

			char[] hall = cur.hallway;
			char[][] rooms = cur.rooms;

			//try moving from hallway to burrow
			for(int hallIndex = 0; hallIndex < hall.length; hallIndex++) {
				if(hall[hallIndex] == 0)
					continue;
				int amphib = hall[hallIndex] - 'A';
				//the hallway index for room entrance of amphibian is always 2 * amphib + 2
				//0 - 2
				//1 - 4
				//2 - 6
				//3 - 8
				int destIndex = amphib * 2 + 2;
				//offset hall index to not count current position
				int hallIndexOffset = hallIndex + (int) Math.signum(destIndex - hallIndex);
				//make sure path to front of burrow is clear
				if(!clearBetween(hall,hallIndexOffset,destIndex))
					continue;
				//ensure burrow is clear
				if(isRoomEmptyExcept(rooms[amphib], (char) (amphib + 'A'))) {
					int roomDestIndex = firstZero(rooms[amphib]);
					char[] newHall = Arrays.copyOf(hall,hall.length);
					newHall[hallIndex] = 0;
					char[][] newRooms = new char[4][];
					for(int i = 0; i < 4; i++)
						newRooms[i] = Arrays.copyOf(rooms[i],rooms[i].length);
					newRooms[amphib][roomDestIndex] = (char) (amphib + 'A');
					AmphiState next = new AmphiState(newRooms,newHall);
					int newCost = (Math.abs(destIndex - hallIndex) + roomDestIndex + 1) * COST[amphib];
					int cost = newCost + gCost.get(cur);
					if(cost < gCost.getOrDefault(next, Integer.MAX_VALUE)) {
						gCost.put(next,cost);
						queue.add(next);
					}
				}
			}

			//try moving from burrow to hallway
			for(int burrow = 0; burrow < 4; burrow++) {
				for(int burrowStart = 0; burrowStart < 4; burrowStart++) {
					//skip if already empty, already correct, or already inaccessible
					if (rooms[burrow][burrowStart] == 0)
						continue;
					//an amphib in the correct burrow is only "locked" if there are none of the wrong type hiding behind it
					if (rooms[burrow][burrowStart] == burrow + 'A' && isRoomEmptyExcept(rooms[burrow], (char) (burrow + 'A')))
						continue;
					if (burrowStart > 0 && !clearBetween(rooms[burrow], burrowStart - 1, 0))
						continue;
					int amphib = rooms[burrow][burrowStart] - 'A';
					int amphibStart = burrow * 2 + 2;
					for (int dest = 0; dest < hall.length; dest++) {
						if (hall[dest] != 0)
							continue;
						if (dest == 2 || dest == 4 || dest == 6 || dest == 8)
							continue;
						if (!clearBetween(hall, amphibStart, dest))
							continue;
						char[] newHall = Arrays.copyOf(hall, hall.length);
						//move to hall
						newHall[dest] = (char) (amphib + 'A');
						char[][] newRooms = new char[4][];
						for (int i = 0; i < 4; i++)
							newRooms[i] = Arrays.copyOf(rooms[i], rooms[i].length);
						//delete in burrow
						newRooms[burrow][burrowStart] = 0;
						AmphiState next = new AmphiState(newRooms, newHall);
						int newCost = (Math.abs(dest - amphibStart) + burrowStart + 1) * COST[amphib];
						int cost = newCost + gCost.get(cur);
						if (cost < gCost.getOrDefault(next, Integer.MAX_VALUE)) {
							gCost.put(next, cost);
							queue.add(next);
						}
					}
				}
			}
		}

		return Integer.toString(gCost.get(end));
	}

	//gets first index that is available to put amphib in
	public int firstZero(char[] a) {
		for(int i = 3; i > -1; i--)
			if(a[i] == 0)
				return i;
		return -1;
	}

	//returns false if a contains any values other than b or zero
	public boolean isRoomEmptyExcept(char[] a, char b) {
		for(char c : a)
			if(c != 0 && c != b)
				return false;
		return true;
	}

	public static void main(String[] args) {
		input = Input.fetchInput(2021,23);
		DayRunner.run(new Day23());
	}

}
