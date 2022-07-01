package advent.aoc2019;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;

import advent.utilities.general.Coord;
import advent.utilities.general.Coord3;
import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.PathNode;
import advent.utilities.utils2019.Node3;

public class Day20 implements IDay {

	static String input = "                                     Q           I     B   S     J     P   J                                         \r\n"
			+ "                                     I           P     B   L     M     Q   H                                         \r\n"
			+ "  ###################################.###########.#####.###.#####.#####.###.#######################################  \r\n"
			+ "  #.#...#.........#.#...#.#...#.....#...#.#.#.#.#...#.....#.......#.....#.........#...#.........#.#.....#...#.....#  \r\n"
			+ "  #.#.###.###.###.#.###.#.###.#.#.#.#.###.#.#.#.#.#######.#.#.#.#.#.#.###.#.#####.#.#.#.#########.#.#####.###.###.#  \r\n"
			+ "  #.........#.#.................#.#...#.....#.#.#.#...#...#.#.#.#.#.#...#.#...#.....#.#.............#.....#...#.#.#  \r\n"
			+ "  ###############.###.#.###.###.###.###.#####.#.#.#.#####.#####.#.#.#########.#########.###.#####.#####.#.#.#.#.###  \r\n"
			+ "  #.............#.#...#.#.....#.#.........#.#...#.....#.......#.#.#.......#.......#...#.#...#...#.#.#...#...#.#.#.#  \r\n"
			+ "  #########.###.#######.#.#.#######.#####.#.#.#.#.#######.###.###.#####.#.#.###.###.###.#####.#####.#.###.#####.#.#  \r\n"
			+ "  #.........#.........#.#.#.#...#...#.....#...#.#.#...#.#...#.#.#.#...#.#.#.#.#.#...#.#...#...#.......#.#.#...#.#.#  \r\n"
			+ "  ###############.###.#######.#########.#.#.###.#.###.#.#.###.#.#.#.#.###.#.#.#####.#.#.###.###.#######.#####.#.#.#  \r\n"
			+ "  #.#...#.#.#.....#.......#.....#.#...#.#.#.#.......#.#.....#.#...#.#.....#...#.#.#.#...#.#.....#.#.#.......#.#.#.#  \r\n"
			+ "  #.#.###.#.#.#########.#######.#.#.###.###.###.#.#.#.###.###.###.#.###.###.#.#.#.#.#.###.#.#.#.#.#.#.#######.#.#.#  \r\n"
			+ "  #...........#.........#.#.#...........#.....#.#.#.#.......#.#...#.#.#.#...#...............#.#.#.........#.......#  \r\n"
			+ "  #######.#########.###.#.#.#######.#######.###.#.#.###.#.#######.#.#.###.#######.#######.#######.#########.#####.#  \r\n"
			+ "  #.#...#.#...#.#.#.#.........#...#.......#...#.#.#.#.#.#...#.#...#.....#...#.....#...#.#.#...#.#.#.#.#.#.......#.#  \r\n"
			+ "  #.#.#######.#.#.#####.###.#.###.#.#####.#.#####.#.#.#.#.###.#.#.#.###.#####.###.#.#.#.###.###.#.#.#.#.###.#.#####  \r\n"
			+ "  #.#.......#.#.........#...#.........#.#.#.#.#...#...#.#...#...#.#.#...#.....#.#...#.#.#...#...#.......#.#.#.#.#.#  \r\n"
			+ "  #.#.#.#.###.###.#.#.#.#####.#.#.#####.#.#.#.###########.#####.#.#.#######.###.#.#.###.###.#.###.#######.#.###.#.#  \r\n"
			+ "  #.#.#.#...#.#.#.#.#.#.#.#...#.#.#.......#.#.#.#.#.#.#...#.....#.#...#...#.....#.#.#.#.....#.#...........#...#...#  \r\n"
			+ "  #.#####.###.#.#########.#.###.#######.#.#.#.#.#.#.#.###.###.###.###.#.#####.#####.#.#####.#.###.###.###.#.###.#.#  \r\n"
			+ "  #.#.........#.#.#.#.#...#.#.#.....#.#.#.#...#...#.......#...#.....#...#.#.......#.#.#.#.....#.....#...#.#.....#.#  \r\n"
			+ "  #.#####.#####.#.#.#.#.#####.#######.###.#.#.#.#.###.###.###.###.###.###.#.#########.#.###.#####.#.#######.#######  \r\n"
			+ "  #.#...#.#.#.#...#.....#.#.#.#.#.#.#.#.#.#.#...#.#.....#.#.#.#.....#.....#...#.........#.#.#.#.#.#.#.#.#...#...#.#  \r\n"
			+ "  #.#.###.#.#.#.#######.#.#.#.#.#.#.#.#.#.###.###.#.#####.#.#####.#######.###.#.#.#.#####.#.#.#.#.###.#.###.#.#.#.#  \r\n"
			+ "  #.#...........#...#.#.....#.#.#.........#...#...#.....#.#...#.#...#.....#.....#.#.#.#...#...#.....#...#.....#...#  \r\n"
			+ "  #.#.###.#########.#.#.#####.#.###.#.###.###.#.#####.#####.###.#.#.#.#.###.#####.###.###.#.###.#####.#####.#######  \r\n"
			+ "  #...#.#.....#.#...#.#.#.....#.#.#.#.#.....#.#...#.......#.......#.#.#...#.....#.#.........#.#.#.#.....#.#...#.#.#  \r\n"
			+ "  ###.#.#######.#.###.#.#####.#.#.#######.#.###.###.#.#####.#########.###.#.#########.###.###.#.#.#.#####.#.###.#.#  \r\n"
			+ "  #.#.#.......#.....#.#...#.#.....#.#.....#.#...#.#.#.#.......#...#.#...#.#.#.#.....#.#.#...#.#.......#.......#.#.#  \r\n"
			+ "  #.#.#.#######.###.#.#.###.#.#####.###.#.#####.#.###.#.#####.#.#.#.#.#.###.#.###.#####.###.#.#.###.###.#.#####.#.#  \r\n"
			+ "  #...#.#.#...#...#.#...#...#.#.#.......#...#.....#...#...#.....#...#.#.#.............#.#.#.#...#.#.#.#.#.....#...#  \r\n"
			+ "  ###.#.#.###.#####.#.###.###.#.#########.#######.#.#######.#####.###.#########.#######.#.#.#.#.#.###.###.#######.#  \r\n"
			+ "  #.......#.....#.#.#...#.#...#.#        J       Q E       P     W   J         M    #.....#...#.#.#...#.....#.....#  \r\n"
			+ "  ###.#######.#.#.#.#.###.###.#.#        M       I A       Q     C   K         Q    ###.###.#####.#.#.###.#####.###  \r\n"
			+ "  #...#.#.#...#.#.#.............#                                                   #...#.........#.#.............#  \r\n"
			+ "  ###.#.#.###.###.###.#######.#.#                                                   #.#.#.#####.#####.###.###.#.###  \r\n"
			+ "SF........#.......#.#...#.#...#.#                                                 YL..#...#...#.#...#...#.#.#.#.#..WC\r\n"
			+ "  #.###.#########.#.###.#.#.#####                                                   #.###.#.#.#.#.#########.#.#.#.#  \r\n"
			+ "  #.#.......#...#.....#...#.....#                                                   #.#...#.#...#.#.#.#.#.....#...#  \r\n"
			+ "  #.#####.###.#.#.###.#.#######.#                                                   #.#.#.#.#####.#.#.#.#.#######.#  \r\n"
			+ "  #.#...#.....#...#.......#......SL                                                 #.#.#.#.................#.#.#.#  \r\n"
			+ "  #####.#########################                                                   #.#######################.#.###  \r\n"
			+ "  #...................#...#...#.#                                                   #.#...................#.......#  \r\n"
			+ "  ###.#.###.#####.###.#.#.###.#.#                                                   #####.###.###.#######.#.#.#.#.#  \r\n"
			+ "YY..#.#.#.......#.#.....#...#....OB                                               VN..#.#.#.#.#.......#.....#.#.#.#  \r\n"
			+ "  #.#.#######.###.#.#####.#####.#                                                   #.#.#.#.#####.###.###.#.#####.#  \r\n"
			+ "  #.#...#.#...#.#.#.#.....#.#...#                                                   #.......#.....#.#.#.#.#.....#..HV\r\n"
			+ "  #.#.#.#.###.#.###.#####.#.###.#                                                   #####.#######.#.###.###########  \r\n"
			+ "  #...#...#.#...#.....#.........#                                                   #...#.#.#.......#.....#.#...#.#  \r\n"
			+ "  #####.###.###############.#####                                                   #.#.###.#####.#.###.#.#.#.###.#  \r\n"
			+ "  #.#.#.#.#...#...#.#.#...#.#...#                                                 SF..#.#.......#.#.#.#.#.#...#...#  \r\n"
			+ "  #.#.###.#.###.###.#.#.#.#####.#                                                   #.#.###.#########.#.#.#.#.#.#.#  \r\n"
			+ "  #.#...........#.#.#...#........HI                                                 #.#.....#...#...#...#.#.#.#.#..EA\r\n"
			+ "  #.#.#####.#####.#.###.#####.#.#                                                   #######.#.###.#####.#.#.#.#.###  \r\n"
			+ "YL..#...#...#...#.#.#.....#.#.#.#                                                   #.#...#.............#...#...#.#  \r\n"
			+ "  #.#.###.###.###.#.###.###.#.###                                                   #.#.#########.###.#.###.#.###.#  \r\n"
			+ "  #.....#.................#.....#                                                   #.#.....#...#...#.#.#...#.#...#  \r\n"
			+ "  #####################.#####.###                                                   #.#.#.#.#.###########.#.###.#.#  \r\n"
			+ "ZZ............#.......#...#.#...#                                                   #...#.#...#.#.#.#...#.#.#...#.#  \r\n"
			+ "  #.#######.#.#.#####.#####.#####                                                   #.#.###.###.#.#.#.#########.#.#  \r\n"
			+ "  #...#.#...#.....#.....#.....#.#                                                 EJ..#.#.....#.#...#.#.....#...#.#  \r\n"
			+ "  #.###.#############.#####.###.#                                                   #########.#.###.#.###.###.#.#.#  \r\n"
			+ "  #.......#.....#.#.#...#.....#..PD                                                 #.........................#.#..PD\r\n"
			+ "  #.###.###.#.###.#.###.#.#.###.#                                                   #######################.#.#####  \r\n"
			+ "MQ..#.#...#.#.#.#...#.....#.....#                                                   #...#.#.#.......#.....#.#.....#  \r\n"
			+ "  ###.#######.#.#.###############                                                   #.###.#.#.#####.#.###.#########  \r\n"
			+ "  #.......#...#.......#.........#                                                   #.#.#...#...#.....#...#...#...#  \r\n"
			+ "  #.#.###.#.#.#.###.#.#.#.###.#.#                                                   #.#.###.###.###.#.#.#####.###.#  \r\n"
			+ "OB..#.#.....#...#...#...#.#...#.#                                                 PT....#...#.....#.#.#.#...#...#..LH\r\n"
			+ "  #########.#.###.###.#########.#                                                   #.#.#.#.#.#####.###.#.#.#.###.#  \r\n"
			+ "  #.....#.#.#.#...#.......#.#.#..GJ                                                 #.#...#.......#...#...#.......#  \r\n"
			+ "  ###.###.#################.#.###                                                   #.#####.#.#.###################  \r\n"
			+ "  #...#.......#.#...#.#.#.......#                                                   #.#...#.#.#.#.#.........#.....#  \r\n"
			+ "  #.#.#.#####.#.#.###.#.###.#####                                                   #.###.#######.#####.#.#.#.###.#  \r\n"
			+ "  #.#.#.#...#.......#.#.#.#...#.#                                                   #...#...#.....#.#...#.#.#...#.#  \r\n"
			+ "  #.#.#.#.#.###.#####.#.#.###.#.#                                                   #####.###.###.#.#.###.#.###.#.#  \r\n"
			+ "DU..#.#...#.#.....#.........#...#                                                   #.......#.#...#.#...#.#.#...#..BC\r\n"
			+ "  ###.#####.#.#######.#.#####.###                                                   ###.#.#.#####.#.###.###.#.#.#.#  \r\n"
			+ "  #.#.......#.........#..........LH                                               YY....#.#...............#...#.#.#  \r\n"
			+ "  #.#####.#.#.###.#####.#####.#.#                                                   #.#####.###.#.#################  \r\n"
			+ "  #...#.#.#.#.#.#.#.#.......#.#.#                                                   #...#.....#.#.#...#.........#.#  \r\n"
			+ "  #.#.#.#######.###.#.###.#######                                                   ###.#############.###.#####.#.#  \r\n"
			+ "GJ..#...#.#.......#...#.#.#...#.#                                                 HV..#.#.#.#...#.....#...#...#.#.#  \r\n"
			+ "  ###.###.#####.#.#####.#####.#.#                                                   #.###.#.###.#####.###.#.#.#.#.#  \r\n"
			+ "  #...#.#...#...#.#.#.#...#.#...#                                                   #.......#.....#.#...#...#.#.#..EJ\r\n"
			+ "  #.###.###.#####.#.#.###.#.#.#.#                                                   #.###.#####.###.#.#.#####.#.#.#  \r\n"
			+ "  #...........................#..BB                                                 #...#.............#.......#...#  \r\n"
			+ "  ###.###.#.#.#.#.#.#.###.#.###.#                                                   #.###.#######.#####.###.#.#.#.#  \r\n"
			+ "  #...#...#.#.#.#.#.#...#.#...#.#                                                   #.#.#.#.#.#.....#.#...#.#.#.#.#  \r\n"
			+ "  #.#.###.###########.#.#.###.###        J     B         I   D       P       F      #.#.#.#.#.###.###.#.#####.#.###  \r\n"
			+ "  #.#...#.....#.#.#...#.#.#.#.#.#        H     C         P   U       A       K      #.#.....#.#...#.........#.#...#  \r\n"
			+ "  #.#####.#.###.#.#####.#.#.###.#########.#####.#########.###.#######.#######.#############.#.#.#.#.###.###.#.#####  \r\n"
			+ "  #...#...#.....#.......#...#.#.#.........#.....#.#.....#.#...#.....#.#...#...........#.....#...#.#...#.#...#.....#  \r\n"
			+ "  #.#######.###.#########.###.#.#.###.###.###.###.#.###.#.#.###.#####.#.#.###.###.###.#####.###.#.#.###.###########  \r\n"
			+ "  #.....#...#.#.....#.#.....#...#...#...#...#.....#...#...#.....#.#...#.#.#...#...#.#...#.....#.#.#.#.........#...#  \r\n"
			+ "  #.#.###.###.###.###.#########.#.###.###.#####.###.#.#.###.###.#.###.#.#.#####.###.#.#####.#.#########.#####.#.###  \r\n"
			+ "  #.#.#.......#...#.......#.......#.#...#.#.#.#.#.#.#.#.#...#.#.....#...#.#.#...#.#.#.....#.#.....#.#.......#.....#  \r\n"
			+ "  ###.#.###.#.#######.###.#.###.###.###.###.#.#.#.#.#.#.#.###.###.###.###.#.###.#.#.#.#.#.#.#.#####.#.#.###.#.#.###  \r\n"
			+ "  #...#...#.#...#.....#.......#.#.....#...#.....#...#.#.#.#.........#.#.....#...#.#.#.#.#.#.#.....#...#...#.#.#.#.#  \r\n"
			+ "  #.#.###########.###########.#####.#.###.#.#######.###.#.###.#######.#.#######.#.#.#.#.#######.#.###.###.#.#.#.#.#  \r\n"
			+ "  #.#.........#...#.#.........#...#.#.....#.#...#.#...#.#.#.#.....#.#.#...#.#.......#.#...#.....#...#.#...#.#.#...#  \r\n"
			+ "  ###.#.#.###.#####.#.###.###.###.#.#.#####.#.#.#.#.#######.#.#.#.#.#####.#.#####.#####.#.###.#.#.###.#########.#.#  \r\n"
			+ "  #...#.#.#.#.#.#.....#...#.#.#.....#.#.#.....#...#.....#.....#.#.....#...#...........#.#...#.#.#.#.#...#.......#.#  \r\n"
			+ "  #.#.#####.###.#####.#.#.#.#######.###.###.#####.#.#############.#####.###.#.#.#.#####.#########.#.###.#########.#  \r\n"
			+ "  #.#...#.#...........#.#.#.#.#...#.....#.....#.#.#.#.....#.#...#.#.......#.#.#.#.#...#.#...#.........#.......#...#  \r\n"
			+ "  #.#.#.#.###########.#####.#.#.###.#.#########.#.#.#.#####.###.#.#.###.###.#######.#######.#.#.###.###.#####.###.#  \r\n"
			+ "  #.#.#...#...#.........#.#.........#.....#.#.....#.#.#...#.#.#...#...#.#...................#.#.#...#...#.....#...#  \r\n"
			+ "  ###.###.#.#####.#.#.###.#####.###.#######.###.#.#.###.#.#.#.#.###.###.#.#.###.###.###.#######.###.###.###.#####.#  \r\n"
			+ "  #.....#.#.......#.#.#...#.#...#.......#...#...#.#.....#.#...#.#...#.#.#.#...#.#.....#.....#.#.#.#...#.#.......#.#  \r\n"
			+ "  #.#####.#####.#.#######.#.#.#.###.#####.#######.#######.#.#.#.###.#.#####.#######.###.#####.###.#####.###.#.#.#.#  \r\n"
			+ "  #.#.#.#.#.#...#.#.......#...#...#.........#.#.....#.....#.#.....#...#...#...#.#.....#.#.#...#.....#.#.#...#.#.#.#  \r\n"
			+ "  #.#.#.#.#.#.###.###.#.###.#############.###.###.###.#.###.#######.#####.#.###.#########.#.#####.###.#####.###.#.#  \r\n"
			+ "  #.....#.#...#...#...#.......#.............#...#.#...#...#.#.....#.#.....#...#.#.#.#.#...#...#.#.#.#.#.#.#...#.#.#  \r\n"
			+ "  #.###.#.#######.###.###.###.#####.###########.#.###.#.###.###.#.#.#####.#.#.#.#.#.#.###.#.###.#.#.#.#.#.#.###.###  \r\n"
			+ "  #...#.#...#.#...#...#.#.#.#...#...#.......#.#.....#.#.#.......#.#.......#.#.#.#.#.....#.#...#.#.......#.....#...#  \r\n"
			+ "  #.#########.#########.###.#.###.#.#.#.#.###.#.#.###.###.###.###.###.#.#####.#.#.#.#####.#.###.#.#######.#####.###  \r\n"
			+ "  #.....#.#.....................#.#.#.#.#.#.....#.#.....#.#...#.....#.#...#.#.....#.#...#...#.#.......#.#...#.....#  \r\n"
			+ "  #.#.###.#.###.###.###.#.#########.###.#.###.###.###.#.#######.#######.###.#####.#.#.###.###.#.###.###.#.###.#####  \r\n"
			+ "  #.#.....#.#.....#.#.#.#.#.............#.#...#.....#.#...#...#.......#.#.........................#.....#.#.......#  \r\n"
			+ "  #.#.#####.#.###.#.#.#.#.#######.#.#####.###.#.#.###.#.#.#.#.###.#####.#####.###.#.#.###.#####.###.#.#.#######.#.#  \r\n"
			+ "  #.#.....#.#.#...#.#...#.#.......#.....#...#.#.#.#...#.#.#.#.......#.....#.....#.#.#.#.......#...#.#.#.....#...#.#  \r\n"
			+ "  #######################################.#######.#.#.#####.###########.#######.###################################  \r\n"
			+ "                                         P       V P A     J           F       H                                     \r\n"
			+ "                                         A       N T A     K           K       I                                     ";
	static String input2 = "             Z L X W       C                 \r\n"
			+ "             Z P Q B       K                 \r\n"
			+ "  ###########.#.#.#.#######.###############  \r\n"
			+ "  #...#.......#.#.......#.#.......#.#.#...#  \r\n"
			+ "  ###.#.#.#.#.#.#.#.###.#.#.#######.#.#.###  \r\n"
			+ "  #.#...#.#.#...#.#.#...#...#...#.#.......#  \r\n"
			+ "  #.###.#######.###.###.#.###.###.#.#######  \r\n"
			+ "  #...#.......#.#...#...#.............#...#  \r\n"
			+ "  #.#########.#######.#.#######.#######.###  \r\n"
			+ "  #...#.#    F       R I       Z    #.#.#.#  \r\n"
			+ "  #.###.#    D       E C       H    #.#.#.#  \r\n"
			+ "  #.#...#                           #...#.#  \r\n"
			+ "  #.###.#                           #.###.#  \r\n"
			+ "  #.#....OA                       WB..#.#..ZH\r\n"
			+ "  #.###.#                           #.#.#.#  \r\n"
			+ "CJ......#                           #.....#  \r\n"
			+ "  #######                           #######  \r\n"
			+ "  #.#....CK                         #......IC\r\n"
			+ "  #.###.#                           #.###.#  \r\n"
			+ "  #.....#                           #...#.#  \r\n"
			+ "  ###.###                           #.#.#.#  \r\n"
			+ "XF....#.#                         RF..#.#.#  \r\n"
			+ "  #####.#                           #######  \r\n"
			+ "  #......CJ                       NM..#...#  \r\n"
			+ "  ###.#.#                           #.###.#  \r\n"
			+ "RE....#.#                           #......RF\r\n"
			+ "  ###.###        X   X       L      #.#.#.#  \r\n"
			+ "  #.....#        F   Q       P      #.#.#.#  \r\n"
			+ "  ###.###########.###.#######.#########.###  \r\n"
			+ "  #.....#...#.....#.......#...#.....#.#...#  \r\n"
			+ "  #####.#.###.#######.#######.###.###.#.#.#  \r\n"
			+ "  #.......#.......#.#.#.#.#...#...#...#.#.#  \r\n"
			+ "  #####.###.#####.#.#.#.#.###.###.#.###.###  \r\n"
			+ "  #.......#.....#.#...#...............#...#  \r\n"
			+ "  #############.#.#.###.###################  \r\n"
			+ "               A O F   N                     \r\n"
			+ "               A A D   M                     ";
	
