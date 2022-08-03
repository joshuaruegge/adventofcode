package advent.aoc2018;

import java.util.HashMap;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;

public class Day02 implements IDay {

	String input = "ybruvapdgixszyckwtfqjonsie\r\n"
			+ "mbruvapxghslyyckwtfqjonsie\r\n"
			+ "mbruvapdghslzyckwtkujonsie\r\n"
			+ "rwruvapdghxlzyckwtfqjcnsie\r\n"
			+ "obruvapdgtxlzyckwtfqionsie\r\n"
			+ "lbruvapdghxqzyckwtfqjfnsie\r\n"
			+ "mbrunapdghxlzyccatfqjonsie\r\n"
			+ "mbruvapdghxlzyokltfqjdnsie\r\n"
			+ "ybruvapdghxlzmckwtfqjmnsie\r\n"
			+ "mbruwaadghxdzyckwtfqjonsie\r\n"
			+ "muruvapdghxlzyckvtfqjonsim\r\n"
			+ "mbruvapdghxlkyckwtxqjonjie\r\n"
			+ "mbruvaqdghxlzyckwtfqjrnnie\r\n"
			+ "mwruvapdghdlzyckttfqjonsie\r\n"
			+ "mbruvapdgtelzyckwxfqjonsie\r\n"
			+ "mbruvapdohxlzvckwtfqjonhie\r\n"
			+ "mbrugapdgbxlzyckwtfqjynsie\r\n"
			+ "mbruvapdghxlzyckwtlqjonjiu\r\n"
			+ "mbruvapwghxlzyckwafqjonbie\r\n"
			+ "wbruvapdghxlhyckwtfqjonsii\r\n"
			+ "mbruvapdghxlzyckwtcqnonsiq\r\n"
			+ "mbyuvapighxlzybkwtfqjonsie\r\n"
			+ "mbrrvapdghxvzyckwtfqjonsio\r\n"
			+ "mhruvapdghrlzyckwtfzjonsie\r\n"
			+ "mtruvapvghxlzyckwtfnjonsie\r\n"
			+ "mmrlhapdghxlzyckwtfqjonsie\r\n"
			+ "mbruvapdgpxlzyjkwtfqjovsie\r\n"
			+ "mbrucapdghxlzymkwtzqjonsie\r\n"
			+ "mbeuvafdghxlzyckwtfqjonwie\r\n"
			+ "mbruvapcghxlayckwtfqjonsii\r\n"
			+ "mbruvabdghxlzyckwtfqyansie\r\n"
			+ "mbruvjpdghxlzyckwtfqgfnsie\r\n"
			+ "lbruvapdghxlzyckwtfqjonriv\r\n"
			+ "mbrupapdghxlzycjwtfqronsie\r\n"
			+ "mbpuvapdthxlzymkwtfqjonsie\r\n"
			+ "mbiuvapdgixlzyckwxfqjonsie\r\n"
			+ "mbruvapdghxyzyckwtfcjonsbe\r\n"
			+ "mbrurapkghxlzyckwtfqjonzie\r\n"
			+ "mbrufapdrhxlzyciwtfqjonsie\r\n"
			+ "mbruvapdghxlzbckwtfqjoisae\r\n"
			+ "ubruhapdghxlzuckwtfqjonsie\r\n"
			+ "mbruvapdjhulzyckwtfqjonshe\r\n"
			+ "mbruwapdgyxlzyckntfqjonsie\r\n"
			+ "mwruvapdghplzyckwtfqjonsme\r\n"
			+ "mbruvapjghtlzyckwtfqgonsie\r\n"
			+ "pbruvapdghhlzyckwtfrjonsie\r\n"
			+ "mbruvgpdihxqzyckwtfqjonsie\r\n"
			+ "mbruvahdohxlzyckwtfijonsie\r\n"
			+ "ibuuvapdghxlzyckwtfqjofsie\r\n"
			+ "mbruvandghxlzyckwtfqjrnxie\r\n"
			+ "mbrjvlpdghxlzyckwgfqjonsie\r\n"
			+ "mbruvapogfxlzyckotfqjonsie\r\n"
			+ "mbruvrpdghxlzyckutfejonsie\r\n"
			+ "mbruvbpdghxlzyhkwtfqjonsip\r\n"
			+ "mbruvapdghxlzyckmnfqjensie\r\n"
			+ "mbruvapdghvlzyckwtfqjowsix\r\n"
			+ "mbruvakdgholzwckwtfqjonsie\r\n"
			+ "mbruvapdghxlzackwtfqconsae\r\n"
			+ "mbruvapdghxlzyqvwtfqjlnsie\r\n"
			+ "mprrvapdgfxlzyckwtfqjonsie\r\n"
			+ "mbrunacdghxlhyckwtfqjonsie\r\n"
			+ "obruvapdgsxlzyckwtfqjonvie\r\n"
			+ "murcvapdghslzyckwtfqjonsie\r\n"
			+ "mbruvapdghxlzyzkwmftjonsie\r\n"
			+ "mbrwvapdgtvlzyckwtfqjonsie\r\n"
			+ "mbxuvapdghxlzqcnwtfqjonsie\r\n"
			+ "mbruvaddghxboyckwtfqjonsie\r\n"
			+ "mhruvwndghxlzyckwtfqjonsie\r\n"
			+ "mbrdvapdghxlzyckwmpqjonsie\r\n"
			+ "mbruvapdgyxlzyckizfqjonsie\r\n"
			+ "mbruvapdghxlzlckwtfqeowsie\r\n"
			+ "mbruvbpdgrxlzyckwtfqjonsxe\r\n"
			+ "mbruqapoghxlzyckwtvqjonsie\r\n"
			+ "mbouhapdghmlzyckwtfqjonsie\r\n"
			+ "mbruvapjghxidyckwtfqjonsie\r\n"
			+ "mbsuvapkghxlkyckwtfqjonsie\r\n"
			+ "mbruvlpdghxlzycrwtfqjonsis\r\n"
			+ "mcrueapdghxlzyckwtfqjynsie\r\n"
			+ "muruvapngbxlzyckwtfqjonsie\r\n"
			+ "mbruvapdghxlzycawtfyjojsie\r\n"
			+ "mbruvbpdghxczyjkwtfqjonsie\r\n"
			+ "ybduvapdghxnzyckwtfqjonsie\r\n"
			+ "mbruvbpdghxlzyckwtfbjousie\r\n"
			+ "mbouvapdghxlzycbwtfqponsie\r\n"
			+ "mbruvaedghplzycgwtfqjonsie\r\n"
			+ "mbrhvapdghxlzyckytfqjgnsie\r\n"
			+ "mbruvapdqbxleyckwtfqjonsie\r\n"
			+ "mbruvapddhhldyckwtfqjonsie\r\n"
			+ "mbruvapdghxlwrckwtfqjondie\r\n"
			+ "mbruvapdmhxlzyckwtfqkonsve\r\n"
			+ "xbbuvapdghxlzyckwtfkjonsie\r\n"
			+ "mbruvapdghxlzyckwcfqjunkie\r\n"
			+ "mbruvapdghxlzyckwtfqxonfib\r\n"
			+ "mbrtvapkghxlzyckwtfqeonsie\r\n"
			+ "mbruvazdghxldymkwtfqjonsie\r\n"
			+ "kbruvapddhxlzfckwtfqjonsie\r\n"
			+ "mbouvapdghxlpyckwtfqjoosie\r\n"
			+ "mbauvapdghxlzyckwtfqjszsie\r\n"
			+ "mbruvapdghtlzyckntfqtonsie\r\n"
			+ "mbruvipdggxlzbckwtfqjonsie\r\n"
			+ "mbruqapdghrlzyckwtfqjznsie\r\n"
			+ "myruvacdghxlzyckwifqjonsie\r\n"
			+ "mbruvapdghxlzuckwtfkjocsie\r\n"
			+ "mwjuvapdghxlzyckwtfqjonsxe\r\n"
			+ "mbruvapxghxlzickwtfqjobsie\r\n"
			+ "mbrupapdghxtlyckwtfqjonsie\r\n"
			+ "meruvapdjjxlzyckwtfqjonsie\r\n"
			+ "mbruvkodghxlzyckwofqjonsie\r\n"
			+ "mbruvapdgexlzyckwtgkjonsie\r\n"
			+ "mbruvapwghxlzyckwtcqjonsiw\r\n"
			+ "mbruvapdghxlzykkwtfqtoxsie\r\n"
			+ "mbruvapdahxlzycgwtfwjonsie\r\n"
			+ "mbruvapdgwxlhyckhtfqjonsie\r\n"
			+ "mbruvapbghxlzycbhmfqjonsie\r\n"
			+ "mbruvapdghxvzyzkwtfqjonsin\r\n"
			+ "mbrcvapdqhxlzyckwyfqjonsie\r\n"
			+ "zbruvaxdghxlzyckwgfqjonsie\r\n"
			+ "mtruvapdghxlilckwtfqjonsie\r\n"
			+ "bbruvapdghxlzyckwtfmjonsxe\r\n"
			+ "mbruvajdghxlzyckwtfqfwnsie\r\n"
			+ "mbruvapdgkxlzyckwtfqionpie\r\n"
			+ "rbruvapdghxlryckwdfqjonsie\r\n"
			+ "mbruvandghxlzyckwmfvjonsie\r\n"
			+ "mbruvahdghxlzeckwtfqjonsme\r\n"
			+ "mbruvnpcghxlzyckwtfqjobsie\r\n"
			+ "mbruvapdghqlzyckwtfbjonsiy\r\n"
			+ "mbruvavdghxlzyckwufqjodsie\r\n"
			+ "mbruvapdghxlzyckwtfzmovsie\r\n"
			+ "mbruvlpdghxuzyckwtfqjoesie\r\n"
			+ "mbruvopdghxlzycwwtfqjansie\r\n"
			+ "obruvapdghglzybkwtfqjonsie\r\n"
			+ "mbpuvlpdghxlcyckwtfqjonsie\r\n"
			+ "mbruvaidghxlzyckwtfmjonoie\r\n"
			+ "mbruvapdihxzzyckwtfqjonsiy\r\n"
			+ "mbquvapdghxlzyckwtfqconsme\r\n"
			+ "mbruvapdghslzyckqtfqjojsie\r\n"
			+ "mbrzdapdghxmzyckwtfqjonsie\r\n"
			+ "mwruvapdghxozyckwtfqjonsxe\r\n"
			+ "muruvapdgfxlzyckwtfqjojsie\r\n"
			+ "wtruvapdghxlzyckvtfqjonsie\r\n"
			+ "mbruvapdghxlzyckysfqjxnsie\r\n"
			+ "mbruvrpdghxczyckktfqjonsie\r\n"
			+ "mbquvapdghxlryckwtfqjonsne\r\n"
			+ "mbruvapdghflzycvwtfqjpnsie\r\n"
			+ "mbruvapughclzyckwtfqjonsin\r\n"
			+ "mbrhvapdghxlpyckwtfqjonsre\r\n"
			+ "mbruvapdgtxlzyckwtfqjoosit\r\n"
			+ "mbrupapnghxhzyckwtfqjonsie\r\n"
			+ "mmvuvapdvhxlzyckwtfqjonsie\r\n"
			+ "mbruvaptghxlzyckwtfqjotsse\r\n"
			+ "mgruvapvghxlzyckwtfqjonsix\r\n"
			+ "mbrupapdghxszyckwtfqjunsie\r\n"
			+ "mbruvkpdghelzyckwtfqjpnsie\r\n"
			+ "mbruvrrdghjlzyckwtfqjonsie\r\n"
			+ "mbruvapdghnlzyckwtfkjonsze\r\n"
			+ "mbruvwpdghxlzyckwtfqhoysie\r\n"
			+ "mbrsvapdfhxlzyckwtfqjobsie\r\n"
			+ "mbruvapdgexezymkwtfqjonsie\r\n"
			+ "ybruvapdghxlzyckwtfqxonsiw\r\n"
			+ "mrruvapdghxdzyckwtfqjossie\r\n"
			+ "mbruvapdghtlzyckwtfqconsiu\r\n"
			+ "mbrpvapdghxlzlckwpfqjonsie\r\n"
			+ "mbruvjpdghslzyckwtfqjjnsie\r\n"
			+ "mhruvapoghxlzyckwtfvjonsie\r\n"
			+ "mbrubqpdghvlzyckwtfqjonsie\r\n"
			+ "mbruvapdghxlzackwtfqconsiw\r\n"
			+ "mbruvapdgnxlzkckwtfqjdnsie\r\n"
			+ "mbrudapgghelzyckwtfqjonsie\r\n"
			+ "mbruvapdghxlzlakwbfqjonsie\r\n"
			+ "mbpuvapdghxlzyckwtuqjonjie\r\n"
			+ "abruvapdghxlzykkwtfqjonzie\r\n"
			+ "mbrupupdghxlsyckwtfqjonsie\r\n"
			+ "mbrsvupdghxlzyckwtfqjonkie\r\n"
			+ "mxruvgpdghxllyckwtfqjonsie\r\n"
			+ "mbrnvapdghxlzycbwtfqfonsie\r\n"
			+ "mbrbxapdghxlzyckttfqjonsie\r\n"
			+ "mbnuvapdghxlzyxkwtmqjonsie\r\n"
			+ "mbrfvapdghjlzickwtfqjonsie\r\n"
			+ "mbhuvupdghxlzyxkwtfqjonsie\r\n"
			+ "mbrcvapdghxluyckwtfqjznsie\r\n"
			+ "mbruvapdghxlzyckwofqjoxsiz\r\n"
			+ "mbrevapdghxloyckwtfqjonnie\r\n"
			+ "mbruvipdghnlzyckwtfqjopsie\r\n"
			+ "mbxxvaptghxlzyckwtfqjonsie\r\n"
			+ "mbruvcpdghxlztckwtjqjonsie\r\n"
			+ "mqruvlpdghxlzyckotfqjonsie\r\n"
			+ "mbruvapdgqxlzyckwtfqjpvsie\r\n"
			+ "mbruvapdgvxlzyjkwtfqjbnsie\r\n"
			+ "mbruvapdghxlgyckwtfqcocsie\r\n"
			+ "mbruvapdghxkwyckwtfqjoqsie\r\n"
			+ "mbrgvavdghxlzyckwxfqjonsie\r\n"
			+ "qbruqapdgvxlzyckwtfqjonsie\r\n"
			+ "mbauvapdghxlzgckwtfqjunsie\r\n"
			+ "mbruvapdgdxluyckwtfqjoosie\r\n"
			+ "mbruvapdghxlzykkwtfqwobsie\r\n"
			+ "mbruvapdghxlzhcnwtfqjonqie\r\n"
			+ "mbruvapdghxlzycbhmfqjonsie\r\n"
			+ "mbruvapdghxluyczwtfqjontie\r\n"
			+ "mbruvapnghxlzyckwnfqjonbie\r\n"
			+ "moruvapdghxlzcckwtfqponsie\r\n"
			+ "mbruvapfgxxlzyckwtfqjunsie\r\n"
			+ "mbruvapdghxlryckvtfejonsie\r\n"
			+ "mbrzvapdghxlzvcbwtfqjonsie\r\n"
			+ "mbruvapdgqxlzyckwcfqjonsce\r\n"
			+ "abruvupdrhxlzyckwtfqjonsie\r\n"
			+ "mbrubaptghxlzyckwtfqjondie\r\n"
			+ "mgruvapdgpxlzyckwtfijonsie\r\n"
			+ "mbruvapdghxczlckwtfujonsie\r\n"
			+ "mbruvapdgmmlzyckwtfqjonsir\r\n"
			+ "mbruvapdhhxltyckwtfdjonsie\r\n"
			+ "mbruvapdghxlzyckwtfdjjnste\r\n"
			+ "mbrdvzpdghxlcyckwtfqjonsie\r\n"
			+ "mbruvapdghxlzyckwtnqbonsim\r\n"
			+ "mbrovapdghxlzyckwtfpjousie\r\n"
			+ "mymuvapdghxlzyjkwtfqjonsie\r\n"
			+ "mbpuvapdghxlzyckwtfljcnsie\r\n"
			+ "mbrxvapdghxlzyclwtfqjonpie\r\n"
			+ "mbrueapdghxlzyckwtfqjopsia\r\n"
			+ "mbruvapdghxlzycdwtfqjbfsie\r\n"
			+ "tbruvavdghxlzyckwtmqjonsie\r\n"
			+ "mbduvapdghxlzyckwrfqjrnsie\r\n"
			+ "mkrsvapughxlzyckwtfqjonsie\r\n"
			+ "mbruvapdghylzyckwtfqtolsie\r\n"
			+ "mgruvapdglxldyckwtfqjonsie\r\n"
			+ "mbrunapdghclzyckwtfqjonsiy\r\n"
			+ "mbruvapdgrxlxyckwtfgjonsie\r\n"
			+ "mbruvapdghxpzbckftfqjonsie\r\n"
			+ "mbruvcpdghxyzyckotfqjonsie\r\n"
			+ "mbruvapdghxlsyckwtfqcqnsie\r\n"
			+ "mbruvapdghxlzzckwtfqjonskf\r\n"
			+ "mbruvppdghxlzfckwtfqjgnsie\r\n"
			+ "mbhuvapdghxlzytkwtfqjonoie\r\n"
			+ "mbruvapdghxlzvrkwtfqjjnsie\r\n"
			+ "mbmuvapdghxuzyckwtfqjonsze\r\n"
			+ "mbruvapdghnlzycnwtfqjonsil\r\n"
			+ "mbruvapdgholzyckitfqjonsia\r\n"
			+ "mbruxapdghxlmyckwtfqbonsie\r\n"
			+ "mbauvapdgholzyckwtfqjolsie\r\n"
			+ "mbruvapdghxlzyckwtfqjotslq\r\n"
			+ "dbrutapdghxlzyckwtfqjonsiv\r\n"
			+ "mbruvapdzhxlyyckwtfbjonsie\r\n"
			+ "mmruaapsghxlzyckwtfqjonsie\r\n"
			+ "mbruvaldgqxqzyckwtfqjonsie\r\n"
			+ "mbruvaodghxdzyjkwtfqjonsie\r\n"
			+ "mbrcmatdghxlzyckwtfqjonsie\r\n"
			+ "mbrqvapdgtxlzycewtfqjonsie\r\n"
			+ "mjruvapdghzlzyckwtfqjonrie\r\n"
			+ "mbruvapdghxopcckwtfqjonsie\r\n"
			+ "mbruvapdghxszycwwtfqjoqsie\r\n"
			+ "mbruvapdgoxezyckwtjqjonsie";
	
