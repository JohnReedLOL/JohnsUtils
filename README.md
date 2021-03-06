# JohnsUtils
My utilities library. This code is in the public domain.

# Printer

My custom java terminal/log_file printing tool. I wrote this code in college and I often use it for generating convenient, easy to debug terminal and log file output. It contains my library code that I have found useful in undergraduate level Java coding. Most notably, it solves the following annoying problems:

* #1. Not being able to find your print statements. Often times, I will include a print statement and completely forget its location. This is especially annoying if a fix needs to be done in the vicinity of that print statement. To solve that problem, my logger includes (clickable) one line stack traces with every print statement, like so:
```
Thread "main": PackageName.ClassName.receiveRepeatedl(ClassName.java:291)
Hello World
```
Using your favorite IDE, you can click on these stack traces and it will take you to the location of the print statement. Note that another nice feature of these print statements is that they all include the name of the thread in quotation marks, which I find helpful when statements are being printed by multiple threads simultaneously. There is a newline before each statement because the white space in between print statements makes them easier to read. The c

* #2. Garbled standard out and standard error. When you mix standard out and standard error, you often get out of order print statements. To keep this from happening, LogAppTester provides the option of sending ALL terminal output either only to standard out or only to standard error.
```
Printer.setMyDefaultPrintStream(Printer.DefaultPrintStream.ONLY_STANDARD_ERROR); // makes sure that no mixing of standard out and standard error occurs.
```
It even allows for formatting and printing of exception to standard out instead of standard error, which is nice if you want to know which print statement immediately proceeded the exception.

* #3. When all output is in one stream (either standard out or standard error), you don't know what is an error and what isn't, so the logger provides six different logging levels. It ranks output in terms of importance (unimportant, normal importance, or important) and whether or not it is some sort of error or irregularity. The print statements are as follows:

```
Printer.printVeryGood("This message is very good. It prints in black. It also cannot be ignored (unless you turn off all output)");
Printer.printGood("This message is good. It prints in black.");
Printer.printSlightlyGood("This message is slightly good. You can ignore it.");

Printer.setMyOutputSignificanceLevel(Printer.Significance.VERY_SIGNIFICANT); // only very significant messages will show.

Printer.printVeryBad("This message is bad. It prints in red. It also cannot be ignored (unless you turn off all output)");
Printer.printBad("This message is bad. It prints in red");
Printer.printSlightlyBad("This message is slightly bad. You can ignore it.");
```

It also allows for printing of exceptions. With the default output stream, exceptions, like errors and irregularities, are printed to standard error, but this can be configured to print to standard out, like so.

```
Printer.setMyDefaultPrintStream(Printer.DefaultPrintStream.ONLY_STANDARD_OUT); // Even exception will print to standard out.
Printer.printException("This is an exception", new Exception("Hi"));
```

* #4. With java assertions, the application keeps running even if there is something horribly wrong with it that needs immediate fixing because Java assertions only kill the thread, not the JVM. AppTester assertions are nicely formatted to your specified number of stack trace elements in the terminal (the rest are in the log file) and they kill the entire application, not just the thread.
```
// Only display three rows of stack trace.
Printer.setNumberOfRowsInStackTraces(3);
// This assertion will fail.
Tester.check(1 == 2, "One is not equal to two");
```

Output of a failed assertion looks like this:
```
Assertion failed in Thread: "main"
One is not equal to two
Test.main(Test.java:50)
Test.func(Test.java:91)
```

Note that it also creates a log file in your working directory with a timestamp and ALL output, even output not important enought to be displayed in the terminal, just in case.

Some other utility code is included as well for navigating the file system (searching for a file in the working directory whose name starts with "glob", for example), but you don't need to use that - my most used code is my Tester/Printer.

