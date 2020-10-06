package eu.arrowhead.electricity.operator;

public class CategoryConsumerConstants {

	//=================================================================================================
	// members

	public static final String BASE_PACKAGE = "eu.arrowhead";

	public static final String INTERFACE_SECURE = "HTTPS-SECURE-JSON";
	public static final String INTERFACE_INSECURE = "HTTP-INSECURE-JSON";
	public static final String HTTP_METHOD = "http-method";

	public static final String CREATE_CATEGORY_SERVICE_DEFINITION = "create-category";
	public static final String GET_CATEGORY_SERVICE_DEFINITION = "get-category";
	public static final String REQUEST_PARAM_KEY_NAME = "request-param-name";
	public static final String REQUEST_PARAM_KEY_VALUE = "request-param-value";

	//=================================================================================================
	// assistant methods

	//-------------------------------------------------------------------------------------------------
	private CategoryConsumerConstants() {
		throw new UnsupportedOperationException();
	}

}
