package resources;

import java.util.ArrayList;
import java.util.List;

/**
 * The Statistic class contains final statistics of multiples games in a row.
 * It also makes the link with (GameManager statistics object - InventoryStats)
 */

public class Statistic {
	
	/**
	 * Represents statistics on player's inventory
	 */
	private InventoryStats statsInv;
	
	/**
	 * Instantiates a new Statistic object,
	 * For parsing reasons.
	 */
	public Statistic() {}
	
	/**
	 * Gets the player's inventory stats 
	 * @return the player's inventory stats 
	 */
	public InventoryStats getInventoryStats() {
		return this.statsInv;
	}
	
	/**
	 * Sets the player's inventory stats 
	 * @param invStats the inventory's stats to set 
	 */
	public void setInventoryStats(InventoryStats invStats) {
		this.statsInv = invStats;
	}
	
}
