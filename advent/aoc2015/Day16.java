package advent.aoc2015;

import java.util.ArrayList;
import java.util.Arrays;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;

public class Day16 implements IDay {

	String input = "Sue 1: goldfish: 6, trees: 9, akitas: 0\r\n"
			+ "Sue 2: goldfish: 7, trees: 1, akitas: 0\r\n"
			+ "Sue 3: cars: 10, akitas: 6, perfumes: 7\r\n"
			+ "Sue 4: perfumes: 2, vizslas: 0, cars: 6\r\n"
			+ "Sue 5: goldfish: 1, trees: 3, perfumes: 10\r\n"
			+ "Sue 6: children: 9, vizslas: 7, cars: 9\r\n"
			+ "Sue 7: cars: 6, vizslas: 5, cats: 3\r\n"
			+ "Sue 8: akitas: 10, vizslas: 9, children: 3\r\n"
			+ "Sue 9: vizslas: 8, cats: 2, trees: 1\r\n"
			+ "Sue 10: perfumes: 10, trees: 6, cars: 4\r\n"
			+ "Sue 11: cars: 9, children: 1, cats: 1\r\n"
			+ "Sue 12: pomeranians: 4, akitas: 6, goldfish: 8\r\n"
			+ "Sue 13: cats: 10, children: 5, trees: 9\r\n"
			+ "Sue 14: perfumes: 8, vizslas: 3, samoyeds: 1\r\n"
			+ "Sue 15: vizslas: 2, perfumes: 8, trees: 3\r\n"
			+ "Sue 16: pomeranians: 10, trees: 9, samoyeds: 4\r\n"
			+ "Sue 17: akitas: 7, vizslas: 0, goldfish: 6\r\n"
			+ "Sue 18: trees: 5, vizslas: 9, cars: 0\r\n"
			+ "Sue 19: akitas: 3, goldfish: 9, trees: 10\r\n"
			+ "Sue 20: perfumes: 7, samoyeds: 3, vizslas: 10\r\n"
			+ "Sue 21: perfumes: 7, pomeranians: 10, akitas: 8\r\n"
			+ "Sue 22: vizslas: 6, trees: 8, akitas: 10\r\n"
			+ "Sue 23: goldfish: 0, trees: 4, children: 9\r\n"
			+ "Sue 24: goldfish: 7, pomeranians: 9, akitas: 4\r\n"
			+ "Sue 25: cars: 7, trees: 4, pomeranians: 4\r\n"
			+ "Sue 26: trees: 9, akitas: 9, pomeranians: 7\r\n"
			+ "Sue 27: samoyeds: 0, perfumes: 9, goldfish: 10\r\n"
			+ "Sue 28: cars: 5, trees: 7, vizslas: 1\r\n"
			+ "Sue 29: perfumes: 9, trees: 1, children: 6\r\n"
			+ "Sue 30: goldfish: 10, trees: 0, cars: 4\r\n"
			+ "Sue 31: akitas: 2, perfumes: 5, goldfish: 5\r\n"
			+ "Sue 32: goldfish: 0, akitas: 5, trees: 0\r\n"
			+ "Sue 33: vizslas: 2, akitas: 2, samoyeds: 3\r\n"
			+ "Sue 34: goldfish: 8, perfumes: 5, cars: 3\r\n"
			+ "Sue 35: akitas: 1, cats: 4, trees: 9\r\n"
			+ "Sue 36: cars: 4, vizslas: 4, goldfish: 7\r\n"
			+ "Sue 37: akitas: 5, perfumes: 7, trees: 3\r\n"
			+ "Sue 38: goldfish: 10, trees: 2, vizslas: 9\r\n"
			+ "Sue 39: goldfish: 4, pomeranians: 5, vizslas: 5\r\n"
			+ "Sue 40: perfumes: 5, samoyeds: 4, akitas: 6\r\n"
			+ "Sue 41: goldfish: 9, cars: 4, perfumes: 5\r\n"
			+ "Sue 42: trees: 6, pomeranians: 9, goldfish: 8\r\n"
			+ "Sue 43: perfumes: 7, pomeranians: 1, akitas: 2\r\n"
			+ "Sue 44: vizslas: 9, cars: 5, cats: 0\r\n"
			+ "Sue 45: akitas: 1, goldfish: 6, trees: 0\r\n"
			+ "Sue 46: akitas: 5, vizslas: 8, trees: 2\r\n"
			+ "Sue 47: trees: 9, akitas: 2, vizslas: 9\r\n"
			+ "Sue 48: goldfish: 10, trees: 5, akitas: 2\r\n"
			+ "Sue 49: cars: 7, vizslas: 2, perfumes: 6\r\n"
			+ "Sue 50: akitas: 5, goldfish: 6, perfumes: 0\r\n"
			+ "Sue 51: cars: 9, cats: 7, trees: 5\r\n"
			+ "Sue 52: akitas: 7, goldfish: 10, cars: 0\r\n"
			+ "Sue 53: cars: 10, cats: 4, perfumes: 2\r\n"
			+ "Sue 54: goldfish: 2, pomeranians: 5, perfumes: 10\r\n"
			+ "Sue 55: vizslas: 5, akitas: 4, cars: 8\r\n"
			+ "Sue 56: goldfish: 9, vizslas: 4, akitas: 5\r\n"
			+ "Sue 57: perfumes: 8, samoyeds: 7, cars: 9\r\n"
			+ "Sue 58: cars: 5, akitas: 7, perfumes: 8\r\n"
			+ "Sue 59: samoyeds: 8, cars: 10, vizslas: 10\r\n"
			+ "Sue 60: akitas: 6, samoyeds: 0, goldfish: 3\r\n"
			+ "Sue 61: trees: 8, pomeranians: 0, akitas: 2\r\n"
			+ "Sue 62: trees: 1, perfumes: 3, vizslas: 4\r\n"
			+ "Sue 63: vizslas: 6, samoyeds: 9, goldfish: 8\r\n"
			+ "Sue 64: goldfish: 7, trees: 6, vizslas: 3\r\n"
			+ "Sue 65: cars: 1, vizslas: 0, akitas: 6\r\n"
			+ "Sue 66: cats: 6, pomeranians: 4, cars: 9\r\n"
			+ "Sue 67: trees: 10, pomeranians: 7, samoyeds: 3\r\n"
			+ "Sue 68: pomeranians: 5, goldfish: 9, akitas: 1\r\n"
			+ "Sue 69: akitas: 1, vizslas: 0, trees: 9\r\n"
			+ "Sue 70: cats: 4, goldfish: 4, vizslas: 10\r\n"
			+ "Sue 71: vizslas: 7, perfumes: 7, trees: 8\r\n"
			+ "Sue 72: children: 2, vizslas: 9, cats: 3\r\n"
			+ "Sue 73: cars: 8, pomeranians: 0, perfumes: 6\r\n"
			+ "Sue 74: akitas: 1, pomeranians: 8, vizslas: 10\r\n"
			+ "Sue 75: vizslas: 5, perfumes: 5, cars: 7\r\n"
			+ "Sue 76: cars: 3, vizslas: 3, goldfish: 0\r\n"
			+ "Sue 77: akitas: 9, samoyeds: 1, pomeranians: 3\r\n"
			+ "Sue 78: trees: 0, vizslas: 0, akitas: 6\r\n"
			+ "Sue 79: pomeranians: 9, cars: 1, perfumes: 0\r\n"
			+ "Sue 80: perfumes: 10, trees: 1, cats: 0\r\n"
			+ "Sue 81: goldfish: 5, akitas: 9, trees: 0\r\n"
			+ "Sue 82: vizslas: 1, akitas: 6, children: 4\r\n"
			+ "Sue 83: samoyeds: 7, perfumes: 8, pomeranians: 4\r\n"
			+ "Sue 84: perfumes: 3, children: 3, cats: 7\r\n"
			+ "Sue 85: goldfish: 9, trees: 3, cars: 9\r\n"
			+ "Sue 86: cars: 0, perfumes: 9, vizslas: 0\r\n"
			+ "Sue 87: children: 3, trees: 4, akitas: 3\r\n"
			+ "Sue 88: trees: 1, samoyeds: 1, goldfish: 0\r\n"
			+ "Sue 89: akitas: 8, cars: 3, vizslas: 9\r\n"
			+ "Sue 90: pomeranians: 9, trees: 9, goldfish: 8\r\n"
			+ "Sue 91: goldfish: 7, trees: 10, children: 0\r\n"
			+ "Sue 92: cats: 9, cars: 7, perfumes: 7\r\n"
			+ "Sue 93: vizslas: 2, goldfish: 7, cats: 9\r\n"
			+ "Sue 94: akitas: 5, cars: 8, vizslas: 4\r\n"
			+ "Sue 95: goldfish: 7, vizslas: 1, perfumes: 2\r\n"
			+ "Sue 96: goldfish: 5, trees: 6, perfumes: 10\r\n"
			+ "Sue 97: trees: 0, perfumes: 7, cars: 0\r\n"
			+ "Sue 98: cars: 2, perfumes: 6, trees: 8\r\n"
			+ "Sue 99: trees: 10, children: 7, cats: 9\r\n"
			+ "Sue 100: samoyeds: 5, goldfish: 6, vizslas: 6\r\n"
			+ "Sue 101: cars: 10, perfumes: 9, vizslas: 3\r\n"
			+ "Sue 102: pomeranians: 6, trees: 1, samoyeds: 4\r\n"
			+ "Sue 103: cars: 2, perfumes: 1, goldfish: 5\r\n"
			+ "Sue 104: goldfish: 2, cars: 8, pomeranians: 2\r\n"
			+ "Sue 105: goldfish: 6, vizslas: 0, trees: 10\r\n"
			+ "Sue 106: trees: 10, akitas: 10, pomeranians: 0\r\n"
			+ "Sue 107: vizslas: 2, pomeranians: 10, trees: 3\r\n"
			+ "Sue 108: children: 3, vizslas: 8, akitas: 7\r\n"
			+ "Sue 109: perfumes: 2, akitas: 2, samoyeds: 3\r\n"
			+ "Sue 110: goldfish: 7, trees: 1, perfumes: 1\r\n"
			+ "Sue 111: akitas: 2, cars: 9, perfumes: 2\r\n"
			+ "Sue 112: children: 10, cars: 0, akitas: 3\r\n"
			+ "Sue 113: akitas: 9, vizslas: 4, children: 3\r\n"
			+ "Sue 114: pomeranians: 3, trees: 2, goldfish: 5\r\n"
			+ "Sue 115: perfumes: 8, cars: 6, trees: 0\r\n"
			+ "Sue 116: samoyeds: 6, children: 3, pomeranians: 1\r\n"
			+ "Sue 117: goldfish: 1, trees: 2, akitas: 1\r\n"
			+ "Sue 118: goldfish: 10, akitas: 10, samoyeds: 0\r\n"
			+ "Sue 119: vizslas: 10, perfumes: 6, cars: 0\r\n"
			+ "Sue 120: cars: 2, perfumes: 9, goldfish: 5\r\n"
			+ "Sue 121: vizslas: 2, trees: 2, cars: 6\r\n"
			+ "Sue 122: vizslas: 3, trees: 0, akitas: 2\r\n"
			+ "Sue 123: akitas: 5, samoyeds: 7, goldfish: 1\r\n"
			+ "Sue 124: goldfish: 8, samoyeds: 7, trees: 8\r\n"
			+ "Sue 125: trees: 3, goldfish: 8, perfumes: 5\r\n"
			+ "Sue 126: cats: 3, vizslas: 9, goldfish: 0\r\n"
			+ "Sue 127: pomeranians: 9, goldfish: 3, perfumes: 6\r\n"
			+ "Sue 128: vizslas: 4, cars: 8, goldfish: 5\r\n"
			+ "Sue 129: vizslas: 8, children: 5, perfumes: 8\r\n"
			+ "Sue 130: cars: 7, children: 7, cats: 3\r\n"
			+ "Sue 131: perfumes: 1, akitas: 8, vizslas: 9\r\n"
			+ "Sue 132: perfumes: 7, samoyeds: 10, pomeranians: 6\r\n"
			+ "Sue 133: cars: 5, perfumes: 3, goldfish: 7\r\n"
			+ "Sue 134: perfumes: 9, akitas: 2, cats: 3\r\n"
			+ "Sue 135: perfumes: 1, trees: 9, vizslas: 9\r\n"
			+ "Sue 136: akitas: 7, cars: 3, perfumes: 7\r\n"
			+ "Sue 137: vizslas: 9, goldfish: 8, cars: 5\r\n"
			+ "Sue 138: trees: 0, samoyeds: 1, cars: 3\r\n"
			+ "Sue 139: cars: 0, perfumes: 6, trees: 0\r\n"
			+ "Sue 140: pomeranians: 4, cars: 1, perfumes: 7\r\n"
			+ "Sue 141: vizslas: 10, akitas: 8, cats: 3\r\n"
			+ "Sue 142: trees: 1, cats: 6, vizslas: 5\r\n"
			+ "Sue 143: pomeranians: 9, cars: 7, perfumes: 9\r\n"
			+ "Sue 144: cars: 0, perfumes: 2, pomeranians: 1\r\n"
			+ "Sue 145: trees: 1, goldfish: 9, perfumes: 8\r\n"
			+ "Sue 146: cars: 8, children: 5, vizslas: 2\r\n"
			+ "Sue 147: perfumes: 2, goldfish: 5, cars: 0\r\n"
			+ "Sue 148: akitas: 2, perfumes: 7, pomeranians: 6\r\n"
			+ "Sue 149: goldfish: 8, cars: 0, trees: 1\r\n"
			+ "Sue 150: akitas: 6, perfumes: 5, trees: 0\r\n"
			+ "Sue 151: vizslas: 6, samoyeds: 8, akitas: 10\r\n"
			+ "Sue 152: trees: 7, akitas: 7, perfumes: 6\r\n"
			+ "Sue 153: goldfish: 9, cats: 9, cars: 3\r\n"
			+ "Sue 154: vizslas: 10, trees: 0, cars: 9\r\n"
			+ "Sue 155: perfumes: 3, children: 2, goldfish: 1\r\n"
			+ "Sue 156: goldfish: 7, perfumes: 5, akitas: 6\r\n"
			+ "Sue 157: cats: 10, trees: 1, goldfish: 0\r\n"
			+ "Sue 158: cats: 7, children: 7, vizslas: 6\r\n"
			+ "Sue 159: perfumes: 9, akitas: 0, cars: 0\r\n"
			+ "Sue 160: akitas: 3, goldfish: 10, pomeranians: 2\r\n"
			+ "Sue 161: goldfish: 10, cars: 6, perfumes: 3\r\n"
			+ "Sue 162: trees: 0, cars: 9, goldfish: 1\r\n"
			+ "Sue 163: cars: 8, perfumes: 9, vizslas: 5\r\n"
			+ "Sue 164: goldfish: 1, trees: 10, children: 6\r\n"
			+ "Sue 165: goldfish: 0, vizslas: 6, cars: 0\r\n"
			+ "Sue 166: akitas: 5, vizslas: 1, cars: 5\r\n"
			+ "Sue 167: vizslas: 1, samoyeds: 1, children: 4\r\n"
			+ "Sue 168: samoyeds: 7, vizslas: 7, akitas: 3\r\n"
			+ "Sue 169: goldfish: 3, cats: 9, trees: 2\r\n"
			+ "Sue 170: cars: 5, perfumes: 9, vizslas: 5\r\n"
			+ "Sue 171: goldfish: 7, cars: 6, perfumes: 10\r\n"
			+ "Sue 172: cats: 6, akitas: 1, children: 6\r\n"
			+ "Sue 173: cats: 4, goldfish: 1, children: 3\r\n"
			+ "Sue 174: cars: 2, pomeranians: 2, vizslas: 7\r\n"
			+ "Sue 175: trees: 0, children: 4, goldfish: 7\r\n"
			+ "Sue 176: children: 8, cars: 5, cats: 9\r\n"
			+ "Sue 177: pomeranians: 4, vizslas: 7, trees: 3\r\n"
			+ "Sue 178: vizslas: 6, perfumes: 10, akitas: 6\r\n"
			+ "Sue 179: cars: 4, akitas: 4, trees: 4\r\n"
			+ "Sue 180: akitas: 8, goldfish: 6, trees: 9\r\n"
			+ "Sue 181: perfumes: 3, vizslas: 10, cars: 3\r\n"
			+ "Sue 182: vizslas: 3, samoyeds: 3, goldfish: 7\r\n"
			+ "Sue 183: goldfish: 10, perfumes: 2, cats: 1\r\n"
			+ "Sue 184: goldfish: 5, trees: 1, perfumes: 1\r\n"
			+ "Sue 185: vizslas: 10, trees: 9, perfumes: 2\r\n"
			+ "Sue 186: goldfish: 6, perfumes: 9, trees: 1\r\n"
			+ "Sue 187: cars: 0, trees: 9, goldfish: 6\r\n"
			+ "Sue 188: cars: 0, trees: 1, vizslas: 9\r\n"
			+ "Sue 189: akitas: 7, vizslas: 2, trees: 0\r\n"
			+ "Sue 190: pomeranians: 5, perfumes: 8, akitas: 10\r\n"
			+ "Sue 191: vizslas: 5, akitas: 3, cats: 0\r\n"
			+ "Sue 192: children: 1, trees: 1, cars: 2\r\n"
			+ "Sue 193: cars: 3, goldfish: 9, trees: 2\r\n"
			+ "Sue 194: samoyeds: 3, akitas: 4, perfumes: 8\r\n"
			+ "Sue 195: trees: 1, vizslas: 8, akitas: 10\r\n"
			+ "Sue 196: akitas: 6, cars: 5, pomeranians: 0\r\n"
			+ "Sue 197: akitas: 5, vizslas: 5, cats: 1\r\n"
			+ "Sue 198: trees: 4, cars: 6, goldfish: 6\r\n"
			+ "Sue 199: cats: 7, cars: 5, goldfish: 6\r\n"
			+ "Sue 200: vizslas: 4, cats: 0, akitas: 9\r\n"
			+ "Sue 201: pomeranians: 1, perfumes: 4, children: 2\r\n"
			+ "Sue 202: cats: 1, perfumes: 4, vizslas: 3\r\n"
			+ "Sue 203: vizslas: 1, akitas: 9, children: 5\r\n"
			+ "Sue 204: perfumes: 8, cars: 7, trees: 4\r\n"
			+ "Sue 205: perfumes: 7, pomeranians: 5, cats: 9\r\n"
			+ "Sue 206: vizslas: 8, trees: 2, akitas: 2\r\n"
			+ "Sue 207: akitas: 6, vizslas: 2, perfumes: 10\r\n"
			+ "Sue 208: vizslas: 1, children: 7, akitas: 4\r\n"
			+ "Sue 209: perfumes: 4, trees: 2, children: 1\r\n"
			+ "Sue 210: goldfish: 0, vizslas: 2, samoyeds: 10\r\n"
			+ "Sue 211: cars: 8, perfumes: 3, trees: 1\r\n"
			+ "Sue 212: cars: 8, samoyeds: 5, pomeranians: 8\r\n"
			+ "Sue 213: akitas: 2, goldfish: 8, pomeranians: 2\r\n"
			+ "Sue 214: akitas: 6, pomeranians: 2, cars: 0\r\n"
			+ "Sue 215: trees: 10, pomeranians: 4, vizslas: 0\r\n"
			+ "Sue 216: perfumes: 0, cars: 8, trees: 0\r\n"
			+ "Sue 217: samoyeds: 8, akitas: 7, children: 10\r\n"
			+ "Sue 218: perfumes: 1, vizslas: 6, children: 0\r\n"
			+ "Sue 219: children: 1, goldfish: 4, trees: 1\r\n"
			+ "Sue 220: akitas: 10, goldfish: 10, trees: 5\r\n"
			+ "Sue 221: cars: 7, pomeranians: 6, perfumes: 3\r\n"
			+ "Sue 222: vizslas: 6, children: 0, akitas: 5\r\n"
			+ "Sue 223: perfumes: 9, cars: 1, trees: 6\r\n"
			+ "Sue 224: pomeranians: 1, trees: 0, vizslas: 0\r\n"
			+ "Sue 225: goldfish: 8, akitas: 4, perfumes: 10\r\n"
			+ "Sue 226: pomeranians: 7, cats: 7, children: 4\r\n"
			+ "Sue 227: trees: 0, akitas: 2, perfumes: 1\r\n"
			+ "Sue 228: vizslas: 6, cars: 10, perfumes: 9\r\n"
			+ "Sue 229: cars: 0, perfumes: 6, trees: 4\r\n"
			+ "Sue 230: pomeranians: 7, perfumes: 5, trees: 2\r\n"
			+ "Sue 231: goldfish: 9, cars: 6, trees: 7\r\n"
			+ "Sue 232: akitas: 1, vizslas: 5, cars: 3\r\n"
			+ "Sue 233: akitas: 7, samoyeds: 2, vizslas: 5\r\n"
			+ "Sue 234: akitas: 6, cats: 8, pomeranians: 0\r\n"
			+ "Sue 235: pomeranians: 5, akitas: 5, vizslas: 3\r\n"
			+ "Sue 236: goldfish: 5, trees: 6, akitas: 5\r\n"
			+ "Sue 237: goldfish: 9, perfumes: 5, cats: 5\r\n"
			+ "Sue 238: cats: 8, goldfish: 4, perfumes: 0\r\n"
			+ "Sue 239: samoyeds: 8, children: 6, pomeranians: 6\r\n"
			+ "Sue 240: akitas: 4, samoyeds: 10, trees: 8\r\n"
			+ "Sue 241: trees: 2, goldfish: 8, cars: 1\r\n"
			+ "Sue 242: perfumes: 2, cars: 0, akitas: 10\r\n"
			+ "Sue 243: pomeranians: 1, cars: 7, trees: 2\r\n"
			+ "Sue 244: trees: 9, vizslas: 2, akitas: 10\r\n"
			+ "Sue 245: cars: 9, pomeranians: 4, trees: 0\r\n"
			+ "Sue 246: cars: 9, pomeranians: 7, perfumes: 1\r\n"
			+ "Sue 247: trees: 0, goldfish: 1, akitas: 8\r\n"
			+ "Sue 248: vizslas: 1, cats: 4, akitas: 4\r\n"
			+ "Sue 249: cats: 6, children: 4, goldfish: 9\r\n"
			+ "Sue 250: vizslas: 1, cars: 10, samoyeds: 5\r\n"
			+ "Sue 251: cars: 0, goldfish: 1, vizslas: 7\r\n"
			+ "Sue 252: cars: 7, akitas: 9, vizslas: 10\r\n"
			+ "Sue 253: akitas: 7, vizslas: 2, perfumes: 5\r\n"
			+ "Sue 254: vizslas: 10, akitas: 5, samoyeds: 0\r\n"
			+ "Sue 255: pomeranians: 8, goldfish: 0, cats: 6\r\n"
			+ "Sue 256: cars: 10, goldfish: 8, vizslas: 9\r\n"
			+ "Sue 257: goldfish: 3, perfumes: 9, cats: 3\r\n"
			+ "Sue 258: trees: 6, goldfish: 6, cars: 6\r\n"
			+ "Sue 259: trees: 0, goldfish: 2, perfumes: 8\r\n"
			+ "Sue 260: trees: 5, akitas: 0, cars: 0\r\n"
			+ "Sue 261: pomeranians: 9, goldfish: 7, perfumes: 8\r\n"
			+ "Sue 262: perfumes: 8, vizslas: 6, goldfish: 2\r\n"
			+ "Sue 263: vizslas: 6, trees: 5, goldfish: 9\r\n"
			+ "Sue 264: vizslas: 4, perfumes: 7, cars: 9\r\n"
			+ "Sue 265: goldfish: 10, trees: 3, perfumes: 1\r\n"
			+ "Sue 266: trees: 10, akitas: 8, goldfish: 8\r\n"
			+ "Sue 267: goldfish: 4, trees: 0, samoyeds: 9\r\n"
			+ "Sue 268: vizslas: 1, trees: 0, goldfish: 8\r\n"
			+ "Sue 269: cars: 2, perfumes: 10, goldfish: 5\r\n"
			+ "Sue 270: perfumes: 7, cars: 2, vizslas: 1\r\n"
			+ "Sue 271: cars: 6, perfumes: 10, goldfish: 6\r\n"
			+ "Sue 272: samoyeds: 4, goldfish: 2, vizslas: 9\r\n"
			+ "Sue 273: perfumes: 4, goldfish: 4, vizslas: 1\r\n"
			+ "Sue 274: children: 4, cars: 4, perfumes: 3\r\n"
			+ "Sue 275: children: 8, vizslas: 3, trees: 2\r\n"
			+ "Sue 276: vizslas: 5, children: 7, perfumes: 3\r\n"
			+ "Sue 277: perfumes: 3, cats: 4, vizslas: 5\r\n"
			+ "Sue 278: cars: 1, samoyeds: 10, akitas: 2\r\n"
			+ "Sue 279: trees: 9, perfumes: 9, cars: 10\r\n"
			+ "Sue 280: vizslas: 5, trees: 0, perfumes: 6\r\n"
			+ "Sue 281: vizslas: 3, akitas: 10, pomeranians: 7\r\n"
			+ "Sue 282: trees: 1, children: 2, akitas: 8\r\n"
			+ "Sue 283: akitas: 9, goldfish: 6, cats: 5\r\n"
			+ "Sue 284: cars: 9, children: 10, pomeranians: 2\r\n"
			+ "Sue 285: pomeranians: 0, perfumes: 4, cars: 7\r\n"
			+ "Sue 286: perfumes: 0, vizslas: 10, akitas: 10\r\n"
			+ "Sue 287: cats: 2, perfumes: 3, trees: 5\r\n"
			+ "Sue 288: akitas: 9, vizslas: 8, samoyeds: 9\r\n"
			+ "Sue 289: perfumes: 6, children: 2, cars: 7\r\n"
			+ "Sue 290: akitas: 0, children: 5, cars: 5\r\n"
			+ "Sue 291: cars: 4, perfumes: 0, trees: 1\r\n"
			+ "Sue 292: cats: 0, cars: 8, perfumes: 6\r\n"
			+ "Sue 293: akitas: 9, cats: 5, children: 5\r\n"
			+ "Sue 294: akitas: 4, cars: 9, goldfish: 3\r\n"
			+ "Sue 295: cars: 2, akitas: 3, perfumes: 7\r\n"
			+ "Sue 296: perfumes: 4, cars: 7, goldfish: 10\r\n"
			+ "Sue 297: trees: 5, akitas: 8, vizslas: 1\r\n"
			+ "Sue 298: perfumes: 0, goldfish: 6, trees: 9\r\n"
			+ "Sue 299: perfumes: 6, samoyeds: 8, cars: 1\r\n"
			+ "Sue 300: goldfish: 10, perfumes: 4, akitas: 2\r\n"
			+ "Sue 301: cars: 3, trees: 0, goldfish: 8\r\n"
			+ "Sue 302: perfumes: 7, samoyeds: 2, vizslas: 7\r\n"
			+ "Sue 303: children: 10, goldfish: 7, perfumes: 2\r\n"
			+ "Sue 304: samoyeds: 8, vizslas: 2, cars: 1\r\n"
			+ "Sue 305: trees: 1, cats: 0, goldfish: 10\r\n"
			+ "Sue 306: trees: 4, perfumes: 2, cars: 7\r\n"
			+ "Sue 307: cars: 6, vizslas: 2, children: 6\r\n"
			+ "Sue 308: vizslas: 2, cars: 0, akitas: 7\r\n"
			+ "Sue 309: cars: 3, vizslas: 8, perfumes: 6\r\n"
			+ "Sue 310: goldfish: 7, perfumes: 7, vizslas: 3\r\n"
			+ "Sue 311: pomeranians: 10, trees: 2, cars: 0\r\n"
			+ "Sue 312: samoyeds: 2, vizslas: 9, akitas: 1\r\n"
			+ "Sue 313: cars: 4, pomeranians: 7, goldfish: 7\r\n"
			+ "Sue 314: akitas: 2, pomeranians: 9, samoyeds: 10\r\n"
			+ "Sue 315: akitas: 3, vizslas: 2, trees: 0\r\n"
			+ "Sue 316: cars: 0, perfumes: 4, pomeranians: 6\r\n"
			+ "Sue 317: akitas: 10, goldfish: 3, pomeranians: 7\r\n"
			+ "Sue 318: cars: 9, trees: 0, pomeranians: 9\r\n"
			+ "Sue 319: akitas: 3, vizslas: 7, children: 10\r\n"
			+ "Sue 320: vizslas: 0, akitas: 8, pomeranians: 4\r\n"
			+ "Sue 321: cars: 10, akitas: 9, vizslas: 3\r\n"
			+ "Sue 322: perfumes: 0, akitas: 8, vizslas: 6\r\n"
			+ "Sue 323: vizslas: 10, perfumes: 5, cars: 3\r\n"
			+ "Sue 324: akitas: 0, goldfish: 6, vizslas: 7\r\n"
			+ "Sue 325: perfumes: 9, vizslas: 5, pomeranians: 2\r\n"
			+ "Sue 326: vizslas: 6, goldfish: 10, pomeranians: 8\r\n"
			+ "Sue 327: vizslas: 10, cars: 1, akitas: 7\r\n"
			+ "Sue 328: trees: 1, perfumes: 10, cars: 10\r\n"
			+ "Sue 329: pomeranians: 5, samoyeds: 3, cars: 10\r\n"
			+ "Sue 330: akitas: 6, cars: 1, pomeranians: 4\r\n"
			+ "Sue 331: cars: 5, children: 2, trees: 0\r\n"
			+ "Sue 332: vizslas: 6, pomeranians: 1, perfumes: 0\r\n"
			+ "Sue 333: akitas: 7, trees: 1, cats: 9\r\n"
			+ "Sue 334: vizslas: 6, goldfish: 9, akitas: 7\r\n"
			+ "Sue 335: akitas: 3, samoyeds: 3, cars: 3\r\n"
			+ "Sue 336: samoyeds: 10, perfumes: 9, trees: 6\r\n"
			+ "Sue 337: vizslas: 2, cars: 9, akitas: 0\r\n"
			+ "Sue 338: akitas: 6, perfumes: 9, vizslas: 3\r\n"
			+ "Sue 339: cars: 3, samoyeds: 8, trees: 2\r\n"
			+ "Sue 340: cats: 7, perfumes: 8, cars: 9\r\n"
			+ "Sue 341: goldfish: 9, perfumes: 5, cars: 10\r\n"
			+ "Sue 342: trees: 0, akitas: 3, perfumes: 5\r\n"
			+ "Sue 343: perfumes: 2, children: 0, cars: 6\r\n"
			+ "Sue 344: goldfish: 8, trees: 8, perfumes: 0\r\n"
			+ "Sue 345: perfumes: 6, cars: 6, goldfish: 5\r\n"
			+ "Sue 346: vizslas: 8, trees: 1, cars: 6\r\n"
			+ "Sue 347: cars: 0, cats: 3, perfumes: 7\r\n"
			+ "Sue 348: children: 7, perfumes: 10, cars: 7\r\n"
			+ "Sue 349: pomeranians: 8, akitas: 5, children: 2\r\n"
			+ "Sue 350: perfumes: 9, pomeranians: 4, goldfish: 3\r\n"
			+ "Sue 351: perfumes: 8, pomeranians: 7, trees: 4\r\n"
			+ "Sue 352: samoyeds: 1, goldfish: 9, akitas: 8\r\n"
			+ "Sue 353: akitas: 6, goldfish: 10, vizslas: 8\r\n"
			+ "Sue 354: akitas: 7, cars: 2, goldfish: 6\r\n"
			+ "Sue 355: cars: 3, goldfish: 6, akitas: 5\r\n"
			+ "Sue 356: akitas: 2, goldfish: 9, pomeranians: 1\r\n"
			+ "Sue 357: goldfish: 10, cars: 6, pomeranians: 9\r\n"
			+ "Sue 358: trees: 0, children: 2, goldfish: 6\r\n"
			+ "Sue 359: samoyeds: 3, cars: 2, akitas: 4\r\n"
			+ "Sue 360: trees: 1, goldfish: 8, cars: 5\r\n"
			+ "Sue 361: akitas: 5, vizslas: 7, perfumes: 1\r\n"
			+ "Sue 362: cats: 5, vizslas: 9, children: 4\r\n"
			+ "Sue 363: goldfish: 9, perfumes: 3, vizslas: 9\r\n"
			+ "Sue 364: children: 7, samoyeds: 2, pomeranians: 10\r\n"
			+ "Sue 365: perfumes: 9, akitas: 10, pomeranians: 4\r\n"
			+ "Sue 366: cars: 10, trees: 3, cats: 4\r\n"
			+ "Sue 367: vizslas: 6, akitas: 10, perfumes: 5\r\n"
			+ "Sue 368: akitas: 9, vizslas: 9, children: 4\r\n"
			+ "Sue 369: goldfish: 8, trees: 2, perfumes: 5\r\n"
			+ "Sue 370: trees: 0, children: 4, cars: 8\r\n"
			+ "Sue 371: cats: 6, perfumes: 0, vizslas: 2\r\n"
			+ "Sue 372: akitas: 7, cars: 5, perfumes: 3\r\n"
			+ "Sue 373: cars: 0, perfumes: 4, pomeranians: 10\r\n"
			+ "Sue 374: akitas: 5, perfumes: 5, vizslas: 2\r\n"
			+ "Sue 375: goldfish: 7, trees: 10, pomeranians: 7\r\n"
			+ "Sue 376: cars: 8, trees: 1, pomeranians: 8\r\n"
			+ "Sue 377: cars: 0, akitas: 9, vizslas: 1\r\n"
			+ "Sue 378: akitas: 5, perfumes: 3, vizslas: 7\r\n"
			+ "Sue 379: trees: 2, goldfish: 8, pomeranians: 8\r\n"
			+ "Sue 380: akitas: 5, cars: 9, perfumes: 9\r\n"
			+ "Sue 381: cars: 2, perfumes: 6, trees: 3\r\n"
			+ "Sue 382: perfumes: 6, vizslas: 2, goldfish: 9\r\n"
			+ "Sue 383: akitas: 8, vizslas: 7, cats: 1\r\n"
			+ "Sue 384: akitas: 9, trees: 10, vizslas: 7\r\n"
			+ "Sue 385: cars: 0, perfumes: 7, vizslas: 2\r\n"
			+ "Sue 386: vizslas: 10, akitas: 4, perfumes: 9\r\n"
			+ "Sue 387: perfumes: 6, pomeranians: 5, samoyeds: 8\r\n"
			+ "Sue 388: vizslas: 10, trees: 9, goldfish: 9\r\n"
			+ "Sue 389: goldfish: 8, akitas: 4, perfumes: 10\r\n"
			+ "Sue 390: goldfish: 6, trees: 8, akitas: 1\r\n"
			+ "Sue 391: vizslas: 4, akitas: 10, goldfish: 7\r\n"
			+ "Sue 392: akitas: 1, vizslas: 6, samoyeds: 5\r\n"
			+ "Sue 393: trees: 6, cars: 3, akitas: 5\r\n"
			+ "Sue 394: goldfish: 9, trees: 3, cars: 5\r\n"
			+ "Sue 395: akitas: 6, samoyeds: 4, goldfish: 4\r\n"
			+ "Sue 396: akitas: 2, trees: 1, cats: 5\r\n"
			+ "Sue 397: cars: 0, children: 9, trees: 10\r\n"
			+ "Sue 398: pomeranians: 3, samoyeds: 9, goldfish: 10\r\n"
			+ "Sue 399: cars: 7, akitas: 4, goldfish: 8\r\n"
			+ "Sue 400: cars: 4, akitas: 5, vizslas: 4\r\n"
			+ "Sue 401: pomeranians: 5, akitas: 8, vizslas: 5\r\n"
			+ "Sue 402: cats: 7, cars: 6, goldfish: 6\r\n"
			+ "Sue 403: samoyeds: 8, perfumes: 4, cars: 5\r\n"
			+ "Sue 404: akitas: 10, goldfish: 4, trees: 2\r\n"
			+ "Sue 405: trees: 8, perfumes: 1, cars: 2\r\n"
			+ "Sue 406: trees: 0, perfumes: 9, pomeranians: 10\r\n"
			+ "Sue 407: perfumes: 4, trees: 7, goldfish: 3\r\n"
			+ "Sue 408: akitas: 1, perfumes: 3, cars: 5\r\n"
			+ "Sue 409: trees: 6, samoyeds: 3, cars: 9\r\n"
			+ "Sue 410: vizslas: 3, goldfish: 5, akitas: 7\r\n"
			+ "Sue 411: goldfish: 10, trees: 1, vizslas: 9\r\n"
			+ "Sue 412: cars: 0, akitas: 6, trees: 6\r\n"
			+ "Sue 413: goldfish: 7, trees: 0, cars: 3\r\n"
			+ "Sue 414: pomeranians: 10, samoyeds: 3, cars: 10\r\n"
			+ "Sue 415: perfumes: 6, trees: 9, cars: 4\r\n"
			+ "Sue 416: trees: 2, cars: 4, goldfish: 8\r\n"
			+ "Sue 417: goldfish: 2, cars: 9, cats: 5\r\n"
			+ "Sue 418: vizslas: 1, cars: 9, akitas: 0\r\n"
			+ "Sue 419: perfumes: 6, cats: 3, children: 9\r\n"
			+ "Sue 420: cats: 5, goldfish: 7, akitas: 9\r\n"
			+ "Sue 421: trees: 1, samoyeds: 6, pomeranians: 1\r\n"
			+ "Sue 422: trees: 10, goldfish: 6, children: 7\r\n"
			+ "Sue 423: cars: 8, goldfish: 7, vizslas: 3\r\n"
			+ "Sue 424: samoyeds: 9, akitas: 7, trees: 5\r\n"
			+ "Sue 425: akitas: 5, children: 4, perfumes: 9\r\n"
			+ "Sue 426: goldfish: 1, children: 9, cats: 2\r\n"
			+ "Sue 427: vizslas: 9, akitas: 7, goldfish: 9\r\n"
			+ "Sue 428: pomeranians: 7, akitas: 5, vizslas: 1\r\n"
			+ "Sue 429: vizslas: 7, goldfish: 7, cars: 9\r\n"
			+ "Sue 430: trees: 7, perfumes: 0, pomeranians: 5\r\n"
			+ "Sue 431: children: 9, perfumes: 5, vizslas: 7\r\n"
			+ "Sue 432: trees: 6, samoyeds: 7, cats: 1\r\n"
			+ "Sue 433: goldfish: 5, trees: 5, children: 6\r\n"
			+ "Sue 434: goldfish: 9, akitas: 7, cars: 3\r\n"
			+ "Sue 435: samoyeds: 10, perfumes: 2, cars: 0\r\n"
			+ "Sue 436: akitas: 5, pomeranians: 4, perfumes: 7\r\n"
			+ "Sue 437: vizslas: 5, cats: 6, perfumes: 5\r\n"
			+ "Sue 438: trees: 2, goldfish: 6, vizslas: 7\r\n"
			+ "Sue 439: samoyeds: 8, pomeranians: 10, goldfish: 1\r\n"
			+ "Sue 440: akitas: 6, children: 9, perfumes: 4\r\n"
			+ "Sue 441: cars: 2, goldfish: 9, children: 0\r\n"
			+ "Sue 442: goldfish: 7, cars: 2, vizslas: 8\r\n"
			+ "Sue 443: goldfish: 6, samoyeds: 3, perfumes: 2\r\n"
			+ "Sue 444: trees: 2, goldfish: 7, cars: 8\r\n"
			+ "Sue 445: trees: 2, pomeranians: 0, children: 0\r\n"
			+ "Sue 446: perfumes: 4, akitas: 4, goldfish: 6\r\n"
			+ "Sue 447: vizslas: 7, akitas: 9, cars: 3\r\n"
			+ "Sue 448: goldfish: 6, trees: 9, cars: 0\r\n"
			+ "Sue 449: samoyeds: 7, perfumes: 4, vizslas: 10\r\n"
			+ "Sue 450: akitas: 7, cars: 10, goldfish: 7\r\n"
			+ "Sue 451: goldfish: 4, children: 7, pomeranians: 4\r\n"
			+ "Sue 452: cats: 4, vizslas: 6, trees: 7\r\n"
			+ "Sue 453: cars: 1, trees: 10, goldfish: 9\r\n"
			+ "Sue 454: trees: 2, goldfish: 3, vizslas: 10\r\n"
			+ "Sue 455: pomeranians: 9, vizslas: 3, akitas: 2\r\n"
			+ "Sue 456: vizslas: 10, akitas: 2, goldfish: 1\r\n"
			+ "Sue 457: trees: 5, cats: 5, children: 8\r\n"
			+ "Sue 458: cars: 6, goldfish: 3, akitas: 9\r\n"
			+ "Sue 459: goldfish: 7, akitas: 2, cats: 7\r\n"
			+ "Sue 460: akitas: 1, cars: 5, children: 8\r\n"
			+ "Sue 461: cars: 8, perfumes: 0, goldfish: 6\r\n"
			+ "Sue 462: pomeranians: 6, cats: 2, perfumes: 6\r\n"
			+ "Sue 463: vizslas: 7, perfumes: 3, goldfish: 3\r\n"
			+ "Sue 464: akitas: 10, goldfish: 10, trees: 1\r\n"
			+ "Sue 465: vizslas: 0, akitas: 2, trees: 2\r\n"
			+ "Sue 466: perfumes: 6, akitas: 8, cars: 2\r\n"
			+ "Sue 467: goldfish: 1, cars: 10, perfumes: 3\r\n"
			+ "Sue 468: goldfish: 4, trees: 2, cars: 9\r\n"
			+ "Sue 469: perfumes: 6, pomeranians: 0, vizslas: 10\r\n"
			+ "Sue 470: samoyeds: 8, children: 0, akitas: 7\r\n"
			+ "Sue 471: children: 3, goldfish: 9, cats: 9\r\n"
			+ "Sue 472: samoyeds: 0, goldfish: 0, trees: 0\r\n"
			+ "Sue 473: trees: 3, goldfish: 4, vizslas: 1\r\n"
			+ "Sue 474: perfumes: 10, cars: 3, trees: 7\r\n"
			+ "Sue 475: akitas: 5, vizslas: 4, goldfish: 5\r\n"
			+ "Sue 476: children: 2, akitas: 7, vizslas: 3\r\n"
			+ "Sue 477: vizslas: 6, pomeranians: 9, trees: 6\r\n"
			+ "Sue 478: vizslas: 7, pomeranians: 6, akitas: 7\r\n"
			+ "Sue 479: trees: 2, perfumes: 2, children: 2\r\n"
			+ "Sue 480: cars: 8, cats: 5, vizslas: 0\r\n"
			+ "Sue 481: trees: 5, goldfish: 0, akitas: 3\r\n"
			+ "Sue 482: cars: 8, perfumes: 6, goldfish: 10\r\n"
			+ "Sue 483: goldfish: 0, cars: 3, perfumes: 10\r\n"
			+ "Sue 484: pomeranians: 1, samoyeds: 1, perfumes: 3\r\n"
			+ "Sue 485: trees: 0, akitas: 2, vizslas: 4\r\n"
			+ "Sue 486: cars: 3, vizslas: 8, goldfish: 1\r\n"
			+ "Sue 487: pomeranians: 9, vizslas: 2, children: 10\r\n"
			+ "Sue 488: akitas: 6, vizslas: 10, perfumes: 9\r\n"
			+ "Sue 489: goldfish: 6, vizslas: 4, cars: 2\r\n"
			+ "Sue 490: vizslas: 10, cats: 8, samoyeds: 1\r\n"
			+ "Sue 491: cats: 9, cars: 1, perfumes: 10\r\n"
			+ "Sue 492: goldfish: 6, cars: 9, pomeranians: 9\r\n"
			+ "Sue 493: children: 10, goldfish: 10, vizslas: 0\r\n"
			+ "Sue 494: pomeranians: 5, cars: 0, vizslas: 0\r\n"
			+ "Sue 495: vizslas: 7, perfumes: 6, samoyeds: 3\r\n"
			+ "Sue 496: trees: 1, cats: 4, cars: 10\r\n"
			+ "Sue 497: cats: 1, perfumes: 0, cars: 7\r\n"
			+ "Sue 498: perfumes: 7, vizslas: 6, cats: 9\r\n"
			+ "Sue 499: vizslas: 8, perfumes: 1, akitas: 3\r\n"
			+ "Sue 500: perfumes: 4, cars: 9, trees: 4";
	
