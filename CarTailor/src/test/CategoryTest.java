package test;

import static org.junit.Assert.*;
import implementations.CategoryImpl;

import org.junit.Test;

import api.Category;

public class CategoryTest {

	@Test
	public void categoryCreationTest(){
		Category c = new CategoryImpl("Engine");
		String cName = c.getName();
		assertEquals("Engine",cName);
		Category c2 = new CategoryImpl("Interior");
		cName = c2.getName();
		assertEquals("Interior",cName);
		Category c3 = new CategoryImpl("Exterior");
		cName = c3.getName();
		assertNotEquals("Interior",cName);
	}
}
