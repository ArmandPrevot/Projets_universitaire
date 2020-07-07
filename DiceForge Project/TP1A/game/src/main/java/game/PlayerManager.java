package game;

import java.util.ArrayList;
import java.util.List;
import client.Inventory;
import client.Player;
import event.ChoiceFace;
import face.Face;
import face.HybridFace;
import interfaces.IGameManager;
import interfaces.IPlayerManager;
import resources.Statistic;
 
/**
 * The Class PlayerManager handles a player and its inventory.
 */
public class PlayerManager implements IPlayerManager {

	/** The inventory of the Player */
	private Inventory i;

	/** The player */
	private Player p;

	/** The Rank of the Player */
	private int rank;

	/** The interface representing the GameManager, use for sending the choiceFace event */
	private IGameManager g;

	/** The HandlerChoiceFace handles the choiceFace event */
	private HandlerChoiceFace handler;

	/** The stats represents the statistics of a PlayerManager, the link between a player and it's inventory */
	private Statistic stats;

	/**
	 * Instantiates a new player manager.
	 * Initialize stats attribute and link it with GameManager stats
	 *	
	 * @param player the player
	 * @param g1 the gameManager interface	
	 */

	public PlayerManager(Player player, IGameManager g1) {
		this.p = player;
		this.setStats(g1.getPlayerStats(this.p.getId()));
		this.rank = 0;
		this.i = new Inventory();
		this.i.linkStats(g1.getPlayerStats(this.p.getId()));
		this.g = g1;
	}

	/**
	 * rollDices() rolls the two dices of the player from its inventory
	 * if the face on which the dice falls is a selectable face, 
	 * launches the ChoiceFace Event through HandlerChoiceFace,
	 * in order to ask the player which face he wants
	 */
	public void rollDices() {
		int dice1 = this.i.rollDice1();
		int dice2 = this.i.rollDice2();
		List<Face> faces = new ArrayList<>();

		faces.add(i.getD1().getFaceByNumF(dice1));
		faces.add(i.getD2().getFaceByNumF(dice2));


		for(Face f : faces) {
			if(f instanceof HybridFace) {

				HybridFace hyF = (HybridFace) f;

				if(hyF.isSelectable()) {
					this.handler = new HandlerChoiceFace(hyF, p.getId(), g, this);
					this.handler.execute();
					this.handler = null;
				}
				else {
					i.addRessources(hyF);
				}	
			}
			else {
				i.addRessources(f);
			}
		}

		Display.getInstance().printRollDices(this.i, dice1, dice2, this.p.getName());		


	}

	/**
	 * If the handler is set (set when the face fallen in rollDice is a choice face)
	 * Throws to the handler 
	 * 
	 * @param chF the choiceFace object
	 */
	public void handleChoiceFace(ChoiceFace chF) {
		if(this.handler != null) {
			this.handler.handleChoiceFace(chF);
		}
	}

	/**
	 * Rank getter, return the player's rank
	 * @return the rank
	 */
	public int getRank() {
		return rank;
	}

	/**
	 * Rank setter, set the new player's rank
	 * @param rank the rank to set
	 */
	public void setRank(int rank) {
		this.rank = rank;
	}

	/**
	 * Gets the player.
	 *
	 * @return the player
	 */
	public Player getPlayer() {
		return this.p;
	}

	/**
	 * Gets the inventory.
	 *
	 * @return the inventory
	 */
	public Inventory getInventory() {
		return this.i;
	}
	
	/**
	 * Gets the statistics
	 * 
	 * @return the statistics
	 */
	public Statistic getStats() {
		return stats;
	}
	
	/**
	 * Sets the statistics
	 * 
	 * @param stats the statistics to set
	 */
	public void setStats(Statistic stats) {
		this.stats = stats;
	}	

}
