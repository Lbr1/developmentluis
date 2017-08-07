package restClient;

import java.io.IOException;
import java.util.Collections;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.entity.ContentType;
import org.apache.http.message.BasicHeader;
import org.apache.http.nio.entity.NStringEntity;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import java.util.Map;

public class restAPI {
	
	
	public static void cenas() throws IOException{
		RestClient restClient = RestClient.builder(
				new HttpHost("localhost", 9200, "http"),
				new HttpHost("localhost", 9201, "http")).build();
		
		///restClient.close();
		RestClientBuilder builder = RestClient.builder(new HttpHost("localhost", 9200, "http"));
		Header[] defaultHeaders = new Header[]{new BasicHeader("header", "value")};
		builder.setDefaultHeaders(defaultHeaders);
		builder.setMaxRetryTimeoutMillis(10000);
		
		Response response2 = restClient.performRequest("GET","/");
		Response response = restClient.performRequest("GET", "game",defaultHeaders);
		
		Map<String, String> params = Collections.singletonMap("pretty", "true");
		Response response3 = restClient.performRequest("GET", "/game", params); 
		
		
		Map<String, String> params2 = Collections.emptyMap();
		String jsonString = "{" +
		            "\"user\":\"Luisinho\"," +
		            "\"postDate\":\"1988\"," +
		            "\"message\":\"esta a começar a andar\"" +
		        "}";
		HttpEntity entity = new NStringEntity(jsonString, ContentType.APPLICATION_JSON);
		Response response4 = restClient.performRequest("PUT", "/posts/doc/2", params, entity);
		
		
		System.out.println(response4);
		System.out.println(response4.toString());
		System.out.println(response4.getClass());
		System.out.println(response4.getEntity());
		System.out.println(response4.getHeaders());
		System.out.println(response4.getHost());
		System.out.println(response4.getRequestLine());
		System.out.println(response4.getStatusLine());
	
	}
	/*
	 	RestClient client = ...;
		String actionMetaData = String.format("{ \"index\" : { \"_index\" : \"%s\", \"_type\" : \"%s\" } }%n", index, type);
		
		List<String> bulkData = ...; // a list of your documents in JSON strings    
		StringBuilder bulkRequestBody = new StringBuilder();
		for (String bulkItem : bulkData) {
		    bulkRequestBody.append(actionMetaData);
		    bulkRequestBody.append(bulkItem);
		    bulkRequestBody.append("\n");
		}
		HttpEntity entity = new NStringEntity(bulkRequestBody.toString(), ContentType.APPLICATION_JSON);
		try {
		    Response response = client.performRequest("POST", "/your_index/your_index/_bulk", Collections.emptyMap(), entity);
		    return response.getStatusLine().getStatusCode() == HttpStatus.SC_OK;
		} catch (Exception e) {
		    // do something
		} 
	 * */
	
	/*		
	// Synchronous variants
	Response performRequest(String method, String endpoint,
	                        Header... headers)
	    throws IOException;

	Response performRequest(String method, String endpoint,
	                        Map<String, String> params, Header... headers)
	    throws IOException;

	Response performRequest(String method, String endpoint,
	                        Map<String, String> params,
	                        HttpEntity entity,
	                        Header... headers)
	    throws IOException;

	Response performRequest(String method, String endpoint,
	                        Map<String, String> params,
	                        HttpEntity entity,
	                        HttpAsyncResponseConsumerFactory responseConsumerFactory,
	                        Header... headers)
	    throws IOException;

	// Asynchronous variants
	void performRequestAsync(String method, String endpoint,
	                         ResponseListener responseListener,
	                         Header... headers);

	void performRequestAsync(String method, String endpoint,
	                         Map<String, String> params,
	                         ResponseListener responseListener,
	                         Header... headers);

	void performRequestAsync(String method, String endpoint,
	                         Map<String, String> params,
	                         HttpEntity entity,
	                         ResponseListener responseListener,
	                         Header... headers);

	void performRequestAsync(String method, String endpoint,
	                         Map<String, String> params,
	                         HttpEntity entity,
	                         HttpAsyncResponseConsumerFactory responseConsumerFactory,
	                         ResponseListener responseListener,
	                         Header... headers);
	                         
	                         
	******  Request Arguments ********
	
	The following are the arguments accepted by the different methods:

	method
	the http method or verb
	endpoint
	the request path, which identifies the Elasticsearch API to call (e.g. /_cluster/health)
	params
	the optional parameters to be sent as querystring parameters
	entity
	the optional request body enclosed in an org.apache.http.HttpEntity object
	responseConsumerFactory
	the optional factory that is used to create an org.apache.http.nio.protocol.HttpAsyncResponseConsumer callback instance per request attempt. Controls how the response body gets streamed from a non-blocking HTTP connection on the client side. When not provided, the default implementation is used which buffers the whole response body in heap memory, up to 100 MB
	responseListener
	the listener to be notified upon asynchronous request success or failure
	headers
	optional request headers
	«  Initialization  
	*/
}