	//hardcoded comparison value array based on puzzle description - may vary
	int[] comparison = new int[] {3,7,2,3,0,0,5,3,2,1};
	
	@Override
	public String part1() {
		//store sues as list of integer list representing counts
		ArrayList<ArrayList<Integer>> sues = new ArrayList<ArrayList<Integer>>();
		for(String s : input.split("\r\n")) {
			ArrayList<String> parts = new ArrayList<String> (Arrays.asList(s.split(" ")));
			//categories for reference:
			//children, cats, samoyeds, pomeranians, akitas, vizslas, goldfish, trees, cars, perfumes
			//arraylist will store characteristics (or -1 if unknown) in above order
			ArrayList<Integer> sue = new ArrayList<Integer>();
			//copy-pasted checks for each characteristic
			if(parts.contains("children:")) {
				String value = parts.get(parts.indexOf("children:") + 1);
				if(value.contains(","))
					value = value.substring(0,value.length()-1);
				sue.add(Integer.parseInt(value));
			} else {
				sue.add(-1);
			}
			if(parts.contains("cats:")) {
				String value = parts.get(parts.indexOf("cats:") + 1);
				if(value.contains(","))
					value = value.substring(0,value.length()-1);
				sue.add(Integer.parseInt(value));
			} else {
				sue.add(-1);
			}
			if(parts.contains("samoyeds:")) {
				String value = parts.get(parts.indexOf("samoyeds:") + 1);
				if(value.contains(","))
					value = value.substring(0,value.length()-1);
				sue.add(Integer.parseInt(value));
			} else {
				sue.add(-1);
			}
			if(parts.contains("pomeranians:")) {
				String value = parts.get(parts.indexOf("pomeranians:") + 1);
				if(value.contains(","))
					value = value.substring(0,value.length()-1);
				sue.add(Integer.parseInt(value));
			} else {
				sue.add(-1);
			}
			if(parts.contains("akitas:")) {
				String value = parts.get(parts.indexOf("akitas:") + 1);
				if(value.contains(","))
					value = value.substring(0,value.length()-1);
				sue.add(Integer.parseInt(value));
			} else {
				sue.add(-1);
			}
			if(parts.contains("vizslas:")) {
				String value = parts.get(parts.indexOf("vizslas:") + 1);
				if(value.contains(","))
					value = value.substring(0,value.length()-1);
				sue.add(Integer.parseInt(value));
			} else {
				sue.add(-1);
			}
			if(parts.contains("goldfish:")) {
				String value = parts.get(parts.indexOf("goldfish:") + 1);
				if(value.contains(","))
					value = value.substring(0,value.length()-1);
				sue.add(Integer.parseInt(value));
			} else {
				sue.add(-1);
			}
			if(parts.contains("trees:")) {
				String value = parts.get(parts.indexOf("trees:") + 1);
				if(value.contains(","))
					value = value.substring(0,value.length()-1);
				sue.add(Integer.parseInt(value));
			} else {
				sue.add(-1);
			}
			if(parts.contains("cars:")) {
				String value = parts.get(parts.indexOf("cars:") + 1);
				if(value.contains(","))
					value = value.substring(0,value.length()-1);
				sue.add(Integer.parseInt(value));
			} else {
				sue.add(-1);
			}
			if(parts.contains("perfumes:")) {
				String value = parts.get(parts.indexOf("perfumes:") + 1);
				if(value.contains(","))
					value = value.substring(0,value.length()-1);
				sue.add(Integer.parseInt(value));
			} else {
				sue.add(-1);
			}
			sues.add(sue);
		}
		sueLoop:
		for(int i = 0; i < 500; i++) {
			ArrayList<Integer> sue = sues.get(i);
			//check each value - if it's not equal to the comparison case AND it's not unknown, skip to next eval
			for(int j = 0; j < sue.size(); j++) {
				if(sue.get(j) != comparison[j] && sue.get(j) != -1)
					continue sueLoop;
			} 
			//if all checks were passed without continuing loop, this is correct aunt sue.
			//adjust for 0-499 range instead of 1-500
			return Integer.toString(i+1);
		}
		return "Solution not found, input error";
	}
	
