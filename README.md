CalculatorIoData
================

This Java project runs a list of operations e.g. `add`, `divide`, `subtract`, `multiply`
according to a input file with instructions.

Compilation
-----------
Use Maven to compile it. Run the following command from the root folder of this project:

    $ mvn clean package

Running the project
-------------------
To run the project from a Linux command line, use the following command:

    $ java -jar target/calculatorIo.jar src/main/resources/example3.txt

where:

 - `target/calculatorIo.jar` is the path to the `jar` file;
 - `src/main/resources/example3.txt` is the path to the input file with the instructions.

