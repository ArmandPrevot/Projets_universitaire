package card;

import java.util.List;

/**
 * The class card to manage the game's cards
 *
 */
public class Card {
	/** List that represent the cost list of the card */
	private List<Cost> cost;
	
	/** Integer that represent the number of glory of the card*/
	private int nbrGlory;
	
	/** String that represent the name's card*/
	 private String name;
	
	/** Variable that represent the immediate effect of the card (NONE if the card doesn't have an effect) */
	private ImmediateEffect iE;
	
	private Deck d;

	/**
	 * Initiate a new Card
	 * 
	 * @param n the name of a card
	 * @param glory the number of glory of a card
	 * @param ie the effect of a card
	 * @param c the cost list of a card
	 * @param deck the id
	 */
	public Card(Deck deck, String n, int glory, ImmediateEffect ie ,List<Cost> c) {
		this.d = deck;
		this.cost = c;
		this.nbrGlory = glory;
		this.name = n;
		this.iE = ie;
	}
	
	public Card() {};
	
	public Deck getD() {
		return d;
	}

	public void setD(Deck d) {
		this.d = d;
	}

	/**
	 * Return the cost list
	 * @return cost the cost list
	 */
	public List<Cost> getCost() {
		return cost;
	}

	/**
	 * Set the cost list
	 * @param cost the new list Cost
	 */
	public void setCost(List<Cost> cost) {
		this.cost = cost;
	}

	/**
	 * Return the number of glory of the card
	 * @return nbrGlory the number of glory
	 */
	public int getNbrGlory() {
		return nbrGlory;
	}

	/**
	 * Set nbrGlory the number of glory of the card
	 * @param nbrGlory the new nbrGlory
	 */
	public void setNbrGlory(int nbrGlory) {
		this.nbrGlory = nbrGlory;
	}

	/**
	 * Return the card's name
	 * @return name the card's name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set the card's name
	 * @param name the new card's name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Set the immediate effect of card
	 * @param iE the effect that'll be set
	 */
	public void setiE(ImmediateEffect iE) {
		this.iE = iE;
	}
	
	/**
	 * Gets the effect a card
	 * @return the immediate effect of the card
	 */
	public ImmediateEffect getiE() {
		return iE;
	}
	
	/**
	 * Prints the name of a card
	 */
	public String toString() {
		return this.name;
	}
	
}
