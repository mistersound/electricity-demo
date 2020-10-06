package eu.arrowhead.electricity.consumer;

import eu.arrowhead.common.SSLProperties;
import eu.arrowhead.common.Utilities;
import eu.arrowhead.common.dto.shared.*;
import eu.arrowhead.common.exception.InvalidParameterException;
import eu.arrowhead.electricity.consumer.electricity_common.dto.CategoryRequestDTO;
import eu.arrowhead.electricity.consumer.electricity_common.dto.CategoryResponseDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpMethod;

import eu.arrowhead.client.library.ArrowheadService;
import eu.arrowhead.common.CommonConstants;
import eu.arrowhead.common.dto.shared.OrchestrationFlags.Flag;
import eu.arrowhead.common.dto.shared.OrchestrationFormRequestDTO.Builder;

import java.util.List;

@SpringBootApplication
@ComponentScan(basePackages = {CommonConstants.BASE_PACKAGE, CategoryConsumerConstants.BASE_PACKAGE}) //TODO: add custom packages if any
public class ConsumerMain implements ApplicationRunner {

	//=================================================================================================
	// members

	@Autowired
	private ArrowheadService arrowheadService;

	@Autowired
	protected SSLProperties sslProperties;

	private final Logger logger = LogManager.getLogger( ConsumerMain.class );

	//=================================================================================================
	// methods

	//------------------------------------------------------------------------------------------------
	public static void main( final String[] args ) {
		SpringApplication.run(ConsumerMain.class, args);
	}

	//-------------------------------------------------------------------------------------------------
	@Override
	public void run(final ApplicationArguments args) throws Exception {
		//createCategoryServiceOrchestrationAndConsumption();
		getCategoryServiceOrchestrationAndConsumption();


	}

	//-------------------------------------------------------------------------------------------------
	public void createCategoryServiceOrchestrationAndConsumption() {
		logger.info("Orchestration request for " + CategoryConsumerConstants.CREATE_CATEGORY_SERVICE_DEFINITION + " service:");
		final ServiceQueryFormDTO serviceQueryForm = new ServiceQueryFormDTO.Builder(CategoryConsumerConstants.CREATE_CATEGORY_SERVICE_DEFINITION)
				.interfaces(getInterface())
				.build();

		final Builder orchestrationFormBuilder = arrowheadService.getOrchestrationFormBuilder();
		final OrchestrationFormRequestDTO orchestrationFormRequest = orchestrationFormBuilder.requestedService(serviceQueryForm)
				.flag(Flag.MATCHMAKING, true)
				.flag(Flag.OVERRIDE_STORE, true)
				.build();

		printOut(orchestrationFormRequest);

		final OrchestrationResponseDTO orchestrationResponse = arrowheadService.proceedOrchestration(orchestrationFormRequest);

		logger.info("Orchestration response:");
		printOut(orchestrationResponse);

		if (orchestrationResponse == null) {
			logger.info("No orchestration response received");
		} else if (orchestrationResponse.getResponse().isEmpty()) {
			logger.info("No provider found during the orchestration");
		} else {
			final OrchestrationResultDTO orchestrationResult = orchestrationResponse.getResponse().get(0);
			validateOrchestrationResult(orchestrationResult, CategoryConsumerConstants.CREATE_CATEGORY_SERVICE_DEFINITION);


			final List<CategoryRequestDTO> categoriesToCreate = List.of(new CategoryRequestDTO("household", Long.valueOf(10)), new CategoryRequestDTO("Business", Long.valueOf(15)));

			for (final CategoryRequestDTO categoryRequestDTO : categoriesToCreate) {
				logger.info("Create a category request:");
				printOut(categoryRequestDTO);
				final String token = orchestrationResult.getAuthorizationTokens() == null ? null : orchestrationResult.getAuthorizationTokens().get(getInterface());
				final CategoryResponseDTO categoryCreated = arrowheadService.consumeServiceHTTP(CategoryResponseDTO.class, HttpMethod.valueOf(orchestrationResult.getMetadata().get(CategoryConsumerConstants.HTTP_METHOD)),
						orchestrationResult.getProvider().getAddress(), orchestrationResult.getProvider().getPort(), orchestrationResult.getServiceUri(),
						getInterface(), token, categoryRequestDTO, new String[0]);
				logger.info("Provider response");
				printOut(categoryCreated);
			}
		}
	}

