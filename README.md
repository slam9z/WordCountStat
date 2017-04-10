# WordCountStat
## Introduction to Cloud and Big Data Systems - Assignment 2.

The program is an alternative version of word count, instead of diplaying the count of each words, it displays the mean, variance, and standard deviation as final output for each configuration.

**Objectives**
* Familiarize with HDFS
* Familiarize with Hadoop
* Gain hands-on experience: MapReduce programming(using python/Java)
  * Generate data sets
  * Analyze data sets from HDFS
  * Study the outcomes of different Hadoop jobs
  
**Assignment Description**
* Participation through the forums is highly encouraged
* We want to illustrate the probability of word repetitions in a random dataset considering amount of data and word length
1. Generate data sets
  * Use the random word generator located at $HADOOP_HOME/generate
  * Create directories in HDFS (in /user/NETID or a subdirectory) for different data sets:
    *Create datasets with words of length 2,…,6 characters
    *Create datasets with 1M, 10M, 100M and 200M words
2. Implement a MapReduce application using Hadoop to obtain, programmatically (using Python or your preferred language), the following metrics
  * Average, standard deviation, maximum and minimum number of repetitions of all words of a given length in a dataset
  * For each of the configurations as defined above

**Question**
1. Provide the code that you have developed to run the proposed problem in Hadoop/HDFS
2. Provide the results obtained (statistics e.g., in a plot)
3. Provide your observations and conclusions based on your experiments
4. Specific question: describe the order in which map and reduce tasks are executed in Hadoop for different configurations

**Lesson Learned**
1. Getting familiar with Hadoop and HDFS environment and user cmd
2. Using bash script to auto generate the datasets & hadoop map-reduce application execution
  * bash/CMD_README.md: notes of all the bash used in this project
3. Implement the MapReduce application:
  * Mapper class implementation
    ####Methods Summary:
        * setup()  :Called once at the beginning of the task.
        * map()    :Called once for each key/value pair in the input split.
        * cleanup():Called once at the end of the task.
        * run()    :
  * Implement Custom Hadoop Writable Data Type - It is not neccessary for this project, but good for knowing
  * Reducer class implementation
    ####Methods Summary:
        setup()  :Called once at the beginning of the task.
        reduce() :Called once for each key/value pair in the input split.
        cleanup():Called once at the end of the task.
        run()    :
