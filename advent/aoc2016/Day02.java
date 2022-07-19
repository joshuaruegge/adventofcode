package advent.aoc2016;

import advent.utilities.general.Coord;
import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;

public class Day02 implements IDay {

	String input = "RDLULDLDDRLLLRLRULDRLDDRRRRURLRLDLULDLDLDRULDDLLDRDRUDLLDDRDULLLULLDULRRLDURULDRUULLLUUDURURRDDLDLDRRDDLRURLLDRRRDULDRULURURURURLLRRLUDULDRULLDURRRLLDURDRRUUURDRLLDRURULRUDULRRRRRDLRLLDRRRDLDUUDDDUDLDRUURRLLUDUDDRRLRRDRUUDUUULDUUDLRDLDLLDLLLLRRURDLDUURRLLDLDLLRLLRULDDRLDLUDLDDLRDRRDLULRLLLRUDDURLDLLULRDUUDRRLDUDUDLUURDURRDDLLDRRRLUDULDULDDLLULDDDRRLLDURURURUUURRURRUUDUUURULDLRULRURDLDRDDULDDULLURDDUDDRDRRULRUURRDDRLLUURDRDDRUDLUUDURRRLLRR\r\n"
			+ "RDRRLURDDDDLDUDLDRURRLDLLLDDLURLLRULLULUUURLDURURULDLURRLRULDDUULULLLRLLRDRRUUDLUUDDUDDDRDURLUDDRULRULDDDLULRDDURRUURLRRLRULLURRDURRRURLDULULURULRRLRLUURRRUDDLURRDDUUDRDLLDRLRURUDLDLLLLDLRURDLLRDDUDDLDLDRRDLRDRDLRRRRUDUUDDRDLULUDLUURLDUDRRRRRLUUUDRRDLULLRRLRLDDDLLDLLRDDUUUUDDULUDDDUULDDUUDURRDLURLLRUUUUDUDRLDDDURDRLDRLRDRULRRDDDRDRRRLRDULUUULDLDDDUURRURLDLDLLDLUDDLDLRUDRLRLDURUDDURLDRDDLLDDLDRURRULLURULUUUUDLRLUUUDLDRUDURLRULLRLLUUULURLLLDULLUDLLRULRRLURRRRLRDRRLLULLLDURDLLDLUDLDUDURLURDLUURRRLRLLDRLDLDRLRUUUDRLRUDUUUR\r\n"
			+ "LLLLULRDUUDUUDRDUUURDLLRRLUDDDRLDUUDDURLDUDULDRRRDDLLLRDDUDDLLLRRLURDULRUUDDRRDLRLRUUULDDULDUUUDDLLDDDDDURLDRLDDDDRRDURRDRRRUUDUUDRLRRRUURUDURLRLDURDDDUDDUDDDUUDRUDULDDRDLULRURDUUDLRRDDRRDLRDLRDLULRLLRLRLDLRULDDDDRLDUURLUUDLLRRLLLUUULURUUDULRRRULURUURLDLLRURUUDUDLLUDLDRLLRRUUDDRLUDUDRDDRRDDDURDRUDLLDLUUDRURDLLULLLLUDLRRRUULLRRDDUDDDUDDRDRRULURRUUDLUDLDRLLLLDLUULLULLDDUDLULRDRLDRDLUDUDRRRRLRDLLLDURLULUDDRURRDRUDLLDRURRUUDDDRDUUULDURRULDLLDLDLRDUDURRRRDLDRRLUDURLUDRRLUDDLLDUULLDURRLRDRLURURLUUURRLUDRRLLULUULUDRUDRDLUL\r\n"
			+ "LRUULRRUDUDDLRRDURRUURDURURLULRDUUDUDLDRRULURUDURURDRLDDLRUURLLRDLURRULRRRUDULRRULDLUULDULLULLDUDLLUUULDLRDRRLUURURLLUUUDDLLURDUDURULRDLDUULDDRULLUUUURDDRUURDDDRUUUDRUULDLLULDLURLRRLRULRLDLDURLRLDLRRRUURLUUDULLLRRURRRLRULLRLUUDULDULRDDRDRRURDDRRLULRDURDDDDDLLRRDLLUUURUULUDLLDDULDUDUUDDRURDDURDDRLURUDRDRRULLLURLUULRLUDUDDUUULDRRRRDLRLDLLDRRDUDUUURLRURDDDRURRUDRUURUUDLRDDDLUDLRUURULRRLDDULRULDRLRLLDRLURRUUDRRRLRDDRLDDLLURLLUDL\r\n"
			+ "ULURLRDLRUDLLDUDDRUUULULUDDDDDRRDRULUDRRUDLRRRLUDLRUULRDDRRLRUDLUDULRULLUURLLRLLLLDRDUURDUUULLRULUUUDRDRDRUULURDULDLRRULUURURDULULDRRURDLRUDLULULULUDLLUURULDLLLRDUDDRRLULUDDRLLLRURDDLDLRLLLRDLDRRUUULRLRDDDDRUDRUULDDRRULLDRRLDDRRUDRLLDUDRRUDDRDLRUDDRDDDRLLRDUULRDRLDUDRLDDLLDDDUUDDRULLDLLDRDRRUDDUUURLLUURDLULUDRUUUDURURLRRDULLDRDDRLRDULRDRURRUDLDDRRRLUDRLRRRRLLDDLLRLDUDUDDRRRUULDRURDLLDLUULDLDLDUUDDULUDUDRRDRLDRDURDUULDURDRRDRRLLRLDLU";
	
