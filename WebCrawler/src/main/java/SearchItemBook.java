import org.json.simple.JSONObject;

import java.util.List;

public class SearchItemBook extends SearchItem {
    private List<String> authors; //specifies authors of the book
    private List<String> publisher; //specifies publisher(s) of the book
    private String isbn; //specifies ISBN number of the book; saved as String as it contains special character "-"

    public SearchItemBook(String name, String genre, String format, int year,
                          List<String> authors, List<String> publisher, String isbn) {
        super(name, genre, format, year);
        this.authors = authors;
        this.publisher = publisher;
        this.isbn=isbn;
    }

    public List<String> getAuthors() {
        return authors;
    }

      public List<String> getPublisher() {
        return publisher;
    }

    public String getIsbn() {
        return isbn;
    }

    @Override
    public JSONObject convertToJSon() {
        JSONObject obj = super.convertToJSon();
        obj.put ("authors", this.getAuthors());
        obj.put("publisher", this.getPublisher());
        obj.put("ISBN", this.getIsbn());
        return  obj;
    }
}
