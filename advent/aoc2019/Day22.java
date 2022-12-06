package advent.aoc2019;

import java.math.BigInteger;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;
import advent.utilities.general.Input;

public class Day22 implements IDay {

	static String input;

	@Override
	public String part1() {
		//because shuffle options work in a loop (shuffling a deck of length (count) (count-1) times will return the same deck),
		//to find the position of a card after one shuffle, we can instead do (count-2) shuffles and check the
		//number on the card at the position of the card we are looking for.
		//this is similar to doing a "reverse" shuffle, then instead of looking for the number 2019, we look at the position 2019
		return shuffle(new BigInteger("10007"),new BigInteger("10005"),new BigInteger("2019")).toString();
	}

	@Override
	public String part2() {
		//this one is a lot simpler - just shuffle the given deck, then find the card at position 2020
		return shuffle(new BigInteger("119315717514047"), new BigInteger("101741582076661"), new BigInteger("2020")).toString();
	}

	//performs the input on a deck of (count) size, (shuffles) times, and returns the card number that would be at (position) index
	//this uses fancy linear function interpretations of the shuffle operations to keep track of one position's value,
	//rather than process the entire deck array
	//because we're throwing exponents around like floating point errors in a Javascript program, BigIntegers are necessary to maintain accuracy.
	//do yourself a favor and allocate some extra memory just in case
	public static BigInteger shuffle(BigInteger count, BigInteger shuffles, BigInteger position) {
		//buffers for various distances within deck
		BigInteger o = BigInteger.ZERO;
		BigInteger i = BigInteger.ONE;
		for(String s : input.split("\n")) {
			String[] words = s.split(" ");
			if(words[0].equals("cut")) {
				o = o.add(i.multiply(new BigInteger(words[1])));
			}
			if(words[1].equals("with")) {
				i = i.multiply(new BigInteger(words[3]).modPow(count.subtract(BigInteger.ONE.add(BigInteger.ONE)), count));
			}
			if(words[1].equals("into")) {
				o = o.subtract(i);
				i = i.negate();
			}
			
		}
		o = o.multiply(BigInteger.ONE.subtract(i).modPow(count.subtract(BigInteger.ONE.add(BigInteger.ONE)), count));
		i = i.modPow(shuffles, count);
		return position.multiply(i).add(BigInteger.ONE.subtract(i).multiply(o)).mod(count);	
	}

	public static void main(String[] args) {
		input = Input.fetchInput(2019,22);
		DayRunner.run(new Day22());
	}
}
