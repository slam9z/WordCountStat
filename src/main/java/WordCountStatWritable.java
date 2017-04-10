import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.text.DecimalFormat;

/**
 * Created by kozue on 4/4/17.
 */
public class WordCountStatWritable implements Writable {

    private long docCount;
    private long freq;
    private double freqSquared;
    private double mean;
    private double variance;
    private double sd;
    private long min;
    private long max;
    private int wordLength;

    public WordCountStatWritable() {

    }

    public WordCountStatWritable(long docCount, int wordLength, long freq, double freqSquared) {
        this.docCount = docCount;
        this.freq = freq;
        this.wordLength = wordLength;
        this.freqSquared = freqSquared;
    }

    public long getDocCount() {
        return docCount;
    }

    public void setDocCount(long docCount) {
        this.docCount = docCount;
    }

    public long getFreq() {
        return freq;
    }

    public void setFreq(long freq) {
        this.freq = freq;
    }

    public double getFreqSquared() {
        return freqSquared;
    }

    public void setFreqSquared(double freqSquared) {
        this.freqSquared = freqSquared;
    }

    public double getMean() {
        return mean;
    }

    public void setMean(double mean) {
        this.mean = mean;
    }

    public double getVariance() {
        return variance;
    }

    public void setVariance(double variance) {
        this.variance = variance;
    }

    public double getSd() {
        return sd;
    }

    public void setSd(double sd) {
        this.sd = sd;
    }

    public long getMin() {
        return min;
    }

    public void setMin(long min) {
        this.min = min;
    }

    public long getMax() {
        return max;
    }

    public void setMax(long max) {
        this.max = max;
    }

    //Sets two doubles given as parameters
    public void setMeanVariance(double mean,double variance){
        setMean(mean);
        setVariance(variance);
        setSd(Math.pow(variance,0.5));
    }

    //Sets min and max
    public void setMinMax(long minVal,long maxVal){
        setMax(maxVal);
        setMin(minVal);
    }

    public int getWordLength() {
        return wordLength;
    }

    public void setWordLength(int wordLength) {
        this.wordLength = wordLength;
    }

    //Read the values from the DataInput and store them within this class
    public void readFields(DataInput in) throws IOException {
        //Set doc count
        setDocCount(in.readLong());
        //Set frequency
        setFreq(in.readLong());
        //Set frequency squared
        setFreqSquared(in.readLong());
        //Set mean
        setMean(in.readDouble());
        //Set variance
        setVariance(in.readDouble());
        //set standard deviation
        setSd(in.readDouble());

        setMin(in.readLong());

        setMax(in.readLong());

        setWordLength(in.readInt());
    }

    //Write values saved in this class out to DataOutput
    public void write(DataOutput out) throws IOException {
        //Write doc
        out.writeLong(getDocCount());
        out.writeLong(getFreq());
        out.writeDouble(getFreqSquared());
        out.writeDouble(getMean());
        out.writeDouble(getVariance());
        out.writeDouble(getSd());
        out.writeLong(getMin());
        out.writeLong(getMax());
        out.writeInt(getWordLength());
    }

    @Override
    public String toString() {

        StringBuilder result = new StringBuilder();
        DecimalFormat df = new DecimalFormat("#.######");

        String NEW_LINE = System.getProperty("line.separator");
        result.append(NEW_LINE +"====================================="+NEW_LINE);
        result.append(" Word Size: \t\t\t" + wordLength + NEW_LINE);
        result.append(" Total Words: \t\t\t" + freq + NEW_LINE);
        result.append(" Total Non_Repeated: \t" + docCount + NEW_LINE);
        result.append(" Min: \t\t\t\t\t" + min + NEW_LINE );
        result.append(" Max: \t\t\t\t\t" + max + NEW_LINE);
        result.append(" Mean: \t\t\t\t\t" + df.format(mean) + NEW_LINE);
        result.append(" Variance: \t\t\t\t" + df.format(variance) + NEW_LINE);
        result.append(" Std Deviation: \t\t" + df.format(sd) + NEW_LINE);
        result.append(NEW_LINE);
        return result.toString();

    }

}
