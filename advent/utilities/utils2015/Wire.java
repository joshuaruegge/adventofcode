package advent.utilities.utils2015;

import java.util.ArrayList;

public class Wire {
	//overall wire list
	public static ArrayList<Wire> wires = new ArrayList<Wire>();
	
	//booleans for whether wire values have been calculated
	boolean hasResult,hasInput1,hasInput2;
	
	//values to be calculated
	int result, input1, input2;
	
	//input sources
	String input1Source, input2Source;
	
	//operation type
	String op;
	
	//wire ID
	String id;
	
	//already-calculated result constructor 
	//for direct assignment, i.e. 127 -> b
	public Wire(String id, String result) {
		this.id = id;
		//determine whether result is parseable int, or another wire
		try {
			//convert int, set as result, note that result has been calculated
			int res = Integer.parseInt(result);
			this.result = res;
			hasResult = true;
		} catch (Exception e) {
			op = "DIRECT";
			input1Source = result;
		}
	}
	
	//operation-based result constructor
	public Wire(String id, String operator, String input1, String input2) {
		this.id = id;
		op = operator;
		//try parsing each input as number, otherwise set to source (i.e., other wire to calculate result of and use)
		try {
			int in1 = Integer.parseInt(input1);
			this.input1 = in1;
			hasInput1 = true;
		} catch (Exception e) {
			input1Source = input1;
		}
		try {
			int in2 = Integer.parseInt(input2);
			this.input2 = in2;
			hasInput2 = true;
		} catch (Exception e) {
			input2Source = input2;
		}
	}
	
	//locates and returns the wire with id i in the static array
	public static Wire find(String i) {
		for(Wire w : wires)
			if(w.id.equals(i))
				return w;
		return null;
	}
	
	//method to calculate result
	//if values for inputs are needed for other wires, calculates them recursively
	public int result() {
		//returns result if already known
		if(hasResult)
			return result;
		//calculate input 1 if necessary
		if(!hasInput1)
			input1 = find(input1Source).result();
		//calculate input 2 if necessary
		if(!hasInput2 && input2Source != null)
			input2 = find(input2Source).result();
		switch(op) {
		case "DIRECT":
			result = input1;
			break;
		case "AND":
			result = input1 & input2;
			break;
		case "OR":
			result = input1 | input2;
			break;
		case "LSHIFT":
			result = input1 << input2;
			break;
		case "RSHIFT":
			result = input1 >> input2;
			break;
		case "NOT":
			result = ~input1;
			break;
		}
		hasResult = true;
		return result;
	}
}
