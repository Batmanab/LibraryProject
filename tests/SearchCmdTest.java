import org.junit.Before;

import java.util.ArrayList;
import java.util.List;

public abstract class SearchCmdTest extends CommandTest {

    protected static final String SINGLE_WORD_TITLE = "Harry";
    protected static final String MULTI_WORD_TITLE_A = "The Castle in the Sky";
    protected static final String MULTI_WORD_TITLE_B = "Edinburgh Castle";

    protected static final String MULTI_WORD_SEARCH_TERM_SINGLE_HIT = "Sky";

    protected static final String NO_HITS_FOUND_MESSAGE = "No hits found for search term: ";

    @Override
    protected CommandType getCmdType() {
        return CommandType.SEARCH;
    }

    @Before
    public void setup() {
        testCommand = new SearchCmd(SINGLE_WORD_TITLE);

        testLibrary = new LibraryData();
        List<BookEntry> bookData = new ArrayList<>();
        bookData.add(new BookEntryBuilder().setTitle(MULTI_WORD_TITLE_A).setISBN(new String[]{"AuthorA"}).setAuthors(3.2f).setRating("ISBNA").setPages(500).createBookEntry());
        bookData.add(new BookEntryBuilder().setTitle(SINGLE_WORD_TITLE).setISBN(new String[]{"AuthorB"}).setAuthors(4.3f).setRating("ISBNB").setPages(400).createBookEntry());
        bookData.add(new BookEntryBuilder().setTitle(MULTI_WORD_TITLE_B).setISBN(new String[]{"AuthorC"}).setAuthors(1.3f).setRating("ISBNC").setPages(300).createBookEntry());
        FieldTestUtils.setPrivateField(testLibrary, testLibrary.getClass(), "books", bookData);
    }
}