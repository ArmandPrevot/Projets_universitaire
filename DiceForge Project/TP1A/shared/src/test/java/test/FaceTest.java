package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import face.Face;
import resources.TypeResource;


/**
* The Class FaceTest.
*/
public class FaceTest {
	
	/** Create a new Face */
	Face f = Mockito.mock(Face.class);
	
	/**
	 * Setup the face
	 */
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		f = new Face(3, TypeResource.GOLD, 0);
	}
	/**
	 * Test the accessor of the Value
	 */
	@Test
	public void accValueTest() {
		f.setValue(10);
		assertEquals(10, f.getValue());
	}
	/**
	 * Test the accessor of TypeResource
	 */
	@Test
	public void accTypeResourceTest() {
		f.setTypeF(TypeResource.LUNAR);
		assertEquals(TypeResource.LUNAR, f.getTypeF());
	}
	/**
	 * Test the accessor of the Price
	 */
	@Test
	public void accPriceTest() {
		f.setPrice(8);
		assertEquals(8, f.getPrice());
	}
	/**
	 * Test the accessor of the Value
	 */
	@Test
	public void Seri() {
		f = new Face();
		assertEquals(0, f.getValue());
		assertEquals(0, f.getPrice());
		assertEquals(null, f.getTypeF());
	}

}
