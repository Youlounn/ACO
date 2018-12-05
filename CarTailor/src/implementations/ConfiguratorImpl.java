package implementations;

import java.util.ArrayList;  
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import Engine.*;
import Exterior.*;
import Transmission.*;
import Interior.*;
import api.*;
import exceptions.ConflictingRuleException;
import implementations.*;



public class ConfiguratorImpl implements Configurator {
	
	Collection <Category> categories = new ArrayList<Category>();
	HashMap<Category, Collection<PartType>> catToParts = new HashMap<>();

	CompatibilityManager cm = new CompatibilityManagerImpl();

	private void init() throws ConflictingRuleException{

        Collection<PartType> Incompatibilities = new ArrayList<PartType>();
        Collection<PartType> Requirements = new ArrayList<PartType>();

        //Initialisation des Category

		Category engine = new CategoryImpl("Engine");
		Category transmission = new CategoryImpl("Transmission");
		Category exterior = new CategoryImpl("Exterior");
		Category interior = new CategoryImpl("Interior");
		
		categories.add(engine);
		categories.add(transmission);
		categories.add(exterior);
		categories.add(interior);

		//Initialisation des PartType Engine

		PartType EG100 = new PartTypeImpl("EG100",engine,"Gasoline, 100KW",EG100.class);
		PartType EG133 = new PartTypeImpl("EG133",engine,"Gasoline, 133KW",EG133.class);
		PartType EG210 = new PartTypeImpl("EG210",engine,"Gasoline, 210KW",EG210.class);
		PartType ED110 = new PartTypeImpl("ED110",engine,"Diesel, 110KW",ED110.class);
		PartType ED180 = new PartTypeImpl("ED180",engine,"Diesel 180KW",ED180.class);
		PartType EH120 = new PartTypeImpl("EH120",engine,"Gasoline/Electric hybrid, 100KW",EH120.class);

		catToParts.put(engine,new ArrayList<PartType>());
		catToParts.get(engine).add(EG100);
		catToParts.get(engine).add(EG133);
		catToParts.get(engine).add(EG210);
		catToParts.get(engine).add(ED110);
		catToParts.get(engine).add(ED180);
		catToParts.get(engine).add(EH120);

        //Initialisation des PartType Transmission

        PartType TM5 = new PartTypeImpl("TM5",transmission,"Manuel, 5 gears",TM5.class);
        PartType TM6 = new PartTypeImpl("TM6",transmission,"Manuel, 6 gears",TM6.class);
        PartType TA5 = new PartTypeImpl("TA5",transmission,"Automatique, 5 gears",TA5.class);
        PartType TS6 = new PartTypeImpl("TS6",transmission,"Sequential, 6 gears",TS6.class);
        PartType TSF7 = new PartTypeImpl("TSF7",transmission,"Sequential, 7 gears, 4 wheels drive",TSF7.class);
        PartType TC120 = new PartTypeImpl("TC120",transmission,"Converter, 120 Kw max",TC120.class);

		catToParts.put(transmission,new ArrayList<PartType>());
		catToParts.get(transmission).add(TM5);
		catToParts.get(transmission).add(TM6);
		catToParts.get(transmission).add(TA5);
		catToParts.get(transmission).add(TS6);
		catToParts.get(transmission).add(TSF7);
		catToParts.get(transmission).add(TC120);

        //Initialisation des PartType Exterior

        PartType XC = new PartTypeImpl("XC",exterior,"Classic paint",XC.class);
        PartType XM = new PartTypeImpl("XM",exterior,"Metallic paint",XM.class);
        PartType XS = new PartTypeImpl("XS",exterior,"Red paint and sport decoration",XS.class);

		catToParts.put(exterior,new ArrayList<PartType>());
		catToParts.get(exterior).add(XC);
		catToParts.get(exterior).add(XM);
		catToParts.get(exterior).add(XS);

        //Initialisation des PartType Interior

        PartType IN = new PartTypeImpl("IN",interior,"Standard interior",IN.class);
        PartType IH = new PartTypeImpl("IH",interior,"High-end interior",IH.class);
        PartType IS = new PartTypeImpl("IS",interior,"Sport finish",IS.class);

		catToParts.put(interior,new ArrayList<PartType>());
		catToParts.get(interior).add(IN);
		catToParts.get(interior).add(IH);
		catToParts.get(interior).add(IS);

        //Initialtisation des Incompatibility de TA5
        Incompatibilities.removeAll(Incompatibilities);
        Incompatibilities.add(EG100);
        cm.addIncompatibilities(TA5,Incompatibilities);

        //Initialtisation des Incompatibility de TSF7
        Incompatibilities.removeAll(Incompatibilities);
        Incompatibilities.add(EG100);
        Incompatibilities.add(EG133);
        Incompatibilities.add(ED110);
        cm.addIncompatibilities(TSF7,Incompatibilities);

        //Initialtisation des Incompatibility de XC
        Incompatibilities.removeAll(Incompatibilities);
        Incompatibilities.add(EG210);
        cm.addIncompatibilities(XC,Incompatibilities);

        //Initialtisation des Incompatibility de XM
        Incompatibilities.removeAll(Incompatibilities);
        Incompatibilities.add(EG100);
        cm.addIncompatibilities(XM,Incompatibilities);

        //Initialtisation des Incompatibility de XS
        Incompatibilities.removeAll(Incompatibilities);
        Incompatibilities.add(EG100);
        cm.addIncompatibilities(XS,Incompatibilities);

        //Initialtisation des Incompatibility de IS
        Incompatibilities.removeAll(Incompatibilities);
        Incompatibilities.add(EG100);
        Incompatibilities.add(TM5);
        cm.addIncompatibilities(IS,Incompatibilities);
	}
	
	
	public ConfiguratorImpl() throws ConflictingRuleException {
		init();
	}
	
	
	
    @Override
    public Configuration getConfiguration() {
        return null;
    }

    @Override
    public Collection<PartType> getVariants(Category category) {
        return catToParts.get(category);
    }
    
}