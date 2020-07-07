package face;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import resources.TypeResource;

 
/**
 * The Class Face is composed of a value and a type.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
public class Face {

	/** The value of a face . */
	private int value;

	/** The type of a face. */
	private TypeResource typeF;

	private int price;

	/**
	 * Instantiates a new face.
	 *
	 * @param val the value of the face+
	 * @param t the type of the face
	 * @param p the price of the face
	 */
	public Face(int val, TypeResource t, int p) {
		this.price = p;
		this.value = val;
		this.typeF = t;
	}
	
	/**
	 * Instantiates a new face.
	 *
	 * @param val the value of the face+
	 * @param t the type of the face
	 */
	public Face(int val, TypeResource t) {
		this.price = 0;
		this.value = val;
		this.typeF = t;
	}
	
	/**
	 * Instantiates a new face with the price only for hybridFace creation.
	 * @param p the price of the face 
	 */
	public Face(int p) {
		this.price = p;
	}
	
	
	/**
	 * Instantiates a new face.
	 * For parsing reasons.
	 */
	public Face() {
	}
	
	/**
	 * Gets the value of a Face.
	 *
	 * @return the value of the face
	 */
	public int getValue() {
		return value;
	}

	/**
	 * Sets the value of the face
	 *
	 * @param value the new value to set on the face
	 */
	public void setValue(int value) {
		this.value = value;
	}
	
	
	/**
	 * Sets the price of a face
	 * 
	 * @param cost to be set
	 */
	public void setPrice(int cost) {
		this.price = cost;
	}
	
	/**
	 * Gets the price of a face
	 * @return the price of a face
	 */
	public int getPrice() {
		return this.price;
	}

	/**
	 * Gets the type of the Face
	 *
	 * @return the type of the Face
	 */
	public TypeResource getTypeF() {
		return typeF;
	}

	/**
	 * Sets the type of the Face
	 *
	 * @param typeF the new type of the Face.
	 */
	public void setTypeF(TypeResource typeF) {
		this.typeF = typeF;
	}
	
	/**
	 * To string that prints a face.
	 *
	 * @return the string that prints a face.
	 */
	public String toString() {
		return value + " " + typeF;
	}
}
