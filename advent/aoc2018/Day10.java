package advent.aoc2018;

import java.util.ArrayList;

import advent.utilities.general.Coord;
import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;

public class Day10 implements IDay {

	String input = "position=< 53050, -42120> velocity=<-5,  4>\r\n"
			+ "position=<-21031, -52696> velocity=< 2,  5>\r\n"
			+ "position=<-42187, -31543> velocity=< 4,  3>\r\n"
			+ "position=< 31896, -10392> velocity=<-3,  1>\r\n"
			+ "position=<-42187, -42121> velocity=< 4,  4>\r\n"
			+ "position=<-52704,  42488> velocity=< 5, -4>\r\n"
			+ "position=< 53041, -52700> velocity=<-5,  5>\r\n"
			+ "position=<-10420, -20969> velocity=< 1,  2>\r\n"
			+ "position=< 31856,  53065> velocity=<-3, -5>\r\n"
			+ "position=<-42134,  21335> velocity=< 4, -2>\r\n"
			+ "position=<-31557, -52698> velocity=< 3,  5>\r\n"
			+ "position=<-52752,  10758> velocity=< 5, -1>\r\n"
			+ "position=<-42174,  42495> velocity=< 4, -4>\r\n"
			+ "position=< 21276, -20974> velocity=<-2,  2>\r\n"
			+ "position=<-42139,  10759> velocity=< 4, -1>\r\n"
			+ "position=<-10451,  21343> velocity=< 1, -2>\r\n"
			+ "position=< 53048, -10388> velocity=<-5,  1>\r\n"
			+ "position=<-42155,  21343> velocity=< 4, -2>\r\n"
			+ "position=<-42170, -20974> velocity=< 4,  2>\r\n"
			+ "position=<-10440,  10766> velocity=< 1, -1>\r\n"
			+ "position=< 42487, -10388> velocity=<-4,  1>\r\n"
			+ "position=< 10742,  42492> velocity=<-1, -4>\r\n"
			+ "position=<-10395, -31543> velocity=< 1,  3>\r\n"
			+ "position=< 10711, -42121> velocity=<-1,  4>\r\n"
			+ "position=< 10698,  31919> velocity=<-1, -3>\r\n"
			+ "position=< 53022, -10390> velocity=<-5,  1>\r\n"
			+ "position=<-31582,  10765> velocity=< 3, -1>\r\n"
			+ "position=< 21328, -20966> velocity=<-2,  2>\r\n"
			+ "position=<-20982,  21339> velocity=< 2, -2>\r\n"
			+ "position=<-42167,  10757> velocity=< 4, -1>\r\n"
			+ "position=< 31860, -52699> velocity=<-3,  5>\r\n"
			+ "position=< 53058, -10389> velocity=<-5,  1>\r\n"
			+ "position=<-21030, -20974> velocity=< 2,  2>\r\n"
			+ "position=<-21033, -20965> velocity=< 2,  2>\r\n"
			+ "position=<-52748,  53068> velocity=< 5, -5>\r\n"
			+ "position=< 21319,  53067> velocity=<-2, -5>\r\n"
			+ "position=< 31852,  53072> velocity=<-3, -5>\r\n"
			+ "position=<-10456,  21334> velocity=< 1, -2>\r\n"
			+ "position=<-20982,  10766> velocity=< 2, -1>\r\n"
			+ "position=<-42178,  42493> velocity=< 4, -4>\r\n"
			+ "position=<-42182, -10395> velocity=< 4,  1>\r\n"
			+ "position=< 31860, -42120> velocity=<-3,  4>\r\n"
			+ "position=<-21016, -20965> velocity=< 2,  2>\r\n"
			+ "position=<-42187, -20967> velocity=< 4,  2>\r\n"
			+ "position=< 10751, -10391> velocity=<-1,  1>\r\n"
			+ "position=< 53043, -10394> velocity=<-5,  1>\r\n"
			+ "position=<-42136,  21343> velocity=< 4, -2>\r\n"
			+ "position=< 10746, -42124> velocity=<-1,  4>\r\n"
			+ "position=<-31609,  21334> velocity=< 3, -2>\r\n"
			+ "position=< 31885, -31550> velocity=<-3,  3>\r\n"
			+ "position=< 10730,  31916> velocity=<-1, -3>\r\n"
			+ "position=< 21333, -20965> velocity=<-2,  2>\r\n"
			+ "position=<-10431, -42124> velocity=< 1,  4>\r\n"
			+ "position=< 53030,  10760> velocity=<-5, -1>\r\n"
			+ "position=< 42455,  31917> velocity=<-4, -3>\r\n"
			+ "position=<-10451,  53067> velocity=< 1, -5>\r\n"
			+ "position=<-42168,  42497> velocity=< 4, -4>\r\n"
			+ "position=<-31609,  10766> velocity=< 3, -1>\r\n"
			+ "position=<-10443,  53073> velocity=< 1, -5>\r\n"
			+ "position=< 10714,  10766> velocity=<-1, -1>\r\n"
			+ "position=<-52716, -10393> velocity=< 5,  1>\r\n"
			+ "position=<-42131,  42494> velocity=< 4, -4>\r\n"
			+ "position=<-52716, -20969> velocity=< 5,  2>\r\n"
			+ "position=< 31888,  42493> velocity=<-3, -4>\r\n"
			+ "position=<-10412, -10392> velocity=< 1,  1>\r\n"
			+ "position=<-42174, -10394> velocity=< 4,  1>\r\n"
			+ "position=<-52708, -20970> velocity=< 5,  2>\r\n"
			+ "position=< 31868,  21336> velocity=<-3, -2>\r\n"
			+ "position=<-10415, -31542> velocity=< 1,  3>\r\n"
			+ "position=< 31865,  31919> velocity=<-3, -3>\r\n"
			+ "position=<-21020, -42126> velocity=< 2,  4>\r\n"
			+ "position=<-10412,  53070> velocity=< 1, -5>\r\n"
			+ "position=<-52715,  10757> velocity=< 5, -1>\r\n"
			+ "position=< 21299,  10763> velocity=<-2, -1>\r\n"
			+ "position=<-52720, -31544> velocity=< 5,  3>\r\n"
			+ "position=< 21299,  31911> velocity=<-2, -3>\r\n"
			+ "position=<-42150, -20972> velocity=< 4,  2>\r\n"
			+ "position=<-31578, -42121> velocity=< 3,  4>\r\n"
			+ "position=< 31860, -52696> velocity=<-3,  5>\r\n"
			+ "position=< 42448, -20965> velocity=<-4,  2>\r\n"
			+ "position=< 53054, -20967> velocity=<-5,  2>\r\n"
			+ "position=<-31590,  21338> velocity=< 3, -2>\r\n"
			+ "position=<-42163, -10393> velocity=< 4,  1>\r\n"
			+ "position=< 42461, -52702> velocity=<-4,  5>\r\n"
			+ "position=<-42150,  21341> velocity=< 4, -2>\r\n"
			+ "position=<-10419,  42491> velocity=< 1, -4>\r\n"
			+ "position=<-52748,  10760> velocity=< 5, -1>\r\n"
			+ "position=<-20977, -52698> velocity=< 2,  5>\r\n"
			+ "position=< 31900, -42125> velocity=<-3,  4>\r\n"
			+ "position=< 10735,  21338> velocity=<-1, -2>\r\n"
			+ "position=< 53031, -31547> velocity=<-5,  3>\r\n"
			+ "position=<-52721,  10757> velocity=< 5, -1>\r\n"
			+ "position=<-52751, -31547> velocity=< 5,  3>\r\n"
			+ "position=< 42486, -10388> velocity=<-4,  1>\r\n"
			+ "position=<-52738,  31914> velocity=< 5, -3>\r\n"
			+ "position=<-20977,  10763> velocity=< 2, -1>\r\n"
			+ "position=<-42169,  53069> velocity=< 4, -5>\r\n"
			+ "position=< 42448,  42492> velocity=<-4, -4>\r\n"
			+ "position=<-10447, -10396> velocity=< 1,  1>\r\n"
			+ "position=< 42441,  10762> velocity=<-4, -1>\r\n"
			+ "position=<-52756, -42121> velocity=< 5,  4>\r\n"
			+ "position=<-10415, -52696> velocity=< 1,  5>\r\n"
			+ "position=<-52756,  21342> velocity=< 5, -2>\r\n"
			+ "position=<-31554, -31547> velocity=< 3,  3>\r\n"
			+ "position=< 21331,  42493> velocity=<-2, -4>\r\n"
			+ "position=< 42453, -52697> velocity=<-4,  5>\r\n"
			+ "position=< 53040,  31911> velocity=<-5, -3>\r\n"
			+ "position=<-10443,  31920> velocity=< 1, -3>\r\n"
			+ "position=<-52754, -20969> velocity=< 5,  2>\r\n"
			+ "position=< 10719,  31920> velocity=<-1, -3>\r\n"
			+ "position=< 10750,  21339> velocity=<-1, -2>\r\n"
			+ "position=<-21009,  42490> velocity=< 2, -4>\r\n"
			+ "position=<-20996,  10764> velocity=< 2, -1>\r\n"
			+ "position=<-52761,  21343> velocity=< 5, -2>\r\n"
			+ "position=< 10735, -20968> velocity=<-1,  2>\r\n"
			+ "position=<-21028, -20974> velocity=< 2,  2>\r\n"
			+ "position=< 31881, -52705> velocity=<-3,  5>\r\n"
			+ "position=< 31860,  31917> velocity=<-3, -3>\r\n"
			+ "position=<-42153, -31546> velocity=< 4,  3>\r\n"
			+ "position=< 42448,  53074> velocity=<-4, -5>\r\n"
			+ "position=<-31578,  42496> velocity=< 3, -4>\r\n"
			+ "position=< 42439,  10757> velocity=<-4, -1>\r\n"
			+ "position=<-10404, -20974> velocity=< 1,  2>\r\n"
			+ "position=<-20993, -20966> velocity=< 2,  2>\r\n"
			+ "position=< 42429, -52697> velocity=<-4,  5>\r\n"
			+ "position=< 31910,  42488> velocity=<-3, -4>\r\n"
			+ "position=< 53038, -31549> velocity=<-5,  3>\r\n"
			+ "position=< 31888,  31912> velocity=<-3, -3>\r\n"
			+ "position=<-31576,  42488> velocity=< 3, -4>\r\n"
			+ "position=< 10714,  10765> velocity=<-1, -1>\r\n"
			+ "position=< 53041,  10762> velocity=<-5, -1>\r\n"
			+ "position=< 21301, -42122> velocity=<-2,  4>\r\n"
			+ "position=< 21319, -42121> velocity=<-2,  4>\r\n"
			+ "position=< 31909, -52705> velocity=<-3,  5>\r\n"
			+ "position=<-10435, -52705> velocity=< 1,  5>\r\n"
			+ "position=<-42170,  42492> velocity=< 4, -4>\r\n"
			+ "position=<-20977,  31912> velocity=< 2, -3>\r\n"
			+ "position=<-42171,  31918> velocity=< 4, -3>\r\n"
			+ "position=< 31884, -42123> velocity=<-3,  4>\r\n"
			+ "position=< 21286,  53065> velocity=<-2, -5>\r\n"
			+ "position=<-10446,  31911> velocity=< 1, -3>\r\n"
			+ "position=<-52711, -52700> velocity=< 5,  5>\r\n"
			+ "position=< 42473,  53066> velocity=<-4, -5>\r\n"
			+ "position=< 10759,  10758> velocity=<-1, -1>\r\n"
			+ "position=<-20982, -42123> velocity=< 2,  4>\r\n"
			+ "position=<-42131,  21341> velocity=< 4, -2>\r\n"
			+ "position=<-10405,  21334> velocity=< 1, -2>\r\n"
			+ "position=<-42147, -52698> velocity=< 4,  5>\r\n"
			+ "position=<-21013,  53074> velocity=< 2, -5>\r\n"
			+ "position=<-31557, -31545> velocity=< 3,  3>\r\n"
			+ "position=<-10403,  21343> velocity=< 1, -2>\r\n"
			+ "position=< 53014, -10388> velocity=<-5,  1>\r\n"
			+ "position=<-42160, -31544> velocity=< 4,  3>\r\n"
			+ "position=<-42169, -20965> velocity=< 4,  2>\r\n"
			+ "position=<-42163, -31549> velocity=< 4,  3>\r\n"
			+ "position=< 10730, -52701> velocity=<-1,  5>\r\n"
			+ "position=<-31586, -10397> velocity=< 3,  1>\r\n"
			+ "position=<-20976, -10388> velocity=< 2,  1>\r\n"
			+ "position=<-21005, -10396> velocity=< 2,  1>\r\n"
			+ "position=<-42155, -20965> velocity=< 4,  2>\r\n"
			+ "position=< 42486,  31920> velocity=<-4, -3>\r\n"
			+ "position=< 53055,  10766> velocity=<-5, -1>\r\n"
			+ "position=< 21295, -10397> velocity=<-2,  1>\r\n"
			+ "position=< 21324, -10397> velocity=<-2,  1>\r\n"
			+ "position=< 42479,  31911> velocity=<-4, -3>\r\n"
			+ "position=<-21017, -31551> velocity=< 2,  3>\r\n"
			+ "position=<-31602, -20966> velocity=< 3,  2>\r\n"
			+ "position=<-10396,  10766> velocity=< 1, -1>\r\n"
			+ "position=< 42481, -42123> velocity=<-4,  4>\r\n"
			+ "position=< 21331, -31550> velocity=<-2,  3>\r\n"
			+ "position=<-52727, -10394> velocity=< 5,  1>\r\n"
			+ "position=<-20991, -10388> velocity=< 2,  1>\r\n"
			+ "position=< 31881, -42119> velocity=<-3,  4>\r\n"
			+ "position=<-42182, -20973> velocity=< 4,  2>\r\n"
			+ "position=<-31551,  42497> velocity=< 3, -4>\r\n"
			+ "position=<-31567,  42488> velocity=< 3, -4>\r\n"
			+ "position=< 53054,  53066> velocity=<-5, -5>\r\n"
			+ "position=<-31601, -20973> velocity=< 3,  2>\r\n"
			+ "position=<-42163,  31918> velocity=< 4, -3>\r\n"
			+ "position=< 42439, -52700> velocity=<-4,  5>\r\n"
			+ "position=< 21310,  42488> velocity=<-2, -4>\r\n"
			+ "position=<-42155, -42120> velocity=< 4,  4>\r\n"
			+ "position=<-31584,  42494> velocity=< 3, -4>\r\n"
			+ "position=< 53035, -42128> velocity=<-5,  4>\r\n"
			+ "position=<-20977, -42127> velocity=< 2,  4>\r\n"
			+ "position=<-10406,  42497> velocity=< 1, -4>\r\n"
			+ "position=< 31861,  21335> velocity=<-3, -2>\r\n"
			+ "position=< 31889, -20966> velocity=<-3,  2>\r\n"
			+ "position=< 31877, -20969> velocity=<-3,  2>\r\n"
			+ "position=< 31864, -20973> velocity=<-3,  2>\r\n"
			+ "position=< 21323, -10391> velocity=<-2,  1>\r\n"
			+ "position=< 10711,  10766> velocity=<-1, -1>\r\n"
			+ "position=<-52720,  53073> velocity=< 5, -5>\r\n"
			+ "position=<-31602, -10394> velocity=< 3,  1>\r\n"
			+ "position=<-31575,  53065> velocity=< 3, -5>\r\n"
			+ "position=<-31597, -10388> velocity=< 3,  1>\r\n"
			+ "position=<-31586, -52704> velocity=< 3,  5>\r\n"
			+ "position=<-42128, -20974> velocity=< 4,  2>\r\n"
			+ "position=<-20980,  42497> velocity=< 2, -4>\r\n"
			+ "position=<-20985, -52704> velocity=< 2,  5>\r\n"
			+ "position=<-42170, -10397> velocity=< 4,  1>\r\n"
			+ "position=< 53038, -42123> velocity=<-5,  4>\r\n"
			+ "position=< 53038,  42491> velocity=<-5, -4>\r\n"
			+ "position=<-42176,  21334> velocity=< 4, -2>\r\n"
			+ "position=< 31868, -42127> velocity=<-3,  4>\r\n"
			+ "position=< 42447, -52705> velocity=<-4,  5>\r\n"
			+ "position=<-52747, -20974> velocity=< 5,  2>\r\n"
			+ "position=<-31561, -10397> velocity=< 3,  1>\r\n"
			+ "position=< 10719, -42128> velocity=<-1,  4>\r\n"
			+ "position=< 53009, -31547> velocity=<-5,  3>\r\n"
			+ "position=< 21296,  10766> velocity=<-2, -1>\r\n"
			+ "position=<-21020,  42494> velocity=< 2, -4>\r\n"
			+ "position=<-42131, -42126> velocity=< 4,  4>\r\n"
			+ "position=< 21292,  31915> velocity=<-2, -3>\r\n"
			+ "position=<-42163,  42493> velocity=< 4, -4>\r\n"
			+ "position=<-42185, -42128> velocity=< 4,  4>\r\n"
			+ "position=<-20977, -10389> velocity=< 2,  1>\r\n"
			+ "position=< 10754,  53068> velocity=<-1, -5>\r\n"
			+ "position=< 21325, -42128> velocity=<-2,  4>\r\n"
			+ "position=< 31876, -20965> velocity=<-3,  2>\r\n"
			+ "position=< 10700,  10757> velocity=<-1, -1>\r\n"
			+ "position=< 53025, -52701> velocity=<-5,  5>\r\n"
			+ "position=< 53056,  21334> velocity=<-5, -2>\r\n"
			+ "position=< 53058,  31919> velocity=<-5, -3>\r\n"
			+ "position=< 53039,  53070> velocity=<-5, -5>\r\n"
			+ "position=< 21335, -10388> velocity=<-2,  1>\r\n"
			+ "position=<-52722,  53074> velocity=< 5, -5>\r\n"
			+ "position=<-10412, -10393> velocity=< 1,  1>\r\n"
			+ "position=<-52744,  31920> velocity=< 5, -3>\r\n"
			+ "position=<-21017, -20970> velocity=< 2,  2>\r\n"
			+ "position=<-31566, -52697> velocity=< 3,  5>\r\n"
			+ "position=< 21332,  53065> velocity=<-2, -5>\r\n"
			+ "position=<-31602, -42124> velocity=< 3,  4>\r\n"
			+ "position=<-42171, -42123> velocity=< 4,  4>\r\n"
			+ "position=< 42430, -10391> velocity=<-4,  1>\r\n"
			+ "position=<-10445, -10392> velocity=< 1,  1>\r\n"
			+ "position=< 31913,  21342> velocity=<-3, -2>\r\n"
			+ "position=<-52748, -20966> velocity=< 5,  2>\r\n"
			+ "position=< 53050, -42128> velocity=<-5,  4>\r\n"
			+ "position=<-10424,  53068> velocity=< 1, -5>\r\n"
			+ "position=<-31597, -42126> velocity=< 3,  4>\r\n"
			+ "position=<-42162,  10762> velocity=< 4, -1>\r\n"
			+ "position=<-21031, -20969> velocity=< 2,  2>\r\n"
			+ "position=< 53064,  10766> velocity=<-5, -1>\r\n"
			+ "position=<-52727,  21336> velocity=< 5, -2>\r\n"
			+ "position=<-20977, -52699> velocity=< 2,  5>\r\n"
			+ "position=<-31574,  53066> velocity=< 3, -5>\r\n"
			+ "position=< 31873, -31551> velocity=<-3,  3>\r\n"
			+ "position=< 31856,  42491> velocity=<-3, -4>\r\n"
			+ "position=<-42139,  10765> velocity=< 4, -1>\r\n"
			+ "position=< 42453,  10760> velocity=<-4, -1>\r\n"
			+ "position=<-31566,  10757> velocity=< 3, -1>\r\n"
			+ "position=< 10742, -31548> velocity=<-1,  3>\r\n"
			+ "position=< 42453, -20967> velocity=<-4,  2>\r\n"
			+ "position=<-10421, -52700> velocity=< 1,  5>\r\n"
			+ "position=< 42485,  31914> velocity=<-4, -3>\r\n"
			+ "position=< 31889, -20969> velocity=<-3,  2>\r\n"
			+ "position=<-42126,  21335> velocity=< 4, -2>\r\n"
			+ "position=<-31602, -42121> velocity=< 3,  4>\r\n"
			+ "position=< 42453,  42493> velocity=<-4, -4>\r\n"
			+ "position=< 53009, -10397> velocity=<-5,  1>\r\n"
			+ "position=<-52748,  42497> velocity=< 5, -4>\r\n"
			+ "position=< 21287,  42489> velocity=<-2, -4>\r\n"
			+ "position=<-52756,  53067> velocity=< 5, -5>\r\n"
			+ "position=<-21029,  10757> velocity=< 2, -1>\r\n"
			+ "position=<-31565, -42128> velocity=< 3,  4>\r\n"
			+ "position=< 21331, -42126> velocity=<-2,  4>\r\n"
			+ "position=<-10443,  21341> velocity=< 1, -2>\r\n"
			+ "position=<-20989, -52699> velocity=< 2,  5>\r\n"
			+ "position=<-31591,  10757> velocity=< 3, -1>\r\n"
			+ "position=<-52724,  21342> velocity=< 5, -2>\r\n"
			+ "position=<-42160, -31549> velocity=< 4,  3>\r\n"
			+ "position=< 42481, -10392> velocity=<-4,  1>\r\n"
			+ "position=<-10443, -20971> velocity=< 1,  2>\r\n"
			+ "position=<-20997,  10762> velocity=< 2, -1>\r\n"
			+ "position=< 10750, -10397> velocity=<-1,  1>\r\n"
			+ "position=<-31597, -20968> velocity=< 3,  2>\r\n"
			+ "position=< 53017, -52700> velocity=<-5,  5>\r\n"
			+ "position=< 21276, -31542> velocity=<-2,  3>\r\n"
			+ "position=<-52720, -31545> velocity=< 5,  3>\r\n"
			+ "position=< 21326,  31916> velocity=<-2, -3>\r\n"
			+ "position=<-42163,  10764> velocity=< 4, -1>\r\n"
			+ "position=<-31589,  10766> velocity=< 3, -1>\r\n"
			+ "position=< 31884,  21336> velocity=<-3, -2>\r\n"
			+ "position=<-31578,  10763> velocity=< 3, -1>\r\n"
			+ "position=< 10706, -31546> velocity=<-1,  3>\r\n"
			+ "position=<-42171,  31919> velocity=< 4, -3>\r\n"
			+ "position=<-52721,  53065> velocity=< 5, -5>\r\n"
			+ "position=< 53054,  31914> velocity=<-5, -3>\r\n"
			+ "position=< 21319, -20972> velocity=<-2,  2>\r\n"
			+ "position=< 21335,  10757> velocity=<-2, -1>\r\n"
			+ "position=< 10716, -42119> velocity=<-1,  4>\r\n"
			+ "position=< 42463, -20969> velocity=<-4,  2>\r\n"
			+ "position=< 31889,  21341> velocity=<-3, -2>\r\n"
			+ "position=<-52740, -10393> velocity=< 5,  1>\r\n"
			+ "position=< 42490, -31543> velocity=<-4,  3>\r\n"
			+ "position=< 10706, -52701> velocity=<-1,  5>\r\n"
			+ "position=< 21315,  31919> velocity=<-2, -3>\r\n"
			+ "position=< 31862, -52700> velocity=<-3,  5>\r\n"
			+ "position=< 31884, -52701> velocity=<-3,  5>\r\n"
			+ "position=< 31870, -10397> velocity=<-3,  1>\r\n"
			+ "position=< 42441, -42123> velocity=<-4,  4>\r\n"
			+ "position=<-42171,  10763> velocity=< 4, -1>\r\n"
			+ "position=<-42171,  21341> velocity=< 4, -2>\r\n"
			+ "position=<-31591,  53069> velocity=< 3, -5>\r\n"
			+ "position=< 10726,  53066> velocity=<-1, -5>\r\n"
			+ "position=<-42134, -20965> velocity=< 4,  2>\r\n"
			+ "position=<-42183,  31920> velocity=< 4, -3>\r\n"
			+ "position=< 31857,  21334> velocity=<-3, -2>\r\n"
			+ "position=<-31597, -42123> velocity=< 3,  4>\r\n"
			+ "position=< 21312, -42120> velocity=<-2,  4>\r\n"
			+ "position=<-20996,  42497> velocity=< 2, -4>\r\n"
			+ "position=<-10399,  53065> velocity=< 1, -5>\r\n"
			+ "position=< 31860, -20969> velocity=<-3,  2>\r\n"
			+ "position=<-31550, -20965> velocity=< 3,  2>\r\n"
			+ "position=<-42162, -42124> velocity=< 4,  4>\r\n"
			+ "position=<-20977, -10393> velocity=< 2,  1>\r\n"
			+ "position=< 10735, -52697> velocity=<-1,  5>\r\n"
			+ "position=< 53049, -42119> velocity=<-5,  4>\r\n"
			+ "position=< 42453, -42122> velocity=<-4,  4>\r\n"
			+ "position=< 10746, -42126> velocity=<-1,  4>\r\n"
			+ "position=<-31569, -20965> velocity=< 3,  2>\r\n"
			+ "position=< 42461,  31918> velocity=<-4, -3>\r\n"
			+ "position=< 31896,  53072> velocity=<-3, -5>\r\n"
			+ "position=< 53009, -52701> velocity=<-5,  5>\r\n"
			+ "position=<-20977,  31919> velocity=< 2, -3>\r\n"
			+ "position=<-31594, -20969> velocity=< 3,  2>";
	
