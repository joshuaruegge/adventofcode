package advent.aoc2015;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;

public class Day06 implements IDay {

	String input = "toggle 461,550 through 564,900\r\n"
			+ "turn off 370,39 through 425,839\r\n"
			+ "turn off 464,858 through 833,915\r\n"
			+ "turn off 812,389 through 865,874\r\n"
			+ "turn on 599,989 through 806,993\r\n"
			+ "turn on 376,415 through 768,548\r\n"
			+ "turn on 606,361 through 892,600\r\n"
			+ "turn off 448,208 through 645,684\r\n"
			+ "toggle 50,472 through 452,788\r\n"
			+ "toggle 205,417 through 703,826\r\n"
			+ "toggle 533,331 through 906,873\r\n"
			+ "toggle 857,493 through 989,970\r\n"
			+ "turn off 631,950 through 894,975\r\n"
			+ "turn off 387,19 through 720,700\r\n"
			+ "turn off 511,843 through 581,945\r\n"
			+ "toggle 514,557 through 662,883\r\n"
			+ "turn off 269,809 through 876,847\r\n"
			+ "turn off 149,517 through 716,777\r\n"
			+ "turn off 994,939 through 998,988\r\n"
			+ "toggle 467,662 through 555,957\r\n"
			+ "turn on 952,417 through 954,845\r\n"
			+ "turn on 565,226 through 944,880\r\n"
			+ "turn on 214,319 through 805,722\r\n"
			+ "toggle 532,276 through 636,847\r\n"
			+ "toggle 619,80 through 689,507\r\n"
			+ "turn on 390,706 through 884,722\r\n"
			+ "toggle 17,634 through 537,766\r\n"
			+ "toggle 706,440 through 834,441\r\n"
			+ "toggle 318,207 through 499,530\r\n"
			+ "toggle 698,185 through 830,343\r\n"
			+ "toggle 566,679 through 744,716\r\n"
			+ "toggle 347,482 through 959,482\r\n"
			+ "toggle 39,799 through 981,872\r\n"
			+ "turn on 583,543 through 846,710\r\n"
			+ "turn off 367,664 through 595,872\r\n"
			+ "turn on 805,439 through 964,995\r\n"
			+ "toggle 209,584 through 513,802\r\n"
			+ "turn off 106,497 through 266,770\r\n"
			+ "turn on 975,2 through 984,623\r\n"
			+ "turn off 316,684 through 369,876\r\n"
			+ "turn off 30,309 through 259,554\r\n"
			+ "turn off 399,680 through 861,942\r\n"
			+ "toggle 227,740 through 850,829\r\n"
			+ "turn on 386,603 through 552,879\r\n"
			+ "turn off 703,795 through 791,963\r\n"
			+ "turn off 573,803 through 996,878\r\n"
			+ "turn off 993,939 through 997,951\r\n"
			+ "turn on 809,221 through 869,723\r\n"
			+ "turn off 38,720 through 682,751\r\n"
			+ "turn off 318,732 through 720,976\r\n"
			+ "toggle 88,459 through 392,654\r\n"
			+ "turn off 865,654 through 911,956\r\n"
			+ "toggle 264,284 through 857,956\r\n"
			+ "turn off 281,776 through 610,797\r\n"
			+ "toggle 492,660 through 647,910\r\n"
			+ "turn off 879,703 through 925,981\r\n"
			+ "turn off 772,414 through 974,518\r\n"
			+ "turn on 694,41 through 755,96\r\n"
			+ "turn on 452,406 through 885,881\r\n"
			+ "turn off 107,905 through 497,910\r\n"
			+ "turn off 647,222 through 910,532\r\n"
			+ "turn on 679,40 through 845,358\r\n"
			+ "turn off 144,205 through 556,362\r\n"
			+ "turn on 871,804 through 962,878\r\n"
			+ "turn on 545,676 through 545,929\r\n"
			+ "turn off 316,716 through 413,941\r\n"
			+ "toggle 488,826 through 755,971\r\n"
			+ "toggle 957,832 through 976,992\r\n"
			+ "toggle 857,770 through 905,964\r\n"
			+ "toggle 319,198 through 787,673\r\n"
			+ "turn on 832,813 through 863,844\r\n"
			+ "turn on 818,296 through 818,681\r\n"
			+ "turn on 71,699 through 91,960\r\n"
			+ "turn off 838,578 through 967,928\r\n"
			+ "toggle 440,856 through 507,942\r\n"
			+ "toggle 121,970 through 151,974\r\n"
			+ "toggle 391,192 through 659,751\r\n"
			+ "turn on 78,210 through 681,419\r\n"
			+ "turn on 324,591 through 593,939\r\n"
			+ "toggle 159,366 through 249,760\r\n"
			+ "turn off 617,167 through 954,601\r\n"
			+ "toggle 484,607 through 733,657\r\n"
			+ "turn on 587,96 through 888,819\r\n"
			+ "turn off 680,984 through 941,991\r\n"
			+ "turn on 800,512 through 968,691\r\n"
			+ "turn off 123,588 through 853,603\r\n"
			+ "turn on 1,862 through 507,912\r\n"
			+ "turn on 699,839 through 973,878\r\n"
			+ "turn off 848,89 through 887,893\r\n"
			+ "toggle 344,353 through 462,403\r\n"
			+ "turn on 780,731 through 841,760\r\n"
			+ "toggle 693,973 through 847,984\r\n"
			+ "toggle 989,936 through 996,958\r\n"
			+ "toggle 168,475 through 206,963\r\n"
			+ "turn on 742,683 through 769,845\r\n"
			+ "toggle 768,116 through 987,396\r\n"
			+ "turn on 190,364 through 617,526\r\n"
			+ "turn off 470,266 through 530,839\r\n"
			+ "toggle 122,497 through 969,645\r\n"
			+ "turn off 492,432 through 827,790\r\n"
			+ "turn on 505,636 through 957,820\r\n"
			+ "turn on 295,476 through 698,958\r\n"
			+ "toggle 63,298 through 202,396\r\n"
			+ "turn on 157,315 through 412,939\r\n"
			+ "turn off 69,789 through 134,837\r\n"
			+ "turn off 678,335 through 896,541\r\n"
			+ "toggle 140,516 through 842,668\r\n"
			+ "turn off 697,585 through 712,668\r\n"
			+ "toggle 507,832 through 578,949\r\n"
			+ "turn on 678,279 through 886,621\r\n"
			+ "toggle 449,744 through 826,910\r\n"
			+ "turn off 835,354 through 921,741\r\n"
			+ "toggle 924,878 through 985,952\r\n"
			+ "turn on 666,503 through 922,905\r\n"
			+ "turn on 947,453 through 961,587\r\n"
			+ "toggle 525,190 through 795,654\r\n"
			+ "turn off 62,320 through 896,362\r\n"
			+ "turn on 21,458 through 972,536\r\n"
			+ "turn on 446,429 through 821,970\r\n"
			+ "toggle 376,423 through 805,455\r\n"
			+ "toggle 494,896 through 715,937\r\n"
			+ "turn on 583,270 through 667,482\r\n"
			+ "turn off 183,468 through 280,548\r\n"
			+ "toggle 623,289 through 750,524\r\n"
			+ "turn on 836,706 through 967,768\r\n"
			+ "turn on 419,569 through 912,908\r\n"
			+ "turn on 428,260 through 660,433\r\n"
			+ "turn off 683,627 through 916,816\r\n"
			+ "turn on 447,973 through 866,980\r\n"
			+ "turn on 688,607 through 938,990\r\n"
			+ "turn on 245,187 through 597,405\r\n"
			+ "turn off 558,843 through 841,942\r\n"
			+ "turn off 325,666 through 713,834\r\n"
			+ "toggle 672,606 through 814,935\r\n"
			+ "turn off 161,812 through 490,954\r\n"
			+ "turn on 950,362 through 985,898\r\n"
			+ "turn on 143,22 through 205,821\r\n"
			+ "turn on 89,762 through 607,790\r\n"
			+ "toggle 234,245 through 827,303\r\n"
			+ "turn on 65,599 through 764,997\r\n"
			+ "turn on 232,466 through 965,695\r\n"
			+ "turn on 739,122 through 975,590\r\n"
			+ "turn off 206,112 through 940,558\r\n"
			+ "toggle 690,365 through 988,552\r\n"
			+ "turn on 907,438 through 977,691\r\n"
			+ "turn off 838,809 through 944,869\r\n"
			+ "turn on 222,12 through 541,832\r\n"
			+ "toggle 337,66 through 669,812\r\n"
			+ "turn on 732,821 through 897,912\r\n"
			+ "toggle 182,862 through 638,996\r\n"
			+ "turn on 955,808 through 983,847\r\n"
			+ "toggle 346,227 through 841,696\r\n"
			+ "turn on 983,270 through 989,756\r\n"
			+ "turn off 874,849 through 876,905\r\n"
			+ "turn off 7,760 through 678,795\r\n"
			+ "toggle 973,977 through 995,983\r\n"
			+ "turn off 911,961 through 914,976\r\n"
			+ "turn on 913,557 through 952,722\r\n"
			+ "turn off 607,933 through 939,999\r\n"
			+ "turn on 226,604 through 517,622\r\n"
			+ "turn off 3,564 through 344,842\r\n"
			+ "toggle 340,578 through 428,610\r\n"
			+ "turn on 248,916 through 687,925\r\n"
			+ "toggle 650,185 through 955,965\r\n"
			+ "toggle 831,359 through 933,536\r\n"
			+ "turn off 544,614 through 896,953\r\n"
			+ "toggle 648,939 through 975,997\r\n"
			+ "turn on 464,269 through 710,521\r\n"
			+ "turn off 643,149 through 791,320\r\n"
			+ "turn off 875,549 through 972,643\r\n"
			+ "turn off 953,969 through 971,972\r\n"
			+ "turn off 236,474 through 772,591\r\n"
			+ "toggle 313,212 through 489,723\r\n"
			+ "toggle 896,829 through 897,837\r\n"
			+ "toggle 544,449 through 995,905\r\n"
			+ "turn off 278,645 through 977,876\r\n"
			+ "turn off 887,947 through 946,977\r\n"
			+ "turn on 342,861 through 725,935\r\n"
			+ "turn on 636,316 through 692,513\r\n"
			+ "toggle 857,470 through 950,528\r\n"
			+ "turn off 736,196 through 826,889\r\n"
			+ "turn on 17,878 through 850,987\r\n"
			+ "turn on 142,968 through 169,987\r\n"
			+ "turn on 46,470 through 912,853\r\n"
			+ "turn on 182,252 through 279,941\r\n"
			+ "toggle 261,143 through 969,657\r\n"
			+ "turn off 69,600 through 518,710\r\n"
			+ "turn on 372,379 through 779,386\r\n"
			+ "toggle 867,391 through 911,601\r\n"
			+ "turn off 174,287 through 900,536\r\n"
			+ "toggle 951,842 through 993,963\r\n"
			+ "turn off 626,733 through 985,827\r\n"
			+ "toggle 622,70 through 666,291\r\n"
			+ "turn off 980,671 through 985,835\r\n"
			+ "turn off 477,63 through 910,72\r\n"
			+ "turn off 779,39 through 940,142\r\n"
			+ "turn on 986,570 through 997,638\r\n"
			+ "toggle 842,805 through 943,985\r\n"
			+ "turn off 890,886 through 976,927\r\n"
			+ "turn off 893,172 through 897,619\r\n"
			+ "turn off 198,780 through 835,826\r\n"
			+ "toggle 202,209 through 219,291\r\n"
			+ "turn off 193,52 through 833,283\r\n"
			+ "toggle 414,427 through 987,972\r\n"
			+ "turn on 375,231 through 668,236\r\n"
			+ "turn off 646,598 through 869,663\r\n"
			+ "toggle 271,462 through 414,650\r\n"
			+ "turn off 679,121 through 845,467\r\n"
			+ "toggle 76,847 through 504,904\r\n"
			+ "turn off 15,617 through 509,810\r\n"
			+ "toggle 248,105 through 312,451\r\n"
			+ "turn off 126,546 through 922,879\r\n"
			+ "turn on 531,831 through 903,872\r\n"
			+ "toggle 602,431 through 892,792\r\n"
			+ "turn off 795,223 through 892,623\r\n"
			+ "toggle 167,721 through 533,929\r\n"
			+ "toggle 813,251 through 998,484\r\n"
			+ "toggle 64,640 through 752,942\r\n"
			+ "turn on 155,955 through 892,985\r\n"
			+ "turn on 251,329 through 996,497\r\n"
			+ "turn off 341,716 through 462,994\r\n"
			+ "toggle 760,127 through 829,189\r\n"
			+ "turn on 86,413 through 408,518\r\n"
			+ "toggle 340,102 through 918,558\r\n"
			+ "turn off 441,642 through 751,889\r\n"
			+ "turn on 785,292 through 845,325\r\n"
			+ "turn off 123,389 through 725,828\r\n"
			+ "turn on 905,73 through 983,270\r\n"
			+ "turn off 807,86 through 879,276\r\n"
			+ "toggle 500,866 through 864,916\r\n"
			+ "turn on 809,366 through 828,534\r\n"
			+ "toggle 219,356 through 720,617\r\n"
			+ "turn off 320,964 through 769,990\r\n"
			+ "turn off 903,167 through 936,631\r\n"
			+ "toggle 300,137 through 333,693\r\n"
			+ "toggle 5,675 through 755,848\r\n"
			+ "turn off 852,235 through 946,783\r\n"
			+ "toggle 355,556 through 941,664\r\n"
			+ "turn on 810,830 through 867,891\r\n"
			+ "turn off 509,869 through 667,903\r\n"
			+ "toggle 769,400 through 873,892\r\n"
			+ "turn on 553,614 through 810,729\r\n"
			+ "turn on 179,873 through 589,962\r\n"
			+ "turn off 466,866 through 768,926\r\n"
			+ "toggle 143,943 through 465,984\r\n"
			+ "toggle 182,380 through 569,552\r\n"
			+ "turn off 735,808 through 917,910\r\n"
			+ "turn on 731,802 through 910,847\r\n"
			+ "turn off 522,74 through 731,485\r\n"
			+ "turn on 444,127 through 566,996\r\n"
			+ "turn off 232,962 through 893,979\r\n"
			+ "turn off 231,492 through 790,976\r\n"
			+ "turn on 874,567 through 943,684\r\n"
			+ "toggle 911,840 through 990,932\r\n"
			+ "toggle 547,895 through 667,935\r\n"
			+ "turn off 93,294 through 648,636\r\n"
			+ "turn off 190,902 through 532,970\r\n"
			+ "turn off 451,530 through 704,613\r\n"
			+ "toggle 936,774 through 937,775\r\n"
			+ "turn off 116,843 through 533,934\r\n"
			+ "turn on 950,906 through 986,993\r\n"
			+ "turn on 910,51 through 945,989\r\n"
			+ "turn on 986,498 through 994,945\r\n"
			+ "turn off 125,324 through 433,704\r\n"
			+ "turn off 60,313 through 75,728\r\n"
			+ "turn on 899,494 through 940,947\r\n"
			+ "toggle 832,316 through 971,817\r\n"
			+ "toggle 994,983 through 998,984\r\n"
			+ "toggle 23,353 through 917,845\r\n"
			+ "toggle 174,799 through 658,859\r\n"
			+ "turn off 490,878 through 534,887\r\n"
			+ "turn off 623,963 through 917,975\r\n"
			+ "toggle 721,333 through 816,975\r\n"
			+ "toggle 589,687 through 890,921\r\n"
			+ "turn on 936,388 through 948,560\r\n"
			+ "turn off 485,17 through 655,610\r\n"
			+ "turn on 435,158 through 689,495\r\n"
			+ "turn on 192,934 through 734,936\r\n"
			+ "turn off 299,723 through 622,847\r\n"
			+ "toggle 484,160 through 812,942\r\n"
			+ "turn off 245,754 through 818,851\r\n"
			+ "turn on 298,419 through 824,634\r\n"
			+ "toggle 868,687 through 969,760\r\n"
			+ "toggle 131,250 through 685,426\r\n"
			+ "turn off 201,954 through 997,983\r\n"
			+ "turn on 353,910 through 832,961\r\n"
			+ "turn off 518,781 through 645,875\r\n"
			+ "turn off 866,97 through 924,784\r\n"
			+ "toggle 836,599 through 857,767\r\n"
			+ "turn on 80,957 through 776,968\r\n"
			+ "toggle 277,130 through 513,244\r\n"
			+ "turn off 62,266 through 854,434\r\n"
			+ "turn on 792,764 through 872,842\r\n"
			+ "turn off 160,949 through 273,989\r\n"
			+ "turn off 664,203 through 694,754\r\n"
			+ "toggle 491,615 through 998,836\r\n"
			+ "turn off 210,146 through 221,482\r\n"
			+ "turn off 209,780 through 572,894\r\n"
			+ "turn on 766,112 through 792,868\r\n"
			+ "turn on 222,12 through 856,241";
	
