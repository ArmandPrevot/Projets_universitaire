package test;

import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import face.CompoundFace;
import face.Face;
import face.SimpleFace;
import forge.Basin;
import forge.Forge;
import forge.TypeBasins;
import face.HybridFace;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

 
/**
 * The Class ForgeTest.
 */
public class ForgeTest {
	
	/** Create a new forge */
	Forge forgeTest = Mockito.mock(Forge.class);
	
	
	
	/**
	 * Setup all the variable.
	 */
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		forgeTest = new Forge();
		
	}
	
	/**
	 * Test addBasin method + getShop() function
	 */
	@Test
	public void addFacesToBasinTest() {
		Face laFaceTest = new Face(SimpleFace.GOLD1.getValue(), SimpleFace.GOLD1.getType(), SimpleFace.GOLD1.getPrice());
		
		forgeTest.getShop().add(new Basin(TypeBasins.BASIN1_1.getPriceBasin()));
		forgeTest.addFacesToBasin(laFaceTest, forgeTest.getShop().get(0), 4);
		
		assertEquals(1, forgeTest.getShop().size());
		assertEquals(4, forgeTest.getShop().get(0).getBasinFace().size());
		
		for(Face uneFace : forgeTest.getShop().get(0).getBasinFace()) {
			assertEquals(laFaceTest.toString(), uneFace.toString());
	}
		
		
		Face laFaceHybridTest = new HybridFace(CompoundFace.G1_AND_L1_AND_S1_AND_GL1.getPrice(), CompoundFace.G1_AND_L1_AND_S1_AND_GL1.isSelectable(),
				CompoundFace.G1_AND_L1_AND_S1_AND_GL1.getListFace()); 
		
		forgeTest.getShop().add(new Basin(TypeBasins.BASIN3.getPriceBasin()));
		forgeTest.addFacesToBasin(laFaceHybridTest,forgeTest.getShop().get(1), 1);
		
		assertEquals(2, forgeTest.getShop().size());
		assertEquals(1, forgeTest.getShop().get(1).getBasinFace().size());
		
		for(Face FaceHybrid : forgeTest.getShop().get(1).getBasinFace() )
		assertEquals(laFaceHybridTest.toString(), FaceHybrid.toString());
		
	}
	
	
	

}
