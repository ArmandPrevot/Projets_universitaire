package face;

import resources.TypeResource;
import java.util.Arrays;
import java.util.List;

public enum CompoundFace {
	// 4 Gold Faces
	G1_OR_S1_OR_L1(4, true, new Face(1,TypeResource.GOLD), new Face(1,TypeResource.LUNAR), new Face(1,TypeResource.SOLAR)),
	GOLD2_AND_LUNAR1(4, false, new Face(2,TypeResource.GOLD), new Face(1,TypeResource.LUNAR)),
	GOLD1_AND_GLORY1(4, false, new Face(1,TypeResource.GOLD), new Face(1, TypeResource.GLORY)),
	// 5 Gold Faces
	GOLD3_OR_GLORY2(5, true, new Face(3, TypeResource.GOLD), new Face(2, TypeResource.GLORY)),
	// 12 Gold Faces
	G1_AND_L1_AND_S1_AND_GL1(12, false, new Face(1, TypeResource.GOLD), new Face(1, TypeResource.LUNAR), new Face(1, TypeResource.SOLAR), new Face(1, TypeResource.GLORY)),
	G2_OR_L2_OR_S2(12, true, new Face(2, TypeResource.GOLD), new Face(2, TypeResource.LUNAR), new Face(2, TypeResource.SOLAR)),
	GLORY2LUNAR2(12, false, new Face(2, TypeResource.GLORY), new Face(2, TypeResource.LUNAR));
	
	/* Price of the HFace */
	private int price;
	/* Hface's cost list */
	private List<Face> listFace;
	/* Boolean variable to precise if the HFace is selectable or not
	 * Basically at false
	 */
	private boolean selectable = false;
	/**
	 * Selectable CompoundFace constructor 
	 * @param p the Hface price
	 * @param select at true if it selectable
	 * @param c the cost list of the Hface
	 */
	CompoundFace(int p, boolean select, Face... f) {
		this.price = p;
		this.listFace = Arrays.asList(f);
		this.selectable = select;
	}
	/**
	 * Price getter
	 * @return the price
	 */
	public int getPrice() {
		return price;
	}
	/** Price setter
	 * @param price the price to set
	 */
	public void setPrice(int price) {
		this.price = price;
	}
	/**
	 * listCost getter
	 * @return the listCost
	 */
	public List<Face> getListFace() {
		return listFace;
	}
	/**
	 * listCost setter
	 * @param listFace the faces to set
	 */
	public void setListFace(List<Face> listFace) {
		this.listFace = listFace;
	}
	/**
	 * selectable getter
	 * @return the selectable
	 */
	public boolean isSelectable() {
		return selectable;
	}
	/**
	 * selectable setter
	 * @param selectable the selectable to set
	 */
	public void setSelectable(boolean selectable) {
		this.selectable = selectable;
	}
}
