# Spark-Twitter-App

## Group Information 

Group: (P102, grup 05)

Aitana González (U186651, Bucket: lsds2024.lab2.output.u186651)

Jordi Alfonso (U111792, Bucket: lsds2024.lab2.output.u111792) 

Arnau Royo (U172499, Bucket: lsds2024.lab2.output.u172499)

## (For group mates) - How to execute

(!! Make sure that you have the tweets tar file downloaded - you can get it from the location given in the statement - and it is in the lab2 folder !!)

-> .tar source - public s3 path: s3://lsds2022/twitter-eurovision-2018.tar

### (PART 2) Implement the Twitter filter using Spark

1. Terminal: brew install openjdk@11 (only the first time!!)

2. Mvn: mvn clean

3. Mvn: mvn validate

4. Mvn: mvn compile

5. Mvn: mvn package

6. Terminal (from the lab2 folder): jar tvf target/spark-test-1.0-SNAPSHOT.jar

8. Mvn: spark-submit --class edu.upf.TwitterLanguageFilterApp target/spark-test-1.0-SNAPSHOT.jar < language > < name_outputFolder > < name_twittterTarFile >

### (PART 3) Implement the Twitter filter using Spark EMR

(!! Make sure that you have uploaded the input file and the jar on the specified folders - given in the statement - of the S3, and that you have created the corrected output folder for the outputs!!)

1. Create cluster (follow guide)

2. (WHEN ADDING STEP IN CLUSTER): spark-submit --class edu.upf.TwitterLanguageFilterApp s3://lsds2024.lab2.output.uxxxxxx/jars/spark-test-1.0-SNAPSHOT.jar < language > < name_outputFolder > < name_twittterTarFile >

### (PART 4) Most popular bi-grams in a given language

1. Terminal: brew install openjdk@11 (only the first time!!)

2. Mvn: mvn clean

3. Mvn: mvn validate

4. Mvn: mvn compile

5. Mvn: mvn package

6. Terminal (from the lab2 folder): jar tvf target/spark-test-1.0-SNAPSHOT.jar

7. Mvn: spark-submit --class spark.BiGramsApp target/spark-test-1.0-SNAPSHOT.jar < language > < name_outputFolder > < name_twittterTarFile >

(!! You have to execute it too in EMR !!)

1. Create cluster (follow guide)

2. (WHEN ADDING STEP IN CLUSTER): spark-submit --class spark.BiGramsApp s3://lsds2024.lab2.output.uxxxxxx/jars/spark-test-1.0-SNAPSHOT.jar < language > < name_outputFolder > < name_twittterTarFile >

### (PART 5) Most Retweeted Tweets for Most Retweeted Users

1. Terminal: brew install openjdk@11 (only the first time!!)

2. Mvn: mvn clean

3. Mvn: mvn validate

4. Mvn: mvn compile

5. Mvn: mvn package

6. Terminal (from the lab2 folder): jar tvf target/spark-test-1.0-SNAPSHOT.jar

7. Mvn: spark-submit --class spark.MostRetweetedApp target/spark-test-1.0-SNAPSHOT.jar < name_outputFolder > < name_twittterTarFile >

(!! You have to execute it too in EMR !!)

1. Create cluster (follow guide)

2. (WHEN ADDING STEP IN CLUSTER): spark-submit --class spark.MostRetweetedApp s3://lsds2024.lab2.output.uxxxxxx/jars/spark-test-1.0-SNAPSHOT.jar < name_outputFolder > < name_twittterTarFile >

## Benchmark

#### Number of output tweets:

    Aitana
        Català: 4583 tweets
        Español: 509433 tweets
        English: 446601 tweets
    
    Jordi
        Català: 4583 tweets
        Español: 509433 tweets
        English: 446601 tweets

    Arnau
        Català: 4583 tweets
        Español: 509433 tweets
        English: 446601 tweets

### Part 2

#### Execution times (local):

    Aitana
        Català: 72.095 ms
        Español: 89.300 ms
        English: 86.172 ms

    Jordi
        Català: 38.321 ms
        Español: 38.824 ms
        English: 38.734 ms

    Arnau
        Català: 83.515 ms
        Español: 90.354 ms
        English: 87.951 ms

### Part 3

#### Elapsed times (EMR):

    Aitana
        Català: 654.000 ms
        Español: 554.000 ms
        English: 642.000 ms

    Jordi
        Català: 328.000 ms
        Español: 330.000 ms
        English: 344.000 ms

    Arnau
        Català:  557.000 ms
        Español: 612.000 ms
        English: 627.000 ms

### Part 4

#### Number of bigrams:

    Aitana
        Català: 19408 bigrams
        Español: 586992 bigrams
        English: 970005 bigrams
    
    Jordi
        Català: 19408 bigrams
        Español: 586992 bigrams
        English: 970005 bigrams

    Arnau
        Català: 19408 bigrams
        Español: 586992 bigrams
        English: 970005 bigrams

#### Execution times (local):

    Aitana
        Català: 72.150 ms
        Español: 88.778 ms
        English: 104.272 ms

    Jordi
        Català: 31.127 ms
        Español: 45.405 ms
        English: 46.084 ms

    Arnau
        Català: 65.800 ms
        Español: 97.987 ms
        English: 93.769 ms

#### Elapsed times (EMR):

    Aitana
        Català: 614.000 ms
        Español: 584.000 ms
        English: 586.000 ms

    Jordi
        Català: 344.000 ms
        Español: 382.000 ms
        English: 362.000 ms

    Arnau
        Català: 622.000 ms
        Español: 672.000 ms
        English: 684.000 ms

### Part 5

#### Number of processed tweets

    Aitana: 1217471 tweets
    
    Jordi: 1217471 tweets

    Arnau:  1217471 tweets

#### Total number of most retweeted tweets:

    Aitana: 10 tweets (1 per most retweeted user)
    
    Jordi: 10 tweets (1 per most retweeted user)

    Arnau: 10 tweets (1 per most retweeted user)

#### Execution times (local):

    Aitana: 78.945 ms

    Jordi: 41.670 ms

    Arnau:  88.986 ms

#### Elapsed times (EMR):

    Aitana: 764.000 ms

    Jordi: 475.000 ms

    Arnau: 874.000 ms

### Hardware (CPU - Memory RAM):
    Aitana
        CPU: 1.7 GHz Intel Core i7 of 4 cores
        Memory RAM: 16 GB 2133 MHz LPDDR3
    Jordi
        CPU: AMD Ryzen 7 6800H with Radeon Graphics 3.20 GHz
        Memory RAM: 32 GB 4800 MHz DDR5

    Arnau
        CPU: AMD Ryzen 7 5800H with Radeon Graphics 3.20 GHz
        Memory RAM: 16 GB 3200 MHz DDR4
