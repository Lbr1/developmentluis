package pt.uminho.sisbi.twitter.api;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import twitter4j.DirectMessage;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

public class TweetAPI {

	public static List<Status> newTweets(Twitter twitter) throws TwitterException {   
        List<Status> statuses = twitter.getHomeTimeline();  
        return statuses;  
    }
	
	public static Status updateStatus(Twitter twitter) throws TwitterException, IOException {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Insira o seu Tweet: \n");
			String status = br.readLine();
			System.out.println("Successfully updated the status to [" + status + "].");
	      return twitter.updateStatus(status);  
	}
	
	public static DirectMessage SendMessage(Twitter twitter, String mensagem, String destinatarioID)  
	         throws TwitterException {
	      return twitter.sendDirectMessage(destinatarioID, mensagem);  
	}  
	
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
	
	public static List<Status> tweetsUtilizador(Twitter twitter, String twitterID)  
	         throws TwitterException {    
	      List<Status> statuses = twitter.getUserTimeline(twitterID);  
	      return statuses;  
	   }  
	
	public static  void ficheiro(Twitter twitter, String fileName,String queryS)  {
	         
	try {	
		int i=0;
		String path = "C:" + File.separator + "Users" + File.separator + "Brito"+ File.separator + "Desktop" 
		+ File.separator + "silicoTwitter"+ File.separator + "twitter"+ File.separator + "texte.txt";
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
		System.out.println("@" + tweet.getUser().getScreenName() + " - " + tweet.getText() + "\n");
		bw.write("@" + tweet.getUser().getScreenName() + " - " + tweet.getText() +"\n\n\n\n\n\n");
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
