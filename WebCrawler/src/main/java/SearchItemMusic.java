import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


import java.util.List;

public class SearchItemMusic extends SearchItem {
    private List<String> artist; //

    public List<String> getArtist() {
        return artist;
    }

    public void setArtist(List<String> artist) {
        this.artist = artist;
    }


    @Override
    public JSONObject convertToJSon() {
        JSONObject obj = super.convertToJSon();
        obj.put("artist", this.getArtist());
        return obj;
    }
}
