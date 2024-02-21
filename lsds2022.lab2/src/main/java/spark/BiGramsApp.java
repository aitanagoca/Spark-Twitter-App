package spark;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import edu.upf.model.BiGram;
import edu.upf.model.ExtendedSimplifiedTweet;
import scala.Tuple2;

public class BiGramsApp {
    public static void main( String[] args ) throws Exception {
        
        // Record the start time of the application
        Instant startTime = Instant.now();

        // Convert command-line arguments to a list for easier access
        List<String> argsList = Arrays.asList(args);
        String language = argsList.get(0); // Extract language from arguments
        String outputFile = argsList.get(1); // Extract output file path from arguments
        String inputFile = argsList.get(2); // Extract input file from arguments

        // Create a SparkContext to initialize
        SparkConf conf = new SparkConf().setAppName("Bi-Grams App");
        JavaSparkContext sparkContext = new JavaSparkContext(conf);
        // Load input
        JavaRDD<String> sentences = sparkContext.textFile(inputFile);

        // Map each input sentence to a SimplifiedTweet object and filter out invalid tweets (taking into account the language)
        JavaRDD<Optional<ExtendedSimplifiedTweet>> tweets = sentences
            .map(tweet -> ExtendedSimplifiedTweet.fromJson(tweet)) 
            .filter(tweet -> tweet.isPresent())
            .filter(tweet-> tweet.get().getLanguage().equals(language));
        
        Long countFilteredTweets = tweets.count();

        // Normalize text and generate bi-grams
        JavaPairRDD<BiGram, Integer> biGrams = tweets
                .flatMapToPair(tweet -> {
                    String text = tweet.get().getText();
                    // Normalize text by trimming, lower-casing, and filtering empty words
                    List<String> words = Arrays.asList(text.split("\\s+"))
                            .stream()
                            .map(word -> word.trim().toLowerCase())
                            .filter(word -> !word.isEmpty())
                            .collect(Collectors.toList());
                    // Generate bi-grams
                    List<Tuple2<BiGram, Integer>> biGramList = new ArrayList<>();
                    for (int i = 0; i < words.size() - 1; i++) {
                        BiGram biGram = new BiGram(words.get(i), words.get(i + 1));
                        biGramList.add(new Tuple2<>(biGram, 1));
                    }
                    return biGramList.iterator();
                })
                .reduceByKey(Integer::sum);

        // Sort the results in descending order by the number of appearances
        JavaPairRDD<Integer, BiGram> sortedBiGrams = biGrams.mapToPair(Tuple2::swap).sortByKey(false);

        // Transform the RDD to the desired format: "<word1, word2>: count"
        JavaRDD<String> outputRDD = sortedBiGrams.map(pair -> "<" + pair._2().getWord1() + ", " + pair._2().getWord2() + ">: " + pair._1());

        Long countBiGrams = outputRDD.count();

        // Save the sorted results
        outputRDD.saveAsTextFile(outputFile);

        // Record the end time of the application
        Instant endTime = Instant.now();
        // Calculate and print the performance time of the application
        long performanceTime = Duration.between(startTime, endTime).toMillis();

        // Print the number of tweets written for the specified language the number of bigrams generated and the performance time
        System.out.println("Number of processed tweets: " + countFilteredTweets + " (language: " + language + ")");
        System.out.println("Number of bigrams: " + countBiGrams);
        System.out.println("Performance time: " + performanceTime + " milliseconds.");
    }

    
}
