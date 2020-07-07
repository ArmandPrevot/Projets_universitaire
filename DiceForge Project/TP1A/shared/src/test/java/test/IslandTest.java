/**
 * 
 */
package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import card.Card;
import card.Island;

/**
 * @author pierr
 *
 */
class IslandTest {
	
	/** */
	Island i;
	
	/** */
	List<Card> listCard;
	
	

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		i = new Island();
		listCard = new ArrayList<>();
	}

	@Test
	void accessorTest() {
		i.setCardsAvailable(listCard);
		assertEquals(listCard, i.getCardsAvailable());
	}
	
	
	@Test
    public void addCardTest() {
        Card c = new Card();
        listCard.add(c);
        i.setCardsAvailable(listCard);
        assertEquals(c, i.getCardsAvailable().get(0));
    }

}
