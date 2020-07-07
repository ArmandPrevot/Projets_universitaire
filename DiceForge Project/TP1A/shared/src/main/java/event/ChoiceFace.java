package event;

import face.Face;
import face.HybridFace;
/**
 * ChoiceFace class is the object that will transit between the client and the server for the choosing face event.
 *
 */
public class ChoiceFace {

	/**
	 * face is the face with choice that has fallen on rollDices()
	 */
	private HybridFace face;
	/**
	 * choiceFace is the face chosen by the client
	 */
	private Face choiceFace;
	
	
	/**Return the the face with choice that has fallen on rollDices()
	 * @return the face
	 */
	public HybridFace getFace() {
		return face;
	}


	/**Set the the face with choice that has fallen on rollDices()
	 * @param face the face to set
	 */
	public void setFace(HybridFace face) {
		this.face = face;
	}


	/**Return the face chosen by the client
	 * @return the choiceFace
	 */
	public Face getChoiceFace() {
		return choiceFace;
	}


	/**Set the face chosen by the client
	 * @param choiceFace the choiceFace to set
	 */
	public void setChoiceFace(Face choiceFace) {
		this.choiceFace = choiceFace;
	}


	/**
	 * Instantiates a new ChoiceFace object 
	 * For parsing reasons.
	 */
	public ChoiceFace() {
		
	}
}
