package com.ofiesh.slcsp;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;

/**
 * Returns a {@link Iterator<String[]>} which contains an array of CSV columns for each line in the file.
 * CsvParser is thread safe
 */
public class CsvParser {

    // TODO create a BufferedReader factory functional interface to mock buffered reader IO exceptions

    /**
     *
     * @param file path of the resource file to read.
     * @return a {@link Iterator<String[]>} of the file lines parsed into array for each csv column.
     */

    public Iterator<String[]> parse(@NotNull String file) {
        InputStream inputStream = ClassLoader.getSystemResourceAsStream(file);
        if (inputStream == null) {
            throw new NullPointerException("file " + file + " cannot be found");
        }

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        // skip csv header
        try {
            bufferedReader.readLine();
        } catch (IOException e) {
            try {
                bufferedReader.close();
            } catch (IOException closeException) {
                closeException.addSuppressed(e);
            }
            throw new RuntimeException(e);
        }

        Iterator<String[]> iterator = new Iterator<String[]>() {
            private String[] next;

            @Override
            public boolean hasNext() {
                return next != null;
            }

            @Override
            public String[] next() {
                String[] next = this.next;

                // after calling next, prime the next value if there is one.
                try {
                    String line = bufferedReader.readLine();

                    if (line != null) {
                        this.next = line.split(",");
                    // if there is no next value from the reader, set next to null
                    } else {
                        this.next = null;
                    }
                } catch (Exception e) {
                    try {
                        bufferedReader.close();
                    } catch (IOException closeError) {
                        e.addSuppressed(closeError);
                    }
                    throw new RuntimeException(e);
                }
                // if next == null, close the reader
                if (this.next == null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e) {
                        /*
                         TODO maybe throw a checked exception since the entire file is read, just can't be closed,
                         let the program finish and write out the file?
                         */
                        throw new RuntimeException(e);
                    }
                }
                return next;
            }
        };
        // prime the iterator
        iterator.next();
        return iterator;
    }
}
