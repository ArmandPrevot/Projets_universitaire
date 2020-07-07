package resources;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import card.Card;
import card.Deck;
import client.Inventory;
import forge.Dice;

/**
 * The Class InventoryStats represents all final game statistics
 */
public class InventoryStats {

	/** The gold number earned in multiple games */
	private int gold;
	
	/** The solar number earned in multiple games */
	private int solar;
	
	/** The lunar number earned in multiple games */
	private int lunar;
	
	/** The glory number earned in multiple games */
	private int glory;
	
	/** The list of Dices bought in multiple games */
	private List<Dice> savedDices;
	
	/** The list of Cards earned in multiple games */
	private List<Card> savedCards;
	
	/** The number of faces forged in multiple games */
	private int forgedFaces = 0;
	
	/** Number of occurrences per card per player in multiple games */
	private Map<Deck, Integer> occurrencesOfCards;
	
	/** Number of victories in multiple games */
	private int winsNbr = 0;
	
	/** The number of equality cases in multiple games */
	private int equalityNbr = 0;
	
	/** The wins percentage in multiple games. */
	private int winsPercentage = 0;
	
	/** The equality percentage in multiple games */
	private int equalityPercentage = 0;
	
	/** First dice of a player in one game */
	private Dice d1;
	
	/** Second dice of a player in one game */
	private Dice d2;

	/** List of cards of a player in one game */
	private List<Card> myCards;
	
	/**
	 * Instantiates a new inventory statistic object.
	 *
	 * @param i the inventory linked to it's stats
	 */
	public InventoryStats(Inventory i) {
		this.gold = 0;
		this.solar = 0;
		this.lunar = 0;
		this.glory = 0;
		this.savedCards = new ArrayList<>();
		this.savedDices = new ArrayList<>();
		this.d1 = i.getD1();
		this.d2 = i.getD2();
		this.myCards = i.getMyCards();
		this.occurrencesOfCards = new HashMap<>();
	}
	
	/**
	 * Update link. Links the cards and dices with the newly created inventory at a start of a new game.
	 * 
	 * @param i the inventory to link with
	 */
	public void updateLink(Inventory i) {
		this.savedDices.add(this.d1);
		this.savedDices.add(this.d2);
		
		this.savedCards.addAll(this.myCards);
		
		this.d1 = i.getD1();
		this.d2 = i.getD2();
		this.myCards = i.getMyCards();	
	}
	
	/**
	 * Generic method useful for adding in a HashMap with a counter.
	 * 
	 * Used to store cards with the number of occurrences.
	 *
	 * @param <T> the generic type
	 * @param map the map
	 * @param oKey the o key
	 */
	private <T> void incValueMapObject(Map<T, Integer> map, T oKey){
        map.putIfAbsent(oKey,0);
        map.put(oKey,map.get(oKey)+1);
    }
	
	/**
	 * Adds a card and increments the counter of occurrences
	 *
	 * @param d the deck represents the card's ID 
	 */
	public void incNbCards(Deck d) {
		this.incValueMapObject(this.occurrencesOfCards, d);
	}
	
	/**
	 * Calculates the percentage rate of winning and equality cases.
	 *
	 * @param gameNbr the game number
	 */
	public void percentageRate(int gameNbr) {
		this.winsPercentage = (this.winsNbr*100)/gameNbr;
		this.equalityPercentage = (this.equalityNbr*100/gameNbr);
	}
	
	/**
	 * Gets the wins percentage.
	 *
	 * @return the wins percentage
	 */
	public int getWinsPercentage() {
		return winsPercentage;
	}

	/**
	 * Sets the wins percentage.
	 *
	 * @param winsPercentage the new wins percentage
	 */
	public void setWinsPercentage(int winsPercentage) {
		this.winsPercentage = winsPercentage;
	}

	/**
	 * Gets the equality percentage.
	 *
	 * @return the equality percentage
	 */
	public int getEqualityPercentage() {
		return equalityPercentage;
	}

	/**
	 * Sets the equality percentage.
	 *
	 * @param equalityPercentage the new equality percentage
	 */
	public void setEqualityPercentage(int equalityPercentage) {
		this.equalityPercentage = equalityPercentage;
	}

	/**
	 * Gets the equality number.
	 *
	 * @return the equality number
	 */
	public int getEqualityNbr() {
		return equalityNbr;
	}