	@Override
	public String part2() {
		//store sues as list of integer list representing counts
		ArrayList<ArrayList<Integer>> sues = new ArrayList<ArrayList<Integer>>();
		for(String s : input.split("\r\n")) {
			ArrayList<String> parts = new ArrayList<String> (Arrays.asList(s.split(" ")));
			//categories for reference:
			//children, cats, samoyeds, pomeranians, akitas, vizslas, goldfish, trees, cars, perfumes
			//arraylist will store characteristics (or -1 if unknown) in above order
			ArrayList<Integer> sue = new ArrayList<Integer>();
			if(parts.contains("children:")) {
				String value = parts.get(parts.indexOf("children:") + 1);
				if(value.contains(","))
					value = value.substring(0,value.length()-1);
				sue.add(Integer.parseInt(value));
			} else {
				sue.add(-1);
			}
			if(parts.contains("cats:")) {
				String value = parts.get(parts.indexOf("cats:") + 1);
				if(value.contains(","))
					value = value.substring(0,value.length()-1);
				sue.add(Integer.parseInt(value));
			} else {
				sue.add(-1);
			}
			if(parts.contains("samoyeds:")) {
				String value = parts.get(parts.indexOf("samoyeds:") + 1);
				if(value.contains(","))
					value = value.substring(0,value.length()-1);
				sue.add(Integer.parseInt(value));
			} else {
				sue.add(-1);
			}
			if(parts.contains("pomeranians:")) {
				String value = parts.get(parts.indexOf("pomeranians:") + 1);
				if(value.contains(","))
					value = value.substring(0,value.length()-1);
				sue.add(Integer.parseInt(value));
			} else {
				sue.add(-1);
			}
			if(parts.contains("akitas:")) {
				String value = parts.get(parts.indexOf("akitas:") + 1);
				if(value.contains(","))
					value = value.substring(0,value.length()-1);
				sue.add(Integer.parseInt(value));
			} else {
				sue.add(-1);
			}
			if(parts.contains("vizslas:")) {
				String value = parts.get(parts.indexOf("vizslas:") + 1);
				if(value.contains(","))
					value = value.substring(0,value.length()-1);
				sue.add(Integer.parseInt(value));
			} else {
				sue.add(-1);
			}
			if(parts.contains("goldfish:")) {
				String value = parts.get(parts.indexOf("goldfish:") + 1);
				if(value.contains(","))
					value = value.substring(0,value.length()-1);
				sue.add(Integer.parseInt(value));
			} else {
				sue.add(-1);
			}
			if(parts.contains("trees:")) {
				String value = parts.get(parts.indexOf("trees:") + 1);
				if(value.contains(","))
					value = value.substring(0,value.length()-1);
				sue.add(Integer.parseInt(value));
			} else {
				sue.add(-1);
			}
			if(parts.contains("cars:")) {
				String value = parts.get(parts.indexOf("cars:") + 1);
				if(value.contains(","))
					value = value.substring(0,value.length()-1);
				sue.add(Integer.parseInt(value));
			} else {
				sue.add(-1);
			}
			if(parts.contains("perfumes:")) {
				String value = parts.get(parts.indexOf("perfumes:") + 1);
				if(value.contains(","))
					value = value.substring(0,value.length()-1);
				sue.add(Integer.parseInt(value));
			} else {
				sue.add(-1);
			}
			sues.add(sue);
		}
		sueLoop:
		for(int i = 0; i < 500; i++) {
			ArrayList<Integer> sue = sues.get(i);
			//check each value - if it's not equal to the comparison case AND it's not unknown, skip to next eval
			for(int j = 0; j < sue.size(); j++) {
				//cats or trees - greater than
				if(j == 1 || j == 7) {
					if(sue.get(j) <= comparison[j] && sue.get(j) != -1)
						continue sueLoop;
				//poms or goldfish - less than
				} else if (j == 3 || j == 6) {
					if(sue.get(j) >= comparison[j] && sue.get(j) != -1)
						continue sueLoop;
				} else {
					if(sue.get(j) != comparison[j] && sue.get(j) != -1)
						continue sueLoop;
				}
			} 
			//if all checks were passed without continuing loop, this is correct aunt sue.
			//adjust for 0-499 range instead of 1-500
			return Integer.toString(i+1);
		}
		return "Solution not found, input error";
	}

	public static void main(String[] args) {
		DayRunner.run(new Day16());
	}

}
