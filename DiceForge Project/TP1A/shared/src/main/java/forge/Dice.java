package forge;

import face.Face;
import resources.TypeResource;

 
/**
 * The Class Dice is composed of 6 Faces
 * 
 */
public class Dice {

	/** tabFaces contains all the faces of a Dice **/
	public Face[] tabFaces;
	
	/**
	 * Instantiates a new dice.
	 */
	public Dice() {
		tabFaces = new Face[6];
		for(int i = 0; i < tabFaces.length; i++) {
			tabFaces[i] = new Face(1, TypeResource.GOLD);
		}
	}
	
	/**
	 * Sets the face.
	 *
	 * @param numF the index in the array tabFaces, the face to set
	 * @param f the face that will be set
	 */
	public void setFace(int numF, Face f) {
		this.tabFaces[numF] = f;
	}
	
	/**
	 * @return an Integer between 1 and 6
	 **/
	public int rollDice() {
		return (int)(Math.random()*6);
	}
	
	
	/**
	 * Gets the face by his number (0-5)
	 *
	 * @param numF the index in tabFaces array, number of the face searched
	 * @return the face corresponding to the number given
	 */
	
	public 	Face getFaceByNumF(int numF) {
		return tabFaces[numF];
	}
	
	/**
	 * Prints the faces.
	 * @return all the faces of a dice
	 */
	public String toString() {
		String s = "";
		
		for(int i=0; i<tabFaces.length;i++) {
			s += "[" + tabFaces[i].toString() + "]" + " ";
		}
		
		return s;
	}
	
}