	/**
	 * Sets the equality number.
	 *
	 * @param equalityNbr the new equality number
	 */
	public void setEqualityNbr(int equalityNbr) {
		this.equalityNbr = equalityNbr;
	}

	/**
	 * Gets the wins number.
	 *
	 * @return the wins number
	 */
	public int getWinsNbr() {
		return winsNbr;
	}

	/**
	 * Sets the wins number.
	 *
	 * @param winsNbr the new wins number
	 */
	public void setWinsNbr(int winsNbr) {
		this.winsNbr = winsNbr;
	}

	/**
	 * Gets the occurrences of cards.
	 *
	 * @return the occurrences of cards
	 */
	public Map<Deck, Integer> getOccurencesOfCards() {
		return occurrencesOfCards;
	}

	/**
	 * Sets the occurrences of cards.
	 *
	 * @param occurrencesOfCards the occurrences of cards
	 */
	public void setOccurencesOfCards(Map<Deck, Integer> occurrencesOfCards) {
		this.occurrencesOfCards = occurrencesOfCards;
	}

	/**
	 * Gets the forged faces.
	 *
	 * @return the forged faces
	 */
	public int getForgedFaces() {
		return forgedFaces;
	}

	/**
	 * Sets the forged faces.
	 *
	 * @param forgedFaces the new forged faces
	 */
	public void setForgedFaces(int forgedFaces) {
		this.forgedFaces = forgedFaces;
	}

	
	/**
	 * Gets the saved dices.
	 *
	 * @return the saved dices
	 */
	public List<Dice> getSavedDices() {
		return savedDices;
	}

	/**
	 * Sets the saved dices.
	 *
	 * @param savedDices the new saved dices
	 */
	public void setSavedDices(List<Dice> savedDices) {
		this.savedDices = savedDices;
	}

	/**
	 * Gets the d1.
	 *
	 * @return the d1
	 */
	public Dice getD1() {
		return d1;
	}

	/**
	 * Sets the d1.
	 *
	 * @param d1 the new d1
	 */
	public void setD1(Dice d1) {
		this.d1 = d1;
	}

	/**
	 * Gets the d2.
	 *
	 * @return the d2
	 */
	public Dice getD2() {
		return d2;
	}

	/**
	 * Sets the d2.
	 *
	 * @param d2 the new d2
	 */
	public void setD2(Dice d2) {
		this.d2 = d2;
	}

	/**
	 * Gets the gold.
	 *
	 * @return the gold
	 */
	public int getGold() {
		return gold;
	}

	/**
	 * Sets the gold.
	 *
	 * @param gold the new gold
	 */
	public void setGold(int gold) {
		this.gold = gold;
	}

	/**
	 * Gets the saved cards.
	 *
	 * @return the saved cards
	 */
	public List<Card> getSavedCards() {
		return savedCards;
	}

	/**
	 * Sets the saved cards.
	 *
	 * @param savedCards the new saved cards
	 */
	public void setSavedCards(List<Card> savedCards) {
		this.savedCards = savedCards;
	}

	/**
	 * Gets the solar points
	 *
	 * @return the solar
	 */
	public int getSolar() {
		return solar;
	}

	/**
	 * Sets the solar points
	 *
	 * @param solar the new solar
	 */
	public void setSolar(int solar) {
		this.solar = solar;
	}

	/**
	 * Gets the lunar points
	 *
	 * @return the lunar
	 */
	public int getLunar() {
		return lunar;
	}

	/**
	 * Sets the lunar points
	 *
	 * @param lunar the new lunar points
	 */
	public void setLunar(int lunar) {
		this.lunar = lunar;
	}

	/**
	 * Gets the glory points
	 *
	 * @return the glory points
	 */
	public int getGlory() {
		return glory;
	}

	/**
	 * Sets the glory points
	 *
	 * @param glory the new glory points
	 */
	public void setGlory(int glory) {
		this.glory = glory;
	}


	/**
	 * Gets list of cards
	 *
	 * @return the cards
	 */
	public List<Card> getMyCards() {
		return myCards;
	}

	/**
	 * Sets the list of cards.
	 *
	 * @param myCards the new list of cards
	 */
	public void setMyCards(List<Card> myCards) {
		this.myCards = myCards;
	}
	
}
