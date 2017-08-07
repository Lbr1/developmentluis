package pt.uminho.sisbi.twitter.api;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import main.java.pt.uminho.sisbi.twitter.api.Tweets;
import twitter4j.DirectMessage;
import twitter4j.JSONArray;
import twitter4j.JSONException;
import twitter4j.JSONObject;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import org.elasticsearch.action.ActionFuture;
import org.elasticsearch.action.admin.cluster.health.ClusterHealthResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.Requests;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.action.ActionFuture;
import org.elasticsearch.action.admin.cluster.health.ClusterHealthResponse;
import org.elasticsearch.client.Requests;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.index.query.QueryBuilders.*;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

public class TweetAPI {

	/*public static void cenas() throws UnknownHostException{
		System.out.println( "1" );
		TransportClient client =new PreBuiltTransportClient(Settings.EMPTY)
				.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));
		//System.out.println( "2" );

		
		SearchResponse response = client.prepareSearch("game").get();
		System.out.println("total Shards" + response.getTotalShards());
		System.out.println("get took"+response.getTook());
		System.out.println(response);
		System.out.println(response.toString());
	
		

		//SearchResponse responde = client.prepareSearch("teste21").get();
		System.out.println( "3" );
		client.close();
		System.out.println( client.nodeName());
		
		
	}*/
	
	// create json object from user timeline
	public static JSONObject jSONforUser(Twitter twitter,String user) throws TwitterException, JSONException{
		List<Tweets> tweetList = new ArrayList<>();
        JSONObject json= new JSONObject();
        JSONArray array=new JSONArray();
        
        List<Status> statuses=Usertweets(twitter,user);
     // iterate via "New way to loop"
     		System.out.println("\n==> "+ user + "Timeline ...");
     		for (Status stat : statuses) {
     			JSONObject item = new JSONObject();
     			
     			Tweets tweetItem = new Tweets();
   
     			tweetItem.setTweetText(stat.getText());
     			item.put("TEXT", stat.getText());
     			tweetItem.setTweetUserName(stat.getUser().getScreenName());
     			item.put("USER", stat.getUser().getScreenName());
     			tweetItem.setId(stat.getId());
     			tweetList.add(tweetItem);
     			array.put(item);
     		}
     		json.put("TWEET", array);

     			return json;
	}
	
	//create json object from keyword with specific date
	
	public static JSONObject jSONforKeyWord(Twitter twitter) throws TwitterException, JSONException, IOException{
        JSONObject json= new JSONObject();
        JSONArray array=new JSONArray();
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Insira a sua Query: \n");
		String args = br.readLine();
		
		if(args.length() < 1){
			System.out.println("java twitter4j.examples.search.SearchTweets [query]");
            System.exit(-1);
		}
			Query query = new Query(args);
			Date date = new Date();
			String modifiedDate = new SimpleDateFormat("yyyymmdd").format(date);
			query.setSince(modifiedDate);
			QueryResult result;
			try {
				do {
					result = twitter.search(query);
					List<Status> tweets = result.getTweets();
					System.out.println(tweets.size());
					for (Status tweet : tweets) {
						JSONObject item = new JSONObject();
						item.put("ID", tweet.getId());
						item.put("USER", tweet.getUser().getScreenName());
						item.put("TEXT", tweet.getText());
						array.put(item);
					}
				} while ((query = result.nextQuery()) != null);
			} catch (TwitterException e) {
				e.printStackTrace();
			}
			json.put("Tweets", array);
			return json;
   
	}
	//Get my recent tweets
	public static List<Status> newTweets(Twitter twitter) throws TwitterException {   
        List<Status> statuses = twitter.getHomeTimeline();  
        return statuses;  
    }
	
	//update my status of the twitter
	public static Status updateStatus(Twitter twitter) throws TwitterException, IOException {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Insira o seu Tweet: \n");
			String status = br.readLine();
			System.out.println("Successfully updated the status to [" + status + "].");
	      return twitter.updateStatus(status);  
	}
	
	//Send a message for a destination
	public static DirectMessage SendMessage(Twitter twitter, String mensagem, String destinatarioID)  
	         throws TwitterException {
	      return twitter.sendDirectMessage(destinatarioID, mensagem);  
	}  
	
	//Search for a query by a keyword
	public static void searchQuery(Twitter twitter) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Insira a sua Query: \n");
		String args = br.readLine();
		
