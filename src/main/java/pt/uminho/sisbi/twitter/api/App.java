package main.java.pt.uminho.sisbi.twitter.api;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.concurrent.TimeUnit;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.entity.ContentType;
import org.apache.http.nio.entity.NStringEntity;
import org.apache.http.util.EntityUtils;
import org.apache.lucene.index.MergePolicy.OneMergeProgress.PauseReason;
import org.apache.lucene.store.SleepingLockWrapper;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.transport.TransportClient;

import com.google.gson.Gson;

import POJO.Tuple;
import mongoDB.MongoDB;
import pt.uminho.sisbi.twitter.api.TweetAPI;
import restClient.RestAPI;
import transportClient.Transport;
import twitter4j.*;
import twitter4j.auth.AccessToken;
import twitter4j.conf.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws TwitterException, IOException, JSONException, IllegalStateException, InterruptedException
    {
    	
    	//TweetAPI api = new TweetAPI();
    	
        System.out.println( "Hello World!" );
        System.out.println( "Loading ...." );
        
        
        List<String> lista = new ArrayList<>();
      //Consumer Key
        String consumerKey = "9Q0DpYL9vJtZcuxNinsA1RjWn";
        //Consumer Secret
        String consumerSecret = "qohM464rRWVYXcNPmrOI7qhkqO1HJHjtGDXkxl0MsLTMdr7AAw";
        //Access Token
        String accessToken = "2841171466-dL9I79GWTG6QKEZW5Swy3e455EXermjkDGycTng";
        //Access Token Secret
        String accessTokenSecret = "tccuixlMuOK7r4H2Eu7jWVa5MDlp9FOohqkX3nbjSNU8N";
        
      //Instantiate a re-usable and thread-safe factory
        TwitterFactory twitterFactory = new TwitterFactory();
      //Instantiate a new Twitter instance
        Twitter twitter = twitterFactory.getInstance();
      //setup OAuth Consumer Credentials
        twitter.setOAuthConsumer(consumerKey, consumerSecret);
      //setup OAuth Access Token
        twitter.setOAuthAccessToken(new AccessToken(accessToken, accessTokenSecret));
        
        
        
        
        
        Response res=RestAPI.existsDoc("sporting", "90011023513796984");

        System.out.println(res.getStatusLine());

        //menuP();
        //MongoDB.exem();
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        //JSONObject obj=new JSONObject();
        //obj=TweetAPI.newTweetsJson(twitter);

        //lista=TweetAPI.getTextFromJson(obj);
        
		//for (String str: lista)
		//{
		   
		//	System.out.println("-> "+str+"\n");
		    
			
		//}
        
        List<String> idList= new ArrayList<>();
        List<HttpEntity> httpList=new ArrayList<>();
        String jsonString = "{" +
        		            "\"user\":\"Luisinho1\"," +
        		           "\"postDate\":\"198\"," +
        		          "\"message\":\"esta a começar a andar1\"" +
        		        "}";
        
        String jsonString2 = "{" +
	            "\"user\":\"Luisinho2\"," +
	           "\"postDate\":\"1988\"," +
	          "\"message\":\"esta a começar a andar2\"" +
	        "}";
        
        String jsonString3 = "{" +
	            "\"user\":\"Luisinho3\"," +
	           "\"postDate\":\"988\"," +
	          "\"message\":\"esta a começar a andar3\"" +
	        "}";
        //Transport.deleteIndexDocs("asdasdafsw");
        //JSONObject item = new JSONObject(jsonString2);
        //JSONObject item2 = new JSONObject(jsonString);
        //JSONObject item3 = new JSONObject(jsonString3);
 
        
       
       //Transport.getListOfIndex();
       //httpList.add(entity4);
        
      //HttpEntity[] listaHttp = new HttpEntity[httpList.size()];
		      //listaHttp[0]=entity1;
		      //listaHttp[1]=entity2;
		      //listaHttp[1]=entity3;
       
      //RestAPI.putListOfTweets("asdasdafsw", "tweet", httpList.toArray(listaHttp));
		        //idList.add(item.getString("postDate"));
		        //idList.add(item2.getString("postDate"));
		        //idList.add(item3.getString("postDate"));
		        
		        //List<String> posts=new ArrayList<>();
		        //posts=Transport.getListOfPostsDocs("super","_type","tweet");
		       // for(String tw:posts){
		       // 	System.out.println(tw+"\n");
		       // }
        	  /*
              Tuple res2=new Tuple();

		       res2=TweetAPI.searchQueryV2(twitter);

		       System.out.println("Numero de Tweets "+ res2.getList().size());

		       for (Status tw : res2.getList()) {
		    	   System.out.println("data "+ tw.getCreatedAt());
		    	   System.out.println("ID "+ tw.getId());
		    	   System.out.println("");
		       }
		       
		       */
           
		       //Transport.addIndexWithDocs(res2.getIndex(),res2.getList());
		      /*  int count=0; ******************************************************************************
		        System.out.println("Status "+ status.size());
		        List<HttpEntity> listHttp=new ArrayList<>();
		        for (Status tweet : status) {
		        	//System.out.println("Iteracao "+ count++);
					//JSONObject item = new JSONObject();
		        	//item.put("ID", tweet.getId());	
		        	//System.out.println("ID"+ tweet.getId());
		        	//item.put("TweetLang", tweet.getLang());
		        	//System.out.println("TweetLang"+ tweet.getLang());
		        	//item.put("Text", tweet.getText());	
		        	//System.out.println("Text"+ tweet.getText());
		        	//item.put("Screen_name", tweet.getUser().getScreenName());		     					     			
		        	//item.put("Name", tweet.getUser().getName());
		        	//item.put("Location", tweet.getUser().getLocation());
					String ent=RestAPI.createString(Long.toString(tweet.getId()),tweet.getLang(), tweet.getText(),
							tweet.getUser().getScreenName(), tweet.getUser().getName(),
							tweet.getUser().getLocation());
					RestAPI.putNewDoc(ent,"asdasdafsw", "tweet");
					TimeUnit.SECONDS.sleep(3);
					//System.out.println("Ent "+ ent.getContentLength());
					//listHttp.add(ent);
					//System.out.println("listHttp "+ listHttp.size());
					//array.put(item);
				}*/
		        //HttpEntity[] httpArray = new HttpEntity[ listHttp.size() ];
		        //RestAPI.putListOfTweets("asdasdafsw", "tweet", listHttp.toArray(httpArray));
       // Transport.deleteDocByQuery("asdasdafsw", "Location", "Lisboa");
		       //RestClient restClient=RestAPI.initAPI(); 
       //RestAPI.putNewDoc(jsonString3, "posts","doc");
       //Response response=RestAPI.putNewDoc(jsonString3, "posts","file");
        //String res=RestAPI.getFromIndex("GET", "/posts/file/_search");
        //Response response=RestAPI.SearchDocQueryDSL("postDate", "988", "posts");
        //System.out.println(EntityUtils.toString(response.getEntity()));
       //System.out.println(res);
       //RestAPI.putNewDoc(jsonString3, "posts","file");
       //RestAPI.closeAPI(restClient);
       //Transport.deleteIndexDocs("asdasdafsw");
       //Transport.deleteDocByQuery("posts", "_type", "file");;
       // search for user and return a json
        /*
        System.out.println("Procurando.....");
        JSONObject jsonObj=new JSONObject();
        JSONObject jsonObj2=new JSONObject();
        jsonObj=TweetAPI.jSONforUser(twitter,"@lfrancobastos");
        jsonObj2=TweetAPI.jSONforUser(twitter,"@LB");
        System.out.println("Imprimindo.....");
        System.out.println(jsonObj);
        System.out.println(jsonObj2);
        /*
        
        
        //search for key and date
        
        /*
        List<Tweets> tweetsList = TweetAPI.searchQuerybyDate(twitter);
        for(Tweets tw:tweetsList){
        	System.out.println(tw);
        }
        */
        
        //search for key and date and return json
        /*
        System.out.println("Procurando.....");
        JSONObject jsonObj=new JSONObject();
        jsonObj=TweetAPI.jSONforKeyWord(twitter);
        System.out.println("Imprimindo.....");
        System.out.println(jsonObj);
        */
        
        
        
        
        
        
        
        
        
        /*System.out.println("O ScreenName do Twitter "+ twitter.getScreenName());
        System.out.println("Os Tweets do Twitter "+ twitter.tweets());
        System.out.println("Os Friends do Twiter "+ twitter.getFriendsIDs(1));
        System.out.println("ToString do Twiter "+ twitter.toString());
        System.out.println("Os Friends do Twiter "+ twitter.friendsFollowers());
        System.out.println("Account Settings "+ twitter.getAccountSettings());
        System.out.println("Languages "+ twitter.getLanguages());
        
        */
        //TweetAPI.cenas();
          
       //*****************get the recents tweets of my account*************
       /*
        List<Status> statuses = TweetAPI.newTweets(twitter);
        for (Status status : statuses) {
            System.out.println(status.getUser().getName()+ " -> " +
                               status.getText());
            //System.out.println("OVER 140 C -> " + status.getRetweetedStatus().getText());
            String urls= "https://twitter.com/" + status.getUser().getScreenName() 
            	    + "/status/" + status.getId();
            System.out.println("Post Url -> "+urls);
            System.out.println("Status getLocation -> "+status.getUser().getLocation());
            System.out.println("Number of Retweets -> "+status.getRetweetCount());
            System.out.println("Status getURL -> "+status.getUser().getURL());
            System.out.println("O getText -> "+ status.getText() );
            TweetAPI.linkifyTweet(status.getText());
            //System.out.println("NOVA-> "+ nova);
            System.out.println("\n");
            System.out.println("\n");
            
        }
        /*
        //************Send a message for a specific user***************
        //TweetAPI.SendMessage(twitter, "Olá Luís 222", "@asdasdafsw");
        */
      
        //System.out.println(TweetAPI.elasticJson(twitter));
        //lista = TweetAPI.elasticJson(twitter);
        //System.out.println(lista.getClass());
        //Iterator<String> listaIterator = lista.iterator();
		//while (listaIterator.hasNext()) {
			//String json = new Gson().toJson(listaIterator.next());
			//System.out.println("Aqui esta -> " +listaIterator.next());
			//System.out.println("Aqui esta -> " +json);
		//}
        //tratamento do ficheiro
       //TweetAPI.ficheiro(twitter, "got2", "Game of Thrones");
        //List<Status> statuses2 = TweetAPI.tweetsUtilizador(twitter, "@MotoGP");
        //System.out.println("OLHA OS TWEETS2: " + statuses2.toString());
        //System.out.println("OLHA OS TWEETS3: " + statuses2.size());
 
        
        
        //*******************Create a Tweet**********************
         
     // TweetAPI.updateStatus(twitter);
        
      
      //*******************Search Querys**********************
        
        //TweetAPI.searchQuery(twitter);
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
       //ir buscar x posts sem serem repetidos

       /* ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setOAuthConsumerKey("");
        cb.setOAuthConsumerSecret("");
        cb.setOAuthAccessToken("");
        cb.setOAuthAccessTokenSecret("");

        Twitter twitter = new TwitterFactory(cb.build()).getInstance();

        int pageno = 1;
        String user = "cnn";
        List statuses = new ArrayList();

        while (true) {

          try {

            int size = statuses.size(); 
            Paging page = new Paging(pageno++, 100);
            statuses.addAll(twitter.getUserTimeline(user, page));
            if (statuses.size() == size)
              break;
          }
          catch(TwitterException e) {

            e.printStackTrace();
          }
        }

        System.out.println("Total: "+statuses.size());
    }
    -------------------------------------------------------
    Query query = new Query("test");
	query.setCount(100);
	
	int searchResultCount;
	long lowestTweetId = Long.MAX_VALUE;
	
	do {
	    QueryResult queryResult = twitterInstance.search(query);
	
	    searchResultCount = queryResult.getTweets().size();
	
	    for (Status tweet : queryResult.getTweets()) {
	
	        // do whatever with the tweet
	
	        if (tweet.getId() < lowestTweetId) {
	            lowestTweetId = tweet.getId();
	            query.setMaxId(lowestTweetId);
	        }
	    }
	
	} while (searchResultCount != 0 && searchResultCount % 100 == 0);
	    */
}
    
    public static Twitter twitterToken(){
    	//Consumer Key
        String consumerKey = "9Q0DpYL9vJtZcuxNinsA1RjWn";
        //Consumer Secret
        String consumerSecret = "qohM464rRWVYXcNPmrOI7qhkqO1HJHjtGDXkxl0MsLTMdr7AAw";
        //Access Token
        String accessToken = "2841171466-dL9I79GWTG6QKEZW5Swy3e455EXermjkDGycTng";
        //Access Token Secret
        String accessTokenSecret = "tccuixlMuOK7r4H2Eu7jWVa5MDlp9FOohqkX3nbjSNU8N";
        
      //Instantiate a re-usable and thread-safe factory
        TwitterFactory twitterFactory = new TwitterFactory();
      //Instantiate a new Twitter instance
        Twitter twitter = twitterFactory.getInstance();
      //setup OAuth Consumer Credentials
        twitter.setOAuthConsumer(consumerKey, consumerSecret);
      //setup OAuth Access Token
        twitter.setOAuthAccessToken(new AccessToken(accessToken, accessTokenSecret));
        
    	return twitter;
    }
    public static void menuP() throws IOException, ParseException, JSONException, IllegalStateException, TwitterException, InterruptedException{
    	do {
            System.out.println("\n\n          DataMining Menu");
            System.out.println("--------------------------------------");
            System.out.println("--------------GET's-------------");
            System.out.println("--------------------------------------");
            System.out.println("1  - Show OPEN Index");
            System.out.println("2  - GET docs for Query");
            System.out.println("3  - GET ALL docs from Index");
            System.out.println("4  - GET X docs from Index");
            System.out.println("5  - GET POSTS from a Index");
            System.out.println("6  - GET X docs from Index and Type");
            System.out.println("7  - GET JSON with docs from Index and Type by ScrollAPI");
            System.out.println("8  - GET POSTS from Index and Type by ScrollAPI");
            System.out.println("--------------------------------------");
            System.out.println("--------------PUT's-------------");
            System.out.println("--------------------------------------");
            System.out.println("9  - PUT my tweets on elasticSearch");
            System.out.println("10 - PUT tweets from a USER on elasticSearch");
            System.out.println("11 - PUT X tweets with a keyword on elasticSearch");
            System.out.println("--------------------------------------");
            System.out.println("--------------DELETE's-------------");
            System.out.println("--------------------------------------");
            System.out.println("12 - DELETE an Index");
            System.out.println("13 - DELETE a Doc with an ID");
            System.out.println("14 - DELETE a Doc for Term");
            System.out.println("--------------------------------------");
            System.out.println("--------------EXIT-------------");
            System.out.println("--------------------------------------");
            System.out.println("0 - EXIT");
            System.out.print("\nSelect a Menu Option: ");
        try {
        	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String args = br.readLine();
            int input = Integer.parseInt(args); 
            String indexDir="";
            String key="";
            String value="";
            int size=0;
            Response res=null;
            List<String> textList = new ArrayList<>();
            List<JSONObject> listJson = new ArrayList<>();
            Twitter twitter=twitterToken();


            switch (input) {
            case 1:  System.out.println("\n\n ------ Index ------");
            		 Transport.getListOfIndex();
            		 System.out.println("\n\n");
                     break;
            case 2:  System.out.println("Insert the Index: \n");
					 indexDir = br.readLine();
					 System.out.println("Insert the key: \n");
					 key = br.readLine();
					 System.out.println("Insert the text: \n");
					 value = br.readLine();
					 res=RestAPI.SearchDoc(key,value,indexDir);
					 System.out.println("Response-> "+EntityUtils.toString(res.getEntity()));
                     break;
            case 3:  System.out.println("Insert the Index: \n");
					 indexDir = br.readLine();
					 res=RestAPI.SearchAllDocQueryDSL(indexDir);
					 System.out.println("Response-> "+EntityUtils.toString(res.getEntity()));
					 break;
            case 4:  System.out.println("Insert the Index: \n");
					 indexDir = br.readLine();
					 System.out.println("Insert number of Documents: \n");
					 size=Integer.parseInt(br.readLine());
					 res=RestAPI.SearchAllDocQueryDSLSize(indexDir, size);
					 System.out.println("Response-> "+EntityUtils.toString(res.getEntity()));
					 break;
            case 5:  System.out.println("Insert the Index: \n");
					 indexDir = br.readLine();
					 System.out.println("Insert number of Documents: \n");
					 size=Integer.parseInt(br.readLine());
					 res=RestAPI.SearchAllDocQueryDSLSize(indexDir, size);
					 textList=RestAPI.getPosts(EntityUtils.toString(res.getEntity()));
					 for(String st:textList){
						 System.out.println("Post-> "+st);
					 }
					 break;
            case 6:  System.out.println("Insert the Index: \n");
					 indexDir = br.readLine();
					 System.out.println("Insert the type of Documents:: \n");
					 value = br.readLine();
					 System.out.println("Insert number of Documents: \n");
					 size=Integer.parseInt(br.readLine());
					 String resString=RestAPI.getFromIndexAndType(indexDir, value, size);
					 System.out.println("JSON-> "+resString);
					 break;
            case 7:  System.out.println("Insert the Index: \n");
					 indexDir = br.readLine();
					 System.out.println("Insert the key: \n");
					 key = br.readLine();
					 System.out.println("Insert the text: \n");
					 value = br.readLine();
					 listJson=Transport.getListOfJsonDocs(indexDir, key, value);
					 System.out.println("JSON-> "+listJson);
					 break;
            case 8:  System.out.println("Insert the Index: \n");
					 indexDir = br.readLine();
					 System.out.println("Insert the key: \n");
					 key = br.readLine();
					 System.out.println("Insert the text: \n");
					 value = br.readLine();
					 textList=Transport.getListOfPostsDocs(indexDir, key, value);
					 System.out.println("POSTS-> "+textList);
					 break;
            case 9:  System.out.println("Inserting....: \n");
            		 TweetAPI.newTweetsJson(twitter);
					 break;
            case 10: System.out.println("Insert the User: \n");
			 		 indexDir = br.readLine();
			 		 System.out.println("Inserting....: \n");
			   		 TweetAPI.putDataforUser(twitter, indexDir);
			   		 break;
            case 11: System.out.println("Inserting....: \n");
			   		 TweetAPI.searchQueryV2(twitter);
			   		 break;
            case 12: System.out.println("Insert the Index to delete: \n");
	 		 		 indexDir = br.readLine();
			   		 Transport.deleteIndexDocs(indexDir);
			   		 break;
            case 13: System.out.println("Insert the Index: \n");
					 indexDir = br.readLine();
					 System.out.println("Insira o Type: \n");
					 key = br.readLine();
					 System.out.println("Insert the Document ID: \n");
					 value = br.readLine();
			   		 Transport.deleteDocById(indexDir, key, value);
			   		 break;
            case 14: System.out.println("Insert the Index: \n");
					 indexDir = br.readLine();
					 System.out.println("Insert the key: \n");
					 key = br.readLine();
					 System.out.println("Insert the text: \n");
					 value = br.readLine();
			   		 Transport.deleteDocByQuery(indexDir, key, value);
			   		 break;
            case 0: 
	                System.out.println("Exiting Program...");
	                System.exit(0);
                 break;
            default :
                     System.out.println("This is not a valid Menu Option! Please Select Another");
                     break;
            
           }
          } catch (NumberFormatException e) { System.out.println(e); }

        }
        while(true);
    }
    
}
