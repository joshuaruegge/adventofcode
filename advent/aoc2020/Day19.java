package advent.aoc2020;

import java.util.ArrayList;
import java.util.HashSet;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.utils2020.Rule;

public class Day19 implements IDay {

	String input = "104: 23 105 | 105 23\r\n"
			+ "40: 23 39 | 105 35\r\n"
			+ "127: 23 3 | 105 49\r\n"
			+ "96: 85 23 | 73 105\r\n"
			+ "114: 70 23 | 106 105\r\n"
			+ "124: 80 105 | 71 23\r\n"
			+ "23: \"a\"\r\n"
			+ "97: 105 12 | 23 104\r\n"
			+ "18: 23 118 | 105 29\r\n"
			+ "89: 121 105 | 39 23\r\n"
			+ "13: 23 18 | 105 87\r\n"
			+ "122: 50 105 | 24 23\r\n"
			+ "6: 58 105 | 59 23\r\n"
			+ "101: 105 44 | 23 43\r\n"
			+ "31: 105 65 | 23 13\r\n"
			+ "36: 64 105 | 68 23\r\n"
			+ "74: 105 123 | 23 7\r\n"
			+ "38: 19 23 | 48 105\r\n"
			+ "118: 105 127 | 23 75\r\n"
			+ "130: 23 30 | 105 44\r\n"
			+ "59: 105 121 | 23 71\r\n"
			+ "112: 92 23 | 97 105\r\n"
			+ "91: 25 23 | 66 105\r\n"
			+ "46: 30 23 | 39 105\r\n"
			+ "111: 105 35 | 23 104\r\n"
			+ "28: 105 129 | 23 112\r\n"
			+ "25: 105 62 | 23 89\r\n"
			+ "125: 23 61 | 105 100\r\n"
			+ "120: 105 131 | 23 44\r\n"
			+ "102: 43 105 | 48 23\r\n"
			+ "105: \"b\"\r\n"
			+ "27: 5 23 | 10 105\r\n"
			+ "84: 21 23 | 130 105\r\n"
			+ "56: 105 39\r\n"
			+ "35: 23 23 | 105 23\r\n"
			+ "44: 23 105\r\n"
			+ "69: 80 105 | 104 23\r\n"
			+ "100: 72 105 | 110 23\r\n"
			+ "72: 39 105 | 19 23\r\n"
			+ "95: 105 69 | 23 109\r\n"
			+ "88: 120 105 | 78 23\r\n"
			+ "53: 67 105 | 14 23\r\n"
			+ "26: 21 23 | 56 105\r\n"
			+ "80: 105 105 | 105 23\r\n"
			+ "70: 19 23 | 12 105\r\n"
			+ "92: 44 23 | 12 105\r\n"
			+ "37: 48 105 | 55 23\r\n"
			+ "132: 23 16 | 105 122\r\n"
			+ "7: 105 48 | 23 12\r\n"
			+ "113: 39 23 | 12 105\r\n"
			+ "10: 102 105 | 126 23\r\n"
			+ "94: 105 | 23\r\n"
			+ "42: 105 128 | 23 132\r\n"
			+ "103: 105 89 | 23 57\r\n"
			+ "107: 105 79 | 23 76\r\n"
			+ "11: 42 31\r\n"
			+ "99: 47 105 | 1 23\r\n"
			+ "55: 105 94 | 23 23\r\n"
			+ "8: 42\r\n"
			+ "4: 23 41 | 105 15\r\n"
			+ "81: 51 105 | 116 23\r\n"
			+ "76: 37 105 | 78 23\r\n"
			+ "110: 12 23 | 35 105\r\n"
			+ "45: 105 33 | 23 84\r\n"
			+ "78: 23 131 | 105 35\r\n"
			+ "63: 72 105 | 133 23\r\n"
			+ "51: 19 105 | 131 23\r\n"
			+ "83: 105 45 | 23 27\r\n"
			+ "21: 23 71 | 105 104\r\n"
			+ "19: 23 105 | 105 94\r\n"
			+ "54: 23 48 | 105 55\r\n"
			+ "41: 23 121 | 105 55\r\n"
			+ "20: 115 105 | 86 23\r\n"
			+ "15: 105 71\r\n"
			+ "66: 105 113 | 23 49\r\n"
			+ "121: 94 94\r\n"
			+ "14: 99 105 | 81 23\r\n"
			+ "22: 30 105\r\n"
			+ "129: 105 109 | 23 111\r\n"
			+ "5: 15 105 | 9 23\r\n"
			+ "30: 105 105\r\n"
			+ "131: 105 23\r\n"
			+ "87: 23 77 | 105 91\r\n"
			+ "98: 23 38 | 105 82\r\n"
			+ "77: 23 88 | 105 74\r\n"
			+ "43: 23 23 | 94 105\r\n"
			+ "86: 23 98 | 105 93\r\n"
			+ "58: 55 23 | 131 105\r\n"
			+ "1: 23 35 | 105 30\r\n"
			+ "9: 105 131 | 23 55\r\n"
			+ "49: 104 105 | 55 23\r\n"
			+ "68: 23 131 | 105 48\r\n"
			+ "0: 8 11\r\n"
			+ "71: 23 23\r\n"
			+ "48: 105 23 | 23 94\r\n"
			+ "29: 105 119 | 23 117\r\n"
			+ "93: 52 23 | 22 105\r\n"
			+ "16: 105 125 | 23 28\r\n"
			+ "126: 35 23 | 43 105\r\n"
			+ "65: 83 23 | 53 105\r\n"
			+ "62: 19 105 | 104 23\r\n"
			+ "12: 23 23 | 23 105\r\n"
			+ "52: 105 19 | 23 104\r\n"
			+ "67: 23 103 | 105 63\r\n"
			+ "24: 4 105 | 26 23\r\n"
			+ "50: 105 95 | 23 96\r\n"
			+ "47: 105 30 | 23 30\r\n"
			+ "32: 105 114 | 23 6\r\n"
			+ "82: 105 19 | 23 12\r\n"
			+ "75: 34 105 | 59 23\r\n"
			+ "34: 23 39 | 105 80\r\n"
			+ "17: 23 104 | 105 131\r\n"
			+ "109: 44 23 | 39 105\r\n"
			+ "79: 17 23 | 78 105\r\n"
			+ "85: 12 23 | 39 105\r\n"
			+ "57: 23 30 | 105 121\r\n"
			+ "2: 23 30\r\n"
			+ "3: 71 23 | 19 105\r\n"
			+ "115: 108 105 | 36 23\r\n"
			+ "64: 23 121 | 105 12\r\n"
			+ "90: 105 32 | 23 107\r\n"
			+ "116: 12 105 | 131 23\r\n"
			+ "123: 71 105 | 35 23\r\n"
			+ "61: 85 23 | 70 105\r\n"
			+ "133: 80 94\r\n"
			+ "119: 105 40 | 23 46\r\n"
			+ "128: 23 90 | 105 20\r\n"
			+ "60: 104 105 | 80 23\r\n"
			+ "33: 23 89 | 105 101\r\n"
			+ "73: 105 121 | 23 19\r\n"
			+ "39: 105 105 | 23 23\r\n"
			+ "108: 105 2 | 23 54\r\n"
			+ "106: 71 105 | 44 23\r\n"
			+ "117: 124 105 | 60 23\r\n"
			+ "\r\n"
			+ "aababbbaaabbaaaabbabaaaabbbaababbbbbaaabbaabbbbb\r\n"
			+ "aababbabaababbbabbbabaaabbbbbbababaaabaa\r\n"
			+ "bababbbabaaabbaababbabaaabbbaaab\r\n"
			+ "baabababaaababbaabbbbabb\r\n"
			+ "baaaaaabaaaaababbbabbabbbaaaabba\r\n"
			+ "abbabbaaabbbabbaabbbababaabbabbaaababaabbabbabbbaabaabababbaaabbaababababbbbbbab\r\n"
			+ "bbbaabbaababaabaabbaaaabbaabaaabababbaababbbaaabaaaaaaabaabbbaabaabababa\r\n"
			+ "baaabaaaababbabbbabbababbbbabaaaabaaaabb\r\n"
			+ "bababaababbbababababaababbabbabbaabbaabbaaabaabb\r\n"
			+ "aabbbbabbabbababbbbaabbb\r\n"
			+ "bbbbbaaabbabbabbbaaabbbb\r\n"
			+ "bbaaabbabababbaaaabbbabb\r\n"
			+ "aaaababbaaaaaabbaaaababbababbbaa\r\n"
			+ "baaaabbbabaabbbaabbabbab\r\n"
			+ "aabbaabbbbbaaaaaabbaaaab\r\n"
			+ "baaaabbbaabbababbbbbbaaababaabba\r\n"
			+ "abaaababbbaabbababbbaaaabaabaabbbbbbbaba\r\n"
			+ "baaababaaabaabaaaaabbbababbaaaaa\r\n"
			+ "bbaabbabaabbbbabaabbbbaa\r\n"
			+ "bbabaaaabbbbaaabbbababbb\r\n"
			+ "abaaaaabbaaabbabbbabbaab\r\n"
			+ "baaaabbbabaabbbaaaabaaaa\r\n"
			+ "bbabbbbbbababaaabbbbabaabaaaaaaa\r\n"
			+ "abababbbabbbbbbbbbababaa\r\n"
			+ "ababaabbbbbabbaaaabaaaaabbbaabbaabbbbaaabbbabaababbbbbbaaaababbaabbaaaba\r\n"
			+ "babbbbaabaaaabbbababbaaa\r\n"
			+ "bababbbaaaaabaabbbabbaaa\r\n"
			+ "babababaaaabbbbbbbababaa\r\n"
			+ "aaaaaaaabbbaabbbabbaaabbabaabbbbabaabbaabababbaaaaababbababbabbaaaaabbbbbaabbbbabbaaabba\r\n"
			+ "babbaabaabababbbaababbbb\r\n"
			+ "baabbaabbaaaabbbbbbbabaabbbababb\r\n"
			+ "bbbaaaaaabbbaaabbbabbaaa\r\n"
			+ "aababbbabbaaababbaababaabbbaabba\r\n"
			+ "bababbbaaabaaabbabbaabba\r\n"
			+ "baaaabaabaaabbabbbbaaaaa\r\n"
			+ "bbbabbabaabaaabbaaabaaabbaaabbba\r\n"
			+ "bbaaaabbbaaabaaabbbaaaabbbaaaabbbabbabbbbabaaaba\r\n"
			+ "bbabbbbabbabaaaaaabaabbbaababbbb\r\n"
			+ "abaaababaababbabbbbaabba\r\n"
			+ "bbbaabaababaaaabbbbababa\r\n"
			+ "aaababaaabbaaabbaaaababa\r\n"
			+ "bbaababaaaaaabaaaaabaaaa\r\n"
			+ "aabbaabaaaaaabababaabaabbbaabaabbbbaabbaabbaaaab\r\n"
			+ "babababababababbbbbbabbb\r\n"
			+ "aabbaaaabbbaaaabbabababbbbbbbbbbaaaaaaaa\r\n"
			+ "babbabaaaaaaababbabaaabb\r\n"
			+ "bbbbabaaabaaabbaaaababbb\r\n"
			+ "bbabaaaabbaabaaabbbaabaaabbaabba\r\n"
			+ "babbbaaaabaaabbaabbaaaab\r\n"
			+ "baaababbababbabbbabababaababbabbaaabbbaaababbaab\r\n"
			+ "baaaabaabaaabbabbbbababa\r\n"
			+ "babababbabbbaaaaaaaaaaaa\r\n"
			+ "bbaababbabbaaabbabbbbabbabbbabababbbabba\r\n"
			+ "aabbbaabbbabbbbbabaaabbabbabbbbbaabaababbabbbabbaabababa\r\n"
			+ "aababbabaabaababbbabbabbabbabaabababbaab\r\n"
			+ "abbbbbaabaaabbababbbbbaabaaabbbb\r\n"
			+ "abababababaaaabbbaaaabbababaaabbabaabbbbbababaab\r\n"
			+ "abaaabbaabababaaababbbbaaabaaabbabbabababababababaaabaabaaaabaaababaaababbaaaaaaabaabbbb\r\n"
			+ "baaabbabaababaabbabababbbaaabaaaabbbabbaabaaaabb\r\n"
			+ "abaababbababaababaabbbaababbaaaa\r\n"
			+ "aaaaaababbbaababbbbaabbb\r\n"
			+ "babababbbababbaabbabbbaaabaababa\r\n"
			+ "aaabaababbbbabbbbbbbbbaababbabbabbbaabababbbbabbbabaaababaabababaaaaabba\r\n"
			+ "abaabaababbbaaaabbaabaab\r\n"
			+ "bbbabaaaabbbaababbaaaaaa\r\n"
			+ "aabaaabbbbababababaaaabaabbbaaaaaaabaababaabaabb\r\n"
			+ "ababbaabbaaaaaabbbbaaabaabbaaaabaababbbbbbaaabbaaababaabbbbababa\r\n"
			+ "babbababbbbbbaaaababbaab\r\n"
			+ "baaabbabbababaabaaaabbbb\r\n"
			+ "baaababbbaabababababbaaa\r\n"
			+ "bbaaabbbaaaaabbabbbaaaba\r\n"
			+ "babbababbaaaabbbabbabaaabaabbbba\r\n"
			+ "babbbaaabbabbbaababaabba\r\n"
			+ "bbaabaaaaaabbabaaaabbabaaaaabbabbaaababa\r\n"
			+ "aaaaaabaabbabaaaabaabbbaaaabbaaabaaabbbb\r\n"
			+ "bababaababaaaaabbaabbaaa\r\n"
			+ "aababaabaabaaababbaaaabbaababaaa\r\n"
			+ "bbbabaabaaaabaababbbbbab\r\n"
			+ "baabaabaaaaababbbbbbabba\r\n"
			+ "abaabaabbbbaabaaababaabababbabbbbbbbaaaa\r\n"
			+ "bababbaaaaaaabbaaaaaaabbabababba\r\n"
			+ "bbabaabaabbabbbbbaaaabaaaabbbabb\r\n"
			+ "bbabababababaababbbaaaabbbbbbaba\r\n"
			+ "aabaabbbbabababaaabaaaaabbabbbabbbbbaabbbbbabbbbabbbbaba\r\n"
			+ "abbaaaaabaababbabaaaabab\r\n"
			+ "aabbbaabbbabbbbbbaaaabbbabbaaaaabababbbb\r\n"
			+ "abaabbbbbbaaabbabaabbaabaaaaabbbbaabbabb\r\n"
			+ "aabaabaababaaaaaabbabaab\r\n"
			+ "abbbbbaababbaabbbbaaabaaaaabbbaa\r\n"
			+ "aabbaabbaaaaababaabbabaabbbbbbbb\r\n"
			+ "abbaaabbbbbabbbbaabbabba\r\n"
			+ "bbabababaaabbaabbaaabbabbbbbbaaaababaabaaaaabbaabaabaaaa\r\n"
			+ "bbaabaaababababbaaaabaaa\r\n"
			+ "babbbbaabaaaaabbabbbaabb\r\n"
			+ "babbaabaaaaabaabaabaaabaaabababa\r\n"
			+ "aabaabbbaabbbabaabbbaabaababaaab\r\n"
			+ "abbbbabbbbaaaabbbbababaa\r\n"
			+ "bbbbbaaabbabaaaabbbbabbb\r\n"
			+ "abaabababbabaaabaababbaa\r\n"
			+ "bbabaaabbaaabbaaabbaaaba\r\n"
			+ "bbabbbbaaaabbbbbbaabbbbababbbaabaabbbaaaabbaabababaabaaabaabbbbbabbaabaabbaaabbaaababbaa\r\n"
			+ "aabaabbbabbaabaaaaaaaaaa\r\n"
			+ "babbbaaababbaabbbbababba\r\n"
			+ "bababbababaaaaaabbbbabba\r\n"
			+ "baabababaabaabaaaaaababa\r\n"
			+ "ababaabaabbabaaabbaabbbbabaaaaabbbbbbaaabbaabaababbbabaaababbaaa\r\n"
			+ "bbaabbaaaabbbaabbaaabbbb\r\n"
			+ "baaaaaabaabaabaaabbaaaaa\r\n"
			+ "aaababaabbabbbbaabbbbbaabbbaaabababaaaba\r\n"
			+ "abbbaababaabaabaabbabbaa\r\n"
			+ "abbbbbaaaabbabaaabababaa\r\n"
			+ "abbbaabababbaabbaababbaabbaaaabbabbbbbbaabbabbbaaaaabbba\r\n"
			+ "babaaaababaaabbabbbabaaabaababbbbabbaaab\r\n"
			+ "babaaaaababbbbbbbaabaaaa\r\n"
			+ "bbbbbaabbabaaaaaabbbbabbaaabbbbbbbabbaabbbabbbbabbbabbbb\r\n"
			+ "babababaaaaababbaaababbaabbbbaaa\r\n"
			+ "aaababaaaabaabaaababaaaa\r\n"
			+ "bbaaaabaabbbbabababbbaab\r\n"
			+ "bbbaaaabaababbbaabaaabbababbbbab\r\n"
			+ "abbbbaabbaaaaabbbabbaabaabbbaaaabbabbaab\r\n"
			+ "abbbababababbabababaaaab\r\n"
			+ "aabaabbbabaababbbabaabab\r\n"
			+ "bbaaaababaabaabaaabbbabb\r\n"
			+ "bbbabbabbabbababaaabaababbbaaabb\r\n"
			+ "babababbbbaaababbbbabaab\r\n"
			+ "baabbbabbbbbbaaabaabbbabaabaaabaabbaabbb\r\n"
			+ "aaaaabbabaaababbbbbababb\r\n"
			+ "bbbaabaabababbbababbabbabbbaabbbbbbabbaa\r\n"
			+ "bbaaabababaaaaababaaaababbababbbbbbbbbbb\r\n"
			+ "aaaaababbaabbbababbaaaab\r\n"
			+ "bbbaaaabbaababaabbbbbbbababbbbbb\r\n"
			+ "bbbbaaababbbaababababbbaabaababaababbbbbbaababbb\r\n"
			+ "bbabbbaabaaabaaabbbababb\r\n"
			+ "aabbababaabaaaaabbbabbabbbaaaaaa\r\n"
			+ "aaababbaabbaabaaabaaaabbaabbabbababbbabbabbaabbb\r\n"
			+ "ababaabaaabaabbbabbaaaaa\r\n"
			+ "ababaabbaaababbaabaaaababbabbbabbbbbbbaaaaaabbaa\r\n"
			+ "baaabaabbbabbabababaabba\r\n"
			+ "aaabaaabaaabaababbbabaabbabbbbbb\r\n"
			+ "aabaaabbaabbaabbabbbababbabbaabaabbabbab\r\n"
			+ "baaaabaaaabaaabbbbbaaaababaaabbabbbababbabababaa\r\n"
			+ "bbaabababbabaaabbbaabaaabaaaabba\r\n"
			+ "abaabaabbbbaaaabbabaabaa\r\n"
			+ "bbbaababaaaabaabababbbbbaabbbabbbaaaaaaa\r\n"
			+ "bbaababbababaababbaaaaaa\r\n"
			+ "bbbbbbbabbbbbbaaabaabbaa\r\n"
			+ "ababbbbbbabbaabbbbaaaaab\r\n"
			+ "bbabbabbaabbabaaaabbbbba\r\n"
			+ "ababbaaababbabbabbaabbbabaabbabbababaabbabbbbaabbbbaaabaaaaabbbaaaaabbabbabbabbb\r\n"
			+ "aaaaaaabaabbbbaababbbabbababbabbbbabaaaababaabbbbbabbabb\r\n"
			+ "baaaabaabababaaaaaabbbaa\r\n"
			+ "ababbbbaaabbaabaaabbababbabababaaaaababbbabaabbaabaabbaabbbbbabb\r\n"
			+ "aabaaabbaaabaababbbaabaabbbbabba\r\n"
			+ "bbabaaabbaabbbaaabbbbbab\r\n"
			+ "bbaabaaabbbbaaababbabbba\r\n"
			+ "aabbabaaabbbbbbbaabbabbb\r\n"
			+ "baabababaabbabababababaa\r\n"
			+ "abbbbabbaaabaababbaabbabbbbaabababaaabbabbbbabbbababababababbaaa\r\n"
			+ "baabbaababababaaabbaabaaaaaabaaaabbabaaabbababaaaaaabbaaabaaabbb\r\n"
			+ "bbbabbbbaaaabaabbbbaaaabbabbbaba\r\n"
			+ "abaaababbabbaabbbbbaabba\r\n"
			+ "ababbabbbaaaabaaabaaabaaabababbbbbbbbbbbbbbabbba\r\n"
			+ "aabbbabaaaaaabaabaaabbbb\r\n"
			+ "aaaaaabaababbbbaabaabbab\r\n"
			+ "ababaaaaabaaabbbbbbbababbabaaababaaaabaaabbabbbb\r\n"
			+ "babbabaaabaaaaabbaaabbaabaaaabaaaababaaa\r\n"
			+ "bbbbbbbaaabbbbabbbbbabbb\r\n"
			+ "abbbbabbbbabbabbbaabababbabaabab\r\n"
			+ "babbbbaabbabaabbabaaabbabaaabbba\r\n"
			+ "bbbabbbbbababababaaabbabbbbbaabb\r\n"
			+ "bbabbabbbaaabbaaaabbaabbabbbbbbbabbbbbbbbabaabbbbabbabbbabaabaaaabbaaaaa\r\n"
			+ "bbabaababaaabbabbbabaabbaabaabbababbbbba\r\n"
			+ "bbabaaabbbaaabaaabbbabbb\r\n"
			+ "aabaaabbaaaabaabababbaaa\r\n"
			+ "abbbaababbabbbaaabbaaaab\r\n"
			+ "abaabbbabbabaaaaaabaaabbaaaaaabbbaabbbba\r\n"
			+ "bbabaabbabbbbbbbaabababb\r\n"
			+ "bababbbaaaaabaabababbbaa\r\n"
			+ "abbbaaaabbbaabababbbbbbaabaaaaabbabbbbbabaabbbbbababbbbabaaababbbbbabbaabbbababa\r\n"
			+ "aaabaababababbaabaabbbabababbaaabaaabbaabaaabbbabbaaaaabaabaaaabaaaaaaaa\r\n"
			+ "abbaaabbababbbbbbaabbaba\r\n"
			+ "aabaababbbabbbabbbabaababbaaaabaabbbbbba\r\n"
			+ "bbaabbaaabbbbaabababbbaa\r\n"
			+ "aababaabababbbbababbbaba\r\n"
			+ "bbbbbbbaaabaababaabbaabbbaaaaabababaabbb\r\n"
			+ "abbbaaaabbbabbbbaababbbb\r\n"
			+ "aaababbaaabbbababaabaaab\r\n"
			+ "bbaaabbababbbbaabaababba\r\n"
			+ "bbbbbaaaabaabbbbabbbaabb\r\n"
			+ "baaabbaabababbabbbbbaaaa\r\n"
			+ "abbbbababbbbbbabbaabaaabbbbbbaab\r\n"
			+ "baabbbabbbbbbbbabbaaaabaababaaaa\r\n"
			+ "aaaaabbaaabbaaaaaabaaabaaaabbbaa\r\n"
			+ "bababbaaaabbaaaaaabbbbaa\r\n"
			+ "abaaabbbaaabaabbabbbaaabaaabaaaaaababbbb\r\n"
			+ "aaabbbbbbbabaaabbbaaabbaababbbbbaabaaaab\r\n"
			+ "aaabbaabaabbaaaabbaaaaab\r\n"
			+ "bbaabaaaaabaaaaaabbabaab\r\n"
			+ "babababaababaabbabbabaab\r\n"
			+ "aabaabbbbbbbbbaabbabaaaaaababbbaaaababaaaaabbbbaabaabbabaaaabbaabbaabaab\r\n"
			+ "bbaaabaaaabbbbabaabbbbabbbbbabba\r\n"
			+ "aaaabaabbaaababbbbbbbbaaabbabaabbbbbbbbb\r\n"
			+ "abbaababbbbabaabbaabbbba\r\n"
			+ "aaaabaabaababbbababbbabb\r\n"
			+ "bbaabbaaaababbabaaabbabaaaaabbaa\r\n"
			+ "aaabaaabaabbabaababaaaaaabaaabaa\r\n"
			+ "aaabbababbaaabbababaabaa\r\n"
			+ "abaaabbaababbbabaabaabbbaabaaaaabbababbb\r\n"
			+ "bbaaabbbaaabaabaabbaabba\r\n"
			+ "bbaababbbbabbbbbbaaababa\r\n"
			+ "baaabbaababbaabaabbbbbbbbabaaaabbbbaabba\r\n"
			+ "baaaabbbbaabaaaabbaaaaaabbbbbabababaaaba\r\n"
			+ "bbaababaaabbbababababaaaabababab\r\n"
			+ "baaaabaabbabbabbbaababbb\r\n"
			+ "bbaaaabaabaaabbababbaababbbbbbbbabbabbab\r\n"
			+ "babbaaaabbababbaaaababbbbabbabaabbababbbbbbabbba\r\n"
			+ "bbabaaabababaabaabbbabba\r\n"
			+ "bbbaababababbbababbabbab\r\n"
			+ "aabbbbabaaabbbbbaabbbbaa\r\n"
			+ "aabaabbbabaaaaababbabbba\r\n"
			+ "bbababababbbbabbabbaabbb\r\n"
			+ "baaaaabbaabbababbbaaaabbbababbbabbbbbaabaaaabbba\r\n"
			+ "bbbaabaaabbabaaabaabaabb\r\n"
			+ "bbbbabaaabbbababbbaabaaabbaabbaa\r\n"
			+ "babbabbbbaabaaaababaaabababbbaaaaaaaabaaaabbababaabababbabbabbbabbabbbaa\r\n"
			+ "aaaaabaaaaaaaabbabbabbaa\r\n"
			+ "aaababbabbbabbbbababbababbbbbbbb\r\n"
			+ "baaabbaabbbbabaaabbbbabbbbababbb\r\n"
			+ "bbabbbbabbbaabaabaabbbabaabaaabbbabaabbababbaaaaaaaabbbb\r\n"
			+ "bbabbbabaabaabaabaaabbba\r\n"
			+ "ababaabbbbaaabaabbaababaaaaabbaabbaabbba\r\n"
			+ "abbbaaaababababbbbbbbbbababbbabb\r\n"
			+ "aabaababbbaabaaaabbabbab\r\n"
			+ "abaabbbabaabaababaaabbbb\r\n"
			+ "baabbbaaabbbbbaabbbabbabaaaaaabaaabaabbbbbaabbaaabbaaaab\r\n"
			+ "bbabaaabaabbababaaabaabb\r\n"
			+ "ababbabaabbbabababbbbaba\r\n"
			+ "abaababaaaaaaabbabbbababaabababaabaabaaa\r\n"
			+ "babababbabbaaaaabbbbabbbaababbbbbaabaabaaabbbbabbabababa\r\n"
			+ "abbaabbaaababbaabaabbababaabaabb\r\n"
			+ "abaabaabbabbabaaabaaabaa\r\n"
			+ "aaaaaaaaaabaabaaabbbababbababbbbaababbaaaaaabbabababbabbbabaabab\r\n"
			+ "bbaaaabaabbbbbaaaaabaaabbaaaabbabaabaaab\r\n"
			+ "ababaabbbaaabaaaaaabbbab\r\n"
			+ "bbabbabaabbaaabbbabbbaab\r\n"
			+ "aaaabbbbaaaabababaabaabb\r\n"
			+ "bbabaaabaaaaabbaaaaabbba\r\n"
			+ "abbbababaabaababababaaab\r\n"
			+ "bbbaaaababaaabbbbabababaabbaabbb\r\n"
			+ "aabbababbaabaababaababaaababaabbbaabbbaa\r\n"
			+ "ababababbaaabbaaababbabaababaaaa\r\n"
			+ "bbaaabbbbbabaabbaabaabaaabaaabaaabbaaaab\r\n"
			+ "bbaababaaabbaaaaaaaaabaaaaaaaababababbababaaaabaaaaaabbb\r\n"
			+ "aaaaabaababbbbaabbabbabaaaaabaabaaaaabbabababbbbabbbbaaa\r\n"
			+ "abbabaaababaaaabaaaaabbbbbbbaaaa\r\n"
			+ "aabbaaaaaabbbaabaabbbbaa\r\n"
			+ "abaabaabbabaaaaabbbbbabbbbababaa\r\n"
			+ "abbaabaabbbabaaaaabbaababbaaaaababbbaaab\r\n"
			+ "bbaabbabaabaabaabaaaabaabbabbaaababaaabb\r\n"
			+ "aaababaabababbbabbbaaaabababbbaa\r\n"
			+ "abaaabbababbbababbbaabababbababb\r\n"
			+ "aababaabaabbbabaabbaaaab\r\n"
			+ "abbabaaabaabbababaabbbbaabbaabbabbbaaaaa\r\n"
			+ "aaabbabaaaabbbbbaabbbaabaaababaaaabaaabbbaabbaaa\r\n"
			+ "babbaabbababaabbaabbabbb\r\n"
			+ "aaabbbbbbbabbbaabaabbaba\r\n"
			+ "abaaabbaabaababaabbaababbaababbaaaabbabb\r\n"
			+ "bbabbbbbaababaabbaabbbbbabbbaabb\r\n"
			+ "baabaabaabbabaaabbaaababaabbaaab\r\n"
			+ "bbbaabababbbbbaaaabbaaaaaabbbaaa\r\n"
			+ "aabaabbbaaabbbabbbbaaabaababababbbabbaabbbbbaababaabbbba\r\n"
			+ "abbbbbaaaaabaabababaaabb\r\n"
			+ "bbbabbbbbbaaaabbabbaaaba\r\n"
			+ "abbabaaaababaabbbaaabbbb\r\n"
			+ "ababbabababbbaaaababbbaa\r\n"
			+ "bbbabaaabbaaabaabbabbaaa\r\n"
			+ "babbaabaabbaabaaaabbabbb\r\n"
			+ "babaaaaaabaaababbababbabbbbbaabababbbbbb\r\n"
			+ "baabbbabaaabaababaaaabba\r\n"
			+ "abbabbbbaabaabababbbabba\r\n"
			+ "baaababbbbaababaaabbabaabbbaababaabbabbababbbbab\r\n"
			+ "abbbbaababaaabbabbababba\r\n"
			+ "bbaabababbaaabbbabbaaaab\r\n"
			+ "baabbaabbbbababaababaaaa\r\n"
			+ "bbabaaaabaabbaababbbaaab\r\n"
			+ "baaaaabbaababaababbaaabbbbaaaaaa\r\n"
			+ "bbabbbbaaabaaabaabbbbabbababbaabbaabbbbb\r\n"
			+ "babbaabbaabbabaaaabbabbb\r\n"
			+ "abaaababbabbbaaabbbbbbbb\r\n"
			+ "abaabbbababbabaaababaabbbbaaabbaaaaabbaa\r\n"
			+ "aabaabbbbbaaaabbaaabaaaa\r\n"
			+ "ababbbbaabaabbbabbbbbabb\r\n"
			+ "aabbbbabaabbbabaaaabbbab\r\n"
			+ "bbabbbbaaaabaababaabaaab\r\n"
			+ "aabbbbbbbbaaabbbbaabbaabaababaabbbababba\r\n"
			+ "abaababbabbabaaaababbbba\r\n"
			+ "aaabbbbbbbabbbbabbbbbbbabbbbbbbaabbaabbabbaabaabbabaabaa\r\n"
			+ "baaabaaaaaababbabababbbb\r\n"
			+ "bbaaaabbaaaaaabbaababbaa\r\n"
			+ "aaaaababbabbbaaabbabaaaaabbbabbbbabbbbab\r\n"
			+ "baaabaabbabbabbabbaaaaaa\r\n"
			+ "bababbbabaaaaaabbaaaabababbbaabbbbbaaaaa\r\n"
			+ "abaaaaabbaaaabaabbbbbbaabaaabbaababaabbb\r\n"
			+ "bbaaabbababbbaaabbbbaaba\r\n"
			+ "babbaabbabbbbbaababbbbab\r\n"
			+ "abaabbbbbbaaababbabababbbabbabababbbabbbbaaabbbb\r\n"
			+ "babbabababbbbaabaaababaaaabaabba\r\n"
			+ "abbabbaaabaabaaaabbaabbabbbaaaaa\r\n"
			+ "bbaaaababbabaaabaabbabba\r\n"
			+ "bbabaaabaaaabaabbbbaaaabaabbbbaa\r\n"
			+ "bbabbbbbbbbbbbaaaabbbbbaabaabaaa\r\n"
			+ "bbbbaaabaaabbabaaaababab\r\n"
			+ "babbbbaabaaaaabbbbaaaababbabbbbbaababbbabbababbbbaaaabba\r\n"
			+ "aabbbbabbbbbbaaaabbaaaaa\r\n"
			+ "bbaabababbabaaabbabbbbab";
	
