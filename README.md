CalculatorIoData
================

This Java project runs a list of operations e.g. `add`, `divide`, `subtract`, `multiply`
according to a input file with instructions.
The aim is to **calculate a result from a set of instructions**.
The last instruction should be `apply` and a number (e.g., `apply 3`).
The calculator is then initialised with that number and the previous instructions are applied to that number.

Basically the idea here is to skip all the lines in the file but the last to initialise the process.
The I/O operations should be efficient, i.e. no loading of the whole file in memory before the computation.

 - The **Apache Commons** frameworks help in this I/O scenario.
 - **Google Guice** is used for the dependency injection pattern.
 - For the Unit Tests **Junit** and **Hamcrest** are used.


Example
-------

An input file example (containing 3 lines) might be:

    add 2
    multiply 3
    apply 3

The system still works with negative numbers e.g. for the operation: `add -3`.


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

