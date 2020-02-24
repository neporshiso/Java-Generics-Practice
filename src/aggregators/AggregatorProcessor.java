package aggregators;

import fileprocessors.StockFileReader;

import java.io.IOException;
import java.util.List;

public class AggregatorProcessor <M extends Aggregator> {

    // I didn't have to declare numbers here as I have access to numbers in the parent class Aggregator
    List<Double> numbers;
    Aggregator agg;
    String s;

    // could have named s better... it's a filepath
    public AggregatorProcessor(M agg, String s) {
        this.agg = agg;
        this.s = s;
        // unnecessary since agg already has numbers
        this.numbers = agg.getValues();
    }


    public double runAggregator(int i) throws IOException {
        StockFileReader read = new StockFileReader(s);
        List<String> data = read.readFileData();
        for (String num: data
             ) {
            String[] split = num.split(",");
            Double val = Double.parseDouble(split[i-1]);
            // in the officialy solution, agg.add(val) works because there is already an add method implemented in Aggregator that appends a Double to a List of Doubles
            numbers.add(val);
        }

        double result = agg.calculate();
        return result;
    }

}
