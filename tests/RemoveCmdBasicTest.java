import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class RemoveCmdBasicTest extends RemoveCmdTest {

    @Before
    public void setup() {
        testCommand = new RemoveCmd(TITLE_ARGUMENT + " " + TITLE_VALUE_ARGUMENT);

        testLibrary = new LibraryData();
        List<BookEntry> bookData = new ArrayList<>();
        bookData.add(new BookEntryBuilder().setTitle("TitleA").setISBN(new String[]{"AuthorA"}).setAuthors(3.2f).setRating("ISBNA").setPages(500).createBookEntry());
        bookData.add(new BookEntryBuilder().setTitle(TITLE_VALUE_ARGUMENT).setISBN(new String[]{AUTHOR_VALUE_ARGUMENT}).setAuthors(4.3f).setRating("ISBNB").setPages(400).createBookEntry());
        bookData.add(new BookEntryBuilder().setTitle("TitleC").setISBN(new String[]{"AuthorC"}).setAuthors(1.3f).setRating("ISBNC").setPages(300).createBookEntry());
        FieldTestUtils.setPrivateField(testLibrary, testLibrary.getClass(), "books", bookData);
    }

    // ------------------------- parseArguments tests --------------------

    @Test
    public void testParseArgumentsIllegalArgument() {
        String blankArg = "";
        CommandTestUtils.checkArgumentInput(testCommand, false, blankArg);
        CommandTestUtils.checkArgumentInput(testCommand, false, TITLE_ARGUMENT + " " + blankArg);
        CommandTestUtils.checkArgumentInput(testCommand, false, AUTHOR_ARGUMENT + " " + blankArg);
        CommandTestUtils.checkArgumentInput(testCommand, false, "nonsense");
    }

    @Test
    public void testParseArgumentsLegalArgument() {
        String[] valueArgs = new String[] { TITLE_VALUE_ARGUMENT, AUTHOR_VALUE_ARGUMENT, GENERIC_VALUE_ARGUMENT };
        String[] typeArgs = new String[] { TITLE_ARGUMENT, AUTHOR_ARGUMENT };

        for (String typeArg : typeArgs) {
            for (String valueArg : valueArgs) {
                CommandTestUtils.checkArgumentInput(testCommand, true, typeArg + " " + valueArg);
            }
        }
    }

    // ------------------------- execute tests --------------------

    @Test
    public void testExecuteRemoveTitle() {
        checkRemoveTitleExecute(testCommand, testLibrary, TITLE_VALUE_ARGUMENT);
    }

    @Test
    public void testExecuteRemoveTitleConsoleOut() {
        String expectedConsoleOutput = String.format(TITLE_REMOVE_MESSAGE, TITLE_VALUE_ARGUMENT);
        CommandTestUtils.checkExecuteConsoleOutput(testCommand, testLibrary, expectedConsoleOutput);
    }

    @Test
    public void testExecuteRemoveAuthor() {
        testCommand = new RemoveCmd(AUTHOR_ARGUMENT + " " + AUTHOR_VALUE_ARGUMENT);
        checkRemoveAuthorExecute(testCommand, testLibrary, AUTHOR_VALUE_ARGUMENT);
    }

    @Test
    public void testExecuteRemoveAuthorConsoleOut() {
        testCommand = new RemoveCmd(AUTHOR_ARGUMENT + " " + AUTHOR_VALUE_ARGUMENT);

        int removedAuthors = 1;
        String expectedConsoleOutput = String.format(AUTHOR_REMOVE_MESSAGE, removedAuthors, AUTHOR_VALUE_ARGUMENT);
        CommandTestUtils.checkExecuteConsoleOutput(testCommand, testLibrary, expectedConsoleOutput);
    }

    @Test
    public void testExecuteNotFound() {
        checkEntryNotFound();
    }
}
