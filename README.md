Calculate second lowest cost silver plan (SLCSP)
=
Currently the program only reads CSVs packaged in the jar.
It could easily be changed to read from the same directory or be able to specify
file paths from the cli.


Storing large files in memory in Java is usually a bad idea,
so, the way this program was written trades speed for low memory use.
It minimizes the data needed in memory from the `plans` and `zips` csv
by reading the plans and zip file for each `slcsp` zip
and only keeping the data relative to that zip.

Alternatively the program could be changed to read the `zips` once and cache the results
in a `HashMap` by the zipcode, and read the `plans` once and cache the results in a `HashMap`
by `state + " " + area`.

To determine which is best other factors need to be considered,
such as where the CSVs are stored (local disk vs network/s3),
the size of the files and the memory concerns of whatever else is in the shared runtime.

The result file I generated is `out.csv.

Requirements
-
  Java 8  
  Maven
  
Compiling and Running
-
To run unit tests and compile the jar, run the following from the root of th e projcet
where the pom.xml is

    mvn clean test compile assembly:single

This will create `target/ofiesh-slcsp-1.0-jar-with-dependencies.jar`

To run the program, run

    java -jar target/ofiesh-slcsp-1.0-jar-with-dependencies.jar out.csv

where out.csv is the file or path to write the result.
