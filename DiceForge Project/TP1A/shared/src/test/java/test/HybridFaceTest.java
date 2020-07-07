package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import face.HybridFace;
import face.CompoundFace;

class HybridFaceTest {
	
	/** Create a new HybrideFace */
	HybridFace HF = Mockito.mock(HybridFace.class);
	

	/**
	 * Setup the HybridFace
	 */
	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);
		HF = new HybridFace(CompoundFace.G1_AND_L1_AND_S1_AND_GL1.getPrice(), CompoundFace.G1_AND_L1_AND_S1_AND_GL1.isSelectable(),
				CompoundFace.G1_AND_L1_AND_S1_AND_GL1.getListFace());
		
	}
	
	/**
	 * Test  isSelectable method 
	 */
	
	@Test
	void isSelectableTest() {
		assertEquals(CompoundFace.G1_AND_L1_AND_S1_AND_GL1.isSelectable(), HF.isSelectable() );
	}
	
	/**
	 * Test setSelectable mutator method 
	 */
	
	@Test
	void setSelectableTest() {
		HF.setSelectable(false);
		assertEquals(false, HF.isSelectable() );
	}
	
	/**
	 * Test setPrice mutator method 
	 */
	
	@Test
	void setPriceTest() {
		HF.setPrice(15);;
		assertEquals(15, HF.getPrice() );
	}
	
	/**
	 * Test setListFace mutator method 
	 */
	
	@Test
	void setListFaceTest() {
		HF.setListFace(CompoundFace.G1_OR_S1_OR_L1.getListFace());
		assertEquals(CompoundFace.G1_OR_S1_OR_L1.getListFace(), HF.getListFace() );
	}
	
	/**
	 * Test the accessor 
	 */
	
	@Test
	void accTest() {
		HF = new HybridFace();
		assertEquals(0, HF.getPrice());
		assertEquals(null, HF.getListFace());
	}
	
	

}
