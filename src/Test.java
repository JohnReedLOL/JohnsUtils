
import info.collaboration_station.utilities.BackgroundEvent;
import info.collaboration_station.utilities.Printer;
import info.collaboration_station.utilities.Tester;

/**
 * Tests the printer and the tester.
 *
 * @author johnmichaelreed2
 */
public class Test {

    public static void main(String[] args) throws Exception {
        Printer.setMyDefaultPrintStream(Printer.DefaultPrintStream.BOTH);
        Printer.printGood("This is good. It prints in black.");
        Thread.sleep(10);
        Printer.printBad("This is bad. It prints in red");
        Printer.setMyDefaultPrintStream(Printer.DefaultPrintStream.ONLY_STANDARD_ERROR);
        Printer.printGood("Now everything prints in red, even the good stuff.");
        Printer.printBad("And the bad stuff.");
        Printer.printSlightlyGood("Some things aren't important enough to be printed.");
        Printer.setMyOutputSignificanceLevel(Printer.Significance.VERY_SIGNIFICANT);
        Printer.printGood("Some things are important, but with the output level set to VERY_SIGNIFICANT, they still don't appear.");
        Printer.printVeryGood("This output level won't be ignored.");
        // set the output level back to default.
        Printer.setMyOutputSignificanceLevel(Printer.Significance.SIGNIFICANT);
        Tester.tryPollForBackgroundEventEveryXms(new BackgroundEvent() {
            int count = 1;

            @Override
            public boolean checkForEventOccurance() {
                if (count % 2 == 0) {
                    Printer.printGood("An event occured " + count / 2 + " times!");
                    return true;
                } else {
                    Printer.printSlightlyBad("No event occured.");
                    ++count;
                    return false;
                }
            }

            @Override
            public void respondToEventOccurance() {
                ++count;
                Printer.printGood("An even count was made odd.");
            }
        }, 50);
        Thread.sleep(230);
        one();
        Tester.check(1 == 2, "One is not equal to two");
    }

    public static void one() throws Exception {
        try {
            two();
        } catch(Exception e) {
            Printer.printException("I caught an exception!", e);
        }
    }

    public static void two() throws Exception  {
        three();
    }

    public static void three() throws Exception  {
        four();
    }

    public static void four() throws Exception {
        throw new Exception("Hi my name is Ex!");
    }
}
