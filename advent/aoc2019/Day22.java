package advent.aoc2019;

import java.math.BigInteger;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;

public class Day22 implements IDay {

	static String input = "deal with increment 15\r\n"
			+ "cut -4394\r\n"
			+ "deal with increment 9\r\n"
			+ "deal into new stack\r\n"
			+ "cut -8068\r\n"
			+ "deal with increment 15\r\n"
			+ "deal into new stack\r\n"
			+ "cut 1470\r\n"
			+ "deal into new stack\r\n"
			+ "cut 4151\r\n"
			+ "deal into new stack\r\n"
			+ "cut -2438\r\n"
			+ "deal into new stack\r\n"
			+ "cut 9852\r\n"
			+ "deal with increment 50\r\n"
			+ "cut -953\r\n"
			+ "deal with increment 8\r\n"
			+ "cut -2836\r\n"
			+ "deal with increment 30\r\n"
			+ "cut -2419\r\n"
			+ "deal into new stack\r\n"
			+ "cut 2759\r\n"
			+ "deal with increment 66\r\n"
			+ "cut 1127\r\n"
			+ "deal with increment 66\r\n"
			+ "cut 2194\r\n"
			+ "deal with increment 48\r\n"
			+ "cut 4710\r\n"
			+ "deal with increment 49\r\n"
			+ "deal into new stack\r\n"
			+ "deal with increment 59\r\n"
			+ "deal into new stack\r\n"
			+ "deal with increment 25\r\n"
			+ "deal into new stack\r\n"
			+ "deal with increment 60\r\n"
			+ "cut -2003\r\n"
			+ "deal with increment 2\r\n"
			+ "cut -6166\r\n"
			+ "deal with increment 26\r\n"
			+ "cut -6179\r\n"
			+ "deal with increment 4\r\n"
			+ "cut 373\r\n"
			+ "deal with increment 53\r\n"
			+ "cut 6849\r\n"
			+ "deal with increment 13\r\n"
			+ "cut 625\r\n"
			+ "deal with increment 68\r\n"
			+ "cut 4084\r\n"
			+ "deal with increment 53\r\n"
			+ "cut -6939\r\n"
			+ "deal into new stack\r\n"
			+ "cut -3416\r\n"
			+ "deal with increment 39\r\n"
			+ "cut -2671\r\n"
			+ "deal with increment 64\r\n"
			+ "deal into new stack\r\n"
			+ "deal with increment 75\r\n"
			+ "cut 7654\r\n"
			+ "deal into new stack\r\n"
			+ "cut -5431\r\n"
			+ "deal with increment 66\r\n"
			+ "cut -370\r\n"
			+ "deal into new stack\r\n"
			+ "cut 3316\r\n"
			+ "deal with increment 31\r\n"
			+ "cut 312\r\n"
			+ "deal with increment 22\r\n"
			+ "cut 71\r\n"
			+ "deal with increment 21\r\n"
			+ "cut 562\r\n"
			+ "deal with increment 27\r\n"
			+ "cut 8611\r\n"
			+ "deal with increment 67\r\n"
			+ "cut 8358\r\n"
			+ "deal with increment 7\r\n"
			+ "cut -2957\r\n"
			+ "deal with increment 71\r\n"
			+ "cut 1740\r\n"
			+ "deal with increment 31\r\n"
			+ "cut -9577\r\n"
			+ "deal with increment 59\r\n"
			+ "cut 6104\r\n"
			+ "deal with increment 40\r\n"
			+ "cut -8862\r\n"
			+ "deal with increment 17\r\n"
			+ "cut 2516\r\n"
			+ "deal with increment 34\r\n"
			+ "cut 9594\r\n"
			+ "deal into new stack\r\n"
			+ "cut 5182\r\n"
			+ "deal with increment 72\r\n"
			+ "cut -2630\r\n"
			+ "deal into new stack\r\n"
			+ "cut -9018\r\n"
			+ "deal with increment 45\r\n"
			+ "cut -1069\r\n"
			+ "deal with increment 28\r\n"
			+ "cut 358\r\n"
			+ "deal into new stack\r\n"
			+ "cut -2244";
	static String input2 = "deal into new stack\r\n"
			+ "cut -2\r\n"
			+ "deal with increment 7\r\n"
			+ "cut 8\r\n"
			+ "cut -4\r\n"
			+ "deal with increment 7\r\n"
			+ "cut 3\r\n"
			+ "deal with increment 9\r\n"
			+ "deal with increment 3\r\n"
			+ "cut -1";
	
	public static void main(String[] args) {
		DayRunner.run(new Day22());
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
		for(String s : input.split("\r\n")) {
			String[] words = s.split(" ");
			if(words[0].equals("cut")) {
				//o += (i * Long.parseLong(words[1]));
				o = o.add(i.multiply(new BigInteger(words[1])));
			}
			if(words[1].equals("with")) {
				//i *= modPow(Long.parseLong(words[3]), count - 2, count);
				i = i.multiply(new BigInteger(words[3]).modPow(count.subtract(BigInteger.ONE.add(BigInteger.ONE)), count));
			}
			if(words[1].equals("into")) {
				o = o.subtract(i);
				//o -= i;
				i = i.negate();
				//i *= -1;
			}
			
		}
		//o *= modPow(1-i,count-2,count);
		o = o.multiply(BigInteger.ONE.subtract(i).modPow(count.subtract(BigInteger.ONE.add(BigInteger.ONE)), count));
		//i = modPow(i,shuffles,count)
		i = i.modPow(shuffles, count);
		//return ((position * i) + ((1-i) * o)) % count;
		return position.multiply(i).add(BigInteger.ONE.subtract(i).multiply(o)).mod(count);	
	}

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
}