		if(args.length() < 1){
			System.out.println("java twitter4j.examples.search.SearchTweets [query]");
            System.exit(-1);
		}
		try {
			Query query = new Query(args);
            QueryResult result;
            do {
                result = twitter.search(query);
                List<Status> tweets = result.getTweets();
                for (Status tweet : tweets) {
                    System.out.println("@" + tweet.getUser().getScreenName() + " -> " + tweet.getText());
                    linkifyTweet(tweet.getText());  
                    System.out.println("\n");
                }
            } while ((query = result.nextQuery()) != null);
            System.exit(0);
			
		} catch (TwitterException te) {
			te.printStackTrace();
            System.out.println("Failed to search tweets: " + te.getMessage());
            System.exit(-1);
		}
		
	}
	
	//search query by date
	
	//Search for a query by a keyword
		public static List<Tweets> searchQuerybyDate(Twitter twitter) throws IOException{
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			List<Tweets> tweetsList = new ArrayList<>();
			System.out.println("Insira a sua Query: \n");
			String args = br.readLine();
			
			if(args.length() < 1){
				System.out.println("java twitter4j.examples.search.SearchTweets [query]");
	            System.exit(-1);
			}
				Query query = new Query(args);
				Date date = new Date();
				String modifiedDate = new SimpleDateFormat("yyyymmdd").format(date);
				query.setSince(modifiedDate);
				QueryResult result;
				try {
					do {
						result = twitter.search(query);
						List<Status> tweets = result.getTweets();
						System.out.println(tweets.size());
						for (Status tweet : tweets) {
							Tweets tweetItem = new Tweets();
							tweetItem.setTweetText(tweet.getText());
							tweetItem.setTweetUserName(tweet.getUser().getScreenName());
							tweetItem.setId(tweet.getId());
							
							System.out.println(
									"@" + tweet.getUser().getScreenName() + "|" + tweet.getText() + "|" + tweet.isRetweeted());
							tweetsList.add(tweetItem);
						}
					} while ((query = result.nextQuery()) != null);
				} catch (TwitterException e) {
					e.printStackTrace();
				}
				return tweetsList;
		}
		
		
	//get the twittes from  user
	public static List<Status> Usertweets(Twitter twitter, String twitterID)  
	         throws TwitterException {    
	      List<Status> statuses = twitter.getUserTimeline(twitterID);  
	      return statuses;  
	   } 
	
    public static List<String> elasticJson(Twitter twitter) throws TwitterException {
        Query query = new Query("motogp");
        List<Status> tweetList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            QueryResult queryResult = twitter.search(query);
            tweetList.addAll(queryResult.getTweets());
            if (!queryResult.hasNext()) {
                break;
            }
            query = queryResult.nextQuery();
        }
        Gson gson = new Gson();
        gson = new GsonBuilder().create();
        
        
        
        return tweetList.stream().map(gson::toJson).collect(Collectors.toList());  	
       
    }
	
	public static String linkifyTweet(String tweet) {
	      Pattern pattern;
	      Matcher matcher;
	      

	      String regex_url = "((https?://\\S+)|(www.\\S+))";
	      String regex_hashtag = "#(\\w+)";
	      String regex_user = "@(\\w+)";

	      //regex to apply links to all urls in the tweet
	      pattern = Pattern.compile(regex_url);
	      System.out.println("PATTERN = "+ pattern);
	      matcher = pattern.matcher(tweet);
	      System.out.println("MATCHER = "+ matcher);
	      if (matcher.find()) {
	    	  //url++;
	    	  System.out.println("MATCHER = "+ matcher);
	           //tweet = tweet.replaceAll(regex_url, "<a target=\"_blank\" href=\"$1\">$1</a>");
	      }

	      //regex to apply links to all hashtags in the tweet
	      pattern = Pattern.compile(regex_hashtag);
	      matcher = pattern.matcher(tweet);
	      if (matcher.find()) {
	    	  System.out.println("oi");
	    	  System.out.println("MATCHER hash = "+ matcher);
	           //tweet = tweet.replaceAll(regex_hashtag, "<a target=\"_blank\" href=\"https://www.twitter.com/hashtag/$1?src=hash\">#$1</a>");
	    	  tweet = tweet.replaceAll(regex_hashtag, "hash");
	    	  System.out.println(tweet);
	      }
	      System.out.println("passei por aqui");
	      //regex to apply links to all users in the tweet
	      pattern = Pattern.compile(regex_user);
	      matcher = pattern.matcher(tweet);
	      if (matcher.find()) {
	    	  System.out.println("oi2");
	           //tweet = tweet.replaceAll(regex_user, "<a target=\"_blank\" href=\"https://www.twitter.com/$1\">@$1</a>");
	      }
	      //System.out.println(tweet);
	      return tweet;
	 }
	
	 
	
	public static  void ficheiro(Twitter twitter, String fileName,String queryS)  {
	         
	try {	
		int i=0;
		String path = "C:" + File.separator + "Users" + File.separator + "Brito"+ File.separator + "Desktop" 
		+ File.separator + "silicoTwitter"+ File.separator + "twitter"+ File.separator + "got.txt";
		// Use relative path for Unix systems
		File f = new File(path);

		f.getParentFile().mkdirs(); 
		f.createNewFile();
		
		//File file = new File("twitter/testes/"+ fileName+".txt");
		FileWriter fw = new FileWriter(f, true);
		BufferedWriter bw = new BufferedWriter(fw);
		QueryResult result;
		Query query = new Query(queryS);
		do {
		i++;
		result = twitter.search(query);
		List<Status> tweets = result.getTweets();
		for (Status tweet : tweets) {
		System.out.println("@" + tweet.getUser().getScreenName() + " - " + tweet.getText()+" - " + tweet.getUser().getLocation());
		bw.write("@" + tweet.getUser().getScreenName() + " - " + tweet.getText() +" Location : " +tweet.getUser().getLocation());
		bw.newLine();
		}
		}
		while (i<=10/*(query = result.nextQuery()) != null*/);
		System.exit(0);
		bw.close();
		} catch (TwitterException te) {
		te.printStackTrace();
		System.out.println("Failed to search tweets: " + te.getMessage());
		System.exit(-1);
		} catch (IOException e) {    e.printStackTrace();    }}
}
