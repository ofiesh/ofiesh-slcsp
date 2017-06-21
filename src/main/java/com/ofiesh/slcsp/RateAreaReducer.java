package com.ofiesh.slcsp;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Iterator;

/**
 * Returns the State and Rate Area for a given zipcode. If a zipcode matches more than one State and Rate Area it will
 * return null.
 */
public class RateAreaReducer {

    private final CsvParser csvParser;
    private final ZipFactory zipFactory = new ZipFactory();

    public RateAreaReducer(CsvParser csvParser) {
        this.csvParser = csvParser;
    }

    /**
     * Returns the State and Rate Area for a given zipcode. If a zipcode matches more than one State and Rate Area it will
     * return null.
     *
     * @param zipcode The zipcode to find a State and Rate Area for.
     * @param file The file to locate the zipcode data.
     * @return Returns a StateRateArea if there is a single unique combination found, otherwise null
     */
    @Nullable
    public StateRateArea getStateRateArea(@NotNull String zipcode, @NotNull String file) {
        StateRateArea stateRateArea = null;
        Iterator<String[]> iterator = csvParser.parse(file);
        while (iterator.hasNext()) {
            Zip zip = zipFactory.createZip(iterator.next());
            if (zip.getZipcode().equals(zipcode)) {
                if (stateRateArea == null) {
                    stateRateArea = new StateRateArea(zip.getState(), zip.getRateArea());
                } else {
                    // if the zip has a different state or area than one already found, the zipcode does not have a unique `rate area'
                    if (!stateRateArea.getState().equals(zip.getState())
                            || !stateRateArea.getRateArea().equals(zip.getRateArea())) {
                        return null;
                    }
                }
            }
        }
        return stateRateArea;
    }
}
