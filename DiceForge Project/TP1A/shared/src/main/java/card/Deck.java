package card;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import resources.TypeResource;
/**
 * Enum of the cards available in the game
 *
 */
public enum Deck {
	/**
	 * Enum of the different cards
	 */
	HAMMER("The Blacksmith's Hammer", 0, 0, ImmediateEffect.NONE, new Cost(1, TypeResource.LUNAR)), /** Name, glory, value, id, type **/
	CHEST("Chest", 2, 1, ImmediateEffect.NONE, new Cost(1, TypeResource.LUNAR)),
	BEAR("The Great Bear", 2, 2, ImmediateEffect.INSTANT_LUNAR, new Cost(2, TypeResource.LUNAR)),
	DOE("The Silver Hind", 2, 3, ImmediateEffect.INSTANT_LUNAR, new Cost(2, TypeResource.LUNAR)),
	BOAR("Tenacious Boar", 4, 4, ImmediateEffect.INSTANT_SOLAR, new Cost(3, TypeResource.LUNAR)),
	CERBERUS("Cerberus", 6, 5, ImmediateEffect.INSTANT_SOLAR,  new Cost(4, TypeResource.LUNAR)),
	
	GRASS("Wild Spirits", 3, 6, ImmediateEffect.INSTANT_GOLD, new Cost(1, TypeResource.SOLAR)),
	OWL("The Guardian Owl", 1, 7, ImmediateEffect.INSTANT_LUNAR, new Cost(2, TypeResource.SOLAR)),
	SHIP("Celestial Ship", 2, 8, ImmediateEffect.INSTANT_GOLD, new Cost(2, TypeResource.SOLAR)),
	MINOTAURE("Minotaur", 3, 9, ImmediateEffect.INSTANT_GOLD, new Cost(3, TypeResource.SOLAR)),
	SHIELD("The Guardian Shield", 5, 10, ImmediateEffect.INSTANT_SOLAR, new Cost(3, TypeResource.SOLAR)),
	MEDUSA("Gorgon", 5, 11, ImmediateEffect.NONE, new Cost(4, TypeResource.SOLAR));
	
	
	/**
	 * Return the card's id
	 * @return id the card id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Set the id of a card
	 * @param id the new card id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Return the list of cost
	 * @return costs the cost list
	 */
	public List<Cost> getCosts() {
		return costs;
	}

	/**
	 * Set the cost list
	 * @param costs the new cost list
	 */
	public void setCosts(List<Cost> costs) {
		this.costs = costs;
	}

	/** String that represent the card name */
	private String name;
	
	/** Integer that represent the number of glory given by the card */
	private int glory;
	
	/** Integer that represent the id of a card */
	private int id;
	
	/** List that represent the cost list of the card */
	private List<Cost> costs;
	
	/** Immediate effect of the card */
	private ImmediateEffect iE;
	
	/**
	 * Initiate a new deck
	 */
	Deck(String n, int g, int id, ImmediateEffect ie, Cost... c) {
		this.name = n;
		this.glory = g;
		this.id = id;
		this.costs = Arrays.asList(c);
		this.iE = ie;
	}

	/**
	 * Return the card's name
	 * 
	 * @return the card's name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set the card name
	 * @param name the new card name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Return the glory
	 * @return glory the card's number of glory
	 */
	public int getGlory() {
		return glory;
	}

	/**
	 * Set the card's glory
	 * @param glory the new card's number of glory
	 */
	public void setGlory(int glory) {
		this.glory = glory;
	}

	/**
	 * 
	 * @return the immediate effect of the card
	 */
	public ImmediateEffect getiE() {
		return iE;
	}
}