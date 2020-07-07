package face;

import java.util.List;


/**
 * This Class represents a HybridFace which works like a face compounded of multiple faces
 *
 */
public class HybridFace extends Face {
	
	/** the price of the HybrdFace */
	private int price;
	
	/** the list of cost of the HybridFace */
	private List<Face> listFace;
	
	/**
	 * Boolean variable that specify if the player have to select which Resource to recieve
	 */
	private boolean selectable;
	
	/**
	 * Initiate an HybridFace
	 * 
	 * @param p the price of the face
	 * @param listF face list of the HybridFace
	 * @param select boolean to know if the face is selectable or not
	 */
	public HybridFace(int p, boolean select, List<Face> listF) {
		super(p);
		this.listFace = listF;
		this.selectable = select;
	}
	
	/**
	 * Initiate a HybridFace
	 * For parsing reasons.
	 */
	public HybridFace() {
		
	}
	
	/**
	 * Return if whether or not the Resource is selectable
	 * @return true if the player have to choose between the different typeResource in listTypeResource 
	 */
	public boolean isSelectable() {
		return selectable;
	}
	
	/**
	 * Sets if the hybridFace is selectable or not
	 * @param selectable true or false
	 */
	public void setSelectable(boolean selectable) {
		this.selectable = selectable;
	}
	
	/**
	 * Price getter
	 * @return price the HybridFace's price
	 */
	@Override
	public int getPrice() {
		return price;
	}
	
	/**
	 * Price setter
	 * @param price the new HybridFace's price
	 */
	public void setPrice(int price) {
		this.price = price;
	}
	
	/**
	 * Face's list getter
	 * @return listFace the face list of the HybridFace
	 */
	public List<Face> getListFace() {
		return listFace;
	}
	
	/**
	 * Face's list setter
	 * @param listFace the face list to set
	 */
	public void setListFace(List<Face> listFace) {
		this.listFace = listFace;
	}
	
	/**
	 * Prints a hybridFace
	 */
	@Override
	public String toString() {
		
		String result = "";
		String separator = " || ";
		
		int lastIndex = this.listFace.size() - 1;
		
		if(this.isSelectable() == false) {
			separator = " && ";
		}
		for(int i = 0; i < lastIndex; i++) {
			result += this.listFace.get(i).toString() + separator;
		}
		result += this.listFace.get(lastIndex).toString();
		return result;
	}
	
}
