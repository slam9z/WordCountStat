#cmd learned/used in this project:

#####ssh connection
`ssh NET_ID@IP_ADDRESS`

`ssh xxx@172.111.000.222`

#####create bash script
1. Add a "shebang" at the top of your file:`#!/bin/bash`
2. Using chmod u+x scriptname make the script executable: `chmod x script.sh`
3. Run the script using just the name of the script. `./script.sh`

#####compile the generate.c
`gcc -o <output> <path to the c program>`

`gcc -o generate generate.c`

#####run generate 
`./generate <amount of words> <word length> > <output filename>`

`./generate 50 8 > file.txt`

####Hadoop/hdfs cmd:
* _fs_ refers to any file system, it could be local
* _dfs_ refers to only HDFS file system
#######1. List the contents of the root directory in HDFS
`hadoop fs -ls`



