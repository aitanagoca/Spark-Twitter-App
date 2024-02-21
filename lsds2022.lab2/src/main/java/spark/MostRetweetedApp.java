package spark;

import java.io.Serializable;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import edu.upf.model.ExtendedSimplifiedTweet;
import scala.Tuple2;

public class MostRetweetedApp {

    // Define a Serializable Comparator class for comparing Tuple2<Long, Integer> by the second element
    public static class TupleComparator implements Comparator<Tuple2<Long, Integer>>, Serializable {
        @Override
        public int compare(Tuple2<Long, Integer> o1, Tuple2<Long, Integer> o2) {
            return o1._2().compareTo(o2._2());
        }
    }

    public static void main(String[] args) throws Exception {
        // Record the start time of the application
        Instant startTime = Instant.now();

        // Convert command-line arguments to a list for easier access
        List<String> argsList = Arrays.asList(args);
        String outputFile = argsList.get(0); // Extract output file path from arguments
        String inputFile = argsList.get(1); // Extract input file from arguments

        // Create a SparkContext to initialize
        SparkConf conf = new SparkConf().setAppName("Most Retweeted App");
        JavaSparkContext sparkContext = new JavaSparkContext(conf);
        // Load input
        JavaRDD<String> sentences = sparkContext.textFile(inputFile);

         // Map each input sentence to a SimplifiedTweet object and filter out invalid tweets
         JavaRDD<java.util.Optional<ExtendedSimplifiedTweet>> tweets = sentences
            .map(tweet -> ExtendedSimplifiedTweet.fromJson(tweet)) 
            .filter(tweet -> tweet.isPresent());

        // Map each tweet to a tuple of (retweetedUserId, tweet)
        JavaPairRDD<Long, ExtendedSimplifiedTweet> retweetedTweets = tweets
            .filter(tweet -> tweet.get().isTweetRetweeted()) // Filter out non-retweeted tweets
            .mapToPair(tweet -> new Tuple2<>(tweet.get().getRetweetUserId(), tweet.get()));

        // Count the number of retweets per user
        JavaPairRDD<Long, Integer> retweetCountsPerUser = retweetedTweets
            .mapValues(tweet -> 1) // Map each tweet to (userId, 1) for counting
            .reduceByKey(Integer::sum); // Sum up the retweet counts per user

        // Find the top 10 most retweeted users
        List<Tuple2<Long, Integer>> top10RetweetedUsers = retweetCountsPerUser
            .top(10, new TupleComparator()); // Use the custom Comparator implementation

        // Create a map of the top 10 most retweeted users for efficient lookup
        Map<Long, Integer> top10UsersMap = top10RetweetedUsers.stream()
            .collect(Collectors.toMap(Tuple2::_1, Tuple2::_2));

        // Filter tweets by the top 10 most retweeted users
        JavaPairRDD<Long, ExtendedSimplifiedTweet> top10RetweetedTweets = retweetedTweets
            .filter(tweet -> top10UsersMap.containsKey(tweet._1()));

        // Group the tweets by user ID
        JavaPairRDD<Long, Iterable<ExtendedSimplifiedTweet>> tweetsByUser = top10RetweetedTweets.groupByKey();

        // Find the most retweeted tweet for each user
        JavaPairRDD<Long, ExtendedSimplifiedTweet> mostRetweetedTweetsPerUser = tweetsByUser
            .flatMapToPair(userTweets -> {
                Long userId = userTweets._1();
                Iterable<ExtendedSimplifiedTweet> userTweetsIterable = userTweets._2();

                // Sort the user's tweets by retweet count in descending order
                List<ExtendedSimplifiedTweet> sortedTweets = StreamSupport.stream(userTweetsIterable.spliterator(), false)
                    .sorted(Comparator.comparingLong(ExtendedSimplifiedTweet::getRetweetTweetId).reversed())
                    .collect(Collectors.toList());

                // Take the first tweet, which is the most retweeted one
                List<Tuple2<Long, ExtendedSimplifiedTweet>> result = new ArrayList<>();
                if (!sortedTweets.isEmpty()) {
                    result.add(new Tuple2<>(userId, sortedTweets.get(0)));
                }
                return result.iterator();
            });

        // Save the most retweeted tweet for each user to a text file
        mostRetweetedTweetsPerUser
            .map(pair -> "{'tweetID': " +  pair._2().getTweetID() + ", 'text': " + pair._2().getText() + ", 'userId': " + pair._1() + ", 'userName:' " + pair._2().getUsername() + ", 'language': " + pair._2().getLanguage() + ", 'timestampMs': " + pair._2().getTimestamp() + "}")
            .saveAsTextFile(outputFile);

        // Print the number of most retweeted tweets per user
        Long numberOfMostRetweetedTweets = mostRetweetedTweetsPerUser.count();

        // Record the end time of the application
        Instant endTime = Instant.now();
        // Calculate and print the performance time of the application
        long performanceTime = Duration.between(startTime, endTime).toMillis();

        // Print the number of processed tweets, the total number of most retweeted tweets per most retweeted users, and the performance time
        System.out.println("Number of processed tweets: " + tweets.count());
        System.out.println("Number of most retweeted tweets per most retweeted users: " + numberOfMostRetweetedTweets);
        System.out.println("Performance time: " + performanceTime + " milliseconds.");
    }
}
