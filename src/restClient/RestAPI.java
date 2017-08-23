package restClient;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.nio.entity.NStringEntity;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteRequestBuilder;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.ResponseListener;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonSyntaxException;
import POJO.TweetsPosts;
import twitter4j.JSONArray;
import twitter4j.JSONException;
import twitter4j.JSONObject;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

public class RestAPI {
	
	
	public static void cenas2() throws IOException, JSONException{
		//RestClient restClient = RestClient.builder(
		//		new HttpHost("localhost", 9200, "http"),
		//		new HttpHost("localhost", 9201, "http")).build();
		
		
		
		final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
		credentialsProvider.setCredentials(AuthScope.ANY,
		        new UsernamePasswordCredentials("luis", "elastic"));

		RestClient restClient = RestClient.builder(new HttpHost("localhost", 9200))
		        .setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
		            @Override
		            public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder) {
		                return httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
		            }
		        })
		        .build();
		
		
		///restClient.close();
		
		
		
		
		RestClientBuilder builder = RestClient.builder(new HttpHost("localhost", 9200, "http"));
		Header[] defaultHeaders = new Header[]{new BasicHeader("header", "value")};
		builder.setDefaultHeaders(defaultHeaders);
		builder.setMaxRetryTimeoutMillis(10000);
		
		//Response response2 = restClient.performRequest("GET","/");
		//Response response = restClient.performRequest("GET", "game",defaultHeaders);
		
		Map<String, String> params = Collections.singletonMap("pretty", "true");
		//Response response3 = restClient.performRequest("GET", "/game", params); 
		
		//create a json to PUT at the ES
		//Map<String, String> params2 = Collections.emptyMap();
		//String jsonString = "{" +
		//            "\"user\":\"Luisinho\"," +
		//            "\"postDate\":\"1988\"," +
		//            "\"message\":\"esta a começar a andar\"" +
		///        "}";
		//HttpEntity entity = new NStringEntity(jsonString, ContentType.APPLICATION_JSON);
		
		
		
		//Response response4 = restClient.performRequest("PUT", "/posts/doc/2", params, entity);
	
		//Response response6 = restClient.performRequest("GET", "/super/_search/size=100",
		//		defaultHeaders);
		//System.out.println(EntityUtils.toString(response6.getEntity()));
		
		
		
		//Response response51 = restClient.performRequest("GET", "/posts/_search");
		//Gson gsonPojo = new Gson();
		//String stringPojo =EntityUtils.toString(response51.getEntity());
		//List<TweetsPosts> pojoList = new ArrayList<>();
		
		//Map<String, String> paramMap = new HashMap<String, String>();
		//paramMap.put("q", "user:Kimchy");
		//paramMap.put("pretty", "true");
		
		HttpEntity entity1 = new NStringEntity(
				 "{\"from\" : 0, \"size\" :"+1+",\n" +
					"    \"query\" : {\n" +
					"    \"match_all\": { } \n" +
					"} \n"+
				"}", ContentType.APPLICATION_JSON);
		
		
		
		
	
									                                                               
	Response response = restClient.performRequest("GET", "/posts/_search?scroll=1m",Collections.singletonMap("pretty", "true"),entity1);
	
	//System.out.println(EntityUtils.toString(response.getEntity()));
	String stringJson = EntityUtils.toString(response.getEntity());
	JSONObject json =new JSONObject();
	JSONObject ddd= new JSONObject(stringJson);
	String id = ddd.getString("_scroll_id");
	System.out.println("Scroll_id "+id+"\n");
	
	//HttpEntity entity2 = new NStringEntity(
	//		 "{\"scroll_id\" : \""+ id+
	//		"\"}\n", ContentType.APPLICATION_JSON);
	//System.out.println("-> "+"{\"scroll_id\" : \""+ id+
	//		"\"}\n");
	//Response response2 = restClient.performRequest("GET", "/posts/_search",Collections.singletonMap("pretty", "true"),entity2);
	//System.out.println("SECOND "+EntityUtils.toString(response2.getEntity()));
	/*List<JSONObject> jsonList = new ArrayList<>();
	JSONObject jsonListFinal = new JSONObject();
	JSONObject json =new JSONObject(EntityUtils.toString(response.getEntity()));
	//JSONObject head =new JSONObject();
	//JSONArray array =new JSONArray();
	JSONObject obj = json.getJSONObject("hits");
	JSONArray arr = obj.getJSONArray("hits");
	for (int i = 0; i < arr.length(); i++)
	{

		JSONObject source =new JSONObject(arr.getJSONObject(i).getString("_source"));
		JSONObject item = new JSONObject();
		item.put("ID", source.getLong("id"));
		item.put("TweetLang", source.getString("lang"));
		item.put("Text", source.getString("text"));
		JSONObject user = source.getJSONObject("user");
		item.put("Screen_name", user.getString("screen_name"));
		item.put("Language", user.getString("lang"));
		item.put("Name", user.getString("name"));
		item.put("Location", user.getString("location"));
		jsonList.add(item);
		}
	jsonListFinal.put("Tweet", jsonList);
		
	
	JSONArray arra =jsonListFinal.getJSONArray("Tweet");
	for (int i = 0; i < arra.length(); i++)
	{
		System.out.println(arra.getJSONObject(i).getString("Language"));
	}
	
	
	
/*
		JSONObject json =new JSONObject(stringPojo);
		JSONObject obj = json.getJSONObject("hits");
		System.out.println("objeto -> "+obj);
		System.out.println("Teste hits -> max_score "+obj.getString("max_score"));
		JSONArray arr = obj.getJSONArray("hits");
		for (int i = 0; i < arr.length(); i++)
		{
			TweetsPosts tweetPojo = new TweetsPosts();
			tweetPojo = gsonPojo.fromJson(arr.getJSONObject(i).toString(), TweetsPosts.class);
			pojoList.add(tweetPojo);
		    //JSONObject source =new JSONObject(arr.getJSONObject(i).getString("_source"));
		    //textList.add(source.getString("message"));
		    
			
		}
		
		for (TweetsPosts tp: pojoList)
		{
			System.out.println(tp.get_source().getMessage());
		}
		//System.out.println(EntityUtils.toString(response51.getEntity()));
		/*
		JSONObject json2 =new JSONObject('{' +
	            "\"user\":\"Luisinho\"," +
	            "\"postDate\":\"1988\"," +
	            "\"message\":\"esta a começar a andar\"" +
	        '}');
		*/

	
	}
	
	//Search all the documents using Query DSL for date

	public Response SearchAllDocQueryDSLDate(String indexDir,String fromDate,String toDate,RestClient restClient) throws IOException{
		HttpEntity entity1 = new NStringEntity(
				 "{\n" +
				"    \"query\" : {\n" +
				"    \"range\" : {\n"+
            "\"post_date\" : { \"from\" : \""+fromDate+"\", \"to\" : \""+toDate+"\" }"+
        "} \n" +
				"} \n"+
				"}", ContentType.APPLICATION_JSON);
							                                                               
				Response response = restClient.performRequest("GET", "/"+indexDir+"/_search",Collections.singletonMap("pretty", "true"),
							                                                           entity1);
		return response;
	}			
	// create a http dir /index/type/${id}
	
	public String createIndexDir(String index, String type, String id){
	    String indexEndPoint = new StringBuffer()
	            .append("/").append(index)
	            .append("/").append(type)
	            .append("/").append(id)
	            .toString();
    	return indexEndPoint;
	}
	
	//PUT more than 1 tweet -  Asynchronous mode 

	public void putListOfTweetsId(String indexDir,HttpEntity[] entityArray,RestClient restClient) throws IOException, InterruptedException{
		int numRequests = entityArray.length;
		final CountDownLatch latch = new CountDownLatch(numRequests);
		for (int i = 0; i < numRequests; i++) {
			restClient.performRequestAsync(
			"PUT",
			indexDir + i,
			Collections.<String, String>emptyMap(),
			entityArray[i],
			new ResponseListener() {
			@Override
			public void onSuccess(Response response) {
				System.out.println(response);
				latch.countDown();
			}
			@Override
			public void onFailure(Exception exception) {
			     System.out.println(exception.getMessage());
				 latch.countDown();
			}
			}
		    );
		}
		//wait for completion of all requests
		latch.await();
	}		
	
	//PUT more than 1 tweet without ID-  Asynchronous mode 

		public static void putListOfTweets(String index,String type,HttpEntity[] entityArray) throws IOException, InterruptedException{
			RestClient restClient = initAPI();
			int numRequests = entityArray.length;
			final CountDownLatch latch = new CountDownLatch(numRequests);
			for (int i = 0; i < numRequests; i++) {
				restClient.performRequestAsync(
				"POST",
				"/"+index+"/"+type,
				Collections.<String, String>emptyMap(),
				entityArray[i],
				new ResponseListener() {
				@Override
				public void onSuccess(Response response) {
					System.out.println(response);
					latch.countDown();
				}
				@Override
				public void onFailure(Exception exception) {
				     System.out.println(exception.getMessage());
					 latch.countDown();
				}
				}
			    );
			}
			//wait for completion of all requests
			latch.await();
			closeAPI(restClient);
		}		
	//Search the document using Query Params

			
	public static Response SearchDoc(String key,String value,String indexDir) throws IOException{
		RestClient restClient = initAPI();
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("q", key+":"+value);
		paramMap.put("pretty", "true");
		                               
		Response response = restClient.performRequest("GET", indexDir+"/_search",paramMap);
		closeAPI(restClient);
		return response;
	}	
	//Search all the documents using Query DSL

		public static Response SearchAllDocQueryDSL(String indexDir) throws IOException{
			RestClient restClient = initAPI();
			HttpEntity entity1 = new NStringEntity(
					 "{\n" +
					"    \"query\" : {\n" +
					"    \"match_all\": { } \n" +
					"} \n"+
					"}", ContentType.APPLICATION_JSON);
						                                                               
					Response response = restClient.performRequest("GET", indexDir+"/_search",Collections.singletonMap("pretty", "true"),
						                                                           entity1);
					
			closeAPI(restClient);
			return response;
		}		
	
	//Search all the documents using Query DSL with size

	public static Response SearchAllDocQueryDSLSize(String indexDir,Integer size) throws IOException{
		RestClient restClient = initAPI();
		HttpEntity entity1 = new NStringEntity(
			 "{\"from\" : 0, \"size\" :"+size+",\n" +
				"    \"query\" : {\n" +
				"    \"match_all\": { } \n" +
				"} \n"+
			"}", ContentType.APPLICATION_JSON);
								                                                               
			Response response = restClient.performRequest("GET", "/"+indexDir+"/_search",Collections.singletonMap("pretty", "true"),
								                                                           entity1);
			closeAPI(restClient);
			return response;
	}		
	
	//Search all the documents using Query DSL with size

		public static Response SearchAllDocQueryDSLSizeExists(String indexDir,String key, String value) throws IOException{
			RestClient restClient = initAPI();
			
			GetRequest getRequest = new GetRequest("index", "type", "id");
			getRequest.storedFields(new String[0]);
			Map<String, String> paramMap = new HashMap<String, String>();
			paramMap.put("q", key+":"+value);
			paramMap.put("pretty", "true");	                                                               
			Response response = restClient.performRequest("GET", "/"+indexDir+"/tweet/_search/exists",paramMap);
			closeAPI(restClient);
			return response;
		}		
		
	//Search the document using Query DSL

	public static Response SearchDocQueryDSL(String key,String value,String indexDir) throws IOException{
		RestClient restClient = initAPI();
		HttpEntity entity1 = new NStringEntity(
				 "{\n" +
				"    \"query\" : {\n" +
				"    \"match\": { \""+key+"\":\""+value+"\"} \n" +
				"} \n"+
				"}", ContentType.APPLICATION_JSON);
					                                                               
				Response response = restClient.performRequest("GET", "/"+indexDir+"/_search",Collections.singletonMap("pretty", "true"),
					                                                           entity1);
		closeAPI(restClient);
		return response;
	}	

	//get a String with documents jsonformat from a index 
	
	public static String getFromIndex(String index) throws IOException{
		RestClient restClient = initAPI();
		Response response = restClient.performRequest("GET", "/"+index+"/_search");
		String stringResponse =EntityUtils.toString(response.getEntity());
		closeAPI(restClient);
		return stringResponse;
	}
	
	//get a String with documents jsonformat from a index with type
	
	public static String getFromIndexAndType(String index,String type,int size) throws IOException{
		RestClient restClient = initAPI();
		HttpEntity entity1 = new NStringEntity(
				 "{\"from\" : 0, \"size\" :"+size+",\n" +
					"    \"query\" : {\n" +
					"    \"match_all\": { } \n" +
					"} \n"+
				"}", ContentType.APPLICATION_JSON);
									                                                               
				Response response = restClient.performRequest("GET", "/"+index+"/"+type+"/_search",Collections.singletonMap("pretty", "true"),
									                                                           entity1);
		closeAPI(restClient);
		return EntityUtils.toString(response.getEntity());
	}
	
	
	//set a list of headers
	public void setHeaders(RestClient restClient) throws IOException{
		RestClientBuilder builder = RestClient.builder(new HttpHost("localhost", 9200, "http"));
		Header[] defaultHeaders = new Header[]{new BasicHeader("header", "value")};
		builder.setDefaultHeaders(defaultHeaders);
		builder.setMaxRetryTimeoutMillis(10000);
	}
		
	// Return a POJO with main info
	public static List<TweetsPosts> getPojo(String response) throws JsonSyntaxException, JSONException{
		Gson gsonPojo = new Gson();
		List<TweetsPosts> pojoList = new ArrayList<>();//tem de ser criado como global
		
		JSONObject json =new JSONObject(response);
		JSONObject obj = json.getJSONObject("hits");
		
		JSONArray arr = obj.getJSONArray("hits");
		for (int i = 0; i < arr.length(); i++)
		{
			TweetsPosts tweetPojo = new TweetsPosts();
			tweetPojo = gsonPojo.fromJson(arr.getJSONObject(i).toString(), TweetsPosts.class);
			pojoList.add(tweetPojo);	
		}
		return pojoList;
	}
	//PUT a new doc without ID 
	
	public static Response putNewDoc(String jsonString,String indexDir,String type) throws IOException{
		RestClient restClient=initAPI();	
		Map<String, String> params = Collections.emptyMap();
			HttpEntity entity = new NStringEntity(jsonString, ContentType.APPLICATION_JSON);
			Response response= restClient.performRequest("POST", "/"+indexDir+"/"+type, params, entity);
		closeAPI(restClient);
		return response;
	}
	
	
	//PUT a new doc with ID
	
	public static Response putNewDocID(String jsonString,String index,String type, String id) throws IOException{
			RestClient restClient=initAPI();
			Map<String, String> params = Collections.emptyMap();
			HttpEntity entity = new NStringEntity(jsonString, ContentType.APPLICATION_JSON);
			Response response= restClient.performRequest("PUT", "/"+index+"/"+type+"/"+id, params, entity);
			closeAPI(restClient);
		return response;
	}
	
	//GET the posts from a index(group of documents)

	public static List<String> getPosts(String response) throws JSONException{
		List<String> textList = new ArrayList<>();
		//String response=EntityUtils.toString(response51.getEntity());
		JSONObject json =new JSONObject(response);
		JSONObject obj = json.getJSONObject("hits");
		JSONArray arr = obj.getJSONArray("hits");
		for (int i = 0; i < arr.length(); i++)
		{
		   
		    JSONObject source =new JSONObject(arr.getJSONObject(i).getString("_source"));
		    textList.add(source.getString("Text"));
		    
			
		}
		return textList;
	}
	
	//GET the List of POJO from a index(group of documents)

	public static List<TweetsPosts> getPojoFromIndex(String response) throws JSONException{
		List<TweetsPosts> pojoList = new ArrayList<>();
		Gson gsonPojo = new Gson();
		JSONObject json =new JSONObject(response);
		System.out.println(json+"\n");
		JSONObject obj = json.getJSONObject("hits");
		System.out.println("objeto -> "+obj);
		JSONArray arr = obj.getJSONArray("hits");
		for (int i = 0; i < arr.length(); i++)
		{

			//JSONObject source =new JSONObject(arr.getJSONObject(i).getString("_source"));
			TweetsPosts tweetPojo = new TweetsPosts();
			tweetPojo = gsonPojo.fromJson(arr.getJSONObject(i).toString(), TweetsPosts.class);
			pojoList.add(tweetPojo);
			}
			
			return pojoList;
		}
	
	//GET list of json Objets from a index(group of documents)

	public static List<JSONObject> getListJsonFromIndex(String response) throws JSONException{
		List<JSONObject> jsonList = new ArrayList<>();
		JSONObject jsonListFinal = new JSONObject();
		JSONObject json =new JSONObject(response);
		//JSONObject head =new JSONObject();
		//JSONArray array =new JSONArray();
		JSONObject obj = json.getJSONObject("hits");
		JSONArray arr = obj.getJSONArray("hits");
		for (int i = 0; i < arr.length(); i++)
		{
			JSONObject source =new JSONObject(arr.getJSONObject(i).getString("_source"));
			JSONObject item = new JSONObject();
			item.put("id", source.getLong("id"));
			item.put("lang", source.getString("lang"));
			item.put("text", source.getString("text"));
			JSONObject user = source.getJSONObject("user");
			item.put("screen_name", user.getString("screen_name"));
			item.put("name", user.getString("name"));
			item.put("location", user.getString("location"));
			jsonList.add(item);
			}
			jsonListFinal.put("Tweet", jsonList);
			return jsonList;
	}
	
