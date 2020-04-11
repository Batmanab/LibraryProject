import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class BookEntryBasicTest extends BookEntryTest {

    // ------------------------- check fields --------------------

    @Test
    public void testFieldTypes() {
        for (int i = 0; i < BOOK_ENTRY_FIELD_NAMES.length; i++) {
            FieldTestUtils.checkFieldType(testBook, BOOK_ENTRY_FIELD_TYPES[i], BOOK_ENTRY_FIELD_NAMES[i]);
        }
    }

    // ------------------------- check constructor --------------------

    @Test
    public void testCtorFieldInitialisation() {
        BookEntryTestUtils.checkBookFieldValues(testBook, BOOK_ENTRY_FIELD_NAMES, BOOK_ENTRY_FIELD_VALUES);
    }

    // ------------------------- check getters --------------------

    @Test
    public void testGetTitle() {
        String fieldName = TITLE_FIELD_NAME;
        String expected = "Test Title";
        FieldTestUtils.setPrivateField(testBook, testBook.getClass(), fieldName, expected);

        String actual = testBook.getTitle();
        assertEquals("Unexpected " + fieldName + " returned by getter.", expected, actual);
    }

    @Test
    public void testGetAuthors() {
        String fieldName = AUTHORS_FIELD_NAME;
        String[] expected = { "Test Author A", "Test Author B" };
        FieldTestUtils.setPrivateField(testBook, testBook.getClass(), fieldName, expected);

        String[] actual = testBook.getAuthors();
        assertArrayEquals("Unexpected " + fieldName + " returned by getter.", expected, actual);
    }

    @Test
    public void testGetRating() {
        String fieldName = RATING_FIELD_NAME;
        float expected = 2.3f;
        FieldTestUtils.setPrivateField(testBook, testBook.getClass(), fieldName, expected);

        float actual = testBook.getRating();
        assertEquals("Unexpected " + fieldName + " returned by getter.", expected, actual, CMP_DELTA);
    }

    @Test
    public void testGetISBN() {
        String fieldName = ISBN_FIELD_NAME;
        String expected = "158234681X";
        FieldTestUtils.setPrivateField(testBook, testBook.getClass(), fieldName, expected);

        String actual = testBook.getISBN();
        assertEquals("Unexpected " + fieldName + " returned by getter.", expected, actual);
    }

    @Test
    public void testGetPages() {
        String fieldName = PAGES_FIELD_NAME;
        int expected = 123;
        FieldTestUtils.setPrivateField(testBook, testBook.getClass(), fieldName, expected);

        int actual = testBook.getPages();
        assertEquals("Unexpected " + fieldName + " returned by getter.", expected, actual);
    }

    // ------------------------- check equals and hash code --------

    private void checkEquality(BookEntry bookA, BookEntry bookB, String field, boolean expected) {
        if (expected) {
            assertTrue("True return value expected for same fields.", bookA.equals(bookB) && bookB.equals(bookA));
            assertEquals("Hashcode expected to be the same for objects with the same state.", bookA.hashCode(), bookB.hashCode());
        } else {
            assertTrue("False return value expected for different " + field + ".", !bookA.equals(bookB) && !bookB.equals(bookA));
            assertNotEquals("Hashcode should be different for objects with different state.", bookA.hashCode(), bookB.hashCode());
        }
    }

    @Test
    public void testEqualsAndHashCode() {
        BookEntry bookA = new BookEntryBuilder().setTitle(DEFAULT_TITLE).setISBN(DEFAULT_AUTHORS).setAuthors(DEFAULT_RATING).setRating(DEFAULT_ISBN).setPages(DEFAULT_PAGES).createBookEntry();
        BookEntry bookB = new BookEntryBuilder().setTitle(DEFAULT_TITLE).setISBN(DEFAULT_AUTHORS).setAuthors(DEFAULT_RATING).setRating(DEFAULT_ISBN).setPages(DEFAULT_PAGES).createBookEntry();

        assertTrue("True return value expected for same book instance.", bookA.equals(bookA) && bookB.equals(bookB));
        assertEquals("Hashcode expected to be the same for same instance.", bookA.hashCode(), bookA.hashCode());

        assertFalse("False return value expected if compared to different object type.", bookA.equals("test"));
        assertFalse("False return value expected if compared to null.", bookA.equals(null));

        checkEquality(bookA, bookB, TITLE_FIELD_NAME, true);

        bookA = new BookEntryBuilder().setTitle("Title A").setISBN(DEFAULT_AUTHORS).setAuthors(DEFAULT_RATING).setRating(DEFAULT_ISBN).setPages(DEFAULT_PAGES).createBookEntry();
        bookB = new BookEntryBuilder().setTitle("Title B").setISBN(DEFAULT_AUTHORS).setAuthors(DEFAULT_RATING).setRating(DEFAULT_ISBN).setPages(DEFAULT_PAGES).createBookEntry();

        checkEquality(bookA, bookB, TITLE_FIELD_NAME, false);
        
        bookA = new BookEntryBuilder().setTitle(DEFAULT_TITLE).setISBN(new String[]{"Author A"}).setAuthors(DEFAULT_RATING).setRating(DEFAULT_ISBN).setPages(DEFAULT_PAGES).createBookEntry();
        bookB = new BookEntryBuilder().setTitle(DEFAULT_TITLE).setISBN(new String[]{"Author B"}).setAuthors(DEFAULT_RATING).setRating(DEFAULT_ISBN).setPages(DEFAULT_PAGES).createBookEntry();

        checkEquality(bookA, bookB, AUTHORS_FIELD_NAME, false);

        bookA = new BookEntryBuilder().setTitle(DEFAULT_TITLE).setISBN(new String[]{"Author A"}).setAuthors(DEFAULT_RATING).setRating(DEFAULT_ISBN).setPages(DEFAULT_PAGES).createBookEntry();
        bookB = new BookEntryBuilder().setTitle(DEFAULT_TITLE).setISBN(new String[]{"Author A", "Author B"}).setAuthors(DEFAULT_RATING).setRating(DEFAULT_ISBN).setPages(DEFAULT_PAGES).createBookEntry();

        checkEquality(bookA, bookB, AUTHORS_FIELD_NAME, false);
    
        bookA = new BookEntryBuilder().setTitle(DEFAULT_TITLE).setISBN(DEFAULT_AUTHORS).setAuthors(2f).setRating(DEFAULT_ISBN).setPages(DEFAULT_PAGES).createBookEntry();
        bookB = new BookEntryBuilder().setTitle(DEFAULT_TITLE).setISBN(DEFAULT_AUTHORS).setAuthors(3f).setRating(DEFAULT_ISBN).setPages(DEFAULT_PAGES).createBookEntry();

        checkEquality(bookA, bookB, RATING_FIELD_NAME, false);

        bookA = new BookEntryBuilder().setTitle(DEFAULT_TITLE).setISBN(DEFAULT_AUTHORS).setAuthors(DEFAULT_RATING).setRating("1400054036").setPages(DEFAULT_PAGES).createBookEntry();
        bookB = new BookEntryBuilder().setTitle(DEFAULT_TITLE).setISBN(DEFAULT_AUTHORS).setAuthors(DEFAULT_RATING).setRating("439554896").setPages(DEFAULT_PAGES).createBookEntry();

        checkEquality(bookA, bookB, ISBN_FIELD_NAME, false);

        bookA = new BookEntryBuilder().setTitle(DEFAULT_TITLE).setISBN(DEFAULT_AUTHORS).setAuthors(DEFAULT_RATING).setRating(DEFAULT_ISBN).setPages(300).createBookEntry();
        bookB = new BookEntryBuilder().setTitle(DEFAULT_TITLE).setISBN(DEFAULT_AUTHORS).setAuthors(DEFAULT_RATING).setRating(DEFAULT_ISBN).setPages(400).createBookEntry();

        checkEquality(bookA, bookB, PAGES_FIELD_NAME, false);
    }

    // ------------------------- check toString --------------------

    @Test
    public void testToStringReturnValue() {
        String expectedResult = DEFAULT_TOSTRING_RESULT;
        String actualResult = testBook.toString();

        // ignore leading and trailing white spaces
        // and correct for potential Windows line endings
        assertEquals("ToString result not as expected.", expectedResult.replaceAll("\r", "").trim(),
                actualResult.replaceAll("\r", "").trim());
    }
}
