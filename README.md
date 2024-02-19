# Spark-Twitter-App

## Group Information 

Group: (P102, grup 05)

Aitana González (U186651, Bucket: lsds2024.lab2.output.u186651)

Jordi Alfonso (U111792, Bucket: lsds2024.lab2.output.u111792) 

Arnau Royo (U172499, Bucket: lsds2024.lab2.output.u172499)

## (For group mates) - How to execute

### (PART 2) Implement the Twitter filter using Spark

(!! Make sure that you have the tweets tar file downloaded - you can get it from the location given in the statement - and it is in the lab2 folder !!)

-> .tar source - public s3 path: s3://lsds2022/twitter-eurovision-2018.tar

1. Terminal: brew install openjdk@11 (only the first time!!)

2. Mvn: mvn clean

3. Mvn: mvn validate

4. Mvn: mvn package

5. Terminal (from the lab2 folder): jar tvf target/lab1-1.0-SNAPSHOT.jar

6. Mvn: mvn compile

7. Mvn: spark-submit --class edu.upf.TwitterLanguageFilterApp target/spark-test-1.0-SNAPSHOT.jar < language > < name_outputFolder > < name_twittterTarFile >

-> Example: spark-submit --class edu.upf.TwitterLanguageFilterApp target/spark-test-1.0-SNAPSHOT.jar es "/Users/aitanagonzalezcardenas/large-scale-distributed-systems/LAB2_LSDS/lsds2022.lab2/_output-folder_TLFA" "/Users/aitanagonzalezcardenas/large-scale-distributed-systems/LAB2_LSDS/lsds2022.lab2/TwitterEurovision2018.tar"

## Benchmark

### Part 2

#### Number of output tweets:

    Aitana
        Català: 4583 tweets
        Español: 509433 tweets
        English: 446601 tweets
    
    Jordi
        Català:  4583 tweets
        Español:  509433 tweets
        English: 446601 tweets

    Arnau
        Català:  4583 tweets
        Español:  509433 tweets
        English: 446601 tweets

#### Execution times:

    Aitana
        Català: 72095 ms
        Español: 89300 ms
        English: 86172 ms

    Jordi
        Català:  ms
        Español:  ms
        English:  ms

    Arnau
        Català:  403846 ms
        Español:  431285 ms
        English:  439621 ms

### Hardware (CPU - Memory RAM):
    Aitana
        CPU: 1.7 GHz Intel Core i7 of 4 cores
        Memory RAM: 16 GB 2133 MHz LPDDR3
    Jordi
        CPU: AMD Ryzen 7 6800H with Radeon Graphics 3.20 GHz
        Memory RAM: 32 GB 4800 MHz DDR5

    Arnau
        CPU: AMD Ryzen 7 5700u 4.3 GHz
        Memory RAM: 16 GB 3200 MHz DDR4
