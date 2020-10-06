package eu.arrowhead.demo.electricity.provider.electricity_common.dto;

import java.io.Serializable;

public class CategoryResponseDTO implements Serializable {

	//=================================================================================================
	// members

	private static final long serialVersionUID = -8371510478751740542L;
	
	private int id;
	private String name;
	private Long value;

	//=================================================================================================
	// methods

	//-------------------------------------------------------------------------------------------------
	public CategoryResponseDTO() {}
	
	//-------------------------------------------------------------------------------------------------
	public CategoryResponseDTO(final int id, final String name, final Long value) {
		this.id = id;
		this.name = name;
		this.value = value;
	}

	//-------------------------------------------------------------------------------------------------
	public int getId() { return id; }
	public String getName() { return name; }
	public Long getValue() { return value; }

	//-------------------------------------------------------------------------------------------------
	public void setId(final int id) {this.id = id; }
	public void setName(final String name) { this.name = name; }
	public void setValue(final Long value) { this.value = value; }
}
