package de.y1337.vortex.logic;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import de.y1337.vortex.R;
import de.y1337.vortex.helpers.Coin;
import de.y1337.vortex.helpers.CoinHelper;
import roboguice.RoboGuice;
import roboguice.inject.InjectResource;

/**
 * @author Vijay Kumar : email kumar.vijay@gmail.com
 * Distributed under Apache 2.0 License
 *
 * The following class contains the final result after running the coin change
 * function.
 */

public class CoinChangeAnswer {
	/**
	 * The optimal solution for i,j where i is the denomination
	 * and j is the current target value.
	 * The final optimal solution is the number of coins required to 
	 * reach the 'target.  The final solution will be the cell with the highest index
	 * eg:- for a target amount of 12 and denomination of 1,6,10 the highest
	 * index will contain the integer value 2 which corresponds to 2 coins 
	 * required to make a target of 12 (using 6,6).
	 */
	public int OPT[][];
	/**
	 * similar to the OPT structure, except that the elements are strings of
	 * change for the i,j optimal solution
	 * eg:- for a target amount of 12 and denomination of 1,6,10 the highest
	 * index will contain the string "6 6". 
	 */
	public String optimalChange[][];
	
	/**
	 * List of all possible solutions for the target
	 */
	public ArrayList<String> allPossibleChanges = new ArrayList<String>();
	
	/**
	 * The target value.
	 */
	private int target;
	
	/**
	 * Copy of the denominations that was used to generate this solution
	 */
	public int[] denoms;

	private CoinHelper helper;

	@InjectResource(R.string.answer_start)
	private String answerStart;

	@InjectResource(R.string.answer_end)
	private String answerEnd;
	
	public CoinChangeAnswer(int target,int[] denoms, Context context) {
		this.target = target;
		this.denoms = denoms;
		optimalChange = new String[denoms.length][target+1];
		OPT = new int[denoms.length][target+1];
		helper = new CoinHelper(optimalChange);
		RoboGuice.getInjector(context).injectMembers(this);
	}
	
	public void printAllPossibleCombos( ) {
		if(allPossibleChanges.size()>0) {
			System.out.println("All possible change(s) Target=" + target + ", Denominations="+denomString());
			int i=1;
			for(String s: allPossibleChanges) {
				System.out.println(i + ") " + s);
				i++;
			}
			System.out.println();
		} else {
			System.out.println("No change for target="+target+ ", Denominations="+denomString());
		}
		
	}
	
	public String getOptimalChange() {
		int i = optimalChange.length;
		int j = optimalChange[0].length;
		helper.generateListOfCoins();
		String str = answerStart + helper.generateAnswer().trim() + answerEnd;
		return str;
	}
	
	public String denomString( ) {
		StringBuilder sb = new StringBuilder();
		for(int i: denoms) {
			sb.append(i + " ");
		}
		return sb.toString();
	}

	public List<Coin> fetchCoins() {
		return helper.getCoinResult();
	}
}
