/*
 * OwnerTests.java
 *
 */

package net.larsbehnke.petclinicplus;

import junit.framework.TestCase;

import org.junit.Test;

/**
 * JUnit test for Owner
 * 
 * @author Ken Krebs
 */
public class OwnerTests extends TestCase {

	@Test
	public void testHasPet() throws Exception {
		Owner owner = new Owner();
		Pet fido = new Pet();
		fido.setName("Fido");
		assertNull(owner.getPet("Fido"));
		assertNull(owner.getPet("fido"));
		owner.addPet(fido);
		assertEquals(fido, owner.getPet("Fido"));
		assertEquals(fido, owner.getPet("fido"));
	
		

	}

}