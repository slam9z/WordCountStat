import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * Created by kozue on 4/4/17.
 *
 *
 * The parameters used for calculation:
 * count = only count unique words appear -> the random generate dataset may have duplicate words
 * words = the total amount of words in the dataset including duplicate words
 * max = the maximum number of the repeated words appear in each datasets
 * min = the minimun number of the repeated words appear in each datasets
 * freqSqrt = the sum of all Xi^2, where i = total of each words appears in the dataset
 *
 * mean formula(xbar): total words appear / total unique words = words / counts
 * variance formula: [(∑ Xi^2) - (n * xbar ^ 2)]/(n - 1) ; n = count
 *
 */
public class StdDevReducer extends Reducer<Text, WordCountStatWritable, Text, WordCountStatWritable> {

    private final static Text RESULT = new Text("RESULT: ");
    private static WordCountStatWritable output = new WordCountStatWritable();
    long count = 0, words = 0;
    long max = Integer.MIN_VALUE;
    long min = Integer.MAX_VALUE;
    double freqSqrt = 0.0;

    @Override
    public void reduce(Text key, Iterable<WordCountStatWritable> values, Context context) throws IOException, InterruptedException{
        long freq = 0;
        double mean, variance;

        for (WordCountStatWritable value : values) {
            freq += value.getFreq();
            min = Math.min(freq,min);
            max = Math.max(freq,max);
            words += 1;
        }

        freqSqrt += (freq) * (freq);
        count += 1;
        mean = (words * 1.0) / count;

        //Get the mean and variance for word
        variance = getCalVariance(freqSqrt, count, mean);
        output.setWordLength(key.getLength());
        output.setDocCount(count);
        output.setFreq(words);
        output.setFreqSquared(freqSqrt);
        output.setMinMax(min, max);
        output.setMeanVariance(mean, variance);
    }

    //Variance formula: (Sum(Xi ^ 2) - [n * (xbar)^2]) / (n - 1)
    public double getCalVariance(double sumX2, double n, double xBar){
        double variance = (sumX2  - n * Math.pow(xBar, 2)) / (n - 1);
        return variance;
    }

    @Override
    public void cleanup(Context context) throws IOException,InterruptedException{
        context.write(RESULT, output);
    }

}
