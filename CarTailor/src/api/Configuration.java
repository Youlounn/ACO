package api;

import implementations.PartTypeImpl;

public interface Configuration {

	  public Boolean isValid();

	  public Boolean isComplete();

	  public PartTypeImpl getSelectionForCategory(Category Category);

	  public void selectPart(PartType partType);
}
