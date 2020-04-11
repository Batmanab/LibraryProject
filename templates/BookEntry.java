import java.util.Arrays;
import java.util.Objects;

/**
 * Immutable class encapsulating data for a single book entry.
 *
 */
public class BookEntry {
    private final String title, ISBN;
    private final String[] authors;
    private final float rating;
    private final int pages;

    public BookEntry(String title, String ISBN, String[] authors, float rating, int pages){
        if(title!= null && authors!=null && ISBN!= null && rating <=5 && rating >=0 && pages >=0 ) {
            this.title = title;
            this.ISBN = ISBN;
            this.authors = authors;
            this.rating = rating;
            this.pages = pages;
        }
        else {
            throw new NullPointerException("Read instructions");
        }
    }

    public String getISBN() {
        return ISBN;
    }

    public String[] getAuthors() {
        return authors;
    }

    public float getRating() {
        return rating;
    }

    public int getPages() {
        return pages;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "BookEntry{" +
                "title='" + title + '\'' +
                "by" + Arrays.toString(authors) +
                ", rating:" + rating +
                ", ISBN :" + ISBN + '\'' +
                 + pages + ", pages=";

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookEntry bookEntry = (BookEntry) o;
        return Float.compare(bookEntry.getRating(), getRating()) == 0 &&
                getPages() == bookEntry.getPages() &&
                getTitle().equals(bookEntry.getTitle()) &&
                getISBN().equals(bookEntry.getISBN()) &&
                Arrays.equals(getAuthors(), bookEntry.getAuthors());
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(getTitle(), getISBN(), getRating(), getPages());
        result = 31 * result + Arrays.hashCode(getAuthors());
        return result;
    }
}
