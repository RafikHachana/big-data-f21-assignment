# Movie Recommendation System using Spark

Assignment submission for the Introduction to Big Data course, 

_Innopolis University, Fall Semester 2021_

Team name: _Big D_

Team members:

- Hasan Khadra (_h.khadra@innopolis.university_)
- Kamil Sabbagh (_k.sabbagh@innopolis.university_)
- Rafik Hachana (_r.hachana@innopolis.university_)
---

## Table of contents
1. [ Introduction ](#intro)
2. [ Implementation in Scala ](#scala)
  a. [ Code Completion ](#complete) 
3. [ Running on a local Hadoop cluster ](#local)
4. [ Running on a private network ](#private)
5. [ Conclusion ](#conc)

___
<a name="intro"></a>
### 1. Introduction
We implemented a movie recommendation system that suggests movies to the user. The system suggests the most suitable movies for the user depending on the ratings he gave to movies he already watched. The system is implemented in a local Hadoop cluster using several local VMs in Vagrant. We used HDFS as our file system and YARN as the resource manager.

<a name="scala"></a>
### 2. Implementation in Scala

#### a. Code Completion
  - We implemented the funciton parseTitle where is extracts the title of the movie from each line of the dataset movies2.csv. It uses a regular expression to match the columns of the title.
  - We implemented rmse(test: RDD[Rating], prediction: scala.collection.Map[Int, Double]) TODO
#### b. Extra tasks
  - Post-processing of Recommendations: We excluded the movies that the user already rated by filtering the predicted movies with the RDD's filter method.
  - Loading Movie Preferences: We added the option of adding the movie prefreneces to a file `user_rating.tsv` instead of interactively providing them to the program. It was implemented through the class `AutomaticGrader` where it reads the data from the file, and then converts it to RDD.
  - 


<a name="local"></a>
### 3. Running on a local Hadoop cluster
Describe the Vagrant config

<a name="private"></a>
### 4. Running on a private network

Describe the Vagrant config

<a name="conc"></a>
### 5. Conclusion
