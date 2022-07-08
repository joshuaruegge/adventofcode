package advent.aoc2015;

import java.util.ArrayList;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;

public class Day02 implements IDay {

	static String input = "29x13x26\r\n"
			+ "11x11x14\r\n"
			+ "27x2x5\r\n"
			+ "6x10x13\r\n"
			+ "15x19x10\r\n"
			+ "26x29x15\r\n"
			+ "8x23x6\r\n"
			+ "17x8x26\r\n"
			+ "20x28x3\r\n"
			+ "23x12x24\r\n"
			+ "11x17x3\r\n"
			+ "19x23x28\r\n"
			+ "25x2x25\r\n"
			+ "1x15x3\r\n"
			+ "25x14x4\r\n"
			+ "23x10x23\r\n"
			+ "29x19x7\r\n"
			+ "17x10x13\r\n"
			+ "26x30x4\r\n"
			+ "16x7x16\r\n"
			+ "7x5x27\r\n"
			+ "8x23x6\r\n"
			+ "2x20x2\r\n"
			+ "18x4x24\r\n"
			+ "30x2x26\r\n"
			+ "6x14x23\r\n"
			+ "10x23x9\r\n"
			+ "29x29x22\r\n"
			+ "1x21x14\r\n"
			+ "22x10x13\r\n"
			+ "10x12x10\r\n"
			+ "20x13x11\r\n"
			+ "12x2x14\r\n"
			+ "2x16x29\r\n"
			+ "27x18x26\r\n"
			+ "6x12x20\r\n"
			+ "18x17x8\r\n"
			+ "14x25x1\r\n"
			+ "30x15x22\r\n"
			+ "17x18x7\r\n"
			+ "28x23x24\r\n"
			+ "15x12x25\r\n"
			+ "14x7x20\r\n"
			+ "29x23x8\r\n"
			+ "24x5x22\r\n"
			+ "6x22x8\r\n"
			+ "1x15x26\r\n"
			+ "14x5x1\r\n"
			+ "24x28x28\r\n"
			+ "17x23x23\r\n"
			+ "4x15x7\r\n"
			+ "23x8x11\r\n"
			+ "6x15x1\r\n"
			+ "23x18x13\r\n"
			+ "17x1x26\r\n"
			+ "23x13x17\r\n"
			+ "2x18x8\r\n"
			+ "22x22x1\r\n"
			+ "10x22x6\r\n"
			+ "28x29x20\r\n"
			+ "22x21x25\r\n"
			+ "14x8x23\r\n"
			+ "12x30x14\r\n"
			+ "8x7x5\r\n"
			+ "3x30x15\r\n"
			+ "4x3x29\r\n"
			+ "25x18x3\r\n"
			+ "16x7x16\r\n"
			+ "4x3x8\r\n"
			+ "9x16x30\r\n"
			+ "20x28x3\r\n"
			+ "28x24x6\r\n"
			+ "4x18x2\r\n"
			+ "23x18x5\r\n"
			+ "22x4x30\r\n"
			+ "15x30x9\r\n"
			+ "7x12x12\r\n"
			+ "3x22x29\r\n"
			+ "12x1x9\r\n"
			+ "9x2x25\r\n"
			+ "17x11x10\r\n"
			+ "25x24x7\r\n"
			+ "7x27x26\r\n"
			+ "26x4x12\r\n"
			+ "29x2x26\r\n"
			+ "19x24x12\r\n"
			+ "23x23x3\r\n"
			+ "26x28x16\r\n"
			+ "18x4x16\r\n"
			+ "25x30x18\r\n"
			+ "29x19x19\r\n"
			+ "16x3x27\r\n"
			+ "29x25x29\r\n"
			+ "18x19x5\r\n"
			+ "14x21x30\r\n"
			+ "19x13x26\r\n"
			+ "19x10x15\r\n"
			+ "9x4x7\r\n"
			+ "18x6x6\r\n"
			+ "24x25x29\r\n"
			+ "9x12x27\r\n"
			+ "15x3x22\r\n"
			+ "30x17x21\r\n"
			+ "18x19x28\r\n"
			+ "9x11x12\r\n"
			+ "8x28x22\r\n"
			+ "11x3x4\r\n"
			+ "28x17x20\r\n"
			+ "24x18x15\r\n"
			+ "11x12x13\r\n"
			+ "6x19x24\r\n"
			+ "28x4x5\r\n"
			+ "28x22x23\r\n"
			+ "13x29x2\r\n"
			+ "9x16x15\r\n"
			+ "29x28x1\r\n"
			+ "10x18x30\r\n"
			+ "19x11x12\r\n"
			+ "26x28x25\r\n"
			+ "23x17x13\r\n"
			+ "25x1x21\r\n"
			+ "17x1x27\r\n"
			+ "17x27x28\r\n"
			+ "28x13x15\r\n"
			+ "14x13x25\r\n"
			+ "11x29x7\r\n"
			+ "22x29x5\r\n"
			+ "13x6x14\r\n"
			+ "23x18x13\r\n"
			+ "25x7x17\r\n"
			+ "18x9x20\r\n"
			+ "21x11x2\r\n"
			+ "28x11x13\r\n"
			+ "13x25x1\r\n"
			+ "19x29x25\r\n"
			+ "16x29x4\r\n"
			+ "10x21x10\r\n"
			+ "7x25x17\r\n"
			+ "5x9x3\r\n"
			+ "1x15x6\r\n"
			+ "8x27x29\r\n"
			+ "23x6x30\r\n"
			+ "22x22x29\r\n"
			+ "6x20x30\r\n"
			+ "26x25x29\r\n"
			+ "10x19x19\r\n"
			+ "20x30x9\r\n"
			+ "5x30x24\r\n"
			+ "17x10x27\r\n"
			+ "30x14x30\r\n"
			+ "8x17x4\r\n"
			+ "7x18x6\r\n"
			+ "3x5x4\r\n"
			+ "24x17x15\r\n"
			+ "14x20x17\r\n"
			+ "22x27x15\r\n"
			+ "18x14x15\r\n"
			+ "23x9x11\r\n"
			+ "21x16x29\r\n"
			+ "7x18x21\r\n"
			+ "9x3x29\r\n"
			+ "10x13x4\r\n"
			+ "2x30x4\r\n"
			+ "23x20x4\r\n"
			+ "8x22x21\r\n"
			+ "29x28x4\r\n"
			+ "13x16x25\r\n"
			+ "21x9x11\r\n"
			+ "7x26x26\r\n"
			+ "13x23x30\r\n"
			+ "19x7x10\r\n"
			+ "9x23x21\r\n"
			+ "21x9x17\r\n"
			+ "9x21x15\r\n"
			+ "20x29x22\r\n"
			+ "23x13x15\r\n"
			+ "19x25x2\r\n"
			+ "12x11x30\r\n"
			+ "20x21x6\r\n"
			+ "21x6x17\r\n"
			+ "24x26x9\r\n"
			+ "29x21x29\r\n"
			+ "29x26x16\r\n"
			+ "6x16x1\r\n"
			+ "2x12x6\r\n"
			+ "6x7x20\r\n"
			+ "7x2x22\r\n"
			+ "6x22x4\r\n"
			+ "13x11x27\r\n"
			+ "25x27x14\r\n"
			+ "11x8x6\r\n"
			+ "26x11x14\r\n"
			+ "30x3x29\r\n"
			+ "27x21x20\r\n"
			+ "15x16x26\r\n"
			+ "6x22x10\r\n"
			+ "11x9x25\r\n"
			+ "23x13x6\r\n"
			+ "13x9x3\r\n"
			+ "30x22x13\r\n"
			+ "29x23x14\r\n"
			+ "25x19x6\r\n"
			+ "7x29x11\r\n"
			+ "19x18x5\r\n"
			+ "29x25x13\r\n"
			+ "25x24x27\r\n"
			+ "1x9x12\r\n"
			+ "22x9x17\r\n"
			+ "14x12x28\r\n"
			+ "19x21x17\r\n"
			+ "13x25x17\r\n"
			+ "14x25x12\r\n"
			+ "4x14x30\r\n"
			+ "7x15x28\r\n"
			+ "3x6x25\r\n"
			+ "6x2x16\r\n"
			+ "15x19x11\r\n"
			+ "17x30x20\r\n"
			+ "20x23x7\r\n"
			+ "26x21x6\r\n"
			+ "26x29x24\r\n"
			+ "2x4x30\r\n"
			+ "4x22x18\r\n"
			+ "13x3x28\r\n"
			+ "27x6x21\r\n"
			+ "5x3x27\r\n"
			+ "12x7x11\r\n"
			+ "28x11x9\r\n"
			+ "12x9x2\r\n"
			+ "1x22x20\r\n"
			+ "15x13x28\r\n"
			+ "14x19x16\r\n"
			+ "28x20x3\r\n"
			+ "20x4x9\r\n"
			+ "26x7x26\r\n"
			+ "18x19x25\r\n"
			+ "7x1x13\r\n"
			+ "20x23x29\r\n"
			+ "27x26x8\r\n"
			+ "11x15x15\r\n"
			+ "10x21x23\r\n"
			+ "29x2x11\r\n"
			+ "21x28x20\r\n"
			+ "3x18x23\r\n"
			+ "26x17x17\r\n"
			+ "14x26x17\r\n"
			+ "20x7x17\r\n"
			+ "18x12x8\r\n"
			+ "4x8x8\r\n"
			+ "8x15x23\r\n"
			+ "24x29x5\r\n"
			+ "1x25x8\r\n"
			+ "1x28x17\r\n"
			+ "16x18x13\r\n"
			+ "29x24x22\r\n"
			+ "13x16x10\r\n"
			+ "14x7x16\r\n"
			+ "15x11x29\r\n"
			+ "12x15x19\r\n"
			+ "17x6x28\r\n"
			+ "4x3x9\r\n"
			+ "15x16x8\r\n"
			+ "29x27x11\r\n"
			+ "2x24x20\r\n"
			+ "4x21x3\r\n"
			+ "29x24x27\r\n"
			+ "18x22x22\r\n"
			+ "7x8x18\r\n"
			+ "20x7x8\r\n"
			+ "19x9x2\r\n"
			+ "20x17x2\r\n"
			+ "2x29x10\r\n"
			+ "19x25x1\r\n"
			+ "28x9x3\r\n"
			+ "29x27x20\r\n"
			+ "7x21x7\r\n"
			+ "10x4x22\r\n"
			+ "26x8x5\r\n"
			+ "26x14x1\r\n"
			+ "5x27x9\r\n"
			+ "2x18x3\r\n"
			+ "3x27x17\r\n"
			+ "30x17x23\r\n"
			+ "30x11x20\r\n"
			+ "4x6x7\r\n"
			+ "6x29x27\r\n"
			+ "30x16x20\r\n"
			+ "24x30x28\r\n"
			+ "19x20x26\r\n"
			+ "18x1x25\r\n"
			+ "26x12x12\r\n"
			+ "19x15x29\r\n"
			+ "16x21x24\r\n"
			+ "23x13x26\r\n"
			+ "25x16x10\r\n"
			+ "8x9x18\r\n"
			+ "24x14x1\r\n"
			+ "24x15x21\r\n"
			+ "19x9x14\r\n"
			+ "8x23x11\r\n"
			+ "22x2x16\r\n"
			+ "29x9x26\r\n"
			+ "3x16x25\r\n"
			+ "15x20x30\r\n"
			+ "3x11x12\r\n"
			+ "15x2x3\r\n"
			+ "13x7x4\r\n"
			+ "2x7x27\r\n"
			+ "9x26x11\r\n"
			+ "30x24x19\r\n"
			+ "28x17x21\r\n"
			+ "10x8x2\r\n"
			+ "11x15x26\r\n"
			+ "10x12x20\r\n"
			+ "24x24x27\r\n"
			+ "25x26x16\r\n"
			+ "13x4x20\r\n"
			+ "25x13x11\r\n"
			+ "12x22x3\r\n"
			+ "20x7x1\r\n"
			+ "12x18x6\r\n"
			+ "26x8x20\r\n"
			+ "14x2x7\r\n"
			+ "23x12x1\r\n"
			+ "26x24x24\r\n"
			+ "27x26x23\r\n"
			+ "26x17x5\r\n"
			+ "17x24x2\r\n"
			+ "26x5x6\r\n"
			+ "23x5x1\r\n"
			+ "5x18x30\r\n"
			+ "24x21x19\r\n"
			+ "5x28x11\r\n"
			+ "21x20x14\r\n"
			+ "25x4x22\r\n"
			+ "26x24x11\r\n"
			+ "7x5x8\r\n"
			+ "13x1x30\r\n"
			+ "5x1x6\r\n"
			+ "14x5x2\r\n"
			+ "8x11x7\r\n"
			+ "13x20x1\r\n"
			+ "17x30x14\r\n"
			+ "29x22x10\r\n"
			+ "12x26x3\r\n"
			+ "27x17x3\r\n"
			+ "26x27x4\r\n"
			+ "5x26x17\r\n"
			+ "22x11x19\r\n"
			+ "8x26x3\r\n"
			+ "24x19x22\r\n"
			+ "7x1x4\r\n"
			+ "6x27x30\r\n"
			+ "4x28x14\r\n"
			+ "16x14x18\r\n"
			+ "4x5x20\r\n"
			+ "19x25x4\r\n"
			+ "15x15x1\r\n"
			+ "10x14x14\r\n"
			+ "16x18x24\r\n"
			+ "21x27x15\r\n"
			+ "5x5x10\r\n"
			+ "1x7x13\r\n"
			+ "16x2x8\r\n"
			+ "13x15x11\r\n"
			+ "3x25x10\r\n"
			+ "20x29x8\r\n"
			+ "12x3x2\r\n"
			+ "10x13x12\r\n"
			+ "25x27x1\r\n"
			+ "11x30x19\r\n"
			+ "7x19x13\r\n"
			+ "27x6x18\r\n"
			+ "16x21x19\r\n"
			+ "21x29x5\r\n"
			+ "16x23x12\r\n"
			+ "29x19x15\r\n"
			+ "5x5x10\r\n"
			+ "27x15x1\r\n"
			+ "13x16x22\r\n"
			+ "29x19x5\r\n"
			+ "8x12x9\r\n"
			+ "3x18x5\r\n"
			+ "13x25x3\r\n"
			+ "5x9x21\r\n"
			+ "10x20x16\r\n"
			+ "9x9x11\r\n"
			+ "23x21x1\r\n"
			+ "22x2x15\r\n"
			+ "27x8x13\r\n"
			+ "23x7x3\r\n"
			+ "26x30x15\r\n"
			+ "29x15x16\r\n"
			+ "16x27x13\r\n"
			+ "2x18x9\r\n"
			+ "10x27x8\r\n"
			+ "20x9x25\r\n"
			+ "10x2x17\r\n"
			+ "16x13x13\r\n"
			+ "21x26x1\r\n"
			+ "27x26x24\r\n"
			+ "9x30x16\r\n"
			+ "19x17x28\r\n"
			+ "25x15x1\r\n"
			+ "10x26x6\r\n"
			+ "10x11x11\r\n"
			+ "5x26x25\r\n"
			+ "30x4x15\r\n"
			+ "9x8x23\r\n"
			+ "14x25x7\r\n"
			+ "8x28x8\r\n"
			+ "28x18x24\r\n"
			+ "4x4x25\r\n"
			+ "16x25x11\r\n"
			+ "17x27x8\r\n"
			+ "15x16x9\r\n"
			+ "24x13x21\r\n"
			+ "17x3x27\r\n"
			+ "27x5x26\r\n"
			+ "8x27x12\r\n"
			+ "29x2x8\r\n"
			+ "24x23x30\r\n"
			+ "1x30x21\r\n"
			+ "6x18x20\r\n"
			+ "13x14x12\r\n"
			+ "25x30x23\r\n"
			+ "24x6x24\r\n"
			+ "12x7x21\r\n"
			+ "11x6x8\r\n"
			+ "8x30x30\r\n"
			+ "26x3x12\r\n"
			+ "28x6x5\r\n"
			+ "18x7x1\r\n"
			+ "7x6x20\r\n"
			+ "14x16x18\r\n"
			+ "11x22x15\r\n"
			+ "4x20x10\r\n"
			+ "19x24x19\r\n"
			+ "8x24x11\r\n"
			+ "4x9x10\r\n"
			+ "6x6x22\r\n"
			+ "10x9x29\r\n"
			+ "1x5x28\r\n"
			+ "19x25x29\r\n"
			+ "20x30x3\r\n"
			+ "15x13x13\r\n"
			+ "9x9x24\r\n"
			+ "20x14x29\r\n"
			+ "26x24x13\r\n"
			+ "2x25x8\r\n"
			+ "10x26x2\r\n"
			+ "12x19x12\r\n"
			+ "18x6x20\r\n"
			+ "4x5x14\r\n"
			+ "26x27x10\r\n"
			+ "16x26x20\r\n"
			+ "3x21x15\r\n"
			+ "2x26x18\r\n"
			+ "14x11x17\r\n"
			+ "26x26x25\r\n"
			+ "10x1x11\r\n"
			+ "17x19x19\r\n"
			+ "27x28x26\r\n"
			+ "9x2x10\r\n"
			+ "19x30x15\r\n"
			+ "23x30x14\r\n"
			+ "15x3x20\r\n"
			+ "2x14x22\r\n"
			+ "21x18x8\r\n"
			+ "22x4x29\r\n"
			+ "19x6x29\r\n"
			+ "9x26x29\r\n"
			+ "16x10x9\r\n"
			+ "22x12x22\r\n"
			+ "13x28x14\r\n"
			+ "25x14x28\r\n"
			+ "28x3x30\r\n"
			+ "10x17x1\r\n"
			+ "10x27x22\r\n"
			+ "10x23x19\r\n"
			+ "14x25x9\r\n"
			+ "11x24x8\r\n"
			+ "30x25x10\r\n"
			+ "22x13x28\r\n"
			+ "2x7x6\r\n"
			+ "11x20x8\r\n"
			+ "9x22x14\r\n"
			+ "19x16x9\r\n"
			+ "11x24x4\r\n"
			+ "11x17x2\r\n"
			+ "6x4x10\r\n"
			+ "26x10x10\r\n"
			+ "12x14x5\r\n"
			+ "27x10x3\r\n"
			+ "15x3x6\r\n"
			+ "11x7x19\r\n"
			+ "22x10x12\r\n"
			+ "21x26x10\r\n"
			+ "13x20x3\r\n"
			+ "27x8x8\r\n"
			+ "1x24x23\r\n"
			+ "24x9x22\r\n"
			+ "23x17x23\r\n"
			+ "3x28x19\r\n"
			+ "2x20x28\r\n"
			+ "23x17x24\r\n"
			+ "26x1x4\r\n"
			+ "4x1x12\r\n"
			+ "5x6x16\r\n"
			+ "13x22x13\r\n"
			+ "25x21x21\r\n"
			+ "20x21x12\r\n"
			+ "9x24x25\r\n"
			+ "17x16x12\r\n"
			+ "12x28x9\r\n"
			+ "18x16x27\r\n"
			+ "29x12x2\r\n"
			+ "30x12x15\r\n"
			+ "24x11x10\r\n"
			+ "4x9x22\r\n"
			+ "4x24x5\r\n"
			+ "19x11x5\r\n"
			+ "6x25x6\r\n"
			+ "1x20x17\r\n"
			+ "22x8x21\r\n"
			+ "11x26x4\r\n"
			+ "16x19x3\r\n"
			+ "8x12x8\r\n"
			+ "13x2x18\r\n"
			+ "10x5x11\r\n"
			+ "8x12x17\r\n"
			+ "21x2x5\r\n"
			+ "26x17x26\r\n"
			+ "23x18x17\r\n"
			+ "28x11x14\r\n"
			+ "1x4x27\r\n"
			+ "29x5x28\r\n"
			+ "5x9x10\r\n"
			+ "5x7x25\r\n"
			+ "20x15x27\r\n"
			+ "15x11x17\r\n"
			+ "12x14x1\r\n"
			+ "29x14x4\r\n"
			+ "18x14x18\r\n"
			+ "14x25x24\r\n"
			+ "26x14x18\r\n"
			+ "13x8x11\r\n"
			+ "30x1x23\r\n"
			+ "3x4x12\r\n"
			+ "12x24x9\r\n"
			+ "8x6x16\r\n"
			+ "14x15x30\r\n"
			+ "12x30x8\r\n"
			+ "22x11x18\r\n"
			+ "16x30x28\r\n"
			+ "17x18x4\r\n"
			+ "13x14x23\r\n"
			+ "2x28x8\r\n"
			+ "3x28x30\r\n"
			+ "29x30x8\r\n"
			+ "4x6x26\r\n"
			+ "6x30x17\r\n"
			+ "11x30x30\r\n"
			+ "19x4x3\r\n"
			+ "12x15x20\r\n"
			+ "22x28x4\r\n"
			+ "26x30x2\r\n"
			+ "6x12x7\r\n"
			+ "1x10x5\r\n"
			+ "25x29x7\r\n"
			+ "17x9x18\r\n"
			+ "16x21x29\r\n"
			+ "21x14x7\r\n"
			+ "15x16x11\r\n"
			+ "26x6x15\r\n"
			+ "8x24x7\r\n"
			+ "2x20x4\r\n"
			+ "2x9x3\r\n"
			+ "19x8x13\r\n"
			+ "18x7x22\r\n"
			+ "27x14x17\r\n"
			+ "2x13x8\r\n"
			+ "18x15x26\r\n"
			+ "15x27x27\r\n"
			+ "18x11x15\r\n"
			+ "1x29x20\r\n"
			+ "21x12x11\r\n"
			+ "20x2x15\r\n"
			+ "28x23x9\r\n"
			+ "1x1x17\r\n"
			+ "7x23x9\r\n"
			+ "30x9x27\r\n"
			+ "9x16x18\r\n"
			+ "15x24x28\r\n"
			+ "30x11x18\r\n"
			+ "29x26x10\r\n"
			+ "9x5x25\r\n"
			+ "2x1x19\r\n"
			+ "14x3x14\r\n"
			+ "6x3x6\r\n"
			+ "30x15x20\r\n"
			+ "20x17x27\r\n"
			+ "28x10x9\r\n"
			+ "14x24x28\r\n"
			+ "17x11x6\r\n"
			+ "12x3x6\r\n"
			+ "8x8x15\r\n"
			+ "23x14x21\r\n"
			+ "11x21x7\r\n"
			+ "5x13x30\r\n"
			+ "4x29x25\r\n"
			+ "30x28x24\r\n"
			+ "18x4x9\r\n"
			+ "3x15x6\r\n"
			+ "13x9x19\r\n"
			+ "30x14x7\r\n"
			+ "7x9x9\r\n"
			+ "17x11x26\r\n"
			+ "24x26x13\r\n"
			+ "16x21x16\r\n"
			+ "27x17x25\r\n"
			+ "2x21x11\r\n"
			+ "9x11x27\r\n"
			+ "3x3x7\r\n"
			+ "13x8x14\r\n"
			+ "20x20x26\r\n"
			+ "13x29x22\r\n"
			+ "30x11x1\r\n"
			+ "7x10x19\r\n"
			+ "27x5x9\r\n"
			+ "23x17x15\r\n"
			+ "21x6x13\r\n"
			+ "24x15x16\r\n"
			+ "18x4x14\r\n"
			+ "18x16x6\r\n"
			+ "22x11x18\r\n"
			+ "14x2x5\r\n"
			+ "15x3x7\r\n"
			+ "10x20x29\r\n"
			+ "16x1x10\r\n"
			+ "30x23x1\r\n"
			+ "10x15x11\r\n"
			+ "17x14x5\r\n"
			+ "22x8x13\r\n"
			+ "7x11x28\r\n"
			+ "26x17x3\r\n"
			+ "2x23x2\r\n"
			+ "28x13x19\r\n"
			+ "18x12x28\r\n"
			+ "22x23x16\r\n"
			+ "14x12x1\r\n"
			+ "20x8x19\r\n"
			+ "17x19x13\r\n"
			+ "29x2x12\r\n"
			+ "2x26x27\r\n"
			+ "29x16x4\r\n"
			+ "13x8x18\r\n"
			+ "16x15x30\r\n"
			+ "23x16x2\r\n"
			+ "28x8x27\r\n"
			+ "21x8x23\r\n"
			+ "13x20x26\r\n"
			+ "19x6x17\r\n"
			+ "17x30x15\r\n"
			+ "7x4x30\r\n"
			+ "2x13x30\r\n"
			+ "18x7x19\r\n"
			+ "4x13x27\r\n"
			+ "8x6x5\r\n"
			+ "18x20x25\r\n"
			+ "2x3x30\r\n"
			+ "23x27x13\r\n"
			+ "22x30x4\r\n"
			+ "23x25x25\r\n"
			+ "23x16x19\r\n"
			+ "25x3x1\r\n"
			+ "5x6x15\r\n"
			+ "11x29x12\r\n"
			+ "25x24x7\r\n"
			+ "16x7x20\r\n"
			+ "20x3x2\r\n"
			+ "12x27x15\r\n"
			+ "16x10x12\r\n"
			+ "1x3x14\r\n"
			+ "22x1x26\r\n"
			+ "2x24x18\r\n"
			+ "11x29x16\r\n"
			+ "15x2x9\r\n"
			+ "10x1x24\r\n"
			+ "21x8x11\r\n"
			+ "30x11x23\r\n"
			+ "6x30x21\r\n"
			+ "13x27x29\r\n"
			+ "14x6x5\r\n"
			+ "18x29x19\r\n"
			+ "12x4x28\r\n"
			+ "29x3x14\r\n"
			+ "10x30x28\r\n"
			+ "5x7x15\r\n"
			+ "14x1x10\r\n"
			+ "9x25x14\r\n"
			+ "7x24x18\r\n"
			+ "28x17x21\r\n"
			+ "18x13x25\r\n"
			+ "26x15x1\r\n"
			+ "21x1x19\r\n"
			+ "12x16x21\r\n"
			+ "4x6x13\r\n"
			+ "7x15x26\r\n"
			+ "17x19x5\r\n"
			+ "12x28x2\r\n"
			+ "1x20x19\r\n"
			+ "27x7x5\r\n"
			+ "17x26x8\r\n"
			+ "12x15x19\r\n"
			+ "5x23x10\r\n"
			+ "8x2x8\r\n"
			+ "16x13x12\r\n"
			+ "14x27x1\r\n"
			+ "26x29x3\r\n"
			+ "24x16x14\r\n"
			+ "14x13x13\r\n"
			+ "7x22x23\r\n"
			+ "2x9x30\r\n"
			+ "4x27x8\r\n"
			+ "26x27x15\r\n"
			+ "23x1x6\r\n"
			+ "25x29x18\r\n"
			+ "5x18x1\r\n"
			+ "20x8x20\r\n"
			+ "5x10x25\r\n"
			+ "30x25x15\r\n"
			+ "7x22x25\r\n"
			+ "28x26x17\r\n"
			+ "29x4x1\r\n"
			+ "21x11x27\r\n"
			+ "20x9x8\r\n"
			+ "25x22x12\r\n"
			+ "2x11x11\r\n"
			+ "23x2x16\r\n"
			+ "23x27x20\r\n"
			+ "2x13x28\r\n"
			+ "27x2x24\r\n"
			+ "11x1x17\r\n"
			+ "12x4x27\r\n"
			+ "16x20x22\r\n"
			+ "30x12x10\r\n"
			+ "5x15x4\r\n"
			+ "5x2x27\r\n"
			+ "12x4x25\r\n"
			+ "1x16x4\r\n"
			+ "27x4x4\r\n"
			+ "21x16x3\r\n"
			+ "27x26x3\r\n"
			+ "24x6x6\r\n"
			+ "24x12x12\r\n"
			+ "20x20x25\r\n"
			+ "8x29x2\r\n"
			+ "21x4x5\r\n"
			+ "2x4x8\r\n"
			+ "4x13x19\r\n"
			+ "3x20x10\r\n"
			+ "12x15x16\r\n"
			+ "6x5x4\r\n"
			+ "12x16x20\r\n"
			+ "22x19x17\r\n"
			+ "8x17x22\r\n"
			+ "25x16x15\r\n"
			+ "7x1x19\r\n"
			+ "10x1x7\r\n"
			+ "23x23x5\r\n"
			+ "28x6x12\r\n"
			+ "2x25x12\r\n"
			+ "10x27x12\r\n"
			+ "24x27x19\r\n"
			+ "14x14x20\r\n"
			+ "4x1x5\r\n"
			+ "16x27x29\r\n"
			+ "20x20x24\r\n"
			+ "28x24x30\r\n"
			+ "6x15x15\r\n"
			+ "9x15x30\r\n"
			+ "23x26x3\r\n"
			+ "17x24x21\r\n"
			+ "22x25x25\r\n"
			+ "18x29x10\r\n"
			+ "20x25x1\r\n"
			+ "24x11x16\r\n"
			+ "20x7x21\r\n"
			+ "20x7x9\r\n"
			+ "7x26x2\r\n"
			+ "5x18x1\r\n"
			+ "16x26x28\r\n"
			+ "4x10x18\r\n"
			+ "27x30x21\r\n"
			+ "26x9x9\r\n"
			+ "8x16x14\r\n"
			+ "6x27x8\r\n"
			+ "28x9x20\r\n"
			+ "13x13x4\r\n"
			+ "9x18x16\r\n"
			+ "18x15x18\r\n"
			+ "22x19x14\r\n"
			+ "14x10x17\r\n"
			+ "25x29x11\r\n"
			+ "1x18x19\r\n"
			+ "8x11x26\r\n"
			+ "18x6x14\r\n"
			+ "30x24x13\r\n"
			+ "27x1x27\r\n"
			+ "15x9x3\r\n"
			+ "2x29x17\r\n"
			+ "2x26x21\r\n"
			+ "22x9x9\r\n"
			+ "20x20x20\r\n"
			+ "22x28x2\r\n"
			+ "26x5x16\r\n"
			+ "11x3x14\r\n"
			+ "21x16x16\r\n"
			+ "18x26x7\r\n"
			+ "18x30x6\r\n"
			+ "7x11x12\r\n"
			+ "15x10x2\r\n"
			+ "27x2x16\r\n"
			+ "27x30x24\r\n"
			+ "28x14x24\r\n"
			+ "7x4x8\r\n"
			+ "6x28x15\r\n"
			+ "13x19x1\r\n"
			+ "22x26x30\r\n"
			+ "7x30x24\r\n"
			+ "2x17x21\r\n"
			+ "19x26x2\r\n"
			+ "19x24x15\r\n"
			+ "14x23x2\r\n"
			+ "21x27x15\r\n"
			+ "30x15x14\r\n"
			+ "21x29x5\r\n"
			+ "23x30x2\r\n"
			+ "4x1x2\r\n"
			+ "15x5x13\r\n"
			+ "21x2x30\r\n"
			+ "20x7x16\r\n"
			+ "1x21x25\r\n"
			+ "2x25x1\r\n"
			+ "12x29x5\r\n"
			+ "28x13x16\r\n"
			+ "26x3x12\r\n"
			+ "29x20x23\r\n"
			+ "28x12x20\r\n"
			+ "4x30x8\r\n"
			+ "16x15x16\r\n"
			+ "6x16x29\r\n"
			+ "2x28x13\r\n"
			+ "24x25x2\r\n"
			+ "26x15x22\r\n"
			+ "17x20x11\r\n"
			+ "18x12x7\r\n"
			+ "19x1x18\r\n"
			+ "8x27x13\r\n"
			+ "22x16x8\r\n"
			+ "19x26x17\r\n"
			+ "13x11x10\r\n"
			+ "22x12x3\r\n"
			+ "13x12x14\r\n"
			+ "29x17x9\r\n"
			+ "6x14x10\r\n"
			+ "14x20x10\r\n"
			+ "8x26x9\r\n"
			+ "25x13x22\r\n"
			+ "3x30x25\r\n"
			+ "14x28x1\r\n"
			+ "30x29x12\r\n"
			+ "3x17x15\r\n"
			+ "3x24x14\r\n"
			+ "28x24x22\r\n"
			+ "16x6x1\r\n"
			+ "20x25x14\r\n"
			+ "17x17x13\r\n"
			+ "6x19x27\r\n"
			+ "10x15x20\r\n"
			+ "8x23x20\r\n"
			+ "7x29x21\r\n"
			+ "18x9x25\r\n"
			+ "10x5x22\r\n"
			+ "2x27x27\r\n"
			+ "16x18x30\r\n"
			+ "15x5x12\r\n"
			+ "26x29x29\r\n"
			+ "28x11x10\r\n"
			+ "9x29x28\r\n"
			+ "24x15x23\r\n"
			+ "26x9x10\r\n"
			+ "5x1x25\r\n"
			+ "22x27x16\r\n"
			+ "7x29x3\r\n"
			+ "1x3x5\r\n"
			+ "8x7x29\r\n"
			+ "19x21x11\r\n"
			+ "28x13x30\r\n"
			+ "17x16x20\r\n"
			+ "5x10x25\r\n"
			+ "9x14x15\r\n"
			+ "15x14x23\r\n"
			+ "16x4x17\r\n"
			+ "21x8x2\r\n"
			+ "9x9x8\r\n"
			+ "22x22x4\r\n"
			+ "10x2x27\r\n"
			+ "12x19x10\r\n"
			+ "15x29x4\r\n"
			+ "22x14x7\r\n"
			+ "29x18x5\r\n"
			+ "1x7x27\r\n"
			+ "24x1x15\r\n"
			+ "23x23x26\r\n"
			+ "12x17x23\r\n"
			+ "26x10x24\r\n"
			+ "8x22x2\r\n"
			+ "8x1x10\r\n"
			+ "22x19x12\r\n"
			+ "2x23x13\r\n"
			+ "11x27x25\r\n"
			+ "26x15x27\r\n"
			+ "27x7x21\r\n"
			+ "18x9x6\r\n"
			+ "22x21x22\r\n"
			+ "7x12x26\r\n"
			+ "23x21x13\r\n"
			+ "14x3x8\r\n"
			+ "5x9x28\r\n"
			+ "29x29x15\r\n"
			+ "27x25x23\r\n"
			+ "12x2x24\r\n"
			+ "8x2x20\r\n"
			+ "29x19x4\r\n"
			+ "12x24x29\r\n"
			+ "2x27x28\r\n"
			+ "14x20x9\r\n"
			+ "28x6x25\r\n"
			+ "18x29x8\r\n"
			+ "19x11x30\r\n"
			+ "15x11x23\r\n"
			+ "18x7x7\r\n"
			+ "14x20x14\r\n"
			+ "26x18x22\r\n"
			+ "27x25x13\r\n"
			+ "12x10x30\r\n"
			+ "30x2x7\r\n"
			+ "28x10x1\r\n"
			+ "18x10x30\r\n"
			+ "22x11x5\r\n"
			+ "22x16x3\r\n"
			+ "25x15x9\r\n"
			+ "5x10x24\r\n"
			+ "4x28x8\r\n"
			+ "19x24x18\r\n"
			+ "3x4x25\r\n"
			+ "14x4x30\r\n"
			+ "11x26x3\r\n"
			+ "12x12x12\r\n"
			+ "26x7x24\r\n"
			+ "3x2x14\r\n"
			+ "1x27x7\r\n"
			+ "2x2x13\r\n"
			+ "3x26x26\r\n"
			+ "12x4x11\r\n"
			+ "12x17x20\r\n"
			+ "4x19x30\r\n"
			+ "5x18x10\r\n"
			+ "17x6x18\r\n"
			+ "19x30x20\r\n"
			+ "11x2x17\r\n"
			+ "30x13x19\r\n"
			+ "22x23x7\r\n"
			+ "17x28x2\r\n"
			+ "5x17x30\r\n"
			+ "7x11x4\r\n"
			+ "21x26x18\r\n"
			+ "15x28x4\r\n"
			+ "5x6x27\r\n"
			+ "12x6x16\r\n"
			+ "9x17x12\r\n"
			+ "27x20x5\r\n"
			+ "14x5x20\r\n"
			+ "27x14x6\r\n"
			+ "2x14x21\r\n"
			+ "4x28x30\r\n"
			+ "24x5x1\r\n"
			+ "19x29x29\r\n"
			+ "11x23x1\r\n"
			+ "8x16x21\r\n"
			+ "3x17x19\r\n"
			+ "10x13x5\r\n"
			+ "20x21x16\r\n"
			+ "23x3x6\r\n"
			+ "27x26x11\r\n"
			+ "3x2x22\r\n"
			+ "14x3x5\r\n"
			+ "10x9x8";
	
