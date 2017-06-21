package com.ofiesh.slcsp;

import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertThat;

public class CsvParserTest {

    CsvParser csvParser = new CsvParser();

    @Test
    public void testCsvParsed() throws IOException, URISyntaxException {

        List<String[]> parsed = new ArrayList<>();
        csvParser.parse("Reader-test.csv").forEachRemaining(parsed::add);

        assertThat(parsed, contains(new String[]{"foo", "bar"}, new String[]{"Hello World!"}));
    }
}
