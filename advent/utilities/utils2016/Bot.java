package advent.utilities.utils2016;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Bot {

	public static ArrayList<Bot> bots = new ArrayList<Bot>();
	public static HashMap<Integer,Integer> outputBins = new HashMap<Integer,Integer>();
	
	public int id;
	public ArrayList<Integer> buffer;
	
	int destLow;
	boolean destLowBot;
	
	int destHigh;
	boolean destHighBot;
	
	public Bot(int id, int destLow, boolean destLowBot, int destHigh, boolean destHighBot) {
		buffer = new ArrayList<Integer>();
		this.id = id;
		this.destLow = destLow;
		this.destLowBot = destLowBot;
		this.destHigh = destHigh;
		this.destHighBot = destHighBot;
	}
	
	public void pushValues() {
		if(buffer.size() != 2) {
			//this shouldnt be pushing, print error message and cancel
			System.out.println("Error! Non-full bot pushing!");
			return;
		}
		//calculate high and low from buffer
		int high = Collections.max(buffer);
		int low = Collections.min(buffer);
		
		//clear buffer
		buffer.clear();
		
		//if dest is true, push to corresponding bot
		if(destLowBot) {
			find(destLow).buffer.add(low);
		} else {
		//otherwise, push to corresponding output bin
			outputBins.put(destLow, low);
		}
		
		if(destHighBot) {
			find(destHigh).buffer.add(high);
		} else {
			outputBins.put(destHigh, high);
		}
	}
	
	public static Bot find(int searchId) {
		for(Bot b : bots) {
			if(b.id == searchId) {
				return b;
			}
		}
		return null;
	}
}
