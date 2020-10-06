package eu.arrowhead.demo.electricity.provider.database;

import eu.arrowhead.demo.electricity.provider.electricity_common.dto.CategoryResponseDTO;
import eu.arrowhead.demo.electricity.provider.entity.Category;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

public class DTOConverter {

	
	//=================================================================================================
	// methods
	
	//-------------------------------------------------------------------------------------------------
	public static CategoryResponseDTO convertCategoryToCategoryResponseDTO(final Category cat) {
		Assert.notNull(cat, "category is null");
		return new CategoryResponseDTO(cat.getId(), cat.getName(), cat.getValue());
	}
	
	//-------------------------------------------------------------------------------------------------
	public static List<CategoryResponseDTO> convertCategoryListToCategoryResponseDTOList(final List<Category> cats) {
		Assert.notNull(cats, "category list is null");
		final List<CategoryResponseDTO> carResponse = new ArrayList<>(cats.size());
		for (final Category cat : cats) {
			carResponse.add(convertCategoryToCategoryResponseDTO(cat));
		}
		return carResponse;
	}

	//=================================================================================================
	// assistant methods

	//-------------------------------------------------------------------------------------------------
	public DTOConverter() {
		throw new UnsupportedOperationException(); 
	}
}
