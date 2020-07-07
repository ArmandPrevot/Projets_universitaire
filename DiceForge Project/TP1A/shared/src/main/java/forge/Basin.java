package forge;

import java.util.ArrayList;
import java.util.List;
import face.Face;


public class Basin {
private List<Face> basinFace ;
private int price;

	/**
	 * Instantiates a new Basin
	 * 
	 * @param p is the price of faces in the basin
	 */
	public Basin(int p) {
		this.basinFace = new ArrayList<Face>();
		this.price = p;
	}
	
	/**
	 * Instantiates a new Basin
	 * 
	 * for parsing reasons
	 */
	public Basin() {
	}
	
	/**
	 * Get the price of faces in basin
	 * 
	 * @return the price of faces in basin
	 */
	
	/**Get the list of basin's faces
	 * 
	 * @return the list of basin's faces
	 * **/
	public List<Face> getBasinFace() {
		return basinFace;
	}

	/**Set the list Face to Basin
	 * 
	 * @param basinFace the new list of Faces in Basin
	 */
	public void setBasinFace(List<Face> basinFace) {
		this.basinFace = basinFace;
	}

	/**Get the price of basin
	 * 
	 * @return the price of the basin
	 * **/
	public int getPrice() {
		return price;
	}

	/**Set the price to Basin
	 * 
	 * @param price the price of the basin
	 */
	public void setPrice(int price) {
		this.price = price;
	}
	

}
