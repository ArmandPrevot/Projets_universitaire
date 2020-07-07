package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import face.CompoundFace;
import face.Face;
import face.HybridFace;
import face.SimpleFace;
import java.util.ArrayList;
import java.util.List;

import forge.Basin;

class BasinTest {
	
	/** Create a new forge */
	Basin Ba = Mockito.mock(Basin.class);
	
	/** Create a Face list for the test */
	List<Face> listFaceTest;
	
	/** Create a Face list for the test */
	List<Face> listFaceHybridTest;
	
	
	/**
	 * Setup all the variable.
	 */
	
	@BeforeEach
	void setUp()  {
		MockitoAnnotations.initMocks(this);
		Ba = new Basin();
	}
		
	

	/**
	 * Test the accessor 
	 */

	@Test
	void accTest() {
		assertEquals(0, Ba.getPrice());
		assertEquals(null, Ba.getBasinFace());
		
	}
	
	/**
	 * Test setPrice mutator method 
	 */
	
	@Test
	void setPriceTest() {
		Ba.setPrice(10);
		assertEquals(10, Ba.getPrice());
		
	}
	
	
	/**
	 * Test setBasinFace mutator method 
	 */
	@Test
	void setBasinFaceTest() {	
		listFaceTest = new ArrayList<Face>();
		for (int j=0; j <4; j++) {
			listFaceTest.add(new Face(SimpleFace.GOLD2.getValue(), SimpleFace.GOLD2.getType(), SimpleFace.GOLD2.getPrice()));
		}
		Ba.setBasinFace(listFaceTest);
		assertEquals(listFaceTest, Ba.getBasinFace());
		
		listFaceHybridTest = new ArrayList<Face>();
		for (int j=0; j <4; j++) {
			listFaceHybridTest.add(new HybridFace(CompoundFace.G1_AND_L1_AND_S1_AND_GL1.getPrice(), CompoundFace.G1_AND_L1_AND_S1_AND_GL1.isSelectable(),
					CompoundFace.G1_AND_L1_AND_S1_AND_GL1.getListFace()));
		}
		Ba.setBasinFace(listFaceHybridTest);
		assertEquals(listFaceHybridTest, Ba.getBasinFace());
		
	}
	
	

}
