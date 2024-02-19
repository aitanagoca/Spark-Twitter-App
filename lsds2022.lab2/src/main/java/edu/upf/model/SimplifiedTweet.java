package edu.upf.model;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.Optional;

/**
 * The SimplifiedTweet class represents a simplified version of a tweet.
 * It contains fields for tweet ID, text, user ID, user name, language, and timestamp.
 */

public class SimplifiedTweet {

  private final long tweetId;			  // the id of the tweet ('id')
  private final String text;  		      // the content of the tweet ('text')
  private final long userId;			  // the user id ('user->id')
  private final String userName;		  // the user name ('user'->'name')
  private final String language;          // the language of a tweet ('lang')
  private final long timestampMs;		  // seconduserIds from epoch ('timestamp_ms')

  /**
   * Constructs a SimplifiedTweet object with the provided values.
   *
   * @param tweetId     the ID of the tweet
   * @param text        the content of the tweet
   * @param userId      the ID of the user
   * @param userName    the name of the user
   * @param language    the language of the tweet
   * @param timestampMs the timestamp of the tweet in milliseconds since epoch
   */
  public SimplifiedTweet(long tweetId, String text, long userId, String userName,
                         String language, long timestampMs){
    
    this.tweetId = tweetId;
    this.text = text;
    this.userId = userId;
    this.userName = userName;
    this.language = language;
    this.timestampMs = timestampMs;
  }

  /**
   * Returns a {@link SimplifiedTweet} from a JSON String.
   * If parsing fails, for any reason, return an {@link Optional#empty()}
   *
   * @param jsonStr
   * @return an {@link Optional} of a {@link SimplifiedTweet}
   */
  public static Optional<SimplifiedTweet> fromJson(String jsonStr){
    
    SimplifiedTweet tweet = null;
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
        String lang = jsonObj.get("lang").getAsString();
        Long timeStamp = jsonObj.get("timestamp_ms").getAsLong();

        // Create a SimplifiedTweet instance with the extracted values
        tweet = new SimplifiedTweet(tweetId, text, userId, userName, lang, timeStamp);
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
   * Returns the language of the tweet.
   *
   * @return the language of the tweet
   */
  public String getLanguage(){
    return this.language;
  }

  /**
   * Returns the JSON representation of the SimplifiedTweet object.
   *
   * @return the JSON representation of the SimplifiedTweet object
   */
  @Override
  public String toString(){
    // Convert the SimplifiedTweet object to a JSON string using Gson
    return new Gson().toJson(this);
  }
}