package transportClient;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.SortOrder;

import org.apache.lucene.queryparser.flexible.core.builders.QueryBuilder;
import static org.elasticsearch.index.query.QueryBuilders.*;
import org.apache.lucene.search.TermQuery;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.action.bulk.byscroll.BulkByScrollResponse;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.index.reindex.DeleteByQueryAction;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import twitter4j.JSONArray;
import twitter4j.JSONException;
import twitter4j.JSONObject;

public class Transport {

	public static void cenas() throws UnknownHostException{
		
		
		
		TransportClient client =new PreBuiltTransportClient(Settings.EMPTY)
				.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));
		
		/*Client client = new TransportClient()
                .addTransportAddress(new InetSocketTransportAddress(
                        "143.79.236.xxx",
                        9300));*/
		//DeleteIndexResponse deleteResponse = client.admin().indices().delete(new DeleteIndexRequest("posts")).actionGet();
		//client.admin().indices().prepareCreate("posts").get();
		
		TermQueryBuilder qb = termQuery("_type", "doc");
		
		SearchResponse scrollResp = client.prepareSearch("posts")
				.addSort(FieldSortBuilder.DOC_FIELD_NAME, org.elasticsearch.search.sort.SortOrder.ASC)
				.setScroll(new TimeValue(60000))
		        .setQuery(qb)
		        .setSize(100).get();
		
		do {
		    for (SearchHit hit : scrollResp.getHits().getHits()) {
		        System.out.println("hit "+hit);
		        System.out.println("hit "+hit.docId());
		        System.out.println("hit "+hit.getId());
		        System.out.println("hit "+hit.getIndex());
		        System.out.println("hit "+hit.getSourceAsString());
		        System.out.println("hit "+hit.getType());
		        System.out.println("hit "+hit.getSource().get("user"));
		    }

		    scrollResp = client.prepareSearchScroll(scrollResp.getScrollId()).setScroll(new TimeValue(60000)).execute().actionGet();
		} while(scrollResp.getHits().getHits().length != 0); 

		//SearchResponse responde = client.prepareSearch("teste21").get();
		//System.out.println( "3" );
		client.close();
		//System.out.println( client.nodeName());
		
	}
	
	public static TransportClient initTransportClient() throws UnknownHostException{
		
			TransportClient client =new PreBuiltTransportClient(Settings.EMPTY)
					.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));
			
			
			
			//Settings settings = Settings.builder()
			 //       .put("client.transport.sniff", true).build();
			//client = new PreBuiltTransportClient(settings);
		
		return client;
	}
	
	public static void closeTransportClient(TransportClient client) throws UnknownHostException{
		client.close();
	}
	
	//GET by ScrollAPI all docs by Index and Term
	
	public static List<JSONObject> getListOfJsonDocs(String index,String term,String text) throws UnknownHostException, JSONException{
		List<JSONObject> listJson = new ArrayList<>();
		TransportClient client = initTransportClient();
		
		TermQueryBuilder qb = termQuery(term, text);
		
		SearchResponse scrollResp = client.prepareSearch(index)
				.addSort(FieldSortBuilder.DOC_FIELD_NAME, org.elasticsearch.search.sort.SortOrder.ASC)
				.setScroll(new TimeValue(60000))
		        .setQuery(qb)
		        .setSize(100).get();
		
		do {
		    for (SearchHit hit : scrollResp.getHits().getHits()) {
		    	JSONObject item = new JSONObject(hit.getSourceAsString());
		        //System.out.println("hit "+hit.getSource().get("user"));
		    	listJson.add(item);
		    }

		    scrollResp = client.prepareSearchScroll(scrollResp.getScrollId()).setScroll(new TimeValue(60000)).execute().actionGet();
		} while(scrollResp.getHits().getHits().length != 0);
		
		closeTransportClient(client);
		return listJson;
	}
	
	//GET by ScrollAPI all docs by Index and Term
	
	public static List<String> getListOfPostsDocs(String index,String term,String text) throws UnknownHostException, JSONException{
		List<String> listPosts = new ArrayList<>();
		TransportClient client = initTransportClient();
		Integer count=0;
			
		TermQueryBuilder qb = termQuery(term, text);
			
		SearchResponse scrollResp = client.prepareSearch(index)
				.addSort(FieldSortBuilder.DOC_FIELD_NAME, org.elasticsearch.search.sort.SortOrder.ASC)
				.setScroll(new TimeValue(60000))
			    .setQuery(qb)
			    .setSize(100).get();
			
		do {
			   for (SearchHit hit : scrollResp.getHits().getHits()) {
			   	String post =(String) hit.getSource().get("text");
			    //System.out.println("hit "+hit.getSource().get("text"));
			   	count++;
			   	listPosts.add(post);
			   }

		    scrollResp = client.prepareSearchScroll(scrollResp.getScrollId()).setScroll(new TimeValue(60000)).execute().actionGet();
		} while(scrollResp.getHits().getHits().length != 0);
			
		closeTransportClient(client);
		System.out.println("Count: "+count);
		return listPosts;
	}
		
		
	//Delete a index
	public static void deleteIndexDocs(String index) throws UnknownHostException, JSONException{
		
		try {
			TransportClient client=initTransportClient();
			DeleteIndexResponse deleteResponse = client.admin().indices().delete(new DeleteIndexRequest(index)).actionGet();
			client.admin().indices().prepareCreate(index).get();
		
			System.out.println("Index: "+index+" deleted");
			
			closeTransportClient(client);
		} catch (Exception e) {
			System.out.println("ERROR delete index: "+index+"-> "+e);
		}
	}
	
	//delete a single document
	public static void deleteDocById(String index,String type,String id) throws UnknownHostException, JSONException{
			
			try {
				TransportClient client=initTransportClient();
				DeleteResponse response = client.prepareDelete(index, type, id) 
						.execute() 
						.actionGet();
			
				System.out.println("Doc with INDEX-TYPE-ID: "+index+"-"+type+"-"+id+" deleted");
				
				closeTransportClient(client);
			} catch (Exception e) {
				System.out.println("Doc with INDEX-TYPE-ID: "+index+"-"+type+"-"+id+"-> "+e);
			}
			
		}
	
	//delete a single document
	public static void deleteDocByQuery(String index,String type,String text) throws UnknownHostException, JSONException{
				
			try {
			
				TransportClient client=initTransportClient();
				BulkByScrollResponse response =
					    DeleteByQueryAction.INSTANCE.newRequestBuilder(client)
					        .filter(QueryBuilders.matchQuery(type, text)) 
					        .source(index)                                  
					        .get();                                             

					long deleted = response.getDeleted(); 
				
				System.out.println("Doc deleted: "+deleted);
					
				closeTransportClient(client);
			} catch (Exception e) {
				System.out.println("ERROR Doc with INDEX-TYPE: "+index+"-"+type+"-"+"-> "+e);
			}
				
	}
}