	@Override
	public String part1() {
		String[] chunks = input.split("\r\n\r\n");
		String[] rules = chunks[0].split("\r\n");
		Rule.rules = new Rule[rules.length];
		for(String rule : rules) {
			String[] parts = rule.split(": ");
			Rule r = new Rule(parts[1]);
			Rule.rules[Integer.parseInt(parts[0])] = r;
		}
		
		//construct rule zero
		HashSet<String> zero = Rule.rules[0].possibilities();
		
		int counter = 0;
		for(String s : chunks[1].split("\r\n")) {
			if(zero.contains(s))
				counter++;
		}
		
		return Integer.toString(counter);
	}

	@Override
	public String part2() {
		//based on our new rules, we can get an idea of what a match should look like
		//for rule 8: 42 | 42 8, a match will be in the form (42) (42)*, where (42)* represents any number of matches to 42
		//for rule 11: 42 31 | 42 11 31, a match will be in the form (42 (42 ()* 31)* 31) , where the () represents further nested 42 (optional nesting) 31 pairs
		//notably, both 42 and 31 point to messages with length of 8
		//also notably, 0 points directly to 8 and 11, meaning our only important rules are 42 and 31 (and whatever subrules that determine them, but these are non-circular)
		//therefore, a rule that satisfies the rule zero must follow the format (42) (42)* (42 (42 ()* 31)* 31)
		//therefore, we can make a few assertions:
		//1. for a message to be valid, it must be made up of sets of 8 characters that match either 42 or 31
		//1a. a message must therefore be a size multiple of 8
		//2. the minimum possible message must match the state 42 42 31, so there must be at minimum 1 31, and 2 42s
		//2a. furthermore, any message must have at least 1 more 42 than 31, because there has to be a leading 42 before the 42 () 31 pattern is repeated some number of times
		//3. after a block of 8 that fits rule 31, no rules can fit rule 42 (because all 42s happen before 31s)
		//therefore, using these 5 assertions, we can evaluate each message!
		
		//create rule set as normal
		String[] chunks = input.split("\r\n\r\n");
		String[] rules = chunks[0].split("\r\n");
		Rule.rules = new Rule[rules.length];
		for(String rule : rules) {
			String[] parts = rule.split(": ");
			Rule r = new Rule(parts[1]);
			Rule.rules[Integer.parseInt(parts[0])] = r;
		}
		
		//construct rules 31 and 42
		HashSet<String> thirtyOne = Rule.rules[31].possibilities();
		HashSet<String> fortyTwo = Rule.rules[42].possibilities();
		
		int counter = 0;
		messages:
		for(String s : chunks[1].split("\r\n")) {
			if(s.length() % 8 != 0)
				continue;
			
			ArrayList<String> eights = new ArrayList<String>();
			for(int i = 0; i < s.length(); i += 8) {
				eights.add(s.substring(i,i+8));
			}
			
			boolean seen31 = false;
			int fortyTwoCount = 0;
			int thirtyOneCount = 0;
			for(int i = 0; i < eights.size(); i++) {
				String cur = eights.get(i);
				//if already seen a 31, only match 31
				if(seen31) {
					if(thirtyOne.contains(cur)) {
						thirtyOneCount++;
					} else {
						//message invalid, skip
						continue messages;
					}
				} else {
					if(fortyTwo.contains(cur)) {
						fortyTwoCount++;
					} else if(thirtyOne.contains(cur)) {
						seen31 = true;
						thirtyOneCount++;
					} else {
						//message invalid, skip
						continue messages;
					}
				}
			}
			//lastly, verify more 42s than 31s, and increment counter
			//also, verify that there are at least the minimum number of each pair
			//remember, our minimum condition is 42 42 31, so at least 1 31 and 2 42s
			if(fortyTwoCount > thirtyOneCount && thirtyOneCount > 0 && fortyTwoCount > 1) {
				counter++;
			}
		}
		
		return Integer.toString(counter);
	}

	public static void main(String[] args) {
		DayRunner.run(new Day19());
	}

}
