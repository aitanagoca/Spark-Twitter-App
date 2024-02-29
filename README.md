# Spark-Twitter-App

(For better viewing, you can visit: https://github.com/aitanagoca/Spark-Twitter-App)

## Group Information 

👥 Group: (P102, grup 05)

Aitana González (U186651, Bucket: lsds2024.lab2.output.u186651)

Jordi Alfonso (U111792, Bucket: lsds2024.lab2.output.u111792) 

Arnau Royo (U172499, Bucket: lsds2024.lab2.output.u172499)

## (For group mates) - How to execute

⚠️ Make sure that you have the tweets tar file downloaded - you can get it from the location given in the statement - and it is in the lab2 folder !!

🔗 public s3 path: s3://lsds2022/twitter-eurovision-2018.tar

### (PART 2) Implement the Twitter filter using Spark

1️⃣ Terminal: brew install openjdk@11 (only the first time!!)

2️⃣ Mvn: mvn clean

3️⃣ Mvn: mvn validate

4️⃣ Mvn: mvn compile

5️⃣ Mvn: mvn package

6️⃣ Terminal (from the lab2 folder): jar tvf target/spark-test-1.0-SNAPSHOT.jar

7️⃣ Mvn: spark-submit --class edu.upf.TwitterLanguageFilterApp target/spark-test-1.0-SNAPSHOT.jar < language > < name_outputFolder > < name_twittterTarFile >

### (PART 3) Implement the Twitter filter using Spark EMR

⚠️ Make sure that you have uploaded the input file and the jar on the specified folders - given in the statement - of the S3, and that you have created the corrected output folder for the outputs!!

1️⃣ Create cluster (follow guide)

2️⃣ (WHEN ADDING STEP IN CLUSTER): spark-submit --class edu.upf.TwitterLanguageFilterApp s3://lsds2024.lab2.output.uxxxxxx/jars/spark-test-1.0-SNAPSHOT.jar < language > < name_outputFolder > < name_twittterTarFile >

### (PART 4) Most popular bi-grams in a given language

1️⃣ Terminal: brew install openjdk@11 (only the first time!!)

2️⃣ Mvn: mvn clean

3️⃣ Mvn: mvn validate

4️⃣ Mvn: mvn compile

5️⃣ Mvn: mvn package

6️⃣ Terminal (from the lab2 folder): jar tvf target/spark-test-1.0-SNAPSHOT.jar

7️⃣ Mvn: spark-submit --class spark.BiGramsApp target/spark-test-1.0-SNAPSHOT.jar < language > < name_outputFolder > < name_twittterTarFile >

⚠️ You have to execute it too in EMR !!

1️⃣ Create cluster (follow guide)

2️⃣ (WHEN ADDING STEP IN CLUSTER): spark-submit --class spark.BiGramsApp s3://lsds2024.lab2.output.uxxxxxx/jars/spark-test-1.0-SNAPSHOT.jar < language > < name_outputFolder > < name_twittterTarFile >

### (PART 5) Most Retweeted Tweets for Most Retweeted Users

1️⃣ Terminal: brew install openjdk@11 (only the first time!!)

2️⃣ Mvn: mvn clean

3️⃣ Mvn: mvn validate

4️⃣ Mvn: mvn compile

5️⃣ Mvn: mvn package

6️⃣ Terminal (from the lab2 folder): jar tvf target/spark-test-1.0-SNAPSHOT.jar

7️⃣ Mvn: spark-submit --class spark.MostRetweetedApp target/spark-test-1.0-SNAPSHOT.jar < name_outputFolder > < name_twittterTarFile >

⚠️ You have to execute it too in EMR !!

1️⃣ Create cluster (follow guide)

2️⃣ (WHEN ADDING STEP IN CLUSTER): spark-submit --class spark.MostRetweetedApp s3://lsds2024.lab2.output.uxxxxxx/jars/spark-test-1.0-SNAPSHOT.jar < name_outputFolder > < name_twittterTarFile >

### (Example EMR - cluster with 2 cores + steps)

<img width="1350" alt="Captura de pantalla 2024-02-29 a les 11 58 48" src="https://github.com/aitanagoca/Spark-Twitter-App/assets/92036724/c0686e8a-1f75-427b-9232-4fe20660aa53">

<img width="984" alt="Captura de pantalla 2024-02-29 a les 18 07 32" src="https://github.com/aitanagoca/Spark-Twitter-App/assets/92036724/1c907d5d-84ac-430e-94a5-6474456acafc">

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
        Español: 90354 ms
        English: 87951 ms

### Part 3

#### Elapsed times (EMR):

    Aitana
        Català: 362000 ms
        Español: 322000 ms
        English: 316000 ms

    Jordi
        Català: 240000 ms
        Español: 196000 ms
        English: 202000 ms

    Arnau
        Català:  336000 ms
        Español: 340000 ms
        English: 358000 ms

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
        Català: 72150 ms
        Español: 88778 ms
        English: 104272 ms

    Jordi
        Català: 31127 ms
        Español: 45405 ms
        English: 46084 ms

    Arnau
        Català: 65800 ms
        Español: 97987 ms
        English: 93769 ms

#### Elapsed times (EMR):

    Aitana
        Català: 344000 ms
        Español: 388000 ms
        English: 362000 ms

    Jordi
        Català: 248000 ms
        Español: 282000 ms
        English: 280000 ms

    Arnau
        Català: 342000 ms
        Español: 384000 ms
        English: 384000 ms

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

    Aitana: 78945 ms

    Jordi: 41670 ms

    Arnau:  88986 ms

#### Elapsed times (EMR):

    Aitana: 436000 ms

    Jordi: 276000 ms

    Arnau: 468000 ms

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

### Observations

- Generally, EMR (Elastic MapReduce) with 2 cores takes longer to execute compared to local execution for the given tasks. Note that, for EMR, the more cores we use, the faster our task will be carried out.

- The difference in execution times between local and EMR varies depending on the task and the dataset.

- EMR tends to scale better with larger datasets, but it incurs higher overhead for smaller tasks due to the setup and teardown time of the EMR cluster.

- Hardware configurations also play a significant role in the execution times, particularly CPU speed and memory capacity.

Overall, the choice between local execution and EMR depends on factors such as dataset size, computational resources available, and the trade-off between setup overhead and performance gains.

## Outputs

The different outputs ar stored in s3://lsds2024.lab2.output.uxxxxxx/output/benchmark.

<img width="1097" alt="Captura de pantalla 2024-02-27 a les 23 15 14" src="https://github.com/aitanagoca/Spark-Twitter-App/assets/92036724/40a9c9fd-6f8d-4766-ac5c-31260d86382b">

As we can see, there are different folders depending on the app used:

- _output-folder_TLFA_<language>: stores the output for Twitter Filter Language App (separated by languages).

        Example (extract from _output-folder_TLFA_EN):

<img width="644" alt="Captura de pantalla 2024-02-29 a les 18 15 29" src="https://github.com/aitanagoca/Spark-Twitter-App/assets/92036724/c7286d89-751a-491e-b1ed-569aee9a21cd">

-  _output-folder_BA_<language>: stores the output for BiGrams App (separated by languages).

        🏅 Top 10 entries (EN):
   
            <of, the>: 21281
            <in, the>: 13671
            <this, is>: 13338
            <for, the>: 11443
            <the, uk>: 9884
            <rt, @eurovision:>: 9747
            <rt, @bbceurovision:>: 9558
            <vote, for>: 9196
            <in, #eurovision>: 8513
            <song, contest>: 8393

        🏅 Top 10 entries (ES):
   
            <#eurovision, #finaleurovision>: 29256
            <de, la>: 24889
            <en, el>: 21524
            <en, #eurovision>: 16829
            <el, que>: 16564
            <lo, que>: 16549
            <que, no>: 15613
            <y, amaia>: 13458
            <en, la>: 13138
            <el, año>: 12649

        🏅 Top 10 entries (CA):
   
            <alexander, rybak>: 404
            <es, el>: 369
            <de, noruega.>: 347
            <-, 2018.>: 346
            <el, jordi>: 346
            <2009, ->: 346
            <#eurovision, https://t.co/b091qrmq5l>: 346
            <jordi, hurtado>: 346
            <hurtado, de>: 346
            <2018., alexander>: 346

         📋 Note: In each language section, the most common pairs of words or tokens are shown, along with their frequencies. 

-  _output-folder_MRA_<language>: stores the output for Most Retweeted App.

         🏅 Most retweeted tweet for the 10 most retweeted users:
   
            - {'tweetID': 995445778528292864, 'text': RT @ManelNMusic: Enhorabuena @NettaBarzilai por tu merecida victoria! Gran talento, carisma arrollador y una canción que te engancha a la…, 'userId': 437025093, 'userName:' Belen Hernandez, 'language': es, 'timestampMs': 1526167742746}

            - {'tweetID': 995441953625067522, 'text': RT @bbceurovision: The moment the Israeli delegation found out they had won Eurovision 2018 🎉🐔🎈👏👏👏 Congratulations @NettaBarzilai #Eurovisi…, 'userId': 24679473, 'userName:' Charlotte, 'language': en, 'timestampMs': 1526166830818}
   
            - {'tweetID': 995438274574520320, 'text': RT @PaquitaSalas: Lo que yo os diga: en un primer plano, los pelos como escarpias. Pero por favor os lo pido, dejad de quemarme el WhatsApp…, 'userId': 739812492310896640, 'userName:' cris 👅, 'language': es, 'timestampMs': 1526165953664}
   
            - {'tweetID': 995447753747595265, 'text': RT @Eurovision: We have a message for you from the winner of #Eurovision 2018! Congratulations @NettaBarzilai! #ESC2018 #AllAboard https://…, 'userId': 15584187, 'userName:' martu { -56 -190} 🇸🇪💙, 'language': en, 'timestampMs': 1526168213675}
   
            - {'tweetID': 995392980596002817, 'text': RT @Uznare: eurovision rules https://t.co/I8cG3D5tCh, 'userId': 29056256, 'userName:' JoJohansen, 'language': en, 'timestampMs': 1526155154738}
   
            - {'tweetID': 995439844410249216, 'text': RT @LVPibai: Rodolfo Chikilicuatre, un actor disfrazado con una guitarra de plástico quedó siete puestos por encima que la ganadora de un c…, 'userId': 2754746065, 'userName:' Todo sobre SLO-CoD, 'language': es, 'timestampMs': 1526166327942}
   
            - {'tweetID': 995438238520299520, 'text': RT @pewdiepie: My chicken is not your goddamn prom dress #Eurovision, 'userId': 39538010, 'userName:' Johannes Perterer, 'language': en, 'timestampMs': 1526165945068}
   
            - {'tweetID': 995438291909513217, 'text': RT @auronplay: España con 0 de vida por favor que alguien nos de un botiquín o unas vendas por favor jaja #Eurovision, 'userId': 1501434991, 'userName:' jdptrdz, 'language': es, 'timestampMs': 1526165957797}
   
            - {'tweetID': 995356844947398661, 'text': RT @NetflixES: Ella está al mando. Con @PaquitaSalas nada malo puede pasar, ¿no? #Eurovision https://t.co/5HeUDCqxX6, 'userId': 3143260474, 'userName:' Lula, 'language': es, 'timestampMs': 1526146539328}
   
            - {'tweetID': 995438907838947329, 'text': RT @elmundotoday: El patrocinio de Turismo de Portugal, que ha costado 60 millones de euros, ha conseguido convencer a un señor de Ucrania…, 'userId': 38381308, 'userName:' Adri Laborda, 'language': es, 'timestampMs': 1526166104646}

### Observations
- Among the top retweeted users, Spanish-language tweets are the most prevalent, followed by English. Catalan tweets don't even appear.
- By examining the content of the most retweeted tweets, it is obvious that the Eurovision Song Contest is a prominent topic as the given dataset is about it; however, topics such as winners, performances and reactions are highlighted.
