import org.json.simple.JSONObject;

import java.util.List;

public class SearchItemMovie extends SearchItem {
    private String director;       //specifies director(s) of the movie
    private List<String> writers;  //specifies writer(s) of the movie
    private List<String> stars;    //specifies star(s) of the movie

    public SearchItemMovie(String name, String genre, String format, int year,
                           String director,List<String> writers,  List<String> stars ) {
        super(name, genre, format, year);
        this.director = director;
        this.writers = writers;
        this.stars=stars;
    }

    public String getDirector() {
        return director;
    }

    public List<String> getWriters() {
        return writers;
    }


    public List<String> getStars() {
        return stars;
    }

    @Override
    public JSONObject convertToJSon() {
        JSONObject obj = super.convertToJSon();
        obj.put("director", this.getDirector());
        obj.put("writers", this.getWriters());
        obj.put("stars", this.getStars());
        return obj;
    }
}
