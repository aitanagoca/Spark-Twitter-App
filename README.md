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

7. Mvn: spark-submit --class edu.upf.BiGramsApp target/spark-test-1.0-SNAPSHOT.jar < language > < name_outputFolder > < name_twittterTarFile >

### (PART 5) Most Retweeted Tweets for Most Retweeted Users

1. Terminal: brew install openjdk@11 (only the first time!!)

2. Mvn: mvn clean

3. Mvn: mvn validate

4. Mvn: mvn compile

5. Mvn: mvn package

6. Terminal (from the lab2 folder): jar tvf target/spark-test-1.0-SNAPSHOT.jar

7. Mvn: spark-submit --class edu.upf.MostRetweetedApp target/spark-test-1.0-SNAPSHOT.jar < name_outputFolder > < name_twittterTarFile >

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
        Català: 72095 ms
        Español: 89300 ms
        English: 86172 ms

    Jordi
        Català: 38321 ms
        Español: 38824 ms
        English: 38734 ms

    Arnau
        Català: 83515 ms
        Español: 509433 ms
        English: 446601 ms

### Part 3

#### Execution times (EMR):

    Aitana
        Català:  ms
        Español:  ms
        English:  ms

    Jordi
        Català:  ms
        Español:  ms
        English:  ms

    Arnau
        Català:  ms
        Español: ms
        English: ms

### Part 4

#### Number of bigrams:

    Aitana
        Català: 19408 bigrams
        Español: 586992 bigrams
        English: 970005 bigrams
    
    Jordi
        Català: bigrams
        Español: bigrams
        English: bigrams

    Arnau
        Català: bigrams
        Español: bigrams
        English: bigrams

#### Execution times (local):

    Aitana
        Català: 72150 ms
        Español: 88778 ms
        English: 104272 ms

    Jordi
        Català:  ms
        Español:  ms
        English:  ms

    Arnau
        Català:  ms
        Español: ms
        English: ms

#### Execution times (EMR):

    Aitana
        Català:  ms
        Español:  ms
        English:  ms

    Jordi
        Català:  ms
        Español:  ms
        English:  ms

    Arnau
        Català:  ms
        Español: ms
        English: ms

### Part 5

#### Number of processed tweets

    Aitana: 1217471 tweets
    
    Jordi:  tweets

    Arnau:  tweets

#### Total number of most retweeted tweets:

    Aitana: 10 tweets (1 per most retweeted user)
    
    Jordi:  tweets (1 per most retweeted user)

    Arnau:  tweets (1 per most retweeted user)

#### Execution times (local):

    Aitana: 78945 ms

    Jordi:  ms

    Arnau:  ms

#### Execution times (EMR):

    Aitana:  ms

    Jordi:  ms

    Arnau:  ms

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
