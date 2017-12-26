

import org.jcp.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.xml.soap.Node;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class WebCrawler {

    private List<SearchItem> listAllItems; // contains all items retrieved from the website

    public WebCrawler() {
        this.listAllItems = new LinkedList<>(); // initialize list
    }

    //all tracked classes
    //needed to convert data properly as each class has its own JSON representation
    private enum trackedClasses {
        SearchItemMovie, SearchItemBook, SearchItemMusic
    }

    ;

    private void loadItems() {
        //TODO
        //set up loading all items
        //will be implemented API will be ready
    }


    private List<SearchItem> generalSearch() {
        // contains 1 item of each genre
        List<SearchItem> foundItems = new ArrayList<>();
        // contains genre which are already found
        //it is needed as general search must give back only one item of each genre
        List<String> genreFound = new ArrayList<>();

        // filter items based on genre to do not add 2 items of same genre
        this.listAllItems.stream().filter((item) -> !genreFound.contains(item.getGenre()))
                // at this point we have only items of genre which are not added yet
                .forEachOrdered((item) -> {
                    //add item to the list which should be rerurned
                    foundItems.add(item);
                    //add genre to the list, which means "genre is already found"
                    genreFound.add(item.getGenre());
                });

        return foundItems;

    }

    private List<SearchItem> searchByName(String name) {
        // contains item(s) which have name which is searched
        List<SearchItem> foundItems = new ArrayList<>();
        // filter items based on name
        this.listAllItems.stream().filter((item) -> item.getName().equals(name))
                // at this point we have only items which correspond to searchable name
                .forEachOrdered((item) -> {
                    //add item to the list which should be rerurned
                    foundItems.add(item);
                });

        return foundItems;

    }

    private List<SearchItem> searchByGenre(String genre) {
        // contains item(s) which have genre which is searched
        List<SearchItem> foundItems = new ArrayList<>();

        // filter items based on genre to do not add 2 items of same genre
        this.listAllItems.stream().filter((item) -> item.getGenre().equals(genre))
                // at this point we have only items which correspond to searchable genre
                .forEachOrdered((item) -> {
                    //add item to the list which should be rerurned
                    foundItems.add(item);
                });

        return foundItems;
    }


    private JSONObject convertFoundItemToJSONObject(List<SearchItem> foundItems) {
        JSONObject object = new JSONObject();

        foundItems.forEach((item) -> {
            trackedClasses classes = trackedClasses.valueOf(item.getClass().getSimpleName());
            switch (classes) {
                case SearchItemMusic:
                    object.put("music", item.convertToJSon());
                case SearchItemBook:
                    object.put("book", item.convertToJSon());
                case SearchItemMovie:
                    object.put("movie", item.convertToJSon());
            }
        });

        return object;
    }

    public JSONObject getGeneralSearchResult() {
        return this.convertFoundItemToJSONObject(this.generalSearch());
    }

    public JSONObject getNameSearchResult(String name) {
        return this.convertFoundItemToJSONObject(this.searchByName(name));
    }

    public JSONObject getGenreSearchResult(String genre) {
        return this.convertFoundItemToJSONObject(this.searchByGenre(genre));
    }


    public static void main(String args[]) {
        System.out.println("Application started");
    }
}
