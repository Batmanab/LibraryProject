import org.junit.Before;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public abstract class GroupCmdTest extends CommandTest {

    protected static final String GROUP_HEADER_OUTPUT = "Grouped data by %s";
    protected static final String GROUP_TITLE_PREFIX = "## ";

    @Override
    protected CommandType getCmdType() {
        return CommandType.GROUP;
    }

    @Before
    public void setup() {
        testCommand = new GroupCmd(TITLE_ARGUMENT);

        testLibrary = new LibraryData();
        List<BookEntry> bookData = new ArrayList<>();
        bookData.add(new BookEntryBuilder().setTitle("B Title").setISBN(new String[]{"B Author"}).setAuthors(3.2f).setRating("ISBNB").setPages(500).createBookEntry());
        bookData.add(new BookEntryBuilder().setTitle("A Title").setISBN(new String[]{"A Author"}).setAuthors(4.3f).setRating("ISBNA").setPages(400).createBookEntry());
        bookData.add(new BookEntryBuilder().setTitle("B Title 2").setISBN(new String[]{"B Author"}).setAuthors(4.3f).setRating("ISBNB2").setPages(400).createBookEntry());
        bookData.add(new BookEntryBuilder().setTitle("C Title").setISBN(new String[]{"C Author"}).setAuthors(1.3f).setRating("ISBNC").setPages(300).createBookEntry());
        bookData.add(new BookEntryBuilder().setTitle("A Title 2").setISBN(new String[]{"A Author"}).setAuthors(1.3f).setRating("ISBNA2").setPages(300).createBookEntry());
        bookData.add(new BookEntryBuilder().setTitle("D Title").setISBN(new String[]{"D Author"}).setAuthors(1.3f).setRating("ISBND").setPages(300).createBookEntry());
        bookData.add(new BookEntryBuilder().setTitle("A Title 3").setISBN(new String[]{"A Author"}).setAuthors(1.3f).setRating("ISBNA3").setPages(300).createBookEntry());
        bookData.add(new BookEntryBuilder().setTitle("F Title").setISBN(new String[]{"F Author"}).setAuthors(1.3f).setRating("ISBNF").setPages(300).createBookEntry());
        bookData.add(new BookEntryBuilder().setTitle("E Title").setISBN(new String[]{"E Author"}).setAuthors(1.3f).setRating("ISBNE").setPages(300).createBookEntry());
        FieldTestUtils.setPrivateField(testLibrary, testLibrary.getClass(), "books", bookData);
    }

    // ------------------------- test helpers ----------------------------

    protected void checkOutputLineCount(int expectedLines) {
        String[] executeStdOutLines = CommandTestUtils.captureExecuteStdOutputLines(testCommand, testLibrary);
        assertEquals("Unexpected amount of output lines for group output.", expectedLines, executeStdOutLines.length);
    }

    protected void checkOutputLinesIgnoringOrder(String[] expectedOutputLines) {
        CommandTestUtils.checkExecuteConsoleOutputLines(expectedOutputLines, testCommand, testLibrary);
        checkOutputLineCount(expectedOutputLines.length);
    }

    protected void checkGroupOutputOrder(String[] executeStdOutLines, List<String> expectedGroups) {
        List<String> actualGroups = new ArrayList<>();

        for (int i = 0; i < executeStdOutLines.length; i++) {
            String currentLine = executeStdOutLines[i].strip();
            if (currentLine.startsWith(GROUP_TITLE_PREFIX)) {
                actualGroups.add(currentLine.substring(GROUP_TITLE_PREFIX.length()));
            }
        }

        assertEquals("Groups not printed in expected order.", expectedGroups, actualGroups);
    }
}