	public String part1() {
		ArrayList<Coord> pos = new ArrayList<Coord>();
		ArrayList<Coord> vel = new ArrayList<Coord>();
		for(String s : input.split("\r\n")) {
			String[] parts = s.split("> ");
			String[] posParts = parts[0].split(", ");
			int posX = Integer.parseInt(posParts[0].substring(posParts[0].indexOf("<") + 1).trim());
			int posY = Integer.parseInt(posParts[1].trim());
			String[] velParts = parts[1].split(", ");
			int velX = Integer.parseInt(velParts[0].substring(velParts[0].indexOf("<") + 1).trim());
			int velY = Integer.parseInt(velParts[1].substring(0,velParts[1].length() - 1).trim());
			pos.add(new Coord(posX,posY));
			vel.add(new Coord(velX,velY));
		}
		do {
			for(int i = 0; i < pos.size(); i++) {
				pos.get(i).sumSelf(vel.get(i));
			}
		} while (!allAdjacent(pos));
		

		int minX = pos.stream().map(x -> x.x).min(Integer::compare).get();
		int maxX = pos.stream().map(x -> x.x).max(Integer::compare).get();
		int minY = pos.stream().map(x -> x.y).min(Integer::compare).get();
		int maxY = pos.stream().map(x -> x.y).max(Integer::compare).get();
		
		StringBuilder out = new StringBuilder("\n");
		for(int y = minY; y <= maxY; y++) {
			for(int x = minX; x <= maxX; x++) {
				if(pos.contains(new Coord(x,y))) {
					out.append('#');
				} else {
					out.append('.');
				}
			}
			out.append('\n');
		}
		return out.toString();
		
	}
	
