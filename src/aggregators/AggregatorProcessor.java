package aggregators;

import fileprocessors.StockFileReader;

import java.io.IOException;
import java.util.List;

public class AggregatorProcessor <M extends Aggregator> {

    M aggregator;
    String file;

    public AggregatorProcessor(M aggregator, String file) {
        super();
        this.aggregator = aggregator;
        this.file = file;
    }

    public double runAggregator(int colIdx) throws IOException {
        StockFileReader fileReader = new StockFileReader(file);
        List<String> lines = fileReader.readFileData();
        colIdx--;
        for (String line: lines
             ) {
            String[] numbers = line.split(",");
            Double val = Double.parseDouble(numbers[colIdx]);
            aggregator.add(val);
        }

        double number = aggregator.calculate();
        return number;
    }

}
