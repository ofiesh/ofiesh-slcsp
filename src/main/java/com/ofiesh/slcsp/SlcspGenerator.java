package com.ofiesh.slcsp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Iterator;

/**
 * Generates the output data driven by the slcsp file.
 */
public class SlcspGenerator {

    private final Logger logger = LoggerFactory.getLogger(SlcspGenerator.class);
    private final CsvParser csvParser;
    private final RateAreaReducer rateAreaReducer;
    private final SlcspRateReducer slcspRateReducer;

    public SlcspGenerator(CsvParser csvParser, RateAreaReducer rateAreaReducer, SlcspRateReducer slcspRateReducer) {
        this.csvParser = csvParser;
        this.rateAreaReducer = rateAreaReducer;
        this.slcspRateReducer = slcspRateReducer;
    }

    /**
     * Gerenates the output data driven by the slcsp file
     * @param slcspFile The slcsp file with zipcodes and missing rate data
     * @param zipsFile the zips file to map a zip to a rate area
     * @param plansFile the plans file to find plan rates for a rate area
     */
    public void run(String slcspFile, String zipsFile, String plansFile, String outFile) {
        try (FileWriter fileWriter = new FileWriter(outFile)) {
            // add csv header
            fileWriter.write("zipcode,rate");
            Iterator<String[]> iterator = csvParser.parse(slcspFile);
            while (iterator.hasNext()) {
                String zip = iterator.next()[0];
                String rate = "";
                StateRateArea rateArea = rateAreaReducer.getStateRateArea(zip, zipsFile);
                if (rateArea != null) {
                    BigDecimal slcspRate = slcspRateReducer.getSlcspRate(rateArea, plansFile);
                    if (slcspRate != null) {
                        rate = slcspRate.toString();
                    } else {
                        logger.info("Unable to get slcsp rate for " + zip +
                                ", " + rateArea.getState() + " " + rateArea.getRateArea());
                    }
                } else {
                    logger.info("Unable to get rate area for " + zip);
                }
                fileWriter.write("\n" + zip + "," + rate);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
