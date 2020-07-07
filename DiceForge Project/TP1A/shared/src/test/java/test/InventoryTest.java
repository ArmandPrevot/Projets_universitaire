package test;



import card.Card;
import card.Deck;

import client.Inventory;
import face.Face;
import forge.Dice;
import resources.Statistic;
import resources.TypeResource;



import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

// TODO: Auto-generated Javadoc

/**
 * The Class InventoryTest.
 */
public class InventoryTest {
	
	/** Creation of an inventory */
	Inventory i;
	/** Creation of a Dice */
	Dice d;
	/** Creation of a Card	*/
	Card c;
	
	Statistic statInv = Mockito.mock(Statistic.class);
	/**
	 * Set up Mockito.
	 */
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		i = new Inventory();
		i.linkStats(statInv);
		d = new Dice();
		c = new Card(Deck.CHEST, Deck.values()[2].getName(), Deck.values()[2].getGlory(),Deck.values()[2].getiE() ,(Deck.values()[2].getCosts()));

	}
	/**
	 * Test the method addRessourcesByType() and the getter with GLORY.
	 */
	@Test
	public void addRessourcesByTypeTest() {
		i.addRessourcesByType(TypeResource.GLORY, 5);
		i.addRessourcesByType(TypeResource.GOLD, 10);
		i.addRessourcesByType(TypeResource.LUNAR, 6);
		i.addRessourcesByType(TypeResource.SOLAR, 4);
		assertEquals(5, i.getNbGlory());
		assertEquals(10, i.getNbrGold());
		assertEquals(6, i.getNbrLunar());
		assertEquals(4, i.getNbrSolar());
	}
	
	/**
	 * Test the method addRessources() and the getter with GOLD.
	 */
	@Test
	public void addRessources() {
		Face[] f = new Face[4];
		f[0]= new Face (12, TypeResource.GOLD);
		f[1]= new Face (50, TypeResource.GLORY);
		f[2]= new Face (5, TypeResource.LUNAR);
		f[3]= new Face (3, TypeResource.SOLAR);
		i.addRessources(f);
		assertEquals(50, i.getNbGlory());
		assertEquals(12, i.getNbrGold());
		assertEquals(5, i.getNbrLunar());
		assertEquals(3, i.getNbrSolar());
	}
	
	
	/**
	 * Test the method removeRessourcesTest().
	 */
	@Test
	public void removeRessourcesTest() {
		i.addRessourcesByType(TypeResource.LUNAR, 6);
		i.removeRessources(TypeResource.LUNAR, 2);
		assertEquals(4, i.getNbrLunar());
		
	}
	
	/**
	 * Test the set accessor for glory
	 */
	@Test
	public void setNbrGloryTest() {
		i.setNbGlory(13);
		assertEquals(13, i.getNbGlory());
	}
	
	/**
	 * Test of addCard
	 */
	@Test
	public void addCardTest() {
		i.addCard(c);
		assertEquals(true, i.getMyCards().contains(c));
	}
	/**
	 * Test the accessor or d1
	 * note : On teste les deux accesseurs en même temps car pour tester set il faut utiliser get etc...
	 */
	@Test
	public void accD1Test() {
		d = new Dice();
		i.setD1(d);
		assertEquals(d, i.getD1());
	}
	/**
	 * Test the accessor or d2
	 */
	@Test
	public void accD2Test() {
		i.setD2(d);
		assertEquals(d, i.getD2());
	}
}
