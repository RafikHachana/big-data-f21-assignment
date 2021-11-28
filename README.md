# Movie Recommendation System using Spark

Assignment submission for the Introduction to Big Data course, 

_Innopolis University, Fall Semester 2021_

Team name: _Big D_

GitHub repo: https://github.com/RafikHachana/big-data-f21-assignment

Team members:
- Hasan Khadra (_h.khadra@innopolis.university_)
- Kamil Sabbagh (_k.sabbagh@innopolis.university_)
- Rafik Hachana (_r.hachana@innopolis.university_)

---

## Table of contents
1. [ Introduction ](#intro)
2. [ Implementation in Scala ](#scala)
3. [ Running on a local Hadoop cluster ](#local)
4. [ Running on a private network ](#private)
5. [ Team members contributions ](#contri)
6. [ Conclusion ](#conc)

___
<a name="intro"></a>
### 1. Introduction
We implemented a movie recommendation system that suggests movies to the user. The system suggests the most suitable movies for the user depending on the ratings he gave to movies he already watched. The system is implemented in a local Hadoop cluster using several local VMs in Vagrant. We used HDFS as our file system and YARN as the resource manager. We also used Apache Spark as our analytics engine.

<a name="scala"></a>
### 2. Implementation in Scala

#### a. Code Completion
  - We implemented the funciton parseTitle where is extracts the title of the movie from each line of the dataset movies2.csv. It uses a regular expression to match the columns of the title.
  ```
  def parseTitle(filmEntry: String) = {
      ",\\\".*\\\",|,.*,".r findFirstIn filmEntry match {
      case Some(s) => s.slice(1, s.length - 1).replaceAll("\"","")
      case None => throw new Exception(s"Cannot parse Movie Title in {$filmEntry}")
    }
  }

  ```
  - We implemented `rmse(test: RDD[Rating], prediction: scala.collection.Map[Int, Double])` where we calculate the Root Mean Squared Error for the predictions of the model compared to the test dataset.
  ```
  def rmse(test: RDD[Rating], prediction: scala.collection.Map[Int, Double]) = {
    var ratings_predictions = test.map(x => (x.rating, prediction(x.product)))
      
    math.sqrt(ratings_predictions
      .map(x => (x._1 - x._2) * (x._1 - x._2))
      .reduce(_ + _) / test.count())
  }
  ```
#### b. Extra tasks
  - Post-processing of Recommendations: We excluded the movies that the user already rated by filtering the predicted movies with the RDD's filter method.
  - Loading Movie Preferences: We added the option of adding the movie prefreneces to a file `user_rating.tsv` instead of interactively providing them to the program. It was implemented through the class [`AutomaticGrader`](https://github.com/RafikHachana/big-data-f21-assignment/blob/main/MovieRecommender/src/AutomaticGrader.scala) where it reads the data from the file, and then converts it to RDD.
  - Rank of the Model: As we can see in the graphs, the rank $10$ is the best among all ranks w.r.t. Error After Training while $5$ is the best rank w.r.t. Baseline Error. 
  
  ![baseline](https://github.com/RafikHachana/big-data-f21-assignment/blob/main/plots/baseline.png) 
  ![trainerror](https://github.com/RafikHachana/big-data-f21-assignment/blob/main/plots/error.png)
  
  - Extra Filtering: We filtered the infrequent movies (we excluded the ones that have less than 50 ratings) by using the `filter` method in RDD.


<a name="local"></a>
### 3. Running on a local Hadoop cluster
We used HDSF file system, where we stored the datasets and the extra required files in. We had to edit several config files in order to make the distributed file system work among 3 local nodes. We edited the following files: `hadoop-env.sh`, `workers`, and `hdfs-site.xml`. Then we used YARN as a resource manager where we edited the following configuration files: `yarn-site.xml`, `mapred-site.xml`, and `core-site.xml`. In Spark, we used the following environment variables `SPARK_LOCAL_IP` and `HADOOP_CONF_DIR`. Note that all those configuration files are in [`local_config`](https://github.com/RafikHachana/big-data-f21-assignment/tree/main/local_config). Finally, in the Vagrantfile, we initiated 3 virtual machines locally connected in a private network. In this step we could have hosted the hadoop cluster on the host machine directly, but we preferred to make it a clean, more organized solution by using virtual machines with vagrant. 

<a name="private"></a>
### 4. Running on a private network

Describe the Vagrant config


<a name="contri"></a>
### 5. Team members contribution

Rafik Hachana: 
- Completed parseTitle and rmse(test: RDD[Rating], prediction: scala.collection.Map[Int, Double]).
- Post-processing of Recommendations Task.
- Load Your Movie Preferences Task.
- Setup the disterbuted physical cluster among different machines

Kamil Sabbgh
- Completed parseTitle and rmse(test: RDD[Rating], prediction: scala.collection.Map[Int, Double]).
- Change the Rank of the Model Task.
- Extra Filtering Task.
- Setup the disterbuted physical cluster among different machines

Hasan Khadra: 
- Setup the hadoop cluster locally.
- Load Your Movie Preferences Task.
- Change the Rank of the Model Task.
- Post-processing of Recommendations Task.

<a name="conc"></a>
### 6. Conclusion