	@Override
	public String part1() {
		int twoCount = 0;
		int threeCount = 0;
		for(String s : input.split("\r\n")) {
			//character frequency map
			HashMap<Character,Integer> freq = new HashMap<Character,Integer>();
			for(char c : s.toCharArray()) {
				freq.put(c, freq.getOrDefault(c, 0) + 1);
			}
			for(int i : freq.values()) {
				if(i == 2) {
					twoCount++;
					break;
				}
			}
			for(int i : freq.values()) {
				if(i == 3) {
					threeCount++;
					break;
				}
			}
		}
		return Integer.toString(twoCount * threeCount);
	}

	@Override
	public String part2() {
		String[] ids = input.split("\r\n");
		for(String s : ids) {
			for(String t : ids) {
				if(s.equals(t))
					continue;
				//tally different characters - break if more than 1
				int charDifference = 0;
				for(int i = 0; i < s.length(); i++) {
					if(s.charAt(i) != t.charAt(i))
						charDifference++;
					if(charDifference > 1)
						break;
				}
				if(charDifference == 1) {
					//create solution string from common characters
					String sol = "";
					for(int i = 0; i < s.length(); i++) {
						if(s.charAt(i) == t.charAt(i))
							sol += s.charAt(i);
					}
					return sol;
				}
			}
		}
		return null;
	}

	public static void main(String[] args) {
		DayRunner.run(new Day02());
	}

}
