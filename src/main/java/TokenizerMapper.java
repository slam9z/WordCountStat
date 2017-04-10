import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.StringTokenizer;

/**
 * Created by kozue on 4/4/17.
 *
 * dataset may looks like for word length = 2, amount = 1Million
 * az
 * aa
 * bn
 * xc
 * lp
 * az
 * ee
 * rt
 * ....
 */
public class TokenizerMapper extends Mapper<LongWritable, Text, Text, WordCountStatWritable> {
    //Instantiate Text object that will be used for writing to context
    private Text word = new Text();

    @Override
    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        String line = value.toString();
        StringTokenizer tokenizer = new StringTokenizer(line);
        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken();
            word.set(token);
            WordCountStatWritable output = new WordCountStatWritable(1,token.length(),1,0.0);
            output.setMeanVariance(0.0,0.0);
            output.setMinMax(0,0);
            context.write(word, output);

        }
    }

}
