package implementations;

import java.lang.reflect.Constructor;  
import java.util.logging.Level;
import java.util.logging.Logger;

import api.*;


public class PartTypeImpl implements PartType {
	
	private Category category;
	private String name;
	private String description;
	private Class <? extends PartImpl> classRef;
	
	//Constructeur
	public PartTypeImpl(String name, Category category,String desc, Class <? extends PartImpl> classRef) {
		this.name = name;
		this.category = category;
		this.description = desc;
		this.classRef = classRef;
	}
	
	public PartTypeImpl(String name, Category category) {
		this.name = name;
		this.category = category;
	}
	
	public Part newInstance() {
		try {
						
			Constructor<? extends Part> constructor;
			constructor = classRef.getConstructor();
			return constructor.newInstance();
		}catch(Exception e) {
			Logger.getAnonymousLogger().log(Level.SEVERE, "erreur d'instanciation", e);
		}
		return null;
	}
	
    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public CategoryImpl getCategory() {
        return (CategoryImpl)this.category;
    }
    
    
}