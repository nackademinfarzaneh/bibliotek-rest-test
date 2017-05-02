package se.nackademin.bibliotek.rest.test;

import static com.jayway.restassured.RestAssured.delete;
import static com.jayway.restassured.RestAssured.post;
import static com.jayway.restassured.RestAssured.put;
import static com.jayway.restassured.RestAssured.given;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import java.util.Random;
import java.util.UUID;

public class Book_AuthorOperations {

    private static final String BASE_URL = "http://localhost:8080/librarytest/rest/";
    private String jsonString = "";

    public Response getBook(int id) {

        String resourceName = "books/" + id;
        Response response = given().accept(ContentType.JSON).log().all().get(BASE_URL + resourceName);
        return response;
    }

    public String getLatestJsonString() {
        return jsonString;
    }

    public Response CreateNewBook(SingleBook singleBook) {

        Response response = resoursCreator("books");
        return response;
    }

    public Response getAllBooks() { //ok lör
        Response response = resoursGetter("books");
        return response;
    }

    public Response getBookById(Integer id) {
        Response response = resoursGetter("books", id);

        return response;
    }

    public Response putBook(SingleBook singleBook) {
        Response response = resoursPutter("books", singleBook);
        return response;
    }

    public Response deleteBook(int id) {

        String deleteResourceName = "books/" + id;
        Response deleteResponse = delete(BASE_URL + deleteResourceName);
        return deleteResponse;
    }

    public Response postBook(int id) {

        String postResourceName = "books/ + id";
        Response postResponse = post(BASE_URL + postResourceName);
        return postResponse;
    }

    //Author operation
    public Response getAllAuthors() {
        Response response = resoursGetter("authors");
        return response;
    }

    public Response postAuthor(SingleAuthor singleAuthor) {
        Response response = resoursPoster("authors", singleAuthor);
        return response;
    }

    public Response getBooksByAuthor(Integer id) {
        Response response = resoursGetter("books/byauthor", id);
        return response;
    }
         
    public Response postAuthorToBook(Integer bookId, SingleBook singleBook) {
        Response response = resoursPoster("books/" + bookId + "/authors", singleBook);
        return response;
    }

    //post, put, get, delete metoder
    private static Response resoursCreator(String resourceName) {
        Response response = given().contentType(ContentType.JSON).log().all().post(BASE_URL + resourceName).prettyPeek();
        return response;
    }

    private static Response resoursGetter(String resourceName, Integer id) {
        Response response = given().accept(ContentType.JSON).log().all().get(BASE_URL + resourceName + "/" + id).prettyPeek();
        return response;
    }

    private static Response resoursGetter(String resourceName) { //ok lör
        Response response = given().accept(ContentType.JSON).log().all().get(BASE_URL + resourceName).prettyPeek();
        return response;
    }

    private static Response resoursPutter(String resourceName, Object obj) {
        Response response = given().contentType(ContentType.JSON).body(obj).log().all().put(BASE_URL + resourceName).prettyPeek();
        return response;
    }

    private static Response resoursPoster(String resourceName, Object obj) {
        Response response = given().contentType(ContentType.JSON).body(obj).log().all().post(BASE_URL + resourceName).prettyPeek();
        return response;
    }

    public Response createRandomBook() {
        String resourceName = "books";
        String title = UUID.randomUUID().toString();
        String description = UUID.randomUUID().toString();
        String isbn = UUID.randomUUID().toString();

        String postBodyTemplate = "{\n"
                + "\"book\":\n"
                + "  {\n"
                + "    \"description\":\"%s\",\n"
                + "    \"isbn\":\"%s\",\n"
                + "    \"nbOfPage\":%s,\n"
                + "    \"title\":\"%s\"\n"
                + "  }\n"
                + "}";
        String postBody = String.format(postBodyTemplate, description, isbn, "" + new Random().nextInt(500), title);
        jsonString = postBody;

        Response postResponse = given().contentType(ContentType.JSON).body(postBody).post(BASE_URL + resourceName);
        return postResponse;
    }

    public Response postAuthorToBook(Integer bookId, SingleAuthor singleAuthor) {
        Response response = resoursPoster("books/" + bookId + "/authors", singleAuthor);
        return response;
    }

    public Response getAuthor(Integer id) {
        Response response = resoursGetter("authors", id);
        return response;
    }


  
}
