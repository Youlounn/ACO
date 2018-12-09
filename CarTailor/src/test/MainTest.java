package test;

import org.junit.Test;

import exceptions.ConflictingRuleException;

public class MainTest {

	@Test 
	public void categoryTest() {
		CategoryTest ct = new CategoryTest();
		ct.categoryCreationTest();
	}
	
	@Test 
	public void configurationTest() throws ConflictingRuleException {
		ConfigurationTest ct = new ConfigurationTest();
		ct.configurationTests();;
	}
	
	@Test 
	public void configuratorTest() throws ConflictingRuleException {
		ConfiguratorTest ct = new ConfiguratorTest();
		ct.configuratorTests();;
	}
	
	@Test 
	public void partTypeTest() {
		PartTypeTest ptt = new PartTypeTest();
		ptt.partTypeTests();
	}
}
