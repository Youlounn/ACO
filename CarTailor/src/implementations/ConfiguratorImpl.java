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
	HashMap<String, Collection<PartType>> catToParts = new HashMap<>();

	CompatibilityManager cm = new CompatibilityManagerImpl();

	private void init() throws ConflictingRuleException{

        Collection<PartType> incomp = new ArrayList<PartType>();
        Collection<PartType> req = new ArrayList<PartType>();

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

		catToParts.put(engine.getName(),new ArrayList<PartType>());
		catToParts.get(engine.getName()).add(EG100);
		catToParts.get(engine.getName()).add(EG133);
		catToParts.get(engine.getName()).add(EG210);
		catToParts.get(engine.getName()).add(ED110);
		catToParts.get(engine.getName()).add(ED180);
		catToParts.get(engine.getName()).add(EH120);

        //Initialisation des PartType Transmission

        PartType TM5 = new PartTypeImpl("TM5",transmission,"Manuel, 5 gears",TM5.class);
        PartType TM6 = new PartTypeImpl("TM6",transmission,"Manuel, 6 gears",TM6.class);
        PartType TA5 = new PartTypeImpl("TA5",transmission,"Automatique, 5 gears",TA5.class);
        PartType TS6 = new PartTypeImpl("TS6",transmission,"Sequential, 6 gears",TS6.class);
        PartType TSF7 = new PartTypeImpl("TSF7",transmission,"Sequential, 7 gears, 4 wheels drive",TSF7.class);
        PartType TC120 = new PartTypeImpl("TC120",transmission,"Converter, 120 Kw max",TC120.class);

		catToParts.put(transmission.getName(),new ArrayList<PartType>());
		catToParts.get(transmission.getName()).add(TM5);
		catToParts.get(transmission.getName()).add(TM6);
		catToParts.get(transmission.getName()).add(TA5);
		catToParts.get(transmission.getName()).add(TS6);
		catToParts.get(transmission.getName()).add(TSF7);
		catToParts.get(transmission.getName()).add(TC120);

        //Initialisation des PartType Exterior

        PartType XC = new PartTypeImpl("XC",exterior,"Classic paint",XC.class);
        PartType XM = new PartTypeImpl("XM",exterior,"Metallic paint",XM.class);
        PartType XS = new PartTypeImpl("XS",exterior,"Red paint and sport decoration",XS.class);

		catToParts.put(exterior.getName(),new ArrayList<PartType>());
		catToParts.get(exterior.getName()).add(XC);
		catToParts.get(exterior.getName()).add(XM);
		catToParts.get(exterior.getName()).add(XS);

        //Initialisation des PartType Interior

        PartType IN = new PartTypeImpl("IN",interior,"Standard interior",IN.class);
        PartType IH = new PartTypeImpl("IH",interior,"High-end interior",IH.class);
        PartType IS = new PartTypeImpl("IS",interior,"Sport finish",IS.class);

		catToParts.put(interior.getName(),new ArrayList<PartType>());
		catToParts.get(interior.getName()).add(IN);
		catToParts.get(interior.getName()).add(IH);
		catToParts.get(interior.getName()).add(IS);

        //incompatibilités TA5
        incomp = new ArrayList<PartType>();
        incomp.add(EG100);
        cm.addIncompatibilities(TA5,incomp);

        //incompatibilités TSF7
        incomp = new ArrayList<PartType>();
        incomp.add(EG100);
        incomp.add(EG133);
        incomp.add(ED110);
        cm.addIncompatibilities(TSF7,incomp);

        //incompatibilités XC
        incomp = new ArrayList<PartType>();
        incomp.add(EG210);
        cm.addIncompatibilities(XC,incomp);

        //incompatibilités XM
        incomp = new ArrayList<PartType>();
        incomp.add(EG100);
        cm.addIncompatibilities(XM,incomp);

        //incompatibilités XS
        incomp = new ArrayList<PartType>();
        incomp.add(EG100);
        cm.addIncompatibilities(XS,incomp);

        //incompatibilités IS
        incomp = new ArrayList<PartType>();
        incomp.add(EG100);
        incomp.add(TM5);
        cm.addIncompatibilities(IS,incomp);

        //requierements EH120
        req = new ArrayList<PartType>();
        req.add(TC120);
        cm.addRequirements(EH120,req);
        
        //requierements TC120
        req = new ArrayList<PartType>();
        req.add(EH120);
        cm.addRequirements(TC120,req);

        //requierements XS
        req = new ArrayList<PartType>();
        req.add(IS);
        cm.addRequirements(XS,req);
        
        //requierements IS
        req = new ArrayList<PartType>();
        req.add(XS);
        cm.addRequirements(IS,req);
	}
	
	public CompatibilityManager getCompatibilityManager(){
		return this.cm;
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
        return catToParts.get(category.getName());
    }
    
}