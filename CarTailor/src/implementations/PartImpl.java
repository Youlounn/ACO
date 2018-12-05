package implementations;

import api.Part;

public class PartImpl implements Part {
	
	PartTypeImpl pt;

	@Override
	public PartTypeImpl getPartype() {
		return this.pt;
	}
}
