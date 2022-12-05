package advent.aoc2020;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;

public class Day01 implements IDay {

	String input = "1883\r\n"
			+ "1543\r\n"
			+ "1801\r\n"
			+ "1731\r\n"
			+ "1070\r\n"
			+ "1631\r\n"
			+ "1490\r\n"
			+ "1179\r\n"
			+ "1098\r\n"
			+ "1582\r\n"
			+ "1717\r\n"
			+ "1830\r\n"
			+ "1408\r\n"
			+ "1524\r\n"
			+ "889\r\n"
			+ "985\r\n"
			+ "2005\r\n"
			+ "1540\r\n"
			+ "1085\r\n"
			+ "1607\r\n"
			+ "1518\r\n"
			+ "1993\r\n"
			+ "1496\r\n"
			+ "1537\r\n"
			+ "1514\r\n"
			+ "1719\r\n"
			+ "1218\r\n"
			+ "1420\r\n"
			+ "1027\r\n"
			+ "1339\r\n"
			+ "1430\r\n"
			+ "989\r\n"
			+ "1613\r\n"
			+ "1970\r\n"
			+ "1227\r\n"
			+ "1082\r\n"
			+ "1079\r\n"
			+ "1068\r\n"
			+ "1674\r\n"
			+ "1186\r\n"
			+ "1744\r\n"
			+ "1297\r\n"
			+ "1467\r\n"
			+ "1647\r\n"
			+ "1141\r\n"
			+ "1825\r\n"
			+ "1759\r\n"
			+ "1395\r\n"
			+ "1596\r\n"
			+ "1405\r\n"
			+ "1844\r\n"
			+ "1461\r\n"
			+ "1762\r\n"
			+ "1122\r\n"
			+ "1173\r\n"
			+ "1838\r\n"
			+ "1983\r\n"
			+ "1632\r\n"
			+ "1995\r\n"
			+ "1245\r\n"
			+ "1454\r\n"
			+ "1120\r\n"
			+ "1671\r\n"
			+ "1526\r\n"
			+ "1572\r\n"
			+ "1929\r\n"
			+ "1642\r\n"
			+ "1864\r\n"
			+ "1351\r\n"
			+ "1155\r\n"
			+ "1885\r\n"
			+ "1226\r\n"
			+ "1810\r\n"
			+ "1252\r\n"
			+ "1061\r\n"
			+ "1882\r\n"
			+ "2002\r\n"
			+ "1627\r\n"
			+ "1128\r\n"
			+ "1575\r\n"
			+ "1750\r\n"
			+ "1046\r\n"
			+ "1767\r\n"
			+ "1270\r\n"
			+ "1037\r\n"
			+ "1198\r\n"
			+ "1942\r\n"
			+ "1074\r\n"
			+ "1820\r\n"
			+ "1301\r\n"
			+ "1382\r\n"
			+ "1687\r\n"
			+ "1824\r\n"
			+ "1996\r\n"
			+ "1704\r\n"
			+ "1051\r\n"
			+ "1546\r\n"
			+ "1431\r\n"
			+ "1102\r\n"
			+ "1041\r\n"
			+ "1547\r\n"
			+ "1202\r\n"
			+ "1875\r\n"
			+ "1800\r\n"
			+ "1433\r\n"
			+ "1901\r\n"
			+ "1165\r\n"
			+ "1151\r\n"
			+ "1785\r\n"
			+ "1903\r\n"
			+ "1278\r\n"
			+ "1185\r\n"
			+ "1940\r\n"
			+ "1935\r\n"
			+ "1479\r\n"
			+ "1495\r\n"
			+ "719\r\n"
			+ "1683\r\n"
			+ "1972\r\n"
			+ "1483\r\n"
			+ "1589\r\n"
			+ "1636\r\n"
			+ "1055\r\n"
			+ "1317\r\n"
			+ "1530\r\n"
			+ "1990\r\n"
			+ "1099\r\n"
			+ "1697\r\n"
			+ "1286\r\n"
			+ "1089\r\n"
			+ "1136\r\n"
			+ "1383\r\n"
			+ "1802\r\n"
			+ "1618\r\n"
			+ "1050\r\n"
			+ "1980\r\n"
			+ "1279\r\n"
			+ "1777\r\n"
			+ "1635\r\n"
			+ "1721\r\n"
			+ "1660\r\n"
			+ "1569\r\n"
			+ "1554\r\n"
			+ "1432\r\n"
			+ "1695\r\n"
			+ "1551\r\n"
			+ "1601\r\n"
			+ "1263\r\n"
			+ "1866\r\n"
			+ "1998\r\n"
			+ "1466\r\n"
			+ "1205\r\n"
			+ "1445\r\n"
			+ "1578\r\n"
			+ "1267\r\n"
			+ "1873\r\n"
			+ "1610\r\n"
			+ "1900\r\n"
			+ "1192\r\n"
			+ "1827\r\n"
			+ "1305\r\n"
			+ "1528\r\n"
			+ "1140\r\n"
			+ "1440\r\n"
			+ "1269\r\n"
			+ "1748\r\n"
			+ "1187\r\n"
			+ "52\r\n"
			+ "1149\r\n"
			+ "1603\r\n"
			+ "1033\r\n"
			+ "1650\r\n"
			+ "1045\r\n"
			+ "1345\r\n"
			+ "1710\r\n"
			+ "1955\r\n"
			+ "1891\r\n"
			+ "1392\r\n"
			+ "1870\r\n"
			+ "1357\r\n"
			+ "1197\r\n"
			+ "1087\r\n"
			+ "1690\r\n"
			+ "1090\r\n"
			+ "622\r\n"
			+ "1590\r\n"
			+ "1304\r\n"
			+ "1533\r\n"
			+ "1971\r\n"
			+ "1959\r\n"
			+ "1842\r\n"
			+ "1172\r\n"
			+ "1653\r\n"
			+ "1093\r\n"
			+ "1299\r\n"
			+ "1203\r\n"
			+ "1119\r\n"
			+ "1193\r\n"
			+ "1223\r\n"
			+ "1291";
	
	@Override
	public String part1() {
		HashSet<Integer> numRequired = new HashSet<Integer>();
		for(String s : input.split("\r\n")) {
			int num = Integer.parseInt(s);
			if(numRequired.contains(2020-num)) {
				return Integer.toString(num * (2020 - num));
			}
			numRequired.add(num);
		}
		return null;
	}

	@Override
	public String part2() {
		//parse into sorted list
		List<Integer> nums = Arrays.stream(input.split("\r\n")).map(x -> Integer.parseInt(x)).sorted().toList();
		//keep two indexes, and for each first index, keep a set of potential third indexes based on each second index
		//if potential third index is discovered, return
		for(int index1 = 0; index1 < nums.size() - 2; index1++) {
			HashSet<Integer> numRequired = new HashSet<Integer>();
			int currentSum = 2020 - nums.get(index1);
			for(int index2 = index1 + 1; index2 < nums.size(); index2++) {
				if(numRequired.contains(currentSum - nums.get(index2))) {
					return Integer.toString(nums.get(index1) * nums.get(index2) *  (currentSum - nums.get(index2)));
				}
				numRequired.add(nums.get(index2));
			}
		}
		return null;
	}

	public static void main(String[] args) {
		DayRunner.run(new Day01());
	}

}
