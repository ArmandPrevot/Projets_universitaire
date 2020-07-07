package client;

import java.util.ArrayList;
import java.util.List;

import card.Card;
import face.Face;
import face.SimpleFace;
import forge.Dice;
import resources.InventoryStats;
import resources.Statistic;
import resources.TypeResource;

/**
 * The Class Inventory represents the resources of a player.
 */
public class Inventory {
	
	/** The number of gold of a Player. */
	private int nbrGold;
	
	/** The number of glory of a Player . */
	private int nbrGlory;
	
	/** Integer that represent the player's amount of lunar point */
	private int nbrLunar;
	
	/** Integer that represent the player's amount of solar point */
	private int nbrSolar;
	
	/** The d1 is the dice 1 of the Player. */
	private Dice d1 = new Dice();
	
	/** The d2 is the dice 2 of the Player  */
	private Dice d2 = new Dice();
	
	/** List that represent the player's card list */
	private List<Card> myCards;
	
	/** The statInv stocks statistic over the inventory */
	private InventoryStats statInv;
	
	/**
	 * Instantiates a new inventory.
	 * D2 has a Glory face at the beginning
	 */
	public Inventory() {
		this.nbrGlory = 0;
		this.nbrGold = 0;
		this.nbrLunar = 0;
		this.nbrSolar = 0;
		this.myCards = new ArrayList<>();
		this.getD2().setFace(5, new Face(2, TypeResource.GLORY, SimpleFace.GLORY2.getPrice()));
	}
	
	
	/**
	 * Sets a face on a dice
	 * @param f the face to set
	 * @param randD represents on which dice the face will be set (0,1)
	 * @param randF represents the position of the face onto the dice (0-5)
	 */
	public void setFaceOnDice(Face f, int randD, int randF) {
		if(randD == 1) {
			this.getD1().setFace(randF, f);
		}
		else {
			this.getD2().setFace(randF, f);
		}
		this.statInv.setForgedFaces(this.statInv.getForgedFaces() + 1);
	}
	
	
	/** 
	 * Links the StatInventory object of Inventory with the GameManager one, 
	 * in order to links the references. It's through linked up references that we fetch statistics. 
	 * 
	 * @param invStats the statistic object to link up to
	 * */
	public void linkStats(Statistic invStats) {
		if(invStats.getInventoryStats() == null) {
			this.statInv = new InventoryStats(this);
			invStats.setInventoryStats(this.statInv);
		}
		else {
			invStats.getInventoryStats().updateLink(this);
			this.statInv = invStats.getInventoryStats();
		}
	}
	
	/**
	 * Add a card to the player's card list
	 * @param c the Card to add
	 */
	public void addCard(Card c) {
		this.myCards.add(c);
		this.statInv.incNbCards(c.getD());
	}
	
	/**
	 * Increase the number of wins of a player
	 */
	public void addVictory() {
		this.statInv.setWinsNbr(this.statInv.getWinsNbr()+1);
	}
	
	/**
	 * Increase the number of equality cases of a player
	 */
	public void addEquality() {
		this.statInv.setEqualityNbr(this.statInv.getEqualityNbr()+1);
		
	}
	
	/**
	 * To string of the inventory.
	 *
	 * @param name the name of the player
	 * @return the entire inventory
	 */
	public String toString(String name) {
		return("Inventaire de " + name + " : \n" + "\tOR : " + nbrGold + " || GLOIRE : " + nbrGlory + " || LUNAIRE " + nbrLunar +" || SOLAIRE " + nbrSolar + "\n\t" +
				"Dé 1 : " + this.getD1().toString() + "\n\tDé 2 : " + this.getD2().toString() + "\n\t Cartes : " + this.myCards.toString() + "\n");
	}
	
	/**
	 * Adds the resources to the inventory.
	 *
	 * @param f the face containing the resource to add
	 * 
	 */
	public void addRessources(Face ... f) {
		for(Face fc : f) {
			if(fc.getTypeF() == TypeResource.GOLD && (fc.getValue() + this.nbrGold) <= 12) {
				this.nbrGold += fc.getValue();
				this.statInv.setGold(this.statInv.getGold() + fc.getValue());
			}
			if(fc.getTypeF() == TypeResource.SOLAR && (fc.getValue() + this.nbrSolar) <= 6) {
				this.nbrSolar += fc.getValue();
				this.statInv.setSolar(this.statInv.getSolar() + fc.getValue());
			}
			if(fc.getTypeF() == TypeResource.LUNAR && (fc.getValue() + this.nbrLunar) <= 6) {
				this.nbrLunar += fc.getValue();
				this.statInv.setLunar(this.statInv.getLunar() + fc.getValue());
			}
			if(fc.getTypeF() == TypeResource.GLORY) {
				this.nbrGlory += fc.getValue();
				this.statInv.setGlory(this.statInv.getGlory() + fc.getValue());
			}
		}
	}
	
