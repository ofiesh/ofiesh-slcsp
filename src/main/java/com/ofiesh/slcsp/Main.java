package com.ofiesh.slcsp;

import java.io.IOException;
import java.net.URISyntaxException;

public class Main {

    public static void main(String... args) throws IOException, URISyntaxException {
        if (args.length != 1) {
            System.out.println("Use java -jar slcsp.jar /path/to/outfile");
            return;
        }

        CsvParser csvParser = new CsvParser();
        new SlcspGenerator(new CsvParser(),
                new RateAreaReducer(csvParser),
                new SlcspRateReducer(csvParser))
                .run("slcsp.csv", "zips.csv", "plans.csv", args[0]);
    }

}