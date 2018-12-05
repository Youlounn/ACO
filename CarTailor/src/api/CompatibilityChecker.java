package api;

import java.util.Collection;

import api.PartType;

public interface CompatibilityChecker {

	public Collection<PartType> getRequiredPart(PartType ref);

	public Collection<PartType> getIncompatiblePart(PartType ref);
}