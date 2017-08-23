package main.java.pt.uminho.sisbi.twitter.api;

public class Tweets {

	private Long id;
	private String tweetLang;
	private String tweetText;
	private String screen_name;
	private String language;
    private String tweetUserName;
    private String location;
    
    
    public String getTweetLang() {
		return tweetLang;
	}
	public void setTweetLang(String tweetLang) {
		this.tweetLang = tweetLang;
	}
	public String getScreen_name() {
		return screen_name;
	}
	public void setScreen_name(String screen_name) {
		this.screen_name = screen_name;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
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