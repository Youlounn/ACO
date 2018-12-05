package api;

import java.util.Collection;

import api.PartType;
import exceptions.ConflictingRuleException;
import implementations.PartTypeImpl;

public interface CompatibilityManager extends CompatibilityChecker {


	void addIncompatibilities(PartType ref, Collection<PartType> incompatibilityPart) throws ConflictingRuleException;

	void addRequirements(PartType ref, Collection<PartType> requirementPart) throws ConflictingRuleException;

}