package POJO;


public class _source
{
    private String filter_level;

    private String retweeted;

   

    private String possibly_sensitive;

    private String truncated;

    private String lang;

    private String id;

    private String timestamp_ms;

    private String created_at;

    private String favorite_count;

    private String[] display_text_range;

    private String text;

    private String is_quote_status;

    private String source;

    private String favorited;

    private String retweet_count;

    private String id_str;

    private User user;

    public String getFilter_level ()
    {
        return filter_level;
    }

    public void setFilter_level (String filter_level)
    {
        this.filter_level = filter_level;
    }

    public String getRetweeted ()
    {
        return retweeted;
    }

    public void setRetweeted (String retweeted)
    {
        this.retweeted = retweeted;
    }


    public String getPossibly_sensitive ()
    {
        return possibly_sensitive;
    }

    public void setPossibly_sensitive (String possibly_sensitive)
    {
        this.possibly_sensitive = possibly_sensitive;
    }

    public String getTruncated ()
    {
        return truncated;
    }

    public void setTruncated (String truncated)
    {
        this.truncated = truncated;
    }

    public String getLang ()
    {
        return lang;
    }

    public void setLang (String lang)
    {
        this.lang = lang;
    }


    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }


    public String getTimestamp_ms ()
    {
        return timestamp_ms;
    }

    public void setTimestamp_ms (String timestamp_ms)
    {
        this.timestamp_ms = timestamp_ms;
    }

    public String getCreated_at ()
    {
        return created_at;
    }

    public void setCreated_at (String created_at)
    {
        this.created_at = created_at;
    }

    public String getFavorite_count ()
    {
        return favorite_count;
    }

    public void setFavorite_count (String favorite_count)
    {
        this.favorite_count = favorite_count;
    }

    public String[] getDisplay_text_range ()
    {
        return display_text_range;
    }

    public void setDisplay_text_range (String[] display_text_range)
    {
        this.display_text_range = display_text_range;
    }

    public String getText ()
    {
        return text;
    }

    public void setText (String text)
    {
        this.text = text;
    }

    public String getIs_quote_status ()
    {
        return is_quote_status;
    }

    public void setIs_quote_status (String is_quote_status)
    {
        this.is_quote_status = is_quote_status;
    }

    public String getSource ()
    {
        return source;
    }

    public void setSource (String source)
    {
        this.source = source;
    }

    public String getFavorited ()
    {
        return favorited;
    }

    public void setFavorited (String favorited)
    {
        this.favorited = favorited;
    }

    public String getRetweet_count ()
    {
        return retweet_count;
    }

    public void setRetweet_count (String retweet_count)
    {
        this.retweet_count = retweet_count;
    }

    public String getId_str ()
    {
        return id_str;
    }

    public void setId_str (String id_str)
    {
        this.id_str = id_str;
    }

    public User getUser ()
    {
        return user;
    }

    public void setUser (User user)
    {
        this.user = user;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [filter_level = "+filter_level+", retweeted = "+retweeted+", in_reply_to_screen_name = "+", possibly_sensitive = "+possibly_sensitive+", truncated = "+truncated+", lang = "+lang+", in_reply_to_status_id_str = "+", id = "+id+", extended_entities = "+", in_reply_to_user_id_str = "+", timestamp_ms = "+timestamp_ms+", in_reply_to_status_id = "+", created_at = "+created_at+", favorite_count = "+favorite_count+", display_text_range = "+display_text_range+", place = "+", text = "+text+", geo = "+", is_quote_status = "+is_quote_status+", source = "+source+", favorited = "+favorited+", retweet_count = "+retweet_count+", in_reply_to_user_id = "+", id_str = "+id_str+", user = "+user+"]";
    }
}
			
			