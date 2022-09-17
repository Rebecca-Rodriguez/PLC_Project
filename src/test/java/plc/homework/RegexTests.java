package plc.homework;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * Contains JUnit tests for {@link Regex}. A framework of the test structure
 * is provided, you will fill in the remaining pieces.
 *
 * To run tests, either click the run icon on the left margin, which can be used
 * to run all tests or only a specific test. You should make sure your tests are
 * run through IntelliJ (File > Settings > Build, Execution, Deployment > Build
 * Tools > Gradle > Run tests using <em>IntelliJ IDEA</em>). This ensures the
 * name and inputs for the tests are displayed correctly in the run window.
 */
public class RegexTests {
    /**
     * This is a parameterized test for the {@link Regex#EMAIL} regex. The
     * {@link ParameterizedTest} annotation defines this method as a
     * parameterized test, and {@link MethodSource} tells JUnit to look for the
     * static method {@link #testEmailRegex()}.
     * <p>
     * For personal preference, I include a test name as the first parameter
     * which describes what that test should be testing - this is visible in
     * IntelliJ when running the tests (see above note if not working).
     */
    @ParameterizedTest
    @MethodSource
    public void testEmailRegex(String test, String input, boolean success) {
        test(input, Regex.EMAIL, success);
    }

    /**
     * This is the factory method providing test cases for the parameterized
     * test above - note that it is static, takes no arguments, and has the same
     * name as the test. The {@link Arguments} object contains the arguments for
     * each test to be passed to the function above.
     */
    public static Stream<Arguments> testEmailRegex() {
        return Stream.of(
                Arguments.of("Alphanumeric", "thelegend27@gmail.com", true),
                Arguments.of("Just Numbers", "123456789@yahoo.com", true),
                Arguments.of("UF Domain", "otherdomain@ufl.edu", true),
                Arguments.of("Period", "a.b@gmail.com", true),
                Arguments.of("Uppercase", "THEQUEEN@yahoo.com", true),
                Arguments.of("Dash", "a-b@gmail.com", true),
                Arguments.of("Underscore", "a_b@gmail.com", true),
                Arguments.of("Uppercase in domain", "something@COP.edu", true),
                Arguments.of("Numbers in domain", "something@COP4020.edu", true),
                Arguments.of("Dash in domain", "something@COP-4020.edu", true),
                Arguments.of("No domain", "something@.com", true),
                Arguments.of("Missing Domain Dot", "missingdot@gmailcom", false),
                Arguments.of("Symbols", "symbols#$%@gmail.com", false),
                Arguments.of("Symbols in domain", "symbols@gmail##.com", false),
                Arguments.of("Symbols in .com", "symbols@gmail.##com", false),
                Arguments.of("Uppercase in .com", "upper@yahoo.COM", false),
                Arguments.of("No name", "@gmail.com", false),
                Arguments.of("No @", "somethinggmail.com", false),
                Arguments.of("Too long .com", "something@gmail.commmmm", false),
                Arguments.of("Too short .com", "something@gmail.c", false)
        );
    }

    @ParameterizedTest
    @MethodSource
    public void testEvenStringsRegex(String test, String input, boolean success) {
        test(input, Regex.EVEN_STRINGS, success);
    }

    public static Stream<Arguments> testEvenStringsRegex() {
        return Stream.of(
                //what has ten letters and starts with gas?
                Arguments.of("10 Characters", "automobile", true),
                Arguments.of("14 Characters", "i<3pancakes10!", true),
                Arguments.of("16 Characters", "i<3pancakes10!!!", true),
                Arguments.of("18 Characters", "i<3pancakes10!!!!!", true),
                Arguments.of("20 Characters", "i<3pancakes10!!!!!!!", true),
                Arguments.of("Symbols", "!@#$%^&*()", true),
                Arguments.of("Numbers", "1234567891", true),
                Arguments.of("6 Characters", "6chars", false),
                Arguments.of("13 Characters", "i<3pancakes9!", false),
                Arguments.of("21 Characters", "abcdefghijklmnopqrstu", false),
                Arguments.of("Empty string", "", false),
                Arguments.of("Just a space", " ", false)
        );
    }

