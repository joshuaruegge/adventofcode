package advent.aoc2016;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.utils2016.Bot;

public class Day10 implements IDay {

	String input = "bot 59 gives low to bot 176 and high to bot 120\r\n"
			+ "bot 92 gives low to bot 42 and high to bot 187\r\n"
			+ "value 31 goes to bot 114\r\n"
			+ "bot 182 gives low to bot 49 and high to bot 176\r\n"
			+ "bot 17 gives low to bot 181 and high to bot 162\r\n"
			+ "bot 36 gives low to bot 118 and high to bot 121\r\n"
			+ "bot 118 gives low to bot 164 and high to bot 55\r\n"
			+ "bot 172 gives low to bot 79 and high to bot 123\r\n"
			+ "bot 51 gives low to bot 60 and high to bot 31\r\n"
			+ "bot 48 gives low to bot 107 and high to bot 58\r\n"
			+ "bot 142 gives low to output 6 and high to bot 35\r\n"
			+ "bot 133 gives low to output 4 and high to bot 47\r\n"
			+ "bot 134 gives low to bot 122 and high to bot 66\r\n"
			+ "bot 106 gives low to bot 155 and high to bot 99\r\n"
			+ "bot 77 gives low to bot 93 and high to bot 84\r\n"
			+ "bot 9 gives low to bot 173 and high to bot 197\r\n"
			+ "bot 64 gives low to bot 123 and high to bot 48\r\n"
			+ "bot 177 gives low to bot 21 and high to bot 132\r\n"
			+ "bot 94 gives low to bot 6 and high to bot 25\r\n"
			+ "bot 126 gives low to bot 193 and high to bot 56\r\n"
			+ "bot 74 gives low to bot 187 and high to bot 125\r\n"
			+ "bot 80 gives low to bot 41 and high to bot 191\r\n"
			+ "bot 62 gives low to bot 157 and high to bot 138\r\n"
			+ "bot 66 gives low to bot 1 and high to bot 209\r\n"
			+ "bot 90 gives low to bot 104 and high to bot 34\r\n"
			+ "bot 68 gives low to bot 23 and high to bot 87\r\n"
			+ "bot 121 gives low to bot 55 and high to bot 126\r\n"
			+ "bot 122 gives low to bot 137 and high to bot 1\r\n"
			+ "bot 209 gives low to bot 168 and high to bot 26\r\n"
			+ "bot 141 gives low to bot 170 and high to bot 6\r\n"
			+ "bot 149 gives low to bot 62 and high to bot 13\r\n"
			+ "bot 120 gives low to bot 179 and high to bot 71\r\n"
			+ "bot 160 gives low to bot 194 and high to bot 151\r\n"
			+ "bot 86 gives low to bot 96 and high to bot 106\r\n"
			+ "value 13 goes to bot 9\r\n"
			+ "bot 180 gives low to bot 189 and high to bot 27\r\n"
			+ "value 67 goes to bot 88\r\n"
			+ "bot 169 gives low to bot 99 and high to bot 159\r\n"
			+ "bot 56 gives low to bot 98 and high to bot 147\r\n"
			+ "bot 197 gives low to bot 174 and high to bot 81\r\n"
			+ "bot 57 gives low to bot 113 and high to bot 179\r\n"
			+ "bot 39 gives low to bot 115 and high to bot 3\r\n"
			+ "bot 79 gives low to bot 22 and high to bot 40\r\n"
			+ "bot 161 gives low to output 14 and high to bot 185\r\n"
			+ "bot 21 gives low to bot 114 and high to bot 119\r\n"
			+ "bot 136 gives low to bot 28 and high to bot 158\r\n"
			+ "bot 105 gives low to bot 89 and high to bot 19\r\n"
			+ "bot 168 gives low to bot 126 and high to bot 26\r\n"
			+ "bot 193 gives low to bot 64 and high to bot 98\r\n"
			+ "bot 186 gives low to bot 86 and high to bot 178\r\n"
			+ "value 11 goes to bot 165\r\n"
			+ "bot 33 gives low to bot 116 and high to bot 150\r\n"
			+ "bot 32 gives low to bot 154 and high to bot 206\r\n"
			+ "bot 166 gives low to bot 33 and high to bot 139\r\n"
			+ "value 7 goes to bot 63\r\n"
			+ "bot 203 gives low to bot 172 and high to bot 64\r\n"
			+ "bot 200 gives low to bot 94 and high to bot 25\r\n"
			+ "value 43 goes to bot 76\r\n"
			+ "bot 145 gives low to bot 103 and high to bot 128\r\n"
			+ "bot 119 gives low to bot 186 and high to bot 97\r\n"
			+ "bot 12 gives low to bot 31 and high to bot 4\r\n"
			+ "bot 23 gives low to bot 198 and high to bot 171\r\n"
			+ "bot 34 gives low to bot 10 and high to bot 20\r\n"
			+ "bot 198 gives low to bot 43 and high to bot 17\r\n"
			+ "bot 50 gives low to output 1 and high to bot 127\r\n"
			+ "bot 155 gives low to bot 191 and high to bot 32\r\n"
			+ "bot 206 gives low to bot 12 and high to bot 43\r\n"
			+ "bot 96 gives low to bot 80 and high to bot 155\r\n"
			+ "bot 93 gives low to bot 44 and high to bot 70\r\n"
			+ "bot 24 gives low to bot 85 and high to bot 83\r\n"
			+ "bot 30 gives low to bot 159 and high to bot 68\r\n"
			+ "bot 55 gives low to bot 203 and high to bot 193\r\n"
			+ "bot 199 gives low to bot 68 and high to bot 135\r\n"
			+ "bot 170 gives low to bot 97 and high to bot 5\r\n"
			+ "bot 65 gives low to bot 152 and high to bot 194\r\n"
			+ "bot 43 gives low to bot 4 and high to bot 181\r\n"
			+ "bot 113 gives low to output 9 and high to bot 161\r\n"
			+ "bot 81 gives low to bot 141 and high to bot 94\r\n"
			+ "value 29 goes to bot 7\r\n"
			+ "bot 46 gives low to bot 175 and high to bot 195\r\n"
			+ "value 47 goes to bot 21\r\n"
			+ "value 23 goes to bot 42\r\n"
			+ "bot 13 gives low to bot 138 and high to bot 61\r\n"
			+ "bot 135 gives low to bot 87 and high to bot 111\r\n"
			+ "bot 194 gives low to bot 190 and high to bot 82\r\n"
			+ "value 73 goes to bot 109\r\n"
			+ "bot 154 gives low to bot 51 and high to bot 12\r\n"
			+ "bot 1 gives low to bot 18 and high to bot 209\r\n"
			+ "bot 98 gives low to bot 48 and high to bot 45\r\n"
			+ "bot 147 gives low to bot 45 and high to bot 95\r\n"
			+ "bot 47 gives low to output 19 and high to bot 152\r\n"
			+ "bot 26 gives low to bot 56 and high to bot 147\r\n"
			+ "bot 179 gives low to bot 161 and high to bot 71\r\n"
			+ "bot 148 gives low to bot 204 and high to bot 137\r\n"
			+ "bot 5 gives low to bot 67 and high to bot 85\r\n"
			+ "bot 174 gives low to bot 132 and high to bot 141\r\n"
			+ "bot 8 gives low to bot 13 and high to bot 75\r\n"
			+ "bot 82 gives low to bot 146 and high to bot 22\r\n"
			+ "bot 123 gives low to bot 40 and high to bot 107\r\n"
			+ "bot 99 gives low to bot 32 and high to bot 201\r\n"
			+ "bot 41 gives low to bot 196 and high to bot 192\r\n"
			+ "bot 139 gives low to bot 150 and high to bot 153\r\n"
			+ "bot 11 gives low to output 16 and high to bot 113\r\n"
			+ "bot 72 gives low to bot 65 and high to bot 160\r\n"
			+ "bot 195 gives low to bot 133 and high to bot 183\r\n"
			+ "bot 54 gives low to output 12 and high to output 10\r\n"
			+ "bot 158 gives low to bot 102 and high to bot 110\r\n"
			+ "bot 112 gives low to bot 19 and high to bot 118\r\n"
			+ "bot 31 gives low to bot 208 and high to bot 143\r\n"
			+ "bot 167 gives low to bot 7 and high to bot 96\r\n"
			+ "bot 63 gives low to bot 92 and high to bot 74\r\n"
			+ "bot 116 gives low to bot 20 and high to bot 131\r\n"
			+ "bot 184 gives low to bot 39 and high to bot 3\r\n"
			+ "bot 162 gives low to bot 205 and high to bot 39\r\n"
			+ "bot 108 gives low to output 11 and high to bot 175\r\n"
			+ "value 53 goes to bot 207\r\n"
			+ "bot 111 gives low to bot 202 and high to bot 184\r\n"
			+ "bot 25 gives low to bot 24 and high to bot 83\r\n"
			+ "value 71 goes to bot 77\r\n"
			+ "bot 69 gives low to bot 142 and high to bot 0\r\n"
			+ "bot 146 gives low to output 13 and high to bot 53\r\n"
			+ "bot 7 gives low to bot 76 and high to bot 80\r\n"
			+ "bot 131 gives low to bot 73 and high to bot 204\r\n"
			+ "bot 102 gives low to bot 195 and high to bot 117\r\n"
			+ "bot 76 gives low to bot 165 and high to bot 41\r\n"
			+ "bot 153 gives low to bot 148 and high to bot 122\r\n"
			+ "bot 208 gives low to bot 90 and high to bot 163\r\n"
			+ "bot 70 gives low to bot 144 and high to bot 78\r\n"
			+ "bot 125 gives low to bot 8 and high to bot 156\r\n"
			+ "bot 83 gives low to bot 199 and high to bot 135\r\n"
			+ "bot 75 gives low to bot 61 and high to bot 104\r\n"
			+ "bot 67 gives low to bot 169 and high to bot 30\r\n"
			+ "bot 14 gives low to bot 81 and high to bot 200\r\n"
			+ "bot 159 gives low to bot 201 and high to bot 23\r\n"
			+ "value 3 goes to bot 93\r\n"
			+ "bot 110 gives low to bot 117 and high to bot 89\r\n"
			+ "bot 128 gives low to bot 129 and high to bot 182\r\n"
			+ "bot 87 gives low to bot 171 and high to bot 111\r\n"
			+ "bot 45 gives low to bot 58 and high to bot 95\r\n"
			+ "bot 4 gives low to bot 143 and high to bot 166\r\n"
			+ "bot 60 gives low to bot 156 and high to bot 208\r\n"
			+ "bot 27 gives low to bot 108 and high to bot 46\r\n"
			+ "bot 42 gives low to bot 207 and high to bot 149\r\n"
			+ "bot 117 gives low to bot 183 and high to bot 72\r\n"
			+ "bot 115 gives low to bot 153 and high to bot 134\r\n"
			+ "bot 140 gives low to bot 125 and high to bot 60\r\n"
			+ "bot 173 gives low to bot 177 and high to bot 174\r\n"
			+ "bot 138 gives low to bot 180 and high to bot 52\r\n"
			+ "bot 100 gives low to bot 38 and high to bot 59\r\n"
			+ "value 41 goes to bot 173\r\n"
			+ "value 59 goes to bot 177\r\n"
			+ "bot 165 gives low to bot 63 and high to bot 196\r\n"
			+ "bot 84 gives low to bot 70 and high to bot 78\r\n"
			+ "bot 2 gives low to bot 160 and high to bot 91\r\n"
			+ "value 61 goes to bot 29\r\n"
			+ "bot 114 gives low to bot 109 and high to bot 186\r\n"
			+ "bot 205 gives low to bot 139 and high to bot 115\r\n"
			+ "bot 175 gives low to output 17 and high to bot 133\r\n"
			+ "bot 176 gives low to bot 57 and high to bot 120\r\n"
			+ "bot 107 gives low to bot 124 and high to bot 15\r\n"
			+ "bot 52 gives low to bot 27 and high to bot 28\r\n"
			+ "bot 103 gives low to bot 50 and high to bot 129\r\n"
			+ "bot 150 gives low to bot 131 and high to bot 148\r\n"
			+ "bot 16 gives low to output 20 and high to bot 189\r\n"
			+ "bot 190 gives low to output 18 and high to bot 146\r\n"
			+ "bot 157 gives low to bot 16 and high to bot 180\r\n"
			+ "bot 10 gives low to bot 158 and high to bot 130\r\n"
			+ "bot 202 gives low to bot 162 and high to bot 184\r\n"
			+ "bot 88 gives low to bot 77 and high to bot 84\r\n"
			+ "bot 188 gives low to bot 128 and high to bot 38\r\n"
			+ "bot 58 gives low to bot 15 and high to bot 101\r\n"
			+ "bot 171 gives low to bot 17 and high to bot 202\r\n"
			+ "bot 97 gives low to bot 178 and high to bot 67\r\n"
			+ "bot 163 gives low to bot 34 and high to bot 116\r\n"
			+ "bot 124 gives low to bot 0 and high to bot 145\r\n"
			+ "bot 71 gives low to bot 185 and high to bot 54\r\n"
			+ "bot 78 gives low to bot 14 and high to bot 200\r\n"
			+ "bot 101 gives low to bot 188 and high to bot 100\r\n"
			+ "bot 189 gives low to output 7 and high to bot 108\r\n"
			+ "bot 95 gives low to bot 101 and high to bot 100\r\n"
			+ "bot 0 gives low to bot 35 and high to bot 103\r\n"
			+ "bot 207 gives low to bot 37 and high to bot 62\r\n"
			+ "bot 49 gives low to bot 11 and high to bot 57\r\n"
			+ "bot 85 gives low to bot 30 and high to bot 199\r\n"
			+ "bot 89 gives low to bot 72 and high to bot 2\r\n"
			+ "bot 3 gives low to bot 134 and high to bot 66\r\n"
			+ "bot 181 gives low to bot 166 and high to bot 205\r\n"
			+ "bot 91 gives low to bot 151 and high to bot 172\r\n"
			+ "value 17 goes to bot 167\r\n"
			+ "bot 20 gives low to bot 130 and high to bot 73\r\n"
			+ "bot 196 gives low to bot 74 and high to bot 140\r\n"
			+ "bot 18 gives low to bot 121 and high to bot 168\r\n"
			+ "bot 185 gives low to output 15 and high to bot 54\r\n"
			+ "bot 178 gives low to bot 106 and high to bot 169\r\n"
			+ "bot 129 gives low to bot 127 and high to bot 49\r\n"
			+ "bot 19 gives low to bot 2 and high to bot 164\r\n"
			+ "bot 15 gives low to bot 145 and high to bot 188\r\n"
			+ "bot 144 gives low to bot 197 and high to bot 14\r\n"
			+ "bot 201 gives low to bot 206 and high to bot 198\r\n"
			+ "bot 164 gives low to bot 91 and high to bot 203\r\n"
			+ "bot 73 gives low to bot 105 and high to bot 112\r\n"
			+ "bot 191 gives low to bot 192 and high to bot 154\r\n"
			+ "bot 109 gives low to bot 167 and high to bot 86\r\n"
			+ "bot 151 gives low to bot 82 and high to bot 79\r\n"
			+ "bot 53 gives low to output 2 and high to bot 142\r\n"
			+ "bot 37 gives low to bot 29 and high to bot 157\r\n"
			+ "value 2 goes to bot 44\r\n"
			+ "bot 204 gives low to bot 112 and high to bot 36\r\n"
			+ "bot 40 gives low to bot 69 and high to bot 124\r\n"
			+ "bot 22 gives low to bot 53 and high to bot 69\r\n"
			+ "bot 104 gives low to bot 136 and high to bot 10\r\n"
			+ "value 19 goes to bot 88\r\n"
			+ "bot 127 gives low to output 5 and high to bot 11\r\n"
			+ "bot 183 gives low to bot 47 and high to bot 65\r\n"
			+ "bot 192 gives low to bot 140 and high to bot 51\r\n"
			+ "bot 38 gives low to bot 182 and high to bot 59\r\n"
			+ "bot 61 gives low to bot 52 and high to bot 136\r\n"
			+ "bot 156 gives low to bot 75 and high to bot 90\r\n"
			+ "value 37 goes to bot 37\r\n"
			+ "bot 28 gives low to bot 46 and high to bot 102\r\n"
			+ "bot 187 gives low to bot 149 and high to bot 8\r\n"
			+ "bot 132 gives low to bot 119 and high to bot 170\r\n"
			+ "bot 44 gives low to bot 9 and high to bot 144\r\n"
			+ "bot 29 gives low to output 0 and high to bot 16\r\n"
			+ "bot 6 gives low to bot 5 and high to bot 24\r\n"
			+ "bot 137 gives low to bot 36 and high to bot 18\r\n"
			+ "bot 130 gives low to bot 110 and high to bot 105\r\n"
			+ "value 5 goes to bot 92\r\n"
			+ "bot 35 gives low to output 3 and high to bot 50\r\n"
			+ "bot 152 gives low to output 8 and high to bot 190\r\n"
			+ "bot 143 gives low to bot 163 and high to bot 33";
	
