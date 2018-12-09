package test;

import static org.junit.Assert.*; 
import implementations.*;

import org.junit.Test;

import exceptions.ConflictingRuleException;
import Engine.*;
import Transmission.*;
import Exterior.*;
import Interior.*;
import api.*;

public class ConfigurationTest {

	@Test
	public void configurationTests() throws ConflictingRuleException{
		Configuration c = new ConfigurationImpl();
		
		assertEquals(false, c.isComplete());

		Category engine = new CategoryImpl("Engine");
		Category transmission = new CategoryImpl("Transmission");
		Category interior = new CategoryImpl("Interior");
		Category exterior = new CategoryImpl("Exterior");

		PartType EG100 = new PartTypeImpl("EG100",engine,"Gasoline, 100KW",EG100.class);
		c.selectPart(EG100);
		
		//La bonne part est sélectionnée
		assertEquals(EG100,c.getSelectionForCategory(engine));
		
		PartType TM5 = new PartTypeImpl("TM5",transmission,"Manual, 5 gears",TM5.class);
		c.selectPart(TM5);
		
		//La bonne part est sélectionnée
		assertEquals(TM5,c.getSelectionForCategory(transmission));
		
		PartType XC = new PartTypeImpl("XC",exterior,"Classic paint",XC.class);
		c.selectPart(XC);

		//La bonne part est sélectionnée
		assertEquals(XC,c.getSelectionForCategory(exterior));
		assertNotEquals(TM5,c.getSelectionForCategory(exterior));
		
		PartType IN = new PartTypeImpl("IN",interior,"Standard interior",IN.class);
		c.selectPart(IN);
		
		//La configuration est complète
		assertTrue(c.isComplete());
		//La configuration est valide
		assertTrue(c.isValid());
		
		//Sélection de TA5, non compatible avec EG100
		PartType TA5 = new PartTypeImpl("TA5",transmission,"Automatic, 5 gears",TA5.class);
		c.selectPart(TA5);
		
		//La configuration est complète
		assertTrue(c.isComplete());
		//La configuration n'est pas valide
		assertFalse(c.isValid());

		c.selectPart(TM5);
		//La configuration est valide
		assertTrue(c.isValid());
		
		//Sélection de EH120, qui requiert la part TC120
		PartType EH120 = new PartTypeImpl("EH120",engine,"Gasoline/electric hybrid, 120KW",EH120.class);
		c.selectPart(EH120);

		//La configuration n'est pas valide
		assertFalse(c.isValid());
		
		//Sélection de la part requise
		PartType TC120 = new PartTypeImpl("TC120",transmission,"Converter, 120kW max",TC120.class);
		c.selectPart(TC120);
		
		//La configuration est valide
		assertTrue(c.isValid());
	}
}