    @ParameterizedTest
    @MethodSource
    public void testIntegerListRegex(String test, String input, boolean success) {
        test(input, Regex.INTEGER_LIST, success);
    }

    public static Stream<Arguments> testIntegerListRegex() {
        return Stream.of(
                Arguments.of("Single Element", "[1]", true),
                Arguments.of("Multiple Elements, no spaces", "[1,2,3]", true),
                Arguments.of("Empty Brackets", "[]", true),
                Arguments.of("Mixed spaces", "[1,2, 3]", true),
                Arguments.of("Multiple ELements, all spaces", "[1, 2, 3]", true),
                Arguments.of("Missing Brackets", "1,2,3", false),
                Arguments.of("Missing Commas", "[1 2 3]", false),
                Arguments.of("Miss-match Brackets", "[1,2,3", false),
                Arguments.of("Miss-match Commas", "[1, 2 3]", false),
                Arguments.of("Trailing Comma", "[1,2,3,]", false),
                Arguments.of("Letters", "[a,b,c]", false),
                Arguments.of("Double Commas", "[1,,2]", false),
                Arguments.of("Negative Numbers", "[-1,-2,-3]", false),
                Arguments.of("Non-integers", "[1.2, 3.4, 5.6]", false),
                Arguments.of("Double Space", "[1,  2,  3]", false),
                Arguments.of("Symbols besides commas and brackets", "[!, @, #]", false)
        );
    }

    @ParameterizedTest
    @MethodSource
    public void testNumberRegex(String test, String input, boolean success) {
        //throw new UnsupportedOperationException();
        test(input, Regex.NUMBER, success);
    }

    public static Stream<Arguments> testNumberRegex() {
        //throw new UnsupportedOperationException();
        return Stream.of(
                Arguments.of("Integer", "1", true),
                Arguments.of("Decimal", "123.456", true),
                Arguments.of("Negative decimal", "-1.0", true),
                Arguments.of("Positive decimal", "+1.0", true),
                Arguments.of("Zero", "0.0", true),
                Arguments.of("Leading Zeroes", "001.2", true),
                Arguments.of("Trailing Zeroes", "1.2000", true),
                Arguments.of("Trailing Decimal", "1.", false),
                Arguments.of("Leading Decimal", ".1", false),
                Arguments.of("Multiple Decimals", "1.1.1", false),
                Arguments.of("Non-numbers", "abc.de", false),
                Arguments.of("Symbols", "/.?", false),
                Arguments.of("Fractions", "1/2", false)
        );
    }

    @ParameterizedTest
    @MethodSource
    public void testStringRegex(String test, String input, boolean success) {
        //throw new UnsupportedOperationException();
        test(input, Regex.STRING, success);
    }

    public static Stream<Arguments> testStringRegex() {
        //throw new UnsupportedOperationException();
        return Stream.of(
                Arguments.of("Empty String", "\"\"", true),
                Arguments.of("Regular String", "\"Hello, World!\"", true),
                Arguments.of("t Escape Sequence", "\"1\\t2\"", true),
                Arguments.of("b Escape Sequence", "\"1\\b2\"", true),
                Arguments.of("n Escape Sequence", "\"1\\n2\"", true),
                Arguments.of("r Escape Sequence", "\"1\\r2\"", true),
                Arguments.of("' Escape Sequence", "\"1\\'2\"", true),
                Arguments.of("Symbols", "\"@#$%\"", true),
                Arguments.of("Numbers", "\"1234\"", true),
                Arguments.of("Unterminated String", "\"unterminated", false),
                Arguments.of("Invalid Escape", "\"invalid\\escape\"", false),
                Arguments.of("No quotes", "Hello", false),
                Arguments.of("Empty String No quotes", "", false),
                Arguments.of("Missing Beginning Quote", "Hello\"", false)
        );
    }

    /**
     * Asserts that the input matches the given pattern. This method doesn't do
     * much now, but you will see this concept in future assignments.
     */
    private static void test(String input, Pattern pattern, boolean success) {
        Assertions.assertEquals(success, pattern.matcher(input).matches());
    }
}
