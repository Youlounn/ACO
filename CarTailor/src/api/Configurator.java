package api;

import java.util.Collection;

import implementations.PartTypeImpl;

public interface Configurator {

	  public Configuration getConfiguration();

	  public Collection<PartType> getVariants(Category category);
}
