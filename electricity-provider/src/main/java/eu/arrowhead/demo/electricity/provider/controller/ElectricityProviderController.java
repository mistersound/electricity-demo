package eu.arrowhead.demo.electricity.provider.controller;

import eu.arrowhead.common.exception.BadPayloadException;
import eu.arrowhead.demo.electricity.provider.ElectricityProviderConstants;
import eu.arrowhead.demo.electricity.provider.database.DTOConverter;
import eu.arrowhead.demo.electricity.provider.database.InMemoryCategoryDB;
import eu.arrowhead.demo.electricity.provider.electricity_common.dto.CategoryRequestDTO;
import eu.arrowhead.demo.electricity.provider.electricity_common.dto.CategoryResponseDTO;
import eu.arrowhead.demo.electricity.provider.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import eu.arrowhead.common.CommonConstants;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(ElectricityProviderConstants.CATEGORY_URI)
public class ElectricityProviderController {
	
	//=================================================================================================
	// members

	@Autowired
	private InMemoryCategoryDB catDB;

	//=================================================================================================
	// methods

	//-------------------------------------------------------------------------------------------------
	@GetMapping(path = CommonConstants.ECHO_URI)
	public String echoService() {
		return "Got it!";
	}
	
	//-------------------------------------------------------------------------------------------------

	//-------------------------------------------------------------------------------------------------
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<CategoryResponseDTO> getCaterories(@RequestParam(name = ElectricityProviderConstants.REQUEST_PARAM_NAME, required = false) final String name,
											 @RequestParam(name = ElectricityProviderConstants.REQUEST_PARAM_VALUE, required = false) final Long value) {
		final List<CategoryResponseDTO> response = new ArrayList<>();
		for (final Category cat : catDB.getAll()) {
			boolean toAdd = true;
			if (name != null && !name.isBlank() && !cat.getName().equalsIgnoreCase(name)) {
				toAdd = false;
			}
			/*if (color != null && !color.isBlank() && !car.getColor().equalsIgnoreCase(color)) {
				toAdd = false;
			}*/
			if (toAdd) {
				response.add(DTOConverter.convertCategoryToCategoryResponseDTO(cat));
			}
		}
		return response;
	}

	//-------------------------------------------------------------------------------------------------
	@GetMapping(path = ElectricityProviderConstants.BY_ID_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody public CategoryResponseDTO getCategoryById(@PathVariable(value = ElectricityProviderConstants.PATH_VARIABLE_ID) final int id) {
		return DTOConverter.convertCategoryToCategoryResponseDTO(catDB.getById(id));
	}

	//-------------------------------------------------------------------------------------------------
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody public CategoryResponseDTO createCategory(@RequestBody final CategoryRequestDTO dto) {
		if (dto.getName() == null || dto.getName().isBlank()) {
			throw new BadPayloadException("Category is null or blank");
		}
		/*if (dto.getColor() == null || dto.getColor().isBlank()) {
			throw new BadPayloadException("color is null or blank");
		}*/
		final Category cat = catDB.create(dto.getName(), dto.getValue());
		return DTOConverter.convertCategoryToCategoryResponseDTO(cat);
	}

	//-------------------------------------------------------------------------------------------------
	@PutMapping(path = ElectricityProviderConstants.BY_ID_PATH, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody public CategoryResponseDTO updateCategory(@PathVariable(name = ElectricityProviderConstants.PATH_VARIABLE_ID) final int id, @RequestBody final CategoryRequestDTO dto) {
		if (dto.getName() == null || dto.getName().isBlank()) {
			throw new BadPayloadException("category is null or blank");
		}
		/* if (dto.getColor() == null || dto.getColor().isBlank()) {
			throw new BadPayloadException("color is null or blank");
		} */
		final Category cat = catDB.updateById(id, dto.getName(), dto.getValue());
		return DTOConverter.convertCategoryToCategoryResponseDTO(cat);
	}


	//-------------------------------------------------------------------------------------------------
	@DeleteMapping(path = ElectricityProviderConstants.BY_ID_PATH)
	public void removeCategoryById(@PathVariable(value = ElectricityProviderConstants.PATH_VARIABLE_ID) final int id) {
		catDB.removeById(id);
	}



}
