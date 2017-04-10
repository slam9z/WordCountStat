import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

/**
 * Created by kozue on 4/4/17.
 */
public class WordCountStat {

    public static void main(String[] args) throws Exception {

            if (args.length != 2) {
                System.err.println("Usage: WordCountStat <input path> <output path>");
                return;
            }
            Configuration conf = new Configuration();

            Job job = new Job(conf, "WordCountStat");
            // Identify the JAR file to replicate to all machines.
            job.setJarByClass(WordCountStat.class);

            // Set the output key and value types (for map and reduce).
            job.setOutputKeyClass(Text.class);
            job.setOutputValueClass(WordCountStatWritable.class);

            // Set the map and reduce classes.
            job.setMapperClass(TokenizerMapper.class);
            //job.setCombinerClass(StdDevReducer.class);
            job.setReducerClass(StdDevReducer.class);

            // Set the input and output file formats.
            job.setInputFormatClass(TextInputFormat.class);
            job.setOutputFormatClass(TextOutputFormat.class);

            // Grab the input file and output directory from the command line.
            FileInputFormat.addInputPath(job, new Path(args[0]));
            FileOutputFormat.setOutputPath(job, new Path(args[1]));

            // Initiate the map-reduce job, and wait for completion.
            job.waitForCompletion(true);

    }
}
