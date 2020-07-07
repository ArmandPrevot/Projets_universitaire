package game;

import java.util.Comparator;

/**
 * The Class SortByGlory uses Comparator to compare two Players's glory points.
 */
public class SortByGlory implements Comparator<PlayerManager> {

	/**
	 * Compare two playerManager with the number of glory
	 *
	 * @param o1 the first PlayerManager 
	 * @param o2 the second PlayerManager
	 * @return the difference between player one and player two in Glory Points 
	 */
	@Override
	public int compare(PlayerManager o1, PlayerManager o2) {
		return o2.getInventory().getNbGlory() - o1.getInventory().getNbGlory();
	}

}
