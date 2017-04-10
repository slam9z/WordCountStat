#!/bin/bash
#Define bash global variable

charLens=("2" "3" "4" "5" "6")
charFreqs=("1000000" "10000000" "100000000" "200000000")
filenameFreq=("1M" "10M" "100M" "200M")
Split="_"
fileExt=".txt"

clen=${#charLens[@]}
cFreq=${#charFreqs[@]}
#echo "cFreq: " + $cFreq
#for loop

for((i=0; i<$clen;i++));
do
	#echo ${charLens[${i}]}
	filename="$(pwd)/input/${charLens[${i}]}/file_"
	mkdir "$(pwd)/input/${charLens[${i}]}"
	
	for((j=0; j <$cFreq;j++));
	do
		./generate ${charFreqs[${j}]} ${charLens[${i}]} > $filename${filenameFreq[${j}]}$fileExt
		#echo "hdfs dfs -put $filename${charLens[${i}]}$Split${filenameFreq[${j}]}$fileExt /user/shaaulam/input"
		echo "generate ${charFreqs[${j}]} ${charLens[${i}]} > $filename${filenameFreq[${j}]}$fileExt"
	done
done
echo "Uploading files to HDFS..."
hdfs dfs -put "$(pwd)/input/" /user/shaaulam
echo "Completed"

