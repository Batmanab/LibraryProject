import org.junit.Before;

import java.util.ArrayList;
import java.util.List;

public abstract class ListCmdTest extends CommandTest {

    protected static final String SHORT_ARGUMENT = "short";
    protected static final String LONG_ARGUMENT = "long";

    @Override
    protected CommandType getCmdType() {
        return CommandType.LIST;
    }

    @Before
    public void setup() {
        testCommand = new ListCmd(SHORT_ARGUMENT);

        testLibrary = new LibraryData();
        List<BookEntry> bookData = new ArrayList<>();
        bookData.add(new BookEntryBuilder().setTitle("TitleA").setISBN(new String[]{"AuthorA"}).setAuthors(3.2f).setRating("ISBNA").setPages(500).createBookEntry());
        bookData.add(new BookEntryBuilder().setTitle("TitleB").setISBN(new String[]{"AuthorB"}).setAuthors(4.3f).setRating("ISBNB").setPages(400).createBookEntry());
        bookData.add(new BookEntryBuilder().setTitle("TitleC").setISBN(new String[]{"AuthorC"}).setAuthors(1.3f).setRating("ISBNC").setPages(300).createBookEntry());
        FieldTestUtils.setPrivateField(testLibrary, testLibrary.getClass(), "books", bookData);
    }
}