	@Override
	public String part1() {
		//keypad constant
		final int[][] keypad = new int[][] {{1,4,7},{2,5,8},{3,6,9}};
		//answer buffer
		String answer = "";
		//iterate over lines of input
		for(String s : input.split("\r\n")) {
			//start in middle of keypad
			Coord position = new Coord(1,1);
			for(char c : s.toCharArray()) {
				//for each direction, move current position if possible
				//i.e. if not already at maximum in that direction
				switch(c) {
				case 'R':
					if(position.x < 2) {
						position.sumSelf(new Coord(1,0));
					}
					break;
				case 'L':
					if(position.x > 0) {
						position.sumSelf(new Coord(-1,0));
					}
					break;
				case 'U':
					if(position.y > 0)
						position.sumSelf(new Coord(0,-1));
					break;
				case 'D':
					if(position.y < 2)
						position.sumSelf(new Coord(0,1));
					break;
				}
			}
			//now, get character from keypad at location and append to answer string
			answer += keypad[position.x][position.y];
		}
		return answer;
	}

	@Override
	public String part2() {
		//now, keypad is slightly more complicated
		//use char instead of int for letters
		//use zeroes to represent locations that don't exist
		//additionally, array is flipped 90 degrees to facilitate (x,y) access
		final char[][] keypad = new char[][] {
			{'0','0','5','0','0'},
			{'0','2','6','A','0'},
			{'1','3','7','B','D'},
			{'0','4','8','C','0'},
			{'0','0','9','0','0'}
		};
		//same answer buffer
		String answer = "";
		//iterate over lines
		for(String s : input.split("\r\n")) {
			//still start at 5, but 5 has moved
			Coord position = new Coord(0,2);
			for(char c : s.toCharArray()) {
				//pre-grab x and y values for convenience
				int x = position.x;
				int y = position.y;
				switch(c) {
				case 'R':
					//if not going outside bounds of keypad
					if(x < 4 && !(keypad[x+1][y] == '0')) {
						position.sumSelf(new Coord(1,0));
					}
					break;
				case 'L':
					//if not going outside bounds of keypad
					if(x > 0 && !(keypad[x-1][y] == '0')) {
						position.sumSelf(new Coord(-1,0));
					}
					break;
				case 'U':
					if(y > 0 && !(keypad[x][y-1] == '0')) {
						position.sumSelf(new Coord(0,-1));
					}
					break;
				case 'D':
					if(y < 4 && !(keypad[x][y+1] == '0')) {
						position.sumSelf(new Coord(0,1));
					}
					break;
				}
			}
			//append final position on keypad to answer string
			answer += keypad[position.x][position.y];
		}
		return answer;
	}

	public static void main(String[] args) {
		DayRunner.run(new Day02());
	}

}