	@Override
	public String part1() {
		//x,y grid of lights
		int[][] lights = new int[1000][1000];
		//iterate over input
		for(String s : input.split("\r\n")) {
			//split into words
			String[] parts = s.split(" ");
			if(parts[0].equals("toggle")) {
				//parse out numbers
				String[] coord1 = parts[1].split(",");
				String[] coord2 = parts[3].split(",");
				int x1 = Integer.parseInt(coord1[0]);
				int y1 = Integer.parseInt(coord1[1]);
				int x2 = Integer.parseInt(coord2[0]);
				int y2 = Integer.parseInt(coord2[1]);
				for(int x = x1; x <= x2; x++) {
					for(int y = y1; y <= y2; y++) {
						//invert
						lights[x][y] = Math.abs(lights[x][y] - 1);
					}
				}
			} else {
				//value to set locations to
				int set = (parts[1].equals("on") ? 1 : 0);
				//parse out numbers
				String[] coord1 = parts[2].split(",");
				String[] coord2 = parts[4].split(",");
				int x1 = Integer.parseInt(coord1[0]);
				int y1 = Integer.parseInt(coord1[1]);
				int x2 = Integer.parseInt(coord2[0]);
				int y2 = Integer.parseInt(coord2[1]);
				//set range to value
				for(int x = x1; x <= x2; x++) {
					for(int y = y1; y <= y2; y++) {
						lights[x][y] = set;
					}
				}
			}
		}
		int lightsOn = 0;
		for(int x = 0; x < 1000; x++) {
			for(int y = 0; y < 1000; y++) {
				lightsOn += lights[x][y];
			}
		}
		return Integer.toString(lightsOn);
	}