	//-------------------------------------------------------------------------------------------------
	public void getCategoryServiceOrchestrationAndConsumption() {
		logger.info("Orchestration request for " + CategoryConsumerConstants.GET_CATEGORY_SERVICE_DEFINITION + " service:");
		final ServiceQueryFormDTO serviceQueryForm = new ServiceQueryFormDTO.Builder(CategoryConsumerConstants.GET_CATEGORY_SERVICE_DEFINITION)
				.interfaces(getInterface())
				.build();

		final Builder orchestrationFormBuilder = arrowheadService.getOrchestrationFormBuilder();
		final OrchestrationFormRequestDTO orchestrationFormRequest = orchestrationFormBuilder.requestedService(serviceQueryForm)
				.flag(Flag.MATCHMAKING, true)
				.flag(Flag.OVERRIDE_STORE, true)
				.build();

		printOut(orchestrationFormRequest);

		final OrchestrationResponseDTO orchestrationResponse = arrowheadService.proceedOrchestration(orchestrationFormRequest);

		logger.info("Orchestration response:");
		printOut(orchestrationResponse);

		if (orchestrationResponse == null) {
			logger.info("No orchestration response received");
		} else if (orchestrationResponse.getResponse().isEmpty()) {
			logger.info("No provider found during the orchestration");
		} else {
			final OrchestrationResultDTO orchestrationResult = orchestrationResponse.getResponse().get(0);
			validateOrchestrationResult(orchestrationResult, CategoryConsumerConstants.GET_CATEGORY_SERVICE_DEFINITION);

			logger.info("Get all categories:");
			final String token = orchestrationResult.getAuthorizationTokens() == null ? null : orchestrationResult.getAuthorizationTokens().get(getInterface());
			@SuppressWarnings("unchecked")
			final List<CategoryResponseDTO> allCategories = arrowheadService.consumeServiceHTTP(List.class, HttpMethod.valueOf(orchestrationResult.getMetadata().get(CategoryConsumerConstants.HTTP_METHOD)),
					orchestrationResult.getProvider().getAddress(), orchestrationResult.getProvider().getPort(), orchestrationResult.getServiceUri(),
					getInterface(), token, null, new String[0]);
			printOut(allCategories);
/*
			logger.info("Get only blue cars:");
			final String[] queryParamColor = {orchestrationResult.getMetadata().get(CategoryConsumerConstants.REQUEST_PARAM_KEY_), "blue"};
			@SuppressWarnings("unchecked")
			final List<CarResponseDTO> blueCars = arrowheadService.consumeServiceHTTP(List.class, HttpMethod.valueOf(orchestrationResult.getMetadata().get(CarConsumerConstants.HTTP_METHOD)),
					orchestrationResult.getProvider().getAddress(), orchestrationResult.getProvider().getPort(), orchestrationResult.getServiceUri(),
					getInterface(), token, null, queryParamColor);
			printOut(blueCars);
*/
		}


	}

	//=================================================================================================
	// assistant methods

	//-------------------------------------------------------------------------------------------------
	private String getInterface() {
		return sslProperties.isSslEnabled() ? CategoryConsumerConstants.INTERFACE_SECURE : CategoryConsumerConstants.INTERFACE_INSECURE;
	}

	//-------------------------------------------------------------------------------------------------
	private void validateOrchestrationResult(final OrchestrationResultDTO orchestrationResult, final String serviceDefinitin) {
		if (!orchestrationResult.getService().getServiceDefinition().equalsIgnoreCase(serviceDefinitin)) {
			throw new InvalidParameterException("Requested and orchestrated service definition do not match");
		}

		boolean hasValidInterface = false;
		for (final ServiceInterfaceResponseDTO serviceInterface : orchestrationResult.getInterfaces()) {
			if (serviceInterface.getInterfaceName().equalsIgnoreCase(getInterface())) {
				hasValidInterface = true;
				break;
			}
		}
		if (!hasValidInterface) {
			throw new InvalidParameterException("Requested and orchestrated interface do not match");
		}
	}

	//-------------------------------------------------------------------------------------------------
	private void printOut(final Object object) {
		System.out.println(Utilities.toPrettyJson(Utilities.toJson(object)));
	}
}
