package main.java.pt.uminho.sisbi.twitter.api;

public class Tweets {

    private String tweetUserName;
    private String tweetText;
    private Long id;
    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTweetUserName() {
		return tweetUserName;
	}
	public void setTweetUserName(String tweetUserName) {
		this.tweetUserName = tweetUserName;
	}
	
	public String getTweetText() {
		return tweetText;
	}
	public void setTweetText(String tweetText) {
		this.tweetText = tweetText;
	}
	@Override
	public String toString() {
		return  "{\"tweetUserName\":" + "\""+ tweetUserName +"\""+ ", \"tweetText\":" +"\" "+ tweetText +" \"" + "}";
	}
	public Tweets() {

    }
}