	@Override
	public String part1() {
		//parse input into list of arraylists - each arraylist represents l,w,h of one present
		ArrayList<ArrayList<Integer>> presents = new ArrayList<ArrayList<Integer>>();
		for(String s : input.split("\r\n")) {
			//split into individual dimensions adn parse
			String[] parts = s.split("x");
			ArrayList<Integer> present = new ArrayList<Integer>();
			for(int i = 0; i < 3; i++)
				present.add(Integer.parseInt(parts[i]));
			presents.add(present);
		}
		//wrapping total
		int total = 0;
		for(ArrayList<Integer> present : presents) {
			//calculate side lengths
			int l = present.get(0);
			int w = present.get(1);
			int h = present.get(2);
			//calculate areas
			int side1 = l*w;
			int side2 = w*h;
			int side3 = l*h;
			//total surface area, plus slack
			total += 2*side1 + 2*side2 + 2*side3 + Math.min(side1, Math.min(side2,side3));
		}
		return Integer.toString(total);
	}

	@Override
	public String part2() {
		//parse input into list of arraylists - each arraylist represents l,w,h of one present
		ArrayList<ArrayList<Integer>> presents = new ArrayList<ArrayList<Integer>>();
		for(String s : input.split("\r\n")) {
			String[] parts = s.split("x");
			ArrayList<Integer> present = new ArrayList<Integer>();
			for(int i = 0; i < 3; i++)
				present.add(Integer.parseInt(parts[i]));
			presents.add(present);
		}
		//ribbon total
		int total = 0;
		for(ArrayList<Integer> present : presents) {
			//side lengths
			int l = present.get(0);
			int w = present.get(1);
			int h = present.get(2);
			//perimeters
			int p1 = 2 * (l+w);
			int p2 = 2 * (w+h);
			int p3 = 2 * (l+h);
			//smallest perimeter, and cubic for bow
			total += Math.min(p1, Math.min(p2, p3)) + l*w*h;
		}
		return Integer.toString(total);
	}

	public static void main(String[] args) {
		DayRunner.run(new Day02());
	}

}
