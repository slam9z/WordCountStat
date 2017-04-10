#!/bin/bash
#path to generate the output in HDFS, /use
path_output="/user/shaaulam/output/"
#path to HDFS input files 
path_input="/user/shaaulam/input/"
#path to the jar file, assume is under your current direcotry, if not please change to where the jar file located
jar_file="WordCountStat-1.0-BUILD.jar"

class_name="com.bigdata.WordCountStat"

charLens=("2" "3" "4" "5" "6")
charFreqs=("1000000" "10000000" "100000000" "20000000")
filenameFreq=("1M" "10M" "100M" "200M")
Split="_"
fileExt=".txt"

clen=${#charLens[@]}
cFreq=${#charFreqs[@]}

#for loop
for((i=0; i<$clen;i++));
do
	#echo ${charLens[${i}]}
	echo "hdfs dfs -mkdir -p $path_output${charLens[${i}]}"
        hdfs dfs -mkdir -p $path_output${charLens[${i}]}
	
	for((j=0; j <$cFreq;j++));
	do
	#	echo "hdfs dfs -mkdir -p $path_output${charLens[${i}]}/${filenameFreq[${j}]}"
	#	hdfs dfs -mkdir -p $path_output${charLens[${i}]}

		echo "hadoop jar $jar_file $class_name $path_input${charLens[${i}]}/file_${filenameFreq[${j}]}$fileExt $path_output${charLens[${i}]}/${filenameFreq[${j}]}"
		hadoop jar $jar_file $class_name $path_input${charLens[${i}]}/file_${filenameFreq[${j}]}$fileExt $path_output${charLens[${i}]}/${filenameFreq[${j}]}
	
		echo "hadoop dfs -cat $path_output${charLens[${i}]}/${filenameFreq[${j}]}/part-r-00000"
		hadoop dfs -cat $path_output${charLens[${i}]}/${filenameFreq[${j}]}/part-r-00000
		hdfs dfs -get hdfs:///user/shaaulam/output/${charLens[${i}]}/ /user/shaaulam/ouput/
	done
done

#hdfs dfs -get hdfs:///user/shaaulam/output${charLens[${i}]}/${filenameFreq[${j}]} /user/shaaulam
