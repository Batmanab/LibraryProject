public class BookEntryBuilder {
    private String title;
    private String isbn;
    private String[] authors;
    private float rating;
    private int pages;

    public BookEntryBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public BookEntryBuilder setISBN(String isbn) {
        this.isbn = isbn;
        return this;
    }

    public BookEntryBuilder setAuthors(String[] authors) {
        this.authors = authors;
        return this;
    }

    public BookEntryBuilder setRating(float rating) {
        this.rating = rating;
        return this;
    }

    public BookEntryBuilder setPages(int pages) {
        this.pages = pages;
        return this;
    }

    public BookEntry createBookEntry() {
        return new BookEntry(title, isbn, authors, rating, pages);
    }
}