package forge;

//TODO: Auto-generated Javadoc
/**
* The Enum TypeBasins.
*/

public enum TypeBasins {
	
	/** All kinds of basins**/
		/** Attribute order : value of the price **/
	BASIN1_1(2),
	BASIN1_2(2),
	
	BASIN2_1(3),
	BASIN2_2(3),
	
	BASIN3(4),
	
	BASIN4(5),
	
	BASIN5(6),
	
	BASIN6_1(8),
	BASIN6_2(8),
	
	BASIN7(12);
	
	/** The price of the hybridFace **/
	private int price;
	 
	/**
	 * Set price of basin
	 * 
	 * @param p the price of a Basin
	 * **/
	TypeBasins(int p){
		this.price = p;
	}
	
	/**Get price of the basin
	 * 
	 * @return the price of a Basin
	 * **/
	public int getPriceBasin() {
		return this.price;
	}
	
}