//GET a JSON with ObjetsFinal from a index(group of documents)

	public static JSONObject getJsonFromIndex(String response) throws JSONException{
		List<JSONObject> jsonList = new ArrayList<>();
		JSONObject jsonListFinal = new JSONObject();
		JSONObject json =new JSONObject(response);
		//JSONObject head =new JSONObject();
		//JSONArray array =new JSONArray();
		JSONObject obj = json.getJSONObject("hits");
		JSONArray arr = obj.getJSONArray("hits");
		for (int i = 0; i < arr.length(); i++)
		{

			JSONObject source =new JSONObject(arr.getJSONObject(i).getString("_source"));
			JSONObject item = new JSONObject();
			item.put("id", source.getLong("id"));
			item.put("lang", source.getString("lang"));
			item.put("text", source.getString("text"));
			JSONObject user = source.getJSONObject("user");
			item.put("screen_name", user.getString("screen_name"));
			item.put("name", user.getString("name"));
			item.put("location", user.getString("location"));
			jsonList.add(item);
			}
			jsonListFinal.put("Tweet", jsonList);
		return jsonListFinal;
	}
	
	//Create a httpEntiy from string
	public static HttpEntity createHttpEntity(String id,String tweetLang, String text, String screenName,
		String name, String location) throws JSONException{
		
		JSONObject obj = new JSONObject();

		obj.put("id", id);
		obj.put("lang", tweetLang);
		obj.put("text", text);
		obj.put("screen_name", screenName);
		obj.put("name", name);
		obj.put("location", location);

		// etc.

		final String jsonString = obj.toString();
		/*
		String jsonString = "{" +
	            "\"ID\":\""+id+"\"," +
	           "\"TweetLang\":\""+tweetLang+"\"," +
	           "\"Text\":\""+text+"\","+
	           "\"Screen_name\":\""+screenName+"\","+
	           "\"Language\":\""+language+"\","+
	           "\"Name\":\""+name+"\","+
	          "\"Location\":\""+location+"\"" +
	        "}";
		*/
		System.out.println("String -> "+jsonString);
		HttpEntity entity= new NStringEntity(
				 jsonString, ContentType.APPLICATION_JSON);
		return entity;	
	}
	//Create a string
		public static String createString(String id,String tweetLang, String text, String screenName,
				String name, String location) throws JSONException{
			
			JSONObject obj = new JSONObject();

			obj.put("id", id);
			obj.put("lang", tweetLang);
			obj.put("text", text);
			obj.put("screen_name", screenName);
			obj.put("name", name);
			obj.put("location", location);

			// etc.

			final String jsonString = obj.toString();
			
			
			return jsonString;	
		}
		
//Read a JSON Finale

	public static void ReadJson(JSONObject json) throws JSONException{
		try {
			JSONArray arr = json.getJSONArray("Tweet");
			for (int i = 0; i < arr.length(); i++)
			{
				System.out.println(arr.getJSONObject(i).getString("Language"));
			}
		} catch (Exception e) {
			System.out.println("ERROR :"+e);
		}
		
			
	}	
		
	
	//init the rest client
	public static  RestClient initAPI(){
		RestClient restClient = RestClient.builder(
				new HttpHost("localhost", 9200, "http"),
				new HttpHost("localhost", 9201, "http")).build();
		return restClient;
	}
	
	// close the restClient
	public static void closeAPI(RestClient client) throws IOException{
		client.close();
	}
			
}
