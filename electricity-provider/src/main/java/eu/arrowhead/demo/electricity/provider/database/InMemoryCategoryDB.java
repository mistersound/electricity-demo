package eu.arrowhead.demo.electricity.provider.database;

import eu.arrowhead.common.exception.InvalidParameterException;
import eu.arrowhead.demo.electricity.provider.entity.Category;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class InMemoryCategoryDB extends ConcurrentHashMap<Integer, Category> {

	//=================================================================================================
	// members
	
	private static final long serialVersionUID = -2462387539362748691L;
	
	private int idCounter = 0;
	
	//=================================================================================================
	// methods

	//-------------------------------------------------------------------------------------------------
	public Category create(final String name, final Long value) {
		if (name == null ){ //|| name.isBlank()) {
			throw new InvalidParameterException("name is null or empty");
		}		
		if (value == null ) {
			throw new InvalidParameterException("Value is null or empty");
		}
		
		idCounter++;
		this.put(idCounter, new Category(idCounter, name.toLowerCase().trim(), value));
		return this.get(idCounter);
	}
	
	//-------------------------------------------------------------------------------------------------
	public List<Category> getAll() {
		return List.copyOf(this.values());
	}
	
	//-------------------------------------------------------------------------------------------------
	public Category getById(final int id) {
		if (this.containsKey(id)) {
			return this.get(id);
		} else {
			throw new InvalidParameterException("id '" + id + "' not exists");
		}
	}
	
	//-------------------------------------------------------------------------------------------------
	public Category updateById(final int id, final String name, final Long value) {
		if (this.containsKey(id)) {
			final Category cat = this.get(id);
			if (name!= null ){ //&& !name.isBlank()) {
				cat.setName(name);
			}
			//if (color != null && !color.isBlank()) {
				cat.setValue(value);
			//}
			this.put(id, cat);
			return cat;
		} else {
			throw new InvalidParameterException("id '" + id + "' not exists");
		}
	}
	
	//-------------------------------------------------------------------------------------------------
	public void removeById(final int id) {
		if (this.containsKey(id)) {
			this.remove(id);
		}
	}
}
