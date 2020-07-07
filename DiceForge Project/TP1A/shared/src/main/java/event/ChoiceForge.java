package event;

import java.util.UUID;

import forge.Dice;
import forge.Forge;

 
/**
 * The Class ChoiceForge.
 */
public class ChoiceForge {
	
	/** Class uses for transmitting data on Choice Forge Event. */
	
	private Forge forge;
	
	/** The index F represents which Face will be chosen into the Forge . */
	private int indexFace;
	
	/** The index B represents which Basin will be chosen into the Forge . */
	private int indexBassin;
	
	/** The d1 and the d2, represents the dices of a Player. */
	private Dice d1, d2;
	
	/** The gold amount of a Player. */
	private int goldAmount;
	
	/** The randDice determines on which dice the face bought will be set. */
	private int randDice;
	
	/** The randFace determines on which face of the dice the face bought will be set. */
	private int randFace;
	
	/** The id of the Player. */
	private UUID id;
	
	
	/**Return the forge
	 * @return the forge
	 */
	public Forge getForge() {
		return forge;
	}


	/**Set the Forge
	 * @param f the forge to set
	 */
	public void setForge(Forge f) {
		this.forge = f;
	}


	/**Return the index of the Face
	 * @return the indexF
	 */
	public int getIndexFace() {
		return indexFace;
	}


	/**Set the index of the Face
	 * @param indexF the indexF to set
	 */
	public void setIndexFace(int indexF) {
		this.indexFace = indexF;
	}


	/**Return the index of the Bassin
	 * @return the indexB
	 */
	public int getIndexBassin() {
		return indexBassin;
	}


	/**Set the index of the Bassin
	 * @param indexB the indexB to set
	 */
	public void setIndexBassin(int indexB) {
		this.indexBassin = indexB;
	}


	/**Return the first dice 
	 * @return the d1
	 */
	public Dice getD1() {
		return d1;
	}


	/**Set the first dice 
	 * @param d1 the first dice  to set
	 */
	public void setD1(Dice d1) {
		this.d1 = d1;
	}


	/**Return the second dice
	 * @return the d2
	 */
	public Dice getD2() {
		return d2;
	}


	/**Set the second dice
	 * @param d2 the second dice to set
	 */
	public void setD2(Dice d2) {
		this.d2 = d2;
	}


	/**Return the gold amount of player
	 * @return the goldAmount
	 */
	public int getGoldAmount() {
		return goldAmount;
	}


	/**Set the gold amount of player
	 * @param goldAmount the goldAmount to set
	 */
	public void setGoldAmount(int goldAmount) {
		this.goldAmount = goldAmount;
	}


	/**Return witch dice the new face is fixed
	 * @return the randDice
	 */
	public int getRandDice() {
		return randDice;
	}


	/**Set on witch dice the new face is fixed
	 * @param randDice the randDice to set
	 */
	public void setRandDice(int randDice) {
		this.randDice = randDice;
	}


	/**Return on witch face of the dice the new face is fixed
	 * @return the randFace
	 */
	public int getRandFace() {
		return randFace;
	}


	/**Set on witch face of the dice the new face is fixed
	 * @param randFace the randFace to set
	 */
	public void setRandFace(int randFace) {
		this.randFace = randFace;
	}


	/**Return the id of the Player
	 * @return the id
	 */
	public UUID getId() {
		return id;
	}


	/**Set the id of the Player
	 * @param id the id to set
	 */
	public void setId(UUID id) {
		this.id = id;
	}


	/**
	 * Instantiates a new choice forge.
	 * For serializing
	 */
	public ChoiceForge() {
	}
	
	
}
