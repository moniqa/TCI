import org.json.simple.JSONObject;

import java.util.List;

/**
 * General class of all items, which are present on the website.
 * It holds general information, which each item has (name, year, etc.)
 * Specific information, which is exclusive for separate item (stars, publishers, etc.),
 * is saved in special classes(SearchItemMovie, SearchItemBook,etc.), which extend this class
 *
 */
public class SearchItem {
    private String name; // specify name of the item
    private String genre; // specify genre of the item (book, music, etc.
    private String format; // specify forant of the item (CD, audio, etc.)
    private int year; // specify year when item was created

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }


    public JSONObject convertToJSon(){
        JSONObject obj =  new JSONObject();
        obj.put ("name", this.getName());
        obj.put ("genre", this.getGenre());
        obj.put("format", this.getFormat());
        obj.put("year", this.getYear());


        return obj;
    }



}