	/**
	 * Add resources using a TypeResource and a value
	 * 
	 * @param type of resources we're adding
	 * @param value is the number that we increase the resource with
	 */
	public void addRessourcesByType(TypeResource type, int value) {
		if(type == TypeResource.GOLD && (value + this.nbrGold) <= 12) {
			this.nbrGold += value;
			this.statInv.setGold(this.statInv.getGold() + value);
		}
		if(type == TypeResource.SOLAR && (value + this.nbrSolar) <= 6) {
			this.nbrSolar += value;
			this.statInv.setSolar(this.statInv.getSolar() + value);
		}
		if(type == TypeResource.LUNAR && (value + this.nbrLunar) <= 6) {
			this.nbrLunar += value;
			this.statInv.setLunar(this.statInv.getLunar() + value);
		}
		if(type == TypeResource.GLORY) {
			this.nbrGlory += value;
			this.statInv.setGlory(this.statInv.getGlory() + value);
		}
	}

	/**
	 * Removes the resource of the inventory.
	 *
	 * @param type the type of the resources to remove
	 * @param nbr the number of resources to remove
	 */
	public void removeRessources(TypeResource type, int nbr) {
		switch (type) {
			case GOLD :
				this.nbrGold -= nbr;
				this.statInv.setGold(this.statInv.getGold() - nbr);
				break;
			case GLORY:
				this.nbrGlory -= nbr;
				this.statInv.setGlory(this.statInv.getGlory() - nbr);
				break;
			case LUNAR:
				this.nbrLunar -= nbr;
				this.statInv.setLunar(this.statInv.getLunar() - nbr);
				break;
			case SOLAR:
				this.nbrSolar -= nbr;
				this.statInv.setSolar(this.statInv.getSolar() - nbr);
				break;
			default:
				break;
		}
				
	}
	
	/**
	 * Rolls the dice 1.
	 *
	 * @return a random integer that represents a face
	 */
	public int rollDice1() {
		return this.getD1().rollDice();
	}
	
	/**
	 * Rolls the dice 2.
	 *
	 * @return a random integer that represents a face
	 */
	public int rollDice2() {
		return this.getD2().rollDice();
	}
	
	
	/**
	 * Return the amount of gold of a player
	 * @return nbrGold the amount of gold
	 */
	public int getNbrGold() {
		return nbrGold;
	}
	
	/**
	 * Set the number of gold
	 * @param nbrGold the new amount of gold
	 */
	public void setNbrGold(int nbrGold) {
		this.nbrGold = nbrGold;
	}

	/**
	 * Return the player's amount of lunar point
	 * @return nbrLunar the amount of lunar point
	 */
	public int getNbrLunar() {
		return nbrLunar;
	}

	/**
	 * Set the number of lunar point
	 * @param nbrLunar the new amount of lunar point
	 */
	public void setNbrLunar(int nbrLunar) {
		this.nbrLunar = nbrLunar;
	}

	/**
	 * Return the player's amount of solar point
	 * @return nbrSolar the amount of solar point
	 */
	public int getNbrSolar() {
		return nbrSolar;
	}

	/**
	 * Set the number of solar point
	 * @param nbrSolar the new amount of solar point
	 */
	public void setNbrSolar(int nbrSolar) {
		this.nbrSolar = nbrSolar;
	}
	
	
	/**
	 * Gets the number of glory.
	 *
	 * @return the number glory
	 */
	public int getNbGlory() {return this.nbrGlory;}

	/**
	 * Sets the number of glory.
	 * 
	 * @param nbrGlory the number of glory to set 
	 */
	public void setNbGlory(int nbrGlory) { this.nbrGlory = nbrGlory;}
	
	/**
	 * Return the player's first dice
	 * @return d1 the first dice
	 */
	public Dice getD1() {
		return d1;
	}

	/**
	 * Set the player's first dice
	 * @param d1 the new first dice
	 */
	public void setD1(Dice d1) {
		this.d1 = d1;
	}

	/**
	 * Return the palyer's second dice
	 * @return d2 the second dice
	 */
	public Dice getD2() {
		return d2;
	}

	/**
	 * Set the player's second dice
	 * @param d2 the new second dice
	 */
	public void setD2(Dice d2) {
		this.d2 = d2;
	}
	/**
	 * Return the Card's List of the inventory
	 * @return myCards the card list
	 */
	public List<Card> getMyCards() {
		return myCards;
	}
	
}
