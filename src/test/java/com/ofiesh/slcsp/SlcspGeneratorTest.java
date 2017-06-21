package com.ofiesh.slcsp;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.*;

public class SlcspGeneratorTest {

    CsvParser csvParser = new CsvParser();
    private final SlcspGenerator slcspGenerator = new SlcspGenerator(new CsvParser(),
            new RateAreaReducer(csvParser), new SlcspRateReducer(csvParser));

    @Test
    public void testGenerateSlcspData() throws IOException {
        slcspGenerator.run("slcsp.csv", "zips.csv", "plans.csv",
                "target/test-out.csv");
        List<String> sclspData = Files.readAllLines(Paths.get("target/test-out.csv"));
        assertThat(sclspData, contains(
                "zipcode,rate",
                "1,",
                "2,",
                "3,2",
                "4,",
                "5,"
        ));
    }
}