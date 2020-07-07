package test;


import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import client.Player;
import java.util.Random;
import java.util.UUID;


/**
 * The Class PlayerTest.
 */
public class PlayerTest {

	/** Create a player */
	Player p;
	
	/** Create an UUID object */
	UUID idPlayer;	
	
	
	/**
	 * Setup the id and instantiate the player
	 */
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		idPlayer = new UUID(3, 8);
		p = new Player(idPlayer, "Test");
	}
	
	
	/**
	 * Test the player field's accessor
	 */
	@Test
	public void getNameTest() {
		assertEquals("Test", p.getName());
		p.setName("Pierre");
		assertEquals("Pierre", p.getName());
	}
	
	
	/**
	 * Test the UUID field's accessor
	 */
	@Test
	public void getUUIDTest() {
		assertEquals("00000000-0000-0003-0000-000000000008", p.getId().toString());
		assertEquals(idPlayer, p.getId());
	}
	/**
	 * Test the UUID field's accessor
	 */
	@Test
	public void setUUIDTest() {
		UUID newUUID = new UUID(5,7);
		p.setId(newUUID);
		assertEquals(newUUID, p.getId());
	}

}
