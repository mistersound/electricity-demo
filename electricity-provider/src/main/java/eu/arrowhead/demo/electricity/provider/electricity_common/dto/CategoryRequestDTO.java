package eu.arrowhead.demo.electricity.provider.electricity_common.dto;

import java.io.Serializable;

public class CategoryRequestDTO implements Serializable {

	//=================================================================================================
	// members

	private static final long serialVersionUID = -5363562707054976998L;

	private String name;
	private Long value;

	//=================================================================================================
	// methods
	
	//-------------------------------------------------------------------------------------------------
	public CategoryRequestDTO(final String name, final Long value) {
		this.name = name;
		this.value = value;
	}

	//-------------------------------------------------------------------------------------------------
	public String getName() { return name; }
	public Long getValue() { return value; }

	//-------------------------------------------------------------------------------------------------
	public void setName(final String name) { this.name = name; }
	public void setValue(final Long value) { this.value = value; }
}
