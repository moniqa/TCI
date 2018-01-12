import org.json.simple.JSONObject;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 */
@Path("WebCrawler")
@Singleton
public class WebCrawler {

    private List<SearchItem> listAllItems; // contains all items retrieved from the website
    private final String webLink;  //link to the website to crawl

    /**
     *
     */
    public WebCrawler() {
        webLink = "";
        this.listAllItems = new LinkedList<>();
    }

    /**
     *
     * @param webLink
     */
    public WebCrawler(String webLink) {
        this.webLink = ""; //set location of website to crawl
        this.listAllItems = new LinkedList<>(); // initialize list
    }


    /**
     * All tracked classes needed to convert data properly as each class has
     * its own JSON representation it is used to avoid using of "item instance of ..."
     */
    private enum trackedClasses {
        SearchItemMovie, SearchItemBook, SearchItemMusic
    }

    /**
     * Get all items from the website.
     * Items are placed into single list.
     * Items are divided based on which type they are (book, music etc.)
     */
    private void loadItems() {
        //TODO
        //set up loading all items
        //will be implemented API will be ready
    }

    /**
     * Search for one item of each genre
     *
     * @return list with items(only one SearchItem of each unique genre will be added)
     */
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
                    //add item to the list which should be returned
                    foundItems.add(item);
                    //add genre to the list, which means "genre is already found"
                    genreFound.add(item.getGenre());
                });
        //return all found items
        return foundItems;
    }

    /**
     * Search for a certain name in entire list of SearchItems
     *
     * @param name value to find in a list of all SearchItems
     * @return list with all items that were found (only SearchItem where name matches the searchable value will be added)
     */
    private List<SearchItem> searchByName(String name) {
        // contains item(s) which have name which is searched
        List<SearchItem> foundItems = new ArrayList<>();
        // filter items based on name
        this.listAllItems.stream().filter((item) -> item.getName().equals(name))
                // at this point we have only items which correspond to searchable name
                .forEachOrdered((item) -> {
                    //add item to the list which should be returned
                    foundItems.add(item);
                });
        //return all found items
        return foundItems;

    }

    /**
     * Search for a certain genre in entire list of SearchItems
     *
     * @param genre value to find in a list of all SearchItems
     * @return list with all items that were found (only SearchItem where genre matches the searchable value will be added)
     */
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
        //return all found items
        return foundItems;
    }

    /**
     * Convert SearchItem into JSONObject.
     * For each genre is generated specific key based on genre value
     *
     * @param foundItems contains all items which were found after certain search (searchGeneral, searchByGenre etc)
     * @return
     */
    private JSONObject convertFoundItemToJSONObject(List<SearchItem> foundItems) {
        JSONObject object = new JSONObject();

        foundItems.forEach((item) -> {
            object.put(item.getGenre(), item.convertToJSon());
          /*  trackedClasses classes = trackedClasses.valueOf(item.getClass().getSimpleName());
            switch (classes) {
                case SearchItemMusic:
                    object.put("music", item.convertToJSon());
                case SearchItemBook:
                    object.put("book", item.convertToJSon());
                case SearchItemMovie:
                    object.put("movie", item.convertToJSon());
            }*/
        });

        return object;
    }

    /**
     * @return
     */
    @GET
    @Path("generalSearch")
    @Produces(MediaType.APPLICATION_JSON)
    public JSONObject getGeneralSearchResult() {
        return this.convertFoundItemToJSONObject(this.generalSearch());
    }

    /**
     * @param name
     * @return
     */
    @GET
    @Path("searchName/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public JSONObject getNameSearchResult(@PathParam("name") String name) {
        return this.convertFoundItemToJSONObject(this.searchByName(name));
    }

    /**
     * @param genre
     * @return
     */
    @GET
    @Path("searchName/{genre}")
    @Produces(MediaType.APPLICATION_JSON)
    public JSONObject getGenreSearchResult(@PathParam("genre") String genre) {
        return this.convertFoundItemToJSONObject(this.searchByGenre(genre));
    }


    public static void main(String args[]) {
        System.out.println("Application started");
    }
}
