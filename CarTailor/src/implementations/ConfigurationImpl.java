package implementations;

import java.util.Collection;  
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import api.* ;
import implementations.* ;


public class ConfigurationImpl implements Configuration {
	Boolean complete = false;
	Map<CategoryImpl, PartTypeImpl> choix = new HashMap<>();
	CompatibilityManagerImpl compatibilityManager = new CompatibilityManagerImpl();
	
	CategoryImpl enCat = new CategoryImpl("Engine");
	CategoryImpl tCat = new CategoryImpl("Transmission");
	CategoryImpl exCat = new CategoryImpl("Exterior");
	CategoryImpl iCat = new CategoryImpl("Interior");
	
	public ConfigurationImpl() {
		this.choix.put(enCat, null);
		this.choix.put(tCat, null);
		this.choix.put(exCat, null);
		this.choix.put(iCat, null);
	}
	
    @Override
    public void selectPart(PartType partType) {
    	PartTypeImpl part = (PartTypeImpl)partType;
    	Part selectpart1 =  part.newInstance();
    	if(this.choix.containsKey(part.getCategory().getName())) {
    		this.choix.put((CategoryImpl) partType.getCategory(), part);
    		//Logger.getAnonymousLogger().log(Level.SEVERE, "j\'ai inserer EG100 dans choix");
    		System.out.println(choix.get(part.getCategory().getName())+" dans select part");
    	}else {
    		Logger.getAnonymousLogger().log(Level.SEVERE, "erreur d'instanciation");
    	}
    	System.out.println(this.choix.values());
    }

    @Override
    public PartTypeImpl getSelectionForCategory(Category category) {
    	  	
        return null;
    }

    @Override
	public Boolean isComplete() {
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
	public Boolean isValid() {
		Boolean valide = null;
		System.out.println(this.isComplete());
		if(this.isComplete() == true) {
			int i = 1;
			for(PartTypeImpl p1 : this.choix.values()) {
				for(PartTypeImpl p2 : this.choix.values()) {
					Collection<PartType> list = compatibilityManager.getIncompatiblePart(p2);
					if(list != null) {
						PartType p = (PartType) p1;
						if(list.contains(p)) {
							Logger.getAnonymousLogger().log(Level.SEVERE, " la selection n'est pas valide !!!!!");
						}
					}
				}
				i = i + 1;
			}
			valide = true;	
		}else {
			Logger.getAnonymousLogger().log(Level.SEVERE, " la selection n'est pas valide !!!!!");
			valide = false;
		}
		return valide;
	}
    
	
	
    public static void main(String[] args) {
    	ConfigurationImpl configuration = new ConfigurationImpl();
    	
		Boolean x = configuration.isComplete();
		
		System.out.println(configuration.isValid());
    }
    
}