package advent.utilities.utils2019;

import java.util.ArrayList;

public class IntCodeComputer {
	//instruction pointer
	public int pointer = 0;
	//memory/instructions
	public ArrayList<Long> program;
	//input & output buffers
	public ArrayList<Long> input = new ArrayList<Long>();
	public ArrayList<Long> output = new ArrayList<Long>();
	//state variables
	public boolean halted = false;
	public boolean inputWaiting = false;
	//relative base counter
	public int relBase = 0;
	
	//initialize from program
	public IntCodeComputer(ArrayList<Long> program) {
		this.program = new ArrayList<Long>(program);
		//append some extra memory
		for(int i = 0; i < 5000; i++) {
			this.program.add(0l);
		}
	}
	
	//execute program currently in memory
	public void run() {
		program:
			//probably unnecessary pointer check, but catches bugs
			while(pointer > -1 && pointer < program.size()) {
				//distance to jump on command completion
				int jumpDist = 0;
				//string representation of current instruction
				String value = program.get(pointer) + "";
				//opcode of current instruction
				int opcode = 0;
				//if current instruction is using an alternate input mode, extract input mode data and opcode
				String modes = "";
				if(value.length() > 2) {
					//pull opcode from end
					opcode = Integer.parseInt(value.substring(value.length() - 2));
					//modes = reversed of mode data (so indexes correspond with parameters)
					modes = value.substring(0,value.length() - 2);
					StringBuilder rev = new StringBuilder(modes);
					rev.reverse();
					modes = rev.toString();
				} else {
					opcode = Integer.parseInt(value);
				}
				switch(opcode) {
				case 1:
					long sum1;
					if(modes.length() > 0 && modes.charAt(0) == '1') {
						sum1 = program.get(pointer+1);
					} else if (modes.length() > 0 && modes.charAt(0) == '2') { 
						sum1 = program.get((int) (program.get(pointer+1) + relBase));
					}else {
						sum1 = program.get(Math.toIntExact(program.get(pointer+1)));
					}
					long sum2;
					if(modes.length() > 1 && modes.charAt(1) == '1') {
						sum2 = program.get(pointer+2);
					} else if(modes.length() > 1 && modes.charAt(1) == '2') { 
						sum2 = program.get((int) (program.get(pointer+2) + relBase));
					}else {
						sum2 = program.get(Math.toIntExact(program.get(pointer+2)));
					}
					long sum = sum1 + sum2;
					if(modes.length() > 2 && modes.charAt(2) == '2') {
						program.set((int) (program.get(pointer+3) + relBase), sum);
					} else {
						program.set(Math.toIntExact(program.get(pointer+3)), sum);
					}
					jumpDist = 4;
					break;
				case 2:
					long mult1;
					if(modes.length() > 0 && modes.charAt(0) == '1') {
						mult1 = program.get(pointer+1);
					} else if(modes.length() > 0 && modes.charAt(0) == '2') { 
						mult1 = program.get((int) (program.get(pointer+1) + relBase));
					}else {
						mult1 = program.get(Math.toIntExact(program.get(pointer+1)));
					}
					long mult2;
					if(modes.length() > 1 && modes.charAt(1) == '1') {
						mult2 = program.get(pointer+2);
					} else if(modes.length() > 1 && modes.charAt(1) == '2') {
						mult2 = program.get((int) (program.get(pointer+2) + relBase));
					}else {
						mult2 = program.get(Math.toIntExact(program.get(pointer+2)));
					}
					long mult = mult1 * mult2;
					if(modes.length() > 2 && modes.charAt(2) == '2') {
						program.set((int) (program.get(pointer+3) + relBase),mult);
					} else {
						program.set(Math.toIntExact(program.get(pointer+3)), mult);
					}
					jumpDist = 4;
					break;
				//3 - no possibility for immediate?
				case 3: 
					long in;
					if(input.size() == 0) {
						break program;
					} else {
						in = input.remove(0);
					}
					if(modes.length() > 0 && modes.charAt(0) == '2') {
						program.set((int) (program.get(pointer+1) + relBase),in);
					} else {
						program.set(Math.toIntExact(program.get(pointer+1)), in);
					}
					jumpDist = 2;
					break;
				case 4:
					long outValue;
					if(modes.length() > 0 && modes.charAt(0) == '1') {
						outValue = program.get(pointer+1);
					} else if(modes.length() > 0 && modes.charAt(0) == '2') { 
						outValue = program.get((int) (program.get(pointer+1) + relBase));
					}else {
						outValue = program.get(Math.toIntExact(program.get(pointer+1)));
					}
					output.add(outValue);
					jumpDist = 2;
					break;
				case 5:
					long jitTest;
					if(modes.length() > 0 && modes.charAt(0) == '1') {
						jitTest = program.get(pointer+1);
					} else if(modes.length() > 0 && modes.charAt(0) == '2') { 
						jitTest = program.get((int) (program.get(pointer+1) + relBase));
					}else {
						jitTest = program.get(Math.toIntExact(program.get(pointer+1)));
					}
					if(jitTest != 0) {
						long jitResult;
						if(modes.length() > 1 && modes.charAt(1) == '1') {
							jitResult = program.get(pointer+2);
						} else if(modes.length() > 1 && modes.charAt(1) == '2') { 
							jitResult = program.get((int) (program.get(pointer+2) + relBase));
						}else {
							jitResult = program.get(Math.toIntExact(program.get(pointer+2)));
						}
						pointer = Math.toIntExact(jitResult);
						jumpDist = 0;
					} else {
						jumpDist = 3;
					}
					break;
				case 6:
					long jifTest;
					if(modes.length() > 0 && modes.charAt(0) == '1') {
						jifTest = program.get(pointer + 1);
					} else if(modes.length() > 0 && modes.charAt(0) == '2') { 
						jifTest = program.get((int) (program.get(pointer+1) + relBase));
					}else {
						jifTest = program.get(Math.toIntExact(program.get(pointer+1)));
					}
					if(jifTest == 0) {
						long jifResult;
						if(modes.length() > 1 && modes.charAt(1) == '1') {
							jifResult = program.get(pointer+2);
						} else if(modes.length() > 1 && modes.charAt(1) == '2') { 
							jifResult = program.get((int) (program.get(pointer+2) + relBase));
						}else {
							jifResult = program.get(Math.toIntExact(program.get(pointer+2)));
						}
						pointer = Math.toIntExact(jifResult);
						jumpDist = 0;
					} else {
						jumpDist = 3;
					}
					break;
				case 7:
					long lt1;
					if(modes.length() > 0 && modes.charAt(0) == '1') {
						lt1 = program.get(pointer+1);
					} else if(modes.length() > 0 && modes.charAt(0) == '2') { 
						lt1 = program.get((int) (program.get(pointer+1) + relBase));
					}else {
						lt1 = program.get(Math.toIntExact(program.get(pointer+1)));
					}
					long lt2;
					if(modes.length() > 1 && modes.charAt(1) == '1') {
						lt2 = program.get(pointer+2);
					} else if(modes.length() > 1 && modes.charAt(1) == '2') { 
						lt2 = program.get((int) (program.get(pointer+2) + relBase));
					}else {
						lt2 = program.get(Math.toIntExact(program.get(pointer+2)));
					}
					
					if(modes.length() > 2 && modes.charAt(2) == '2') {
						program.set((int) (program.get(pointer+3) + relBase), (lt1 < lt2 ? 1l : 0l));
					} else {
						program.set(Math.toIntExact(program.get(pointer+3)), (lt1 < lt2 ? 1l : 0l));
					}
					jumpDist = 4;
					break;
				case 8:
					long eq1;
					if(modes.length() > 0 && modes.charAt(0) == '1') {
						eq1 = program.get(pointer+1);
					} else if(modes.length() > 0 && modes.charAt(0) == '2') {
						eq1 = program.get((int) (program.get(pointer+1) + relBase));
					}else {
						eq1 = program.get(Math.toIntExact(program.get(pointer+1)));
					}
					long eq2;
					if(modes.length() > 1 && modes.charAt(1) == '1') {
						eq2 = program.get(pointer+2);
					} else if(modes.length() > 1 && modes.charAt(1) == '2') {
						eq2 = program.get((int) (program.get(pointer+2) + relBase));
					}else {
						eq2 = program.get(Math.toIntExact(program.get(pointer+2)));
					}
					if(modes.length() > 2 && modes.charAt(2) == '2') {
						program.set((int) (program.get(pointer+3) + relBase), (eq1 == eq2 ? 1l : 0l));
					} else {
						program.set(Math.toIntExact(program.get(pointer+3)), (eq1 == eq2 ? 1l : 0l));
					}
					jumpDist = 4;
					break;
				case 9:
					long rb;
					if(modes.length() > 0 && modes.charAt(0) == '1') {
						rb = program.get(pointer+1);
					} else if(modes.length() > 0 && modes.charAt(0) == '2') {
						rb = program.get((int) (program.get(pointer+1) + relBase));
					} else {
						rb = program.get(Math.toIntExact(program.get(pointer+1)));
					}
					relBase += rb;
					jumpDist = 2;
					break;
				case 99:
					halted = true;
					break program;
				default:
					System.out.println("Invalid opcode!");
					break program;
				}
				//increment by jump distance
				pointer += jumpDist;
			}
	}
	
	//pull first output from buffer
	public long output() {
		return output.remove(0);
	}
	
	//append to input buffer
	public void input(long i) {
		input.add(i);
	}
}