	static HashSet<Coord> pathPositions = new HashSet<Coord>();
	static HashMap<Coord,Coord> portals = new HashMap<Coord,Coord>();
	static ArrayList<String> inputMap = new ArrayList<String>();
	
	static Coord start, end;
	
	public static void main(String[] args) {
		DayRunner.run(new Day20());
	}
	
	//quick BFS from point to point, including portals
	public static int path(Coord start, Coord end) {		
		//BFS arrays
		LinkedList<PathNode> queue = new LinkedList<PathNode>();
		HashSet<PathNode> closed = new HashSet<PathNode>();
		//final path
		ArrayList<Coord> path = null;
		//enqueue start
		queue.add(new PathNode(start,0,0));
		while(queue.size() > 0) {
			//remove head of queue
			PathNode cur = queue.poll();
			Coord curPos = cur.pos;
			//arrived at end!! build path list, then break
			if(curPos.equals(end)) {
				return cur.path.size();
			}
			//append portal adjacency to queue first
			if(portals.containsKey(curPos)) {
				PathNode newNode = new PathNode(cur,portals.get(curPos),cur.gcost + 1,0);
				newNode.path.add(newNode);
				if(!closed.contains(newNode) && !queue.contains(newNode))
					queue.add(newNode);
			}
			//append neighbors to queue
			for(int xOff = -1; xOff < 2; xOff++) {
				for(int yOff = -1; yOff < 2; yOff++) {
					if(xOff == 0 ^ yOff == 0) {
						Coord neighbor = new Coord(curPos.x + xOff, curPos.y + yOff);
						if(pathPositions.contains(neighbor)) {
							PathNode newNode = new PathNode(cur,neighbor,cur.gcost + 1,0);
							newNode.path.add(newNode);
							if(!closed.contains(newNode) && !queue.contains(newNode)) {
								queue.add(newNode);
							}							
						}
					}
				}
			}				
			closed.add(cur);
		}		
		//if no path was found, return -1
		return -1;
	}
	
