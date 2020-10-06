package eu.arrowhead.demo.electricity.provider.entity;

public class Category {

	//=================================================================================================
	// members

	private final int id;
	private String name;
	private Long value;

	//=================================================================================================
	// methods

	//-------------------------------------------------------------------------------------------------
	public Category(final int id, final String name, final Long value) {
		this.id = id;
		this.name = name;
		this.value = value;
	}

	//-------------------------------------------------------------------------------------------------
	public int getId() { return id; }
	public String getName() { return name; }
	public Long getValue() { return value; }

	//-------------------------------------------------------------------------------------------------
	public void setName(final String name) { this.name = name; }
	public void setValue(final Long value) { this.value = value; }
}
