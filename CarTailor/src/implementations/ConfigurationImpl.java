package implementations;

import java.util.Collection;  
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import exceptions.ConflictingRuleException;
import api.* ;
import implementations.* ;


public class ConfigurationImpl implements Configuration {
	Boolean complete;
	Map<String, PartTypeImpl> choix;
	Configurator configurator;
	
	CategoryImpl enCat;
	CategoryImpl tCat;
	CategoryImpl exCat;
	CategoryImpl iCat;
	
	public ConfigurationImpl() throws ConflictingRuleException {
		this.enCat = new CategoryImpl("Engine"); 
		this.tCat = new CategoryImpl("Transmission");
		this.exCat = new CategoryImpl("Exterior");
		this.iCat = new CategoryImpl("Interior");
		this.choix = new HashMap<>();
		this.complete = false;
		this.choix.put(enCat.getName(), null);
		this.choix.put(tCat.getName(), null);
		this.choix.put(exCat.getName(), null);
		this.choix.put(iCat.getName(), null);
		this.configurator = new ConfiguratorImpl();
	}
	
    @Override
    public void selectPart(PartType partType) {
    	PartTypeImpl part = (PartTypeImpl)partType;
    	Part selectpart1 =  part.newInstance();
    	if(this.choix.containsKey(part.getCategory().getName())) {
    		this.choix.put(partType.getCategory().getName(), part);
    	}else {
    		System.out.println("Erreur");
    	}
    }

    @Override
    public PartTypeImpl getSelectionForCategory(Category category) {
        return this.choix.get(category.getName());
    }

    @Override
	public boolean isComplete() {
    	complete = true ;
		for(PartTypeImpl p : this.choix.values()){
			if(p == null) {
				complete = false;
				break;
			}
		}
		return complete;
	}
    
	@Override
	public boolean isValid() {
		Boolean valide = true;
		System.out.println(this.isComplete());
		if(this.isComplete() == true) {
			int i = 1;
			for(PartTypeImpl p1 : this.choix.values()) {
				Collection<PartType> reqs = ((ConfiguratorImpl)configurator).getCompatibilityManager().getRequiredPart(p1);
				boolean containsReq = false;
				if(reqs == null)
					containsReq = true;
				for(PartTypeImpl p2 : this.choix.values()) {
					Collection<PartType> incomp = ((ConfiguratorImpl)configurator).getCompatibilityManager().getIncompatiblePart(p2);
					if(reqs != null) {
						for(PartType req : reqs) {
							if(req.getName() == p2.getName())
								containsReq = true;
						}
					}
					if(incomp != null) {
						PartType p = (PartType) p1;
						for(PartType tmp : incomp) {
							if(tmp.getName() == p.getName()) {
								valide = false;
							}
						}
					}
				}
				if(!containsReq)
					valide = false;
				i = i + 1;
			}
		}else {
			System.out.println("La sélection n'est pas valide");
			valide = false;
		}
		return valide;
	}
    
	
	
    public static void main(String[] args) throws ConflictingRuleException {
    	ConfigurationImpl configuration = new ConfigurationImpl();
    }
    
}