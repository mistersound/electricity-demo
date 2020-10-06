package eu.arrowhead.demo.electricity.provider;

public class ElectricityProviderConstants {
	
	//=================================================================================================
	// members
	
	public static final String BASE_PACKAGE = "eu.arrowhead";
	
	public static final String CREATE_CATEGORY_SERVICE_DEFINITION = "create-category";
	public static final String GET_CATEGORY_SERVICE_DEFINITION = "get-category";
	public static final String INTERFACE_SECURE = "HTTPS-SECURE-JSON";
	public static final String INTERFACE_INSECURE = "HTTP-INSECURE-JSON";
	public static final String HTTP_METHOD = "http-method";
	public static final String CATEGORY_URI = "/category";
	public static final String BY_ID_PATH = "/{id}";
	public static final String PATH_VARIABLE_ID = "id";
	public static final String REQUEST_PARAM_KEY_NAME = "request-param-category";
	public static final String REQUEST_PARAM_NAME= "name";
	public static final String REQUEST_PARAM_KEY_VALUE = "request-param-value";
	public static final String REQUEST_PARAM_VALUE = "value";
	
	//=================================================================================================
	// assistant methods

	//-------------------------------------------------------------------------------------------------
	private ElectricityProviderConstants() {
		throw new UnsupportedOperationException();
	}
}
