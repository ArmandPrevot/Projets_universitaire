package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import card.Cost;
import resources.TypeResource;

class CostTest {
	
	/**
	 * 
	 */
	Cost c;
	
	@BeforeEach
	public void setUp() {
		c = new Cost();
	}

	@Test
	public void accessorTest() {
		 c.setType(TypeResource.GLORY);
		 assertEquals(TypeResource.GLORY, c.getType());
		 c.setValue(3);
		 assertEquals(3, c.getValue());
		
	}

}