	@Override
	public String part1() {
		//parse lines into arraylist and sort by alphabetical so
		//bots are created before receiving values+
		ArrayList<String> lines = new ArrayList<String>();
		for(String s : input.split("\r\n"))
			lines.add(s);
		//alphabetic order (first character only)
		Collections.sort(lines,new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				return Integer.compare(o1.charAt(0),o2.charAt(0));
			}
		
		});
		
		for(String s : lines) {
			String[] parts = s.split(" ");
			if(parts[0].equals("value")) {
				//push value to bot
				Bot.find(Integer.parseInt(parts[5])).buffer.add(Integer.parseInt(parts[1]));
			} else {
				//create new bot
				Bot.bots.add(new Bot(Integer.parseInt(parts[1]), Integer.parseInt(parts[6]), parts[5].equals("bot"), Integer.parseInt(parts[11]), parts[10].equals("bot")));
			}
		}
		
		//go over bot array, pushing result of bots that have both values necessary to compare
		while(true) {
			for(Bot b : Bot.bots) {
				if(b.buffer.size() == 2) {
					//if target condition found, return ID
					if(b.buffer.contains(61) && b.buffer.contains(17)) {
						return Integer.toString(b.id);
					}
					b.pushValues();
					break;
				}
			}
		}
		
		
	}

	@Override
	public String part2() {
		//clear bot internal static fields
		Bot.bots.clear();
		Bot.outputBins.clear();
		
		//parse lines into arraylist and sort by alphabetical so
		//bots are created before recieving values
		ArrayList<String> lines = new ArrayList<String>();
		for(String s : input.split("\r\n"))
			lines.add(s);
		//alphabetic order (first character only)
		Collections.sort(lines,new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				return Integer.compare(o1.charAt(0),o2.charAt(0));
			}
		
		});
		
		for(String s : lines) {
			String[] parts = s.split(" ");
			if(parts[0].equals("value")) {
				//push value to bot
				Bot.find(Integer.parseInt(parts[5])).buffer.add(Integer.parseInt(parts[1]));
			} else {
				//create bot
				Bot.bots.add(new Bot(Integer.parseInt(parts[1]), Integer.parseInt(parts[6]), parts[5].equals("bot"), Integer.parseInt(parts[11]), parts[10].equals("bot")));
			}
		}
		
		//go over bot array, pushing result of bots that have both values necessary to compare
		while(!Bot.outputBins.containsKey(0) || !Bot.outputBins.containsKey(1) || !Bot.outputBins.containsKey(2)) {
			for(Bot b : Bot.bots) {
				if(b.buffer.size() == 2) {
					b.pushValues();
					break;
				}
			}
		}
			
		//return values of outputs 0, 1, and 2 multiplied
		return Integer.toString(Bot.outputBins.get(0) * Bot.outputBins.get(1) * Bot.outputBins.get(2));
	}

	public static void main(String[] args) {
		DayRunner.run(new Day10());
	}

}
