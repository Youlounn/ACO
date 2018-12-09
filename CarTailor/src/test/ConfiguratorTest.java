package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import implementations.CategoryImpl;
import implementations.ConfiguratorImpl;
import implementations.PartTypeImpl;

import org.junit.Test;

import exceptions.ConflictingRuleException;
import Engine.*;
import api.*;

public class ConfiguratorTest {

	@Test
	public void configuratorTests() throws ConflictingRuleException{
		Configurator c = new ConfiguratorImpl();
		Collection <PartType> partTypes = new ArrayList<PartType>();

		Category engine = new CategoryImpl("Engine");
		partTypes.add(new PartTypeImpl("EG100",engine,"Gasoline, 100KW",EG100.class));
		partTypes.add(new PartTypeImpl("EG133",engine,"Gasoline, 133KW",EG133.class));
		partTypes.add(new PartTypeImpl("EG210",engine,"Gasoline, 210KW",EG210.class));
		partTypes.add(new PartTypeImpl("ED110",engine,"Diesel, 110KW",ED110.class));
		partTypes.add(new PartTypeImpl("ED180",engine,"Diesel 180KW",ED180.class));
		partTypes.add(new PartTypeImpl("EH120",engine,"Gasoline/Electric hybrid, 100KW",EH120.class));

		Iterator<PartType> it1 = partTypes.iterator();
		Iterator<PartType> it2 = c.getVariants(engine).iterator();
		
		while(it1.hasNext() && it2.hasNext()){
			assertEquals(it1.next().getName(),it2.next().getName());
		}
		
		
	}
}
