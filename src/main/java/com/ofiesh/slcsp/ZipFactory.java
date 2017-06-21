package com.ofiesh.slcsp;

import java.util.Arrays;

/**
 * Creates a {@link Zip} from the zips csv
 */
public class ZipFactory {

    /**
     * Creates a {@link Zip} from the zips csv
     *
     * @param csvColumns Array of String columns where columns are [ zipcode, state, county_code, name, rate_area ]
     * @return The {@link Zip} constructed from the csv line.
     */
    public Zip createZip(String[] csvColumns) {
        if (csvColumns.length != 5) {
            throw new IllegalArgumentException(Arrays.toString(csvColumns) + " not of expected length, 5");
        }
        return new Zip(csvColumns[0], csvColumns[1], csvColumns[2], csvColumns[3], csvColumns[4]);
    }
}
