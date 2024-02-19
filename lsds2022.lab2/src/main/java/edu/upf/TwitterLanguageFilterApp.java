package edu.upf;

import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.SparkConf;

import edu.upf.model.SimplifiedTweet;

import java.time.Duration;
import java.time.Instant;
import java.util.Optional;
import java.util.Arrays;
import java.util.List;

/**
 * The TwitterLanguageFilterApp class is the entry point for the Twitter language filtering application.
 * It reads command-line arguments, filters tweets based on language, writes the filtered tweets to a file,
 * and records the performance time of the application.
 */

public class TwitterLanguageFilterApp {
    public static void main( String[] args ) throws Exception {
        
        // Record the start time of the application
        Instant startTime = Instant.now();

        // Convert command-line arguments to a list for easier access
        List<String> argsList = Arrays.asList(args);
        String language = argsList.get(0); // Extract language from arguments
        String outputFile = argsList.get(1); // Extract output file path from arguments
        String inputFile = argsList.get(2); // Extract input file from arguments

        // Create a SparkContext to initialize
        SparkConf conf = new SparkConf().setAppName("Twitter Language Filter App");
        JavaSparkContext sparkContext = new JavaSparkContext(conf);
        // Load input
        JavaRDD<String> sentences = sparkContext.textFile(inputFile);

        // Map each input sentence to a SimplifiedTweet object and filter out invalid tweets
        JavaRDD<Optional<SimplifiedTweet>> tweets = sentences
            .map(tweet -> SimplifiedTweet.fromJson(tweet)) 
            .filter(tweet -> tweet.isPresent())
            .filter(tweet-> tweet.get().getLanguage().equals(language));
        
        // Convert filtered tweets to string representation and save to output file
        JavaRDD<String> finalTweets = tweets.map(filteredTweets -> filteredTweets.toString());
        finalTweets.saveAsTextFile(outputFile);
        Long countFilteredTweets = tweets.count();

        // Record the end time of the application
        Instant endTime = Instant.now();
        // Calculate and print the performance time of the application
        long performanceTime = Duration.between(startTime, endTime).toMillis();
        
        // Print the number of tweets written for the specified language and the performance time
        System.out.println("Number of written tweets: " + countFilteredTweets + " (language: " + language + ")");
        System.out.println("Performance time: " + performanceTime + " milliseconds.");
    }
}
