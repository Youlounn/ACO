package api;

import implementations.PartTypeImpl;

public interface Configuration {

	  public boolean isValid();

	  public boolean isComplete();

	  public PartTypeImpl getSelectionForCategory(Category Category);

	  public void selectPart(PartType partType);
}
