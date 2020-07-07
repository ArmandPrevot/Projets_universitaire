package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import card.Card;
import card.Cost;
import card.Deck;
import card.ImmediateEffect;
import resources.TypeResource;

/**
 * 
 * The class CardTest.
 *
 */
public class CardTest {

	/** Create new Card **/
	Card c;
	
	List<Cost> list;
	
	/**
	 * Setup the card
	 */
	@BeforeEach
	public void setUp() {
		c = new Card(Deck.HAMMER, "The Blacksmith's Hammer", 0, ImmediateEffect.NONE, Arrays.asList(new Cost(1, TypeResource.LUNAR)));
		list = new ArrayList<>();
	}
	
	/**
	 * The accessor of the list of Cost
	 */
	@Test
	public void acclistCostCard() {
		list.add(new Cost(1, TypeResource.SOLAR));
		c.setCost(list);
		assertEquals(list, c.getCost());
	}
	
	/**
	 * The accessor of number of Glory
	 */
	@Test
	public void accNbrGloryCard() {
		c.setNbrGlory(5);
		assertEquals(5, c.getNbrGlory());
	}
	
	/**
	 * The accessor of the Name 
	 */
	@Test
	public void accNameCard() {
		c.setName("Test name");
		assertEquals("Test name", c.getName());
	}
	
	/**
	 * The accessor of the ImmediatEffect
	 */
	@Test
	public void accIECard() {
		c.setiE(ImmediateEffect.INSTANT_GOLD);
		assertEquals(ImmediateEffect.INSTANT_GOLD,c.getiE());
	}
	
	
}





