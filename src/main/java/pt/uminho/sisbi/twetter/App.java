package pt.uminho.sisbi.twetter;
import java.io.IOException;
import java.util.*;

import pt.uminho.sisbi.twetter.api.TweetAPI;
import twitter4j.*;
import twitter4j.auth.AccessToken;
import twitter4j.conf.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws TwitterException, IOException
    {
    	
    	//TweetAPI api = new TweetAPI();
    	
        System.out.println( "Hello World!" );
        
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
        
        /*System.out.println("O ScreenName do Twitter "+ twitter.getScreenName());
        System.out.println("Os Tweets do Twitter "+ twitter.tweets());
        System.out.println("Os Friends do Twiter "+ twitter.getFriendsIDs(1));
        System.out.println("ToString do Twiter "+ twitter.toString());
        System.out.println("Os Friends do Twiter "+ twitter.friendsFollowers());
        System.out.println("Account Settings "+ twitter.getAccountSettings());
        System.out.println("Languages "+ twitter.getLanguages());
        
        */
        
          
       //*****************get the recents tweets of my account*************
       /* 
        List<Status> statuses = TweetAPI.newTweets(twitter);
        for (Status status : statuses) {
            System.out.println(status.getUser().getName()+ " -> " +
                               status.getText());
            String urls= "https://twitter.com/" + status.getUser().getScreenName() 
            	    + "/status/" + status.getId();
            System.out.println("Post Url -> "+urls);
            System.out.println("Status getLocation -> "+status.getUser().getLocation());
            System.out.println("Number of Retweets -> "+status.getRetweetCount());
            System.out.println("Status getURL -> "+status.getUser().getURL());
            System.out.println("\n");
            System.out.println("\n");
            
        }
        */
        //************Send a message for a specific user***************
        //TweetAPI.SendMessage(twitter, "Olá Luís 222", "@asdasdafsw");
        
        System.out.println("DONE ");
        /*
        Query query = new Query("java");  
        QueryResult result = twitter.search(query);
        System.out.println("OLHA OS TWEETS : " + result.getCount());
        System.out.println("OLHA OS TWEETS : " + result.getMaxId());
        System.out.println("retweet "+ twitter.toString());
		*/
        
        
        //tratamento do ficheiro
        /*TweetAPI.ficheiro(twitter, "teste", "MotoGP");
        List<Status> statuses2 = TweetAPI.tweetsUtilizador(twitter, "@MotoGP");
        System.out.println("OLHA OS TWEETS2: " + statuses2.toString());
        System.out.println("OLHA OS TWEETS3: " + statuses2.size());
 */
        
        
        //*******************Create a Tweet**********************
         
     // TweetAPI.updateStatus(twitter);
        
      
      //*******************Search Querys**********************
        
        TweetAPI.searchQuery(twitter);
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
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
    
    
}
