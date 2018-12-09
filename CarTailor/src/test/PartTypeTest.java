package test;

import static org.junit.Assert.*;
import implementations.CategoryImpl;
import implementations.PartTypeImpl;

import org.junit.Test;

import Engine.EG100;
import Engine.EG133;
import Transmission.TS6;
import api.Category;
import api.PartType;

public class PartTypeTest {

	@Test
	public void partTypeTests(){

		Category engine = new CategoryImpl("Engine");
		Category transmission = new CategoryImpl("Transmission");
		

		PartType EG100 = new PartTypeImpl("EG100",engine,"Gasoline, 100KW",EG100.class);
        PartType TS6 = new PartTypeImpl("TS6",transmission,"Sequential, 6 gears",TS6.class);

		String EG100name = EG100.getName();
		assertEquals("EG100",EG100name);
		assertEquals(engine,EG100.getCategory());
		assertNotEquals(transmission,EG100.getCategory());
		
		String TS6name = TS6.getName();
		assertNotEquals("EG100",TS6name);
		assertEquals("TS6",TS6name);
		assertNotEquals(engine,TS6.getCategory());
		assertEquals(transmission,TS6.getCategory());
	}
	
}
