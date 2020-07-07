package forge;

import java.util.ArrayList;
import java.util.List;

import face.Face;

 
/**
 * The Class Forge handles the shop, composed of Item
 */
public class Forge {
	

	/** The sanctuary of the Forge */
	private List<Basin> sanctuary;
	
	/**
	 * Instantiates a new forge.
	 */
	public Forge() {
		this.sanctuary = new ArrayList<Basin>();
	}
	
	/**
	 * Gets the sanctuary.
	 *
	 * @return the sanctuary
	 */
	public List<Basin> getShop(){ 
		return this.sanctuary; 
		}

	
	
	/**
	 * Adds a face and basin into the sanctuary.
	 *
	 * @param f the Face to add either hybrid or simple.
	 * @param b the Basin to add.
	 * @param size the number of players
	 */
	public void addFacesToBasin(Face f, Basin b,int size) {
			for (int i = 0; i <size; i++) {
				b.getBasinFace().add(f) ;
			}	
	}
	
}
