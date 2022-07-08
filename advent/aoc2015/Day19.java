package advent.aoc2015;

import java.util.HashMap;
import java.util.HashSet;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;

public class Day19 implements IDay {

	String input = "Al => ThF\r\n"
			+ "Al => ThRnFAr\r\n"
			+ "B => BCa\r\n"
			+ "B => TiB\r\n"
			+ "B => TiRnFAr\r\n"
			+ "Ca => CaCa\r\n"
			+ "Ca => PB\r\n"
			+ "Ca => PRnFAr\r\n"
			+ "Ca => SiRnFYFAr\r\n"
			+ "Ca => SiRnMgAr\r\n"
			+ "Ca => SiTh\r\n"
			+ "F => CaF\r\n"
			+ "F => PMg\r\n"
			+ "F => SiAl\r\n"
			+ "H => CRnAlAr\r\n"
			+ "H => CRnFYFYFAr\r\n"
			+ "H => CRnFYMgAr\r\n"
			+ "H => CRnMgYFAr\r\n"
			+ "H => HCa\r\n"
			+ "H => NRnFYFAr\r\n"
			+ "H => NRnMgAr\r\n"
			+ "H => NTh\r\n"
			+ "H => OB\r\n"
			+ "H => ORnFAr\r\n"
			+ "Mg => BF\r\n"
			+ "Mg => TiMg\r\n"
			+ "N => CRnFAr\r\n"
			+ "N => HSi\r\n"
			+ "O => CRnFYFAr\r\n"
			+ "O => CRnMgAr\r\n"
			+ "O => HP\r\n"
			+ "O => NRnFAr\r\n"
			+ "O => OTi\r\n"
			+ "P => CaP\r\n"
			+ "P => PTi\r\n"
			+ "P => SiRnFAr\r\n"
			+ "Si => CaSi\r\n"
			+ "Th => ThCa\r\n"
			+ "Ti => BP\r\n"
			+ "Ti => TiTi\r\n"
			+ "e => HF\r\n"
			+ "e => NAl\r\n"
			+ "e => OMg\r\n"
			+ "\r\n"
			+ "CRnCaCaCaSiRnBPTiMgArSiRnSiRnMgArSiRnCaFArTiTiBSiThFYCaFArCaCaSiThCaPBSiThSiThCaCaPTiRnPBSiThRnFArArCaCaSiThCaSiThSiRnMgArCaPTiBPRnFArSiThCaSiRnFArBCaSiRnCaPRnFArPMgYCaFArCaPTiTiTiBPBSiThCaPTiBPBSiRnFArBPBSiRnCaFArBPRnSiRnFArRnSiRnBFArCaFArCaCaCaSiThSiThCaCaPBPTiTiRnFArCaPTiBSiAlArPBCaCaCaCaCaSiRnMgArCaSiThFArThCaSiThCaSiRnCaFYCaSiRnFYFArFArCaSiRnFYFArCaSiRnBPMgArSiThPRnFArCaSiRnFArTiRnSiRnFYFArCaSiRnBFArCaSiRnTiMgArSiThCaSiThCaFArPRnFArSiRnFArTiTiTiTiBCaCaSiRnCaCaFYFArSiThCaPTiBPTiBCaSiThSiRnMgArCaF";
	
	@Override
	public String part1() {
		//store conversions backwards to avoid key conflicts
		HashMap<String,String> conversions = new HashMap<String,String>();
		//store target string
		String target = input.split("\r\n\r\n")[1];
		for(String s : input.split("\r\n")) {
			if(s.equals(""))
				break;
			String[] parts = s.split(" => ");
			conversions.put(parts[1], parts[0]);
		}
		
		//hash sets are naturally duplicate-excluding for properly hashed types
		HashSet<String> results = new HashSet<String>();
		for(String s : conversions.keySet()) {
			String replace = conversions.get(s);
			int index = target.indexOf(replace);
			//try replacing each occurrence of replace
			while(index != -1) {
				String result = target.substring(0,index) + s + target.substring(index+replace.length());
				results.add(result);
				index = target.indexOf(replace, index+1);
			}
		}
		return Integer.toString(results.size());
	} 

	@Override
	public String part2() {
		//this solution requires a lot of in-depth CFG and CNF knowledge, but it's far less tedious than other solutions
		
		//input is a context-free-grammar that is essentially in Chomsky normal form
		//examining input, transforms are all in the following forms:
		// e => (2 molecules)
		// 1 molecule => (2 molecules)
		//1 molecule => molecule Rn molecule AR or molecule RN molecule Y molecule Ar or molecule RN molecule Y molecule Y molecule AR
		//using context-free-grammar rules, this means RN and AR function as ( and ) symbols, and Y as comma symbols
		//this means that, reducing downwards from the large element rather than upwards from e
		//it will take (number of elements in the large element) - (number of RN or AR elements) - (2 * number of Y elements) - 1 steps
		String target = input.split("\r\n\r\n")[1];
		//so first, count elements
		int elementCount = 0;
		for(int i = 0; i < target.length(); i++) {
			elementCount++;
			//skip forwards for 2 letter elements
			if(i < target.length() - 1 && Character.isLowerCase(target.charAt(i+1))) {
				i++;
			}
		}
		//then, count Ar and Rn
		int parenthesisCount = 0;
		while(target.contains("Ar") || target.contains("Rn")) {
			if(target.contains("Ar"))
				target = target.replaceFirst("Ar", "");
			else
				target = target.replaceFirst("Rn", "");
			parenthesisCount++;
			
		}
		
		//then, count Y
		int commaCount = 0;
		for(char c : target.toCharArray())
			if(c == 'Y')
				commaCount++;
		
		return Integer.toString(elementCount - parenthesisCount - 2 * commaCount - 1);
	}
	
	public static void main(String[] args) {
		DayRunner.run(new Day19());
	}

}
