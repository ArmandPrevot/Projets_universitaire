package face;

import resources.TypeResource;

 
/**
 * The Enum SimpleFace.
 */
public enum SimpleFace {
	
	/** All kinds of simples faces */
	 /** Attribute order : value of the face, its type and its price **/
	GOLD1(3,TypeResource.GOLD,2),
	LUNAR1(1,TypeResource.LUNAR,2),
	
	GOLD2(4,TypeResource.GOLD,3),
	SOLAR2(1,TypeResource.SOLAR,3), 
	
    GOLD3(6,TypeResource.GOLD,4),
    
    GLORY1(2,TypeResource.GLORY,5),

    LUNAR2(2, TypeResource.LUNAR, 6), 
    
    GLORY2(3, TypeResource.GLORY, 8), 
    SOLAR1(2, TypeResource.SOLAR, 8),
    
    GLORY3(4, TypeResource.GLORY, 12);
	
	
	/** The value. */
	private int value;
	
	/** The type. */
	private TypeResource type;
	
	/** The price. */
	private int price;
	
	/** The number of the face */
	private int FaceNumber = 24;
	
	/**Return the value of the simpleFace
	 * @return the value
	 */
	public int getValue() {
		return value;
	}

	/**Set the value of the simpleFace
	 * @param value the value to set
	 */
	public void setValue(int value) {
		this.value = value;
	}

	/**Return the type of ressource of the simpleFace
	 * @return the type
	 */
	public TypeResource getType() {
		return type;
	}

	/**Set the type of ressource of the simpleFace
	 * @param type the type to set
	 */
	public void setType(TypeResource type) {
		this.type = type;
	}

	/**Return the price of the simpleFace
	 * @return the price
	 */
	public int getPrice() {
		return price;
	}

	/**Set the price of the simpleFace
	 * @param price the price to set
	 */
	public void setPrice(int price) {
		this.price = price;
	}

	/**Return the number of the simpleFace
	 * @return the faceNumber
	 */
	public int getFaceNumber() {
		return FaceNumber;
	}

	/**Set the number of the simpleFace
	 * @param faceNumber the faceNumber to set
	 */
	public void setFaceNumber(int faceNumber) {
		FaceNumber = faceNumber;
	}

	/**
	 * Instantiates a new simple face.
	 *
	 * @param val the val
	 * @param t the t
	 * @param price the price
	 */
	SimpleFace(int val, TypeResource t, int price) {
		this.value = val;
		this.type = t;
		this.price = price;
	}
	
}
