package implementations;

import api.Category;

public class CategoryImpl implements Category {
	
	private String name;
	
	public CategoryImpl(String name) {
		this.name = name;
	}

	@Override
	public String getName() {		
		return this.name;
	}
}