	@Override
	public String part2() {
		//exact same, just adjusted grid changes
		//x,y grid of lights
		int[][] lights = new int[1000][1000];
		//iterate over input
		for(String s : input.split("\r\n")) {
			//split into words
			String[] parts = s.split(" ");
			if(parts[0].equals("toggle")) {
				//parse out numbers
				String[] coord1 = parts[1].split(",");
				String[] coord2 = parts[3].split(",");
				int x1 = Integer.parseInt(coord1[0]);
				int y1 = Integer.parseInt(coord1[1]);
				int x2 = Integer.parseInt(coord2[0]);
				int y2 = Integer.parseInt(coord2[1]);
				for(int x = x1; x <= x2; x++) {
					for(int y = y1; y <= y2; y++) {
						//increase by 2
						lights[x][y] += 2;
					}
				}
			} else {
				//value to set locations to
				int set = (parts[1].equals("on") ? 1 : -1);
				//parse out numbers
				String[] coord1 = parts[2].split(",");
				String[] coord2 = parts[4].split(",");
				int x1 = Integer.parseInt(coord1[0]);
				int y1 = Integer.parseInt(coord1[1]);
				int x2 = Integer.parseInt(coord2[0]);
				int y2 = Integer.parseInt(coord2[1]);
				//set range to value
				for(int x = x1; x <= x2; x++) {
					for(int y = y1; y <= y2; y++) {
						//if going in positive direction or above zero
						//(not at risk of going negative)
						if(set == 1 || lights[x][y] > 0)
							lights[x][y] += set;
					}
				}
			}
		}
		int lightsOn = 0;
		for(int x = 0; x < 1000; x++) {
			for(int y = 0; y < 1000; y++) {
				lightsOn += lights[x][y];
			}
		}
		return Integer.toString(lightsOn);
	}

	public static void main(String[] args) {
		DayRunner.run(new Day06());
	}

}
