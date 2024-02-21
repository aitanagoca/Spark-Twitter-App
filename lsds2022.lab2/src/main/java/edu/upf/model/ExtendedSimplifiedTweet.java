package edu.upf.model;

import java.io.Serializable;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Represents an extended simplified tweet.
 */

public class ExtendedSimplifiedTweet implements Serializable {
    private final long tweetId; // the id of the tweet (’id’)
    private final String text; // the content of the tweet (’text’)
    private final long userId; // the user id (’user->id’)
    private final String userName; // the user name (’user’->’name’)
    private final long followersCount; // the number of followers (’user’->’followers_count’) 
    private final String language; // the language of a tweet (’lang’)
    private final boolean isRetweeted; // is it a retweet? (the object ’retweeted_status’ exists?) 
    private final Long retweetedUserId; // [if retweeted] (’retweeted_status’->’user’->’id’) 
    private final Long retweetedTweetId; // [if retweeted] (’retweeted_status’->’id’)
    private final long timestampMs; // seconds from epoch (’timestamp_ms’)
    
    /**
     * Constructs an ExtendedSimplifiedTweet object.
     * 
     * @param tweetId the id of the tweet
     * @param text the content of the tweet
     * @param userId the user id
     * @param userName the user name
     * @param followersCount the number of followers
     * @param language the language of the tweet
     * @param isRetweeted indicates if it's a retweet
     * @param retweetedUserId the id of the retweeted user (if retweeted)
     * @param retweetedTweetId the id of the retweeted tweet (if retweeted)
     * @param timestampMs the timestamp in milliseconds
     */
    public ExtendedSimplifiedTweet(long tweetId, String text, long userId, String userName, long followersCount, String language, boolean isRetweeted, Long retweetedUserId, Long retweetedTweetId, long timestampMs){
        this.tweetId = tweetId;
        this.text = text;
        this.userId = userId;
        this.userName = userName;
        this.followersCount = followersCount;
        this.language = language;
        this.isRetweeted = isRetweeted;
        this.retweetedUserId = retweetedUserId;
        this.retweetedTweetId = retweetedTweetId;
        this.timestampMs = timestampMs;
    }
   
    /**
     * Returns a {@link ExtendedSimplifiedTweet} from a JSON String.
     * If parsing fails, for any reason, return an {@link Optional#empty()}
     *
     * @param jsonStr
     * @return an {@link Optional} of a {@link ExtendedSimplifiedTweet}
     */
    public static Optional<ExtendedSimplifiedTweet> fromJson(String jsonStr) {
        ExtendedSimplifiedTweet tweet = null;
        JsonElement jsonElem = null;
        JsonParser jsonParser = new JsonParser();

        try{
            // Parse the JSON string into a JsonElement
            jsonElem = jsonParser.parse(jsonStr);
            Optional<JsonElement> optJsonElem = Optional.ofNullable(jsonElem);
            JsonObject jsonObj = optJsonElem.get().getAsJsonObject();

            // Check if the JSON object has a "user" property
            if (jsonObj.has("user")) {
                JsonObject userObj = jsonObj.getAsJsonObject("user");

                // Extract values from the JSON object
                Long tweetId = jsonObj.get("id").getAsLong();
                Long userId = userObj.get("id").getAsLong();
                String userName = userObj.get("name").getAsString();
                String text = jsonObj.get("text").getAsString();
                Long followersCount = userObj.get("followers_count").getAsLong();
                String lang = jsonObj.get("lang").getAsString();
                Boolean isRetweeted = jsonObj.has("retweeted_status");
                Long retweetedUserId = null;
                Long retweetedTweetId = null;

                // Check if the tweet has been retweeted
                if (isRetweeted) {
                    // If it has been retweeted, extract additional information
                    JsonObject retweetedStatusObject = jsonObj.getAsJsonObject("retweeted_status");
                    retweetedTweetId = retweetedStatusObject.get("id").getAsLong();
                    retweetedUserId = retweetedStatusObject.getAsJsonObject("user").get("id").getAsLong();
                }

                // Extract values from the JSON object
                Long timeStamp = jsonObj.get("timestamp_ms").getAsLong();

                // Create a ExtendedSimplifiedTweet instance with the extracted values
                tweet = new ExtendedSimplifiedTweet(tweetId, text, userId, userName, followersCount, lang, isRetweeted, retweetedUserId, retweetedTweetId, timeStamp);
            }
        }

        catch(Exception e){
            // Handle any exceptions during parsing and return an empty Optional
            return Optional.empty();
        }

        // Return an Optional containing the created SimplifiedTweet, or an empty Optional if parsing fails
        return Optional.ofNullable(tweet);
    }

    /**
    * Returns the text content of the tweet.
    *
    * @return the text content of the tweet
    */
    public String getText(){
        return this.text;
    }

    /**
    * Returns the language of the tweet.
    *
    * @return the language of the tweet
    */
    public String getLanguage(){
        return this.language;
    }

    /**
     * Returns if the tweet has been retweeted or not.
     *
     * @return true if the tweet has been retweeted, false otherwise
     */
    public boolean isTweetRetweeted(){
      return this.isRetweeted;
    }
  
    /**
     * Returns the user ID of the retweeted tweet (if retweeted).
     *
     * @return the user ID of the retweeted tweet, or null if not been retweeted
     */
    public Long getRetweetUserId(){
        return this.retweetedUserId;
    }

    /**
    * Returns the JSON representation of the ExtendedSimplifiedTweet object.
    *
    * @return the JSON representation of the ExtendedimplifiedTweet object
    */
    @Override
    public String toString(){
        // Convert the ExtendedSimplifiedTweet object to a JSON string using Gson
        return new Gson().toJson(this);
    }
}