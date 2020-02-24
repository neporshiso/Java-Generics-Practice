package aggregators;

import fileprocessors.StockFileReader;

import java.io.IOException;
import java.util.List;

public class AggregatorProcessor <M extends Aggregator> {

    List<Double> numbers;
    Aggregator agg;
    String s;

    public AggregatorProcessor(M agg, String s) {
        this.agg = agg;
        this.s = s;
        this.numbers = agg.getValues();
    }


    public double runAggregator(int i) throws IOException {
        StockFileReader read = new StockFileReader(s);
        List<String> data = read.readFileData();
        for (String num: data
             ) {
            String[] split = num.split(",");
            Double val = Double.parseDouble(split[i-1]);
            numbers.add(val);
        }

        double result = agg.calculate();
        return result;
    }

}
