package client;

import java.util.UUID;

 
/**
 * The Class Player contains a name and an UUID id, also a Client.
 */
public class Player {
	
	/** The name of the Player */
	private String name;
	
	/** The id of the Player. */
	private UUID id;

	/**
	 * Instantiates a new player.
	 *
	 * @param id the identifier of the player
	 * @param name the name of the player
	 */
	public Player(UUID id, String name) {
		this.setName(name);
		this.id = id;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the player's name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the id (uuid) of the player.
	 *
	 * @return the id of the player.
	 */
	public UUID getId() {
		return id;
	}

	/**
	 * Sets the id of the player.
	 *
	 * @param id the new id of the player.
	 */
	public void setId(UUID id) {
		this.id = id;
	}
	
	
}