	//presumably, our valid "state" where the letters show up is one where all points are adjacent to at least one other point
	public boolean allAdjacent(ArrayList<Coord> a) {
		//iterate over positions - if at least one neighboring point is in positions, point is valid
		pointLoop:
		for(Coord c : a) {
			for(Coord d : c.allNeighbors()) {
				if(a.contains(d))
					continue pointLoop;
			}
			//if we made it here, no neighbors are in positions - state is invalid
			return false;
		}
		return true;
	}

	@Override
	public String part2() {
		ArrayList<Coord> pos = new ArrayList<Coord>();
		ArrayList<Coord> vel = new ArrayList<Coord>();
		for(String s : input.split("\r\n")) {
			String[] parts = s.split("> ");
			String[] posParts = parts[0].split(", ");
			int posX = Integer.parseInt(posParts[0].substring(posParts[0].indexOf("<") + 1).trim());
			int posY = Integer.parseInt(posParts[1].trim());
			String[] velParts = parts[1].split(", ");
			int velX = Integer.parseInt(velParts[0].substring(velParts[0].indexOf("<") + 1).trim());
			int velY = Integer.parseInt(velParts[1].substring(0,velParts[1].length() - 1).trim());
			pos.add(new Coord(posX,posY));
			vel.add(new Coord(velX,velY));
		}
		int counter = 0;
		do {
			for(int i = 0; i < pos.size(); i++) {
				pos.get(i).sumSelf(vel.get(i));
			}
			counter++;
		} while (!allAdjacent(pos));
		
		return Integer.toString(counter);
	}

	public static void main(String[] args) {
		DayRunner.run(new Day10());
	}

}