	//expanded BFS, using a 3-dimensional coordinate to represent (x,y,depth)
	public static int path2(Coord3 start, Coord3 end) {
		ArrayList<Node3> open = new ArrayList<Node3>();
		HashSet<Node3> closed = new HashSet<Node3>();
		open.add(new Node3(start,0,0));
		while(open.size() > 0) {
			//speed up by exploring lower depths first (this technically means it's more A* than BFS,
			//but it doesn't modify linear search order, only recursive depth search order)
			Collections.sort(open, new Comparator<Node3> () {

				@Override
				public int compare(Node3 o1, Node3 o2) {
					return Integer.compare(o1.pos.z, o2.pos.z);
				}
			
			});
			
			Node3 cur = open.remove(0);
			Coord3 curPos = cur.pos;
			
			//no need to ever descend more layers than the total number of portals
			//since portals has each portal mapped in both directions, this means size / 2
			if(curPos.z > portals.size() / 2) {
				closed.add(cur);
				continue;
			}
			if(cur.pos.equals(end)) {
				return cur.gcost;
			}
			//portals first
			//portal has 2 dimensional keys, so extract 2 dimensional position
			Coord curPosFlat = new Coord(curPos.x,curPos.y);
			if(portals.containsKey(curPosFlat)) {
				Coord neighborFlat = portals.get(curPosFlat);
				Coord3 neighbor = null;
				//if outer layer portal, travel up if coordinate is less than zero
				if(outerPortal(curPosFlat)) {
					if(curPos.z > 0) {
						neighbor = new Coord3(neighborFlat.x,neighborFlat.y,curPos.z - 1);
					}
				//if inner portal, travel down (extra check for maximum depth case here)
				} else {
					if(curPos.z < portals.size() / 2) 
						neighbor = new Coord3(neighborFlat.x,neighborFlat.y,curPos.z + 1);
				}
				//if neighbor was properly set (not moving out of bounds up or down)
				if(neighbor != null) {
					Node3 newNode = new Node3(neighbor,cur.gcost+1,0);
					if(!closed.contains(newNode) && !open.contains(newNode)) {
						open.add(newNode);
					} 
				}
			}
			//add linear neighbors
			for(int xOff = -1; xOff < 2; xOff++) {
				for(int yOff = -1; yOff < 2; yOff++) {
					if((xOff == 0 || yOff == 0)) {
						Coord neighborFlat = new Coord(curPos.x+xOff,curPos.y+yOff);
						if(pathPositions.contains(neighborFlat)) {
							Coord3 neighbor = new Coord3(neighborFlat.x,neighborFlat.y,curPos.z);
							Node3 newNode = new Node3(neighbor,cur.gcost+1,0);
							if(!closed.contains(newNode) && !open.contains(newNode)) {
								open.add(newNode);
							} 
							
						}
					}
				}
			}
			closed.add(cur);
			
		}
		return -1;
	}
	
