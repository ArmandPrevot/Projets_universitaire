package card;

import java.util.ArrayList;
import java.util.List;
/**
 * The class that containt the different cards
 *
 */
public class Island {
	
	/* List<Card> that represent a list of available cards */
	private List<Card> cardsAvailable;
	
	/**
	 * Initiate a new Island
	 */
	public Island() {
		this.cardsAvailable = new ArrayList<>();
	}

	/**
	 * Return the island's card list
	 * @return cardAvailable the island's available cards list
	 */
	public List<Card> getCardsAvailable() {
		return cardsAvailable;
	}

	/**
	 * Set the island's available cards list
	 * @param cardsAvailable the new island's available cards list
	 */
	public void setCardsAvailable(List<Card> cardsAvailable) {
		this.cardsAvailable = cardsAvailable;
	}
	
	/**
	 * Add a card to the island's list
	 * @param c a Card to add
	 */
	public void addCard(Card c) {
		cardsAvailable.add(c);
	}
	
	/**
	 * Remove a Card from the island's card list
	 * @param c a Card to remove
	 */
	public void removeCard(Card c) {
		cardsAvailable.remove(c);
	}
	
	/**
	 * Display the available card list
	 * @return a string that contain the availables cards
	 */
	public String toString() {
		return this.cardsAvailable.toString();
	}

}
