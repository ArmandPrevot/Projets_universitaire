package test;

import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import face.Face;
import forge.Dice;
import resources.TypeResource;
 
/**
 * The Class DiceTest.
 */
public class DiceTest {
	
	
	/** Create a new dice, using Mockito */
	Dice de = Mockito.mock(Dice.class);
	
	
	/**
	 * Setup Mockito.
	 */
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	
	
	/**
	 * Test the method rollDice() with a rigged return and using Mockito
	 */
	@Test
	public void diceRoll() {		
		when(de.rollDice()).thenReturn(5, 5, 5, 5);
		for(int i=0; i < 4; i++) assertEquals(5, de.rollDice());
	}
	
	
	/**
	 * Test the method rollDice() with Mockito 
	 */
	@Test
	public void diceRollWrong() {
		when(de.rollDice()).thenReturn(6);
		assertNotEquals(2, de.rollDice());
	}
	
	/**
	 * Test the setter of a face.
	 */
	@Test
	public void setFaceTest() {
		de = new Dice();
		Face f = new Face(6, TypeResource.GOLD, 0);
		de.setFace(1, f);
		assertEquals(6, de.getFaceByNumF(1).getValue());
	}
	
	
	

}
