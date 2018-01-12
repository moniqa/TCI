import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


import java.util.List;

public class SearchItemMusic extends SearchItem {
    private List<String> artist; //

    public SearchItemMusic(String name, String genre, String format, int year,
                           List<String> artist) {
        super(name, genre, format, year);
        this.artist = artist;
    }

    public List<String> getArtist() {
        return artist;
    }

    @Override
    public JSONObject convertToJSon() {
        JSONObject obj = super.convertToJSon();
        obj.put("artist", this.getArtist());
        return obj;
    }
}
