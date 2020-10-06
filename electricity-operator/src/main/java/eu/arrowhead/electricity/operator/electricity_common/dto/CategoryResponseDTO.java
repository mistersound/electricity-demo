package eu.arrowhead.electricity.operator.electricity_common.dto;

import java.io.Serializable;

public class CategoryResponseDTO implements Serializable {

	//=================================================================================================
	// members

	private static final long serialVersionUID = -8371510478751740542L;
	
	private int id;
	private String name;
	private int value;

	//=================================================================================================
	// methods

	//-------------------------------------------------------------------------------------------------
	public CategoryResponseDTO() {}
	
	//-------------------------------------------------------------------------------------------------
	public CategoryResponseDTO(final int id, final String name, final int value) {
		this.id = id;
		this.name = name;
		this.value = value;
	}

	//-------------------------------------------------------------------------------------------------
	public int getId() { return id; }
	public String getName() { return name; }
	public int getValue() { return value; }

	//-------------------------------------------------------------------------------------------------
	public void setId(final int id) {this.id = id; }
	public void setName(final String name) { this.name = name; }
	public void setValue(final int value) { this.value = value; }
}