	public static boolean outerPortal(Coord c) {
		return c.x == 0 || c.x == inputMap.get(0).length() - 1 || c.y == 0 || c.y == inputMap.size() - 1;
	}

	@Override
	public String part1() {
		Scanner scan = new Scanner(input);
		//keep track of portal identifiers as they are encountered, to assign them a numeric value based on index in this list
		ArrayList<String> portalCodes = new ArrayList<String>();
		
		//parse input into String arraylist to get a feel for x and y bounds
		while(scan.hasNextLine()) {
			inputMap.add(scan.nextLine());
		}
		
		//we replace locations of matching portals with an ASCII character within the lowercase range to later clean them up during the conversion pass
		
		//top-down portal code replace
		for(int i = 0; i < inputMap.size() - 2; i++) {
			for(int j = 0; j < inputMap.get(i).length() - 2; j++) {
				char a = inputMap.get(i).charAt(j);
				char b = inputMap.get(i+1).charAt(j);
				char c = inputMap.get(i+2).charAt(j);
				//letter, letter, path
				if(a > 64 && a < 91 && b > 64 && b < 91 && c == '.') {
					String code = a + "" + b;
					if(!portalCodes.contains(code))
						portalCodes.add(code);
					//just replace path, we'll take out uppercase letters on a later pass
					String replace = inputMap.get(i+2).substring(0,j) + (char) (portalCodes.indexOf(code) + 91) + inputMap.get(i+2).substring(j+1);
					inputMap.set(i+2, replace);
					
				}
			}
		}
		
		//bottom-top replace
		for(int i = 2; i < inputMap.size(); i++) {
			for(int j = 0; j < inputMap.get(i).length() - 2; j++) {
				char c = inputMap.get(i).charAt(j);
				char b = inputMap.get(i-1).charAt(j);
				char a = inputMap.get(i-2).charAt(j);
				//path, letter, letter
				if(a == '.' && b > 64 && b < 91 && c > 64 && c < 91) {
					String code = b + "" + c;
					if(!portalCodes.contains(code))
						portalCodes.add(code);
					String replace = inputMap.get(i-2).substring(0,j) + (char) (portalCodes.indexOf(code) + 91) + inputMap.get(i-2).substring(j+1);
					inputMap.set(i-2,replace);
					
				}
			}
		}
		//left-right
		
		for(int i = 0; i < inputMap.size(); i++) {
			for(int j = 0; j < inputMap.get(i).length() - 2; j++) {
				char a = inputMap.get(i).charAt(j);
				char b = inputMap.get(i).charAt(j+1);
				char c = inputMap.get(i).charAt(j+2);
				//letter, letter, path
				if(c == '.' && a > 64 && a < 91 && b > 64 && b < 91) {
					String code = a + "" + b;
					if(!portalCodes.contains(code))
						portalCodes.add(code);
					String replace = inputMap.get(i).substring(0,j) + "  " + (char) (portalCodes.indexOf(code) + 91) + inputMap.get(i).substring(j+3);
					inputMap.set(i, replace);
					
				}
			}
		}
		//right-left
	
		for(int i = 0; i < inputMap.size(); i++) {
			for(int j = 2; j < inputMap.get(i).length(); j++) {
				char a = inputMap.get(i).charAt(j);
				char b = inputMap.get(i).charAt(j-1);
				char c = inputMap.get(i).charAt(j-2);
				//path, letter, letter
				if(c == '.' && a > 64 && a < 91 && b > 64 && b < 91) {
					String code = b + "" + a;
					if(!portalCodes.contains(code)) {
						portalCodes.add(code);
					}
					String replace = inputMap.get(i).substring(0,j-2) + (char) (portalCodes.indexOf(code) + 91) + "  " + inputMap.get(i).substring(j+1);
					inputMap.set(i, replace);
					
				}
					
			}
		}
		
		//remove remaining uppercase letters
		for(int i = 0; i < inputMap.size(); i++) {
			inputMap.set(i, inputMap.get(i).replaceAll("[A-Z]", " "));
		}
		//trim whitespace now that portals are gone
		for(int i = 0; i < inputMap.size(); i++) {
			String trim = inputMap.get(i).trim();
			if(trim.equals("")) {
				inputMap.remove(i);
				i--;
				continue;
			}
			inputMap.set(i, trim);
		}
		//add all portals to a temporary map
		HashMap<Coord,Character> tempPortals = new HashMap<Coord,Character>();
		start = null;
		end = null;
		for(int y = 0; y < inputMap.size(); y++) {
			String line = inputMap.get(y);
			for(int x = 0; x < line.length(); x++) {
				char c = line.charAt(x);
				//paths go in path positions
				if(c == '.') {
					pathPositions.add(new Coord(x,y));
				//skip wall and whitespace
				} else if (c == ' ' || c == '#') {
					continue;
				//entrance
				} else if (portalCodes.get(c-91).equals("AA")){
					start = new Coord(x,y);
					pathPositions.add(new Coord(x,y));
				//exit
				} else if (portalCodes.get(c-91).equals("ZZ")) {
					end = new Coord(x,y);
					pathPositions.add(new Coord(x,y));
				//other portal
				} else {
					tempPortals.put(new Coord(x,y), c);
					pathPositions.add(new Coord(x,y));
				}
			}
		}
		//now, perform portal matching
		portal:
		while(tempPortals.keySet().size() > 0) {
			for(Coord c : tempPortals.keySet()) {
				char code = tempPortals.get(c);
				for(Coord d : tempPortals.keySet()) {
					//different locations, matching portal code
					if(!c.equals(d) && tempPortals.get(d) == code) {
						//make portals bidirectional just in case
						portals.put(c, d);
						portals.put(d, c);
						tempPortals.remove(c);
						tempPortals.remove(d);
						continue portal;
					}
				}
			}
		}
		return Integer.toString(path(start,end));
	}

	@Override
	public String part2() {
		return Integer.toString(path2(new Coord3(start.x,start.y,0), new Coord3(end.x,end.y,0)));
	}
	
}
