import org.json.simple.JSONObject;

import java.util.List;

public class SerchItemMovie extends SearchItem {
    private String director;       //specifies director(s) of the movie
    private List<String> writers;  //specifies writer(s) of the movie
    private List<String> stars;    //specifies star(s) of the movie
    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public List<String> getWriters() {
        return writers;
    }

    public void setWriters(List<String> writers) {
        this.writers = writers;
    }

    public List<String> getStars() {
        return stars;
    }

    public void setStars(List<String> stars) {
        this.stars = stars;
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
