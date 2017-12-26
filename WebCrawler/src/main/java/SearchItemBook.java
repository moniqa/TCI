import org.json.simple.JSONObject;

import java.util.List;

public class SearchItemBook extends SearchItem {
    private List<String> autors; //specifies athor(s) of the book
    private List<String> publisher; //specifies publisher(s) of the book
    private String isbn; //specifies ISBN number of the book; saved as String as it contains special character "-"

    public List<String> getAutors() {
        return autors;
    }

    public void setAutors(List<String> autors) {
        this.autors = autors;
    }

    public List<String> getPublisher() {
        return publisher;
    }

    public void setPublisher(List<String> publisher) {
        this.publisher = publisher;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Override
    public JSONObject convertToJSon() {
        JSONObject obj = super.convertToJSon();
        obj.put ("authors", this.getAutors());
        obj.put("publisher", this.getPublisher());
        obj.put("ISBN", this.getIsbn());
        return  obj;
    }
}
