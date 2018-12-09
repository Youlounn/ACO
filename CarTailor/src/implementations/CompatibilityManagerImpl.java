package implementations;

import api.* ; 
import exceptions.ConflictingRuleException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;

public class CompatibilityManagerImpl implements CompatibilityManager {
	
	Map<PartType,Collection<PartType>> incompatibilities = new HashMap<>();
	Map<PartType,Collection<PartType>> requirements = new HashMap<>();

	@Override
	public void addIncompatibilities (PartType ref , Collection<PartType> incomp) throws ConflictingRuleException {
		Objects.requireNonNull(ref);
		Objects.requireNonNull(incomp);
		for(PartType pt : incomp) {
		    Collection<PartType> requiredPart = getRequiredPart(ref);
		    if (requiredPart != null) {
                if (getRequiredPart(ref).contains(pt)) {
                    throw new ConflictingRuleException();
                }else if (getRequiredPart(pt).contains(ref)) {
                    throw new ConflictingRuleException();
                } 
		    }else {
                if (incompatibilities.containsKey(ref) && !getIncompatiblePart(ref).contains(pt)) {
                    incompatibilities.get(ref).add(pt);
                }else {
                	incompatibilities.put(ref, new ArrayList<PartType>());
                	incompatibilities.get(ref).add(pt);
                }
            }
		}
	}
	
	@Override
	public void addRequirements(PartType ref, Collection<PartType> req) throws ConflictingRuleException {
		Objects.requireNonNull(ref);
		Objects.requireNonNull(req);
		for(PartType pt : req) {
            Collection<PartType> incompatiblePart = getIncompatiblePart(ref);
            if (incompatiblePart != null) {
			    if (getIncompatiblePart(ref).contains(pt)) {
			        throw new ConflictingRuleException();
			    }else if (getIncompatiblePart(pt).contains(ref)) {
			        throw new ConflictingRuleException();
			    }
            }else {
		    	if (requirements.containsKey(ref) && !getRequiredPart(ref).contains(pt)) {
                    requirements.get(ref).add(pt);
                }else {
                	requirements.put(ref, new ArrayList<PartType>());
                	requirements.get(ref).add(pt);
                }
			}
		}
		
	}

	@Override
	public Collection<PartType> getIncompatiblePart(PartType ref) {
		for(PartType pt : incompatibilities.keySet()) {
			if(pt.getName() == ref.getName())
				return incompatibilities.get(pt);
		}
		return null;
	}
	
	@Override
	public Collection<PartType> getRequiredPart(PartType ref) {
		for(PartType pt : requirements.keySet()) {
			if(pt.getName() == ref.getName())
				return requirements.get(pt);
		}
		return null;
	}
}