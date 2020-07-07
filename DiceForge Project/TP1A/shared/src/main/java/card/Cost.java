package card;

import resources.TypeResource;
/**
 * 
 * The class used in Card to represent a card cost
 *
 */
public class Cost {
	
	/**
	 * Initialize Cost for serializing
	 */
	public Cost() {};
	
	/** TypeRessource that represent the type of resource of the cost */
	private TypeResource type;
	
	/** Integer that represents the amount of TypeRessource */
	private int value;
	
	/**
	 * Initiate a new Cost
	 * @param v the value of the resource
	 * @param t the type of the resource
	 */

	public Cost(int v, TypeResource t) {
		this.value = v;
		this.type = t;
	}

	/**
	 * Return the TypeRessource of the cost
	 * @return type the cost's TypeRessource
	 */
	public TypeResource getType() {
		return type;
	}

	/**
	 * Set the cost's TypeRessource
	 * @param type the new cost's TypeRessource
	 */
	public void setType(TypeResource type) {
		this.type = type;
	}

	/**
	 * Return the cost's value
	 * @return value the cost's value
	 */
	public int getValue() {
		return value;
	}

	/**
	 * Set the cost's value
	 * @param value the new cost's value
	 */
	public void setValue(int value) {
		this.value = value;
	}
}
